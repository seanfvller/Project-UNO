import java.util.ArrayList;

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
	
	// A Player has-a name
	public String playerName;
	// A Player has-a number
	public int playerNumber;
	// A Player has-a next player
	public Player nextPlayer;
	// A Player has-a previous player
	public Player prevPlayer;
	// A Player has-a player deck
	public ArrayList<Card> playerDeck;
	
	/**
	 * Purpose: Class constructor that specifies the Player's playerName and
	 * playerNumber, then initializes the Player's nextPlayer, prevPlayer, and playerDeck.
	 * 
	 * @param userName specified playerName of the Player
	 * @param userNum specified playerNumber of the Player
	 */
	public Player(String userName, int userNum)
	{
		
		playerName = userName;
		playerNumber = userNum;
		nextPlayer = null;
		prevPlayer = null;
		playerDeck = new ArrayList<Card>();
		
	}

}
