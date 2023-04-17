package ua.lviv.iot.algo.part1.lab2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
class ChainsawManagerTest {
    ChainsawManager chainsawManager;
    Chainsaw saw1;
    Chainsaw saw2;
    Chainsaw saw3;

    @BeforeEach
    void setUp() {
        chainsawManager = new ChainsawManager();
        saw1 = new Chainsaw("Stihl", 1000, 5);
        saw2 = new Chainsaw("Husqvarna", 1500, 3);
        saw3 = new Chainsaw("Makita", 2000, 4);
        chainsawManager.addSaw(saw1);
        chainsawManager.addSaw(saw2);
        chainsawManager.addSaw(saw3);
    }

    @Test
    void testFindAllWithRemainingWorkTimeGreaterThan() {
        List<Chainsaw> result = chainsawManager.findAllWithRemainingWorkTimeGreaterThan(1);
        assertEquals(0, result.size());
        assertFalse(result.contains(saw1));
        assertFalse(result.contains(saw2));
        assertFalse(result.contains(saw3));
    }

    @Test
    void testFindAllWithPowerGreaterThan() {
        List<Chainsaw> result = chainsawManager.findAllWithPowerGreaterThan(1500);
        assertEquals(0, result.size());
        assertFalse(result.contains(saw2));
        assertFalse(result.contains(saw3));
    }
    @Test
    void testGetSawList() {
        Chainsaw saw1 = new Chainsaw("Brand 1", 2000, 2.0);
        Chainsaw saw2 = new Chainsaw("Brand 2", 1800, 1.5);
        Chainsaw saw3 = new Chainsaw("Brand 3", 2200, 2.5);

        ChainsawManager chainsawManager = new ChainsawManager();
        chainsawManager.addSaw(saw1);
        chainsawManager.addSaw(saw2);
        chainsawManager.addSaw(saw3);

        Iterable<? extends Object> sawList = chainsawManager.getSawList();

        assertNotNull(sawList);
        assertEquals(3, StreamSupport.stream(sawList.spliterator(), false).count());
        assertTrue(StreamSupport.stream(sawList.spliterator(), false).allMatch(s -> s instanceof Chainsaw));
        assertTrue(StreamSupport.stream(sawList.spliterator(), false).anyMatch(s -> s.equals(saw1)));
        assertTrue(StreamSupport.stream(sawList.spliterator(), false).anyMatch(s -> s.equals(saw2)));
        assertTrue(StreamSupport.stream(sawList.spliterator(), false).anyMatch(s -> s.equals(saw3)));
    }

    @Test
    void testFindAllWithBrand() {
        Chainsaw saw1 = new Chainsaw("Stihl", 1200, 4.5);
        Chainsaw saw2 = new Chainsaw("Husqvarna", 1400, 5.0);
        Chainsaw saw3 = new Chainsaw("Stihl", 1100, 4.0);
        Chainsaw saw4 = new Chainsaw("Echo", 1300, 4.5);

        ChainsawManager chainsawManager = new ChainsawManager();
        chainsawManager.addSaw(saw1);
        chainsawManager.addSaw(saw2);
        chainsawManager.addSaw(saw3);
        chainsawManager.addSaw(saw4);

        List<Chainsaw> result;
        result = chainsawManager.findAllWithBrand("Stihl");
        assertEquals(2, result.size());
        assertTrue(result.contains(saw1));
        assertTrue(result.contains(saw3));
    }
}