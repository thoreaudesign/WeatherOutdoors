
package com.thoreaudesign.weatheroutdoors.serialization.StormglassAstro;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Meta implements Serializable, Parcelable
{

    @SerializedName("cost")
    @Expose
    private double cost;
    @SerializedName("dailyQuota")
    @Expose
    private double dailyQuota;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("requestCount")
    @Expose
    private double requestCount;
    @SerializedName("start")
    @Expose
    private String start;
    public final static Creator<Meta> CREATOR = new Creator<Meta>()
    {


        @SuppressWarnings({
                "unchecked"
        })
        public Meta createFromParcel(Parcel in)
        {
            return new Meta(in);
        }

        public Meta[] newArray(int size)
        {
            return (new Meta[size]);
        }

    };
    private final static long serialVersionUID = -4251183936128456405L;

    protected Meta(Parcel in)
    {
        this.cost = ((long) in.readValue((long.class.getClassLoader())));
        this.dailyQuota = ((long) in.readValue((long.class.getClassLoader())));
        this.lat = ((double) in.readValue((double.class.getClassLoader())));
        this.lng = ((double) in.readValue((double.class.getClassLoader())));
        this.requestCount = ((long) in.readValue((long.class.getClassLoader())));
        this.start = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Meta()
    {
    }

    /**
     * @param dailyQuota
     * @param start
     * @param lng
     * @param requestCount
     * @param cost
     * @param lat
     */
    public Meta(long cost, long dailyQuota, double lat, double lng, long requestCount, String start)
    {
        super();
        this.cost = cost;
        this.dailyQuota = dailyQuota;
        this.lat = lat;
        this.lng = lng;
        this.requestCount = requestCount;
        this.start = start;
    }

    public double getCost()
    {
        return cost;
    }

    public void setCost(long cost)
    {
        this.cost = cost;
    }

    public Meta withCost(long cost)
    {
        this.cost = cost;
        return this;
    }

    public double getDailyQuota()
    {
        return dailyQuota;
    }

    public void setDailyQuota(long dailyQuota)
    {
        this.dailyQuota = dailyQuota;
    }

    public Meta withDailyQuota(long dailyQuota)
    {
        this.dailyQuota = dailyQuota;
        return this;
    }

    public double getLat()
    {
        return lat;
    }

    public void setLat(double lat)
    {
        this.lat = lat;
    }

    public Meta withLat(double lat)
    {
        this.lat = lat;
        return this;
    }

    public double getLng()
    {
        return lng;
    }

    public void setLng(double lng)
    {
        this.lng = lng;
    }

    public Meta withLng(double lng)
    {
        this.lng = lng;
        return this;
    }

    public double getRequestCount()
    {
        return requestCount;
    }

    public void setRequestCount(long requestCount)
    {
        this.requestCount = requestCount;
    }

    public Meta withRequestCount(long requestCount)
    {
        this.requestCount = requestCount;
        return this;
    }

    public String getStart()
    {
        return start;
    }

    public void setStart(String start)
    {
        this.start = start;
    }

    public Meta withStart(String start)
    {
        this.start = start;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeValue(cost);
        dest.writeValue(dailyQuota);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeValue(requestCount);
        dest.writeValue(start);
    }

    public int describeContents()
    {
        return 0;
    }

}
