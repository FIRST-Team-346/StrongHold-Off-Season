package org.usfirst.frc.team346.subsystem;

import org.usfirst.frc.team346.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	private HookPosition m_hookPosition;
	
	/*
	 * For some reason the supply port for the 
	 * hook DVC is controlled by another DVC.
	 * The hook supply is the DVC that controls
	 * when the hook DVC can receive air.
	 */
	private Solenoid m_hookSupply;
	
    /**
     * Default constructor for winch object.
     */
	public Climber() {
		/*this.m_engagementLock = new Solenoid(
				RobotMap.WINCH_ENGAGEMENT_LOCK_MODULE,
				RobotMap.WINCH_ENGAGEMENT_LOCK_PORT);*/
		this.m_winchMotor = new CANTalon(RobotMap.WINCH_MOTOR_PORT);
		this.m_hook = new DoubleSolenoid(RobotMap.HOOK_MODULE,
				RobotMap.HOOK_PORT_1, RobotMap.HOOK_PORT_2);
		this.m_hookSupply = new Solenoid(RobotMap.HOOK_SUPPLY_MODULE,
				RobotMap.HOOK_SUPPLY_PORT);
		this.m_hookPosition = HookPosition.RESET;
		this.setHookPosition(HookPosition.RESET);
	}
	
	/**
	 * Set the hook to a specified position.
	 * 
	 * @param _position specified position of hook
	 */
	public void setHookPosition(HookPosition _position) {
		switch(_position) {
			case EXTEND : {
				SmartDashboard.putString("Hook Position ", "EXTEND");
				this.m_hook.set(Value.kReverse);
				this.m_hookSupply.set(true);
				this.m_hookPosition = HookPosition.EXTEND;
			}; break;
			case RESET : {
				SmartDashboard.putString("Hook Position ", "RESET");
				this.m_hook.set(Value.kForward);
				this.m_hookSupply.set(false);
				this.m_hookPosition = HookPosition.RESET;
			}; break;
			case RETRACT : {
				SmartDashboard.putString("Hook Position ", "RETRACT");
				this.m_hook.set(Value.kForward);
				this.m_hookSupply.set(true);
				this.m_hookPosition = HookPosition.RETRACT;
			}; break;
		}
	}
	
	/**
	 * Gets the current position of the hook.
	 * 
	 * @return current position of the hook
	 */
	public HookPosition getHookPosition() {
		return this.m_hookPosition;
	}
	
	/**
	 * Sets the motor state.
	 * 
	 * @param _state desired motor state
	 */
	public void setMotorState(MotorState _state) {
		switch(_state) {
			case STOP : {
				SmartDashboard.putString("Hook Winch State ", "STOP");
				this.m_winchMotor.set(0);
			}; break;
			case HANG : {
				SmartDashboard.putString("Hook Winch State ", "HANG");
				this.m_winchMotor.set(-1);
			}; break;
			case REVERSE : {
				SmartDashboard.putString("Hook Winch State ", "REVERSE");
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
				SmartDashboard.putString("Hook Engagement ", "ENGAGED");
				this.m_engagementLock.set(true);
			}; break;
			case DISENGAGED : {
				SmartDashboard.putString("Hook Engagement ", "DISENGAGED");
				this.m_engagementLock.set(false);
			}; break;
		}
	}
}
