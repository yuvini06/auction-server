import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.JLabel;

class Panel implements ActionListener {
	JTextField search_bar;
	public static JFrame frame;
	public static JPanel logPanel;
	public static JPanel searchPanel;
	public static JPanel tablePanel;
	public static JScrollPane scroll;
	public static JTextField symbolfield;
	public static JTextField pricefield;
	public static JLabel symbollabel;
	public static JLabel pricelabel;
	public static JButton updateButton;
	public static JTextArea textarea;
	public static JTable table;
	public static String symbol;
	public static String price;
	public static String secName;
	int row = 10;
	static Font font = new Font("Calibri", Font.BOLD, 20);
	
	public Panel() {

		// Create a frame
		frame = new JFrame("Stock Information");
		
		// Create a panel container to display log information
		logPanel = new JPanel();
		
		// Create a panel container to display search information
		searchPanel = new JPanel();
		
		// Create a panel container to display information of some selected securities in a tabular form 
		tablePanel = new JPanel();
		
		// Create a text area
		textarea = new JTextArea();
		logPanel.add(textarea);
		textarea.setFont(new Font("Calibri", Font.PLAIN, 20)); 
		
		// Create fields to update the price of a given item via the GUI
		symbollabel = new JLabel();
		symbollabel.setText("Symbol");
		pricelabel = new JLabel();
		pricelabel.setText("Bid Price");
		symbolfield = new JTextField(20);
		pricefield = new JTextField(20);
		symbollabel.setFont(new Font("Calibri",Font.PLAIN,20));
		pricelabel.setFont(new Font("Calibri",Font.PLAIN,20));
		searchPanel.add(symbollabel);
		searchPanel.add(symbolfield);
		searchPanel.add(pricelabel);
		searchPanel.add(pricefield);
		
		// Create an update button in the GUI
		updateButton = new JButton();
		updateButton.setText("UPDATE");
		updateButton.setFont(new Font("Calibri",Font.BOLD,15));
		updateButton.addActionListener(this);
		searchPanel.add(updateButton);
		
	    
	    // Display information of some selected securities in a table
	    String headers[]={"SYMBOL","SECURITY NAME","PRICE"}; 
	    String data[][]={
	    		{"FB", Main.stockData.getSecurityName("FB"), Main.stockData.getPrice("FB")},
				{"VRTU", Main.stockData.getSecurityName("VRTU"), Main.stockData.getPrice("VRTU")},
				{"MSFT", Main.stockData.getSecurityName("MSFT"), Main.stockData.getPrice("MSFT")},
				{"GOOGL", Main.stockData.getSecurityName("GOOGL"), Main.stockData.getPrice("GOOGL")},
				{"YHOO", Main.stockData.getSecurityName("YHOO"), Main.stockData.getPrice("YHOO")},
				{"XLNX", Main.stockData.getSecurityName("XLNX"), Main.stockData.getPrice("XLNX")},
				{"TSLA", Main.stockData.getSecurityName("TSLA"), Main.stockData.getPrice("TSLA")},
				{"TXN", Main.stockData.getSecurityName("TXN"), Main.stockData.getPrice("TXN")}
				};

	    table=new JTable(data,headers); 
	    table.setPreferredScrollableViewportSize(new Dimension(1000, 800));
		table.setFillsViewportHeight(true);
		table.setRowHeight(50);
		table.getColumnModel().getColumn(0).setPreferredWidth(200);
		table.getColumnModel().getColumn(1).setPreferredWidth(600);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		// Rendering (displaying) individual cells in a JTable
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
		table.setFont(font);
  
	    scroll=new JScrollPane(table); 
	    table.getTableHeader().setPreferredSize(new Dimension(scroll.getWidth(),40));
		table.getTableHeader().setFont(new Font("Calibri",Font.BOLD,20));
		tablePanel.add(scroll);
	    
	    // Border layout is the default layout for a frame
	    frame.getContentPane().add(BorderLayout.SOUTH, logPanel);
	    frame.getContentPane().add(BorderLayout.CENTER, tablePanel);
	    frame.getContentPane().add(BorderLayout.NORTH, searchPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		// Set the panel size 
		frame.setPreferredSize(new Dimension(1000,800));    
		// Size the frame
		frame.pack(); 
		frame.setLocationRelativeTo(null); 
		// Display the frame
		frame.setVisible(true);
		
	}
	
	// Display recently updated securities with offered price and bidder name
	public static void addLabel(String user, String symbol, String price) {
		textarea.insert("Bid placed by user: "+user + " on secuirty: " + symbol + ", Offered Price: " + price+"\n", 0);
		frame.pack();
	}	
	
	// Display recently updated securities with offered price and bidder name in the GUI
	public static void errorLabel(String a) {
		if(a.equals("0")) {
			textarea.insert("Error! Item not found in stock list\n", 0);
		}
		else if(a.equals("1")) {
			textarea.insert("Invalid format for price. Re-enter bid price.", 0);	
		}
		else {
			textarea.insert("Offered price is less than the current value of the security. Current price: "+a+". Re-enter bid price.\n", 0);
		}

	}

	// Placing the bid via the GUI using the update button
	public void actionPerformed(ActionEvent e) {
			if(e.getSource()==updateButton) {
				performSearch();
			}
		}
			
	private void performSearch() {
		if(Main.stockData.isListed(symbolfield.getText())) {
			symbol=symbolfield.getText();
			price=pricefield.getText();
			secName=Main.stockData.getSecurityName(symbol);
			try {
				Double.parseDouble(price);
				if (symbol.equals("FB")) row = 0;
				else if (symbol.equals("VRTU")) row = 1;
				else if (symbol.equals("MSFT")) row = 2;
				else if (symbol.equals("GOOGL")) row = 3;
				else if (symbol.equals("YHOO")) row = 4;
				else if (symbol.equals("XLNX")) row = 5;
				else if (symbol.equals("TSLA")) row = 6;
				else if (symbol.equals("TXN")) row = 7;
			
				if (Double.parseDouble(price)> Double.parseDouble(Main.stockData.getPrice(symbol))){
					Main.stockData.updatePrice(symbol,secName,price);
					Panel.addLabel(symbol,secName,price);
					if (row!=10) Panel.table.setValueAt(price,row,2);
				}
				else {
					Panel.errorLabel(Main.stockData.getPrice(symbol));
				}
			}
			catch(NumberFormatException e1) {
				Panel.errorLabel("1");
			}				
		}
		else {
			Panel.errorLabel("0");
		}
	
	}        
}      
   

  
    

    
