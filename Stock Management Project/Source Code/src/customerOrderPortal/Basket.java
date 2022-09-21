//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Basket class to create Basket objects for customer purchases
package customerOrderPortal;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import databaseConnection.ConnectDB;


//Basket Class

/**
 * Basket Object representing Custome Shopping Basket
 * @author Shane Kennedy
 */
public class Basket 
{   
    //Instance variable to store owner of basket becauce mysql table will have that name as identifier
    private String basketOwner;
    //Two arraylists instance variables for the item IDs and quantitys of products in the Basket object
    private ArrayList<Integer> itemID;
    private ArrayList<Integer> quantity;
    /**
     * Constructor for Basket object to store Customer order details
     * @param basketOwner - Takes a String parameter which is the Basket owner
     */
    //Constructor for Basket Object
    public Basket(String basketOwner)
    {   
        this.basketOwner = basketOwner;
        itemID = new ArrayList<Integer>();
        quantity = new ArrayList<Integer>();
    }
    /**
     * Getter Method to return Basket owner
     * @return - Returns a String which represents the basket owner
     */
    //Getters and Setters
    public String getBasketOwner()
    {
        return basketOwner;
    }
    /**
     * Setter Method to set Basket owner
     * @param basketOwner - Takes a String parameter which represents the basket owner
     */
    public void setBasketOwner(String basketOwner)
    {
        this.basketOwner = basketOwner; 
    }
    /**
    * Getter Method to set ArrayList of integers representing Item IDs in basket
    * @return - Returns an ArrayList of Integers which stores Items IDs of items in basket
    */
    public ArrayList<Integer> getItemArray()
    {
        return itemID;
    }
    /**
     * Setter Method to set ArrayList of Integers which stores Item IDs of Items in basket
     * @param itemID - Takes an ArrayList of Integers as parameter which stores Items IDs of items in basket
     */
    public void setItemArray(ArrayList<Integer> itemID)
    {
        this.itemID = itemID; 
    }
    /**
    * Getter Method to set ArrayList of Integers which represent Item quantities in basket
    * @return - Returns an ArrayList of Integers which stores Item quantities of items in basket
    */
    public ArrayList<Integer> getQuantityArray()
    {
        return quantity;
    }
    /**
     * Setter Method to set ArrayList of Integers which stores Item quantities of Items in basket
     * @param quantity - Takes an ArrayList of Integers as parameter which stores Items quantities of items in basket
     */
    public void setQuantityArray(ArrayList<Integer> quantity)
    {
        this.quantity = quantity; 
    }
    /**
     * Method which adds an item to basket, if an item is already in basket will add quantity to that item
     * @param itemID - Takes an integer which represents Item ID of item
     * @param quantity - Takes an integer which represents Item quantity of item
     */
    //Method to add items to basket, will increase quantity of items already in basket instead of adding them
    public void addItem(int itemID, int quantity)
    {   
        //If basket already contains an item, add quantity
        if(this.itemID.contains(itemID))
        {
            int index = this.itemID.indexOf(itemID);
            int currentQuantity = this.quantity.get(index);
            this.quantity.set(index, currentQuantity+quantity);
            
        }
        //Else just add item
        else
        {
            this.itemID.add(itemID);
            this.quantity.add(quantity);
        }
    }
    /**
     * Method to delete an item from basket
     * @param itemID - Takes an integer representing Item ID of item to be deleted
     */
    //Method to remove items from basket
     public void removeItem(int itemID)
    {
        int index = this.itemID.indexOf(itemID);
        this.itemID.remove(index);
        this.quantity.remove(index);
    }
     /**
      * To String method for basket object
      * @return - Returns a String listing Item ID and quantity of Items in basket
      */
    //To String method
    public String toString()
    {
        String result = "";
        int i = 0;
        result += "Item ID Array Contents\n";
        for(Object x: itemID)
        {   
            result += "Item ID Index "+(i++)+": " + String.valueOf(x) +"\n";
            
        }
        i = 0;
        result += "\nQuantity Array Contents\n";
        for(Object x: quantity)
        {   
            
            result += "Quantity Index "+(i++) +": " + String.valueOf(x)+"\n";
           
        }
        return result;
    }
    /**
     * Method which returns number of Items in basket
     * @return - Returns an integer representing number of items in basket
     */
    //Method to return number of items in basket
    public int noOfItems()
    {
        int noOfItems = itemID.size();
        return noOfItems;
    }
    /**
     * Method to change quantity of an Item in basket table in database
     * @param itemID - Integer representing the Item ID of item to have quantity changed
     * @param quantity - Integer representing the new quantity
     * @return - Returns integer representing if database update worked, 1 for success, 0 for failure
     */
    //Method to change the quantity of an item in basket table in database, returns 0 if method fails
    public int changeItemQuantity(int itemID, int quantity)
    {	
        //Create connection object
        ConnectDB con = new ConnectDB();
        int i=0;
        //Change item quantity in array
        this.quantity.set(this.itemID.indexOf(itemID), quantity);
        //Prepare sql query
        String query = "UPDATE Basket"+basketOwner+" SET Quantity=? WHERE Item_ID=?";
        //Execute query
        i = con.prepareStatement(query, new ArrayList<Object>(Arrays.asList(quantity, itemID)));
        return i;

    }
    /**
     * Method to add an item to Basket table in database, if item already in database will add quantity
     * @param inputID - Integer representing Item ID to add to database
     * @return - Returns integer representing if database update worked, 1 for success, 0 for failure 
     */
    //Method to add item to basket table in database
    public int addItemBasketTable(int inputID)
    {	
        //Create connection object
        ConnectDB con = new ConnectDB();
        int i=0;
        //Get index of item from basket
        int index = itemID.indexOf(inputID);
        ResultSet set = null;
        try
        {
            //Check if item already in basket
            String query = "SELECT Item_ID FROM Basket"+basketOwner+" WHERE Item_ID=?";
            set = con.returnSetCond(query,new ArrayList<Object>(Arrays.asList(inputID)));
            //Set is not empty, item already in basket so change item quantity instead
            if(set.next())
            {   
                //Return 1 if method successful
                int j = changeItemQuantity(inputID, quantity.get(index));
                return j;
            }
            //Else add item to basket, return 1 it query successful
            else
            {   
                query = "INSERT INTO Basket"+basketOwner+" (Item_ID, Quantity) VALUES (?,?)";
                i = con.prepareStatement(query, new ArrayList<Object>(Arrays.asList(inputID,quantity.get(index) )));
                return i;
            }

        }
        //Return 0 if exception occurs
        catch(SQLException e)
        {
            return i;
        }
    }
    /**
     * Method to delete an item from Basket database table
     * @param inputID - Integer representing item ID to be deleted
     * @return - Returns integer representing if database update worked, 1 for success, 0 for failure 
     */
    //Method to delete item from basket table in database, returns 1 if successful
    public int deleteItemfromTable(int inputID)
    {	
        //Create connection object
        ConnectDB con = new ConnectDB();
        int i=0;
        //Prepare query
        String query = "DELETE FROM Basket"+basketOwner+" WHERE Item_ID=?";
        i = con.prepareStatement(query, new ArrayList<Object>(Arrays.asList(inputID)));
        //If i is 1, item exists in table so delete it from database and basket object
        if(i == 1)
        {
            int index = itemID.indexOf(inputID);
            itemID.remove(index);
            quantity.remove(index);
        }
        return i;
    }
    /**
     * Method to delete all items from Basket table in database
     */
    //Method to delete all items in basket
    public void deleteAllItems()
    {
        //For every item, delete from database and basket object
        for(int i = itemID.size()-1; i >= 0; i--)
        {
            deleteItemfromTable(itemID.get(i));
        }
    }
 
}//end class
