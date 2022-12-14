import javax.swing.*;

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

	Card card;
	
	public CardButton(Card unoCard)
	{
		card = unoCard;
	}

}
