import org.junit.jupiter.api.*;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

/**
 * SimulationTests tests equation calculations for the competition simulation
 */
public class SimulationTests {

    /**
     * Test instantaneous predator growth component
     */
    @Test
    public void testInstPredatorGrowthCalculation1()
    {
        Assertions.assertTrue(Math.abs(357.5- Predator.calculateGrowth(11, 0.5, 65)) < 1e-3);
    }

    /**
     * Test instantaneous predator growth component
     */
    @Test
    public void testInstPredatorGrowthCalculation2()
    {
        Assertions.assertTrue(Math.abs(1.8-Predator.calculateGrowth(2.0, 0.3, 3.0))<1e-3);
    }

    /**
     * Test instantaneous predator growth component
     */
    @Test
    public void testInstPredatorGrowthCalculation3()
    {
        Assertions.assertTrue(Math.abs(0.0 - Predator.calculateGrowth(18, 0.3, 0)) < 1e-3);
    }

    /**
     * Test instantaneous prey growth component
     */
    @Test
    public void testInstPreyGrowthCalculation1()
    {
        Assertions.assertTrue(Math.abs(357.5+ Prey.calculateGrowth(11, 0.5, 65)) < 1e-3);
    }

    /**
     * Test instantaneous prey growth component
     */
    @Test
    public void testInstPreyGrowthCalculation2()
    {
        Assertions.assertTrue(Math.abs(1.8+Prey.calculateGrowth(2.0, 0.3, 3.0))<1e-3);
    }

    /**
     * Test instantaneous prey growth component
     */
    @Test
    public void testInstPreyGrowthCalculation3()
    {
        Assertions.assertTrue(Math.abs(0.0 + Prey.calculateGrowth(18, 0.3, 0)) < 1e-3);
    }

    /**
     Tests instantaneous population change for Wolf
     */
    @Test
    public void testInstWolfChange()
    {
        HashMap<String, Double> wolf_deltas = new HashMap<>();
        wolf_deltas.put("Fox", 0.3);
        wolf_deltas.put("Rabbit", 0.5);
        wolf_deltas.put("Mouse", 0.6);

        HashMap<String, Double> wolf_betas = new HashMap<>();

        HashMap<String, Double> fox_deltas = new HashMap<>();
        fox_deltas.put("Rabbit", 0.6);
        fox_deltas.put("Mouse", 0.7);

        HashMap<String, Double> fox_betas = new HashMap<>();
        fox_betas.put("Wolf", 0.3);

        HashMap<String, Double> rabbit_deltas = new HashMap<>();

        HashMap<String, Double> rabbit_betas = new HashMap<>();
        rabbit_betas.put("Wolf", 0.4);
        rabbit_betas.put("Fox", 0.6);

        HashMap<String, Double> mouse_deltas = new HashMap<>();

        HashMap<String, Double> mouse_betas = new HashMap<>();
        mouse_betas.put("Wolf", 0.3);
        mouse_betas.put("Fox", 0.2);

        Wolf test_wolf = new Wolf(3, 0.0, 0.7, wolf_deltas, wolf_betas);
        Fox test_fox = new Fox(2, 0.4, 0.6, fox_deltas, fox_betas);
        Rabbit test_rabbit = new Rabbit(15, 0.6, 0.0, rabbit_deltas, rabbit_betas);
        Mouse test_mouse = new Mouse(30, 0.7, 0.0, mouse_deltas, mouse_betas);

        double dt = 0.001;
        double wolf_fox_change = Predator.calculateGrowth(test_wolf.getPopulation(), test_wolf.getDeltas().get("Fox"),test_fox.getPopulation());
        double wolf_rabbit_change = Predator.calculateGrowth(test_wolf.getPopulation(), test_wolf.getDeltas().get("Rabbit"),test_rabbit.getPopulation());
        double wolf_mouse_change = Predator.calculateGrowth(test_wolf.getPopulation(), test_wolf.getDeltas().get("Mouse"),test_mouse.getPopulation());

        assertEquals((wolf_fox_change + wolf_rabbit_change + wolf_mouse_change - (test_wolf.getPopulation() *
                test_wolf.getDeathCoeff())) * dt,
                test_wolf.getInstPopulationChange(test_wolf, test_fox, test_rabbit, test_mouse, 0.001));
    }

    /**
    Tests instantaneous population change for Fox
     */
    @Test
    public void testInstFoxChange()
    {
        HashMap<String, Double> wolf_deltas = new HashMap<>();
        wolf_deltas.put("Fox", 0.3);
        wolf_deltas.put("Rabbit", 0.5);
        wolf_deltas.put("Mouse", 0.6);

        HashMap<String, Double> wolf_betas = new HashMap<>();

        HashMap<String, Double> fox_deltas = new HashMap<>();
        fox_deltas.put("Rabbit", 0.6);
        fox_deltas.put("Mouse", 0.7);

        HashMap<String, Double> fox_betas = new HashMap<>();
        fox_betas.put("Wolf", 0.3);

        HashMap<String, Double> rabbit_deltas = new HashMap<>();

        HashMap<String, Double> rabbit_betas = new HashMap<>();
        rabbit_betas.put("Wolf", 0.4);
        rabbit_betas.put("Fox", 0.6);

        HashMap<String, Double> mouse_deltas = new HashMap<>();

        HashMap<String, Double> mouse_betas = new HashMap<>();
        mouse_betas.put("Wolf", 0.3);
        mouse_betas.put("Fox", 0.2);

        Wolf test_wolf = new Wolf(3, 0.0, 0.7, wolf_deltas, wolf_betas);
        Fox test_fox = new Fox(2, 0.4, 0.6, fox_deltas, fox_betas);
        Rabbit test_rabbit = new Rabbit(15, 0.6, 0.0, rabbit_deltas, rabbit_betas);
        Mouse test_mouse = new Mouse(30, 0.7, 0.0, mouse_deltas, mouse_betas);
        double dt = 0.001;
        double fox_wolf_change = Prey.calculateGrowth(test_fox.getPopulation(), test_fox.getBetas().get("Wolf"),test_wolf.getPopulation());
        double fox_rabbit_change = Predator.calculateGrowth(test_fox.getPopulation(), test_fox.getDeltas().get("Rabbit"),test_rabbit.getPopulation());
        double fox_mouse_change = Predator.calculateGrowth(test_fox.getPopulation(), test_fox.getDeltas().get("Mouse"),test_mouse.getPopulation());
        assertEquals((fox_wolf_change + fox_rabbit_change + fox_mouse_change - (test_fox.getPopulation() *
                        test_fox.getDeathCoeff()) + (test_fox.getPopulation() * test_fox.getGrowthCoeff())) * dt,
                test_fox.getInstPopulationChange(test_wolf, test_fox, test_rabbit, test_mouse, 0.001));
    }

