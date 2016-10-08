package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;


//TODO: tune the PIDs
public class StrongholdMotorPreferences {
	
	/**
	 * Default constructor for StrongholdMotorPreferences object.
	 * This constructor calls all of the initialization methods
	 * for the subsystem motors.
	 */	
	
	
	
	

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
