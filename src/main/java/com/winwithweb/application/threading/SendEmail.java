/**
 * 
 */
package com.winwithweb.application.threading;

import com.winwithweb.application.model.EmailConfigurations;
import com.winwithweb.application.model.EmailDetails;
import com.winwithweb.application.service.EmailUtility;

/**
 * @author sachingoyal
 *
 */
public class SendEmail implements Runnable {

	EmailDetails emaildata;
	EmailConfigurations emailconfig;

	public SendEmail(EmailDetails emaildata, EmailConfigurations emailconfig) {
		this.emaildata = emaildata;
		this.emailconfig = emailconfig;
	}

	@Override
	public void run() {
		EmailUtility.sendEmail(emaildata, emailconfig);
	}

}
