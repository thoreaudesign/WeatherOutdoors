
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IceCover implements Serializable, Parcelable
{

    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("value")
    @Expose
    private double value;
    public final static Creator<IceCover> CREATOR = new Creator<IceCover>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public IceCover createFromParcel(Parcel in)
        {
            return new IceCover(in);
        }

        public IceCover[] newArray(int size)
        {
            return (new IceCover[size]);
        }

    };
    private final static long serialVersionUID = -8243906163929916623L;

    protected IceCover(Parcel in)
    {
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.value = ((long) in.readValue((long.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public IceCover()
    {
    }

    /**
     * @param source
     * @param value
     */
    public IceCover(String source, long value)
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

    public IceCover withSource(String source)
    {
        this.source = source;
        return this;
    }

    public double getValue()
    {
        return value;
    }

    public void setValue(long value)
    {
        this.value = value;
    }

    public IceCover withValue(long value)
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
