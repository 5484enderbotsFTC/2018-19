package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.AnalogTouch;
import org.firstinspires.ftc.teamcode.util.DriveBase;
import org.firstinspires.ftc.teamcode.util.Encoder;

import static org.firstinspires.ftc.teamcode.ClarksonNearAuto.TAPEIN;

/**
 * Created by Sarahpambi76 on 12/10/18.
 */
@TeleOp (name="ClarksonTeleOp", group="K9bot")
public class ClarksonTeleOp extends OpMode {


    private DcMotor mtrHang;
    private DcMotor mtrExtend;
    private Servo svoRotate;
    private DcMotorSimple mtrCollect;
    private DriveBase driveBase;
    private Servo svoTapeRotate;
    private Encoder encHang;
    private Servo svoTapeExtend;
    private DcMotorSimple mtrDispense;


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


    static double UPWARD = 1;
    static double DOWNWARD = 0;
    static double IN = 1;
    static double OUT = -1;
    static double COLLECTOROUT = 0.61;
    static double COLLECTORUP = 0.47;
    static double COLLECTORIN = 0;
    static double COLLECT = 1;
    static double REVERSECOLLECT = 0;
    double posSvo = 0;
    double DispenseHigh;
    double DispenseLow;

    @Override
    public void init() {
        driveBase = new DriveBase(hardwareMap);
        mtrHang = hardwareMap.dcMotor.get("mtrHang");
        mtrExtend = hardwareMap.dcMotor.get("mtrExtend");
        svoRotate = hardwareMap.servo.get("svoRotate");
        mtrCollect = hardwareMap.get(DcMotorSimple.class,"mtrCollect");
        svoTapeRotate = hardwareMap.servo.get("svoTapeRotate");
        svoTapeExtend = hardwareMap.servo.get("svoTapeExtend");
        mtrDispense = hardwareMap.get(DcMotorSimple.class,"mtrDispense");

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
        svoTapeExtend.setPosition(TAPEIN);
    }

    public void loop() {
        double Y = -gamepad1.left_stick_y;
        double X = gamepad1.right_stick_x;


        driveBase.drive(Y, X);

        if(gamepad2.right_stick_y>0.5 || gamepad1.a){// && !limHangHigh.isPressed()){
            mtrHang.setPower(1);}
        else if(gamepad2.right_stick_y<-0.5 || gamepad1.b){// && !limHangLow.isPressed()){
            mtrHang.setPower(-1);}
        else{
            mtrHang.setPower(0);}

        if(gamepad1.left_trigger>0.5 && !limExtendHigh.isPressed()){
            mtrExtend.setPower(OUT);
        }
        else if(gamepad1.left_bumper && !limExtendLow.isPressed()){
            mtrExtend.setPower(IN); }
        else {
            mtrExtend.setPower(0);
        }

        //if(gamepad2.left_stick_y<.5 && !limDispenseHigh.isPressed()){
        //    mtrDispense.setPosition(1);}
        //else if(gamepad2.left_stick_y>-0.5 && !limDispenseLow.isPressed()){
        //    mtrDispense.setPosition(0);}
        //else{
        //    mtrDispense.setPosition(0.5);}
        DispenseHigh = limDispenseHigh.isPressed() ? 0.5 : 1;
        DispenseLow = limDispenseLow.isPressed() ? 0.5 : 0;
        mtrDispense.setPower(
                //Math.min(
                        //Math.max(
                                gamepad2.left_stick_y//,
                        //        DispenseLow),
                        //DispenseHigh)
        );
        if(gamepad1.y){
            mtrDispense.setPower(-1);
        } else if(gamepad1.x){
            mtrDispense.setPower(1);
        }

        //if (gamepad2.dpad_up){posSvo=0.61;} else if (gamepad2.dpad_down){posSvo=0;} else if (gamepad2.dpad_left||gamepad2.dpad_right) {posSvo=0.235;}
        if (gamepad1.dpad_up && posSvo<=1){posSvo+=0.02;} else if (gamepad1.dpad_down && posSvo>=0){posSvo-=0.02;} else if (gamepad1.dpad_left||gamepad1.dpad_right) {}
        svoRotate.setPosition(posSvo);
        telemetry.addData("pos", posSvo);

        //if (gamepad1.dpad_left){svoHang.setPosition(0);}
        //if (gamepad1.dpad_right){svoHang.setPosition(1);}
        //telemetry.addData("hang pos", encHang.getEncValue());

        //if(gamepad1.right_trigger>0.5){mtrCollect.setPosition(COLLECT);}
        //else if(gamepad1.right_bumper){mtrCollect.setPosition(REVERSECOLLECT);}
        //else{mtrCollect.setPosition(0.5);}

        if(gamepad1.right_trigger>0.5){
            mtrCollect.setPower(1);}
        else if(gamepad1.right_bumper){
            mtrCollect.setPower(-1);}
        else{
            mtrCollect.setPower(0);}

        if(gamepad1.left_bumper) {
            svoTapeExtend.setPosition(0.08);}
        else{
            svoTapeExtend.setPosition(0.08);}


        if(gamepad2.a){driveBase.turnInPlace(180);}
        telemetry.update();
    }

    public void stop(){}

}
