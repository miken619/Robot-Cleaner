package robotCleaner.src.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;



/*    Name of class:          SensorSimulatorImpl.java
 *    Name of Package:        robotCleaner.src.model
 *    Description of class:
 *    		This class determine the direction the RobotImpl.java should go, base on the Tile type and the RobotImpl.java
 *     		current task.
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


public class SensorSimulatorImpl implements SensorSimulator {
	ControlSystem _control;                 //Instance of ControlSystemImpl.java, to place the robot on the Tile
	Robot _robot;                           //Instance of RobotImpl, to get its' current power unit and carrying capacity
	PriorityQueue<Tile> _queue;             //Queue for navigating around a room
	PriorityQueue<Tile> _pathQueue;         //PriorityQueue for finding the shortest path
	ArrayList<Tile> _path;                  //Queue store the shortest path
	Stack<Tile> _doorPath;                  //Stack holds the location of the doors
	Tile _powerStation;                     //Holds location of the power station
	int _sleep;                             
	SensorSimulatorImpl(ControlSystem c, Robot r){
		//Initialize the variables
		_control = c;
		_sleep = 250;
		_robot = r;
		_doorPath = new Stack<Tile>();
		_powerStation = _robot.getPowerStation();
	}
	//This method is based off of a breath-first search and will navigate around a room and cleans it
	//When it moves to a new tile it will recalculate the shortest path back to the power station and recharge 
	//empty its dirt if needed
	public void cleanHouse(Tile startingPoint) {		
    	_queue = new PriorityQueue<Tile> ();    
    	
    	_queue.add(startingPoint);  	 //add startingPoint to _queue and starts navigating from there
    	 Tile _temp;                     //Next tile to visit
    	 
    	 //Iinitialize the name of the startingPoint
    	 int _id = startingPoint.getIdentifier();
		 String _name = "";
		    if(_id == 1)
		    	_name = "Bare Floor Tile";
		    else if(_id == 2 )
		    	_name = "Low Pile Tile";
		    else if(_id == 3)
		    	_name = "High Pile Tile";
		    else if(_id == 9)
		    	_name = "Power Station Tile";
    	 
    	 
    	System.out.println("Cleaning " +  _name + " at position: " + startingPoint.getCoordinate() +
                 ". Power units at: " + _robot.getPowerUnit() + ", carry capacity at: " + _robot.getCarryCap());
    	while(!_queue.isEmpty() ){
    		Tile _tile = _queue.poll();                      //Get the tile from _queue so the method can check it's bordering Tiles
    		 _temp = null;                       
    		
    		 //Initialize variables from bordering Tiles
    		Tile _westTile =  _tile.getWestTile();
    		Tile _eastTile =  _tile.getEastTile();
    		Tile _northTile = _tile.getNorthTile();
    		Tile _southTile = _tile.getSouthTile();
    		boolean _northVisit = _northTile.getVisit();
    		boolean _southVisit = _southTile.getVisit();
    		boolean _eastVisit = _eastTile.getVisit();
    		boolean _westVisit = _westTile.getVisit();
    		int _n = _tile.getNorthTile().getIdentifier();
        	int _s = _tile.getSouthTile().getIdentifier();
        	int _e = _tile.getEastTile().getIdentifier();
        	int _w = _tile.getWestTile().getIdentifier();
        	
        
        	//If all Tiles have been visited break out of this method
    		if(_northVisit == true && _southVisit == true && 
    	    		   _westVisit  == true && _eastVisit == true){
    	    			break;
    	    }
    		
    		//Set the location of doors
    		if(_n == 4)
    			setDoorLocation(_northTile);
    		else if(_s == 4)
    			setDoorLocation(_southTile);
    		else if(_e == 4)
    			setDoorLocation(_eastTile);
    		else if(_w == 4)
    			setDoorLocation(_westTile);
    		
    		
    		//Set the next Tile
    		if(_northVisit == true && _southVisit == true && _westVisit  == true ) {				
    			_temp = _eastTile;    			
    		}else if(_northVisit == true && _eastVisit == true && _westVisit == true) {	
    			_temp = _southTile;    	
    		}else if(_northVisit == true && _eastVisit == true && _southVisit == true) {
    			_temp = _westTile;
    		}else if(_eastVisit == true && _westVisit == true && _southVisit == true ) {
    			_temp = _northTile;	
    		}else if(_southVisit == true && _westVisit == true) {
    			_temp = _northTile;	
    		}else if(_westVisit == true && _northVisit == true) {
    			_temp = _eastTile;  
    		}else if(_northVisit == true && _eastVisit == true) {
    			_temp = _southTile; 
    		}else if(_southVisit == true && _eastVisit == true) {
    			_temp = _westTile;
    		}else {
    			//When first entering a room, and if the RobotImpl is not in a corner, its' goes to a corner
    			_queue.remove(_tile);     			
				_queue.add(findCorner(_tile));   			
    		}
    			
    		    
    		 	//If the next Tile is not null go to it
    		    if(_temp != null) {	 
    		    	//Get the next Tile type
    		    	_id = _temp.getIdentifier();        		   
        		    if(_id == 1)
        		    	_name = "Bare Floor Tile";
        		    else if(_id == 2 )
        		    	_name = "Low Pile Tile";
        		    else if(_id == 3)
        		    	_name = "High Pile Tile";
        		    else if(_id == 9)
        		    	_name = "Power Station Tile";
    		    	
        		    //Move to the tile, cleans it, set the previous tile to true, and put the next Tile into _queue so it
        		    //can starts exploring
    		 		_control.moveToTile(_temp);
    		 		_control.clean(_temp);
	    		 	_tile.setVisit(true);	  			
					_queue.remove(_tile);   				
					_queue.add(_temp);					
					
					//Print its' current position, power unit, and carrying capacity
					System.out.println("Cleaning " +  _name + " at position: " + _temp.getCoordinate() +
			                           ". Power units at: " + _robot.getPowerUnit() + ", carry capacity at: " + _robot.getCarryCap());
					//Put this thread to sleep so the graphic component can catch up
					try {
						Thread.sleep(_sleep); 	   
				    } catch (InterruptedException e) {
				    
					 e.printStackTrace();
				    }	
					//Calculate its' shortest path back to the power station and recharge or empty out the dirts if needed
					calculateShortestPath(_robot.getCurrentTile(),_powerStation);	
    		 	}
						   			
    		
    	 }   
    		
    	
    	 
    	}
    	
	
  
	  //Find the shortest path back to the charging station, this method uses dijkstra's algorithm
    public void calculateShortestPath(Tile startingPoint, Tile endPoint) {
    	//initailize variable
    	_pathQueue = new PriorityQueue<Tile> ();
    	 _path = new ArrayList<Tile>();    
    	double _pathCost = 0.0; 
    	double _prevCost = 0.0;
    	resetValuesInTile();               //Reset variable in each Tile inorder to use this method properly
    	_pathQueue.add(startingPoint);      //Add the starting point
    	startingPoint.setPathCost(0.0);    //Reset the cost of the starting point
    	
    	
       
    	while(!_pathQueue.isEmpty() ){
    		Tile _tile = _pathQueue.poll();       //get the Tile from _pathQueue
    		//Initialize to its' bordering Tile
    		Tile _westTile =  _tile.getWestTile();
    		Tile _eastTile =  _tile.getEastTile();
    		Tile _northTile = _tile.getNorthTile();
    		Tile _southTile = _tile.getSouthTile();
    		Tile[] _list = {_southTile,_northTile,_westTile,_eastTile,}; //Put bordering Tile into an array
    	    
    		//Loop there the Tile array and find correct path
    		for(int i = 0; i < 4; i++) {
    			double _tileCost = _list[i].getTileCost();                   //Get cost to move to this Tile
    			double _distance = _tile.getPathCost() + _tileCost;          //Get path cost to this Tile
    			int    _id = _list[i].getIdentifier();                       //Get Tile id to avoid borders
    			
    			if(_distance < _list[i].getPathCost() && _id != 0) {
    				
    				_list[i].setPathCost(_distance);                       //Set new path cost
    				_list[i].setPrevTile(_tile);                           //Get the previous Tile it is connected to
    				_pathQueue.remove(_tile);                              //Remove the old Tile 
    				_pathQueue.add(_list[i]);   				           //Add the new Tile
    			}
    			
    			
    		}   
    		
    
    	}
    	//Start from the endPoint and finish at the startingPoint
    	for(Tile _temp = endPoint; _temp != null; _temp = _temp.getPrevTile()) {
			_path.add(_temp);					                             //add the path
			//Find the cost it would take to go back to the power station
			_pathCost +=  (_temp.getTileCost() + _prevCost)/2;
			_prevCost = _temp.getTileCost();
		}    	
    	
    	
    	Collections.reverse(_path);                     //Reverse the path so is starts at the startingPoint
    	_pathCost += 6.0;                               //Add a buffer to the path cost
    	recharge(_pathCost);                           //Method will determine if the RobotImpl needs to go back to the power station
    	 	
    	
    	
    }
    
    
    //Method will reset all the Tile back to its' default values for the path cost and the previous Tile
    //This is needed to be done so the SensorSensorImpl can find the shortest path from point A to point B
    public void resetValuesInTile() {
    	  Tile[][] layout = Para.INSTANCE.getLayout();               //Get the floor plan layout
		  int rows = layout.length;                                  //Get the row of the layout
		  int columns = layout[0].length;                            //Get the column of the layout
		 
		  //Reset values
		  for(int i = 0; i < rows; i++)
			  for(int j = 0; j < columns; j++) {
				  layout[i][j].setPathCost(Double.POSITIVE_INFINITY);
				  layout[i][j].setPrevTile(null);
			  }
    }
    
    
    //Method will determine if the RobotImpl needs to recharge or empty out the dirts, robot will move back to
    //the power station and back to the original Tile
    public void recharge(double cost) {
    	double _cost = cost;                                //The path cost
    	double _power = _robot.getPowerUnit();              //The power unit of the RobotImpl
    	int    _carryCap = _robot.getCarryCap();            //The carrying capacity of the RobotImpl
    	
    	if(_cost >= _power || _carryCap <= 3) {
    		//Print out RobotImpl status
    		System.out.println("Robot power units at: " + _robot.getPowerUnit() + ", carry capacity at: " + _robot.getCarryCap() +
			           ". Moving back to power station to recharge and empty out dirts");
	          
    		 //Move back to the power station
	         for(Tile tile: _path) {					
		        _control.moveToTile(tile);
		        //Print out the path, and its' power unit and carry capacity
		        System.out.println("Moving to tile at position: " + tile.getCoordinate() +
	                    ". Power units at: " + _robot.getPowerUnit()+ ", carry capacity at: " + _robot.getCarryCap());
	            //Put thread to sleep so the graphic can catch up
		        try {
		           Thread.sleep(_sleep); 	   
                  } catch (InterruptedException e) {}
	           }
	         //Reset the RobotImpl power unit and carry capacity
	          _robot.setPowerUnit(100.0);
	          _robot.setCarryCap(50);
	       
	          Collections.reverse(_path);                //Get path back to the original Tile
	          //Print RobotImpl status
	          System.out.println("Robot is done charging and emptying dirts, moving back to original tile. \nPower units at: " 
	                            + _robot.getPowerUnit() + ", carry capacity at: " + _robot.getCarryCap());
	          
	          //Move back to the original Tile
	          for(Tile tile: _path) {
		         _control.moveToTile(tile);
		         //Print out the path, and its' power unit and carry capacity
		         System.out.println("Moving to tile at position: " + tile.getCoordinate() +
		                    ". Power units at: " + _robot.getPowerUnit() + ", carry capacity at: " + _robot.getCarryCap());
		         //Put thread to sleep so the graphic can catch up
		         try {
			     Thread.sleep(_sleep); 	   
	             } catch (InterruptedException e) {}
	           }
	 		
    	}
    	
    }
    
    
    //Method moves the RobotImpl to the next room
    public void followPath() {
    	ArrayList<Tile> _temp = new ArrayList<Tile>(_path);             //Get a copy of the path to the room
    	//Print out RobotImpl Status
    	System.out.println("Robot has finishing cleaning the room and is moving on to the next room");
    	
    	//Move to the next room
    	for(Tile _t: _temp) {
    		_control.moveToTile(_t);
    		 //Print out the path, and its' power unit and carry capacity
    		System.out.println("Moving to tile at position: " + _t.getCoordinate() +
                    ". Power units at: " + _robot.getPowerUnit() + ", carry capacity at: " + _robot.getCarryCap());
    		
    		//Put thread to sleep so the graphic can catch up
    		try {
				Thread.sleep(_sleep); 	   
		    } catch (InterruptedException e) {}
    		
    		//Calculate its' shortest path back to the power station and recharge or empty out the dirts if needed
    		 calculateShortestPath(_robot.getCurrentTile(),_powerStation);	
    	}
    }
    
    //Method move the RobotImpl into a corner when its' enter a room, if it needs to. This is to ensure
    //the RobotImpl records all the doors it encounter
    public Tile findCorner(Tile t) {
    	Tile _cornerTile = t;                                    //initialize to the Tile right after the RobotImpl goes into a room
    	Tile _returnTile = _cornerTile;                          //initialize to _cornerTile
    	boolean _x = _cornerTile.getWestTile().getVisit();       //Get boolean value for the west Tile
    	boolean _y = _cornerTile.getSouthTile().getVisit();      //Get boolean value for the south Tile
    	
    	//Print out RobotImpl status
    	System.out.println("Robot is finding a corner before it starts cleaning");
    	
    	//Finds the farthest west tile
    	while(!_x) {
    		_returnTile = _cornerTile;          //reinitialize to a Tile that isn't an obstacle
    		_control.moveToTile(_returnTile);    //move to this the next Tile
    		//Print out the path, and its' power unit and carry capacity
    		System.out.println("Moving to tile at position: " + _returnTile.getCoordinate() +
	                    ". Power units at: " + _robot.getPowerUnit() + ", carry capacity at: " + _robot.getCarryCap());
    		
    		//Put thread to sleep so graphics can catch up
    		try {
				Thread.sleep(_sleep); 	   
		    } catch (InterruptedException e) {
		
			 e.printStackTrace();
		    }	
    		
    		//Calculate its' shortest path back to the power station and recharge or empty out the dirts if needed
    		 calculateShortestPath(_robot.getCurrentTile(),_powerStation);	
    		
    		 _cornerTile = _cornerTile.getWestTile();             //Get the next south Tile
    		_x = _cornerTile.getVisit();                          //Get loop condition
    		
    	}
    	
    	//Finds the farthest south tile	
    	while(!_y) {
    		_returnTile = _cornerTile;          //reinitialize to a Tile that isn't an obstacle
    		_control.moveToTile(_returnTile);   //move to this the next Tile
    		//Print out the path, and its' power unit and carry capacity
    		System.out.println("Moving to tile at position: " + _returnTile.getCoordinate() +
	                    ". Power units at: " + _robot.getPowerUnit() + ", carry capacity at: " + _robot.getCarryCap());
    		
    		//Put thread to sleep so graphics can catch up
    		try {
				Thread.sleep(_sleep); 	   
		    } catch (InterruptedException e) {
		
			 e.printStackTrace();
		    }	
    		//Calculate its' shortest path back to the power station and recharge or empty out the dirts if needed
    		 calculateShortestPath(_robot.getCurrentTile(),_powerStation);	
    		
    		 _cornerTile = _cornerTile.getSouthTile();          //Get the next south Tile
    		_y = _cornerTile.getVisit();					   //Get loop condition
    		
    	}
    	return _returnTile;
    }
    
    //Set the door location of the current room
    public void setDoorLocation(Tile door) {
    	if(door.getIdentifier()  == 4 ) {
    		_doorPath.push(door);
    	}   	
    }
    
    
    //Finds a path to the next room and starts cleaning
    public void findPathToDoor() {
    	Tile _tile;                                 //Hold the current tile
    	Tile _dest = null;                          //Hold the door destination
    	
    	//Variable use to determine the next Tile after entering a room
    	int _n;
    	int _s;
    	int _e;
    	int _w;
    	boolean _bn;
    	boolean _bs;
    	boolean _be;
    	boolean _bw;
    	
    	
    	while(!_doorPath.empty()) {

        	
    		_tile = _doorPath.pop();                      //Get the door location
    		
    		
    		//Initialize the variable
    		_n = _tile.getNorthTile().getIdentifier();
    		_s = _tile.getSouthTile().getIdentifier();
    		_e = _tile.getEastTile().getIdentifier();
    		_w = _tile.getWestTile().getIdentifier();
    		
    		_bn = _tile.getNorthTile().getVisit();    		
    		_be = _tile.getEastTile().getVisit();
    		_bw = _tile.getWestTile().getVisit();
    		_bs = _tile.getSouthTile().getVisit();
    		
    		
    		//Finds the next Tile after entering a room
    		if(_n == 0 && _s == 0 && _bw == true && _be == false)
    			_dest = _tile.getEastTile();
    		else if(_n == 0 && _s == 0 && _be == true && _bw == false)
    			_dest = _tile.getWestTile();
    		else if(_e == 0 && _w == 0 && _bn == true && _bs == false)
    			_dest = _tile.getSouthTile();
    		else if(_e == 0 && _w == 0 && _bs == true && _bn == false)
    			_dest = _tile.getNorthTile();
    		else {
    			continue;   //Room has already been clean, moving on to the next room
    		}
    		
    		//Find the path to the door
    		calculateShortestPath(_robot.getCurrentTile(),_dest);   
    		
    		//Go to the door
    		followPath();
    		
    		//Clean the room
    		cleanHouse(_robot.getCurrentTile());
    		
    	}
    	
    	//Robot is finish cleaning
    	System.out.println("Robot is finish cleaning and is moving back to power station to shut down");
    	
    	//Find path back to the power station
    	calculateShortestPath(_robot.getCurrentTile(),_powerStation);   
		
    	//Go to the power station
		followPath();
    }
}