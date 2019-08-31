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

import com.google.gson.Gson;

import in.winwithweb.application.model.PollutionData;
import in.winwithweb.application.model.PollutionTrendData;
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

	static Gson gson = new Gson();

	private static List<String> states = new ArrayList<String>();

	private static Map<String, List<String>> region = new HashMap<String, List<String>>();

	private static Map<String, List<String>> stationMap = new HashMap<String, List<String>>();

	static List<Sample> dataList = new ArrayList<Sample>();

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

	public static Record getPollutionData(String region) {
		Record recordq = new Record();
		List<Record> recordList = data.getRecords();
		for (Record record : recordList) {
			if (region.equalsIgnoreCase(record.getStation())) {
				recordq = record;
			}
		}

		return recordq;

	}

	public static String getPollutionTrendData(String stationData) {
		List<PollutionData> list = new ArrayList<PollutionData>();

		for (Sample data : dataList) {
			List<Record> recordList = data.getRecords();
			for (Record record : recordList) {
				if (record.getStation().equalsIgnoreCase(stationData)) {
					PollutionData pollData = new PollutionData();
					pollData.setPollutionId(record.getPollutant_id());
					try {
						pollData.setPollutionMin(getIntData(record.getPollutant_min()));
						pollData.setPollutionMax(getIntData(record.getPollutant_max()));
						pollData.setPollutionAvg(getIntData(record.getPollutant_avg()));
						pollData.setLastUpdated(record.getLast_update());

					} catch (Exception e) {
						continue;
					}
					list.add(pollData);
				}
			}

		}

		List<PollutionTrendData> pollutionTrendList = new ArrayList<PollutionTrendData>();

		if (!list.isEmpty()) {
			for (PollutionData pollutionData : list) {
				boolean isFound = false;
				for (PollutionTrendData PollutionTrendData : pollutionTrendList) {
					if (PollutionTrendData.getPollutionId().equalsIgnoreCase(pollutionData.getPollutionId())) {
						PollutionTrendData.getPollutionMin().add(pollutionData.getPollutionMin());
						PollutionTrendData.getPollutionMax().add(pollutionData.getPollutionMax());
						PollutionTrendData.getPollutionAvg().add(pollutionData.getPollutionAvg());
						isFound = true;
					}
				}

				if (!isFound) {
					PollutionTrendData pollutionTrendData = new PollutionTrendData();
					pollutionTrendData.setPollutionId(pollutionData.getPollutionId());
					pollutionTrendData.getPollutionMin().add(pollutionData.getPollutionMin());
					pollutionTrendData.getPollutionMax().add(pollutionData.getPollutionMax());
					pollutionTrendData.getPollutionAvg().add(pollutionData.getPollutionAvg());
					pollutionTrendList.add(pollutionTrendData);

				}

			}

		}

		return gson.toJson(pollutionTrendList);
	}

	@Scheduled(cron = "0 0 0/1 * * ?")
	public void deleteData() {
		if (dataList.size() > 5) {
			dataList.remove(0);
		}
	}

	@Scheduled(cron = "0 0 0/1 * * ?")
	public void cronJobSch() {
		String url = "https://api.data.gov.in/resource/3b01bcb8-0b14-4abf-b6f2-c1bfd384ba69?api-key=579b464db66ec23bdd00000173db32215e6b4ad04b1c0d2d7138b31d&format=json&offset=0&limit=5000";
		data = getRestTemplate().getForObject(url, Sample.class);
		if (data != null) {
			dataList.add(data);
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
					stationList.add("Select Region");

					stationList.add(record.getStation());
					stationMap.put(record.getCity(), stationList);
				}

			}
		}
	}

	public static String getStationPolluutionData(String region) {
		List<PollutionData> list = new ArrayList<PollutionData>();
		List<Record> recordList = data.getRecords();
		for (Record record : recordList) {
			if (record.getStation().equalsIgnoreCase(region)) {
				PollutionData pollData = new PollutionData();
				pollData.setPollutionId(record.getPollutant_id());
				try {
					pollData.setPollutionMin(getIntData(record.getPollutant_min()));
					pollData.setPollutionMax(getIntData(record.getPollutant_max()));
					pollData.setPollutionAvg(getIntData(record.getPollutant_avg()));
					pollData.setLastUpdated(record.getLast_update());

				} catch (Exception e) {
					continue;
				}
				list.add(pollData);
			}
		}

		return gson.toJson(list);
	}

	public static String getAQI(String region) {
		List<Record> recordList = data.getRecords();
		int AQI = 0;
		for (Record record : recordList) {
			if (record.getStation().equalsIgnoreCase(region)) {
				if (getIntDataWithDefualt(record.getPollutant_avg()) > AQI) {
					AQI = getIntDataWithDefualt(record.getPollutant_avg());

				}

			}
		}
		return gson.toJson(AQI);
	}

	public static int getIntDataWithDefualt(String value) {
		int data = 0;

		try {
			data = Integer.parseInt(value);
		} catch (Exception e) {

		}

		return data;
	}

	public static int getIntData(String value) throws Exception {
		int data = 0;

		data = Integer.parseInt(value);

		return data;
	}
}
