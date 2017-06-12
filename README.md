# The problem

Imagine you had to write an email client for sending emails with different characteristics and you came up  
with a basic public contract like the one below: 
 
```java
public interface EmailService { 
    void send(Email email); 
} 
```

The emails can be sent as plain text or as HTML and emails which are sent outside the company servers need to be  
logged and have a disclaimer added to the end. What's worse, the emails could also be encrypted with AES or DES,  
or even both at the same time.  Your boss tells you that in case there are problems when sending the emails you  
should retry the sending operation up to three times. 
  
Design a simple client with the accompanying class(es) which you consider to be necessary and write a short program  
inside the standard `void main(String[] args)` function that would implement the following scenarios by making use  
of your class(es): 
  
- sending a plain text email to an outside resource, with a disclaimer added at the end, unencrypted and no retry 
- sending an HTML email to an internal server (so without the disclaimer), encrypted with DES, with the retry functionality 
- sending an HTML email to an outside resource, with a disclaimer added at the end and encrypted with AES with retries in case of errors 
- sending a plain text email to an outside resource and encrypted first with DES and then with AES 
  
Please don't provide real implementations for your methods unless you consider it is really necessary, but use `System.out.println` instead.  
For example, if you had to implement the `send` method, you could do something like this: 

```java
void send(Email email) { 
	System.out.println("Sending email"); 
} 
```
 
 
 # The approach
 
 This is a typical case where design patterns may be used. A quick analysis of the required functionalities highlight the following must to have features:
 - sending e-mails in plain text or HTML format
 - adding a disclaimer to the out going e-mail message
 - encrypting the e-mail message with AES or DES or both
 - e-mail message queue with 3 retries per message
 - out bound messages should be logged
 
