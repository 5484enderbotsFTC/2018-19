package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.utilRR.AnalogTouch;
import org.firstinspires.ftc.teamcode.utilRR.DriveBase;
import org.firstinspires.ftc.teamcode.utilRR.Encoder;

/**
 * Created by Sarahpambi76 on 12/10/18.
 */
@TeleOp (name="ClarksonTeleOp", group="K9bot")
public class ClarksonTeleOp extends OpMode {


    private DcMotor mtrHang;
    private DcMotor mtrExtend;
    private Servo svoRotate;
    private Servo svoCollect;
    private DriveBase driveBase;
    private Servo svoTapeRotate;
    private Encoder encHang;
    private Servo svoTapeExtend;
    private Servo svoDispenser;


    //private TouchSensor limHangLow;
    //private TouchSensor limHangHigh;
    //private TouchSensor limDispenseLow;
    //private TouchSensor limDispenseHigh;
    //private TouchSensor limExtendLow;
    //private TouchSensor limExtendHigh;
    private AnalogTouch limDispenseLow;
    private AnalogTouch limDispenseHigh;
    private AnalogTouch limExtendLow;
    private AnalogTouch limExtendHigh;


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
        svoTapeRotate = hardwareMap.servo.get("svoTape");
        svoTapeExtend = hardwareMap.servo.get("mtrTape");
        svoDispenser = hardwareMap.servo.get("svoDispenser");

        //limHangHigh = hardwareMap.touchSensor.get("limHangHigh");
        //limHangLow = hardwareMap.touchSensor.get("limHangLow");
        //limDispenseHigh = hardwareMap.touchSensor.get("limHDispenseHigh");
        //limDispenseLow = hardwareMap.touchSensor.get("limDispenseLow");
        //limExtendHigh = hardwareMap.touchSensor.get("limExtendHigh");
        //limExtendLow = hardwareMap.touchSensor.get("limExtendLow");
        limDispenseLow = new AnalogTouch(hardwareMap, "limDispenseLow");
        limDispenseHigh = new AnalogTouch(hardwareMap, "limDispenseHigh");
        limExtendLow = new AnalogTouch(hardwareMap, "limExtendLow");
        limExtendHigh = new AnalogTouch(hardwareMap, "limExtendHigh");

    }

    public void start() {
    }

    public void loop() {
        double Y = -gamepad1.left_stick_y;
        double X = gamepad1.right_stick_x;
        double posSvo = 0;

        driveBase.drive(Y, X);

        if(gamepad2.right_stick_y>0.5){// && !limHangHigh.isPressed()){
            mtrHang.setPower(1);}
        else if(gamepad2.right_stick_y<-0.5){// && !limHangLow.isPressed()){
            mtrHang.setPower(-1);}
        else{
            mtrHang.setPower(0);}

        if(gamepad2.left_trigger>0.5 && !limExtendHigh.isPressed()){
            mtrExtend.setPower(OUT);}
        else if(gamepad2.left_bumper && !limExtendLow.isPressed()){
            mtrExtend.setPower(IN);}
        else {
            mtrExtend.setPower(0);
        }

        if(gamepad2.left_stick_y>0.5 && !limDispenseHigh.isPressed()){
            svoDispenser.setPosition(1);}
        else if(gamepad2.left_stick_y<-0.5 && !limDispenseLow.isPressed()){
            svoDispenser.setPosition(0);}
        else{
            svoDispenser.setPosition(0.5);}

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
            svoTapeExtend.setPosition(0);}
        else{
            svoTapeExtend.setPosition(0.5);}

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
