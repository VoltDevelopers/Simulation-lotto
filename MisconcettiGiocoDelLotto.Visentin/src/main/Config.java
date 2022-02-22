package main;

public class Config {

	private static Config   config   = null;
	private double winningValue = 0;
	private int numbersXBet = 0;

	private Config() {}

	public static Config getConfig() {
		
		if(config == null) config = new Config();
		
		return config;
		
	}

	public double getWinningValue() {
		
		return winningValue;
		
	}

	public void setWinningValue(double winningValue) {
		
		this.winningValue = winningValue;
		
	}

	public int getNumbersXBet() {
		
		return numbersXBet;
		
	}

	public void setNumbersXBet(int numbersXBet) {
		
		this.numbersXBet = numbersXBet;
		
	}

}
