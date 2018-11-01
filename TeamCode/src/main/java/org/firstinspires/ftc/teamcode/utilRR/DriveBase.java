package org.firstinspires.ftc.teamcode.utilRR;

import android.graphics.YuvImage;

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
 * Jacked by Ted on October 27, 2018
 **/

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

    double offset = 0;
    final double K_P = 0.01;
    public DriveBase(HardwareMap hardwareMap, boolean calibrate) {
        this.hardwareMap = hardwareMap;
        mtrBL = hardwareMap.dcMotor.get("mtrBl");
        mtrBR = hardwareMap.dcMotor.get("mtrBr");
        mtrFL = hardwareMap.dcMotor.get("mtrFl");
        mtrFR = hardwareMap.dcMotor.get("mtrFr");

        /*mtrFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/

        /*encFL = new Encoder(mtrFL);
        encFR = new Encoder(mtrFL);
        encBL = new Encoder(mtrFL);
        encBR = new Encoder(mtrFL);
        */

//        if (useIMU) {
//            snsImu = hardwareMap.get(BNO055IMU.class, "snsImu");
//            if (calibrate)
//                initIMU();
//        } else {
//        }

    }
    public void drive(double X, double Y){
        double leftPower = X - Y;
        double rightPower = X + Y;
        mtrBL.setPower(leftPower);
        mtrBR.setPower(rightPower);
        mtrFL.setPower(leftPower);
        mtrFR.setPower(rightPower);
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

    public double getAngle() {
//        if (snsImu != null) {
//            return snsImu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle - offset;
//        } else {
        return 0;
//        }
    }

    public void resetGyro() {
//        if (snsImu != null) {
//            offset = snsImu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
//        } else {

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

}
