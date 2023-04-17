package ua.lviv.iot.algo.part1.lab2;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ElectricSawManagerTest {
    private final ElectricSaw saw1 = new ElectricSaw("Makita", 1500, 3, "Brushless", 5);
    private final ElectricSaw saw2 = new ElectricSaw("DeWalt", 2000, 2.5, "Brushed", 4);
    private final ElectricSaw saw3 = new ElectricSaw("Bosch", 1800, 3.5, "Brushless", 6);

    private final ElectricSawManager manager = new ElectricSawManager();

    @Test
    void testAddSaw() {
        manager.addSaw(saw1);
        manager.addSaw(saw2);
        manager.addSaw(saw3);

        Iterable<Object> result = Collections.singleton(manager.getSawList());
        assertFalse(result.equals(saw1));
        assertFalse(result.equals(saw2));
        assertFalse(result.equals(saw3));
    }

    @Test
    void testFindAllWithBrand() {
        manager.addSaw(saw1);
        manager.addSaw(saw2);
        manager.addSaw(saw3);

        List<ElectricSaw> result = manager.findAllWithBrand("Makita");
        assertEquals(1, result.size());
        assertTrue(result.contains(saw1));
    }

    @Test
    void testFindAllWithRemainingWorkTimeGreaterThan() {
        manager.addSaw(saw1);
        manager.addSaw(saw2);
        manager.addSaw(saw3);

        List<ElectricSaw> result = manager.findAllWithRemainingWorkTimeGreaterThan(4);
        assertEquals(0, result.size());
        assertFalse(result.contains(saw1));
        assertFalse(result.contains(saw3));
    }
}
