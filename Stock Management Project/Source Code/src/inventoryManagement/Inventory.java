//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Inventory class to generate Inventory object to represent Inventory items attributes and behaviours
package inventoryManagement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import databaseConnection.ConnectDB;


//Inventory class

/**
 * Inventory Object for inventory items attributes and behaviours
 * @author Shane Kennedy
 */
public class Inventory 
{	
	//Instance variables
	private int productID;
	private String productName;
	private String productDesc;
	private int qtyInStock;
	private int reorderLevel;
	private int maxStock;
	private double costPrice;
	private double retailPrice;
	private int qtyOnOrder;
	private int supplierID;
	
        /**
         * Default Constructor for Inventory objects
         */
	//Empty default constructor
	public Inventory()
	{
		productID = 0;
		productName = "Not defined";
		productDesc = "Not defined";
		qtyInStock = 0;
		reorderLevel = 0;
		maxStock = 0;
		costPrice = 0;
		retailPrice = 0;
		qtyOnOrder = 0;
		supplierID = 0;
	}
        /**
         * Full Constructor for Inventory Objects
         * @param productID - Takes an integer as a parameter representing Product ID
         * @param productName - Takes a string as a parameter representing Product Name
         * @param productDesc - Takes a string as a parameter representing Product Description
         * @param qtyInStock - Takes an integer as a parameter representing Qty in Stock
         * @param reorderLevel - Takes an integer as a parameter representing Reorder Level
         * @param maxStock - Takes an integer as a parameter representing Max Stock Level
         * @param costPrice - Takes a double as a parameter representing Cost Price
         * @param retailPrice - Takes a double as a parameter representing Retail Price
         * @param qtyOnOrder - Takes an integer as a parameter representing Qty on Order
         * @param supplierID - Takes an integer as a parameter representing Supplier ID of product supplier
         */
	//Full constructor
	public Inventory(int productID, String productName, String productDesc, int qtyInStock, int reorderLevel,
			int maxStock, double costPrice, double retailPrice, int qtyOnOrder, int supplierID) 
	{
		this.productID = productID;
		this.productName = productName;
		this.productDesc = productDesc;
		this.qtyInStock = qtyInStock;
		this.reorderLevel = reorderLevel;
		this.maxStock = maxStock;
		this.costPrice = costPrice;
		this.retailPrice = retailPrice;
		this.qtyOnOrder = qtyOnOrder;
		this.supplierID = supplierID;
	}
        /**
         * Getter method to return Product ID
         * @return - Returns an integer representing Product ID
         */
	//Getters and Setters
	public int getProductID() {
		return productID;
	}
        /**
         * Setter method to set Product ID
         * @param productID - Takes an integer as a parameter to set Product ID
         */
	public void setProductID(int productID) {
		this.productID = productID;
	}
        /**
         * Getter method to return Product Name
         * @return - Returns a String representing Product Name
         */
	public String getProductName() {
		return productName;
	}
        /**
         * Setter method to set Product Name
         * @param productName - Takes a String as a parameter to set Product Name
         */
	public void setProductName(String productName) {
		this.productName = productName;
	}
        /**
         * Getter method to return Product Description
         * @return - Returns a String representing Product Description
         */
	public String getProductDesc() {
		return productDesc;
	}
        /**
         * Setter method to set Product Description
         * @param productDesc - Takes a String as a parameter to set Product Description
         */
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
        /**
         * Getter method to return Quantity in Stock
         * @return - Returns an integer representing Quantity in Stock
         */
	public int getQtyInStock() {
		return qtyInStock;
	}
        /**
         * Setter method to set Quantity in Stock
         * @param qtyInStock - Takes an integer as a parameter to set Quantity in Stock
         */
	public void setQtyInStock(int qtyInStock) {
		this.qtyInStock = qtyInStock;
	}
        /**
         * Getter method to return Reorder Level
         * @return - Returns an integer representing Reorder Level
         */
	public int getReorderLevel() {
		return reorderLevel;
	}
        /**
         * Setter method to set Reorder Level
         * @param reorderLevel - Takes an integer as a parameter to set Reorder Level
         */
	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}
        /**
         * Getter method to return Max Stock Level
         * @return - Returns an integer representing Max Stock Level
         */
	public int getMaxStock() {
		return maxStock;
	}
        /**
        * Setter method to set Max Stock Level
        * @param maxStock - Takes an integer as a parameter to set Max Stock Level
        */
	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}
        /**
         * Getter method to return Cost Price
         * @return - Returns a double representing Cost Price
         */
	public double getCostPrice() {
		return costPrice;
	}
         /**
        * Setter method to set Max Stock Level
        * @param costPrice - Takes a double as a parameter to set Cost Price
        */
	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}
        /**
         * Getter method to return Retail Price
         * @return - Returns a double representing Retail Price
         */
	public double getRetailPrice() {
		return retailPrice;
	}
         /**
        * Setter method to set Retail Price
        * @param retailPrice - Takes a double as a parameter to set Retail Price
        */
	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}
        /**
         * Getter method to return Qty on Order
         * @return - Returns an integer representing Qty on Order
         */
	public int getQtyOnOrder() {
		return qtyOnOrder;
	}
         /**
        * Setter method to set Qty on Order
        * @param qtyOnOrder - Takes an integer  as a parameter to set Qty on Order
        */
	public void setQtyOnOrder(int qtyOnOrder) {
		this.qtyOnOrder = qtyOnOrder;
	}
        /**
         * Getter method to return Supplier ID
         * @return - Returns an integer representing Supplier ID
         */
	public int getSupplierID() {
		return supplierID;
	}
         /**
        * Setter method to set Supplier ID
        * @param supplierID - Takes an integer  as a parameter to set Supplier ID
        */
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
        /**
        * Method to create an ArrayList of Inventory details to use with the ConnectDB prepared statement method
        * @return - Returns an ArrayList of objects with Inventory parameters 
        */
        //Method to create a value list for prepared statements
	public ArrayList<Object> createValueList()
	{
		ArrayList<Object> values = new ArrayList<Object>(Arrays.asList(this.productName,this.productDesc,
				this.qtyInStock,this.reorderLevel,this.maxStock,this.costPrice,this.retailPrice,
				this.qtyOnOrder, this.supplierID));
		
		return values;
	}
        /**
        * Method to add Inventory objects to database
        * @return - Returns an integer representing success or failure, success will be 1, failure will be 0
        */
        //Method to add Inventory items to the database, returns 1 if successful or 0 it failed
	public int createInvEntry()
	{	
		ConnectDB con = new ConnectDB();
		
		int i=0;
		String query = 	"INSERT INTO Inventory (Product_Name, Product_Description,Qty_in_Stock,"
				+ " Reorder_Level,Max_Stock_Level,Cost_Price, Retail_Price,Qty_on_Order, Supplier_ID)"
				+ " VALUES (?,?,?,?,?,?,?,?,?)";
		i = con.prepareStatement(query, this.createValueList());
		return i;
	}
        /**
        * Method to amend the details of an Inventory object in the database
        * @param prodNumber - Integer representing product ID of record to be amended
        * @return 
        */
        //Method to amend Inventory items in the database, returns 1 if successful or 0 it failed, takes product ID as a parameter
	public int updateInvEntry(int prodNumber)
	{	
		ConnectDB con = new ConnectDB();
		ArrayList<Object> values = this.createValueList();
		values.add(prodNumber);
		int i=0;
		String query = "UPDATE Inventory SET Product_Name=?, Product_Description=?,Qty_in_Stock=?,"
				+ "Reorder_Level=?,Max_Stock_Level=?,Cost_Price=?, Retail_Price=?,Qty_on_Order=?, "
				+ "Supplier_ID=? WHERE Product_ID=?";
		i = con.prepareStatement(query, values);
		return i;
		
	}
        /**
        * Method to delete an Inventory record from the database
        * @param prodNumber - Integer representing the product ID of the record to be deleted
        * @return 
         */
        //Method to delete Inventory items in the database, returns 1 if successful or 0 it failed, takes product ID as a parameter
	public int deleteEntry(int prodNumber)
	{	
		ConnectDB con = new ConnectDB();
		ArrayList<Object> id = new ArrayList<Object>();
		id.add(prodNumber);
		int i=0;
		String query = "DELETE FROM Inventory WHERE Product_ID=?";
		i = con.prepareStatement(query, id);
		return i;
	}
	
}//end class
