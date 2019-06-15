
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

public class Daily implements Serializable, Parcelable
{

    @SerializedName("summary")
    @Expose
    private String summary;
    @SerializedName("icon")
    @Expose
    private String icon;
    @SerializedName("data")
    @Expose

    private List<Datum__> data = new ArrayList<Datum__>();
    public final static Parcelable.Creator<Daily> CREATOR = new Creator<Daily>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Daily createFromParcel(Parcel in) {
            return new Daily(in);
        }

        public Daily[] newArray(int size) {
            return (new Daily[size]);
        }

    }
    ;
    private final static long serialVersionUID = 8148669332741098316L;

    protected Daily(Parcel in) {
        this.summary = ((String) in.readValue((String.class.getClassLoader())));
        this.icon = ((String) in.readValue((String.class.getClassLoader())));
        in.readList(this.data, (com.thoreaudesign.weatheroutdoors.serialization.Darksky.Datum__.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Daily() {
    }

    /**
     * 
     * @param summary
     * @param icon
     * @param data
     */
    public Daily(String summary, String icon, List<Datum__> data) {
        super();
        this.summary = summary;
        this.icon = icon;
        this.data = data;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Daily withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Daily withIcon(String icon) {
        this.icon = icon;
        return this;
    }

    public List<Datum__> getData() {
        return data;
    }

    public void setData(List<Datum__> data) {
        this.data = data;
    }

    public Daily withData(List<Datum__> data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("summary", summary).append("icon", icon).append("data", data).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(summary).append(icon).append(data).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Daily) == false) {
            return false;
        }
        Daily rhs = ((Daily) other);
        return new EqualsBuilder().append(summary, rhs.summary).append(icon, rhs.icon).append(data, rhs.data).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(summary);
        dest.writeValue(icon);
        dest.writeList(data);
    }

    public int describeContents() {
        return  0;
    }

}
