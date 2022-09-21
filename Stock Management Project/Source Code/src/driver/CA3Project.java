//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Driver for CA3Project class, initiates project
package driver;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * Main Driver class for program
 * @author Shane Kennedy
 */
public class CA3Project {

    //Constant VAT Rate for Sales Orders

    /**
     *Static double representing VAT Rate for non-VAT registered companies
     */
    public static final double VAT_RATE = 0.21;

    //Main Method

    /**
     *
     * @param args - Arguments that can be read in to program at runtime
     */
    public static void main(String[] args) 
    {	
    	
        //Create ConnectDB object
        databaseConnection.ConnectDB con = new databaseConnection.ConnectDB();
        
        //Test if connection is working, if it is start program
        if(con.testConnection())
        {
            //Create Login Frame and set visible
            loginAndUsers.LoginFrame frame = new loginAndUsers.LoginFrame();
            frame.setVisible(true);
        }
        //Otherwise don't start program

                
    }//end main
    
}//end class
