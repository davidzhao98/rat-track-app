package kanye2020.gatech.edu.rattrackapp;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by David Zhao on 10/6/2017.
 */

public class RatSighting implements Parcelable {
    private String borough, city, address, locationType, uniqueKey, zipcode, dateTime, latitude, longitude;
//    Date date;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm", Locale.US);

    public RatSighting(String borough, String city, String address, String zipcode, String locationType, String dateTime, String strLatitude, String strLongitude, String strKey) {
        this.borough = borough;
        this.city = city;
        this.address = address;
        this.zipcode = zipcode;
        this.locationType = locationType;
        this.dateTime = dateTime;
//        try {
//            this.date = sdf.parse(strDate);
//        } catch (ParseException e){
//            try {
//                this.date = sdf.parse("01/01/1900");
//            } catch (ParseException f) {
//                System.out.print("DATE PARSE ERROR");
//            }
//            System.out.print("DATE PARSE ERROR");
//        }
        this.latitude = strLatitude;
        this.longitude = strLongitude;
        this.uniqueKey = strKey;
//        sdf.applyPattern("MM/dd/yyyy");
//        String asdf = sdf.format(date);
    }

    /**
     *
     * @return the borough
     */
    public String getBorough() {
        return borough;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @return locationType
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     *
     * @return uniqueKey
     */
    public String getUniqueKey() {
        return uniqueKey;
    }

    /**
     *
     * @return the zipcode
     */
    public String getZipcode() {
        return zipcode;
    }

    /**
     *
     * @return the dateTime
     */
    public String getDateTime() {
        return dateTime;
    }

    /**
     *
     * @return latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *
     * @return longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * allows the marker to be viewable
     * @param in the parcel being passed in
     */
    public RatSighting(Parcel in){
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
        public RatSighting createFromParcel(Parcel in) {
            return new RatSighting(in);
        }

        public RatSighting[] newArray(int size) {
            return new RatSighting[size];
        }
    };
}
