package com.smarttask;

/**
 * Clase que hereda de Tarea y representa las tareas de Alta
 * prioridad dentro del sistema.
 * 
 * Esta clase es una especializacion de tarea. Define las tareas
 * de prioridad alta.
 * 
 * Permite diferenciar las tareas según su importancia
 * dentro del gestor de tareas.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */

public class TareaUrgente extends Tarea {

    /**
     * Nivel de prioridad de la tarea.
     * 
     * Indica que la tarea presenta una prioridad alta
     * dentro del sistema de gestión de tareas.
     *
     */
    private final String prioridad = "Prioridad: Alta";

    /**
     * Constructor de la clase TareaUrgente.
     * 
     * Crea una nueva tarea con prioridad alta.
     * Recibe la descripción de la tarea (parámetro nombreTarea) y su
     * estado inicial (parámetro completado), y los pasa al constructor de
     * la clase mediante super().
     * 
     * @param nombreTarea cadena de texto correspondiente a la descripción de la
     *                    tarea.
     * @param completado  boolean correspondiente a estado actual de la tarea. true
     *                    si
     *                    está completado y false si está pendiente.
     */
    public TareaUrgente(String nombreTarea, boolean completado) {
        super(nombreTarea, completado);
    }

    /**
     * Sobreescribe el método getPrioridad() definido en la clase padre Tarea.
     * Este método devuelve el nivel de prioridad asociado a tareas urgentes.
     * 
     * @return devuelve una cadena de texto que indica "Prioridad: Alta".
     */
    @Override
    public String getPrioridad() {
        return prioridad;
    }

}
