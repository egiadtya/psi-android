package imd.psiapp.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * data model for Pollutant Standards Index item
 * Created by egiadtya on 7/24/17.
 */

public class PSIItem implements Parcelable {
    @SerializedName("timestamp")
    private String timestamp;
    @SerializedName("update_timestamp")
    private String updateTimestamp;
    @SerializedName("readings")
    private PSIReadings psiReadings;

    public PSIItem() {
    }

    private PSIItem(Parcel in) {
        timestamp = in.readString();
        updateTimestamp = in.readString();
    }

    public static final Creator<PSIItem> CREATOR = new Creator<PSIItem>() {
        @Override
        public PSIItem createFromParcel(Parcel in) {
            return new PSIItem(in);
        }

        @Override
        public PSIItem[] newArray(int size) {
            return new PSIItem[size];
        }
    };

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUpdateTimestamp() {
        return updateTimestamp;
    }

    public void setUpdateTimestamp(String updateTimestamp) {
        this.updateTimestamp = updateTimestamp;
    }

    public PSIReadings getPsiReadings() {
        return psiReadings;
    }

    public void setPsiReadings(PSIReadings psiReadings) {
        this.psiReadings = psiReadings;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(timestamp);
        dest.writeString(updateTimestamp);
    }
}
