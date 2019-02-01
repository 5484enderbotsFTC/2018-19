package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Hang {
    DcMotor mtrHang;

    //private TouchSensor limHangLow;
    //private TouchSensor limHangHigh;

    public Hang(HardwareMap hardwareMap) {
    mtrHang = hardwareMap.get(DcMotor.class, "mtrHang");
        //limHangHigh = hardwareMap.touchSensor.get("limHangHigh");
        //limHangLow = hardwareMap.touchSensor.get("limHangLow");

    }

    public void up(){
        mtrHang.setPower(1);
    }

    public void down(){
        mtrHang.setPower(-1);
    }

    public void stop(){
        mtrHang.setPower(0);
    }
}

