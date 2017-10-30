import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class LevelClass extends JPanel implements ActionListener {
	private JDialog dialog = null;
	private boolean ok = false;
	private Component northText;
	private JPanel southPanel;
	private JButton save;
	private JButton cancel;
	private Container centerPanel;
	private JTextField nameText;
	private JTextField milesText;
	private String name;
	private int miles;
	private FlierPackage pack;



	public String getName() {
		return name;
	}

	public int getMiles() {
		return miles;
	}

	public FlierPackage getPackage(){
		return pack;
	}
	
	public boolean hasPackage(){
		return pack != null;
	}
	
	public LevelClass(){
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
		centerPanel.add(new JLabel("Miles to Redeem:"));
		milesText = new JTextField();
		centerPanel.add(milesText);
		this.add(centerPanel,BorderLayout.CENTER);

		northText = new JLabel("Enter Frequent Flier Program Information");
		this.add(northText, BorderLayout.NORTH);


		save = new JButton("Save");
		save.setActionCommand("Save");
		save.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(this);

		southPanel.add(save);
		southPanel.add(cancel);
		this.add(southPanel,BorderLayout.SOUTH);

		//We don't want the widgets right up against the edge of our window -
		//it doesn't look good so we will create an empty border around them.
		Border empty;
		empty = BorderFactory.createEmptyBorder(borderWidth, borderWidth, borderWidth, borderWidth);
		this.setBorder(empty);
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

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == cancel)
			dialog.setVisible(false);
		else if(event.getSource() == save){
			name = nameText.getText();
			try{
				miles = Integer.parseInt(milesText.getText());
				if(miles<=0)
					throw new Exception();
				if(name.equals(""))
					throw new NumberFormatException();
				pack = new FlierPackage(name, miles);
				dialog.setVisible(false);
			}
			catch(NumberFormatException ex){
				if(name.equals(""))
					JOptionPane.showMessageDialog(this, "Level name must be provided. \nMiles redeemed must be an integer");
				else
					JOptionPane.showMessageDialog(this, "Invalid number.");
			} 
			catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Miles required for redemption must be greater than zero.");
			}
			
			
		}

	}
}
