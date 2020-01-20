
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SeaLevel implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<SeaLevel> CREATOR = new Creator<SeaLevel>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public SeaLevel createFromParcel(Parcel in)
        {
            return new SeaLevel(in);
        }

        public SeaLevel[] newArray(int size)
        {
            return (new SeaLevel[size]);
        }

    };
    private final static long serialVersionUID = 3346828443957783535L;

    protected SeaLevel(Parcel in)
    {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public SeaLevel()
    {
    }

    /**
     * @param source
     * @param value
     */
    public SeaLevel(String source, double value)
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

    public SeaLevel withSource(String source)
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

    public SeaLevel withValue(double value)
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
