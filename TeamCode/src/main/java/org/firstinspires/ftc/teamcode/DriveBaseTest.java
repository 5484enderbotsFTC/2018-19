package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.DriveBase;


@Autonomous (name = "DriveBase Test")
public class DriveBaseTest extends LinearOpMode {
    DriveBase driveBase;



    @Override
    public void runOpMode() {
        driveBase = new DriveBase(hardwareMap,this);
        driveBase.initIMU();
        waitForStart();
        driveBase.driveEncoder(500);
        driveBase.turnInPlace(100);
    }
}