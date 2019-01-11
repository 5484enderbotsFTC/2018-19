
package org.firstinspires.ftc.teamcode.utilRR;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;


/**
 * Created by guinea on 11/5/17.
 * Jacked by Ted on October 27, 2018
 **/

public class DriveBase {
    public DcMotor mtrL;
    public DcMotor mtrR;
    public Encoder encL;
    public Encoder encR;
    public BNO055IMU snsImu;
    private HardwareMap hardwareMap;

    double offset = 0;
    final double K_P = 0.01;
    public DriveBase(HardwareMap hardwareMap, boolean calibrate) {
        this.hardwareMap = hardwareMap;
        mtrL = hardwareMap.dcMotor.get("mtrL");
        mtrR = hardwareMap.dcMotor.get("mtrR");
        mtrL.setDirection(DcMotorSimple.Direction.REVERSE);

        mtrL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        encL = new Encoder(mtrL);
        encR = new Encoder(mtrR);

        snsImu = hardwareMap.get(BNO055IMU.class, "snsImu");
        if (calibrate)
            initIMU();


    }
    public void drive(double Y, double X){
        double leftPower = -Y + X;
        double rightPower = Y + X;
        mtrL.setPower(leftPower);
        mtrR.setPower(rightPower);
    }

    public void driveEncoder(double count){
        resetEncoders();
        while (encL.getEncValue()<count){
            drive(1, 0);
        }
        drive(0, 0);
    }

    public void turnInPlace(double rotation){
        resetGyro();
        double leftPower = Integer.signum((int)rotation);
        double rightPower = Integer.signum((int)rotation);
        while (Math.abs(getAngle())<Math.abs(rotation)){
            mtrL.setPower(leftPower);
            mtrR.setPower(rightPower);
        }
        drive(0, 0);
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
        // and named "snsImu".
        snsImu.initialize(parameters);
    }

    public double getAngle() {
        if (snsImu != null) {
            return snsImu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle - offset;
        } else {
        return 0;
        }
    }

    public void resetGyro() {
        if (snsImu != null) {
            offset = snsImu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
        } else {
        }
    }

    public void resetDrive() {
        resetGyro();
        resetEncoders();
    }
    public void resetEncoders() {
        encL.resetEncoder();
        encR.resetEncoder();
    }

}
