package org.usfirst.frc.team346.subsystem;

import org.usfirst.frc.team346.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

/**
 * TODO: Change CANTalon objects to "GearBox" objects
 * TODO: Figure out a way to use the runPeriodic() with all subsystems
 * TODO: Implement gyro-based heading hold
 */

/**
 * This is the drive-base class for the robot.
 * 
 * It is responsible for making the robot move.
 * 
 * @author Darren C.
 *
 */
public class Drive implements Subsystem {
	
	// Motor controller declarations
	private CANTalon m_leftDriveMaster;
	private CANTalon m_leftDriveSlave;
	private CANTalon m_rightDriveMaster;
	private CANTalon m_rightDriveSlave;
	
	// Solenoid declarations
	private DoubleSolenoid m_gearSolenoid;	
	
	/**
	 * Enumeration for transmission speeds.
	 * 
	 * @author Adam Morrissett
	 *
	 */
	public static enum GearSpeed {
		HIGH_SPEED,	///< Transmission is set to high speed
		LOW_SPEED;	///< Transmission is set to low speed
	}
	
	/**
	 * Default constructor for Drive object. Initializes
	 * all member variables and the motor controller settings. 
	 */
	public Drive() {
		this.m_leftDriveMaster = new CANTalon(RobotMap.LEFT_DRIVE_MASTER_PORT);		// Instantiate left master motor
		this.m_leftDriveMaster.changeControlMode(TalonControlMode.PercentVbus);		// Set left master to %Vbus mode	
		
		this.m_leftDriveSlave = new CANTalon(RobotMap.LEFT_DRIVE_SLAVE_PORT);		// Instantiate left slave motor		
		this.m_leftDriveSlave.changeControlMode(TalonControlMode.Follower);			// Set left slave to follower mode
		this.m_leftDriveSlave.set(this.m_leftDriveMaster.getDeviceID());			// Assign left slave's master
		
		this.m_leftDriveMaster.enable();	// Enable left master motor
		this.m_leftDriveSlave.enable();		// Enable left slave motor
		
		this.m_rightDriveMaster = new CANTalon(RobotMap.RIGHT_DRIVE_MASTER_PORT);	// Instantiate right master motor	
		this.m_rightDriveMaster.changeControlMode(TalonControlMode.PercentVbus);	// Set right master to %Vbus mode
		
		this.m_rightDriveSlave = new CANTalon(RobotMap.RIGHT_DRIVE_SLAVE_PORT);		// Instantiate right slave motor
		this.m_rightDriveSlave.changeControlMode(TalonControlMode.Follower);		// Set right slave to follower mode
		this.m_rightDriveSlave.set(this.m_rightDriveMaster.getDeviceID()); 			// Assign right slave's master
		
		this.m_rightDriveMaster.enable();	// Enable right master motor
		this.m_rightDriveSlave.enable();	// Enable right slave motor
		
		this.m_gearSolenoid = new DoubleSolenoid(RobotMap.DRIVE_SOLENOID_MODULE,
				RobotMap.DRIVE_SOLENOID_PORT_1,
				RobotMap.DRIVE_SOLENOID_PORT_2);									// Instantiate transmission solenoid
		
		this.enable();	// Enables the subsystem
	}	
	
	/**
	 * This method is the main method behind all of the
	 * drive-based functions. It should be called during
	 * autonomous and/or teleoperation periodic methods.
	 */
	@Override
	public void runPeriodic(Object...objects) {
		
	}
	
	/**
	 * Set the output of the drive. THIS METHOD SHOULD ONLY
	 * BE TEMPORATRY.
	 * 
	 * @param _leftYValue the value of the left Y-axis
	 * @param _rightYValue the value of the right Y-axis
	 */
	@Deprecated
	public void setDrive(double _leftYValue, double _rightYValue) {
		this.m_leftDriveMaster.set(-1 * _leftYValue);
		this.m_rightDriveMaster.set(_rightYValue);
	}
	
	/**
	 * This method disables the subsystem. This method 
	 * could be used for any sort of safety driven disable.
	 */
	@Override
	public void disable() {
		this.m_leftDriveMaster.disable();		// Disable motor controller output
		this.m_leftDriveSlave.disable(); 		// Disable motor controller output
		
		this.m_rightDriveMaster.disable();		// Disable motor controller output
		this.m_rightDriveSlave.disable();		// Disable motor controller output
		
		this.m_gearSolenoid.set(Value.kOff);	// Disable solenoid output
	}
	
	/**
	 * This method enables the subsystem. This allows
	 * the subsystem to actually be driven.
	 */
	@Override
	public void enable() {
		this.m_leftDriveMaster.enable();	// Enable motor controller output
		this.m_leftDriveSlave.enable(); 	// Enable motor controller output
		
		this.m_rightDriveMaster.enable();	// Enable motor controller output
		this.m_rightDriveSlave.enable();	// Enable motor controller output			
	}
	
	/**
	 * Sets the speed gear to either high or low.
	 * 
	 * @param _gearSpeed drive speed to set
	 */
	public void setGear(GearSpeed _gearSpeed) {
		switch(_gearSpeed) {		
			case HIGH_SPEED : {
				this.m_gearSolenoid.set(Value.kReverse);
			}; break;
			case LOW_SPEED : {
				this.m_gearSolenoid.set(Value.kForward);
			}; break;
		}
	}					
}
