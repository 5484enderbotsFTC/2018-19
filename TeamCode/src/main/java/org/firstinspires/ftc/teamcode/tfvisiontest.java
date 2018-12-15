package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.utilRR.SamplingVision;


@Autonomous (name = "TensorFlow Vision Test")
public class tfvisiontest extends LinearOpMode {
    SamplingVision samplingVision;

    @Override
    public void runOpMode(){

        samplingVision = new SamplingVision(hardwareMap);

        while(opModeIsActive()){
            telemetry.addData("Mineral position", samplingVision.getMineral());
            telemetry.update();
        }
    }



}