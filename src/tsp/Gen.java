package tsp;

public class Gen {

	private int[] solution;
	private double fitnessValue;
	
	public Gen(int[] solution, double fitnessValue) {
		this.solution = solution;
		this.fitnessValue = fitnessValue;
	}
	
	public int[] getSolution() {
		return solution;
	}
	
	public double getFitnessValue() {
		return fitnessValue;
	}
	
}
