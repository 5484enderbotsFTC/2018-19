package org.firstinspires.ftc.teamcode.vision;

import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.firstinspires.ftc.teamcode.util.RollingAverage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by guinea on 10/1/17.
 */

public class JewelReader extends OpenCVPipeline {
    private Point xy;
    private Point wh;
    private Scalar lower;
    private Scalar upper;
    private RollingAverage avg;
    long lastFrame = 0;
    long areaSum = 0;
    long roiSize = 1;
    long frameCounter = 0;
    double ms = 0;
    Mat roi;
    Mat hsv = new Mat();
    Mat thresh = new Mat();
    Mat th = new Mat();
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
    public synchronized double getAvgBlue() {
        return avg.average();
    }
    public synchronized double getMs() {
        return ms;
    }
    public synchronized long getFrameCount() {
        return frameCounter;
    }

    public JewelReader(int cacheSize) {
        setDims(575, 240, 863, 479);
        setHSVParams(84, 128, 46, 140, 255, 255);
        avg = new RollingAverage(cacheSize);
    }

    @Override
    public Mat processFrame(Mat rgba, Mat gray) {
        setDims((int) (rgba.width() * 2.0d / 3.0d), (int) (rgba.height() * 0.5d), rgba.width() - 1, rgba.height() - 1);
        contours.clear();
        roi = new Mat(rgba, new Rect(xy, wh));
        Imgproc.cvtColor(roi, hsv, Imgproc.COLOR_RGB2HSV, 3);
        Core.inRange(hsv, lower, upper, thresh);
        Imgproc.dilate(thresh, th, new Mat(), new Point(-1, -1), 10);
        Imgproc.erode(th, thresh, new Mat(), new Point(-1, -1), 10);
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
        avg.update(getBlue());
        return rgba;
    }

}
