import java.util.Random;

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
 *         color matches that of the card on the field.
 *         
 *         Skip Card: An action card that can be played if its color matches
 *         that of the card on the field. Upon playing this card, it will 
 *         skip the next player's turn.
 *         
 *         Reverse Card: An action card that can be played if its color matches
 *         that of the card on the field.Upon playing this card, it will reverse 
 *         the direction of the flow of the game turns.
 *         
 *         Wild Card: An action card that can be played regardless of the number
 *         of color of the card on the field. Upon playing this card, the Player
 *         may choose its color and number before it is set as the top card.
 *         
 *         Draw Four Card: An action card that can be played regardless of the
 *         number or color of the card on the field. Upon playing this card, 
 *         the next player must draw four cards into their deck.
 */

public class Card
{

	//A Card has-a color
	public int cardColor;
	// A Card has-a type
	public int cardType;
	// A Card has-an owner
	public Player cardOwner;
	// Initialization of Random object for utilization by class methods
	private static Random random = new Random();

	/**
	 * Purpose: Class constructor without any arguments; intializes cardOwner as
	 * null and cardColor and cardType as 0.
	 */
	Card()
	{
		
		cardOwner = null;
		cardColor = 0;
		cardType = 0;
		
	}
	
	/**
	 * Purpose: Class constructor that specifies the Card's cardOwner, and
	 * initializes the cardColor and cardType as 0.
	 * 
	 * @param owner specified Player that owns the Card
	 */
	Card(Player owner)
	{
		
		cardOwner = owner;
		cardColor = 0;
		cardType = 0;
		
	}
	
	/**
	 * Purpose: Class constructor that specifies the Card's cardOwner,
	 * cardColor, and cardType.
	 * 
	 * @param owner specified Player that owns the Card
	 * @param color specified color of the Card
	 * @param type  specified type of the card
	 */
	Card(Player owner, int color, int type)
	{
		
		cardOwner = owner;
		cardColor = color;
		cardType = type;
		
	}

	/**
	 * Purpose: Creates and returns new Card object with randomized variables.
	 * The chance that a Card will be a particular cardType is weighted, based
	 * off of the following probabilities:
	 * 
	 * Draw Four: 5% chance
	 * Wild, Reverse, or Skip Card: 20% chance
	 * Number Card: 80% chance
	 * 
	 * Draw Four and Wild Cards are guaranteed to be black cards. All other card
	 * types have a 25% chance of being any particular color.
	 *
	 * @return random Card object
	 */
	public static Card createCard()
	{

		Card newCard = new Card();
		int cardTypeWeightedChance = random.nextInt(101);
		
		if (cardTypeWeightedChance > 94)
		{
			
			newCard.cardType = 13;
			
		}
		
		else if (cardTypeWeightedChance > 80)
		{
			
			newCard.cardType = random.nextInt(3) + 10;
			
		}
		
		else
		{
			
			newCard.cardType = random.nextInt(10);
			
		}
		
		switch (newCard.cardType)
		{
			
			case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 -> newCard.cardColor = random.nextInt(4);
			case 12, 13 -> newCard.cardColor = 4;
			
		}

		return newCard;

	}

	/**
	 * Purpose: Returns the Card's cardColor and cardType as a String.
	 * 
	 * @return the Card's color and type
	 */
	public String getCardColorAndTypeAsString()
	{
		
		return (cardColorToString() + " " + cardTypeToString());
		
	}
	
	/**
	 * Purpose: Gets the Card's color and returns it as a string.
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
	 * Purpose: Gets the Card's type and returns it as a string.
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
				cardTypeString = "Wild";
				return cardTypeString;
			case 13:
				cardTypeString = "4+";
				return cardTypeString;
		}

		return cardTypeString;

	}

}