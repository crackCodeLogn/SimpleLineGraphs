package com.vv.git.graphs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String LOGGING_KEY = "GRAPHING";

    private GraphView graphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphView = findViewById(R.id.graph);

        createLineGraph(populateData());
    }

    private List<Point> populateData() {
        List<Point> listing = new ArrayList<>();
        listing.add(new Point(10, 10));
        listing.add(new Point(10, 20));
        listing.add(new Point(4, 29));
        listing.add(new Point(25, 50));
        listing.add(new Point(16, 32));
        Log.d(LOGGING_KEY, "Listing size : " + listing.size());

        Collections.sort(listing, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return (int) (p1.getX() - p2.getX());
            }
        }); //the points should be in an increasing order of x co-ords

        return listing;
    }

    private void createLineGraph(List<Point> result) {
        DataPoint[] dataPoints = new DataPoint[result.size()];
        for (int i = 0; i < result.size(); i++) {
            Point point = result.get(i);
            Log.d(LOGGING_KEY, "Point : " + point);
            dataPoints[i] = new DataPoint(point.getX(), point.getY());
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints);
        graphView.addSeries(series);
    }

    private class Point {
        private final double x;
        private final double y;

        private Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        @SuppressLint("DefaultLocale")
        public String toString() {
            return String.format("[x : %.2f, y : %.2f]", x, y);
        }
    }
}
