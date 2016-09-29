package robotCleaner.src.model;

public interface SensorSimulator {
	public void cleanHouse(Tile startingPoint);
	
	public void calculateShortestPath(Tile startingPoint, Tile endPoint);
	
	public void resetValuesInTile();
	
	 public void recharge(double cost);
	 
	 public void followPath();
	 
	 public Tile findCorner(Tile t);
	 
	 public void setDoorLocation(Tile door);

	 public void findPathToDoor();
}
