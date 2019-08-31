/**
 * 
 */
package in.winwithweb.application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sachingoyal
 *
 */
public class PollutionTrendData {

	private String pollutionId;
	private List<Integer> pollutionMin = new ArrayList<Integer>();
	private List<Integer> pollutionMax = new ArrayList<Integer>();
	private List<Integer> pollutionAvg = new ArrayList<Integer>();
	/**
	 * @return the pollutionId
	 */
	public String getPollutionId() {
		return pollutionId;
	}
	/**
	 * @param pollutionId the pollutionId to set
	 */
	public void setPollutionId(String pollutionId) {
		this.pollutionId = pollutionId;
	}
	/**
	 * @return the pollutionMin
	 */
	public List<Integer> getPollutionMin() {
		return pollutionMin;
	}
	/**
	 * @param pollutionMin the pollutionMin to set
	 */
	public void setPollutionMin(List<Integer> pollutionMin) {
		this.pollutionMin = pollutionMin;
	}
	/**
	 * @return the pollutionMax
	 */
	public List<Integer> getPollutionMax() {
		return pollutionMax;
	}
	/**
	 * @param pollutionMax the pollutionMax to set
	 */
	public void setPollutionMax(List<Integer> pollutionMax) {
		this.pollutionMax = pollutionMax;
	}
	/**
	 * @return the pollutionAvg
	 */
	public List<Integer> getPollutionAvg() {
		return pollutionAvg;
	}
	/**
	 * @param pollutionAvg the pollutionAvg to set
	 */
	public void setPollutionAvg(List<Integer> pollutionAvg) {
		this.pollutionAvg = pollutionAvg;
	}

	
}
