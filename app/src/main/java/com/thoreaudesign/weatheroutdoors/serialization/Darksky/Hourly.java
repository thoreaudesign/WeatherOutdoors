
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

public class Hourly implements Serializable, Parcelable
{

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("data")
    @Expose

    private List<DatumHourly> data = new ArrayList<DatumHourly>();
    public final static Parcelable.Creator<Hourly> CREATOR = new Creator<Hourly>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public Hourly createFromParcel(Parcel in)
        {
            return new Hourly(in);
        }

        public Hourly[] newArray(int size)
        {
            return (new Hourly[size]);
        }

    };
    private final static long serialVersionUID = 1088714212528463360L;

    protected Hourly(Parcel in)
    {
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (DatumHourly.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     */
    public Hourly()
    {
    }

    /**
     * @param summary
     * @param icon
     * @param data
     */
    public Hourly(String summary, String icon, List<DatumHourly> data)
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

    public Hourly withSummary(String summary)
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

    public Hourly withIcon(String icon)
    {
        this.icon = icon;
        return this;
    }

    public List<DatumHourly> getData()
    {
        return data;
    }

    public void setData(List<DatumHourly> data)
    {
        this.data = data;
    }

    public Hourly withData(List<DatumHourly> data)
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
        if ((other instanceof Hourly) == false)
        {
            return false;
        }
        Hourly rhs = ((Hourly) other);
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
