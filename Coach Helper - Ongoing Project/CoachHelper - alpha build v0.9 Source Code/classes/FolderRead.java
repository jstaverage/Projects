//Author: Shane Kennedy
//Purpose: Reads folder data and converts it into an arraylist of swimmer objects

package classes;

//Imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FolderRead 
{	
	//Instance variables
	private String directory;
	private ArrayList<Swimmer> swimmers;
	
	//Constructor
	public FolderRead(String directory)
	{
		this.directory = directory;
		this.swimmers = null;
	}
	//Getter method for Swimmer ArrayList
	public ArrayList<Swimmer> getSwimmers()
	{
		return swimmers;
	}
    //Method to read swimdata folder and return an ArrayList of the filenames and data 
    public ArrayList<String> readFile()
    {   
        //Create File folder which is folder containing files with swimmer data
        File folder = new File(directory);
        //Create ArrayList to contain file data
        ArrayList<String> fileData = new ArrayList<String>();
        //For loop to cycle through all files in folder
        for(File fileEntry: folder.listFiles())
        {   
            //Try with resources for objects which throw IO exceptions
            try(FileReader fileReader = new FileReader(fileEntry);BufferedReader buffer = new BufferedReader(fileReader))
            { 
                //Get file name of file
                String filename = fileEntry.getName();
                //If file name matches pattern
                if(filename.matches("[\\D]+[-]+[\\d]+[-]+[\\d|\\D]*[-]+[\\D]+[\\.txt]+"))
                {   
                    //Add filename to fileData
                    fileData.add(filename);
                    //Read lines of file
                    String line = "";
                    while((line=buffer.readLine() )!=null)
                    {   
                        //Add file contents to fileData
                        fileData.add(line);
                    }
                }
                
            }
            catch(IOException e)
            {
                
            }
        
        }
        //If no files were read, set fileData to null
        if(fileData.size() == 0)
        {
        	fileData = null;
        }
        return fileData;
    }
    //Method to create an ArrayList of Swimmmer objects from inputData read from folder
    public ArrayList<Swimmer> createSwimmers(ArrayList<String> inputData)
    {
        ArrayList<Swimmer> swimmers = new ArrayList<Swimmer>();
        for(int i = 0; i < inputData.size();)
        {   
            String[] data = (inputData.get(i).replace(".txt", "")).split("-");
            boolean newSwimmer = true;
            for(Swimmer person:swimmers)
            {   
                if(!(data[0].equals(person.getSwimmerName())))
                {   
                    newSwimmer = true;
                }
                else
                {
                    newSwimmer = false;
                    person.addEvent(inputData.get(i), inputData.get(i+1));
                }
            }
            if(newSwimmer)
            {   
                //Create new Swimmer
                swimmers.add(new Swimmer(inputData.get(i)));
                swimmers.get(swimmers.size()-1).addEvent(inputData.get(i), inputData.get(i+1));
            }
            newSwimmer=false;
            i+=2;
        }
            return swimmers;
    }
    //Method to test if folder contains data returns false if it does not
    public boolean testFolder()
    {	
    	boolean conWorking = true;
    	ArrayList<String> testData = readFile();
    	if(testData == null)
    	{
    		conWorking = false;
    	}
    	else if(testData.size() == 0)
    	{
    		conWorking = false;
    	}
    	return conWorking;
    }
    //Method to read file and convert to swimmer data
    public void connectToData()
    {
    	this.swimmers = createSwimmers(readFile());
    }
}//end class
	