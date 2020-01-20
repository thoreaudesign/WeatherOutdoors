
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WindWaveHeight implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<WindWaveHeight> CREATOR = new Creator<WindWaveHeight>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public WindWaveHeight createFromParcel(Parcel in)
        {
            return new WindWaveHeight(in);
        }

        public WindWaveHeight[] newArray(int size)
        {
            return (new WindWaveHeight[size]);
        }

    };
    private final static long serialVersionUID = -3457519161248023373L;

    protected WindWaveHeight(Parcel in)
    {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public WindWaveHeight()
    {
    }

    /**
     * @param source
     * @param value
     */
    public WindWaveHeight(String source, double value)
    {
        super();
        this.source = source;
        this.value = value;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public WindWaveHeight withSource(String source)
    {
        this.source = source;
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

    public WindWaveHeight withValue(double value)
    {
        this.value = value;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeValue(source);
        dest.writeValue(value);
    }

    public int describeContents()
    {
        return 0;
    }

}
