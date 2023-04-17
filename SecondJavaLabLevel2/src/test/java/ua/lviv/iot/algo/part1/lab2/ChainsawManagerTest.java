package ua.lviv.iot.algo.part1.lab2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
class ChainsawManagerTest {
    SawManager.ChainsawManager chainsawManager;
    Chainsaw saw1;
    Chainsaw saw2;
    Chainsaw saw3;

    @BeforeEach
    void setUp() {
        chainsawManager = new SawManager.ChainsawManager();
        saw1 = new Chainsaw("Stihl", 1000);
        saw2 = new Chainsaw("Husqvarna", 1500);
        saw3 = new Chainsaw("Makita", 2000);
        chainsawManager.addSaw(saw1);
        chainsawManager.addSaw(saw2);
        chainsawManager.addSaw(saw3);
    }

    @Test
    void testFindAllWithRemainingWorkTimeGreaterThan() {
        List<Saw> result = chainsawManager.findAllWithRemainingWorkTimeGreaterThan(1);
        assertEquals(0, result.size());
        assertFalse(result.contains(saw1));
        assertFalse(result.contains(saw2));
        assertFalse(result.contains(saw3));
    }

    @Test
    void testFindAllWithPowerGreaterThan() {
        List<Saw> result = chainsawManager.findAllWithPowerGreaterThan(1500);
        assertEquals(0, result.size());
        assertFalse(result.contains(saw2));
        assertFalse(result.contains(saw3));
    }

    @Test
    void testGetSawList() {
        Chainsaw saw1 = new Chainsaw("Brand 1", 2000);
        Chainsaw saw2 = new Chainsaw("Brand 2", 1800);
        Chainsaw saw3 = new Chainsaw("Brand 3", 2200);

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

}