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

public class Game
{
	public static int numberOfPlayers;
	public static int gameDirection = 0;
	public static Player currentPlayer;
	public static Card topCard;
	public static Player[] playerList;
	public Random random = new Random();

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
				topCard = playerCard;
				player.playerDeck.remove(playerCard);
				currentPlayer = nextTurn(player);
				drawCard(4, currentPlayer);
			}
		}
	}

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

	public Player getCurrentPlayer()
	{
		return currentPlayer;
	}
	
	public Card getCardDrawn()
	{
		return currentPlayer.playerDeck.get(currentPlayer.playerDeck.size() - 1);
	}

	public void setCurrentPlayer(Player player)
	{
		currentPlayer = player;
	}
	
	/**
	 * Purpose: Gets a specified Player from the playerList Array by their playerNumber
	 * @param playerNumber the specified Player's playerNumber
	 * @return the specified Player
	 */
	public Player getPlayer(int playerNumber)
	{
		return playerList[playerNumber - 1];
	}
	
	public int getPlayerNumber(Player player)
	{
		return player.playerNumber;
	}

	/**
	 * Purpose: Gets the Game's number of players
	 * 
	 * @return the Game's number of players
	 */
	public int getNumberOfPlayers()
	{
		return numberOfPlayers;
	}

	/**
	 * Purpose: Sets the Game's number of players to a specified amount
	 * 
	 * @param userNum specified amount of players
	 */
	public void setNumberOfPlayers(int userNumberOfPlayers)
	{
		numberOfPlayers = userNumberOfPlayers;
	}

	/**
	 * Purpose: Constructs an Array of Players with a length specified by the
	 * Game's numberOfPlayers
	 */
	public void createPlayerList()
	{
		playerList = new Player[numberOfPlayers];
	}

	/**
	 * Purpose: Adds a Player to the playerList Array. The Player's index within
	 * the playerList is determined by the Player's playerNumber
	 * 
	 * @param newPlayer    specified Player to add
	 * @param playerNumber specified Player's playerNum
	 */
	public void addPlayer(String userName, int userNumber)
	{
		Player newPlayer = new Player(userName, userNumber);
		drawCard(7, newPlayer);
		playerList[userNumber - 1] = newPlayer;
	}

	/**
	 * Purpose: Constructs a Doubly Linked List of the Players contianed in the
	 * playerList Array
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
	 * Purpose: Checks a Card's playability by comparing it with the Game's
	 * topCard
	 * 
	 * @param card specified Card to check
	 * @return boolean value indicating if Card can be played
	 */
	public boolean checkCardValidity(Card card)
	{
		if (topCard.cardColor == 4 || topCard.cardColor == 12)
		{
			return true;
		}
		else if (card.cardType == 12 || card.cardColor == 4)
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
			Card newCard = new Card();
			newCard.cardOwner = player;
			newCard.cardType = random.nextInt(13);
			switch (newCard.cardType)
			{
				case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ->
				newCard.cardColor = random.nextInt(4);

				
				case 12 ->
				newCard.cardColor = 4;

			}
			player.playerDeck.add(newCard);
		}
	}

	/**
	 * Purpose: Causes the specified Player to draw 4 random Cards to their
	 * playerDeck
	 * 
	 * @param player specified player
	 */
	public void cardDrawFour(Player player)
	{
		System.out.println(player.playerName + " draws 4 cards!");
		drawCard(4, player);
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
	 * Purpose: Gets the Game's topCard
	 * 
	 * @return the Game's topCard
	 */
	public Card getTopCard()
	{
		return topCard;
	}

}
