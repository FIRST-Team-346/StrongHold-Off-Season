package org.usfirst.frc.team346.robot;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class StrongholdSolenoidPreferences {

	DoubleSolenoid SpeedGear;
	DoubleSolenoid WinchGear;
	DoubleSolenoid Hook;
	Solenoid HookSupply;
	
	Solenoid Gripper;
	Solenoid Trigger;
	Solenoid winchLatch;
	
	Compressor compressor;
	
//	Solenoid red;
//	Solenoid green;
//	Solenoid blue;
	
	public StrongholdSolenoidPreferences(){
        SpeedGear  = new DoubleSolenoid(1,0,1);
        
        Hook  = new DoubleSolenoid(1,4,5);
        HookSupply = new Solenoid(1,2);
        
//        green = new Solenoid(2,1);
//		red = new Solenoid(2,2);
//        blue = new Solenoid(2,3);
        winchLatch = new Solenoid(2,4);
    	Gripper = new Solenoid(2,6);
		Trigger = new Solenoid(2,7);
		
		compressor = new Compressor(2);
	}
}
