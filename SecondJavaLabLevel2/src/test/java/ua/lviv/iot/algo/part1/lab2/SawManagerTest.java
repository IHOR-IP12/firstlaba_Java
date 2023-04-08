package ua.lviv.iot.algo.part1.lab2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SawManagerTest {

    private SawManager<Saw> sawManager;

    @BeforeEach
    public void setUp() {
        sawManager = new SawManager<>() {
            @Override
            public List<Saw> findAllWithBrand(String brand) {
                return null;
            }

            @Override
            public List<Saw> findAllWithRemainingWorkTimeGreaterThan(double remainingWorkTime) {
                return null;
            }
        };
    }

    @Test
    public void testAddSaw() {
        Saw saw = new Chainsaw("Husqvarna", 1000, 60, 1000);
        sawManager.addSaw(saw);
        List<Saw> found = sawManager.findAllWithPowerGreaterThan(500);
        assertEquals(0, found.size());
        assertFalse(found.contains(saw));
    }

    @Test
    public void testFindAllWithPowerGreaterThan() {
        Saw saw1 = new Chainsaw("Stihl", 500, 60, 1000);
        Saw saw2 = new Chainsaw("Husqvarna", 1000, 60, 1000);
        Saw saw3 = new ElectricSaw("Dewalt", 1500, 30, "Brushless", 2000);
        Saw saw4 = new ElectricSaw("Ryobi", 2000, 30, "Brushless", 3000);
        sawManager.addSaw(saw1);
        sawManager.addSaw(saw2);
        sawManager.addSaw(saw3);
        sawManager.addSaw(saw4);
        List<Saw> found = sawManager.findAllWithPowerGreaterThan(1000);
        assertEquals(0, found.size());
        assertFalse(found.contains(saw3));
        assertFalse(found.contains(saw4));
    }

    @Test
    public void testFindAllChainsaws() {
        Saw saw1 = new Chainsaw("Stihl", 500, 60, 1000);
        Saw saw2 = new ElectricSaw("Dewalt", 1000, 30, "Brushless", 2000);
        Saw saw3 = new Chainsaw("Husqvarna", 1500, 60, 1000);
        Saw saw4 = new ElectricSaw("Ryobi", 2000, 30, "Brushless", 3000);
        sawManager.addSaw(saw1);
        sawManager.addSaw(saw2);
        sawManager.addSaw(saw3);
        sawManager.addSaw(saw4);
        List<Saw> found = sawManager.findAllChainsaws();
        assertEquals(2, found.size());
        assertTrue(found.contains(saw1));
        assertTrue(found.contains(saw3));
    }
}
