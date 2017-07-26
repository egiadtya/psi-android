package imd.psiapp.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * data model for Pollutant Standards Index readings
 * Created by egiadtya on 7/24/17.
 */

public class PSIReadings implements Parcelable {
    @SerializedName("o3_sub_index")
    private PSIReadingsItem o3SubIndex;
    @SerializedName("pm10_twenty_four_hourly")
    private PSIReadingsItem pm10TwentyFourHourly;
    @SerializedName("pm10_sub_index")
    private PSIReadingsItem pm10SubIndex;
    @SerializedName("co_sub_index")
    private PSIReadingsItem coSubIndex;
    @SerializedName("pm25_twenty_four_hourly")
    private PSIReadingsItem pm25TwentyFourHourly;
    @SerializedName("so2_sub_index")
    private PSIReadingsItem so2SubIndex;
    @SerializedName("co_eight_hour_max")
    private PSIReadingsItem coEightHourMax;
    @SerializedName("no2_one_hour_max")
    private PSIReadingsItem no2OneHourMax;
    @SerializedName("so2_twenty_four_hourly")
    private PSIReadingsItem so2TwentyFourHourly;
    @SerializedName("pm25_sub_index")
    private PSIReadingsItem pm25SubIndex;
    @SerializedName("psi_twenty_four_hourly")
    private PSIReadingsItem psiTwentyFourHourly;
    @SerializedName("o3_eight_hour_max")
    private PSIReadingsItem o3EightHourMax;

    public PSIReadings() {
    }

    private PSIReadings(Parcel in) {
        o3SubIndex = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        pm10TwentyFourHourly = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        pm10SubIndex = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        coSubIndex = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        pm25TwentyFourHourly = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        so2SubIndex = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        coEightHourMax = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        no2OneHourMax = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        so2TwentyFourHourly = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        pm25SubIndex = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        psiTwentyFourHourly = in.readParcelable(PSIReadingsItem.class.getClassLoader());
        o3EightHourMax = in.readParcelable(PSIReadingsItem.class.getClassLoader());
    }

    public static final Creator<PSIReadings> CREATOR = new Creator<PSIReadings>() {
        @Override
        public PSIReadings createFromParcel(Parcel in) {
            return new PSIReadings(in);
        }

        @Override
        public PSIReadings[] newArray(int size) {
            return new PSIReadings[size];
        }
    };

    public PSIReadingsItem getO3SubIndex() {
        return o3SubIndex;
    }

    public void setO3SubIndex(PSIReadingsItem o3SubIndex) {
        this.o3SubIndex = o3SubIndex;
    }

    public PSIReadingsItem getPm10TwentyFourHourly() {
        return pm10TwentyFourHourly;
    }

    public void setPm10TwentyFourHourly(PSIReadingsItem pm10TwentyFourHourly) {
        this.pm10TwentyFourHourly = pm10TwentyFourHourly;
    }

    public PSIReadingsItem getPm10SubIndex() {
        return pm10SubIndex;
    }

    public void setPm10SubIndex(PSIReadingsItem pm10SubIndex) {
        this.pm10SubIndex = pm10SubIndex;
    }

    public PSIReadingsItem getCoSubIndex() {
        return coSubIndex;
    }

    public void setCoSubIndex(PSIReadingsItem coSubIndex) {
        this.coSubIndex = coSubIndex;
    }

    public PSIReadingsItem getPm25TwentyFourHourly() {
        return pm25TwentyFourHourly;
    }

    public void setPm25TwentyFourHourly(PSIReadingsItem pm25TwentyFourHourly) {
        this.pm25TwentyFourHourly = pm25TwentyFourHourly;
    }

    public PSIReadingsItem getSo2SubIndex() {
        return so2SubIndex;
    }

    public void setSo2SubIndex(PSIReadingsItem so2SubIndex) {
        this.so2SubIndex = so2SubIndex;
    }

    public PSIReadingsItem getCoEightHourMax() {
        return coEightHourMax;
    }

    public void setCoEightHourMax(PSIReadingsItem coEightHourMax) {
        this.coEightHourMax = coEightHourMax;
    }

    public PSIReadingsItem getNo2OneHourMax() {
        return no2OneHourMax;
    }

    public void setNo2OneHourMax(PSIReadingsItem no2OneHourMax) {
        this.no2OneHourMax = no2OneHourMax;
    }

    public PSIReadingsItem getSo2TwentyFourHourly() {
        return so2TwentyFourHourly;
    }

    public void setSo2TwentyFourHourly(PSIReadingsItem so2TwentyFourHourly) {
        this.so2TwentyFourHourly = so2TwentyFourHourly;
    }

    public PSIReadingsItem getPm25SubIndex() {
        return pm25SubIndex;
    }

    public void setPm25SubIndex(PSIReadingsItem pm25SubIndex) {
        this.pm25SubIndex = pm25SubIndex;
    }

    public PSIReadingsItem getPsiTwentyFourHourly() {
        return psiTwentyFourHourly;
    }

    public void setPsiTwentyFourHourly(PSIReadingsItem psiTwentyFourHourly) {
        this.psiTwentyFourHourly = psiTwentyFourHourly;
    }

    public PSIReadingsItem getO3EightHourMax() {
        return o3EightHourMax;
    }

    public void setO3EightHourMax(PSIReadingsItem o3EightHourMax) {
        this.o3EightHourMax = o3EightHourMax;
    }

    /**
     * for getting psi readings base on region
     *
     * @param region {@link String} region of region, the value should be like <p>
     *               {@value PSIReadingsItem#EAST},
     *               {@value PSIReadingsItem#NATIONAL},
     *               {@value PSIReadingsItem#CENTRAL},
     *               {@value PSIReadingsItem#WEST},
     *               {@value PSIReadingsItem#NORTH},
     *               {@value PSIReadingsItem#SOUTH}
     *               </p>
     * @return HashMap of psi data
     */
    public HashMap<String, Double> getPSIBaseOnRegion(String region) {
        HashMap<String, Double> psiRegion = new HashMap<>();
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
                if (field.getType().isAssignableFrom(PSIReadingsItem.class)) {
                    try {
                        PSIReadingItemRegion readingItemRegion = (PSIReadingItemRegion) field.get(this);
                        psiRegion.put(field.getName(), readingItemRegion.getData(region));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return psiRegion;
    }

    /**
     * @return pollutant sub indices collection
     */
    public List<PSIReadingItemRegion> getPollutantSubIndice() {
        List<PSIReadingItemRegion> readingsList = new ArrayList<>();
        readingsList.add(coSubIndex);
        readingsList.add(o3SubIndex);
        readingsList.add(so2SubIndex);
        readingsList.add(pm10SubIndex);
        readingsList.add(pm25SubIndex);
        return readingsList;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(o3SubIndex, flags);
        dest.writeParcelable(pm10TwentyFourHourly, flags);
        dest.writeParcelable(pm10SubIndex, flags);
        dest.writeParcelable(coSubIndex, flags);
        dest.writeParcelable(pm25TwentyFourHourly, flags);
        dest.writeParcelable(so2SubIndex, flags);
        dest.writeParcelable(coEightHourMax, flags);
        dest.writeParcelable(no2OneHourMax, flags);
        dest.writeParcelable(so2TwentyFourHourly, flags);
        dest.writeParcelable(pm25SubIndex, flags);
        dest.writeParcelable(psiTwentyFourHourly, flags);
        dest.writeParcelable(o3EightHourMax, flags);
    }
}
