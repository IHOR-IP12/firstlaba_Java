package ua.lviv.iot.algo.part1.lab2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static java.lang.Double.parseDouble;
import static org.junit.jupiter.api.Assertions.*;

class ElectricSawTest {
    @Test
    void testElectricSawGettersSetters() {
        ElectricSaw saw = new ElectricSaw();
        saw.setBrand("Dewalt");
        saw.setPower(800.0);
        saw.setEngineWorkDuration(2.5);
        saw.setMotorType("Brushless");
        saw.setBatteryCapacity(1.5);

        assertEquals("Dewalt", saw.getBrand());
        assertEquals(800.0, saw.getPower(), 0.0001);
        assertEquals(2.5, saw.getEngineWorkDuration(), 0.0001);
        assertEquals("Brushless", saw.getMotorType());
        assertEquals(1.5, saw.getBatteryCapacity(), 0.0001);
    }

        private final double DELTA = 0.0001;

        @Test
        public void testGetRemainingWorkTime() {
            ElectricSaw saw = new ElectricSaw("BrandA", 1500, 5, "Brushless", 30);
            double expectedRemainingTime = 30 / (1500 / 5);
            double fiftin;
            assertEquals(parseDouble("fiftin"), saw.getRemainingWorkTime(), 0.01);
        }
    }
