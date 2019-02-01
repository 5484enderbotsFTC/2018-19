package org.firstinspires.ftc.teamcode.utilRR;

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

    }
    public void setTarget(double target){
        this.target = target;
    }

    public double getPower(double currentValue){
        errorPrev = this.error;
        this.error = target - currentValue;
        totalError+=error;
        return m_P*error + m_I*totalError + m_D*(error-errorPrev);

    }

    public void reset(){
        totalError = 0;
        errorPrev = 0;
    }

}