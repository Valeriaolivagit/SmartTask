

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.smarttask.TareaUrgente;

public class TareaUrgenteTest {
     @Test
    void testTareaUrgenteGetPrioridadAlta(){
        TareaUrgente tarea = new TareaUrgente("Test", false);

        assertEquals(tarea.getPrioridad(), "Prioridad: Alta");
    }
}
