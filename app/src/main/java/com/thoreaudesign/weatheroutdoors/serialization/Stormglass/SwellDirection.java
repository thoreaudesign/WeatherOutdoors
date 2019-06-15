
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SwellDirection implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<SwellDirection> CREATOR = new Creator<SwellDirection>() {


        @SuppressWarnings({
            "unchecked"
        })
        public SwellDirection createFromParcel(Parcel in) {
            return new SwellDirection(in);
        }

        public SwellDirection[] newArray(int size) {
            return (new SwellDirection[size]);
        }

    }
    ;
    private final static long serialVersionUID = -3838692235684475415L;

    protected SwellDirection(Parcel in) {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public SwellDirection() {
    }

    /**
     * 
     * @param source
     * @param value
     */
    public SwellDirection(String source, double value) {
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

    public SwellDirection withSource(String source) {
        this.source = source;
        return this;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public SwellDirection withValue(double value) {
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
