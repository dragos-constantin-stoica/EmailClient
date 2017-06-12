/**
 * 
 */
package emailclient;

/**
 * @author Dragos STOICA
 *
 */
public class Email {
	private String body;
	private String to;
	private String subject;
	private String from;
	private EMAIL_TYPE messageType = Email.EMAIL_TYPE.PLAIN_TEXT;
	
	
	
	public enum EMAIL_TYPE{
		PLAIN_TEXT,
		HTML
	}
	
	public Email(){
		this.setSubject("Test e-mail");
		this.setBody("This is the body of test e-mail");
		this.setTo("dragos_s@hotmail.com");
		this.setFrom("dragos.constantin.stoica@outlook.com");
	}
	
	@Override
	public String toString(){
		return "+-------------------------------------------------------+\n" + 
	           "| FROM: " + this.getFrom() + "\n" +
	           "| TO: " + this.getTo() +  "\n" +
	           "| SUBJECT: " + this.getSubject() +  "\n" + 
	           "| MESSAGE:\n| " + this.getBody() +  "\n" +
	           "+-------------------------------------------------------+\n";
	}

	/**
	 * @return the body
	 */
	public String getBody() {
		return body;
	}

	/**
	 * @param body the body to set
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the messageType
	 */
	public EMAIL_TYPE getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType the messageType to set
	 */
	public void setMessageType(EMAIL_TYPE messageType) {
		this.messageType = messageType;
	}
	
	
	

}
