package robotCleaner.src.model;

import java.util.ArrayList;

public interface Robot {
	
	public void setCurrentTile(Tile tile);
    
	public Tile getCurrentTile();
	
	public void deductPowerUnit(double power);
	
	public double getPowerUnit();
	
	public void setPowerUnit(double unit);
		
	public  Tile getPowerStation();
	
	public void cleanHouse();
		
	public int getCarryCap();
	
	public void setCarryCap(int unit);
	
	public void deductCarryCap(int unit);

}
