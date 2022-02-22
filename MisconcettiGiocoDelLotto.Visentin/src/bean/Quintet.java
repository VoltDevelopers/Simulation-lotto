package bean;

import java.util.ArrayList;

public class Quintet {
	
	private ArrayList <Integer> quintet = null;
	
	public Quintet(ArrayList<Integer> randomQuintet) {
		
		quintet = new ArrayList<Integer>(5);
		quintet = randomQuintet;
		
	}
	
	public Quintet(int numbersXBet) {
		
		quintet = new ArrayList<Integer>(numbersXBet);
		
	}

	public ArrayList<Integer> getQuintet() {
		
		return quintet;
		
	}

	public void setQuintet(ArrayList<Integer> quintet) {
		
		this.quintet = quintet;
		
	}

}
