/**
 * 
 */
package in.winwithweb.application.model;

/**
 * @author sachingoyal
 *
 */
public class PollutionData {

	private String pollutionId;
	private int pollutionMin;
	private int pollutionMax;
	private int pollutionAvg;
	private String lastUpdated;

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
	public int getPollutionMin() {
		return pollutionMin;
	}

	/**
	 * @param pollutionMin the pollutionMin to set
	 */
	public void setPollutionMin(int pollutionMin) {
		this.pollutionMin = pollutionMin;
	}

	/**
	 * @return the polluitonMax
	 */
	public int getPollutionMax() {
		return pollutionMax;
	}

	/**
	 * @param polluitonMax the polluitonMax to set
	 */
	public void setPollutionMax(int pollutionMax) {
		this.pollutionMax = pollutionMax;
	}

	/**
	 * @return the pollutionAvg
	 */
	public int getPollutionAvg() {
		return pollutionAvg;
	}

	/**
	 * @param pollutionAvg the pollutionAvg to set
	 */
	public void setPollutionAvg(int pollutionAvg) {
		this.pollutionAvg = pollutionAvg;
	}

	/**
	 * @return the lastUpdated
	 */
	public String getLastUpdated() {
		return lastUpdated;
	}

	/**
	 * @param lastUpdated the lastUpdated to set
	 */
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
