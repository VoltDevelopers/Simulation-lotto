package bean;

public class Number {
	
	private int number = 0;
	private int counter = 0;
	
	public Number(int number, int counter) {
		
		this.number = number;
		this.counter = counter;
		
	}

	public int getNumber() {
		
		return number;
		
	}

	public void setNumber(int number) {
		
		this.number = number;
		
	}

	public int getCounter() {
		
		return counter;
		
	}

	public void setCounter(int counter) {
		
		this.counter = counter;
		
	}

}
