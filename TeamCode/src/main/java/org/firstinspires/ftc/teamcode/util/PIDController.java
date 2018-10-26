package org.firstinspires.ftc.teamcode.util;

/**
 * Created by guinea on 1/7/18.
 *
 * This is a simple pid controller class planned for future use. written by the head programmer as an example.
 * Currently, our proportional control logic is inline in our autonomous code, but in the future, we
 * are planning to use a class written by the more junior programmers using this logic to modularlize our
 * control loops.;
 */

public class PIDController {
    private double Kp;
    private double Ki;
    private double Kd;
    private long ts;
    private double sumError;
    private double lastError;

    public PIDController(double Kp, double Ki, double Kd) {
        sumError = 0;
        lastError = 0;
        setConstants(Kp, Ki, Kd);
        ts = System.nanoTime();
    }

    public void setConstants(double Kp, double Ki, double Kd) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
    }

    public double get(double error) {
        long now = System.nanoTime();
        double dT = (now - ts) / 1e6d;
        double res = Kp * error + Ki * sumError + Kd * (error - lastError) / dT;
        sumError += error;
        lastError = error;
        ts = now;
        return res;
    }
}
