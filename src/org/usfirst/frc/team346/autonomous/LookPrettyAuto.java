package org.usfirst.frc.team346.autonomous;

import org.usfirst.frc.team346.subsystem.Arm;
import org.usfirst.frc.team346.subsystem.Drive;
import org.usfirst.frc.team346.subsystem.Shooter;

import edu.wpi.first.wpilibj.interfaces.Gyro;

public class LookPrettyAuto implements StrongholdAutonomous {

	Drive RobotDrive;
	Gyro gyro;
	Arm Arm;
	Shooter Shooter;
	boolean setTurn = false;
	double heading = 0.0;

	public LookPrettyAuto(Drive D, Gyro gyro, Arm arm, Shooter Shooter){
		RobotDrive = D;
		this.gyro = gyro;
		this.Arm = arm;
		this.Shooter = Shooter;
	}
	

	public void DoAutonomousLogic(double matchtime){
		if(matchtime < 1 && !setTurn){
			setTurn = true;
			heading = gyro.getAngle();
			RobotDrive.LowGear();
			Arm.LoadBall();
			Shooter.closeJaws();
		}
		if(matchtime>5 && matchtime < 10){
			RobotDrive.DriveWithHeadingHold(-.6, .6, heading);
		}
		if(matchtime>10){
			RobotDrive.drive(0, 0);
			RobotDrive.HighGear();
			Arm.Shoot();
		}
	}
}

	

