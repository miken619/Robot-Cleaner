package robotCleaner.src.model;

import java.util.ArrayList;
import java.util.Collections;

/*    Name of class:          ControlSystemImpl.java
 *    Name of Package:        robotCleaner.src.model
 *    Description of class:
 *    		This class place the robot on the correct tile and cleans it
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


public final class ControlSystemImpl implements ControlSystem{
	Robot _r;	     //Instance of RobotImpl.java
	ControlSystemImpl(Robot r){
		_r = r;
	}
	
	//Tell the robot to move to the correct Tile and deduct the power units it takes to move there.
	//Set the previous Tile setRobot() to false and the new Tile setRobot() to true, which indicate the robot 
	//is on that Tile
	public void moveToTile(Tile t) {
		_r.getCurrentTile().setRobot(false);
		_r.deductPowerUnit((_r.getCurrentTile().getTileCost() + t.getTileCost())/2);
		_r.setCurrentTile(t);
		_r.getCurrentTile().setRobot(true);
	}
	
	//Clean the tile and deduct the correct power units
	public void clean(Tile t) {
		
		_r.deductPowerUnit(t.getTileCost());	
		_r.deductCarryCap((int)t.getTileCost());
		
		
	}
	
	
	
  
}
