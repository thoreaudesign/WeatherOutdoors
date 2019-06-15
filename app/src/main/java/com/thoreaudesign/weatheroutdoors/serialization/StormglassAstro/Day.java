
package com.thoreaudesign.weatheroutdoors.serialization.StormglassAstro;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Day implements Serializable, Parcelable
{

    @SerializedName("astronomicalDawn")
    @Expose
    private String astronomicalDawn;
    @SerializedName("astronomicalDusk")
    @Expose
    private String astronomicalDusk;
    @SerializedName("civilDawn")
    @Expose
    private String civilDawn;
    @SerializedName("civilDusk")
    @Expose
    private String civilDusk;
    @SerializedName("moonFraction")
    @Expose
    private double moonFraction;
    @SerializedName("moonPhase")
    @Expose

    private MoonPhase moonPhase;
    @SerializedName("moonrise")
    @Expose
    private String moonrise;
    @SerializedName("moonset")
    @Expose
    private String moonset;
    @SerializedName("nauticalDawn")
    @Expose
    private String nauticalDawn;
    @SerializedName("nauticalDusk")
    @Expose
    private String nauticalDusk;
    @SerializedName("sunrise")
    @Expose
    private String sunrise;
    @SerializedName("sunset")
    @Expose
    private String sunset;
    @SerializedName("time")
    @Expose
    private String time;
    public final static Creator<Day> CREATOR = new Creator<Day>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Day createFromParcel(Parcel in) {
            return new Day(in);
        }

        public Day[] newArray(int size) {
            return (new Day[size]);
        }

    }
    ;
    private final static long serialVersionUID = 5181719811195715000L;

    protected Day(Parcel in) {
        this.astronomicalDawn = ((String) in.readValue((String.class.getClassLoader())));
        this.astronomicalDusk = ((String) in.readValue((String.class.getClassLoader())));
        this.civilDawn = ((String) in.readValue((String.class.getClassLoader())));
        this.civilDusk = ((String) in.readValue((String.class.getClassLoader())));
        this.moonFraction = ((double) in.readValue((double.class.getClassLoader())));
        this.moonPhase = ((MoonPhase) in.readValue((MoonPhase.class.getClassLoader())));
        this.moonrise = ((String) in.readValue((String.class.getClassLoader())));
        this.moonset = ((String) in.readValue((String.class.getClassLoader())));
        this.nauticalDawn = ((String) in.readValue((String.class.getClassLoader())));
        this.nauticalDusk = ((String) in.readValue((String.class.getClassLoader())));
        this.sunrise = ((String) in.readValue((String.class.getClassLoader())));
        this.sunset = ((String) in.readValue((String.class.getClassLoader())));
        this.time = ((String) in.readValue((String.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     * 
     */
    public Day() {
    }

    /**
     * 
     * @param moonset
     * @param astronomicalDusk
     * @param astronomicalDawn
     * @param moonFraction
     * @param moonrise
     * @param civilDawn
     * @param time
     * @param nauticalDusk
     * @param moonPhase
     * @param nauticalDawn
     * @param sunset
     * @param civilDusk
     * @param sunrise
     */
    public Day(String astronomicalDawn, String astronomicalDusk, String civilDawn, String civilDusk, double moonFraction, MoonPhase moonPhase, String moonrise, String moonset, String nauticalDawn, String nauticalDusk, String sunrise, String sunset, String time) {
        super();
        this.astronomicalDawn = astronomicalDawn;
        this.astronomicalDusk = astronomicalDusk;
        this.civilDawn = civilDawn;
        this.civilDusk = civilDusk;
        this.moonFraction = moonFraction;
        this.moonPhase = moonPhase;
        this.moonrise = moonrise;
        this.moonset = moonset;
        this.nauticalDawn = nauticalDawn;
        this.nauticalDusk = nauticalDusk;
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.time = time;
    }

    public String getAstronomicalDawn() {
        return astronomicalDawn;
    }

    public void setAstronomicalDawn(String astronomicalDawn) {
        this.astronomicalDawn = astronomicalDawn;
    }

    public Day withAstronomicalDawn(String astronomicalDawn) {
        this.astronomicalDawn = astronomicalDawn;
        return this;
    }

    public String getAstronomicalDusk() {
        return astronomicalDusk;
    }

    public void setAstronomicalDusk(String astronomicalDusk) {
        this.astronomicalDusk = astronomicalDusk;
    }

    public Day withAstronomicalDusk(String astronomicalDusk) {
        this.astronomicalDusk = astronomicalDusk;
        return this;
    }

    public String getCivilDawn() {
        return civilDawn;
    }

    public void setCivilDawn(String civilDawn) {
        this.civilDawn = civilDawn;
    }

    public Day withCivilDawn(String civilDawn) {
        this.civilDawn = civilDawn;
        return this;
    }

    public String getCivilDusk() {
        return civilDusk;
    }

    public void setCivilDusk(String civilDusk) {
        this.civilDusk = civilDusk;
    }

    public Day withCivilDusk(String civilDusk) {
        this.civilDusk = civilDusk;
        return this;
    }

    public double getMoonFraction() {
        return moonFraction;
    }

    public void setMoonFraction(double moonFraction) {
        this.moonFraction = moonFraction;
    }

    public Day withMoonFraction(double moonFraction) {
        this.moonFraction = moonFraction;
        return this;
    }

    public MoonPhase getMoonPhase() {
        return moonPhase;
    }

    public void setMoonPhase(MoonPhase moonPhase) {
        this.moonPhase = moonPhase;
    }

    public Day withMoonPhase(MoonPhase moonPhase) {
        this.moonPhase = moonPhase;
        return this;
    }

    public String getMoonrise() {
        return moonrise;
    }

    public void setMoonrise(String moonrise) {
        this.moonrise = moonrise;
    }

    public Day withMoonrise(String moonrise) {
        this.moonrise = moonrise;
        return this;
    }

    public String getMoonset() {
        return moonset;
    }

    public void setMoonset(String moonset) {
        this.moonset = moonset;
    }

    public Day withMoonset(String moonset) {
        this.moonset = moonset;
        return this;
    }

    public String getNauticalDawn() {
        return nauticalDawn;
    }

    public void setNauticalDawn(String nauticalDawn) {
        this.nauticalDawn = nauticalDawn;
    }

    public Day withNauticalDawn(String nauticalDawn) {
        this.nauticalDawn = nauticalDawn;
        return this;
    }

    public String getNauticalDusk() {
        return nauticalDusk;
    }

    public void setNauticalDusk(String nauticalDusk) {
        this.nauticalDusk = nauticalDusk;
    }

    public Day withNauticalDusk(String nauticalDusk) {
        this.nauticalDusk = nauticalDusk;
        return this;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public Day withSunrise(String sunrise) {
        this.sunrise = sunrise;
        return this;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public Day withSunset(String sunset) {
        this.sunset = sunset;
        return this;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Day withTime(String time) {
        this.time = time;
        return this;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(astronomicalDawn);
        dest.writeValue(astronomicalDusk);
        dest.writeValue(civilDawn);
        dest.writeValue(civilDusk);
        dest.writeValue(moonFraction);
        dest.writeValue(moonPhase);
        dest.writeValue(moonrise);
        dest.writeValue(moonset);
        dest.writeValue(nauticalDawn);
        dest.writeValue(nauticalDusk);
        dest.writeValue(sunrise);
        dest.writeValue(sunset);
        dest.writeValue(time);
    }

    public int describeContents() {
        return  0;
    }

}
