import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import com.smarttask.GestorTareas;
import com.smarttask.Tarea;

public class GestorTareasTest {
    private GestorTareas gestor;
    private PrintStream originalOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        Tarea.resetCounter();
        gestor = new GestorTareas();
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void testTotalDeTareasInicialYConIncremento() {
        assertEquals(0, gestor.totalTareas());

        gestor.addTarea("Estudiar java", 1);

        assertEquals(1, gestor.totalTareas());
    }

    @Test
    void testAddTareaConPriorityUno() {
        gestor.addTarea("Estudiar Java", 1);

        assertEquals(1, gestor.totalTareas());
    }

    @Test
    void testAddTareaConPriorityDos() {
        gestor.addTarea("Comprar pan", 2);

        assertEquals(1, gestor.totalTareas());
    }

    @Test
    void testAddTareaConPriorityDefault() {
        gestor.addTarea("Jugar videojuegos", 3);

        assertEquals(1, gestor.totalTareas());
    }

    @Test
    void testShowTareasPendientesSiHayTareasEsFalse() {
        gestor.showTareasPendientes();

        String output = outputStream.toString();
        assertTrue(output.contains("No tienes tareas pendientes"));
    }

    @Test
    void testShowTareasPendientesSiHayTareasEsTrue() {
        gestor.addTarea("Estudiar java", 1);
        gestor.showTareasPendientes();

        String output = outputStream.toString();
        assertTrue(output.contains("Estudiar java"));
    }

    @Test
    void testShowTareasPendientesPeroEstaCompletada() {
        gestor.addTarea("Estudiar java", 1);
        gestor.completarTarea(1);
        gestor.showTareasPendientes();

        String output = outputStream.toString();
        assertFalse(output.contains("Estudiar java"));
        assertTrue(output.contains("No tienes tareas pendientes"));

    }

    @Test
    void testDeShowTareasCompletasCuandoNoHayTareasAgregadas() {
        gestor.showTareasCompletas();

        String output = outputStream.toString();
        assertTrue(output.contains("No hay tareas agregadas aún."));
    }

    @Test
    void testDeShowTareasCompletasConUnaTareaCompletada() {
        gestor.addTarea("Estudiar java", 1);
        gestor.completarTarea(1);

        gestor.showTareasCompletas();
        String output = outputStream.toString();
        assertTrue(output.contains("Estado: Completado"));
    }

    @Test
    void testDeShowTareasCompletasCuandoNoHayTareasCompletadas() {
        gestor.addTarea("Estudiar java", 1);

        gestor.showTareasCompletas();
        String output = outputStream.toString();
        assertTrue(output.contains("No tienes tareas completadas por el momento."));
    }

    @Test
    void testDeleteTareaSiElIndiceEsCorrecto() {
        gestor.addTarea("Jugar videojuegos", 2);
        gestor.deleteTareas(1);

        String output = outputStream.toString();
        assertTrue(output.contains("Tarea eliminada exitosamente."));
    }

     @Test
    void testDeleteTareaSiElIndiceEsIncorrecto() {
        gestor.addTarea("Jugar videojuegos", 2);
        gestor.deleteTareas(2);

        String output = outputStream.toString();
        assertTrue(output.contains("ID invalido"));
    }

    @Test
    void testNoHayTareas() {
        gestor.noHayTareas();     

        String output = outputStream.toString();
        assertTrue(output.contains("No hay tareas agregadas aún."));
    }

    @Test
    void testCorroboraPrimeroQueIDCoincideEnCompletarTarea(){
        gestor.addTarea("Estudiar java", 1);

        gestor.completarTarea(2);
        String output = outputStream.toString();
        assertTrue(output.contains("ID inválido"));
        assertEquals(false, gestor.getTareaPorIndice(0).getCompletado());
    }

      @Test
    void testCompletarTareaPeroLaTareaEstaCompletada(){
        gestor.addTarea("Estudiar java", 1);
        gestor.completarTarea(1);

        assertTrue(gestor.getTareaPorIndice(0).getCompletado());

        gestor.completarTarea(1);

        String output = outputStream.toString();
        assertTrue(output.contains("La tarea ya se encuentra completada"));        
    }

    @Test
    void testEditarDescripcionDeTarea() {
        gestor.addTarea("Estudiar", 1);

        gestor.editarDescripcionTarea(0, "Estudiar Java");

        assertEquals("Estudiar Java", gestor.getTareaPorIndice(0).getNombreTarea());
    }

    @Test
    void testEditarDescripcionDeTareaMensajeExitoso() {
        gestor.addTarea("Estudiar", 1);

        gestor.editarDescripcionTarea(0, "Estudiar Java");

        String output = outputStream.toString();
        assertTrue(output.contains("Se ha establecido la nueva descripción."));
    }

    @Test
    void testDeEditSubMenuOptionsMensaje() {
        gestor.editSubMenuShowOptions();

        String output = outputStream.toString();
        assertTrue(output.contains("¿Qué desea editar?"));
    }

    @Test
    void testEstadoDeTareaAEditarCuandoNoEstaCompletada() {
        gestor.addTarea("Estudiar java", 1);

        gestor.estadoTareaAEditar(0);

        assertFalse(gestor.getTareaPorIndice(0).getCompletado());
    }

    @Test
    void testEstadoDeTareaAEditarCuandoNoEstaCompletadaMensaje() {
        gestor.addTarea("Estudiar java", 1);

        gestor.estadoTareaAEditar(0);

        String output = outputStream.toString();
        assertTrue(output.contains("La tarea actual esta Pendiente"));
    }

    @Test
    void testEstadoDeTareaAEditarCuandoEstaCompletada() {
        gestor.addTarea("Estudiar java", 1);
        gestor.completarTarea(1);

        gestor.estadoTareaAEditar(0);
    }

    @Test
    void testEstadoDeTareaAEditarCuandoEstaCompletadaMensaje() {
        gestor.addTarea("Estudiar java", 1);
        gestor.completarTarea(1);

        gestor.estadoTareaAEditar(0);

        assertTrue(gestor.getTareaPorIndice(0).getCompletado());

        String output = outputStream.toString();
        assertTrue(output.contains("La tarea actual esta Completada"));
    }

    @Test
    void testCambioDeEstadoSiEstaPendientePasaACompletada() {
        gestor.addTarea("Estudiar java", 1);
        gestor.cambioDeEstado(0);

        assertTrue(gestor.getTareaPorIndice(0).getCompletado());
    }

    @Test
    void testCambioDeEstadoSiEstaCompletadoPasaAPendiente() {
        gestor.addTarea("Estudiar java", 1);
        gestor.completarTarea(1);
        gestor.cambioDeEstado(0);

        assertFalse(gestor.getTareaPorIndice(0).getCompletado());
    }

}
