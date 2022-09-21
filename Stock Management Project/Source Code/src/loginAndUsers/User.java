//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Abstract User Object to generate User accounts of different subclass User Objects
package loginAndUsers;

//Imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import customerManagement.Address;
import customerManagement.Customer;
import databaseConnection.ConnectDB;

//Abstract User Class

/**
 * Abstract super class User, representing Users of program
 * @author Shane Kennedy
 */
public abstract class User 
{	
	//Instance Variables
	private int userID;
	private Address userAddress;
	private String userPhone;
	private String userEmail;
	private String userUsername;
	private String userPassword;
	
        /**
         * Constructor for User Object
         * @param userID - Integer parameter for User ID
         * @param userAddress - Address object parameter for User Address
         * @param userPhone - String parameter for User phone number
         * @param userEmail - String parameter for User email
         * @param userUsername - String parameter for User username
         * @param userPassword - String parameter for User password
         */
	//Constructor
	public User(int userID, Address userAddress, String userPhone,
			String userEmail, String userUsername, String userPassword) 
	{
		this.userID = userID;
		this.userAddress = userAddress;
		this.userPhone = userPhone;
		this.userEmail = userEmail;
		this.userUsername = userUsername;
		this.userPassword = userPassword;
	}
	/**
         * Getter method to get USer ID
         * @return - Returns integer for User ID
         */
	//Getters and Setters
	public int getUserID() 
	{
		return userID;
	}
        /**
         * Setter method for User ID
         * @param userID -Integer Parameter for User ID
         */
	public void setUserID(int userID) 
	{
		this.userID = userID;
	}
        /**
         * Getter method to get USer Address
         * @return - Returns Address Object for User Address
         */
	public Address getUserAddress() 
	{
		return userAddress;
	}
        /**
         * Setter method for User Address
         * @param userAddress -Address object Parameter for User Address
         */
	public void setUserAddress(Address userAddress) 
	{
		this.userAddress = userAddress;
	}
        /**
         * Getter method to get User Phone Number
         * @return - Returns String for User Phone Number
         */
	public String getUserPhone() 
	{
		return userPhone;
	}
        /**
         * Setter method for User Phone Number
         * @param userPhone - String parameter for User Phone
         */
	public void setUserPhone(String userPhone)
	{
		this.userPhone = userPhone;
	}
        /**
         * Getter method to get User Email
         * @return - Returns String for User Email
         */
	public String getUserEmail() 
	{
		return userEmail;
	}
        /**
         * Setter method for User Email
         * @param userEmail - String parameter for User Email
         */
	public void setUserEmail(String userEmail) 
	{
		this.userEmail = userEmail;
	}
        /**
         * Getter method to get User Username
         * @return - Returns String for User Username
         */
	public String getUserUsername() 
	{
		return userUsername;
	}
        /**
         * Setter method for User Username
         * @param userUsername - String parameter for User Username
         */
	public void setUserUsername(String userUsername) 
	{
		this.userUsername = userUsername;
	}
        /**
         * Getter method to get User Password
         * @return - Returns String for User Password
         */
	public String getUserPassword() 
	{
		return userPassword;
	}
        /**
         * Setter method for User Password
         * @param userPassword - String parameter for User Password
         */
	public void setUserPassword(String userPassword) 
	{
		this.userPassword = userPassword;
	}
        /**
         * To string for User object
         * @return - Returns String with user details
         */
	//To String method
	public String toString() 
	{
		return "User [userID=" + userID + ", userAddress=" + userAddress + ", userPhone=" + userPhone + ", userEmail=" + userEmail
				+ ", userUsername=" + userUsername + ", userPassword=" + userPassword + "]";
	}
	/**
         * Method to validate user details for login for both sub classes of user, Customer and Staff
         * @return - Returns true if login details valid, false if not
         */
	//Method to validate Username and Password are valid, returns true if valid, false if not
	public boolean validateLogin()
	{
	//Declare an instantiatle a connction objects and result set for the query	
	ConnectDB connection = new ConnectDB();
	ResultSet resultSet = null;
        //Set up array list for values of parameters in query
        ArrayList<Object> values = new ArrayList<Object>();
        //Boolean value to check if method succeeded
	boolean valid = false;
	try
	{
            //If type of object calling is Staff,  get the Staff details for validation
            if(this instanceof Staff)
            {	
                    //Query staff database with input username for passwords
                    String query = "SELECT Staff_Password FROM Staff WHERE Staff_Username = ?";
                    values.add(this.getUserUsername());
                    resultSet = connection.returnSetCond(query, values);
            }
            //If type of object calling is Customer, get the customer details for validation
            else if(this instanceof Customer)
            {
                    //Query staff database with input username for passwords
                    String query = "SELECT Customer_Password FROM Customer WHERE Customer_Username = ?";
                    values.add(this.getUserUsername());
                    resultSet = connection.returnSetCond(query, values);
            }
            
            //If result set is not empty ie. if username exists
            if(resultSet.next())
            {	
                    //Check password matches
                    String password = (String) resultSet.getObject(1);
                    if(password.equals(this.getUserPassword()))
                    {
                            valid = true;
                    }
                    else
                    {
                            valid = false;
                    }
            }
            else
            {
                valid = false;
            }
        
	}
	catch(SQLException sqlException ) 
	{
		JOptionPane.showMessageDialog(null, "User name and password invalid', "
                + "please contact your network administrator.", 
                "Wrong password", JOptionPane.ERROR_MESSAGE );
	}
		finally 
		{
                    connection.closeResultSet(resultSet);
                    connection.closeConnection();
               
                }
        return valid;

	}//end method
}//end class
 