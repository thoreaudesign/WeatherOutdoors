
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SwellPeriod implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<SwellPeriod> CREATOR = new Creator<SwellPeriod>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SwellPeriod createFromParcel(Parcel in) {
            return new SwellPeriod(in);
        }

        public SwellPeriod[] newArray(int size) {
            return (new SwellPeriod[size]);
        }

    }
    ;
    private final static long serialVersionUID = -768284643272790588L;

    protected SwellPeriod(Parcel in) {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public SwellPeriod() {
    }

    /**
     * 
     * @param source
     * @param value
     */
    public SwellPeriod(String source, double value) {
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

    public SwellPeriod withSource(String source) {
        this.source = source;
        return this;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public SwellPeriod withValue(double value) {
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
