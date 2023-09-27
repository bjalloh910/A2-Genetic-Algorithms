import java.util.ArrayList;
import java.util.Comparator;
public class GA_Simulation{
    private int n;          //  The number of individuals in the population 
    private int k;          //  The number of winners in each generation 
    private int r;          //  The number of evolution rounds to run 
    private int c_0;        //  The initial chromosome size 
    private int c_max;      //  The maximum chromosome size 
    private double m;       //  Chance per round of a mutation in each gen
    private int g;          //  Number of letters possible per gene

    public GA_Simulation(int n, int k, int r, int c_0, int c_max, double m, int g ){
        this.n = n;
        this.k = k;
        this.r = r;
        this.c_0 = c_0;
        this.c_max = c_max;
        this.m = m;
        this.g = g;
        ArrayList<Individual> population;

    }

// `init` initializes a population of the desired size, calling the `Individual` setup constructor for each one.
    public void init(int n, ArrayList<Individual> population){
       for( int i= 0; i<n; i++){
        Individual newPerson = new Individual();
        population.add(newPerson);
       }
     }  

      /** Sorts population by fitness score, best first */
    public void rankPopulation(ArrayList<Individual> pop) {
      // sort population by fitness
      Comparator<Individual> ranker = new Comparator<>() {
        // this order will sort higher scores at the front
        public int compare(Individual c1, Individual c2) {
          return (int)Math.signum(c2.calculateFitness()-c1.calculateFitness());
        }
      };
      pop.sort(ranker); 
    }

    
    public void evolve(ArrayList<Individual> population) {
    for (int round = 0; round < r; round++) {
        // Step 1: Determine the k winners of the previous round
        ArrayList<Individual> winners = new ArrayList<>(population.subList(0, k));

        // Step 2: Generate a new generation
        ArrayList<Individual> newGeneration = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // Randomly select two parents from the winners
            Individual parent1 = winners.get((int) (Math.random() * k));
            Individual parent2 = winners.get((int) (Math.random() * k));

            // Create a new individual as the offspring of two parents
            Individual child = new Individual(); // You need to define how to create a new individual

            // Apply mutation to the child
            // You need to define your mutation logic here
            // Modify the child's chromosome based on your mutation logic

            // Add the child to the new generation
            newGeneration.add(child);
        }

        // Step 3: Rank the new generation based on fitness
        rankPopulation(newGeneration);

        // Step 4: Replace the current population with the new generation
        population.clear();
        population.addAll(newGeneration);
    }

    // At the end, taking the individual(s) with the best fitness as the solution
    Individual bestSolution = population.get(0); // Assuming it's sorted by fitness
    // Perform any additional actions with the bestSolution as needed
}


    public static void main(String[] args) {
    int n = 10; 
    int k = 5; 
    int r = 5;
    int c_0 = 10; 
    int c_max = 20; 
    double m = 0.1; 
    int g = 4; 

    GA_Simulation gaSimulation = new GA_Simulation(n, k, r, c_0, c_max, m, g);

    // Create an ArrayList to represent the population
    ArrayList<Individual> population = new ArrayList<>();

    // Call the init method to initialize the population
    gaSimulation.init(n, population);

    // Print or inspect the initialized population
    for (Individual individual : population) {
        System.out.println(individual); 
    }
  }
}