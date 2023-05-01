import java.util.HashMap;

/**
 * This is the Fox class that extends Animal
 */
public class Fox extends Animal{

    /**
     * Default Constructor
     */
    public Fox()
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
    public Fox(double pop, double gc, double dc, HashMap<String, Double> deltas, HashMap<String, Double> betas)
    {
        super(pop, gc, dc, deltas, betas);
    }

    /**
     * Returns the instantaneous population change for Fox
     * @param wolf Wolf object
     * @param fox Fox object reference
     * @param rabbit Rabbit object reference
     * @param mouse Mouse object reference
     * @param dt Time
     * @return instantaneous population change
     */
    public double getInstPopulationChange(Wolf wolf, Fox fox, Rabbit rabbit, Mouse mouse, double dt)
    {
        // fox eats rabbit, mouse, gets eaten by wolf
        return ( (Predator.calculateGrowth(this.getPopulation(), this.getDeltas().get("Rabbit"), rabbit.getPopulation())) +
                (Predator.calculateGrowth(this.getPopulation(), this.getDeltas().get("Mouse"), mouse.getPopulation())) +
                (Prey.calculateGrowth(this.getPopulation(), this.getBetas().get("Wolf"), wolf.getPopulation())) +
                (this.getPopulation() * this.getGrowthCoeff()) -
                (this.getPopulation() * this.getDeathCoeff())) * dt;
    }

}
