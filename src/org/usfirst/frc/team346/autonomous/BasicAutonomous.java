package org.usfirst.frc.team346.autonomous;

import org.usfirst.frc.team346.subsystem.Drive;

/**
 * This is a basic autonomous sequence that tells the
 * robot to simply drive forward for 5 seconds.
 * 
 * @author Adam Morrissett
 *
 */
public class BasicAutonomous implements AutonomousSequence {

	private Drive m_driveSubsystem;
	
	private long m_startTime;
	
	/**
	 * Custom constructor for BasicAutonommous object.
	 * 
	 * @param _driveSubsystem the drive subsystem
	 */
	public BasicAutonomous(Drive _driveSubsystem) {
		this.m_driveSubsystem = _driveSubsystem;
	}
	
	/**
	 * Initialize all time-based parameters.
	 */
	@Override
	public void init() {
		this.m_startTime = System.currentTimeMillis();		
	}
	
	/**
	 * Performs the autonomous sequence
	 */
	@Override
	public void doSequence() {
		if (System.currentTimeMillis() - this.m_startTime < 5000) {
			this.m_driveSubsystem.setDrive(-0.3, -0.3);
		} else {
			this.m_driveSubsystem.setDrive(0, 0);
		}		
	}

}
