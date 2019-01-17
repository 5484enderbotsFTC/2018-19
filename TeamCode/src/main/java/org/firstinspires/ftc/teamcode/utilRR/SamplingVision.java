package org.firstinspires.ftc.teamcode.utilRR;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.vuforia.CameraDevice;

import java.util.List;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;

import static org.firstinspires.ftc.robotcore.external.tfod.TfodRoverRuckus.TFOD_MODEL_ASSET;


public class SamplingVision {

    private Telemetry telemetry;
    private HardwareMap hardwareMap;
    private static final String TFOD_MODEL_ASSET = "RoverRuckus.tflite";
    private static final String LABEL_GOLD_MINERAL = "Gold Mineral";
    private static final String LABEL_SILVER_MINERAL = "Silver Mineral";
    private static final String VUFORIA_KEY =
            "AaykDuL/////AAABmRi+UFZ1dkWHuI8mpO65odVhF2E+c0UeNeYto6BUgRANzX6RVu2pIac0ONKNt3qLSkBp800RXcnJHq872Hc56F4RL2r9syzf3UGr3qi/q7EpodafX76lIwKQ95sJH4Vl7Ai/5qU0qv93CjK6igT05PA/3edAdXgPy7WM9GgHRlhYCaGxD91dagW9svqOWCEAYd+7uqsNv7ROqoZIsQGJ38BDzbQK39bszo5kmU5zqbAcW6oO91NuQn5IlbiFgNEU7lhRX0hhBuel2CufJHb2L8wtAS/L48Sp/gWsaLPmbNoHT8+Ma1DYQSXXic6NtZR7iGPmmKmmt3N39UjYTROlLznmN2qpfH4ZHWK172qDpble";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the Tensor Flow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    boolean focusModeSet;

    public SamplingVision(HardwareMap hardwareMap){
        this(hardwareMap, null);
    }

    public SamplingVision(HardwareMap hardwareMap, Telemetry telemetry){
        this.telemetry = telemetry;
        this.hardwareMap = hardwareMap;

        initVuforia();
        initTfod();

        this.focusModeSet = CameraDevice.getInstance().setFocusMode(
                CameraDevice.FOCUS_MODE.FOCUS_MODE_CONTINUOUSAUTO);
        if(!focusModeSet){
            Log.d("Sample Log Tag", "Failed to set focus mode (unsupported mode).");
        }
        /** Activate Tensor Flow Object Detection. */

        if (tfod != null) {
            tfod.activate();
        }
    }

    public int getMineral3X(){
        int mineralPos = -1;
        // getUpdatedRecognitions() will return null if no new information is available since
        // the last time that call was made.
        List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
        if (updatedRecognitions != null) {

            if (updatedRecognitions.size() >= 2) {
                int goldMineralX = -1;
                int silverMineral1X = -1;
                int silverMineral2X = -1;
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                        goldMineralX = (int) recognition.getLeft();
                    } else if (silverMineral1X == -1) {
                        silverMineral1X = (int) recognition.getLeft();
                    } else {
                        silverMineral2X = (int) recognition.getLeft();
                    }
                    if(telemetry!=null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        telemetry.addData("silver 1", silverMineral1X);
                        telemetry.addData("gold 1", goldMineralX);
                        telemetry.addData("silver 2", silverMineral2X);
                        telemetry.update();
                    }

                }
                if (goldMineralX != -1 && silverMineral1X != -1 && silverMineral2X != -1) {
                    if (goldMineralX < silverMineral1X && goldMineralX < silverMineral2X) {
                        mineralPos = 0; //left
                    } else if (goldMineralX > silverMineral1X && goldMineralX > silverMineral2X) {
                        mineralPos = 2; //right
                    } else {
                        mineralPos = 1; //centre
                    }
                }

            }
        }
        return mineralPos;
    }
    public int getMineral2XLeft(){
        int mineralPos = -1;
        // getUpdatedRecognitions() will return null if no new information is available since
        // the last time that call was made.
        List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
        if (updatedRecognitions != null) {

            if (updatedRecognitions.size() >= 2) {
                int goldMineralX = -1;
                int silverMineral1X = -1;
                int silverMineral2X = -1;
                for (Recognition recognition : updatedRecognitions) {
                    if (recognition.getLabel().equals(LABEL_GOLD_MINERAL)) {
                        goldMineralX = (int) recognition.getLeft();
                    } else if (silverMineral1X == -1) {
                        silverMineral1X = (int) recognition.getLeft();
                    } else {
                        silverMineral2X = (int) recognition.getLeft();
                    }
                    if(telemetry!=null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        telemetry.addData("silver 1", silverMineral1X);
                        telemetry.addData("gold 1", goldMineralX);
                        telemetry.addData("silver 2", silverMineral2X);
                        telemetry.update();
                    }

                }
                if (goldMineralX == -1) {
                    mineralPos = 2; //right
                } else if (goldMineralX <= silverMineral1X) {
                    mineralPos = 0; //left
                } else {
                    mineralPos = 1; //centre
                }
                if(telemetry!=null) {
                    telemetry.addData("mineral pos", mineralPos);
                }
            }
        }
        return mineralPos;
    }
    public void stop(){
        if(tfod !=null){
            tfod.shutdown();
        }
    }



    /**
     * Initialize the Vuforia localization engine.
     */
    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the Tensor Flow Object Detection engine.
    }

    /**
     * Initialize the Tensor Flow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_GOLD_MINERAL, LABEL_SILVER_MINERAL);
    }

}