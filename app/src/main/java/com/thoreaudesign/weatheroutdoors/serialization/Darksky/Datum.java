
package com.thoreaudesign.weatheroutdoors.serialization.Darksky;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class Datum implements Serializable, Parcelable
{

    @SerializedName("time")
    @Expose
    private double time;
    @SerializedName("precipIntensity")
    @Expose
    private double precipIntensity;
    @SerializedName("precipProbability")
    @Expose
    private double precipProbability;
    public final static Parcelable.Creator<Datum> CREATOR = new Creator<Datum>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        public Datum[] newArray(int size) {
            return (new Datum[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2654249221104049013L;

    protected Datum(Parcel in) {
        this.time = ((long) in.readValue((long.class.getClassLoader())));
        this.precipIntensity = ((long) in.readValue((long.class.getClassLoader())));
        this.precipProbability = ((long) in.readValue((long.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param time
     * @param precipProbability
     * @param precipIntensity
     */
    public Datum(long time, long precipIntensity, long precipProbability) {
        super();
        this.time = time;
        this.precipIntensity = precipIntensity;
        this.precipProbability = precipProbability;
    }

    public double getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Datum withTime(long time) {
        this.time = time;
        return this;
    }

    public double getPrecipIntensity() {
        return precipIntensity;
    }

    public void setPrecipIntensity(long precipIntensity) {
        this.precipIntensity = precipIntensity;
    }

    public Datum withPrecipIntensity(long precipIntensity) {
        this.precipIntensity = precipIntensity;
        return this;
    }

    public double getPrecipProbability() {
        return precipProbability;
    }

    public void setPrecipProbability(long precipProbability) {
        this.precipProbability = precipProbability;
    }

    public Datum withPrecipProbability(long precipProbability) {
        this.precipProbability = precipProbability;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("time", time).append("precipIntensity", precipIntensity).append("precipProbability", precipProbability).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(time).append(precipProbability).append(precipIntensity).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Datum) == false) {
            return false;
        }
        Datum rhs = ((Datum) other);
        return new EqualsBuilder().append(time, rhs.time).append(precipProbability, rhs.precipProbability).append(precipIntensity, rhs.precipIntensity).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(time);
        dest.writeValue(precipIntensity);
        dest.writeValue(precipProbability);
    }

    public int describeContents() {
        return  0;
    }

}
