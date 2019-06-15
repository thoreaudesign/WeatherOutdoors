
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WindWavePeriod implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<WindWavePeriod> CREATOR = new Creator<WindWavePeriod>() {


        @SuppressWarnings({
            "unchecked"
        })
        public WindWavePeriod createFromParcel(Parcel in) {
            return new WindWavePeriod(in);
        }

        public WindWavePeriod[] newArray(int size) {
            return (new WindWavePeriod[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8086372796245972842L;

    protected WindWavePeriod(Parcel in) {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public WindWavePeriod() {
    }

    /**
     * 
     * @param source
     * @param value
     */
    public WindWavePeriod(String source, double value) {
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

    public WindWavePeriod withSource(String source) {
        this.source = source;
        return this;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public WindWavePeriod withValue(double value) {
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
