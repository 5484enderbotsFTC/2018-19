package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Sarahpambi76 on 12/10/18.
 */
@TeleOp (name="SarahRoverTeleop", group="K9bot")
public class SarahRoverTeleop extends OpMode {

    DcMotor mtrFL;
    DcMotor mtrFR;
    DcMotor mtrBL;
    DcMotor mtrBR;
    DcMotor mtrHang;
    DcMotor mtrCollect;
    Servo svoRotate;
    Servo svoCollect;

    double UPWARD = 1;
    double DOWNWARD = -1;
    double IN = -1;
    double OUT = 1;
    double ANGLEUP = 0.55;
    double ANGLEDOWN = 0.45;
    double COLLECT = ANGLEUP;
    double REVERSECOLLECT = ANGLEDOWN;


    @Override
    public void init() {
        mtrFL = hardwareMap.dcMotor.get("mtrFL");
        mtrFR = hardwareMap.dcMotor.get("mtrFR");
        mtrBL = hardwareMap.dcMotor.get("mtrBL");
        mtrBR = hardwareMap.dcMotor.get("mtrBR");
        mtrHang = hardwareMap.dcMotor.get("mtrHang");
        mtrCollect = hardwareMap.dcMotor.get("mtrCollect");
        svoRotate = hardwareMap.servo.get("svoRotate");
        svoCollect = hardwareMap.servo.get("svoCollect");

    }

    public void start() {
    }

    public void loop() {
        double Y = gamepad1.left_stick_y;
        double X = gamepad1.left_stick_x;

        double leftPower = Y - X;
        double rightPower = Y + X;

        mtrFL.setPower(leftPower);
        mtrFR.setPower(rightPower);
        mtrBL.setPower(leftPower);
        mtrBR.setPower(rightPower);

        if(gamepad1.dpad_up){mtrHang.setPower(UPWARD);}
        else if(gamepad1.dpad_down){mtrHang.setPower(DOWNWARD);}
        else{mtrHang.setPower(0);}

        if(gamepad1.right_stick_y>0.5){mtrCollect.setPower(OUT);}
        else if(gamepad1.right_stick_y<-0.5){mtrCollect.setPower(IN);}

        if(gamepad2.dpad_up) {svoRotate.setPosition(ANGLEUP);}
        else if(gamepad2.dpad_down) {svoRotate.setPosition(ANGLEDOWN);}
        else {svoRotate.setPosition(.50);}

        if(gamepad2.a){svoCollect.setPosition(COLLECT);}
        else if(gamepad2.b){svoCollect.setPosition(REVERSECOLLECT);}
        else{svoCollect.setPosition(.5);}

    }

    public void stop(){}

}