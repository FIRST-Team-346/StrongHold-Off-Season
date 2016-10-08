package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;


//TODO: tune the PIDs
public class StrongholdMotorPreferences {
	Preferences Prefs;
	
//	public CANTalon leftDriveMaster; /* MOVED */
//	public CANTalon leftDriveSlave; /* MOVED */

//	public CANTalon rightDriveMaster; /* MOVED */
//	public CANTalon rightDriveSlave; /* MOVED */

//	public CANTalon armMotor; /* MOVED */
//	public CANTalon winch; /* MOVED */

//	public CANTalon shooterTop; /* MOVED */
//	public CANTalon shooterBottom; /* MOVED */	
	
//	public double armVolts = 12.0; /* MOVED */
	
	/**
	 * Default constructor for StrongholdMotorPreferences object.
	 * This constructor calls all of the initialization methods
	 * for the subsystem motors.
	 */	
	public StrongholdMotorPreferences()	{
		Prefs = Preferences.getInstance();	// Get instance of SmartDashboard preferences
		initDriveMotors();					// Initialize drive motors
		initArmMotors();					// Initialize arm motors
		initShooterMotors();				// Initialize shooter motors       
	}

	/**
	 * Initialize all drive motor settings.
	 */
	public void initDriveMotors() {
//		leftDriveMaster = new CANTalon(1);	// Instantiate left master motor
//		leftDriveSlave = new CANTalon(2);	// Instantiate left slave motor
//		rightDriveMaster = new CANTalon(3);	// Instantiate right master motor
//		rightDriveSlave = new CANTalon (4);	// Instantiate right slave motor
		
		leftDriveMaster.changeControlMode(TalonControlMode.PercentVbus);	// Set left master to %Vbus mode
		leftDriveSlave.changeControlMode(TalonControlMode.Follower);		// Set right slave to follower	
		leftDriveSlave.set(1);												// Assign left slave's master

		rightDriveMaster.changeControlMode(TalonControlMode.PercentVbus);	// Set right master to %Vbus mode
		rightDriveSlave.changeControlMode(TalonControlMode.Follower);		// Set right slave to follower
		rightDriveSlave.set(3);												// Assign right slave's master
		
		rightDriveMaster.enable();	// Enable right master output
		rightDriveSlave.enable();	// Enable right slave output

		leftDriveMaster.enable();	// Enable left master output
		leftDriveSlave.enable();	// Enable left slave output		
	}

	/** 
	 * Initialize arm motor settings.
	 */
	public void initArmMotors()	{
		armMotor = new CANTalon(5);	// Instantiate arm motor
		winch = new CANTalon(6);	// Instantiate winch motor
		
		armMotor.changeControlMode(TalonControlMode.Position);				// Set arm to position mode		
		armMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);	// Set encoder type
		armMotor.configMaxOutputVoltage(armVolts);							// Set maximum output voltage	
		armMotor.setPID(10, 0, 0.002);											// Set PID parameters
		
/*		// SmartDashboard preferences not implemented, doing magic numbers
		armMotor.setPID(Prefs.getDouble("ArmP", 1), 
				Prefs.getDouble("ArmI", 0), 
				Prefs.getDouble("ArmD", 0));								// Set PID parameters based on 
																			// SmartDashboard preferences
//*/
				
		winch.changeControlMode(TalonControlMode.PercentVbus);			// Set winch to %Vbus mode				
		winch.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);	// Set encoder type
		winch.configEncoderCodesPerRev(360);							// Set encoder codes per revolution
		
		armMotor.enable();			// Enable arm motor output
		armMotor.enableControl();	// Enable control for arm motor (position control)
		
		winch.disable();	// Disable  motor output		
	}

	/**
	 * Initialize shooter motor settings
	 */
	public void initShooterMotors()	{
//		shooterTop = new CANTalon(7);
//		shooterBottom = new CANTalon(8);


/*
		shooterTop.changeControlMode(TalonControlMode.Speed);
		shooterTop.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		shooterTop.configEncoderCodesPerRev(1024);
		shooterTop.setVoltageRampRate(Prefs.getDouble("TopVoltageRamprate",24));
		shooterTop.setPID(10, 0, 0);
//*/
		
//		shooterTop.changeControlMode(TalonControlMode.PercentVbus);
		
/*		// SmartDashboard preferences not implemented, doing magic numbers		
		shooterTop.setPID(Prefs.getDouble("ShootP", 0),
				Prefs.getDouble("ShootI", 0),
				Prefs.getDouble("ShootD", 0));
//*/		
		shooterTop.changeControlMode(TalonControlMode.PercentVbus);
		
		shooterBottom.changeControlMode(TalonControlMode.Follower);
		shooterBottom.set(shooterTop.getDeviceID());
//		shooterBottom.changeControlMode(TalonControlMode.PercentVbus);
		
/*		// SmartDashboard preferences not implemented, doing magic numbers		
		shooterBottom.setPID(Prefs.getDouble("ShootP", 0), 
				Prefs.getDouble("ShootI", 0), 
				Prefs.getDouble("ShootD", 0));
//*/	
/*		
		shooterTrigger.changeControlMode(TalonControlMode.Speed);
		shooterTrigger.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		shooterTrigger.configEncoderCodesPerRev(360);
		shooterTrigger.setPID(0, 0, 0);
//*/		
		
/*		// SmartDashboard preferences not implemented, doing magic numbers		
		shooterTrigger.setPID(Prefs.getDouble("TriggerP", 0), 
				Prefs.getDouble("TriggerI", 0), 
				Prefs.getDouble("TriggerD", 0));
//*/		
				
		leftDriveMaster.setPosition(0);
        rightDriveMaster.setPosition(0);
        shooterTop.enable();
//        shooterTop.enableControl();
        shooterBottom.enable();
//        shooterBottom.enableControl();
//        shooterTrigger.enable();
	
	}
	
	/**
	 * Configure PID settings for all motors
	 * TODO: Put PID configurations in each subsystem initialization
	 */
	public void setPID() {
//		shooterTop.setPID(Prefs.getDouble("ShootP", 0), Prefs.getDouble("ShootI", 0), Prefs.getDouble("ShootD", 0));
//		shooterBottom.setPID(Prefs.getDouble("ShootP", 0), Prefs.getDouble("ShootI", 0), Prefs.getDouble("ShootD", 0));
//		shooterTrigger.setPID(Prefs.getDouble("TriggerP", 0), Prefs.getDouble("TriggerI", 0), Prefs.getDouble("TriggerD", 0));
//		armMotor.setPID(Prefs.getDouble("ArmP", 0), Prefs.getDouble("ArmI", 0), Prefs.getDouble("ArmD", 0));		
//		armMotor.configMaxOutputVoltage(armVolts);
//		armMotor.enable();
		winch.setPosition(0);
	}
}
