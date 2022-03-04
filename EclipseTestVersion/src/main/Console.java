package main;

import java.util.Scanner;

import businness.GameManager;

public class Console {
	
	private Scanner scan = null;
	private char choice = ' ';
	private GameManager gameManager = null;
	
	public Console() {
		
		scan = new Scanner(System.in);
		gameManager = new GameManager();
		
		start();
		
	}
	
	private void start() {
		
		setGameType();
		
		setPlayerBet();
		
		System.out.println("Cinquine estratte in precedenza:\n" + gameManager.getPreviousQuintets());

		gameManager.startGame();
	
		System.out.println("Puntata del giocatore 1 che sceglie di puntare un numero uscito tra la cinquina precedente --> " + gameManager.getPlayer1Bet());
		System.out.println("Puntata del giocatore 2 che sceglie di giocare i numeri usciti meno frequentemente --> " + gameManager.getPlayer2Bet());
		System.out.println("Puntata del giocatore 3 che sceglie di giocare dei numeri casuali --> " + gameManager.getPlayer3Bet());
		System.out.println("Puntata del giocatore 4 che sceglie di giocare dei numeri fissi --> " + gameManager.getPlayer4Bet());
		System.out.println("Puntata del giocatore 5 che sceglie di giocare i numeri usciti piu' frequentemente --> " + gameManager.getPlayer5Bet());
		
		System.out.println("Cinquina estratta:" + gameManager.getQuintetExtracted());
		
		System.out.println(gameManager.getResults());
		
	}

	private void setGameType() {
		
		System.out.println("1--> Gioco equo\n2--> Gioco reale");
		
		while(choice != '1' && choice != '2') {
			
			choice = scan.next().charAt(0);
			
			switch(choice) {
			
				case '1':
				
					Config.getConfig().setWinningValue(18);;
					break;
					
				case '2':
					
					Config.getConfig().setWinningValue(10.33);
					break;
			
			}
		}
	}
	
	private void setPlayerBet() {
		
		System.out.println("1--> Ogni giocatore gioca un numero\n2--> Ogni giocatore gioca due numeri\n3--> Ogni giocatore gioca tre numeri\n4--> Ogni giocatore gioca quattro numeri\n5--> Ogni giocatore gioca cinque numeri");
		choice = ' ';
		
		while(choice != '1' && choice != '2' && choice != '3' && choice != '4' && choice != '5') {
			
			choice = scan.next().charAt(0);
			
			switch(choice) {
			
				case '1':
				
					Config.getConfig().setNumbersXBet(1);
					break;
					
				case '2':
					
					Config.getConfig().setNumbersXBet(2);
					break;
					
				case '3':
					
					Config.getConfig().setNumbersXBet(3);
					break;
					
				case '4':
					Config.getConfig().setNumbersXBet(4);
					break;
					
				case '5':
					Config.getConfig().setNumbersXBet(5);
					break;
			
			}
		}
	}
	
}
