package com.example.android.flymetothemoon.FlightsDoc;

/**
 * Created by tsita on 17/1/2017.
 */

public class AirportsJsonTranslations {

    private String value;
    private String label;

    public AirportsJsonTranslations(String value, String label) {
        this.value = value;
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
