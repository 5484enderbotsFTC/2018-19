package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Collector {
    DcMotorSimple mtrSlide;
    DcMotorSimple mtrCollect;
    Servo svoRotate;

    AnalogTouch limExtendLow;
    AnalogTouch limExtendHigh;

    public Collector(HardwareMap hardwareMap){
        mtrSlide = hardwareMap.get(DcMotorSimple.class,"mtrExtend");
        svoRotate = hardwareMap.servo.get("svoRotate");
        mtrCollect = hardwareMap.get(DcMotorSimple.class,"mtrCollect");
        limExtendLow = new AnalogTouch(hardwareMap, "limExtendLow");
        limExtendHigh = new AnalogTouch(hardwareMap, "limExtendHigh");
    }

    public boolean slideIn(){
        if (!limExtendHigh.isPressed()){
            mtrSlide.setPower(-1);
            return(false);
        } else {
            slideStop();
            return(true);
        }
    }
    public boolean slideOut(){
        if (!limExtendLow.isPressed()){
            mtrSlide.setPower(1);
            return(false);
        } else {
            slideStop();
            return(true);
        }
    }
    public void slideStop(){
        mtrSlide.setPower(0);
    }

    public void collectIn(){
        mtrCollect.setPower(1);
    }
    public void collectOut(){
        mtrCollect.setPower(-1);
    }
    public void collectStop(){
        mtrCollect.setPower(0);
    }

    public void collectorIn(){
        svoRotate.setPosition(0);
    }
    public void collectorOut(){
        svoRotate.setPosition(0.72);
    }
    public void collectorMid(){
        svoRotate.setPosition(0.49);
    }
}
