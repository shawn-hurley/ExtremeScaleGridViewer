package com.shawnhurley;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;

public class GridViewer {
	public static void main(String Args[]){
		// create an instance of our GUI and run it
		
		//schedule a job for the event-dispatching thread:
		//creating and showing this application's GUI.
		//any logic that I need to do
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					createAndShowGUI();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void createAndShowGUI() throws FileNotFoundException {
		//create and set up the window
		JFrame frame = new JFrame("GridViewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add contents to the window.
		try {
			frame.add(new GridViewerGUI());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Display the window.
		frame.pack();
		
		frame.setVisible(true);
	}
	
}
