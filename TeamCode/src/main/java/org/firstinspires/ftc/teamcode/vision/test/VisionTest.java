package org.firstinspires.ftc.teamcode.vision.test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.firstinspires.ftc.teamcode.util.IniConfig;
import org.corningrobotics.enderbots.endercv.CameraViewDisplay;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guinea on 6/21/17.
 * 864x480
 * BUGS: don't rotate the heccin screen during operation or crash
 */
@Disabled
@TeleOp(name="vision test", group="test")
public class VisionTest extends OpMode {

    TestCV cv;
    IniConfig config;
    IniConfig.ConfigSection p;

    private class TestCV extends OpenCVPipeline {
        private Point xy;
        private Point wh;
        private Scalar lower;
        private Scalar upper;
        long lastFrame = 0;
        long areaSum = 0;
        long roiSize = 1;
        long frameCounter = 0;
        double ms = 0;
        Mat roi;
        Mat hsv = new Mat();
        Mat thresh = new Mat();
        Mat hierarchy = new Mat();
        List<MatOfPoint> contours = new ArrayList<>();
        public synchronized void setDims(int x, int y, int width, int height) {
            xy = new Point(x, y);
            wh = new Point(width, height);
            roiSize = (width - x) * (height - y);
        }
        public synchronized void setHSVParams(int lowH, int lowS, int lowV, int highH, int highS, int highV) {
            lower = new Scalar(lowH, lowS, lowV);
            upper = new Scalar(highH, highS, highV);
        }
        public synchronized int getNumContours() {
            return contours.size();
        }
        public synchronized double getBlue() {
            return areaSum / (double) roiSize;
        }
        public synchronized double getMs() {
            return ms;
        }
        public synchronized long getFrameCount() {
            return frameCounter;
        }

        public TestCV() {
            setDims(0, 0, 10, 10);
        }

        @Override
        public Mat processFrame(Mat rgba, Mat gray) {
            contours.clear();
            roi = new Mat(rgba, new Rect(xy, wh));
            Imgproc.cvtColor(roi, hsv, Imgproc.COLOR_RGB2HSV, 3);
            Core.inRange(hsv, lower, upper, thresh);
            Imgproc.findContours(thresh, contours, hierarchy, Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            Imgproc.drawContours(rgba, contours, -1, new Scalar(0, 255, 0), 2, 8, new Mat(), Integer.MAX_VALUE, xy);
            areaSum = 0;
            for (int i = 0; i < contours.size(); i++) {
                areaSum += Imgproc.contourArea(contours.get(i));
            }

            Imgproc.rectangle(rgba, xy, wh,
                    new Scalar(255, 255, 0), 1, 8, 0);
            ms = (System.nanoTime() - lastFrame) / 1e6d;
            lastFrame = System.nanoTime();
            frameCounter++;
            return rgba;
        }
    }
    private void reloadConfig() {
        config.readConfig();
        p = config.getSection("VisionTest");
        cv.setDims(p.geti("x"), p.geti("y"), p.geti("width"), p.geti("height"));
        cv.setHSVParams(p.geti("minH"), p.geti("minS"), p.geti("minV"), p.geti("maxH"), p.geti("maxS"), p.geti("maxV"));
    }
    @Override
    public void init() {

        config = new IniConfig(new File("/sdcard/RobotConfig/VisionTest.ini"));
        cv = new TestCV();
        reloadConfig();
        cv.init(hardwareMap.appContext, CameraViewDisplay.getInstance());
        cv.enable();
    }

    @Override
    public void loop() {
        if (gamepad1.a) {
            reloadConfig();
        }
        telemetry.addData("num contours", cv.getNumContours());
        telemetry.addData("blue%", cv.getBlue() * 100);
        telemetry.addData("ms", cv.getMs());
        telemetry.addData("framecount", cv.getFrameCount());
    }

    @Override
    public void stop() {
        cv.disable();
    }
}
