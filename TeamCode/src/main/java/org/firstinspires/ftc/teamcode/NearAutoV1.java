package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utilRR.DriveBase;

/**
 * Created by Avery on 10/26/18.
 */
@Autonomous (name = "NearAutoV0")
public class NearAutoV1 extends LinearOpMode {
    DcMotor mtrCollect;
    Servo svoExtend;
    Servo svoRotate;
    DriveBase driveBase;

    double DOWN = 1;
    double UP = 0;
    double IN = 0;
@Override
    public void runOpMode(){
    driveBase = new DriveBase(hardwareMap,false);
    mtrCollect = hardwareMap.dcMotor.get("mtrCollect");
    svoExtend = hardwareMap.servo.get("svoExtend");
    svoRotate = hardwareMap.servo.get("svoRotate");
    waitForStart();


}
}
