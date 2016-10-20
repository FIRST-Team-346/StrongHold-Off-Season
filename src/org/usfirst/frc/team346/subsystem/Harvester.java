package org.usfirst.frc.team346.subsystem;

import org.usfirst.frc.team346.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

public class Harvester implements Subsystem {
	
	/**
	 * Enumeration for harvester position.
	 * The harvester will either be extended or
	 * retracted depending on the enumeration.
	 * 
	 * @author Adam Morrissett
	 *
	 */
	public static enum HarvesterPosition {
		DEPLOY,
		RETRACT;
	}
	
	/**
	 * Enumeration for harvester roller.
	 * The harvester roller will be controlled
	 * by this enumeration. 
	 * 
	 * @author Adam Morrissett
	 *
	 */
	public static enum HarvesterRollerState {
		STOP,
		INTAKE,
		EJECT;
	}
	
	private Talon m_rollerMotor;
	private Solenoid m_extender;
	
	/**
	 * Default constructor for Harvester object.
	 */
	public Harvester() {
		this.m_rollerMotor = new Talon(RobotMap.HARVESTER_MOTOR_PORT);
		this.m_extender = new Solenoid(RobotMap.HARVESTER_EXTENDER_MODULE,
				RobotMap.HARVESTER_EXTENDER_PORT);
		this.enable();
	}
	
	/**
	 * This method should be called during the 
	 * autonomousPeriodic() and/or teleopPeriodic()
	 * methods. It is the main entry point for all
	 * subsystem logic.
	 */
	@Override
	public void runPeriodic(Object... objects) {
	}

	/**
	 * This method should disable the subsystem, meaning
	 * all outputs should be zero. This method could be 
	 * used for any sort of safety driven disable.
	 */
	@Override
	public void disable() {		
		this.m_extender.set(false);		
	}

	/**
	 * This method should enable the subsystem, meaning
	 * all outputs are allowed to actually drive the 
	 * physical components. This method should be used
	 * right after the constructor.
	 */
	@Override
	public void enable() {		
	}
	
	/**
	 * Sets the harvester position.
	 * Currently, the only two positions are extended and
	 * retracted.
	 * 
	 * @param _position desired position of harvester
	 */
	public void setHarvesterPosition(HarvesterPosition _position) {
		switch(_position) {
			case DEPLOY : {
				this.m_extender.set(false);				
			}; break;
			
			case RETRACT : {
				this.m_extender.set(true);
			}; break;
		}
	}
	
	/**
	 * Toggle the harvester position.
	 */
	public void toggleHarvesterPosition() {		
		boolean tmp = this.m_extender.get();
		this.m_extender.set(!tmp);
	}
	
	/**
	 * Sets the harvester roller motors.
	 * This method controls the rotation of the 
	 * harvester roller motors.
	 * 
	 * @param _state desired state of the roller motors
	 */
	public void setHarvesterRoller(HarvesterRollerState _state) {
		switch(_state) {
			case STOP : {
				this.m_rollerMotor.set(0);
			}; break;
			
			case INTAKE : {
				this.m_rollerMotor.set(-1);
			}; break;
			
			case EJECT : {
				this.m_rollerMotor.set(1);
			}; break;
		}
	}	

}
