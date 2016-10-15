package org.usfirst.frc.team346.subsystem;

import org.usfirst.frc.team346.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

/**
 * TODO: Recalibrate encoders for arm positioning
 * TODO: Tune PID parameters
 */

/**
 * This class controls the positioning of the arm
 * 
 * @author Grant R.
 *
 */
public class Arm {
	
	/**
	 * Public enumeration for arm position. 
	 * These positions correspond to encoder 
	 * ticks.
	 * 
	 * @author Adam Morrissett
	 *
	 */
	public enum ArmPosition {
		LOAD(176),
		TRAVEL(254),
		SHOOT(392),
		GATE_DOWN(190),
		GATE_UP(380),
		RESET(290),
		LIFT(460),
		START(339),
		CLIMB(680);
		
		private int m_position;
		
		/**
		 * Get the position of the enumeration.
		 * 
		 * @return number of encoder ticks to reach specified
		 * position
		 */
		public int getPosition() {
			return this.m_position;
		}
		
		/**
		 * Custom constructor for arm position enumeration
		 * 
		 * @param _position
		 */
		private ArmPosition(int _position) {
			this.m_position = _position;
		}
	}
	
	// Motor controller declarations
	private CANTalon m_armMotor;
	
	/**
	 * Default constructor for Arm object.
	 */
	public Arm () {
		this.m_armMotor = new CANTalon(RobotMap.ARM_MOTOR_PORT);					// Instantiate arm motor controller
		this.m_armMotor.changeControlMode(TalonControlMode.Position);				// Set arm to position mode		
		this.m_armMotor.setFeedbackDevice(CANTalon.FeedbackDevice.AnalogEncoder);	// Set encoder type		
		this.m_armMotor.setPID(10, 0, 0.002);										// Set PID parameters
		this.m_armMotor.enable();													// Enable arm motor output		
	}
	
	/**
	 * Set the arm position to a specified position.
	 * 
	 * @param _position target position of the arm
	 */
	public void setArmPosition(double _position) {
		if (this.m_armMotor.get() != _position) {
			this.m_armMotor.set(_position);
		}	
	}	
}
