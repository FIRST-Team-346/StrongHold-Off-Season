package org.usfirst.frc.team346.subsystem;

import org.usfirst.frc.team346.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Climber {

	/**
	 * Enumeration for winch engagement.
	 * 
	 * @author Adam Morrissett
	 *
	 */
	public static enum EngagementState {
		ENGAGED,
		DISENGAGED;
	}
	
	/**
	 * Enumeration for hook position.
	 * 
	 * @author Adam Morrissett
	 *
	 */
	public static enum HookPosition {
		EXTEND,
		RETRACT,
		RESET;
	}
	
	/**
	 * Enumeration for winch motor state.
	 * 
	 * @author Adam Morrissett
	 *
	 */
	public static enum MotorState {
		STOP,
		HANG,
		REVERSE;
	}
	
	/*
	 * The engagement lock might need to be
	 * changed to a double solenoid. This
	 * is the solenoid on the transmission
	 * to engage the motor.
	 */
	private Solenoid m_engagementLock;
	private CANTalon m_winchMotor;
	private DoubleSolenoid m_hook;
	
	// Old stuff
	Solenoid m_HookSupply;
		
    Solenoid HookSupply = new Solenoid(1,2);    
	
    /**
     * Default constructor for winch object.
     */
	public Climber() {
		this.m_engagementLock = new Solenoid(
				RobotMap.WINCH_ENGAGEMENT_LOCK_MODULE,
				RobotMap.WINCH_ENGAGEMENT_LOCK_PORT);
		this.m_winchMotor = new CANTalon(RobotMap.WINCH_MOTOR_PORT);
		this.m_hook = new DoubleSolenoid(RobotMap.HOOK_MODULE,
				RobotMap.HOOK_PORT_1, RobotMap.HOOK_PORT_2);
	}
	
	/**
	 * Set the hook to a specified position.
	 * 
	 * @param _position specified position of hook
	 */
	public void setHookPosition(HookPosition _position) {
		switch(_position) {
			case EXTEND : {
				this.m_hook.set(Value.kReverse);
				this.m_HookSupply.set(true);			
			}; break;
			case RESET : {
				this.m_hook.set(Value.kForward);
				this.m_HookSupply.set(false);
			}; break;
			case RETRACT : {
				this.m_hook.set(Value.kForward);
				this.m_HookSupply.set(true);
			}; break;
		}
	}
	
	/**
	 * Sets the motor state.
	 * 
	 * @param _state desired motor state
	 */
	public void setMotorState(MotorState _state) {
		switch(_state) {
			case STOP : {
				this.m_winchMotor.set(0);
			}; break;
			case HANG : {
				this.m_winchMotor.set(-1);
			}; break;
			case REVERSE : {
				this.m_winchMotor.set(0.3);
			}; break;
		}
	}
	
	/**
	 * Sets the engagement solenoid state.
	 * 
	 * @param _state desired engagement state
	 */
	public void setWinchEngagement(EngagementState _state) {
		switch(_state) {
			case ENGAGED : {
				this.m_engagementLock.set(true);
			}; break;
			case DISENGAGED : {
				this.m_engagementLock.set(false);
			}; break;
		}
	}
}
