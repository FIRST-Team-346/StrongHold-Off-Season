package org.usfirst.frc.team346.subsystem;

import org.usfirst.frc.team346.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

//TODO: make sure these positions are right.
public class Arm {
														// Instantiate winch motor
	public final double PositionLoad = 176;
	public final double PositionTravel = 254;
	public final double PositionShoot = 392;
	public final double PositionGateDown = 190;
	public final double PositionGateUp = 380;
	public final double PositionReset = 290;
	public final double PositionLift = 460;
	public final double Position45 = 339;
	
	//setup procedure
	//Limit voltage to 6v
	//Lower arm until load position has be reached
	//Rotate encoder so it reads ~PositionLoad on the dashboard

	private CANTalon m_armMotor;
	
	public Arm () {
		this.m_armMotor = new CANTalon(RobotMap.ARM_MOTOR_PORT);
		this.m_armMotor.changeControlMode(TalonControlMode.Position);				// Set arm to position mode		
		this.m_armMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);	// Set encoder type		
		this.m_armMotor.setPID(10, 0, 0.002);										// Set PID parameters
		this.m_armMotor.enable();													// Enable arm motor output
		this.m_armMotor.enableControl();											// Enable control for arm motor (position control)
	}
	
	public void setArmPosition(double position) {
		if (this.m_armMotor.get() != position) {
			this.m_armMotor.set(position);
		}	
	}
	
	public void LoadBall() {
		setArmPosition(PositionLoad);
	}
	
	public void Travel() {
		setArmPosition(PositionTravel);			
	}
	
	public void Shoot() {
		setArmPosition(PositionShoot);
	}
	
	public void GateDown() {
		setArmPosition(PositionGateDown);
	}
	public void Lift() {
		setArmPosition(PositionLift);
	}
	
	public boolean IsAbleToFire() {
		if(ArmMotor.get() == PositionShoot) // need to create dead zone?
			return true;
		return false;
	}
	
	public void GateUp() {
		setArmPosition(PositionGateUp);
	}
	
	public boolean IsAbleToLift() {	
		//need to create dead zone?
		if(ArmMotor.get() == PositionGateDown) {
			return true;
		}
		
		return false;
	}
	
	public void Stop() {
    	ArmMotor.set(0);
    	if (ArmMotor.isEnabled()) {
    		ArmMotor.disable();
    	}
	}
	
	public void Reset() {
		//setArmPosition(PositionReset);
		setArmPosition(Position45);
	}
	
	//public void fourtyFiveDegrees(){
	//	setArmPosition(Position45);
	//}
	
	public void hang() {
		if(ArmMotor.getControlMode() != TalonControlMode.PercentVbus) {	
			ArmMotor.changeControlMode(TalonControlMode.PercentVbus);
			ArmMotor.setVoltageRampRate(24);
		}
			
		//if(ArmMotor.getPosition() > PositionShoot /*Position45*/){
			ArmMotor.set(-1);
//		}else{
//			ArmMotor.set(0);
//			//We need to disable vramp for the motor to stop properly
//			//this is a bug in the firmware
//			ArmMotor.setVoltageRampRate(0);
//			ArmMotor.disable();
//			ArmMotor.disableControl();
//		}
	}
}
