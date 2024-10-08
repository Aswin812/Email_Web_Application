package com.emailApplication.dto;

public class MailsDTO {

//	private String fromEmail;
	private int id;
	private String toEmail;
	private String subject;
	private String content;
	
	
	
	
	public MailsDTO(String toEmail, String sub, String content) {
//		this.fromEmail=fromEmail;
		this.toEmail=toEmail;
		this.subject=sub;
		this.content=content;
		this.id=id+1;
		
	}
	
	
//	public String getFromEmail() {
//		return fromEmail;
//	}
	
	public int getId() {
		return id;
	}

	public String getToEmail() {
		return toEmail;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}
	
	public String toString() {
		return "To : "+toEmail+"\nSubject : "+subject+"\nContent: "+content+"\n";
	}
}
