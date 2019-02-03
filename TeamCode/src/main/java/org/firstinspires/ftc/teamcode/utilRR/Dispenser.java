package org.firstinspires.ftc.teamcode.utilRR;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Dispenser {

    DcMotorSimple mtrDispense;

    AnalogTouch limDispenseLow;
    AnalogTouch limDispenseHigh;

    public Dispenser(HardwareMap hardwareMap) {
        mtrDispense = hardwareMap.get(DcMotorSimple.class,"mtrDispense");
        limDispenseLow = new AnalogTouch(hardwareMap, "limDispenseLow");
        limDispenseHigh = new AnalogTouch(hardwareMap, "limDispenseHigh");


    }

    public void up(){
        /*DispenseHigh = limDispenseHigh.isPressed() ? 0.5 : 1;
        DispenseLow = limDispenseLow.isPressed() ? 0.5 : 0;
        mtrDispense.setPower(
                //Math.min(
                //Math.max(
                gamepad2.left_stick_y//,
                //        DispenseLow),
                //DispenseHigh)
        );
        if(gamepad1.y){
            mtrDispense.setPower(-1);
        } else if(gamepad1.x){
            mtrDispense.setPower(1);
        }
        */
        mtrDispense.setPower(-1);
    }
    public void down(){
        mtrDispense.setPower(1);
    }
    public void stop(){
        mtrDispense.setPower(0);
    }
}
