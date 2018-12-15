package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.utilRR.DriveBase;

/**
 * Created by Ted on 10/27/18.
 */
@Autonomous (name = "NJFarAuto")
public class NJFarAuto extends LinearOpMode {
    DriveBase DriveBase;
    DcMotor mtrCollect;
    Servo svoExtend;
    Servo svoRotate;

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
    DriveBase.drive(0, -1);
    sleep(800);
    svoRotate.setPosition(DOWN);
    DriveBase.drive(0, 1);
    sleep(700);
    DriveBase.drive(0, -1);
    sleep(900);
    DriveBase.drive(-1, 0);
    sleep(500);
    DriveBase.drive(0, -1);
    sleep(1000);
    DriveBase.drive(-1, 0);
    sleep(1000);
    DriveBase.drive(0, -1);
    sleep(1500);
    DriveBase.drive(0, 0);

}
}
