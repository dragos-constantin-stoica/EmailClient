/**
 * 
 */
package emailclient;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author dragos
 *
 */
public class ExternalEmailService implements EmailService, Runnable {

	private BlockingQueue<EmailSendTask> queue;
	public boolean RUNNING = true;


	public ExternalEmailService(BlockingQueue<EmailSendTask> q){
		this.queue = q;
	}
	

	/* (non-Javadoc)
	 * @see emailclient.EmailService#send(emailclient.Email)
	 */
	@Override
	public void send(Email email) {
		//This message must contain a disclaimer and must be logged
		
		GlobalVariables.logger.logEmailMessage(email);
		System.out.println("\n\n_______________________________________________________\nExternal e-mail sent:\n");
		System.out.println(email);
		System.out.println(Disclaimer.BODY);
	}

	
	@Override
	public void run() {
		GlobalVariables.logger.log("Exteral Email Server started!");
		Crypto security = new Crypto();
		Random failureRate = new Random();
		
		// Get mail messages from the mail Queue
		while (RUNNING || !queue.isEmpty()) {
            EmailSendTask emailTask;
			try {
				emailTask = queue.take();
				if (emailTask.encryptDES) security.encryptDES(emailTask.email);
				if (emailTask.encryptAES) security.encryptAES(emailTask.email);
				System.out.println("Number of retries: " + emailTask.numberOfRetries);
				System.out.println("Content type: " + emailTask.email.getMessageType());
				this.send(emailTask.email);
				
				//simulate random failure 33%
				if ( failureRate.nextInt(100) < 50){
					System.out.println("Failed to send e-mail");
					if (emailTask.numberOfRetries > 0){
						System.out.println("Try one more time to send the email!");
						emailTask.numberOfRetries--;
						this.queue.add(emailTask);
					}else{
						System.out.println("Maximum number of retries was reached and the email send operation failed!");
					}
				}else{
					System.out.println("Email successfully send!\n\n-=(°•°)=-\n\n");
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		GlobalVariables.logger.log("Exteral Email Server finished!");

	}
	
	
}
