//Author: Shane Kennedy
//Purpose: Swimmer object to represent swimmers in time sets

package classes;

//Imports
import java.util.ArrayList;

public class Swimmer 
{
    private String name;
    private String age;
    private ArrayList<Event> events;
    
    //Constructor for Swimmer, takes filename as a parameter
    public Swimmer(String filename)
    {   
        ArrayList<String> nameAndDetails = convertFilename(filename);
        this.name = nameAndDetails.get(0);
        if(Integer.parseInt(nameAndDetails.get(1)) < 13)
        {
            this.age = "Under 13";
        }
        else if(Integer.parseInt(nameAndDetails.get(1)) >= 13 && Integer.parseInt(nameAndDetails.get(1)) < 18)
        {
            this.age = "13 to 17";
        }
        else
        {
            this.age = "Seniors";
        }
        this.events = new ArrayList<Event>();
    }
    //Getter for Swimmer name
    public String getSwimmerName()
    {
        return name;
    }
    //Getter for Swimmer age
    public String getSwimmerAge()
    {
        return age;
    }
    //Getter for Swimmer events
    public ArrayList<Event> getSwimmerEvents()
    {
    	return events;
    }
    //To string method
    public String toString()
    {
        String result = "";
        result += "Swimmer Name: " + name + "\nSwimmer Age: "+ age + "\nSwimmer Events: ";
        for(Event vnt: events)
        {
            result += vnt.toString();
        }
        return result;
    }
    //Method to convert filename into ArrayList with Swimmer name, age and event name
    private ArrayList<String> convertFilename(String filename)
    {   
        ArrayList<String> nameAndDetails = new ArrayList<String>();
        String[] data = (filename.replace(".txt", "")).split("-");
        for(String details:data)
        {
            nameAndDetails.add(data[0]);
            nameAndDetails.add(data[1]);
            nameAndDetails.add(data[2]);
        }
        return nameAndDetails;
    }
    //Method to add event to ArrayList of events
    public void addEvent(String filename,String eventData)
    {   
        Event event = new Event(filename,eventData);
        this.events.add(event);
    }

}//end class
