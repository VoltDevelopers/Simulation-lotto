package bean;

import main.Config;

public class Player {
	
	private double currentMoney = 100;
	private int numbersXBet = Config.getConfig().getNumbersXBet();
	private Quintet playerBet = null;
	private String betToString = "";
	
	public Player() {
		
		playerBet = new Quintet(numbersXBet);
		
	}

	public double getCurrentMoney() {
		
		return currentMoney;
		
	}

	public void setCurrentMoney(double currentMoney) {
		
		this.currentMoney = currentMoney;
		
	}

	public int getNumbersXBet() {
		
		return numbersXBet;
		
	}

	public void setNumbersXBet(int numbersXBet) {
		
		this.numbersXBet = numbersXBet;
		
	}

	public Quintet getPlayerBet() {
		
		return playerBet;
		
	}

	public void setPlayerBet(Quintet playerBet) {
		
		this.playerBet = playerBet;
		
	}

	public String getBetToString() {
		
		return betToString;
		
	}

	public void setBetToString(String betToString) {
		
		this.betToString = betToString;
		
	}
	
}
