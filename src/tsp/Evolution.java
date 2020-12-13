package tsp;

import java.util.Random;

public class Evolution {

	private int cityCount;
	private FitnessCalculator fitnessCalculator;
	private int genCount;
	private int maxGeneration;
	private double pCrossover;
	private double pMutation;
	
	public Evolution(int cityCount, FitnessCalculator fitnessCalculator, int genCount, int maxGeneration, double pCrossover, double pMutation) {
		this.cityCount = cityCount;
		this.fitnessCalculator = fitnessCalculator;
		this.genCount = genCount;
		this.maxGeneration = maxGeneration;
		this.pCrossover = pCrossover;
		this.pMutation = pMutation;
	}
	
	public Gen[][][] getEvolutions(int evolutionCount) {
		Gen[][][] evolutionCollection = new Gen[evolutionCount][maxGeneration][genCount];
		for (int i = 0; i < evolutionCount; i++) {
			evolutionCollection[i][0] = generateGeneration();						
			for (int j = 1; j < maxGeneration; j++) {
				Gen[] tempGeneration = crossover(evolutionCollection[i][j-1]);
				tempGeneration = mutation(tempGeneration);
				evolutionCollection[i][j] = selection(tempGeneration);
			}
		}
		return evolutionCollection;
	}
	
	public Gen[] crossover(Gen[] priorGeneration) {
		Gen[] crossoverGeneration = new Gen[priorGeneration.length + 1];
		int childCount = 0;
		for (int i = 0; i < priorGeneration.length; i+=2) {
			if (Math.random() <= pCrossover) {
				int[] firstParentSolution = priorGeneration[(int)(Math.random()*priorGeneration.length)].getSolution();
				int[] secondParentSolution = priorGeneration[(int)(Math.random()*priorGeneration.length)].getSolution();
				int firstCut = (int)(Math.random()*firstParentSolution.length);
				int secondCut = (int)(Math.random()*(firstParentSolution.length - firstCut)) + firstCut;				
				int[] firstChild = new int[firstParentSolution.length];
				int firstPosition = 0;
				for (int j = 0; j < firstChild.length; j++) {
					if (j < firstCut || j > secondCut) {
						boolean numberContained = false;
						for (int k = firstCut; k <= secondCut; k++) {
							if (firstParentSolution[firstPosition] == secondParentSolution[k]) {
								numberContained = true;
							}
						}
						if (numberContained) {
							j--;
						} else {
							firstChild[j] = firstParentSolution[firstPosition];
						}
						firstPosition++;
					} else {
						firstChild[j] = secondParentSolution[j];
					}
				}
				int[] secondChild = new int[secondParentSolution.length];
				int secondPosition = 0;
				for (int j = 0; j < secondChild.length; j++) {
					if (j < firstCut || j > secondCut) {
						boolean numberContained = false;
						for (int k = firstCut; k <= secondCut; k++) {
							if (secondParentSolution[secondPosition] == firstParentSolution[k]) {
								numberContained = true;
							}
						}
						if (numberContained) {
							j--;
						} else {
							secondChild[j] = secondParentSolution[secondPosition];
						}
						secondPosition++;
					} else {
						secondChild[j] = firstParentSolution[j];
					}
				}
				crossoverGeneration[childCount] = new Gen(firstChild, fitnessCalculator.calculateFitnessValue(firstChild));
				crossoverGeneration[childCount+1] = new Gen(secondChild, fitnessCalculator.calculateFitnessValue(secondChild));
				childCount += 2;
			}
		}
		Gen[] nextGeneration = new Gen[priorGeneration.length+childCount];
		for (int i = 0; i < nextGeneration.length; i++) {
			if (i < priorGeneration.length) {
				nextGeneration[i] = priorGeneration[i];
			} else {
				nextGeneration[i] = crossoverGeneration[i - priorGeneration.length];
			}
		}		
		return sortGeneration(nextGeneration);
	}
	
	public Gen[] mutation(Gen[] priorGeneration) {
		Gen[] nextGeneration = new Gen[priorGeneration.length];
		nextGeneration[0] = priorGeneration[0];
		for (int i = 1; i < priorGeneration.length; i++) {
			if (Math.random() <= pMutation) {
				int[] solutionToMutate = priorGeneration[i].getSolution();
				int firstIndexToSwap = (int)(Math.random()*solutionToMutate.length);
				int secondIndexToSwap = (int)(Math.random()*solutionToMutate.length);
				int[] mutatedSolution = new int[solutionToMutate.length];
				mutatedSolution[firstIndexToSwap] = solutionToMutate[secondIndexToSwap];
				mutatedSolution[secondIndexToSwap] = solutionToMutate[firstIndexToSwap];
				for (int j = 0; j < mutatedSolution.length; j++) {
					if (j != firstIndexToSwap && j != secondIndexToSwap) {
						mutatedSolution[j] = solutionToMutate[j];
					}
				}
				nextGeneration[i] = new Gen(mutatedSolution, fitnessCalculator.calculateFitnessValue(mutatedSolution));
			} else {
				nextGeneration[i] = priorGeneration[i];
			}
		}
		return sortGeneration(nextGeneration);
	}

	public Gen[] selection(Gen[] priorGeneration) {
		Gen[] nextGeneration = new Gen[genCount];
		nextGeneration[0] = priorGeneration[0];
		for (int i = 0; i < nextGeneration.length; i++) {
			int firstGenIndex = (int)(Math.random()*priorGeneration.length);
			int secondGenIndex = (int)(Math.random()*priorGeneration.length);
			if (priorGeneration[firstGenIndex].getFitnessValue() < priorGeneration[secondGenIndex].getFitnessValue()) {
				nextGeneration[i] = priorGeneration[firstGenIndex];
			} else {
				nextGeneration[i] = priorGeneration[secondGenIndex];
			}
		}
		return sortGeneration(nextGeneration);
	}
	
	public Gen[] sortGeneration(Gen[] generationToSort) {
		Gen[] sortedGeneration = new Gen[generationToSort.length];
		for (int i = 0; i < sortedGeneration.length; i++) {
			sortedGeneration[i] = new Gen(generationToSort[i].getSolution(), fitnessCalculator.calculateFitnessValue(generationToSort[i].getSolution()));
		}
		for (int i = 0; i < sortedGeneration.length; i++) {
			int min = i;
			for (int j = i + 1; j < sortedGeneration.length; j++) {
				if (sortedGeneration[j].getFitnessValue() <= sortedGeneration[min].getFitnessValue()) {
					min = j;
				}
			}
			Gen tempGen = sortedGeneration[i];
			sortedGeneration[i] = sortedGeneration[min];
			sortedGeneration[min] = tempGen;
		}
		return sortedGeneration;
	}
	
	public Gen[] generateGeneration() {
		Gen[] randomGeneration = new Gen[genCount];
		for (int i = 0; i < genCount; i++) {
			int[] genSolution = getRandomSolution();
			randomGeneration[i] = new Gen(genSolution, fitnessCalculator.calculateFitnessValue(genSolution));
		}
		return sortGeneration(randomGeneration);
	}
	
	public int[] getRandomSolution() {
		int[] randomSolution = new int[cityCount];
		for (int i = 0; i < randomSolution.length; i++) {
			randomSolution[i] = i;
		}
		Random shuffle = new Random();
		for (int i = 0; i < randomSolution.length; i++) {
			int randomIndexToSwap = shuffle.nextInt(randomSolution.length);
			int tempInt = randomSolution[randomIndexToSwap];
			randomSolution[randomIndexToSwap] = randomSolution[i];
			randomSolution[i] = tempInt;
		}
		return randomSolution;
	}
	
}
