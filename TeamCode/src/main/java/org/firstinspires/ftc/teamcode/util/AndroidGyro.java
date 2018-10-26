package org.firstinspires.ftc.teamcode.util;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import org.firstinspires.ftc.robotcontroller.internal.FtcRobotControllerActivity;

/**
 * Created by nicholas on 6/12/17.
 * Adapted from the Android examples and 731 Wannabee Strange example code, with permission.
 * https://github.com/731WannabeeStrange/ftc_app_examples/blob/master/TeamCode/src/main/java/org/firstinspires/ftc/teamcode/hardware/AndroidGyro.java
 */

public class AndroidGyro implements SensorEventListener {
    private static AndroidGyro instance = null;
    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float timestamp = 1;
    private static final double EPSILON = 0.05f;

    private boolean reset = false;
    public double ax = 0;
    public double ay = 0;
    public double az = 0;
    private double x = 0.420;
    private double y = 0.420;
    private double z = 0.420;
    private double delta_x = 0;
    private double delta_y = 0;
    private double delta_z = 0;
    private int counter = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Reset the integration sums, if asked to
        if (reset) {
            x = 0;
            y = 0;
            z = 0;
            reset = false;
        }
        Log.i("gyro", "Recieved event");
        // This timestep's delta rotation to be multiplied by the current rotation
        // after computing it from the gyro sample data.
        if (timestamp != 0) {
            final float dT = (event.timestamp - timestamp) * NS2S;
            // Axis of the rotation sample, not normalized yet.
            float axisX = event.values[0];
            float axisY = event.values[1];
            float axisZ = event.values[2];
            ax = axisX;
            ay = axisY;
            az = axisZ;

            // Calculate the angular speed of the sample
            float omegaMagnitude = (float) Math.sqrt(axisX * axisX + axisY * axisY + axisZ * axisZ);

            // Normalize the rotation vector if it's big enough to get the axis
            // (that is, EPSILON should represent your maximum allowable margin of error)
            if (omegaMagnitude > EPSILON) {
                axisX /= omegaMagnitude;
                axisY /= omegaMagnitude;
                axisZ /= omegaMagnitude;
            }

            // Integrate around this axis with the angular speed by the timestep
            // in order to get a delta rotation from this sample over the timestep
            // We will convert this axis-angle representation of the delta rotation
            // into a quaternion before turning it into the rotation matrix.
            float thetaOverTwo = omegaMagnitude * dT / 2.0f;
            float sinThetaOverTwo = (float) Math.sin(thetaOverTwo);
            float cosThetaOverTwo = (float) Math.cos(thetaOverTwo);
            deltaRotationVector[0] = sinThetaOverTwo * axisX;
            deltaRotationVector[1] = sinThetaOverTwo * axisY;
            deltaRotationVector[2] = sinThetaOverTwo * axisZ;
            deltaRotationVector[3] = cosThetaOverTwo;
            delta_x = 2 * Math.toDegrees(deltaRotationVector[0]);
            delta_y = 2 * Math.toDegrees(deltaRotationVector[1]);
            delta_z = 2 * Math.toDegrees(deltaRotationVector[2]);

            // just add the code
            x += delta_x;
            y += delta_y;
            z += delta_z;
            if (counter % 30 == 0) {
                Log.i("gyro", String.format("%f %f %f|%f %f %f|%f", x, y, z, axisX, axisY, axisZ, timestamp));
            }
        }
        timestamp = event.timestamp;
        counter++;
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    // Initialize all of the hardware variables
    private AndroidGyro() {
    }

    public static AndroidGyro getInstance(Context context) {
        if (instance == null) {
            instance = new AndroidGyro();
            SensorManager sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
            Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
            sensorManager.registerListener(instance, sensor, SensorManager.SENSOR_DELAY_FASTEST);
        }


        Log.i("gyro", "Registered gyro???");
        return instance;
    }

    public static double wrapAngle(double angle) {
        angle %= 360;
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public void reset() {
        reset = true;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public double getHX() {
        return wrapAngle(getX());
    }

    public double getHY() {
        return wrapAngle(getY());
    }

    public double getHZ() {
        return wrapAngle(getZ());
    }

    public double getdX() {
        return delta_x;
    }

    public double getdY() {
        return delta_y;
    }

    public double getdZ() {
        return delta_z;
    }

}
