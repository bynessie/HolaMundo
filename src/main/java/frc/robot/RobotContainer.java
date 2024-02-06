package frc.robot;

import frc.robot.commands.Cmd_MoveFor;
import frc.robot.commands.Cmd_chasis_manual;
import frc.robot.subsystems.Sub_Chasis;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  private final Sub_Chasis Chasis = new Sub_Chasis();
  private final CommandXboxController JoyS = new CommandXboxController(0); 

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    Chasis.setDefaultCommand(new Cmd_chasis_manual(()->JoyS.getRightTriggerAxis(), ()->JoyS.getLeftX(), Chasis, () -> JoyS.getLeftTriggerAxis()));
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    JoyS.a().whileTrue(new Cmd_MoveFor(100,Chasis));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
