package com.shawnhurley;
import java.io.*;
import java.lang.reflect.*;

import javax.swing.*;
import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
public class objectToXML {
	
	/**
	 * @param args
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws FileNotFoundException
	 */
	public static StringBuffer reflection(Object thing, StringBuffer sb)  throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, FileNotFoundException{
		int i;
		boolean firstTimeThrough = true;
		if(sb == null){
			sb = new StringBuffer();
		}
		else{
			firstTimeThrough = false;
		}
		sb.append("<objectfield>");
		Class<? extends Object> c = thing.getClass();
		if(firstTimeThrough){
		sb.append("<name>"+c.toString()+"</name>");
		}
		//PrintWriter prntwriter = new PrintWriter("NewFile");
		Field newFields[]= c.getDeclaredFields();
		sb.append("<fields>");
		for(i=0; i<newFields.length; i++){	
			Field aField = newFields[i];
			String typeField = aField.getType().toString();
			if(typeField.startsWith("class ")){
				int INDEX_WHERE_CLASSNAME_BEGINS = 6;	
				if(typeField.endsWith("java.lang.String")){
					sb.append("<stringfield>");
					sb.append("<name>"+aField.getName()+"</name>");
					sb.append("<value>");
					Method method = getMethod(aField, c);
			        sb.append(method.invoke(thing, null));
			        sb.append("</value>");
			        sb.append("</stringfield>");
				}
				else if (typeField.endsWith("java.util.Date") || typeField.endsWith("java.lang.Byte")
						|| typeField.endsWith("java.lang.Short") || typeField.endsWith("java.lang.Integer") || typeField.endsWith("java.lang.Long") || 
						typeField.endsWith("java.lang.Float") || typeField.endsWith("java.lang.Double") || typeField.endsWith("java.lang.Character") || typeField.endsWith("Boolean")) {
					String className = typeField.substring(INDEX_WHERE_CLASSNAME_BEGINS).replace(".", "");
					sb.append("<" + className + "field>");
					sb.append("<name>"+aField.getName()+"</name>");
					sb.append("<value>");
					Method method = getMethod(aField, c);
			        sb.append(method.invoke(thing, null).toString());
			        sb.append("</value>");
					sb.append("</" + className + "field>");
				}
				else if (typeField.endsWith("java.util.GregorianCalendar")) {
					
				}
					
				else{
					Method method = getMethod(aField, c);
					sb.append("<name>"+aField.getName()+"</name>");
					Object obj = method.invoke(thing, null);
					reflection(obj, sb);
				}
			}
			else{
				//sb.append(aField.getName()+" "+aField.getType());
				sb.append("<"+aField.getType()+"field>");
				sb.append("<name>");
				sb.append(aField.getName());
				sb.append("</name>");
				sb.append("<value>");
				Method method = getMethod(aField, c);
				sb.append(method.invoke(thing, null));
				sb.append("</value>"); 
				sb.append("</"+aField.getType()+"field>");
				//method.invoke(thing, null);
			}
			
		}
		sb.append("</fields>");
		sb.append("</objectfield>");
		//System.out.println(sb);
		return sb;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Method getMethod(Field field, Class c) throws SecurityException, NoSuchMethodException{
		String fieldname = field.getName();
		String firstletter = fieldname.substring(0, 1);
		String restofstring = fieldname.substring(1);
		String name = "get"+firstletter.toUpperCase()+restofstring;
		Method method = c.getDeclaredMethod(name, null);
		return method;
	}
	public static MySAXApp attempts(String sb) throws IOException{
		ByteArrayInputStream inputstream = new ByteArrayInputStream(sb.getBytes());
		try {
			//book of four pattern go look up
			XMLReader xr = XMLReaderFactory.createXMLReader();
			MySAXApp handler = new MySAXApp();
			xr.setContentHandler(handler);
			xr.setErrorHandler(handler);
			xr.parse(new InputSource(inputstream));
			return handler;
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
}
