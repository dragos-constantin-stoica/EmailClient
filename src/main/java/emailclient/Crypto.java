/**
 * 
 */
package emailclient;

/**
 * @author Dragos STOICA
 *
 */
public class Crypto {

	public Crypto(){
		
	}
	
	public void encryptAES(Email email){
		System.out.println("AES [e-mail encrypted]! >>> " + email.getSubject());
		GlobalVariables.logger.log("AES - encryption done!");
	}
	
	public void encryptDES(Email email){
		System.out.println("DES [e-mail encrypted]! >>> " + email.getSubject());
		GlobalVariables.logger.log("DES - encryption done!");
	}
}
