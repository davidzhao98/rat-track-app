package kanye2020.gatech.edu.rattrackapp;

import android.icu.text.SimpleDateFormat;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

class RatSighting implements Parcelable, Comparable<RatSighting> {
    private final String borough;
    private final String city;
    private final String address;
    private final String locationType;
    private final String uniqueKey;
    private final String zipcode;
    private final String dateTime;
    private final String latitude;
    private final String longitude;

    RatSighting(String borough, String city, String address, String zipcode, String locationType,
                String dateTime, String strLatitude, String strLongitude, String strKey) {
        this.borough = borough;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
        this.locationType = locationType;
        this.dateTime = dateTime;
        this.latitude = strLatitude;
        this.longitude = strLongitude;
        this.uniqueKey = strKey;
    }

    /**
     *
     * @return the borough
     */
    public CharSequence getBorough() {
        return borough;
    }

    /**
     * @return the city
     */
    CharSequence getCity() {
        return city;
    }

    /**
     *
     * @return the address
     */
    CharSequence getAddress() {
        return address;
    }

    /**
     *
     * @return locationType
     */
    CharSequence getLocationType() {
        return locationType;
    }

    /**
     *
     * @return uniqueKey
     */
    String getUniqueKey() {
        return uniqueKey;
    }

    /**
     *
     * @return the zipcode
     */
    CharSequence getZipcode() {
        return zipcode;
    }

    /**
     *
     * @return the dateTime
     */
    String getDateTime() {
        return dateTime;
    }

    /**
     *
     * @return latitude
     */
    String getLatitude() {
        return latitude;
    }

    /**
     *
     * @return longitude
     */
    String getLongitude() {
        return longitude;
    }

    /**
     * allows the marker to be viewable
     * @param in the parcel being passed in
     */
    private RatSighting(Parcel in){
        String[] data = new String[9];

        in.readStringArray(data);
        // the order needs to be the same as in writeToParcel() method
        this.borough = data[0];
        this.city = data[1];
        this.address = data[2];
        this.zipcode = data[3];
        this.locationType = data[4];
        this.dateTime = data[5];
        this.latitude = data[6];
        this.longitude = data[7];
        this.uniqueKey = data[8];
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {this.borough, this.city, this.address, this.zipcode,
                this.locationType, this.dateTime, this.latitude, this.longitude, this.uniqueKey});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public RatSighting createFromParcel(Parcel in) {
            return new RatSighting(in);
        }

        @Override
        public RatSighting[] newArray(int size) {
            return new RatSighting[size];
        }
    };

    @Override
    public int compareTo(RatSighting other) {
        String thisDateTime = this.getDateTime();
        String otherDateTime = "";
            try {
                otherDateTime = other.getDateTime();
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                String thisDateText = thisDateTime.substring(0, "MM/dd/yyyy".length() + 1);
                Date thisDate = dateFormat.parse(thisDateText);
                String otherDateText = otherDateTime.substring(0, "MM/dd/yyyy".length() + 1);
                Date otherDate = dateFormat.parse(otherDateText);
                return thisDate.compareTo(otherDate);
            } catch (Exception e) {

            }
        return thisDateTime.compareTo(otherDateTime);
    }
}
