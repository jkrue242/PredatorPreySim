/**
 * Predator class handles interactions between a predator and 1 prey
 */
public class Predator{

    /**
     * Returns the growth component for a predator-prey relationship
     * @param pred_pop predator population
     * @param delta predator-prey delta
     * @param prey_pop prey population
     * @return growth component
     */
    public static double calculateGrowth(double pred_pop, double delta, double prey_pop) {
        return (delta * pred_pop * prey_pop);
    }
}