//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Edit Customer frame class to edit/delete Customer records
package customerManagement;

//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

/**
 * Edit Customer GUI frame to allow Staff to edit/delete Customer records
 * @author Shane Kennedy
 */
public class EditCustomerFrame extends JFrame {
	
	//Components
    private JButton btnAmend;
    private JButton btnDelete;
    private JButton btnExit;
    private ButtonGroup btnGrpCustType;
    private JComboBox<String> jCombCountry;
    private JComboBox<String> jCombCounty;
    private JRadioButton jRBtnNotVatReg;
    private JRadioButton jRBtnVatReg;
    private JLabel labelLogo;
    private JLabel labelText;
    private JLabel lblAddLine1;
    private JLabel lblContact;
    private JLabel lblCountry;
    private JLabel lblCounty;
    private JLabel lblCustType;
    private JLabel lblEmail;
    private JLabel lblName;
    private JLabel lblPass;
    private JLabel lblPhone;
    private JLabel lblStreet;
    private JLabel lblTown;
    private JLabel lblUsername;
    private JLabel lblVatNo;
    private JPanel panelAddress;
    private JPanel panelContact;
    private JPanel panelCustName;
    private JPanel panelDetails;
    private JPanel panelMain;
    private JPanel panelTop;
    private JPanel panelButtons;
    private JPanel panelUsername;
    private JPanel panelVatNo;
    private JTextField tFldAddLine1;
    private JTextField tFldContact;
    private JTextField tFldEmail;
    private JTextField tFldName;
    private JTextField tFldPassword;
    private JTextField tFldPhone;
    private JTextField tFldStreet;
    private JTextField tFldTown;
    private JTextField tFldUsername;
    private JTextField tFldVat;
	    
    //Array Lists of Countries and counties to appear in comboboxes
    private ArrayList<String> countries = new ArrayList<String>(Arrays.asList("Ireland", "United Kingdom"));
    private ArrayList<String> countiesIre = new ArrayList<String>(Arrays.asList("Carlow", "Cavan", "Clare", "Cork", "Donegal", "Dublin", "Galway", "Kerry", "Kildare", "Kilkenny", "Laois", "Leitrim", "Limerick", "Longford", "Louth", "Mayo", "Meath", "Offaly", "Roscommon", "Sligo", "Tipperary", "Waterford", "Westmeath", "Wexford", "Wicklow"));
    private ArrayList<String> countiesUk = new ArrayList<String>(Arrays.asList("Antrim","Armagh","Avon","Bath","Bedfordshire","Bedford","Blackpool","Bristol","Cambridgeshire","Chesire","Cumbria","Derby","Devon","Dorset","Essex","Hartepool","Isle of Wight","Kent","Lincolnshire","London","Luton","Medway","Middlesex","Norfolk","Nottingham","Plymouth","Poole","Portsmouth","Rutland","Shropshire","Surrey","Sussex","Swindon","Thurrock","Torbay","Tyne and Wear","Warrigton","West Sussex","Yorkshire","York"));
    
    //Create reference for JFrame window
   	EditCustomerFrame frame = this;
   	
    //Instance variables
    private int pubCustID ;
    private String currentCustUsername;
   	
    /**
     * Constructor for Add Customer Frame
     */
    public EditCustomerFrame(int custID) 
    {	
    	//Call method to build frame
        buildFrame();
        //Set the instance variable
        pubCustID = custID;
        //Call method to fill item details
        getItemDetails(custID);
        //Call method to fill county and country comboboxes
        fillComboBoxes();
        //Get current username for validation
        currentCustUsername = tFldUsername.getText();
   }

    /**
     * Method to initialize and format  GUI components
     */
    private void buildFrame() 
    {    	
    	//Build panel for Customer Name
    	panelCustName = new JPanel();
    	panelCustName.setLayout(null);
    	panelCustName.setBounds(10,15,360, 130);
    	panelCustName.setBorder(BorderFactory.createTitledBorder(null, "Name", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14), new Color(166, 76, 156)));
    		//Build labels and textfields
	    	lblName = new JLabel();
	        lblName.setBackground(new Color(68, 62, 90));
	        lblName.setFont(new Font("Segoe UI", 0, 14));
	        lblName.setForeground(new Color(255, 255, 255));
	        lblName.setHorizontalAlignment(SwingConstants.CENTER);
	        lblName.setText("Customer Name:");
	        lblName.setOpaque(true);
	        lblName.setBounds(10,30,150,35);
	        
