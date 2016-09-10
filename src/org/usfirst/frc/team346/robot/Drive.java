package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;

public class Drive {

	CANTalon LeftMaster;
	CANTalon RightMaster;
	DoubleSolenoid SpeedGear;
	AnalogGyro HeadingGyro;
	dummyPIDOutput Gpid;
	PIDController gyroPIDController;
	Preferences Prefs;
	boolean lowGear;
	boolean highGear;
	
	public Drive(CANTalon l, CANTalon r, DoubleSolenoid sg, PIDController headingHoldController)
	{
		LeftMaster = l;
		RightMaster = r;
		SpeedGear = sg;
		
		lowGear=true;
		
		Gpid = new dummyPIDOutput();
		gyroPIDController = headingHoldController;
	}
	
	public void drive(double left, double right)
	{
		if (gyroPIDController.isEnabled())
		{
			gyroPIDController.disable();
    			Gpid.out = 0;
		}

		LeftMaster.set(left);
	    RightMaster.set(right);
	}
	
	public void DriveWithHeadingHold(double left, double right, double angle)
	{
	    	if (!gyroPIDController.isEnabled())
	    	{
	    		gyroPIDController.enable();
	    		gyroPIDController.setSetpoint(angle);
	    	}
		/*if(gyroPIDController.getSetpoint() != angle)
		{
			gyroPIDController.setSetpoint(angle);
		}*/

		LeftMaster.set(left - Gpid.out);
		RightMaster.set(right + Gpid.out);
	}
	
	public void HighGear()
	{
		if(lowGear)
		{
			SpeedGear.set(DoubleSolenoid.Value.kForward);
			lowGear=false;
		}
	}
	
	public void LowGear()
	{

		if(!lowGear)
		{
			SpeedGear.set(DoubleSolenoid.Value.kReverse);
			lowGear = true;
		}
	}
	
	public boolean isHighGear()
	{
		return (!lowGear);
	}
	
	public boolean isLowGear()
	{
		return (lowGear);

	}
	
	public void toggleGear()
	{
		System.out.println("Shifting");
		if(isLowGear())
		{
			HighGear();
		}
		else
		{
			LowGear();
		}
	}
	
	public void setPID(String prefix)
	{
		gyroPIDController.setPID(Prefs.getDouble(prefix + "P", 0), Prefs.getDouble(prefix + "I", 0), Prefs.getDouble(prefix + "D", 0));
	}
	
	public double GyroAngle(){
		return HeadingGyro.getAngle();
	}
}
