package com.shawnhurley;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GridBrowserGUI extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	 private static final String ACTION_CALCULATE = "Calculate"; 
	
	//Title labels to identify groups of fields
	private JLabel EntryTitleLabel;
	private JLabel KeyPanelLabel;
	//private JLabel ResultTitleLabel;
	private JLabel RequiredInputLabel;
	//private JLabel OutPutTableLabel;
	private JLabel ValueClassLabel;
	private JLabel Grid_End_Pointslabel;
	private JLabel Grid_Namelabel;
	//JPanels in which we the sub-areas of our GUI
	private JPanel EntryTitlePanel;
	//private JPanel ResultTitlePanel;
	private JPanel requiredInputPanel;
	//private JPanel OutPutTablePanel;
	private JPanel ListOfValuesPanel;
	private JPanel KeyValuesPanel;
	
	//Fields for data entry
	//private JFormattedTextField EntryTitle;
	private JFormattedTextField classLookingfor;
	private JFormattedTextField KeyPanelTextField;
	//private JFormattedTextField Keytolookup;
	private JFormattedTextField ValueClass;
	private JFormattedTextField Grid_endpt;
	private JFormattedTextField Grid_Name;
	private JButton refreshButton;
	private JButton updateButton;
	private JButton removeButton;
	private JButton invalidateButton;
	//************ Need to add more here, attempting to make the first screen first*******/
	private JButton getButton;
	
	//Formats to format and parse numbers
	//private NumberFormat integerFormat = NumberFormat.getIntegerInstance();
	
	
	public GridBrowserGUI() throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
		//set that we are a grid layout type of JPanel
		super(new GridBagLayout());
		
		//Create a GridBagConstraints object we can reuse when adding componets
		GridBagConstraints c = new GridBagConstraints();
		
		//load our properties from the file in our .jar
		
		//loadProps();
		
		//********Construct sub-sections in main panel***************
		EntryTitlePanel = new JPanel(new GridBagLayout());
		requiredInputPanel = new JPanel(new GridBagLayout());
		KeyValuesPanel = new JPanel(new GridBagLayout());
		ListOfValuesPanel = new JPanel(new GridBagLayout());
		
		fillYourEntryTitlePanel();
		c.fill = GridBagConstraints.FIRST_LINE_START;
		//c.anchor = GridBagConstraints.VERTICAL;
		c.gridx = 1;
		c.gridy = 0;
		EntryTitlePanel.setBackground(Color.ORANGE);
		add(EntryTitlePanel,c);
		
		//Put Componentes within requiredInputPanel, then place it in main panel ("this")
		fillyourRequiredInputPanel();
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx=1;
		c.gridy=1;
		requiredInputPanel.setBackground(Color.GRAY);
		add(requiredInputPanel,c);
		

		//Put components in ListOfValues
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 2;
		c.gridy= 1;
		ListOfValuesPanel.setBackground(Color.BLUE);
		add(ListOfValuesPanel,c);
		
		//Put components in KeyValuePanel
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx = 0;
		c.gridy = 2;
		KeyValuesPanel.setBackground(Color.cyan);
		add(KeyValuesPanel,c);
		
		
	}
	//private void loadProps() {
		//props = new Properties();
		//InputStream in = props.getClass().getResourceAsStream(propsFileName);
		//try {
			//props.load(in);
		//} catch (IOException e) {
			//e.printStackTrace();
		//}
	//}
	
	private void fillYourEntryTitlePanel(){
		//create a GridBagConstraints object we can reuse when adding componet
		GridBagConstraints c = new GridBagConstraints();
		
		//add the label
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 0;
		c.gridy = 0;
		EntryTitleLabel = new JLabel("IBM Grid Browser");
		EntryTitlePanel.add(EntryTitleLabel, c);
//		
//		//Add the text field
//		c.fill = GridBagConstraints.HORIZONTAL;
//		c.gridx = 0;
//		c.gridy = 0;
//		EntryTitle = new JFormattedTextField();
//		EntryTitle.setColumns(80);
//		EntryTitle.setEditable(false);
//		EntryTitlePanel.add(EntryTitle,c);
//		
		return;
	}
	
	private void fillyourRequiredInputPanel(){
		//create a GridBagConstraints object we can reuse when adding componet
		GridBagConstraints c = new GridBagConstraints();
		
		//add the label
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx=0;
		c.gridy=0;
		RequiredInputLabel = new JLabel("Key Class");
		requiredInputPanel.add(RequiredInputLabel, c);
		
		//Add the text field
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx=1;
		c.gridy=0;
		classLookingfor = new JFormattedTextField();
		classLookingfor.setColumns(10);
		classLookingfor.setEditable(true);
		requiredInputPanel.add(classLookingfor, c);
		
		//add another label for the value class
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx=0;
		c.gridy=1;
		ValueClassLabel = new JLabel("Value_Class");
		requiredInputPanel.add(ValueClassLabel, c);
		
		//Add the text field to get value
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx=1;
		c.gridy=1;
		ValueClass = new JFormattedTextField();
		ValueClass.setEditable(true);
		ValueClass.setColumns(10);
		requiredInputPanel.add(ValueClass, c);
		
		//add label for Catalog end points
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx=0;
		c.gridy=2;
		Grid_End_Pointslabel = new JLabel("Grid_end_PTS");
		requiredInputPanel.add(Grid_End_Pointslabel, c);
		//add Text field for Catalog end points
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx=1;
		c.gridy=2;
		Grid_endpt = new JFormattedTextField();
		Grid_endpt.setEditable(true);
		Grid_endpt.setColumns(10);
		requiredInputPanel.add(Grid_endpt, c);
		//add label for grid name
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx=0;
		c.gridy=3;
		Grid_Namelabel = new JLabel("Grid_Name");
		requiredInputPanel.add(Grid_Namelabel, c);
		
		//add text field for grid name
		c.fill = GridBagConstraints.VERTICAL;
		c.gridx=1;
		c.gridy=3;
		Grid_Name = new JFormattedTextField();
		Grid_Name.setEditable(true);
		Grid_Name.setColumns(10);
		requiredInputPanel.add(Grid_Name, c);
		
		//Add the Refresh button and the Update value 
	    refreshButton = new JButton("Refresh");
	    refreshButton.setEnabled(true);
	    refreshButton.setActionCommand(ACTION_CALCULATE);
	    refreshButton.addActionListener(this);
	    c.fill = GridBagConstraints.SOUTH;
		c.gridx = 0;
		c.gridy = 4;
	    requiredInputPanel.add(refreshButton, c);
	}
	@SuppressWarnings("null")
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (ACTION_CALCULATE.equals(e.getActionCommand())){
			invalidate();
			KeyValuesPanel.removeAll();
			ListOfValuesPanel.removeAll();
			
			
			
			//JOptionPane.showMessageDialog(refreshButton, "hello world");
			GridBagConstraints c = new GridBagConstraints();
			GridBagConstraints labelconstraints = new GridBagConstraints();
			
			try {
				try {
					try {
						fillLisOfValuesPanel(Class.forName(ValueClass.getText()));
					} catch (InstantiationException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NoSuchMethodException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String name = classLookingfor.getText();
			try {
				Class<?> cl = Class.forName(name);
				@SuppressWarnings({ "rawtypes" })
				Constructor[] co = cl.getConstructors();
				@SuppressWarnings("rawtypes")
				Constructor picked = (Constructor)JOptionPane.showInputDialog(classLookingfor, "Pick a Constructor", "", JOptionPane.QUESTION_MESSAGE
		                , null, co, co[0]);
				StringBuffer sb = new StringBuffer();
				sb = constructorToXML.reflection(picked, sb);
				MySAXApp handler = objectToXML.attempts(sb.toString());
				KeyValuesPanel = handler.getTestPanel();
				c.gridx = 2;
				c.gridy = 1;
				//We need to add the Label at the Top as well as the buttons. 
				JLabel KeyValuesPanelLabel = new JLabel("Keys");
				Font labelfont = KeyValuesPanelLabel.getFont();
				KeyValuesPanelLabel.setFont(new Font(labelfont.getName(), 25, 25));
				labelconstraints.fill = GridBagConstraints.NORTH;
				labelconstraints.gridx = 0;
				labelconstraints.gridy = 0;
				KeyValuesPanel.add(KeyValuesPanelLabel, labelconstraints);
				//Adding the Buttons. //These Buttons will be what the user uses to interact with WXS
				
				 getButton = new JButton("Get");
				 updateButton = new JButton("Update");
				 Font cf = refreshButton.getFont();
				 Font buttonFont = new Font(cf.getName(), cf.getStyle(), 12);
				 updateButton.setFont(buttonFont);
				 getButton.setEnabled(true);
				 getButton.setActionCommand(ACTION_CALCULATE);
				 getButton.addActionListener(this);
				 labelconstraints.fill = GridBagConstraints.SOUTH;
				 labelconstraints.gridx = 0;
				 labelconstraints.gridy = 4;
				 KeyValuesPanel.add(getButton, labelconstraints);
				 updateButton.setEnabled(true);
				 updateButton.addActionListener(this);
				 labelconstraints.fill = GridBagConstraints.SOUTH;
				 labelconstraints.gridx = 1;
				 labelconstraints.gridy= 4;
				 KeyValuesPanel.add(updateButton, labelconstraints);
				    
				  //Add the Remove and Invalidate Buttons
				  removeButton = new JButton("Remove");
				  invalidateButton = new JButton("Invalidate");
				  removeButton.setFont(buttonFont);
				  invalidateButton.setFont(buttonFont);
				  removeButton.setEnabled(true);
				  removeButton.addActionListener(this);
				  labelconstraints.fill = GridBagConstraints.SOUTH;
				  labelconstraints.gridx=0;
				  labelconstraints.gridy=5;
				  KeyValuesPanel.add(removeButton, labelconstraints);
				  invalidateButton.setEnabled(true);
				  invalidateButton.addActionListener(this);
				  labelconstraints.fill = GridBagConstraints.SOUTH;
				  labelconstraints.gridx=1;
				  labelconstraints.gridy=5;
				  KeyValuesPanel.add(invalidateButton, labelconstraints);
				add(KeyValuesPanel, c);
			
			}
			catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Key Class not found, pleas select again");
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SecurityException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			c.fill = GridBagConstraints.VERTICAL;
			c.gridx = 3;
			c.gridy= 1;
			ListOfValuesPanel.setBackground(Color.LIGHT_GRAY);
			add(ListOfValuesPanel,c);
			ListOfValuesPanel.validate();
			ListOfValuesPanel.repaint();
			KeyValuesPanel.validate();
			KeyValuesPanel.repaint();
			revalidate();
			repaint();
		}
	}
	public void fillLisOfValuesPanel(@SuppressWarnings("rawtypes") Class given) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, InstantiationException{
		new GridBagConstraints();
		//will use the XMLReader and call MySAXApp to return the panel
		StringBuffer sb = null;
		sb = objectToXML.reflection(given, sb);
		final MySAXApp handler = objectToXML.attempts(sb.toString());
		ListOfValuesPanel = handler.getTestPanel();
}
}