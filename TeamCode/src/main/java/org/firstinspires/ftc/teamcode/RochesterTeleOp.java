package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.utilRR.DriveBase;

/**
 * Created by Sarahpambi76 on 12/10/18.
 */
@TeleOp (name="RochesterTeleOp", group="K9bot")
public class RochesterTeleOp extends OpMode {


    Servo svoHang;
    DcMotor mtrCollect;
    Servo svoRotate;
    Servo svoCollect;
    DriveBase driveBase;
    TouchSensor limHangLow;
    TouchSensor limHangHigh;
    Servo svoTape;

    double svopos = 0;
    double UPWARD = 0;
    double DOWNWARD = 1;
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
        svoHang = hardwareMap.servo.get("mtrHang");
        mtrCollect = hardwareMap.dcMotor.get("mtrCollect");
        svoRotate = hardwareMap.servo.get("svoRotate");
        svoCollect = hardwareMap.servo.get("svoCollect");
        //limExtendHigh = hardwareMap.touchSensor.get("limExtendHigh");
        //limExtendLow = hardwareMap.touchSensor.get("limExtendLow");
        svoTape = hardwareMap.servo.get("svoTape");

    }

    public void start() {
    }

    public void loop() {
        double Y = gamepad1.left_stick_y;
        double X = gamepad1.right_stick_x;

        driveBase.drive(X,Y);

        if(gamepad2.dpad_up){
            svoHang.setPosition(UPWARD);}
        else if(gamepad2.dpad_down){
            svoHang.setPosition(DOWNWARD);}
        else{
            svoHang.setPosition(0.5);}

        if(gamepad1.left_trigger>0.5){mtrCollect.setPower(OUT);}
        else if(gamepad1.left_bumper){mtrCollect.setPower(IN);}
        else {mtrCollect.setPower(0);}

        //if(gamepad1.dpad_up){svoRotate.setPosition(CPOSITION);}
        //else if(gamepad1.dpad_down){svoRotate.setPosition(DPOSITION);}
        //else if(gamepad1.dpad_left){svoRotate.setPosition(SETPOSITION);}
        //else if(gamepad1.dpad_right){svoRotate.setPosition(SETPOSITION);}
        if (gamepad1.dpad_up){svopos+=0.005;}
        if (gamepad1.dpad_down){svopos+=-0.005;}
        svoTape.setPosition(svopos);

        if(gamepad1.right_trigger>0.5){svoCollect.setPosition(COLLECT);}
        else if(gamepad1.right_bumper){svoCollect.setPosition(REVERSECOLLECT);}
        else{svoCollect.setPosition(.5);}

        if(gamepad2.a){driveBase.turnInPlace(180);}
        telemetry.addData("pos",svopos);
        telemetry.update();
    }

    public void stop(){}

}