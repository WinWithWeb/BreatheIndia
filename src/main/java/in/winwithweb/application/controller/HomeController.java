package in.winwithweb.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.winwithweb.application.model.Details;
import in.winwithweb.application.model.Record;
import in.winwithweb.application.model.Sample;
import in.winwithweb.application.schedular.AirPollutionDataSchedular;

@Controller
public class HomeController {

	@RequestMapping(value = { "/home" }, method = RequestMethod.GET)
	public String heome(Model model) {

		return "home";
	}

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getHomePage(Model model) {

		Sample data = AirPollutionDataSchedular.getData();

		if (data == null) {
			AirPollutionDataSchedular abc = new AirPollutionDataSchedular();
			abc.cronJobSch();
			data = AirPollutionDataSchedular.getData();

		}

		if (data != null) {
			List<Record> record = data.getRecords();
			if (record != null && !record.isEmpty()) {
				model.addAttribute("recordList", record);
				model.addAttribute("details", new Details());

				if (AirPollutionDataSchedular.getStates() != null) {

					model.addAttribute("stateList", AirPollutionDataSchedular.getStates());
				} else {
					model.addAttribute("stateList", new ArrayList<String>());

				}

			}
		}
		return "home";
	}

	@RequestMapping(value = "/getPollutionData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getPollutionData(@RequestParam String station) {
		return AirPollutionDataSchedular.getStationPolluutionData(station);
	}
	
	@RequestMapping(value = "/getAQI", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAQI(@RequestParam String station) {
		return AirPollutionDataSchedular.getAQI(station);
	}
	
	@RequestMapping(value = "/getTrendData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getTrendData(@RequestParam String station) {
		System.out.println("test");
		return AirPollutionDataSchedular.getPollutionTrendData(station);
	}

	@RequestMapping(value = "/getCitiesData")
	@ResponseBody
	public List<String> getCitiesData(@RequestParam String state) {
		return AirPollutionDataSchedular.getCity(state);
	}

	@RequestMapping(value = "/getStationData")
	@ResponseBody
	public List<String> getStationData(@RequestParam String city) {
		return AirPollutionDataSchedular.getStation(city);
	}

}
