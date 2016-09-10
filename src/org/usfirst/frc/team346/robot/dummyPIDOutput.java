package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class dummyPIDOutput implements PIDOutput{

public double out;
	
	public dummyPIDOutput(){}

	public void pidWrite(double output) {
		out = output;
	}

	
}
