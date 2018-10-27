package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Ted on 10/27/18.
 */
@Autonomous (name = "FarAutoV0")
public class FarAutoV0 extends LinearOpMode {
    DcMotor mtrFl;
    DcMotor mtrBl;
    DcMotor mtrFr;
    DcMotor mtrBr;
    DcMotor mtrCollect;
    Servo svoExtend;
    Servo svoRotate;

    double DOWN = 1;
    double UP = 0;
    double IN = 0;
    @Override
    public void runOpMode(){
    mtrBl = hardwareMap.dcMotor.get("mtrBl");
    mtrBr = hardwareMap.dcMotor.get("mtrBr");
    mtrFr = hardwareMap.dcMotor.get("mtrFr");
    mtrFl = hardwareMap.dcMotor.get("mtrFl");
    mtrCollect = hardwareMap.dcMotor.get("mtrCollect");
    svoExtend = hardwareMap.servo.get("svoExtend");
    svoRotate = hardwareMap.servo.get("svoRotate");
    waitForStart();
    DrivePower(-1, 0);
    sleep(800);
    svoRotate.setPosition(DOWN);
    DrivePower(1, 0);
    sleep(700);
    DrivePower(-1, 0);
    sleep(900);
    DrivePower(0,-1);
    sleep(500);
    DrivePower(-1,0);
    sleep(1000);
    DrivePower(0,-1);
    sleep(1000);
    DrivePower(-1,0);
    sleep(1500);
    DrivePower(0,0);

}
public void DrivePower(double X, double Y){
    double leftPower = X - Y;
    double rightPower = X + Y;
    mtrBl.setPower(leftPower);
    mtrBr.setPower(rightPower);
    mtrFl.setPower(leftPower);
    mtrFr.setPower(rightPower);
}
}
