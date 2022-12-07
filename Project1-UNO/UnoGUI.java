import java.awt.BorderLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

public class UnoGUI extends JFrame
{
	//window to add player name
	//private Player addPlayers = new Player();
	ArrayList<String> temp = new ArrayList<>();
	String[] playerName;
//	Game game;

	//fields for the user interface
	private JPanel displayPanel;
	private JPanel playerPanel;
	private ArrayList<JButton> cardButton = new ArrayList<JButton>();
	ArrayList<String> cardMatch;
	
//	private JButton drawButton;
//	private JButton currentCard;
//	
//	private JButton UnoButton;
//	private JLabel[] playerName;
//	private JLabel[] lNames;
//	
//	private JTextField numberPlayer;
//	private JTextField nameInput;
	
	//fields for the game
	private Game game;
	
	
	//constructor
	public UnoGUI()
	{
		super("UNO");
		setSize(1000,800);
		
		displayPanel = new JPanel();
		add(displayPanel);
		
//		displayPanel.setLayout(new BoxLayout(displayPanel, BoxLayout.Y_AXIS));
//		JScrollPane scrollPane = new JScrollPane (displayPanel);
//		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
//		initializeDisplayPanel ();
//		initializeplayerPanel ();
		
	    
	    //make window visible
	    setVisible(true);
	    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}
//	
//	 class extends JTextField implemts ActionListener
//	{
//		
//	}
//	void initalizedPlayerPanel()
//	{
//		window = new JFrame();
//		
//		playerPanel = new JPanel();
//		playerPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//		
//		playerName = "";
//		
//	}
	public static void main(String[] args) {
		UnoGUI window = new UnoGUI();
		//TowersOfHanoiGUI tohg = new TowersOfHanoiGUI();
	}
}
