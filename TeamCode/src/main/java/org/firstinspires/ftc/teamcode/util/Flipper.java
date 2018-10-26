package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.File;

/**
 * Created by Avery on 2/15/18.
 */

public class Flipper {
    DcMotor mtrDispense;
    Encoder enc;
    TouchSensor lowLimmit;
    TouchSensor highLimmit;
    PIDController pid;
    boolean openLoop = true;
    boolean goingUp = false;
    boolean goingDown = false;

    // up and down target encoder counts
    final int upTarget = 3200;
    final int downTarget = 0;

    // pid constants
    final double kP = 0.0005;
    final double kI = 0;
    final double kD = 0;

    public Flipper(DcMotor mtrDispense, TouchSensor lowLimmit, TouchSensor highLimmit) {
        this.mtrDispense = mtrDispense;
        this.lowLimmit = lowLimmit;
        this.highLimmit = highLimmit;
        pid = new PIDController(kP, kI, kD);
        openLoop = true;
    }
    public void up() {
        goingUp = true;
        goingDown = false;
    }

    public void down() {
        goingUp = false;
        goingDown = true;
    }

    public void stop() {
        goingUp = goingDown = false;
    }

    public boolean isUp() {
        return highLimmit.isPressed();
    }
    public boolean isDown() {
        return lowLimmit.isPressed();
    }
    public void update(Telemetry telemetry) {
        telemetry.addData("openLoop", openLoop);
        if (!openLoop)
            telemetry.addData("enc", enc.getEncValue());
        telemetry.addData("power", mtrDispense.getPower());
        // periodic actions here
        // this needs to be called every round of loop() in an opmode
        if (lowLimmit.isPressed() && openLoop) {
            enc = new Encoder(mtrDispense);
            enc.resetEncoder();
            openLoop = false;
        }
        // take us out of an invalid state
        if (goingUp && goingDown)
            goingUp = goingDown = false;
        // nothing to do, just stop
        if ((goingUp && highLimmit.isPressed()) || (goingDown && lowLimmit.isPressed()) || !(goingUp || goingDown)) {
            mtrDispense.setPower(0);
            return;
        }

        if (openLoop) {
            mtrDispense.setPower((goingUp) ? 0.4 : -0.3);
        }
        else {
            int target = (goingUp) ? upTarget : downTarget;
            mtrDispense.setPower(Range.clip(pid.get(target - enc.getEncValue()), -1, 1));
        }
    }
}
