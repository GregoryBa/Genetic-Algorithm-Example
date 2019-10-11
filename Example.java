import java.lang.Math;
import java.util.*; 

class Example {
    Population population = new Population();
    Population2 population2 = new Population2();
    List<Individual> leastFittestList = new ArrayList<>();
    Individual2 lowestWeight;
    Individual2 secondLowestWeight;
    Individual secondFittest;

    int generationCount = 0;

    public static void main(String[] args) {
        //Do *not* delete/alter the next line
        //Changing the next line will lead to loss of marks
        long startT = System.currentTimeMillis();

        //Edit this according to your name and login
        String name = "Gregory Baranowski";
        String login = "gb430";
        Example exe = new Example();
        int fittestIndex;
        // Initializes population of size 50
        exe.population.initializePopulation(5000);
        // Calculate all fitnesses from the population: 
        exe.population.calcAllFitness();

        // Wile loop creates new generations, population goes through mutation,
        // adds new population, chooses the best one,
        // and updates least fittest of them to add it to new population
        while(exe.population.minFitness() > 0.0001) {    

            exe.generationCount++;
            exe.population.mutation();
            exe.population.addNewPopulation(2000);
            exe.population.calcAllFitness();
            exe.population.updateLeastFittest();
        }
        //exe.answer1();
        exe.generationCount = 0;

        exe.secondLowestWeight = new Individual2();
        exe.secondFittest = new Individual();

        exe.population2.createPopulation(10);
        exe.selection();
        exe.crossover();
        boolean execute = true;

        while(execute) { 
            if(exe.lowestWeight.getWeight() < 500) {
                exe.generationCount++;
                exe.population2.newPopulation(10, exe.lowestWeight);
                exe.selection();
                exe.crossover();
                if(exe.lowestWeight.getUtility() > 60) {
                   // exe.answer2();
                    execute = false;
                }
            }
            else {
                exe.generationCount++;
                exe.population2.newPopulation(100, exe.lowestWeight);
                exe.selection();
                exe.crossover();
            }
            if(exe.lowestWeight.getWeight() < 100) {
                exe.lowestWeight = new Individual2();
                exe.secondLowestWeight = new Individual2();
            }
        }

        double[] sol1 = {0, 0};
        sol1 = exe.population.getLeastFittest();

        //Creating a sample solution for the second problem
        //The higher the fitness, the better, but be careful of  the weight constraint!
        boolean[] sol2 = new boolean[100];
        double[] tmp = new double[100];

        sol2 = exe.lowestWeight.getSol2();
        //System.out.println("Index :  "+ i + "            " + tmp[2]);

        //Now checking the fitness of the candidate solution
        tmp = (Assess.getTest2(sol2));

        //The index 0 of tmp gives the weight. Index 1 gives the utility
        System.out.println("The weight is: " + tmp[0]);
        System.out.println("The utility is: " + tmp[1]);

        //Once completed, your code must submit the results you generated, including your name and login: 
        //If you don't check in your results, you will incur a substantil loss of marks
        //Use and adapt  the function below:
        Assess.checkIn(name,login,sol1,sol2);
     
         //Do not delete or alter the next 2 lines
         //Changing them will lead to loss of marks
         long endT = System.currentTimeMillis();
         System.out.println("Total execution time was: " +  ((endT - startT)/1000.0) + " seconds");
    }

    void selection() {
        lowestWeight = population2.getLowestWeight();
        secondLowestWeight = population2.getSecondLowestWeight();
    }

    // void rulleteSelection() {
    //     // for (int i = 0 ; i < lowestWeight. ; ) {
            
    //     // }
    //     lowestWeight.getUtility();
    // }

    void crossover() {
        Random r = new Random();
        int min = 0;
        int max = 100;
        int index;

        double weightlowestBefore = population2.individuals2[population2.getIndex()].getWeight();
        double weightsecLowestBefore = population2.individuals2[population2.getIndex2()].getWeight();

        int crossoverPoint = r.nextInt(100);

        boolean[] best = population2.individuals2[population2.getIndex()].getSol2();
        boolean[] secondBest = population2.individuals2[population2.getIndex2()].getSol2();

         for (int i = 0; i < crossoverPoint; i++) {
            boolean temp = best[i];
            best[i] = secondBest[i];
            secondBest[i] = temp;
            if (weightlowestBefore > lowestWeight.getWeight()) {
                lowestWeight.setSol2(best);
                weightlowestBefore = lowestWeight.getWeight();
            }

            else if(weightlowestBefore < lowestWeight.getWeight()){
                if(best[i] == true) {
                    best[i] = false;
                }
                else if(best[i] == false){
                    best[i] = false;
                }
            } 
        }
    }

