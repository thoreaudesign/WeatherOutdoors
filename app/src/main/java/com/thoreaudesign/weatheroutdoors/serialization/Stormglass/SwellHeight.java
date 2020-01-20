
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SwellHeight implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<SwellHeight> CREATOR = new Creator<SwellHeight>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public SwellHeight createFromParcel(Parcel in)
        {
            return new SwellHeight(in);
        }

        public SwellHeight[] newArray(int size)
        {
            return (new SwellHeight[size]);
        }

    };
    private final static long serialVersionUID = 1708276802201244524L;

    protected SwellHeight(Parcel in)
    {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public SwellHeight()
    {
    }

    /**
     * @param source
     * @param value
     */
    public SwellHeight(String source, double value)
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

    public SwellHeight withSource(String source)
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

    public SwellHeight withValue(double value)
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
