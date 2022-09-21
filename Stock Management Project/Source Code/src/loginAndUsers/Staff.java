//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Subclass of User class Staff, to generate Staff objects
package loginAndUsers;

import customerManagement.Address;

//Subclass of user for Staff members

/**
 * Staff Object, subclass of User object for representing instances of Employees
 * @author Shane Kennedy
 */
public class Staff extends User
{	
	//Instance variables
	private int staffID;
	private String staffSurname;
	private String staffFirstName;
	private Address staffAddress;
	private String staffPhone;
	private String staffEmail;
	private String staffJobTitle;
	private String staffUsername;
	private String staffPassword;
	
        /**
         * Default constructor for Staff Object
         */
	//Empty Constuctor
	public Staff()
	{
		super(1, new Address(), "Not defined", "Not defined",  
				"Not defined", "Not defined");
                this.staffFirstName = "Not defined";
                this.staffSurname = "Not defined";
		this.staffJobTitle = "Not defined";
	}
        /**
         * Full constructor for Staff Object
         * @param staffID - Integer parameter for Staff ID
         * @param staffFirstName - String parameter for Staff Firstname
         * @param staffSurname - String parameter for Staff Surname
         * @param staffAddress - Address Object parameter for Staff address
         * @param staffPhone - String parameter for staff phone number
         * @param staffEmail - String parameter for staff email address
         * @param staff_JobTitle - String parameter for Staff Job title
         * @param staffUsername - String parameter for Staff Username
         * @param staffPassword - String parameter for Staff Password
         */
	//Full constructor
	public Staff(int staffID, String staffFirstName, String staffSurname, Address staffAddress, String staffPhone,
			String staffEmail, String staff_JobTitle, String staffUsername, String staffPassword) 
	{
		super(staffID, staffAddress, staffPhone, staffEmail, staffUsername, staffPassword);
		this.staffFirstName = staffFirstName;
                this.staffSurname = staffSurname;
                this.staffJobTitle = staffJobTitle;
	}
        /**
         * Constructor for Staff Object with just Username and Password parameters
         * @param staffUsername - String parameter for Staff Username
         * @param staffPassword - String parameter for Staff Password
         */
	//Constructor for just username and password
	public Staff(String staffUsername, String staffPassword)
	{
		super(1,  new Address(), "Not defined", "Not defined",  
				staffUsername, staffPassword);
                this.staffFirstName = "Not defined";
                this.staffSurname = "Not defined";
		this.staffJobTitle = "Not defined";
	}
        /**
         * Getter method for Staff Firstname
         * @return - Returns String parameter for Staff Firstname
         */
	//Getter and Setters
        public String getStaffFirstname() {
		return staffFirstName;
	}
        /**
         * Setter method for Staff Firstname
         * @param staffFirstName - Takes a String Parameter for Staff Firstname
         */
	public void setStaffFirstname(String staffFirstName) {
		this.staffFirstName = staffFirstName;
	}
        /**
         * Getter method for Staff Surname
         * @return - Returns String parameter for Staff Surname
         */
        public String getStaffSurname() {
		return staffSurname;
	}
        /**
         * Setter method for Staff Surname
         * @param staffSurname - Takes a String Parameter for Staff Surname
         */
	public void setStaffSurname(String staffSurname) {
		this.staffSurname = staffSurname;
	}
        /**
         * Getter method for Staff Jobtitle
         * @return - Returns String parameter for Staff Jobtitle
         */
	public String getStaffJobTitle() {
		return staffJobTitle;
	}
        /**
         * Setter method for Staff Jobtitle
         * @param staffJobTitle - Takes a String Parameter for Staff Jobtitle
         */
	public void setStaffJobTitle(String staffJobTitle) {
		this.staffJobTitle = staffJobTitle;
	}
	/**
         * To String method for Staff Object
         * @return - Returns a string with Staff details 
         */
	//To string method
	@Override
	public String toString() {
		return "Staff [staffID=" + staffID + ", staffFirstName=" + staffFirstName + ", staffSurname=" + staffSurname
				+ ", staffAddress=" + staffAddress + ", staffPhone=" + staffPhone + ", staffEmail=" + staffEmail
				+ ", staffJobTitle=" + staffJobTitle +", staffUsername=" + staffUsername + ", staffPassword=" 
				+ staffPassword + "]";
	}
	
}//end class
