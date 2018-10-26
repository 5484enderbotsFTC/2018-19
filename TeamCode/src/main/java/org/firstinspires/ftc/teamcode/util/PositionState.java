package org.firstinspires.ftc.teamcode.util;

/**
 * Created by guinea on 10/23/17.
 */

public class PositionState {
    private double xrad;
    private double yrad;
    private double x;
    private double y;
    private double theta;

    public PositionState() {
        zero();
        xrad = 0;
        yrad = 0;
    }

    public PositionState(double x_offset, double y_offset) {
        xrad = x_offset;
        yrad = y_offset;
    }

    public PositionState(double iX, double iY, double itheta, double x_offset, double y_offset) {
        x = iX;
        y = iY;
        theta = itheta;
        xrad = x_offset;
        yrad = y_offset;
    }

    public void zero() {
        zero(0, 0, 0);
    }

    public void zero(double x, double y, double theta) {
        this.x = x;
        this.y = y;
        this.theta = theta;
    }

    /**
     *
     * @param xf final raw distance, x (strafe) axis
     * @param yf final raw distance, y (forward) axis
     * @param thetaf final angle measurement, in radians
     */
    public void update(double xf, double yf, double thetaf) {
        double dtheta = (thetaf - theta);
        // normalize from offset of rollers from central axes
        xf -= dtheta * xrad;
        yf -= dtheta * yrad;
        // raw measurement change, not accounting for rotation yet
        double dx = x - xf;
        double dy = y - yf;
        x += dx * Math.cos(thetaf) - dy * Math.sin(thetaf);
        y += dx * Math.sin(thetaf) + dy * Math.cos(thetaf);
    }
}
