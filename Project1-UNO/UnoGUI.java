import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

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
 *         Responsibilities of class: Constructor and methods for unoGUI class
 */

@SuppressWarnings("serial")
public class UnoGUI extends JFrame
{
	
	// Initialization of Dimensions for UnoGUI Component sizes.
	private final Dimension PRIMARY_WINDOW_SIZE = new Dimension(1200, 900);
	private final Dimension SECONDARY_WINDOW_SIZE = new Dimension(300, 150);
	public final Dimension BUTTON_SIZE = new Dimension(100, 120);
	// Initialization of UnoGame for utilization by class methods.
	private UnoGame unoModel;
	// Initialization of UnoGUI for utilization by class methods.
	private UnoGUI unoView;
	// Initialization of JButton components for access and utilization by class methods.
	private CardButton topCardButton;
	private JButton drawButton;
	private JButton helpButton;
	private JButton unoButton;
	private JButton wildCardButton;
	// Initialization of JDialog components for access and utilization by class methods.
	private JDialog gameInitializerWindow;
	private JDialog victoryWindow;
	private JDialog wildCardWindow;
	// Initialization of JLabel components for access and utilization by class methods.
	public JLabel gameStatusTracker;
	public JLabel helpLevelTracker;
	// Initialization of JPanel components for access and utilization by class methods.
	private JPanel actionPanel;
	private JPanel cardPanel;
	private JPanel playerPanel;
	private JPanel topCardPanel;
	private JPanel windowPanel;
	private JPanel[] cardPanelArray;
	private JScrollPane scrollablePlayerPanel;
	// Initialization of helpLevel variable for access and utilization by class methods.
	public int helpLevel = 0;

	/**
	 * Purpose: Class constructor specifying the UnoGUI's UnoGame.
	 */
	public UnoGUI(UnoGame model)
	{
		
		setTitle("UNO");
		setSize(PRIMARY_WINDOW_SIZE);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setEnabled(false);
		setVisible(true);
		
		unoModel = model;
		unoModel.unoView = this;
		unoView = this;

		windowPanel = new JPanel()
		{
			
			protected void paintComponent(Graphics g)
			{
				
				super.paintComponent(g);
				Graphics2D g2d = (Graphics2D) g;
				final int R = 1;
				final int G = 50;
				final int B = 32;
				Paint p = new GradientPaint(0, 0, new Color(R, G, B, 255),
						getWidth(), getHeight(), new Color(R, G, B, 230), true);
				g2d.setPaint(p);
				g2d.fillRect(0, 0, getWidth(), getHeight());
				
			}
			
		};
		windowPanel.setSize(PRIMARY_WINDOW_SIZE);
		windowPanel.setLayout(new BoxLayout(windowPanel, BoxLayout.Y_AXIS));
		add(windowPanel);
		
		createGameInitializerWindow();
		
	}
	
