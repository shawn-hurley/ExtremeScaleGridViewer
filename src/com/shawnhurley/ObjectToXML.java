package com.shawnhurley;
import java.io.*;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.xml.sax.*;
import org.xml.sax.helpers.XMLReaderFactory;
public class ObjectToXML {
	
	private static Object array;
	/**
	 * Author: Shawn Hurley
	 * This is a utility function that is used to make an xml string buffer from an object so that we can display the values and fields of that object. We will do this by going through
	 * each declared field and calling the get method on it, and putting that all in an xml format to be output as an String Buffer
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws FileNotFoundException
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("deprecation")
	public static StringBuffer reflection(Object object, StringBuffer sb)  throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, FileNotFoundException, InstantiationException{
		int i;
		boolean firstTimeThrough = true;
		if(sb == null){
			sb = new StringBuffer();
		}
		else{
			firstTimeThrough = false;
		}
		sb.append("<objectfield>");
		Class thing =  (Class) object.getClass();
		if(firstTimeThrough){
			//No need to add a name of a object twice, the only time to do this is when it is the first time through
			sb.append("<name>"+object.toString()+"</name>");
		}
		Field newFields[]=  thing.getDeclaredFields();
		sb.append("<fields>");
		for(i=0; i<newFields.length; i++){	
			Field aField = newFields[i];
			 //AJ: First, check if this is a static field; if it is, we skip it because we don't support it. We don't support it because 
            //AJ: static fields of Serialized classes (which WXS keys & values must be) are almost certain to be 
            //AJ: declared with a value (like serialVersionUID, for example) and thus don't need to be set by us.
            if (Modifier.isStatic(aField.getModifiers()) ){
                    continue; 
            }
			String typeField = aField.getType().toString();
			if(typeField.startsWith("class ")){
				int INDEX_WHERE_CLASSNAME_BEGINS = 6;	
				if(typeField.endsWith("java.lang.String")){
					sb.append("<stringfield>");
					sb.append("<name>"+aField.getName()+"</name>");
					sb.append("<value>");
					Method method = getMethod(aField, thing);
			        sb.append(method.invoke(object, null));
			        sb.append("</value>");
			        sb.append("</stringfield>");
				}
				else if (typeField.endsWith("java.lang.Byte") || typeField.endsWith("java.lang.Short") || typeField.endsWith("java.lang.Integer") || typeField.endsWith("java.lang.Long") || 
						typeField.endsWith("java.lang.Float") || typeField.endsWith("java.lang.Double") || typeField.endsWith("java.lang.Character") || typeField.endsWith("Boolean")) {
					String className = typeField.substring(INDEX_WHERE_CLASSNAME_BEGINS).replace(".", "");
					sb.append("<" + className + "field>");
					sb.append("<name>"+aField.getName()+"</name>");
					sb.append("<value>");
					Method method = getMethod(aField, thing);
			        sb.append(method.invoke(object, null).toString());
			        sb.append("</value>");
					sb.append("</" + className + "field>");
				}
				else if (typeField.endsWith("java.util.Date")){
					String className = typeField.substring(INDEX_WHERE_CLASSNAME_BEGINS).replace(".", "");
					sb.append("<"+className+ "field>");
					sb.append("<name>"+aField.getName()+"</name>");
					sb.append("<value>");
					Method method = getMethod(aField, thing);
					Date newDate = (java.util.Date) method.invoke(object, null);
					sb.append(newDate.getYear());
					sb.append("</value>");
					sb.append("<value>");
					sb.append(newDate.getMonth()+1);
					sb.append("</value><value>");
					sb.append(newDate.getDate());
					sb.append("</value>");
					sb.append("</"+className+"field>");
				}
				else if (typeField.endsWith("java.util.GregorianCalendar")) {
					String className = typeField.substring(INDEX_WHERE_CLASSNAME_BEGINS).replace(".", "");
					sb.append("<" + className + "field>");
					sb.append("<name>"+aField.getName()+"</name>");
					sb.append("<value>");
					Method method = getMethod(aField, thing);
					GregorianCalendar calendar = (((java.util.GregorianCalendar) method.invoke(object, null)));
					sb.append((calendar.get(Calendar.YEAR)));
					sb.append("</value><value>");
					sb.append(calendar.get(Calendar.MONTH)+1);
					sb.append("</value><value>");
					sb.append(calendar.get(Calendar.DAY_OF_MONTH));
					sb.append("</value>");
					sb.append("</" + className + "field>");

				}
				else if (typeField.contains("[")) {
					sb.append("<Arrayfield>");
					//no other class should contain and [ in the name except for an array
					sb.append("<name>"+aField.getName()+"</name>");
					Method method = getMethod(aField, thing);
					if (typeField.contains("[I")){
					int [] array = (int[]) method.invoke(object, null);
					for (int j = 0; j < 5; j++) {
						sb.append("<value>");
						try{
							sb.append(array[j]);	
						} catch (Exception e1){
							sb.append("0</value>");
							continue;
						}
						sb.append("</value>");
					}
					}
					if (typeField.contains("[B")){
						byte[] array = (byte[]) method.invoke(object, null);
						for (int j = 0; j < 5; j++) {
							sb.append("<value>");
							sb.append(array[j]);
							sb.append("</value>");
						}
					}
					if (typeField.contains("[S")){
						short[] array = (short[]) method.invoke(object, null);
						for (int j = 0; j < 5; j++) {
							sb.append("<value>");
							sb.append(array[j]);
							sb.append("</value>");
						}
					}
					if (typeField.contains("[L")){
						long[] array = (long[]) method.invoke(object, null);
						for (int j = 0; j < 5; j++) {
							sb.append("<value>");
							sb.append(array[j]);
							sb.append("</value>");
						}
					}
					if (typeField.contains("F")){
						float[] array = (float[]) method.invoke(object, null);
						for (int j = 0; j < 5; j++) {
							sb.append("<value>");
							sb.append(array[j]);
							sb.append("</value>");
						}
					}
					if (typeField.contains("D")){
						double[] array = (double[]) method.invoke(object, null);
						for (int j = 0; j < 5; j++) {
							sb.append("<value>");
							sb.append(array[j]);
							sb.append("</value>");
						}
					}
					if (typeField.contains("C")){
						char[] array = (char[]) method.invoke(object, null);
						for (int j = 0; j < 5; j++) {
							sb.append("<value>");
							sb.append(array[j]);
							sb.append("</value>");
						}
					}
//					else{ 
//						int[] array = (int[]) method.invoke(object, null);
//						for (int j = 0; j < 5; j++) {
//							sb.append("<value>");
//							sb.append(array[j]);
//							sb.append("</value>");
//						}
					//}
					sb.append("</Arrayfield>");
					
				}
				else{
					Method method = getMethod(aField, thing);
					sb.append("<name>"+aField.getName()+"</name>");
					Object obj = method.invoke(object, null);
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
				Method method = getMethod(aField, thing);
				sb.append(method.invoke(object, null));
				sb.append("</value>"); 
				sb.append("</"+aField.getType()+"field>");
				//method.invoke(thing, null);
			}
			
		}
		sb.append("</fields>");
		sb.append("</objectfield>");
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
