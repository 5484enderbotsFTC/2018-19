package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.utilRR.AnalogTouch;
import org.firstinspires.ftc.teamcode.utilRR.DriveBase;
import org.firstinspires.ftc.teamcode.utilRR.Encoder;
import org.firstinspires.ftc.teamcode.utilRR.SamplingVision;

/**
 * Created by Avery on 10/26/18.
 */
@Autonomous (name = "UticaNearAuto")
public class UticaNearAuto extends LinearOpMode {
    private DcMotor mtrHang;
    private DcMotor mtrExtend;
    private Servo svoRotate;
    private DcMotorSimple mtrCollect;
    private DriveBase driveBase;
    private Servo svoTapeRotate;
    private Encoder encHang;
    private Servo svoTapeExtend;
    private DcMotorSimple mtrDispense;


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
        mtrCollect = hardwareMap.get(DcMotorSimple.class,"mtrCollect");
        svoTapeRotate = hardwareMap.servo.get("svoTapeRotate");
        svoTapeExtend = hardwareMap.servo.get("svoTapeExtend");
        mtrDispense = hardwareMap.get(DcMotorSimple.class,"mtrDispense");
        driveBase.initIMU();
        samplingVision = new SamplingVision(hardwareMap);

        telemetry.addData("Status", "Initialised");
        telemetry.update();
        waitForStart();

        telemetry.addData("Status", "Unhanging");
        telemetry.update();
        //mtrHang.setPower(1);
        sleep(4000);
        mtrHang.setPower(0);
        int posMineral = samplingVision.getMineral2XLeft();

        telemetry.addData("Status", "Sampling");
        telemetry.addData("Mineral position", posMineral);
        telemetry.update();


        int signTurn;
        signTurn = -1+posMineral;
        driveBase.driveEncoder(200, this);
        driveBase.turnInPlace(30*signTurn);
        svoRotate.setPosition(0.61);
        driveBase.driveEncoder(500, this);
        driveBase.driveEncoder(-500, this);
        driveBase.turnInPlace(-30*signTurn-90);
        sleep(100);
        svoTapeRotate.setPosition(TAPEDOWN);
        svoTapeExtend.setPosition(TAPEOUT);
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
