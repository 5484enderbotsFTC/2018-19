package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

import java.util.Arrays;

/**
 * Created by guinea on 11/5/17.
 */

public class DriveBase {
    public DcMotor mtrFL;
    public DcMotor mtrFR;
    public DcMotor mtrBL;
    public DcMotor mtrBR;
    public Encoder encFL;
    public Encoder encFR;
    public Encoder encBL;
    public Encoder encBR;
//    BNO055IMU snsImu;
    HardwareMap hardwareMap;
    ModernRoboticsI2cGyro snsGyro;

    double offset = 0;
    final double K_P = 0.01;
    public DriveBase(HardwareMap hardwareMap, boolean calibrate) {
        this.hardwareMap = hardwareMap;
        mtrBL = hardwareMap.dcMotor.get("mtrBL");
        mtrBR = hardwareMap.dcMotor.get("mtrBR");
        mtrFL = hardwareMap.dcMotor.get("mtrFL");
        mtrFR = hardwareMap.dcMotor.get("mtrFR");

        /*mtrFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/

        encFL = new Encoder(mtrFL);
        encFR = new Encoder(mtrFL);
        encBL = new Encoder(mtrFL);
        encBR = new Encoder(mtrFL);

//        if (useIMU) {
//            snsImu = hardwareMap.get(BNO055IMU.class, "snsImu");
//            if (calibrate)
//                initIMU();
//        } else {
        snsGyro = hardwareMap.get(ModernRoboticsI2cGyro.class, "snsGyro");
        if (calibrate)
            initMRGyro();
//        }
    }

    private void initIMU() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
//        snsImu.initialize(parameters);
    }

    private void initMRGyro() {
        snsGyro.calibrate();
        while (snsGyro.isCalibrating()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }

    public double getAngle() {
//        if (snsImu != null) {
//            return snsImu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle - offset;
//        } else {
            return -snsGyro.getIntegratedZValue();
//        }
    }

    public void resetGyro() {
//        if (snsImu != null) {
//            offset = snsImu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
//        } else {
            snsGyro.resetZAxisIntegrator();
//        }
    }

    public void resetDrive() {
        resetGyro();
        resetEncoders();
    }
    public void resetEncoders() {
        encFL.resetEncoder();
        encFR.resetEncoder();
        encBL.resetEncoder();
        encBR.resetEncoder();
    }

    public void drive(double magnitude, double angle) { drive(magnitude, angle, 0, 1); }
    public void drive(double magnitude, double angle, double rotate) { drive(magnitude, angle, rotate, 1); }
    public void drive(double magnitude, double angle, double rotate, double factor) {
        double rad = Math.toRadians(angle);
        driveArcade(magnitude * Math.sin(rad), magnitude * Math.cos(rad), rotate, factor);
    }

    public void driveArcade(double x1, double y1, double x2, double factor) {

        double fl = (x1 + y1 + x2) / factor;
        double fr = (x1 - y1 + x2) / factor;
        double bl = (-x1 + y1 + x2) / factor;
        double br = (-x1 - y1 + x2) / factor;
        double[] powers = {
                Math.abs(fl),
                Math.abs(fr),
                Math.abs(bl),
                Math.abs(br),
        };
        Arrays.sort(powers);

        if (powers[3] > 1) {
            fl /= powers[3];
            fr /= powers[3];
            bl /= powers[3];
            br /= powers[3];
        }
        setDrivePowers(fl, fr, bl, br);
    }

    public void setDrivePowers(double fl, double fr, double bl, double br) {
        mtrFL.setPower(fl);
        mtrFR.setPower(fr);
        mtrBL.setPower(bl);
        mtrBR.setPower(br);
    }
}
