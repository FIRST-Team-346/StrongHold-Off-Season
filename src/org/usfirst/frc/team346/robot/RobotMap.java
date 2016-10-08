package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.CANTalon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
    // Human interface device (HID) ports:
	public static final int LEFT_JOYSTICK_PORT = 0;
	public static final int RIGHT_JOIYSTICK_PORT = 1;
	public static final int BUTTON_BOARD_PORT = 2;
	
	// Motor controller ports:
	public static final int LEFT_DRIVE_MASTER_PORT = 1;
	public static final int LEFT_DRIVE_SLAVE_PORT = 2;
	
	public static final int RIGHT_DRIVE_MASTER_PORT = 3;
	public static final int RIGHT_DRIVE_SLAVE_PORT = 4;
	
	public static final int ARM_MOTOR_PORT = 5;
	
	public static final int TOP_SHOOTER_PORT = 7;
	public static final int BOTTOM_SHOOTER_PORT = 8;	
	
	public static final int WINCH_PORT = 6;
	
	/**
	 * TODO: Figure out what "Hook" and "HookSupply" mean
	 */
	
	// Pneumatic solenoid modules and ports:
	public static final int DRIVE_TRANSMISSION_MODULE = 1;
	public static final int DRIVE_TRANSMISSION_PORT_1 = 0;
	public static final int DRIVE_TRANSMISSION_PORT_2 = 1;
	
	public static final int GRIPPER_MODULE = 2;
	public static final int GRIPPER_PORT = 6;
	
	public static final int HOOK_MODULE = 1;
	public static final int HOOK_PORT_1 = 4;
	public static final int HOOK_PORT_2 = 5;
	
	public static final int HOOK_SUPPLY_MODULE = 1;
	public static final int HOOK_SUPPLY_PORT = 2;
	
	public static final int TRIGGER_MODULE = 2;
	public static final int TRIGGER_PORT = 7;	
	
	public static final int WINCH_LATCH_MODULE = 2;
	public static final int WINCH_LATCH_PORT = 4;	
	
	// Compressor port:
	public static final int COMPRESSOR_PORT = 2;
}
