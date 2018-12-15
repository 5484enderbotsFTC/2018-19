package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utilRR.DriveBase;

/**
 * Created by Avery on 10/26/18.
 */
@Autonomous (name = "RochesterDepotAuto")
public class RochesterDepotAuto extends LinearOpMode {

    DriveBase driveBase;
    Servo svoTape;
    double DOWN = 1;
    double UP = 0;
    double IN = 0;
@Override
    public void runOpMode(){
    driveBase = new DriveBase(hardwareMap,false);
    svoTape = hardwareMap.servo.get("svoTape");
    waitForStart();


}
}
