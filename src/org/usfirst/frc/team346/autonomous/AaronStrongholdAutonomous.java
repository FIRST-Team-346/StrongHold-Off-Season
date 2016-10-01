package org.usfirst.frc.team346.autonomous;

import org.usfirst.frc.team346.robot.Arm;
import org.usfirst.frc.team346.robot.Drive;

import edu.wpi.first.wpilibj.interfaces.Gyro;

public class AaronStrongholdAutonomous implements StrongholdAutonomous{

	Drive RobotDrive;
	Arm Arm;
	Gyro gyro;
	boolean setTurn = false;
	double heading = 0.0;

	public AaronStrongholdAutonomous(Drive D, Gyro gyro, Arm arm){
		RobotDrive = D;
		this.gyro = gyro;
		heading = gyro.getAngle();
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
			RobotDrive.DriveWithHeadingHold(-.6, -.6, heading);
			if(matchtime > 1)
				setTurn = false;
		}
		else if (matchtime >= 3 && matchtime < 5){
			if(!setTurn)
			{
				heading = gyro.getAngle()-180;
				setTurn = true;
			}
			
			RobotDrive.DriveWithHeadingHold(0,0, heading);
		}

		else if (matchtime >= 5 && matchtime < 8){
			RobotDrive.DriveWithHeadingHold(-.6, -.6, heading);
		}
		else
		{
			RobotDrive.drive(0, 0);
		}
	}
}
