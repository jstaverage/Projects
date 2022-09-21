//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Address Object to hold addresses
package customerManagement;

//Class for Addresses

/**
 * Address Object for storing address details
 * @author Shane Kennedy
 */
public class Address 
{	
	//Instance Variables
	private String addLine1;
	private String addStreet;
	private String addTown;
	private String addCounty;
	private String addCountry;
	
        /**
         * Default Constructor
         */
	//Empty constructor
	public Address()
	{
		this.addLine1 = "Not defined";
		this.addStreet = "Not defined";
		this.addTown = "Not defined";
		this.addCounty = "Not defined";
		this.addCountry = "Not defined";
	}
        /**
         * Full Constructor
         * @param addLine1 -String parameter for address line 1
         * @param addStreet -String parameter for address street
         * @param addTown -String parameter for address town
         * @param addCounty -String parameter for address county
         * @param addCountry  -String parameter for address country
         */
	//Full Constructor
	public Address(String addLine1, String addStreet, String addTown, String addCounty, String addCountry) 
	{
		this.addLine1 = addLine1;
		this.addStreet = addStreet;
		this.addTown = addTown;
		this.addCounty = addCounty;
		this.addCountry = addCountry;
	}
	/**
         * Getter Method for Address Line 1
         * @return - returns a string representing Address Line 1
         */
	//Getter and Setters
	public String getAddLine1() {
		return addLine1;
	}
        /**
         * Setter Method for Address Line 1
         * @param addLine1 - Takes a string parameter representing Address Line 1
         */
	public void setAddLine1(String addLine1) {
		this.addLine1 = addLine1;
	}
        /**
         * Getter Method for Address Street
         * @return - returns a string representing Address Street
         */
	public String getAddStreet() {
		return addStreet;
	}
         /**
         * Setter Method for Address Street
         * @param addStreet - Takes a string parameter representing Address Street
         */
	public void setAddStreet(String addStreet) {
		this.addStreet = addStreet;
	}
        /**
         * Getter Method for Address Town
         * @return - returns a string representing Address Town
         */
	public String getAddTown() {
		return addTown;
	}
        /**
        * Setter Method for Address Town
        * @param addTown - Takes a string parameter representing Address Town
        */
	public void setAddTown(String addTown) {
		this.addTown = addTown;
	}
        /**
         * Getter Method for Address County
         * @return - returns a string representing Address County
         */
	public String getAddCounty() {
		return addCounty;
	}
        /**
        * Setter Method for Address County
        * @param addCounty - Takes a string parameter representing Address County
        */
	public void setAddCounty(String addCounty) {
		this.addCounty = addCounty;
	}
        /**
         * Getter Method for Address Country
         * @return - returns a string representing Address Country
         */
	public String getAddCountry() {
		return addCountry;
	}
        /**
        * Setter Method for Address Country
        * @param addCountry - Takes a string parameter representing Address Country
        */
	public void setAddCountry(String addCountry) {
		this.addCountry = addCountry;
	}
        /**
         * To String Method for Address Objects
         * @return - Returns a String with address details
         */
	//To String method
	@Override
	public String toString() {
		return "Address [addLine1=" + addLine1 + ", addStreet=" + addStreet + ", addTown=" + addTown + ", addCounty="
				+ addCounty + ", addCountry=" + addCountry + "]";
	}
	
	
	
}//end class
