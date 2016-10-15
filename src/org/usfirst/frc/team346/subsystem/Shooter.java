package org.usfirst.frc.team346.subsystem;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//TODO: make sure these speeds are rights
public class Shooter {

    double m_loadSetpointTop;
    double m_loadSetpointBottom;
    double m_loadSetpointshooterTrigger;
    double m_shootSetpointTop;
    double m_shootSetpointBottom;
    double m_shootSetpointMaxErrorPercent;
    double m_portcullis;
    double m_shootCloseTop;
    double m_shootCloseBottom;
    
	double minSpeedToFire = 4000;
	
	private CANTalon m_shooterTop;
	private CANTalon m_shooterBottom;
	private Solenoid m_shooterGripper = new Solenoid(2,6);
	private Solenoid m_shooterTrigger = new Solenoid(2,7);
	
    public Shooter (CANTalon a, CANTalon b, Solenoid g, Solenoid t)
    {
    	m_shooterTop = a;
    	m_shooterBottom = b;
    	m_shooterGripper = g;
    	m_shooterTrigger = t;
    	m_loadSetpointTop = 3600; //need to set these values later
    	m_loadSetpointBottom = 1000;
    	m_loadSetpointshooterTrigger = -1000;
    	m_shootSetpointTop = -8000;
    	m_shootSetpointBottom = -100;
    	m_shootCloseTop = -4200;
    	m_shootCloseBottom = - 50;
    	m_shootSetpointMaxErrorPercent = .01;
    	m_portcullis = 1800;
        
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

		this.m_shooterBottom.changeControlMode(TalonControlMode.Speed);
		this.m_shooterBottom.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		this.m_shooterBottom.configEncoderCodesPerRev(360);
		this.m_shooterBottom.setPID(0, 0, 0);
    }
	
    public void setMotors(double topSpeed, double bottomSpeed, double shooterTriggerSpeed)
    {
//    	if(!ShooterTop.isControlEnabled()){
//    		ShooterTop.enableControl();
//    	}
    	m_shooterTop.set(topSpeed);	
    	m_shooterTop.enable();
        SmartDashboard.putNumber("ShootSetpointTop", topSpeed);
//    	ShooterBottom.set(-1*ShooterTop.getOutputVoltage());
    	
   		//shooterTrigger.set(shooterTriggerSpeed);		
    }
    
    public void LoadBall(){
//    	setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed
		setMotors(m_loadSetpointTop, m_loadSetpointBottom, m_loadSetpointshooterTrigger); // Not implementing speed control
		if(m_shooterGripper.get())
		{
			m_shooterTrigger.set(false);
			m_shooterGripper.set(false);
		}
    }
    
    public void Stop(){
//    	ShooterTop.disableControl();
    	setMotors(0,0,0);    	
//    	ShooterBottom.set(0);
    //	shooterTrigger.set(0);

    }
    
    public void closeJaws(){
    
    	m_shooterGripper.set(true);
    	
    }
    public void openJaws(){
    	m_shooterGripper.set(false);
    }
  /*  public void GripBall (){
    		shooterGripper.set(true);
    		shooterTrigger.set(false);
    }*/
    
    public void SpinUp(){
//    	setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed    	
    	setMotors(m_shootSetpointTop,m_shootSetpointBottom,0); // Not implementing speed control
    	m_shooterGripper.set(false);
    }
    public void ShootClose(){
//    	setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed
    	setMotors(m_shootCloseTop,m_shootCloseBottom,0); // Not implementing speed control
    	m_shooterGripper.set(false);
    }
  //  public boolean IsAbleToFire(){
    	/*if((ShooterTop.getSpeed()>=ShootSetpointTop - (ShootSetpointTop * ShootSetpointMaxErrorPercent)) &&
    			(ShooterBottom.getSpeed()>=ShootSetpointBottom - (ShootSetpointBottom * ShootSetpointMaxErrorPercent)))
    		return true;
    	return false; //figure this out later
    *///}
    
    public void Fire()
    {

    		if(m_shooterGripper.get())
    		{
    			m_shooterGripper.set(false);
    			m_shooterTrigger.set(true);
    		}
    		
//    		setMotors(1.0,1.0, 0.0);	// Set shoot motors to full speed
    		
        	setMotors(m_shootSetpointTop,m_shootSetpointBottom,100); // Not implementing speed control
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
		return m_shooterTop.getSpeed() > minSpeedToFire;
	}
}
