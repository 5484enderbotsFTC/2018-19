package org.firstinspires.ftc.teamcode.utilRR;

import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AnalogTouch {
    public AnalogInput sns;

    public AnalogTouch(HardwareMap hardwareMap, String name) {
        this.sns = hardwareMap.analogInput.get(name);
    }
    public boolean isPressed() {
        return sns.getVoltage()<=2;
    }
}
