package org.firstinspires.ftc.teamcode.vision;

import org.corningrobotics.enderbots.endercv.OpenCVPipeline;
import org.opencv.core.*;
import org.opencv.imgcodecs.*;
import org.opencv.imgproc.*;

import java.util.ArrayList;
import java.util.List;

import static org.opencv.core.CvType.CV_8U;

/**
 * Created by guinea on 11/13/17.
 */

public class GlyphDetector extends OpenCVPipeline {
    int canny_t1 = 20;
    int canny_t2 = 40;
    int canny_px = 3;
    int dilate_pass = 5;
    int contour1_min = 3000;
    int contour1_max = 30000;
    int brownH_min = 2;
    int brownH_max = 20;
    int grayH_min = 18;
    int grayH_max = 102;
    int imgIndex = 0;

    @Override
    public void onCameraViewStarted(int width, int height) {
        cameraView.enableFpsMeter();
    }

    @Override
    public Mat processFrame(Mat rgba, Mat gray) {
        Mat edges = new Mat();
        long start = System.nanoTime();

        List<MatOfPoint> rawContours = new ArrayList<>();
        List<MatOfPoint> contours = new ArrayList<>();
        Mat bgray = new Mat();
        Imgproc.GaussianBlur(gray, bgray, new Size(1, 1), 0, 0);
        Imgproc.Canny(bgray, edges, canny_t1, canny_t2, canny_px, false);
        // adjust the dilate params with a better kernel?
        // idk the params for this part are very good

        Mat dilateKernel = Imgproc.getStructuringElement(Imgproc.CV_SHAPE_CROSS, new Size(4, 4));

        Imgproc.dilate(edges, edges, dilateKernel, new Point(-1, -1), dilate_pass);
        // find the contours that ostensibly include the outlines of glyphs
        Imgproc.findContours(edges, rawContours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        // start constructing the mask
        // the last arg is necessary to make sure that opencv actually zeros the allocated memory
        Mat glyphMask = new Mat(edges.rows(), edges.cols(), CV_8U, new Scalar(0, 0, 0));
        Mat glyphs = new Mat();
        Mat hsvGlyphs = new Mat();

        // filter the stupid contours
        for (MatOfPoint contour : rawContours) {
            double area = Imgproc.contourArea(contour);
            Rect boundingBox = Imgproc.boundingRect(contour);
            double aspectRatio = boundingBox.width / (double) boundingBox.height;
            if (contour1_min < area && area < contour1_max && (0.5 < aspectRatio  && aspectRatio < 2)) {
                contours.add(contour);
            }
        }
        Imgproc.drawContours(rgba, contours, -1, new Scalar(0, 255, 0), 2);
        Mat hsv  = new Mat();
        Imgproc.cvtColor(rgba, hsv, Imgproc.COLOR_RGB2HSV);

        /*
        // create and apply the mask itself
        Imgproc.drawContours(glyphMask, contours, -1, new Scalar(255, 255, 255), -1);
        rgba.copyTo(glyphs, glyphMask);

        Imgproc.cvtColor(glyphs, hsvGlyphs, Imgproc.COLOR_BGR2HSV);

        Mat brownMat = new Mat();
        Mat grayMat = new Mat();
        Core.inRange(hsvGlyphs, new Scalar(brownH_min, 0, 0), new Scalar(brownH_max, 255, 255), brownMat);
        Core.inRange(hsvGlyphs, new Scalar(grayH_min, 0, 0), new Scalar(grayH_max, 255, 255), grayMat);
        List<MatOfPoint> brownContours = getGlyphContour(brownMat);
        List<MatOfPoint> grayContours = getGlyphContour(grayMat);

        Imgproc.drawContours(rgba, brownContours, -1, new Scalar(0, 255, 0));
        Imgproc.drawContours(rgba, grayContours, -1, new Scalar(0, 0, 255));

        for (MatOfPoint contour : brownContours) {
            Imgproc.circle(rgba, contourCenter(contour), 5, new Scalar(0, 255, 0), -1);
        }
        for (MatOfPoint contour : grayContours) {
            Imgproc.circle(rgba, contourCenter(contour), 5, new Scalar(255, 0, 0), -1);
        }
        double s = (System.nanoTime() - start) / 1e9d;

        Mat[] imgs = {rgba, edges, glyphMask, glyphs, brownMat, grayMat};
        return imgs[imgIndex];*/
        return rgba;
    }
    public static List<MatOfPoint> getGlyphContour(Mat glyphs) {
        List<MatOfPoint> raw = new ArrayList<>();
        List<MatOfPoint> ret = new ArrayList<>();
        Imgproc.findContours(glyphs, raw, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        for (MatOfPoint contour : raw) {
            if (Imgproc.contourArea(contour) > 3000) {
                ret.add(contour);
            }
        }
        return ret;
    }

    public void setImgIndex(int idx) {
        if (0 < idx && idx < 6) {
            imgIndex = idx;
        } else if (idx < 0) {
            imgIndex = (imgIndex + 1) % 6;
        }
    }

    public static Point contourCenter(MatOfPoint contour) {
        Rect rect = Imgproc.boundingRect(contour);
        return new Point(rect.x + rect.width / 2, rect.y + rect.height / 2);
    }

}
// vim: softtabstop=0 expandtab shiftwidth=4 smarttab
