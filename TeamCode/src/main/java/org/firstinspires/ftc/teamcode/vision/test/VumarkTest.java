package org.firstinspires.ftc.teamcode.vision.test;

import android.app.Activity;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.util.ButtonWatcher;
import org.firstinspires.ftc.teamcode.vision.VuMarkReader;


/**
 * Created by guinea on 9/26/17.
 */
@TeleOp(name="Vumark Test", group="test")
public class VumarkTest extends OpMode {
    VuMarkReader v;
    ButtonWatcher gp1;

    @Override
    public void init() {
        v = new VuMarkReader(hardwareMap.appContext);
        v.start();
        gp1 = new ButtonWatcher();
    }

    @Override
    public void start() {
    }


    @Override
    public void loop() {

        telemetry.addData("Vumark: ", "%s visible", v.getVuMark());
    }
    @Override
    public void stop() {
    }
}
