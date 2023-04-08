package ua.lviv.iot.algo.part1.lab2;

import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
public class MainTest {

    @Test
    public void testChainsawManager() {
        ChainsawManager chainsawManager = new ChainsawManager();
        chainsawManager.addSaw(new Chainsaw("Stihl", 2000, 500, 500));
        chainsawManager.addSaw(new Chainsaw("Husqvarna", 1800, 400, 400));
        chainsawManager.addSaw(new Chainsaw("Makita", 2200, 550, 600));
        chainsawManager.addSaw(new Chainsaw("Echo", 1600, 350, 300));
        chainsawManager.addSaw(new Chainsaw("Black & Decker", 1200, 250, 200));

        List<Chainsaw> allChainsaws = (List<Chainsaw>) chainsawManager.getSawList();
        assertEquals(5, allChainsaws.size());

        List<Chainsaw> chainsawsWithPowerGreaterThan1800 = chainsawManager.findAllWithPowerGreaterThan(1800);
        assertEquals(0, chainsawsWithPowerGreaterThan1800.size());
    }

    @Test
    public void testElectricSawManager() {
        ElectricSawManager electricSawManager = new ElectricSawManager();
        electricSawManager.addSaw(new ElectricSaw("Bosch", 2000, 600, "Brushless", 1000));
        electricSawManager.addSaw(new ElectricSaw("Dewalt", 1800, 550, "Brushless", 800));
        electricSawManager.addSaw(new ElectricSaw("Makita", 2200, 700, "Brushed", 1200));

        List<ElectricSaw> allElectricSaws = electricSawManager.getSawList();
        assertEquals(3, allElectricSaws.size());

        List<ElectricSaw> electricSawsWithRemainingWorkTimeGreaterThan2 = electricSawManager.findAllWithRemainingWorkTimeGreaterThan(2);
        assertEquals(3, electricSawsWithRemainingWorkTimeGreaterThan2.size());
    }

}
