//Student Name 	   : Shane Kennedy
//StudentId Number : C00263504
//Date 		   : March 2022
//Purpose 	   : JTable child objects with columns that auto adjust to cell contents widths
package customGuiComponents;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;


    //Class to set columns to auto adjust to column contents

/**
 * Custom JTable object which automatically adjusts columns widths to contents
 * @author Shane Kennedy
 */
    public class AutoColumnWidthTable extends JTable
    {   
        /**
         * Constructor for Auto Column width table
         */
        //Call normal JTable constructor
        public AutoColumnWidthTable()
        {
            super();
        }
        /**
         * Overrides prepareRenderer method which adjusts Table column width based on preferred size and contents
         * @param renderer - Parameter takes a TableCellRenderer object
         * @param row - Integer parameter representing row number of table
         * @param column - Integer parameter representing column number of table
         * @return - Returns a Component object
         */
        //Override prepareRenderer
        @Override
        public Component prepareRenderer(TableCellRenderer renderer, int row, int column) 
        {
           Component component = super.prepareRenderer(renderer, row, column);
           //Set preferred size in renderWidth
           int rendererWidth = component.getPreferredSize().width;
           //Get column model and save in tableColumn
           TableColumn tableColumn = getColumnModel().getColumn(column);
           //Set preferred width to either preferred width or the renderedWidth+intercell spacing whichever is bigger
           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
           return component;
        }
    }//end class