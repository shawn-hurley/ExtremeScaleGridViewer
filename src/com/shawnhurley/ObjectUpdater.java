package com.shawnhurley;

import java.awt.Component;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFormattedTextField;

public class ObjectUpdater {
	public static Object update(Object originalObject, Component[] components, int k) throws RuntimeException, NoSuchMethodException, IllegalAccessException, InvocationTargetException{
		Class<? extends Object> c = originalObject.getClass();
		Field classesweuse[]= c.getDeclaredFields();
		int counter = 0;
		for (int i=k; i < components.length; i++) {
			if (components[i].toString().contains("JFormattedTextField")){
				if(components[i+1].toString().contains("JFormattedTextField")){
					//will make an array of all the values or is a date field.
					if(classesweuse[counter].toString().equals("class [I")){
						int[] array = {Integer.valueOf(((JFormattedTextField) components[i]).getValue().toString()), Integer.valueOf(((JFormattedTextField) components[i+1]).getValue().toString()), Integer.valueOf(((JFormattedTextField) components[i+2]).getValue().toString()), Integer.valueOf(((JFormattedTextField) components[i+3]).getValue().toString()), Integer.valueOf(((JFormattedTextField) components[i+4]).getValue().toString())};
						//need to move past the 5 values for the array, which should all be right next to each other so no need for anything else
						i = i+4;
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, array);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("class [B")){
						byte[] array = {Byte.valueOf(((JFormattedTextField) components[i]).getValue().toString()), Byte.valueOf(((JFormattedTextField) components[i+1]).getValue().toString()), Byte.valueOf(((JFormattedTextField) components[i+2]).getValue().toString()), Byte.valueOf(((JFormattedTextField) components[i+3]).getValue().toString()), Byte.valueOf(((JFormattedTextField) components[i+4]).getValue().toString())};
						//need to move past the 5 values
						i = i+4;
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, array);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("class [C")){
						char[] array = {Character.valueOf(((JFormattedTextField) components[i]).getValue().toString().charAt(0)), Character.valueOf(((JFormattedTextField) components[i+1]).getValue().toString().charAt(0)), Character.valueOf(((JFormattedTextField) components[i+2]).getValue().toString().charAt(0)), Character.valueOf(((JFormattedTextField) components[i+3]).getValue().toString().charAt(0)), Character.valueOf(((JFormattedTextField) components[i+4]).getValue().toString().charAt(0))};
						//need to move past the 5 values
						i = i+4;
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, array);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("class [S")){
						short[] array ={Short.valueOf(((JFormattedTextField) components[i]).getValue().toString()), Short.valueOf(((JFormattedTextField) components[i+1]).getValue().toString()), Short.valueOf(((JFormattedTextField) components[i+2]).getValue().toString()), Short.valueOf(((JFormattedTextField) components[i+3]).getValue().toString()), Short.valueOf(((JFormattedTextField) components[i+4]).getValue().toString())};
						//need to move past the 5 values
						i = i+4;
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, array);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("class [D")){
						double[] array = {Double.valueOf(((JFormattedTextField) components[i]).getValue().toString()), Double.valueOf(((JFormattedTextField) components[i+1]).getValue().toString()), Double.valueOf(((JFormattedTextField) components[i+2]).getValue().toString()), Double.valueOf(((JFormattedTextField) components[i+3]).getValue().toString()), Double.valueOf(((JFormattedTextField) components[i+4]).getValue().toString())};
						//need to move past the 5 values
						i = i+4;
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, array);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("class [F")){
						float[] array = {Float.valueOf(((JFormattedTextField) components[i]).getValue().toString()), Float.valueOf(((JFormattedTextField) components[i+1]).getValue().toString()), Float.valueOf(((JFormattedTextField) components[i+2]).getValue().toString()), Float.valueOf(((JFormattedTextField) components[i+3]).getValue().toString()), Float.valueOf(((JFormattedTextField) components[i+4]).getValue().toString())};
						i = i+4;
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, array);						counter++;
					}
					else if(classesweuse[counter].toString().equals("class [L")){
						long[] array = {Long.valueOf(((JFormattedTextField) components[i]).getValue().toString()), Long.valueOf(((JFormattedTextField) components[i+1]).getValue().toString()), Long.valueOf(((JFormattedTextField) components[i+2]).getValue().toString()), Long.valueOf(((JFormattedTextField) components[i+3]).getValue().toString()), Long.valueOf(((JFormattedTextField) components[i+4]).getValue().toString())};
						i = i+4;
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, array);						
				        counter++;
					}
					else if(classesweuse[counter].toString().contains("java.util.Date")){
						@SuppressWarnings("deprecation")
						Date date = new Date(Integer.valueOf(((JFormattedTextField) components[i]).getValue().toString()), Integer.valueOf(((JFormattedTextField) components[i]).getValue().toString()), Integer.valueOf(((JFormattedTextField) components[i]).getValue().toString()));
						i = i+2;
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, date);
						counter++;
					}
					else if(classesweuse[counter].toString().contains("GregorianCalendar")){
						GregorianCalendar calendar = new GregorianCalendar(Integer.valueOf(((JFormattedTextField) components[i]).getValue().toString()), Integer.valueOf(((JFormattedTextField) components[i]).getValue().toString()), Integer.valueOf(((JFormattedTextField) components[i]).getValue().toString()));
						i = i+2;
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, calendar);
						counter++;
					}

				}
				else{
					if(classesweuse[counter].toString().equals("int") || classesweuse[counter].toString().contains("Integer")){
						Integer thing = new Integer(((JFormattedTextField) components[i]).getValue().toString());
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("double") || classesweuse[counter].toString().contains("Double")){
						Double thing = new Double(((JFormattedTextField) components[i]).getValue().toString());
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("float") || classesweuse[counter].toString().contains("Float")){
						Float thing = new Float(((JFormattedTextField) components[i]).getValue().toString());
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("long") || classesweuse[counter].toString().contains("Long")){
						Long thing = new Long(((JFormattedTextField) components[i]).getValue().toString());
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("short") || classesweuse[counter].toString().contains("Short")){
						Short thing = new Short(((JFormattedTextField) components[i]).getValue().toString());
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("byte") || classesweuse[counter].toString().contains("Byte")){
						Byte thing = new Byte(((JFormattedTextField) components[i]).getValue().toString());
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, thing);
						counter++;
					}
					else if(classesweuse[counter].toString().equals("char") || classesweuse[counter].toString().contains("Character")){
						Character thing = new Character(((JFormattedTextField) components[i]).getValue().toString().charAt(0));
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, thing);
						counter++;
					}
					else if(classesweuse[counter].toString().contains("String")){
						String thing = new String(((JFormattedTextField) components[i]).getValue().toString());
						Method method = getMethod(classesweuse[counter], c);
				        method.invoke(originalObject, thing);
						counter++;
					}
					else{
						Object newObject = update(originalObject, components, i);
						Method method = getMethod(classesweuse[counter], c);
						method.invoke(originalObject, newObject);
						counter++;
					}
				}
			}
		}
		return originalObject;	
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Method getMethod(Field field, Class c) throws SecurityException, NoSuchMethodException{
		String fieldname = field.getName();
		String firstletter = fieldname.substring(0, 1);
		String restofstring = fieldname.substring(1);
		String name = "set"+firstletter.toUpperCase()+restofstring;
		Method method = c.getDeclaredMethod(name, null);
		return method;
	}

}
