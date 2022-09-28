//Author: Shane Kennedy
//Purpose: Create GUI Frame for window which displays swimmer data and graph
package classes;

//Imports
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class MainFrame extends JFrame
{	
	//Declare GUI components
	private JPanel pnlTitle;
	private JPanel pnlMain;
	private JPanel pnlCombobox;
	private JPanel pnlTimes;
	private JPanel pnlLeftBottom;
	private JPanel pnlMainLeft;
	private JPanel pnlSelectName;
	private JPanel pnlAge;
	private JPanel pnlSelectEvent;
	private JPanel pnlAvgTime;
	private JPanel pnlButtons;
	
	private BarChartPanel pnlMainRight;
	
	private JLabel lblLogo;
	private JLabel lblTitle;
	private JLabel lblAvgTime;
	private JLabel lblName;
	private JLabel lblAge;
	private JLabel lblEvent;
	
	private JComboBox<String> jCmbName;
	private JComboBox<String> jCmbEvent;
	
	private JTable tableTimes;
	
	private JScrollPane tableScrollPane;
	
	private JTextField txtFldAge;
	private JTextField txtFldAvgTime;
	
	private JButton btnNew;
	private JButton btnExit;
	
	//Instance variables
	private ArrayList<Swimmer> swimmers;
	private MainFrame frame;
	
	public MainFrame(ArrayList<Swimmer> swimmers)
	{	
		//Call superclass constructor
		super("Coach Helper 9000 - Swimmer Times");
		
		//Set references
		this.swimmers = swimmers;
		frame = this;
		
		//Call methods
		buildFrame();
		fillNameComboBox();
	}//end constructor
	//Method to build GUI frame
	public void buildFrame()
	{
		//Create panel for title north section
        pnlTitle = new JPanel()
        {	
        	//Create color gradient for pnlTitle
        	 @Override
             protected void paintComponent(Graphics grphcs) {
                 super.paintComponent(grphcs);
                 Graphics2D g2d = (Graphics2D) grphcs;
                 g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                         RenderingHints.VALUE_ANTIALIAS_ON);
                 GradientPaint gp = new GradientPaint(0, 0,
                         getBackground().brighter().brighter(), 0, getHeight(),
                         getBackground().darker().darker());
                 g2d.setPaint(gp);
                 g2d.fillRect(0, 0, getWidth(), getHeight()); 

             }
        };
        pnlTitle.setPreferredSize(new Dimension(800,100));
        pnlTitle.setBackground(new Color(14, 89, 180));
        pnlTitle.setLayout(new BoxLayout(pnlTitle,BoxLayout.LINE_AXIS)); 
            //Create JLabels
	        lblLogo = new JLabel();
	        lblLogo.setPreferredSize(new Dimension(80,100));
	        lblLogo.setBackground(new Color(192, 218, 244));
	        lblLogo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/stopwatch.png")).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
            lblLogo.setOpaque(true);
            
            lblTitle = new JLabel("Coach Helper 9000 - Swimmer Times");
            lblTitle.setFont(new Font("Segoe UI", 1, 32));
            lblTitle.setForeground(new Color(192, 218, 244));
            lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        //Add components
        pnlTitle.add(Box.createRigidArea(new Dimension(20,0)));
        pnlTitle.add(lblLogo);
        pnlTitle.add(Box.createHorizontalGlue());
        pnlTitle.add(lblTitle);
        pnlTitle.add(Box.createHorizontalGlue());
        
        //Build pnlCombobox to contain JPanel to pick swimmer and event
        pnlCombobox = new JPanel();
        pnlCombobox.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        pnlCombobox.setMaximumSize(new Dimension(480,60));
        pnlCombobox.setBackground(new Color(185, 209, 234));
        		//Build JPanel to contain name label and combobox
        		pnlSelectName = new JPanel(new GridLayout(2,1));
        		pnlSelectName.setPreferredSize(new Dimension(150,80));
	        		//Build JLabel JComboboxes for panel
        			lblName = new JLabel("Select Name");
        			lblName.setPreferredSize(new Dimension(150,40));
        			lblName.setBackground(new Color(14, 89, 180));
        			lblName.setForeground(Color.WHITE);
        			lblName.setFont(new Font("Segoe UI", 1, 16));
        			lblName.setHorizontalAlignment(SwingConstants.CENTER);
        			lblName.setOpaque(true);

        			jCmbName = new JComboBox<String>();
	        		jCmbName.setPreferredSize(new Dimension(150,40));
	        		jCmbName.setFont(new Font("Segoe UI", 1, 14));
	        		jCmbName.addActionListener(
	    		            //Anonymous Inner Class
	    					new ActionListener() 
	    					{
	    						//Handling jCmbName changed action event
	    			            public void actionPerformed(ActionEvent evt) 
	    			            {
	    			            	//Name combobox changed event
	    			                fillEventComboBox((String) jCmbName.getSelectedItem());
	    			            }
	    					}
	    					);//End Action Listener
	        			//Add components
	        			pnlSelectName.add(lblName);
	        			pnlSelectName.add(jCmbName);
    			//Build JPanel to contain act label and textfield
        		pnlAge = new JPanel(new GridLayout(2,1));
        		pnlAge.setPreferredSize(new Dimension(150,80));
        			//Build JLabel and JTextField for Age panel
        			lblAge = new JLabel("Age Group");
        			lblAge.setPreferredSize(new Dimension(150,40));
        			lblAge.setBackground(new Color(14, 89, 180));
        			lblAge.setForeground(Color.WHITE);
        			lblAge.setHorizontalAlignment(SwingConstants.CENTER);
        			lblAge.setFont(new Font("Segoe UI", 1, 16));
        			lblAge.setOpaque(true);
        			
	        		txtFldAge = new JTextField();
	        		txtFldAge.setPreferredSize(new Dimension(150,40));
	        		txtFldAge.setFont(new Font("Segoe UI", 1, 16));
	        		txtFldAge.setForeground(new Color(14, 89, 180));
	        		txtFldAge.setEditable(false);
	        			//Add components
	        			pnlAge.add(lblAge);
	        			pnlAge.add(txtFldAge);
    			//Build JPanel to contain name label and combobox
        		pnlSelectEvent = new JPanel(new GridLayout(2,1));
        		pnlSelectEvent.setPreferredSize(new Dimension(150,80));
        			//Build JLabel and combobox for select event
	        		lblEvent = new JLabel("Select Event");
	        		lblEvent.setPreferredSize(new Dimension(150,40));
	        		lblEvent.setBackground(new Color(14, 89, 180));
	        		lblEvent.setForeground(Color.WHITE);
	        		lblEvent.setFont(new Font("Segoe UI", 1, 16));
	        		lblEvent.setHorizontalAlignment(SwingConstants.CENTER);
	        		lblEvent.setOpaque(true);
	    			
	        		jCmbEvent = new JComboBox<String>();
	        		jCmbEvent.setPreferredSize(new Dimension(150,40));
	        		jCmbEvent.setEnabled(false);
	        		jCmbEvent.setFont(new Font("Segoe UI", 1, 14));
	        		jCmbEvent.addActionListener(
	    		            //Anonymous Inner Class
	    					new ActionListener() 
	    					{
	    						//Handling jCmbEvent changed action event
	    			            public void actionPerformed(ActionEvent evt) 
	    			            {	
	    			            	//Name combobox changed event
	    			            	fillTable((String) jCmbEvent.getSelectedItem());
	    			            }
	    					}
	    					);//End Action Listener
		        		//Add components
	        			pnlSelectEvent.add(lblEvent);
	        			pnlSelectEvent.add(jCmbEvent);
        //Add components
        pnlCombobox.add(pnlSelectName);
        pnlCombobox.add(pnlAge);
        pnlCombobox.add(pnlSelectEvent);
        
        //Build pnlTimes to contain swimmers times and average time
        pnlTimes = new JPanel(new BorderLayout());
        pnlTimes.setMinimumSize(new Dimension(470,200));
        pnlTimes.setMaximumSize(new Dimension(470,200));
        pnlTimes.setBackground(new Color(185, 209, 234));
        	//Build JTable to contain swimmer times
      //Build JTable
    	tableTimes = new JTable();
    	tableTimes.setFont(new Font("Segoe UI", 0, 14));
    	tableTimes.setModel(
        	//Default table model
        	new DefaultTableModel
        	(
                new Object [][] {                },
                //Column Names
                new String [] {
                    "Swim Number", "Time"}
            ) 
        	{	
        		//Column classes
                Class[] types = new Class [] {
                    java.lang.Integer.class, java.lang.String.class	};
                boolean[] canEdit = new boolean [] {
                    false, false };

                public Class getColumnClass(int columnIndex) {
                    return types [columnIndex];
                }

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
            });
    	tableTimes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_NEXT_COLUMN);//Table resize
    	tableTimes.setRowHeight(26);//Row height
    	tableTimes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);//Selection mode
    	//Set column widths
    	tableTimes.getColumnModel().getColumn(0).setPreferredWidth(55);
    	tableTimes.getColumnModel().getColumn(1).setPreferredWidth(155);
    	//Set fonts
    	tableTimes.setFont(new Font("Segoe UI", 0, 16));
    	tableTimes.setForeground(new Color(14, 89, 180));
    	tableTimes.setAlignmentX(CENTER_ALIGNMENT);
    	//Center table header
    	TableCellRenderer rendererFromHeader = tableTimes.getTableHeader().getDefaultRenderer();
    	JLabel headerLabel = (JLabel) rendererFromHeader;
    	headerLabel.setHorizontalAlignment(JLabel.CENTER);
    	//Center table cell text
    	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    	centerRenderer.setHorizontalAlignment( JLabel.CENTER );
    	tableTimes.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
    	tableTimes.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
    	//Set table scroll pane
        tableScrollPane = new JScrollPane();
        tableScrollPane.setViewportView(tableTimes);
        //Add components
        pnlTimes.add(tableScrollPane, BorderLayout.CENTER);
        
        //Build pnlCombobox to contain swimmers times and average time
        pnlLeftBottom = new JPanel();
        pnlLeftBottom.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
        pnlLeftBottom.setMaximumSize(new Dimension(470,60));
        pnlLeftBottom.setPreferredSize(new Dimension(470,60));
        
        	//Build panel for buttons
        	pnlButtons = new JPanel(new GridLayout(1,2,5,0));
        	pnlButtons.setPreferredSize(new Dimension(185,40));
        		
	        	//Build JButtons JLabel + JTextArea to contain swimmer times
	        	btnNew = new JButton("New Data");
	        	btnNew.setPreferredSize(new Dimension(110,40));
	        	btnNew.setBackground(new Color(92, 187, 233));
	        	btnNew.setForeground(Color.white);
	        	btnNew.setFont(new Font("Segoe UI", 1, 10));
	        	btnNew.setOpaque(true);
	        	btnNew.setAlignmentY(CENTER_ALIGNMENT);
	        	btnNew.addActionListener(
	        			new ActionListener()
	        			{	
	        				//Listener to handle btnNew pressed event
							public void actionPerformed(ActionEvent e) 
							{	
								//Open menu frame
								MenuFrame frm = new MenuFrame();
								frm.setVisible(true);
								frame.dispose();
							}
	        			});//End action listener
	        	btnExit = new JButton("Exit");
	        	btnExit.setPreferredSize(new Dimension(110,40));
	        	btnExit.setBackground(new Color(92, 187, 233));
	        	btnExit.setForeground(Color.white);
	        	btnExit.setFont(new Font("Segoe UI", 1, 10));
	        	btnExit.setOpaque(true);
	        	btnExit.setAlignmentY(CENTER_ALIGNMENT);
	        	btnExit.addActionListener(
	        			new ActionListener()
	        			{	
	        				//Listener to handle btnExit pressed event
							public void actionPerformed(ActionEvent e) 
							{
								frame.dispose();
							}
	        			});//End action listener
	             	
	        	//Add components
	        	pnlButtons.add(btnNew);
	        	pnlButtons.add(btnExit);
        	
	        //Build panel for average time
	        pnlAvgTime = new JPanel(new GridLayout(1,2));
	        pnlAvgTime.setPreferredSize(new Dimension(240,40));
	        
	        //Build JLabela and Textfield
        	lblAvgTime = new JLabel("Average Time");
        	lblAvgTime.setPreferredSize(new Dimension(120,40));
        	lblAvgTime.setBackground(new Color(14, 89, 180));
        	lblAvgTime.setForeground(Color.WHITE);
        	lblAvgTime.setFont(new Font("Segoe UI", 1, 14));
        	lblAvgTime.setHorizontalAlignment(SwingConstants.CENTER);
        	lblAvgTime.setOpaque(true);
        	lblAvgTime.setAlignmentY(CENTER_ALIGNMENT);
        	
        	txtFldAvgTime = new JTextField();
        	txtFldAvgTime.setHorizontalAlignment(SwingConstants.CENTER);
        	txtFldAvgTime.setFont(new Font("Segoe UI", 1, 14));
        	txtFldAvgTime.setPreferredSize(new Dimension(120,40));
        	txtFldAvgTime.setEditable(false);
        	txtFldAvgTime.setAlignmentY(CENTER_ALIGNMENT);
        	//Add components
        	pnlAvgTime.add(lblAvgTime);
        	pnlAvgTime.add(txtFldAvgTime);
        	
        //Add components
        pnlLeftBottom.add(pnlButtons);
        pnlLeftBottom.add(pnlAvgTime);
        
        //Build pnlMainLeft to contain Comboboxes, Times and AvgTime
        pnlMainLeft = new JPanel();
        pnlMainLeft.setLayout(new BoxLayout(pnlMainLeft,BoxLayout.PAGE_AXIS));
        pnlMainLeft.setPreferredSize(new Dimension(500,600));
        pnlMainLeft.setBackground(new Color(185, 209, 234));
        //Add components
        pnlMainLeft.add(Box.createRigidArea(new Dimension(0,10)));
        pnlMainLeft.add(pnlCombobox);
        pnlMainLeft.add(Box.createRigidArea(new Dimension(0,10)));
        pnlMainLeft.add(Box.createVerticalGlue());
        pnlMainLeft.add(pnlTimes);
        pnlMainLeft.add(Box.createVerticalGlue());
        pnlMainLeft.add(pnlLeftBottom);
        pnlMainLeft.add(Box.createRigidArea(new Dimension(0,10)));
        
        //Build pnlMainRight
        pnlMainRight = new BarChartPanel();
        pnlMainRight.setPreferredSize(new Dimension(500,600));
        
        //Build pnlMain to contain swimmer data and graph
        pnlMain = new JPanel();
        pnlMain.setLayout(new BorderLayout());
        	//Add components
        	pnlMain.add(pnlMainLeft, BorderLayout.WEST);
        	pnlMain.add(pnlMainRight, BorderLayout.CENTER);
        	
        //Add components
        getContentPane().add(pnlTitle, BorderLayout.NORTH);
        getContentPane().add(pnlMain, BorderLayout.CENTER);
        //Set JFrame properties
		setSize(1000,700);
		setLocationRelativeTo(null);
		setIconImage(new ImageIcon(getClass().getResource("/images/stopwatch.png")).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH));
		setMinimumSize(new Dimension(1000,700));
	    setDefaultCloseOperation(EXIT_ON_CLOSE);
	}//end method
	
	//Method to fill both Name ComboBox on Frame opening
	private void fillNameComboBox()
	{	
		ArrayList<String> names = new ArrayList<String>();
		for(Swimmer person:swimmers)
		{
			names.add(person.getSwimmerName());
		}
        //Fill name combobox
        jCmbName.setModel(new DefaultComboBoxModel(names.toArray()));
        //Set index to -1
        jCmbName.setSelectedIndex(-1);
	}
	//Method to fill Event ComboBox on Frame opening
	private void fillEventComboBox(String name)
	{	
		ArrayList<String> eventNames = new ArrayList<String>();
		for(Swimmer person:swimmers)
		{
			if(person.getSwimmerName().equals(name))
			{	
            	txtFldAge.setText(person.getSwimmerAge());
				ArrayList<Event> events = person.getSwimmerEvents();
				for(Event event:events)
				{
					eventNames.add(event.getEventName());
				}
				
			}
		}
        //Fill name combobox
        jCmbEvent.setModel(new DefaultComboBoxModel(eventNames.toArray()));
        //Set index to -1
        jCmbEvent.setSelectedIndex(-1);
        jCmbEvent.setEnabled(true);
	}
	private void fillTable(String eventName)
	{	
		DefaultTableModel tblModel = (DefaultTableModel) tableTimes.getModel();
        tblModel.setRowCount(0);
        String title = "";
		int counter = 1;
		ArrayList<Event> eventNames = null;
		ArrayList<Integer> eventTimes = null;
		for(Swimmer person:swimmers)
		{
			if(person.getSwimmerName().equals((String) jCmbName.getSelectedItem()))
			{	
				title += person.getSwimmerName() + " " + person.getSwimmerAge();
				eventNames = person.getSwimmerEvents();
				break;
			}
		}
		if(eventNames != null)
		{
			for(Event event:eventNames)
			{
				if(event.getEventName().equals(eventName))
				{	
					title += " " + event.getEventName();
					eventTimes = event.getEventTimes();
					break;
				}
			}
		}
		if(eventTimes != null)
		{	
			double avgTime = 0;
			for(int time : eventTimes)
			{
				Object[] tbData = {(int) counter, (String) convertTime(time) };
				avgTime += time;
				tblModel = (DefaultTableModel)tableTimes.getModel();
				tblModel.addRow(tbData);
		        tblModel.fireTableDataChanged();
		        counter++;
			}
			avgTime /= eventTimes.size();
			txtFldAvgTime.setText(convertTime((int) avgTime));
        	pnlMainRight.redrawGraph(title, eventTimes);
        	pnlMainRight.repaint();
		}
	}
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
	}
}//end class
