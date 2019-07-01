package in.winwithweb.application.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import in.winwithweb.application.model.Record;
import in.winwithweb.application.model.Sample;
import in.winwithweb.application.schedular.AirPollutionDataSchedular;

@Controller
public class HomeController {

	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String getHomePage(Model model) {
		Sample data = AirPollutionDataSchedular.getData();
		
		if (data != null) {
			List<Record> record = data.getRecords();
			if (record != null && !record.isEmpty()) {
				for (Record rec : record) {
					model.addAttribute("sachin", rec.toString());
				}
			}

		}
		
		
		
		
		return "home";
	}

}
