package ru.tisov.denis.dto;

import java.util.List;

public class Plot {

    private String name;
    private List<PlotPoint> points;

    public Plot() {
    }

    public Plot(String name, List<PlotPoint> points) {
        this.name = name;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlotPoint> getPoints() {
        return points;
    }

    public void setPoints(List<PlotPoint> points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Plot{" +
                "name='" + name + '\'' +
                ", points=" + points +
                '}';
    }

}
