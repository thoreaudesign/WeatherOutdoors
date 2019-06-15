
package com.thoreaudesign.weatheroutdoors.serialization.StormglassAstro;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class StormglassAstro implements Serializable, Parcelable
{

    @SerializedName("days")
    @Expose

    private List<Day> days = new ArrayList<Day>();
    @SerializedName("meta")
    @Expose

    private Meta meta;
    public final static Creator<StormglassAstro> CREATOR = new Creator<StormglassAstro>() {


        @SuppressWarnings({
            "unchecked"
        })
        public StormglassAstro createFromParcel(Parcel in) {
            return new StormglassAstro(in);
        }

        public StormglassAstro[] newArray(int size) {
            return (new StormglassAstro[size]);
        }

    }
    ;
    private final static long serialVersionUID = -5102637044457432646L;

    protected StormglassAstro(Parcel in) {
        in.readList(this.days, (Day.class.getClassLoader()));
        this.meta = ((Meta) in.readValue((Meta.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public StormglassAstro() {
    }

    /**
     * 
     * @param days
     * @param meta
     */
    public StormglassAstro(List<Day> days, Meta meta) {
        super();
        this.days = days;
        this.meta = meta;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

    public StormglassAstro withDays(List<Day> days) {
        this.days = days;
        return this;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public StormglassAstro withMeta(Meta meta) {
        this.meta = meta;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(days);
        dest.writeValue(meta);
    }

    public int describeContents() {
        return  0;
    }

}
