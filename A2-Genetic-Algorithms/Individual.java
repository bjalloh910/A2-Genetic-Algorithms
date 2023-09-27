import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Individual {

    private ArrayList<Character> chromosome = new ArrayList<>(); // Declaring the chromosome variable as an ArrayList<Character>

    int c_0 = 0;
    int c_max = 20;
    double m = 0.01;
    int g = 4;
// first constructor generates initial population members
    public Individual(){  // needs to allocate a sequence of the desired length
        for( int i= 0; i <= c_0; i++){ 
            this.chromosome.add(randomLetter(g));
       }
    }
// Constructor to generate offspring from two parents in subsequent generations
    public Individual(ArrayList<Character> parent1Chromosome, ArrayList<Character> parent2Chromosome){
       // only the 15 winners bread??
    }

    private Character randomLetter(int num_dna_letters) {  /** Choose a letter at random, in the range * from A to the number of letters indicated */
      return Character.valueOf((char)(65+ThreadLocalRandom.current().nextInt(num_dna_letters)));
    }

    public String toString() { /** Expresses the individual's chromosome * as a String, for display purposes */
        StringBuilder builder = new StringBuilder(chromosome.size());
        for(Character ch: chromosome) {
          builder.append(ch);
        }
        return builder.toString();
        //return chromosome.stream().map(e->e.toString()).collect(Collectors.joining());
    }

    public int calculateFitness(){
      int fitness = 0;
      for( int i= 0; i<chromosome.size(); i++){
        Character current = this.chromosome.get(i);   //get the value at the index 
        Character mirror = chromosome.get(chromosome.size()-i-1); // get the size of the chromosome-subtract the current index and then subtract by 1.

        if(current == mirror){
          fitness++;
        } else {
          fitness--;
        }
      }
      for( int i=1; i<chromosome.size(); i++){
        Character current = this.chromosome.get(i);
        Character prev = chromosome.get(i-1); //retrieving the character before the index

        if( current == prev){
          fitness--;
        } 
      }
      return fitness; 
    }

     public void mutate() {
        // Define your mutation logic here
        int geneIndexToMutate = (int) (Math.random() * chromosome.length); // Assuming 'chromosome' is your gene representation
        double newValue = Math.random(); // Modify this as needed for your gene type
        chromosome[geneIndexToMutate] = newValue;
    }

    public static void main(String[] args) {
        
        Individual individual = new Individual();
        System.out.println(individual.chromosome);

        int fitness = individual.calculateFitness();

        System.out.println(fitness);
    }
}
