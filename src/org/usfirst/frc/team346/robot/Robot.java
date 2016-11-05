
package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;

import org.usfirst.frc.team346.autonomous.AutonomousSequence;
import org.usfirst.frc.team346.autonomous.BasicAutonomous;
import org.usfirst.frc.team346.autonomous.LowBarAutonomous;
import org.usfirst.frc.team346.autonomous.RockWallAutonomous;
import org.usfirst.frc.team346.subsystem.Arm;
import org.usfirst.frc.team346.subsystem.Arm.ArmPosition;
import org.usfirst.frc.team346.subsystem.Climber.HookPosition;
import org.usfirst.frc.team346.subsystem.Climber.MotorState;
import org.usfirst.frc.team346.subsystem.Drive;
import org.usfirst.frc.team346.subsystem.Drive.GearSpeed;
import org.usfirst.frc.team346.subsystem.Harvester.HarvesterPosition;
import org.usfirst.frc.team346.subsystem.Harvester.HarvesterRollerState;
import org.usfirst.frc.team346.subsystem.Harvester;
import org.usfirst.frc.team346.subsystem.Shooter;
import org.usfirst.frc.team346.subsystem.Shooter.JawPosition;
import org.usfirst.frc.team346.subsystem.Shooter.ShooterSpeed;
import org.usfirst.frc.team346.subsystem.Shooter.TriggerPosition;
import org.usfirst.frc.team346.subsystem.Climber;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	// Human interface device (HID) declarations
	private Joystick m_leftJoystick;		// Left joystick on the driver station
	private Joystick m_rightJoystick;		// Right joystick on the driver station
	private Joystick m_buttonBoard;			// Button board on the drive station

	// Subsystem declarations
	private Drive m_driveSubsystem;			// Drive subsystem
	private Arm m_armSubsystem;				// Arm subsystem
	private Shooter m_shooterSubsystem;		// Shooter subsystem
	private Harvester m_harvesterSubsystem;	// Harvester subsystem
	private Climber m_climberSubsystem; 	// Winch subsystem
	
	// Compressor declaration
	private Compressor m_compressor;	// Pneumatic compressor
	
	// Autonomous sequence declaration
	private AutonomousSequence m_autoSequence;
	
	/**
	 * This is the main initialization method. 
	 * This method is called when the robot first starts up. 
	 * Do not put any time-dependent variables in here because 
	 * the robot will sit indefinitely after calling this method.
	 */
    public void robotInit() {
    	
    	// HID instantiations
    	this.m_leftJoystick = new Joystick(RobotMap.LEFT_JOYSTICK_PORT); 	// Init the left joystick
    	this.m_rightJoystick = new Joystick(RobotMap.RIGHT_JOYSTICK_PORT);  // Init the right joystick
    	this.m_buttonBoard = new Joystick(RobotMap.BUTTON_BOARD_PORT);		// Init the Button Board
    	
    	// Subsystem instantiations
    	this.m_driveSubsystem = new Drive();								// Init the Drive subsystem
    	this.m_armSubsystem = new Arm();									// Init the Arm subsystem
    	this.m_shooterSubsystem = new Shooter();							// Init the Shooter subsystem
    	this.m_harvesterSubsystem = new Harvester();
    	this.m_climberSubsystem = new Climber();								// Init the Winch subsystem
    	
    	// Compressor instantiations
    	this.m_compressor = new Compressor(RobotMap.COMPRESSOR_PORT);		// Instantiate the pneumatic compressor
    
    	// Autonomous sequence instantiations
//    	this.m_autoSequence = new BasicAutonomous(this.m_driveSubsystem);
    	this.m_autoSequence = new RockWallAutonomous(this.m_driveSubsystem,
    			this.m_armSubsystem, this.m_harvesterSubsystem);
    }

    @Override
    public void autonomousInit() {
    	this.m_autoSequence.init();
    }
    
    @Override
    public void autonomousPeriodic() {
    	this.m_autoSequence.doSequence();
    }
    
    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {    	
    	// Compressor event
    	this.m_compressor.start();
    	
    	//System.out.println(this.m_armSubsystem.getArmPosition());    	   //Uncomment when testing is needed     	
    	
    	// Drive events
    	this.m_driveSubsystem.setDrive(this.m_leftJoystick.getY(), this.m_rightJoystick.getY());
    	if (this.m_leftJoystick.getRawButton(1)) {
    		this.m_driveSubsystem.setGear(GearSpeed.HIGH_SPEED);    		
    	} else {
    		this.m_driveSubsystem.setGear(GearSpeed.LOW_SPEED);
    	}
    	
    	// Arm position events with interlocks
    	//System.out.println(this.m_armSubsystem.getArmPosition());		//Used to recalibrate arm positions
    	if (this.m_buttonBoard.getRawButton(8)) {
    		this.m_harvesterSubsystem.setHarvesterPosition(HarvesterPosition.DEPLOY);
    		this.m_armSubsystem.setArmPosition(ArmPosition.LOAD);
    		
    	} else if (this.m_buttonBoard.getRawButton(9)) {
    		if(m_armSubsystem.getArmPosition()> 200 || m_harvesterSubsystem.isExtended())
    		this.m_armSubsystem.setArmPosition(ArmPosition.CLIMB);
    		
    	} else if (this.m_buttonBoard.getRawButton(10)) {
    		if(m_armSubsystem.getArmPosition()> 200 || m_harvesterSubsystem.isExtended())
    		this.m_armSubsystem.setArmPosition(ArmPosition.SHOOT);
    		
    	} else if (this.m_buttonBoard.getRawButton(11)) {
    		if(m_armSubsystem.getArmPosition()> 200 || m_harvesterSubsystem.isExtended())
    		this.m_armSubsystem.setArmPosition(ArmPosition.START);
    		
    	} else if (this.m_buttonBoard.getRawButton(12)) {
    		this.m_armSubsystem.setArmPosition(ArmPosition.TRAVEL);
    		
    	}   else if (this.m_buttonBoard.getRawButton(13)) {
    		this.m_harvesterSubsystem.setHarvesterPosition(HarvesterPosition.DEPLOY);
    		this.m_armSubsystem.setArmPosition(ArmPosition.LOWBAR);
    	}   	    	    	   
    	
    	// Jaw open/close and shooting events
    	if (this.m_buttonBoard.getRawButton(3)) {
    		this.m_shooterSubsystem.setJawPosition(JawPosition.OPEN);
    	} 
    	
    	if (this.m_buttonBoard.getRawButton(6)) {
    		this.m_shooterSubsystem.setShooterSpeed(ShooterSpeed.SHOOT);
    		this.m_harvesterSubsystem.setHarvesterRoller(HarvesterRollerState.EJECT);
    	} else if (this.m_buttonBoard.getRawButton(8)) {
    		this.m_shooterSubsystem.setShooterSpeed(ShooterSpeed.LOAD);
    		this.m_harvesterSubsystem.setHarvesterRoller(HarvesterRollerState.INTAKE);
    	} else {
    		this.m_shooterSubsystem.setShooterSpeed(ShooterSpeed.OFF);
    		this.m_harvesterSubsystem.setHarvesterRoller(HarvesterRollerState.STOP);
    	} 
    	
    	if (!this.m_buttonBoard.getRawButton(3) && 
    			!this.m_buttonBoard.getRawButton(6) &&
    			!this.m_buttonBoard.getRawButton(8)) {
    		this.m_shooterSubsystem.setJawPosition(JawPosition.CLOSE);
    	}
    	
    	// Shooting events
    	if (this.m_buttonBoard.getRawButton(2) 
    			&& this.m_shooterSubsystem.getJawPosition() == JawPosition.OPEN) {
    		this.m_shooterSubsystem.setTriggerPosition(TriggerPosition.EXTEND);
    	} else {
    		this.m_shooterSubsystem.setTriggerPosition(TriggerPosition.RETRACT);
    	}      	    
    	
    	// Harvester events
    	if (this.m_buttonBoard.getRawButton(4)) {
    		this.m_harvesterSubsystem.toggleHarvesterPosition();
    	}    	    
    	
    	// Hook/winch events
    	if (this.m_buttonBoard.getRawButton(1)) {
    		this.m_climberSubsystem.setHookPosition(HookPosition.EXTEND);
    	} else if (this.m_buttonBoard.getRawButton(5)) {
    		this.m_climberSubsystem.setHookPosition(HookPosition.RETRACT);
    	} else {
    		this.m_climberSubsystem.setHookPosition(HookPosition.RESET);
    	}
    	
    	if (this.m_buttonBoard.getRawButton(5)) {
    		this.m_climberSubsystem.setMotorState(MotorState.HANG);
       	} else {
    		this.m_climberSubsystem.setMotorState(MotorState.STOP);
    	}
    
    	if (this.m_buttonBoard.getRawButton(14)) {
    		this.m_climberSubsystem.setHookPosition(HookPosition.RETRACT);
    	}
    }
    
    @Override
    public void testPeriodic() {
    	this.m_climberSubsystem.setHookPosition(HookPosition.RETRACT);
    }
}
