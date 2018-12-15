package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.utilRR.DriveBase;

/**
 *
 * Created by Avery on 10/26/18.
 */
@Autonomous (name = "RochesterDepotAuto")
public class RochesterDepotAuto extends LinearOpMode {

    private DriveBase driveBase;
    private Servo svoHang;
    private Servo svoTape;
    private DigitalChannel limHangLow;
    private DigitalChannel limHangHigh;

    double DOWN = 1;
    double UP = 0;
    double tapeUp = 0;
    double tapeMid = 0;
    double tapeDown = 0;
@Override
    public void runOpMode(){
    driveBase = new DriveBase(hardwareMap,false);
    svoHang = hardwareMap.servo.get("mtrHang");
    svoTape = hardwareMap.servo.get("svoTape");
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
    //driveBase.driveEncoder(300);


//    while (limHangLow.getState()){
      //  svoHang.setPosition(DOWN);

  //  }
    //svoHang.setPosition(0.5);

    }
}
