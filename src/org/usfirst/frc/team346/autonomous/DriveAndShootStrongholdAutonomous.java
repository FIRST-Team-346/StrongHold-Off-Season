package org.usfirst.frc.team346.autonomous;

import org.usfirst.frc.team346.subsystem.Arm;
import org.usfirst.frc.team346.subsystem.Drive;
import org.usfirst.frc.team346.subsystem.Shooter;

import edu.wpi.first.wpilibj.interfaces.Gyro;

public class DriveAndShootStrongholdAutonomous {
/*
	Drive RobotDrive;
	Gyro gyro;
	Arm Arm;
	boolean setTurn = false;
	double heading = 0.0;
	
	double crossDefenseSpeed = -0.6;
	double driveToTargetSpeed = -0.6;

	PiCamera Camera;
	Shooter Shooter;
	double maxError = 5; //Max error in degrees
	double minDistance = 5;
	double searchSpeed = 0.1; //Ammount we add to heading to search for the target
	double trackSpeed = 0.1; //Ammount we add to heading to try to track the target

	public DriveAndShootStrongholdAutonomous(Drive D, Gyro gyro, Arm arm, PiCamera Camera, Shooter Shooter){
		RobotDrive = D;
		this.gyro = gyro;
		this.Camera = Camera;
		this.Shooter = Shooter;
		this.Arm = arm;
	}
	

	public void DoAutonomousLogic(double matchtime){
		if(matchtime < 1 && !setTurn){
			setTurn = true;
			heading = gyro.getAngle();
			RobotDrive.LowGear();
		}
		if(matchtime < 3){
			Arm.Travel();
			RobotDrive.DriveWithHeadingHold(crossDefenseSpeed, crossDefenseSpeed, heading);
		}
		else
		{
			
			//Get arm in the upper position
			Arm.Shoot();
			//If we dont have a target spin to look for one
			if(!Camera.hasTarget())
			{
				//We may need to reverse this
				RobotDrive.DriveWithHeadingHold(0, 0, heading);
				heading += searchSpeed;
			}
			else
			{
				//We have a target ... are we close enough?
				if(Camera.getLinearDistance() > 5)
				{
					//Not close enough
					//Drive toward the target
					if(Camera.getLinearAngle() < maxError)
					{
						heading -= trackSpeed;
					}
					else if(Camera.getLinearAngle() > maxError)
					{
						heading += trackSpeed;
					}
					RobotDrive.DriveWithHeadingHold(driveToTargetSpeed, driveToTargetSpeed, heading);
				}
				else
				{
					//Make sure we are on target
					if(Camera.getLinearAngle() < maxError)
					{
						heading -= trackSpeed;
					}
					else if(Camera.getLinearAngle() > maxError)
					{
						heading += trackSpeed;
					} 
					else
					{
						//Close enough on target ... shoot and fire
						Shooter.AutoShootAndFire();
					}
					RobotDrive.DriveWithHeadingHold(0, 0, heading);
				}
			}
		}	
	}*/
}

	

