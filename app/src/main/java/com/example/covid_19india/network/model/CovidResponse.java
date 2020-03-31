package com.example.covid_19india.network.model;

import java.util.ArrayList;

public class CovidResponse {

    private ArrayList<StateWise> statewise;

    public ArrayList<StateWise> getStatewise() {
        return statewise;
    }

    public void setStatewise(ArrayList<StateWise> statewise) {
        this.statewise = statewise;
    }
}


