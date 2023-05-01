import java.util.HashMap;

/**
 * This is the Mouse class that extends Animal
 */
public class Mouse extends Animal {

    /**
     * Default Constructor
     */
    public Mouse(){
        super();
    }

    /**
     * Input constructor
     * @param pop population
     * @param gc growth coefficient
     * @param dc death coefficient
     * @param deltas delta hash map
     * @param betas beta hash map
     */
    public Mouse(double pop, double gc, double dc, HashMap<String, Double> deltas, HashMap<String, Double> betas)
    {
        super(pop, gc, dc, deltas, betas);
    }

    /**
     * Gets instantaneous population change for Mouse
     * @param wolf Wolf object
     * @param fox Fox object reference
     * @param rabbit Rabbit object reference
     * @param mouse Mouse object reference
     * @param dt Time
     * @return instantaneous population change
     */
    @Override
    public double getInstPopulationChange(Wolf wolf, Fox fox, Rabbit rabbit, Mouse mouse, double dt) {

        // mouse gets eaten by wolf, fox
        return ( (Prey.calculateGrowth(this.getPopulation(), this.getBetas().get("Wolf"), wolf.getPopulation())) +
                (Prey.calculateGrowth(this.getPopulation(), this.getBetas().get("Fox"), fox.getPopulation())) +
                (this.getPopulation() * this.getGrowthCoeff())) * dt;
    }
}
