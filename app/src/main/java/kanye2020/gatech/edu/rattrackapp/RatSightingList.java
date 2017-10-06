package kanye2020.gatech.edu.rattrackapp;

import java.util.ArrayList;

/**
 * Singleton class to be used by the different search activities and the list view.
 * Contains all the rats stored in the database.
 *
 * Created by David Zhao on 10/6/2017.
 */

public class RatSightingList {
    private ArrayList<RatSighting> rats;
    private static RatSightingList instance;

    public static RatSightingList getInstance() {
        if (instance == null) {
            instance = new RatSightingList();
        }
        return instance;
    }

    private RatSightingList() {
        //TODO
        //ADAPT GETUSERS() FROM LOGINACTIVITY.JAVA TO GET RATS INSTEAD
    }

    public ArrayList<RatSighting> getRats() {
        return rats;
    }
}
