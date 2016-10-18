
package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;

import org.usfirst.frc.team346.subsystem.Arm;
import org.usfirst.frc.team346.subsystem.Arm.ArmPosition;
import org.usfirst.frc.team346.subsystem.Drive;
import org.usfirst.frc.team346.subsystem.Drive.GearSpeed;
import org.usfirst.frc.team346.subsystem.Shooter;
import org.usfirst.frc.team346.subsystem.Shooter.JawPosition;
import org.usfirst.frc.team346.subsystem.Shooter.TriggerPosition;
import org.usfirst.frc.team346.subsystem.Winch;

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
	private Joystick m_leftJoystick;	// Left joystick on the driver station
	private Joystick m_rightJoystick;	// Right joystick on the driver station
	private Joystick m_buttonBoard;		// Button board on the drive station

	// Subsystem declarations
	private Drive m_driveSubsystem;		// Drive subsystem
	private Arm m_armSubsystem;			// Arm subsystem
	private Shooter m_shooterSubsystem;	// Shooter subsystem
	private Winch m_winchSubsystem; 	// Winch subsystem
	
	// Compressor declaration
	private Compressor m_compressor;	// Pneumatic compressor
	
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
//    	this.m_winchSubsystem = new Winch();								// Init the Winch subsystem
    	
    	// Compressor instantiations
    	this.m_compressor = new Compressor(RobotMap.COMPRESSOR_PORT);		// Instantiate the pneumatic compressor
    }

    /**
     * This function is called periodically during operator control.
     */
    public void teleopPeriodic() {
    	this.m_compressor.start();
    	this.m_driveSubsystem.setDrive(this.m_leftJoystick.getY(), this.m_rightJoystick.getY());
    	
    	// Drive speed events
    	if (this.m_leftJoystick.getRawButton(1)) {
    		this.m_driveSubsystem.setGear(GearSpeed.HIGH_SPEED);    		
    	} else {
    		this.m_driveSubsystem.setGear(GearSpeed.LOW_SPEED);
    	}
    	
    	// Arm position events
    	if (this.m_buttonBoard.getRawButton(8)) {
    		this.m_armSubsystem.setArmPosition(ArmPosition.LOAD.getPosition());
    		
    	} else if (this.m_buttonBoard.getRawButton(9)) {
    		this.m_armSubsystem.setArmPosition(ArmPosition.CLIMB.getPosition());
    		
    	} else if (this.m_buttonBoard.getRawButton(10)) {
    		this.m_armSubsystem.setArmPosition(ArmPosition.SHOOT.getPosition());
    		
    	} else if (this.m_buttonBoard.getRawButton(11)) {
    		this.m_armSubsystem.setArmPosition(ArmPosition.START.getPosition());
    		
    	} else if (this.m_buttonBoard.getRawButton(12)) {
    		this.m_armSubsystem.setArmPosition(ArmPosition.TRAVEL.getPosition());
    	}    	    	    	   
    	
    	// Jaw open/close events
    	if (this.m_buttonBoard.getRawButton(3)) {
    		this.m_shooterSubsystem.setJawPosition(JawPosition.OPEN);
    	} else {
    		this.m_shooterSubsystem.setJawPosition(JawPosition.CLOSE);
    	}
    	
    	// Shooting events
    	if (this.m_buttonBoard.getRawButton(2) && this.m_shooterSubsystem.getJawPosition() == JawPosition.OPEN) {
    		this.m_shooterSubsystem.setTriggerPosition(TriggerPosition.EXTEND);
    	} else {
    		this.m_shooterSubsystem.setTriggerPosition(TriggerPosition.RETRACT);
    	}        
    }


    
