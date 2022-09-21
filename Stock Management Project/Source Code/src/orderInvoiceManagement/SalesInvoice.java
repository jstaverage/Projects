//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : SalesInvoice class to generae SalesInvoice objects
package orderInvoiceManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;

import databaseConnection.ConnectDB;

/**
 * Sales invoice objects to represents instances of Sales Invoices
 * @author Shane Kennedy
 */
public class SalesInvoice 
{
    //Instance Variables
    private int invoiceID;
    private int orderID;
    private Date invoiceDate;
    private Date shippingDate;
    private Date deliveryDate;
    private Date paymentDate;
    private ArrayList<OrderItem> items;
    private int custID;  //Customer ID for calculating net price
    /**
     * Constructor for Sales Invoice
     * @param orderID - Integer Parameter representing Order ID
     * @param invoiceDate - Date Object Parameter representing Invoice Date
     * @param shippingDate - Date Object Parameter representing Shipping Date
     * @param deliveryDate - Date Object Parameter representing Delivery Date
     * @param paymentDate - Date Object Parameter representing Payment Date
     * @param items - ArrayList of Order Items representing Items on order
     */
    //Constructor with payment date
    public SalesInvoice(int orderID, Date invoiceDate,Date shippingDate,Date deliveryDate, Date paymentDate ,ArrayList<OrderItem> items)
    {
        this.invoiceID = 0;
        this.orderID = orderID;
        this.invoiceDate = invoiceDate;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
        this.paymentDate = this.paymentDate;
        this.items = items;
    }
    /**
     * Constructor for Sales Invoice without Payment Date
     * @param orderID - Integer Parameter representing Order ID
     * @param invoiceDate - Date Object Parameter representing Invoice Date
     * @param shippingDate - Date Object Parameter representing Shipping Date
     * @param deliveryDate - Date Object Parameter representing Delivery Date
     * @param items - ArrayList of Order Items representing Items on order
     */
     //Constructor without payment date
    public SalesInvoice(int orderID, Date invoiceDate,Date shippingDate,Date deliveryDate, ArrayList<OrderItem> items)
    {
        this.invoiceID = 0;
        this.orderID = orderID;
        this.invoiceDate = invoiceDate;
        this.shippingDate = shippingDate;
        this.deliveryDate = deliveryDate;
        this.paymentDate = null;
        this.items = items;
    }
    /**
     * Constructor for Sales Invoice with only payment date parameter
     * @param paymentDate - Date Object Parameter representing Payment Date
     */
    //Constructor with just payment date
    public SalesInvoice(Date paymentDate)
    {
        this.invoiceID = 0;
        this.orderID = 0;
        this.invoiceDate = null;
        this.shippingDate = null;
        this.deliveryDate = null;
        this.paymentDate = paymentDate;
        this.items = items;
    }
    /**
     * 
     * @param invoiceID - Integer Parameter representing Order ID
     * @param custID - Customer ID for calculating net total
     * @param items - ArrayList of Order Items representing Items on invoice
     */
    public SalesInvoice(int invoiceID, int custID, ArrayList<OrderItem> items)
    {
        this.invoiceID = invoiceID;
        this.orderID = 0;
        this.invoiceDate = null;
        this.shippingDate = null;
        this.deliveryDate = null;
        this.paymentDate = null;
        this.items = items;
        this.custID = custID;
    }
    
    /**
     * Default Constructor for Sales Invoice
     */
    //Empty constructor
    public SalesInvoice()
    {
        this.invoiceID = 0;
        this.orderID = 0;
        this.invoiceDate = null;
        this.shippingDate = null;
        this.deliveryDate = null;
        this.paymentDate = null;
        this.items = null;
    }
    
