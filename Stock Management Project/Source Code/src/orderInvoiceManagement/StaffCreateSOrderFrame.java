//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Frame to allow staff to Create Sales Orders
package orderInvoiceManagement;

//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXImagePanel;

import databaseConnection.ConnectDB;

/**
 * Staff Create Sales Order Frame to allow employees to add new Sales Orders to database
 * @author Shane Kennedy
 */
public class StaffCreateSOrderFrame extends JFrame {

	//Components
    private JButton btnCreateOrder;
    private JButton btnBack;
    private JButton btnConfirmAdd;
    private JButton btnDeleteItem;
    private JButton btnChangeQuan;
    private JPanel panelSideMenu;
    private JPanel panelCustomer;
    private JPanel panelCustSel;
    private JPanel panelCustDetails;
    private JPanel panelItem;
    private JPanel panelItemSel;
    private JPanel panelItemDetails;
    private JScrollPane tableScrollPane;
    private JTable jTableOrd;
    private org.jdesktop.swingx.JXImagePanel imageJxPanel;
    private JLabel labelLogo;
    private JLabel labelText;
    private JLabel lblCustName;
    private JLabel lblCustID;
    private JLabel lblIcon;
    private JLabel lblNetPrice;
    private JLabel lblOrdDate;
    private JLabel lblPriceexVat;
    private JLabel lblTitle;
    private JLabel lblTotVAT;
    private JLabel lblVatNo;
    private JLabel lblQuantity;
    private JLabel lblRetailPrice;
    private JLabel lblItemID;
    private JLabel lblSelDate;
    private JPanel panelBottom;
    private JPanel panelBottomRight;
    private JPanel panelTop;
    private JPanel panelOrderDate;
    private JPanel panelPriceDetails;
    private JPanel panelNestBut;
    private JTextField tFldCustName;
    private JTextField tFldNetPrice;
    private JTextField tFldOrdDate;
    private JTextField tFldVatNo;
    private JTextField tFldPexVAT;
    private JTextField tFldTotVAT;
    private JTextField tFldRetailPrice;
    private JTextField tFldQuantity;
    private JComboBox<String> cmbCust;
    private JComboBox<String> cmbItemID;
    private JXDatePicker jXDateOrder;
    
    //Parallel ArrayLists for Supplier ComboBox
    private final ArrayList<Integer> productID = new ArrayList<>();
    private final ArrayList<String> productName = new ArrayList<>();
    private final ArrayList<Double> currentprice = new ArrayList<>();
    //ArrayList for Customer Details
    private final ArrayList<Integer> custID = new ArrayList<>();
    private final ArrayList<String> custName = new ArrayList<>();
    private final ArrayList<String> custVAT = new ArrayList<>();
    
    //OrderItem and Order Objects for Order details
    private final ArrayList<OrderItem> orderItems = new ArrayList<>();
    
    //Create reference for JFrame window
   	StaffCreateSOrderFrame frame = this;
   	
    //Instance variable to store reference to parent frame
    StaffInvFrame parent;
      
