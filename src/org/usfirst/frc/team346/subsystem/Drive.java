package org.usfirst.frc.team346.subsystem;

import org.usfirst.frc.team346.robot.dummyPIDOutput;
import org.usfirst.frc.team346.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

/**
 * TODO: Change CANTalon objects to "GearBox" objects
 */

/**
 * This is the drive-base class for the robot.
 * 
 * It is responsible for making the robot move.
 * 
 * @author Student
 *
 */
public class Drive implements Subsystem {

	// Stuff from Adam - begin
	
	private CANTalon m_leftDriveMaster;
	private CANTalon m_leftDriveSlave;
	private CANTalon m_rightDriveMaster;
	private CANTalon m_rightDriveSlave;	
	private CANTalon m_gearSolenoid;
	
	// Stuff from Adam - end
	
	CANTalon LeftMaster;
	CANTalon RightMaster;
	DoubleSolenoid SpeedGear = new DoubleSolenoid(1,0,1);;
	AnalogGyro HeadingGyro;
	dummyPIDOutput Gpid;
	PIDController gyroPIDController;
	Preferences Prefs;
	boolean lowGear;
	boolean highGear;
	
	/**
	 * Default constructor for Drive object. Initializes
	 * all member variables.
	 */
	public Drive() {
		this.m_leftDriveMaster = new CANTalon(RobotMap.LEFT_DRIVE_MASTER_PORT);		// Instantiate left master motor
		this.m_leftDriveMaster.changeControlMode(TalonControlMode.PercentVbus);		// Set left master to %Vbus mode
		this.m_leftDriveSlave = new CANTalon(RobotMap.LEFT_DRIVE_SLAVE_PORT);		// Instantiate left slave motor
		this.m_leftDriveSlave.set(RobotMap.LEFT_DRIVE_SLAVE_PORT);					// Assign left slave's master
		this.m_leftDriveSlave.changeControlMode(TalonControlMode.Follower);			// Set left slave to follower mode
		
		this.m_leftDriveMaster.enable();	// Enable left master motor
		this.m_leftDriveSlave.enable();		// Enable left slave motor
		
		this.m_rightDriveMaster = new CANTalon(RobotMap.RIGHT_DRIVE_MASTER_PORT);	// Instantiate right master motor	
		this.m_rightDriveMaster.changeControlMode(TalonControlMode.PercentVbus);	// Set right master to %Vbus mode
		this.m_rightDriveSlave = new CANTalon(RobotMap.RIGHT_DRIVE_SLAVE_PORT);		// Instantiate right slave motor
		this.m_rightDriveSlave.set(RobotMap.RIGHT_DRIVE_SLAVE_PORT); 				// Assign right slave's master
		this.m_rightDriveSlave.changeControlMode(TalonControlMode.Follower);		// Set right slave to follower mode
		
		this.m_rightDriveMaster.enable();	// Enable right master motor
		this.m_rightDriveSlave.enable();	// Enable right slave motor
	}	
	
	/**
	 * This method is the main method behind all of the
	 * drive-based functions. It should be called during
	 * autonomous and/or teleoperation periodic methods.
	 */
	public void runPeriodic() {		
	}
	
	/**
	 * This method disables the subsystem. This method 
	 * could be used for any sort of safety driven disable.
	 */
	public void disable() {
		this.m_leftDriveMaster.disable();
		this.m_leftDriveSlave.disable();
		this.m_rightDriveMaster.disable();
		this.m_rightDriveSlave.disable();
		this.m_gearSolenoid.disable();
	}
	
	/**
	 * 
	 * @param left
	 * @param right
	 */
	public void drive(double left, double right)
	{
		if (gyroPIDController.isEnabled())
		{
			gyroPIDController.disable();
    			Gpid.out = 0;
		}

		LeftMaster.set(left);
	    RightMaster.set(right);
	}
	
	public void DriveWithHeadingHold(double left, double right, double angle)
	{
	    	if (!gyroPIDController.isEnabled())
	    	{
	    		gyroPIDController.enable();
	    		gyroPIDController.setSetpoint(angle);
	    	}
		/*if(gyroPIDController.getSetpoint() != angle)
		{
			gyroPIDController.setSetpoint(angle);
		}*/

		LeftMaster.set(left - Gpid.out);
		RightMaster.set(right + Gpid.out);
	}
	
	public void HighGear()
	{
		if(lowGear)
		{
			SpeedGear.set(DoubleSolenoid.Value.kForward);
			lowGear=false;
		}
	}
	
	public void LowGear()
	{

		if(!lowGear)
		{
			SpeedGear.set(DoubleSolenoid.Value.kReverse);
			lowGear = true;
		}
	}
	
	public boolean isHighGear()
	{
		return (!lowGear);
	}
	
	public boolean isLowGear()
	{
		return (lowGear);

	}
	
	public void toggleGear()
	{
		System.out.println("Shifting");
		if(isLowGear())
		{
			HighGear();
		}
		else
		{
			LowGear();
		}
	}
	
	public void setPID(String prefix)
	{
		gyroPIDController.setPID(Prefs.getDouble(prefix + "P", 0), Prefs.getDouble(prefix + "I", 0), Prefs.getDouble(prefix + "D", 0));
	}
	
	public double GyroAngle(){
		return HeadingGyro.getAngle();
	}
}
