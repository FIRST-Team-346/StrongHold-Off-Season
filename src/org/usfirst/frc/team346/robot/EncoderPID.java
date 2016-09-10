package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.PIDOutput;

public class EncoderPID implements PIDOutput {

public double out;
	
	public EncoderPID(){}
	
	public void pidWrite(double output) {
		out = output;
	}
}
