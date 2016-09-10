package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardUpdater {

	AnalogInput temp1;
    AnalogInput temp2;
   	AnalogInput underPressure;
   	AnalogGyro gyro;
   	Joystick controller1;
	Joystick controller2;
	PIDController gyroPIDController;
	StrongholdMotorPreferences motors;
	
	public SmartDashboardUpdater(AnalogInput t1, AnalogInput t2, AnalogInput up, StrongholdMotorPreferences m, PIDController gp, 
			Joystick left, Joystick right, AnalogGyro g)
	{
		temp1 = t1;
		temp2 = t2;
		motors= m;
		gyroPIDController = gp;
		controller1 = left;
		controller2 = right;
		gyro = g;
		underPressure = up;
	}
	public void UpdateSmartDashboard()
	{
		SmartDashboard.putNumber("Temp1", ((temp1.getVoltage()*1000)-500)/10);
		SmartDashboard.putNumber("Temp2", ((temp2.getVoltage()*1000)-500)/10);
		SmartDashboard.putBoolean("gyroPID is Enabled?", gyroPIDController.isEnabled());
        SmartDashboard.putNumber("Gyropid output", gyroPIDController.get());
        SmartDashboard.putNumber("Gyro SetPoint", gyroPIDController.getSetpoint());
        SmartDashboard.putNumber("GyroERROR", gyroPIDController.getError());
        
        //SmartDashboard.putBoolean("armPID is Enabled?", armMotor.isEnabled());
        //SmartDashboard.putNumber("Arm Position", armMotor.getPosition());
        //SmartDashboard.putNumber("Top motor Speed", top.getSpeed());
        //SmartDashboard.putNumber("Bottom motor Speed", bottom.getSpeed());
        //SmartDashboard.putNumber("Arm SetPoint", armMotor.getSetpoint());
        //SmartDashboard.putNumber("ArmERROR", armMotor.getError());
        
        SmartDashboard.putNumber("leftJoy",controller1.getRawAxis(1));
        SmartDashboard.putNumber("rightJoy",controller2.getRawAxis(1));
        SmartDashboard.putNumber("leftDriveSpeed", motors.leftDriveMaster.get());
        SmartDashboard.putNumber("rightDriveSpeed", motors.rightDriveMaster.get());
        SmartDashboard.putNumber("leftDriveSetPoint",controller1.getRawAxis(1)*100);
        SmartDashboard.putNumber("rightDriveSetPoint",controller2.getRawAxis(1)*100);
        SmartDashboard.putNumber("Pressure", (250*(underPressure.getVoltage()/5)-25));
        SmartDashboard.putNumber("Temp1", ((temp1.getVoltage()*1000)-500)/10);
        SmartDashboard.putNumber("Temp2", ((temp2.getVoltage()*1000)-500)/10);
        SmartDashboard.putNumber("leftDriveSpeed", motors.leftDriveMaster.getSpeed());
        SmartDashboard.putNumber("rightDriveSpeed", motors.rightDriveMaster.getSpeed());
        SmartDashboard.putNumber("leftDriveCurrentPID", motors.leftDriveMaster.getOutputCurrent());
        SmartDashboard.putNumber("rightDriveCurrentPID", motors.rightDriveMaster.getOutputCurrent());
        SmartDashboard.putNumber("leftDriveVoltage", motors.leftDriveMaster.getOutputVoltage());
        SmartDashboard.putNumber("rightDriveVoltage", motors.rightDriveMaster.getOutputVoltage());
        SmartDashboard.putNumber("GyroAngle", gyro.getAngle());
        
        SmartDashboard.putNumber("Winch Position", motors.winch.getPosition());
        
        
        SmartDashboard.putNumber("Arm Setpoint", motors.armMotor.getSetpoint());
        SmartDashboard.putNumber("Arm Position", motors.armMotor.getPosition());
        SmartDashboard.putNumber("Arm Output Voltage", motors.armMotor.getOutputVoltage());
        SmartDashboard.putNumber("Arm Output Current", motors.armMotor.getOutputCurrent());
	}
}
