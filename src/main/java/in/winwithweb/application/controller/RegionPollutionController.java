/**
 * 
 */
package in.winwithweb.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import in.winwithweb.application.model.Details;
import in.winwithweb.application.model.Record;
import in.winwithweb.application.model.Sample;
import in.winwithweb.application.schedular.AirPollutionDataSchedular;

/**
 * @author sachingoyal
 *
 */

@Controller
public class RegionPollutionController {

	@RequestMapping(value = { "/station/{stationName}" }, method = RequestMethod.GET)
	public String getRegionData(@PathVariable("stationName") String stationName, ModelMap model) {

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
				Details details = new Details();
				details.setStationName(stationName);
				model.addAttribute("details", details);

				if (AirPollutionDataSchedular.getStates() != null) {

					model.addAttribute("stateList", AirPollutionDataSchedular.getStates());
				} else {
					model.addAttribute("stateList", new ArrayList<String>());

				}

			}
		}
		


		return "displayStationData";
	}
	
	@RequestMapping(value = "/station/getPollutionData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getPollutionData(@RequestParam String station) {
		return AirPollutionDataSchedular.getStationPolluutionData(station);
	}
	
	
	
	@RequestMapping(value = "/station/getAQI", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getAQI(@RequestParam String station) {
		return AirPollutionDataSchedular.getAQI(station);
	}
	
	@RequestMapping(value = "/station/getTrendData", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getTrendData(@RequestParam String station) {
		return AirPollutionDataSchedular.getPollutionTrendData(station);
	}

}
