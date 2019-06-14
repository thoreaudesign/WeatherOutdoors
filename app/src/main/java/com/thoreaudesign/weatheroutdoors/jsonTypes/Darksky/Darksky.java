
package com.thoreaudesign.weatheroutdoors.jsonTypes.Darksky;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Darksky implements Serializable, Parcelable
{

    @SerializedName("latitude")
    @Expose
    private double latitude;
    @SerializedName("longitude")
    @Expose
    private double longitude;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("currently")
    @Expose

    private Currently currently;
    @SerializedName("minutely")
    @Expose

    private Minutely minutely;
    @SerializedName("hourly")
    @Expose

    private Hourly hourly;
    @SerializedName("daily")
    @Expose

    private Daily daily;
    @SerializedName("flags")
    @Expose

    private Flags flags;
    @SerializedName("offset")
    @Expose
    private long offset;
    public final static Parcelable.Creator<Darksky> CREATOR = new Creator<Darksky>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Darksky createFromParcel(Parcel in) {
            return new Darksky(in);
        }

        public Darksky[] newArray(int size) {
            return (new Darksky[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1165632754801542554L;

    protected Darksky(Parcel in) {
        this.latitude = ((double) in.readValue((double.class.getClassLoader())));
        this.longitude = ((double) in.readValue((double.class.getClassLoader())));
        this.timezone = ((String) in.readValue((String.class.getClassLoader())));
        this.currently = ((Currently) in.readValue((Currently.class.getClassLoader())));
        this.minutely = ((Minutely) in.readValue((Minutely.class.getClassLoader())));
        this.hourly = ((Hourly) in.readValue((Hourly.class.getClassLoader())));
        this.daily = ((Daily) in.readValue((Daily.class.getClassLoader())));
        this.flags = ((Flags) in.readValue((Flags.class.getClassLoader())));
        this.offset = ((long) in.readValue((long.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Darksky() {
    }

    /**
     * 
     * @param timezone
     * @param flags
     * @param currently
     * @param longitude
     * @param latitude
     * @param offset
     * @param hourly
     * @param daily
     * @param minutely
     */
    public Darksky(double latitude, double longitude, String timezone, Currently currently, Minutely minutely, Hourly hourly, Daily daily, Flags flags, long offset) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.timezone = timezone;
        this.currently = currently;
        this.minutely = minutely;
        this.hourly = hourly;
        this.daily = daily;
        this.flags = flags;
        this.offset = offset;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Darksky withLatitude(double latitude) {
        this.latitude = latitude;
        return this;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public Darksky withLongitude(double longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Darksky withTimezone(String timezone) {
        this.timezone = timezone;
        return this;
    }

    public Currently getCurrently() {
        return currently;
    }

    public void setCurrently(Currently currently) {
        this.currently = currently;
    }

    public Darksky withCurrently(Currently currently) {
        this.currently = currently;
        return this;
    }

    public Minutely getMinutely() {
        return minutely;
    }

    public void setMinutely(Minutely minutely) {
        this.minutely = minutely;
    }

    public Darksky withMinutely(Minutely minutely) {
        this.minutely = minutely;
        return this;
    }

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public Darksky withHourly(Hourly hourly) {
        this.hourly = hourly;
        return this;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public Darksky withDaily(Daily daily) {
        this.daily = daily;
        return this;
    }

    public Flags getFlags() {
        return flags;
    }

    public void setFlags(Flags flags) {
        this.flags = flags;
    }

    public Darksky withFlags(Flags flags) {
        this.flags = flags;
        return this;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public Darksky withOffset(long offset) {
        this.offset = offset;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("latitude", latitude).append("longitude", longitude).append("timezone", timezone).append("currently", currently).append("minutely", minutely).append("hourly", hourly).append("daily", daily).append("flags", flags).append("offset", offset).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(timezone).append(flags).append(currently).append(longitude).append(latitude).append(offset).append(hourly).append(daily).append(minutely).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Darksky) == false) {
            return false;
        }
        Darksky rhs = ((Darksky) other);
        return new EqualsBuilder().append(timezone, rhs.timezone).append(flags, rhs.flags).append(currently, rhs.currently).append(longitude, rhs.longitude).append(latitude, rhs.latitude).append(offset, rhs.offset).append(hourly, rhs.hourly).append(daily, rhs.daily).append(minutely, rhs.minutely).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(latitude);
        dest.writeValue(longitude);
        dest.writeValue(timezone);
        dest.writeValue(currently);
        dest.writeValue(minutely);
        dest.writeValue(hourly);
        dest.writeValue(daily);
        dest.writeValue(flags);
        dest.writeValue(offset);
    }

    public int describeContents() {
        return  0;
    }

}
