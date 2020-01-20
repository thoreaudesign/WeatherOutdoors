
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class WaterTemperature implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<WaterTemperature> CREATOR = new Creator<WaterTemperature>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public WaterTemperature createFromParcel(Parcel in)
        {
            return new WaterTemperature(in);
        }

        public WaterTemperature[] newArray(int size)
        {
            return (new WaterTemperature[size]);
        }

    };
    private final static long serialVersionUID = 8504657843549508527L;

    protected WaterTemperature(Parcel in)
    {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public WaterTemperature()
    {
    }

    /**
     * @param source
     * @param value
     */
    public WaterTemperature(String source, double value)
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

    public WaterTemperature withSource(String source)
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

    public WaterTemperature withValue(double value)
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
