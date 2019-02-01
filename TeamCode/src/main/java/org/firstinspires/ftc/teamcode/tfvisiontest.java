package org.firstinspires.ftc.teamcode;

        import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
        import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

        import org.firstinspires.ftc.teamcode.util.SamplingVision;


@Autonomous (name = "TensorFlow Vision Test")
public class tfvisiontest extends LinearOpMode {
    SamplingVision samplingVision;

    @Override
    public void runOpMode(){

        samplingVision = new SamplingVision(hardwareMap,telemetry);

        while(opModeIsActive()){
            //telemetry.addData("Mineral position", samplingVision.getMineral3X());
            //telemetry.update();
            samplingVision.getMineral2XLeft();
        }
    }
}