package businness;

import java.util.ArrayList;
import java.util.Random;

import bean.Number;
import bean.Player;
import bean.Quintet;
import bean.Quintets;
import main.Config;

public class GameManager {
	
	private Quintets previousQuintets = null;
	private ArrayList<bean.Number> numbers = null;
	private final int PREVIOUSQUINTETS = 100;
	private final int MAX = 90, MIN = 1;
	private Random random = null;
	private String quintetsToString = "";
	
/* -------------------------------------------------------------------------------------*/	
	
	private double winningPrice = Config.getConfig().getWinningValue();
	private final int GAMEPRICE = 1;
	private Player player1, player2, player3, player4, player5;
	private Quintet quintetExtracted = null;
	private String quintetExtractedToString = "";
	private String results = "";
	
	public GameManager() {

		previousQuintets = new Quintets();
		random = new Random();
		numbers = new ArrayList<bean.Number>(MAX + 1);
		quintetExtracted = new Quintet(5);
		
		generatePreviousQuintets();
		
	}
	

	private void generatePreviousQuintets() {
		
		for(int i = 0; i < PREVIOUSQUINTETS; i++) {
			
			ArrayList<Integer>tempQuintet = new ArrayList<Integer>(5);
			
			for(int j = 0; j < 5; j++) {
				
				int value = random.nextInt(MAX) + MIN;
				tempQuintet.add(value);
				
				for (int k = 0; k < j; k++) {

	                if (tempQuintet.get(j) == tempQuintet.get(k)) {

	                	tempQuintet.remove(j);
	                    j--;
	                    break;

	                }
	            }	
			}
			
			previousQuintets.getQuintets().add(new Quintet(tempQuintet));
			
		}
		
		countNumbers();
		
	}
		
	private void countNumbers() {
		
		for(int i = 0; i < MAX; i++) {
			
			int counter = 0;
			numbers.add(new Number(i + 1, counter));
			
			for(int j = 0; j < PREVIOUSQUINTETS; j++) {
				
				for(int m = 0; m < 5; m++) {
					
					if(numbers.get(i).getNumber() == previousQuintets.getQuintets().get(j).getQuintet().get(m)) {
						
						counter++;
						numbers.get(i).setCounter(counter);
											}
				}
			}
		}
	}
	
	public String getPreviousQuintets() {
		
		for(int i = 0; i < PREVIOUSQUINTETS; i++) {
			
			for(int j = 0; j < 5; j++) {
				
				if(j == 0) {
					
					quintetsToString += "[ " + previousQuintets.getQuintets().get(i).getQuintet().get(j) + ", ";
					
				}else if (j== 4) {
					
					quintetsToString += previousQuintets.getQuintets().get(i).getQuintet().get(j) + "] ";
					
				}else {
					
					quintetsToString += previousQuintets.getQuintets().get(i).getQuintet().get(j) + ", ";
					
				}
			}
			
			quintetsToString += "\n";
			
		}
		
		return quintetsToString;
		
	}
	
	public void startGame() {
		
		player1 = new Player();
		player1.setCurrentMoney(player1.getCurrentMoney() - GAMEPRICE);
		setPlayer1Quintet();
		
		player2 = new Player();
		player2.setCurrentMoney(player2.getCurrentMoney() - GAMEPRICE);
		setPlayer2Quintet();
		
		player3 = new Player();
		player3.setCurrentMoney(player3.getCurrentMoney() - GAMEPRICE);
		setPlayer3Quintet();
		
		player4 = new Player();
		player4.setCurrentMoney(player4.getCurrentMoney() - GAMEPRICE);
		setPlayer4Quintet();
		
		player5 = new Player();
		player5.setCurrentMoney(player5.getCurrentMoney() - GAMEPRICE);
		setPlayer5Quintet();
		
		generateRandomQuintet();
		checkResults();
		
	}
	
	private void setPlayer1Quintet() {
		
		int prevValue = random.nextInt(5);
		int randomPos = random.nextInt(Config.getConfig().getNumbersXBet());
		ArrayList<Integer> temp = new ArrayList<>();
		
		for(int i = 0; i < Config.getConfig().getNumbersXBet(); i++) {
			
			if(i == randomPos) {
				
				temp.add(previousQuintets.getQuintets().get(PREVIOUSQUINTETS - 1).getQuintet().get(prevValue));
				
			}else {
				
				int value = random.nextInt(MAX) + MIN;
				temp.add(value);
				
				for (int k = 0; k < i; k++) {

	                if (temp.get(i) == temp.get(k) ) {

	                	temp.remove(i);
	                    i--;
	                    break;

	                }
	            }
			}	
		}
		
		player1.getPlayerBet().setQuintet(temp);
		
	}
	
	private void setPlayer2Quintet() {
		
		ArrayList<Integer> temp = new ArrayList<>();
		int i = 0;
			
			do {
				
				for(int j = 0; j < MAX; j++) {
					
					if(numbers.get(j).getCounter() == i) {
						
						if(temp.size() != Config.getConfig().getNumbersXBet()) {
							
							temp.add(numbers.get(j).getNumber());
							
						}else {
							
							break;
							
						}
					}
				}
				
				i++;
				
			}while(temp.size() != Config.getConfig().getNumbersXBet());
		
		player2.getPlayerBet().setQuintet(temp);	
		
	}
	
	private void setPlayer3Quintet() {
		
		ArrayList <Integer> temp = new ArrayList<>();
		
		for(int i = 0; i < Config.getConfig().getNumbersXBet(); i++) {
			
			int value = random.nextInt(MAX) + MIN;
			temp.add(value);
			
			for (int k = 0; k < i; k++) {

                if (temp.get(i) == temp.get(k)) {

                	temp.remove(i);
                    i--;
                    break;

                }
			}
		}
		
		player3.getPlayerBet().setQuintet(temp);
		
	}

