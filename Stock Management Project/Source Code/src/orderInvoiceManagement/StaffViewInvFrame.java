//Student Name 	    : Shane Kennedy
//StudentId Number  : C00263504
//Date 	            : March 2022
//Purpose 	    : Customer View Sales Invoice Frame to allow staff to view the details of their Sales Invoices or delete them
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
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import org.jdesktop.swingx.JXImagePanel;

import databaseConnection.ConnectDB;
import driver.CA3Project;

/**
 * Staff View Sales Invoice GUI frame, allows employee to view and delete existing Sales Invoices
 * @author Shane Kennedy
 */
public class StaffViewInvFrame extends JFrame {

    private JButton btnDeleteInv;
    private JButton btnBack;
    private JPanel panelSideMenu;
    private JScrollPane tableScrollPane;
    private JTable jTableOrd;
    private org.jdesktop.swingx.JXImagePanel imageJxPanel;
    private JLabel labelLogo;
    private JLabel labelText;
    private JLabel lblCustName;
    private JLabel lblAddLine1;
    private JLabel lblAddStreet;
    private JLabel lblCountry;
    private JLabel lblCounty;
    private JLabel lblCustID;
    private JLabel lblInvNo;
    private JLabel lblIcon;
    private JLabel lblNetPrice;
    private JLabel lblOrdDate;
    private JLabel lblDelivDate;
    private JLabel lblPhone;
    private JLabel lblPriceexVat;
    private JLabel lblTitle;
    private JLabel lblTotVAT;
    private JLabel lblTown;
    private JLabel lblShipDate;
    private JLabel lblPayDate;
    private JLabel lblVatNo;
    private JPanel panelBottom;
    private JPanel panelBottomRight;
    private JPanel panelTop;
    private JPanel panelInvDetails;
    private JPanel panelAddDetails;
    private JPanel panelPriceDetails;
    private JTextField tFldCustName;
    private JTextField tFldAddLine;
    private JTextField tFldCountry;
    private JTextField tFldCounty;
    private JTextField tFldCustID;
    private JTextField tFldInvNo;
    private JTextField tFldNetPrice;
    private JTextField tFldShipDate;
    private JTextField tFldPayDate;
    private JTextField tFldOrdDate;
    private JTextField tFldVatNo;
    private JTextField tFldPexVAT;
    private JTextField tFldStreet;
    private JTextField tFldTelephone;
    private JTextField tFldTotVAT;
    private JTextField tFldTown;
    private JTextField tFldDelivDate;
    
    //Instance Variables
    private int invoiceID;
    private int orderID;
    
    //Instance variable to store reference to parent frame
    StaffInvFrame parent;
    
    //Create reference for JFrame window
   	StaffViewInvFrame frame = this;
      
