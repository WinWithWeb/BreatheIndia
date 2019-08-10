/**
 * 
 */
package in.winwithweb.application.schedular;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import in.winwithweb.application.model.Record;
import in.winwithweb.application.model.Sample;

/**
 * @author sachingoyal
 *
 */
@Component
public class AirPollutionDataSchedular {

	RestTemplate restTemplate;

	static Sample data;

	private static List<String> states = new ArrayList<String>();

	private static Map<String, List<String>> region = new HashMap<String, List<String>>();
	
	private static Map<String, List<String>> stationMap = new HashMap<String, List<String>>();


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

	public static List<String> getStates() {
		return states;
	}

	public static List<String> getCity(String stateName) {
		return region.get(stateName);
	}
	
	public static List<String> getStation(String city) {
		return stationMap.get(city);
	}

	@Scheduled(cron = "0/10 * * * * ?")
	public void cronJobSch() {
		String url = "https://api.data.gov.in/resource/3b01bcb8-0b14-4abf-b6f2-c1bfd384ba69?api-key=579b464db66ec23bdd00000173db32215e6b4ad04b1c0d2d7138b31d&format=json&offset=0&limit=5000";
		data = getRestTemplate().getForObject(url, Sample.class);
		if (data != null) {
			List<Record> recordList = data.getRecords();
			states = new ArrayList<String>();
			region = new HashMap<String, List<String>>();
			stationMap = new HashMap<String, List<String>>();
			for (Record record : recordList) {
				if (!states.contains(record.getState())) {
					states.add(record.getState());

				}

				if (region.containsKey(record.getState())) {
					List<String> reg = region.get(record.getState());
					if (!reg.contains(record.getCity())) {
						reg.add(record.getCity());
					}
				} else {
					List<String> cityList = new ArrayList<String>();
					cityList.add("Select City");

					cityList.add(record.getCity());
					
					region.put(record.getState(), cityList);
				}
				
				if (stationMap.containsKey(record.getCity())) {
					List<String> station = stationMap.get(record.getCity());
					if (!station.contains(record.getStation())) {

						station.add(record.getStation());
					}
				} else {
					List<String> stationList = new ArrayList<String>();
					stationList.add("Select Station");

					stationList.add(record.getStation());
					stationMap.put(record.getCity(), stationList);
				}

			}
		}
	}

}
