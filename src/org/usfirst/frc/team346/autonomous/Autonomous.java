package org.usfirst.frc.team346.autonomous;

import org.usfirst.frc.team346.robot.dummyPIDOutput;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
public class Autonomous {

	CANTalon left;
	CANTalon right;
	dummyPIDOutput Gpid;

	public Autonomous(CANTalon a, CANTalon b, PIDController g, dummyPIDOutput gy) {
		left = a;
		right = b;
        Gpid = gy;
        
        }
	public void AutoMode(double time1) {

		if(time1 < 5){
			left.set(-.6 - Gpid.out);
			right.set(-.6 + Gpid.out);
		}
	}
}
	