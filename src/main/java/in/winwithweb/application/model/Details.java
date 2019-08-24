/**
 * 
 */
package in.winwithweb.application.model;

/**
 * @author sachingoyal
 *
 */
public class Details {

	String state;
	String region;
	String station;

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/**
	 * @return the station
	 */
	public String getStation() {
		return station;
	}

	/**
	 * @param station the station to set
	 */
	public void setStation(String station) {
		this.station = station;
	}

	@Override
	public String toString() {
		return "Details [state=" + state + ", region=" + region + ", station=" + station + "]";
	}
	
	

}
