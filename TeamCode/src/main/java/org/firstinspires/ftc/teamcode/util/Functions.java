package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;


public class Functions {
    public static void sample(LinearOpMode opMode, SamplingVision samplingVision, DriveBase driveBase, Collector collector){
        Telemetry telemetry = opMode.telemetry;

        int posMineral = samplingVision.getMineral2XLeft();
        telemetry.addData("Status", "Sampling");
        telemetry.addData("Mineral position", posMineral);
        telemetry.update();
        int signTurn;
        signTurn = -1+posMineral;

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
