package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.AnalogTouch;
import org.firstinspires.ftc.teamcode.util.Dispenser;
import org.firstinspires.ftc.teamcode.util.DriveBase;
import org.firstinspires.ftc.teamcode.util.Encoder;
import org.firstinspires.ftc.teamcode.util.Hang;

import static org.firstinspires.ftc.teamcode.ClarksonNearAuto.TAPEIN;

/**
 * Created by Sarahpambi76 on 12/10/18.
 */
@TeleOp (name="UticaTeleOp", group="K9bot")
public class UticaTeleOp extends OpMode {

    private DriveBase driveBase;
    private Dispenser dispense;
    private Hang hang;

    private DcMotor mtrHang;
    private DcMotorSimple mtrExtend;
    private Servo svoRotate;
    private DcMotorSimple mtrCollect;
    private Servo svoTapeRotate;
    private Encoder encHang;
    private Servo svoTapeExtend;
    private DcMotor mtrDispense;

    //private TouchSensor limExtendLow;
    //private TouchSensor limExtendHigh;
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
        driveBase = new DriveBase(hardwareMap,false);
        dispense = new Dispenser(hardwareMap);
        hang = new Hang(hardwareMap);

        mtrDispense = hardwareMap.dcMotor.get("mtrDispense");
        mtrExtend = hardwareMap.get(DcMotorSimple.class,"mtrExtend");
        svoRotate = hardwareMap.servo.get("svoRotate");
        mtrCollect = hardwareMap.get(DcMotorSimple.class,"mtrCollect");
        svoTapeRotate = hardwareMap.servo.get("svoTapeRotate");
        svoTapeExtend = hardwareMap.servo.get("svoTapeExtend");


        //limHangHigh = hardwareMap.touchSensor.get("limHangHigh");
        //limHangLow = hardwareMap.touchSensor.get("limHangLow");
        //limExtendHigh = hardwareMap.touchSensor.get("limExtendHigh");
        //limExtendLow = hardwareMap.touchSensor.get("limExtendLow");

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

        if(gamepad1.y) {
            mtrDispense.setPower(-1);
        }
        else if(gamepad1.x) {
            mtrDispense.setPower(1);
        }
        else {
            mtrDispense.setPower(0);
        }


        if(gamepad2.right_stick_y>0.5 || gamepad1.a){// && !limHangHigh.isPressed())
             hang.up();}
        else if(gamepad2.right_stick_y<-0.5 || gamepad1.b){// && !limHangLow.isPressed()
              hang.down();}
        else {hang.stop();}

        if(gamepad1.left_trigger>0.5 && !limExtendHigh.isPressed()){
            mtrExtend.setPower(-1);
        }
        else if(gamepad1.left_bumper && !limExtendLow.isPressed()){
            mtrExtend.setPower(1); }
        else {
            mtrExtend.setPower(0);
        }

        if(gamepad2.a){driveBase.turnInPlace(180);}
        telemetry.update();
    }

    public void stop(){}

}
