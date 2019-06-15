
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WavePeriod implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<WavePeriod> CREATOR = new Creator<WavePeriod>() {


        @SuppressWarnings({
            "unchecked"
        })
        public WavePeriod createFromParcel(Parcel in) {
            return new WavePeriod(in);
        }

        public WavePeriod[] newArray(int size) {
            return (new WavePeriod[size]);
        }

    }
    ;
    private final static long serialVersionUID = 2913884179861227318L;

    protected WavePeriod(Parcel in) {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public WavePeriod() {
    }

    /**
     * 
     * @param source
     * @param value
     */
    public WavePeriod(String source, double value) {
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

    public WavePeriod withSource(String source) {
        this.source = source;
        return this;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public WavePeriod withValue(double value) {
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
