package org.usfirst.frc.team346.subsystem;

import org.usfirst.frc.team346.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
public class Arm implements Subsystem {
	
	/**
	 * Public enumeration for arm position. 
	 * These positions correspond to encoder 
	 * ticks.
	 * 
	 * @author Adam Morrissett
	 *
	 */
	public enum ArmPosition {
		LOAD(220),		// Original 176
		TRAVEL(297),	// Original 254
		SHOOT(470),		// Original 392				
		START(372),		// Original 339
		CLIMB(502);		// Original 680
		
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
	 * This method enables the subsystem. 
	 * This allows the subsystem to 
	 * actually be driven.
	 */
	@Override
	public void enable() {
		this.m_armMotor.enable();
	}
		
	/**
	 * This method disables the subsystem. 
	 * This method could be used for any 
	 * sort of safety driven disable.
	 */
	@Override
	public void disable() {
		this.m_armMotor.disable();
	}
	
	/**
	 * This method should be called during the 
	 * autonomousPeriodic() and/or teleopPeriodic()
	 * methods. It is the main entry point for all
	 * subsystem logic.
	 */
	@Override
	public void runPeriodic(Object...objects) {
		
	}
	
	/**
	 * Set the arm position to a specified position.
	 * 
	 * @param _position target position of the arm
	 */
	public void setArmPosition(ArmPosition _position) {
		if (this.getArmPosition() != _position.getPosition()) {
												
			this.m_armMotor.set(_position.getPosition());
		}	
		switch(_position) {
			case LOAD : SmartDashboard.putString("Arm Position ", "LOAD");
			break;						
			case TRAVEL : SmartDashboard.putString("Arm Position ", "TRAVEL");
			break;	
			case SHOOT : SmartDashboard.putString("Arm Position ", "SHOOT");
			break;	
			case START : SmartDashboard.putString("Arm Position ", "START");
			break;	
			case CLIMB : SmartDashboard.putString("Arm Position ", "CLIMB");
			break;	
		}
	}	
	
	/**
	 * Gets the current position of the arm.
	 * 
	 * @return the current position of the arm
	 */
	public double getArmPosition() {
		return this.m_armMotor.get(); 
	}
}