    // void mutation() {
    //     Random rn = new Random();
    //     boolean[] mutated = lowestWeight.getSol2();
    //     int mutationPoint = rn.nextInt(100);
    //     Individual2 temp;

    //     for (int i = 0; i < mutationPoint ; i++ ) {
    //         mutated[i] = (Math.random()>0.5);
    //         //if is better
    //         // if(mutated[i] > lowestWeight.getUtility()) {
    //         //     lowestWeight.setSol2(mutated);
    //         // }
    //         //  // if is not better -> Rollback
    //         // else if(mutated[i] < lowestWeight.getUtility()) {
    //         //     System.out.println("ROLLBACK; mutated[i] is: " + mutated[i]);
    //         // }
    //         // else {
    //         //     System.out.println("FAILED!");
    //         // }
           
    //     }
        
    // }

    void answer1() {
        System.out.println(" --------------------------------------------------- ");
        System.out.println("Exercise 1:");
        System.out.println("FOUND AFTER " + generationCount + " GENERATIONS      " + "    FITNESS:  " + population.minFitness());
        System.out.println(" --------------------------------------------------- ");
        System.out.println("\n");
    }

    void answer2() {
        Individual2 individual;
        double weight;
        individual = population2.getLowestWeight();
        weight = individual.getWeight();
        System.out.println(" --------------------------------------------------- ");
        System.out.println("Exercise 2:");
        System.out.println("FOUND AFTER " + generationCount + " GENERATIONS      " + "    LEAST WEIGHT:  " + lowestWeight.getWeight() + "     UTILITIES:    " + lowestWeight.getUtility());
        System.out.println(" --------------------------------------------------- ");
    }
}

class Individual2 extends Population2 {
    boolean[] sol2 = new boolean[100];
    double[] weightAndUtility = new double[2];
    int fitness = 0;

    // Sets random number to sol1[0] and sol1[2];
    public Individual2() {
        for (int i = 0; i < sol2.length; i++) { sol2[i] = (Math.random() > 0.5); }
    }

    public boolean[] getSol2() { return sol2; }

    public void setSol2(boolean[] newSolution) { sol2 = newSolution; }

    public String getSol2ToString() { 
        String sol2String = "";
        for (int i = 0; i < sol2.length ; i++ ) { sol2String += sol2[i]; } 
        return sol2String;
    }

    public double getWeight() {
        weightAndUtility = Assess.getTest2(sol2);
        return weightAndUtility[0];
    }
    public double getUtility() {
        weightAndUtility = Assess.getTest2(sol2);
        return weightAndUtility[1];
    }
}

class Population2 {
    Individual2[] individuals2;
    double weight;
    int utility;
    int index, index2;

    public int getIndex() { return index; }

    public int getIndex2() { return index2; }

    public Individual2[] getIndividualArray() { return individuals2; }

    public Individual2 getNewIndividual(boolean[] newSol2) {
        Individual2 tempIndividual2 = new Individual2();
        double tempWeight;

        tempIndividual2.setSol2(newSol2);
        tempWeight = tempIndividual2.getWeight();

        return tempIndividual2;
    }

    public void createPopulation(int size){;
        individuals2 = new Individual2[size];
        for (int i = 0; i < individuals2.length ; i++) { individuals2[i] = new Individual2(); }
    }

    public void newPopulation(int size, Individual2 lowestWeight) {
        Arrays.fill(individuals2, null);
        individuals2 = new Individual2[size];

         for (int i = 0; i < size; i++) {
            if(i < (size - 1)) individuals2[i] = new Individual2(); 
            else individuals2[size - 1] = lowestWeight;
        }
    }

    public Individual2 getLowestWeight() {
        weight = Double.MAX_VALUE;
        index = 0;

        for (int i = 0; i < individuals2.length ; i++ ) {
            if(weight >= individuals2[i].getWeight()) { 
                weight = individuals2[i].getWeight();
                index = i;
            }
        }
        return individuals2[index];
    }

    public Individual2 getHighestUtility() {
        utility = Integer.MAX_VALUE;
        int tempIndex = 0;

        for (int i = 0; i < individuals2.length ; i++ ) {
            if(weight >= individuals2[i].getUtility()) { 
                weight = individuals2[i].getUtility();
                tempIndex = i;
            }
        }
        return individuals2[tempIndex];
    }

