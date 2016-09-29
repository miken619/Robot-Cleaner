package robotCleaner.src.model;

import java.awt.Graphics;

public interface Tile {
	
	public void setWestTile(Tile end);

	public void setNorthTile(Tile end);
	
	public void setEastTile(Tile end);
	
	public void setSouthTile(Tile end);
	
	public Tile getWestTile();

	public Tile getNorthTile();
	
	public Tile getEastTile();
	
	public Tile getSouthTile();	
	
	public int getIdentifier();

	public double getTileCost();
	
  
	
	public void setPathCost(double cost); 
		  
	public double getPathCost(); 
	
	public void setPrevTile(Tile tile);
	
	public Tile getPrevTile();
	
	public String getCoordinate();
	
	public void render(Graphics g);
	
	public void setRobot(boolean r);
	
	public boolean getVisit();
	
	public void setVisit(boolean visit);
}
