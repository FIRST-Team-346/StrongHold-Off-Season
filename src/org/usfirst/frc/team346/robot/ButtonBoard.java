package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.Joystick;

public class ButtonBoard extends Joystick {
	private int travelPosition = 12;
	private int winchPush= 13; 
	private int takeInBall = 8;
	private int climb = 9;
	private int armReset = 11;
	private int shootClose = 7;
	private int ejectoSeatoCuz = 10;
	private int stopArm = 4;
	private int deployHook = 1;
	private int startHanging = 5;
	private int spinRollers = 6;
    private int trigger = 2;
	private int openJaws = 3;
	private int armHang = 14;
	private int lightOn = 15;
	
	public ButtonBoard(int port) {
		super(port);
	}
	
	@Deprecated
	public boolean getRawButton(int button) {
		return false;
	}
	

	public boolean getTravelPosition() {
		return super.getRawButton(this.travelPosition);
	}


	public boolean getWinchPush() {
		return super.getRawButton(this.winchPush);
	}

	public boolean getClimb(){
		return super.getRawButton(this.climb);
	}
	public boolean getArmReset(){
		return super.getRawButton(this.armReset);
	}
	public boolean getShootClose(){
		return super.getRawButton(this.shootClose);
	}
	public boolean getEjectBall(){
		return super.getRawButton(this.ejectoSeatoCuz);
	}
	public boolean getStopArm(){
		return super.getRawButton(this.stopArm);
	}
	public boolean getDeployHook(){
		return super.getRawButton(this.deployHook);
	}
	public boolean getStartHanging(){
		return super.getRawButton(this.startHanging);
	}
	public boolean getTakeIn(){
		return super.getRawButton(this.takeInBall);
	}
	public boolean getSpinrollers(){
		return super.getRawButton(this.spinRollers);
	}
	public boolean getTriggered(){
		return super.getRawButton(this.trigger);
	}
	public boolean getOpenJaws(){
		return super.getRawButton(this.openJaws);
	}
	public boolean getArmHang(){
		return super.getRawButton(this.armHang);
	}
	public boolean getLightOn(){
		return super.getRawButton(this.lightOn);
	}
}

