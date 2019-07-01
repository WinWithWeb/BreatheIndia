/**
 * 
 */
package in.winwithweb.application.schedular;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import in.winwithweb.application.model.Sample;

/**
 * @author sachingoyal
 *
 */
@Component
public class AirPollutionDataSchedular {

	RestTemplate restTemplate;

	static Sample data;

	private RestTemplate getRestTemplate() {
		if (restTemplate == null) {
			restTemplate = new RestTemplate();
		}
		return restTemplate;
	}
	
	public static Sample getData() {
		return data;
	}

	@Scheduled(cron = "0 0/1 * * * ?")
	public void cronJobSch() {
		String url = "https://api.data.gov.in/resource/3b01bcb8-0b14-4abf-b6f2-c1bfd384ba69?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=json&offset=0&limit=20";
		data = getRestTemplate().getForObject(url, Sample.class);
	}

}
