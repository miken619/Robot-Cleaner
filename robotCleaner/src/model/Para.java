package robotCleaner.src.model;

/*    Name of enum:          Para.java
 *    Name of Package:        robotCleaner.src.model
 *    Description of class:
 *    		Create a singleton instance of Para, which has parameters use in the implement of this software
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


public enum Para {
	//Create one instance
	INSTANCE;
	private Para(){}
	
	//Id for each of the different Tile types
	private int obstacle = 0;
	private int bareFloor = 1;
	private int lowPile = 2;
	private int highPile = 3;
	private int door = 4;
	private int powerStation = 9;
	
	//Return the Tile id
	public int getObstacleID() {
		return obstacle;
	}

	public int getBareFloorID() {
		return bareFloor;
	}

	public int getLowPileID() {
		return lowPile;
	}

	public int getHighPileID() {
		return highPile;
	}
	
	public int getdoorID() {
		return door;
	}
	
	public int getPowerStationID() {
		return powerStation;
	}
	
	//Sample floor layout plan which mimics the one from Chris Jones student guild
	//The array will be use to parse the corresponding Tile
	private int[][] layOutEx = new int [][]{
			                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},  //0
			                    {0,2,2,2,2,2,0,1,1,1,1,1,0,1,1,0},  //1
			                    {0,2,2,2,2,2,0,0,0,0,4,0,0,1,1,0},  //2
			                    {0,2,2,2,2,2,0,1,1,0,1,1,4,1,1,0},  //3
			                    {0,2,2,2,2,2,0,0,4,0,1,1,0,1,1,0},  //4			                      
			                    {0,2,2,2,2,2,4,1,1,0,1,1,0,1,1,0},  //5
			                    {0,4,0,0,0,0,0,1,1,4,1,1,0,0,0,0},  //6			                    			                    
			                    {0,1,1,0,1,1,4,1,1,0,1,1,1,1,1,0},  //7
			                    {0,0,0,0,0,0,0,1,1,0,1,3,3,3,1,0},  //8			                    			                    
			                    {0,1,1,1,1,1,0,1,1,0,1,3,3,3,1,0},  //9
			                    {0,0,0,4,0,0,0,1,1,0,1,3,3,3,1,0},  //10			                    			                			          			                    
			                    {0,2,2,2,2,2,4,1,1,0,1,3,3,3,1,0},  //11	                    			                 
			                    {0,2,2,2,2,2,0,1,1,0,1,3,3,3,1,0},  //12			                      
			                    {0,2,2,2,2,2,0,1,1,0,1,3,3,3,1,0},  //13			                    
			                    {0,9,2,2,2,2,0,0,1,0,1,1,1,1,1,0},  //14
			                    {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}}; //15
	
	
	
			  
  public int[][] getLayoutEx2(){
	  return layOutEx;

  }
  
  //Store the parse layout
  Tile[][] _parseLayout;
  
  //Set method for the parselayout
  public Tile[][] getLayout(){
	  return _parseLayout;
  }
  
  //Get method for the parselayout
  public void setLayout(Tile[][] layout){
	  _parseLayout = layout;
  }
  
  //Get location of the power station and starting point for the robot
  public Tile getPowerStation() {
	  return _parseLayout[14][1];
  }
}
