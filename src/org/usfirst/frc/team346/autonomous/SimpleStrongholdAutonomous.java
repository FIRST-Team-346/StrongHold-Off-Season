package org.usfirst.frc.team346.autonomous;

import org.usfirst.frc.team346.subsystem.Arm;
import org.usfirst.frc.team346.subsystem.Drive;
import org.usfirst.frc.team346.subsystem.Shooter;

import edu.wpi.first.wpilibj.interfaces.Gyro;
/*
public class SimpleStrongholdAutonomous {

	Drive RobotDrive;
	Gyro gyro;
	Arm Arm;
	Shooter Shooter;
	boolean setTurn = false;
	double heading = 0.0;

	public SimpleStrongholdAutonomous(Drive D, Gyro gyro, Arm arm){
		RobotDrive = D;
		this.gyro = gyro;
		this.Arm = arm;
	}
	

	public void DoAutonomousLogic(double matchtime){
		if(matchtime < 1 && !setTurn){
			setTurn = true;
			heading = gyro.getAngle();
			RobotDrive.LowGear();
		}
		if(matchtime < 5){
			Arm.Travel();
			Shooter.closeJaws();
			RobotDrive.DriveWithHeadingHold(-.6, .6, heading);
		}
		if(matchtime>5){
			RobotDrive.drive(0, 0);
			Arm.Shoot();
			RobotDrive.HighGear();
		}
	}
}

	

*/