package org.usfirst.frc.team346.camera;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PiCamera extends Thread
{
	boolean isValid;

	double lastTargetX;
	double lastTargetY;
	double lastTargetZ;

	double lastTargetRotX;
	double lastTargetRotY;
	double lastTargetRotZ;

	double linearDistance;
	double linearAngle;

	double robotX;
	double robotY;
	double robotZ;

	//This is the angle in which the camera is rotated up, 
	//we need this to determine the hypotenuse 
	public double cameraAngle = 0;
	final double maxError = 1.5; //Degrees either way for error .... to be changed later
	final double goalOpening = 16.0/12; //Calculation in feet ..
	final double ballDiam = 10.0/12; //Calculation in feet ..
	final double ballRad = ballDiam/2.0; //Calculation in feet ..

	public void run()
	{
		while(true)
		{
			tick();
			trySleep(1);
		}
	}

	public void trySleep(long ms)
	{
		try
		{
			sleep(ms);
		} catch(Exception ex)
		{}
	}

	public void tick()
	{
		try
		{
			isValid = SmartDashboard.getBoolean("camera_isValid");

			lastTargetX = SmartDashboard.getNumber("camera_last_target_x");
			lastTargetY = SmartDashboard.getNumber("camera_last_target_y");
			lastTargetZ = SmartDashboard.getNumber("camera_last_target_z");

			lastTargetRotX = SmartDashboard.getNumber("camera_last_target_rot_x");
			lastTargetRotY = SmartDashboard.getNumber("camera_last_target_rot_y");
			lastTargetRotZ = SmartDashboard.getNumber("camera_last_target_rot_z");  

			if(isValid)
			{
				calculatePosition();
			}
		}
		catch(Exception e)
		{
			isValid = false;
		}      
	}

	protected void calculatePosition()
	{

		double opp = -1*lastTargetY; //Negative is up so reverse it first
		double hypo = lastTargetZ; //Z is the distance in a straight line from the lens to the center
		double alpha = Math.toDegrees(Math.asin(opp/hypo));
		alpha += cameraAngle;
		//cos(angle) = adj/hypo
		double adj = hypo*Math.cos(Math.toRadians(alpha));
		linearDistance = adj;
		opp = lastTargetX;
		alpha = Math.toDegrees(Math.asin(opp/hypo));
		linearAngle = alpha;
		
	}


/************************************************************************************************
Getters 
************************************************************************************************/
	public boolean hasTarget()
	{
		return isValid;
	}


	public double getTargetCameraX()
	{
		return lastTargetX;
	}
	public double getTargetCameraY()
	{
		return lastTargetY;
	}
	public double getTargetCameraZ()
	{
		return lastTargetZ;
	}


	public double getTargetCameraRotX()
	{
		return lastTargetRotX;
	}
	public double getTargetCameraRotY()
	{
		return lastTargetRotY;
	}
	public double getTargetCameraRotZ()
	{
		return lastTargetRotZ;
	}


	//This is the distance ON THE GROUND from the robot to the BASE of where the target is
	public double getLinearDistance()
	{
		return linearDistance;
	}

	//This is the rotation of the robot ON THE GROUND in relation to the target
	public double getLinearAngle()
	{
		return linearAngle;
	}

	public boolean onTargetAngle()
	{
		return Math.abs(linearAngle) <= maxError;
	}

	public boolean onTargetBallInGoal()
	{
		//TODO: Calculate based on the target's rotational vector as well as the opening closes as it is rotated
		return Math.abs(lastTargetX) <= Math.abs(Math.cos(lastTargetRotZ)*goalOpening)/2.0-ballRad;
	}
		
}
