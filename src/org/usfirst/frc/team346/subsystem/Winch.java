package org.usfirst.frc.team346.subsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;

//TODO: make sure these positions are right
public class Winch {

	DoubleSolenoid m_WinchGear;

	Solenoid m_HookSupply;
	DoubleSolenoid m_Hook;
	CANTalon m_winch; 
	double m_PositionHang;
	
	Hook  = new DoubleSolenoid(1,4,5);
    HookSupply = new Solenoid(1,2);

    winchLatch = new Solenoid(2,4);
	

	
	public Winch (DoubleSolenoid winchGear, CANTalon winchMotor, Solenoid hookSupply, DoubleSolenoid hook){
		m_WinchGear = winchGear;
		m_HookSupply = hookSupply;
		m_winch = winchMotor;
		m_PositionHang = -0.2;
		m_Hook = hook;
		m_HookSupply.set(false);
		
		this.m_winch.changeControlMode(TalonControlMode.PercentVbus);			// Set winch to %Vbus mode				
		this.m_winch.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);	// Set encoder type
		this.m_winch.configEncoderCodesPerRev(360);								// Set encoder codes per revolution
		this.m_winch.disable();													// Disable  motor output	
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