    public Individual2 getSecondLowestWeight() {
        weight = Double.MAX_VALUE;
        index2 = 0;
        int tempIndex = 0;

        for (int i = 0; i < individuals2.length ; i++ ) {      
            if ((individuals2[index].getWeight()) < (individuals2[i].getWeight())) {
                
                if(weight >= individuals2[i].getWeight()) { 
                    weight = individuals2[i].getWeight();
                    index2 = i;
                }
            }
        } 
        return individuals2[index2];
    }
}
    
class Individual {
    double fitness = 0;
    double[] sol1 = new double[2];
    int max = 500;
    int min = -500;

    public double[] getSol1() { return sol1; }

    // Sets random number to sol1[0] and sol1[2];
    public Individual() {
        Random rn = new Random();
        for (int i = 0; i < sol1.length; i++) {sol1[i] = min + (max - min) * rn.nextDouble(); }
    }

    public Individual(double firstValue, double secondValue) {
        sol1[0] = firstValue;
        sol1[1] = secondValue;
    }

    //Calculate fitness
    public double getFitness() {
        fitness = Assess.getTest1(sol1[0],sol1[1]);
        return fitness;
    }
}

class Population {
    double[] fitness;
    Individual[] individuals;
    double fittest;
    double leastFittestFitness;
    int leastFittestIndex = 0;
    List<Individual> mutatedList;
   
    public double minFitness() { return leastFittestFitness; }

    public double[] getLeastFittest() { return individuals[leastFittestIndex].getSol1(); }

    public void addNewIndividualToList(int index, double firstValue, double secondValue) {
        Individual singleIndividual = new Individual(firstValue, secondValue);
        mutatedList.add(index, singleIndividual);
    }

    //Initialize population
    public void initializePopulation(int size) {
        individuals = new Individual[size];
        fitness = new double[size];
        for (int i = 0; i < individuals.length; i++) {
            individuals[i] = new Individual();
        }
    }

    public void addNewPopulation(int size) {
        double[] tempValue = new double[2];
        individuals = new Individual[size];
        fitness = new double[size];

        for (int i = 0; i < mutatedList.size(); i++) {
            tempValue = mutatedList.get(i).getSol1();
            individuals[i] = new Individual(tempValue[0], tempValue[1]);
        }

        for (int i = mutatedList.size(); i < individuals.length; i++) { individuals[i] = new Individual(); }

        mutatedList.clear();
        mutatedList = new ArrayList<>();
    }

    public void calcAllFitness() {

        for (int i = 0; i < fitness.length; i++ ) { fitness[i] = individuals[i].getFitness(); }
        updateLeastFittest();
    }

    public void updateLeastFittest() {
        double maxFit = Double.MAX_VALUE;

        for(int i = 0; i < fitness.length; i++) {
            if (maxFit >= fitness[i]) {
                maxFit = fitness[i];
                leastFittestIndex = i;
            }
        }
        leastFittestFitness = fitness[leastFittestIndex];
    }
    
    public void mutation() {
        mutatedList = new ArrayList<>();
        Random rn = new Random();
         double min = 0.01;
         double max = 0.05;
         double[] sol1Mutated = new double[2];
         double mutationPercentage =  min + (max - min) * rn.nextDouble();
         double oldFitness;
         Individual newIndividual;
         int index = 0;

         sol1Mutated = individuals[leastFittestIndex].getSol1();
         oldFitness = getFitness(sol1Mutated);
         sol1Mutated[0] = sol1Mutated[0] - (sol1Mutated[0] * mutationPercentage);
         sol1Mutated[1] = sol1Mutated[1] - (sol1Mutated[1] * mutationPercentage);

        if((oldFitness > getFitness(sol1Mutated)) && 
            (sol1Mutated[0] <= 500) && 
            (sol1Mutated[0] >= -500) && 
            (sol1Mutated[1] <= 500) && 
            (sol1Mutated[1] >= -500)) {

            leastFittestFitness = getFitness(sol1Mutated);
            addNewIndividualToList(index, sol1Mutated[0], sol1Mutated[1]);
            index++;
         }
    }

    public int getLeastFittestIndex() {
        double minFit = Double.MAX_VALUE;
        int minFitIndex = 0;

        for(int i = 0; i < fitness.length; i++) {
            if (minFit >= fitness[i]) {
            minFit = fitness[i];
            minFitIndex = i; }
        }
        return minFitIndex;
    }

    public double getFitness(double[] mutatedFitness) {
        double fitness = Assess.getTest1(mutatedFitness[0],mutatedFitness[1]);
        return fitness;
    }
}

