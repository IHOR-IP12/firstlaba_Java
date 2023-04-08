package ua.lviv.iot.algo.part1.lab2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SawTest {
    @Test
    void testAllArgsConstructor() {
        Chainsaw chainsaw = new Chainsaw("Husqvarna", 1200.0, 4.5, 0.5);
        //assertEquals("null", chainsaw.getBrand());
        assertEquals(0.0, chainsaw.getPower(), 0.0001);
        assertEquals(0.0, chainsaw.getEngineWorkDuration(), 0.0001);
        assertEquals(0.5, chainsaw.getFuelTankCapacity(), 0.0001);
        assertEquals(0.0, chainsaw.getFuelLevel(), 0.0001);
    }
}