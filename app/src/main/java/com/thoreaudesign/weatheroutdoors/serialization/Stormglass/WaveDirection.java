
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WaveDirection implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<WaveDirection> CREATOR = new Creator<WaveDirection>() {


        @SuppressWarnings({
            "unchecked"
        })
        public WaveDirection createFromParcel(Parcel in) {
            return new WaveDirection(in);
        }

        public WaveDirection[] newArray(int size) {
            return (new WaveDirection[size]);
        }

    }
    ;
    private final static long serialVersionUID = 1066352422859557835L;

    protected WaveDirection(Parcel in) {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public WaveDirection() {
    }

    /**
     * 
     * @param source
     * @param value
     */
    public WaveDirection(String source, double value) {
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

    public WaveDirection withSource(String source) {
        this.source = source;
        return this;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public WaveDirection withValue(double value) {
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
