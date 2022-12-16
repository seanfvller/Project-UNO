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
	
	// Initialization of CardButton for utilization by class methods
	CardButton cardButton;
	// Initialization of UnoGame for utilization by class methods
	UnoGame unoModel;
	// Initialization of UnoGUI for utilization by class methods
	UnoGUI unoView;
	// Initialization of Card for utilization by class methods
	Card unoCard;
	
	/**
	 * Purpose: Class constructor specifying the CardButtonListener's
	 * CardButton, UnoGame, and UnoGUI. The CardButtonListener's Card is set to
	 * the CardButton's Card.
	 * 
	 * @param button specified CardButton
	 * @param model  specified UnoGame
	 * @param view   specified UnoGUI
	 */
	public CardButtonListener(CardButton button, UnoGame model, UnoGUI view)
	{
		
		cardButton = button;
		unoModel = model;
		unoView = view;
		unoCard = cardButton.card;
		
	}
	
	/**
	 * Purpose: Determines the behavior of the CardButton when it is clicked by
	 * the Player. Upon clicking a Card, it is checked for playability; if it
	 * passes, the Card is played according to the game rules. If it does not
	 * pass, nothing happens, and the Player is advised to make a different
	 * choice.
	 */
	public void mousePressed(MouseEvent e)
	{
		if (unoModel.checkCardValidity(unoCard) == true)
		{

			unoModel.playCard(unoCard, unoCard.cardOwner);
			
			if (unoCard.cardOwner.playerDeck.size() == 0)
			{
				
				unoView.gameStatusTracker.setText(unoCard.cardOwner.playerName + " forgot to call UNO! Their turn has been skipped, and they have been forced to draw 2 cards!");
				unoModel.drawCard(2, unoCard.cardOwner);
				unoModel.setCurrentPlayer(unoModel.nextTurn(unoCard.cardOwner));
				unoView.updateGUI();
				
			}
			
			else
			{
				
				switch (unoCard.cardType)
				{
					
					case 0,1,2,3,4,5,6,7,8,9 ->
					{
						
						unoView.gameStatusTracker.setText(unoCard.cardOwner.playerName + " played a " + unoCard.getCardColorAndTypeAsString() + ".");
						unoView.updateGUI();
						
					}
					
					case 10 ->
					{
						
						unoView.gameStatusTracker.setText(unoCard.cardOwner.playerName + " skipped " + unoModel.nextTurn(unoCard.cardOwner).playerName + "'s turn!");
						unoView.updateGUI();
						
					}
					
					case 11 ->
					{
						
						unoView.gameStatusTracker.setText(unoCard.cardOwner.playerName + " reversed the direction of the game!");
						unoView.updateGUI();
						
					}
					
					case 12 ->
					{
						
						unoView.gameStatusTracker.setText(unoCard.cardOwner.playerName + " is playing a wild card!");
						unoView.updateGUI();
					}
					
					case 13 ->
					{
						
						unoView.gameStatusTracker.setText(unoCard.cardOwner.playerName + " forced " + unoModel.getCurrentPlayer().playerName + " to draw 4 cards!");
						unoView.updateGUI();
						
					}
					
				}
			
			}
			
			unoView.helpLevel = 0;
			unoView.helpLevelTracker.setText("Help is available in " + Integer.toString(6 - unoView.helpLevel) + " draws.");
			
		}
		
		if (unoModel.checkCardValidity(unoCard) == false)
		{
			
			unoView.gameStatusTracker.setText("This card cannot be played. Please select a different card.");
			
		}
		
	}

	/**
	 * Purpose: Increases the size of the CardButton when the cursor hovers over
	 * it for better clarity.
	 */
	public void mouseEntered(MouseEvent e)
	{
		
		cardButton.setSize(new Dimension(110,132));
		cardButton.setLocation(cardButton.getLocation().x - 5,cardButton.getLocation().y - 6);
		
	}
	
	/**
	 * Purpose: Reverts the size of the CardButton to its original size after
	 * the cursor is no longer hovering over it.
	 */
	public void mouseExited(MouseEvent e)
	{
		
		cardButton.setSize(unoView.BUTTON_SIZE);
		cardButton.setLocation(cardButton.getLocation().x + 5,cardButton.getLocation().y + 6);
		
	}
	
	/**
	 * Purpose: Unimplemented MouseListener behavior.
	 */
	public void mouseClicked (MouseEvent e)
	{
	}
	
	/**
	 * Purpose: Unimplemented MouseListener behavior.
	 */
	public void mouseReleased(MouseEvent e)
	{
	}
	
}
