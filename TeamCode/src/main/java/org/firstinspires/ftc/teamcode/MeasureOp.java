package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.util.DriveBase;
import org.firstinspires.ftc.teamcode.util.Encoder;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Created by Sarahpambi76 on 12/10/18.
 */
@TeleOp (name="MeasureOp", group="K9bot")
public class MeasureOp extends OpMode {


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


    @Override
    public void init() {
        driveBase = new DriveBase(hardwareMap,false);
        svoHang = hardwareMap.servo.get("mtrHang");
        mtrCollect = hardwareMap.dcMotor.get("mtrCollect");
        svoRotate = hardwareMap.servo.get("svoRotate");
        svoCollect = hardwareMap.servo.get("svoCollect");
        //limExtendHigh = hardwareMap.touchSensor.get("limExtendHigh");
        //limExtendLow = hardwareMap.touchSensor.get("limExtendLow");
        svoTape = hardwareMap.servo.get("svoTapeRotate");
        encHang = new Encoder(mtrCollect);
    }

    public void start() {
    }

    public void loop() {
        double Y = gamepad1.left_stick_y;
        double X = gamepad1.right_stick_x;

        driveBase.drive(Y, X);
        if (gamepad1.dpad_up){posSvo =min(posSvo +0.005,1);}
        if (gamepad1.dpad_down){posSvo =max(posSvo -0.005,0);}
        svoRotate.setPosition(posSvo);
        telemetry.addData("pos", posSvo);

        if (gamepad1.dpad_up){posTape =min(posTape +0.005,1);}
        if (gamepad1.dpad_down){posTape =max(posTape -0.005,0);}
        svoTape.setPosition(posTape);
        telemetry.addData("tape pos", posTape);

        //if (gamepad1.dpad_left){svoHang.setPosition(0);}
        //if (gamepad1.dpad_right){svoHang.setPosition(1);}
        //telemetry.addData("hang pos", encHang.getEncValue());

        telemetry.update();
    }

    public void stop(){}

}