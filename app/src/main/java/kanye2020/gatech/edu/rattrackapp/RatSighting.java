package kanye2020.gatech.edu.rattrackapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by David Zhao on 10/6/2017.
 */

public class RatSighting {
    private String borough, city, address, locationType, uniqueKey, zipcode, dateTime, latitude, longitude;
//    Date date;
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");

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

    public String getBorough() {
        return borough;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getLocationType() {
        return locationType;
    }

    public String getUniqueKey() {
        return uniqueKey;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
