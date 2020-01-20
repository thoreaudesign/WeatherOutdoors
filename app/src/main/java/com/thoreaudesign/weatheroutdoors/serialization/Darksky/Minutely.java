
package com.thoreaudesign.weatheroutdoors.serialization.Darksky;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Minutely implements Serializable, Parcelable
{

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("data")
    @Expose

    private List<Datum> data = new ArrayList<Datum>();
    public final static Parcelable.Creator<Minutely> CREATOR = new Creator<Minutely>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public Minutely createFromParcel(Parcel in)
        {
            return new Minutely(in);
        }

        public Minutely[] newArray(int size)
        {
            return (new Minutely[size]);
        }

    };
    private final static long serialVersionUID = 5228647071443351707L;

    protected Minutely(Parcel in)
    {
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (com.thoreaudesign.weatheroutdoors.serialization.Darksky.Datum.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public Minutely()
    {
    }

    /**
     * @param summary
     * @param icon
     * @param data
     */
    public Minutely(String summary, String icon, List<Datum> data)
    {
        super();
        this.summary = summary;
        this.icon = icon;
        this.data = data;
    }

    public String getSummary()
    {
        return summary;
    }

    public void setSummary(String summary)
    {
        this.summary = summary;
    }

    public Minutely withSummary(String summary)
    {
        this.summary = summary;
        return this;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public Minutely withIcon(String icon)
    {
        this.icon = icon;
        return this;
    }

    public List<Datum> getData()
    {
        return data;
    }

    public void setData(List<Datum> data)
    {
        this.data = data;
    }

    public Minutely withData(List<Datum> data)
    {
        this.data = data;
        return this;
    }

    @Override
    public String toString()
    {
        return new ToStringBuilder(this).append("summary", summary).append("icon", icon).append("data", data).toString();
    }

    @Override
    public int hashCode()
    {
        return new HashCodeBuilder().append(summary).append(icon).append(data).toHashCode();
    }

    @Override
    public boolean equals(Object other)
    {
        if (other == this)
        {
            return true;
        }
        if ((other instanceof Minutely) == false)
        {
            return false;
        }
        Minutely rhs = ((Minutely) other);
        return new EqualsBuilder().append(summary, rhs.summary).append(icon, rhs.icon).append(data, rhs.data).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeValue(summary);
        dest.writeValue(icon);
        dest.writeList(data);
    }

    public int describeContents()
    {
        return 0;
    }

}
