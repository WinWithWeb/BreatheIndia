package in.winwithweb.application.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
		
		AirPollutionDataSchedular abc = new AirPollutionDataSchedular();
				abc.cronJobSch();
		Sample data = AirPollutionDataSchedular.getData();

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
		return "displayData";
	}

	@RequestMapping(value = "/getPollutionData", method = RequestMethod.POST)
	public String createNewUser(@Valid Details details, BindingResult bindingResult, Model model) {

		Sample data = AirPollutionDataSchedular.getData();

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

		if (bindingResult.hasErrors()) {
			return "index";
		} else {
			model.addAttribute("city", "acb");
			model.addAttribute("station", details.getStation());


			System.out.println(details.toString());

		}
		return "displayData";
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
