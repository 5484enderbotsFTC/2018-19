package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Tape {
    Servo svoRotate;
    Servo svoExtend;
    public Tape(HardwareMap hardwareMap){
        svoRotate = hardwareMap.servo.get("svoTapeRotate");
        svoExtend = hardwareMap.servo.get("svoTapeExtend");
    }
    public void rotateUp(){
        svoExtend.setPosition(0);
    }
    public void rotateMid(){
        svoExtend.setPosition(0.5);
    }
    public void rotateDown(){
        svoExtend.setPosition(1);
    }
    public void in(){
        svoRotate.setPosition(0.8);
    }
    public void out(){
        svoRotate.setPosition(0.04);
    }

}
