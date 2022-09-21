//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Edit Product frame class to edit/delete Product records in database
package inventoryManagement;

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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import databaseConnection.ConnectDB;
import validation.DoubleValidate;
import validation.IntegerValidate;
import validation.JTextFieldEmpty;
import validation.Validator;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;

/**
 * Edit Product GUI frame to allow Staff to edit/delete Inventory records
 * @author Shane Kennedy
 */
public class EditProductFrame extends JFrame {
	
	//Components
    private JButton btnAmend;
    private JButton btnDelete;
    private JButton btnExit;
    private JComboBox<String> comboSuppId;
    private JLabel labelLogo;
    private JLabel labelText;
    private JLabel lblQtyinStock;
    private JLabel lblProdDesc;
    private JLabel lblReorderLevel;
    private JLabel lblRetailPrice;
    private JLabel lblProductName;
    private JLabel lblCostPrice;
    private JLabel lblMaxStock;
    private JLabel lblSuppId;
    private JLabel lblSuppName;
    private JLabel lblQtyOrder;
    private JPanel panelStock;
    private JPanel panelPrice;
    private JPanel panelProdName;
    private JPanel panelDetails;
    private JPanel panelMain;
    private JPanel panelTop;
    private JPanel panelButtons;
    private JTextField tFldQtyinStock;
    private JTextField tFldContact;
    private JTextField tFldRetailPrice;
    private JTextField tFldProductName;
    private JTextField tFldPassword;
    private JTextField tFldCostPrice;
    private JTextField tFldReorderLevel;
    private JTextField tFldTown;
    private JTextField tFldSuppName;
    private JTextField tFldQtyOrder;
    private JTextField tFldMaxStock;
    private JTextArea  tAreaDesc;
    private JScrollPane descScrollPane;
	    
    //Parallel ArrayLists for Supplier ComboBox
    ArrayList<Object> suppID = new ArrayList<Object>();
    ArrayList<Object> suppName = new ArrayList<Object>();
    //Instance variable
    private int pubProdID;
    //Reference to parent
    private InventoryFrame parent;
    
    //Create reference for JFrame window
   	JFrame frame = this;
   	
    /**
     * Constructor for Add Product Frame
     * @param parent -Takes an Inventory Frame object as parameter to serve as a reference to parent object
     */
    public EditProductFrame(int prodID, InventoryFrame parent) //InventoryFrame parent
    {	
    	//Call method to build frame
        buildFrame();
        //Set instance variable
        this.parent = parent;
        pubProdID = prodID;
        //Call method to fill supplier details
        fillComboBoxes();
        //Call method to fill item data from database
        getItemDetails(prodID);
   }

