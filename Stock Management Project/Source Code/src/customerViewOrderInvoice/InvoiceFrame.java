//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Invoice Frame for displaying sales orders and confirming them to checkout
package customerViewOrderInvoice;

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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import org.jdesktop.swingx.JXImagePanel;

import customerOrderPortal.Basket;
import databaseConnection.ConnectDB;
import orderInvoiceManagement.Order;

/**
 * Customer facing GUI frame to allow Customer to view created Sales Orders
 * @author Shane Kennedy
 */
public class InvoiceFrame extends JFrame 
{
	//Declare components
    private JButton btnBack;
    private JButton btnConfirm;
    private JPanel panelSideMenu;
    private JScrollPane tableScrollPane;
    private JTable jTableOrd;
    private org.jdesktop.swingx.JXImagePanel imageJxPanel;
    private JLabel labelLogo;
    private JLabel labelText;
    private JLabel lblAddLine1;
    private JLabel lblAddStreet;
    private JLabel lblCountry;
    private JLabel lblCounty;
    private JLabel lblCustID;
    private JLabel lblCustName;
    private JLabel lblIcon;
    private JLabel lblNetPrice;
    private JLabel lblOrdDate;
    private JLabel lblVatNo;
    private JLabel lblPhone;
    private JLabel lblPriceexVat;
    private JLabel lblTitle;
    private JLabel lblTotVAT;
    private JLabel lblTown;
    private JLabel lblOrdNo;
    private JPanel panelBottom;
    private JPanel panelBottomRight;
    private JPanel panelTop;
    private JPanel panelOrdDetails;
    private JPanel panelAddDetails;
    private JPanel panelPriceDetails;
    private JTextField tFldAddLine;
    private JTextField tFldCountry;
    private JTextField tFldCounty;
    private JTextField tFldCustID;
    private JTextField tFldCustName;
    private JTextField tFldNetPrice;
    private JTextField tFldOrdDate;
    private JTextField tFldOrdNo;
    private JTextField tFldPexVAT;
    private JTextField tFldStreet;
    private JTextField tFldTelephone;
    private JTextField tFldTotVAT;
    private JTextField tFldTown;
    private JTextField tFldVatNo;
    
    //Create reference for JFrame window
   	InvoiceFrame frame = this;
      
    //Instance variables
    private Basket custBasket;    //Store a customer basket object
    
    /**
     * Constructor for Invoice Frame
     * @param custBasket - Takes a Basket object as input parameter
     */  
    public InvoiceFrame(Basket custBasket)	
    {
        //Call method to build GUI frame
    	buildFrame();
    	///Set customer basket instance variable to input basket
        this.custBasket = custBasket;
        //Call method to fill table with data        
        refreshTableData();
    }//end constructor
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
	        labelText.setPreferredSize(new Dimension(290,35));
	        labelText.setMaximumSize(new Dimension(290,35));
	        labelText.setFont(new Font("Segoe UI", 1, 18));
	        labelText.setForeground(new Color(166, 76, 156));
	        labelText.setText("VENTAIR - CONFIRM ORDER");
	    //Add components
	    panelTop.add(Box.createRigidArea(new Dimension(28,0)));
	    panelTop.add(labelLogo);
	    panelTop.add(Box.createRigidArea(new Dimension(240,0)));
	    panelTop.add(labelText);

