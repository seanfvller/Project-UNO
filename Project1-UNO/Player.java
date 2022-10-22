/**
 * 
 */

/**
 * @author Dad
 *
 */
import java.util.*;
import java.util.InputMismatchException;

public class Player 
{
	//Fields
	String playerName;
	int playerNum = 0; // number of players in game min is 2
	int numberOfCards = 0; // total number of cards the player has
	ArrayList<Card> playerDeck;// = new ArrayList<Card>() ; //array of cards object each player has
//	ArrayList<Player> players; //array of players
//	int direction;
//	Player  nextPlayer;
//	Player  previousPlayer;
	
	Scanner scnr = new Scanner (System.in);
	
	
	//Constructor
	
	public Player (String name,int playerNumber)
	{
		playerName = null;
		playerNum = playerNumber;
		numberOfCards = 0;

	}
	
//	public void addPlayers(Scanner sc) {
//		this.players = new ArrayList<Player>();
//	}
	
	//Get Player name
//	public void getPlayerName(){
//		
//		try 
//		{
//			System.out.println("Enter the number of Players playing Uno");
//			playerNum = scnr.nextInt();
//	
//		}
//		catch(InputMismatchException e) 
//		{
//			System.out.println("Please enter a number!");
//			scnr.nextLine();
//			playerNum = scnr.nextInt();
//		}
//		
//			for(int i = 1; i < playerNum + 1; i++) {
//				System.out.print("Enter name of player " + i + ": ");
//				playerName = scnr.next();
//				System.out.println();
//			}
//		
//		
//		scnr.close();
//
//		
//	}
	
//	public ArrayList<Card> getPlayerHand() {
//		return playerDeck;
//		
//	}

	//Add a card to players deck
	public void drawCard(Card card) { 
		
		//if user inputs Draw, add one card to array list
		if(scnr.nextLine() == "Draw")
		{
			numberOfCards++;
			//implement create card method
			//TODO:
			playerDeck.add(card);		
		}
		
	}
	
	//remove a card from players deck
	public void removeCard(int index) {
		//TODO: remove card from players hand and add it to the discard pile
		playerDeck.remove(index); 
	}
	
//	//displays the players hand to player at every turn
	public void displayPlayerDeck() {
		System.out.println(playerName+ "'s cards");
		for (int i = 0; i < playerDeck.size(); i++) 
		{
	        System.out.print(playerDeck.get(i) + " ");
		}

	}

}


