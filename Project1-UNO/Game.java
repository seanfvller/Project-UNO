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
	// A Game has-a numberOfPlayers
	public static int numberOfPlayers;
	static int playerNum = 0;
	static String playerName;
	public ArrayList<ArrayList<Card>> playerDecks;
	public Player[] playerList;
	// A Game has-a topCard
	static Card topCard;
	// A Game has-a choice
	static int choice;
	// A Game has-a gameDirection
	private static int gameDirection = 0;
	static int option;
	static Player newPlayerA, newPlayerB, newPlayerC;
	Player player;
	static Scanner scnr = new Scanner(System.in);

	// public static void gameStart()
	// {
	//
	// try
	// {
	// System.out.println(
	// "Enter '1' to start the Uno Game or enter '2' to Quit");
	//
	// option = scnr.nextInt();
	//
	// if (option == 1)
	// {
	// System.out.println("Please enter the amount of players: ");
	//
	// numberOfPlayers = scnr.nextInt();
	//
	// if (numberOfPlayers < 2 || numberOfPlayers > 8)
	// {
	// System.out.println(
	// "Error: Cannot play UNO with less than two players or more than eight
	// players. Please try again.");
	// gameStart();
	// }
	//
	// else if (numberOfPlayers >= 2 || numberOfPlayers <= 8)
	// {
	// createPlayerList();
	// }
	//
	// }
	//
	// else if (option == 2)
	// {
	// quitGame();
	// }
	//
	// }
	// catch (InputMismatchException e)
	// {
	// System.out.println("Please enter a number!");
	// scnr.nextLine();
	//
	// }
	//
	// }

	public static void playerTurn(Player player, int choice)
	{
		displayTopCard();
		System.out.println("It is " + player.playerName
				+ "'s turn. Please select from the following options:");
		System.out.println("1: Select a card to play from your deck.");
		System.out.println(
				"2: End your turn. (Automatically draws one card to your deck.)");
		System.out.println("3: UNO!");

		choice = scnr.nextInt();

		if (choice == 1)
		{
			int cardChoice;
			player.displayPlayerDeck();
			System.out.println(
					"Select a card by inputting its corresponding integer value.");
			try
			{
				cardChoice = scnr.nextInt() - 1;

				if (cardChoice == (player.playerDeck.size()))
				{
					drawCard(1, player.playerDeck);

					if (gameDirection == 0)
					{
						playerTurn(player.nextPlayer, 0);
					}

					else if (gameDirection == 1)
					{
						playerTurn(player.prevPlayer, 0);
					}

				}
				else if (checkCardValidity(
						player.playerDeck.get(cardChoice)) == true)
				{

					topCard = player.playerDeck.get(cardChoice);
					player.playerDeck.remove(cardChoice);

					if (topCard.cardType == 10)
					{
						System.out.println("Skipped "
								+ player.nextPlayer.playerName + "'s turn.\n");
						if (gameDirection == 0)
						{
							player = player.nextPlayer;
							playerTurn(player.nextPlayer, 0);
						}
						else if (gameDirection == 1)
						{
							player = player.prevPlayer;
							playerTurn(player.prevPlayer, 0);
						}

					}

					else if (topCard.cardType == 11)
					{
						System.out.println(
								"The flow of player turns has been reversed.");
						if (gameDirection == 0)
						{
							gameDirection = 1;

						}
						else if (gameDirection == 1)
						{
							gameDirection = 0;

						}
						if (gameDirection == 0)
						{
							playerTurn(player.nextPlayer, 0);
						}
						else if (gameDirection == 1)
						{
							playerTurn(player.prevPlayer, 0);
						}

					}

					else if (topCard.cardType == 12)
					{
						if (gameDirection == 1)
						{
							cardDrawFour(player.nextPlayer);
							playerTurn(player.nextPlayer, 0);
						}
						else if (gameDirection == 0)
						{
							cardDrawFour(player.prevPlayer);
							playerTurn(player.prevPlayer, 0);
						}
					}

					else
					{
						if (gameDirection == 0)
						{
							playerTurn(player.nextPlayer, 0);
						}
						else if (gameDirection == 1)
						{
							playerTurn(player.prevPlayer, 0);
						}
					}

				}

				else if (checkCardValidity(
						player.playerDeck.get(cardChoice)) == false)
				{
					System.out.println(
							"You cannot play this card. Please select a different card.");
					playerTurn(player, 0);
				}

			}
			catch (ArrayIndexOutOfBoundsException e)
			{
				System.out.println(
						"Error: Number chosen not valid. Please try again. \n");
				playerTurn(player, 0);
			}
			catch (NullPointerException e)
			{
				System.out.println(
						"Error: Number chosen not valid. Please try again. \n");
				playerTurn(player, 0);
			}
		}

		else if (choice == 2)
		{
			drawCard(1, player.playerDeck);

			if (gameDirection == 0)
			{
				playerTurn(player.nextPlayer, 0);
			}

			else if (gameDirection == 1)
			{
				playerTurn(player.prevPlayer, 0);
			}
		}

		else if (choice == 3)
		{
			if (player.playerDeck.size() == 0)
			{
				System.out.println(player.playerName + " has won the game!");
				quitGame();
			}
			else if (player.playerDeck.size() > 0)
			{
				System.out.println(
						"You do not meet the conditions to call 'UNO!'");
				playerTurn(player, 0);
			}
		}
	}

	/**
	 * Purpose: Displays the Game's topCard
	 */
	public static void displayTopCard()
	{
		System.out.println("The Top Card is a " + topCard.cardColorToString()
				+ " " + topCard.cardTypeToString() + ".\n");
	}

	/**
	 * Purpose: Checks a Card's playability by comparing it with the Game's
	 * topCard
	 * 
	 * @param card specified Card to check
	 * @return boolean value indicating if Card can be played
	 */
	public static boolean checkCardValidity(Card card)
	{
		if (card.cardType == 12 || card.cardColor == 4
				|| card.cardType == topCard.cardType
				|| card.cardColor == topCard.cardColor)
		{
			return true;
		}
		else
		{
			return false;
		}
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
	 * Purpose: Adds a Player to the playerList
	 * @param newPlayer
	 * @param playerNumber
	 */
	public void addPlayer(Player newPlayer, int playerNumber)
	{
		playerList[playerNumber - 1] = newPlayer;
	}

	public void linkPlayerList()
	{
		for (int i = 0; i < playerList.length - 1; i++)
		{
			if (playerList[i + 1] != null)
			{
			playerList[i].nextPlayer = playerList[i + 1];
			System.out.println("Player " + (i + 1) + "'s next player is Player " + (i + 2));
			playerList[i + 1].prevPlayer = playerList[i];
			System.out.println("Player " + (i + 2) + "'s previous player is Player " + (i + 1));
			}
		}
		playerList[0].prevPlayer = playerList[playerList.length - 1];
		System.out.println("Player 1's previous player is Player " + (playerList.length));
		playerList[playerList.length - 1].nextPlayer = playerList[0];
		System.out.println("Player " + (playerList.length) + "'s next player is Player 1");
	}

	/**
	 * Purpose: Creates a random Card and returns it as the Game's topCard
	 * 
	 * @return random topCard
	 */
	public static Card createTopCard()
	{
		topCard = new Card();
		topCard.createCard();
		return topCard;
	}

	/**
	 * Purpose: Draws a specified amount of random Cards to a specified
	 * playerDeck
	 * 
	 * @param cards      specified amount of Cards to draw
	 * @param playerDeck specified playerDeck to add cards to
	 */
	public static void drawCard(int cards, ArrayList<Card> playerDeck)
	{
		for (int i = 0; i < cards; i++)
		{
			Card newCard = new Card();
			newCard.createCard();
			playerDeck.add(newCard);
		}
	}

	/**
	 * Purpose: Causes the specified Player to draw 4 random Cards to their
	 * playerDeck
	 * 
	 * @param player specified player
	 */
	public static void cardDrawFour(Player player)
	{
		System.out.println(player.playerName + " draws 4 cards!");
		drawCard(4, player.playerDeck);
	}

	/**
	 * Purpose: Exits the application
	 */
	public static void quitGame()
	{
		System.out.print("Thank you for playing Uno. Goodbye!");
		System.exit(0);
	}

	/**
	 * Purpose: Main method of Game class
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		System.out.println("Welcome to Uno!");
		// gameStart();
		createTopCard();
		// playerTurn(newPlayerA, 0);
	}
}
