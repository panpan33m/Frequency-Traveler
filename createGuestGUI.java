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

public class createGuestGUI extends JPanel implements ActionListener {
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
	private String name;
	private int miles;
	private GuestProgram program;
	private JComboBox levelsBox;

	public GuestProgram getProgram(){
		return program;
	}

	public createGuestGUI(){
		super();

		this.setLayout(new BorderLayout());
		int borderWidth = 5;
		this.setPreferredSize(new Dimension(400,120));
		//Set up our panel where we will put the various widgets being displayed
		centerPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(2,2));
		southPanel.setLayout(new FlowLayout());

		centerPanel.add(new JLabel("Enter name:"));
		nameText = new JTextField();
		centerPanel.add(nameText);
		centerPanel.add(new JLabel("Select level:"));

		levelsBox = new JComboBox();
		centerPanel.add(levelsBox);
		this.add(centerPanel,BorderLayout.CENTER);

		northText = new JLabel("Enter Frequent Guest Program Information");
		this.add(northText, BorderLayout.NORTH);


		addLevel = new JButton("Add level");
		addLevel.setActionCommand("Add level");
		addLevel.addActionListener(this);
		save = new JButton("Save");
		save.setActionCommand("Save");
		save.addActionListener(this);
		save.setEnabled(false);
		cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(this);

		southPanel.add(addLevel);
		southPanel.add(save);
		southPanel.add(cancel);
		this.add(southPanel,BorderLayout.SOUTH);

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
		else if(event.getSource() == addLevel){
			GuestClass newLevel = new GuestClass();
			newLevel.showDialog(null, "Add Award Level");
			if(newLevel.hasPackage())
			{
				GuestPackage levelPackage = newLevel.getPackage();	
				packages.add(levelPackage);
				levelsBox = new JComboBox(packages.toArray());
				save.setEnabled(true);
				centerPanel.remove(3);
				centerPanel.add(levelsBox);
				centerPanel.repaint();
				centerPanel.revalidate();
			}
		}
		else if(event.getSource() == save){
			name = nameText.getText();
			try{
				if(name.equals(""))
					throw new NumberFormatException();
				program = new GuestProgram(name);
				for(int i=0; i<packages.size(); i++){
					program.addPackage(packages.get(i));
				}
				JOptionPane.showMessageDialog(this, program);
				dialog.setVisible(false);
			}
			catch(NumberFormatException ex){
				dialog.setVisible(false);
				JOptionPane.showMessageDialog(this, "Program name can not be null. \nNumber of Miles must be a valid integer");
				dialog.setVisible(true);
			} 
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
