/**
 * 
 */
package emailclient;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @author Dragos STOICA
 *
 */
public class EmailLogger {

	PrintWriter pw;
	Locale currentLocale = new Locale("en","EN");
	
	public EmailLogger(){
		try{
			this.pw = new PrintWriter(new FileWriter("EmailService.log"));
			this.log("Logger started!");
		}catch(Exception e){
			System.out.println("EmailSerice: " + e);
		}
	
	}

	public void logEmailMessage(Email email){
		this.log("Logging e-mail message:\n" + email);
	}

	
	public synchronized void log(String msg){
		try{
			Date today;
			String output;
			SimpleDateFormat formatter;

			formatter = new SimpleDateFormat("yyyy MM dd HH:mm:ss", currentLocale);
			today = new Date();
			output = formatter.format(today);
			this.pw.println("[" + output + "] " + msg);			
			this.pw.flush();
		}catch(Exception e){
			System.out.println("log: " + e);
		}				
	}

	public void close(){
		try{			
			this.log("Logger closed!");
			this.pw.close();
		}catch(Exception e){
			System.out.println("EmailService: " + e);
		}
		
	}
	
}
