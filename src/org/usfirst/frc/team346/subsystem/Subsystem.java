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
	
	/**
	 * This method should be called during the 
	 * autonomousPeriodic() and/or teleopPeriodic()
	 * methods. It is the main entry point for all
	 * subsystem logic.
	 */
	public void runPeriodic(Object... objects);
	
	/**
	 * This method should disable the subsystem, meaning
	 * all outputs should be zero. This method could be 
	 * used for any sort of safety driven disable.
	 */
	public void disable();
	
	/**
	 * This method should enable the subsystem, meaning
	 * all outputs are allowed to actually drive the 
	 * physical components. This method should be used
	 * right after the constructor.
	 */
	public void enable();
}
