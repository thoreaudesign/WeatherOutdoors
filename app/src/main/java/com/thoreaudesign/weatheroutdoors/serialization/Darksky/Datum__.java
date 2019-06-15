
package com.thoreaudesign.weatheroutdoors.serialization.Darksky;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Datum__ implements Serializable, Parcelable
{

    @SerializedName("time")
    @Expose
    private double time;
    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("sunriseTime")
    @Expose
    private double sunriseTime;
    @SerializedName("sunsetTime")
    @Expose
    private double sunsetTime;
    @SerializedName("moonPhase")
    @Expose
    private double moonPhase;
    @SerializedName("precipIntensity")
    @Expose
    private double precipIntensity;
    @SerializedName("precipIntensityMax")
    @Expose
    private double precipIntensityMax;
    @SerializedName("precipIntensityMaxTime")
    @Expose
    private double precipIntensityMaxTime;
    @SerializedName("precipProbability")
    @Expose
    private double precipProbability;
    @SerializedName("precipType")
    @Expose
    private String precipType;
    @SerializedName("temperatureHigh")
    @Expose
    private double temperatureHigh;
    @SerializedName("temperatureHighTime")
    @Expose
    private double temperatureHighTime;
    @SerializedName("temperatureLow")
    @Expose
    private double temperatureLow;
    @SerializedName("temperatureLowTime")
    @Expose
    private double temperatureLowTime;
    @SerializedName("apparentTemperatureHigh")
    @Expose
    private double apparentTemperatureHigh;
    @SerializedName("apparentTemperatureHighTime")
    @Expose
    private double apparentTemperatureHighTime;
    @SerializedName("apparentTemperatureLow")
    @Expose
    private double apparentTemperatureLow;
    @SerializedName("apparentTemperatureLowTime")
    @Expose
    private double apparentTemperatureLowTime;
    @SerializedName("dewPoint")
    @Expose
    private double dewPoint;
    @SerializedName("humidity")
    @Expose
    private double humidity;
    @SerializedName("pressure")
    @Expose
    private double pressure;
    @SerializedName("windSpeed")
    @Expose
    private double windSpeed;
    @SerializedName("windGust")
    @Expose
    private double windGust;
    @SerializedName("windGustTime")
    @Expose
    private double windGustTime;
    @SerializedName("windBearing")
    @Expose
    private double windBearing;
    @SerializedName("cloudCover")
    @Expose
    private double cloudCover;
    @SerializedName("uvIndex")
    @Expose
    private double uvIndex;
    @SerializedName("uvIndexTime")
    @Expose
    private double uvIndexTime;
    @SerializedName("visibility")
    @Expose
    private double visibility;
    @SerializedName("ozone")
    @Expose
    private double ozone;
    @SerializedName("temperatureMin")
    @Expose
    private double temperatureMin;
    @SerializedName("temperatureMinTime")
    @Expose
    private double temperatureMinTime;
    @SerializedName("temperatureMax")
    @Expose
    private double temperatureMax;
    @SerializedName("temperatureMaxTime")
    @Expose
    private double temperatureMaxTime;
    @SerializedName("apparentTemperatureMin")
    @Expose
    private double apparentTemperatureMin;
    @SerializedName("apparentTemperatureMinTime")
    @Expose
    private double apparentTemperatureMinTime;
    @SerializedName("apparentTemperatureMax")
    @Expose
    private double apparentTemperatureMax;
    @SerializedName("apparentTemperatureMaxTime")
    @Expose
    private double apparentTemperatureMaxTime;
    public final static Parcelable.Creator<Datum__> CREATOR = new Creator<Datum__>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum__ createFromParcel(Parcel in) {
            return new Datum__(in);
        }

        public Datum__[] newArray(int size) {
            return (new Datum__[size]);
        }

    }
    ;
    private final static long serialVersionUID = 157983865386105425L;

    protected Datum__(Parcel in) {
        this.time = ((long) in.readValue((long.class.getClassLoader())));
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        this.sunriseTime = ((long) in.readValue((long.class.getClassLoader())));
        this.sunsetTime = ((long) in.readValue((long.class.getClassLoader())));
        this.moonPhase = ((double) in.readValue((double.class.getClassLoader())));
        this.precipIntensity = ((double) in.readValue((double.class.getClassLoader())));
        this.precipIntensityMax = ((double) in.readValue((double.class.getClassLoader())));
        this.precipIntensityMaxTime = ((long) in.readValue((long.class.getClassLoader())));
        this.precipProbability = ((double) in.readValue((double.class.getClassLoader())));
        this.precipType = ((String) in.readValue((String.class.getClassLoader())));
        this.temperatureHigh = ((double) in.readValue((double.class.getClassLoader())));
        this.temperatureHighTime = ((long) in.readValue((long.class.getClassLoader())));
        this.temperatureLow = ((double) in.readValue((double.class.getClassLoader())));
        this.temperatureLowTime = ((long) in.readValue((long.class.getClassLoader())));
        this.apparentTemperatureHigh = ((double) in.readValue((double.class.getClassLoader())));
        this.apparentTemperatureHighTime = ((long) in.readValue((long.class.getClassLoader())));
        this.apparentTemperatureLow = ((double) in.readValue((double.class.getClassLoader())));
        this.apparentTemperatureLowTime = ((long) in.readValue((long.class.getClassLoader())));
        this.dewPoint = ((double) in.readValue((double.class.getClassLoader())));
        this.humidity = ((double) in.readValue((double.class.getClassLoader())));
        this.pressure = ((double) in.readValue((double.class.getClassLoader())));
        this.windSpeed = ((double) in.readValue((double.class.getClassLoader())));
        this.windGust = ((double) in.readValue((double.class.getClassLoader())));
        this.windGustTime = ((long) in.readValue((long.class.getClassLoader())));
        this.windBearing = ((long) in.readValue((long.class.getClassLoader())));
        this.cloudCover = ((double) in.readValue((double.class.getClassLoader())));
        this.uvIndex = ((long) in.readValue((long.class.getClassLoader())));
        this.uvIndexTime = ((long) in.readValue((long.class.getClassLoader())));
        this.visibility = ((double) in.readValue((double.class.getClassLoader())));
        this.ozone = ((double) in.readValue((double.class.getClassLoader())));
        this.temperatureMin = ((double) in.readValue((double.class.getClassLoader())));
        this.temperatureMinTime = ((long) in.readValue((long.class.getClassLoader())));
        this.temperatureMax = ((double) in.readValue((double.class.getClassLoader())));
        this.temperatureMaxTime = ((long) in.readValue((long.class.getClassLoader())));
        this.apparentTemperatureMin = ((double) in.readValue((double.class.getClassLoader())));
        this.apparentTemperatureMinTime = ((long) in.readValue((long.class.getClassLoader())));
        this.apparentTemperatureMax = ((double) in.readValue((double.class.getClassLoader())));
        this.apparentTemperatureMaxTime = ((long) in.readValue((long.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum__() {
    }

    /**
     * 
     * @param temperatureMinTime
     * @param sunsetTime
     * @param summary
     * @param precipIntensityMaxTime
     * @param visibility
     * @param temperatureLowTime
     * @param temperatureHighTime
     * @param temperatureLow
     * @param precipIntensity
     * @param precipIntensityMax
     * @param ozone
     * @param time
     * @param apparentTemperatureMaxTime
     * @param uvIndex
     * @param apparentTemperatureHighTime
     * @param temperatureHigh
     * @param icon
     * @param windGust
     * @param apparentTemperatureLowTime
     * @param temperatureMaxTime
     * @param pressure
     * @param cloudCover
     * @param apparentTemperatureMinTime
     * @param temperatureMin
     * @param precipType
     * @param apparentTemperatureLow
     * @param dewPoint
     * @param sunriseTime
     * @param windSpeed
     * @param humidity
     * @param apparentTemperatureMax
     * @param windBearing
     * @param moonPhase
     * @param precipProbability
     * @param windGustTime
     * @param apparentTemperatureMin
     * @param uvIndexTime
     * @param temperatureMax
     * @param apparentTemperatureHigh
     */
    public Datum__(long time, String summary, String icon, long sunriseTime, long sunsetTime, double moonPhase, double precipIntensity, double precipIntensityMax, long precipIntensityMaxTime, double precipProbability, String precipType, double temperatureHigh, long temperatureHighTime, double temperatureLow, long temperatureLowTime, double apparentTemperatureHigh, long apparentTemperatureHighTime, double apparentTemperatureLow, long apparentTemperatureLowTime, double dewPoint, double humidity, double pressure, double windSpeed, double windGust, long windGustTime, long windBearing, double cloudCover, long uvIndex, long uvIndexTime, double visibility, double ozone, double temperatureMin, long temperatureMinTime, double temperatureMax, long temperatureMaxTime, double apparentTemperatureMin, long apparentTemperatureMinTime, double apparentTemperatureMax, long apparentTemperatureMaxTime) {
        super();
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.sunriseTime = sunriseTime;
        this.sunsetTime = sunsetTime;
        this.moonPhase = moonPhase;
        this.precipIntensity = precipIntensity;
        this.precipIntensityMax = precipIntensityMax;
        this.precipIntensityMaxTime = precipIntensityMaxTime;
        this.precipProbability = precipProbability;
        this.precipType = precipType;
        this.temperatureHigh = temperatureHigh;
        this.temperatureHighTime = temperatureHighTime;
        this.temperatureLow = temperatureLow;
        this.temperatureLowTime = temperatureLowTime;
        this.apparentTemperatureHigh = apparentTemperatureHigh;
        this.apparentTemperatureHighTime = apparentTemperatureHighTime;
        this.apparentTemperatureLow = apparentTemperatureLow;
        this.apparentTemperatureLowTime = apparentTemperatureLowTime;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
        this.windGustTime = windGustTime;
        this.windBearing = windBearing;
        this.cloudCover = cloudCover;
        this.uvIndex = uvIndex;
        this.uvIndexTime = uvIndexTime;
        this.visibility = visibility;
        this.ozone = ozone;
        this.temperatureMin = temperatureMin;
        this.temperatureMinTime = temperatureMinTime;
        this.temperatureMax = temperatureMax;
        this.temperatureMaxTime = temperatureMaxTime;
        this.apparentTemperatureMin = apparentTemperatureMin;
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
        this.apparentTemperatureMax = apparentTemperatureMax;
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
    }

    public double getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Datum__ withTime(long time) {
        this.time = time;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Datum__ withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Datum__ withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public double getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(long sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public Datum__ withSunriseTime(long sunriseTime) {
        this.sunriseTime = sunriseTime;
        return this;
    }

    public double getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(long sunsetTime) {
        this.sunsetTime = sunsetTime;
    }

    public Datum__ withSunsetTime(long sunsetTime) {
        this.sunsetTime = sunsetTime;
        return this;
    }

    public double getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(double moonPhase) {
        this.moonPhase = moonPhase;
    }

    public Datum__ withMoonPhase(double moonPhase) {
        this.moonPhase = moonPhase;
        return this;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(double precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public Datum__ withPrecipIntensity(double precipIntensity) {
        this.precipIntensity = precipIntensity;
        return this;
    }

    public double getPrecipIntensityMax() {
        return precipIntensityMax;
    }

    public void setPrecipIntensityMax(double precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
    }

    public Datum__ withPrecipIntensityMax(double precipIntensityMax) {
        this.precipIntensityMax = precipIntensityMax;
        return this;
    }

    public double getPrecipIntensityMaxTime() {
        return precipIntensityMaxTime;
    }

    public void setPrecipIntensityMaxTime(long precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
    }

    public Datum__ withPrecipIntensityMaxTime(long precipIntensityMaxTime) {
        this.precipIntensityMaxTime = precipIntensityMaxTime;
        return this;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(double precipProbability) {
        this.precipProbability = precipProbability;
    }

    public Datum__ withPrecipProbability(double precipProbability) {
        this.precipProbability = precipProbability;
        return this;
    }

    public String getPrecipType() {
        return precipType;
    }

    public void setPrecipType(String precipType) {
        this.precipType = precipType;
    }

    public Datum__ withPrecipType(String precipType) {
        this.precipType = precipType;
        return this;
    }

    public double getTemperatureHigh() {
        return temperatureHigh;
    }

    public void setTemperatureHigh(double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
    }

    public Datum__ withTemperatureHigh(double temperatureHigh) {
        this.temperatureHigh = temperatureHigh;
        return this;
    }

    public double getTemperatureHighTime() {
        return temperatureHighTime;
    }

    public void setTemperatureHighTime(long temperatureHighTime) {
        this.temperatureHighTime = temperatureHighTime;
    }

    public Datum__ withTemperatureHighTime(long temperatureHighTime) {
        this.temperatureHighTime = temperatureHighTime;
        return this;
    }

    public double getTemperatureLow() {
        return temperatureLow;
    }

    public void setTemperatureLow(double temperatureLow) {
        this.temperatureLow = temperatureLow;
    }

    public Datum__ withTemperatureLow(double temperatureLow) {
        this.temperatureLow = temperatureLow;
        return this;
    }

    public double getTemperatureLowTime() {
        return temperatureLowTime;
    }

    public void setTemperatureLowTime(long temperatureLowTime) {
        this.temperatureLowTime = temperatureLowTime;
    }

    public Datum__ withTemperatureLowTime(long temperatureLowTime) {
        this.temperatureLowTime = temperatureLowTime;
        return this;
    }

    public double getApparentTemperatureHigh() {
        return apparentTemperatureHigh;
    }

    public void setApparentTemperatureHigh(double apparentTemperatureHigh) {
        this.apparentTemperatureHigh = apparentTemperatureHigh;
    }

    public Datum__ withApparentTemperatureHigh(double apparentTemperatureHigh) {
        this.apparentTemperatureHigh = apparentTemperatureHigh;
        return this;
    }

    public double getApparentTemperatureHighTime() {
        return apparentTemperatureHighTime;
    }

    public void setApparentTemperatureHighTime(long apparentTemperatureHighTime) {
        this.apparentTemperatureHighTime = apparentTemperatureHighTime;
    }

    public Datum__ withApparentTemperatureHighTime(long apparentTemperatureHighTime) {
        this.apparentTemperatureHighTime = apparentTemperatureHighTime;
        return this;
    }

    public double getApparentTemperatureLow() {
        return apparentTemperatureLow;
    }

    public void setApparentTemperatureLow(double apparentTemperatureLow) {
        this.apparentTemperatureLow = apparentTemperatureLow;
    }

    public Datum__ withApparentTemperatureLow(double apparentTemperatureLow) {
        this.apparentTemperatureLow = apparentTemperatureLow;
        return this;
    }

    public double getApparentTemperatureLowTime() {
        return apparentTemperatureLowTime;
    }

    public void setApparentTemperatureLowTime(long apparentTemperatureLowTime) {
        this.apparentTemperatureLowTime = apparentTemperatureLowTime;
    }

    public Datum__ withApparentTemperatureLowTime(long apparentTemperatureLowTime) {
        this.apparentTemperatureLowTime = apparentTemperatureLowTime;
        return this;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Datum__ withDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
        return this;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public Datum__ withHumidity(double humidity) {
        this.humidity = humidity;
        return this;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public Datum__ withPressure(double pressure) {
        this.pressure = pressure;
        return this;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Datum__ withWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public double getWindGust() {
        return windGust;
    }

    public void setWindGust(double windGust) {
        this.windGust = windGust;
    }

    public Datum__ withWindGust(double windGust) {
        this.windGust = windGust;
        return this;
    }

    public double getWindGustTime() {
        return windGustTime;
    }

    public void setWindGustTime(long windGustTime) {
        this.windGustTime = windGustTime;
    }

    public Datum__ withWindGustTime(long windGustTime) {
        this.windGustTime = windGustTime;
        return this;
    }

    public double getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(long windBearing) {
        this.windBearing = windBearing;
    }

    public Datum__ withWindBearing(long windBearing) {
        this.windBearing = windBearing;
        return this;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Datum__ withCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
        return this;
    }

    public double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(long uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Datum__ withUvIndex(long uvIndex) {
        this.uvIndex = uvIndex;
        return this;
    }

    public double getUvIndexTime() {
        return uvIndexTime;
    }

    public void setUvIndexTime(long uvIndexTime) {
        this.uvIndexTime = uvIndexTime;
    }

    public Datum__ withUvIndexTime(long uvIndexTime) {
        this.uvIndexTime = uvIndexTime;
        return this;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public Datum__ withVisibility(double visibility) {
        this.visibility = visibility;
        return this;
    }

    public double getOzone() {
        return ozone;
    }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public Datum__ withOzone(double ozone) {
        this.ozone = ozone;
        return this;
    }

    public double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Datum__ withTemperatureMin(double temperatureMin) {
        this.temperatureMin = temperatureMin;
        return this;
    }

    public double getTemperatureMinTime() {
        return temperatureMinTime;
    }

    public void setTemperatureMinTime(long temperatureMinTime) {
        this.temperatureMinTime = temperatureMinTime;
    }

    public Datum__ withTemperatureMinTime(long temperatureMinTime) {
        this.temperatureMinTime = temperatureMinTime;
        return this;
    }

    public double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Datum__ withTemperatureMax(double temperatureMax) {
        this.temperatureMax = temperatureMax;
        return this;
    }

    public double getTemperatureMaxTime() {
        return temperatureMaxTime;
    }

    public void setTemperatureMaxTime(long temperatureMaxTime) {
        this.temperatureMaxTime = temperatureMaxTime;
    }

    public Datum__ withTemperatureMaxTime(long temperatureMaxTime) {
        this.temperatureMaxTime = temperatureMaxTime;
        return this;
    }

    public double getApparentTemperatureMin() {
        return apparentTemperatureMin;
    }

    public void setApparentTemperatureMin(double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
    }

    public Datum__ withApparentTemperatureMin(double apparentTemperatureMin) {
        this.apparentTemperatureMin = apparentTemperatureMin;
        return this;
    }

    public double getApparentTemperatureMinTime() {
        return apparentTemperatureMinTime;
    }

    public void setApparentTemperatureMinTime(long apparentTemperatureMinTime) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
    }

    public Datum__ withApparentTemperatureMinTime(long apparentTemperatureMinTime) {
        this.apparentTemperatureMinTime = apparentTemperatureMinTime;
        return this;
    }

    public double getApparentTemperatureMax() {
        return apparentTemperatureMax;
    }

    public void setApparentTemperatureMax(double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
    }

    public Datum__ withApparentTemperatureMax(double apparentTemperatureMax) {
        this.apparentTemperatureMax = apparentTemperatureMax;
        return this;
    }

    public double getApparentTemperatureMaxTime() {
        return apparentTemperatureMaxTime;
    }

    public void setApparentTemperatureMaxTime(long apparentTemperatureMaxTime) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
    }

    public Datum__ withApparentTemperatureMaxTime(long apparentTemperatureMaxTime) {
        this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("time", time).append("summary", summary).append("icon", icon).append("sunriseTime", sunriseTime).append("sunsetTime", sunsetTime).append("moonPhase", moonPhase).append("precipIntensity", precipIntensity).append("precipIntensityMax", precipIntensityMax).append("precipIntensityMaxTime", precipIntensityMaxTime).append("precipProbability", precipProbability).append("precipType", precipType).append("temperatureHigh", temperatureHigh).append("temperatureHighTime", temperatureHighTime).append("temperatureLow", temperatureLow).append("temperatureLowTime", temperatureLowTime).append("apparentTemperatureHigh", apparentTemperatureHigh).append("apparentTemperatureHighTime", apparentTemperatureHighTime).append("apparentTemperatureLow", apparentTemperatureLow).append("apparentTemperatureLowTime", apparentTemperatureLowTime).append("dewPoint", dewPoint).append("humidity", humidity).append("pressure", pressure).append("windSpeed", windSpeed).append("windGust", windGust).append("windGustTime", windGustTime).append("windBearing", windBearing).append("cloudCover", cloudCover).append("uvIndex", uvIndex).append("uvIndexTime", uvIndexTime).append("visibility", visibility).append("ozone", ozone).append("temperatureMin", temperatureMin).append("temperatureMinTime", temperatureMinTime).append("temperatureMax", temperatureMax).append("temperatureMaxTime", temperatureMaxTime).append("apparentTemperatureMin", apparentTemperatureMin).append("apparentTemperatureMinTime", apparentTemperatureMinTime).append("apparentTemperatureMax", apparentTemperatureMax).append("apparentTemperatureMaxTime", apparentTemperatureMaxTime).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(temperatureMinTime).append(sunsetTime).append(summary).append(precipIntensityMaxTime).append(visibility).append(temperatureLowTime).append(temperatureLow).append(temperatureHighTime).append(precipIntensity).append(precipIntensityMax).append(ozone).append(time).append(apparentTemperatureMaxTime).append(uvIndex).append(apparentTemperatureHighTime).append(temperatureHigh).append(windGust).append(icon).append(apparentTemperatureLowTime).append(temperatureMaxTime).append(pressure).append(cloudCover).append(apparentTemperatureMinTime).append(temperatureMin).append(precipType).append(apparentTemperatureLow).append(dewPoint).append(sunriseTime).append(windSpeed).append(humidity).append(apparentTemperatureMax).append(windBearing).append(moonPhase).append(precipProbability).append(windGustTime).append(apparentTemperatureMin).append(uvIndexTime).append(temperatureMax).append(apparentTemperatureHigh).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Datum__) == false) {
            return false;
        }
        Datum__ rhs = ((Datum__) other);
        return new EqualsBuilder().append(temperatureMinTime, rhs.temperatureMinTime).append(sunsetTime, rhs.sunsetTime).append(summary, rhs.summary).append(precipIntensityMaxTime, rhs.precipIntensityMaxTime).append(visibility, rhs.visibility).append(temperatureLowTime, rhs.temperatureLowTime).append(temperatureLow, rhs.temperatureLow).append(temperatureHighTime, rhs.temperatureHighTime).append(precipIntensity, rhs.precipIntensity).append(precipIntensityMax, rhs.precipIntensityMax).append(ozone, rhs.ozone).append(time, rhs.time).append(apparentTemperatureMaxTime, rhs.apparentTemperatureMaxTime).append(uvIndex, rhs.uvIndex).append(apparentTemperatureHighTime, rhs.apparentTemperatureHighTime).append(temperatureHigh, rhs.temperatureHigh).append(windGust, rhs.windGust).append(icon, rhs.icon).append(apparentTemperatureLowTime, rhs.apparentTemperatureLowTime).append(temperatureMaxTime, rhs.temperatureMaxTime).append(pressure, rhs.pressure).append(cloudCover, rhs.cloudCover).append(apparentTemperatureMinTime, rhs.apparentTemperatureMinTime).append(temperatureMin, rhs.temperatureMin).append(precipType, rhs.precipType).append(apparentTemperatureLow, rhs.apparentTemperatureLow).append(dewPoint, rhs.dewPoint).append(sunriseTime, rhs.sunriseTime).append(windSpeed, rhs.windSpeed).append(humidity, rhs.humidity).append(apparentTemperatureMax, rhs.apparentTemperatureMax).append(windBearing, rhs.windBearing).append(moonPhase, rhs.moonPhase).append(precipProbability, rhs.precipProbability).append(windGustTime, rhs.windGustTime).append(apparentTemperatureMin, rhs.apparentTemperatureMin).append(uvIndexTime, rhs.uvIndexTime).append(temperatureMax, rhs.temperatureMax).append(apparentTemperatureHigh, rhs.apparentTemperatureHigh).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(time);
        dest.writeValue(summary);
        dest.writeValue(icon);
        dest.writeValue(sunriseTime);
        dest.writeValue(sunsetTime);
        dest.writeValue(moonPhase);
        dest.writeValue(precipIntensity);
        dest.writeValue(precipIntensityMax);
        dest.writeValue(precipIntensityMaxTime);
        dest.writeValue(precipProbability);
        dest.writeValue(precipType);
        dest.writeValue(temperatureHigh);
        dest.writeValue(temperatureHighTime);
        dest.writeValue(temperatureLow);
        dest.writeValue(temperatureLowTime);
        dest.writeValue(apparentTemperatureHigh);
        dest.writeValue(apparentTemperatureHighTime);
        dest.writeValue(apparentTemperatureLow);
        dest.writeValue(apparentTemperatureLowTime);
        dest.writeValue(dewPoint);
        dest.writeValue(humidity);
        dest.writeValue(pressure);
        dest.writeValue(windSpeed);
        dest.writeValue(windGust);
        dest.writeValue(windGustTime);
        dest.writeValue(windBearing);
        dest.writeValue(cloudCover);
        dest.writeValue(uvIndex);
        dest.writeValue(uvIndexTime);
        dest.writeValue(visibility);
        dest.writeValue(ozone);
        dest.writeValue(temperatureMin);
        dest.writeValue(temperatureMinTime);
        dest.writeValue(temperatureMax);
        dest.writeValue(temperatureMaxTime);
        dest.writeValue(apparentTemperatureMin);
        dest.writeValue(apparentTemperatureMinTime);
        dest.writeValue(apparentTemperatureMax);
        dest.writeValue(apparentTemperatureMaxTime);
    }

    public int describeContents() {
        return  0;
    }

}
