package com.smarttask;

import java.util.Scanner;

/**
 * Clase encargada de gestionar la navegación e interacción del usuario.
 * 
 * Muestra las distintas opciones disponibles en la aplicación y permite
 * al usuario tomar decisiones mediante el menú principal y los distintos
 * submenús.
 * 
 * Cuenta con un menú principal, submenús dependiendo de la acción del usuario,
 * y es la clase que realiza el manejo principal de Scanner.
 * 
 * Métodos principales:
 * menu(): controla el flujo general de la aplicacion.
 * subMenu(): permite visualizar las tareas y gestiona su manejo.
 * salirApp(): finaliza la ejecución de la aplicacion.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */

public class Menu {
    /**
     * Indica si la aplicación debe continuar en ejecución.
     * 
     * Su valor es true mientras la aplicación este activa y
     * false cuando se requiere finalizar la ejecución.
     */
    private boolean appRunner = true;
    /**
     * Utilizado para instanciar el objeto Scanner.
     * 
     * Permite la captura de datos en la consola.
     */
    private Scanner sc;

    /**
     * Utilizado para instanciar el objeto GestorTareas.
     * 
     * Permite acceder a los métodos encargados de
     * la gestión y administración de las tareas.
     */
    private GestorTareas gestor;

    /**
     * Constructor de la clase Menú.
     * 
     * Inicializa el estado de la aplicación, el objeto Scanner para el ingreso
     * de datos del usuario y el gestor encargado de administrar las tareas.
     * 
     * @param appRunner true si la aplicación se encuentra en ejecución.
     *                  false si la aplicación debe finalizar.
     * @param sc        utilizado para la captura de valores ingresados por el
     *                  usuario.
     * @param gestor    instancia la clase GestorTareas encargada de gestionar las
     *                  tareas
     */
    public Menu(boolean appRunner, Scanner sc, GestorTareas gestor) {
        this.appRunner = appRunner;
        this.sc = sc;
        this.gestor = gestor;
    }

    /**
     * Obtiene el estado actual de la ejecución de la aplicación.
     * 
     * @return true si la aplicación debe continuar ejecutándose,
     *         false si debe finalizar.
     */
    public boolean getAppRunner() {
        return appRunner;
    }

    /**
     * Establece el estado actual de la aplicación.
     * 
     * @param appRunner true si la aplicación debe continuar ejecutándose,
     *                  false si debe finalizar.
     */
    public void setAppRunner(boolean appRunner) {
        this.appRunner = appRunner;
    }

    /**
     * Muestra las opciones disponibles según la sección actual de la aplicación.
     * 
     * @param options indica el menu que se desea mostrar.
     *                1 para el menú principal,
     *                2 para el submenú de tareas,
     *                u otros valores según la navegación definida.
     */
    public void showOptions(int options) {
        switch (options) {
            case 1:
                System.out.println(" ");
                System.out.println("¡Bienvenido a tu Aplicación SmartTask!");
                System.out.println("¿Que deseas hacer?");
                System.out.println("-----------------------------");
                System.out.println("1. Ver todas mis tareas");
                System.out.println("2. Agregar una tarea");
                System.out.println("3. Salir de la aplicación");
                break;

            case 2:
                System.out.println("                     ");
                System.out.println("¿Que deseas hacer?");
                System.out.println("1. Completar una tarea");
                System.out.println("2. Editar una tarea");
                System.out.println("3. Borrar una tarea");
                System.out.println("4. Ver tareas completadas");
                System.out.println("5. Volver al menú principal");
                break;

            case 3:
                System.out.println(" ");
                System.out.println("----------- Agregar una tarea ---------------");
                break;

            case 4:
                System.out.println(" ");
                System.out.println("¿Deseas realizar otra accion?");
                System.out.println("1. Agregar otra tarea");
                System.out.println("2. Volver al menú principal");
                System.out.println("3. Salir de la aplicación");
                break;

            case 5:
                System.out.println("Agradecemos tu preferencia. ¡Vuelve pronto!");
                break;

            default:
                System.out.println("Opcion no valida");
                break;
        }
    }

    /**
     * Manejo principal del menú y ejecución de la aplicación.
     * 
     * Ejecuta un ciclo while que se mantiene activo mientras la variable appRunner
     * sea true.
     * Dentro del ciclo: Muestra el menú principal, solicita al usuario una opción y
     * ejecuta
     * la acción correspondiente según la opción elegida.
     */
    public void menu() {
        while (appRunner) {
            menuprincipal();
            int opcion = opcionElegida();
            opcionEscogida(opcion);
        }
    }

