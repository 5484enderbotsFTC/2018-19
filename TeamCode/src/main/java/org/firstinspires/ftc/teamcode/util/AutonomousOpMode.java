package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cColorSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.vision.VuMarkReader;

/**
 * Created by guinea on 11/15/17.
 */

public abstract class AutonomousOpMode extends LinearOpMode {

    protected final static double TICKS_PER_ROTATION = 1120 / 2;

    protected final static double FORWARD = 0;
    protected final static double LEFT = 90;
    protected final static double RIGHT = -90;
    protected final static double BACKWARD = 180;
    protected DriveBase driveBase;
    ModernRoboticsI2cColorSensor a;

    protected RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;
    protected VuMarkReader vuMarkReader;

    @Override
    public final void runOpMode() throws InterruptedException {
        _runInit();
        runInit();
        while (!isStarted() && !isStopRequested()) {
            runInitLoop();
            Thread.yield();
        }
        if (!opModeIsActive()) return;
        driveBase.resetDrive();
        runAutonomous();
    }

    public boolean stillRunning() throws InterruptedException {
        if (opModeIsActive()) return true;
        else {
            throw new InterruptedException("opmode stop");
        }
    }

    public void _runInit() throws InterruptedException {
        telemetry.setAutoClear(false);
        telemetry.addLine("Waiting on Vuforia to initialize...");
        driveBase = new DriveBase(hardwareMap, false);
        ElapsedTime vuforiaTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        vuMarkReader = new VuMarkReader(hardwareMap.appContext);
        vuMarkReader.start();
        driveBase.snsGyro.calibrate();
        telemetry.setAutoClear(true);
    }

    public abstract void runInit() throws InterruptedException;

    public void runInitLoop() throws InterruptedException {
        RelicRecoveryVuMark v = vuMarkReader.getVuMark();
        if (v != RelicRecoveryVuMark.UNKNOWN) {
            vuMark = v;
        }
        telemetry.addData("Vumark: ", vuMark);
        telemetry.addData("Gyro calibrating", driveBase.snsGyro.isCalibrating());
        telemetry.update();
    }
    public abstract void runAutonomous() throws InterruptedException;

    public void driveDistanceInt(double magnitude, double angle, int ticks) {
        driveDistanceInt(magnitude, angle, ticks, true);
    }
    public void driveDistanceInt(double magnitude, double angle, int ticks, boolean leftEncoder)  {
        driveBase.resetDrive();
        Encoder enc = leftEncoder ? driveBase.encFL : driveBase.encFR;
        while (Math.abs(enc.getEncValue()) < ticks && opModeIsActive())
            driveBase.drive(magnitude, angle, Range.clip(driveBase.getAngle() * 0.02, -1, 1));
        driveBase.setDrivePowers(0, 0, 0, 0);
    }

    public void driveDistance(double magnitude, double angle, double rotations) {
        driveDistance(magnitude, angle, rotations, true);
    }
    public void driveDistance(double magnitude, double angle, double rotations, boolean leftEncoder) {
        driveBase.resetDrive();
        Encoder enc = leftEncoder ? driveBase.encFL : driveBase.encFR;
        while (Math.abs(enc.getEncValue() / TICKS_PER_ROTATION) < rotations && opModeIsActive())
            driveBase.drive(magnitude, angle, Range.clip(driveBase.getAngle() * 0.02, -1, 1));
        driveBase.setDrivePowers(0, 0, 0, 0);
    }

    public void driveStraight(double magnitude, double angle) {
        driveBase.drive(magnitude, angle, Range.clip(driveBase.getAngle() * 0.02, -1, 1));
    }
    public void turnAngle(double p, double angle) {
        driveBase.resetDrive();
        driveBase.driveArcade(0, 0, (driveBase.getAngle() - angle) * p, 1);
    }
}
