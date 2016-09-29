package robotCleaner.src.model;

import java.awt.Color;
import java.awt.Graphics;


/*    Name of class:          TileImpl.java
 *    Name of Package:        robotCleaner.src.model
 *    Description of class:
 *    		Class provides Tile so the RobotImpl can move around. Hold its' neighbor Tile and the RobotImpl.
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



public abstract class TileImpl implements Tile, Comparable<Tile> {

	boolean _visit;                     //Holds wherever the RobotImpl has been on this Tile
    boolean _robot;                     //Holds wherever the RobotImpl is currently on this Tile
	double  _pathCost;                  //Holds the cost to move to move along a path
	
	Tile _nextNorthTile;                //Holds its' north Tile
	Tile _nextWestTile;                 //Holds its' west Tile
	Tile _nextSouthTile;				//Holds its' south Tile
	Tile _nextEastTile;					//Holds its' east Tile
	Tile _prevTile;                     //Holds the previous Tile for a path
	String _coordinate;                 //Holds x and y coordinate
    
	TileImpl(String coordinate){
		//Initialize viables 
    	_robot = false;
    	_pathCost = Double.POSITIVE_INFINITY;
    	_coordinate = coordinate;    	
    	_visit = false;
    }
	
	
	//Set its' west tile
	public void setWestTile(Tile nextTile) {
		if(nextTile == null)
			throw new NullPointerException();
		_nextWestTile = nextTile; 		
	}
	
	//Set its' north tile
	public void setNorthTile(Tile nextTile) {
		if(nextTile == null)
			throw new NullPointerException();
		_nextNorthTile = nextTile; 		
	}
	
	//Set its' east tile
	public void setEastTile(Tile nextTile) {
		if(nextTile == null)
			throw new NullPointerException();
		_nextEastTile = nextTile; 		
	}
	
	//Set its' south tile
	public void setSouthTile(Tile nextTile) {

		if(nextTile == null)
			throw new NullPointerException();
		_nextSouthTile = nextTile; 
		
	}
	
	//Get its' west Tile
	public Tile getWestTile() {
		return 	_nextWestTile;
	}
	
	//Get its' north Tile
	public Tile getNorthTile() {
		return 	_nextNorthTile;
	}
	
	//Get its' east Tile
	public Tile getEastTile() {
		return 	_nextEastTile;
	}
	
	//Get its' south Tile
	public Tile getSouthTile() {
		return 	_nextSouthTile;
	}
		
	 //Set the previous Tile for a path
	public void setPrevTile(Tile tile) {
		  _prevTile = tile;
	 }
	 //Set the previous Tile for a path	
	public Tile getPrevTile() {
		return _prevTile;
	}
	
	//Get the path cost
	public double getPathCost() {
		  return _pathCost;
	  }
	  
	//Set the path cost
	  public void setPathCost(double cost) {
		  _pathCost = cost;
	  }
	  
	//Method needed for priority queue
	  public int compareTo(Tile other) {
		  return Double.compare(getPathCost(),other.getPathCost());
	  }
	  
	  //Get Tile position
	  public String getCoordinate() {
		  String[] _split = _coordinate.split(" ");
		  return "x = " + _split[0] + ", y = " + _split[1];
	  }
	  
	  //Set the robot on this Tile
	  public void setRobot(boolean r) {
		  _robot = r;
	  }
	  
	  //Paint the Robot on JFrame
	  public void renderRobot(Graphics g) {
		  String[] _split = _coordinate.split(" ");
	    	int _x = Integer.parseInt(_split[1]) * 50;
	    	int _y = Integer.parseInt(_split[0]) * 50;
			g.setColor(Color.RED);
			g.fillOval(_x+5,_y+5,40,40);
			g.setColor(Color.BLACK);
			g.drawOval(_x+5,_y+5,40,40);
	  }
	  
	  //Set if the robot has been on this Tile
	  public boolean getVisit() {		  
		  return _visit;
	  }
	  
	//Get if the robot has been on this Tile
	  public void setVisit(boolean visit) {
			_visit = visit;
	}

}
