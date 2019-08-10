
package in.winwithweb.application.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Record {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("station")
    @Expose
    private String station;
    @SerializedName("last_update")
    @Expose
    private String last_update;
   
    @SerializedName("pollutant_id")
    @Expose
    private String pollutant_id;
    
    @SerializedName("pollutant_min")
    @Expose
    private String pollutant_min;
    @SerializedName("pollutant_max")
    @Expose
    private String pollutant_max;
    @SerializedName("pollutant_avg")
    @Expose
    private String pollutant_avg;
    @SerializedName("pollutant_unit")
    @Expose
    private String pollutant_unit;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
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
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
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
	
	/**
	 * @return the last_update
	 */
	public String getLast_update() {
		return last_update;
	}
	/**
	 * @param last_update the last_update to set
	 */
	public void setLast_update(String last_update) {
		this.last_update = last_update;
	}
	/**
	 * @return the pollutant_id
	 */
	public String getPollutant_id() {
		return pollutant_id;
	}
	/**
	 * @param pollutant_id the pollutant_id to set
	 */
	public void setPollutant_id(String pollutant_id) {
		this.pollutant_id = pollutant_id;
	}
	/**
	 * @return the pollutant_min
	 */
	public String getPollutant_min() {
		return pollutant_min;
	}
	/**
	 * @param pollutant_min the pollutant_min to set
	 */
	public void setPollutant_min(String pollutant_min) {
		this.pollutant_min = pollutant_min;
	}
	/**
	 * @return the pollutant_max
	 */
	public String getPollutant_max() {
		return pollutant_max;
	}
	/**
	 * @param pollutant_max the pollutant_max to set
	 */
	public void setPollutant_max(String pollutant_max) {
		this.pollutant_max = pollutant_max;
	}
	/**
	 * @return the pollutant_avg
	 */
	public String getPollutant_avg() {
		return pollutant_avg;
	}
	/**
	 * @param pollutant_avg the pollutant_avg to set
	 */
	public void setPollutant_avg(String pollutant_avg) {
		this.pollutant_avg = pollutant_avg;
	}
	/**
	 * @return the pollutant_unit
	 */
	public String getPollutant_unit() {
		return pollutant_unit;
	}
	/**
	 * @param pollutant_unit the pollutant_unit to set
	 */
	public void setPollutant_unit(String pollutant_unit) {
		this.pollutant_unit = pollutant_unit;
	}
	
    
    

    
    
    

}
