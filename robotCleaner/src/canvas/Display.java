package robotCleaner.src.canvas;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import javax.swing.JFrame;


/*    Name of class:          Display.java
 *    Name of Package:        robotCleaner.src.canvas
 *    Description of class:
 *    		This class is used to display the output of CSC 459: Vacuum Robot's Simulator. 
 *          The class starts a thread and stays in a loop updating the position of the robot 
 *          until the whole program is close
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





import robotCleaner.src.model.Para;
import robotCleaner.src.model.Tile;

public final class Display extends Canvas implements Runnable
{
	 
	 private static final long serialVersionUID = 1L;    //Ensure serialized object have loaded class that are compatible with respect to serialization
	 BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);  //Stablized the graphic output
	 private static Tile[][] layout = Para.INSTANCE.getLayout();                        //2d array of the Tile that the robot will travel on
	 private static int rows = layout.length;                                           //Row length of the 2d array
	 private static int columns = layout[0].length;                                     //Column length of the 2d array
	 private static final String TITLE = "Vacuum Robot Simulator";                      //Title for JFrame
	 private static boolean running = false;                                            //Condition for stoping/starting this thread
	 private static final int WIDTH = 790;                                              //Width of the JFrame
	 private static final int HEIGHT = 790;                                             //Height of the Jframe
	 public static final Dimension dim = new Dimension(WIDTH,HEIGHT);                   //Use for Component sizes
	 JFrame frame;
	 
	 public Display() {
		 //Initialize variable
		 setMinimumSize(dim);
		 setMaximumSize(dim);
		 setPreferredSize(dim);
		 frame = new JFrame(TITLE);
		 
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 frame.setLayout(new BorderLayout());
		 
		 frame.add(this,BorderLayout.CENTER);
		 frame.pack();
		 
		 frame.setResizable(false);
		 frame.setLocationRelativeTo(null);
		 frame.setVisible(true);
		 
		 requestFocus();
	 }
	 
	 //Method will continuously update and render the position of the floor plan and the position of the robot 
	@Override
	public void run() {
		//loop will run until the user exit out of the program
		while(running) {
		
			render();
		}
		
	}
	//Start this thread
	public synchronized void start() {
		//initialize variable this thread is in a loop and start the thread
		running = true;
		new Thread(this).start();
	}
	//Stop this thread
	public synchronized void stop() {
		//Stop this thread and exit with 0
		running = true;
		System.exit(0);
	}
		
   //Display the graphic output of this software
	public void render() {
		//Initialize variable needed to paint the output
		BufferStrategy strategy = getBufferStrategy();
		if(strategy == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = strategy.getDrawGraphics();
		
		g.drawImage(image,0,0,getWidth(),getHeight(),null);
		
		//Loop through the 2d array and display its' position and the robot if it has it
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < columns; j++) {
				layout[i][j].render(g);
				
			}
		}
		//Show the graphic output
		g.dispose();
		strategy.show();
	}
	
}
