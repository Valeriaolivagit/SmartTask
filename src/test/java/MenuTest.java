import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.smarttask.GestorTareas;
import com.smarttask.Menu;
import com.smarttask.Tarea;

public class MenuTest {
    private PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;
    private Menu menu;
    private Scanner sc;
    private GestorTareas gestor;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        sc = new Scanner("1\n");
        gestor = new GestorTareas();
        menu = new Menu(true, sc, gestor);
        Tarea.resetCounter();
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }
    
    @Test
    void testMenuGetAppRunnerIsTrue() {
        assertEquals(menu.getAppRunner(), true);
    }

    @Test
    void testMenuSetAppRunner() {

        menu.setAppRunner(false);
        assertFalse(menu.getAppRunner());
    }

    @Test
    void testMenuShowOptionsOne() {
        menu.showOptions(1);
        String output = outputStream.toString();
        assertTrue(output.contains("¡Bienvenido a tu Aplicación SmartTask!"));
    }

    @Test
    void testMenuShowOptionsTwo() {
        menu.showOptions(2);
        String output = outputStream.toString();
        assertTrue(output.contains("1. Completar una tarea"));
        ;
    }

    @Test
    void testMenuShowOptionsThree() {
        menu.showOptions(3);
        String output = outputStream.toString();
        assertTrue(output.contains("Agregar una tarea"));
        ;
    }

    @Test
    void testMenuShowOptionsFourth() {
        menu.showOptions(4);
        String output = outputStream.toString();
        assertTrue(output.contains("1. Agregar otra tarea"));
        ;
    }

    @Test
    void testMenuShowOptionsFive() {
        menu.showOptions(5);
        String output = outputStream.toString();
        assertTrue(output.contains("Agradecemos tu preferencia. ¡Vuelve pronto!"));
    }

    @Test
    void testMenuShowOptionsDefault() {
        menu.showOptions(6);
        String output = outputStream.toString();
        assertTrue(output.contains("Opcion no valida"));
    }

    @Test
    void testMensajeMenuPrincipal() {
        menu.menuprincipal();
        String mensaje = outputStream.toString();
        assertTrue(mensaje.contains("Por favor, ingrese una opción: "));
    }

    @Test
    void testDeOpcionElegidaCorrectamente() {
        Scanner sc = new Scanner("2\n");
        GestorTareas gestor = new GestorTareas();
        Menu menu = new Menu(true, sc, gestor);

        int opcionElegida = menu.opcionElegida();
        assertEquals(2, opcionElegida);
    }

    @Test
    void testDeOpcionElegidaInCorrectamenteYLuegoCorrectamente() {
        Scanner sc = new Scanner("abc\n1\n");
        GestorTareas gestor = new GestorTareas();
        Menu menu = new Menu(true, sc, gestor);

        int opcionElegida = menu.opcionElegida();
        String output = outputStream.toString();
        assertTrue(output.contains("Entrada inválida. Por favor ingrese un número."));
        assertEquals(1, opcionElegida);

    }

    @Test
    void testOpcionEscogidaDeLaOpcionTres() {
        menu.opcionEscogida(3);

        assertFalse(menu.getAppRunner());
    }

    @Test
    void testOpcionEscogidaDeLaOpcionDefault() {
        menu.opcionEscogida(4);

        String mensaje = outputStream.toString();
        assertTrue(mensaje.contains("Opcion no válida. Por favor ingrese"));
    }

    @Test
    void testSalirDeLaAplicacionYMensaje() {
        menu.salirDeLaApp();

        assertFalse(menu.getAppRunner());
        String mensaje = outputStream.toString();
        assertTrue(mensaje.contains("Agradecemos tu preferencia"));
    }

    @Test
    void testShowSubMenuMensajes() {
        menu.showSubMenu();

        String mensaje = outputStream.toString();
        assertTrue(mensaje.contains("Listado de tareas Pendientes"));
    }

    @Test
    void testSubMenuOptionsOptionUnoSiNoHayTareas() {       
        boolean resultado = menu.subMenuOptions(1);

        assertTrue(resultado);
        assertTrue(outputStream.toString().contains("No hay tareas agregadas aún."));
    }

    @Test
    void testSubMenuOptionsOptionUnoSiHayTareas() {
        gestor.addTarea("Estudiar java", 1);
        boolean resultado = menu.subMenuOptions(1);

        assertTrue(resultado);
        assertTrue(outputStream.toString().contains("Completar una tarea"));
    }

    @Test
    void testSubMenuOptionsOptionDos() {
        gestor.addTarea("Estudiar java", 1);    
        
        Scanner sc = new Scanner("1\n1\nEstudiar Java\n");
        menu = new Menu(true,sc,gestor);

        boolean resultado = menu.subMenuOptions(2);

        assertTrue(resultado);
        assertTrue(outputStream.toString().contains("Editar una tarea"));
    }

    @Test
    void testSubMenuOptionsOptionTres() {
        gestor.addTarea("Estudiar java", 1);
        boolean resultado = menu.subMenuOptions(3);

        assertTrue(resultado);
        assertTrue(outputStream.toString().contains("Borrar una tarea"));
    }

    @Test
    void testSubMenuOptionsOptionCuatro() {
        boolean resultado = menu.subMenuOptions(4);

        assertTrue(resultado);
        assertTrue(outputStream.toString().contains("Listado de tareas Completadas"));
    }

    @Test
    void testSubMenuOptionsOptionCinco() {
        boolean resultado = menu.subMenuOptions(5);

        assertFalse(resultado);
        assertTrue(outputStream.toString().contains("Volviendo al menu anterior"));
    }

    @Test
    void testSubMenuOptionsOptionDefault() {
        boolean resultado = menu.subMenuOptions(6);

        assertTrue(resultado);
        assertTrue(outputStream.toString().contains("Opción no válida"));
    }

    @Test
    void testOptionAddTask() {
        Scanner sc = new Scanner("Aprender test unitarios\n1\n");
        GestorTareas gestor = new GestorTareas();
        Menu menu = new Menu(true, sc, gestor);

        menu.optionAddTask();

        assertEquals("Aprender test unitarios", gestor.getTareaPorIndice(0).getNombreTarea());
        assertEquals("Prioridad: Alta", gestor.getTareaPorIndice(0).getPrioridad());
    }

    @Test
    void testComeBackSubMenuOptionDos() {
        boolean resultado = menu.comeBackSubMenu(2);
        assertFalse(resultado);
    }

    @Test
    void testComeBackSubMenuOptionTres() {
        boolean resultado = menu.comeBackSubMenu(3);
        menu.salirDeLaApp();
        assertFalse(resultado);
        assertFalse(menu.getAppRunner());
    }

    @Test
    void testComeBackSubMenuOptionDefault() {
        boolean resultado = menu.comeBackSubMenu(4);

        assertTrue(resultado);
        assertTrue(outputStream.toString().contains("Opcion no válida."));
    }

    @Test
    void testOptionDeleteTask() {
        GestorTareas gestor = new GestorTareas();
        gestor.addTarea("Borrar tarea", 1);       

        assertEquals(1, gestor.totalTareas());

        Scanner sc = new Scanner("1\n");
        Menu menu = new Menu(true, sc, gestor);

        menu.optionDeleteTask();
        assertEquals(0, gestor.totalTareas());
    }

    @Test
    void testOptionCompleteTask() {
        
        GestorTareas gestor = new GestorTareas();
        gestor.addTarea("Completar tarea", 1);

        Scanner sc = new Scanner("1\n");
        Menu menu = new Menu(true, sc, gestor); 

        menu.optionCompleteTask();
        assertEquals(true, gestor.getTareaPorIndice(0).getCompletado());
    }

    @Test
    void testEditSubMenuOptions(){
        
    }
}
