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
	 * A Card has-a cardColor - 0: Red, 1: Blue, 2: Yellow, 3: Green, 4: Black
	 */
	public int cardColor;
	/*
	 * A Card has-a cardType - 0-9: Number Card (0-9), 10: Skip Card, 11:
	 * Reverse Card, 12: Draw Four Card
	 */
	public int cardType;
	// A Card has-an owner
	public Player cardOwner;
	// Initialization of Random object for utilization by class methods
	private Random random = new Random();

	/**
	 * Purpose: Class constructor, initializes cardColor and cardType as 0 (Red
	 * 0)
	 */
	Card()
	{
		cardOwner = null;
		cardColor = 0;
		cardType = 0;

	}
	
	Card(Player owner, int color, int type)
	{
		cardOwner = owner;
		cardColor = color;
		cardType = type;
	}

	/**
	 * Purpose: Creates and returns new Card object with randomized variables
	 *
	 * @return random Card object
	 */
	public Card createCard()
	{

		Card newCard = new Card();
		int cardTypeWeightedChance = random.nextInt(101);
		
		if (cardTypeWeightedChance < 86)
		{
			newCard.cardType = random.nextInt(10) ;
		}
		else
		{
			newCard.cardType = random.nextInt(3) + 10;
		}
		
		switch (newCard.cardType)
		{
			case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 ->
			newCard.cardColor = random.nextInt(4);

			
			case 12 ->
			newCard.cardColor = 4;

		}

		return newCard;

	}

	public String getCardTypeAndColor()
	{
		return (cardColorToString() + " " + cardTypeToString());
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
				return cardColorString;
			case 1:
				cardColorString = "Blue";
				return cardColorString;
			case 2:
				cardColorString = "Yellow";
				return cardColorString;
			case 3:
				cardColorString = "Green";
				return cardColorString;
			case 4:
				cardColorString = "Black";
				return cardColorString;

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
				return cardTypeString;
			case 1:
				cardTypeString = "1";
				return cardTypeString;
			case 2:
				cardTypeString = "2";
				return cardTypeString;
			case 3:
				cardTypeString = "3";
				return cardTypeString;
			case 4:
				cardTypeString = "4";
				return cardTypeString;
			case 5:
				cardTypeString = "5";
				return cardTypeString;
			case 6:
				cardTypeString = "6";
				return cardTypeString;
			case 7:
				cardTypeString = "7";
				return cardTypeString;
			case 8:
				cardTypeString = "8";
				return cardTypeString;
			case 9:
				cardTypeString = "9";
				return cardTypeString;
			case 10:
				cardTypeString = "Skip";
				return cardTypeString;
			case 11:
				cardTypeString = "Reverse";
				return cardTypeString;
			case 12:
				cardTypeString = "4+";
				return cardTypeString;

		}

		return cardTypeString;

	}

}