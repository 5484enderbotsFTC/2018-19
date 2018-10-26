package org.firstinspires.ftc.teamcode.vision.test;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.corningrobotics.enderbots.endercv.CameraViewDisplay;
import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.util.IniConfig;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guinea on 2/20/18.
 */
@TeleOp(name="box vision test", group="test")
public class BoxVisionTest extends OpMode {
    IniConfig config;
    IniConfig.ConfigSection p;
    BoxDetector cv;

    private class BoxDetector extends OpenCVPipeline {
        public Telemetry telemetry;
        private Scalar lower;
        private Scalar upper;
        public double boxRatio = 1.3;
        List<MatOfPoint> contours = new ArrayList<>();

        public synchronized void setHSVParams(int lowH, int lowS, int lowV, int highH, int highS, int highV) {
            lower = new Scalar(lowH, lowS, lowV);
            upper = new Scalar(highH, highS, highV);
        }

        @Override
        public Mat processFrame(Mat rgba, Mat gray) {
            telemetry.addData("dim1", String.format("%dx%d", rgba.cols(), rgba.rows()));
            telemetry.update();
            contours.clear();
            Mat hsv = new Mat();
            Mat thresh = new Mat();
            Mat hierarchy = new Mat();
            Imgproc.blur(rgba, rgba, new Size(2, 2));
            Imgproc.cvtColor(rgba, hsv, Imgproc.COLOR_RGB2HSV, 3);
            Core.inRange(hsv, lower, upper, thresh);
            Imgproc.findContours(thresh, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            //Imgproc.drawContours(rgba, contours, -1, new Scalar(0, 255, 0), 2);
            for (MatOfPoint contour: contours) {
                Rect bR = Imgproc.boundingRect(contour);
                if ((double) bR.height / (double) bR.width < boxRatio)
                    continue;

                Imgproc.rectangle(rgba, new Point(bR.x, bR.y), new Point(bR.x+bR.width, bR.y+bR.height), new Scalar(0, 255, 0), 2);
            }
            return rgba;
        }
    }
    private void reloadConfig() {
        config.readConfig();
        p = config.getSection("BoxVision");
        cv.setHSVParams(p.geti("minH"), p.geti("minS"), p.geti("minV"), p.geti("maxH"), p.geti("maxS"), p.geti("maxV"));
        cv.boxRatio = p.getd("boxRatio");
    }
    @Override
    public void init() {
        config = new IniConfig(new File("/sdcard/RobotConfig/BoxVision.ini"));
        cv = new BoxDetector();
        cv.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        reloadConfig();
        cv.enable();
        cv.getCameraView().enableFpsMeter();
        cv.telemetry = telemetry;
    }
    @Override
    public void start() {

    }
    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        cv.disable();
    }

    // disable telemetry updates
    @Override
    public void internalPostInitLoop() {}
    @Override
    public void internalPostLoop() {}


}
