package com.shawnhurley;

import java.awt.Color;
import java.awt.Component;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import com.sun.tools.javac.util.List;

public class GridBrowserGUI extends JPanel implements ActionListener{
	private static final long serialVersionUID = 1L;
	 private static final String refreshButtonPushed = "Calculate";
	 private static final String getButtonPushed = "getButtonPushed";
	 private static final String updateButtonPushed = "updateButtonPushed";
	 private static final String removeButtonPushed = "removeButtonPushed";
	 //TESTING ONLY**********
	 HashMap hashmap = new HashMap();

	 @SuppressWarnings("rawtypes")
	private Constructor keyPicked;
	
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
	private JFormattedTextField classLookingfor;
	private JFormattedTextField KeyPanelTextField;
	private JFormattedTextField ValueClass;
	private JFormattedTextField Grid_endpt;
	private JFormattedTextField Grid_Name;
	private JButton refreshButton;
	private JButton updateButton;
	private JButton removeButton;
	private JButton invalidateButton;
	private JButton getButton;
	
	//Formats to format and parse numbers
	//private NumberFormat integerFormat = NumberFormat.getIntegerInstance();
	
	
	public GridBrowserGUI() throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException {
		//set that we are a grid layout type of JPanel
		super(new GridBagLayout());
		
		//Create a GridBagConstraints object we can reuse when adding components
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
		
		//Put Components within requiredInputPanel, then place it in main panel ("this")
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
	    refreshButton.setActionCommand(refreshButtonPushed);
	    refreshButton.addActionListener(this);
	    c.fill = GridBagConstraints.SOUTH;
		c.gridx = 0;
		c.gridy = 4;
	    requiredInputPanel.add(refreshButton, c);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public  void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (refreshButtonPushed.equals(e.getActionCommand())){
			invalidate();
			KeyValuesPanel.removeAll();
			ListOfValuesPanel.removeAll();
			
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
					JOptionPane.showMessageDialog(null, "Value Class not found, pleas select again");
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
				@SuppressWarnings({ })
				Constructor[] co = cl.getConstructors();
				this.keyPicked = (Constructor)JOptionPane.showInputDialog(classLookingfor, "Pick a Constructor", "", JOptionPane.QUESTION_MESSAGE
		                , null, co, co[0]);
				StringBuffer sb = new StringBuffer();
				sb = constructorToXML.reflection(keyPicked, sb);
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
				 getButton.setActionCommand(getButtonPushed);
				 getButton.addActionListener(this);
				 labelconstraints.fill = GridBagConstraints.SOUTH;
				 labelconstraints.gridx = 0;
				 labelconstraints.gridy = 4;
				 KeyValuesPanel.add(getButton, labelconstraints);
				 updateButton.setEnabled(true);
				 updateButton.setActionCommand(updateButtonPushed);
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
				  removeButton.setActionCommand("updateButtonPushed");
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
		if (getButtonPushed.equals(e.getActionCommand())){
			Component[] arrayofcomponets = KeyValuesPanel.getComponents();
			ArrayList<Object> arrayOfValuesForKeyClass = cleanComponents(arrayofcomponets, keyPicked);
			try {
				fillLisOfValuesPanel((Class) hashmap.get(keyPicked.newInstance(arrayOfValuesForKeyClass.toArray())).getClass());
				
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
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (removeButtonPushed.equals(e.getActionCommand())){
			Component[] arrayofcomponets = KeyValuesPanel.getComponents();
			ArrayList<Object> arrayOfValuesForKeyClass = cleanComponents(arrayofcomponets, keyPicked);
			try {
				hashmap.remove(keyPicked.newInstance(arrayOfValuesForKeyClass.toArray()));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
			
		if (updateButtonPushed.equals(e.getActionCommand())){
			//Figure out how to get the constructor for the Value Class. 
			Component[] arrayofcomponetsForKey = KeyValuesPanel.getComponents();
			ArrayList<Object> arrayOfValueForKeyClass = cleanComponents(arrayofcomponetsForKey, keyPicked);
			Object whatever;
			try {
				whatever = hashmap.get(keyPicked.newInstance(arrayOfValueForKeyClass.toArray()));
				Component[] arrayofcomponetsForValue = ListOfValuesPanel.getComponents();
				hashmap.put((keyPicked.newInstance(arrayOfValueForKeyClass.toArray())), objectPlusUpdates(whatever, arrayofcomponetsForValue));
			} catch (IllegalArgumentException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InstantiationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	

	private Object objectPlusUpdates(Object whatever, Component[] components) {
		
		return null;
	}

	public void fillLisOfValuesPanel(@SuppressWarnings("rawtypes") Class given) throws IllegalArgumentException, SecurityException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, InstantiationException{
		new GridBagConstraints();
		//will use the XMLReader and call MySAXApp to return the panel
		StringBuffer sb = null;
		sb = objectToXML.reflection(given, sb);
		final MySAXApp handler = objectToXML.attempts(sb.toString());
		ListOfValuesPanel = handler.getTestPanel();
}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList<Object> cleanComponents(Component[] componets, Constructor constructor){
		ArrayList<Object> listOfObjects = new ArrayList();
		Type[] classesweuse = constructor.getGenericParameterTypes();
		int counter = 0;
		for (int i = 0; i < componets.length; i++) {
			if (componets[i].toString().contains("JFormattedTextField")){
				if(componets[i+1].toString().contains("JFormattedTextField")){
					//will make an array of all the values or is a date field.
					if(classesweuse[counter].toString().equals("class [I")){
						int[] array = {Integer.valueOf(((JFormattedTextField) componets[i]).getValue().toString()), Integer.valueOf(((JFormattedTextField) componets[i+1]).getValue().toString()), Integer.valueOf(((JFormattedTextField) componets[i+2]).getValue().toString()), Integer.valueOf(((JFormattedTextField) componets[i+3]).getValue().toString()), Integer.valueOf(((JFormattedTextField) componets[i+4]).getValue().toString())};
						//need to move past the 5 values for the array, which should all be right next to each other so no need for anything else
						i = i+4;
						listOfObjects.add(array);
						counter++;
						}
					else if(classesweuse[counter].toString().equals("class [B")){
						byte[] array = {Byte.valueOf(((JFormattedTextField) componets[i]).getValue().toString()), Byte.valueOf(((JFormattedTextField) componets[i+1]).getValue().toString()), Byte.valueOf(((JFormattedTextField) componets[i+2]).getValue().toString()), Byte.valueOf(((JFormattedTextField) componets[i+3]).getValue().toString()), Byte.valueOf(((JFormattedTextField) componets[i+4]).getValue().toString())};
						//need to move past the 5 values
						i = i+4;
						listOfObjects.add(array);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("class [C")){
						char[] array = {Character.valueOf(((JFormattedTextField) componets[i]).getValue().toString().charAt(0)), Character.valueOf(((JFormattedTextField) componets[i+1]).getValue().toString().charAt(0)), Character.valueOf(((JFormattedTextField) componets[i+2]).getValue().toString().charAt(0)), Character.valueOf(((JFormattedTextField) componets[i+3]).getValue().toString().charAt(0)), Character.valueOf(((JFormattedTextField) componets[i+4]).getValue().toString().charAt(0))};
						//need to move past the 5 values
						i = i+4;
						listOfObjects.add(array);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("class [S")){
						short[] array ={Short.valueOf(((JFormattedTextField) componets[i]).getValue().toString()), Short.valueOf(((JFormattedTextField) componets[i+1]).getValue().toString()), Short.valueOf(((JFormattedTextField) componets[i+2]).getValue().toString()), Short.valueOf(((JFormattedTextField) componets[i+3]).getValue().toString()), Short.valueOf(((JFormattedTextField) componets[i+4]).getValue().toString())};
						//need to move past the 5 values
						i = i+4;
						listOfObjects.add(array);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("class [D")){
						double[] array = {Double.valueOf(((JFormattedTextField) componets[i]).getValue().toString()), Double.valueOf(((JFormattedTextField) componets[i+1]).getValue().toString()), Double.valueOf(((JFormattedTextField) componets[i+2]).getValue().toString()), Double.valueOf(((JFormattedTextField) componets[i+3]).getValue().toString()), Double.valueOf(((JFormattedTextField) componets[i+4]).getValue().toString())};
						//need to move past the 5 values
						i = i+4;
						listOfObjects.add(array);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("class [F")){
						float[] array = {Float.valueOf(((JFormattedTextField) componets[i]).getValue().toString()), Float.valueOf(((JFormattedTextField) componets[i+1]).getValue().toString()), Float.valueOf(((JFormattedTextField) componets[i+2]).getValue().toString()), Float.valueOf(((JFormattedTextField) componets[i+3]).getValue().toString()), Float.valueOf(((JFormattedTextField) componets[i+4]).getValue().toString())};
						i = i+4;
						listOfObjects.add(array);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("class [L")){
						long[] array = {Long.valueOf(((JFormattedTextField) componets[i]).getValue().toString()), Long.valueOf(((JFormattedTextField) componets[i+1]).getValue().toString()), Long.valueOf(((JFormattedTextField) componets[i+2]).getValue().toString()), Long.valueOf(((JFormattedTextField) componets[i+3]).getValue().toString()), Long.valueOf(((JFormattedTextField) componets[i+4]).getValue().toString())};
						i = i+4;
						listOfObjects.add(array);
						counter++;
					}
					else if(classesweuse[counter].toString().contains("java.util.Date")){
						@SuppressWarnings("deprecation")
						Date date = new Date(Integer.valueOf(((JFormattedTextField) componets[i]).getValue().toString()), Integer.valueOf(((JFormattedTextField) componets[i]).getValue().toString()), Integer.valueOf(((JFormattedTextField) componets[i]).getValue().toString()));
						i = i+2;
						listOfObjects.add(date);
						counter++;
					}
					else if(classesweuse[counter].toString().contains("GregorianCalendar")){
						GregorianCalendar calendar = new GregorianCalendar(Integer.valueOf(((JFormattedTextField) componets[i]).getValue().toString()), Integer.valueOf(((JFormattedTextField) componets[i]).getValue().toString()), Integer.valueOf(((JFormattedTextField) componets[i]).getValue().toString()));
						i = i+2;
						listOfObjects.add(calendar);
						counter++;
					}
				}
				else{
					if(classesweuse[counter].toString().equals("int") || classesweuse[counter].toString().contains("Integer")){
						Integer thing = new Integer(((JFormattedTextField) componets[i]).getValue().toString());
						listOfObjects.add(thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("double") || classesweuse[counter].toString().contains("Double")){
						Double thing = new Double(((JFormattedTextField) componets[i]).getValue().toString());
						listOfObjects.add(thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("float") || classesweuse[counter].toString().contains("Float")){
						Float thing = new Float(((JFormattedTextField) componets[i]).getValue().toString());
						listOfObjects.add(thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("long") || classesweuse[counter].toString().contains("Long")){
						Long thing = new Long(((JFormattedTextField) componets[i]).getValue().toString());
						listOfObjects.add(thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("short") || classesweuse[counter].toString().contains("Short")){
						Short thing = new Short(((JFormattedTextField) componets[i]).getValue().toString());
						listOfObjects.add(thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("byte") || classesweuse[counter].toString().contains("Byte")){
						Byte thing = new Byte(((JFormattedTextField) componets[i]).getValue().toString());
						listOfObjects.add(thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("char") || classesweuse[counter].toString().contains("Character")){
						Character thing = new Character(((JFormattedTextField) componets[i]).getValue().toString().charAt(0));
						listOfObjects.add(thing);
						counter++;
					}
					else if(classesweuse[counter].toString().contains("String")){
						String thing = new String(((JFormattedTextField) componets[i]).getValue().toString());
						listOfObjects.add(thing);
						counter++;
					}
				}
			}
		}
		return listOfObjects;
	}
}