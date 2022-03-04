package bean;

import java.util.ArrayList;

public class Quintets {
	
	private ArrayList <Quintet> quintets = null;
	
	public Quintets() {
		
		quintets = new ArrayList<Quintet>();
		
	}

	public ArrayList<Quintet> getQuintets() {
		
		return quintets;
		
	}

	public void setQuintets(ArrayList<Quintet> quintets) {
		
		this.quintets = quintets;
		
	} 

}
