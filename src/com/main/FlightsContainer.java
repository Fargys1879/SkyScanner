package com.main;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
public class FlightsContainer {
    @JsonDeserialize(as = ArrayList.class)
    private List<Flight> flights;

    public List<Flight> getFlights() {
        return flights;
    }

    @Override
    public String toString() {
        return "{" + '\n'+
                "\"flights\" : " + '\n' + flights + '\n' +
                '}' + '\n';
    }
}
