//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Exception class for validating textfields WITH Username
package validation;
//Imports
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Username already existing in database exception class
 * @author Shane Kennedy
 */
public class UsernameValidate extends Throwable
{       
        /**
         * Default Constructor for UsernameValidate Exception Object
         */
        //Consructors
        public UsernameValidate()
	{
                super();
         
        }
        /**
         * Constructor for  UsernameValidate Exception, takes a textfield object as input parameter, if exception thrown, print error message
         * @param fld - Takes a textfield object as input parameter
         */
        //Shows error message specifying field if thrown
	public UsernameValidate(JTextField fld)
	{
                super();
                JOptionPane.showMessageDialog(fld, "Username already taken. Please enter another username and try again ","Username already taken", JOptionPane.ERROR_MESSAGE);
        } 
         /**
         * Constructor for UsernameValidate Validate Exception
         * @param customErrorMessage - Takes a string representing custom error message
         */
       	public UsernameValidate(String customErrorMessage)
	{
		super(customErrorMessage);
	}
         /**
         * Constructor for UsernameValidate Validate exception
         * @param customErrorMessage - Takes a string representing custom error message
         * @param chain - Takes a throwable object as parameter
         */
	public UsernameValidate(String customErrorMessage, Throwable chain)
	{
		super(customErrorMessage, chain);
	}
         /**
         * Constructor for UsernameValidate  Validate exception
         * @param chain - Takes a throwable object as parameter
         */
	public UsernameValidate(Throwable chain)
	{
		super(chain);
	}
}//end class
