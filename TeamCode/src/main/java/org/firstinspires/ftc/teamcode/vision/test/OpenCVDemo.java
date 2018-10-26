package org.firstinspires.ftc.teamcode.vision.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;
import org.firstinspires.ftc.teamcode.vision.JewelReader;

/**
 * Created by guinea on 10/1/17.
 */
@Disabled
@TeleOp(name="OpenCV Demo", group="test")
public class OpenCVDemo extends OpMode {
    JewelReader jewelReader;
    @Override
    public void init() {
        jewelReader = new JewelReader(30);
        jewelReader.setDims(0, 0, 863, 479);
        jewelReader.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        jewelReader.enable();
    }

    @Override
    public void loop() {
        telemetry.addData("Current blue%", jewelReader.getAvgBlue());
    }

    @Override
    public void stop() {
        jewelReader.disable();
    }
}
