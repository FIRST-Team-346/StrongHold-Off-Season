package org.usfirst.frc.team346.subsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

//TODO: make sure these positions are right
public class Winch {

	DoubleSolenoid WinchGear;

	Solenoid HookSupply;
	DoubleSolenoid Hook;
	CANTalon winch; 
	double PositionHang;
	
	public Winch (DoubleSolenoid winchGear, CANTalon winchMotor, Solenoid hookSupply, DoubleSolenoid hook)
	{
		WinchGear = winchGear;
		HookSupply = hookSupply;
		winch = winchMotor;
		PositionHang = -0.2;
		Hook = hook;
		HookSupply.set(false);
	}
	
	public void resetHook()
	{
		Hook.set(DoubleSolenoid.Value.kForward);
		HookSupply.set(false);
		
	}
	
	public void DeployHook()
	{
		
		Hook.set(DoubleSolenoid.Value.kReverse);
		HookSupply.set(true);
	}
	public void retractHook(){
		
		Hook.set(DoubleSolenoid.Value.kForward);
		HookSupply.set(true);
	}
	public void normalHook(){
		Hook.set(DoubleSolenoid.Value.kForward);
	}
	
	public void startHanging()
	{
		//if (WinchMotor.get() != PositionHang) 
		{
			winch.set(-1.0);			
		}	
	}
	public void reverseWinch(){
		winch.set(0.3);
	}
	public void stopTurning(){
		winch.set(0);
	}
	public boolean IsHanging()
	{
		return winch.getPosition() >= PositionHang;
	}	
	public void reset()
	{
		winch.disable();
	}
	
}
