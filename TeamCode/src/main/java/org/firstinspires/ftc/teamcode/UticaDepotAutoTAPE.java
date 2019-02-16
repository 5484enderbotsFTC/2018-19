package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Collector;
import org.firstinspires.ftc.teamcode.util.Dispenser;
import org.firstinspires.ftc.teamcode.util.DriveBase;
import org.firstinspires.ftc.teamcode.util.Hang;
import org.firstinspires.ftc.teamcode.util.SamplingVision;
import org.firstinspires.ftc.teamcode.util.Tape;

/**
 * Created by Avery on 10/26/18.
 */
@Autonomous (name = "UticaDepotAutoTAPE")
public class UticaDepotAutoTAPE extends LinearOpMode {

    private DriveBase driveBase;
    private Dispenser dispenser;
    private Collector collector;
    private Hang hang;
    private Tape tape;

    SamplingVision samplingVision;



    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialising");
        telemetry.update();

        driveBase = new DriveBase(hardwareMap,false);
        dispenser = new Dispenser(hardwareMap);
        collector = new Collector(hardwareMap);
        hang = new Hang(hardwareMap);
        tape = new Tape(hardwareMap);

        driveBase.initIMU();

        samplingVision = new SamplingVision(hardwareMap);

        telemetry.addData("Status", "Initialised");
        telemetry.update();
        waitForStart();

        telemetry.addData("Status", "Unhanging");
        telemetry.update();
        driveBase.drive(0.5,0);
        hang.up();
        sleep(1500);
        hang.down();
        sleep(600);
        hang.up();
        driveBase.drive(-1,0);
        sleep(500);
        hang.down();
        sleep(1000);
        hang.stop();
        driveBase.drive(0,0);
        int posMineral = samplingVision.getMineral2XLeft();
        telemetry.addData("Status", "Sampling");
        telemetry.addData("Mineral position", posMineral);
        telemetry.update();
        int signTurn;
        signTurn = -1+posMineral;
        tape.in();
        tape.rotateMid();
        sleep(9000);
        tape.rotateDown();
        tape.out();
        sleep(1000);
        tape.rotateUp();
        telemetry.addData("Status","Placing marker");
        telemetry.update();
        driveBase.driveEncoder(50, this);
        hang.down();
        hang.stop();
        //sample
        driveBase.turnInPlace(20*signTurn);
        collector.collectorOut();
        collector.collectOut();
        while(!collector.slideOut() && opModeIsActive()){
            collector.slideOut();
        }
        collector.slideStop();
        while(!collector.slideIn() && opModeIsActive()){
            collector.slideIn();
        }
        collector.slideStop();
        collector.collectStop();
        collector.collectorIn();
        driveBase.turnInPlace(-30*signTurn);

        //drive to crater
        driveBase.turnTank(75,-1, 1);
        driveBase.drive(0,0);
        sleep(100);
        driveBase.driveEncoder(300,this);
        driveBase.drive(0,0);
        sleep(100);
        driveBase.turnTank(20,-1, 1);


        tape.in();
        tape.rotateMid();
        sleep(9000);



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
