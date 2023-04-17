package ua.lviv.iot.algo.part1.lab2;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ChainsawElectricSawTest {

    private ChainsawManager chainsawManager;
    private ElectricSawManager electricSawManager;

    @Before
    public void setUp() {
        chainsawManager = new ChainsawManager();
        chainsawManager.addSaw(new Chainsaw("Stihl", 2000));
        chainsawManager.addSaw(new Chainsaw("Husqvarna", 1800));
        chainsawManager.addSaw(new Chainsaw("Makita", 2200));
        chainsawManager.addSaw(new Chainsaw("Echo", 1600));
        chainsawManager.addSaw(new Chainsaw("Black & Decker", 1200));

        electricSawManager = new ElectricSawManager();
        electricSawManager.addSaw(new ElectricSaw("Bosch", 2000, 600));
        electricSawManager.addSaw(new ElectricSaw("Dewalt", 1800, 550));
        electricSawManager.addSaw(new ElectricSaw("Makita", 2200, 700));
        electricSawManager.addSaw(new ElectricSaw("Ryobi", 1500, 400));
        electricSawManager.addSaw(new ElectricSaw("Milwaukee", 1700, 500));
    }

    @Test
    public void testChainsawManager() {
        // Test that all chainsaws are added to the list
        List<Object> chainsaws = (List<Object>) chainsawManager.getSawList();
        assertEquals(5, chainsaws.size());

        // Test findAllWithPowerGreaterThan() method
        List<Chainsaw> powerfulChainsaws = chainsawManager.findAllWithPowerGreaterThan(1800);
        assertEquals(0, powerfulChainsaws.size());
        assertEquals("Stihl", powerfulChainsaws.get(0).getBrand());
        assertEquals("Makita", powerfulChainsaws.get(1).getBrand());
        assertEquals("Makita", powerfulChainsaws.get(2).getBrand());


        // Test findAllWithBrand() method
        List<Chainsaw> makitaChainsaws = chainsawManager.findAllWithBrand("Makita");
        assertEquals(2, makitaChainsaws.size());
        assertEquals("Makita", makitaChainsaws.get(0).getBrand());
        assertEquals("Makita", makitaChainsaws.get(1).getBrand());

        // Test that an empty list is returned if no chainsaws match the criteria
        List<Chainsaw> emptyList = chainsawManager.findAllWithBrand("Unknown Brand");
        assertEquals(0, emptyList.size());
    }

    // Test ElectricSawManager
    @Test
    public void testElectricSawManager() {
        // Test that all electric saws are added to the list
        List<ElectricSaw> electricSaws = electricSawManager.getSawList();
      //  assertEquals(5, electricSaws.size());

        // Test findAllWithRemainingWorkTimeGreaterThan() method
        List<ElectricSaw> longLastingSaws = electricSawManager.findAllWithRemainingWorkTimeGreaterThan(5);
        assertEquals(0, longLastingSaws.size());
        assertEquals("Bosch", longLastingSaws.get(1).getBrand());
        assertEquals("Dewalt", longLastingSaws.get(2).getBrand());
        assertEquals("Milwaukee", longLastingSaws.get(3).getBrand());

        // Test findAllWithBrand() method
        List<ElectricSaw> boschSaws = electricSawManager.findAllWithBrand("Bosch");
        assertEquals(1, boschSaws.size());
        assertEquals("Bosch", boschSaws.get(0).getBrand());

        // Test finding non-existing brand with findAllWithBrand() method
        List<ElectricSaw> emptyList = electricSawManager.findAllWithBrand("Unknown Brand");
        assertEquals(1, emptyList.size());
    }
}

