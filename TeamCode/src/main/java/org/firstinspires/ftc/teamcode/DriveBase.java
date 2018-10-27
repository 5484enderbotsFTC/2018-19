package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by Avery on 10/27/18.
 */

public class DriveBase {
    public void incrementsvo(Servo svo, double change) {
        svo.setPosition(svo.getPosition() + change);
    }
}

