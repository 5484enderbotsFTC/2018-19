package org.firstinspires.ftc.teamcode.util;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Sarahpambi76 on 12/21/17.
 */
@Disabled
@TeleOp (name = "JogMode")
public class JogMode extends OpMode{
    DcMotor mtrFL;
    DcMotor mtrFR;
    DcMotor mtrBL;
    DcMotor mtrBR;

    public void init_loop() {

    }

    @Override
    public void init() {

        mtrFL = hardwareMap.dcMotor.get("mtrFL");
        mtrFR = hardwareMap.dcMotor.get("mtrFR");
        mtrBL = hardwareMap.dcMotor.get("mtrBL");
        mtrBR = hardwareMap.dcMotor.get("mtrBR");

        mtrFL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrFR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrBL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        mtrBR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);





    }

    public void start() {

    }

    @Override
    public void loop() {

    }

    public void stop() {

    }



}
