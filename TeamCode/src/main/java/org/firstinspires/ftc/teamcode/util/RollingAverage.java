package org.firstinspires.ftc.teamcode.util;

/**
 * Created by guinea on 10/1/17.
 */

public class RollingAverage {
    double[] buffer;
    int idx;
    public RollingAverage(int maxSize) {
        buffer = new double[maxSize];
        idx = 0;
    }
    public RollingAverage(int maxSize, double fillValue) {
        this(maxSize);
        for (int i = 0; i < maxSize; i++)
            buffer[i] = fillValue;
    }

    public void update(double value) {
        buffer[idx] = value;
        idx++;
        if (idx >= buffer.length)
            idx = 0;
    }

    public double average() {
        double sum = 0;
        for (double v : buffer)
            sum += v;
        return sum / (double) buffer.length;
    }

    public int size() {
        return buffer.length;
    }
}
