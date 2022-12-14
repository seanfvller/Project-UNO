import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

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
 *         Responsibilities of class: Constructor and methods for UnoGUI class
 */

public class UnoGUI extends JFrame
{
	// A UnoGUI has-a menu size
	private final Dimension WINDOW_SIZE = new Dimension(1200, 800);
	private final Dimension GAME_INITIALIZER_SIZE = new Dimension(300,110);
	// Initialization of Game for utilization by class methods
	private Game UnoModel;
	// Initialization of UnoGUI for utilization by class methods
	private UnoGUI UnoView;

	// window to add player name
	// private Player addPlayers = new Player();
	ArrayList<String> temp = new ArrayList<>();

	// fields for the user interface
	private JPanel windowPanel;
	private JPanel playerPanel;
	private JDialog gameInitializer;
	private ArrayList<JButton> cardButton = new ArrayList<JButton>();
	ArrayList<String> cardMatch;

	private JButton drawButton;
	// private JButton currentCard;
	// private JButton UnoButton;
	public JLabel gameStatusTracker;
	private JLabel[] playerName;
	private JLabel[] lNames;
	//
	// private JTextField numberPlayer;
	// private JTextField nameInput;

	/**
	 * Purpose: Class constructor
	 */
	public UnoGUI(Game model)
	{
		setTitle("UNO");
		setSize(WINDOW_SIZE);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		UnoModel = model;
		UnoView = this;

		windowPanel = new JPanel();
		windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));

		JScrollPane scrollPane = new JScrollPane(windowPanel);
		scrollPane.setHorizontalScrollBarPolicy(
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setVerticalScrollBarPolicy(
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		////draw button
		//actionPerformed draw = new actionPerformed();
		//drawButton.addActionListener(draw);
		//drawButton.setBounds(400, 400, 200, 120);
		//drawButton.setBackground(Color.YELLOW);
		//drawButton.setText("DRAW");
		//windowPanel.add(drawButton);

		// createCardLayout();

		// initializeDisplayPanel ();
		// initializeplayerPanel ();

		setVisible(true);

	}
	
	/**
	 * Purpose: Creates a window for the user to specify number of Players, then
	 * has user specify each Players' name
	 */
	public void createGameInitializer()
	{
		UnoGUI unoView = this;
		JDialog gameInitializer = new JDialog(unoView, "Initializing game...");
		gameInitializer.setSize(GAME_INITIALIZER_SIZE);
		gameInitializer.setLocationRelativeTo(unoView);
		gameInitializer.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(EXIT_ON_CLOSE);
			}
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JTextField textField = new JTextField(16);
		textField.setBorder(BorderFactory.createTitledBorder("Please enter number of players:"));
		JButton button = new JButton("Confirm");
		
		class PlayerNameListener implements ActionListener
		{
			int playerNumber = 1;
			
			public void actionPerformed(ActionEvent e)
			{
				if (playerNumber < UnoModel.getNumberOfPlayers())
				{
				Player newPlayer = new Player(playerNumber);
				newPlayer.setPlayerName(textField.getText());
				newPlayer.setPlayerNumber(playerNumber);
				UnoModel.addPlayer(newPlayer, playerNumber);
				System.out.println("Player " + playerNumber + "'s name has been set.");
				playerNumber = playerNumber + 1;
				textField.setText("");
				textField.setBorder(BorderFactory.createTitledBorder("Please enter name for Player " + playerNumber + ": "));
				}
				else
				{
				Player newPlayer = new Player(playerNumber);
				newPlayer.setPlayerName(textField.getText());
				newPlayer.setPlayerNumber(playerNumber);
				UnoModel.addPlayer(newPlayer, playerNumber);
				System.out.println("Player " + playerNumber + "'s name has been set.");
				UnoModel.linkPlayerList();
				System.out.println("All players' names have been set.");
				gameInitializer.dispose();
				}
			}
		}
		
		class numberOfPlayersListener implements ActionListener 
		{
			public void actionPerformed(ActionEvent e)
			{
				int numberOfPlayers;
				try {
				numberOfPlayers = Integer.parseInt(textField.getText());
				if (numberOfPlayers < 2)
				{
					System.out.println("Must have at least 2 players.");
				}
				else if (numberOfPlayers > 8)
				{
					System.out.println("Cannot have more than 8 players.");
				}
				else
				{
					System.out.println("Set the number of players to " + numberOfPlayers + ".");
					UnoModel.setNumberOfPlayers(numberOfPlayers);
					UnoModel.createPlayerList();
					textField.setBorder(BorderFactory.createTitledBorder("Please enter name for Player 1: "));
					textField.setText("");
					button.removeActionListener(this);
					button.addActionListener(new PlayerNameListener());
				}
				}
				catch (NumberFormatException exception) {
					System.out.println("Please enter a number.");
				}
			}
		}
		
			
		button.addActionListener(new numberOfPlayersListener());
		
		
		

		panel.add(textField);
		panel.add(button);
		textField.setAlignmentX(CENTER_ALIGNMENT);
		button.setAlignmentX(CENTER_ALIGNMENT);
		
		gameInitializer.add(panel);
		gameInitializer.setVisible(true);

	}

	/**
	 * Purpose: Creates and adds a label that communicates game information to
	 * the player (Example: "You cannot play that card!", "You were forced to
	 * draw 4 cards!", "(Player) has won!", etc.)
	 */
	public void createGameStatusTracker()
	{
		JLabel gameStatusTracker = new JLabel("Welcome to UNO!");

		add(gameStatusTracker);
	}

	/*
	 * public void createCardLayout() {
	 * //sets players name and card buttons
	 * playerPanel = new JPanel();
	 * playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
	 * }
	 */

	//
	// class extends JTextField implemts ActionListener
	// {
	//
	// }
	// void initalizedPlayerPanel()
	// {
	// window = new JFrame();
	//
	// playerPanel = new JPanel();
	// playerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
	//
	// playerName = "";
	//
	// }

	/**
	 * Sets the background color of the Jbutton with the corresponding card
	 * color
	 * 
	 * @param cardColor, String color of the card
	 * @param button,    Jbutton
	 *
	 **/

	public void setButtonColor(Card Card, JButton button)
	{
		if (Card.cardColor == 0)
		{
			button.setBackground(Color.RED);
		}
		else if (Card.cardColor == 1)
		{
			button.setBackground(Color.BLUE);
		}
		else if (Card.cardColor == 2)
		{
			button.setBackground(Color.YELLOW);
		}
		else if (Card.cardColor == 3)
		{
			button.setBackground(Color.GREEN);
		}
	}

	/**
	 * set the size and font of the card button
	 * 
	 * @param button, Jbutton
	 *
	 **/
	public void setButtonLayout(JButton button)
	{
		button.setMargin(new Insets(1, 1, 1, 1));
		button.setPreferredSize(new Dimension(80, 100));
		button.setFont(new Font("Arial", Font.BOLD, 40));
	}

	public void updateGUI()
	{
		playerPanel.revalidate();
		playerPanel.repaint();
		windowPanel.revalidate();
		windowPanel.repaint();
	}

	public static void main(String[] args)
	{
		UnoGUI UnoGame = new UnoGUI(new Game());
		UnoGame.createGameInitializer();
	}
}
