package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Functions {
    public static void sample(LinearOpMode opMode, DriveBase driveBase, Collector collector, SamplingVision samplingVision){
        int posMineral = samplingVision.getMineral2XLeft();
        sample(opMode, driveBase, collector, posMineral);

    }
    public static void sample(LinearOpMode opMode, DriveBase driveBase, Collector collector, int posMineral){
        Telemetry telemetry = opMode.telemetry;


        telemetry.addData("Status", "Sampling");
        telemetry.addData("Mineral position", posMineral);
        telemetry.update();
        int signTurn;
        signTurn = -1+posMineral;
        if(posMineral==-1){
            signTurn = 0;
        }

        driveBase.turnInPlace(30*signTurn);
        collector.collectorOut();
        collector.collectOut();
        while(!collector.slideOut() && opMode.opModeIsActive()){
            collector.slideOut();
        }
        collector.slideStop();
        while(!collector.slideIn() && opMode.opModeIsActive()){
            collector.slideIn();
        }
        collector.slideStop();
        collector.collectStop();
        collector.collectorIn();
        driveBase.turnInPlace(-30*signTurn);
    }
}
