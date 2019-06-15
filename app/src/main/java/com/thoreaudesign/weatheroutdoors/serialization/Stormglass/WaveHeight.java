
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WaveHeight implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<WaveHeight> CREATOR = new Creator<WaveHeight>() {


        @SuppressWarnings({
            "unchecked"
        })
        public WaveHeight createFromParcel(Parcel in) {
            return new WaveHeight(in);
        }

        public WaveHeight[] newArray(int size) {
            return (new WaveHeight[size]);
        }

    }
    ;
    private final static long serialVersionUID = 545137288642940779L;

    protected WaveHeight(Parcel in) {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public WaveHeight() {
    }

    /**
     * 
     * @param source
     * @param value
     */
    public WaveHeight(String source, double value) {
        super();
        this.source = source;
        this.value = value;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public WaveHeight withSource(String source) {
        this.source = source;
        return this;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public WaveHeight withValue(double value) {
        this.value = value;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(source);
        dest.writeValue(value);
    }

    public int describeContents() {
        return  0;
    }

}
