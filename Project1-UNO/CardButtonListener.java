import java.awt.event.*;
import java.awt.Dimension;

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
 *         Responsibilities of class: Constructor and methods for the CardButtonListener class
 */

public class CardButtonListener implements MouseListener
{

	CardButton cardButton;
	Game unoModel;
	UnoGUI unoView;
	Card unoCard;
	
	public CardButtonListener(CardButton button, Game model, UnoGUI view)
	{
		cardButton = button;
		unoModel = model;
		unoView = view;
		unoCard = cardButton.card;
		
		
	}
	
	public void mouseClicked(MouseEvent e)
	{
		if (unoModel.checkCardValidity(unoCard) == true)
		{
			unoModel.playCard(unoCard, unoCard.cardOwner);
			
			switch (unoCard.cardType)
			{
				case 10:
					unoView.gameStatusTracker.setText(unoModel.getCurrentPlayer() + "'s turn was skipped!");
				case 11:
					unoView.gameStatusTracker.setText("The direction of the game has been reversed!");
				case 12:
					unoView.gameStatusTracker.setText(unoModel.getCurrentPlayer() + " was forced to draw 4 cards!");
				default:
					unoView.gameStatusTracker.setText("Played a " + unoCard.getCardTypeAndColor());
			}
			unoView.updateGUI();
		}
		if (unoModel.checkCardValidity(unoCard) == false)
		{
			unoView.gameStatusTracker.setText("This card cannot be played. Please select a different card.");
			unoView.updateGUI();
		}
	}
	
	public void mouseEntered(MouseEvent e)
	{
		cardButton.setSize(new Dimension(130,140));
	}
	
	public void mouseExited(MouseEvent e)
	{
		cardButton.setSize(unoView.BUTTON_SIZE);
	}
	
	public void mousePressed (MouseEvent e)
	{
	}
	public void mouseReleased(MouseEvent e)
	{
	}
	
}
