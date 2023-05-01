/**
 * Prey class handles interactions between a prey and 1 predator
 */
public class Prey {

    /**
     * Calculates the growth component of Prey-Predator relationship
     * @param prey_pop prey population
     * @param beta prey-predator beta
     * @param pred_prop predator population
     * @return growth component
     */
    public static double calculateGrowth(double prey_pop, double beta, double pred_prop) {
        return -1.0*(beta * prey_pop * pred_prop) ;
    }
}
