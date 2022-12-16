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
 *         Responsibilities of class: Constructor and methods for Game class
 */

public class UnoGame
{
	
	// Initialization of the numberOfPlayers variable for utilization by class methods.
	public static int numberOfPlayers;
	// Initialization of the gameDirection variable for utilization by class methods.
	public static int gameDirection = 0;
	// Initialization of the currentPlayer object for utilization by class methods.
	public static Player currentPlayer;
	// Initialization of the topCard object for utilization by class methods.
	public static Card topCard;
	// Initialization of the playerList array for utilization by class methods.
	public static Player[] playerList;
	// Initialization of the Random object for utilization by class methods.
	public Random random = new Random();
	// Initialization of UnoGUI for utilization by class methods.
	public UnoGUI unoView;

	/**
	 * Purpose: Method that is called after a Player's Card passes the
	 * playability check. Sets the topCard to the Player's Card, and removes it
	 * from their playerDeck, then moves to the next Player's turn based on the
	 * gameDirection. May have additional affects on the game state based on the
	 * type of card.
	 * 
	 * @param playerCard the Card that was played
	 * @param player     the Player that played the Card
	 */
	public void playCard(Card playerCard, Player player)
	{
		
		switch (playerCard.cardType)
		{
			
			case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 ->
			{
				
				topCard = playerCard;
				player.playerDeck.remove(playerCard);
				currentPlayer = nextTurn(player);
				
			}
			
			case 10 ->
			{
				
				topCard = playerCard;
				player.playerDeck.remove(playerCard);
				currentPlayer = nextTurn(nextTurn(player));
				
			}
			
			case 11 ->
			{
				
				topCard = playerCard;
				if (gameDirection == 0)
				{
					
					gameDirection = 1;
					
				}
				
				else
				{
					
					gameDirection = 0;
					
				}
				
				player.playerDeck.remove(playerCard);
				currentPlayer = nextTurn(player);
				
			}
			
			case 12 ->
			{
				
				player.playerDeck.remove(playerCard);
				currentPlayer = nextTurn(player);
				unoView.createWildCardWindow();
				
			}
			
			case 13 ->
			{
				
				topCard = playerCard;
				player.playerDeck.remove(playerCard);
				currentPlayer = nextTurn(player);
				drawCard(4, currentPlayer);
				
			}
			
		}
		
	}
	
	/**
	 * Purpose: Returns the Player's nextPlayer or prevPlayer based on the
	 * gameDirection.
	 * 
	 * @param player specified Player
	 * @return the Player's nextPlayer or prevPlayer
	 */
	public Player nextTurn(Player player)
	{
		
		if (gameDirection == 0)
		{
			
			return player.nextPlayer;
			
		}
		
		else
		{
			
			return player.prevPlayer;
			
		}
		
	}

	/**
	 * Purpose: Gets the currentPlayer.
	 * 
	 * @return the currentPlayer
	 */
	public Player getCurrentPlayer()
	{
		
		return currentPlayer;
		
	}

	/**
	 * Purpose: Sets the currentPlayer.
	 * 
	 * @param player specified Player to set the currentPlayer to
	 */
	public void setCurrentPlayer(Player player)
	{
		
		currentPlayer = player;
		
	}
	
	/**
	 * Purpose: Gets a specified Player from the playerList Array by their
	 * playerNumber
	 * 
	 * @param playerNumber the specified Player's playerNumber
	 * @return the specified Player
	 */
	public Player getPlayer(int playerNumber)
	{
		
		return playerList[playerNumber - 1];
		
	}

	/**
	 * Purpose: Gets the UnoGame's number of players
	 * 
	 * @return the UnoGame's number of players
	 */
	public int getNumberOfPlayers()
	{
		
		return numberOfPlayers;
		
	}

	/**
	 * Purpose: Sets the UnoGame's numberOfPlayers to a specified amount
	 * 
	 * @param userNumberOfPlayers specified amount of players
	 */
	public void setNumberOfPlayers(int userNumberOfPlayers)
	{
		
		numberOfPlayers = userNumberOfPlayers;
		
	}

	/**
	 * Purpose: Constructs an Array of Players with a length based on the
	 * UnoGame's numberOfPlayers.
	 */
	public void createPlayerList()
	{
		
		playerList = new Player[numberOfPlayers];
		
	}

	/**
	 * Purpose: Adds a Player to the playerList Array. The Player's index within
	 * the playerList is determined by the Player's playerNumber.
	 * 
	 * @param newPlayer    specified Player to add
	 * @param playerNumber specified Player's playerNumber
	 */
	public void addPlayer(String userName, int userNumber)
	{
		
		Player newPlayer = new Player(userName, userNumber);
		drawCard(7, newPlayer);
		
		playerList[userNumber - 1] = newPlayer;
		
	}

	/**
	 * Purpose: Constructs a Doubly Linked List of the Players contained in the
	 * playerList Array.
	 */
	public void linkPlayerList()
	{
		
		for (int i = 0; i < playerList.length - 1; i++)
		{
			
			if (playerList[i + 1] != null)
			{
				
				playerList[i].nextPlayer = playerList[i + 1];
				playerList[i + 1].prevPlayer = playerList[i];
				
			}
			
		}
		
		playerList[playerList.length - 1].nextPlayer = playerList[0];
		playerList[0].prevPlayer = playerList[playerList.length - 1];
		
		currentPlayer = playerList[0];
		
	}

	/**
	 * Purpose: Checks a Card's playability by comparing it with the UnoGame's
	 * topCard.
	 * 
	 * @param card specified Card to check
	 * @return boolean value indicating if the Card can be played
	 */
	public boolean checkCardValidity(Card card)
	{
		
		if (topCard.cardColor == 4)
		{
			
			return true;
			
		}
		
		else if (card.cardColor == 4)
		{
			
			return true;
			
		}
		
		else if (card.cardType == topCard.cardType || card.cardColor == topCard.cardColor)
		{
			
			return true;
			
		}
		
		else
		{
			
			return false;
			
		}
		
	}

	/**
	 * Purpose: Draws a specified amount of random Cards to a specified
	 * playerDeck
	 * 
	 * @param cards      specified amount of Cards to draw
	 * @param playerDeck specified playerDeck to add cards to
	 */
	public void drawCard(int cards, Player player)
	{
		
		for (int i = 0; i < cards; i++)
		{
			
			Card newCard = Card.createCard();
			newCard.cardOwner = player;
			player.playerDeck.add(newCard);
			
		}
		
	}

	/**
	 * Purpose: Creates a random Card and returns it as the Game's topCard
	 * 
	 * @return random topCard
	 */
	public void createTopCard()
	{
		
		topCard = new Card();
		topCard.cardType = random.nextInt(10);
		topCard.cardColor = random.nextInt(4);
		
	}
	
	/**
	 * Purpose: Sets the UnoGame's topCard to a specified Card.
	 * 
	 * @param card specified Card to set the topCard to
	 */
	public void setTopCard(Card card)
	{
		
		topCard = card;
		
	}

	/**
	 * Purpose: Gets the UnoGame's topCard.
	 * 
	 * @return the UnoGame's topCard
	 */
	public Card getTopCard()
	{
		
		return topCard;
		
	}

}
