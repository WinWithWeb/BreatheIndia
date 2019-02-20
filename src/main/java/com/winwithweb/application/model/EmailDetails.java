package com.winwithweb.application.model;

public class EmailDetails {
	
	private String sendersname;
	private String recepientemailIds;
	private String emailcc;
	private String emailbcc;
	private String emailsubject;
	private String emailcontent;
	public String getSendersname() {
		return sendersname;
	}
	public void setSendersname(String sendersname) {
		this.sendersname = sendersname;
	}
	public String getRecepientemailIds() {
		return recepientemailIds;
	}
	public void setRecepientemailIds(String recepientemailIds) {
		this.recepientemailIds = recepientemailIds;
	}
	public String getEmailcc() {
		return emailcc;
	}
	public void setEmailcc(String emailcc) {
		this.emailcc = emailcc;
	}
	public String getEmailbcc() {
		return emailbcc;
	}
	public void setEmailbcc(String emailbcc) {
		this.emailbcc = emailbcc;
	}
	public String getEmailsubject() {
		return emailsubject;
	}
	public void setEmailsubject(String emailsubject) {
		this.emailsubject = emailsubject;
	}
	public String getEmailcontent() {
		return emailcontent;
	}
	public void setEmailcontent(String emailcontent) {
		this.emailcontent = emailcontent;
	}
	
	@Override
	public String toString(){
		return "\n SendersName[" +sendersname+"] \n"+
				"Recepient Email Ids[" +recepientemailIds+"] \n"+
				"CC Emails[" +emailcc+"] \n"+
				"BCC Email[" +emailbcc+"] \n"+
				"Email Subject[" +emailsubject+"] \n"+
				"Email Content[" +emailcontent+"] \n";
	}

	
}
