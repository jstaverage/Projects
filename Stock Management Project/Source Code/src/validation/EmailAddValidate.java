//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Exception class for validating textfields WITH Email Addresses
package validation;
//Imports
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Email Address not valid exception class
 * @author Shane Kennedy
 */
public class EmailAddValidate extends Throwable
{       
        /**
         * Default Constructor for Email Address Exception Object
         */
        //Consructors
        public EmailAddValidate()
	{
                super();
         
        }
        /**
         * Constructor for Email Address Exception, takes a textfield object as input parameter, if exception thrown, print error message
         * @param fld - Takes a textfield object as input parameter
         */
        //Shows error message specifying field if thrown
	public EmailAddValidate(JTextField fld)
	{
                super();
                JOptionPane.showMessageDialog(fld, fld.getToolTipText()+" in incorrect format. Please only enter valid email addresses\nExample: username@domain.com ",fld.getToolTipText()+" Incorrect Format", JOptionPane.ERROR_MESSAGE);
        }   
         /**
         * Constructor for Email Address Exception
         * @param customErrorMessage - Takes a string representing custom error message
         */
       	public EmailAddValidate(String customErrorMessage)
	{
		super(customErrorMessage);
	}
        /**
         * Constructor for Email Address exception
         * @param customErrorMessage - Takes a string representing custom error message
         * @param chain - Takes a throwable object as parameter
         */
	public EmailAddValidate(String customErrorMessage, Throwable chain)
	{
		super(customErrorMessage, chain);
	}
        /**
         * Constructor for Email Address exception
         * @param chain - Takes a throwable object as parameter
         */
	public EmailAddValidate(Throwable chain)
	{
		super(chain);
	}
}//end class
