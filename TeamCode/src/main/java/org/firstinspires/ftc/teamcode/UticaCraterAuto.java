package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.util.Collector;
import org.firstinspires.ftc.teamcode.util.Dispenser;
import org.firstinspires.ftc.teamcode.util.DriveBase;
import org.firstinspires.ftc.teamcode.util.Functions;
import org.firstinspires.ftc.teamcode.util.Hang;
import org.firstinspires.ftc.teamcode.util.SamplingVision;
import org.firstinspires.ftc.teamcode.util.Tape;

/**
 * Created by Avery on 10/26/18.
 */
@Autonomous (name = "UticaCraterAuto")
public class UticaCraterAuto extends LinearOpMode {

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


        driveBase = new DriveBase(hardwareMap,this);
        dispenser = new Dispenser(hardwareMap);
        collector = new Collector(hardwareMap);
        hang = new Hang(hardwareMap);
        tape = new Tape(hardwareMap);

        driveBase.initIMU();

        samplingVision = new SamplingVision(hardwareMap);

        telemetry.addData("Status", "Initialised");
        telemetry.update();
        waitForStart();
        tape.rotateUp();
        telemetry.addData("Status", "Unhanging");
        telemetry.update();
        driveBase.drive(0.5,0);
        hang.up();
        sleep(2000);
        hang.down();
        sleep(600);
        hang.up();
        driveBase.drive(-1,0);
        sleep(500);
        hang.down();
        sleep(1500);
        hang.stop();
        driveBase.drive(0,0);
        sleep(1000);
        //sample
        driveBase.driveEncoder(50);
        hang.down();
        Functions.sample(this, driveBase, collector, samplingVision);
        //drive to wall
        driveBase.turnTank(65, -1, 1);
        driveBase.drive(0,0);
        sleep(100);
        driveBase.driveEncoder(750);
        driveBase.drive(0,0);
        sleep(100);
        driveBase.turnTank(55,-1, 1);
        //tape measure marker stuff
        tape.in();
        tape.rotateDown();
        sleep(9000);
        tape.rotateDown();
        tape.out();
        driveBase.driveEncoder(-700);
    }

    }
