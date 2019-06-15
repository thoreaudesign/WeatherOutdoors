
package com.thoreaudesign.weatheroutdoors.serialization.Darksky;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Currently implements Serializable, Parcelable
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
    @SerializedName("nearestStormDistance")
    @Expose
    private double nearestStormDistance;
    @SerializedName("nearestStormBearing")
    @Expose
    private double nearestStormBearing;
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
    public final static Parcelable.Creator<Currently> CREATOR = new Creator<Currently>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Currently createFromParcel(Parcel in) {
            return new Currently(in);
        }

        public Currently[] newArray(int size) {
            return (new Currently[size]);
        }

    }
    ;
    private final static long serialVersionUID = -2598816022788777640L;

    protected Currently(Parcel in) {
        this.time = ((long) in.readValue((long.class.getClassLoader())));
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        this.nearestStormDistance = ((long) in.readValue((long.class.getClassLoader())));
        this.nearestStormBearing = ((long) in.readValue((long.class.getClassLoader())));
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
        this.ozone = ((long) in.readValue((long.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Currently() {
    }

    /**
     * 
     * @param summary
     * @param windGust
     * @param icon
     * @param nearestStormBearing
     * @param pressure
     * @param visibility
     * @param cloudCover
     * @param apparentTemperature
     * @param precipIntensity
     * @param temperature
     * @param dewPoint
     * @param ozone
     * @param windSpeed
     * @param time
     * @param humidity
     * @param windBearing
     * @param nearestStormDistance
     * @param uvIndex
     * @param precipProbability
     */
    public Currently(long time, String summary, String icon, long nearestStormDistance, long nearestStormBearing, long precipIntensity, long precipProbability, double temperature, double apparentTemperature, double dewPoint, double humidity, double pressure, double windSpeed, double windGust, long windBearing, double cloudCover, long uvIndex, double visibility, long ozone) {
        super();
        this.time = time;
        this.summary = summary;
        this.icon = icon;
        this.nearestStormDistance = nearestStormDistance;
        this.nearestStormBearing = nearestStormBearing;
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

    public Currently withTime(long time) {
        this.time = time;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Currently withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Currently withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public double getNearestStormDistance() {
        return nearestStormDistance;
    }

    public void setNearestStormDistance(long nearestStormDistance) {
        this.nearestStormDistance = nearestStormDistance;
    }

    public Currently withNearestStormDistance(long nearestStormDistance) {
        this.nearestStormDistance = nearestStormDistance;
        return this;
    }

    public double getNearestStormBearing() {
        return nearestStormBearing;
    }

    public void setNearestStormBearing(long nearestStormBearing) {
        this.nearestStormBearing = nearestStormBearing;
    }

    public Currently withNearestStormBearing(long nearestStormBearing) {
        this.nearestStormBearing = nearestStormBearing;
        return this;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(long precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public Currently withPrecipIntensity(long precipIntensity) {
        this.precipIntensity = precipIntensity;
        return this;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(long precipProbability) {
        this.precipProbability = precipProbability;
    }

    public Currently withPrecipProbability(long precipProbability) {
        this.precipProbability = precipProbability;
        return this;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Currently withTemperature(double temperature) {
        this.temperature = temperature;
        return this;
    }

    public double getApparentTemperature() {
        return apparentTemperature;
    }

    public void setApparentTemperature(double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
    }

    public Currently withApparentTemperature(double apparentTemperature) {
        this.apparentTemperature = apparentTemperature;
        return this;
    }

    public double getDewPoint() {
        return dewPoint;
    }

    public void setDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
    }

    public Currently withDewPoint(double dewPoint) {
        this.dewPoint = dewPoint;
        return this;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public Currently withHumidity(double humidity) {
        this.humidity = humidity;
        return this;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public Currently withPressure(double pressure) {
        this.pressure = pressure;
        return this;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Currently withWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public double getWindGust() {
        return windGust;
    }

    public void setWindGust(double windGust) {
        this.windGust = windGust;
    }

    public Currently withWindGust(double windGust) {
        this.windGust = windGust;
        return this;
    }

    public double getWindBearing() {
        return windBearing;
    }

    public void setWindBearing(long windBearing) {
        this.windBearing = windBearing;
    }

    public Currently withWindBearing(long windBearing) {
        this.windBearing = windBearing;
        return this;
    }

    public double getCloudCover() {
        return cloudCover;
    }

    public void setCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
    }

    public Currently withCloudCover(double cloudCover) {
        this.cloudCover = cloudCover;
        return this;
    }

    public double getUvIndex() {
        return uvIndex;
    }

    public void setUvIndex(long uvIndex) {
        this.uvIndex = uvIndex;
    }

    public Currently withUvIndex(long uvIndex) {
        this.uvIndex = uvIndex;
        return this;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public Currently withVisibility(double visibility) {
        this.visibility = visibility;
        return this;
    }

    public double getOzone() {
        return ozone;
    }

    public void setOzone(long ozone) {
        this.ozone = ozone;
    }

    public Currently withOzone(long ozone) {
        this.ozone = ozone;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("time", time).append("summary", summary).append("icon", icon).append("nearestStormDistance", nearestStormDistance).append("nearestStormBearing", nearestStormBearing).append("precipIntensity", precipIntensity).append("precipProbability", precipProbability).append("temperature", temperature).append("apparentTemperature", apparentTemperature).append("dewPoint", dewPoint).append("humidity", humidity).append("pressure", pressure).append("windSpeed", windSpeed).append("windGust", windGust).append("windBearing", windBearing).append("cloudCover", cloudCover).append("uvIndex", uvIndex).append("visibility", visibility).append("ozone", ozone).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(summary).append(windGust).append(icon).append(pressure).append(nearestStormBearing).append(visibility).append(cloudCover).append(apparentTemperature).append(precipIntensity).append(dewPoint).append(temperature).append(ozone).append(windSpeed).append(time).append(humidity).append(windBearing).append(nearestStormDistance).append(uvIndex).append(precipProbability).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Currently) == false) {
            return false;
        }
        Currently rhs = ((Currently) other);
        return new EqualsBuilder().append(summary, rhs.summary).append(windGust, rhs.windGust).append(icon, rhs.icon).append(pressure, rhs.pressure).append(nearestStormBearing, rhs.nearestStormBearing).append(visibility, rhs.visibility).append(cloudCover, rhs.cloudCover).append(apparentTemperature, rhs.apparentTemperature).append(precipIntensity, rhs.precipIntensity).append(dewPoint, rhs.dewPoint).append(temperature, rhs.temperature).append(ozone, rhs.ozone).append(windSpeed, rhs.windSpeed).append(time, rhs.time).append(humidity, rhs.humidity).append(windBearing, rhs.windBearing).append(nearestStormDistance, rhs.nearestStormDistance).append(uvIndex, rhs.uvIndex).append(precipProbability, rhs.precipProbability).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(time);
        dest.writeValue(summary);
        dest.writeValue(icon);
        dest.writeValue(nearestStormDistance);
        dest.writeValue(nearestStormBearing);
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
