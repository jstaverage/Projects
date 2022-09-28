//Author: Shane Kennedy
//Purpose: Event object to represent individual events and the list of times

package classes;

//Imports
import java.util.ArrayList;

public class Event 
{
	//Instance variables
    private String eventName;
    private ArrayList<Integer> eventTimes;

    //Constructor parameters, String with eventname, ArrayList with event times
    public Event(String eventName, ArrayList eventTimes)
    {
        this.eventName = eventName;
        this.eventTimes = eventTimes;
    }
    //Constructor parameters, String with filename, ArrayList with event times
    public Event(String filename, String eventData)
    {
        this.eventName = convertFilenameToEventname(filename);
        this.eventTimes = convertEventDataToTimes(eventData);
    }
    //Getter for eventname
    public String getEventName()
    {
    	return eventName;
    }
    //Getter for event times
    public ArrayList<Integer> getEventTimes()
    {
    	return eventTimes;
    }
    //To string method
    public String toString()
    {   String result = "";
        result += "\nEvent Name: " + eventName + "\nEvent Times: ";
        for(int time:eventTimes)
        {
            result += "\n" + time;
        }
        return result;
    }
    //Method to take filename and extract event name
    private String convertFilenameToEventname(String filename)
    {   
        String[] data = (filename.replace(".txt", "")).split("-");
        String eventname = data[2] + " " + data[3];
        return eventname;
    }
    //Method to take eventData string and convert to ArrayList of event times in hsecs
    private ArrayList<Integer> convertEventDataToTimes(String eventData)
    {   
        ArrayList<Integer> timeData = new ArrayList<Integer>();
        String[] times = eventData.split(",");
        for(String time:times)
        {   
            int hsecs = 0;
            if(time.contains(":"))
            {
                String[] increments = time.split("[:.]");
                for(int i =0; i < increments.length;)
                {   
                    hsecs += (Integer.parseInt(increments[i])*6000)+((Integer.parseInt(increments[i+1]))*60)+(Integer.parseInt(increments[i+2]));
                    i += 3;
                    timeData.add(hsecs);
                }
            }
            else
            {
                String[] increments = time.split("[.]");
                for(int i =0; i < increments.length;)
                {   
                    hsecs += ((Integer.parseInt(increments[i])*60)+Integer.parseInt(increments[i+1]));
                    i += 2;
                    timeData.add(hsecs);
                }
            }
        }    
        return timeData;
    }

    
}//end class
