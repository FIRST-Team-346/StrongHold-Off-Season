package org.usfirst.frc.team346.subsystem;

import org.usfirst.frc.team346.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Shooter implements Subsystem { 
    
	/**
	 * Enumeration for shooter speeds.
	 * 
	 * @author Adam Morrissett
	 */
	public static enum ShooterSpeed {
		LOAD,
		SHOOT,
		CLOSE,
		TRIGGER,
		PORTCULLIS;
	}
		
	/**
	 * Internal enumeration for shooter speeds. 
	 * This is a private internal enumeration for the
	 * actual motor speeds. This is used in
	 * conjunction with the ShooterSpeed enumeration
	 * to abstract some details away. 
	 * 
	 * @author Scott C.
	 */
	private enum internalShooterSpeed {
		LOAD_TOP(3600),
		LOAD_BOTTOM(1000),
		
		SHOOT_TOP(-8000),
		SHOOT_BOTTOM(-100),
		
		CLOSE_TOP(-4200),
		CLOSE_BOTTOM(-50),
		
		LOAD_TRIGGER(-1000),						
		PORTCULLIS(1800);
		
		private int m_speed;	// Speed of the rollers
		
		/**
		 * Gets the speed of the enumeration.
		 * 
		 * @return (int) speed of the enumeration
		 */
		public int getSpeed() {
			return this.m_speed;
		}
		
		/**
		 * Custom constructor for ShooterSpeed object.
		 * 
		 * @param _speed speed of the roller
		 */
		private internalShooterSpeed(int _speed) {
			this.m_speed = _speed;
		}
	}		
	
	/**
	 * Enumeration for jaw position.
	 * 
	 * @author Adam Morrissett
	 */
	public static enum JawPosition {
		OPEN,
		CLOSE;
	}
	
	/**
	 * Enumeration for trigger position.
	 * 
	 * @author Adam Morrissett
	 *
	 */
	public static enum TriggerPosition {
		EXTEND,
		RETRACT;
	}
	
	// Motor controller declarations
	private CANTalon m_topRoller;
	private CANTalon m_bottomRoller;
	
	// Solenoid declarations
	private Solenoid m_jaw;
	private Solenoid m_trigger;
	
	/**
	 * Default constructor for Shooter object.
	 */
    public Shooter() {
    	this.m_topRoller = new CANTalon(RobotMap.SHOOTER_TOP_ROLLER_PORT);								// Init top roller
    	this.m_topRoller.changeControlMode(TalonControlMode.Speed);										// Sets the top roller to speed mode
    	this.m_topRoller.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);						// Sets feedback device to Quad Encoder
    	this.m_topRoller.configEncoderCodesPerRev(RobotMap.SHOOTER_TOP_ROLLER_CODES_PER_REV);			// Sets codes per revolution to 1024
    	this.m_topRoller.setPID(RobotMap.SHOOTER_TOP_ROLLER_PID_P, 										// Sets the PID parameters
    			RobotMap.SHOOTER_TOP_ROLLER_PID_I, 
    			RobotMap.SHOOTER_TOP_ROLLER_PID_D);    	
    	
    	this.m_bottomRoller = new CANTalon(RobotMap.SHOOTER_BOTTOM_ROLLER_PORT);						// Init bottom roller					
    	this.m_bottomRoller.changeControlMode(TalonControlMode.Speed);									// Sets the bottom roller to speed mode
    	this.m_bottomRoller.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);						// Sets feedback device to Quad Encoder
    	this.m_bottomRoller.configEncoderCodesPerRev(RobotMap.SHOOTER_BOTTOM_ROLLER_CODES_PER_REV);		// Sets codes per revolution to 360						
    	this.m_bottomRoller.setPID(RobotMap.SHOOTER_BOTTOM_ROLLER_PID_P, 								// Sets the PID parameters
    			RobotMap.SHOOTER_BOTTOM_ROLLER_PID_I, 
    			RobotMap.SHOOTER_BOTTOM_ROLLER_PID_D);
    	
    	this.m_jaw = new Solenoid(RobotMap.SHOOTER_CLAW_GRIPPER_MODULE,
    			RobotMap.SHOOTER_CLAW_GRIPPER_PORT);													// Init claw solenoid
    	this.m_trigger = new Solenoid(RobotMap.SHOOTER_TRIGGER_MODULE,
    			RobotMap.SHOOTER_TRIGGER_PORT);															// Init trigger to start shot        	           
    }
	
    /**
	 * This method is the main method behind all of the
	 * shooter-based functions. It should be called during
	 * autonomous and/or teleoperation periodic methods.
	 */
	@Override
	public void runPeriodic() {
	
	}

	/**
	 * This method disables the subsystem. This method 
	 * could be used for any sort of safety driven disable.
	 */
	@Override
	public void disable() {
		this.m_topRoller.disable();
		this.m_bottomRoller.disable();		
	}

	/**
	 * This method enables the subsystem. 
	 * This allows the subsystem to actually 
	 * be driven.
	 */
	@Override
	public void enable() {
		this.m_bottomRoller.enable();
		this.m_topRoller.enable();		
	}
    
	/**
	 * Sets the shooter jaw open or closed.
	 * 
	 * @param _jawPosition target position of the jaw
	 */
	public void setJawPosition(JawPosition _jawPosition) {
		switch(_jawPosition) {
			case OPEN : {
				this.m_jaw.set(false);
			}; break;
			
			case CLOSE : {
				this.m_jaw.set(true);
			}; break;
		}
	}
	
	/**
	 * Get the position of the jaw.
	 * 
	 * @return the position of the jaw
	 */
	public JawPosition getJawPosition() {
		if (this.m_jaw.get()) {
			return JawPosition.CLOSE;
		} else {
			return JawPosition.OPEN;
		}
	}
	
	/**
	 * sets the trigger position.
	 * 
	 * @param _triggerPosition position to set the trigger
	 */
	public void setTriggerPosition(TriggerPosition _triggerPosition) { 
		switch(_triggerPosition) {
			case EXTEND : {
				this.m_trigger.set(true);
			}; break;
			
			case RETRACT : {
				this.m_trigger.set(false);
			}; break;
		}
	}
	
	/**
	 * Gets the position of the trigger.
	 * 
	 * @return position of the trigger
	 */
	public TriggerPosition getTriggerPosition() {
		if (this.m_trigger.get()) {
			return TriggerPosition.EXTEND;
		} else {
			return TriggerPosition.RETRACT;
		}
	}
	
	/**
	 * Sets the shooter speed to one of the preset 
	 * speeds in the enumeration.
	 * 
	 * @param _speed the speed of the shooter
	 *//*
	public void setShooterSpeed(ShooterSpeed _speed) {		
		switch(_speed) {
			case LOAD : {
				this.m_topRoller.set(internalShooterSpeed.LOAD_TOP.getSpeed());
				this.m_bottomRoller.set(internalShooterSpeed.LOAD_BOTTOM.getSpeed());
				
				this.m_shooterTrigger.set(false);
				this.m_clawGripper.set(false);
			}; break;
				
			case SHOOT : {
				this.m_topRoller.set(internalShooterSpeed.SHOOT_TOP.getSpeed());
				this.m_bottomRoller.set(internalShooterSpeed.SHOOT_BOTTOM.getSpeed());
			}; break;
			
			case CLOSE : {
				this.m_topRoller.set(internalShooterSpeed.CLOSE_TOP.getSpeed());
				this.m_bottomRoller.set(internalShooterSpeed.CLOSE_BOTTOM.getSpeed());
			}; break;
			
			case PORTCULLIS : {
				this.m_topRoller.set(internalShooterSpeed.PORTCULLIS.getSpeed());
				this.m_bottomRoller.set(internalShooterSpeed.PORTCULLIS.getSpeed());
			}; break;	
			case TRIGGER : {
				this.m_topRoller.set(internalShooterSpeed.LOAD_TRIGGER.getSpeed());
				this.m_bottomRoller.set(internalShooterSpeed.LOAD_TRIGGER.getSpeed());
			}; break;
			
			default : {
				this.m_topRoller.set(0);
				this.m_bottomRoller.set(0);
				
				this.m_shooterTrigger.set(false);
				this.m_clawGripper.set(false);
			}; break;
		}		
	}    
    
    public void Stop(){
//    	ShooterTop.disableControl();
    	setMotors(0,0,0);    	
//    	ShooterBottom.set(0);
    //	shooterTrigger.set(0);

    }
    
    public void closeJaws(){
    
    	m_clawGripper.set(true);
    	
    }
    public void openJaws(){
    	m_clawGripper.set(false);
    }
    public void GripBall (){
    		shooterGripper.set(true);
    		shooterTrigger.set(false);
    }
    
    public void SpinUp(){
//    	setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed
    	setMotors(internalShooterSpeed.SHOOT_TOP, internalShooterSpeed.SHOOT_BOTTOM,0); // Not implementing speed control
    	m_clawGripper.set(false);
    }
    public void ShootClose(){
//    	setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed
    	setMotors(internalShooterSpeed.CLOSE_TOP,internalShooterSpeed.CLOSE_TOP,0); // Not implementing speed control
    	m_clawGripper.set(false);
    }
  //  public boolean IsAbleToFire(){
    	if((ShooterTop.getSpeed()>=ShootSetpointTop - (ShootSetpointTop * ShootSetpointMaxErrorPercent)) &&
    			(ShooterBottom.getSpeed()>=ShootSetpointBottom - (ShootSetpointBottom * ShootSetpointMaxErrorPercent)))
    		return true;
    	return false; //figure this out later
    //}
    
    public void Fire()
    {
    		if(m_clawGripper.get())
    		{
    			m_clawGripper.set(false);
    			m_shooterTrigger.set(true);
    		}
    		
//    		setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed
    		
        	setMotors(internalShooterSpeed.SHOOT_TOP, internalShooterSpeed.SHOOT_BOTTOM, 100); // Not implementing speed control
    }
    public void ejectBall()
    {
//    	setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed
    	
    	setMotors(m_shootSetpointTop,m_shootSetpointBottom,100);	// Not implementing speed control
    }
    
    public void AutoShootAndFire(){
    	SpinUp();
	if(CanFire())
	{
		Fire();
	}
	
    }
    public void shooterTrigger(){
    	m_shooterTrigger.set(true);
    }
    public void shooterTriggerFalse(){
    	m_shooterTrigger.set(false);
    }
    public void Portcullis(){
//    ShooterTop.set(portcullis);
//    ShooterBottom.set(0);
    	System.out.println("\nPORTCULLIS\n");
    }

	public boolean CanFire()
	{
		return m_topRoller.getSpeed() > minSpeedToFire;
	}
*/
}
