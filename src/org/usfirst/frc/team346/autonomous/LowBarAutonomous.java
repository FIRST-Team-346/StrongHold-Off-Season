package org.usfirst.frc.team346.autonomous;

import org.usfirst.frc.team346.subsystem.Arm;
import org.usfirst.frc.team346.subsystem.Drive;
import org.usfirst.frc.team346.subsystem.Harvester;
import org.usfirst.frc.team346.subsystem.Arm.ArmPosition;
import org.usfirst.frc.team346.subsystem.Harvester.HarvesterPosition;

public class LowBarAutonomous implements AutonomousSequence {
	
	private Drive m_driveSubsystem;
	private Arm m_armSubsystem;
	private Harvester m_harvesterSubsystem;
	
	private long m_startTime;
	
	public LowBarAutonomous(Drive _driveSubsystem, 
			Arm _armSubsystem, Harvester _harvesterSubsystem) {
		this.m_driveSubsystem = _driveSubsystem;
		this.m_armSubsystem = _armSubsystem;
		this.m_harvesterSubsystem = _harvesterSubsystem;
	}

	/**
	 * Initialize all time-based parameters.
	 */
	@Override
	public void init() {
		this.m_startTime = System.currentTimeMillis();
	}

	/**
	 * Performs autonomous sequence
	 */
	@Override
	public void doSequence() {
		this.m_harvesterSubsystem.setHarvesterPosition(HarvesterPosition.DEPLOY);
		if (System.currentTimeMillis() - this.m_startTime >= 500) {
			this.m_armSubsystem.setArmPosition(ArmPosition.TRAVEL);
		}		
		
		if ((System.currentTimeMillis() - this.m_startTime >= 4000)
				&& (System.currentTimeMillis() - this.m_startTime < 9000)) {
			this.m_driveSubsystem.setDrive(-0.5, -0.5);
		} else {
			this.m_driveSubsystem.setDrive(0, 0);
		}				
	}
	
}