    /**
    Tests instantaneous change for Rabbit
    */
    @Test
    public void testInstRabbitChange()
    {
        HashMap<String, Double> wolf_deltas = new HashMap<>();
        wolf_deltas.put("Fox", 0.3);
        wolf_deltas.put("Rabbit", 0.5);
        wolf_deltas.put("Mouse", 0.6);

        HashMap<String, Double> wolf_betas = new HashMap<>();

        HashMap<String, Double> fox_deltas = new HashMap<>();
        fox_deltas.put("Rabbit", 0.6);
        fox_deltas.put("Mouse", 0.7);

        HashMap<String, Double> fox_betas = new HashMap<>();
        fox_betas.put("Wolf", 0.3);

        HashMap<String, Double> rabbit_deltas = new HashMap<>();

        HashMap<String, Double> rabbit_betas = new HashMap<>();
        rabbit_betas.put("Wolf", 0.4);
        rabbit_betas.put("Fox", 0.6);

        HashMap<String, Double> mouse_deltas = new HashMap<>();

        HashMap<String, Double> mouse_betas = new HashMap<>();
        mouse_betas.put("Wolf", 0.3);
        mouse_betas.put("Fox", 0.2);

        Wolf test_wolf = new Wolf(3, 0.0, 0.7, wolf_deltas, wolf_betas);
        Fox test_fox = new Fox(2, 0.4, 0.6, fox_deltas, fox_betas);
        Rabbit test_rabbit = new Rabbit(15, 0.6, 0.0, rabbit_deltas, rabbit_betas);
        Mouse test_mouse = new Mouse(30, 0.7, 0.0, mouse_deltas, mouse_betas);
        double dt = 0.001;
        double rabbit_wolf_change = Prey.calculateGrowth(test_rabbit.getPopulation(), test_rabbit.getBetas().get("Wolf"),test_wolf.getPopulation());
        double rabbit_fox_change = Prey.calculateGrowth(test_rabbit.getPopulation(), test_rabbit.getBetas().get("Fox"),test_fox.getPopulation());
        assertEquals((rabbit_wolf_change + rabbit_fox_change + (test_rabbit.getPopulation() * test_rabbit.getGrowthCoeff())) * dt,
                test_rabbit.getInstPopulationChange(test_wolf, test_fox, test_rabbit, test_mouse, 0.001));
    }

    /**
    Tests instantaneous population change for Mouse
    */
    @Test
    public void testInstMouseChange()
    {
        HashMap<String, Double> wolf_deltas = new HashMap<>();
        wolf_deltas.put("Fox", 0.3);
        wolf_deltas.put("Rabbit", 0.5);
        wolf_deltas.put("Mouse", 0.6);

        HashMap<String, Double> wolf_betas = new HashMap<>();

        HashMap<String, Double> fox_deltas = new HashMap<>();
        fox_deltas.put("Rabbit", 0.6);
        fox_deltas.put("Mouse", 0.7);

        HashMap<String, Double> fox_betas = new HashMap<>();
        fox_betas.put("Wolf", 0.3);

        HashMap<String, Double> rabbit_deltas = new HashMap<>();

        HashMap<String, Double> rabbit_betas = new HashMap<>();
        rabbit_betas.put("Wolf", 0.4);
        rabbit_betas.put("Fox", 0.6);

        HashMap<String, Double> mouse_deltas = new HashMap<>();

        HashMap<String, Double> mouse_betas = new HashMap<>();
        mouse_betas.put("Wolf", 0.3);
        mouse_betas.put("Fox", 0.2);

        Wolf test_wolf = new Wolf(3, 0.0, 0.7, wolf_deltas, wolf_betas);
        Fox test_fox = new Fox(2, 0.4, 0.6, fox_deltas, fox_betas);
        Rabbit test_rabbit = new Rabbit(15, 0.6, 0.0, rabbit_deltas, rabbit_betas);
        Mouse test_mouse = new Mouse(30, 0.7, 0.0, mouse_deltas, mouse_betas);
        double dt = 0.001;
        double mouse_wolf_change = Prey.calculateGrowth(test_mouse.getPopulation(), test_mouse.getBetas().get("Wolf"),test_wolf.getPopulation());
        double mouse_fox_change = Prey.calculateGrowth(test_mouse.getPopulation(), test_mouse.getBetas().get("Fox"),test_fox.getPopulation());
        assertEquals((mouse_wolf_change + mouse_fox_change + (test_mouse.getPopulation() * test_mouse.getGrowthCoeff())) * dt,
                test_mouse.getInstPopulationChange(test_wolf, test_fox, test_rabbit, test_mouse, 0.001));
    }
}
