/**
 * 
 */
package emailclient;

import java.util.concurrent.BlockingQueue;

/**
 * @author Dragos STOICA
 *
 */
public class InternalEmailService implements EmailService, Runnable {

	
	private BlockingQueue<EmailSendTask> queue;
	public boolean RUNNING = true;
	
	public InternalEmailService(BlockingQueue<EmailSendTask> q){
		this.queue = q;
	}
	
	/* (non-Javadoc)
	 * @see emailclient.EmailService#send(emailclient.Email)
	 */
	@Override
	public void send(Email email) {
		System.out.println("\n\n_______________________________________________________\nInternal e-mail sent:\n" + email);
	}

	@Override
	public void run() {
		GlobalVariables.logger.log("Interal Email Server started!");
		Crypto security = new Crypto();
		// Get mail messages from the mail Queue
		while (RUNNING || !queue.isEmpty()) {
            EmailSendTask emailTask;
			try {
				emailTask = queue.take();
				if (emailTask.encryptDES) security.encryptDES(emailTask.email);
				if (emailTask.encryptAES) security.encryptAES(emailTask.email);
				this.send(emailTask.email);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}            
        }
		
		GlobalVariables.logger.log("Interal Email Server finished!");

	}

}