    /**
     * Muestra el menú principal de la aplicación.
     * 
     * Despliega las acciones disponibles al usuario y solicita el ingreso de una
     * opción.
     */
    public void menuprincipal() {
        showOptions(1);
        System.out.print("Por favor, ingrese una opción: ");
    }

    /**
     * Solicita y valida la opcion ingresada por el usuario.
     * 
     * Ejecuta un ciclo while que verifica que la entrada sea un número entero.
     * Muestra un mensaje de error cuando el usuario ingresa un valor
     * inválido(signo, letra, etc..)
     * y vuelve a solicitar un valor válido.
     * 
     * @return el número entero ingresado correctamente por el usuario.
     */
    public int opcionElegida() {
        while (!sc.hasNextInt()) {
            System.out.println("Entrada inválida. Por favor ingrese un número.");
            sc.nextLine();
        }

        int option = sc.nextInt();
        sc.nextLine();
        return option;
    }

    /**
     * Gestiona la opcion ingresada por el usuario.
     * 
     * Ejecuta un switch, que de acuerdo a la opción ingresada por el usuario,
     * evalúa y ejecuta distintas funciones.
     * 
     * @param option 1 ejecuta el subMenú para visualizar y gestionar las tareas.
     *               2 ejecuta la acción para agregar una nueva tarea.
     *               3 finaliza la ejecución de la aplicación.
     *               Cualquier otro valor muestra un mensaje indicando que la opción
     *               no es válida.
     */
    public void opcionEscogida(int option) {
        switch (option) {
            case 1:
                subMenu();
                break;

            case 2:
                optionAddTask();
                comeBack();
                break;

            case 3:
                salirDeLaApp();
                break;

            default:
                System.out.println("Opcion no válida. Por favor ingrese una opcion válida (1, 2 o 3)");
                break;
        }
    }

    /**
     * Finaliza la ejecución de la aplicación.
     *
     * Cambia la variable appRunner a false para detener el ciclo principal
     * y muestra un mensaje de salida correspondiente.
     */
    public void salirDeLaApp() {
        showOptions(5);
        appRunner = false;
    }

    /**
     * Submenú que permite visualizar y gestionar las tareas.
     * 
     * Utiliza una variable local (subMenuRunner) que maneja la
     * ejecución y finalización del submenú.
     * 
     * Ejecuta un ciclo while que se mantiene activo mientras
     * subMenuRunner sea true. Dentro del ciclo muestra el subMenú,
     * solicita al usuario una opción y ejecuta la acción de
     * acuerdo a la opción ingresada.
     */
    private void subMenu() {
        boolean subMenuRunner = true;

        while (subMenuRunner) {
            showSubMenu();
            int subMenuOp = opcionElegida();
            subMenuRunner = subMenuOptions(subMenuOp);
        }
    }

    /**
     * Muestra el submenú de tareas pendientes.
     * 
     * Imprime las tareas pendientes mediante el gestor
     * muestra las posibles acciones a realizar y
     * solicita al usuario una opción.
     */
    public void showSubMenu() {
        System.out.println(" ");
        System.out.println("----------- Listado de tareas Pendientes---------------");
        gestor.showTareasPendientes();
        showOptions(2);
        System.out.print("Por favor, ingrese una opción: ");
    }

    /**
     * Gestiona la opción ingresada por el usuario del submenú
     * 
     * Evalúa la opcion ingresada por el usuario mediante un switch
     * y ejecuta la acción correspondiente a la opción ingresada.
     * 
     * Opciones:
     * 1 opción que permite completar una tarea.
     * 2 opción que permite editar una tarea.
     * 3 opción que permite borrar una tarea.
     * 4 opción que muestra las tareas completadas.
     * 5 opción que permite volver al menú anterior.
     * Cualquier otro valor ingresado muestra un mensaje que indica
     * que la opción no es válida.
     * 
     * @param subMenuOp número ingresado por el usuario que determina la opción a
     *                  ejecutar.
     * @return true si el submenú debe continuar ejecutándose.
     *         false si debe finalizar y regresar al menú anterior.
     */
    public boolean subMenuOptions(int subMenuOp) {
        int totalTareas = gestor.totalTareas();

        if (noHayTareasRegistradas(totalTareas, subMenuOp)) {
            return true;
        }

        switch (subMenuOp) {
            case 1:
                System.out.println(" ");
                System.out.println("-----Completar una tarea ------");
                optionCompleteTask();
                return true;
            case 2:
                System.out.println(" ");
                System.out.println("-----Editar una tarea------");
                optionEditTask();
                return appRunner;

            case 3:
                System.out.println(" ");
                System.out.println("-----Borrar una tarea------");
                optionDeleteTask();
                return true;

            case 4:
                System.out.println(" ");
                System.out.println("----------- Listado de tareas Completadas---------------");
                gestor.showTareasCompletas();
                return true;

            case 5:
                System.out.println("Volviendo al menu anterior....");
                return false;

            default:
                System.out.println("Opción no válida");
                return true;
        }
    }

