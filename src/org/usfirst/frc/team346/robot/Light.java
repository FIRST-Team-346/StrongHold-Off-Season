package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.Solenoid;

public class Light {

	Solenoid LightSolenoid;
	String Name;
	public double flashRate = 0;
	public enum lightState{On,Off,Flashing};
	public lightState CurrentState = lightState.Off;

	public Light(Solenoid l, String n){
		LightSolenoid = l;
		Name = n;
	}
	
	public void TurnOn()
	{
		LightSolenoid.set(true);
	}
	
	public void TurnOff()
	{
		LightSolenoid.set(false);
	}
	
	public boolean isOn()
	{
		if(LightSolenoid.get())
			return true;
		else
			return false;
	}
	
}