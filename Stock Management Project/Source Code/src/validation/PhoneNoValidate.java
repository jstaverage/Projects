//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Exception class for validating textfields WITH PHONE numbers
package validation;
//Imports
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Phone number not valid exception class
 * @author Shane Kennedy
 */
public class PhoneNoValidate extends Throwable
{       
        /**
         * Default Constructor for PhoneNoValidate Exception Object
         */
        //Consructors
        public PhoneNoValidate()
	{
                super();
         
        }
        /**
         * Constructor for  PhoneNoValidate Exception, takes a textfield object as input parameter, if exception thrown, print error message
         * @param fld - Takes a textfield object as input parameter
         */
        //Shows error message specifying field if thrown
	public PhoneNoValidate(JTextField fld)
	{
                super();
                JOptionPane.showMessageDialog(fld, fld.getToolTipText()+" in incorrect format. Please use only numbers and spaces\nExample 087 11122233",fld.getToolTipText()+" Incorrect Format", JOptionPane.ERROR_MESSAGE);
        } 
         /**
         * Constructor for PhoneNoValidate Validate Exception
         * @param customErrorMessage - Takes a string representing custom error message
         */
       	public PhoneNoValidate(String customErrorMessage)
	{
		super(customErrorMessage);
	}
         /**
         * Constructor for PhoneNoValidate Validate exception
         * @param customErrorMessage - Takes a string representing custom error message
         * @param chain - Takes a throwable object as parameter
         */
	public PhoneNoValidate(String customErrorMessage, Throwable chain)
	{
		super(customErrorMessage, chain);
	}
        /**
         * Constructor for PhoneNoValidate  Validate exception
         * @param chain - Takes a throwable object as parameter
         */
	public PhoneNoValidate(Throwable chain)
	{
		super(chain);
	}
}//end class
