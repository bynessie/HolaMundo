package frc.robot.subsystems;
//import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkBase.IdleMode;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Sub_Chasis extends SubsystemBase {
  final CANSparkMax Motor1R = new CANSparkMax(1, MotorType.kBrushless);
  final CANSparkMax Motor2R = new CANSparkMax(2, MotorType.kBrushless);
  final CANSparkMax Motor1L = new CANSparkMax(4, MotorType.kBrushless);
  final CANSparkMax Motor2L = new CANSparkMax(3, MotorType.kBrushless);
  final RelativeEncoder EncoR = Motor1R.getEncoder(); final RelativeEncoder EncoL = Motor1L.getEncoder();
  public Sub_Chasis() {
    Motor1R.restoreFactoryDefaults(); Motor2R.restoreFactoryDefaults(); 
    Motor1L.restoreFactoryDefaults(); Motor2L.restoreFactoryDefaults();
    Motor2R.follow(Motor1R); Motor2L.follow(Motor1L);
    Motor1R.setInverted(true);
    Motor1R.setIdleMode(IdleMode.kCoast);Motor2R.setIdleMode(IdleMode.kCoast);
    Motor1L.setIdleMode(IdleMode.kCoast);Motor2L.setIdleMode(IdleMode.kCoast);
    Motor1R.set(0); Motor1L.set(0);
    EncoR.setPosition(0);EncoL.setPosition(0);
    EncoR.setPositionConversionFactor(6*Math.PI*(1/7.14));EncoL.setPositionConversionFactor(6*Math.PI*(1/7.14));
  }
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Velocidad R", Motor1R.get());
    SmartDashboard.putNumber("Velocidad L", Motor1L.get());
    SmartDashboard.putNumber("Encoder R: ", EncoR.getPosition());
    SmartDashboard.putNumber("Encoder L: ", EncoL.getPosition());
  }

  public void SetSpeed (double R, double L){
    if (Math.abs(R)>0.8){R=(R/Math.abs(R))*0.8;}
    if (Math.abs(L)>0.8){L=(L/Math.abs(L))*0.8;}
    Motor1R.set(R); Motor1L.set(L);
  }
  public double getEncoderR(){
    return EncoR.getPosition();
  }
  public double getEncoderL(){
    return EncoL.getPosition();
  }
  public void resetEncoder(){
    EncoR.setPosition(0);
    EncoL.setPosition(0);
  }
  public void SetRamp(double Ramp){
    Motor1R.setOpenLoopRampRate(Ramp);
    Motor1L.setOpenLoopRampRate(Ramp);
    Motor2R.setOpenLoopRampRate(Ramp);
    Motor2L.setOpenLoopRampRate(Ramp);
  }
}
