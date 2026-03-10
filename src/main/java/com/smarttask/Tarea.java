package com.smarttask;

/**
 * Clase abstracta que representa una tarea dentro de la aplicacion 
 * SmartTask.
 * 
 * Cada tarea posee un identificador único, una descripción, un estado 
 * (pendiente o completado), y un nivel de prioridad.
 * Esta clase permite organizar y gestionar las tareas segun su prioridad.
 * 
 * Es extendida por las clases TareaNormal y TareaUrgente, las cuales
 * definen el nivel de prioridad.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */

public abstract class Tarea {
    /**
     * Contador utilizado para generar el identificador único
     * para cada tarea creada.
     */
    private static int counterId = 1;
    /**
     * Identificador único de la tarea.
     */
    private int idTarea;
    /**|
     * Descripción de la tarea.
     */
    private String nombreTarea;

    /**
     * Estado actual de la tarea. 
     * 
     * Se encuentra predeterminado en false que indica
     * que la tarea está pendiente.
     */
    private boolean completado = false;
    

    /**
     * Constructor de la clase Tarea.
     * 
     * Permite la creación de una nueva tarea, asignándole un identificador
     * único mediante la variable counterId++, además de una descripción 
     * y un estado inicial.
     * 
     * @param nombreTarea cadena de texto con la descripción de la tarea
     * @param completado true si está completado y false si está pendiente
     */
    public Tarea(String nombreTarea, boolean completado) {
        this.idTarea = counterId++;
        this.nombreTarea = nombreTarea;
        this.completado = completado;
    }

    /**
     * Obtiene el identificador único de la tarea.
     * 
     * @return identificador de la tarea.
     */
    public int getId() {
        return idTarea;
    }

    /**
     * Obtiene la descripción asociada a la tarea.
     * 
     * @return descripción de la tarea.
     */
    public String getNombreTarea() {
        return nombreTarea;
    }

    /**
     * Obtiene el estado actual de la tarea.
     * 
     * @return true si está completada 
     *         false si está pendiente.
     */
    public boolean getCompletado() {
        return completado;
    }

    /**
     * Método abstracto para obtener el nivel de 
     * prioridad asociado a la tarea.
     * 
     * @return cadena de texto que representa la prioridad
     *         de la tarea.
     */
    public abstract String getPrioridad();

    /**
     * Establece la descripción asociada a la tarea.
     * 
     * @param nombreTarea descripción asociada a la tarea.
     */
    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    /**
     * Establece el estado actual de la tarea.
     * 
     * @param completado true si está completada.
     *                   false si está pendiente.
     */
    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    /**
     * Reinicia el contador utilizado para generar 
     * el identificador único de la/s tarea/s.
     */
    public static void resetCounter() {
        counterId = 1;
    }
}
