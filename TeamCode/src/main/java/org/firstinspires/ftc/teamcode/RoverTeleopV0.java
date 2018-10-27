package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Avery on 10/24/18.
 * This is version 0 of our teleop program, designed to work on the robot we bring to the new jersey league meet
 * We have a drivetrain, collector, rotater, and extender, so this is only gonna be a 1 person op to keep things simple
 */
@TeleOp(name="RoverRuckV0", group="K9bot")
public class RoverTeleopV0 extends OpMode {
    DcMotor mtrFl;
    DcMotor mtrBl;
    DcMotor mtrFr;
    DcMotor mtrBr;
    DcMotor mtrCollect;
    Servo svoExtend;
    Servo svoRotate;

    double UP = 1;
    double DOWN = 0;
    double IN = 0;

    @Override
    public void init() {
        mtrBl = hardwareMap.dcMotor.get("mtrBl");
        mtrBr = hardwareMap.dcMotor.get("mtrBr");
        mtrFr = hardwareMap.dcMotor.get("mtrFr");
        mtrFl = hardwareMap.dcMotor.get("mtrFl");
        mtrCollect = hardwareMap.dcMotor.get("mtrCollect");
        svoExtend = hardwareMap.servo.get("svoExtend");
        svoRotate = hardwareMap.servo.get("svoRotate");
    }

    public void start() {
        svoExtend.setPosition(IN);
    }

    public void loop() {
        double X = gamepad1.left_stick_y;
        double Y = gamepad1.right_stick_x;
        double leftPower = X - Y;
        double rightPower = X + Y;
        mtrBl.setPower(leftPower);
        mtrBr.setPower(rightPower);
        mtrFl.setPower(leftPower);
        mtrFr.setPower(rightPower);

        if (gamepad1.dpad_down) {svoRotate.setPosition(UP);}
        else if (gamepad1.dpad_up) {svoRotate.setPosition(DOWN);}

        if (gamepad1.left_trigger > 0.5) {
            incrementsvo(svoExtend, 0.2);
        } else if (gamepad1.left_bumper) {
            incrementsvo(svoExtend, -0.2);
        }

        if (gamepad1.right_trigger > 0.5) {
            mtrCollect.setPower(1);
        } else if (gamepad1.right_bumper) {
            mtrCollect.setPower(-1);
        } else {
            mtrCollect.setPower(0);
        }
    }

    public void stop() {

    }
    public void incrementsvo(Servo svo, double change) {
        svo.setPosition(svo.getPosition() + change);
    }
}