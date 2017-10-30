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
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class createPurchaseFlyGUI extends JPanel implements ActionListener {
	private JDialog dialog = null;
	private boolean ok = false;
	private Component northText;
	private JPanel southPanel;
	private JButton addLevel;
	private JButton save;
	private JButton cancel;
	private Container centerPanel;
	private JTextField milesText;
	private String name;
	private ArrayList<String> travNames;
	private ArrayList<FlierPackage> packs;
	private JComboBox packageBox;
	private JCheckBox checkBox;
	private JTextField travNum;
	private JButton buy;
	private int numOfTrav;
	private Traveler traveler;
	private TravelProgram program;
	private int numOfMiles;
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == cancel)
			dialog.setVisible(false);
		else if(event.getSource() == buy){
			try{
				numOfTrav = Integer.parseInt(travNum.getText());
				if(numOfTrav<=0)
					throw new Exception();
				if(checkBox.isSelected()){
					FlierPackage picked = (FlierPackage) packageBox.getSelectedItem();
					if(numOfTrav*picked.getPurchase() > traveler.getPoints(program)){
						JOptionPane.showMessageDialog(this, 
								"Failed to redeem award travel. Passenger has "+ traveler.getPoints(program)+" miles but requires "
						+ picked.getPurchase()+" miles for "+numOfTrav+" "+ picked.getName()+" ticket(s)");
					}
					else{
						traveler.usingPointsFlier(program, numOfTrav, picked.getPurchase());
						JOptionPane.showMessageDialog(this, "Redeemed points for " + numOfTrav +" "+ picked +" tickets");	
					}
				}
				else{
					numOfMiles = Integer.parseInt(milesText.getText());
					if(numOfMiles<=0)
						throw new Exception();
					traveler.pointsCreditFlier(program, numOfTrav, numOfMiles, ((FlierProgram) program).getMiles());
					JOptionPane.showMessageDialog(this, "Purchased tickets for "+numOfTrav+" traveler(s) on "+program.getProgramName());
				}
				JOptionPane.showMessageDialog(this,traveler);
				dialog.setVisible(false);
			}
			catch(NumberFormatException ex){
				dialog.setVisible(false);
				JOptionPane.showMessageDialog(this, "Invalid number.");
				dialog.setVisible(true);
			} 
			catch (Exception e) {
					dialog.setVisible(false);
					JOptionPane.showMessageDialog(this, "Number must be greater than zero.");
					dialog.setVisible(true);
			}
		}
		else if(event.getSource() == checkBox){
			flip();
		}
	}
	
	private void flip() {
		if(packageBox.isEnabled()){
			packageBox.setEnabled(false);
			milesText.setEnabled(true);
		}
		else{
			packageBox.setEnabled(true);
			milesText.setEnabled(false);
		}
	}

	public createPurchaseFlyGUI(Traveler trav, TravelProgram prog){
		super();
		traveler = trav;
		program = prog;
		this.setLayout(new BorderLayout());
		int borderWidth = 5;
		this.setPreferredSize(new Dimension(450,180));
		//Set up our panel where we will put the various widgets being displayed
		centerPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(4,2));
		southPanel.setLayout(new FlowLayout());

		packs = new ArrayList<FlierPackage>();
		
		for(int i = 0; i<prog.numOfPackages(); i++){
			packs.add((FlierPackage) prog.getPackage(i));
		}
		
		
		centerPanel.add(new JLabel("Number of travelers:"));
		travNum = new JTextField();
		centerPanel.add(travNum);
		centerPanel.add(new JLabel("Will this be a rewards ticket?"));
		checkBox = new JCheckBox();
		checkBox.addActionListener(this);
		centerPanel.add(checkBox);
		centerPanel.add(new JLabel("Package option:"));
		packageBox = new JComboBox(packs.toArray());
		packageBox.setEnabled(false);
		centerPanel.add(packageBox);
		centerPanel.add(new JLabel("Number of miles:"));
		milesText = new JTextField();
		centerPanel.add(milesText);
		
		
		this.add(centerPanel,BorderLayout.CENTER);

		northText = new JLabel("Input ticket information:");
		this.add(northText, BorderLayout.NORTH);

		buy = new JButton("Buy");
		buy.setActionCommand("Buy");
		buy.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.setActionCommand("Cancel");
		cancel.addActionListener(this);

		southPanel.add(buy);
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
}
