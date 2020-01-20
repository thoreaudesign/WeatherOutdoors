
package com.thoreaudesign.weatheroutdoors.serialization.StormglassAstro;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MoonPhase implements Serializable, Parcelable
{

    @SerializedName("closest")
    @Expose

    private Closest closest;
    @SerializedName("current")
    @Expose

    private Current current;
    public final static Creator<MoonPhase> CREATOR = new Creator<MoonPhase>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public MoonPhase createFromParcel(Parcel in)
        {
            return new MoonPhase(in);
        }

        public MoonPhase[] newArray(int size)
        {
            return (new MoonPhase[size]);
        }

    };
    private final static long serialVersionUID = -682166310672365912L;

    protected MoonPhase(Parcel in)
    {
        this.closest = ((Closest) in.readValue((Closest.class.getClassLoader())));
        this.current = ((Current) in.readValue((Current.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public MoonPhase()
    {
    }

    /**
     * @param current
     * @param closest
     */
    public MoonPhase(Closest closest, Current current)
    {
        super();
        this.closest = closest;
        this.current = current;
    }

    public Closest getClosest()
    {
        return closest;
    }

    public void setClosest(Closest closest)
    {
        this.closest = closest;
    }

    public MoonPhase withClosest(Closest closest)
    {
        this.closest = closest;
        return this;
    }

    public Current getCurrent()
    {
        return current;
    }

    public void setCurrent(Current current)
    {
        this.current = current;
    }

    public MoonPhase withCurrent(Current current)
    {
        this.current = current;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeValue(closest);
        dest.writeValue(current);
    }

    public int describeContents()
    {
        return 0;
    }

}
