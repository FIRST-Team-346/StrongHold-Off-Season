package org.usfirst.frc.team346.subsystem;

import org.usfirst.frc.team346.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

public class Climber {

	/*
	 * The engagement lock might need to be
	 * changed to a double solenoid.
	 */
	private Solenoid m_engagementLock;
	private CANTalon m_winchMotor;
	private DoubleSolenoid m_hook;
	
	// Old stuff
	DoubleSolenoid m_WinchGear;

	Solenoid m_HookSupply;
	DoubleSolenoid m_Hook;
	CANTalon m_winch; 
	double m_PositionHang;
	
	DoubleSolenoid Hook  = new DoubleSolenoid(1,4,5);
    Solenoid HookSupply = new Solenoid(1,2);

    Solenoid winchLatch = new Solenoid(2,4);
	

    /**
     * Default constructor for winch object.
     */
	public Climber (){
		this.m_engagementLock = new Solenoid(
				RobotMap.WINCH_ENGAGEMENT_LOCK_MODULE,
				RobotMap.WINCH_ENGAGEMENT_LOCK_PORT);
		this.m_winchMotor = new CANTalon(RobotMap.WINCH_MOTOR_PORT);	
		
	}
	
	public void resetHook()
	{
		m_Hook.set(DoubleSolenoid.Value.kForward);
		m_HookSupply.set(false);
		
	}
	
	public void DeployHook()
	{
		
		m_Hook.set(DoubleSolenoid.Value.kReverse);
		m_HookSupply.set(true);
	}
	public void retractHook(){
		
		m_Hook.set(DoubleSolenoid.Value.kForward);
		m_HookSupply.set(true);
	}
	public void normalHook(){
		m_Hook.set(DoubleSolenoid.Value.kForward);
	}
	
	public void startHanging()
	{
		//if (WinchMotor.get() != PositionHang) 
		{
			m_winch.set(-1.0);			
		}	
	}
	public void reverseWinch(){
		m_winch.set(0.3);
	}
	public void stopTurning(){
		m_winch.set(0);
	}
	public boolean IsHanging()
	{
		return m_winch.getPosition() >= m_PositionHang;
	}	
	public void reset()
	{
		m_winch.disable();
	}
}
