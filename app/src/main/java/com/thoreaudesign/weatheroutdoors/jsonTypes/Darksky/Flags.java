
package com.thoreaudesign.weatheroutdoors.jsonTypes.Darksky;

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

public class Flags implements Serializable, Parcelable
{

    @SerializedName("sources")
    @Expose

    private List<String> sources = new ArrayList<String>();
    @SerializedName("nearest-station")
    @Expose
    private double nearestStation;
    @SerializedName("units")
    @Expose
    private String units;
    public final static Parcelable.Creator<Flags> CREATOR = new Creator<Flags>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Flags createFromParcel(Parcel in) {
            return new Flags(in);
        }

        public Flags[] newArray(int size) {
            return (new Flags[size]);
        }

    }
    ;
    private final static long serialVersionUID = 7403893317410228892L;

    protected Flags(Parcel in) {
        in.readList(this.sources, (java.lang.String.class.getClassLoader()));
        this.nearestStation = ((double) in.readValue((double.class.getClassLoader())));
        this.units = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Flags() {
    }

    /**
     * 
     * @param units
     * @param nearestStation
     * @param sources
     */
    public Flags(List<String> sources, double nearestStation, String units) {
        super();
        this.sources = sources;
        this.nearestStation = nearestStation;
        this.units = units;
    }

    public List<String> getSources() {
        return sources;
    }

    public void setSources(List<String> sources) {
        this.sources = sources;
    }

    public Flags withSources(List<String> sources) {
        this.sources = sources;
        return this;
    }

    public double getNearestStation() {
        return nearestStation;
    }

    public void setNearestStation(double nearestStation) {
        this.nearestStation = nearestStation;
    }

    public Flags withNearestStation(double nearestStation) {
        this.nearestStation = nearestStation;
        return this;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Flags withUnits(String units) {
        this.units = units;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("sources", sources).append("nearestStation", nearestStation).append("units", units).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(units).append(nearestStation).append(sources).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Flags) == false) {
            return false;
        }
        Flags rhs = ((Flags) other);
        return new EqualsBuilder().append(units, rhs.units).append(nearestStation, rhs.nearestStation).append(sources, rhs.sources).isEquals();
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(sources);
        dest.writeValue(nearestStation);
        dest.writeValue(units);
    }

    public int describeContents() {
        return  0;
    }

}
