//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Basket Frame for Customer to create Sales Orders
package customerOrderPortal;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import org.jdesktop.swingx.JXImagePanel;

import customerViewOrderInvoice.InvoiceFrame;
import databaseConnection.ConnectDB;

/**
 * GUI Frame where customer can view items added to basket table
 * @author Shane Kennedy
 */
public class BasketFrame extends JFrame {

    private JButton btnRemove;
    private JButton btnQuantity;
    private JButton btnCheckout;
    private JPanel panelSideMenu;
    private JScrollPane tableScrollPane;
    private JTable jTableBasket;
    private org.jdesktop.swingx.JXImagePanel imageJxPanel;
    private JLabel labelLogo;
    private JLabel labelText;
    private JLabel lblUser;
    private JLabel lblNetPrice;
    private JLabel lblPriceexVat;
    private JLabel lblTitle;
    private JLabel lblTotVAT;
    private JPanel panelBottom;
    private JPanel panelBottomRight;
    private JPanel panelTop;
    private JPanel panelPriceDetails;
    private JTextField tFldNetPrice;
    private JTextField tFldPexVAT;
    private JTextField tFldTotVAT;
    
    //Instance Variable stored Basket Object
    private Basket custBasket;
    
    //Reference to parent for calling methods
    private CustPortalFrame parent;
    
    //Create reference for JFrame window
   	BasketFrame frame = this;
      
