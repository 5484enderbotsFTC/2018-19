package org.firstinspires.ftc.teamcode.util;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Dispenser {

    DcMotor mtrDispense;

    AnalogTouch limDispenseLow;
    AnalogTouch limDispenseHigh;

    public Dispenser(HardwareMap hardwareMap) {
        mtrDispense = hardwareMap.dcMotor.get("mtrDispense");
        limDispenseLow = new AnalogTouch(hardwareMap, "limDispenseLow");
        limDispenseHigh = new AnalogTouch(hardwareMap, "limDispenseHigh");


    }

    public void up(){
        if (!limDispenseHigh.isPressed()){
            mtrDispense.setPower(-1);
        } else {
            stop();
        }
    }
    public void down(){
        if (!limDispenseLow.isPressed()){
            mtrDispense.setPower(1);
        } else {
            stop();
        }
    }
    public void stop(){
        mtrDispense.setPower(0);
    }
}
