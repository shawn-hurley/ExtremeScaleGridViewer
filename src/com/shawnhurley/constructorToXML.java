package com.shawnhurley;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class constructorToXML {
	public static StringBuffer reflection(Constructor picked, StringBuffer sb){
		Type[] something = picked.getGenericParameterTypes();
		sb.append("<panel>");
		for (int i = 0; i < something.length; i++) {
			String typeField = something[i].toString();
			if (typeField.contains("[")) {
				sb.append("<Arrayfield>");
				//no other class should contain and [ in the name except for an array
				if (typeField.contains("[I")){
					sb.append("<name>ints</name>");
					for (int j = 0; j < 5; j++) {
						sb.append("<value>");
						sb.append(" ");
						sb.append("</value>");
					}
				}
				if (typeField.contains("[B")){
					sb.append("<name>bytes</name>");
					for (int j = 0; j < 5; j++) {
						sb.append("<value>");
						sb.append(" ");
						sb.append("</value>");
					}
				}
				if (typeField.contains("[S")){
					short[] array = {};
					sb.append("<name>shorts</name>");
					for (int j = 0; j < 5; j++) {
						sb.append("<value>");
						sb.append(" ");
						sb.append("</value>");
					}
				}
				if (typeField.contains("[L")){
					long[] array = {};
					sb.append("<name>long</name>");
					for (int j = 0; j < 5; j++) {
						sb.append("<value>");
						sb.append(" ");
						sb.append("</value>");
					}
				}
				if (typeField.contains("F")){
					float[] array = {};
					sb.append("<name>floats</name>");
					for (int j = 0; j < 5; j++) {
						sb.append("<value>");
						sb.append(" ");
						sb.append("</value>");
					}
				}
				if (typeField.contains("D")){
					double[] array = {};
					sb.append("<name>double</name>");
					for (int j = 0; j < 5; j++) {
						sb.append("<value>");
						sb.append(" ");
						sb.append("</value>");
					}
				}
				if (typeField.contains("C")){
					char[] array = {};
					sb.append("<name>char</name>");
					for (int j = 0; j < 5; j++) {
						sb.append("<value>");
						sb.append(" ");
						sb.append("</value>");
					}
				}
			sb.append("</Arrayfield>");
			}
				else{
					sb.append("<field>");
					sb.append("<name>");
					sb.append(typeField.toString());
					sb.append("</name>");
					sb.append("<value>");
					sb.append(" ");
					sb.append("</value>"); 
					sb.append("</field>");
				}
			}
		sb.append("</panel>");
		return sb;
		
	}
}