	        tFldName = new JTextField();
	        tFldName.setFont(new Font("Segoe UI", 0, 14));
	        tFldName.setToolTipText("Customer Name Field");
	        tFldName.setBounds(165,30,185,35);
	        
	        lblContact = new JLabel();
	        lblContact.setBackground(new Color(68, 62, 90));
	        lblContact.setFont(new Font("Segoe UI", 0, 14));
	        lblContact.setForeground(new Color(255, 255, 255));
	        lblContact.setHorizontalAlignment(SwingConstants.CENTER);
	        lblContact.setText("Contact Name:");
	        lblContact.setOpaque(true);
	        lblContact.setPreferredSize(new Dimension(150, 35));
	        lblContact.setBounds(10,75,150,35);
	        
	        tFldContact = new JTextField();
	        tFldContact.setFont(new Font("Segoe UI", 0, 14));
	        tFldContact.setToolTipText("Contact Name Field");
	        tFldContact.setBounds(165,75,185,35);
	    //Add components
	    panelCustName.add(lblName);
	    panelCustName.add(tFldName);
	    panelCustName.add(lblContact);
	    panelCustName.add(tFldContact);
	    
	 	//Build panel for Customer Address
	    panelAddress = new JPanel();
	    panelAddress.setLayout(null);
	    panelAddress.setBounds(10,170,360, 270);
	    panelAddress.setBorder(BorderFactory.createTitledBorder(null, "Address", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14), new Color(166, 76, 156)));
    		//Build labels,textfields and comboboxes
	    	lblAddLine1 = new JLabel();
	    	lblAddLine1.setBackground(new Color(68, 62, 90));
	        lblAddLine1.setFont(new Font("Segoe UI", 0, 14));
	        lblAddLine1.setForeground(new Color(255, 255, 255));
	        lblAddLine1.setHorizontalAlignment(SwingConstants.CENTER);
	        lblAddLine1.setText("Address Line 1:");
	        lblAddLine1.setOpaque(true);
	        lblAddLine1.setBounds(10,35,150,35);
	        
	        tFldAddLine1 = new JTextField();
	        tFldAddLine1.setFont(new Font("Segoe UI", 0, 14));
	        tFldAddLine1.setToolTipText("Address Line 1 Field");
	        tFldAddLine1.setBounds(165,30,185,35);
	        tFldAddLine1.setBounds(165,35,185,35);
	        
	        lblStreet = new JLabel();
	        lblStreet.setBackground(new Color(68, 62, 90));
	        lblStreet.setFont(new Font("Segoe UI", 0, 14));
	        lblStreet.setForeground(new Color(255, 255, 255));
	        lblStreet.setHorizontalAlignment(SwingConstants.CENTER);
	        lblStreet.setText("Street:");
	        lblStreet.setOpaque(true);
	        lblStreet.setBounds(10,80,150,35);
	        
	        tFldStreet = new JTextField();
	        tFldStreet.setFont(new Font("Segoe UI", 0, 14));
	        tFldStreet.setToolTipText("Address Street Field");
	        tFldStreet.setBounds(165,80,185,35);

	        lblTown = new JLabel();
	        lblTown.setBackground(new Color(68, 62, 90));
	        lblTown.setFont(new Font("Segoe UI", 0, 14));
	        lblTown.setForeground(new Color(255, 255, 255));
	        lblTown.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTown.setText("Town:");
	        lblTown.setOpaque(true);
	        lblTown.setPreferredSize(new Dimension(150, 35));
	        lblTown.setBounds(10,125,150,35);
	        
	        tFldTown = new JTextField();
	        tFldTown.setFont(new Font("Segoe UI", 0, 14));
	        tFldTown.setToolTipText("Address Town Field");
	        tFldTown.setBounds(165,125,185,35);

	        lblCounty = new JLabel();
	        lblCounty.setBackground(new Color(68, 62, 90));
	        lblCounty.setFont(new Font("Segoe UI", 0, 14));
	        lblCounty.setForeground(new Color(255, 255, 255));
	        lblCounty.setHorizontalAlignment(SwingConstants.CENTER);
	        lblCounty.setText("County:");
	        lblCounty.setOpaque(true);
	        lblCounty.setBounds(10,170,150,35);

	        jCombCounty = new JComboBox<>();
	        jCombCounty.setFont(new Font("Segoe UI", 0, 14));
	        jCombCounty.setBounds(165,170,185,35);
	        jCombCounty.addActionListener(
		            //Anonymous Inner Class
					new ActionListener() 
					{
						//Handling jCombCounty changed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	
			            }
					}
					);//End Action Listener
	        
	        lblCountry = new JLabel();
	        lblCountry.setBackground(new Color(68, 62, 90));
	        lblCountry.setFont(new Font("Segoe UI", 0, 14));
	        lblCountry.setForeground(new Color(255, 255, 255));
	        lblCountry.setHorizontalAlignment(SwingConstants.CENTER);
	        lblCountry.setText("Country:");
	        lblCountry.setOpaque(true);
	        lblCountry.setBounds(10,215,150,35);
	      	        
	        jCombCountry = new JComboBox<>();
	        jCombCountry.setFont(new Font("Segoe UI", 0, 14));
	        jCombCountry.setBounds(165,215,185,35);
	        jCombCountry.addActionListener(
		            //Anonymous Inner Class
					new ActionListener() 
					{
						//Handling jCombCountry changed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			                // Country combobox changed event
			                //If index = ireland remove all elements and add irish counties else fill with UK counties
			                if(jCombCountry.getSelectedIndex() == 0)
			                {   
			                    //Fill counties combobox with Irish counties
			                    jCombCounty.setModel(new DefaultComboBoxModel(countiesIre.toArray()));
			                    //Set index to 0
			                    jCombCounty.setSelectedIndex(0);
			                    
			                }
			                else
			                //Fill combobox with UK counties
			                {   
			                    jCombCounty.setModel(new DefaultComboBoxModel(countiesUk.toArray()));
			                    //Set index to 1
			                    jCombCounty.setSelectedIndex(1);
			                }
			            }
					}
					);//End Action Listener
	    //Add components
	    panelAddress.add(lblAddLine1);
	    panelAddress.add(tFldAddLine1);
	    panelAddress.add(lblStreet);
	    panelAddress.add(tFldStreet);
	    panelAddress.add(lblTown);
	    panelAddress.add(tFldTown);
	    panelAddress.add(lblCounty);
	    panelAddress.add(jCombCounty);
	    panelAddress.add(lblCountry);
	    panelAddress.add(jCombCountry);

	    //Build panel for Customer VAT no
	    panelVatNo = new JPanel();
	    panelVatNo.setLayout(null);
	    panelVatNo.setBounds(375,15,375, 145);
	    panelVatNo.setBorder(BorderFactory.createTitledBorder(null, "Customer VAT", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14), new Color(166, 76, 156)));
    		//Build labels and textfields
	    	lblCustType = new JLabel();
	    	lblCustType.setBackground(new Color(68, 62, 90));
	        lblCustType.setFont(new Font("Segoe UI", 0, 14));
	        lblCustType.setForeground(new Color(255, 255, 255));
	        lblCustType.setHorizontalAlignment(SwingConstants.CENTER);
	        lblCustType.setText("Customer Type:");
	        lblCustType.setOpaque(true);
	        lblCustType.setBounds(10,30,180,35);

	        jRBtnNotVatReg = new JRadioButton();
	        jRBtnNotVatReg.setText("Non-VAT Registered");
	        jRBtnNotVatReg.setBounds(190,30,175,18);
	        jRBtnNotVatReg.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling jRBtnNotVatReg changed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// No Vat button radiobutton selected, set TextField for Vat no to not editable
			                tFldVat.setEditable(false);
			                tFldVat.setText("");
			            }
					}
					);//End Action Listener

	        jRBtnVatReg = new JRadioButton();
	        jRBtnVatReg.setText("VAT Registered");
	        jRBtnVatReg.setBounds(190,50,145,18);
	        jRBtnVatReg.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling jRBtnVatReg changed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Vat button radiobutton selected, set TextField for Vat no to editable
			                tFldVat.setEditable(true);
			            }
					}
					);//End Action Listener
	        //Add radiobuttons to buttongroup
	        btnGrpCustType = new ButtonGroup();
	        btnGrpCustType.add(jRBtnNotVatReg);
	        btnGrpCustType.add(jRBtnVatReg);
	        //Set JRadiobutton not vat registered
	        jRBtnNotVatReg.setSelected(true);
	        
	        lblVatNo = new JLabel();
	        lblVatNo.setBackground(new Color(68, 62, 90));
	        lblVatNo.setFont(new Font("Segoe UI", 0, 14));
	        lblVatNo.setForeground(new Color(255, 255, 255));
	        lblVatNo.setHorizontalAlignment(SwingConstants.CENTER);
	        lblVatNo.setText("VAT Number:");
	        lblVatNo.setOpaque(true);
	        lblVatNo.setBounds(10,90,150,35);

	        tFldVat = new JTextField();
	        tFldVat.setEditable(false);
	        tFldVat.setFont(new Font("Segoe UI", 0, 14));
	        tFldVat.setToolTipText("Vat Number Field");
	        tFldVat.setBounds(165,90,200,35);      
	    //Add components
	    panelVatNo.add(lblCustType);
	    panelVatNo.add(jRBtnNotVatReg);
	    panelVatNo.add(jRBtnVatReg);
	    panelVatNo.add(lblVatNo);
	    panelVatNo.add(tFldVat);
	    
		//Build panel for Customer Contact Details
	    panelContact = new JPanel();
	    panelContact.setLayout(null);
	    panelContact.setBounds(375,170,375, 130);
	    panelContact.setBorder(BorderFactory.createTitledBorder(null, "Contact Details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14), new Color(166, 76, 156)));
    		//Build labels and textfields
	    	lblPhone = new JLabel();
	        lblPhone.setBackground(new Color(68, 62, 90));
	        lblPhone.setFont(new Font("Segoe UI", 0, 14));
	        lblPhone.setForeground(new Color(255, 255, 255));
	        lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
	        lblPhone.setText("Phone Number:");
	        lblPhone.setOpaque(true);
	        lblPhone.setPreferredSize(new Dimension(150, 35));
	        lblPhone.setBounds(10,30,150,35);

	        tFldPhone = new JTextField();
	        tFldPhone.setFont(new Font("Segoe UI", 0, 14));
	        tFldPhone.setToolTipText("Phone Number Field");
	        tFldPhone.setBounds(165,30,200,35);
	        
	        lblEmail = new JLabel();
	        lblEmail.setBackground(new Color(68, 62, 90));
	        lblEmail.setFont(new Font("Segoe UI", 0, 14));
	        lblEmail.setForeground(new Color(255, 255, 255));
	        lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
	        lblEmail.setText("Email:");
	        lblEmail.setOpaque(true);
	        lblEmail.setPreferredSize(new Dimension(150, 35));
	        lblEmail.setBounds(10,75,150,35);
	        		
	        tFldEmail = new JTextField();
	        tFldEmail.setFont(new Font("Segoe UI", 0, 14));
	        tFldEmail.setToolTipText("Email Field");
	        tFldEmail.setBounds(165,75,200,35);
	    //Add components
	    panelContact.add(lblPhone);
	    panelContact.add(tFldPhone);
	    panelContact.add(lblEmail);
	    panelContact.add(tFldEmail);

		//Build panel for Customer Username and Password
        panelUsername = new JPanel();
        panelUsername.setLayout(null);
        panelUsername.setBounds(375,310,375, 130);
        panelUsername.setBorder(BorderFactory.createTitledBorder(null, "Create Username and Password", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14), new Color(166, 76, 156)));
    		//Build labels and textfields
	        lblUsername = new JLabel();
	        lblUsername.setBackground(new Color(68, 62, 90));
	        lblUsername.setFont(new Font("Segoe UI", 0, 14));
	        lblUsername.setForeground(new Color(255, 255, 255));
	        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
	        lblUsername.setText("Username:");
	        lblUsername.setOpaque(true);
	        lblUsername.setBounds(10,30,150,35);

	        tFldUsername = new JTextField();
			tFldUsername.setFont(new Font("Segoe UI", 0, 14));
	        tFldUsername.setToolTipText("Username Field");
	        tFldUsername.setBounds(165,30,200,35);
	        
	        lblPass = new JLabel();
	        lblPass.setBackground(new Color(68, 62, 90));
	        lblPass.setFont(new Font("Segoe UI", 0, 14));
	        lblPass.setForeground(new Color(255, 255, 255));
	        lblPass.setHorizontalAlignment(SwingConstants.CENTER);
	        lblPass.setText("Password:");
	        lblPass.setOpaque(true);
	        lblPass.setBounds(10,75,150,35);
	        
	        tFldPassword = new JTextField();
	        tFldPassword.setFont(new Font("Segoe UI", 0, 14));
	        tFldPassword.setToolTipText("Password Field");
	        tFldPassword.setBounds(165,75,200,35);
	    //Add components
	    panelUsername.add(lblUsername);
	    panelUsername.add(tFldUsername);
	    panelUsername.add(lblPass);
	    panelUsername.add(tFldPassword);
	    
	    //Build title panel
	    panelTop = new JPanel(); 
	    panelTop.setPreferredSize(new Dimension(795,95));
	    panelTop.setBackground(new Color(68, 62, 90));
	    panelTop.setLayout(new BoxLayout(panelTop,BoxLayout.LINE_AXIS));
	    	//Build image and title labels
		    labelLogo = new JLabel();
		    labelLogo.setPreferredSize(new Dimension(85,85));
		    labelLogo.setMaximumSize(new Dimension(85,85));
		    labelLogo.setIcon(new ImageIcon(getClass().getResource("/Images/logosmall.png")));
	        labelLogo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

	        labelText = new JLabel();
	        labelText.setPreferredSize(new Dimension(275,35));
	        labelText.setMaximumSize(new Dimension(275,35));
	        labelText.setFont(new Font("Segoe UI", 1, 18));
	        labelText.setForeground(new Color(166, 76, 156));
	        labelText.setText("VENTAIR - EDIT CUSTOMER");
	    //Add components
	    panelTop.add(Box.createRigidArea(new Dimension(25,0)));
	    panelTop.add(labelLogo);
	    panelTop.add(Box.createRigidArea(new Dimension(175,0)));
	    panelTop.add(labelText);
	    
	    //Build Details Panel
	    panelDetails = new JPanel();
	    panelDetails.setLayout(null);
        panelDetails.setBorder(BorderFactory.createEtchedBorder());
        panelDetails.setPreferredSize(new Dimension(755,455));
        panelDetails.setMaximumSize(new Dimension(755,455));
        //Add Detail Panels
        panelDetails.add(panelCustName);
        panelDetails.add(panelAddress);
        panelDetails.add(panelVatNo);
        panelDetails.add(panelContact);
        panelDetails.add(panelUsername);
                
        //Build Buttons Panel
        panelButtons = new JPanel();
        panelButtons.setLayout(new BoxLayout(panelButtons, BoxLayout.LINE_AXIS));
        panelButtons.setPreferredSize(new Dimension(755,30));
        panelButtons.setMaximumSize(new Dimension(755,30));
        	//Build Buttons
        	//Amend Button
	        btnAmend = new JButton();
	        btnAmend.setBackground(new Color(166, 76, 156));
	        btnAmend.setFont(new Font("Tahoma", 0, 12));
	        btnAmend.setForeground(new Color(255, 255, 255));
	        btnAmend.setText("Amend");
	        btnAmend.setBorderPainted(false);
	        btnAmend.setFocusable(false);
	        btnAmend.setPreferredSize(new Dimension(140, 30));
	        btnAmend.setMaximumSize(new Dimension(140, 30));
	        btnAmend.addActionListener(
	            //Anonymous Inner Class
				new ActionListener() 
				{
					//Handling btnAmend pressed action event
		            public void actionPerformed(ActionEvent evt) 
		            {
		            	validation.Validator val = new validation.Validator();
		                try
		                {   //Will check components on panel, if Component is a Jpanel inner for loop will check for JTextfields and validate them
		                    for(Component c : panelDetails.getComponents())
		                    {
		                        if(c instanceof JPanel)
		                        {   
		                            for(Component d : ((JPanel) c).getComponents())
		                            {
		                                if(d instanceof JTextField)
		                                {   
		                                   //Check if radiobutton not vat registers is selected, if it is and textfield selected is Vat field do nothing
		                                   if(d == tFldVat && jRBtnNotVatReg.isSelected() == true)
		                                   {
		                                      
		                                   }
		                                   //Check for valid phone number
		                                   else if(d == tFldPhone)
		                                   {
		                                       val.PhoneNoValidate((JTextField)d);
		                                   }
		                                   //Check for valid email
		                                   else if(d == tFldEmail)
		                                   {
		                                       val.EmailAddValidate((JTextField)d);
		                                   }
		                                   //Check that username doesn't match existing username
		                                   else if(d == tFldUsername)
		                                   {    
		                                       //If username hasn't changed don't check if the same for obvious reasons
		                                       if(!tFldUsername.getText().equals(currentCustUsername))
		                                       {
		                                            val.UsernameValidate((JTextField)d);
		                                       }
		                                   }
		                                   //Check for empty textfields
		                                   else
		                                   {
		                                       val.JTextFieldEmpty((JTextField)d);
		                                   }
		                                   
		                                }
		                            }
		                        }
		                    }
		                    // Add Item Button Press Event
		                    int confirm = 0;
		                    confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to amend customer data?");
		                    if(confirm == 0)
		                    {   
		                        Address add = new Address(tFldAddLine1.getText(),tFldStreet.getText(), tFldTown.getText(),
		                                                    String.valueOf(jCombCounty.getSelectedItem()),String.valueOf(jCombCountry.getSelectedItem()));
		                        
		                        Customer cust = new Customer(1, tFldName.getText(), tFldContact.getText(), add, tFldPhone.getText(), tFldEmail.getText(), tFldVat.getText(),
		                                                      tFldUsername.getText(), tFldPassword.getText());

		                        

		                        int test = cust.updateCustEntry(pubCustID);
		                        if(test == 1)
		                        {
		                            JOptionPane.showMessageDialog(frame, "Customer successfully amended");
		                            frame.dispose();
		                        }
		                        else
		                        {
		                            JOptionPane.showMessageDialog(frame, "Problem amending customer, please try again");
		                        }

		                    }
		                  }//end try
		                catch(validation.JTextFieldEmpty e)
		                {
		                    //JTextFieldEmpty will handle exception
		                }
		                catch(validation.PhoneNoValidate e)
		                {
		                    //PhoneNoValidate will handle exception
		                }
		                catch(validation.EmailAddValidate e)
		                {
		                    //PhoneNoValidate will handle exception
		                }
		                 catch(validation.UsernameValidate e)
		                {
		                    //UsernameValidate will handle exception
		                }
		            }
				}
				);//End Action Listener
	        
	        //Delete Customer Button
	        btnDelete = new JButton();
	        btnDelete.setBackground(new Color(166, 76, 156));
	        btnExit.setFont(new Font("Tahoma", 0, 10));
	        btnDelete.setForeground(new Color(255, 255, 255));
	        btnDelete.setText("Delete");
	        btnDelete.setBorderPainted(false);
	        btnDelete.setFocusable(false);
	        btnDelete.setPreferredSize(new Dimension(140, 30));
	        btnDelete.setMaximumSize(new Dimension(140, 30));
	        btnDelete.addActionListener(
		            //Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnDelete pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			                // Delete Customer button event
			                int confirm = 0;
			                confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete customer data?");
			                if(confirm == 0)
			                {   
			                    Customer cust = new Customer();
			                    int test = cust.deleteEntry(pubCustID);
			                    if(test == 1)
			                    {
			                        JOptionPane.showMessageDialog(frame, "Customer successfully deleted");
			                        frame.dispose();
			                    }
			                    else
			                    {
			                        JOptionPane.showMessageDialog(frame, "Problem deleting customer, please try again");
			                    }
			                   
			                }
			            }
					}
					);//End Action Listener

	        //Exit Button
	        btnExit = new JButton();
	        btnExit.setBackground(new Color(166, 76, 156));
	        btnExit.setForeground(new Color(255, 255, 255));
	        btnExit.setFont(new Font("Tahoma", 0, 12));
	        btnExit.setText("Exit");
	        btnExit.setBorderPainted(false);
	        btnExit.setFocusable(false);
	        btnExit.setPreferredSize(new Dimension(140, 30));
	        btnExit.setMaximumSize(new Dimension(140, 30));
	        btnExit.addActionListener(
		            //Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnExit pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	frame.dispose();
			            }
					}
					);//End Action Listener
	    //Add Buttons
		panelButtons.add(Box.createRigidArea(new Dimension(315,0)));
	    panelButtons.add(btnAmend);
	    panelButtons.add(Box.createRigidArea(new Dimension(10,0)));
	    panelButtons.add(btnDelete);
	    panelButtons.add(Box.createRigidArea(new Dimension(10,0)));
	    panelButtons.add(btnExit);	
	      
	    //Build main panel(Details Panel and Buttons)
	    panelMain = new JPanel();
	    panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.PAGE_AXIS));
	    panelMain.setPreferredSize(new Dimension(795,520 ));
	    //Add components
	    panelMain.add(Box.createRigidArea(new Dimension(0,25)));
	    panelMain.add(panelDetails);
	    panelMain.add(Box.createRigidArea(new Dimension(0,12)));
	    panelMain.add(panelButtons);
	    
	    //Build frame
  		getContentPane().setLayout(new BorderLayout());
  		getContentPane().add(panelTop, BorderLayout.NORTH);
  		getContentPane().add(panelMain, BorderLayout.CENTER);
  		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  		setTitle("Ventair - Edit Customer");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Images/LogoIcon.png")).getImage());
  		setSize(795, 615);
  		setResizable(false);
  		pack();
    }//end buildFrame method
    /**
     * Fill Country and County ComboBoxes when Frame is opened
     */
    private void fillComboBoxes()
    {   
        //Method to fill both ComboBoxes on form
        //Fill countries combobox
        jCombCountry.setModel(new DefaultComboBoxModel(countries.toArray()));
         //Set index to 0
        jCombCountry.setSelectedIndex(0);
        //Fill counties combobox
        jCombCounty.setModel(new DefaultComboBoxModel(countiesIre.toArray()));
         //Set index to 0
        jCombCounty.setSelectedIndex(0);
    }
    /**
     * Method to fill textfield of frame with Customer details
     * @param custID - Integer parameter representing Customer ID of record being amended
     */
    //Method to fill text fields from database
    private void getItemDetails(int custID)
    {
        databaseConnection.ConnectDB con = new databaseConnection.ConnectDB();
        ResultSet rs = null;
        
        String query = "SELECT * FROM  Customer WHERE Customer_ID = ?";
        ArrayList<Object> cond = new ArrayList<Object>();
        cond.add(custID);
               
        try
        {
            //Execute query
            rs = con.returnSetCond(query, cond);
            
            while(rs.next())
            {   
                tFldName.setText(String.valueOf((rs.getObject(2))));
                tFldContact.setText(String.valueOf((rs.getObject(3))));
                tFldAddLine1.setText(String.valueOf((rs.getObject(4))));
                tFldStreet.setText(String.valueOf((rs.getObject(5))));
                tFldTown.setText(String.valueOf((rs.getObject(6))));
                jCombCountry.setSelectedItem(String.valueOf((rs.getObject(8))));
                jCombCounty.setSelectedItem(String.valueOf((rs.getObject(7))));
                tFldPhone.setText(rs.getString(9));
                tFldEmail.setText(rs.getString(10));
                if(!(String.valueOf(rs.getObject(11)).equals("None")))
                {
                    jRBtnVatReg.setSelected(true);
                    tFldVat.setText(rs.getString(11));
                    tFldVat.setEditable(true);
                }
                
                tFldUsername.setText(rs.getString(12));
                tFldPassword.setText(rs.getString(13));
                    
            }
        }
        catch(SQLException e)
        {
           JOptionPane.showMessageDialog(this, "Problem retrieving item details from database.\nPlease contact your network administrator.");
        }
        finally
        {
            
            //Close objects, exceptions handled by databaseConnection.ConnectDB class
            con.closeResultSet(rs);
            con.closeConnection();
        }
    }

}//end class
