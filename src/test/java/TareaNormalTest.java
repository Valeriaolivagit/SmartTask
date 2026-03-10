
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.smarttask.TareaNormal;

public class TareaNormalTest {
    @Test
    void testTareaNormalGetPrioridadBaja(){
        TareaNormal tarea = new TareaNormal("Test", false);

        assertEquals(tarea.getPrioridad(), "Prioridad: Baja");
    }
}
