package com.laioffer.laiofferproject;

/**
 * Created by music on 11/20/2017.
 */

public class Restaurant {
    /**
     * All data
     */
    private String name;
    private String address;
    private String type;

    /**
     * Constructor
     */
    public Restaurant(String name, String address, String type) {
        this.name = name;
        this.address = address;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getType() {
        return type;
    }
}
