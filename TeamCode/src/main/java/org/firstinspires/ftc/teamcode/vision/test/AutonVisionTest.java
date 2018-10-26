package org.firstinspires.ftc.teamcode.vision.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.teamcode.vision.JewelReader;
import org.firstinspires.ftc.teamcode.vision.VuMarkReader;

/**
 * Created by guinea on 10/1/17.
 */
@Disabled
@TeleOp(name="Auton Vision Demo", group="test")
public class AutonVisionTest extends LinearOpMode {
    VuMarkReader vuMarkReader;
    JewelReader jewelReader;
    RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.UNKNOWN;
    @Override
    public void runOpMode() throws InterruptedException {

        vuMarkReader = new VuMarkReader(hardwareMap.appContext);
        vuMarkReader.start();
        while (!(isStarted() || isStopRequested())) {
//            telemetry.addData("Current blue%", jewelReader.getAvgBlue());
            RelicRecoveryVuMark v = vuMarkReader.getVuMark();
            if (v != RelicRecoveryVuMark.UNKNOWN) {
                vuMark = v;
            }
            telemetry.addData("Vumark: ", vuMark);
            telemetry.update();
            idle();
        }
        vuMarkReader.stop();


        if (!opModeIsActive()) return;

        // init the jewel reader
        ElapsedTime visionTimer = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);

        jewelReader = new JewelReader(15);
        jewelReader.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        jewelReader.enable();

        while (jewelReader.getFrameCount() < 15) idle();
        jewelReader.disable();

        // Presumably at this point you'd swing your jewel appendage down at this point.
//        vuMarkReader = new VuMarkReader(hardwareMap.appContext);
//        vuMarkReader.start();
//        while (opModeIsActive() && (vuMark = vuMarkReader.getVuMark()) == RelicRecoveryVuMark.UNKNOWN) idle();
        double ts = visionTimer.milliseconds();
        while (opModeIsActive()) {
            telemetry.addData("Blue%", jewelReader.getAvgBlue());
            telemetry.addData("Runtime: ", "%f ms", ts);
            telemetry.update();
            idle();
        }
        jewelReader.disable();
    }
}
