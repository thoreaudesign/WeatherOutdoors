
package com.thoreaudesign.weatheroutdoors.serialization.StormglassAstro;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Current implements Serializable, Parcelable
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
    public final static Creator<Current> CREATOR = new Creator<Current>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public Current createFromParcel(Parcel in)
        {
            return new Current(in);
        }

        public Current[] newArray(int size)
        {
            return (new Current[size]);
        }

    };
    private final static long serialVersionUID = -558107173778170073L;

    protected Current(Parcel in)
    {
        this.text = ((String) in.readValue((String.class.getClassLoader())));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Current()
    {
    }

    /**
     * @param time
     * @param text
     * @param value
     */
    public Current(String text, String time, double value)
    {
        super();
        this.text = text;
        this.time = time;
        this.value = value;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public Current withText(String text)
    {
        this.text = text;
        return this;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public Current withTime(String time)
    {
        this.time = time;
        return this;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(double value)
    {
        this.value = value;
    }

    public Current withValue(double value)
    {
        this.value = value;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeValue(text);
        dest.writeValue(time);
        dest.writeValue(value);
    }

    public int describeContents()
    {
        return 0;
    }

}