    /**
     * Constructor for Staff Create Sales Order Frame
     * @param parent - Takes a StaffInvFrame object as parameter for reference to parent object
     */
    public StaffCreateSOrderFrame(StaffInvFrame parent) {
        //Call method to build GUI frame
    	buildFrame();
        //Set parent reference to instance variable
        this.parent = parent;
        //Call methods to fill combobox for both Inventory and Customer
        fillCustComboBoxes();
        fillItemComboBoxes();
   }
    /**
     * Method to initialize and format  GUI components
     */
    private void buildFrame() 
    {
        //Build title panel
	    panelTop = new JPanel(); 
	    panelTop.setPreferredSize(new Dimension(870,85));
	    panelTop.setBackground(new Color(68, 62, 90));
	    panelTop.setLayout(new BoxLayout(panelTop,BoxLayout.LINE_AXIS));
	    	//Build image and title labels
		    labelLogo = new JLabel();
		    labelLogo.setPreferredSize(new Dimension(85,85));
		    labelLogo.setMaximumSize(new Dimension(85,85));
		    labelLogo.setIcon(new ImageIcon(getClass().getResource("/Images/logosmall.png")));
	        labelLogo.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));

	        labelText = new JLabel();
	        labelText.setPreferredSize(new Dimension(340,35));
	        labelText.setMaximumSize(new Dimension(340,35));
	        labelText.setFont(new Font("Segoe UI", 1, 18));
	        labelText.setForeground(new Color(166, 76, 156));
	        labelText.setText("VENTAIR - CREATE SALES ORDER");
	    //Add components
	    panelTop.add(Box.createRigidArea(new Dimension(38,0)));
	    panelTop.add(labelLogo);
	    panelTop.add(Box.createRigidArea(new Dimension(220,0)));
	    panelTop.add(labelText);

	    //Build side menu panel(delete and back buttons)
		panelSideMenu = new JPanel();
        panelSideMenu.setBackground(new Color(68, 62, 90));
        panelSideMenu.setLayout(new BoxLayout(panelSideMenu,BoxLayout.PAGE_AXIS));
        panelSideMenu.setMinimumSize(new Dimension(150,190));
        panelSideMenu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
  		panelSideMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
  				//Build buttons
		        btnCreateOrder = new JButton();
		  		btnCreateOrder.setBackground(new Color(166, 76, 156));
		        btnCreateOrder.setForeground(new Color(255, 255, 255));
		        btnCreateOrder.setText("Create Order");
		        btnCreateOrder.setBorderPainted(false);
		        btnCreateOrder.setFocusable(false);
		        btnCreateOrder.setPreferredSize(new Dimension(140, 30));
		        btnCreateOrder.setMaximumSize(new Dimension(140, 30));
		        btnCreateOrder.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnCreateOrder.addActionListener(
		        		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnCreateOrder menu item pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		            	 // Create Order button
	    		                //Check that a date is picked
	    		                if(jXDateOrder.getDate() == null)
	    		                {
	    		                    JOptionPane.showMessageDialog(jXDateOrder, "Please select an order date for order");
	    		                }
	    		                //else if()
	    		                else
	    		                {
	    		                    // Confirm Purchase Button Event
	    		                    int confirm = 0;
	    		                    confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish confirm Order?");
	    		                    if(confirm == 0)
	    		                    {   
	    		                        //Get Customer ID from combobox
	    		                        int index = cmbCust.getSelectedIndex();
	    		                        int cId = custID.get(index);
	    		                        //Create date formatter to convert Dates to sql date format
	    		                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    		                        //Get shipping date from Date Picker and convert to mysql Date format
	    		                        Date orderDate = jXDateOrder.getDate();
	    		                        df.format(orderDate);
	    		                        java.sql.Date sqlOrderDate = new java.sql.Date(orderDate.getTime());
	    		                        //Create order object and use to add order entry to database
	    		                        Order order = new Order(sqlOrderDate, cId, orderItems);
	    		                        int i = order.createOrderItemEntry();
	    		                        if(i == 1)
	    		                        {
	    		                            JOptionPane.showMessageDialog(frame, "Order successfully created. You can view orders in Transaction History.");
	    		                        }
	    		                        //Refresh sales order table and Close frame
	    		                        parent.refreshTableData();
	    		                        frame.dispose();

	    		                    }
	    		                }
	    		            }
	    				}
	    				);//End Action Listener
		        btnDeleteItem = new JButton();
		        btnDeleteItem.setBackground(new Color(166, 76, 156));
		        btnDeleteItem.setForeground(new Color(255, 255, 255));
		        btnDeleteItem.setText("Delete Item");
		        btnDeleteItem.setBorderPainted(false);
		        btnDeleteItem.setFocusable(false);
		        btnDeleteItem.setPreferredSize(new Dimension(140, 30));
		        btnDeleteItem.setMaximumSize(new Dimension(140, 30));
		        btnDeleteItem.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnDeleteItem.addActionListener(
		        		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnDeleteItem menu item pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		                // Delete Item
	    		                // Change Quantity button
	    		                if(jTableOrd.getSelectedRow() == -1)
	    		                {
	    		                    JOptionPane.showMessageDialog(btnDeleteItem, "Please select an item from order");
	    		                }
	    		                else
	    		                {   
	    		                    try
	    		                    {
	    		                        // Confirm Delete Item Button
	    		                        int confirm =  JOptionPane.showConfirmDialog(btnDeleteItem, "Are you sure you wish to delete item from Order?");
	    		                        if(confirm == 0)
	    		                        {   
	    		                            //Remove selected item from order Item array
	    		                            int prodID = Integer.parseInt(jTableOrd.getValueAt(jTableOrd.getSelectedRow(), 0).toString());
	    		                            for(int i = 0; i < orderItems.size(); i++)
	    		                            {
	    		                                if(orderItems.get(i).getProductID() == prodID)
	    		                                {
	    		                                    orderItems.remove(i);
	    		                                }
	    		                            }
	    		                            //Call updateOrder to update totals and table
	    		                            updateOrder();
	    		                            //Update items box to include deleted item
	    		                            fillItemComboBoxes();
	    		                            
	    		                        }
	    		                    }
	    		                    catch(NumberFormatException e)
	    		                    {
	    		                        JOptionPane.showMessageDialog(null, "A problem occured parsing table. Please contact your administrator or try again");
	    		                    }
	    		                }
	    		            }
	    				}
	    				);//End Action Listener
		        btnChangeQuan = new JButton();
		        btnChangeQuan.setBackground(new Color(166, 76, 156));
		        btnChangeQuan.setForeground(new Color(255, 255, 255));
		        btnChangeQuan.setText("Edit Quantity");
		        btnChangeQuan.setBorderPainted(false);
		        btnChangeQuan.setFocusable(false);
		        btnChangeQuan.setPreferredSize(new Dimension(140, 30));
		        btnChangeQuan.setMaximumSize(new Dimension(140, 30));
		        btnChangeQuan.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnChangeQuan.addActionListener(
		        		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnChangeQuan menu item pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		                // Change Quantity button
	    		                if(jTableOrd.getSelectedRow() == -1)
	    		                {
	    		                    JOptionPane.showMessageDialog(btnChangeQuan, "Please select an item from order");
	    		                }
	    		                else
	    		                {
	    		                    //Ask user to input quantity
	    		                    String input = JOptionPane.showInputDialog("Please select a new quantity");
	    		                    //If user does not press cancel
	    		                    if(input!=null)
	    		                    {
	    		                        //Try block to catch non integer values
	    		                        try
	    		                        {   
	    		                            //Change quantity in table
	    		                            int quantity = Integer.parseInt(input);
	    		                            jTableOrd.setValueAt(quantity, jTableOrd.getSelectedRow(), 2);
	    		                            //Change quantity in order item arra
	    		                            int prodID = Integer.parseInt((String)jTableOrd.getValueAt(jTableOrd.getSelectedRow(), 0));
	    		                            for(int i = 0; i < orderItems.size(); i++)
	    		                            {
	    		                                if(orderItems.get(i).getProductID() == prodID)
	    		                                {
	    		                                    orderItems.get(i).setQuantity(quantity);
	    		                                }
	    		                            }
	    		                        }
	    		                        catch(NumberFormatException e)
	    		                        {   
	    		                            JOptionPane.showMessageDialog(btnChangeQuan, "Please enter an integer quantity");
	    		                        }
	    		                    }
	    		                }
	    		            }
	    				}
	    				);//End Action Listener
		        btnBack = new JButton();
		        btnBack.setBackground(new Color(166, 76, 156));
		        btnBack.setForeground(new Color(255, 255, 255));
		        btnBack.setText("Back");
		        btnBack.setToolTipText("");
		        btnBack.setBorderPainted(false);
		        btnBack.setFocusable(false);
		        btnBack.setPreferredSize(new Dimension(140, 30));
		        btnBack.setMaximumSize(new Dimension(140, 30));
		        btnBack.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnBack.addActionListener(
		        		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnBack menu item pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		                // Back to orders button
	    		             	parent.refreshTableData();
	    		                frame.dispose();
	    		            }
	    				}
	    				);//End Action Listener
  		//Add components
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
        panelSideMenu.add(btnCreateOrder);
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
        panelSideMenu.add(btnDeleteItem);
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
        panelSideMenu.add(btnChangeQuan);
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
        panelSideMenu.add(btnBack);
        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
	  		
	    //Build Image Panel
	    imageJxPanel = new JXImagePanel();
	    imageJxPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,105));
	    imageJxPanel.setPreferredSize(new Dimension(155,670));
	    //Set Image icon to be the background for Jxpanel background
        ImageIcon icon = new ImageIcon(getClass().getResource("/Images/Aircon.jpg"));
        Image img = icon.getImage();
        imageJxPanel.setImage(img) ;  
        imageJxPanel.setStyle(JXImagePanel.Style.SCALED);
        //Add components
        imageJxPanel.add(panelSideMenu);
        
        //Build panel for customer select combobox
        panelCustSel = new JPanel(new GridLayout(2,1));
        panelCustSel.setPreferredSize(new Dimension(285,60));
        panelCustSel.setMaximumSize(new Dimension(285,60));
        	//Build label and combobox
        	lblCustID = new JLabel();
        	lblCustID.setBackground(new Color(166, 76, 156));
            lblCustID.setFont(new Font("Segoe UI", 0, 14));
            lblCustID.setForeground(new Color(255, 255, 255));
            lblCustID.setHorizontalAlignment(SwingConstants.CENTER);
            lblCustID.setText("Select Customer");
            lblCustID.setOpaque(true);
        	
        	cmbCust = new JComboBox<>();
        	cmbCust.addActionListener(
    			 new ActionListener() 
    			 {
	                public void actionPerformed(ActionEvent evt) 
	                {
	                	// Combobox customer changed
	                    if(custID.size() != 0)
	                    {
	                        //Get combobox customer
	                        int custIndex = cmbCust.getSelectedIndex();
	                        String name = custName.get(custIndex);
	                        String vat = custVAT.get(custIndex);
	                        tFldCustName.setText(name);
	                        tFldVatNo.setText(vat);
	                        
	                        //Call method to updateOrderdetails based on customers vat details
	                        updateOrder();            
	                    }
                 	}
    			 });
        //Add components
        panelCustSel.add(lblCustID);
        panelCustSel.add(cmbCust);
        
        //Build panel for customer details
        panelCustDetails = new JPanel(null);
        panelCustDetails.setPreferredSize(new Dimension(285,65));
        panelCustDetails.setMaximumSize(new Dimension(285,65));
        panelCustDetails.setBackground(new Color(20, 62, 90));
        panelCustDetails.setBounds(0,0,285,65);
        	//Build label and textfields
	        lblCustName = new JLabel();
	        lblCustName.setBackground(new Color(185, 209, 234));
	        lblCustName.setFont(new Font("Segoe UI", 0, 14));
	        lblCustName.setHorizontalAlignment(SwingConstants.CENTER);
	        lblCustName.setText("Customer Name:");
	        lblCustName.setOpaque(true);
	        lblCustName.setBounds(0,0,120,30);
	        
	        tFldCustName = new JTextField();
	        tFldCustName.setFont(new Font("Segoe UI", 0, 14));
	        tFldCustName.setHorizontalAlignment(JTextField.CENTER);
	        tFldCustName.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldCustName.setDisabledTextColor(new Color(0, 0, 0));
	        tFldCustName.setEnabled(false);
	        tFldCustName.setBounds(125,0,160,30);
	        
	        lblVatNo = new JLabel();     
	        lblVatNo.setBackground(new Color(185, 209, 234));
	        lblVatNo.setFont(new Font("Segoe UI", 0, 14));
	        lblVatNo.setHorizontalAlignment(SwingConstants.CENTER);
	        lblVatNo.setText("VAT No:");
	        lblVatNo.setOpaque(true);
	        lblVatNo.setBounds(0,35,120,30);
	
	        tFldVatNo = new JTextField();
	        tFldVatNo.setFont(new Font("Segoe UI", 0, 14));
	        tFldVatNo.setHorizontalAlignment(JTextField.CENTER);
	        tFldVatNo.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldVatNo.setDisabledTextColor(new java.awt.Color(0, 0, 0));
	        tFldVatNo.setEnabled(false);
	        tFldVatNo.setBounds(125,35,160,30);
        //Add components
	    panelCustDetails.add(lblCustName);
	    panelCustDetails.add(tFldCustName);
	    panelCustDetails.add(lblVatNo);
	    panelCustDetails.add(tFldVatNo);

        //Build panel for select customer(panel select customer and customer details)
    	panelCustomer = new JPanel();
    	panelCustomer.setLayout(new BoxLayout(panelCustomer,BoxLayout.PAGE_AXIS));
    	panelCustomer.setBackground(new Color(20, 62, 90));
    	panelCustomer.setBounds(15, 40, 320, 200);
    	panelCustomer.setBorder(BorderFactory.createTitledBorder(null, "Customer", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14), new Color(166, 76, 156)));
	    //Add components
    	panelCustomer.add(Box.createRigidArea(new Dimension(0,10)));
    	panelCustomer.add(panelCustSel);
    	panelCustomer.add(Box.createRigidArea(new Dimension(0,15)));
    	panelCustomer.add(panelCustDetails);
	    
        //Build panel for customer select combobox
        panelItemSel = new JPanel(new GridLayout(2,1));
        panelItemSel.setPreferredSize(new Dimension(285,60));
        panelItemSel.setMaximumSize(new Dimension(285,60));
        	//Build label and combobox
        	lblItemID = new JLabel();
        	lblItemID.setBackground(new Color(166, 76, 156));
        	lblItemID.setFont(new Font("Segoe UI", 0, 14));
        	lblItemID.setForeground(new Color(255, 255, 255));
        	lblItemID.setHorizontalAlignment(SwingConstants.CENTER);
        	lblItemID.setText("Select Item");
        	lblItemID.setOpaque(true);
        	
        	cmbItemID = new JComboBox<>();
        	cmbItemID.addActionListener(
    			 new ActionListener() 
    			 {
	                public void actionPerformed(ActionEvent evt) 
	                {
	                    //Combobox item changed
	                    //Just so product id arraylist can fill before this method is called
	                    if(productID.size() != 0)
	                    {
	                        //Create currency formatter
	                        NumberFormat form =  NumberFormat.getCurrencyInstance();
	                        //Get combobox item
	                        int prodIndex = cmbItemID.getSelectedIndex();
	                        double price = currentprice.get(prodIndex);
	                        tFldRetailPrice.setText(form.format(price));
	                       
	                    }
                 	}
    			 });
        //Add components
    	panelItemSel.add(lblItemID);
    	panelItemSel.add(cmbItemID);
        
        //Build panel for customer details
        panelItemDetails = new JPanel(null);
        panelItemDetails.setPreferredSize(new Dimension(285,65));
        panelItemDetails.setMinimumSize(new Dimension(285,65));
        panelItemDetails.setMaximumSize(new Dimension(285,65));
        panelItemDetails.setBackground(new Color(20, 62, 90));
        panelItemDetails.setBounds(0,0,285,65);
        	//Build label and textfields
	        lblRetailPrice = new JLabel();
	        lblRetailPrice.setBackground(new Color(185, 209, 234));
	        lblRetailPrice.setFont(new Font("Segoe UI", 0, 14));
	        lblRetailPrice.setHorizontalAlignment(SwingConstants.CENTER);
	        lblRetailPrice.setText("Retail Price:");
	        lblRetailPrice.setOpaque(true);
	        lblRetailPrice.setBounds(0,0 ,120,30);
	        
	        tFldRetailPrice = new JTextField();
	        tFldRetailPrice.setFont(new Font("Segoe UI", 0, 14));
	        tFldRetailPrice.setHorizontalAlignment(JTextField.CENTER);
	        tFldRetailPrice.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldRetailPrice.setDisabledTextColor(new Color(0, 0, 0));
	        tFldRetailPrice.setEnabled(false);
	        tFldRetailPrice.setBounds(125,0 ,160,30);
	        
	        lblQuantity = new JLabel();     
	        lblQuantity.setBackground(new Color(185, 209, 234));
	        lblQuantity.setFont(new Font("Segoe UI", 0, 14));
	        lblQuantity.setHorizontalAlignment(SwingConstants.CENTER);
	        lblQuantity.setText("Quantity:");
	        lblQuantity.setOpaque(true);
	        lblQuantity.setBounds(0,35 ,120,30);
	
	        tFldQuantity = new JTextField();
	        tFldQuantity.setFont(new Font("Segoe UI", 0, 14));
	        tFldQuantity.setHorizontalAlignment(JTextField.CENTER);
	        tFldQuantity.setBorder(javax.swing.BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldQuantity.setDisabledTextColor(new java.awt.Color(0, 0, 0));
	        tFldQuantity.setEnabled(false);
	        tFldQuantity.setBounds(125,35 ,160,30);
        //Add components
        panelItemDetails.add(lblRetailPrice);
        panelItemDetails.add(tFldRetailPrice);
        panelItemDetails.add(lblQuantity);
        panelItemDetails.add(tFldQuantity);

        //Build panel for select items(panel select item, panel item details and add button)
    	panelItem = new JPanel();
    	panelItem.setLayout(new BoxLayout(panelItem,BoxLayout.PAGE_AXIS));
    	panelItem.setBounds(375, 40, 320, 230);
    	panelItem.setBackground(new Color(20, 62, 90));
    	panelItem.setBorder(BorderFactory.createTitledBorder(null, "Items", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14), new Color(166, 76, 156)));
	    	//Build button
            btnConfirmAdd = new JButton();
	        btnConfirmAdd.setBackground(new Color(166, 76, 156));
	        btnConfirmAdd.setForeground(new Color(255, 255, 255));
	        btnConfirmAdd.setText("Confirm Item Add");
	        btnConfirmAdd.setBorderPainted(false);
	        btnConfirmAdd.setFocusable(false);
	        btnConfirmAdd.setPreferredSize(new Dimension(160, 30));
	        btnConfirmAdd.setMaximumSize(new Dimension(160, 30));
	        btnConfirmAdd.setAlignmentX(Component.CENTER_ALIGNMENT);
	        btnConfirmAdd.addActionListener(
	        		//Anonymous Inner Class
					new ActionListener() 
					{
						//Handling btnDeleteInv menu item pressed action event
			            public void actionPerformed(ActionEvent evt) 
			            {
			            	//If user does not press cancel
			                if(tFldQuantity.getText().equals(""))
			                {
			                    JOptionPane.showMessageDialog(tFldQuantity, "Please enter a quantity");
			                }
			                else
			                {   
			                    // Confirm Add Button
			                    int confirm =  JOptionPane.showConfirmDialog(rootPane, "Are you sure you wish to add item?");
			                    if(confirm == 0)
			                    {   
			                        //Check customer VAT status
			                        String custVATNo = tFldVatNo.getText();
			                        //Variable to store VAT rate
			                        double vatRate = driver.CA3Project.VAT_RATE;
			                        //If Customer eligible for VAT free purchase
			                        if(!custVATNo.equals("None"))
			                        {
			                           vatRate = 0;

			                        }
			                        //Try block to catch non integer values
			                        try
			                        {   
			                            //Get Product ID from combobox
			                            int quantity = Integer.parseInt(tFldQuantity.getText());
			                            int prodIndex = cmbItemID.getSelectedIndex();
			                            int prodID = productID.get(prodIndex);
			                            double price = currentprice.get(prodIndex);
			                            String prodName = productName.get(prodIndex);
			                           
			                           
			                            //Create order item and add to order items
			                            OrderItem item = new OrderItem(prodID, quantity, price);
			                            boolean inList = false;
			                            //If item already on order add quantity
			                            for(int i = 0; i < orderItems.size();i++)
			                            {
			                                if(orderItems.get(i).getProductID() == prodID)
			                                {
			                                    int quan = orderItems.get(i).getQuantity();
			                                    orderItems.get(i).setQuantity(quantity + quan);
			                                    inList = true;
			                                }
			                            }
			                            //If item not already on order
			                            if(!inList)
			                            {
			                                orderItems.add(item);
			                            }
			                                                
			                            //Call method to updateOrderdetails
			                            updateOrder();
			                            
			                        }
			                        catch(NumberFormatException e)
			                        {   
			                            JOptionPane.showMessageDialog(btnChangeQuan, "Please enter an integer quantity");
			                        }
			                                       
			                    }
			                }
			            }
					}
					);//End Action Listener
			//Build nested panel for button
			panelNestBut = new JPanel(new FlowLayout(FlowLayout.TRAILING,15,0));
			panelNestBut.setPreferredSize(new Dimension(285,30));
			panelNestBut.setBackground(new Color(20, 62, 90));
			panelNestBut.add(btnConfirmAdd);
    	//Add components
    	panelItem.add(Box.createRigidArea(new Dimension(0,10)));
    	panelItem.add(panelItemSel);
    	panelItem.add(Box.createRigidArea(new Dimension(0,15)));
    	panelItem.add(panelItemDetails);
    	panelItem.add(Box.createRigidArea(new Dimension(0,15)));
    	panelItem.add(panelNestBut);
    	
        //Build panel for order date
        panelOrderDate = new JPanel();
        panelOrderDate.setLayout(new BoxLayout(panelOrderDate,BoxLayout.LINE_AXIS));
        panelOrderDate.setPreferredSize(new Dimension(680,75));
        panelOrderDate.setBackground(new Color(20, 62, 90));
        panelOrderDate.setBorder(BorderFactory.createTitledBorder(null, "Order Date", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Segoe UI", 1, 14), new Color(166, 76, 156)));
        panelOrderDate.setBounds(15, 280, 680, 75);
        	//Build components
	        lblSelDate = new JLabel();
	        lblSelDate.setFont(new Font("Segoe UI", 0, 14));
	        lblSelDate.setHorizontalAlignment(SwingConstants.CENTER);
	        lblSelDate.setBackground(new Color(166, 76, 156));
	        lblSelDate.setOpaque(true);
	        lblSelDate.setForeground(new Color(255, 255, 255));
	        lblSelDate.setText("Select Order Date:");
	        lblSelDate.setPreferredSize(new Dimension(145,35));
	        lblSelDate.setMaximumSize(new Dimension(145,35));
	
	        jXDateOrder = new JXDatePicker();
	        jXDateOrder.setPreferredSize(new Dimension(170,35));
	        jXDateOrder.setMaximumSize(new Dimension(170,35));
	        jXDateOrder.addActionListener(
	        		new ActionListener() 
	        		{
			            public void actionPerformed(ActionEvent evt) 
			            {
			                // Date Picker listener
			                //Create date formatter to convert Dates to sql date format
			                DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			                tFldOrdDate.setText(df.format(jXDateOrder.getDate())); 
			            }
	        		});
       
	        lblOrdDate = new JLabel();
	        lblOrdDate.setFont(new Font("Segoe UI", 0, 14));
	        lblOrdDate.setHorizontalAlignment(SwingConstants.CENTER);
	        lblOrdDate.setText("Order Date:");
	        lblOrdDate.setBackground(new Color(185, 209, 234));
	        lblOrdDate.setOpaque(true);
	        lblOrdDate.setForeground(new Color(0, 0, 0));
	        lblOrdDate.setPreferredSize(new Dimension(120,35));
	        lblOrdDate.setMaximumSize(new Dimension(120,35));
	        lblOrdDate.setBackground(new Color(185, 209, 234));
	
	        tFldOrdDate = new JTextField();
	        tFldOrdDate.setFont(new Font("Segoe UI", 0, 14));
	        tFldOrdDate.setHorizontalAlignment(JTextField.CENTER);
	        tFldOrdDate.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldOrdDate.setDisabledTextColor(new Color(0, 0, 0));
	        tFldOrdDate.setEnabled(false);
	        tFldOrdDate.setBackground(new Color(242,242,242));
	        tFldOrdDate.setPreferredSize(new Dimension(160,35));
	        tFldOrdDate.setMaximumSize(new Dimension(160,35));
	        tFldOrdDate.setEnabled(false);
	    //Add components
	    panelOrderDate.add(Box.createRigidArea(new Dimension(15,0)));
        panelOrderDate.add(lblSelDate);
        panelOrderDate.add(Box.createRigidArea(new Dimension(5,0)));
        panelOrderDate.add(jXDateOrder);
        panelOrderDate.add(Box.createRigidArea(new Dimension(35,0)));
        panelOrderDate.add(lblOrdDate);
        panelOrderDate.add(Box.createRigidArea(new Dimension(5,0)));
        panelOrderDate.add(tFldOrdDate);

        //Build panel for price details
        panelPriceDetails = new JPanel(new GridLayout(1,6,5,5));
        panelPriceDetails.setBackground(new Color(255, 255, 255));
        panelPriceDetails.setBounds(125, 620, 565, 30);
        	//Build labels and textfields
	        lblPriceexVat = new JLabel();
	        lblPriceexVat.setBackground(new Color(32, 177, 191));
	        lblPriceexVat.setForeground(new Color(255, 255, 255));
	        lblPriceexVat.setHorizontalAlignment(SwingConstants.CENTER);
	        lblPriceexVat.setText("Price ex VAT");
	        lblPriceexVat.setOpaque(true);
	        lblPriceexVat.setPreferredSize(new Dimension(90,30));
	
	        tFldPexVAT = new JTextField();
	        tFldPexVAT.setEditable(false);
	        tFldPexVAT.setBackground(new Color(242,242,242));
	        tFldPexVAT.setPreferredSize(new Dimension(90,30));
	        tFldPexVAT.setText("0");
	        	        
	        lblTotVAT = new JLabel();
	        lblTotVAT.setBackground(new Color(32, 177, 191));
	        lblTotVAT.setForeground(new Color(255, 255, 255));
	        lblTotVAT.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTotVAT.setText("Total VAT");
	        lblTotVAT.setOpaque(true);
	        lblTotVAT.setPreferredSize(new Dimension(90,30));
	        
	        tFldTotVAT = new JTextField();
	        tFldTotVAT.setEditable(false);
	        tFldTotVAT.setBackground(new Color(242,242,242));
	        tFldTotVAT.setPreferredSize(new Dimension(90,30));
	        tFldTotVAT.setText("0");
	        
	        lblNetPrice = new JLabel();
	        lblNetPrice.setBackground(new Color(32, 177, 191));
	        lblNetPrice.setForeground(new Color(255, 255, 255));
	        lblNetPrice.setHorizontalAlignment(SwingConstants.CENTER);
	        lblNetPrice.setText("Net Price");
	        lblNetPrice.setOpaque(true);
	        lblNetPrice.setPreferredSize(new Dimension(90,30));
        
	        tFldNetPrice = new JTextField();
	        tFldNetPrice.setEditable(false);
	        tFldNetPrice.setBackground(new Color(242,242,242));
	        tFldNetPrice.setPreferredSize(new Dimension(90,30));
	        tFldNetPrice.setText("0");
	    //Add components
        panelPriceDetails.add(lblPriceexVat);
        panelPriceDetails.add(tFldPexVAT);
        panelPriceDetails.add(lblTotVAT);
        panelPriceDetails.add(tFldTotVAT);
        panelPriceDetails.add(lblNetPrice);
        panelPriceDetails.add(tFldNetPrice);
                
        //Build bottom right panel(main content)
        panelBottomRight = new JPanel();
        panelBottomRight.setLayout(null);
        panelBottomRight.setBackground(new Color(255, 255, 255));
        	//Build components
	        lblIcon = new JLabel();
	        lblIcon.setIcon(new ImageIcon(getClass().getResource("/Images/logosmall.png")));
	        lblIcon.setBounds(10, 10, 80, 80);
	        
	        lblTitle = new JLabel("<HTML><U>Create Sales Order</U></HTML>");
	        lblTitle.setBackground(new Color(255, 255, 255));
	        lblTitle.setFont(new Font("Segoe UI", 1, 20));
	        lblTitle.setForeground(new Color(166, 76, 156));
	        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTitle.setOpaque(true);
	        lblTitle.setBounds(230,10,230,30);
	        //JTable of Sales Orders
	        jTableOrd = new JTable();
	        jTableOrd.setModel(
	        		//Default table model
	        		new DefaultTableModel(
	                new Object [][] {
	                    {null, null, null, null, null, null},
	                    {null, null, null, null, null, null},
	                    {null, null, null, null, null, null},
	                    {null, null, null, null, null, null}
	                },
	                //Column Names
	                new String [] {
	                	"Product ID", "Product Name", "Quantity", "Price exc VAT", "VAT", "Total Price"
	                }
	            ) {
	        		//Column classes
	                Class[] types = new Class [] {
	                    java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
	                };
	                boolean[] canEdit = new boolean [] {
	                    false, false, false, false, false, false
	                };

	                public Class getColumnClass(int columnIndex) {
	                    return types [columnIndex];
	                }

	                public boolean isCellEditable(int rowIndex, int columnIndex) {
	                    return canEdit [columnIndex];
	                }
	            });
	            jTableOrd.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            jTableOrd.setRowHeight(26);
	            jTableOrd.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	            jTableOrd.getTableHeader().setReorderingAllowed(false);
	            //Column widths
                jTableOrd.getColumnModel().getColumn(0).setPreferredWidth(80);
                jTableOrd.getColumnModel().getColumn(1).setResizable(false);
                jTableOrd.getColumnModel().getColumn(1).setPreferredWidth(210);
                jTableOrd.getColumnModel().getColumn(2).setPreferredWidth(70);
                jTableOrd.getColumnModel().getColumn(3).setPreferredWidth(105);
                jTableOrd.getColumnModel().getColumn(4).setPreferredWidth(105);
                jTableOrd.getColumnModel().getColumn(5).setPreferredWidth(105);
	            //Scroll Pane
	            tableScrollPane = new JScrollPane();
	            tableScrollPane.setViewportView(jTableOrd);
	            tableScrollPane.setBounds(15,365, 680, 245);
	           
	    //Add components
	    panelBottomRight.add(lblTitle);
	    panelBottomRight.add(panelCustomer);
	    panelBottomRight.add(panelItem);
	    panelBottomRight.add(panelOrderDate);
	    panelBottomRight.add(tableScrollPane);
	    panelBottomRight.add(panelPriceDetails);
	    
        //Build bottom panel(side menu and main content)
        panelBottom = new JPanel(new BorderLayout());
        panelBottom.add(imageJxPanel, BorderLayout.WEST);
        panelBottom.add(panelBottomRight, BorderLayout.CENTER);
        
	    //Build frame
  		getContentPane().setLayout(new BorderLayout());
  		getContentPane().add(panelTop, BorderLayout.NORTH);
  		getContentPane().add(panelBottom, BorderLayout.CENTER);
  		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  		setTitle("Ventair - Create Sales Order");
        setIconImage(new ImageIcon(getClass().getResource("/Images/LogoIcon.png")).getImage());
  		setSize(840, 755);
  		setResizable(false);
  		pack();
	    
    }//end buildFrame method
   /**
    * Method to update the items on table and total of order text fields when called
    */  
   private void updateOrder()
   {
       //Update prices depending on VAT status of customer
       //Check customer VAT status
       String vat = tFldVatNo.getText();
       //Variable to store VAT rate
       double vatRate = driver.CA3Project.VAT_RATE;
       //If Customer eligible for VAT free purchase
       if(!vat.equals("None"))
       {
          vatRate = 0;
       }
       //Set table model back to 0 rows
       DefaultTableModel tblModel = (DefaultTableModel) jTableOrd.getModel();
       tblModel.setRowCount(0);

       //Variable for totals
       double totalPrice = 0;
       double totalVat = 0;
       double netPrice = 0;
       
       //Run through order items and update prices
       for(int i = 0; i < orderItems.size();i++)
       {   
           //Initalise variables
           double itemPrice = 0;
           double VAT = 0;
           double priceIncVat = 0;
           int quantity =0;
           int prodID = 0;

           //Get Item details
           itemPrice = orderItems.get(i).getCurrentPrice();
           quantity = orderItems.get(i).getQuantity();
           prodID = orderItems.get(i).getProductID();

           //Get product name from arraylist
           int prodIndex = productID.indexOf(prodID);
           String prodName = productName.get(prodIndex);
           
           //Recalculate totals
           itemPrice = itemPrice * quantity;
           VAT = itemPrice * vatRate;
           priceIncVat =  itemPrice + VAT;
           totalPrice += itemPrice;
           totalVat += VAT;
                      
           //Add row back to table
           Object[] tbData = {prodID,prodName, quantity, itemPrice, VAT,priceIncVat};
           tblModel = (DefaultTableModel)jTableOrd.getModel();
           tblModel.addRow(tbData);
       }
       netPrice += (totalPrice + totalVat);
       tblModel.fireTableDataChanged();
       //Create currency formatter
       NumberFormat form =  NumberFormat.getCurrencyInstance();
       //Set total textboxes
       tFldPexVAT.setText(form.format(totalPrice));
       tFldTotVAT.setText(form.format(totalVat));
       tFldNetPrice.setText(form.format(netPrice));  
        
   }
   /**
    * Method to fill Item Combo Box with data from database
    */
   //Method to fill combobox Add Items
   private void fillItemComboBoxes()
   {   
       //Use query to fill with inventory items
       ConnectDB con = new ConnectDB();
       ResultSet rs = null;
       //Create ArrayList for combobox items
       ArrayList<String> comboBoxItems = new ArrayList<>();
       
       //Clear arraylists of items
       productID.clear();
       currentprice.clear();
       productName.clear();
       
       try
       {
           String query = "Select Product_ID, Product_Name, Retail_Price From Inventory";
           rs = con.returnSet(query);
          
           //Fill array list from result set
           while(rs.next())
           {
               int prodID = rs.getInt("Product_ID");
               String prodName = rs.getString("Product_Name");
               Double price = rs.getDouble("Retail_Price");
               //Check if item already on order, if it is don't don't to combobox
              
               //Add to arraylists
               productID.add(prodID);
               currentprice.add(price);
               productName.add(prodName);
               String fillValue = String.valueOf(prodID)+": "+ prodName;
               comboBoxItems.add(fillValue);
               
              
           }
           //Set Model of combobox and set selected index to 0
           cmbItemID.setModel(new DefaultComboBoxModel(comboBoxItems.toArray()));
           if(cmbItemID.getItemAt(0) != null)
           {
               cmbItemID.setSelectedIndex(0);
           }
                
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(this, "A problem occured filling Inventory combobox from database, please contact your network administrator");
           
       }
       //Finally block to close connection
       finally
       {
           
           con.closeConnection();
           con.closeResultSet(rs);
         
       }
         
   }
   /**
    * Method to fill Customer Combo Box with data from database
    */
   //Method to fill combobox Add Items
   private void fillCustComboBoxes()
   {   
       //Use query to fill with inventory items
       ConnectDB con = new ConnectDB();
       ResultSet rs = null;
       //Create ArrayList for combobox items
       ArrayList<String> comboBoxCustomers = new ArrayList<String>();
       
       try
       {
           String query = "Select Customer_ID, Customer_Name, Customer_VatNo From Customer";
           rs = con.returnSet(query);
           //Fill array list from result set
           while(rs.next())
           {
               int id = rs.getInt("Customer_ID");
               String name = rs.getString("Customer_Name");
               String vat = rs.getString("Customer_VatNo");
               //Add to arraylists
               custID.add(id);
               custName.add(name);
               custVAT.add(vat);
               comboBoxCustomers.add(id+": "+name);
               
           }
           //Set Model of combobox and set selected index to 0
           cmbCust.setModel(new DefaultComboBoxModel(comboBoxCustomers.toArray()));
           if(cmbCust.getItemAt(0) != null)
           {
               cmbCust.setSelectedIndex(0);
           }
                
       }
       catch(SQLException e)
       {
           JOptionPane.showMessageDialog(this, "A problem occured filling Customer combobox from database, please contact your network administrator");
       }
       //Finally block to close connection
       finally
       {
           con.closeConnection();
           con.closeResultSet(rs);
       }
         
   }
}//end class
