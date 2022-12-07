/**
 * 
 */

/**
 * @author Sean Fuller, Tristen Tran
 *
 */
import java.util.*;

public class Player 
{
	//Fields
	String playerName;
	int playerNumber; 
	int numberOfCards = 0; // total number of cards the player has
	Player nextPlayer;
	Player prevPlayer;
	ArrayList<Card> playerDeck; //array of cards object each player hass
	
	Scanner scnr = new Scanner (System.in);
	
	
	//Constructor
	public Player(int playerNum)
	{
		playerName = "";
		playerNumber = playerNum;
		numberOfCards = 0;
		playerDeck = new ArrayList<Card>();
		setPlayerName();
	}
	
	//Get Player name
	public void setPlayerName() {
		System.out.print("Enter name of player " + (playerNumber + 1) + ": ");
		playerName = scnr.next();
		System.out.println();
		}

	
	//remove a card from players deck
	public void removeCard(int index) {
		playerDeck.remove(index); 
	}
	
//	//displays the players hand to player at every turn
	public void displayPlayerDeck() {
		
		System.out.println(playerName + "'s cards");
		for (int i = 0; i < playerDeck.size(); i++) 
		{
	        System.out.println((i +1) + ": " + playerDeck.get(i).cardColorToString() + " " + playerDeck.get(i).cardTypeToString());
			}
			System.out.println((playerDeck.size() + 1) + ": Draw Card") ;
	
	
	}
}


