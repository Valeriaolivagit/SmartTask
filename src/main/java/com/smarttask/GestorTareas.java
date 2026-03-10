package com.smarttask;

import java.util.ArrayList;

/**
 * Clase encargada de crear, organizar y gestionar las tareas.
 * 
 * Cuenta con métodos que permiten la creacion, edición,
 * eliminación y clasificación de las tareas.
 * 
 * Métodos principales:
 * GestorTareas(): inicializa la lista donde se almacenan las tareas.
 * addTarea(): permite la creación de una tarea.
 * deleteTareas(): elimina una tarea existente.
 * completarTarea(): marca una tarea como completada.
 * showTareasPendientes(): muestra las tareas que aún no han sido completadas.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */

public class GestorTareas implements IGestorTareas {
    /**
     * Lista que almacena todas las tareas registradas.
     */
    private ArrayList<Tarea> tareas;

    /**
     * Inicializa la lista que almacenará las tareas.
     */
    public GestorTareas() {
        this.tareas = new ArrayList<>();
    }

    /**
     * Obtiene el número total de tareas almacenadas.
     * 
     * @return total de tareas registradas.
     */
    public int totalTareas() {
        return tareas.size();
    }

    /**
     * Obtiene la tarea ubicada en un índice específico de la lista.
     * 
     * @param indice posición de la tarea dentro de la lista.
     * @return tarea correspondiente al índice ingresado.
     */
    public Tarea getTareaPorIndice(int indice) {
        return tareas.get(indice);
    }

    /**
     * Crea y agrega una nueva tarea a la lista de tareas.
     * 
     * Ejecuta un switch el cual, dependiendo del valor númerico ingresado,
     * clasificará la tarea como tarea urgente o normal, mostrando un mensaje
     * en cada caso.
     * 
     * Opciones:
     * 1: crea una tarea urgente.
     * 2: crea una tarea normal.
     * Cualquier otro valor ingresado crea una tarea normal por defecto.
     * 
     * @param descripcion descripción asoaciada a la tarea.
     * @param priority    valor númerico que indica la prioridad, 1 para alta, 2
     *                    para baja,
     *                    cualquier otro valor se asigna como prioridad baja.
     */
    public void addTarea(String descripcion, int priority) {

        switch (priority) {
            case 1:
                tareas.add(new TareaUrgente(descripcion, false));
                System.out.println("Se ha agregado una Tarea Urgente.");
                break;

            case 2:
                tareas.add(new TareaNormal(descripcion, false));
                System.out.println("Se ha agregado una Tarea Normal.");
                break;

            default:
                System.out.println("Prioridad invalida. Se asignará como tarea normal.");
                tareas.add(new TareaNormal(descripcion, false));
                break;
        }
    }

    /**
     * Muestra el listado de tareas pendientes.
     * 
     * Recorre la lista de tareas e imprime únicamente las
     * tareas que no se encuentran completadas. Si no existen
     * tareas registradas o todas están completadas, muestra un mensaje
     * de que no hay tareas pendientes.
     * 
     */
    public void showTareasPendientes() {
        boolean hayTarea = false;

        for (Tarea tarea : tareas) {
            if (tarea.getCompletado()) {
                continue;
            }
            System.out.println(tarea.getId() + ". " + tarea.getNombreTarea() + " " + tarea.getPrioridad());
            hayTarea = true;
        }

        if (!hayTarea) {
            System.out.println(" ");
            System.out.println("- No tienes tareas pendientes -");
        }
    }

