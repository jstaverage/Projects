//Author: Shane Kennedy
//Purpose: Create GUI Frame for window where user selects folder with swimmer data

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
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MenuFrame extends JFrame
{   
	//Declare GUI components
    JPanel pnlTitle;
    JLayeredPane layeredPnlBack;
    JPanel pnlText;

    JLabel lblLogo;
    JLabel lblTitle;
    JLabel lblBackground;
    JLabel lblInstructions;
    
    JButton btnChoose;   
    
    JFileChooser choose;
    
    //Instance variable to save reference to MenuFrame
    private MenuFrame frame;
    
    //Constructor
    public MenuFrame()
    {   
    	//Call constructor of JFrame class
        super("Coach Helper - Choose Folder");
        
        frame = this;
        
        //Create panel for title north section
        pnlTitle = new JPanel()
        {		
        		//Add color gradient to pnlTitle
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
        pnlTitle.setPreferredSize(new Dimension(800,110));
        pnlTitle.setBackground(new Color(14, 89, 180));
        pnlTitle.setLayout(new BoxLayout(pnlTitle,BoxLayout.LINE_AXIS)); 
            //Create JLabels
	        lblLogo = new JLabel();
   	        lblLogo.setPreferredSize(new Dimension(80,100));
	        lblLogo.setBackground(new Color(192, 218, 244));
	        lblLogo.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/stopwatch.png")).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH)));
            lblLogo.setOpaque(true);
            
            lblTitle = new JLabel("Coach Helper 9000 - Select Folder");
            lblTitle.setFont(new Font("Segoe UI", 1, 28));
            lblTitle.setForeground(new Color(192, 218, 244));
            lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        //Add components
        pnlTitle.add(Box.createRigidArea(new Dimension(20,0)));
        pnlTitle.add(lblLogo);
        pnlTitle.add(Box.createHorizontalGlue());
        pnlTitle.add(lblTitle);
        pnlTitle.add(Box.createHorizontalGlue());

        //Create panel for text and file picker
        
        pnlText = new JPanel();
        pnlText.setBounds(150,130,500,80);
        pnlText.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        pnlText.setBackground(new Color(192, 218, 244));
        pnlText.setPreferredSize(new Dimension(500,150));
        	//Build JButton and JLabel for file chooser
	        lblInstructions = new JLabel("Please choose folder with swimmer data:");
	        lblInstructions.setFont(new Font("Segoe UI", 1, 14));
	        lblInstructions.setForeground(new Color(14, 89, 180));
	        lblInstructions.setHorizontalAlignment(SwingConstants.CENTER);
	        
	        choose = new JFileChooser();
	        choose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        
	        btnChoose = new JButton("...");
	        btnChoose.setFont(new Font("Segoe UI", 1, 18));
	        btnChoose.setVerticalAlignment(SwingConstants.CENTER);
	        btnChoose.setBackground(new Color(92, 187, 233));
	        btnChoose.setForeground(Color.white);
	        btnChoose.setOpaque(true);
	        btnChoose.setPreferredSize(new Dimension(80,30));
	        btnChoose.addActionListener(
	        		new ActionListener()
	        		{
						public void actionPerformed(ActionEvent e) 
						{	
							  if (choose.showOpenDialog(pnlText) == JFileChooser.APPROVE_OPTION) 
							  { 
							      String directory = choose.getSelectedFile().toString();
							      FolderRead fr = new FolderRead(directory);
							      if(fr.testFolder())
							      {	
							    	  fr.connectToData();
							    	  ArrayList<Swimmer> swimmers = fr.getSwimmers();
							    	  MainFrame frm = new MainFrame(swimmers);
							    	  frm.setVisible(true);
							    	  frame.dispose();
							      }
							      else
							      {
							    	  JOptionPane.showMessageDialog(null, "Unable to read file data, "
					                            + "please check that the folder you selected contains "
					                            + "swim data and is accessible. ", "Error Reading File Data",
					                            JOptionPane.ERROR_MESSAGE );
							      }
							  }
						}
	        		});
        //Add components
	    pnlText.add(lblInstructions);
	    pnlText.add(btnChoose);
        pnlText.add(choose);

        //Create panel for background of south section
        layeredPnlBack = new JLayeredPane();
        layeredPnlBack.setLayout(null);
        	//Add background label
        	lblBackground = new JLabel();
        	lblBackground.setBounds(0,0,800,390);
        	//lblBackground.setPreferredSize(new Dimension(800,390));
        	lblBackground.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/pool.png")).getImage().getScaledInstance(800, 390, Image.SCALE_SMOOTH)));
        	lblBackground.setOpaque(true);
        //Add components and set Layers
        layeredPnlBack.add(pnlText);
        layeredPnlBack.setLayer(pnlText, 1);
        layeredPnlBack.add(lblBackground);
        layeredPnlBack.setLayer(lblBackground, 0);
      

        //Add panels to JFrame
        add(pnlTitle, BorderLayout.NORTH);
        add(layeredPnlBack,BorderLayout.CENTER);
        //Set JFrame Properties
        setSize(800,500);//size
        setIconImage(new ImageIcon(getClass().getResource("/images/stopwatch.png")).getImage().getScaledInstance(80, 100, Image.SCALE_SMOOTH));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);//Center when opened
    }//end constructor
}//end class
