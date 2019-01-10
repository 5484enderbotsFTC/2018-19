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
@TeleOp (name="RochesterTeleOp", group="K9bot")
public class ClarksonTeleOp extends OpMode {


    Servo svoHang;
    DcMotor mtrCollectSlide;
    Servo svoRotate;
    DcMotor mtrCollect;
    DriveBase driveBase;
    TouchSensor limHangLow;
    TouchSensor limHangHigh;
    Servo svoTape;
    Encoder encHang;
    DcMotor mtrTape;
    Servo svoDispenser;

    double posSvo = 0;
    double posTape = 0;
    static double UPWARD = 0;
    static double DOWNWARD = 1;
    static double IN = -1;
    static double OUT = 1;
    double DPOSITION = 0.55;
    double CPOSITION = 0.45;
    double SETPOSITION = 0.5;
    static double COLLECT = 1;
    static double REVERSECOLLECT = -1;


    @Override
    public void init() {
        driveBase = new DriveBase(hardwareMap,false);
        svoHang = hardwareMap.servo.get("mtrHang");
        mtrCollectSlide = hardwareMap.dcMotor.get("mtrCollectSlide");
        svoRotate = hardwareMap.servo.get("svoRotate");
        mtrCollect = hardwareMap.dcMotor.get("mtrCollect");
        //limExtendHigh = hardwareMap.touchSensor.get("limExtendHigh");
        //limExtendLow = hardwareMap.touchSensor.get("limExtendLow");
        svoTape = hardwareMap.servo.get("svoTape");
        encHang = new Encoder(mtrCollect);
        mtrTape = hardwareMap.dcMotor.get("mtrTape");
        svoDispenser = hardwareMap.servo.get("svoDispenser");
    }

    public void start() {
    }

    public void loop() {
        double Y = -gamepad1.left_stick_y;
        double X = gamepad1.right_stick_x;

        driveBase.drive(Y, X);

        if(gamepad2.right_stick_y>0.5){
            svoHang.setPosition(UPWARD);}
        else if(gamepad2.right_stick_y<-0.5){
            svoHang.setPosition(DOWNWARD);}
        else{
            svoHang.setPosition(0.5);}

        if(gamepad2.left_trigger>0.5){mtrCollectSlide.setPower(OUT);}
        else if(gamepad2.left_bumper){mtrCollectSlide.setPower(IN);}
        else {mtrCollectSlide.setPower(0);}

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

        if(gamepad2.right_trigger>0.5){mtrCollect.setPower(COLLECT);}
        else if(gamepad2.right_bumper){mtrCollect.setPower(REVERSECOLLECT);}
        else{mtrCollect.setPower(0);}

        if(gamepad1.left_bumper) {mtrTape.setPower(-1);}
        else{mtrTape.setPower(0);}

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