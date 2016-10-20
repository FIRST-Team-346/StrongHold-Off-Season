package org.usfirst.frc.team346.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    // Human interface device (HID) ports:
	public static final int LEFT_JOYSTICK_PORT = 0;
	public static final int RIGHT_JOYSTICK_PORT = 1;
	public static final int BUTTON_BOARD_PORT = 2;
	
	// Motor controller ports:
	public static final int LEFT_DRIVE_MASTER_PORT = 3;
	public static final int LEFT_DRIVE_SLAVE_PORT = 4;
	
	public static final int RIGHT_DRIVE_MASTER_PORT = 1;
	public static final int RIGHT_DRIVE_SLAVE_PORT = 2;
	
	public static final int ARM_MOTOR_PORT = 5;
	
	public static final int SHOOTER_TOP_ROLLER_PORT = 7;
	public static final int SHOOTER_BOTTOM_ROLLER_PORT = 8;	
	
	public static final int WINCH_MOTOR_PORT = 6;
	
	/**
	 * TODO: Figure out what "Hook" and "HookSupply" mean
	 */
	
	// Pneumatic solenoid modules and ports:
	public static final int DRIVE_SOLENOID_MODULE = 1;
	public static final int DRIVE_SOLENOID_PORT_1 = 0;
	public static final int DRIVE_SOLENOID_PORT_2 = 1;
	
	public static final int SHOOTER_CLAW_GRIPPER_MODULE = 2;
	public static final int SHOOTER_CLAW_GRIPPER_PORT = 6;
	
	public static final int HOOK_MODULE = 1;
	public static final int HOOK_PORT_1 = 4;
	public static final int HOOK_PORT_2 = 5;
	
	public static final int HOOK_SUPPLY_MODULE = 1;
	public static final int HOOK_SUPPLY_PORT = 2;
	
	public static final int SHOOTER_TRIGGER_MODULE = 2;
	public static final int SHOOTER_TRIGGER_PORT = 7;	
	
	public static final int WINCH_ENGAGEMENT_LOCK_MODULE = 2;
	public static final int WINCH_ENGAGEMENT_LOCK_PORT = 4;	
	
	// Compressor port:
	public static final int COMPRESSOR_PORT = 2;
	
	// Shooter encoder parameters:
	public static final int SHOOTER_TOP_ROLLER_CODES_PER_REV = 1024;
	public static final int SHOOTER_BOTTOM_ROLLER_CODES_PER_REV = 360;
	
	// Shooter PID parameters:
	public static final double SHOOTER_TOP_ROLLER_PID_P = 0.22;
	public static final double SHOOTER_TOP_ROLLER_PID_I = 0;
	public static final double SHOOTER_TOP_ROLLER_PID_D = 0;
	
	public static final double SHOOTER_BOTTOM_ROLLER_PID_P = 0.2;
	public static final double SHOOTER_BOTTOM_ROLLER_PID_I = 0;
	public static final double SHOOTER_BOTTOM_ROLLER_PID_D = 0;
}
