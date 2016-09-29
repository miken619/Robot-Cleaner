package robotCleaner.src.main;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import robotCleaner.src.canvas.Display;
import robotCleaner.src.model.Factory;
import robotCleaner.src.model.Para;
import robotCleaner.src.model.Robot;
import robotCleaner.src.model.RobotImpl;
import robotCleaner.src.model.Tile;

/*    Name of class:          SetUp.java
 *    Name of Package:        robotCleaner.src.main
 *    Description of class:
 *    		This class will parse a sample floor layout from a int 2darray to a Tile 2darray.
 *          It will also set up a menu and take in user input and produce the correct output
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


public final class SetUp {
	public void initialize() {
		  int[][] layout = Para.INSTANCE.getLayoutEx2();       //Get int 2darray sample layout
		  int rows = layout.length;                            //Get layout row
		  int columns = layout[0].length;                      //get layout column
		  Tile floor = null;                                   //will be initialize to the color instance of Tile
	      Tile[][] parseLayout = new Tile[rows][columns];      //Store the parse Tile
	      MenuBuilder _menu  = new MenuBuilder();              //Initialize the Menu class
		  
	    
       //Parse the 2darray to a Tile 2darray
	    for (int i=0; i<rows; i++) {	   	     
		      for (int j=0; j<columns; j++) {
		    	  floor = null;
		    		   switch(layout[i][j]) {
		    		      case 0:
		    		    	  floor = Factory.createObstacle(i + " " + j);		    		    	 
		    		    	  break;
		    		      case 1:
		    		    	  floor = Factory.createBareFloor(i + " " + j);		    		    	  
		    		    	  break;
		    		      case 2:
		    		    	  floor = Factory.createLowPile(i + " " + j);		    		    	  
		    		    	  break;
		    		      case 3:
		    		    	  floor = Factory.createHighPile(i + " " + j);			    		    	  
		    		    	  break;	
		    		      case 4:
		    		    	  floor = Factory.createDoor(i + " " + j);		    		    	  
		    		    	  break;		  
		    		      case 9:
		    		    	  floor = Factory.createPowerStation(i + " " + j);		    
		    		    	  break;  	  
		    		   }	  
		    		   parseLayout[i][j] = floor;	    				    		      
		    	   }	    	       	   	    
	    }
	    //Initialize parseLayout[][] east, and west Tile
	    for(int i=0; i<rows ; i++) {   	
	    	for(int j=0; j<columns - 1; j++) {    			    		
	    		parseLayout[i][j].setEastTile(parseLayout[i][j+1]);	    		
	    		parseLayout[i][j+1].setWestTile(parseLayout[i][j]);

	    		
	        }
	    }
	    //Initialize parseLayout[][] north, and south Tile
	    for(int i=0; i<rows - 1; i++) {   	
	    	for(int j=0; j<columns; j++) {
	    			   	    	
	    		parseLayout[i][j].setSouthTile(parseLayout[i+1][j]);
	    		parseLayout[i+1][j].setNorthTile(parseLayout[i][j]);
	    
	    		
	        }
	    }
	    //Set it to the Para.java
	    Para.INSTANCE.setLayout(parseLayout);
	   
	   //Start the graphic output and menu
	    Display show = new Display();
		show.start();
	    _menu.run();
	    show.stop();

	}
	
	
}

//Inner class use to display the available option and take in user input
class MenuBuilder{
	 //Condition for starting/stopping this class
	  boolean stop;                
	  
	  //Inner class, use as data type for the menu
	  static class Menu{
		    final String option;
		    final Action action;

		    Menu(String o, Action r) {
		    	option = o;
		    	action = r;
		    }
	 }
	 
	 //Interface that will be defined with different run() methods
	 //Use in Menu class
	 interface Action{
		 
		 public void run(Robot r);
	 }
	 
	  public void run(){
		  final Menu[] menu;                       //Holds the list of cleaning options
		  BufferedReader in;                       //Read user input
		  int i = 0;                               //Holds user input parse to int
		  Robot r = new RobotImpl();                    		  
		    
			menu = new Menu[3];
			setupMenu(menu);
			stop = true;
			in = new BufferedReader(new InputStreamReader(System.in));  
			
		  
		  
		 		 
		     try {
		
					 while(stop){
						 
						 //Display menu[]
						 System.out.println ("\nSelect an option \n");
						 for(int c = 1; c < menu.length; c++){
							 System.out.println(menu[c].option);
						 }
						 System.out.println("\n");
						 
						 
						 i = Integer.parseInt(in.readLine());
						 System.out.println("\n");
						 
						 if(i>0 && i<3){
							 
							 //run action related to user input
							 menu[i].action.run(r);
							 
							
						 }
						 System.out.println("\n");
						 
					 }
			 
				 
				
			} catch (NumberFormatException | IOException e) 
		            {System.out.println("Invalid input. Type in a number ");} //Invalid input from user
		 
		 System.out.println("\nProgram shutting down.\n");          //User exited out of the program
		 
	 }
	 
	 //Set up the menu[] and initialize each index to perform a certain action
	 public void setupMenu(Menu[] menu){
		
		 //Index o is set to invalid
		 menu[0] = new Menu(
					" 0. Invalid ",
					new Action() {
						public void run(Robot r) {
							System.out.println("Invalid command.");
							}
	               });

		//1. Robot will clean the whole house
		menu[1] = new Menu(
				"1. Clean House",
				new Action() {
					public void run(Robot r) {
						
						r.cleanHouse();
					}
				});
		//Index 2 exits out of the loop and the program
		menu[2] = new Menu(
				"2. Exit program",
				new Action() {
					public void run(Robot r) {
						stop=false;
					}
				});
	 }
}

