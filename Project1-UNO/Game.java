import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.*;

/**
 * @author Sean Fuller, Tristen Tran
 *
 */
public class Game
{
	// Fields
	static int numberOfPlayers;
	static int playerNum = 0;
	static String playerName;
	static Card topCard; // displays top card on discard pile
	static int choice; // index of options (i.e. 1 - red 5)
	static int gameDirection = 0; // variable to keep track of game direction
	static int option;
	static Player newPlayerA, newPlayerB, newPlayerC;
	static Scanner scnr = new Scanner(System.in);

	public static void gameStart()
	{

		try
		{
			System.out.println(
					"Enter '1' to start the Uno Game or enter '2' to Quit");

			option = scnr.nextInt();

			if (option == 1)
			{
				System.out.println("Please enter the amount of players: ");

				numberOfPlayers = scnr.nextInt();

				if (numberOfPlayers < 2 || numberOfPlayers > 8)
				{
					System.out.println(
							"Error: Cannot play UNO with less than two players or more than eight players. Please try again.");
					gameStart();
				}

				else if (numberOfPlayers >= 2 || numberOfPlayers <= 8)
				{
					createPlayerList();
				}

			}

			else if (option == 2)
			{
				quitGame();
			}

		}
		catch (InputMismatchException e)
		{
			System.out.println("Please enter a number!");
			scnr.nextLine();

		}

	}

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
							cardDrawFour(player.nextPlayer, 4);
							playerTurn(player.nextPlayer, 0);
						}
						else if (gameDirection == 0)
						{
							cardDrawFour(player.prevPlayer, 4);
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

	public static void displayTopCard()
	{
		System.out.println("The Top Card is a " + topCard.cardColorToString()
				+ " " + topCard.cardTypeToString() + ".\n");
	}

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

	public static void createPlayerList()
	{
		newPlayerA = new Player(0);
		setCardForPlayer(newPlayerA);
		newPlayerB = new Player(1);
		setCardForPlayer(newPlayerB);
		newPlayerA.nextPlayer = newPlayerB;
		newPlayerB.prevPlayer = newPlayerA;

		for (int i = 2; i < numberOfPlayers; i++)
		{
			newPlayerC = new Player(i);
			setCardForPlayer(newPlayerC);
			newPlayerB.nextPlayer = newPlayerC;
			newPlayerC.prevPlayer = newPlayerB;
			newPlayerB = newPlayerC;
		}

		newPlayerA.prevPlayer = newPlayerB;
		newPlayerB.nextPlayer = newPlayerA;
	}

	// create new card to for starting card
	public static Card createTopCard()
	{
		topCard = new Card();
		topCard.createCard();
		return topCard;
	}

	// gives 7 cards to each player in the beginning
	public static void setCardForPlayer(Player player)
	{
		for (int i = 0; i < 7; i++)
		{
			Card newCard = new Card();
			newCard.createCard();
			player.playerDeck.add(newCard);
		}
	}

	public static void drawCard(int cards, ArrayList<Card> playerDeck)
	{
		for (int i = 0; i < cards; i++)
		{
			Card newCard = new Card();
			newCard.createCard();
			playerDeck.add(newCard);
		}
	}

	public static void cardDrawFour(Player player, int card)
	{
		card = 4;
		System.out.println(player.playerName + " draws " + card + "\n");
		drawCard(4, player.playerDeck);
	}

	public static void quitGame()
	{
		System.out.print("Thank you for playing Uno. Goodbye!");
		System.exit(0);
	}

	public static void main(String[] args)
	{
		System.out.println("Welcome to Uno!");
		gameStart();
		createTopCard();
		playerTurn(newPlayerA, 0);
	}
}
