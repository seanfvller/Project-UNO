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
 *         Responsibilities of class: Constructor and methods for Card class
 * 
 *         Card Type Explanations:
 *         Number Card (0-9): A basic card that can be played if its number OR
 *         color
 *         matches that of the card on the field.
 *         Skip Card: An action card that can be played if its color matches
 *         that of
 *         the card on the field.
 *         Upon playing this card, it will skip the next player's turn.
 *         Reverse Card: An action card that can be played if its color matches
 *         that
 *         of the card on the field.
 *         Upon playing this card, it will reverse the direction of the flow of
 *         the
 *         game turns.
 *         Draw Four Card: An action card that can be played regardless of the
 *         number or color of the card on the field.
 *         Upon playing this card, the next player must draw four cards into
 *         their
 *         deck.
 */

public class Card
{

	/*
	 * A Card has-a cardColor - 0: Red, 1: Blue, 2: Yellow, 3: Green
	 */
	public int cardColor;
	/*
	 * A Card has-a cardType - 0-9: Number Card (0-9), 10: Skip Card, 11:
	 * Reverse Card,12: Draw Four Card
	 */
	public int cardType;
	// Initialization of Random object for utilization by class methods
	private Random random = new Random();
	// Initialization of Card object for utilization by class methods
	private Card card;

	/**
	 * Purpose: Class constructor, initializes cardColor and cardType as 0 (Red
	 * 0)
	 */
	Card()
	{

		cardColor = 0;
		cardType = 0;

	}

	/**
	 * Purpose: Creates and returns new Card object with randomized variables
	 *
	 * @return random Card object
	 */
	public Card createCard()
	{

		card = new Card();
		cardType = random.nextInt(13);
		cardColor = random.nextInt(4);

		return card;

	}

	/**
	 * Purpose: Gets the Card's color and returns it as a string
	 * 
	 * @return the Card's color as a string
	 */
	public String cardColorToString()
	{

		String cardColorString = null;

		switch (this.cardColor)
		{

			case 0:
				cardColorString = "Red";
				break;
			case 1:
				cardColorString = "Blue";
				break;
			case 2:
				cardColorString = "Yellow";
				break;
			case 3:
				cardColorString = "Green";
				break;

		}

		return cardColorString;

	}

	/**
	 * Purpose: Gets the Card's type and returns it as a string
	 * 
	 * @return the Card's type as a string
	 */
	public String cardTypeToString()
	{

		String cardTypeString = null;

		switch (this.cardType)
		{

			case 0:
				cardTypeString = "0";
				break;
			case 1:
				cardTypeString = "1";
				break;
			case 2:
				cardTypeString = "2";
				break;
			case 3:
				cardTypeString = "3";
				break;
			case 4:
				cardTypeString = "4";
				break;
			case 5:
				cardTypeString = "5";
				break;
			case 6:
				cardTypeString = "6";
				break;
			case 7:
				cardTypeString = "7";
				break;
			case 8:
				cardTypeString = "8";
				break;
			case 9:
				cardTypeString = "9";
				break;
			case 10:
				cardTypeString = "Skip";
				break;
			case 11:
				cardTypeString = "Reverse";
				break;
			case 12:
				cardTypeString = "Draw Four";
				break;

		}

		return cardTypeString;

	}

}