    /**
     * Constructor for Staff View Invoice Frame
     * @param invoiceID -Integer parameter for Invoice ID
     * @param parent - Takes a StaffInvFrame object as parameter for reference to parent object
     */
    public StaffViewInvFrame(int invoiceID, StaffInvFrame parent) {
        //Call method to build GUI frame
    	buildFrame();
    	//Set invoiceID parameter to instance variable invoiceID
        this.invoiceID = invoiceID;
        //Set parent reference to instance variable
        this.parent = parent;
        //Call method to fill table with data        
        refreshTableData();
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
	    	//Add image and title labels
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
	        labelText.setText("VENTAIR - SALES INVOICE");
	    //Add components
	    panelTop.add(Box.createRigidArea(new Dimension(38,0)));
	    panelTop.add(labelLogo);
	    panelTop.add(Box.createRigidArea(new Dimension(240,0)));
	    panelTop.add(labelText);

	    //Build side menu panel(delete and back buttons)
		panelSideMenu = new JPanel();
        panelSideMenu.setBackground(new Color(68, 62, 90));
        panelSideMenu.setLayout(new BoxLayout(panelSideMenu,BoxLayout.PAGE_AXIS));
        panelSideMenu.setMinimumSize(new Dimension(150,330));
        panelSideMenu.setMaximumSize(new Dimension(150,520));
        panelSideMenu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
  		panelSideMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
  				//Build buttons
		        btnDeleteInv = new JButton();
		  		btnDeleteInv.setBackground(new Color(166, 76, 156));
		        btnDeleteInv.setForeground(new Color(255, 255, 255));
		        btnDeleteInv.setText("Delete Invoice");
		        btnDeleteInv.setBorderPainted(false);
		        btnDeleteInv.setFocusable(false);
		        btnDeleteInv.setPreferredSize(new Dimension(140, 30));
		        btnDeleteInv.setMaximumSize(new Dimension(140, 30));
		        btnDeleteInv.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnDeleteInv.addActionListener(
		        		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnDeleteInv pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		                // Delete Invoice button
	    		                //Ask user to confirm
	    		                int confirm =  JOptionPane.showConfirmDialog(rootPane, "Are you sure you wish to delete Invoice?");
	    		                if(confirm == 0)
	    		                {
	    		                    int orderID = Integer.parseInt(tFldInvNo.getText());
	    		                    //Create an order object and call delete entry method
	    		                    SalesInvoice inv = new SalesInvoice();
	    		                    int i = inv.deleteEntry(orderID);

	    		                    if(i == 1)
	    		                    {
	    		                        JOptionPane.showMessageDialog(frame, "Invoice successfully deleted");
	    		                        //Refresh Sales Invoice Table
	    		                        parent.refreshTable2Data();
	    		                        frame.dispose();
	    		                    }
	    		                    else
	    		                    {
	    		                        JOptionPane.showMessageDialog(frame, "Problem deleting invoice, please try again");
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
	    					//Handling btnBack pressed action event
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
        panelSideMenu.add(btnDeleteInv);
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
        
        //Build panel for invoice details
        panelInvDetails = new JPanel(new GridLayout(7,2,5,5));
        panelInvDetails.setBackground(new Color(255, 255, 255));
        panelInvDetails.setBounds(20, 90, 265, 240);
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
	        
	        lblInvNo = new JLabel();
	        lblInvNo.setFont(new Font("Segoe UI", 0, 14));
	        lblInvNo.setHorizontalAlignment(SwingConstants.LEFT);
	        lblInvNo.setText("Invoice No::");
	        lblInvNo.setPreferredSize(new Dimension(130,30));
	
	        tFldInvNo = new JTextField();
	        tFldInvNo.setFont(new Font("Segoe UI", 0, 14));
	        tFldInvNo.setHorizontalAlignment(JTextField.CENTER);
	        tFldInvNo.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldInvNo.setDisabledTextColor(new Color(0, 0, 0));
	        tFldInvNo.setEnabled(false);
	        tFldInvNo.setBackground(new Color(242,242,242));
	        tFldInvNo.setPreferredSize(new Dimension(130,30));
	        
	        lblOrdDate = new JLabel();
	        lblOrdDate.setFont(new Font("Segoe UI", 0, 14));
	        lblOrdDate.setHorizontalAlignment(SwingConstants.LEFT);
	        lblOrdDate.setText("Invoice Date:");
	        lblOrdDate.setPreferredSize(new Dimension(130,30));
	
	        tFldOrdDate = new JTextField();
	        tFldOrdDate.setFont(new Font("Segoe UI", 0, 14));
	        tFldOrdDate.setHorizontalAlignment(JTextField.CENTER);
	        tFldOrdDate.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldOrdDate.setDisabledTextColor(new Color(0, 0, 0));
	        tFldOrdDate.setEnabled(false);
	        tFldOrdDate.setBackground(new Color(242,242,242));
	        tFldOrdDate.setPreferredSize(new Dimension(130,30));
	        
	        lblShipDate = new JLabel();
	        lblShipDate.setFont(new Font("Segoe UI", 0, 14));
	        lblShipDate.setHorizontalAlignment(SwingConstants.LEFT);
	        lblShipDate.setText("Shipping Date:");
	        lblShipDate.setPreferredSize(new Dimension(130,30));
	        
	        tFldShipDate = new JTextField();
	        tFldShipDate.setFont(new Font("Segoe UI", 0, 14));
	        tFldShipDate.setHorizontalAlignment(JTextField.CENTER);
	        tFldShipDate.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldShipDate.setDisabledTextColor(new Color(0, 0, 0));
	        tFldShipDate.setEnabled(false);
	        tFldShipDate.setBackground(new Color(242,242,242));
	        tFldShipDate.setPreferredSize(new Dimension(130,30));
	        
	        lblDelivDate = new JLabel();
	        lblDelivDate.setFont(new Font("Segoe UI", 0, 14));
	        lblDelivDate.setHorizontalAlignment(SwingConstants.LEFT);
	        lblDelivDate.setText("Delivery Date:");
	        lblDelivDate.setPreferredSize(new Dimension(130,30));
	        
	        tFldDelivDate = new JTextField();
	        tFldDelivDate.setFont(new Font("Segoe UI", 0, 14));
	        tFldDelivDate.setHorizontalAlignment(JTextField.CENTER);
	        tFldDelivDate.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldDelivDate.setDisabledTextColor(new Color(0, 0, 0));
	        tFldDelivDate.setEnabled(false);
	        tFldDelivDate.setBackground(new Color(242,242,242));
	        tFldDelivDate.setPreferredSize(new Dimension(130,30));
	        
	        lblPayDate = new JLabel();
	        lblPayDate.setFont(new Font("Segoe UI", 0, 14));
	        lblPayDate.setHorizontalAlignment(SwingConstants.LEFT);
	        lblPayDate.setText("Payment Date:");
	        lblPayDate.setPreferredSize(new Dimension(130,30));
	        
	        tFldPayDate = new JTextField();
	        tFldPayDate.setFont(new Font("Segoe UI", 0, 14));
	        tFldPayDate.setHorizontalAlignment(JTextField.CENTER);
	        tFldPayDate.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
	        tFldPayDate.setDisabledTextColor(new Color(0, 0, 0));
	        tFldPayDate.setEnabled(false);
	        tFldPayDate.setBackground(new Color(242,242,242));
	        tFldPayDate.setPreferredSize(new Dimension(130,30));
	        
	        lblVatNo = new JLabel();
	        lblVatNo.setFont(new Font("Segoe UI", 0, 14));
	        lblVatNo.setHorizontalAlignment(SwingConstants.LEFT);
	        lblVatNo.setText("Vat No:");
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
        panelInvDetails.add(lblCustID);
        panelInvDetails.add(tFldCustID);
        panelInvDetails.add(lblInvNo);
        panelInvDetails.add(tFldInvNo);
        panelInvDetails.add(lblOrdDate);
        panelInvDetails.add(tFldOrdDate);
        panelInvDetails.add(lblShipDate);
        panelInvDetails.add(tFldShipDate);
        panelInvDetails.add(lblDelivDate);
        panelInvDetails.add(tFldDelivDate);
        panelInvDetails.add(lblPayDate);
        panelInvDetails.add(tFldPayDate);
        panelInvDetails.add(lblVatNo);
        panelInvDetails.add(tFldVatNo);
        
        //Build panel for address details
        panelAddDetails = new JPanel(new GridLayout(7,2,5,5));
        panelAddDetails.setBackground(new Color(255, 255, 255));
        panelAddDetails.setBounds(430, 90, 265, 240);
        	//Build labels and textfields
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
	    panelAddDetails.add(lblCustName);
	    panelAddDetails.add(tFldCustName);
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
        panelPriceDetails.setBounds(510, 560, 185, 100);
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
	        
	        lblTitle = new JLabel("<HTML><U>Sales Invoice</U></HTML>");
	        lblTitle.setBackground(new Color(255, 255, 255));
	        lblTitle.setFont(new Font("Segoe UI", 1, 20));
	        lblTitle.setForeground(new Color(166, 76, 156));
	        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	        lblTitle.setOpaque(true);
	        lblTitle.setBounds(280,15,150,30);
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
	                	"Product ID", "Product Name", "Quantity", "Price per Unit", "VAT", "Total Price"
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
	            tableScrollPane.setBounds(15,345, 680, 205);
	    //Add components
	    panelBottomRight.add(lblIcon);
	    panelBottomRight.add(lblTitle);
	    panelBottomRight.add(panelInvDetails);
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
  		setTitle("Ventair - View Invoice");
        setIconImage(new ImageIcon(getClass().getResource("/Images/LogoIcon.png")).getImage());
  		setSize(840, 755);
  		setResizable(false);
  		pack();
	    
    }//end buildFrame method

    /**
     * Method to return Customer Details from database, returns the Customer Vat number as a string
     * @return - Returns a String holding the Customer VAT number
     */
    //Method to retrieve Customer Details returns CustomerVATNo
    private String getCustDetails()
    {
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        String custVATNo = "";
        
        try
        {   
            //Create Query inner join Sales Invoice, Sales Order and Customer tables
                String query = "SELECT SalesInvoice.Order_ID, SalesOrder.Customer_ID, Customer_Name, Address_Line_1, Address_Street, Address_Town, Address_County, Address_Country, Customer_Phonenumber, Customer_VatNo, SalesInvoice.Invoice_Date, SalesInvoice.Shipping_Date, SalesInvoice.Delivery_Date, SalesInvoice.Payment_Date FROM Customer Inner Join SalesOrder on SalesOrder.Customer_ID = Customer.Customer_ID Inner Join SalesInvoice on SalesOrder.Order_ID = SalesInvoice.Order_ID ";
                                
            rs = con.returnSet(query);
           
            //Retrieve data from result set to fill textfields
            while(rs.next())
            {  
                //Set value of text fields
                //Set Invoice ID from input parameter
                tFldInvNo.setText(String.valueOf(invoiceID));
                //Get Order ID from Invoice
                orderID = rs.getInt(1);
                //Get customer details from query
                tFldCustID.setText(rs.getString(2));
                tFldCustName.setText(rs.getString(3));
                tFldAddLine.setText(rs.getString(4));
                tFldStreet.setText(rs.getString(5));
                tFldTown.setText(rs.getString(6));
                tFldCounty.setText(rs.getString(7));
                tFldCountry.setText(rs.getString(8));
                tFldTelephone.setText(rs.getString(9));
                custVATNo= rs.getString(10);
                tFldVatNo.setText(custVATNo);
                //Create Date Format Object
                DateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
                //Get dates from query
                Date invDate = rs.getDate(11);
                Date shipDate = rs.getDate(12);
                Date delivDate = rs.getDate(13);
                Date payDate = new Date();
                String payDateS = "";
                //If payment date is not null get and format date otherwise set to none
                if(rs.getDate(14)!= null)
                {
                    payDate = rs.getDate(14);
                    payDateS = formatDate.format(payDate);
                }
                else
                {
                    payDateS = "None";
                }
                //Format Dates and set TextField values
                String invDateS = formatDate.format(invDate);
                tFldOrdDate.setText(invDateS);
                String shipDateS = formatDate.format(shipDate);
                tFldShipDate.setText(shipDateS);
                String delivDateS = formatDate.format(delivDate);
                tFldDelivDate.setText(invDateS);
                tFldPayDate.setText(payDateS);
                
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, "An Error retrieving Customer details, "
                                + "please contact your network administrator.", 
                                "Customer Details Retrieval failed", JOptionPane.ERROR_MESSAGE );
        }
        finally 
        {
            //Close connection objects, exceptions handled by ConnectDB
            con.closeResultSet(rs);
            con.closeConnection();
        }
        return custVATNo;
        
    }
    /**
     * Method to fill the Sales invoice table with data from database
     */
    //Method to fill table details
    public void refreshTableData()
    {  
        //Number Formatter Object to format invoice total text fields to currency
        NumberFormat form =  NumberFormat.getCurrencyInstance();
        //Check customer VAT status
        String custVATNo = getCustDetails();
        //Variable to store VAT rate
        double vatRate = CA3Project.VAT_RATE;
        //If Customer eligible for VAT free purchase
        if(!custVATNo.equals("None"))
        {
           vatRate = 0;
            
        }
        
        ConnectDB con = new ConnectDB();
        ResultSet res = null;
        ResultSet rs = null;
        
        try
        {   //Create table model and set row count to 0 to clear table before filling
            DefaultTableModel tblModel = (DefaultTableModel) jTableOrd.getModel();
            tblModel.setRowCount(0);
            //Create arraylist to hold order items
            ArrayList<OrderItem> items = new ArrayList<OrderItem>();
            //Create query to retrieve Sale Invoice and Product details from database
            String query = "Select Invoice_Order_Item.Product_ID, Quantity, Current_Price, Inventory.Product_Name From Invoice_Order_Item INNER JOIN Inventory on Invoice_Order_Item.Product_ID = Inventory.Product_ID Where Invoice_ID = ?" ;
            res = con.returnSetCond(query, new ArrayList<Object>(Arrays.asList(invoiceID)));
            while(res.next())
            {
                int prodID = res.getInt("Product_ID");
                int quantity = res.getInt("Quantity");
                double price = res.getDouble("Current_Price");
                String prodName = res.getString("Product_Name");
                double grossTotal = price * quantity;
                double VAT =   grossTotal * vatRate;
                double netTotal = grossTotal + VAT;
                Object[] tbData = {prodID,prodName, quantity, grossTotal, VAT,netTotal};
                tblModel = (DefaultTableModel)jTableOrd.getModel();
                tblModel.addRow(tbData);
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
            //Fill total fields with totals formatted to currency
            tFldPexVAT.setText(form.format(totalPrice));
            tFldTotVAT.setText(form.format(totalVat));
            tFldNetPrice.setText(form.format(totalPrice));            
       }
    
            
        catch(SQLException e)
        {   
            JOptionPane.showMessageDialog(null, "An Error retrieving Sales Invoice details, "
                                + "please contact your network administrator.", 
                                "Sales Invoice Retrieval failed", JOptionPane.ERROR_MESSAGE );
        }
        finally 
        {
            //Close connection objects, exceptions handled by ConnectDB
            con.closeResultSet(rs);
            con.closeResultSet(res);
            con.closeConnection();
        }
    }       
}//end class