	private void setPlayer4Quintet() {
		
		ArrayList <Integer> temp = new ArrayList<>();
		
		for(int i = 0; i < Config.getConfig().getNumbersXBet(); i++) {
			
			switch (i) {
			
				case 0:
					temp.add(13);
					break;
				case 1:
					temp.add(73);
					break;
				case 2:
					temp.add(47);
					break;
				case 3:
					temp.add(84);
					break;
				case 4:
					temp.add(5);
					break;
			}
		}
		
		player4.getPlayerBet().setQuintet(temp);
		
	}
	
	private void setPlayer5Quintet() {
		
		ArrayList<Integer> temp = new ArrayList<>();
		int i = PREVIOUSQUINTETS * 5;
			
			do {
				
				for(int j = 0; j < MAX; j++) {
					
					if(numbers.get(j).getCounter() == i) {
						
						if(temp.size() != Config.getConfig().getNumbersXBet()) {
							
							temp.add(numbers.get(j).getNumber());
							
						}else {
							
							break;
							
						}
					}
				}
				
				i--;
				
			}while(temp.size() != Config.getConfig().getNumbersXBet());
		
		player5.getPlayerBet().setQuintet(temp);
		
	}
	
	private void generateRandomQuintet() {
		
		ArrayList<Integer> temp = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			
			int value = random.nextInt(MAX) + MIN;
			temp.add(value);
			
			for (int k = 0; k < i; k++) {

                if (temp.get(i) == temp.get(k)) {

                	temp.remove(i);
                    i--;
                    break;

                }
			}
		}
		
		quintetExtracted.setQuintet(temp);
		
	}
	
	private void checkResults() {
		
		int counter1 = 0,counter2 = 0,counter3 = 0,counter4 = 0,counter5 = 0;
		
		for(int i = 0; i < Config.getConfig().getNumbersXBet(); i++) {
			
			for(int j = 0; j < 5; j++) {
				
				if(player1.getPlayerBet().getQuintet().get(i) == quintetExtracted.getQuintet().get(j)) {
					
					player1.setCurrentMoney(player1.getCurrentMoney() + winningPrice);
					counter1++;
					
				}else if(player2.getPlayerBet().getQuintet().get(i) == quintetExtracted.getQuintet().get(j)) {
					
					player2.setCurrentMoney(player2.getCurrentMoney() + winningPrice);
					counter2++;
					
				}else if(player3.getPlayerBet().getQuintet().get(i) == quintetExtracted.getQuintet().get(j)) {
					
					player3.setCurrentMoney(player3.getCurrentMoney() + winningPrice);
					counter3++;
					
				}else if(player4.getPlayerBet().getQuintet().get(i) == quintetExtracted.getQuintet().get(j)) {
					
					player4.setCurrentMoney(player4.getCurrentMoney() + winningPrice);
					counter4++;
					
				}else if(player5.getPlayerBet().getQuintet().get(i) == quintetExtracted.getQuintet().get(j)) {
					
					player5.setCurrentMoney(player5.getCurrentMoney() + winningPrice);
					counter5++;
					
				}
				
			}
			
		}
		
		results = "Il giocatore 1 ha indovinato " + counter1 + " numeri\n" + "Il giocatore 2 ha indovinato " + counter2 + " numeri\n" + "Il giocatore 3 ha indovinato " + counter3 + " numeri\n" + "Il giocatore 4 ha indovinato " + counter4 + " numeri\n"+ "Il giocatore 5 ha indovinato " + counter5 + " numeri";
		
	}
	
	public String getPlayer1Bet() {
		
		for(int i = 0; i < player1.getNumbersXBet(); i++) {
			
			player1.setBetToString(player1.getBetToString() + "[ " + player1.getPlayerBet().getQuintet().get(i).toString() + " ]");
									
		}
		
		return player1.getBetToString();
	}
	
	public String getPlayer2Bet() {
		
		for(int i = 0; i < player2.getNumbersXBet(); i++) {
			
			player2.setBetToString(player2.getBetToString() + "[ " + player2.getPlayerBet().getQuintet().get(i).toString() + " ]");
									
		}
		
		return player2.getBetToString();
		
	}
	
	public String getPlayer3Bet() {
		
		for(int i = 0; i < player3.getNumbersXBet(); i++) {
			
			player3.setBetToString(player3.getBetToString() + "[ " + player3.getPlayerBet().getQuintet().get(i).toString() + " ]");
									
		}
		
		return player3.getBetToString();
		
	}
	
	public String getPlayer4Bet() {
		
		for(int i = 0; i < player4.getNumbersXBet(); i++) {
			
			player4.setBetToString(player4.getBetToString() + "[ " + player4.getPlayerBet().getQuintet().get(i).toString() + " ]");
									
		}
		
		return player4.getBetToString();
		
	}
	
	public String getPlayer5Bet() {
		
		for(int i = 0; i < player5.getNumbersXBet(); i++) {
			
			player5.setBetToString(player5.getBetToString() + "[ " + player5.getPlayerBet().getQuintet().get(i).toString() + " ]");
									
		}
		
		return player5.getBetToString();
		
	}
	
	public String getQuintetExtracted() {
		
		for(int i = 0; i < 5; i++) {
				
			quintetExtractedToString += "[ " + quintetExtracted.getQuintet().get(i) + " ]";
				
		}
		
		return quintetExtractedToString;
		
	}


	public String getResults() {
		
		return results;
		
	}
}
