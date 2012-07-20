package com.shawnhurley;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.OceanTheme;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;

import java.awt.Color;

public class GridBrowser {
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
		try {
			MetalLookAndFeel.setCurrentTheme(new OceanTheme());
			UIManager.setLookAndFeel(new com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel());
			
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		JFrame frame = new JFrame("GridViewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//Add contents to the window.
		try {
			GridBrowserGUI gridViewerGUI = new GridBrowserGUI();
			gridViewerGUI.setForeground(Color.WHITE);
			frame.getContentPane().add(gridViewerGUI, BorderLayout.NORTH);
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
