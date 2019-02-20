package com.winwithweb.application.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration("emailProperties")
@ConfigurationProperties(prefix = "email.configuration")
public class EmailConfigurations {
	
	private String hostname;
	private String senderemail;
	private String senderemailpassword;
	private String setReplyto;
	
	public String gethostname() {
		return hostname;
	}
	public void sethostname(String hostname) {
		this.hostname = hostname;
	}
	public String getsenderemail() {
		return senderemail;
	}
	public void setsenderemail(String senderemail) {
		this.senderemail = senderemail;
	}
	public String getsenderemailpassword() {
		return senderemailpassword;
	}
	public void setsenderemailpassword(String senderemailpassword) {
		this.senderemailpassword = senderemailpassword;
	}
	public String getsetReplyto() {
		return setReplyto;
	}
	public void setsetReplyto(String setReplyto) {
		this.setReplyto = setReplyto;
	}
	
	@Override
	public String toString(){
		return "\n hostname[" +hostname+"] \n"+
				"senderemail[" +senderemail+"] \n"+
				"senderemailpassword[" +senderemailpassword+"] \n"+
				"Reply to [" +setReplyto+"] \n";
	}

}
