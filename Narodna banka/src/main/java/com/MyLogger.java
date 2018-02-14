package com;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyLogger {
	private static DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:mmm");
	private static final String logFilePath = "D:\\Program Files\\djole\\xml\\myLog.txt";

	public static void log(String prefix, String text) {
		String textToLog = "***MyLogger*** "
						+ df.format(new Date())
						+ " " + "[" + prefix + "]" 
						+ ": "
						+ text;
		System.out.println(textToLog);
		try {
		    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
		    out.println(textToLog);
		    out.close();
		} catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}
	}
	
	/*public static void main(String[] args) {
		log("nekiMojPrefiks", "nekiMojText");
		log("nekiMojPrefiks", "nekiMojText2");
	}*/
}
