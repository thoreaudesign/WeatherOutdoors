
package com.thoreaudesign.weatheroutdoors.serialization.Darksky;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Datum_ implements Serializable, Parcelable
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
    @SerializedName("precipIntensity")
    @Expose
    private double precipIntensity;
    @SerializedName("precipProbability")
    @Expose
    private double precipProbability;
    @SerializedName("temperature")
    @Expose
    private double temperature;
    @SerializedName("apparentTemperature")
    @Expose
    private double apparentTemperature;
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
    @SerializedName("windBearing")
    @Expose
    private double windBearing;
    @SerializedName("cloudCover")
    @Expose
    private double cloudCover;
    @SerializedName("uvIndex")
    @Expose
    private double uvIndex;
    @SerializedName("visibility")
    @Expose
    private double visibility;
    @SerializedName("ozone")
    @Expose
    private double ozone;
    public final static Parcelable.Creator<Datum_> CREATOR = new Creator<Datum_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum_ createFromParcel(Parcel in) {
            return new Datum_(in);
        }

        public Datum_[] newArray(int size) {
            return (new Datum_[size]);
        }

    }
    ;
    private final static long serialVersionUID = -4476947800460489619L;

    protected Datum_(Parcel in) {
        this.time = ((long) in.readValue((long.class.getClassLoader())));
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        this.precipIntensity = ((long) in.readValue((long.class.getClassLoader())));
        this.precipProbability = ((long) in.readValue((long.class.getClassLoader())));
        this.temperature = ((double) in.readValue((double.class.getClassLoader())));
        this.apparentTemperature = ((double) in.readValue((double.class.getClassLoader())));
        this.dewPoint = ((double) in.readValue((double.class.getClassLoader())));
        this.humidity = ((double) in.readValue((double.class.getClassLoader())));
        this.pressure = ((double) in.readValue((double.class.getClassLoader())));
        this.windSpeed = ((double) in.readValue((double.class.getClassLoader())));
        this.windGust = ((double) in.readValue((double.class.getClassLoader())));
        this.windBearing = ((long) in.readValue((long.class.getClassLoader())));
        this.cloudCover = ((double) in.readValue((double.class.getClassLoader())));
        this.uvIndex = ((long) in.readValue((long.class.getClassLoader())));
        this.visibility = ((double) in.readValue((double.class.getClassLoader())));
        this.ozone = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum_() {
    }

    /**
     * 
     * @param summary
     * @param icon
     * @param windGust
     * @param pressure
     * @param visibility
     * @param cloudCover
     * @param apparentTemperature
     * @param precipIntensity
     * @param temperature
     * @param dewPoint
     * @param ozone
     * @param time
     * @param windSpeed
     * @param humidity
     * @param windBearing
     * @param uvIndex
     * @param precipProbability
     */
    public Datum_(long time, String summary, String icon, long precipIntensity, long precipProbability, double temperature, double apparentTemperature, double dewPoint, double humidity, double pressure, double windSpeed, double windGust, long windBearing, double cloudCover, long uvIndex, double visibility, double ozone) {
        super();
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.precipIntensity = precipIntensity;
        this.precipProbability = precipProbability;
        this.temperature = temperature;
        this.apparentTemperature = apparentTemperature;
        this.dewPoint = dewPoint;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
        this.windBearing = windBearing;
        this.cloudCover = cloudCover;
        this.uvIndex = uvIndex;
        this.visibility = visibility;
        this.ozone = ozone;
    }

    public double getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Datum_ withTime(long time) {
        this.time = time;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Datum_ withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Datum_ withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(long precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public Datum_ withPrecipIntensity(long precipIntensity) {
        this.precipIntensity = precipIntensity;
        return this;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(long precipProbability) {
        this.precipProbability = precipProbability;
    }

    public Datum_ withPrecipProbability(long precipProbability) {
        this.precipProbability = precipProbability;
        return this;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Datum_ withTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public Datum_ withApparentTemperature(double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
        return this;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Datum_ withDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
        return this;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public Datum_ withHumidity(double humidity) {
        this.humidity = humidity;
        return this;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public Datum_ withPressure(double pressure) {
        this.pressure = pressure;
        return this;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Datum_ withWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public double getWindGust() {
        return windGust;
    }

    public void setWindGust(double windGust) {
        this.windGust = windGust;
    }

    public Datum_ withWindGust(double windGust) {
        this.windGust = windGust;
        return this;
    }

    public double getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(long windBearing) {
        this.windBearing = windBearing;
    }

    public Datum_ withWindBearing(long windBearing) {
        this.windBearing = windBearing;
        return this;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Datum_ withCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
        return this;
    }

    public double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(long uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Datum_ withUvIndex(long uvIndex) {
        this.uvIndex = uvIndex;
        return this;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public Datum_ withVisibility(double visibility) {
        this.visibility = visibility;
        return this;
    }

    public double getOzone() {
        return ozone;
    }

    public void setOzone(double ozone) {
        this.ozone = ozone;
    }

    public Datum_ withOzone(double ozone) {
        this.ozone = ozone;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("time", time).append("summary", summary).append("icon", icon).append("precipIntensity", precipIntensity).append("precipProbability", precipProbability).append("temperature", temperature).append("apparentTemperature", apparentTemperature).append("dewPoint", dewPoint).append("humidity", humidity).append("pressure", pressure).append("windSpeed", windSpeed).append("windGust", windGust).append("windBearing", windBearing).append("cloudCover", cloudCover).append("uvIndex", uvIndex).append("visibility", visibility).append("ozone", ozone).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(summary).append(windGust).append(icon).append(pressure).append(visibility).append(cloudCover).append(apparentTemperature).append(precipIntensity).append(dewPoint).append(temperature).append(ozone).append(windSpeed).append(time).append(humidity).append(windBearing).append(uvIndex).append(precipProbability).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Datum_) == false) {
            return false;
        }
        Datum_ rhs = ((Datum_) other);
        return new EqualsBuilder().append(summary, rhs.summary).append(windGust, rhs.windGust).append(icon, rhs.icon).append(pressure, rhs.pressure).append(visibility, rhs.visibility).append(cloudCover, rhs.cloudCover).append(apparentTemperature, rhs.apparentTemperature).append(precipIntensity, rhs.precipIntensity).append(dewPoint, rhs.dewPoint).append(temperature, rhs.temperature).append(ozone, rhs.ozone).append(windSpeed, rhs.windSpeed).append(time, rhs.time).append(humidity, rhs.humidity).append(windBearing, rhs.windBearing).append(uvIndex, rhs.uvIndex).append(precipProbability, rhs.precipProbability).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(time);
        dest.writeValue(summary);
        dest.writeValue(icon);
        dest.writeValue(precipIntensity);
        dest.writeValue(precipProbability);
        dest.writeValue(temperature);
        dest.writeValue(apparentTemperature);
        dest.writeValue(dewPoint);
        dest.writeValue(humidity);
        dest.writeValue(pressure);
        dest.writeValue(windSpeed);
        dest.writeValue(windGust);
        dest.writeValue(windBearing);
        dest.writeValue(cloudCover);
        dest.writeValue(uvIndex);
        dest.writeValue(visibility);
        dest.writeValue(ozone);
    }

    public int describeContents() {
        return  0;
    }

}