    //Getters and Setters
    /**
     * Setter method for Invoice ID
     * @param invoiceID - Integer parameter for Invoice ID
     */
    public void setInvoiceID(int invoiceID)
    {
        this.invoiceID = invoiceID;
    }
    /**
     * Getter method for Invoice ID
     * @return - Returns an integer for Invoice ID
     */
    public int getInvoiceID()
    {
        return invoiceID;
    }
    /**
     * Getter Method for Order ID
     * @return - Returns an integer for Invoice ID
     */
    public int getOrderID() {
        return orderID;
    }
    /**
     * Setter method for Order ID
     * @param orderID - Integer parameter for Order ID
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    /**
     * Setter method for Invoice Date
     * @param invoiceDate - Date Object parameter for Order Date
     */
    public void setInvoiceDate(Date invoiceDate)
    {
        this.invoiceDate = invoiceDate;
    }
    /**
     * Getter Method for Invoice Date
     * @return - Returns a Date Object for Invoice Date
     */
    public Date getInvoiceDate()
    {
        return invoiceDate;
    }
    /**
     * Getter Method for Shipping Date
     * @return - Returns a Date Object for Shipping Date
     */
    public Date getShippingDate() {
        return shippingDate;
    }
    /**
     * Setter method for Shipping Date
     * @param shippingDate - Date Object parameter for Shipping Date
     */
    public void setShippingDate(Date shippingDate) {
        this.shippingDate = shippingDate;
    }
    /**
     * Getter Method for Delivery Date
     * @return - Returns a Date Object for Delivery Date
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }
     /**
     * Setter method for Delivery Date
     * @param deliveryDate - Date Object parameter for Delivery Date
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    /**
     * Getter Method for Payment Date
     * @return - Returns a Date Object for Payment Date
     */
    public Date getPaymentDate() {
        return paymentDate;
    }
     /**
     * Setter method for Payment Date
     * @param paymentDate - Date Object parameter for Payment Date
     */
    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
    /**
     * Setter method for Order Item ArrayList
     * @param items - Takes a ArrayList of Order Items as parameter representing Items on Invoice
     */
    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }
    /**
     * Getter method for Order Items ArrayList
     * @return - Returns an ArrayList of Order Items representing items on Invoice
     */
    public ArrayList<OrderItem> getItems() {
        return items;
    }
    /**
     * Method which calculates Gross Total Price of Invoice
     * @return - Returns a double representing Gross Total of Invoice
     */
    //Method to calculate Gross Total of Invoice
    public double calculateGrossTotal()
    {
        double result = 0;
        for(int i = 0; i < items.size(); i++)
        {
            result += items.get(i).getQuantity() * items.get(i).getCurrentPrice();
        }
        return result;
    }
    /**
     * Method which calculates Net Total of Invoice Object
     * @return - Returns a double representing Net Total of Invoice
     */
    //Method to calculate Net Total of Invoice
    public double calculateNetTotal()
    {
        double result = 0;
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        String custVATNo = "";
        
        try
        {   
            //Customer ID to get Customer VAT no for Net Total Calculation
            String query = "SELECT Customer_VatNo FROM Customer WHERE Customer_ID = ?";
            
            rs = con.returnSetCond(query, new ArrayList<Object>(Arrays.asList(custID)));
           
            while(rs.next())
            {  
                custVATNo= rs.getString(1);
            }
           
        }//end try block
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "An unknown error has occured while querying database for VATNo, "
                                               + "please contact your network administrator.", 
                                                "Unknown error querying database", JOptionPane.ERROR_MESSAGE );
        }//end catch block
        //Finally block to close connection
        finally 
        {
                 try 
                 {
                      con.closeConnection();
                 }
                 catch(Exception e)
                 {
                     JOptionPane.showMessageDialog(null, "An unknown error has occured while closing connections, "
                                               + "please contact your network administrator.", 
                                                "Unknown error closing connections", JOptionPane.ERROR_MESSAGE );
                 }
         }
        double vatRate = 0;
        
        //If Customer eligible for VAT free purchase
        if(custVATNo.equals("None"))
        {
            vatRate = driver.CA3Project.VAT_RATE;
                       
        }
        //Calculate Net Total based on Vat free status of cutsomer
        for(int i = 0; i < items.size(); i++)
        {
            result += items.get(i).getQuantity() * (items.get(i).getCurrentPrice()+ (items.get(i).getCurrentPrice()*vatRate));
        }
        return result;
    }
     /**
     * Method which creates an ArrayList of objects for prepared statement method of ConnectDB
     * @return - Returns an ArrayList of objects representing Invoice variable 
     */
    //Method to create ArrayList of value to make prepared statements as per the ConnectDB object
    public ArrayList<Object> createValueList()
	{
		ArrayList<Object> values = new ArrayList<Object>(Arrays.asList(this.orderID, this.invoiceDate,this.shippingDate, this.deliveryDate));
		
		return values;
	}
     /**
     * Method to add Sales Invoice to database
     * @return - - Returns an integer representing success or failure, success will be 1, failure will be 0
     */
    //Method to insert Sales Invoice into database, return integer 1 if successful
    public int createInvoiceEntry()
    {	
            ResultSet res = null;
            ResultSet rs = null;
            int invoiceID = 0 ;
            ConnectDB con = new ConnectDB();
            ConnectDB con2 = new ConnectDB();
            try
            {
                //Insert Order into Sales Order Table
                int i=0;
                String query = 	"INSERT INTO SalesInvoice (Order_ID, Invoice_Date, Shipping_Date, Delivery_Date)"
                                + " VALUES (?,?,?,?)";
                i = con.prepareStatement(query, this.createValueList());
                //If successful get value of newly created Invoice ID
                if(i == 1)
                {   
                    query = "Select * from SalesInvoice WHERE Order_ID = ?";

                    res=con.returnSetCond(query,new ArrayList<Object>(Arrays.asList(orderID)));
                    while(res.next()) 
                    {   
                        //Get new sales invoice ID
                        invoiceID = res.getInt(1);

                    }
                    query = "Select * from Order_Item WHERE Order_ID =?";

                    rs=con2.returnSetCond(query, new ArrayList<Object>(Arrays.asList(orderID)));
                    //Get items from Order_Item to add to Invoice_Order_Item table
                    while(rs.next()) 
                    {   
                        int productID = rs.getInt("Product_ID");
                        int quantity = rs.getInt("Quantity");
                        double price = rs.getInt("Current_Price");                        

                        query = "INSERT INTO Invoice_Order_Item (Invoice_ID, Product_ID, Quantity, Current_Price)"
                                    + " VALUES (?,?,?,?)";
                        i = con.prepareStatement(query, new ArrayList<>(Arrays.asList(invoiceID, 
                                productID, quantity, price)));
                    }
                    return i;

                }
                else
                {
                    return 0;
                }
            }
            catch(SQLException e)
            {       
                    JOptionPane.showMessageDialog(null, "An unknown error has occured while querying database, "
                                           + "please contact your network administrator.", 
                                            "Unknown error querying database", JOptionPane.ERROR_MESSAGE );
                    return 0;
            }
            finally
            {
               con.closeConnection();
               con.closeResultSet(res);
               con.closeResultSet(rs);
            }
    }
     /**
        * Method to amend the details of an Invoice object in the database
        * @param invoiceID - Integer representing order ID of record to be amended
        * @param paymentDate - Sql date object parameter for Payment Date
        * @return - Returns an integer representing success or failure, success will be 1, failure will be 0 
        */
    //Method to update Sales Invoice values
    public int updateSalesOrderEntry(int invoiceID, java.sql.Date paymentDate)
    {	
            ConnectDB con = new ConnectDB();
            //Insert Order into Sales Order Table
            int i=0;
            String query = "Update SalesInvoice Set Order_Date=?, Customer_ID=?, Payment_Date=? where Invoice_ID=?";
            ArrayList<Object> value = this.createValueList();
            value.add(paymentDate);
            value.add(invoiceID);
            i = con.prepareStatement(query, value);
                
            return i;

    }
    /**
        * Method to delete an Invoice record from the database
        * @param invoiceID - Integer representing the Invoice ID of the record to be deleted
        * @return - Returns an integer representing success or failure, success will be 1, failure will be 0
         */
    //Method to delete Sales Invoice
    public int deleteEntry(int invoiceID)
    {	
            ConnectDB con = new ConnectDB();
            ArrayList<Object> id = new ArrayList<Object>();
            id.add(invoiceID);
            int i=0;
            String query = "DELETE FROM SalesInvoice WHERE Invoice_ID=?";
            i = con.prepareStatement(query, id);
            if(i == 1)
            {
                    JOptionPane.showMessageDialog(null, "Sales Invoice successfully deleted ","Sales Invoice deleted", JOptionPane.ERROR_MESSAGE );
                    query = "DELETE FROM Invoice_Order_Item WHERE Invoice_ID=?";
                    i = con.prepareStatement(query, id);
                    return i;   
            }
            else
            {
                     JOptionPane.showMessageDialog(null, "An unknown error has occured while deleting Sales Invoice entry, "
                                               + "please contact your network administrator.", 
                                                "Unknown error querying database", JOptionPane.ERROR_MESSAGE );
            }
            return i;
    }
    /**
     * Method to set the payment Date of Sales Invoice in database
     * @param invoiceID - Integer parameter for Invoice ID of record to be updated
     * @return - Returns an integer representing success or failure, success will be 1, failure will be 0
     */
    //Method to mark order as complete
    public int setInvoicePayDate(int invoiceID)
    {	
            ConnectDB con = new ConnectDB();
            ArrayList<Object> value = new ArrayList<Object>();
            //Insert Order into Sales Order Table
            int i=0;
            String query = "Update SalesInvoice Set Payment_Date=? where Invoice_ID=?";
            value.add(this.getPaymentDate());
            value.add(invoiceID);
            i = con.prepareStatement(query, value);
            return i;

    }
}//end class		