/*    
    public void processArmInput(){    	
    	
         if(buttonBoard.getArmReset()) {
        	lightManager.Flash("Green",1500);
        	 robotArm.Reset();
    		System.out.println("ArmGateDown");
        } else if(buttonBoard.getClimb()) {
    		robotArm.Lift();
        } else if(buttonBoard.getTravelPosition()){
        	robotArm.Travel();
        }else if(buttonBoard.getStopArm())
        	{
        	robotArm.Stop();
        } else if(buttonBoard.getArmHang()){
        	robotArm.hang();
    	}
/*         
        else if(armcontroller.getRawButton(1)) {
    		System.out.println("ArmControl");
    		double newPoint = motors.armMotor.getSetpoint()+(armcontroller.getY()*5);
    		if(newPoint>robotArm.PositionLift)
    			newPoint=robotArm.PositionLift;
    		if(newPoint<robotArm.PositionLoad)
    			newPoint=robotArm.PositionLoad;
    		robotArm.setArmPosition(newPoint);
    		}
//*/    		
//        } 
    
    /*
    public void processShootingInput(){
		if(buttonBoard.getLightOn()){
			//System.out.println("Light is On");
			//System.out.println(light.get());
			light.set(Relay.Value.kForward);
		}
    	if(buttonBoard.getOpenJaws()){
			robotShooter.openJaws();
		}
		if(!buttonBoard.getTakeIn() && !buttonBoard.getSpinrollers() && !buttonBoard.getOpenJaws() && !buttonBoard.getShootClose() && !buttonBoard.getDeployHook() && !buttonBoard.getStartHanging() && !buttonBoard.getEjectBall()){
			robotShooter.closeJaws();
		}
		
    	if(buttonBoard.getEjectBall())
		{
			//System.out.println("Shooting");
			lightManager.Flash("red", 1500);
			robotArm.Shoot();
			robotShooter.openJaws();
			light.set(Relay.Value.kForward);
		}
    	if(!buttonBoard.getEjectBall() && !buttonBoard.getSpinrollers() && !buttonBoard.getLightOn()){
    		light.set(Relay.Value.kOff);
    	}
    	if(buttonBoard.getTriggered()){
    		robotShooter.trigger();
    	} 
    	if(!buttonBoard.getTriggered()){
    		robotShooter.triggerFalse();
    	}
		if(buttonBoard.getTakeIn())
		{
			//System.out.println("Taking in");
			lightManager.Flash("blue", 1500);
			robotArm.LoadBall();
//			robotShooter.LoadBall();
			motors.shooterTop.set(1);
			robotShooter.openJaws();
		}
		if(!buttonBoard.getEjectBall() && !buttonBoard.getTakeIn() && !buttonBoard.getSpinrollers() && !buttonBoard.getShootClose()){
			//System.out.println("Roller Stop");
//			robotShooter.Stop();
			motors.shooterTop.set(0);
			//robotShooter.GripBall();
		}
		if(buttonBoard.getSpinrollers()){
			//System.out.println("Spinning Rollers");
//			robotShooter.SpinUp();
			motors.shooterTop.enableControl();
			motors.shooterBottom.enableControl();
			motors.shooterTop.set(1);
			
			robotShooter.openJaws();
			light.set(Relay.Value.kForward);
		}
		if(buttonBoard.getShootClose()) {
//        	robotShooter.ShootClose();
			motors.shooterTop.set(1);
        	robotShooter.openJaws();
		}
    }
    
    public void processWinchInput()
    {
		if(buttonBoard.getDeployHook()) //&& (startTime-hookDuration)>=hookDuration)
		{
			//System.out.println("Deploying Hook");
			robotWinch.DeployHook();
			robotShooter.openJaws();
			}
		if(buttonBoard.getStartHanging())
		{
			//System.out.println("Winch");
			robotWinch.startHanging();
			robotWinch.retractHook();
			robotShooter.openJaws();
		}
		if(!buttonBoard.getStartHanging()&&!buttonBoard.getWinchPush()){
			robotWinch.stopTurning();
		}
		if(!buttonBoard.getDeployHook() && !buttonBoard.getStartHanging())
		{
			robotWinch.normalHook();
		}
		if(buttonBoard.getWinchPush())
		{
			robotWinch.reverseWinch();
			robotShooter.openJaws();
		}
    }     
*/    
}
