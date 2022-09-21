//Student Name 	    : Shane Kennedy
//StudentId Number  : C00263504
//Date 	            : March 2022
//Purpose 	    : Connection class for connecting to database and executing prepared statements
package databaseConnection;

//Imports
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
	
    //ConnectDB class for connecting to Database

/**
 * Connection object for connecting to database
 * @author Shane Kennedy
 */
    public class ConnectDB {   

        //Instance Variables
        private final String DATABASE_URL;
        private final String USER;	
        private final String PASSWORD;
        private Connection connection;

        /**
         * Default Constructor for Connect DB class
         */
        //Default constructor
        public ConnectDB()
        {
                this.DATABASE_URL = "jdbc:mysql://localhost:3306/CA3Database";
                this.USER = "root";
                this.PASSWORD = "programming";
                this.connection = null;
        }
        /**
         * Full Constructor for Connect DB class
         * @param dbUrl - String parameter to hold database url
         * @param user - String parameter to hold username
         * @param password - String parameter to hold password
         */
        //Full contructor
        public ConnectDB(String dbUrl, String user, String password)
        {
                this.DATABASE_URL = dbUrl;
                this.USER = user;
                this.PASSWORD = password;
                this.connection = null;
        }

        //No Getters and Setters for security?
        /**
         * Method to connect to database
         */
        //Connect to database method
        public void connectDB()
        {       
            //Try to connect
            try 
            {
                    this.connection = DriverManager.getConnection(this.DATABASE_URL, this.USER, this.PASSWORD);
            }
            //If connection fails print error message
            catch(SQLException e)
            {
                    this.connection = null;
                    if(e.getSQLState().equals("08S01"))
                    {
                        JOptionPane.showMessageDialog(null, "The connection to the database has failed, "
                                + "please check your internet connection and try again.\nIf that fails please contact your network administrator.", 
                                "Database Connection Error", JOptionPane.ERROR_MESSAGE );
                    }
                    else if(e.getSQLState().equals("28000"))
                    {
                        JOptionPane.showMessageDialog(null, "User authentication has failed, "
                                + "\nPlease contact your network administrator.", 
                                "User Authentication Failed", JOptionPane.ERROR_MESSAGE );
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "An unknown error has occured when connecting to database, "
                                + "please contact your network administrator.", 
                                "Unknown connection error", JOptionPane.ERROR_MESSAGE );
                    }
            }
        }
        /**
         * Method to close connection
         */
        //Close connection method
        public void closeConnection()
        {    
            //Do not attempt to close connection if it is null
            if(this.connection != null)
            {
                //If not null, try close connection, give error message if it fails
                try 
                {
                    this.connection.close();
                } 
                catch (SQLException e) 
                {
                    JOptionPane.showMessageDialog(null, "Error closing connection to database, "
                                + "please contact your network administrator.", 
                                "Closing Connection Error", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
        /**
         * Method to create a prepared statement and execute query, opens and close connection automatically
         * @param query - Takes a string as a parameter to store the sql query
         * @param values - Takes an ArrayList of objects which store the parameters for the query
         * @return - Returns an integer representing success or failure of query, 1 for success, 0 for failure
         */
        //Method to prepare statement, return 1 for successful completion, 0 for unsuccessful attempt
        public int prepareStatement(String query, ArrayList<Object> values)
        {	
            PreparedStatement ps = null;
            try
            {       //If not connected, try to connect
                    if(!this.isConnected())
                    {
                        this.connectDB();
                    }
                    //If connection is active try execute query, if successful return 1
                    if(isConnected())
                    {
                        ps = this.connection.prepareStatement(query);
                        for(int i = 1; i <= values.size(); i++)
                        {
                                ps.setObject(i, values.get(i-1));
                        }
                        ps.execute();
                        return 1;
                    }
                    else
                    {
                        return 0;
                    }
            }
            //If query fails, give warning and return 0
            catch (SQLException e) 
            {	
                    JOptionPane.showMessageDialog(null, "Error in database operation, "
                                + "please contact your network administrator.", 
                                "Database operation error", JOptionPane.ERROR_MESSAGE );
                    return 0;
            }
            //Finally, close prepared statement and connection if not null
            finally 
            {   
                this.closePStatement(ps);
                this.closeConnection();
            }
        }
        /**
         * Method to execute a prepared statement with parameters and return a ResultSet
         * @param query - Takes a string as a parameter to store the sql query
         * @param values - Takes an ArrayList of objects which store the parameters for the query
         * @return - Returns a ResultSet object with the results of query
         * @throws SQLException 
         */
        //Method to prepare statement which will return a Result Set, throws error up because cannot close connection before result set is used
        public ResultSet returnSetCond(String query, ArrayList<Object> values) throws SQLException
        {	
                ResultSet set = null;
                PreparedStatement ps = null;
                //If not connected, try to connect
                if(!this.isConnected())
                {
                        this.connectDB();
                }
                //If connection is active try execute query, if successful return result set
                if(isConnected())
                {
                    ps = this.connection.prepareStatement(query);

                    for(int i = 1; i <= values.size(); i++)
                    {
                            ps.setObject(i, values.get(i-1));
                    }
                    set = ps.executeQuery();
                }
                return set;

        }
        /**
         * Method to execute a prepared statement with no parameters and return a ResultSet
         * @param query - Takes a string as a parameter to store the sql query
         * @return - Returns a ResultSet object with the results of query
         * @throws SQLException 
         */
        //Method which will return a Result Set with no added parameters, throws error up because cannot close connection before result set is used
        public ResultSet returnSet(String query) throws SQLException
        {	
                ResultSet set = null;
                PreparedStatement ps = null;
                //If not connected, try to connect
                if(!this.isConnected())
                {
                        this.connectDB();
                }
                //If connection is active try execute query, if successful return result set
                if(isConnected())
                {
                    ps = this.connection.prepareStatement(query);
                    set = ps.executeQuery();
                }
                return set;
        }
        /**
         * Method which checks if connection is active
         * @return - Returns a boolean true for connected, false for not connected
         */
        //Method to check if connection is active
        public boolean isConnected()
        {
            //If connection is not null or closed return true
            try 
            {	
                if(this.connection != null && !this.connection.isClosed())
                {	
                        return true;
                }
            } 
            //If exception is thrown return false
            catch (SQLException e) 
            {	
                    return false;
            }
            return false;
        }
        /**
         * Method which tests if connection is working correctly
         * @return - Returns a boolean true for connection working, false for connection not working
         */
        //Method to test if connection is working
        public boolean testConnection()
        {       
            try 
            {   //If connection is closed attempt to connect
                if(this.connection == null)
                {	
                    this.connectDB();
                    //Return false if connection fails
                    if(this.connection == null)
                    {
                        return false;
                    }
                    else
                    {
                        return true;
                    }
                }
                //Else return true
                else
                {
                    return true;
                }
            } 
            //If exception occurs return false
            catch (Exception e) 
            {	
                    return false;
            }
            finally 
            {
                closeConnection();
            }
        }
        /**
         * Method to close a resultSet
         * @param rs - Takes a ResultSet object as parameter
         */
        //Method to close a result set
        public void closeResultSet(ResultSet rs)
        {   
            try
            {
                if(rs!=null)
                {
                    rs.close();
                }
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, "An error has occured closing ResultSet, "
                            + "please contact your network administrator.", 
                            "ResultSet close error", JOptionPane.ERROR_MESSAGE );
            }
        }
        /**
         * Method to close a Prepared Statement
         * @param ps - Takes a Prepared Statement object as parameter
         */
         //Method to close a prepared statement
        public void closePStatement(PreparedStatement ps)
        {   
            try
            {
                if(ps!=null)
                {
                    ps.close();
                }
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, "An error has occured closing Prepared Statement, "
                            + "please contact your network administrator.", 
                            "Prepared Statement close error", JOptionPane.ERROR_MESSAGE );
            }
        }
        /**
         * Method to return connection
         * @return - returns the connection
         */
        public Connection getConnection()
        {   
            //If connection is closed attempt to connect
            if(this.connection == null)
            {	
                this.connectDB();
            }
            return this.connection;
        }
        
    }//end class
