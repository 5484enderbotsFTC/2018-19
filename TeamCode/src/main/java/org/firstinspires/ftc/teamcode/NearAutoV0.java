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
public class NearAutoV0 extends LinearOpMode {
    DcMotor mtrCollect;
    Servo svoExtend;
    Servo svoRotate;
    DriveBase DriveBase;

    double DOWN = 1;
    double UP = 0;
    double IN = 0;
@Override
    public void runOpMode(){
    DriveBase = new DriveBase(hardwareMap,false);
    mtrCollect = hardwareMap.dcMotor.get("mtrCollect");
    svoExtend = hardwareMap.servo.get("svoExtend");
    svoRotate = hardwareMap.servo.get("svoRotate");
    waitForStart();
    DriveBase.drive(-1, 0);
    sleep(800);
    svoRotate.setPosition(DOWN);
    DriveBase.drive(1, 0);
    sleep(700);
    DriveBase.drive(-1, 0);
    sleep(1500);
    DriveBase.drive(0,0);
    mtrCollect.setPower(1);
    DriveBase.drive(0.4, 0);
    sleep(2000);
    mtrCollect.setPower(0);
    DriveBase.drive(0,0);

}
}
