//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Exception class for validating textfields have an Integer value
package validation;
//Imports
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Not an integer exception class
 * @author Shane Kennedy
 */
public class IntegerValidate extends Throwable
{       
        /**
         * Default Constructor for Integer Validate Exception Object
         */
        //Consructors
        public IntegerValidate()
	{
                super();
         
        }
        /**
         * Constructor for Integer Validate Exception, takes a textfield object as input parameter, if exception thrown, print error message
         * @param fld - Takes a textfield object as input parameter
         */
        //Shows error message specifying field if thrown
	public IntegerValidate(JTextField fld)
	{
                super();
                JOptionPane.showMessageDialog(fld, fld.getToolTipText()+" in incorrect format. Please enter an integer value\nExample: 30",fld.getToolTipText()+" Incorrect Format", JOptionPane.ERROR_MESSAGE);
        }  
         /**
         * Constructor for Integer Validate Exception
         * @param customErrorMessage - Takes a string representing custom error message
         */
       	public IntegerValidate(String customErrorMessage)
	{
		super(customErrorMessage);
	}
         /**
         * Constructor for Integer Validate exception
         * @param customErrorMessage - Takes a string representing custom error message
         * @param chain - Takes a throwable object as parameter
         */
	public IntegerValidate(String customErrorMessage, Throwable chain)
	{
		super(customErrorMessage, chain);
	}
        /**
         * Constructor for integer Validate exception
         * @param chain - Takes a throwable object as parameter
         */
	public IntegerValidate(Throwable chain)
	{
		super(chain);
	}
}//end class
