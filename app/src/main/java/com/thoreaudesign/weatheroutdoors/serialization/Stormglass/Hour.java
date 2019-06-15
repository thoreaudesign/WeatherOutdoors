
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hour implements Serializable, Parcelable
{

    @SerializedName("airTemperature")
    @Expose

    private List<AirTemperature> airTemperature = new ArrayList<AirTemperature>();
    @SerializedName("iceCover")
    @Expose

    private List<IceCover> iceCover = new ArrayList<IceCover>();
    @SerializedName("seaLevel")
    @Expose

    private List<SeaLevel> seaLevel = new ArrayList<SeaLevel>();
    @SerializedName("swellDirection")
    @Expose

    private List<SwellDirection> swellDirection = new ArrayList<SwellDirection>();
    @SerializedName("swellHeight")
    @Expose

    private List<SwellHeight> swellHeight = new ArrayList<SwellHeight>();
    @SerializedName("swellPeriod")
    @Expose

    private List<SwellPeriod> swellPeriod = new ArrayList<SwellPeriod>();
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("visibility")
    @Expose

    private List<Visibility> visibility = new ArrayList<Visibility>();
    @SerializedName("waterTemperature")
    @Expose

    private List<WaterTemperature> waterTemperature = new ArrayList<WaterTemperature>();
    @SerializedName("waveDirection")
    @Expose

    private List<WaveDirection> waveDirection = new ArrayList<WaveDirection>();
    @SerializedName("waveHeight")
    @Expose

    private List<WaveHeight> waveHeight = new ArrayList<WaveHeight>();
    @SerializedName("wavePeriod")
    @Expose

    private List<WavePeriod> wavePeriod = new ArrayList<WavePeriod>();
    @SerializedName("windDirection")
    @Expose

    private List<WindDirection> windDirection = new ArrayList<WindDirection>();
    @SerializedName("windSpeed")
    @Expose

    private List<WindSpeed> windSpeed = new ArrayList<WindSpeed>();
    @SerializedName("windWaveDirection")
    @Expose

    private List<WindWaveDirection> windWaveDirection = new ArrayList<WindWaveDirection>();
    @SerializedName("windWaveHeight")
    @Expose

    private List<WindWaveHeight> windWaveHeight = new ArrayList<WindWaveHeight>();
    @SerializedName("windWavePeriod")
    @Expose

    private List<WindWavePeriod> windWavePeriod = new ArrayList<WindWavePeriod>();
    public final static Creator<Hour> CREATOR = new Creator<Hour>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Hour createFromParcel(Parcel in) {
            return new Hour(in);
        }

        public Hour[] newArray(int size) {
            return (new Hour[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1785991147217140134L;

    protected Hour(Parcel in) {
        in.readList(this.airTemperature, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.AirTemperature.class.getClassLoader()));
        in.readList(this.iceCover, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.IceCover.class.getClassLoader()));
        in.readList(this.seaLevel, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.SeaLevel.class.getClassLoader()));
        in.readList(this.swellDirection, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.SwellDirection.class.getClassLoader()));
        in.readList(this.swellHeight, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.SwellHeight.class.getClassLoader()));
        in.readList(this.swellPeriod, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.SwellPeriod.class.getClassLoader()));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.visibility, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.Visibility.class.getClassLoader()));
        in.readList(this.waterTemperature, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.WaterTemperature.class.getClassLoader()));
        in.readList(this.waveDirection, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.WaveDirection.class.getClassLoader()));
        in.readList(this.waveHeight, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.WaveHeight.class.getClassLoader()));
        in.readList(this.wavePeriod, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.WavePeriod.class.getClassLoader()));
        in.readList(this.windDirection, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.WindDirection.class.getClassLoader()));
        in.readList(this.windSpeed, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.WindSpeed.class.getClassLoader()));
        in.readList(this.windWaveDirection, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.WindWaveDirection.class.getClassLoader()));
        in.readList(this.windWaveHeight, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.WindWaveHeight.class.getClassLoader()));
        in.readList(this.windWavePeriod, (com.thoreaudesign.weatheroutdoors.serialization.Stormglass.WindWavePeriod.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Hour() {
    }

    /**
     * 
     * @param waveDirection
     * @param iceCover
     * @param windDirection
     * @param visibility
     * @param windWavePeriod
     * @param swellHeight
     * @param seaLevel
     * @param windSpeed
     * @param time
     * @param airTemperature
     * @param windWaveDirection
     * @param windWaveHeight
     * @param swellPeriod
     * @param wavePeriod
     * @param waveHeight
     * @param waterTemperature
     * @param swellDirection
     */
    public Hour(List<AirTemperature> airTemperature, List<IceCover> iceCover, List<SeaLevel> seaLevel, List<SwellDirection> swellDirection, List<SwellHeight> swellHeight, List<SwellPeriod> swellPeriod, String time, List<Visibility> visibility, List<WaterTemperature> waterTemperature, List<WaveDirection> waveDirection, List<WaveHeight> waveHeight, List<WavePeriod> wavePeriod, List<WindDirection> windDirection, List<WindSpeed> windSpeed, List<WindWaveDirection> windWaveDirection, List<WindWaveHeight> windWaveHeight, List<WindWavePeriod> windWavePeriod) {
        super();
        this.airTemperature = airTemperature;
        this.iceCover = iceCover;
        this.seaLevel = seaLevel;
        this.swellDirection = swellDirection;
        this.swellHeight = swellHeight;
        this.swellPeriod = swellPeriod;
        this.time = time;
        this.visibility = visibility;
        this.waterTemperature = waterTemperature;
        this.waveDirection = waveDirection;
        this.waveHeight = waveHeight;
        this.wavePeriod = wavePeriod;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.windWaveDirection = windWaveDirection;
        this.windWaveHeight = windWaveHeight;
        this.windWavePeriod = windWavePeriod;
    }

    public List<AirTemperature> getAirTemperature() {
        return airTemperature;
    }

    public void setAirTemperature(List<AirTemperature> airTemperature) {
        this.airTemperature = airTemperature;
    }

    public Hour withAirTemperature(List<AirTemperature> airTemperature) {
        this.airTemperature = airTemperature;
        return this;
    }

    public List<IceCover> getIceCover() {
        return iceCover;
    }

    public void setIceCover(List<IceCover> iceCover) {
        this.iceCover = iceCover;
    }

    public Hour withIceCover(List<IceCover> iceCover) {
        this.iceCover = iceCover;
        return this;
    }

    public List<SeaLevel> getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(List<SeaLevel> seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Hour withSeaLevel(List<SeaLevel> seaLevel) {
        this.seaLevel = seaLevel;
        return this;
    }

    public List<SwellDirection> getSwellDirection() {
        return swellDirection;
    }

    public void setSwellDirection(List<SwellDirection> swellDirection) {
        this.swellDirection = swellDirection;
    }

    public Hour withSwellDirection(List<SwellDirection> swellDirection) {
        this.swellDirection = swellDirection;
        return this;
    }

    public List<SwellHeight> getSwellHeight() {
        return swellHeight;
    }

    public void setSwellHeight(List<SwellHeight> swellHeight) {
        this.swellHeight = swellHeight;
    }

    public Hour withSwellHeight(List<SwellHeight> swellHeight) {
        this.swellHeight = swellHeight;
        return this;
    }

    public List<SwellPeriod> getSwellPeriod() {
        return swellPeriod;
    }

    public void setSwellPeriod(List<SwellPeriod> swellPeriod) {
        this.swellPeriod = swellPeriod;
    }

    public Hour withSwellPeriod(List<SwellPeriod> swellPeriod) {
        this.swellPeriod = swellPeriod;
        return this;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Hour withTime(String time) {
        this.time = time;
        return this;
    }

    public List<Visibility> getVisibility() {
        return visibility;
    }

    public void setVisibility(List<Visibility> visibility) {
        this.visibility = visibility;
    }

    public Hour withVisibility(List<Visibility> visibility) {
        this.visibility = visibility;
        return this;
    }

    public List<WaterTemperature> getWaterTemperature() {
        return waterTemperature;
    }

    public void setWaterTemperature(List<WaterTemperature> waterTemperature) {
        this.waterTemperature = waterTemperature;
    }

    public Hour withWaterTemperature(List<WaterTemperature> waterTemperature) {
        this.waterTemperature = waterTemperature;
        return this;
    }

    public List<WaveDirection> getWaveDirection() {
        return waveDirection;
    }

    public void setWaveDirection(List<WaveDirection> waveDirection) {
        this.waveDirection = waveDirection;
    }

    public Hour withWaveDirection(List<WaveDirection> waveDirection) {
        this.waveDirection = waveDirection;
        return this;
    }

    public List<WaveHeight> getWaveHeight() {
        return waveHeight;
    }

    public void setWaveHeight(List<WaveHeight> waveHeight) {
        this.waveHeight = waveHeight;
    }

    public Hour withWaveHeight(List<WaveHeight> waveHeight) {
        this.waveHeight = waveHeight;
        return this;
    }

    public List<WavePeriod> getWavePeriod() {
        return wavePeriod;
    }

    public void setWavePeriod(List<WavePeriod> wavePeriod) {
        this.wavePeriod = wavePeriod;
    }

    public Hour withWavePeriod(List<WavePeriod> wavePeriod) {
        this.wavePeriod = wavePeriod;
        return this;
    }

    public List<WindDirection> getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(List<WindDirection> windDirection) {
        this.windDirection = windDirection;
    }

    public Hour withWindDirection(List<WindDirection> windDirection) {
        this.windDirection = windDirection;
        return this;
    }

    public List<WindSpeed> getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(List<WindSpeed> windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Hour withWindSpeed(List<WindSpeed> windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public List<WindWaveDirection> getWindWaveDirection() {
        return windWaveDirection;
    }

    public void setWindWaveDirection(List<WindWaveDirection> windWaveDirection) {
        this.windWaveDirection = windWaveDirection;
    }

    public Hour withWindWaveDirection(List<WindWaveDirection> windWaveDirection) {
        this.windWaveDirection = windWaveDirection;
        return this;
    }

    public List<WindWaveHeight> getWindWaveHeight() {
        return windWaveHeight;
    }

    public void setWindWaveHeight(List<WindWaveHeight> windWaveHeight) {
        this.windWaveHeight = windWaveHeight;
    }

    public Hour withWindWaveHeight(List<WindWaveHeight> windWaveHeight) {
        this.windWaveHeight = windWaveHeight;
        return this;
    }

    public List<WindWavePeriod> getWindWavePeriod() {
        return windWavePeriod;
    }

    public void setWindWavePeriod(List<WindWavePeriod> windWavePeriod) {
        this.windWavePeriod = windWavePeriod;
    }

    public Hour withWindWavePeriod(List<WindWavePeriod> windWavePeriod) {
        this.windWavePeriod = windWavePeriod;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(airTemperature);
        dest.writeList(iceCover);
        dest.writeList(seaLevel);
        dest.writeList(swellDirection);
        dest.writeList(swellHeight);
        dest.writeList(swellPeriod);
        dest.writeValue(time);
        dest.writeList(visibility);
        dest.writeList(waterTemperature);
        dest.writeList(waveDirection);
        dest.writeList(waveHeight);
        dest.writeList(wavePeriod);
        dest.writeList(windDirection);
        dest.writeList(windSpeed);
        dest.writeList(windWaveDirection);
        dest.writeList(windWaveHeight);
        dest.writeList(windWavePeriod);
    }

    public int describeContents() {
        return  0;
    }

}
