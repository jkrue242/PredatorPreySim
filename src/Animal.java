import java.util.HashMap;

/**
 * Animal class is the base class for each species in the model
 */
public class Animal {

    /**
     * Default Constructor
     */
    public Animal()
    {
        population = 0.0;
    }

    /**
     * Input Constructor
     * @param population population
     * @param gc growth coefficient
     * @param dc death coefficient
     * @param deltas delta map
     * @param betas beta map
     */
    public Animal(double population, double gc, double dc, HashMap<String, Double> deltas, HashMap<String, Double> betas)
    {
        this.population = population;
        growth_coeff = gc;
        death_coeff = dc;
        this.deltas = deltas;
        this.betas = betas;
    }

    /**
     * Returns the instantaneous population change
     * @param wolf Wolf object
     * @param fox Fox object reference
     * @param rabbit Rabbit object reference
     * @param mouse Mouse object reference
     * @param dt Time
     * @return instantaneous population change
     */
    public double getInstPopulationChange(Wolf wolf, Fox fox, Rabbit rabbit, Mouse mouse, double dt)
    {
        return 0.0;
    }

    /**
     * Returns the instantaneous population
     * @return instantaneous population
     */
    public double getPopulationInst()
    {
        if (population <= 0)
        {
            population = 0;
        }
        return population;
    }

    /**
     * Returns the integer value of population rounding up
     * @return population
     */
    public double getPopulation()
    {
        if (population <= 0)
        {
            return 0.0;
        }
        return Math.abs(Math.ceil(population));
    }

    /**
     * Sets the population
     * @param population population
     */
    public void setPopulation(double population)
    {
        if (population <= 0)
        {
            population = 0;
        }
        this.population = population;
    }

    /**
     * Sets growth coefficient
     * @param gc growth coefficient
     */
    public void setGrowthCoeff(double gc)
    {
        growth_coeff = gc;
    }

    /**
     * Sets death coefficient
     * @param dc death coefficient
     */
    public void setDeathCoeff(double dc){
        death_coeff = dc;
    }

    /**
     * Gets growth coefficient
     * @return growth coefficient
     */
    public double getGrowthCoeff(){return growth_coeff;}

    /**
     * Gets death coefficient
     * @return death coefficient
     */
    public double getDeathCoeff(){return death_coeff;}

    /**
     * Sets the delta hash map
     * @param delta_map delta hash map
     */
    public void setDeltas(HashMap<String, Double> delta_map)
    {deltas = delta_map;}

    /**
     * Gets the delta hash map
     * @return delta hash map
     */
    public HashMap<String, Double> getDeltas(){return deltas;}

    /**
     * Sets the beta hash map
     * @param beta_map beta hash map
     */
    public void setBetas(HashMap<String, Double> beta_map)
    {betas = beta_map;}

    /**
     * Gets the beta hash map
     * @return beta hash map
     */
    public HashMap<String, Double> getBetas(){return betas;}
    private double population;
    private double growth_coeff;
    private double death_coeff;

    private HashMap<String, Double> deltas;
    private HashMap<String, Double> betas;
}
