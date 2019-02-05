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
        svoRotate.setPosition(0.78);
    }
    public void rotateMid(){
        svoRotate.setPosition(0.43);
    }
    public void rotateDown(){
        svoRotate.setPosition(0.27);
    }
    public void in(){
        svoRotate.setPosition(0.08);
    }
    public void out(){

    }

}
