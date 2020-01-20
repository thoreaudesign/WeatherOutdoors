
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Visibility implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<Visibility> CREATOR = new Creator<Visibility>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public Visibility createFromParcel(Parcel in)
        {
            return new Visibility(in);
        }

        public Visibility[] newArray(int size)
        {
            return (new Visibility[size]);
        }

    };
    private final static long serialVersionUID = 7938511303433979228L;

    protected Visibility(Parcel in)
    {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((double) in.readValue((double.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Visibility()
    {
    }

    /**
     * @param source
     * @param value
     */
    public Visibility(String source, double value)
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

    public Visibility withSource(String source)
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

    public Visibility withValue(double value)
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