	    //Build side menu panel(Back button)
		panelSideMenu = new JPanel();
        panelSideMenu.setBackground(new Color(68, 62, 90));
        panelSideMenu.setLayout(new BoxLayout(panelSideMenu,BoxLayout.PAGE_AXIS));
        panelSideMenu.setPreferredSize(new Dimension(150,100));
  		panelSideMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
  				//Build buttons
	  	     	btnBack = new JButton();
		        btnBack.setBackground(new Color(166, 76, 156));
		        btnBack.setForeground(new Color(255, 255, 255));
		        btnBack.setText("Back to Order");
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
	 		                // Back button
	 		                frame.dispose();
	 		            }
	 				}
	 				);//End Action Listener
	        
		        btnConfirm = new JButton();
		        btnConfirm.setBackground(new Color(166, 76, 156));
		        btnConfirm.setForeground(new Color(255, 255, 255));
		        btnConfirm.setText("Back to Order");
		        btnConfirm.setToolTipText("");
		        btnConfirm.setBorderPainted(false);
		        btnConfirm.setFocusable(false);
		        btnConfirm.setPreferredSize(new Dimension(140, 30));
		        btnConfirm.setMaximumSize(new Dimension(140, 30));
		        btnConfirm.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnConfirm.addActionListener(
		        		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnConfirm menu item pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		            	// Confirm Purchase Button Event
	    		               int confirm = 0;
	    		               confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish confirm Order?");
	    		               if(confirm == 0)
	    		               {   
	    		                   //Create todays date
	    		                   Date date = new Date();
	    		                   date.getTime();
	    		                   DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    		                   df.format(date);
	    		                   java.sql.Date sqlDate = new java.sql.Date(date.getTime());
	    		                   //Get Cust Id from textbox
	    		                   int custId = Integer.parseInt(tFldCustID.getText());
	    		                   Order ord1 = new Order(sqlDate, custId,custBasket);  
	    		                   ord1.createOrderItemEntry();
	    		                   custBasket.deleteAllItems();
	    		                   JOptionPane.showMessageDialog(null, "Order successfully created. You can view orders in Transaction History.");
	    		                   frame.dispose();
	    		                   
	    		               }
	    		            }
	    				}
	    				);//End Action Listener
	  		//Add components
	        panelSideMenu.add(Box.createRigidArea(new Dimension(0,15)));
	        panelSideMenu.add(btnBack);
	        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
	        panelSideMenu.add(btnBack);
	        panelSideMenu.add(Box.createRigidArea(new Dimension(0,15)));
	  		
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
        
        //Build panel for order details
        panelOrdDetails = new JPanel(new GridLayout(5,2,5,5));
        panelOrdDetails.setBackground(new Color(255, 255, 255));
        panelOrdDetails.setBounds(20, 100, 265, 170);
        	//Build labels and textfields
	        lblCustID = new JLabel();
	        lblCustID.setFont(new Font("Segoe UI", 0, 14));
	        lblCustID.setHorizontalAlignment(SwingConstants.LEFT);
	        lblCustID.setText("Customer ID:");
	        lblCustID.setPreferredSize(new Dimension(130,30));
	
	        tFldCustID = new JTextField();
	        tFldCustID.setFont(new Font("Segoe UI", 0, 14));
	        tFldCustID.setHorizontalAlignment(JTextField.CENTER);
	        tFldCustID.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldCustID.setDisabledTextColor(new Color(0, 0, 0));
	        tFldCustID.setEnabled(false);
	        tFldCustID.setBackground(new Color(242,242,242));
	        tFldCustID.setPreferredSize(new Dimension(130,30));
	        
	        lblCustName = new JLabel();
	        lblCustName.setFont(new Font("Segoe UI", 0, 14));
	        lblCustName.setHorizontalAlignment(SwingConstants.LEFT);
	        lblCustName.setText("Customer Name:");
	        lblCustName.setPreferredSize(new Dimension(130,30));
	
	        tFldCustName = new JTextField();
	        tFldCustName.setFont(new Font("Segoe UI", 0, 14));
	        tFldCustName.setHorizontalAlignment(JTextField.CENTER);
	        tFldCustName.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldCustName.setDisabledTextColor(new Color(0, 0, 0));
	        tFldCustName.setEnabled(false);
	        tFldCustName.setBackground(new Color(242,242,242));
	        tFldCustName.setPreferredSize(new Dimension(130,30));
	        
	        lblOrdNo = new JLabel();
	        lblOrdNo.setFont(new Font("Segoe UI", 0, 14));
	        lblOrdNo.setHorizontalAlignment(SwingConstants.LEFT);
	        lblOrdNo.setText("Order Number:");
	        lblOrdNo.setPreferredSize(new Dimension(130,30));
	
	        tFldOrdNo = new JTextField();
	        tFldOrdNo.setFont(new Font("Segoe UI", 0, 14));
	        tFldOrdNo.setHorizontalAlignment(JTextField.CENTER);
	        tFldOrdNo.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldOrdNo.setDisabledTextColor(new Color(0, 0, 0));
	        tFldOrdNo.setEnabled(false);
	        tFldOrdNo.setBackground(new Color(242,242,242));
	        tFldOrdNo.setPreferredSize(new Dimension(130,30));
	        
	        lblOrdDate = new JLabel();
	        lblOrdDate.setFont(new Font("Segoe UI", 0, 14));
	        lblOrdDate.setHorizontalAlignment(SwingConstants.LEFT);
	        lblOrdDate.setText("Order Date:");
	        lblOrdDate.setPreferredSize(new Dimension(130,30));
	        
	        tFldOrdDate = new JTextField();
	        tFldOrdDate.setFont(new Font("Segoe UI", 0, 14));
	        tFldOrdDate.setHorizontalAlignment(JTextField.CENTER);
	        tFldOrdDate.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldOrdDate.setDisabledTextColor(new Color(0, 0, 0));
	        tFldOrdDate.setEnabled(false);
	        tFldOrdDate.setBackground(new Color(242,242,242));
	        tFldOrdDate.setPreferredSize(new Dimension(130,30));
	        
	        lblVatNo = new JLabel();
	        lblVatNo.setFont(new Font("Segoe UI", 0, 14));
	        lblVatNo.setHorizontalAlignment(SwingConstants.LEFT);
	        lblVatNo.setText("VAT No:");
	        lblVatNo.setPreferredSize(new Dimension(130,30));
	        
	        tFldVatNo = new JTextField();
	        tFldVatNo.setFont(new Font("Segoe UI", 0, 14));
	        tFldVatNo.setHorizontalAlignment(JTextField.CENTER);
	        tFldVatNo.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldVatNo.setDisabledTextColor(new Color(0, 0, 0));
	        tFldVatNo.setEnabled(false);
	        tFldVatNo.setBackground(new Color(242,242,242));
	        tFldVatNo.setPreferredSize(new Dimension(130,30));
	    //Add components
        panelOrdDetails.add(lblCustID);
        panelOrdDetails.add(tFldCustID);
        panelOrdDetails.add(lblCustName);
        panelOrdDetails.add(tFldCustName);
        panelOrdDetails.add(lblOrdNo);
        panelOrdDetails.add(tFldOrdNo);
        panelOrdDetails.add(lblOrdDate);
        panelOrdDetails.add(tFldOrdDate);
        panelOrdDetails.add(lblVatNo);
        panelOrdDetails.add(tFldVatNo);
        
        //Build panel for address details
        panelAddDetails = new JPanel(new GridLayout(6,2,5,5));
        panelAddDetails.setBackground(new Color(255, 255, 255));
        panelAddDetails.setBounds(430, 65, 265, 205);
        	//Build labels and textfields
	        lblAddLine1 = new JLabel();
	        lblAddLine1.setFont(new Font("Segoe UI", 0, 14));
	        lblAddLine1.setHorizontalAlignment(SwingConstants.LEFT);
	        lblAddLine1.setText("Address Line 1:");
	        lblAddLine1.setPreferredSize(new Dimension(130,30));
	
	        tFldAddLine = new JTextField();
	        tFldAddLine.setFont(new Font("Segoe UI", 0, 14));
	        tFldAddLine.setHorizontalAlignment(JTextField.CENTER);
	        tFldAddLine.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldAddLine.setDisabledTextColor(new Color(0, 0, 0));
	        tFldAddLine.setEnabled(false);
	        tFldAddLine.setBackground(new Color(242,242,242));
	        tFldAddLine.setPreferredSize(new Dimension(130,30));
	        
	        lblAddStreet = new JLabel();
	        lblAddStreet.setFont(new Font("Segoe UI", 0, 14));
	        lblAddStreet.setHorizontalAlignment(SwingConstants.LEFT);
	        lblAddStreet.setText("Street:");
	        lblAddStreet.setPreferredSize(new Dimension(130,30));
	
	        tFldStreet = new JTextField();
	        tFldStreet.setFont(new Font("Segoe UI", 0, 14));
	        tFldStreet.setHorizontalAlignment(JTextField.CENTER);
	        tFldStreet.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldStreet.setDisabledTextColor(new Color(0, 0, 0));
	        tFldStreet.setEnabled(false);
	        tFldStreet.setBackground(new Color(242,242,242));
	        tFldStreet.setPreferredSize(new Dimension(130,30));
	        
	        lblTown = new JLabel();
	        lblTown.setFont(new Font("Segoe UI", 0, 14));
	        lblTown.setHorizontalAlignment(SwingConstants.LEFT);
	        lblTown.setText("Town/City:");
	        lblTown.setPreferredSize(new Dimension(130,30));
	
	        tFldTown = new JTextField();
	        tFldTown.setFont(new Font("Segoe UI", 0, 14));
	        tFldTown.setHorizontalAlignment(JTextField.CENTER);
	        tFldTown.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldTown.setDisabledTextColor(new Color(0, 0, 0));
	        tFldTown.setEnabled(false);
	        tFldTown.setBackground(new Color(242,242,242));
	        tFldTown.setPreferredSize(new Dimension(130,30));
	        
	        lblCounty = new JLabel();
	        lblCounty.setFont(new Font("Segoe UI", 0, 14));
	        lblCounty.setHorizontalAlignment(SwingConstants.LEFT);
	        lblCounty.setText("County:");
	        lblCounty.setPreferredSize(new Dimension(130,30));
	
	        tFldCounty = new JTextField();
	        tFldCounty.setFont(new Font("Segoe UI", 0, 14));
	        tFldCounty.setHorizontalAlignment(JTextField.CENTER);
	        tFldCounty.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldCounty.setDisabledTextColor(new Color(0, 0, 0));
	        tFldCounty.setEnabled(false);
	        tFldCounty.setBackground(new Color(242,242,242));
	        tFldCounty.setPreferredSize(new Dimension(130,30));

	        lblCountry = new JLabel();
	        lblCountry.setFont(new Font("Segoe UI", 0, 14));
	        lblCountry.setHorizontalAlignment(SwingConstants.LEFT);
	        lblCountry.setText("Country:");
	        lblCountry.setPreferredSize(new Dimension(130,30));
	
	        tFldCountry = new JTextField();
	        tFldCountry.setFont(new Font("Segoe UI", 0, 14));
	        tFldCountry.setHorizontalAlignment(JTextField.CENTER);
	        tFldCountry.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldCountry.setDisabledTextColor(new Color(0, 0, 0));
	        tFldCountry.setEnabled(false);
	        tFldCountry.setBackground(new Color(242,242,242));
	        tFldCountry.setPreferredSize(new Dimension(130,30));
	
	        lblPhone = new JLabel();
	        lblPhone.setFont(new Font("Segoe UI", 0, 14));
	        lblPhone.setHorizontalAlignment(SwingConstants.LEFT);
	        lblPhone.setText("Telephone:");
	        lblPhone.setPreferredSize(new Dimension(130,30));
	
	        tFldTelephone = new JTextField();
	        tFldTelephone.setFont(new Font("Segoe UI", 0, 14));
	        tFldTelephone.setHorizontalAlignment(JTextField.CENTER);
	        tFldTelephone.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldTelephone.setDisabledTextColor(new Color(0, 0, 0));
	        tFldTelephone.setEnabled(false);
	        tFldTelephone.setBackground(new Color(242,242,242));
	        tFldTelephone.setPreferredSize(new Dimension(130,30));
	    //Add components
        panelAddDetails.add(lblAddLine1);
        panelAddDetails.add(tFldAddLine);
        panelAddDetails.add(lblAddStreet);
        panelAddDetails.add(tFldStreet);
        panelAddDetails.add(lblTown);
        panelAddDetails.add(tFldTown);
        panelAddDetails.add(lblCounty);
        panelAddDetails.add(tFldCounty);
        panelAddDetails.add(lblCountry);
        panelAddDetails.add(tFldCountry);
        panelAddDetails.add(lblPhone);
        panelAddDetails.add(tFldTelephone);
        
        //Build panel for price details
        panelPriceDetails = new JPanel(new GridLayout(3,2,5,5));
        panelPriceDetails.setBackground(new Color(255, 255, 255));
        panelPriceDetails.setBounds(510, 555, 185, 100);
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
	        lblIcon.setIcon(new ImageIcon(getClass().getResource("/Images/logosmall.png"))); // NOI18N
	        lblIcon.setBounds(10, 10, 80, 80);
	        
	        lblTitle = new JLabel("<HTML><U>Sales Order</U></HTML>");
	        lblTitle.setBackground(new Color(255, 255, 255));
	        lblTitle.setFont(new Font("Segoe UI", 1, 20));
	        lblTitle.setForeground(new Color(166, 76, 156));
	        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTitle.setOpaque(true);
	        lblTitle.setBounds(280,15,140,30);
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
	                    "Product ID", "Product Name", "Quantity", "Price exc Vat", "VAT", "Total Price"
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
                jTableOrd.getColumnModel().getColumn(1).setPreferredWidth(205);
                jTableOrd.getColumnModel().getColumn(2).setPreferredWidth(70);
                jTableOrd.getColumnModel().getColumn(3).setPreferredWidth(105);
                jTableOrd.getColumnModel().getColumn(4).setPreferredWidth(105);
                jTableOrd.getColumnModel().getColumn(5).setPreferredWidth(105);
	            //Scroll Pane
	            tableScrollPane = new JScrollPane();
	            tableScrollPane.setViewportView(jTableOrd);
	            tableScrollPane.setBounds(20,280, 675, 265);
	    //Add components
	    panelBottomRight.add(lblIcon);
	    panelBottomRight.add(lblTitle);
	    panelBottomRight.add(panelOrdDetails);
	    panelBottomRight.add(panelAddDetails);
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
  		setTitle("Ventair - Confirm Order");
        setIconImage(new ImageIcon(getClass().getResource("/Images/LogoIcon.png")).getImage());
  		setSize(840, 755);
  		setResizable(false);
  		pack();
	    
    }//end buildFrame method
    /**
     * Method to return Customer Details from database, returns the Customer Vat number as a string
     * @return - Returns a String holding the Customer VAT number
     */
    private String getCustDetails()
    {
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        String custVATNo = "";
        
        try
        {   
            String query = "SELECT Customer_ID, Customer_Name, Address_Line_1, Address_Street, Address_Town, Address_County, Address_Country, Customer_Phonenumber, Customer_VatNo FROM Customer WHERE Customer_Username = ?";
            
            rs = con.returnSetCond(query, new ArrayList<Object>(Arrays.asList(custBasket.getBasketOwner())));
           
            while(rs.next())
            {  
                tFldCustID.setText(rs.getString(1));
                tFldCustName.setText(rs.getString(2));
                tFldAddLine.setText(rs.getString(3));
                tFldStreet.setText(rs.getString(4));
                tFldTown.setText(rs.getString(5));
                tFldCounty.setText(rs.getString(6));
                tFldCountry.setText(rs.getString(7));
                tFldTelephone.setText(rs.getString(8));                
                custVATNo= rs.getString(9);
                tFldVatNo.setText(custVATNo);
            }
            //Get today's date for order
            LocalDateTime date = LocalDateTime.now();
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String stringDate = date.format(formatDate);
            
            tFldOrdDate.setText(stringDate);
            
           
        }
        catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null, "An Error retrieving Customer details, "
                                + "please contact your network administrator.", 
                                "Customer Details Retrieval failed", JOptionPane.ERROR_MESSAGE );
        }
        finally 
        {
            //Close connection
            con.closeResultSet(rs);
            con.closeConnection();
        }
        return custVATNo;
        
    }
     /**
     * Method to fill the Sales Invoice table with data from database
     */
    public void refreshTableData()
    {  
        NumberFormat form =  NumberFormat.getCurrencyInstance();
        
        //Check customer VAT status
        String custVATNo = getCustDetails();
        //Variable to store VAT rate
        double vatRate = driver.CA3Project.VAT_RATE;
        //If Customer eligible for VAT free purchase
        if(!custVATNo.equals("None"))
        {
           vatRate = 0;
            
        }
        
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        
        try
        {   DefaultTableModel tblModel = (DefaultTableModel) jTableOrd.getModel();
            tblModel.setRowCount(0);
            String query = "Select Basket"+custBasket.getBasketOwner()+".Item_ID, Inventory.Product_Name, Basket"+custBasket.getBasketOwner()+".Quantity, "
                    + "Inventory.Retail_Price FROM Basket"+custBasket.getBasketOwner()+" INNER JOIN Inventory ON Basket"+custBasket.getBasketOwner()+".Item_ID=Inventory.Product_ID";
            
            rs = con.returnSet(query);
            
            while(rs.next())
            {   
                
                int productID = rs.getInt("Item_ID");
                String productName = rs.getString("Product_Name");
                int quantity = rs.getInt("Quantity");
                double retail = rs.getDouble("Retail_Price");
                double vatPrice = (retail * quantity) * vatRate;
                double totPrice = (retail*quantity) + vatPrice;
                Object[] tbData = {productID, productName, quantity, retail, vatPrice, totPrice};
                tblModel = (DefaultTableModel)jTableOrd.getModel();
                
                tblModel.addRow(tbData);
                tblModel.fireTableDataChanged();
                double totalPrice = retail + vatPrice;
            }
            //Calculate price totals for textfields
            double totalPrice = 0;
            double totalVat = 0;
            double netPrice = 0;
            for(int i = 0; i < jTableOrd.getRowCount();i++)
            {
                totalPrice += (double) jTableOrd.getValueAt(i, 5);
                totalVat += (double) jTableOrd.getValueAt(i, 4);
                netPrice +=  totalPrice + totalVat;
            }
            tFldPexVAT.setText(form.format(totalPrice));
            tFldTotVAT.setText(form.format(totalVat));
            tFldNetPrice.setText(form.format(totalPrice));
            
              //Set some of the Jable columns to have centered text
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
                jTableOrd.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
                jTableOrd.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
                jTableOrd.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
                jTableOrd.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
                jTableOrd.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
        }
        catch(SQLException e)
        {       
                JOptionPane.showMessageDialog(null, "An Error retrieving Sales Invoice details, "
                                + "please contact your network administrator.", 
                                "Sales Invoice Retrieval failed", JOptionPane.ERROR_MESSAGE );
        }
        finally 
        {
                 
            con.closeResultSet(rs);
            con.closeConnection();
               
        }
    }
}//end class
