
package com.thoreaudesign.weatheroutdoors.serialization.StormglassAstro;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Closest implements Serializable, Parcelable
{

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<Closest> CREATOR = new Creator<Closest>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Closest createFromParcel(Parcel in) {
            return new Closest(in);
        }

        public Closest[] newArray(int size) {
            return (new Closest[size]);
        }

    }
    ;
    private final static long serialVersionUID = 6362122781963025627L;

    protected Closest(Parcel in) {
        this.text = ((String) in.readValue((String.class.getClassLoader())));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Closest() {
    }

    /**
     * 
     * @param time
     * @param text
     * @param value
     */
    public Closest(String text, String time, double value) {
        super();
        this.text = text;
        this.time = time;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Closest withText(String text) {
        this.text = text;
        return this;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Closest withTime(String time) {
        this.time = time;
        return this;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Closest withValue(double value) {
        this.value = value;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(text);
        dest.writeValue(time);
        dest.writeValue(value);
    }

    public int describeContents() {
        return  0;
    }

}
