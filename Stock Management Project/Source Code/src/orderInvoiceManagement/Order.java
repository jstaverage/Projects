//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Order class to generate Order objects to store items on Orders/Invoices
package orderInvoiceManagement;

//Imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JOptionPane;

import customerOrderPortal.Basket;
import databaseConnection.ConnectDB;

    //Full constructor

/**
 * Order object for storing Sales Order attributes and behaviours
 * @author Shane Kennedy
 */
    public class Order 
    {
    private int orderID;
    private Date orderDate;
    private int customerID;
    private ArrayList<OrderItem> items;
    /**
     * Constructor for Order Objects
     * @param orderDate - Takes a Date object as parameter for Order Date
     * @param customerID - Takes an Integer as parameter for Customer ID
     * @param items - Takes an arraylist of Order Item Objects as parameter representing Order Items
     */
    //Constructor for objects where order id is unknown
    public Order(Date orderDate, int customerID, ArrayList<OrderItem> items)
    {
        this.orderID = 0;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.items = items;
    }
    /**
     * Constructor for Order Objects
     * @param orderDate - Takes a Date object as parameter for Order Date
     * @param customerID - Takes an Integer as parameter for Customer ID
     * @param basket - Takes a basket object as parameter for Order Items
     */
    //Constructor which takes a Basket Object and not an ArrayList for retrieving order products
    public Order(Date orderDate, int customerID, Basket basket)
    {
        this.orderID = 0;
        this.orderDate = orderDate;
        this.customerID = customerID;
        this.items = convertBasket(basket);
    }
    /**
     * Default Constructor for Order objects
     */
    //Empty constructor
    public Order()
    {
        this.orderID = 0;
        this.orderDate = null;
        this.customerID = 0;
        this.items = null;
    }
    /**
     * Setter method for Order Id
     * @param orderID - Takes an integer as parameter representing Order ID
     */
    //Getters and Setters
    public void setOrderID(int orderID)
    {
        this.orderID = orderID;
    }
    /**
     * Getter method for order id
     * @return - Returns an integer representing Order ID
     */
    public int getOrderID()
    {
        return orderID;
    }
    /**
     * Setter method for Order Date
     * @param orderDate - Takes a Date object as parameter representing Order Date
     */
    public void setOrderDate(Date orderDate)
    {
        this.orderDate = orderDate;
    }
    /**
     * Getter method for order date
     * @return - Returns a Date Object representing Order Date
     */
    public Date getOrderDate()
    {
        return orderDate;
    }
    /**
     * Setter method for Customer Id
     * @param customerID - Takes an integer as parameter representing Customer ID
     */
    public void setCustomerID(int customerID)
    {
        this.customerID = customerID;
    }
     /**
     * Getter method for Customer id
     * @return - Returns an integer representing Customer ID
     */
    public int getCustomerID()
    {
        return customerID;
    }
    /**
     * Setter method for Order Item ArrayList
     * @param items - Takes a ArrayList of Order Items as parameter representing Items on Order
     */
    public void setItems(ArrayList<OrderItem> items) {
        this.items = items;
    }
     /**
     * Getter method for Order Items ArrayList
     * @return - Returns an ArrayList of Order Items representing items on order
     */
    public ArrayList<OrderItem> getItems() {
        return items;
    }
    /**
     * Method to convert a Basket Object of Order Items into ArrayList of Order Items
     * @param basket - Takes a basket object as parameter
     * @return - Returns an ArrayList of Order Items
     */
    //Method to convery basket objects items to an ArrayList of items
    private ArrayList<OrderItem> convertBasket(Basket basket)
    {
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        ArrayList<OrderItem> orderItems = new ArrayList<OrderItem>();
        double retailPrice = 0;
        //For each item in basket
        for(int i = 0; i < basket.getItemArray().size();i++)
        {   
            
            try
            {   //Get retail price from inventory for each item in Basket Objects
                String query = "SELECT Retail_Price FROM Inventory WHERE Product_ID = ?";

                rs = con.returnSetCond(query, new ArrayList<Object>(Arrays.asList(basket.getItemArray().get(i))));
                
                while(rs.next())
                {  
                    retailPrice= rs.getDouble(1);
                }
                //Add items to ArrayList
                orderItems.add(new OrderItem(basket.getItemArray().get(i),basket.getQuantityArray().get(i),retailPrice));
                
            }
            catch(SQLException e)
            {
                 JOptionPane.showMessageDialog(null, "An unknown error has occured while querying database for retail price, "
                                               + "please contact your network administrator.", 
                                                "Unknown error querying database", JOptionPane.ERROR_MESSAGE );
            }
            finally 
            {
                //Close connection objects    
                con.closeResultSet(rs);
                con.closeConnection();
            }
        }
        return orderItems;
    }
    /**
     * Method which calculates Gross Total Price of order
     * @return - Returns a double representing Gross Total of Order
     */
    //Method to calculateGrossTotal, run through items calculate price and then return value
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
     * Method which calculates Net Total of Order Object
     * @return - Returns a double representing Net Total of Order
     */
     //Method to calculate Net Total, run through items calculate price and then return value
    public double calculateNetTotal()
    {
        double result = 0;
        ConnectDB con = new ConnectDB();
        ResultSet rs = null;
        String custVATNo = "";
        
        try
        {   //Get whether customer has VAT number for calculation
            String query = "SELECT Customer_VatNo FROM Customer WHERE Customer_ID = ?";
            
            rs = con.returnSetCond(query, new ArrayList<Object>(Arrays.asList(customerID)));
           
            //Get Vat No
            while(rs.next())
            {  
                custVATNo= rs.getString(1);
            }
           
        }
        catch(SQLException e)
        {
             JOptionPane.showMessageDialog(null, "An unknown error has occured while querying database for Customer VAT number, "
                                               + "please contact your network administrator.", 
                                                "Unknown error querying database", JOptionPane.ERROR_MESSAGE );
        }
        finally 
        {
            //Close connection object
            con.closeResultSet(rs);
            con.closeConnection();
                 
        }
        double vatRate = 0;
        
        //If Customer eligible for VAT free purchase
        if(custVATNo.equals("None"))
        {
            
           vatRate = driver.CA3Project.VAT_RATE;;
                 
        }
        //Finally calculate total
        for(int i = 0; i < items.size(); i++)
        {
            result += items.get(i).getQuantity() * (items.get(i).getCurrentPrice()+ (items.get(i).getCurrentPrice()*vatRate));
        }
        return result;
    }
    /**
     * Method which creates an ArrayList of objects for prepared statement method of ConnectDB
     * @return - Returns an ArrayList of objects representing Order sttributes 
     */
    //Method to create a list of values for prepared statements
    public ArrayList<Object> createValueList()
	{
		ArrayList<Object> values = new ArrayList<Object>(Arrays.asList(this.orderDate,this.customerID));
		
		return values;
	}
    /**
     * Method to add Sales Order to database
     * @return - - Returns an integer representing success or failure, success will be 1, failure will be 0
     */
    //Method to add a Sales Order and all Order Items from Sales Order to the database, will returns 1 if successful, 0 if not
    public int createOrderItemEntry()
    {	
            ConnectDB con = new ConnectDB();
            //Insert Order into Sales Order Table
            int i=0;
            String query = 	"INSERT INTO SalesOrder (Order_Date, Customer_ID)"
                            + " VALUES (?,?)";
            i = con.prepareStatement(query, this.createValueList());

            ResultSet res = null;
            //If query successful
            if(i == 1)
            {   
                try
                {
                    query = "Select * from SalesOrder";

                    res=con.returnSet(query);
                    while(res.next()) 
                    {   
                        //Get last id in database
                        if(res.isLast())
                        {
                            //Get sales order ID
                            int orderID = res.getInt(1);
                            for(int j = 0; j < items.size(); j++)
                            {
                                query = "INSERT INTO Order_Item (Order_ID, Product_ID, Quantity, Current_Price)"
                                            + " VALUES (?,?,?,?)";
                                i = con.prepareStatement(query, new ArrayList<>(Arrays.asList(orderID, 
                                        items.get(j).getProductID(),items.get(j).getQuantity(),items.get(j).getCurrentPrice())));
                            }
                            return i;
                        }
                    }
                   
                }
                catch(Exception e)
                {
                        JOptionPane.showMessageDialog(null, "An unknown error has occured while querying database, "
                                               + "please contact your network administrator.", 
                                                "Unknown error querying database", JOptionPane.ERROR_MESSAGE );
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null, "An unknown error has occured while inserting Entries, "
                                               + "please contact your network administrator.", 
                                                "Unknown error querying database", JOptionPane.ERROR_MESSAGE );

            }
            
            i = 0;
            return i;

    }
     /**
        * Method to amend the details of an Order object in the database
        * @param orderID - Integer representing order ID of record to be amended
        * @return - Returns an integer representing success or failure, success will be 1, failure will be 0 
        */
    //Method to update Sales Order Entry and Order Item entries associated with Order into the database, 1 for successful, 0 for failure
    public int updateSalesOrderEntry(int orderID)
    {	
            ConnectDB con = new ConnectDB();
            //Insert Order into Sales Order Table
            int i=0;
            //Update the Sales Order
            String query = "Update SalesOrder Set Order_Date=?, Customer_ID=? where Order_ID=?";
            ArrayList<Object> value = this.createValueList();
            value.add(orderID);
            i = con.prepareStatement(query, value);
            if(i == 1)
            {   
                //First delete current Order_Item Items
                query = "Delete FROM Order_Item Where Order_ID=?";
                i = con.prepareStatement(query, new ArrayList<>(Arrays.asList(orderID)));
                //If deletion successful add back updated items
                if(i==1)
                {   
                    //Now add back the updated Items
                    for(int j = 0; j < items.size(); j++)
                    {
                        query = "INSERT INTO Order_Item (Order_ID, Product_ID, Quantity, Current_Price)"
                                    + " VALUES (?,?,?,?)";
                        i = con.prepareStatement(query, new ArrayList<>(Arrays.asList(orderID, 
                                items.get(j).getProductID(),items.get(j).getQuantity(),items.get(j).getCurrentPrice())));
                    }
                    return i;
                }
                else
                {
                   JOptionPane.showMessageDialog(null, "Deletion of order items has failed., "
                                               + "please contact your network administrator.", 
                                                "Unknown error querying database", JOptionPane.ERROR_MESSAGE );
                }
                
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Updating the entry has failed. "
                                               + "please contact your network administrator.", 
                                                "Unknown error querying database", JOptionPane.ERROR_MESSAGE );
            }
            i = 0;
            return i;

    }
    /**
     * Method to set Order to be complete, change complete field to 1 and subtract order items from Inventory.
     * @param orderID - Integer representing the order ID of the record to be marked as complete
     * @return - Returns an integer representing success or failure, success will be 1, failure will be 0
     */
    //Method to mark order as complete
    public int markOrderComplete(int orderID)
    {	
            ConnectDB con = new ConnectDB();
            ConnectDB con2 = new ConnectDB();
            ArrayList<Object> value = new ArrayList<Object>();
            ResultSet rs = null;
            String query = "";
            String query2 = "";
            int i=0;
            try
                {   
                    //Get all Order_Items from Order with correct Order ID
                    query = "Select Order_Item.Product_ID, Quantity, Qty_in_Stock,Qty_on_Order from Order_Item Inner Join Inventory on Inventory.Product_ID = Order_Item.Product_ID Where Order_ID=?";
                    value.add(orderID);
                    rs = con.returnSetCond(query, value);
                    
                        while(rs.next()) 
                        {   
                            //Get product ID and quantity of first item
                            int prodID = rs.getInt("Product_ID");
                            int quantity = rs.getInt("Quantity");
                            
                            int difference = 0;
                            int newQuantity = 0;
                           
                            int quantityStock = rs.getInt("Qty_in_Stock");
                            int onOrder = rs.getInt("Qty_on_Order");
                            //If quantity in stock less than quantity on order
                            if(quantityStock < quantity)
                            {   
                                //get the difference
                                difference = quantity - quantityStock;
                                difference += onOrder;
                            }
                            else
                            {
                                newQuantity = quantityStock - quantity;
                                difference += onOrder;
                            }
                            //Update stock totals in database
                            query2 = "Update Inventory set Qty_in_Stock=?,Qty_on_Order=? Where Product_ID=?";
                            //Clear values
                            value.clear();
                            //Add parameters values to arraylist
                            value.add(newQuantity);
                            value.add(difference);
                            value.add(prodID);
                            //Execute update query
                            con2.prepareStatement(query2, value);
                            
                        }
                         //Insert Order into Sales Order Table
                        query = "Update SalesOrder Set Complete=? where Order_ID=?";
                        //Clear value and add parameters for query
                        value.clear();
                        value.add(1);
                        value.add(orderID);
                        i = con.prepareStatement(query, value);
                        return i;
                    
                   
                }
                catch(SQLException e)
                {   
                        JOptionPane.showMessageDialog(null, "An unknown error has occured while querying database, "
                                               + "please contact your network administrator.", 
                                                "Unknown error querying database", JOptionPane.ERROR_MESSAGE );
                        return i;
                }
                finally
                {   
                   //Close objects
                   con.closeConnection();
                   con.closeResultSet(rs);
                   con2.closeConnection();
                }
           

    }
    /**
        * Method to delete an Order record from the database
        * @param orderID - Integer representing the order ID of the record to be deleted
        * @return - Returns an integer representing success or failure, success will be 1, failure will be 0
         */
    //Method to delete Sales Order entry and associated Order Item entries
    public int deleteEntry(int orderID)
    {	
            ConnectDB con = new ConnectDB();
            ArrayList<Object> id = new ArrayList<Object>();
            id.add(orderID);
            int i=0;
            String query = "DELETE FROM SalesOrder WHERE Order_ID=?";
            i = con.prepareStatement(query, id);
            if(i == 1)
            {
                    query = "DELETE FROM Order_Item WHERE Order_ID=?";
                    i = con.prepareStatement(query, id);
                    return i;                   
            }
            else
            {
                    JOptionPane.showMessageDialog(null, "An unknown error has occured while deleting Sales Order entry, "
                                               + "please contact your network administrator.", 
                                                "Unknown error querying database", JOptionPane.ERROR_MESSAGE );
            }
            return i;
    }
   
}//end class
