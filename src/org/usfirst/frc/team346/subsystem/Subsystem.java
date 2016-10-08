package org.usfirst.frc.team346.subsystem;

/**
 * This is the interface for all subsystems on the 
 * robot. This class defines the interface for other 
 * classes that implement it. You can think of an
 * interface as a contract that classes sign; each 
 * class that implements this has to have the following
 * methods.
 * 
 * @author Adam Morrissett
 *
 */
public interface Subsystem {
	public void init();
	public void runPeriodic();
	public void disable();
}
