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

public class createFlierGUI extends JPanel implements ActionListener {
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
	private ArrayList<FlierPackage> packages = new ArrayList<FlierPackage>();
	private String name;
	private int miles;
	private FlierProgram program;
	private JComboBox levelsBox;

	public FlierProgram getProgram(){
		return program;
	}
	
	public createFlierGUI(){
		super();

		this.setLayout(new BorderLayout());
		int borderWidth = 5;
		this.setPreferredSize(new Dimension(400,150));
		//Set up our panel where we will put the various widgets being displayed
		centerPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3,2));
		southPanel.setLayout(new FlowLayout());

		centerPanel.add(new JLabel("Enter name:"));
		nameText = new JTextField();
		centerPanel.add(nameText);
		centerPanel.add(new JLabel("Minimum Miles:"));
		milesText = new JTextField();
		centerPanel.add(milesText);
		centerPanel.add(new JLabel("Select level:"));

		levelsBox = new JComboBox();
		centerPanel.add(levelsBox);
		this.add(centerPanel,BorderLayout.CENTER);

		northText = new JLabel("Enter Frequent Flier Program Information");
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
			LevelClass newLevel = new LevelClass();
			newLevel.showDialog(null, "Add Award Level");
			if(newLevel.hasPackage())
			{
				FlierPackage levelPackage = newLevel.getPackage();	
				packages.add(levelPackage);
				levelsBox = new JComboBox(packages.toArray());
				save.setEnabled(true);
				centerPanel.remove(5);
				centerPanel.add(levelsBox);
				centerPanel.repaint();
				centerPanel.revalidate();
			}
		}
		else if(event.getSource() == save){
			name = nameText.getText();
			try{
				miles = Integer.parseInt(milesText.getText());
				if(miles<=0)
					throw new Exception();
				if(name.equals(""))
					throw new NumberFormatException();
				program = new FlierProgram(name,miles);
				for(int i=0; i<packages.size(); i++){
					program.addPackage(packages.get(i));
				}
				JOptionPane.showMessageDialog(this, program);
				dialog.setVisible(false);
			}
			catch(NumberFormatException ex){
				if(name.equals(""))
				{
					dialog.setVisible(false);
					JOptionPane.showMessageDialog(this, "Program name can not be null. \nNumber of Miles must be a valid integer");
					dialog.setVisible(true);
				}
				else
				{
					dialog.setVisible(false);
					JOptionPane.showMessageDialog(this, "Invalid number.");
					dialog.setVisible(true);
				}
			} 
			catch (Exception e) {
				{
					dialog.setVisible(false);
					JOptionPane.showMessageDialog(this, "Miles required for redemption must be greater than zero.");
					dialog.setVisible(true);
				}
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
