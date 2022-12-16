import javax.swing.JButton;

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
 *         Responsibilities of class: Constructor and methods for the CardButton class
 */

@SuppressWarnings("serial")
public class CardButton extends JButton
{
	
	//Initializes the Card field for utilization by class methods.
	Card card;
	
	/**
	 * Purpose: Class constructor specifying the CardButton's Card.
	 * 
	 * @param unoCard specified Card for CardButton
	 */
	public CardButton(Card unoCard)
	{
		
		card = unoCard;
		
	}
	
}
