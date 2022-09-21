//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : Order Item class for generating Order Item objects to represent Itsms on orders/invoices
package orderInvoiceManagement;

//Order Item Class

/**
 * Order Item object class for instances of Order Item attributes and behaviours
 * @author Shane Kennedy
 */
public class OrderItem {
    //Instance Variables
    private int productID;
    private int quantity;
    private double currentPrice;
    
    /**
     * Constructor for Order Item object
     * @param productID - Integer parameter representing Product ID
     * @param quantity - Integer parameter representing quantity of item
     * @param currentPrice - - Double parameter representing price of item
     */
    //Constructor
    public OrderItem(int productID, int quantity, double currentPrice)
    {
        this.productID = productID;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }
    /**
     * Getter Method to get Product ID
     * @return - Returns an integer representing Product ID
     */
    //Getters and Setters
    public int getProductID() {
        return productID;
    }
    /**
     * Setter method to set Product ID
     * @param productID - Takes an integer parameter representing Product ID
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }
    /**
     * Getter Method to get Product Quantity
     * @return - Returns an integer representing Product Quantity
     */
    public int getQuantity() {
        return quantity;
    }
     /**
     * Setter method to set Product Quantity
     * @param quantity - Takes an integer parameter representing Product Quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
     /**
     * Getter Method to get Product Price
     * @return - Returns a double representing Product Price
     */
    public double getCurrentPrice() {
        return currentPrice;
    }
     /**
     * Setter method to set Product Quantity
     * @param currentPrice -This parameter represents the currentPrice of Order_Item when order was generated
     */
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
    /**
     * To String method for Order Items
     * @return - Returns a string with Order Item details
     */
    //To String Method
    @Override
    public String toString() {
        return "Product ID: " + productID + "\nQuantity: "+quantity + "\nCurrent Price: " +currentPrice; 
    }

}//end class
