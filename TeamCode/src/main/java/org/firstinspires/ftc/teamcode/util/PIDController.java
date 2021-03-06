package org.firstinspires.ftc.teamcode.util;

public class PIDController {
    double m_P;
    double m_I;
    double m_D;

    double target;
    double error;
    double errorPrev;
    double totalError;

    public PIDController(double Kp, double Ki, double Kd)
    {
        m_P = Kp;
        m_I = Ki;
        m_D = Kd;
        reset();
    }
    public PIDController(){
        m_P = 0.01;
        m_I = 0.00001;
        m_D = 0.004;
        reset();
    }
    public void setTarget(double target){
        this.target = target;
    }

    public double getPower(double currentValue){
        errorPrev = error;
        error = target - currentValue;
        totalError+=error;
        return m_P*error + m_I*totalError + m_D*(error-errorPrev);

    }

    public void reset(){
        totalError = 0;
        errorPrev = 0;
    }

}