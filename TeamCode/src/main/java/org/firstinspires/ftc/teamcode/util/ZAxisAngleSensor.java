package org.firstinspires.ftc.teamcode.util;

/**
 * Created by guinea on 11/12/17.
 */

public interface ZAxisAngleSensor {
    void init();
    void reset();
    double getAbsoluteAngle();
    double getAngle();
}
