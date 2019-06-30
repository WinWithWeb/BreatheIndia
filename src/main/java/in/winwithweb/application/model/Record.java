
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
    private String lastUpdate;
    @SerializedName("pollutant_id")
    @Expose
    private String pollutantId;
    @SerializedName("pollutant_min")
    @Expose
    private String pollutantMin;
    @SerializedName("pollutant_max")
    @Expose
    private String pollutantMax;
    @SerializedName("pollutant_avg")
    @Expose
    private String pollutantAvg;
    @SerializedName("pollutant_unit")
    @Expose
    private String pollutantUnit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getPollutantId() {
        return pollutantId;
    }

    public void setPollutantId(String pollutantId) {
        this.pollutantId = pollutantId;
    }

    public String getPollutantMin() {
        return pollutantMin;
    }

    public void setPollutantMin(String pollutantMin) {
        this.pollutantMin = pollutantMin;
    }

    public String getPollutantMax() {
        return pollutantMax;
    }

    public void setPollutantMax(String pollutantMax) {
        this.pollutantMax = pollutantMax;
    }

    public String getPollutantAvg() {
        return pollutantAvg;
    }

    public void setPollutantAvg(String pollutantAvg) {
        this.pollutantAvg = pollutantAvg;
    }

    public String getPollutantUnit() {
        return pollutantUnit;
    }

    public void setPollutantUnit(String pollutantUnit) {
        this.pollutantUnit = pollutantUnit;
    }

	@Override
	public String toString() {
		return "Record [id=" + id + ", country=" + country + ", state=" + state + ", city=" + city + ", station="
				+ station + ", lastUpdate=" + lastUpdate + ", pollutantId=" + pollutantId + ", pollutantMin="
				+ pollutantMin + ", pollutantMax=" + pollutantMax + ", pollutantAvg=" + pollutantAvg
				+ ", pollutantUnit=" + pollutantUnit + "]";
	}
    
    

}
