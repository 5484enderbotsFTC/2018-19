package org.firstinspires.ftc.teamcode;

import android.hardware.Sensor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

/**
 * Created by Corning FTC on 10/1/2018.
 */

@TeleOp
public class testOPMODE_____S__S__A extends LinearOpMode {
    private DcMotor mtrFR;
    private DcMotor mtrFL;
    private DcMotor mtrBR;
    private DcMotor mtrBL;

    private DistanceSensor sensorColorRange;

    private double yPower;
    private double xPower;

    private double rightPower;
    private double leftPower;

    @Override
    public void runOpMode() {
        mtrFR = hardwareMap.get(DcMotor.class,"mtrFR");
        mtrFL = hardwareMap.get(DcMotor.class,"mtrFL");
        mtrBR = hardwareMap.get(DcMotor.class,"mtrBR");
        mtrBL = hardwareMap.get(DcMotor.class,"mtrBL");

        sensorColorRange = hardwareMap.get(DistanceSensor.class, "sensorColorRange");

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            yPower = -this.gamepad1.left_stick_y;
            xPower = this.gamepad1.right_stick_x;


            rightPower = yPower-xPower;
            leftPower = yPower+xPower;

            mtrFR.setPower(rightPower);
            mtrFL.setPower(-leftPower);
            mtrBR.setPower(rightPower);
            mtrBL.setPower(-leftPower);



            telemetry.addData("Status", "Running");

            telemetry.addData("Y power:",yPower);
            telemetry.addData("X power:",xPower);
            telemetry.addData("Distance (cm)", sensorColorRange.getDistance(DistanceUnit.CM));
            telemetry.update();





        }
    }
}
