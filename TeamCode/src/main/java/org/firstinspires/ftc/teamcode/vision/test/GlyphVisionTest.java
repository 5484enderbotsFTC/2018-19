package org.firstinspires.ftc.teamcode.vision.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;
import org.firstinspires.ftc.teamcode.vision.GlyphDetector;


/**
 * Created by guinea on 11/14/17.
 */
@Disabled
@TeleOp(name="win worlds test", group="test")
public class GlyphVisionTest extends OpMode {
    GlyphDetector glyphDetector;
    @Override
    public void init() {
        glyphDetector = new GlyphDetector();
        glyphDetector.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        glyphDetector.setImgIndex(3);
    }
    @Override
    public void start() {
        glyphDetector.enable();
    }


    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        glyphDetector.disable();
    }
}