	/**
	 * Purpose: Creates a window for the user to specify number of Players, as
	 * well as each Player's name; afterwards, intiates session by calling
	 * methods from UnoGame to initialize playerDecks and linking the
	 * playerList.
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
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JTextField textField = new JTextField(16);
		textField.setBorder(BorderFactory.createTitledBorder("Please enter number of players:"));

		JButton button = new JButton("Confirm");
		JLabel label = new JLabel(" ");
		
		class textFieldListener implements ActionListener
		{
			public void actionPerformed(ActionEvent e)
			{
				
				button.doClick();
				
			}
			
		}
		
		class PlayerNameListener implements ActionListener
		{
			
			ArrayList<String> nameList = new ArrayList<String>();
			int playerNumber = 1;
			
			public void actionPerformed(ActionEvent e)
			{
				
				if (playerNumber != unoModel.getNumberOfPlayers())
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
						textField.setBorder(BorderFactory.createTitledBorder("Please enter name for Player " + playerNumber+ ": "));
						
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
						textField.setBorder(BorderFactory.createTitledBorder("Please enter name for Player 1: "));
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
		
		textField.addActionListener(new textFieldListener());
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
		
		EventQueue.invokeLater(() -> gameInitializerWindow.requestFocus());
		EventQueue.invokeLater(() -> textField.requestFocusInWindow());
		
	}
	
	/**
	 * Purpose: Creates a window indicating a Player's victory, then closes program with user input.
	 */
	public void createVictoryWindow()
	{
		
		unoView.setEnabled(false);
		
		victoryWindow = new JDialog(unoView, "Game Over!");
		victoryWindow.setSize(SECONDARY_WINDOW_SIZE);
		victoryWindow.setLocationRelativeTo(unoView);
		victoryWindow.setResizable(false);
		victoryWindow.addWindowListener(new WindowAdapter()
		{
			
			@Override
			public void windowClosing(WindowEvent e)
			{
				
				System.exit(EXIT_ON_CLOSE);
				
			}
			
		});

		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JLabel label = new JLabel(unoModel.getCurrentPlayer().playerName + " has achieved victory!");
		label.setFont(new Font("Helvetica", Font.BOLD, 16));
		label.setAlignmentX(CENTER_ALIGNMENT);
		
		JButton button = new JButton("Close");
		button.setFocusPainted(false);
		button.setAlignmentX(CENTER_ALIGNMENT);
		button.addActionListener(new ActionListener()
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				System.exit(0);
				
			}
			
		});
		
		panel.add(Box.createVerticalStrut(20));
		panel.add(label);
		panel.add(Box.createVerticalGlue());
		panel.add(button);
		panel.add(Box.createVerticalStrut(20));
		
		victoryWindow.add(panel);
		victoryWindow.setVisible(true);
		
	}
	
	/**
	 * Purpose: Creates a window upon a Player drawing a Wild Card, that allows them to specify its color and number.
	 */
	public void createWildCardWindow()
	{
		
		Card wildCard = new Card(unoModel.getCurrentPlayer());

		wildCardWindow = new JDialog(unoView, "Wild Card!");
		wildCardWindow.setSize(200, 200);
		wildCardWindow.setLocationRelativeTo(unoView);
		wildCardWindow.setResizable(false);
		wildCardWindow.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		wildCardWindow.addWindowListener(new WindowAdapter()
		{
			
			public void windowClosing(WindowEvent e)
			{
				
				gameStatusTracker.setText(wildCard.cardOwner.playerName + " has decided not to play their wild card.");
				
			}
			
		});
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

		class wildCardTypeListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				JButton typeButton = (JButton) e.getSource();
				switch (typeButton.getText())
				
				{
					case "0" -> wildCard.cardType = 0;
					case "1" -> wildCard.cardType = 1;
					case "2" -> wildCard.cardType = 2;
					case "3" -> wildCard.cardType = 3;
					case "4" -> wildCard.cardType = 4;
					case "5" -> wildCard.cardType = 5;
					case "6" -> wildCard.cardType = 6;
					case "7" -> wildCard.cardType = 7;
					case "8" -> wildCard.cardType = 8;
					case "9" -> wildCard.cardType = 9;
				}
				
				unoModel.setTopCard(wildCard);
				gameStatusTracker.setText(wildCard.cardOwner.playerName + " played a " + wildCard.getCardColorAndTypeAsString() + ".");
				wildCardWindow.dispose();
				updateGUI();
				
			}
			
		}
		
		class wildCardColorListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				JButton colorButton = (JButton) e.getSource();
				
				switch (colorButton.getText())
				{
					
					case "Red" -> wildCard.cardColor = 0;
					case "Blue" -> wildCard.cardColor = 1;
					case "Yellow" -> wildCard.cardColor = 2;
					case "Green" -> wildCard.cardColor = 3;
					
				}

				panel.removeAll();
				
				for (int i = 0; i <= 9; i++)
				{
					
					wildCardButton = new JButton();
					wildCardButton.setText(Integer.toString(i));
					wildCardButton.addActionListener(new wildCardTypeListener());
					
					panel.add(wildCardButton);
					panel.add(Box.createVerticalStrut(10));
					
				}
				
				panel.revalidate();
				panel.repaint();
				
			}
			
		}

		for (int i = 0; i < 4; i++)
		{
			
			wildCardButton = new JButton();
			wildCardButton.setPreferredSize(new Dimension(80, 40));
			wildCardButton.setFont(new Font("Helvetica", Font.BOLD, 12));
			wildCardButton.setForeground(Color.WHITE);
			wildCardButton.setFocusPainted(false);

			switch (i)
			{
				
				case 0 ->
				{
					
					wildCardButton.setText("Red");
					wildCardButton.setBackground(new Color(225, 0, 0));
					
				}
				case 1 ->
				{
					
					wildCardButton.setText("Blue");
					wildCardButton.setBackground(new Color(0, 0, 225));
					
				}
				case 2 ->
				{
					
					wildCardButton.setText("Yellow");
					wildCardButton.setBackground(new Color(225, 225, 0));
					
				}
				case 3 ->
				{
					
					wildCardButton.setText("Green");
					wildCardButton.setBackground(new Color(0, 225, 0));
					
				}
				
			}
			
			wildCardButton.addActionListener(new wildCardColorListener());
			panel.add(wildCardButton);

		}

		wildCardWindow.add(panel);
		wildCardWindow.setVisible(true);
		
	}
	
	/**
	 * Purpose: Creates and adds a label that communicates game information to
	 * the player. (Example: "You cannot play that card!", "You were forced to
	 * draw 4 cards!", "(Player) has won!", etc.)
	 */
	public void createGameStatusTracker()
	{
		
		gameStatusTracker = new JLabel("Welcome to UNO!");
		gameStatusTracker.setForeground(Color.WHITE);
		gameStatusTracker.setFont(new Font("Helvetica", Font.BOLD, 20));
		gameStatusTracker.setAlignmentX(CENTER_ALIGNMENT);
		
		windowPanel.add(gameStatusTracker);
		
	}

	/**
	 * Purpose: Creates and adds a label that communicates the Player's current
	 * helpLevel, indicating how many times they have to draw before they can
	 * receive help.
	 */
	public void createHelpLevelTracker()
	{
		
		helpLevelTracker = new JLabel("Help is available in " + Integer.toString(6 - helpLevel) + " draws.");
		helpLevelTracker.setForeground(Color.WHITE);
		helpLevelTracker.setFont(new Font("Helvetica", Font.BOLD, 20));
		helpLevelTracker.setAlignmentX(CENTER_ALIGNMENT);
		
		windowPanel.add(helpLevelTracker);
		
	}
	
	/**
	 * Purpose: Creates and adds a panel that contains three buttons pertaining
	 * to user interaction with the game logic; a button to draw a card, a
	 * button to call UNO, and a button to receive assistance when warranted.
	 * 
	 * Button Explanations:
	 * 
	 * Draw Button: When clicked, draws one card and increments the current
	 * helpLevel by a value of 1.
	 * 
	 * UNO! Button: When clicked, checks if the Player meets the conditions to
	 * win the game (must have 1 card left in their playerDeck). If the Player
	 * passes the check, the Player is victorious and the session ends. If the
	 * Player does not pass the check, they are punished; their turn is skipped
	 * and 2 cards are drawn to their playerDeck.
	 * 
	 * HELP!!! Button: Starts off disabled at the beginning of each Players'
	 * turn. Upon reaching a helpLevel of 6, the HELP!!! Button is activated;
	 * when clicked, the Player is given a free Wild Card as well as a special
	 * black Skip Card.
	 */
	public void createActionPanel()
	{
		
		actionPanel = new JPanel();
		actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 0));
		actionPanel.setBackground(Color.DARK_GRAY);
		actionPanel.setOpaque(false);
		
		drawButton = new JButton("Draw");
		drawButton.setPreferredSize(new Dimension(175, 75));
		drawButton.setBorder(BorderFactory.createBevelBorder(0));
		drawButton.setFont(new Font("Helvetica", Font.BOLD, 22));
		drawButton.setText("Draw");
		drawButton.setFocusPainted(false);
		
		unoButton = new JButton("UNO!");
		unoButton.setPreferredSize(new Dimension(175, 75));
		unoButton.setBorder(BorderFactory.createBevelBorder(0));
		unoButton.setFont(new Font("Helvetica", Font.BOLD, 22));
		unoButton.setText("UNO!");
		unoButton.setFocusPainted(false);
		
		helpButton = new JButton("HELP!!!");
		helpButton.setPreferredSize(new Dimension(175, 75));
		helpButton.setBorder(BorderFactory.createBevelBorder(0));
		helpButton.setFont(new Font("Helvetica", Font.BOLD, 22));
		helpButton.setFocusPainted(false);
		helpButton.setEnabled(false);
		
		class drawButtonListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				unoModel.drawCard(1, unoModel.getCurrentPlayer());
				gameStatusTracker.setText(unoModel.getCurrentPlayer().playerName + " drew a " + unoModel.getTopCard().getCardColorAndTypeAsString() + ".");
				updateGUI();
				
				switch (helpLevel)
				{
					
					case 0, 1, 2, 3, 4 ->
					{
						
						helpLevel = helpLevel + 1;
						helpLevelTracker.setText("Help is available in " + Integer.toString(6 - helpLevel) + " draws.");
						
					}
					case 5 ->
					{
						
						helpLevel = helpLevel + 1;
						helpLevelTracker.setText("Help is available in " + Integer.toString(6 - helpLevel) + " draw.");
						
					}
					case 6, 7 ->
					{
						
						helpLevel = helpLevel + 1;
						helpLevelTracker.setText("Help is now available!");
						helpButton.setEnabled(true);

						
					}
					case 8, 9, 10 ->
					{
						
						helpLevel = helpLevel + 1;
						helpButton.setFont(new Font("Helvetica", Font.BOLD, 28));
						helpLevelTracker.setText("Help is now available!!!");
						helpButton.setEnabled(true);
						
					}
					case 11 ->
					{
						
						gameStatusTracker.setText(unoModel.getCurrentPlayer().playerName + " keeps drawing more cards; somebody needs to stop them!");
						helpLevelTracker.setText("???????");
						helpLevelTracker.setFont(new Font("Helvetica", Font.BOLD, 28));
						helpButton.setFont(new Font("Helvetica", Font.BOLD, 40));
						helpButton.setEnabled(true);
						
					}
					
				}
				
			}
			
		}
		
		class unoButtonListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				if (unoModel.getCurrentPlayer().playerDeck.size() != 1)
				{
					
					gameStatusTracker.setText(unoModel.getCurrentPlayer().playerName + " called UNO too early! Their turn has been skipped, and they have been forced to draw a card!");
					unoModel.drawCard(1, unoModel.getCurrentPlayer());
					unoModel.setCurrentPlayer(unoModel.nextTurn(unoModel.getCurrentPlayer()));
					updateGUI();
					
				}
				else
				{
					
					gameStatusTracker.setText("UNO!");
					unoView.createVictoryWindow();
					unoView.setEnabled(false);
					
				}
				
			}
			
		}
		
		class helpButtonListener implements ActionListener
		{
			
			public void actionPerformed(ActionEvent e)
			{
				
				unoModel.getCurrentPlayer().playerDeck.add(new Card(unoModel.getCurrentPlayer(), 4, 10));
				unoModel.getCurrentPlayer().playerDeck.add(new Card(unoModel.getCurrentPlayer(), 4, 12));
				gameStatusTracker.setText(unoModel.getCurrentPlayer().playerName + " is on an unlucky streak, and has received some help!");
				helpLevelTracker.setText("A free Wild Card and Skip Card has been added to your deck.");
				helpButton.setFont(new Font("Helvetica", Font.BOLD, 22));
				helpButton.setEnabled(false);
				helpLevel = 0;
				updateGUI();
				
			}
			
		}

		drawButton.addActionListener(new drawButtonListener());
		unoButton.addActionListener(new unoButtonListener());
		helpButton.addActionListener(new helpButtonListener());

		actionPanel.add(drawButton);
		actionPanel.add(unoButton);
		actionPanel.add(helpButton);
		
		windowPanel.add(actionPanel);
		
	}

	/**
	 * Purpose: Creates and adds a panel which contains several cardPanels based
	 * on the unoModel's numberOfPlayers; each Player has their own cardPanel,
	 * which contains all of the Cards in their playerDeck. The currentPlayer's
	 * cardPanel is always drawn at the top, and is the only cardPanel that can
	 * be interacted with. When the currentPlayer clicks on any Card in their
	 * cardPanel, the Card is checked for playability; if the Card passes, it is
	 * played and removed from the Player's playerDeck (and by extension, their
	 * cardPanel). If it does not pass, nothing happens, and the Player is
	 * advised to make a different choice. The cardPanels belonging to the rest
	 * of the Players are drawn in order of turns, based on the current
	 * gameDirection; the Cards in their cardPanels are hidden, and cannot be
	 * interacted with.
	 */
	public void createPlayerPanel()
	{
		
		cardPanelArray = new JPanel[unoModel.getNumberOfPlayers()];
		
		playerPanel = new JPanel();
		playerPanel.setSize(new Dimension(1200, 900));
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));
		playerPanel.setBackground(new Color(1, 50, 32));
			
		for (int i = 0; i < unoModel.getNumberOfPlayers(); i++)
		{
			
			TitledBorder cardPanelNameBorder = new TitledBorder(new LineBorder(new Color(255, 255, 255, 125), 4), unoModel.getPlayer(i + 1).playerName, TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font("Helvetica", Font.BOLD, 26), Color.WHITE);
			cardPanel = new JPanel();
			cardPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 250));
			cardPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 10));
			cardPanel.setBorder(cardPanelNameBorder);
			cardPanel.setBackground(new Color(1, 50, 32));
			cardPanelArray[i] = cardPanel;
			playerPanel.add(cardPanel);
			
		}
		
		for (int i = 0; i < unoModel.getPlayer(1).playerDeck.size(); i++)
		{
			
			CardButton cardButton = new CardButton(unoModel.getPlayer(1).playerDeck.get(i));
			setButtonLayout(unoModel.getPlayer(1).playerDeck.get(i), cardButton, true);
			cardButton.addMouseListener(new CardButtonListener(cardButton, unoModel, unoView));
			cardButton.setFocusPainted(false);
			cardPanelArray[0].add(cardButton);
			
		}
		
		for (int i = 1; i < unoModel.getNumberOfPlayers(); i++)
		{
			
			for (int j = 0; j < unoModel.getPlayer(i + 1).playerDeck.size(); j++)
			{
				
				CardButton cardButton = new CardButton(unoModel.getPlayer(i + 1).playerDeck.get(j));
				setButtonLayout(unoModel.getPlayer(i + 1).playerDeck.get(j), cardButton, false);
				cardButton.removeMouseListener(cardButton.getMouseListeners()[0]);
				cardButton.setFocusPainted(false);
				cardPanelArray[i].add(cardButton);
				
			}
			
		}
		
		playerPanel.add(Box.createVerticalGlue());
		
		scrollablePlayerPanel = new JScrollPane(playerPanel);
		scrollablePlayerPanel.setPreferredSize(new Dimension(1200, 500));
		scrollablePlayerPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollablePlayerPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		windowPanel.add(scrollablePlayerPanel);
		
	}
	
	/**
	 * Purpose: Creates and adds a panel which contains the unoModel's current
	 * topCard.
	 */
	public void createTopCardPanel()
	{
		
		topCardPanel = new JPanel();
		topCardPanel.setPreferredSize(new Dimension(120, 140));
		topCardPanel.setOpaque(false);
		
		topCardButton = new CardButton(unoModel.getTopCard());
		setButtonLayout(unoModel.getTopCard(), topCardButton, true);
		topCardButton.removeMouseListener(topCardButton.getMouseListeners()[0]);
		
		topCardPanel.add(topCardButton);
		
		windowPanel.add(topCardPanel);
		
	}

	/**
	 * Purpose: Initializes the specified CardButton's size and
	 * borders, then changes its color and text based off of its associated
	 * Card's cardColor and cardType. For all players besides the currentPlayer,
	 * all cards are instead anonymized.
	 * 
	 * @param card                   specified Card to access
	 * @param cardButton             specified CardButton to modify
	 * @param topCardorCurrentPlayer boolean that indicates if the Cards belong
	 *                               to the currentPlayer
	 */
	public void setButtonLayout(Card card, CardButton cardButton, boolean topCardorCurrentPlayer)
	{
		
		cardButton.setPreferredSize(BUTTON_SIZE);
		
		if (topCardorCurrentPlayer == true)
		{
			
			cardButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false), BorderFactory.createLineBorder(Color.WHITE, 5, false)));
			cardButton.setForeground(Color.WHITE);
			
			switch(card.cardType)
			{
				
				case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 13 ->
				{
					
					cardButton.setText(card.cardTypeToString());
					cardButton.setFont(new Font("Impact", Font.PLAIN, 55));
					
				}

				case 10, 12 ->
				{
					
					cardButton.setText(card.cardTypeToString());
					cardButton.setFont(new Font("Impact", Font.PLAIN, 30));
					
				}

				case 11 ->
				{
					
					cardButton.setText(card.cardTypeToString());
					cardButton.setFont(new Font("Impact", Font.PLAIN, 20));
					
				}
				
			}
			
			switch (card.cardColor)
			{
				
				case 0 -> cardButton.setBackground(new Color(240, 0, 0));

				case 1 -> cardButton.setBackground(new Color(0, 0, 240));

				case 2 -> cardButton.setBackground(new Color(240, 240, 0));

				case 3 -> cardButton.setBackground(new Color(0, 240, 0));

				case 4 -> cardButton.setBackground(Color.BLACK);
				
			}
			
		}
		
		else if (topCardorCurrentPlayer == false)
		{
			
			cardButton.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK, 5, false), BorderFactory.createLineBorder(Color.GRAY, 5, false)));
			cardButton.setBackground(Color.DARK_GRAY);
			cardButton.setForeground(Color.GRAY);
			cardButton.setFont(new Font("Impact", Font.BOLD, 30));
			cardButton.setText("UNO");
			
		}
		
	}

	/**
	 * Purpose: Initializes the unoView once the Players have advanced past the
	 * game initialization phase.
	 */
	public void initializeGUI()
	{
		windowPanel.add(Box.createVerticalStrut(15));
		createGameStatusTracker();
		windowPanel.add(Box.createVerticalStrut(10));
		createTopCardPanel();
		windowPanel.add(Box.createVerticalStrut(10));
		createHelpLevelTracker();
		windowPanel.add(Box.createVerticalStrut(10));
		createActionPanel();
		windowPanel.add(Box.createVerticalGlue());
		createPlayerPanel();


		topCardPanel.revalidate();
		topCardPanel.repaint();
		playerPanel.revalidate();
		playerPanel.repaint();
		
		for (int i = 0; i < cardPanelArray.length; i++)
		{
			
			cardPanelArray[i].revalidate();
			cardPanelArray[i].repaint();
			
		}
		
	}

	/**
	 * Purpose: Revalidates and repaints all unoView components to reflect any
	 * changes in the game state.
	 */
	public void updateGUI()
	{
		
		helpLevelTracker.setFont(new Font("Helvetica", Font.BOLD, 20));
		helpButton.setFont(new Font("Helvetica", Font.BOLD, 22));
		helpButton.setEnabled(false);
		
		updateTopCardPanel();
		updatePlayerPanel();
		
	}

	/**
	 * Purpose: Revalidates and repaints all of the CardButtons in every
	 * cardPanel in the cardPanelArray to reflect any changes in each Player's
	 * playerDeck. Removes interactability and anonymizes cardPanels that do not
	 * belong to the currentPlayer.
	 * 
	 * @param index the index of the cardPanel to modify within the
	 *              cardPanelArray
	 */
	public void updateCardPanel(int index)
	{
		
		cardPanelArray[index].removeAll();
		
		for (int j = 0; j < unoModel.getPlayer(index + 1).playerDeck.size(); j++)
		{
			
			if (index == unoModel.getCurrentPlayer().playerNumber - 1)
			{
				
				CardButton cardButton = new CardButton(unoModel.getPlayer(index + 1).playerDeck.get(j));
				setButtonLayout(unoModel.getPlayer(index + 1).playerDeck.get(j), cardButton, true);
				cardButton.addMouseListener(new CardButtonListener(cardButton, unoModel, unoView));
				cardButton.setFocusPainted(false);
				
				cardPanelArray[index].add(cardButton);
				
			}
			
			else
			{
				
				CardButton cardButton = new CardButton(unoModel.getPlayer(index + 1).playerDeck.get(j));
				setButtonLayout(unoModel.getPlayer(index + 1).playerDeck.get(j), cardButton, false);
				cardButton.removeMouseListener(cardButton.getMouseListeners()[0]);
				cardButton.setFocusPainted(false);
				
				cardPanelArray[index].add(cardButton);
				
			}
			
		}
		
		cardPanelArray[index].revalidate();
		cardPanelArray[index].repaint();
		
	}
	
	/**
	 * Purpose: Revalidates and repaints all of the cardPanels contained within
	 * the playerPanel; the currentPlayer's cardPanel is always drawn at the
	 * top, and subsequent cardPanels are drawn in order based on the current
	 * gameDirection. In addition, resets the horizontal position of the
	 * scrollbar.
	 */
	public void updatePlayerPanel()
	{
		
		playerPanel.removeAll();
		
		for (int i = 0; i < unoModel.getNumberOfPlayers(); i++)
		{
			
			updateCardPanel(i);
			
			cardPanelArray[i].revalidate();
			cardPanelArray[i].repaint();
			
		}
		
		playerPanel.add(cardPanelArray[unoModel.getCurrentPlayer().playerNumber - 1]);
		
		Player tempPlayer = unoModel.nextTurn(unoModel.getCurrentPlayer());
		
		while (tempPlayer != unoModel.getCurrentPlayer())
		{
			
			playerPanel.add(cardPanelArray[tempPlayer.playerNumber - 1]);
			tempPlayer = unoModel.nextTurn(tempPlayer);
			
		}
		
		playerPanel.add(Box.createVerticalGlue());
		
		playerPanel.revalidate();
		playerPanel.repaint();
		
		scrollablePlayerPanel.getHorizontalScrollBar().setValue(0);
		
	}

	/**
	 * Purpose: Revalidates and repaints the topCard panel based on the
	 * unoModel's current topCard.
	 */
	public void updateTopCardPanel()
	{
		
		topCardPanel.removeAll();
		
		topCardButton = new CardButton(unoModel.getTopCard());
		setButtonLayout(unoModel.getTopCard(), topCardButton, true);
		topCardButton.removeMouseListener(topCardButton.getMouseListeners()[0]);
		topCardButton.setFocusPainted(false);
		
		topCardPanel.add(topCardButton);
		topCardPanel.revalidate();
		topCardPanel.repaint();
		
	}

	/**
	 * Purpose: Main method for the UNO program.
	 * 
	 * @param args the command line arguments
	 */
	public static void main(String[] args)
	{
		
		new UnoGUI(new UnoGame());
		
	}
	
}
