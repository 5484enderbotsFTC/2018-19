package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by Avery on 10/29/17.
 */

public class Encoder {

    DcMotor motor;
    int offset;

    public Encoder(DcMotor mtr) {
        motor = mtr;
        offset = 0;
    }
    public int getEncValue(){
        return -(motor.getCurrentPosition()-offset);
    }
    public void resetEncoder(){
        offset = motor.getCurrentPosition();
    }
}
