package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.utilRR.DriveBase;
import org.firstinspires.ftc.teamcode.utilRR.Encoder;


@Autonomous (name = "Encoder Test")
public class EncoderTest extends LinearOpMode {
    DcMotor mtr;
    Encoder enc;


    @Override
    public void runOpMode() {
        mtr = hardwareMap.dcMotor.get("mtr");
        enc = new Encoder(mtr);
        mtr.setPower(1);
        while(true){
            telemetry.addData("value",enc.getEncValue());
            telemetry.update();
            sleep(50);
        }
    }
}