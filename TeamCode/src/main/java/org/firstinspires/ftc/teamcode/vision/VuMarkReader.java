package org.firstinspires.ftc.teamcode.vision;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.R;

/**
 * Created by guinea on 9/26/17.
 */

public class VuMarkReader {
    private static final String KEY =
            "AcKBAmT/////AAAAGY4K0hyfkEo/nayULNSRzW08epeUop9MOINzd03DbXSZfMNiAjnmJx0s7preMhvnZGzYw"+
            "ukEMpVckxH4vQvyBstC1vI7Z4cFk6IECz2P6jDESvotsajWZTjIdlkEUeo3Aez58ti3MCB9PJPxWcjbbO28O7"+
            "ngQH3IAIz27uj1q0YOR4Jkgy1WCDK7ma5ubY3ex9U03fTd4V5rFWG1Ghmd6w40z/pxJ9zxfPD/3Xq9zeC3fsS"+
            "xSYrLdWbNQsh0zT8yspniRdBDQc9dC0zvfeJry2MwlWBgQ9C7Lt+LggHIvf8d+HOO8vYQIRACX3SZq97elA4W"+"" +
            "YFDN23LkAcQ2z+04JSDmcGWk2h7/xrlZwXgGQfbR";

    private ClosableVuforiaLocalizer vuforia;
    VuforiaLocalizer.Parameters parameters;
    private VuforiaTrackables relicTrackables;
    private VuforiaTrackable relicTemplate;
    private Context context;
    private boolean started = false;
    //TODO: camera direction param?
    public VuMarkReader(Context context) {
        this.context = context;
        int cameraMonitorViewId = context.getResources().getIdentifier("cameraMonitorViewId", "id", context.getPackageName());
        parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);
        parameters.fillCameraMonitorViewParent = false;
        parameters.vuforiaLicenseKey = KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        parameters.useExtendedTracking = false;
    }

    public void start() {
        if (started) return;
        final Activity activity = (Activity) context;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.findViewById(R.id.textNetworkConnectionStatus).setVisibility(View.GONE);
                activity.findViewById(R.id.textRobotStatus).setVisibility(View.GONE);
            }
        });
        vuforia = new ClosableVuforiaLocalizer(parameters);
        /**
         * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
         * in this data set: all three of the VuMarks in the game were created from this one template,
         * but differ in their instance id information.
         * @see VuMarkInstanceId
         */
        relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary
        relicTrackables.activate();
        started = true;
        //vuforia.setFrameQueueCapacity(1);
        //vuforia.getFrameQueue().peek();
    }

    public RelicRecoveryVuMark getVuMark() {
        if (!started) return RelicRecoveryVuMark.UNKNOWN;
        return RelicRecoveryVuMark.from(relicTemplate);
    }
    public void stop() {
        if (!started) return;
        final Activity activity = (Activity) context;
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                activity.findViewById(R.id.textNetworkConnectionStatus).setVisibility(View.VISIBLE);
                activity.findViewById(R.id.textRobotStatus).setVisibility(View.VISIBLE);
            }
        });
        vuforia.close();
        started = false;
    }
    public boolean running() {
        return started;
    }
}
