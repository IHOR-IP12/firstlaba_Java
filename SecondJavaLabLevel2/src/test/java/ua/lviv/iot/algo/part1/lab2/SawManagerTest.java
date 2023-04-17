package ua.lviv.iot.algo.part1.lab2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class SawManagerTest {
    SawManager<Saw> sawManager;
@BeforeEach
 void setup() {
    sawManager = new SawManager<Saw>();
}
    @Test
    void testFindAllWithPowerGreaterThan() {


        Saw saw1 = new Chainsaw("Bosch", 5.0);
        Saw saw2 = new Chainsaw("Dewalt", 9.0);
        Saw saw3 = new Chainsaw("Makita", 5.0);

        sawManager.addSaw(saw1);
        sawManager.addSaw(saw2);
        sawManager.addSaw(saw3);

        List<Saw> sawList = sawManager.findAllWithPowerGreaterThan(10.0);

        assertEquals(0, sawList.size());
        assertFalse(sawList.contains(saw1));
        assertFalse(sawList.contains(saw2));
        assertFalse(sawList.contains(saw3));
    }

    @Test
    void testFindAllChainsaws() {
        SawManager<Saw> sawManager = new SawManager<Saw>();

        Saw saw1 = new Chainsaw("Bosch", 10.0);
        Saw saw2 = new Chainsaw("Dewalt", 15.0);
        Saw saw3 = new Chainsaw("Makita", 5.0);

        sawManager.addSaw(saw1);
        sawManager.addSaw(saw2);
        sawManager.addSaw(saw3);

        List<Chainsaw> chainsaws = sawManager.findAllChainsaws();

        assertEquals(3, chainsaws.size());
        assertTrue(chainsaws.contains(saw1));
        assertTrue(chainsaws.contains(saw2));
        assertTrue(chainsaws.contains(saw3));
    }
    @Test
    void testFindAllWithRemainingWorkTimeGreaterThan() {
        SawManager<Saw> sawManager = new SawManager<Saw>();

        Saw saw1 = new Chainsaw("Bosch", 10.0);
        Saw saw2 = new Chainsaw("Dewalt", 15.0);
        Saw saw3 = new ElectricSaw("Makita", 8.0, 4.0);

        sawManager.addSaw(saw1);
        sawManager.addSaw(saw2);
        sawManager.addSaw(saw3);

        List<Saw> result = sawManager.findAllWithRemainingWorkTimeGreaterThan(3.0);

        assertEquals(0, result.size());
        assertFalse(result.contains(saw2));
        assertFalse(result.contains(saw3));
    }
}