    /**
     * Method to initialize and format  GUI components
     */
    private void buildFrame() 
    {    	
    	//Build panel for Product Name and Description
    	panelProdName = new JPanel();
    	panelProdName.setLayout(null);
    	panelProdName.setBounds(10,20,405, 190);
    	panelProdName.setBorder(BorderFactory.createTitledBorder(null, "Product Details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14), new Color(166, 76, 156)));
    		//Build labels and textfields
	    	lblProductName = new JLabel();
	        lblProductName.setBackground(new Color(68, 62, 90));
	        lblProductName.setFont(new Font("Segoe UI", 0, 14));
	        lblProductName.setForeground(new Color(255, 255, 255));
	        lblProductName.setHorizontalAlignment(SwingConstants.CENTER);
	        lblProductName.setText("Customer Name:");
	        lblProductName.setOpaque(true);
	        lblProductName.setBounds(10,30,150,35);
	        
	        tFldProductName = new JTextField();
	        tFldProductName.setFont(new Font("Segoe UI", 0, 14));
	        tFldProductName.setToolTipText("Product Name Field");
	        tFldProductName.setBounds(165,30,230,35);
	        
	        lblProdDesc = new JLabel();
	        lblProdDesc.setBackground(new Color(68, 62, 90));
	        lblProdDesc.setFont(new Font("Segoe UI", 0, 14));
	        lblProdDesc.setForeground(new Color(255, 255, 255));
	        lblProdDesc.setHorizontalAlignment(SwingConstants.CENTER);
	        lblProdDesc.setText("Description:");
	        lblProdDesc.setOpaque(true);
	        lblProdDesc.setPreferredSize(new Dimension(150, 35));
	        lblProdDesc.setBounds(10,75,150,35);
	        
	        tAreaDesc = new JTextArea();
	        tAreaDesc.setFont(new Font("Segoe UI", 0, 14));
	        tAreaDesc.setColumns(20);
	        tAreaDesc.setRows(5);
	        tAreaDesc.setToolTipText("Description Field");
	        tAreaDesc.setBounds(165,75,230,90);
	        
	        descScrollPane = new JScrollPane();
	        descScrollPane.setViewportView(tAreaDesc);
	        descScrollPane.setBounds(165,75,230,90);
	    //Add components
	    panelProdName.add(lblProductName);
	    panelProdName.add(tFldProductName);
	    panelProdName.add(lblProdDesc);
	    panelProdName.add(descScrollPane);

		//Build panel for Product Price details
	    panelPrice = new JPanel();
	    panelPrice.setLayout(null);
	    panelPrice.setBounds(420,20,325, 190);
	    panelPrice.setBorder(BorderFactory.createTitledBorder(null, "Pricing Details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14), new Color(166, 76, 156)));
    		//Build labels and textfields
	    	lblCostPrice = new JLabel();
	        lblCostPrice.setBackground(new Color(68, 62, 90));
	        lblCostPrice.setFont(new Font("Segoe UI", 0, 14));
	        lblCostPrice.setForeground(new Color(255, 255, 255));
	        lblCostPrice.setHorizontalAlignment(SwingConstants.CENTER);
	        lblCostPrice.setText("Cost Price:");
	        lblCostPrice.setOpaque(true);
	        lblCostPrice.setPreferredSize(new Dimension(150, 35));
	        lblCostPrice.setBounds(15,30,150,35);

	        tFldCostPrice = new JTextField();
	        tFldCostPrice.setFont(new Font("Segoe UI", 0, 14));
	        tFldCostPrice.setToolTipText("Cost Price Field");
	        tFldCostPrice.setBounds(170,30,140,35);
	        
	        lblRetailPrice = new JLabel();
	        lblRetailPrice.setBackground(new Color(68, 62, 90));
	        lblRetailPrice.setFont(new Font("Segoe UI", 0, 14));
	        lblRetailPrice.setForeground(new Color(255, 255, 255));
	        lblRetailPrice.setHorizontalAlignment(SwingConstants.CENTER);
	        lblRetailPrice.setText("Retail Price:");
	        lblRetailPrice.setOpaque(true);
	        lblRetailPrice.setPreferredSize(new Dimension(150, 35));
	        lblRetailPrice.setBounds(15,105,150,35);
	        		
	        tFldRetailPrice = new JTextField();
	        tFldRetailPrice.setFont(new Font("Segoe UI", 0, 14));
	        tFldRetailPrice.setToolTipText("Retail Price Field");
	        tFldRetailPrice.setBounds(170,105,140,35);
	    //Add components
	    panelPrice.add(lblCostPrice);
	    panelPrice.add(tFldCostPrice);
	    panelPrice.add(lblRetailPrice);
	    panelPrice.add(tFldRetailPrice);

	    //Build panel for Product Stock Details
	    panelStock = new JPanel();
	    panelStock.setLayout(null);
	    panelStock.setBounds(10,230,735, 195);
	    panelStock.setBorder(BorderFactory.createTitledBorder(null, "Stock Level Details", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14), new Color(166, 76, 156)));
    		//Build labels,textfields and comboboxes
	    	lblQtyinStock = new JLabel();
	    	lblQtyinStock.setBackground(new Color(68, 62, 90));
	        lblQtyinStock.setFont(new Font("Segoe UI", 0, 14));
	        lblQtyinStock.setForeground(new Color(255, 255, 255));
	        lblQtyinStock.setHorizontalAlignment(SwingConstants.CENTER);
	        lblQtyinStock.setText("Qty in Stock:");
	        lblQtyinStock.setOpaque(true);
	        lblQtyinStock.setBounds(25,35,150,35);
	        
	        tFldQtyinStock = new JTextField();
	        tFldQtyinStock.setFont(new Font("Segoe UI", 0, 14));
	        tFldQtyinStock.setToolTipText("Qty in Stock Field");
	        tFldQtyinStock.setBounds(180,35,140,35);
	        
	        lblMaxStock = new JLabel();
	        lblMaxStock.setBackground(new Color(68, 62, 90));
	        lblMaxStock.setFont(new Font("Segoe UI", 0, 14));
	        lblMaxStock.setForeground(new Color(255, 255, 255));
	        lblMaxStock.setHorizontalAlignment(SwingConstants.CENTER);
	        lblMaxStock.setText("Max Stock Level:");
	        lblMaxStock.setOpaque(true);
	        lblMaxStock.setBounds(25,85,150,35);
	        
	        tFldMaxStock = new JTextField();
	        tFldMaxStock.setFont(new Font("Segoe UI", 0, 14));
	        tFldMaxStock.setToolTipText("Max Stock Level Field");
	        tFldMaxStock.setBounds(180,85,140,35);

	        lblSuppId = new JLabel();
	        lblSuppId.setBackground(new Color(68, 62, 90));
	        lblSuppId.setFont(new Font("Segoe UI", 0, 14));
	        lblSuppId.setForeground(new Color(255, 255, 255));
	        lblSuppId.setHorizontalAlignment(SwingConstants.CENTER);
	        lblSuppId.setText("Supplier ID:");
	        lblSuppId.setOpaque(true);
	        lblSuppId.setPreferredSize(new Dimension(150, 35));
	        lblSuppId.setBounds(25,130,150,35);
	        
	        comboSuppId = new JComboBox<>();
	        comboSuppId.setFont(new Font("Segoe UI", 0, 14));
	        comboSuppId.setToolTipText("Supplier ID Field");
	        comboSuppId.setBounds(180,130,140,35);
	        comboSuppId.addActionListener(
		            //Anonymous Inner Class
					new ActionListener() 
					{
						//Handling comboSuppId changed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	//Index changed event for combobox
			                int id = comboSuppId.getSelectedIndex();
			                tFldSuppName.setText(suppName.get(id).toString());
			            }
					}
					);//End Action Listener

	        lblReorderLevel = new JLabel();
	        lblReorderLevel.setBackground(new Color(68, 62, 90));
	        lblReorderLevel.setFont(new Font("Segoe UI", 0, 14));
	        lblReorderLevel.setForeground(new Color(255, 255, 255));
	        lblReorderLevel.setHorizontalAlignment(SwingConstants.CENTER);
	        lblReorderLevel.setText("Reorder Level:");
	        lblReorderLevel.setOpaque(true);
	        lblReorderLevel.setBounds(420,35,150,35);

	        tFldReorderLevel = new JTextField();
	        tFldReorderLevel.setFont(new Font("Segoe UI", 0, 14));
	        tFldReorderLevel.setToolTipText("Reorder Level Field");
	        tFldReorderLevel.setBounds(575,35,140,35);
	        
	        lblQtyOrder = new JLabel();
	        lblQtyOrder.setBackground(new Color(68, 62, 90));
	        lblQtyOrder.setFont(new Font("Segoe UI", 0, 14));
	        lblQtyOrder.setForeground(new Color(255, 255, 255));
	        lblQtyOrder.setHorizontalAlignment(SwingConstants.CENTER);
	        lblQtyOrder.setText("Qty on Order:");
	        lblQtyOrder.setOpaque(true);
	        lblQtyOrder.setBounds(420,85,150,35);

	        tFldQtyOrder = new JTextField();
	        tFldQtyOrder.setEditable(false);
	        tFldQtyOrder.setFont(new Font("Segoe UI", 0, 14));
	        tFldQtyOrder.setToolTipText("Qty on Order Field");
	        tFldQtyOrder.setBounds(575,85,140,35);   
	        
	        lblSuppName = new JLabel();
	        lblSuppName.setBackground(new Color(68, 62, 90));
	        lblSuppName.setFont(new Font("Segoe UI", 0, 14));
	        lblSuppName.setForeground(new Color(255, 255, 255));
	        lblSuppName.setHorizontalAlignment(SwingConstants.CENTER);
	        lblSuppName.setText("Supplier Name:");
	        lblSuppName.setOpaque(true);
	        lblSuppName.setBounds(350,130,150,35);

	        tFldSuppName = new JTextField();
			tFldSuppName.setFont(new Font("Segoe UI", 0, 14));
	        tFldSuppName.setToolTipText("Supplier Name Field");
	        tFldSuppName.setBounds(505,130,210,35);
	        tFldSuppName.setEditable(false);
	    //Add components
	    panelStock.add(lblQtyinStock);
	    panelStock.add(tFldQtyinStock);
	    panelStock.add(lblMaxStock);
	    panelStock.add(tFldMaxStock);
	    panelStock.add(lblSuppId);
	    panelStock.add(comboSuppId);
	    panelStock.add(lblReorderLevel);
	    panelStock.add(tFldReorderLevel);
	    panelStock.add(lblQtyOrder);
	    panelStock.add(tFldQtyOrder);
	    panelStock.add(lblSuppName);
	    panelStock.add(tFldSuppName);

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
	        labelText.setPreferredSize(new Dimension(350,35));
	        labelText.setMaximumSize(new Dimension(350,35));
	        labelText.setFont(new Font("Segoe UI", 1, 18));
	        labelText.setForeground(new Color(166, 76, 156));
	        labelText.setText("VENTAIR - EDIT/DELETE PRODUCT");
	    //Add components
	    panelTop.add(Box.createRigidArea(new Dimension(25,0)));
	    panelTop.add(labelLogo);
	    panelTop.add(Box.createRigidArea(new Dimension(145,0)));
	    panelTop.add(labelText);
	    
	    //Build Details Panel
	    panelDetails = new JPanel();
	    panelDetails.setLayout(null);
        panelDetails.setBorder(BorderFactory.createEtchedBorder());
        panelDetails.setPreferredSize(new Dimension(755,455));
        panelDetails.setMaximumSize(new Dimension(755,455));
        //Add Detail Panels
        panelDetails.add(panelProdName);
        panelDetails.add(panelPrice);
        panelDetails.add(panelStock);
               
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
	        btnAmend.setPreferredSize(new Dimension(120, 30));
	        btnAmend.setMaximumSize(new Dimension(120, 30));
	        btnAmend.addActionListener(
	            //Anonymous Inner Class
				new ActionListener() 
				{
					//Handling btnAmend pressed action event
		            public void actionPerformed(ActionEvent evt) 
		            {
		            	// Add Item Button Press Event
		                //Validate data
		               Validator val = new Validator();
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
		                                  
		                                  //Check all textfield which should have integer value
		                                  if(d == tFldQtyinStock || d == tFldMaxStock || d == tFldReorderLevel || d == tFldQtyOrder)
		                                  {
		                                     val.IntegerValidate((JTextField)d);
		                                  }
		                                  //Check all textfields which should have double values
		                                  else if(d == tFldCostPrice || d == tFldRetailPrice)
		                                  {
		                                      val.DoubleValidate((JTextField)d);
		                                  }
		                                  //Check for empty textfields
		                                  else
		                                  {
		                                      val.JTextFieldEmpty((JTextField)d);
		                                  }
		                                  
		                               }
		                               //Also check that JTextArea not empty
		                               
		                           }
		                       }
		                   }    
		                   //Handle JTextArea seperately
		                   if(tAreaDesc.getText().equals(""))
		                   {
		                       JOptionPane.showMessageDialog(tAreaDesc,"Description Field is empty, fill field and try again","Description Field Empty", JOptionPane.ERROR_MESSAGE);
		                   }
		                   else
		                   {
		                       //Ask user for confirmation, it yes amend data
		                       int confirm = 0;
		                       confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to amend product data?");
		                       if(confirm == 0)
		                       {   
		                           int suppId = Integer.parseInt(String.valueOf(comboSuppId.getSelectedItem()));
		                           Inventory inv = new Inventory(1, tFldProductName.getText(), tAreaDesc.getText(), Integer.parseInt(tFldQtyinStock.getText()),
		                                                           Integer.parseInt(tFldReorderLevel.getText()), Integer.parseInt(tFldMaxStock.getText()),Double.parseDouble(tFldCostPrice.getText()),
		                                                           Double.parseDouble(tFldRetailPrice.getText()), Integer.parseInt(tFldQtyOrder.getText()),suppId );

		                           int test = inv.updateInvEntry(pubProdID);
		                           //If test returns 0, query was successful
		                           if(test == 1)
		                           {
		                               JOptionPane.showMessageDialog(frame, "Product data successfully amended");
		                               parent.refreshTableData();
		                               frame.dispose();
		                           }
		                           else
		                           {
		                               JOptionPane.showMessageDialog(frame, "Problem adding product, please try again");
		                           }

		                       }
		                   }
		               }//end try
		               catch(JTextFieldEmpty e)
		               {
		                   //JTextFieldEmpty will handle exception
		               }
		               catch(IntegerValidate e)
		               {
		                   //IntegerValidate will handle exception
		               }
		               catch(DoubleValidate e)
		               {
		                   //DoubleValidate will handle exception
		               }
		            }
				}
				);//End Action Listener
	        
	        //Delete Product Button
	        btnDelete = new JButton();
	        btnDelete.setBackground(new Color(166, 76, 156));
	        btnDelete.setForeground(new Color(255, 255, 255));
	        btnDelete.setText("Delete");
	        btnDelete.setBorderPainted(false);
	        btnDelete.setFocusable(false);
	        btnDelete.setPreferredSize(new Dimension(120, 30));
	        btnDelete.setMaximumSize(new Dimension(120, 30));
	        btnDelete.addActionListener(
		            //Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnDelete pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	// Delete Item button event
			                int confirm = 0;
			                confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete product data?");
			                if(confirm == 0)
			                {   
			                    Inventory item = new Inventory();
			                    int test = item.deleteEntry(pubProdID);
			                    if(test == 1)
			                    {
			                        JOptionPane.showMessageDialog(frame, "Product successfully deleted");
			                        parent.refreshTableData();
			                        frame.dispose();
			                    }
			                    else
			                    {
			                        JOptionPane.showMessageDialog(frame, "Problem deleting product, please try again");
			                    }
			                   
			                }
			            }
					}
					);//End Action Listener

	        //Exit Button
	        btnExit = new JButton();
	        btnExit.setBackground(new Color(166, 76, 156));
	        btnExit.setForeground(new Color(255, 255, 255));
	        btnExit.setText("Exit");
	        btnExit.setBorderPainted(false);
	        btnExit.setFocusable(false);
	        btnExit.setPreferredSize(new Dimension(120, 30));
	        btnExit.setMaximumSize(new Dimension(120, 30));
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
		panelButtons.add(Box.createRigidArea(new Dimension(375,0)));
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
	    panelMain.add(Box.createRigidArea(new Dimension(0,30)));
	    panelMain.add(panelDetails);
	    panelMain.add(Box.createRigidArea(new Dimension(0,12)));
	    panelMain.add(panelButtons);
	    
	    //Build frame
  		getContentPane().setLayout(new BorderLayout());
  		getContentPane().add(panelTop, BorderLayout.NORTH);
  		getContentPane().add(panelMain, BorderLayout.CENTER);
  		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
  		setTitle("Ventair - Edit/Delete Product");
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/Images/LogoIcon.png")).getImage());
  		setSize(795, 615);
  		setResizable(false);
  		pack();
    }//end buildFrame method
    /**
     * Method to fill textfields of frame with Inventory details from database
     * @param itemID - Integer parameter representing Item ID of record being amended
     */
    //Method to fill the data about the selected product from database
    private void getItemDetails(int itemID)
    {
        ConnectDB con = new ConnectDB();
        String query = "SELECT * FROM Inventory WHERE Product_ID = ?";
        ArrayList<Object> cond = new ArrayList<Object>();
        cond.add(itemID);
        ResultSet rs = null;
        
        try
        {
            rs = con.returnSetCond(query, cond);
            
            while(rs.next())
            {   
                tFldProductName.setText(String.valueOf((rs.getObject(2))));
                tAreaDesc.setText(String.valueOf((rs.getObject(3))));
                tFldQtyinStock.setText(String.valueOf((rs.getObject(4))));
                tFldReorderLevel.setText(String.valueOf((rs.getObject(5))));
                tFldMaxStock.setText(String.valueOf((rs.getObject(6))));
                tFldCostPrice.setText(String.valueOf((rs.getObject(7))));
                tFldRetailPrice.setText(String.valueOf((rs.getObject(8))));
                tFldQtyOrder.setText(String.valueOf((rs.getObject(9))));
                comboSuppId.setSelectedItem(String.valueOf(rs.getObject(10)));
                
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Problem retrieving item details from database.\nPlease contact your network administrator.");
        }
        finally
        {
            //Close connection objects
            con.closeResultSet(rs);
            con.closeConnection();
        }
        
    }
     /**
     * Method which fills Supplier ID and Supplier Name ArrayLists from database
     */
    //Method to fill supplier array lists from database
    private void fillSuppArray()
    {   
        ConnectDB con = new ConnectDB();
        String query = "SELECT Supplier_ID, Supplier_Name FROM Supplier";
        ResultSet rs = null;
        
        try
        {
            rs = con.returnSet(query);
            
            while(rs.next())
            {   
                suppID.add(rs.getObject(1));
                suppName.add(rs.getObject(2));
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Problem retrieving supplier details from database.\nPlease contact your network administrator.");
        }
        finally
        {
            //Close objects
            con.closeResultSet(rs);
            con.closeConnection();
        }
        
    }
    /**
     * Method which fills Supplier ID Combobox from ArrayList
     */
    private void fillComboBoxes()
    {
        //Fill combo box from supplier arraylists
        fillSuppArray();
        
        for(Object id : suppID)
        {    
            comboSuppId.addItem(id.toString());
            
        }
        comboSuppId.setSelectedIndex(0);
        
    }
}//end class
