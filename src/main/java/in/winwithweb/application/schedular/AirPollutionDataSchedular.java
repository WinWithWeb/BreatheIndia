/**
 * 
 */
package in.winwithweb.application.schedular;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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

	private static Map<String, Integer> aqiData = new HashMap<String, Integer>();

	private static List<String> lowestAQI = new ArrayList<String>();
	private static List<String> highestAQI = new ArrayList<String>();

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
						PollutionTrendData.getTime().add(getTime(pollutionData.getLastUpdated()));
						isFound = true;
					}
				}

				if (!isFound) {
					PollutionTrendData pollutionTrendData = new PollutionTrendData();
					pollutionTrendData.setPollutionId(pollutionData.getPollutionId());
					pollutionTrendData.getPollutionMin().add(pollutionData.getPollutionMin());
					pollutionTrendData.getPollutionMax().add(pollutionData.getPollutionMax());
					pollutionTrendData.getPollutionAvg().add(pollutionData.getPollutionAvg());
					pollutionTrendData.getTime().add(getTime(pollutionData.getLastUpdated()));
					pollutionTrendList.add(pollutionTrendData);

				}

			}

		}

		return gson.toJson(pollutionTrendList);
	}

	private static String getTime(String date) {
		String originalString = date;

		try {
			Date newdate = new SimpleDateFormat("dd-MM-dyyy HH:mm:ss").parse(originalString);
			originalString = new SimpleDateFormat("HH:mm").format(newdate);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return originalString;
	}

	@Scheduled(cron = "0 0 0/1 * * ?")
	public void cronJobSch() {
		try {
		String url = "https://api.data.gov.in/resource/3b01bcb8-0b14-4abf-b6f2-c1bfd384ba69?api-key=579b464db66ec23bdd00000173db32215e6b4ad04b1c0d2d7138b31d&format=json&offset=0&limit=999";
		
		Sample tempdata = getRestTemplate().getForObject(url, Sample.class);
		data = tempdata;
		
		if (data != null) {

			states.clear();
			region.clear();
			stationMap.clear();
			aqiData.clear();

			Date date = new Date();

			DateFormat df = new SimpleDateFormat("hh");
			int hour = Integer.parseInt(df.format(date));

			if (dataList.size() == 0) {
				dataList.add(data);
			} else {
				if (hour % 6 == 0) {
					if (dataList.size() >= 5) {
						dataList.remove(0);
					}
					dataList.add(data);
				}
			}

			List<Record> recordList = data.getRecords();

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

				if (aqiData.containsKey(record.getStation().toUpperCase())) {
					int AQI = aqiData.get(record.getStation().toUpperCase());
					if (AQI < getIntDataWithDefualt(record.getPollutant_avg())) {
						AQI = getIntDataWithDefualt(record.getPollutant_avg());
						aqiData.put(record.getStation().toUpperCase(), AQI);
					}

				} else {
					aqiData.put(record.getStation().toUpperCase(), getIntDataWithDefualt(record.getPollutant_avg()));
				}

			}

			aqiData = sortByValue(aqiData);

			int i = 0;
			for (Entry<String, Integer> entry : aqiData.entrySet()) {
				if (i == 10) {
					break;
				} else {
					if (entry.getValue() != 0) {
						lowestAQI.add(entry.getKey());

						i++;
					}
				}
			}

			aqiData = sortByValue1(aqiData);

			int j = 0;
			for (Entry<String, Integer> entry : aqiData.entrySet()) {
				if (j == 10) {
					break;
				} else {
					if (entry.getValue() != 0) {
						highestAQI.add(entry.getKey());
						j++;
					}
				}
			}

		}
		}catch(Exception e) {
		}
	}

	private static Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {

		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// 3. Loop the sorted list and put it into a new insertion order Map
		// LinkedHashMap
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	private static Map<String, Integer> sortByValue1(Map<String, Integer> unsortMap) {

		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		// 3. Loop the sorted list and put it into a new insertion order Map
		// LinkedHashMap
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
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
		return gson.toJson(aqiData.get(region.toUpperCase()));
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
