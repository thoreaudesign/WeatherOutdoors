
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stormglass implements Serializable, Parcelable
{

    @SerializedName("hours")
    @Expose

    private List<Hour> hours = new ArrayList<Hour>();
    @SerializedName("meta")
    @Expose

    private Meta meta;
    public final static Creator<Stormglass> CREATOR = new Creator<Stormglass>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public Stormglass createFromParcel(Parcel in)
        {
            return new Stormglass(in);
        }

        public Stormglass[] newArray(int size)
        {
            return (new Stormglass[size]);
        }

    };
    private final static long serialVersionUID = -5313040634380428552L;

    protected Stormglass(Parcel in)
    {
        in.readList(this.hours, (Hour.class.getClassLoader()));
        this.meta = ((Meta) in.readValue((Meta.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Stormglass()
    {
    }

    /**
     * @param hours
     * @param meta
     */
    public Stormglass(List<Hour> hours, Meta meta)
    {
        super();
        this.hours = hours;
        this.meta = meta;
    }

    public List<Hour> getHours()
    {
        return hours;
    }

    public void setHours(List<Hour> hours)
    {
        this.hours = hours;
    }

    public Stormglass withHours(List<Hour> hours)
    {
        this.hours = hours;
        return this;
    }

    public Meta getMeta()
    {
        return meta;
    }

    public void setMeta(Meta meta)
    {
        this.meta = meta;
    }

    public Stormglass withMeta(Meta meta)
    {
        this.meta = meta;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeList(hours);
        dest.writeValue(meta);
    }

    public int describeContents()
    {
        return 0;
    }

}
