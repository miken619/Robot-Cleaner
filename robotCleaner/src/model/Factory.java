package robotCleaner.src.model;

/*    Name of class:          Factory.java
 *    Name of Package:        robotCleaner.src.model
 *    Description of class:
 *    		Return the different instances of Tiles and the robot
 *    Required classes;       SetUp.java
 *                            BareFloor.java
 *                            ControlSystem.java
 *                            ControlSystemImpl.java
 *                            Door.java
 *                            Factory.java
 *                            HighPile.java
 *                            LowPile.java
 *                            Obstacle.java
 *                            Para.java
 *                            PowerStation.java
 *                            Robot.java
 *                            RobotImpl.java
 *                            SensorSimulator.java
 *                            Tile.java
 *                            TileImpl.java
 *    Instruction to run this program;   Open an instance of SetUp.java and call the initialize() method;
 */



public final class Factory {

	
	
	public static Robot createRobot(){
		return new RobotImpl();
	}
	
	public static Tile createBareFloor(String coordinate){
		return new BareFloor(coordinate);
	}
	
	public static Tile createLowPile(String coordinate){
		return new LowPile(coordinate);
	}
	
	public static Tile createHighPile(String coordinate){
		return new HighPile(coordinate);
	}
	
	public static Tile createObstacle(String coordinate){
		return new Obstacle(coordinate);
	}
	
	public static Tile createPowerStation(String coordinate){
		return new PowerStation(coordinate);
	}
	
	public static Tile createDoor(String coordinate){
		return new Door(coordinate);
	}
	
}
