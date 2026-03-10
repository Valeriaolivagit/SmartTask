package com.smarttask;

/**
 * Define el contrato para una operación de GestorTareas.
 * 
 * Gestiona el método editarTarea() del proceso de
 * edición de tarea.
 * 
 * @author Valeria Oliva
 * @version 1.0
 */

public interface IGestorTareas {
   /**
    * Obtiene la posición de una tarea a partir de su identificador.
    * 
    * Recibe el identificador de la tarea y retorna su posición dentro
    * de la lista si existe.
    * 
    * @param idTarea identificador de la tarea dentro de la lista.
    * @return la posición de la tarea si la encuentra, 
    *          -1 si no existe con ese identificador.
    */
   public int editarTarea(int idTarea);
   
}
