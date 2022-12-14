import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

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
 *         Responsibilities of class: Constructor and methods for unoGuI class
 */

@SuppressWarnings("serial")
public class UnoGUI extends JFrame
{
	// Component sizes
	public final Dimension PRIMARY_WINDOW_SIZE = new Dimension(1200, 900);
	public final Dimension SECONDARY_WINDOW_SIZE = new Dimension(300, 150);
	public final Dimension BUTTON_SIZE = new Dimension(100,120);
	// Initialization of Game for utilization by class methods
	private Game unoModel;
	// Initialization of unoGuI for utilization by class methods
	private static UnoGUI unoView;
	// fields for the user interface
	private CardButton topCardButton;
	
	private JButton drawButton;
	private JButton unoButton;
	
	private JDialog gameInitializerWindow;
	private JDialog victoryWindow;
	
	private JLabel cardPanelPlayerName;
	public  JLabel gameStatusTracker;
	
	public JLabel playerName;
	public JPanel actionPanel;
	public JPanel cardPanel;
	public JPanel gameStatusTrackerPanel;
	public JPanel playerPanel;
	public JPanel topCardPanel;
	public JPanel windowPanel;
	
	private static JPanel[] cardPanelArray;


	/**
	 * Purpose: Class constructor
	 */
	public UnoGUI(Game model)
	{
		setTitle("UNO");
		setSize(PRIMARY_WINDOW_SIZE);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		unoModel = model;
		unoView = this;

		windowPanel = new JPanel();
		windowPanel.setSize(PRIMARY_WINDOW_SIZE);
		windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
		windowPanel.setBackground(Color.DARK_GRAY);
		
		add(windowPanel);

		createGameInitializerWindow();
		
		setEnabled(false);
		setVisible(true);

	}

