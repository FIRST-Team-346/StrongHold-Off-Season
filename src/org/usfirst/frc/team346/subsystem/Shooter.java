package org.usfirst.frc.team346.subsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//TODO: make sure these speeds are rights
public class Shooter {

    CANTalon ShooterTop;
    CANTalon ShooterBottom;
    Solenoid Gripper;
    Solenoid Trigger;
    double LoadSetpointTop;
    double LoadSetpointBottom;
    double LoadSetpointTrigger;
    double ShootSetpointTop;
    double ShootSetpointBottom;
    double ShootSetpointMaxErrorPercent;
    double portcullis;
    double ShootCloseTop;
    double ShootCloseBottom;
    
	double minSpeedToFire = 4000;
	
	private CANTalon m_shooterTop;
	private CANTalon m_shooterBottom;
	private CANTalon m_shooterTrigger;
	
	Gripper = new Solenoid(2,6);
	Trigger = new Solenoid(2,7);
	
    public Shooter (CANTalon a, CANTalon b, Solenoid g, Solenoid t)
    {
    	ShooterTop = a;
    	ShooterBottom = b;
    	Gripper = g;
    	Trigger = t;
    	LoadSetpointTop = 3600; //need to set these values later
        LoadSetpointBottom = 1000;
        LoadSetpointTrigger = -1000;
        ShootSetpointTop = -8000;
        ShootSetpointBottom = -100;
        ShootCloseTop = -4200;
        ShootCloseBottom = - 50;
        ShootSetpointMaxErrorPercent = .01;
        portcullis = 1800;
        
        this.m_shooterTop = new CANTalon(7);
		this.m_shooterBottom = new CANTalon(8);

		this.m_shooterTop.changeControlMode(TalonControlMode.Speed);
		this.m_shooterTop.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		this.m_shooterTop.configEncoderCodesPerRev(1024);
		this.m_shooterTop.setPID(10, 0, 0);

		
		this.m_shooterTop.changeControlMode(TalonControlMode.PercentVbus);
		
		this.m_shooterTop.changeControlMode(TalonControlMode.PercentVbus);
		
		this.m_shooterBottom.changeControlMode(TalonControlMode.Follower);
		this.m_shooterBottom.set(this.m_shooterTop.getDeviceID());
		this.m_shooterBottom.changeControlMode(TalonControlMode.PercentVbus);

		this.m_shooterTrigger.changeControlMode(TalonControlMode.Speed);
		this.m_shooterTrigger.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		this.m_shooterTrigger.configEncoderCodesPerRev(360);
		this.m_shooterTrigger.setPID(0, 0, 0);
    }
	
    public void setMotors(double topSpeed, double bottomSpeed, double triggerSpeed)
    {
//    	if(!ShooterTop.isControlEnabled()){
//    		ShooterTop.enableControl();
//    	}
    	ShooterTop.set(topSpeed);	
    	ShooterTop.enable();
        SmartDashboard.putNumber("ShootSetpointTop", topSpeed);
//    	ShooterBottom.set(-1*ShooterTop.getOutputVoltage());
    	
   		//Trigger.set(triggerSpeed);		
    }
    
    public void LoadBall(){
//    	setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed
		setMotors(LoadSetpointTop, LoadSetpointBottom, LoadSetpointTrigger); // Not implementing speed control
		if(Gripper.get())
		{
			Trigger.set(false);
			Gripper.set(false);
		}
    }
    
    public void Stop(){
//    	ShooterTop.disableControl();
    	setMotors(0,0,0);    	
//    	ShooterBottom.set(0);
    //	Trigger.set(0);

    }
    
    public void closeJaws(){
    
    	Gripper.set(true);
    	
    }
    public void openJaws(){
    	Gripper.set(false);
    }
  /*  public void GripBall (){
    		Gripper.set(true);
    		Trigger.set(false);
    }*/
    
    public void SpinUp(){
//    	setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed    	
    	setMotors(ShootSetpointTop,ShootSetpointBottom,0); // Not implementing speed control
    	Gripper.set(false);
    }
    public void ShootClose(){
//    	setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed
    	setMotors(ShootCloseTop,ShootCloseBottom,0); // Not implementing speed control
    	Gripper.set(false);
    }
  //  public boolean IsAbleToFire(){
    	/*if((ShooterTop.getSpeed()>=ShootSetpointTop - (ShootSetpointTop * ShootSetpointMaxErrorPercent)) &&
    			(ShooterBottom.getSpeed()>=ShootSetpointBottom - (ShootSetpointBottom * ShootSetpointMaxErrorPercent)))
    		return true;
    	return false; //figure this out later
    *///}
    
    public void Fire()
    {

    		if(Gripper.get())
    		{
    			Gripper.set(false);
    			Trigger.set(true);
    		}
    		
//    		setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed
    		
        	setMotors(ShootSetpointTop,ShootSetpointBottom,100); // Not implementing speed control
    }
    public void ejectBall()
    {
//    	setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed
    	
    	setMotors(ShootSetpointTop,ShootSetpointBottom,100);	// Not implementing speed control
    }
    
    public void AutoShootAndFire(){
    	SpinUp();
	if(CanFire())
	{
		Fire();
	}
    }
    public void trigger(){
    	Trigger.set(true);
    }
    public void triggerFalse(){
    	Trigger.set(false);
    }
    public void Portcullis(){
//    ShooterTop.set(portcullis);
//    ShooterBottom.set(0);
    	System.out.println("\nPORTCULLIS\n");
    }

	public boolean CanFire()
	{
		return ShooterTop.getSpeed() > minSpeedToFire;
	}
}
