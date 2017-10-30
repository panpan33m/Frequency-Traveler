import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Scanner;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class FrequentTravelerTracker extends JFrame implements ActionListener {

	private JPanel centerPanel;
	private JPanel southPanel;
	private JLabel selOption;
	private JButton select;
	private JButton exit;
	private JRadioButton cFFP;
	private JRadioButton cFGP;
	private JRadioButton aT;
	private JRadioButton pT;
	private ButtonGroup menuGroup;
	private static ArrayList<TravelProgram> programs;
	private static ArrayList<Traveler> travelers;


	public void actionPerformed (ActionEvent event)
	{
		if (event.getSource() == exit)
			this.dispose();
		else if(event.getSource() == select)
		{
			if(cFFP.isSelected()){
				createFlierGUI window = new createFlierGUI();
				window.setLocation(this.getLocation());
				this.setVisible(false);
				window.showDialog(null, "Create Frequent Flier Program");
				TravelProgram program = window.getProgram();
				if(program!=null)
					programs.add(program);
				this.setVisible(true);
			}
			else if(cFGP.isSelected()){
				createGuestGUI window = new createGuestGUI();
				window.setLocation(this.getLocation());
				this.setVisible(false);
				window.showDialog(null, "Create Frequent Guest Program");
				TravelProgram program = window.getProgram();
				if(program!=null)
					programs.add(program);
				this.setVisible(true);
			}
			else if(aT.isSelected()){
				createTravelerGUI window = new createTravelerGUI(programs);
				window.setLocation(this.getLocation());
				this.setVisible(false);
				window.showDialog(null, "Add Traveler");
				Traveler trav = window.getTraveler();
				if(trav!=null)
					travelers.add(trav);
				this.setVisible(true);
			}
			else if(pT.isSelected()){
				if(travelers.isEmpty())
					JOptionPane.showMessageDialog(this, "No travelers exist yet.");
				else{
					createPurchaseGUI window = new createPurchaseGUI(travelers);
					window.setLocation(this.getLocation());
					this.setVisible(false);
					window.showDialog(null, "Purchase Travel");
					this.setVisible(true);
				}
			}
		}
	}


	public FrequentTravelerTracker(){
		super();
		this.setSize(400,400);
		this.setLayout(new BorderLayout());
		int borderWidth = 5;

		//Set up our panel where we will put the various widgets being displayed
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(5,1));
		selOption = new JLabel("Select an Option");
		centerPanel.add(selOption);
		cFFP = new JRadioButton("Create Frequent Flier Program");
		cFFP.setSelected(true);
		cFGP = new JRadioButton("Create Frequent Guest Program");
		aT = new JRadioButton("Add Traveler");
		pT = new JRadioButton("Purchase Travel");

		menuGroup = new ButtonGroup();
		menuGroup.add(cFFP);
		menuGroup.add(cFGP);
		menuGroup.add(aT);
		menuGroup.add(pT);

		centerPanel.add(cFFP);
		centerPanel.add(cFGP);
		centerPanel.add(aT);
		centerPanel.add(pT);
		this.add(centerPanel,BorderLayout.CENTER);

		southPanel = new JPanel();
		southPanel.setLayout(new FlowLayout());
		select = new JButton("Select");
		select.setActionCommand("Select");
		select.addActionListener(this);
		exit = new JButton("Exit");
		exit.setActionCommand("Exit");
		exit.addActionListener(this);

		southPanel.add(select);
		southPanel.add(exit);
		this.add(southPanel,BorderLayout.SOUTH);

		//We don't want the widgets right up against the edge of our window -
		//it doesn't look good so we will create an empty border around them.
		Border empty;
		empty = BorderFactory.createEmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth);
		centerPanel.setBorder(empty);

	}

	public static void main(String[] args) {
		travelers = new ArrayList<Traveler>();
		programs = new ArrayList<TravelProgram>();
		
		FrequentTravelerTracker newT = new FrequentTravelerTracker();
		newT.setTitle("Frequent Traveler Tracker");
		newT.pack();

		//don't let our window zoom up into the upper left corner!
		//This command will center it.
		newT.setLocationRelativeTo(null);
		newT.setVisible(true);

		
		Scanner sc = new Scanner(System.in);


		boolean select = true; //control the while loop of main menu;
		boolean finish = true; //control the while loop of selecting frequent traveler program to join
		boolean select2 = true; //control the while loop of purchase menu;
		boolean finish2 = true; //control the while loop of buy air ticket/book hotel menu;	

	}

}
