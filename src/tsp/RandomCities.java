package tsp;

public class RandomCities {

	private int cityCount;
	private int widthOfMap;
	private int heightOfMap;
	
	public RandomCities(int cityCount, int widthOfMap, int heightOfMap) {
		this.cityCount = cityCount;
		this.widthOfMap = widthOfMap;
		this.heightOfMap = heightOfMap;
	}
	
	public FitnessCalculator getFittingFitnessCalculator() {
		City[] cities = new City[cityCount];
		for (int i = 0; i < cities.length; i++) {
			cities[i] = new City((int)(Math.random()*(widthOfMap + 1)), (int)(Math.random()*(heightOfMap+1)));
		}
		return new FitnessCalculator(cities);		
	}
}
