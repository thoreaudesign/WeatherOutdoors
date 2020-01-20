
package com.thoreaudesign.weatheroutdoors.serialization.Stormglass;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Meta implements Serializable, Parcelable
{

    @SerializedName("cost")
    @Expose
    private double cost;
    @SerializedName("dailyQuota")
    @Expose
    private double dailyQuota;
    @SerializedName("end")
    @Expose
    private String end;
    @SerializedName("lat")
    @Expose
    private double lat;
    @SerializedName("lng")
    @Expose
    private double lng;
    @SerializedName("params")
    @Expose

    private List<String> params = new ArrayList<String>();
    @SerializedName("requestCount")
    @Expose
    private double requestCount;
    @SerializedName("source")
    @Expose
    private String source;
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
    private final static long serialVersionUID = -531765890621137310L;

    protected Meta(Parcel in)
    {
        this.cost = ((long) in.readValue((long.class.getClassLoader())));
        this.dailyQuota = ((long) in.readValue((long.class.getClassLoader())));
        this.end = ((String) in.readValue((String.class.getClassLoader())));
        this.lat = ((double) in.readValue((double.class.getClassLoader())));
        this.lng = ((double) in.readValue((double.class.getClassLoader())));
        in.readList(this.params, (String.class.getClassLoader()));
        this.requestCount = ((long) in.readValue((long.class.getClassLoader())));
        this.source = ((String) in.readValue((String.class.getClassLoader())));
        this.start = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     */
    public Meta()
    {
    }

    /**
     * @param source
     * @param dailyQuota
     * @param start
     * @param lng
     * @param requestCount
     * @param params
     * @param cost
     * @param lat
     * @param end
     */
    public Meta(long cost, long dailyQuota, String end, double lat, double lng, List<String> params, long requestCount, String source, String start)
    {
        super();
        this.cost = cost;
        this.dailyQuota = dailyQuota;
        this.end = end;
        this.lat = lat;
        this.lng = lng;
        this.params = params;
        this.requestCount = requestCount;
        this.source = source;
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

    public String getEnd()
    {
        return end;
    }

    public void setEnd(String end)
    {
        this.end = end;
    }

    public Meta withEnd(String end)
    {
        this.end = end;
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

    public List<String> getParams()
    {
        return params;
    }

    public void setParams(List<String> params)
    {
        this.params = params;
    }

    public Meta withParams(List<String> params)
    {
        this.params = params;
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

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public Meta withSource(String source)
    {
        this.source = source;
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
        dest.writeValue(end);
        dest.writeValue(lat);
        dest.writeValue(lng);
        dest.writeList(params);
        dest.writeValue(requestCount);
        dest.writeValue(source);
        dest.writeValue(start);
    }

    public int describeContents()
    {
        return 0;
    }

}
