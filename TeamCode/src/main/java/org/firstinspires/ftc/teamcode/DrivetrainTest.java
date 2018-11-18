package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.util.Encoder;

@Autonomous (name = "DriveTrainTest")
public class DrivetrainTest extends LinearOpMode {
    DcMotor mtrFR;
    DcMotor mtrBR;
    DcMotor mtrFL;
    DcMotor mtrBL;
    Encoder encFL;

    @Override
    public void runOpMode(){
        mtrBL = hardwareMap.dcMotor.get("mtrBL");
        mtrBR = hardwareMap.dcMotor.get("mtrBR");
        mtrFL = hardwareMap.dcMotor.get("mtrFL");
        mtrFR = hardwareMap.dcMotor.get("mtrFR");
    }
}
