package robotCleaner.src.model;





/*    Name of class:          RobotImpl.java
 *    Name of Package:        robotCleaner.src.model
 *    Description of class:
 *    		This is the class that will move around the Tile and update it power unit and carry capacity
 *          according to the cost of each Tile. If needed it will move back to power station to recharge
 *          and empty out the dirts it had clean
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


public final class RobotImpl implements Robot{
	
	private Tile _currentTile;	              //Current tile this RobotImpl.java is on
	private double _powerUnit;                 //Store the power units
	private ControlSystem _control;            //Instance of the ControlSystemImpl.java, so it can move
	private SensorSimulatorImpl  _sensor;     //Instance of the SensorSimulatorImpl, so it can move in the right direction
	private Tile _powerStation;                //Location of the power station
	private int _carryCap;                     //Store the dirt amount
	public RobotImpl(){
		//Initialize the variable
		_powerStation = Para.INSTANCE.getPowerStation();
		_powerUnit = 100.00;
		_carryCap = 50;
		_currentTile = Para.INSTANCE.getPowerStation();
		_control = new ControlSystemImpl(this);
		_sensor = new SensorSimulatorImpl(_control,this);
		
	}
	//return the current tile the it is on
	public  Tile getCurrentTile() {
		return _currentTile;
	}
	
	//return the location of the power station
	public  Tile getPowerStation() {
		return _powerStation;
	}
	
	//place the robot on a tile
	public void setCurrentTile (Tile tile){
		if(tile != null) {
			_currentTile = tile;			
			
		}else 
			throw new NullPointerException();
	}
	
	
   //return its' carrying capacity
	public int getCarryCap() {
		return _carryCap;
	}
	//Reset its' carrying capacity to 50
	public void setCarryCap(int unit) {
		_carryCap = unit;
	}
	//Subtract the dirt it pick up from its' carrying capacity
	public void deductCarryCap(int unit) {
		_carryCap -= unit;
	}
	//Subtract the power from cleaning and moving
	public void deductPowerUnit(double power) {
		_powerUnit -= power;
	}
	//Get the power unit
	public double getPowerUnit() {
		return _powerUnit;
	}
	//Reset it power unit to 100.00
	public void setPowerUnit(double unit) {
		_powerUnit = unit;
	}
		
	//clean the whole house
	public void cleanHouse() {
		_sensor.cleanHouse(getCurrentTile());
		_sensor.findPathToDoor();
		
	}	

}
