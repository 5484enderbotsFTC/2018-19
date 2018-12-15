package org.firstinspires.ftc.teamcode;

import android.hardware.Sensor;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Corning FTC on 10/1/2018.
 *
 */

@TeleOp
public class testOPMODE_____S__S__A extends LinearOpMode {
    private DcMotor mtrFR;
    private DcMotor mtrFL;
    private DcMotor mtrBR;
    private DcMotor mtrBL;

    private DcMotor mtrCollect;

    private Servo svoSlide;
    private Servo svoWrist;

    /* private DistanceSensor sensorColorRange;
    private BNO055IMU imu; */

    private double yPower;
    private double xPower;

    private double rightPower;
    private double leftPower;


    @Override
    public void runOpMode() {

        /*BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        */

        mtrFR = hardwareMap.get(DcMotor.class,"mtrFR");
        mtrFL = hardwareMap.get(DcMotor.class,"mtrFL");
        mtrBR = hardwareMap.get(DcMotor.class,"mtrBR");
        mtrBL = hardwareMap.get(DcMotor.class,"mtrBL");

        mtrCollect = hardwareMap.get(DcMotor.class, "mtrCollect");
        svoSlide = hardwareMap.get(Servo.class, "svoSlide");
        svoWrist = hardwareMap.get(Servo.class, "svoWrist");

        /*
        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        telemetry.addData("Status", "Initialized");
        telemetry.update();
*/

        waitForStart();

        while (opModeIsActive()) {

            yPower = -this.gamepad1.left_stick_y;
            xPower = this.gamepad1.left_stick_x;

            rightPower = yPower-xPower;
            leftPower = yPower+xPower;

            mtrFR.setPower(rightPower);
            mtrFL.setPower(-leftPower);
            mtrBR.setPower(rightPower);
            mtrBL.setPower(-leftPower);




            telemetry.addData("Status", "Running");

            telemetry.addData("Y power:",yPower);
            telemetry.addData("X power:",xPower);
            //telemetry.addData("Distance (cm)", sensorColorRange.getDistance(DistanceUnit.CM));
            telemetry.update();





        }
    }
}
