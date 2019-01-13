package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utilRR.AnalogTouch;
import org.firstinspires.ftc.teamcode.utilRR.DriveBase;
import org.firstinspires.ftc.teamcode.utilRR.Encoder;
import org.firstinspires.ftc.teamcode.utilRR.SamplingVision;

/**
 * Created by Avery on 10/26/18.
 */
@Autonomous (name = "ClarksonNearAuto")
public class ClarksonNearAuto extends LinearOpMode {
    private DcMotor mtrHang;
    private DcMotor mtrExtend;
    private Servo svoRotate;
    private Servo svoCollect;
    private DriveBase driveBase;
    private Servo svoTapeRotate;
    private Encoder encHang;
    private Servo svoTapeExtend;
    private Servo svoDispense;


    //private TouchSensor limHangLow;
    //private TouchSensor limHangHigh;
    //private TouchSensor limDispenseLow;
    //private TouchSensor limDispenseHigh;
    //private TouchSensor limExtendLow;
    //private TouchSensor limExtendHigh;
    private AnalogTouch limDispenseLow;
    private AnalogTouch limDispenseHigh;
    private AnalogTouch limExtendLow;
    private AnalogTouch limExtendHigh;

    SamplingVision samplingVision;


    static double UPWARD = 1;
    static double DOWNWARD = 0;
    static double IN = -1;
    static double OUT = 1;
    double DPOSITION = 0.55;
    double CPOSITION = 0.45;
    double SETPOSITION = 0.5;
    static double COLLECT = 1;
    static double REVERSECOLLECT = 0;
    static double TAPEIN = 0.08;
    static double TAPEOUT = 0.4;
    static double TAPEDOWN = 0.43;
    static double TAPEUP = 0.73;



    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialising");
        telemetry.update();

        driveBase = new DriveBase(hardwareMap,false);
        mtrHang = hardwareMap.dcMotor.get("mtrHang");
        mtrExtend = hardwareMap.dcMotor.get("mtrExtend");
        svoRotate = hardwareMap.servo.get("svoRotate");
        svoCollect = hardwareMap.servo.get("svoCollect");
        svoTapeRotate = hardwareMap.servo.get("svoTapeRotate");
        svoTapeExtend = hardwareMap.servo.get("svoTapeExtend");
        svoDispense = hardwareMap.servo.get("svoDispense");
        driveBase.initIMU();
        samplingVision = new SamplingVision(hardwareMap);

        telemetry.addData("Status", "Initialised");
        telemetry.update();
        waitForStart();

        int posMineral = samplingVision.getMineral();
        telemetry.addData("Status", "Unhanging");
        telemetry.addData("Mineral position", posMineral);
        telemetry.update();
        mtrHang.setPower(1);
        sleep(4000);
        mtrHang.setPower(0);

        telemetry.addData("Status", "Sampling");
        telemetry.addData("Mineral position", posMineral);
        telemetry.update();

        driveBase.drive(1,0);
        svoRotate.setPosition(0.8);
        sleep(200);
        driveBase.drive(0,0);

        driveBase.turnInPlace(30-(posMineral*30));
        driveBase.drive(1,0);
        sleep(1500);
        driveBase.drive(-1,0);
        svoRotate.setPosition(0);
        sleep(1500);
        driveBase.drive(0,0);
        svoTapeRotate.setPosition(TAPEDOWN);
        svoTapeExtend.setPosition(TAPEOUT);
        driveBase.turnInPlace(-30+(posMineral*30));
        sleep(20000);

/*
        driveBase.drive(-1, 0);
        sleep(800);
        svoTapeRotate.setPosition(TAPEDOWN);
        driveBase.drive(1, 0 );
        sleep(700);
        driveBase.drive(-1, 0);
        sleep(1500);
        driveBase.drive(0, 0);
        svoTapeExtend.setPosition(TAPEOUT);
        driveBase.drive(0.4, 0);
        sleep(2000);
        svoTapeExtend.setPosition(TAPEIN);
        driveBase.drive(0, 0);
        */
    }
}
