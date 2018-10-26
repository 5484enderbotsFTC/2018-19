package org.firstinspires.ftc.teamcode.util;

public class Incrementable {
    private double increment = 1;
    private double value = 0;

    private boolean upWatch = false;
    private boolean downWatch = false;

    public Incrementable(double value, double increment) {
        this.value = value;
        this.increment = increment;
    }

    public double get() { return value; }
    public int getInt() { return Math.round((float) value); }
    public void set(double value) { this.value = value; }
    public double getIncrement() { return increment; }
    public void setIncrement() { this.increment = increment; }
    public void increment() { value += increment; }
    public void decrement() { value -= increment; }

    public void updateUp(boolean up) {
        if (up && !upWatch)
            increment();
        upWatch = up;
    }

    public void updateDown(boolean down) {
        if (down && !downWatch)
            decrement();
        downWatch = down;
    }
}
