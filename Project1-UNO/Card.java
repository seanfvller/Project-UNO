
/**
 * @author Sean Fuller, Tristen Tran
 *
 */
import java.util.*;

public class Card
{
	// Card Variables
	int cardColor; // 0: Red, 1: Blue, 2: Yellow, 3: Green
	int cardType; // 0-9: Number Card (0-9), 10: Skip Card, 11: Reverse Card,
					// 12: Draw Four Card

	/*
	 * Card Type Explanations:
	 * Number Card (0-9): A basic card that can be played if its number OR color
	 * matches that of the card on the field.
	 * Skip Card: An action card that can be played if its color matches that of
	 * the card on the field.
	 * Upon playing this card, it will skip the next player's turn.
	 * Reverse Card: An action card that can be played if its color matches that
	 * of the card on the field.
	 * Upon playing this card, it will reverse the direction of the flow of the
	 * game turns.
	 * Draw Four Card: An action card that can be played regardless of the
	 * number or color of the card on the field.
	 * Upon playing this card, the next player must draw four cards into their
	 * deck.
	 */

	// Card Constructor
	Card()
	{
		cardColor = 0;
		cardType = 0;
	}

	// Method to create and return new Card.class object with randomized
	// variables.
	public Card createCard()
	{
		Card newCard = new Card();
		Random random = new Random();
		cardType = random.nextInt(13);
		cardColor = random.nextInt(4);
		return newCard;
	}

	// Method to return int cardColor variable as a printable String
	public String cardColorToString()
	{
		String cardColorString = null;

		if (this.cardColor == 0)
		{
			cardColorString = "Red";
		}
		else if (this.cardColor == 1)
		{
			cardColorString = "Blue";
		}
		else if (this.cardColor == 2)
		{
			cardColorString = "Yellow";
		}
		else if (this.cardColor == 3)
		{
			cardColorString = "Green";
		}

		return cardColorString;
	}

	// Method to return int cardType variable as a printable String
	public String cardTypeToString()
	{
		String cardTypeString = null;

		if (this.cardType == 0)
		{
			cardTypeString = "0";
		}
		else if (this.cardType == 1)
		{
			cardTypeString = "1";
		}
		else if (this.cardType == 2)
		{
			cardTypeString = "2";
		}
		else if (this.cardType == 3)
		{
			cardTypeString = "3";
		}
		else if (this.cardType == 4)
		{
			cardTypeString = "4";
		}
		else if (this.cardType == 5)
		{
			cardTypeString = "5";
		}
		else if (this.cardType == 6)
		{
			cardTypeString = "6";
		}
		else if (this.cardType == 7)
		{
			cardTypeString = "7";
		}
		else if (this.cardType == 8)
		{
			cardTypeString = "8";
		}
		else if (this.cardType == 9)
		{
			cardTypeString = "9";
		}
		else if (this.cardType == 10)
		{
			cardTypeString = "Skip";
		}
		else if (this.cardType == 11)
		{
			cardTypeString = "Reverse";
		}
		else if (this.cardType == 12)
		{
			cardTypeString = "Draw Four";
		}

		return cardTypeString;
	}

	public static void main(String[] args)
	{
	}
}
