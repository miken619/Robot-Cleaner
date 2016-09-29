package robotCleaner.src.main;

/*    Name of class:          Main.java
 *    Name of Package:        robotCleaner.src.main
 *    Description of class:
 *    		This class is use to starts the program.
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
 *                            Display.java
 *    Instruction to run this program;   Open an instance of SetUp.java and call the initialize() method;
 */



public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SetUp _setup = new SetUp(); //Create a new instance of SetUp.java
		_setup.initialize();        //Start program
		
	
		

	}

}
