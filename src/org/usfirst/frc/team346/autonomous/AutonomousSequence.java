package org.usfirst.frc.team346.autonomous;

/**
 * This is the interface for all autonomous sequences.
 * All autonomous sequences must implement this interface
 * so that they can be used in the Robot.java class.
 * 
 * @author Adam Morrissett
 *
 */
public interface AutonomousSequence {
	
	/**
	 * Initializes any time-based parameters.
	 * This is important because autonomous
	 * sequences are almost all time-based.
	 */
	public void init();
	
	/**
	 * Performs the autonomous sequence
	 */
	public void doSequence();
}
