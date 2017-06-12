/**
 * 
 */
package emailclient;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Dragos STOICA
 *
 */
public class MainEmailClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		GlobalVariables.logger.log("MainEmailCient - tests started!");
		
		BlockingQueue<EmailSendTask> extQueue = new ArrayBlockingQueue<EmailSendTask>(10);
		BlockingQueue<EmailSendTask> intQueue = new ArrayBlockingQueue<EmailSendTask>(10);

		ExternalEmailService extES = new ExternalEmailService(extQueue);
		InternalEmailService intES = new InternalEmailService(intQueue);
		Thread extESThread = new Thread(extES);
		extESThread.start();
		Thread intESThread = new Thread(intES);
		intESThread.start();
		
		
		// sending a plain text email to an outside resource, with a disclaimer added at the end, unencrypted and no retry
		EmailSendTask emailTask = new EmailSendTask(); 
		emailTask.email = new Email();
		emailTask.email.setTo("dragos@outside.server.be");
		emailTask.email.setSubject("TEST #1");
		emailTask.email.setBody("sending a plain text email to an outside resource, with a disclaimer added at the end, unencrypted and no retry");
		emailTask.email.setMessageType(Email.EMAIL_TYPE.PLAIN_TEXT);
		emailTask.discalimer = true;
		emailTask.numberOfRetries = 0;
		
		try {
			extQueue.put(emailTask);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//~•1•~
		
		//sending an HTML email to an internal server (so without the disclaimer), encrypted with DES, with the retry functionality
		emailTask = new EmailSendTask(); 
		emailTask.email = new Email();
		emailTask.email.setTo("dragos@internal.be");
		emailTask.email.setSubject("TEST #2");
		emailTask.email.setBody("sending an HTML email to an internal server (so without the disclaimer), encrypted with DES, with the retry functionality");
		emailTask.email.setMessageType(Email.EMAIL_TYPE.HTML);
		emailTask.discalimer = false;
		emailTask.numberOfRetries = 3;
		emailTask.encryptDES = true;
		
		try {
			intQueue.put(emailTask);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//~•2•~
				
		//sending an HTML email to an outside resource, with a disclaimer added at the end and encrypted with AES with retries in case of errors
		emailTask = new EmailSendTask(); 
		emailTask.email = new Email();
		emailTask.email.setTo("dragos@outside.resource.be");
		emailTask.email.setSubject("TEST #3");
		emailTask.email.setBody("sending an HTML email to an outside resource, with a disclaimer added at the end and encrypted with AES with retries in case of errors");
		emailTask.email.setMessageType(Email.EMAIL_TYPE.HTML);
		emailTask.discalimer = true;
		emailTask.numberOfRetries = 3;
		emailTask.encryptAES = true;
		
		try {
			extQueue.put(emailTask);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//~•3•~

		//sending a plain text email to an outside resource and encrypted first with DES and then with AES
		emailTask = new EmailSendTask();
		emailTask.email = new Email();
		emailTask.email.setTo("dragos@outside.resource.be");
		emailTask.email.setSubject("TEST #4");
		emailTask.email.setBody("sending a plain text email to an outside resource and encrypted first with DES and then with AES");
		emailTask.email.setMessageType(Email.EMAIL_TYPE.PLAIN_TEXT);
		emailTask.discalimer = true;
		emailTask.numberOfRetries = 3;
		emailTask.encryptDES = true;
		emailTask.encryptAES = true;
		
		try {
			extQueue.put(emailTask);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//~•4•~

		
		extES.RUNNING = false;
		intES.RUNNING = false;
		try {
			extESThread.join();
			intESThread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GlobalVariables.logger.log("MainEmailCient - tests ended!");
		GlobalVariables.logger.close();

	}

}
