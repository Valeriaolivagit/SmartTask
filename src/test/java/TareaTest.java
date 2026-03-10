
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.smarttask.Tarea;
import com.smarttask.TareaNormal;

public class TareaTest {
    private Tarea tarea;

    @BeforeEach
    void setUp() {
        Tarea.resetCounter();
        tarea = new TareaNormal("Estudiar", false);
    }

    @Test
    void testTareaGetId() {
        assertEquals(tarea.getId(), 1);
    }

    @Test
    void testTareaGetIdConIncrementoId() {
        Tarea tareaDos = new TareaNormal("Testear app", false);
        assertEquals(tarea.getId(), 1);
        assertEquals(tareaDos.getId(), 2);
    }

    @Test
    void testTareaGetNombre() {
        assertEquals(tarea.getNombreTarea(), "Estudiar");
    }

    @Test
    void testTareaGetCompletado() {
        assertEquals(tarea.getCompletado(), false);
    }

    @Test
    void testTareaGetPrioridad() {
        assertEquals(tarea.getPrioridad(), "Prioridad: Baja");
    }

    @Test
    void testTareaSetNombreTarea() {
        tarea.setNombreTarea("Estudiar java");

        assertEquals("Estudiar java", tarea.getNombreTarea());
    }

    @Test
    void testTareaSetCompletado() {
        tarea.setCompletado(true);

        assertEquals(true, tarea.getCompletado());
    }

}
