package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.utilRR.DriveBase;
import org.firstinspires.ftc.teamcode.utilRR.Encoder;

import static java.lang.Math.max;
import static java.lang.Math.min;

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
    Encoder encHang;

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
    static double REVERSECOLLECT = 0;


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
        encHang = new Encoder(mtrCollect);
    }

    public void start() {
    }

    public void loop() {
        double Y = -gamepad1.left_stick_y;
        double X = gamepad1.right_stick_x;

        driveBase.drive(Y, X);

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
        if (gamepad1.dpad_up){posSvo=0.635;} else if (gamepad1.dpad_down){posSvo=0;} else if (gamepad1.dpad_left||gamepad1.dpad_right) {posSvo=0.235;}
        svoRotate.setPosition(posSvo);
        telemetry.addData("pos", posSvo);

        //if (gamepad1.dpad_left){svoHang.setPosition(0);}
        //if (gamepad1.dpad_right){svoHang.setPosition(1);}
        //telemetry.addData("hang pos", encHang.getEncValue());

        if(gamepad1.right_trigger>0.5){svoCollect.setPosition(COLLECT);}
        else if(gamepad1.right_bumper){svoCollect.setPosition(REVERSECOLLECT);}
        else{svoCollect.setPosition(.5);}

        if(gamepad2.a){driveBase.turnInPlace(180);}
        telemetry.update();
    }

    public void stop(){}

}