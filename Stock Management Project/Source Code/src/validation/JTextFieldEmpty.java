//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Exception class for validating textfields
package validation;
//Imports
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 * JTextfield empty exception class
 * @author Shane Kennedy
 */
public class JTextFieldEmpty extends Throwable
{       
        /**
         * Default Constructor for JTextFieldEmpty Exception Object
         */
        public JTextFieldEmpty()
	{
                super();
         
        }
        /**
         * Constructor for  JTextFieldEmpty Exception, takes a textfield object as input parameter, if exception thrown, print error message
         * @param fld - Takes a textfield object as input parameter
         */
        //Shows error message specifying field if thrown
	public JTextFieldEmpty(JTextField fld)
	{
                super();
                JOptionPane.showMessageDialog(fld, fld.getToolTipText()+" is empty, fill field and try again",fld.getToolTipText()+"Empty", JOptionPane.ERROR_MESSAGE);
        }
         /**
         * Constructor for JTextFieldEmpty Validate Exception
         * @param customErrorMessage - Takes a string representing custom error message
         */
       	public JTextFieldEmpty(String customErrorMessage)
	{
		super(customErrorMessage);
	}
         /**
         * Constructor for JTextFieldEmpty Validate exception
         * @param customErrorMessage - Takes a string representing custom error message
         * @param chain - Takes a throwable object as parameter
         */
	public JTextFieldEmpty(String customErrorMessage, Throwable chain)
	{
		super(customErrorMessage, chain);
	}
        /**
         * Constructor for JTextFieldEmpty  Validate exception
         * @param chain - Takes a throwable object as parameter
         */
	public JTextFieldEmpty(Throwable chain)
	{
		super(chain);
	}
}//end class
