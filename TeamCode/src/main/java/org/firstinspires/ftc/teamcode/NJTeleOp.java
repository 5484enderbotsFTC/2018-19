package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.util.DriveBase;
@TeleOp(name="NJTeleOp", group="K9bot")
public class NJTeleOp extends OpMode{
    org.firstinspires.ftc.teamcode.util.DriveBase DriveBase;


    @Override
    public void init() {
        DriveBase = new DriveBase(hardwareMap);
    }

    public void start() {
    }

    public void loop() {
        double X = gamepad1.left_stick_y;
        double Y = gamepad1.right_stick_x;
        DriveBase.drive(Y, X);
    }

    public void stop() {

    }
    public void incrementsvo(Servo svo, double change) {
        svo.setPosition(svo.getPosition() + change);
    }
}