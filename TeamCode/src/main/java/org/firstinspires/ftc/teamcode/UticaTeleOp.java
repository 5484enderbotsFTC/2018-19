package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.util.Collector;
import org.firstinspires.ftc.teamcode.util.Dispenser;
import org.firstinspires.ftc.teamcode.util.DriveBase;
import org.firstinspires.ftc.teamcode.util.Encoder;
import org.firstinspires.ftc.teamcode.util.Hang;
import org.firstinspires.ftc.teamcode.util.Tape;

/**
 * Created by Sarahpambi76 on 12/10/18.
 */
@TeleOp (name="UticaTeleOp", group="K9bot")
public class UticaTeleOp extends OpMode {

    private DriveBase driveBase;
    private Dispenser dispenser;
    private Collector collector;
    private Hang hang;
    private Tape tape;

    @Override
    public void init() {
        driveBase = new DriveBase(hardwareMap,false);
        dispenser = new Dispenser(hardwareMap);
        collector = new Collector(hardwareMap);
        hang = new Hang(hardwareMap);
        tape = new Tape(hardwareMap);

    }

    public void start() {
        tape.in();
    }

    public void loop() {
        double Y = -gamepad1.left_stick_y;
        double X = gamepad1.right_stick_x;


        driveBase.drive(Y, X);

        if(gamepad1.y || gamepad2.right_stick_y>0.5) {
            dispenser.down();
        }
        else if(gamepad1.x || gamepad2.right_stick_y<-0.5) {
            dispenser.up();
        }
        else {
            dispenser.stop();
        }


        if(gamepad2.right_stick_y>0.5 || gamepad1.a){// && !limHangHigh.isPressed())
             hang.down();}
        else if(gamepad2.right_stick_y<-0.5 || gamepad1.b){// && !limHangLow.isPressed()
              hang.up();}
        else {hang.stop();}

        if(gamepad1.left_bumper){
            collector.slideIn();
        }
        else if(gamepad1.left_trigger>0.5){
            collector.slideOut();
        }
        else {
            collector.slideStop();
        }


        if (gamepad1.dpad_up){
            collector.collectorOut();
        } else if (gamepad1.dpad_down){
            collector.collectorIn();
        } else if (gamepad1.dpad_left||gamepad2.dpad_right) {
            collector.collectorMid();
        }

        if(gamepad1.right_trigger>0.5){
            collector.collectIn();
        }
        else if(gamepad1.right_bumper){
            collector.collectOut();}
        else{
            collector.collectStop();}
        telemetry.addData("hang encoder",hang.encHang.getEncValue());
        telemetry.update();
    }

    public void stop(){}

}
