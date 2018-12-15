package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utilRR.DriveBase;

/**
 *
 * Created by Avery on 10/26/18.
 */
@Autonomous (name = "RochesterAutoCrater")
public class RochesterAutoCrater extends LinearOpMode {

    private DriveBase driveBase;
    private Servo svoHang;
    private Servo svoTape;
    private DcMotor mtrTape;
    private DigitalChannel limHangLow;
    private DigitalChannel limHangHigh;

    double DOWN = 1;
    double UP = 0;
    double tapeUp = 0.8;
    double tapeMid = 0.48;
    double tapeDown = 0;
@Override
    public void runOpMode(){
    driveBase = new DriveBase(hardwareMap,false);
    svoHang = hardwareMap.servo.get("mtrHang");
    svoTape = hardwareMap.servo.get("svoTape");
    mtrTape = hardwareMap.dcMotor.get("mtrTape");
    limHangLow = hardwareMap.get(DigitalChannel.class, "limHangLow");
    limHangHigh = hardwareMap.get(DigitalChannel.class, "limHangHigh");


    limHangLow.setMode(DigitalChannel.Mode.INPUT);
    limHangHigh.setMode(DigitalChannel.Mode.INPUT);

    waitForStart();
//    while (limHangHigh.getState()){
        svoHang.setPosition(UP);
        sleep(4000);
//    }
    svoHang.setPosition(0.5);
    driveBase.drive(1, 0);
    sleep(1000);
    driveBase.drive(0, 0);



//    while (limHangLow.getState()){
      //  svoHang.setPosition(DOWN);

  //  }
    //svoHang.setPosition(0.5);

    }
}
