import java.util.HashMap;

/**
 * This is the Wolf class that extends Animal
 */
public class Wolf extends Animal{

    /**
     * Default Constructor
     */
    public Wolf()
    {
        super();
    }

    /**
     * Input Constructor
     * @param pop population
     * @param gc growth coefficient
     * @param dc death coefficient
     * @param deltas delta hash map
     * @param betas beta hash map
     */
    public Wolf(double pop, double gc, double dc, HashMap<String, Double> deltas, HashMap<String, Double> betas)
    {
        super(pop, gc, dc, deltas, betas);
    }

    /**
     * Returns the instantaneous population change for Wolf
     * @param wolf Wolf object
     * @param fox Fox object reference
     * @param rabbit Rabbit object reference
     * @param mouse Mouse object reference
     * @param dt Time
     * @return instantaneous population change
     */
    @Override
    public double getInstPopulationChange(Wolf wolf, Fox fox, Rabbit rabbit, Mouse mouse, double dt) {

        // wolf eats fox, rabbit, mouse
        return ( (Predator.calculateGrowth(this.getPopulation(), this.getDeltas().get("Fox"), fox.getPopulation())) +
                (Predator.calculateGrowth(this.getPopulation(), this.getDeltas().get("Rabbit"), rabbit.getPopulation())) +
                (Predator.calculateGrowth(this.getPopulation(), this.getDeltas().get("Mouse"), mouse.getPopulation())) -
                (this.getPopulation() * this.getDeathCoeff())) * dt;
    }
}
