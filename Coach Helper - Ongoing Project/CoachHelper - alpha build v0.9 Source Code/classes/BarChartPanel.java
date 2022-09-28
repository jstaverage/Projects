//Author: Shane Kennedy
//Purpose: Class to create a horizontal bar chart of times

package classes;

//Imports
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;

//Bar Chart Panel class
public class BarChartPanel extends JPanel
{	
	//Instance Variable
	private String title;
	private ArrayList<Integer> times;
	
	//Empty constructor for before values are loaded in program
	public BarChartPanel()
	{
		super();
		this.title = "";
		this.times = null;
	}
	//Full constructor (parameters: String title of chart, ArrayList:times in hsecs
	public BarChartPanel(String title, ArrayList<Integer> times)
	{
		super();
		this.title = title;
		this.times = times;
	}
	@Override
	public void paintComponent(Graphics g)
	{	
		//Call paintComponent method of JPanel
		super.paintComponent(g);
		
		//Get size of JPanel
		Dimension panelSize = getSize();
		int panelHeight = panelSize.height;
		int panelWidth = panelSize.width;
		
		//Create font and font metrics for title and labels
		Font titleFont = new Font("Segue", Font.BOLD, 18);
		FontMetrics metric = g.getFontMetrics(titleFont);
		Font labelFont = new Font("Segue", Font.BOLD, 16);
		FontMetrics labelMetric = g.getFontMetrics(labelFont);
		
		//Padding values
		int smallPadding = 10;
		int padding = 50;
		int barPadding = 5;
		
		//Set bounds of chart at left and right
		int chartStartLeft = 50; //padding 50 for y axis and number of bars
		int chartEndRight = panelWidth - 20;	//padding of 20 at right of chart
		int chartStartTop = metric.getHeight()+(smallPadding*2); //gap at top for title font with padding
		int chartEndBottom = panelHeight - 40;	//padding 40 at bottom for x axis and time scale
		
		//Draw y axis
		g.drawLine(chartStartLeft, chartStartTop, chartStartLeft, chartEndBottom);
		//Draw x axis
		g.drawLine(chartStartLeft, chartEndBottom, chartEndRight, chartEndBottom);
		//Check if times is null, if it is return
		if(times == null)
		{	
			return;
		}
		//Check if times size is 0, if it is return
		else if(times.size() == 0)
		{
			return;
		}
		//Else draw rest of chart
		else
		{
			//Get min and max times
			double min = times.get(0);
			double max = times.get(0);
			for(int i = 0; i < times.size();i++)
			{
				if(max < times.get(i))
				{
					max = times.get(i);
				}
				if(min > times.get(i))
				{
					min = times.get(i);
				}
			}
			//Cutoff time is 90% of min, chart starts from this time to emphasis differences
			double cutOffTime = min * .9d ;		
			
			//Draw title
			int titleWidth = metric.stringWidth(title);
			//Y is title height + some padding
			int y = metric.getAscent()+smallPadding;
			//X coordinate, center title
			int x = (panelWidth-titleWidth)/2;
			g.setFont(titleFont);
		    g.drawString(title, x, y);
		    
		    //Set font for labels
		    g.setFont(labelFont);

	    	//Calculate bar height
	    	int noOfBars = times.size();
	    	int barPaddingSize = (noOfBars-1) * barPadding;
			int barHeight = (chartEndBottom - chartStartTop - barPaddingSize)/noOfBars;
			
			//for no of Bars
	    	for(int i = 0; i < noOfBars ; i++)
	    	{	
	    		//Calculate bar x and y values
	    		int valuex = chartStartLeft;
	    		int valuey = (i * (barHeight + barPadding))+(chartStartTop);
	    		
	    		int width = 0;
	    		/*
	    		//Old method converting barwidth to percentage
	    		//If times is max 
	    		if(times.get(i)== max)
	    		{
	    			width = (int) panelWidth-((smallPadding*2)+padding);
	    		}
	    		else
	    		{	
	    			double factor = (times.get(i)-cutOffTime) / (max-cutOffTime);
	    			width = (int) ((panelWidth-((smallPadding*2)+padding))*factor);
	    		}
	    		*/
	    		//New method using formula to map a value from one number scale to another
	    		width = convert2Range(times.get(i),cutOffTime , max , 0, chartEndRight-chartStartLeft);
	    		
	    		//Draw bar number label
	    		String barLabel = String.valueOf(i+1);
				int labelWidth = metric.stringWidth(barLabel);
				//Y is label height + some padding
				y = valuey + (int) (barHeight/2) - labelMetric.getAscent();
				//X coordinate, center title
				x = (chartStartLeft-labelWidth)/2;
				//Draw bar label
				g.drawString(barLabel, x, y);
				//Draw bar
	    		g.setColor((new Color(14, 89, 180)));
	    		g.fillRect(valuex, valuey, width, barHeight);
	    		g.setColor(Color.black);
	    		g.drawRect(valuex, valuey, width, barHeight);
	    		
	    	}
	    	//Draw time labels for cutoff time and max time
    		String timeLabelStart = convertTime((int)cutOffTime); //cutOff time
    		String timeLabelEnd = convertTime((int) max ); //max time
    		
    		//Cutoff time label at start of x axis
			int labelWidth = metric.stringWidth(timeLabelStart);
			//Y is bottom of chart + some padding
			y = chartEndBottom + ((labelMetric.getAscent()+(smallPadding*2))/2) ;
			//X coordinate, center title
			x = chartStartLeft-(labelWidth/2);
			//Draw bar label
			g.drawString(timeLabelStart, x, y);
			
			//Max time label at end of x axis
			labelWidth = metric.stringWidth(timeLabelEnd);
			//Y is bottom of chart + some padding
			y = chartEndBottom + ((labelMetric.getAscent()+(smallPadding*2))/2) ;
			//X coordinate, center title
			x = chartEndRight-labelWidth;
			//Draw bar label
			g.drawString(timeLabelEnd, x, y);
			
			//Draw time increments on graph
			//Calculate spaceLeft for labels
			int spaceLeft = (chartEndRight-chartStartLeft) - (metric.stringWidth(timeLabelStart)/2) - metric.stringWidth(timeLabelEnd);
			int numOfLabels = (spaceLeft/(labelWidth+(smallPadding*2))) - 1;
			//Time increment of bars
			int timeIncrement = (int) (max-cutOffTime) / (numOfLabels+1);
			int timeLocation = (int) (cutOffTime + timeIncrement);
			//Draw bars and times
			for(int i = 0; i < numOfLabels; i++)
			{	
				//Calculate width of time increment
				int width = convert2Range(timeLocation,cutOffTime , max , 0, chartEndRight-chartStartLeft);
				width += chartStartLeft;
				//Draw line
				g.drawLine(width, chartStartTop, width, chartEndBottom);
				//Draw label
				String timeIncLabel = convertTime((int)timeLocation); //value of current time increment
				labelWidth = metric.stringWidth(timeIncLabel); // Label width
				//Y is bottom of chart + some padding
				y = chartEndBottom + ((labelMetric.getAscent()+(smallPadding*2))/2) ;
				//X coordinate, center of width
				x = width-(labelWidth/2);
				//Draw bar label
				g.drawString(timeIncLabel, x, y);
				//Increment time location
				timeLocation += timeIncrement;
			}
	    }
		    
			
	}
	//Method to set new values for title and times
	public void redrawGraph(String title, ArrayList<Integer> times)
	{
		this.title = title;
		this.times = times;
	}
	//Method to convert a given integer from one number scale to another
	private int convert2Range(int v, double f_min, double f_max, int t_min, int t_max)
	{
	    /*Given a value (v) in the range f_min-f_max, convert the value
		to its equivalent value in the range t_min-t_max.

	    Based on the technique described here:
	        http://james-ramsden.com/map-a-value-from-one-number-scale-to-another-formula-and-c-code/
	    */
	    return (int) (t_min + (t_max - t_min) * ((v - f_min) / (f_max - f_min)));
	}
	//Method to convert time from hsecs to a String of form "mins: secs. hsecs"
	private String convertTime(int inputTime)
	{
		String result = "";
		int secs = 0;
		int mins = 0;
		int hsecs = 0;
		
		mins = (int) inputTime/6000;
		inputTime -= (mins*6000);
		
		secs = (int) inputTime/100;
		inputTime -= (secs * 100);
		
		hsecs = inputTime;
		
		if(mins < 10)
		{
			result += "0" + String.valueOf(mins) + ":";
		}
		else
		{
			result += String.valueOf(mins) + ":";
		}
		if(secs < 10)
		{
			result += "0" + String.valueOf(secs) + ".";
		}
		else
		{
			result += String.valueOf(secs) + ".";
		}
		if(hsecs < 10)
		{
			result += "0" + String.valueOf(hsecs);
		}
		else
		{
			result += String.valueOf(hsecs);
		}
		return result;
	}//end method
	    		
}//end class
