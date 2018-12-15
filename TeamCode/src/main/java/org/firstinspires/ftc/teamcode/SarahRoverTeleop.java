package org.firstinspires.ftc.teamcode;

import android.hardware.Sensor;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.utilRR.DriveBase;

/**
 * Created by Sarahpambi76 on 12/10/18.
 */
@TeleOp (name="SarahRoverTeleop", group="K9bot")
public class SarahRoverTeleop extends OpMode {


    DcMotor mtrHang;
    DcMotor mtrCollect;
    Servo svoRotate;
    Servo svoCollect;
    DriveBase driveBase;
    TouchSensor limExtendLow;
    TouchSensor limExtendHigh;

    double UPWARD = 1;
    double DOWNWARD = -1;
    double IN = -1;
    double OUT = 1;
    double DPOSITION = 0.55;
    double CPOSITION = 0.45;
    double SETPOSITION = 0.5;
    double COLLECT = 1;
    double REVERSECOLLECT = 0;


    @Override
    public void init() {
        driveBase = new DriveBase(hardwareMap,false);
        mtrHang = hardwareMap.dcMotor.get("mtrHang");
        mtrCollect = hardwareMap.dcMotor.get("mtrCollect");
        svoRotate = hardwareMap.servo.get("svoRotate");
        svoCollect = hardwareMap.servo.get("svoCollect");
        limExtendHigh = hardwareMap.touchSensor.get("limExtendHigh");
        limExtendLow = hardwareMap.touchSensor.get("limExtendLow");

    }

    public void start() {
    }

    public void loop() {
        double Y = gamepad1.left_stick_y;
        double X = gamepad1.right_stick_x;

        driveBase.drive(X,Y);

        if(gamepad2.dpad_up){mtrHang.setPower(UPWARD);}
        else if(gamepad2.dpad_down){mtrHang.setPower(DOWNWARD);}
        else{mtrHang.setPower(0);}

        if(gamepad1.left_trigger>0.5){mtrCollect.setPower(OUT);}
        else if(gamepad1.left_bumper){mtrCollect.setPower(IN);}
        else {mtrCollect.setPower(0);}

        if(gamepad1.dpad_up){svoRotate.setPosition(CPOSITION);}
        else if(gamepad1.dpad_down){svoRotate.setPosition(DPOSITION);}
        else if(gamepad1.dpad_left){svoRotate.setPosition(SETPOSITION);}
        else if(gamepad1.dpad_right){svoRotate.setPosition(SETPOSITION);}

        if(gamepad1.right_trigger>0.5){svoCollect.setPosition(COLLECT);}
        else if(gamepad1.right_bumper){svoCollect.setPosition(REVERSECOLLECT);}
        else{svoCollect.setPosition(.5);}

    }

    public void stop(){}

}