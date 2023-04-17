package ua.lviv.iot.algo.part1.lab2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ChainsawTest {

    @Test
    void testChainsaw() {
        Chainsaw chainsaw = new Chainsaw("Stihl", 2.5, 60);
        assertFalse(chainsaw.isWorking());

        chainsaw.start();
        assertTrue(chainsaw.isWorking());

        chainsaw.cutWood(100);
        assertFalse(chainsaw.isWorking());
        assertEquals(0.0, chainsaw.getFuelLevel());

        chainsaw.cutWood(500);
        assertFalse(chainsaw.isWorking());
        assertEquals(0.0, chainsaw.getFuelLevel());

        assertEquals(0, chainsaw.getRemainingWorkTime());
    }
    @Test
    void testFuelLevel() {
        Chainsaw chainsaw = new Chainsaw("Stihl", 2.5, 60);
        chainsaw.cutWood(50);
        assertEquals(0.0, chainsaw.getFuelLevel());
        chainsaw.cutWood(50);
        assertEquals(0.0, chainsaw.getFuelLevel());
        chainsaw.cutWood(250);
        assertEquals(0.0, chainsaw.getFuelLevel());
    }
    @Test
    public void testFuelTankCapacity() {
        double expectedFuelTankCapacity = 2.0;
        Chainsaw chainsaw = new Chainsaw("Stihl", 2.5, 1.0);
        double actualFuelTankCapacity = chainsaw.getFuelTankCapacity();
        assertEquals(expectedFuelTankCapacity, actualFuelTankCapacity, 0.01);
    }
    @Test
    public void testStop() {
        Chainsaw chainsaw = new Chainsaw("Stihl", 2.5, 1.0);
        chainsaw.start();
        assertTrue(chainsaw.isWorking());
        chainsaw.stop();
        assertFalse(chainsaw.isWorking());
    }
    @Test
    public void testGettersAndSetters() {
        Chainsaw chainsaw = new Chainsaw("null", 2.5, 1.0);
        chainsaw.setBrand("Husqvarna");
        assertEquals("Husqvarna", chainsaw.getBrand());

        assertEquals(0.0, chainsaw.getPower(), 0.01);
        chainsaw.setPower(3.0);
        assertEquals(3.0, chainsaw.getPower(), 0.01);

        assertEquals(0.0, chainsaw.getEngineWorkDuration(), 0.01);
        chainsaw.setEngineWorkDuration(1.5);
        assertEquals(1.5, chainsaw.getEngineWorkDuration(), 0.01);

        assertEquals(2.0, chainsaw.getFuelTankCapacity(), 0.01);
        chainsaw.setFuelTankCapacity(2.5);
        assertEquals(2.5, chainsaw.getFuelTankCapacity(), 0.01);

        assertEquals(0.0, chainsaw.getFuelLevel(), 0.01);
        chainsaw.setFuelLevel(1.5);
        assertEquals(1.5, chainsaw.getFuelLevel(), 0.01);

        assertEquals("Chainsaw(super=Saw(brand=Husqvarna, power=3.0, engineWorkDuration=1.5, isWorking=false), fuelTankCapacity=2.5, fuelLevel=1.5)", chainsaw.toString());
    }
}
