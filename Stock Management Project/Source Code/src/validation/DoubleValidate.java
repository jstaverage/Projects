//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Exception class for validating textfields have a Double value
package validation;
//Imports
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * Textfield not a double exception class
 * @author Shane Kennedy
 */
public class DoubleValidate extends Throwable
{       
        /**
         * Default Constructor for Double Validate Object
         */
        //Consructors
        public DoubleValidate()
	{
                super();
         
        }
        /**
         * Constructor for Double Validate, takes a textfield object as input parameter, if exception thrown, print error message
         * @param fld - Takes a textfield object as input parameter
         */
        //Shows error message specifying field if thrown
	public DoubleValidate(JTextField fld)
	{
                super();
                JOptionPane.showMessageDialog(fld, fld.getToolTipText()+" in incorrect format. Please enter a double or integer value\nExample: 30.50",fld.getToolTipText()+" Incorrect Format", JOptionPane.ERROR_MESSAGE);
        } 
        /**
         * Constructor for Double Validate exception
         * @param customErrorMessage - Takes a string representing custom error message
         */
       	public DoubleValidate(String customErrorMessage)
	{
		super(customErrorMessage);
	}
        /**
         * Constructor for Double Validate exception
         * @param customErrorMessage - Takes a string representing custom error message
         * @param chain - Takes a throwable object as parameter
         */
	public DoubleValidate(String customErrorMessage, Throwable chain)
	{
		super(customErrorMessage, chain);
	}
        /**
         * Constructor for Double Validate exception
         * @param chain - Takes a throwable object as parameter
         */
	public DoubleValidate(Throwable chain)
	{
		super(chain);
	}
}//end class
