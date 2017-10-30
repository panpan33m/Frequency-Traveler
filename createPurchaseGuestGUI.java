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

public class createPurchaseGuestGUI extends JPanel implements ActionListener {
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
	private ArrayList<GuestPackage> packs;
	private JComboBox packageBox;
	private JCheckBox checkBox;
	private JTextField travNum;
	private JButton buy;
	private int numOfTrav;
	private Traveler traveler;
	private TravelProgram program;
	private int numOfMiles;
	private JTextField nightsNum;
	private int numOfNights;
	
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == cancel)
			dialog.setVisible(false);
		else if(event.getSource() == buy){
			try{
				numOfNights = Integer.parseInt(nightsNum.getText());
				if(numOfNights<=0)
					throw new Exception();
				if(checkBox.isSelected()){
					GuestPackage picked = (GuestPackage) packageBox.getSelectedItem();
					if(numOfNights*picked.getPurchase() > traveler.getPoints(program)){
						JOptionPane.showMessageDialog(this, 
	"Failed to redeem award travel. Passenger has "+ traveler.getPoints(program)+" points but requires "
	+ picked.getPurchase()*numOfNights+" points for "+numOfNights+" night(s) "+ picked +" stay");
					}
					else{
						traveler.usingPointsHotel(program, numOfNights, picked.getPurchase());
						JOptionPane.showMessageDialog(this, "Redeemed points for " + numOfNights +" night(s) stay at a "+ picked +" hotel");	
					}
				}
				else{
					GuestPackage picked = (GuestPackage) packageBox.getSelectedItem();
					traveler.pointsCreditHotel(program, numOfNights, picked.getAwardPerNight());
					JOptionPane.showMessageDialog(this, "Purchased "+numOfNights+" nights at a "+picked+" hotel.");
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
	}
	

	public createPurchaseGuestGUI(Traveler trav, TravelProgram prog){
		super();
		traveler = trav;
		program = prog;
		this.setLayout(new BorderLayout());
		int borderWidth = 5;
		this.setPreferredSize(new Dimension(450,160));
		//Set up our panel where we will put the various widgets being displayed
		centerPanel = new JPanel();
		southPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(3,2));
		southPanel.setLayout(new FlowLayout());

		packs = new ArrayList<GuestPackage>();
		
		for(int i = 0; i<prog.numOfPackages(); i++){
			packs.add((GuestPackage) prog.getPackage(i));
		}
		
		centerPanel.add(new JLabel("Select property level:"));
		packageBox = new JComboBox(packs.toArray());
		centerPanel.add(packageBox);
		centerPanel.add(new JLabel("Number of nights:"));
		nightsNum = new JTextField();
		centerPanel.add(nightsNum);
		centerPanel.add(new JLabel("Will this be a rewards ticket?"));
		checkBox = new JCheckBox();
		checkBox.addActionListener(this);
		centerPanel.add(checkBox);
			
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