    /**
     * Verifica si hay tareas registradas antes de ejecutar 
     * ciertas opciones.
     * 
     * Si el total de tareas es 0 y el usuario ingreso la opción
     * 1, 2 o 3, se muestra un mensaje indicando que no hay tareas
     * agregadas.
     * 
     * @param totalTareas cantidad de tareas registradas.
     * @param opcion      opcion seleccionada por el usuario.
     * @return          true si no hay tareas registradas, 
     *                  false en caso contrario.
     */
    private boolean noHayTareasRegistradas(int totalTareas, int opcion) {
        if (totalTareas == 0 && (opcion == 1 || opcion == 2 || opcion == 3)) {
            gestor.noHayTareas();
            return true;
        }
        return false;
    }

    /**
     * Gestiona el proceso de creación de una nueva tarea.
     * 
     * Muestra el mensaje correspondiente para agregar una tarea,
     * solicita al usuario una descripción y el nivel de prioridad
     * de la tarea, y delega al gestor de tareas la creación de
     * la nueva tarea con los datos ingresados.
     * 
     * El Nivel de prioridad debe ingresarse como:
     * 1 para prioridad Alta y 2 para prioridad Baja.
     * 
     */
    public void optionAddTask() {
        showOptions(3);
        System.out.print("Ingrese su tarea: ");
        String descripcion = sc.nextLine();

        System.out.print("Escriba la prioridad (1 = Alta o 2 = Baja): ");
        int priority = opcionElegida();

        gestor.addTarea(descripcion, priority);
    }

    /**
     * Controla el subflujo posterior a la creación de una tarea.
     * 
     * Utiliza una variable local (back) que gestiona la ejecución
     * y finalización de este submenú.
     * 
     * Ejecuta un ciclo while que se mantiene activo mientras back sea true.
     * Dentro del ciclo muestra las opciones correspondientes, solicita
     * al usuario ingresar una opción y ejecuta el método comeBackSubMenu que
     * determina si la variable back debe continuar ejecutándose o finalizar.
     */
    private void comeBack() {
        boolean back = true;

        while (back) {
            showOptions(4);
            int optioncb = opcionElegida();
            back = comeBackSubMenu(optioncb);
        }
    }

    /**
     * Gestiona y controla las opciones del subflujo posterior a
     * la creación de una tarea.
     * 
     * Comportamiento:
     * 1 opción para agregar otra tarea.
     * 2 opción para volver al menú anterior.
     * 3 opción para finalizar la aplicación.
     * Cualquier otro valor mostrará un mensaje de opción inválida.
     * 
     * @param optioncb número ingresado por el usuario que determina la opción a a
     *                 ejecutar.
     * @return true si el subflujo debe continuar ejecutándose.
     *         false si debe finalizar. En el caso de opción 3 provoca la
     *         finalización de la aplicación.
     */
    public boolean comeBackSubMenu(int optioncb) {
        switch (optioncb) {
            case 1:
                optionAddTask();
                return true;
            case 2:
                return false;

            case 3:
                salirDeLaApp();
                return false;

            default:
                System.out.println("Opcion no válida.");
                return true;
        }
    }

    /**
     * Gestiona el proceso de eliminación de una tarea.
     * 
     * Solicita al usuario el ingreso del id de la tarea
     * a eliminar y delega al gestor de tareas la eliminación
     * de la tarea seleccionada.
     */
    public void optionDeleteTask() {
        System.out.println("Ingrese el ID de la tarea a eliminar: ");
        int idDelete = opcionElegida();
        gestor.deleteTareas(idDelete);
    }

    /**
     * Gestiona el proceso para completar una tarea.
     * 
     * Solicita al usuario el ingreso del id de la tarea
     * a completar y delega al gestor de tareas la operación
     * correspondiente.
     */
    public void optionCompleteTask() {
        System.out.println("Ingrese el ID de la tarea a completar: ");
        int idTarea = opcionElegida();
        gestor.completarTarea(idTarea);
    }