	/**
	 * Purpose: Creates a window for the user to specify number of Players, then
	 * has user specify each Players' name
	 */
	public void createGameInitializerWindow()
	{

		gameInitializerWindow = new JDialog(unoView, "Initializing game...");
		gameInitializerWindow.setSize(SECONDARY_WINDOW_SIZE);
		gameInitializerWindow.setLocationRelativeTo(unoView);
		gameInitializerWindow.setResizable(false);
		gameInitializerWindow.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(EXIT_ON_CLOSE);
			}
		});

		JPanel panel = new JPanel();
		JLabel label = new JLabel(" ");
		JTextField textField = new JTextField(16);
		JButton button = new JButton("Confirm");

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		textField.setBorder(BorderFactory
				.createTitledBorder("Please enter number of players:"));

		class PlayerNameListener implements ActionListener
		{
			ArrayList<String> nameList = new ArrayList<String>();
			int playerNumber = 1;

			public void actionPerformed(ActionEvent e)
			{
				if (playerNumber < unoModel.getNumberOfPlayers())
				{
					if (textField.getText().isEmpty() == true)
					{
						label.setText("Name cannot be blank.");
					}
					else if (nameList.contains(textField.getText()))
					{
						label.setText("Another player is using that name.");
					}
					else
					{
						label.setText(" ");
						nameList.add(textField.getText());
						unoModel.addPlayer(textField.getText(), playerNumber);
						playerNumber = playerNumber + 1;
						textField.setText("");
						textField.setBorder(BorderFactory.createTitledBorder("Please enter name for Player " + playerNumber + ": "));
					}
				}
				else
				{
					if (textField.getText().isEmpty() == true)
					{
						label.setText("Name cannot be blank.");
					}
					else if (nameList.contains(textField.getText()))
					{
						label.setText("Another player is using that name.");
					}
					else
					{
						unoModel.addPlayer(textField.getText(), playerNumber);
						unoModel.linkPlayerList();
						unoModel.createTopCard();
						unoView.setEnabled(true);
						initializeGUI();
						gameInitializerWindow.dispose();
					}
				}
			}
		}

		class numberOfPlayersListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				int numberOfPlayers;
				try
				{
					numberOfPlayers = Integer.parseInt(textField.getText());
					if (numberOfPlayers < 2)
					{
						label.setText("Must have at least 2 players.");
					}
					else if (numberOfPlayers > 8)
					{
						label.setText("Cannot have more than 8 players.");
					}
					else
					{
						label.setText(" ");
						unoModel.setNumberOfPlayers(numberOfPlayers);
						unoModel.createPlayerList();
						textField.setBorder(BorderFactory.createTitledBorder(
								"Please enter name for Player 1: "));
						textField.setText("");
						button.removeActionListener(this);
						button.addActionListener(new PlayerNameListener());
					}
				}
				catch (NumberFormatException exception)
				{
					label.setText("Please enter a number.");
				}
			}
		}

		button.addActionListener(new numberOfPlayersListener());
		panel.add(Box.createVerticalStrut(5));
		panel.add(label);
		panel.add(Box.createVerticalStrut(5));
		panel.add(textField);
		panel.add(Box.createVerticalStrut(5));
		panel.add(button);
		panel.add(Box.createVerticalStrut(5));
		label.setAlignmentX(CENTER_ALIGNMENT);
		textField.setAlignmentX(CENTER_ALIGNMENT);
		button.setAlignmentX(CENTER_ALIGNMENT);

		gameInitializerWindow.add(panel);
		gameInitializerWindow.setVisible(true);

	}
	
	public void createVictoryWindow()
	{
		victoryWindow = new JDialog(unoView,"Game Over");
		victoryWindow.setSize(SECONDARY_WINDOW_SIZE);
		victoryWindow.setResizable(false);
		victoryWindow.setLocationRelativeTo(unoView);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		JLabel label = new JLabel(unoModel.getCurrentPlayer().playerName + " has achieved victory!");
		JButton button = new JButton("Close");

		victoryWindow.addWindowListener(new WindowAdapter()
		{
			@Override
			public void windowClosing(WindowEvent e)
			{
				System.exit(EXIT_ON_CLOSE);
			}
		});

		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		
		panel.add(Box.createVerticalStrut(5));
		panel.add(label);
		panel.add(Box.createVerticalStrut(5));
		panel.add(button);
		panel.add(Box.createVerticalStrut(5));
		label.setAlignmentX(CENTER_ALIGNMENT);
		button.setAlignmentX(CENTER_ALIGNMENT);
		
		victoryWindow.add(panel);
		victoryWindow.setVisible(true);
	}
	
	public void createTopCardPanel()
	{
		topCardPanel = new JPanel();
		topCardPanel.setBackground(Color.DARK_GRAY);
		topCardButton = new CardButton(unoModel.getTopCard());
		setButtonLayout(unoModel.getTopCard(),topCardButton);
		topCardButton.removeMouseListener(topCardButton.getMouseListeners()[0]);
		topCardPanel.add(topCardButton);
		windowPanel.add(topCardPanel);
	}
	

	/**
	 * Purpose: Creates and adds a label that communicates game information to
	 * the player (Example: "You cannot play that card!", "You were forced to
	 * draw 4 cards!", "(Player) has won!", etc.)
	 */
	public void createGameStatusTracker()
	{
//		gameStatusTrackerPanel
		gameStatusTracker = new JLabel("Welcome to UNO!");
		gameStatusTracker.setFont(new Font("Helvetica", Font.BOLD, 20));
		gameStatusTracker.setForeground(Color.WHITE);
		gameStatusTracker.setBackground(Color.DARK_GRAY);
		windowPanel.add(gameStatusTracker);
	}
	
	public void createActionPanel()
	{
		actionPanel = new JPanel();
		actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER,30,0));
		actionPanel.setBackground(Color.DARK_GRAY);
		
		drawButton = new JButton("Draw");
		drawButton.setPreferredSize(new Dimension(175,75));
		drawButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		drawButton.setText("Draw");
		drawButton.setFont(new Font("Helvetica", Font.BOLD, 18));
		
		class drawButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				unoModel.drawCard(1, unoModel.getCurrentPlayer());
				gameStatusTracker.setText("You drew a " + unoModel.getCardDrawn().getCardTypeAndColor());
				updateGUI();
			}
		}

		drawButton.addActionListener(new drawButtonListener());
		
		unoButton = new JButton("UNO!");
		unoButton.setPreferredSize(new Dimension(175,75));
		unoButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		unoButton.setText("UNO!");
		unoButton.setFont(new Font("Helvetica", Font.BOLD, 18));

		class unoButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				if (unoModel.getCurrentPlayer().playerDeck.size() != 1)
				{
					gameStatusTracker.setText("You can only call UNO! when there is 1 card left in your deck.");
				}
				else if (unoModel.checkCardValidity(unoModel.getCurrentPlayer().playerDeck.get(0)) != true)
				{
					gameStatusTracker.setText("Your can only call UNO! if your final card is playable.");
				}
				else
				{
					gameStatusTracker.setText("UNO!");
					unoView.setEnabled(false);
					unoView.createVictoryWindow();
				}
			}
		}
		
		unoButton.addActionListener(new unoButtonListener());
		
		actionPanel.add(drawButton);
		actionPanel.add(unoButton);
		
		actionPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		windowPanel.add(actionPanel);
	}

	/*
	 * Purpose: Creates arraylist of Jbutton for users deck
	 */

	public void createPlayerPanel()
	{
		cardPanelArray = new JPanel[unoModel.getNumberOfPlayers()];
		
		playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel,BoxLayout.Y_AXIS));
		playerPanel.setBackground(Color.DARK_GRAY);

		for (int i = 0; i < unoModel.getNumberOfPlayers(); i++)
		{
			cardPanel = new JPanel();
			cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
			cardPanel.setBackground(Color.DARK_GRAY);
			cardPanel.setForeground(Color.WHITE);
			cardPanel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.LOWERED), unoModel.getPlayer(i + 1).playerName, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", Font.BOLD, 30), Color.WHITE));
			for (int j = 0; j < unoModel.getPlayer(i + 1).playerDeck.size(); j++)
			{
				CardButton cardButton = new CardButton(unoModel.getPlayer(i + 1).playerDeck.get(j));
				cardButton.addMouseListener(new CardButtonListener(cardButton, unoModel, unoView));
				cardPanel.add(cardButton);
				setButtonLayout(unoModel.getPlayer(i + 1).playerDeck.get(j), cardButton);
			}
			cardPanelArray[i] = cardPanel;
			playerPanel.add(cardPanel);
		}
		
		for (int i = 1; i < cardPanelArray.length; i++)
		{
			
			  for (int j = 0; j < cardPanelArray[i].getComponents().length; j++)
			  {
				CardButton temp = (CardButton) cardPanelArray[i].getComponents()[j];
				temp.setBackground(Color.GRAY);
				temp.setFont(new Font("Impact", Font.BOLD, 30));
				temp.setText("UNO");
				temp.removeMouseListener(temp.getMouseListeners()[1]);
				temp.removeMouseListener(temp.getMouseListeners()[0]);
			  }
		}
		
		JScrollPane scrollablePlayerPanel = new JScrollPane(playerPanel);
		scrollablePlayerPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollablePlayerPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		scrollablePlayerPanel.setAlignmentX(CENTER_ALIGNMENT);
		windowPanel.add(scrollablePlayerPanel);

	}
	
	
	
	/**
	 * Sets the background color of the Jbutton with the corresponding card
	 * color
	 * 
	 * @param cardColor, String color of the card
	 * @param button,    Jbutton
	 *
	 **/

	public void setButtonLayout(Card card, CardButton cardButton)
	{
		cardButton.setMargin(new Insets(1, 1, 1, 1));
		cardButton.setPreferredSize(BUTTON_SIZE);
		cardButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 6));
		cardButton.setForeground(Color.WHITE);
		
		switch(card.cardType)
		{
			case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 12 -> 
			{
				cardButton.setText(card.cardTypeToString());
				cardButton.setFont(new Font("Impact", Font.BOLD, 50));
			}
			
			case 10, 11 ->
			{
				cardButton.setText(card.cardTypeToString());
				cardButton.setFont(new Font("Impact", Font.PLAIN, 20));
			}
		}
		
		switch (card.cardColor)
		{
			case 0 ->
			cardButton.setBackground(Color.RED);
			
			case 1 ->
			cardButton.setBackground(Color.BLUE);
			
			case 2 ->
			cardButton.setBackground(Color.YELLOW);
			
			case 3 ->
			cardButton.setBackground(Color.GREEN);
			
			case 4 ->
			cardButton.setBackground(Color.BLACK);
		}
	}
	
	public void initializeGUI()
	{
		windowPanel.add(Box.createVerticalStrut(20));
		createGameStatusTracker();
		windowPanel.add(Box.createVerticalStrut(30));
		createTopCardPanel();
		windowPanel.add(Box.createVerticalStrut(20));
		createActionPanel();
		windowPanel.add(Box.createVerticalStrut(20));
		createPlayerPanel();
		
		gameStatusTracker.setAlignmentX(CENTER_ALIGNMENT);
		topCardPanel.setAlignmentX(CENTER_ALIGNMENT);
		actionPanel.setAlignmentX(CENTER_ALIGNMENT);
		playerPanel.setAlignmentX(CENTER_ALIGNMENT);
		
		gameStatusTracker.revalidate();
		gameStatusTracker.repaint();
		topCardPanel.revalidate();
		topCardPanel.repaint();
		playerPanel.revalidate();
		playerPanel.repaint();
	}
	


	public void updateGUI()
	{
		remove(gameStatusTracker);
		gameStatusTracker.revalidate();
		gameStatusTracker.repaint();
		updateTopCardPanel();
		updatePlayerPanel();
		windowPanel.revalidate();
		windowPanel.repaint();
	}
	
	public void updatePlayerPanel()
	{
		playerPanel.removeAll();
		cardPanel = cardPanelArray[unoModel.getCurrentPlayer().playerNumber - 1];
		cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cardPanel.setBackground(Color.DARK_GRAY);
		cardPanel.setForeground(Color.WHITE);
		cardPanel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.LOWERED), unoModel.getCurrentPlayer().playerName, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", Font.BOLD, 30), Color.WHITE));
		for (int i = 0; i < unoModel.getCurrentPlayer().playerDeck.size(); i++)
		{
			CardButton cardButton = new CardButton(unoModel.getCurrentPlayer().playerDeck.get(i));
			cardButton.addMouseListener(new CardButtonListener(cardButton, unoModel, unoView));
			cardPanel.add(cardButton);
			setButtonLayout(unoModel.getCurrentPlayer().playerDeck.get(i), cardButton);
		}
		playerPanel.add(cardPanel);
		
		Player tempCurrentPlayer = unoModel.getCurrentPlayer();
		Player tempPlayer = tempCurrentPlayer.nextPlayer;
		
		while (tempPlayer.nextPlayer != tempCurrentPlayer)
		{
			cardPanel = cardPanelArray[tempPlayer.playerNumber - 1];
			cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
			cardPanel.setBackground(Color.DARK_GRAY);
			cardPanel.setForeground(Color.WHITE);
			cardPanel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.LOWERED), unoModel.getCurrentPlayer().playerName, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", Font.BOLD, 30), Color.WHITE));
			for (int j = 0; j < tempPlayer.playerDeck.size(); j++)
			{
				CardButton cardButton = new CardButton(tempPlayer.playerDeck.get(j));
				cardButton.addMouseListener(new CardButtonListener(cardButton, unoModel, unoView));
				cardPanel.add(cardButton);
				setButtonLayout(tempPlayer.playerDeck.get(j), cardButton);
			}
		}
		cardPanel = cardPanelArray[tempPlayer.playerNumber - 1];
		cardPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));
		cardPanel.setBackground(Color.DARK_GRAY);
		cardPanel.setForeground(Color.WHITE);
		cardPanel.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.LOWERED), unoModel.getCurrentPlayer().playerName, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", Font.BOLD, 30), Color.WHITE));
		for (int j = 0; j < tempPlayer.playerDeck.size(); j++)
		{
			CardButton cardButton = new CardButton(tempPlayer.playerDeck.get(j));
			cardButton.addMouseListener(new CardButtonListener(cardButton, unoModel, unoView));
			cardPanel.add(cardButton);
			setButtonLayout(tempPlayer.playerDeck.get(j), cardButton);
		}
		
		
		playerPanel.revalidate();
		playerPanel.repaint();
	}
	
	public void updateTopCardPanel()
	{
		topCardPanel.removeAll();
		topCardButton = new CardButton(unoModel.getTopCard());
		setButtonLayout(unoModel.getTopCard(),topCardButton);
		topCardButton.setEnabled(false);
		topCardPanel.add(topCardButton);
		topCardPanel.revalidate();
		topCardPanel.repaint();
		
	}

	public static void main(String[] args)
	{
		unoView = new UnoGUI(new Game());
	}
}
