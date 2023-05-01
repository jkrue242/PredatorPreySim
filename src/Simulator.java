import java.util.HashMap;

/**
 * This is the main driver class for the simulation
 */
public class Simulator {

    /**
     * Simulation driver method
     * @param args command line input
     */
    public static void main(String args[])
    {
        // read arguments from command line
        // Note: Depending on the class, not every parameter will actually be necessary

        Wolf wolf = new Wolf();
        Fox fox = new Fox();
        Rabbit rabbit = new Rabbit();
        Mouse mouse = new Mouse();

        // loop through arguments
        for (int i = 0; i < args.length; i++)
        {
            // reset every loop
            double pop = 0.0;
            double gc = 0.0;
            double dc = 0.0;
            HashMap<String, Double> deltas = new HashMap<>();
            HashMap<String, Double> betas = new HashMap<>();

            // check for class names
            if (args[i].equals("Wolf"))
            {
                // wolf will have population, death coeff, alpha(fox), alpha(rabbit), alpha(mouse)

                // grab population
                pop = Double.parseDouble(args[i+1]);

                // grab death current
                dc = Double.parseDouble(args[i+2]);

                // grab deltas
                deltas.put("Fox", Double.parseDouble(args[i+3]));
                deltas.put("Rabbit", Double.parseDouble(args[i+4]));
                deltas.put("Mouse", Double.parseDouble(args[i+5]));

                // update Wolf object
                wolf.setPopulation(pop);
                wolf.setDeathCoeff(dc);
                wolf.setDeltas(deltas);

                System.out.println("\n");

                // shift i
                i += 5;
            }
            else if (args[i].equals("Fox"))
            {
                // fox will have population, growth coefficient, death coefficient, alpha(Rabbit), alpha (Mouse),
                // beta (wolf)

                // grab population
                pop = Double.parseDouble(args[i+1]);

                // grab growth coeff
                gc = Double.parseDouble(args[i+2]);

                // grab death coeff
                dc = Double.parseDouble(args[i+3]);

                // grab deltas
                deltas.put("Rabbit", Double.parseDouble(args[i+4]));
                deltas.put("Mouse", Double.parseDouble(args[i+5]));

                // grab betas
                betas.put("Wolf", Double.parseDouble(args[i+6]));

                // update fox object
                fox.setPopulation(pop);
                fox.setGrowthCoeff(gc);
                fox.setDeathCoeff(dc);
                fox.setDeltas(deltas);
                fox.setBetas(betas);

                // shift i
                i+=6;

            }
            else if (args[i].equals("Rabbit"))
            {
                // rabbit will have population, growth coefficient, beta (wolf), beta (fox)

                // grab population
                pop = Double.parseDouble(args[i+1]);

                // grab growth coeff
                gc = Double.parseDouble(args[i+2]);

                // grab betas
                betas.put("Wolf", Double.parseDouble(args[i+3]));
                betas.put("Fox", Double.parseDouble(args[i+4]));

                // update rabbit object
                rabbit.setPopulation(pop);
                rabbit.setGrowthCoeff(gc);
                rabbit.setBetas(betas);

                // update i
                i+=4;

            }
            else if (args[i].equals("Mouse"))
            {
                // mouse will have population, growth coefficient, beta(wolf), beta(fox)

                // grab population
                pop = Double.parseDouble(args[i+1]);

                // grab growth coeff
                gc = Double.parseDouble(args[i+2]);

                // grab betas
                betas.put("Wolf", Double.parseDouble(args[i+3]));
                betas.put("Fox", Double.parseDouble(args[i+4]));

                // update mouse object
                mouse.setPopulation(pop);
                mouse.setGrowthCoeff(gc);
                mouse.setBetas(betas);

                // shift i
                i+=4;
            }
        }

        System.out.println("============================================================================================\n" +
                "Model Parameters: \n______________________________________\n" +
                "Wolf: \nPop: "+wolf.getPopulationInst()+" | Death Coeff.: "+
                wolf.getDeathCoeff() + " | " + "Deltas: "+wolf.getDeltas().get("Fox")+"(Fox) "+wolf.getDeltas().get("Rabbit")
                +"(Rabbit) "+wolf.getDeltas().get("Mouse")+"(Mouse)\n--------------------------------------\n" +
                "Fox: \nPop: "+fox.getPopulationInst()+" | Growth Coeff.: "+fox.getGrowthCoeff()+" | Death Coeff.: "+
                fox.getDeathCoeff()+ " | Deltas: "+fox.getDeltas().get("Rabbit")+"(Rabbit) "+fox.getDeltas().get("Mouse")+
                "(Mouse) | Betas: "+fox.getBetas().get("Wolf")+"(Wolf)\n -------------------------------------\n" +
                "Rabbit: \nPop: "+rabbit.getPopulationInst()+" | Growth Coeff.: "+rabbit.getGrowthCoeff()+" | Betas: "+
                rabbit.getBetas().get("Wolf")+"(Wolf) "+rabbit.getBetas().get("Fox")+"(Fox)\n--------------------------------------\n" +
                "Mouse: \nPop: "+mouse.getPopulationInst() + " | Growth Coeff.: "+mouse.getGrowthCoeff()+ " | Betas: " +
                mouse.getBetas().get("Wolf")+"(Wolf) "+mouse.getBetas().get("Fox")+"(Fox)\n============================================================================================\n");

        // time parameters
        double time_limit = 100;
        double dt = 0.001;
        double time = 0.0;

        // this will keep track of population changes at each time intervals
        double last_wolf_population = wolf.getPopulation();
        double last_fox_population = fox.getPopulation();
        double last_rabbit_population = rabbit.getPopulation();
        double last_mouse_population = mouse.getPopulation();

        // print initial
        System.out.println("Simulation:");
        System.out.println("\n========================================\nTime: 0");
        System.out.println("Wolf population  : "+ wolf.getPopulation()  + "   | Change: "+0.0);
        System.out.println("Fox population   : "+ fox.getPopulation()   + "   | Change: "+0.0);
        System.out.println("Rabbit population: "+ rabbit.getPopulation()+ "   | Change: "+0.0);
        System.out.println("Mouse population : "+mouse.getPopulation()  + "   | Change: "+0.0+ "\n========================================");

        // simulate until time limit is reached
        while (time < time_limit)
        {
            // update wolf population
            wolf.setPopulation(wolf.getPopulationInst() + wolf.getInstPopulationChange(wolf, fox, rabbit, mouse, dt));

            // update fox population
            fox.setPopulation(fox.getPopulationInst() + fox.getInstPopulationChange(wolf, fox, rabbit, mouse, dt));

            // update rabbit population
            rabbit.setPopulation(rabbit.getPopulationInst() + rabbit.getInstPopulationChange(wolf, fox, rabbit, mouse, dt));

            // update mouse population
            mouse.setPopulation(mouse.getPopulationInst() + mouse.getInstPopulationChange(wolf, fox, rabbit, mouse, dt));

            // check if we are at an interval time value
            if ( (time % 1) < 0.0005)
            {
                // get changes
                double wolf_change = wolf.getPopulation() - last_wolf_population;
                double fox_change = fox.getPopulation() - last_fox_population;
                double rabbit_change = rabbit.getPopulation() - last_rabbit_population;
                double mouse_change = mouse.getPopulation() - last_mouse_population;

                System.out.println("Time: "+ ((int)time+1));
                System.out.println("Wolf population  : "+ wolf.getPopulation()  + "   | Change: "+wolf_change);
                System.out.println("Fox population   : "+ fox.getPopulation()   + "   | Change: "+fox_change);
                System.out.println("Rabbit population: "+ rabbit.getPopulation()+ "   | Change: "+rabbit_change);
                System.out.println("Mouse population : "+mouse.getPopulation()  + "   | Change: "+ mouse_change+ "\n========================================");

                // update change variables
                last_wolf_population = wolf.getPopulation();
                last_fox_population = fox.getPopulation();
                last_rabbit_population = rabbit.getPopulation();
                last_mouse_population = mouse.getPopulation();
            }

            // update time
            time += dt;

        }
    }
}
