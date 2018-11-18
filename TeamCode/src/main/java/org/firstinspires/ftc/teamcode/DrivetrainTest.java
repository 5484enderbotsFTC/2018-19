package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.util.Encoder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@Autonomous (name = "DriveTrainTest")
public class DrivetrainTest extends LinearOpMode {
    DcMotor mtrFR;
    DcMotor mtrBR;
    DcMotor mtrFL;
    DcMotor mtrBL;
    Encoder encFL;


    @Override
    public void runOpMode(){
        mtrBL = hardwareMap.dcMotor.get("mtrBL");
        mtrBR = hardwareMap.dcMotor.get("mtrBR");
        mtrFL = hardwareMap.dcMotor.get("mtrFL");
        mtrFR = hardwareMap.dcMotor.get("mtrFR");
        encFL = new Encoder(mtrFL);
        encFL.resetEncoder();
        waitForStart();

        appendLog("dab");
        mtrBL.setPower(1);
        mtrBR.setPower(1);
        float t;
        for (int i=0;i<11;i++){
            t=i/2;

            appendLog("time:");
            appendLog(t.toString());
            appendLog(encFL.getEncValue().toString());
            sleep(500);
        }
        mtrBL.setPower(1);
        mtrBR.setPower(1);
    }











    public void appendLog(String text)
    {
        File logFile = new File("sdcard/log.file");
        if (!logFile.exists())
        {
            try
            {
                logFile.createNewFile();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        try
        {
            //BufferedWriter for performance, true to set append to file flag
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text);
            buf.newLine();
            buf.close();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
