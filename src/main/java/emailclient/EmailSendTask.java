/**
 * 
 */
package emailclient;

/**
 * @author dragos
 *
 */
public class EmailSendTask {
	public Email email = new Email();
	public int numberOfRetries = 3;
	public boolean encryptAES = false;
	public boolean encryptDES = false;
	public boolean discalimer = false;
}
