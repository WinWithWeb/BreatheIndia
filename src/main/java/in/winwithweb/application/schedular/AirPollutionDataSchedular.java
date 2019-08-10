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
		String url = "https://api.data.gov.in/resource/3b01bcb8-0b14-4abf-b6f2-c1bfd384ba69?api-key=579b464db66ec23bdd00000173db32215e6b4ad04b1c0d2d7138b31d&format=json&offset=0&limit=5000";
		data = getRestTemplate().getForObject(url, Sample.class);
	}

}
