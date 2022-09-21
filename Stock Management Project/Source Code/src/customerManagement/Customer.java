//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Customer class to create Customer objects
package customerManagement;

import java.util.ArrayList;
import java.util.Arrays;

import databaseConnection.ConnectDB;
import loginAndUsers.User;

//Customer extends user class

/**
 * Customer subclass Object of User, for customer attributes and behaviours
 * 
 * @author Shane Kennedy
 */
public class Customer extends User {
	// Instance variables
	private int customerID;
	private String customerName;
	private String customerContact;
	private String customerVatNo;

	/**
	 * Default Constructor Customer Objects
	 */
	// Empty Constuctor
	public Customer() {
		super(1, new Address(), "Not defined", "Not defined", "Not defined", "Not defined");
		this.customerName = "Not defined";
		this.customerContact = "Not defined";
		this.customerVatNo = "Not defined";
	}

	/**
	 * Full Constructor for Customer Objects
	 * 
	 * @param customerID       - Integer parameter to store Customer IDs
	 * @param customerName     - String parameter to store Customer Name
	 * @param customerContact  - String parameter to store Customer Contact Name
	 * @param customerAddress  - Address Object parameter to store Customer Address
	 * @param customerPhone    - String parameter to store Customer Phone Number
	 * @param customerEmail    - String parameter to store Customer Email
	 * @param customerVatNo    - String parameter to store Customer Vat Number
	 * @param customerUsername - String parameter to store Customer Username
	 * @param customerPassword - String parameter to store Customer Password
	 */
	// Full constructor
	public Customer(int customerID, String customerName, String customerContact,
			Address customerAddress, String customerPhone, String customerEmail,
			String customerVatNo, String customerUsername, String customerPassword) {
		super(customerID, customerAddress, customerPhone, customerEmail, customerUsername, customerPassword);
		// Set VAT Number to default value of None if blank
		if (customerVatNo.equals("")) {
			this.customerVatNo = "None";
		} else {
			this.customerVatNo = customerVatNo;
		}
		this.customerName = customerName;
		this.customerContact = customerContact;

	}

	/**
	 * Constructor for Customer Objects with just Username and Password parameters
	 * 
	 * @param customerUsername - String parameter to store Customer Username
	 * @param customerPassword - String parameter to store Customer Password
	 */
	// Constructor for just username and password
	public Customer(String customerUsername, String customerPassword) {
		super(1, new Address(), "Not defined", "Not defined", customerUsername, customerPassword);
		this.customerName = "Not defined";
		this.customerContact = "Not defined";
		this.customerVatNo = "Not defined";
	}

	/**
	 * Getter method to get Customer Name
	 * 
	 * @return - Returns a string representing Customer Name
	 */
	// Getter and Setters
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * Setter method to set Customer Name
	 * 
	 * @param customerName - String parameter to input Customer Name
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * Getter method to get Customer Contact Name
	 * 
	 * @return - Returns a string representing Customer Contact Name
	 */
	public String getCustomerContact() {
		return customerContact;
	}

	/**
	 * Setter method to set Customer Contact Name
	 * 
	 * @param customerContact - String parameter to input Customer Contact Name
	 */
	public void setCustomerContact(String customerContact) {
		this.customerContact = customerContact;
	}

	/**
	 * Getter method to get Customer Vat Number
	 * 
	 * @return - Returns a string representing Customer Vat Number
	 */
	public String getCustomerVatNo() {
		return customerVatNo;
	}

	/**
	 * Setter method to set Customer Vat Number
	 * 
	 * @param customerVatNo - String parameter to input Customer Vat Number
	 */
	public void setCustomerVatNo(String customerVatNo) {
		this.customerVatNo = customerVatNo;
	}

	/**
	 * To String method for Customer object
	 * 
	 * @return - Returns a string with Customer object details
	 */
	// To string method
	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerName=" + customerName + ", customerContactName="
				+ customerContact + ", customerAddress=" + this.getUserAddress() + ", customerPhone="
				+ this.getUserPhone() + ", customerEmail=" + this.getUserEmail() + ", customerVatNo=" + customerVatNo
				+ ", customerUsername=" + this.getUserUsername() + ", customerPassword=" + this.getUserPassword() + "]";
	}

	/**
	 * Method to create an ArrayList of customer details to use with the ConnectDB
	 * prepared statement method
	 * 
	 * @return - Returns an ArrayList of objects with customer parameters
	 */
	// Method to create value list for prepared statements
	public ArrayList<Object> createValueList() {
		ArrayList<Object> values = new ArrayList<Object>(Arrays.asList(this.customerName, this.customerContact,
				this.getUserAddress().getAddLine1(), this.getUserAddress().getAddStreet(),
				this.getUserAddress().getAddTown(), this.getUserAddress().getAddCounty(),
				this.getUserAddress().getAddCountry(), this.getUserPhone(), this.getUserEmail(), this.customerVatNo,
				this.getUserUsername(), this.getUserPassword()));

		return values;
	}

	/**
	 * Method to add Customer objects to database
	 * 
	 * @return - Returns an integer representing success or failure, success will be
	 *         1, failure will be 0
	 */
	// Method to insert customer entries into database, returns 1 if query
	// successful
	public int createCustEntry() {
		ConnectDB con = new ConnectDB();

		int i = 0;
		String query = "INSERT INTO Customer (Customer_Name, Customer_Contact_Name,Address_Line_1, Address_Street,"
				+ " Address_Town,Address_County,Address_Country, Customer_Phonenumber,Customer_Email,"
				+ "Customer_VatNo, Customer_Username, Customer_Password) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
		i = con.prepareStatement(query, this.createValueList());
		return i;
	}

	/**
	 * Method to amend the details of a Customer object in the database
	 * 
	 * @param custNumber - Integer representing customer ID of record to be amended
	 * @return
	 */
	// Method to amend customer entries in database, returns 1 if query successful,
	// takes customer number as parameter to allow customer objects without id set
	// to also be updated
	public int updateCustEntry(int custNumber) {
		ConnectDB con = new ConnectDB();
		ArrayList<Object> values = this.createValueList();
		values.add(custNumber);
		int i = 0;
		String query = "UPDATE Customer SET Customer_Name=?, Customer_Contact_Name=?,Address_Line_1=?,"
				+ "Address_Street=?,Address_Town=?,Address_County=?, Address_Country=?,Customer_Phonenumber=?, "
				+ "Customer_Email=?,Customer_VatNo=?,Customer_Username=?,Customer_Password=? WHERE Customer_ID=?";

		i = con.prepareStatement(query, values);
		return i;

	}

	/**
	 * Method to delete a Customer record from the database
	 * 
	 * @param custNumber - Integer representing the Customer ID of the record to be
	 *                   deleted
	 * @return
	 */
	// Method to delete customer entries from database, returns 1 if query
	// successful, takes customer number as parameter
	public int deleteEntry(int custNumber) {
		ConnectDB con = new ConnectDB();
		ArrayList<Object> id = new ArrayList<Object>();
		id.add(custNumber);
		int i = 0;
		String query = "DELETE FROM Customer WHERE Customer_ID=?";
		i = con.prepareStatement(query, id);

		return i;
	}

}// end class
