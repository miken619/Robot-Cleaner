package robotCleaner.src.model;

import java.awt.Color;
import java.awt.Graphics;


/*    Name of class:          BareFloor.java
 *    Name of Package:        robotCleaner.src.model
 *    Description of class:
 *    		Inherited most of its' method from TileImpl.java. This holds variable that different between each 
 *          tile.
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

public final class BareFloor extends TileImpl{
	
	
	int _identifier;	//This tile id
	double _cost;	    //This tile cost to move onto it
	
	
	BareFloor(String coordinate){ 
	    super(coordinate);		//call super class
	    //initialize variable
		_identifier = Para.INSTANCE.getBareFloorID();		
		_cost = (double)_identifier;
		
		
    }
	//return its' id
	public int getIdentifier() {
		return _identifier;	
	}
    //return its' cost
    public double getTileCost() {
    	return _cost;
    }
    //paint this tile on JFrame and its' specific color
    //paint robot if it is on this tile
    public void render(Graphics g) {
    	String[] _split = _coordinate.split(" ");
    	int _x = Integer.parseInt(_split[1]) * 50;
    	int _y = Integer.parseInt(_split[0]) * 50;
    	g.setColor(Color.WHITE);
		g.fillRect(_x,_y,50,50);
		g.setColor(Color.BLACK);
		g.drawRect(_x,_y,50,50);
		if(_robot == true) {
			renderRobot(g);
		}
    }
   
   

}
