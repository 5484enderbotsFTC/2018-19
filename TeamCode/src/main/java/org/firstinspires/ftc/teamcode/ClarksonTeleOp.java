package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.utilRR.DriveBase;
import org.firstinspires.ftc.teamcode.utilRR.Encoder;

/**
 * Created by Sarahpambi76 on 12/10/18.
 */
@TeleOp (name="ClarksonTeleOp", group="K9bot")
public class ClarksonTeleOp extends OpMode {


    DcMotor mtrHang;
    DcMotor mtrExtend;
    Servo svoRotate;
    Servo svoCollect;
    DriveBase driveBase;
    Servo svoTapeRotate;
    Encoder encHang;
    Servo svoTapeExtend;
    Servo svoDispenser;


    TouchSensor limExtendLow;
    TouchSensor limExtendHigh;
    TouchSensor limHangLow;
    TouchSensor limHangHigh;

    double posSvo = 0;
    static double UPWARD = 0;
    static double DOWNWARD = 1;
    static double IN = -1;
    static double OUT = 1;
    double DPOSITION = 0.55;
    double CPOSITION = 0.45;
    double SETPOSITION = 0.5;
    static double COLLECT = 1;
    static double REVERSECOLLECT = 0;


    @Override
    public void init() {
        driveBase = new DriveBase(hardwareMap,false);
        mtrHang = hardwareMap.dcMotor.get("mtrHang");
        mtrExtend = hardwareMap.dcMotor.get("mtrExtend");
        svoRotate = hardwareMap.servo.get("svoRotate");
        svoCollect = hardwareMap.servo.get("svoCollect");
        //limExtendHigh = hardwareMap.touchSensor.get("limExtendHigh");
        //limExtendLow = hardwareMap.touchSensor.get("limExtendLow");
        svoTape = hardwareMap.servo.get("svoTape");
        mtrTape = hardwareMap.dcMotor.get("mtrTape");
        svoDispenser = hardwareMap.servo.get("svoDispenser");

        limExtendHigh = hardwareMap.touchSensor.get("limExtendHigh");
        limExtendLow = hardwareMap.touchSensor.get("limExtendLow");
        limHangHigh = hardwareMap.touchSensor.get("limHangHigh");
        limHangLow = hardwareMap.touchSensor.get("limHangLow");
    }

    public void start() {
    }

    public void loop() {
        double Y = -gamepad1.left_stick_y;
        double X = gamepad1.right_stick_x;

        driveBase.drive(Y, X);

        if(gamepad2.right_stick_y>0.5){
            mtrHang.setPower(1);}
        else if(gamepad2.right_stick_y<-0.5){
            mtrHang.setPower(-1);}
        else{
            mtrHang.setPower(0);}

        if(gamepad2.left_trigger>0.5){
            mtrExtend.setPower(OUT);}
        else if(gamepad2.left_bumper){
            mtrExtend.setPower(IN);}
        else {
            mtrExtend.setPower(0);}

        //if(gamepad1.dpad_up){svoRotate.setPosition(CPOSITION);}
        //else if(gamepad1.dpad_down){svoRotate.setPosition(DPOSITION);}
        //else if(gamepad1.dpad_left){svoRotate.setPosition(SETPOSITION);}
        //else if(gamepad1.dpad_right){svoRotate.setPosition(SETPOSITION);}
        if (gamepad2.dpad_up){posSvo=0.635;} else if (gamepad2.dpad_down){posSvo=0;} else if (gamepad2.dpad_left||gamepad2.dpad_right) {posSvo=0.235;}
        svoRotate.setPosition(posSvo);
        telemetry.addData("pos", posSvo);

        //if (gamepad1.dpad_left){svoHang.setPosition(0);}
        //if (gamepad1.dpad_right){svoHang.setPosition(1);}
        //telemetry.addData("hang pos", encHang.getEncValue());

        if(gamepad2.right_trigger>0.5){svoCollect.setPosition(COLLECT);}
        else if(gamepad2.right_bumper){svoCollect.setPosition(REVERSECOLLECT);}
        else{svoCollect.setPosition(0);}

        if(gamepad1.left_bumper) {
            svoTapeExtend.setPower(-1);}
        else{
            svoTapeExtend.setPower(0);}

        if(gamepad2.left_stick_y>0.5){
            svoDispenser.setPosition(UPWARD);
        }
        else if(gamepad2.left_stick_y<-0.5){
            svoDispenser.setPosition(DOWNWARD);
        }
        else {svoDispenser.setPosition(0.5);}

        if(gamepad2.a){driveBase.turnInPlace(180);}
        telemetry.update();
    }

    public void stop(){}

}