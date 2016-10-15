/*package org.usfirst.frc.team346.subsystem;

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

	private CANTalon m_m_armMotor;
	
	public Arm () {
		this.m_m_armMotor = new CANTalon(RobotMap.ARM_MOTOR_PORT);
		this.m_m_armMotor.changeControlMode(TalonControlMode.Position);				// Set arm to position mode		
		this.m_m_armMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);	// Set encoder type		
		this.m_m_armMotor.setPID(10, 0, 0.002);										// Set PID parameters
		this.m_m_armMotor.enable();													// Enable arm motor output
		this.m_m_armMotor.enableControl();											// Enable control for arm motor (position control)
	}
	
	public void setArmPosition(double position) {
		if (this.m_m_armMotor.get() != position) {
			this.m_m_armMotor.set(position);
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
		if(m_armMotor.get() == PositionShoot) // need to create dead zone?
			return true;
		return false;
	}
	
	public void GateUp() {
		setArmPosition(PositionGateUp);
	}
	
	public boolean IsAbleToLift() {	
		//need to create dead zone?
		if(m_armMotor.get() == PositionGateDown) {
			return true;
		}
		
		return false;
	}
	
	public void Stop() {
    	m_armMotor.set(0);
    	if (m_armMotor.isEnabled()) {
    		m_armMotor.disable();
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
		if(m_armMotor.getControlMode() != TalonControlMode.PercentVbus) {	
			m_armMotor.changeControlMode(TalonControlMode.PercentVbus);
			m_armMotor.setVoltageRampRate(24);
		}
			
//		//if(m_armMotor.getPosition() > PositionShoot /*Position45*///){
//			m_armMotor.set(-1);
//		}else{
//			m_armMotor.set(0);
//			//We need to disable vramp for the motor to stop properly
//			//this is a bug in the firmware
//			m_armMotor.setVoltageRampRate(0);
//			m_armMotor.disable();
//			m_armMotor.disableControl();
//		}
//	}
//}*/