    /**
     * Constructor for Basket Frame
     * @param custBasket - Takes a Basket Object as a parameter storing basket contents
     * @param parent - Takes a CustPortalFrame object to give a reference to parent frame
     */
    public BasketFrame(Basket custBasket, CustPortalFrame parent) {
        //Call method to build GUI frame
    	buildFrame();
    	//Pass reference to basket to instance variable
        this.custBasket = custBasket;
        //Pass reference to parent frame to instance variable
        this.parent = parent;
        //Set label for User
        lblUser.setText("Username: " +custBasket.getBasketOwner());
        //Call method to fill Basket Table
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
	        labelText.setText("VENTAIR - BASKET");
	    //Add components
	    panelTop.add(Box.createRigidArea(new Dimension(38,0)));
	    panelTop.add(labelLogo);
	    panelTop.add(Box.createRigidArea(new Dimension(240,0)));
	    panelTop.add(labelText);

	    //Build side menu panel(delete and back buttons)
		panelSideMenu = new JPanel();
        panelSideMenu.setBackground(new Color(68, 62, 90));
        panelSideMenu.setLayout(new BoxLayout(panelSideMenu,BoxLayout.PAGE_AXIS));
        panelSideMenu.setPreferredSize(new Dimension(150,130));
  		panelSideMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
  				//Build buttons
		        btnRemove = new JButton();
		  		btnRemove.setBackground(new Color(166, 76, 156));
		        btnRemove.setForeground(new Color(255, 255, 255));
		        btnRemove.setText("Remove Item");
		        btnRemove.setFont(new Font("Segoe UI", 0, 12));
		        btnRemove.setBorderPainted(false);
		        btnRemove.setFocusable(false);
		        btnRemove.setPreferredSize(new Dimension(140, 30));
		        btnRemove.setMaximumSize(new Dimension(140, 30));
		        btnRemove.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnRemove.addActionListener(
		        		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnRemove menu item pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		            	// Remove item from basket button event
	    		                // Delete Item button event
	    		                int confirm = 0;
	    		                confirm = JOptionPane.showConfirmDialog(null, "Are you sure you wish to delete item from basket?");
	    		                if(confirm == 0)
	    		                {   
	    		                    int itemID = Integer.parseInt(String.valueOf(jTableBasket.getValueAt(jTableBasket.getSelectedRow(), 0)));
	    		                    custBasket.deleteItemfromTable(itemID);
	    		                    
	    		                    JOptionPane.showMessageDialog(frame, "Item Removed from Basket");
	    		                }
	    		                refreshTableData();
	    		                parent.updateLabel();
	    		            }
	    				}
	    				);//End Action Listener
		        btnQuantity = new JButton();
		        btnQuantity.setBackground(new Color(166, 76, 156));
		        btnQuantity.setForeground(new Color(255, 255, 255));
		        btnQuantity.setText("Change Quantity");
		        btnQuantity.setFont(new Font("Segoe UI", 0, 12));
		        btnQuantity.setBorderPainted(false);
		        btnQuantity.setFocusable(false);
		        btnQuantity.setPreferredSize(new Dimension(140, 30));
		        btnQuantity.setMaximumSize(new Dimension(140, 30));
		        btnQuantity.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnQuantity.addActionListener(
		        		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnQuantity menu item pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		            	// Quantity change button event
	    		                int itemID = Integer.parseInt(String.valueOf(jTableBasket.getValueAt(jTableBasket.getSelectedRow(), 0)));
	    		                String input = JOptionPane.showInputDialog("Choose new quantity for item: ");
	    		                int quantity = Integer.parseInt(input);
	    		                if(quantity == 0)
	    		                {
	    		                    custBasket.deleteItemfromTable(itemID);
	    		                    
	    		                 
	    		                }
	    		                else
	    		                {
	    		                    custBasket.changeItemQuantity(itemID, quantity);
	    		                }  
	    		                refreshTableData();
	    		            }
	    				}
	    				);//End Action Listener
		        btnCheckout = new JButton();
		        btnCheckout.setBackground(new Color(166, 76, 156));
		        btnCheckout.setForeground(new Color(255, 255, 255));
		        btnCheckout.setText("Checkout");
		        btnCheckout.setFont(new Font("Segoe UI", 0, 12));
		        btnCheckout.setToolTipText("");
		        btnCheckout.setBorderPainted(false);
		        btnCheckout.setFocusable(false);
		        btnCheckout.setPreferredSize(new Dimension(140, 30));
		        btnCheckout.setMaximumSize(new Dimension(140, 30));
		        btnCheckout.setAlignmentX(Component.CENTER_ALIGNMENT);
		        btnCheckout.addActionListener(
		        		//Anonymous Inner Class
	    				new ActionListener() 
	    				{
	    					//Handling btnCheckout menu item pressed action event
	    		            public void actionPerformed(ActionEvent evt) 
	    		            {
	    		            	// Checkout Button press event
	    		                InvoiceFrame frm = new InvoiceFrame(custBasket);
	    		                frm.setVisible(true);
	    		                frame.hide();
	    		            }
	    				}
	    				);//End Action Listener
	  		//Add components
	        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
	        panelSideMenu.add(btnRemove);
	        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
	        panelSideMenu.add(btnQuantity);
	        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
	        panelSideMenu.add(btnCheckout);
	        panelSideMenu.add(Box.createRigidArea(new Dimension(0,10)));
	  		
	    //Build Image Panel
	    imageJxPanel = new JXImagePanel();
	    imageJxPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,105));
	    imageJxPanel.setPreferredSize(new Dimension(155,425));
	    imageJxPanel.setMaximumSize(new Dimension(155,425));
	    //Set Image icon to be the background for Jxpanel background
        ImageIcon icon = new ImageIcon(getClass().getResource("/Images/Aircon.jpg"));
        Image img = icon.getImage();
        imageJxPanel.setImage(img) ;  
        imageJxPanel.setStyle(JXImagePanel.Style.SCALED);
        //Add components
        imageJxPanel.add(panelSideMenu);

        //Build panel for price details
        panelPriceDetails = new JPanel(new GridLayout(3,2,5,5));
        panelPriceDetails.setBackground(new Color(185,209,234));
        panelPriceDetails.setBounds(510, 310, 185, 100);
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
        panelBottomRight.setBackground(new Color(185,209,234));
        	//Build components
	        lblUser = new JLabel();
	        lblUser.setBackground(new Color(185, 209, 234));
	        lblUser.setFont(new Font("Segoe UI", 0, 14));
	        lblUser.setHorizontalAlignment(SwingConstants.LEFT);
	        lblUser.setText("Username:");
	        lblUser.setBorder(BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	        lblUser.setOpaque(true);
	        lblUser.setBounds(20, 5, 200, 30);
	        
	        lblTitle = new JLabel("<HTML><U>Basket Contents</U></HTML>");
	        lblTitle.setBackground(new Color(32, 177, 191));
	        lblTitle.setFont(new Font("Segoe UI", 0, 18));
	        lblTitle.setForeground(new Color(255, 255, 255));
	        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        lblTitle.setOpaque(true);
	        lblTitle.setBounds(225,5,210,30);
	        
	        //JTable of Sales Orders
	        jTableBasket = new JTable();
	        jTableBasket.setModel(
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
	            jTableBasket.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	            jTableBasket.setRowHeight(26);
	            jTableBasket.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	            jTableBasket.getTableHeader().setReorderingAllowed(false);
	            //Column widths
                jTableBasket.getColumnModel().getColumn(0).setPreferredWidth(80);
                jTableBasket.getColumnModel().getColumn(1).setResizable(false);
                jTableBasket.getColumnModel().getColumn(1).setPreferredWidth(210);
                jTableBasket.getColumnModel().getColumn(2).setPreferredWidth(70);
                jTableBasket.getColumnModel().getColumn(3).setPreferredWidth(105);
                jTableBasket.getColumnModel().getColumn(4).setPreferredWidth(105);
                jTableBasket.getColumnModel().getColumn(5).setPreferredWidth(105);
	            //Scroll Pane
	            tableScrollPane = new JScrollPane();
	            tableScrollPane.setViewportView(jTableBasket);
	            tableScrollPane.setBounds(20,50, 680, 245);
	    //Add components
	    panelBottomRight.add(lblUser);
	    panelBottomRight.add(lblTitle);
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
  		setTitle("Ventair - Basket");
        setIconImage(new ImageIcon(getClass().getResource("/Images/LogoIcon.png")).getImage());
  		setSize(710, 510);
  		setResizable(false);
  		pack();
	    
    }//end buildFrame method
    /**
     * Method to get Customer VAT status
     * @return - Returns a string representing Customer VAT Number
     */
    private String checkVATStatus()
    {
        //Create Connection Objects
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        String custVATNo = "";
        
        try
        {   //Create query to retrive Customer VAT number from database
            String query = "SELECT Customer_VatNo FROM Customer WHERE Customer_Username = ?";
            rs = con.returnSetCond(query, new ArrayList<Object>(Arrays.asList(custBasket.getBasketOwner())));
           
            while(rs.next())
            {  
                custVATNo= rs.getString(1);
            }
           
        }
        catch(SQLException e)
        {
           JOptionPane.showMessageDialog(null, "An Error retrieving Customer VAT Number, "
                                + "please contact your network administrator.", 
                                "Customer VAT Number Retrieval failed", JOptionPane.ERROR_MESSAGE );
        }
        finally 
        {
            //Close connection objects, exception handled by ConnectDB
            con.closeResultSet(rs);
            con.closeConnection();
        }
        return custVATNo;
        
    }
    /**
     * Method to fill Basket Table with data
     */
    //Method to fill Basket Table with data
    public void refreshTableData()
    {  
        //Number format object to format double as currency
        NumberFormat form =  NumberFormat.getCurrencyInstance();
        //Check customer VAT status
        String custVATNo = checkVATStatus();
        //Variable to store VAT rate
        double vatRate = 0;
        //If Customer eligible for VAT free purchase
        if(custVATNo.equals("None"))
        {
           //Set to Constant Vat Rate
           vatRate = driver.CA3Project.VAT_RATE;
            
        }
        
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        
        try
        {   DefaultTableModel tblModel = (DefaultTableModel) jTableBasket.getModel();
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
                //String vatPricefpr = form.format(String.valueOf(vatPrice));
                Object[] tbData = {productID, productName, quantity, retail, vatPrice, totPrice};
                tblModel = (DefaultTableModel)jTableBasket.getModel();
                
                tblModel.addRow(tbData);
                tblModel.fireTableDataChanged();
                double totalPrice = retail + vatPrice;
                //tFldPexVAT.setText(Double.toString(Double.parseDouble(tFldPexVAT.getText())+retail));
                //tFldTotVAT.setText(Double.toString(Double.parseDouble(tFldTotVAT.getText())+vatPrice));
                //tFldNetPrice.setText(Double.toString(Double.parseDouble(tFldNetPrice.getText())+totalPrice));
            }
            //Calculate price totals for textfields
            double totalPrice = 0;
            double totalVat = 0;
            double netPrice = 0;
            for(int i = 0; i < jTableBasket.getRowCount();i++)
            {
                totalPrice += (double) jTableBasket.getValueAt(i, 5);
                totalVat += (double) jTableBasket.getValueAt(i, 4);
                netPrice +=  totalPrice + totalVat;
            }
            
            
            tFldPexVAT.setText(form.format(totalPrice));
            tFldTotVAT.setText(form.format(totalVat));
            tFldNetPrice.setText(form.format(totalPrice));
            
            //Set some of the Jable columns to have centered text
            DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
            centerRenderer.setHorizontalAlignment( SwingConstants.CENTER );
            jTableBasket.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
            jTableBasket.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
            jTableBasket.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
            jTableBasket.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
            jTableBasket.getColumnModel().getColumn(5).setCellRenderer( centerRenderer );
         
        }
        catch(SQLException e)
        {      JOptionPane.showMessageDialog(null, "An Error retrieving Basket details, "
                                + "please contact your network administrator.", 
                                "Basket Retrieval failed", JOptionPane.ERROR_MESSAGE );
        }
        finally 
        {
            //Close connection objects, exceptions handled by COnnect DB
            con.closeResultSet(rs);
            con.closeConnection();
        }
    }      
}//end class
