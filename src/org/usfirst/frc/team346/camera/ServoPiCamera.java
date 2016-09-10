package org.usfirst.frc.team346.camera;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ServoPiCamera extends PiCamera
{
	Servo servo;
	int lowServoPos;
	float lowCameraAngle;
	int highServoPos;
	float highCameraAngle;

	boolean isLow = true;

	public ServoPiCamera(Servo servo, int lowServoPos, float lowCameraAngle, int highServoPos, float highCameraAngle)
	{
		this.servo = servo;
		this.lowServoPos = lowServoPos;
		this.lowCameraAngle = lowCameraAngle;
		this.highServoPos = highServoPos;
		this.highCameraAngle = highCameraAngle;
		goLow();
	}

	public void goLow()
	{
		servo.set(lowServoPos);
		cameraAngle = lowCameraAngle;

		if(isValid && !isLow) //we switched from high to low and we already have tracking info ... recalculate 
		{
			calculatePosition();
		}

		isLow = true;
	}


	public void goHigh()
	{
		servo.set(highServoPos);
		cameraAngle = highCameraAngle;
		
		if(isValid && isLow) //we switched from low to high and we already have tracking info ... recalculate 
		{
			calculatePosition();
		}

		isLow = false;

	}

}
