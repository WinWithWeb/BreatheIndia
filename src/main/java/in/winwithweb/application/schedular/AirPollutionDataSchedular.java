/**
 * 
 */
package in.winwithweb.application.schedular;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
			List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
			MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
			converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
			messageConverters.add(converter);
			restTemplate.setMessageConverters(messageConverters);
		}

		return restTemplate;
	}

	public static Sample getData() {
		return data;
	}

	@Scheduled(cron = "0/10 * * * * ?")
	public void cronJobSch() {
		System.out.println("Sachin");
		String url = "https://api.data.gov.in/resource/3b01bcb8-0b14-4abf-b6f2-c1bfd384ba69?api-key=579b464db66ec23bdd000001cdd3946e44ce4aad7209ff7b23ac571b&format=json&offset=0&limit=5000";
		data = getRestTemplate().getForObject(url, Sample.class);
		System.out.println(data.toString());
	}

}
