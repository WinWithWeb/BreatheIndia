package com.winwithweb.application.model;

public class Email {
	
	private String domainname;
	private String domainRegistrarName;
	private String registrantName;
	private String registrantEmail;
	
	public Email(String domainname,String domainRegistrarName,String RegistrantName,String registrantEmail){
		this.domainname=domainname;
		this.domainRegistrarName=domainRegistrarName;
		this.registrantName=RegistrantName;
		this.registrantEmail=registrantEmail;
	}
	public String getDomainname() {
		return domainname;
	}
	public void setDomainname(String domainname) {
		this.domainname = domainname;
	}
	public String getDomainRegistrarName() {
		return domainRegistrarName;
	}
	public void setDomainRegistrarName(String domainRegistrarName) {
		this.domainRegistrarName = domainRegistrarName;
	}
	public String getRegistrantName() {
		return registrantName;
	}
	public void setRegistrantName(String registrantName) {
		this.registrantName = registrantName;
	}
	public String getRegistrantEmail() {
		return registrantEmail;
	}
	public void setRegistrantEmail(String registrantEmail) {
		this.registrantEmail = registrantEmail;
	}
	
	@Override
	public String toString(){
		return "\n DomainName[" +domainname+"] \n"+
				"DomainRegistrarName[" +domainRegistrarName+"] \n"+
				"RegistrantName[" +registrantName+"] \n"+
				"DomainRegistrarName[" +registrantEmail+"] \n";
	}

}