    /**
     * Gestiona el proceso de edición de una tarea.
     * 
     * Solicita al usuario mediante el método ingresoIdAEditar()
     * el identificador de la tarea.
     * 
     * Si el identificador es válido (distinto de -1), delega al gestor
     * de tareas el despliegue de las opciones de edición, y ejecuta la
     * acción correspondiente mediante editSubMenuOptions().
     * 
     * Si el identificador no es válido, muestra un mensaje de error y
     * finaliza el método
     */
    public void optionEditTask() {

        int resultado = ingresoIdAEditar();
        if (resultado != -1) {
            gestor.editSubMenuShowOptions();
            editSubMenuOptions(resultado);
        } else {
            System.out.println("ID invalido");
            return;
        }
    }

    /**
     * Gestiona el identificador ingresado por el usuario para el menú de edición.
     * 
     * Solicita al usuario el idetificador de la tarea a editar,
     * y delega al gestor de tareas la validación correspondiente.
     * 
     * @return el identificador validado por el gestor.
     *         puede retornar -1 si la tarea no existe.
     */
    private int ingresoIdAEditar() {
        System.out.println("Ingrese el ID de la tarea a editar: ");
        int idTarea = opcionElegida();
        int idToEdit = gestor.editarTarea(idTarea);
        return idToEdit;
    }

    /**
     * Gestiona y controla las opciones del submenú de edición de tareas.
     * 
     * Evalúa la opcion ingresada por el usuario mediante un switch
     * y ejecuta la acción correspondiente.
     * 
     * Opciones:
     * 1: cambia la descripción de la tarea mediante el método newEditDescription()
     * y delega al gestor el guardado de la nueva información.
     * 2: Muestra el estado actual de la tarea y mediante el método
     * editCambioEstadoCompletado() cambia ese estado.
     * 3: retorna al menú anterior sin realizar cambios.
     * 4: finaliza la ejecución de la aplicación.
     * Cualquier otro valor muestra el mensaje de opción inválida.
     * 
     * @param resultado identificador validado de la tarea a editar, obtenida a
     *                  través del método ingresoIdAEditar()
     */
    private void editSubMenuOptions(int resultado) {
        int opcion = opcionElegida();
        switch (opcion) {
            case 1:
                String nuevaDescripcion = newEditDescription();
                gestor.editarDescripcionTarea(resultado, nuevaDescripcion);
                break;

            case 2:
                gestor.estadoTareaAEditar(resultado);
                editCambioEstadoCompletado(resultado);
                break;

            case 3:
                System.out.println("Volviendo al menú anterior....");
                break;

            case 4:
                salirDeLaApp();
                break;

            default:
                System.out.println("Opcion invalida");
                break;
        }

    }

    /**
     * Solicita al usuario una nueva descripción para la tarea en el proceso de
     * edición.
     * 
     * Muestra un mensaje solicitando la nueva descripcion y captura el texto
     * ingresado por el usuario.
     * 
     * @return una cadena te texto con la nueva descripción de la tarea.
     */
    private String newEditDescription() {
        System.out.print("Ingrese la nueva descripcion: ");
        String newDescripcion = sc.nextLine();
        return newDescripcion;
    }

    /**
     * Gestiona el cambio de estado de la tarea seleccionada en el proceso de
     * edición.
     * 
     * Muestra un mensaje preguntando al usuario si desea cambiar el estado de
     * la tarea previamente seleccionada, solicita el ingreso de una opción y
     * ejecuta la acción correspondiente.
     * 
     * Comportamiento:
     * 1 A través del gestor realiza el cambio de estado e imprime un mensaje de
     * éxito.
     * 2 detiene la ejecución para volver al menú anterior.
     * Cualquier otra opción mostrará un mensaje de opción no válida.
     * 
     * @param resultado corresponde al identificador de la tarea seleccionada a
     *                  editar
     */
    private void editCambioEstadoCompletado(int resultado) {
        System.out.println("¿Cambiar estado? (1. Si - 2. No)");
        int optionstate = opcionElegida();
        switch (optionstate) {
            case 1:
                gestor.cambioDeEstado(resultado);
                System.out.println("Estado cambiado correctamente.");
                break;

            case 2:
                System.out.println("Volviendo al menú anterior....");
                break;

            default:
                System.out.println("Opcion no válida.");
                break;
        }
    }

}
