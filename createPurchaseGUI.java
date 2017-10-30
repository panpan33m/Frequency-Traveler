import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class createPurchaseGUI extends JPanel implements ActionListener {
	private JDialog dialog = null;
	private boolean ok = false;
	private Component northText;
	private JPanel southPanel;
	private JButton addLevel;
	private JButton save;
	private JButton cancel;
	private Container centerPanel;
	private JTextField nameText;
	private JTextField milesText;
	private ArrayList<GuestPackage> packages = new ArrayList<GuestPackage>();
	private Traveler traveler;
	private JComboBox levelsBox;
	private String name;
	private JButton addMember;
	private ArrayList<Traveler> travs;
	private JComboBox programBox;
	private ArrayList<String> progNames;
	private ArrayList<TravelProgram> memberOf;
	private JButton select;
	private ArrayList<String> travNames;


	public createPurchaseGUI(ArrayList<Traveler> travelers){
		super();

		this.setLayout(new BorderLayout());
		int borderWidth = 5;
		this.setPreferredSize(new Dimension(450,100));
		//Set up our panel where we will put the various widgets being displayed
		centerPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(1,2));
		southPanel.setLayout(new FlowLayout());

		centerPanel.add(new JLabel("Select Traveler:"));
		travs = new ArrayList<Traveler>();
		travNames = new ArrayList<String>();
		for(int i = 0; i< travelers.size(); i++){
			travs.add(travelers.get(i));
			travNames.add(travelers.get(i).getName());
		}
		programBox = new JComboBox(travNames.toArray());
		centerPanel.add(programBox);

		this.add(centerPanel,BorderLayout.CENTER);

		northText = new JLabel("Enter Traveler Information");
		this.add(northText, BorderLayout.NORTH);

		select = new JButton("Select");
		select.setActionCommand("Select");
		select.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(this);

		southPanel.add(select);
		southPanel.add(cancel);
		this.add(southPanel,BorderLayout.SOUTH);

		memberOf = new ArrayList<TravelProgram>();
		//We don't want the widgets right up against the edge of our window -
		//it doesn't look good so we will create an empty border around them.
		Border empty;
		empty = BorderFactory.createEmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth);
		this.setBorder(empty);
	}


	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == cancel)
			dialog.setVisible(false);
		else if(event.getSource() == select){
			int chosen = programBox.getSelectedIndex();
			createPersonPurchaseGUI window = new createPersonPurchaseGUI(travs.get(chosen));
			window.setLocation(this.getLocation());
			this.setVisible(false);
			window.showDialog(null, "Purchase Travel");
			this.setVisible(true);
			
		}
	}


	public boolean showDialog(Component parent, String title)
	{
		ok = false;
		//find the owner
		Frame owner = null;
		if (parent instanceof Frame)
			owner = (Frame) parent;
		else
			owner = (Frame) SwingUtilities.getAncestorOfClass(Frame.class, parent);
		if (dialog == null || dialog.getOwner() != owner)
		{
			dialog = new JDialog(owner, true);
			dialog.add(this);
			dialog.setLocationRelativeTo(null);
			//	dialog.getRootPane().setDefaultButton(cancelButton);
			dialog.pack();
		}
		//set the title and show it
		dialog.setTitle(title);
		dialog.setVisible(true);
		return ok;
	}
}
