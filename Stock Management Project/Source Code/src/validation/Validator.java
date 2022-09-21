//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Data validation class for validating data in forms
package validation;

//Imports
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import databaseConnection.ConnectDB;

//Class Validator 
/**
 * Validator class for validating forms
 * @author SKy
 */
public class Validator 
{
    private JTextFieldEmpty exp;

    /**
     * Default Constructor for Validator
     */
    public Validator()
    {
            exp = new JTextFieldEmpty();
    }
    /**
     * Method to validate that Text Field is not empty, throws JTextFieldEmpty exception if it is
     * @param fld - Takes a JTextfield as input parameter
     * @throws JTextFieldEmpty 
     */
    //Throws exception if Textfield is empty
    public void JTextFieldEmpty(JTextField fld) throws JTextFieldEmpty
    {
            if(fld.getText().equals(""))
            {
                    throw new JTextFieldEmpty(fld);
            }
    }
    /**
     * Method to validate that contents of Textfield is an integer, throws IntegerValidate exception if it is not
     * @param fld - Takes a JTextfield as input parameter
     * @throws IntegerValidate 
     */
    //Throws exception if Textfield does not have integer value
    public void IntegerValidate(JTextField fld) throws IntegerValidate
    {       
            try
            {   
                Integer.parseInt(fld.getText());
            }
            catch(NumberFormatException e)
            {
                throw new IntegerValidate(fld);
            }
    }
    /**
     * Method to validate that contents of Textfield is a double, throws DoubleValidate exception if it is not
     * @param fld - Takes a JTextfield as input parameter
     * @throws DoubleValidate 
     */
     //Throws exception if Textfield does not have integer or double value
    public void DoubleValidate(JTextField fld) throws DoubleValidate
    {       
            try
            {   
                Double.parseDouble(fld.getText());
            }
            catch(NumberFormatException e)
            {
                throw new DoubleValidate(fld);
            }
    }
     /**
     * Method to validate that contents of Textfield could be valid phone number, throws PhoneNoValidate exception if it is not
     * @param fld - Takes a JTextfield as input parameter
     * @throws PhoneNoValidate 
     */
    //Throws exception if Textfield phone number in wrong format
    public void PhoneNoValidate(JTextField fld) throws PhoneNoValidate
    {       
            try
            {   
                Integer.parseInt(fld.getText().replaceAll(" ", ""));
            }
            catch(NumberFormatException e)
            {
                throw new PhoneNoValidate(fld);
            }
    }
    /**
     * Method to validate that contents of Textfield is a valid email format, throws EmailAddValidate exception if it is not
     * @param fld - Takes a JTextfield as input parameter
     * @throws EmailAddValidate 
     */
     //Throws exception if Textfield phone number in wrong format
    public void EmailAddValidate(JTextField fld) throws EmailAddValidate
    {       
            try
            {   
                InternetAddress email = new InternetAddress(fld.getText());
                email.validate();
            }
            catch(AddressException e)
            {
                throw new EmailAddValidate(fld);
            }
    }
    /**
     * Method to validate that username is not repeated , throws UsernameValidate exception if it is
     * @param fld - Takes a JTextfield as input parameter
     * @throws UsernameValidate 
     */
      //Throws exception if Textfield phone number in wrong format
    public void UsernameValidate(JTextField fld) throws UsernameValidate
    {       
            try
            {   
                ConnectDB con = new ConnectDB();
                ResultSet rs = null;
                //Get all current username from database
                String query = "Select Customer_Username from Customer";
                
                rs = con.returnSet(query);
                //If any of the usernames = username entered throw exception
                while(rs.next())
                {
                    String user = rs.getString("Customer_Username");
                    if(user.equals(fld.getText()))
                    {
                        throw new UsernameValidate(fld);
                    }
                    
                }
            }
            //Catch SQL Exceptions
            catch (SQLException ex) 
            {
                   JOptionPane.showMessageDialog(null, "An unknown error has occured while querying database to validate username, "
                                               + "please contact your network administrator.", 
                                                "Unknown error querying database", JOptionPane.ERROR_MESSAGE );
            }
    }
} 


    