    /**
     * Muestra el listado de tareas completadas.
     * 
     * Recorre la lista de tareas e imprime únicamente las
     * tareas que se encuentran completadas. Si no existen
     * tareas registradas, muestra un mensaje de que aún no hay tareas
     * agregadas. Si todas las tareas están pendientes,informa
     * de que no hay tareas completadas por el momento.
     * 
     */
    public void showTareasCompletas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas agregadas aún.");
            return;
        }

        boolean hayCompletadas = false;

        for (Tarea tarea : tareas) {
            if (tarea.getCompletado()) {
                System.out.println(tarea.getId() + ". " + tarea.getNombreTarea() + " " + tarea.getPrioridad() + " "
                        + "Estado: Completado");
                hayCompletadas = true;
            }
        }

        if (!hayCompletadas) {
            System.out.println("No tienes tareas completadas por el momento.");
        }
    }

    /**
     * Elimina la tarea seleccionada.
     * 
     * Mediante el método getIndice(), obtiene la posición
     * en la lista de la tarea que se requiere eliminar. Si el índice
     * es válido, la tarea se elimina de la lista, y se muestra un
     * mensaje de confirmación. Caso contrario, se informa que el
     * identificador es inválido.
     * 
     * @param idDelete identificador de la tarea que se requiere eliminar.
     */
    public void deleteTareas(int idDelete) {
        int resultado = getIndice(idDelete);

        if (resultado != -1) {
            tareas.remove(resultado);
            System.out.println("Tarea eliminada exitosamente.");
        } else {
            System.out.println("ID invalido");           
        }
    }

    /**
     * Retorna la posición de la tarea solicitada.
     * 
     * Recorre la lista de tareas, y busca aquella cuyo
     * identificador coincida con el valor ingresado. Si
     * encuentra coincidencia retorna la posición de la tarea
     * dentro de la lista. En caso contrario retorna -1.
     * 
     * @param idBuscado identificador de la tarea que se desea buscar.
     * @return posición de la tarea en la lista si existe,
     *         -1 si no se encuentra una coincidencia.
     */
    private int getIndice(int idBuscado) {
        for (int i = 0; i < tareas.size(); i++) {
            Tarea indice = tareas.get(i);

            if (indice.getId() == idBuscado) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Imprime un mensaje si no hay tareas
     */
    public void noHayTareas() {
        System.out.println("No hay tareas agregadas aún.");
    }

    /**
     * Marca la tarea como completada.
     * 
     * Recibe el identificador de la tarea a completar,
     * y mediante el método getIndice() obtiene la posición
     * de la tarea. Si el identificador no coincide con la tarea
     * indica que el id no es válido y detiene la ejecución.
     * 
     * Si el identificador coincide con la tarea,
     * cambia el estado a completado y muestra un mensaje de éxito.
     * Si la tarea ya está completada, muestra un mensaje indicando
     * el estado.     
     * 
     * @param idTarea identificador de la tarea a completar.
     */
    public void completarTarea(int idTarea) {
        int resultado = getIndice(idTarea);

        if (resultado == -1) {
            System.out.println("ID inválido");
            return;
        }

        boolean completado = tareas.get(resultado).getCompletado();

        if (completado) {
            System.out.println("La tarea ya se encuentra completada");
        } else {
            tareas.get(resultado).setCompletado(true);
            System.out.println("¡Felicidades! Has completado la tarea.");
        }
    }

    /**
     * Sobreescribe el método editarTarea() de la
     * interfaz IGestorTareas.
     * 
     * Recibe el identificador de la tarea, y mediante el
     * método getIndice() obtiene la posición de la tarea
     * en la lista.
     * 
     * @param idTarea identificador de la tarea que se desea editar
     * @return la posición de la tarea en la lista si existe, o
     *         -1 en caso de no existir coincidencias.
     */
    @Override
    public int editarTarea(int idTarea) {
        int resultado = getIndice(idTarea);
        return resultado;
    }

    /**
     * Edita la descripción de la tarea seleccionada.
     * 
     * Recibe la posición de la tarea dentro de la lista
     * y la nueva descripción que se desea asignar. Luego ejecuta
     * el cambio correspondiente y muestra un mensaje de éxito.
     *
     * @param resultado      posición de la tarea dentro de la lista.
     * @param newDescripcion nueva descripción de la tarea seleccionada.
     */
    public void editarDescripcionTarea(int resultado, String newDescripcion) {
        tareas.get(resultado).setNombreTarea(newDescripcion);
        System.out.println("Se ha establecido la nueva descripción.");
    }

    /**
     * Muestra las opciones disponibles del submenú de edición de tareas.
     * 
     * Permite al usuario seleccionar que aspecto de la tarea desea
     * modificar, si desea volver al menú principal o finalizar la aplicación.
     */
    public void editSubMenuShowOptions() {
        System.out.println(" ");
        System.out.println("¿Qué desea editar?");
        System.out.println("1. Descripción de la tarea.");
        System.out.println("2. Estado de la tarea.");
        System.out.println("3. Volver al menú principal.");
        System.out.println("4. Salir de la aplicación.");
    }

    /**
     * Muestra el estado actual de la tarea seleccionada.
     * 
     * Comprueba el estado actual de la tarea(pendiente o completado),
     * y muestra el mensaje correspondiente.
     * 
     * @param resultado posición de la tarea dentro de la lista.
     */
    public void estadoTareaAEditar(int resultado) {
        if (tareas.get(resultado).getCompletado()) {
            System.out.println("La tarea actual esta Completada");
        } else {
            System.out.println("La tarea actual esta Pendiente");
        }
    }

    /**
     * Cambia el estado actual de una tarea.
     * 
     * Si la tarea se encuentra pendiente la marca como completada,
     * y viceversa.
     * 
     * @param resultado posición de la tarea dentro de la lista.
     */
    public void cambioDeEstado(int resultado) {
        if (tareas.get(resultado).getCompletado()) {
            tareas.get(resultado).setCompletado(false);
        } else {
            tareas.get(resultado).setCompletado(true);
        }
    }

}
