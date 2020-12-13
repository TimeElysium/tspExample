package tsp;

public class FitnessCalculator {

	private City[] cities;
	private double[][] distances;
	
	public FitnessCalculator(City[] cities) {
		this.cities = cities;
		this.distances = calculateDistances(cities);
	}
	
	public double[][] calculateDistances(City[] cities) {
		double[][] distances = new double[cities.length][cities.length];
		for (int i = 0; i < distances.length; i++) {
			for (int j = i; j < distances[i].length; j++) {
				distances[i][j] = Math.round(100.0*Math.sqrt((cities[i].getXPos() - cities[j].getXPos())*(cities[i].getXPos() - cities[j].getXPos()) + (cities[i].getYPos() - cities[j].getYPos())*(cities[i].getYPos() - cities[j].getYPos())))/100.0;
				distances[j][i] = distances[i][j];
			}
		}
		return distances;
	}
	
	public double calculateFitnessValue(int[] solutionToEvaluate) {
		double fitnessValue = 0.0;
		for (int i = 1; i < solutionToEvaluate.length; i++) {
			fitnessValue += distances[solutionToEvaluate[i]][solutionToEvaluate[i-1]];
		}
		fitnessValue += distances[solutionToEvaluate[0]][solutionToEvaluate[solutionToEvaluate.length-1]];
		return fitnessValue;
	}
	
	public City[] getCities() {
		return cities;
	}
}
