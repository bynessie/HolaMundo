// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Sub_Chasis;

public class Cmd_MoveFor extends Command {
  private final double Setpoint;
  private final Sub_Chasis Chasis;
  private double ErrorPR, ErrorPL, kP, ErrorIR, ErrorIL, kI, ErrorDR, ErrorDL, kD, SpeedR, SpeedL,  LastDt, LastErrorR, LastErrorL, Dt; 
  /** Creates a new Cmd_MoveFor. */
  public Cmd_MoveFor(double setpoint, Sub_Chasis chasis) {
    this.Setpoint = setpoint;
    this.Chasis = chasis;
    addRequirements(chasis);

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Chasis.resetEncoder();
    Chasis.SetSpeed(0, 0);
    //Chasis.SetRamp(.1);
    ErrorPR=0; ErrorPL=0; kP=0.22; SpeedR=0; SpeedL=0; ErrorIR=0; ErrorIL=0; kI=0.08; ErrorDR=0; ErrorDL=0; kD=0;
    LastDt=0; LastErrorR=0; LastErrorL=0;

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    Dt= Timer.getFPGATimestamp() - LastDt; 
    //P
    ErrorPR = Setpoint - Chasis.getEncoderR();
    ErrorPL = Setpoint - Chasis.getEncoderL();
    //I
     
    if (ErrorPR< (Setpoint*0.3)){
      ErrorIR= ErrorIR + ErrorPR*Dt;  
    } else{
      ErrorIR=0;
    }
    if (ErrorPL< (Setpoint*0.3)){
      ErrorIL= ErrorIL + ErrorPL*Dt;  
    }else{
      ErrorIL=0;
    }
    //D
    ErrorDR = (ErrorPR-LastErrorR)/Dt;
    ErrorDL = (ErrorPL-LastErrorL)/Dt; 
  
    SpeedR = (ErrorPR *kP)+(ErrorIR*kI)+(ErrorDR*kD);
    SpeedL = (ErrorPL *kP)+(ErrorIL*kI)+(ErrorDL*kD);

    Chasis.SetSpeed(SpeedR, SpeedL);

    LastErrorR = ErrorPR;
    LastErrorL = ErrorPL;
    LastDt= Timer.getFPGATimestamp();

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Chasis.resetEncoder();
    Chasis.SetSpeed(0, 0);
    //Chasis.SetRamp(0);
  }

  // Returns true when the command should end. 
  
  @Override
  public boolean isFinished() {
    if (ErrorPR<2 && ErrorPL<2){
      System.out.println("si jjala");
      return true;
    } else{
      return false;
    }
  }
}
