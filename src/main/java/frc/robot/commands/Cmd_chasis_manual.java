package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Sub_Chasis;

public class Cmd_chasis_manual extends Command {
  Supplier <Double> Speed; Supplier <Double> Turn; Supplier <Double> Back; 
  Sub_Chasis Chasis;
  Double Vel, Giro, VelR, VelL, VelBack;
  public Cmd_chasis_manual(Supplier <Double> speed, Supplier <Double> turn, Sub_Chasis chasis, Supplier <Double> back){
    this.Speed=speed;
    this.Chasis=chasis;
    this.Turn=turn;
    this.Back=back; 
    addRequirements(chasis); // interrumpe el otro comando que se esta utilizando
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if(Math.abs(Back.get())<0.2){
      VelBack=0.0;
    } else{
      VelBack=Back.get();
    }
    if(Math.abs(Speed.get())<0.2){
      Vel=0.0;
    } else{
      Vel=Speed.get();
    }
    if(Math.abs(Turn.get())<0.2){
      Giro=0.0;
    }else{
      Giro=Turn.get();
    }
    VelR = Vel + Giro-VelBack;
    VelL = Vel - Giro-VelBack;

    Chasis.SetSpeed(VelR, VelL);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
