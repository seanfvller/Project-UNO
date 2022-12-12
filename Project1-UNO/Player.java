import java.util.*;

/**
 * Lead Authors(s):
 * 
 * @author Sean Fuller
 * @author Tristen Tran
 * 
 *         Other contributors:
 *         None
 * 
 *         Version/date: 1.0
 * 
 *         Responsibilities of class: Constructor and methods for Player class
 */

public class Player
{
	// A Player has-a playerName
	public String playerName;
	// A Player has-a playerNumber
	public int playerNumber;
	// A Player has-a numberOfCards
	public int numberOfCards = 0;
	// A Player has-a nextPlayer
	public Player nextPlayer;
	// A Player has-a prevPlayer
	public Player prevPlayer;
	// A Player has-a playerDeck
	public ArrayList<Card> playerDeck;

	/**
	 * Purpose: Class constructor that specifies the Player's playerNumber;
	 * initializes the Player's playerName, numberOfCards, and playerDeck, then
	 * calls setPlayerName() to set the Player's name
	 * 
	 * @param playerNum
	 */
	public Player(int playerNum)
	{
		playerName = "";
		playerNumber = playerNum;
		numberOfCards = 0;
		playerDeck = new ArrayList<Card>();
	}

	/**
	 * Purpose: Sets the Player's playerName to a specified String
	 */
	public void setPlayerName()
	{
		Scanner scnr = new Scanner(System.in);
		System.out.print("Enter name of player " + (playerNumber + 1) + ": ");
		playerName = scnr.next();
		System.out.println();
	}

	/**
	 * Purpose: Removes a Card at a specified index from the Player's playerDeck
	 * 
	 * @param specified index of Card to remove
	 */
	public void removeCard(int index)
	{
		playerDeck.remove(index);
	}
	
	public Player getPlayer(int i)
	{
		
		return;
	}

	/**
	 * Purpose: Displays all of the Player's Cards from their playerDeck as
	 * Strings
	 */
	public void displayPlayerDeck()
	{

		System.out.println(playerName + "'s cards");
		for (int i = 0; i < playerDeck.size(); i++)
		{
			System.out.println(
					(i + 1) + ": " + playerDeck.get(i).cardColorToString() + " "
							+ playerDeck.get(i).cardTypeToString());
		}
		System.out.println((playerDeck.size() + 1) + ": Draw Card");

	}
}
