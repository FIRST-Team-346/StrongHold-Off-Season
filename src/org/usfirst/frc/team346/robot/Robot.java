
package org.usfirst.frc.team346.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team346.autonomous.SimpleStrongholdAutonomous;
import org.usfirst.frc.team346.autonomous.StrongholdAutonomous;
import org.usfirst.frc.team346.camera.PiCamera;
import org.usfirst.frc.team346.subsystem.Arm;
import org.usfirst.frc.team346.subsystem.Drive;
import org.usfirst.frc.team346.subsystem.Shooter;
import org.usfirst.frc.team346.subsystem.Winch;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;

import edu.wpi.first.wpilibj.CameraServer;
import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	StrongholdMotorPreferences motors;
	StrongholdSolenoidPreferences solenoids;
	Joystick armcontroller;
	Joystick controller1;
	Joystick controller2;
	ButtonBoard buttonBoard;
	
	
	
	
	PIDController gyroPIDController;
	Preferences Prefs;
	Command autonomousCommand;
	double RobotSpeedMultiplier;
	long hookDuration;
	long startTime;
	AnalogInput underPressure;
	AnalogGyro gyro;
	double dead;
	double previousLeft;
	double previousRight;
	dummyPIDOutput Gpid;
	Drive RobotDrive;
	double heading;
	boolean headingSet;
	Arm robotArm;
	Shooter robotShooter;
	Winch robotWinch;
	boolean armControl;
	boolean lastToggleGear;
	
    public void robotInit() {
		motors = new StrongholdMotorPreferences(); // Init all motors
		solenoids = new StrongholdSolenoidPreferences(); // Init all solenoids
		robotArm = new Arm(motors.armMotor);
		robotShooter = new Shooter(motors.shooterTop, motors.shooterBottom, solenoids.Gripper, solenoids.Trigger);
	    robotWinch = new Winch(solenoids.WinchGear, motors.winch, solenoids.HookSupply, solenoids.Hook);	
		lightManager = new LightManager(new Light(solenoids.green, "green"), new Light(solenoids.red,"red"), new Light(solenoids.blue,"blue"));
    	lightManager.start();
        
    	
        controller1 = new Joystick(1);
        controller2 = new Joystick(2); 
        buttonBoard = new ButtonBoard(3);
//        armcontroller = new Joystick(4);
        Prefs = Preferences.getInstance();
        gyro = new AnalogGyro(0);
        hookDuration = 0;
        
        underPressure = new AnalogInput(1);
        gyro.calibrate();
        //rangeSensor = new Ultrasonic(new DigitalOutput(4), new DigitalInput(3));
        //rangeSensor.setAutomaticMode(true);
        previousLeft =0;
        previousRight = 0;
        Gpid = new dummyPIDOutput();
        gyroPIDController = new PIDController(Prefs.getDouble("GyroP", 0), Prefs.getDouble("GyroI", 0), Prefs.getDouble("GyroD", 0),gyro,Gpid);
        
        RobotDrive = new Drive(motors.leftDriveMaster, motors.rightDriveMaster, solenoids.SpeedGear, gyroPIDController);

//        auto = new SimpleStrongholdAutonomous(RobotDrive);

       
        
        armControl = false;
        light = new Relay(1);               
    }

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
 /*  	
        auto = (StrongholdAutonomous) chooser.getSelected();
        gyro.initGyro();

        gyroPIDController.setPID(Prefs.getDouble("GyroP", 0), Prefs.getDouble("GyroI", 0), Prefs.getDouble("GyroD", 0));
        if (autonomousCommand != null) autonomousCommand.start();
        gyroPIDController.setSetpoint(gyro.getAngle());
        //gyroPID.reset();
        time1.reset();
        time1.start();
//*/     
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
//        auto.DoAutonomousLogic(time1.get()); 
        //SmartDashboard.putNumber("Timer1", time1.get());
    	
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
    	// tile open starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
    	if (autonomousCommand != null) autonomousCommand.cancel();
    	        dead = Prefs.getDouble("dead", 0);
        gyroPIDController.setPID(Prefs.getDouble("GyroP", 0), Prefs.getDouble("GyroI", 0), Prefs.getDouble("GyroD", 0));

        armControl = false;
        startTime = System.currentTimeMillis();
        motors.winch.reset();
//        motors.shooterTop.reset();
        motors.shooterTop.enable();
        motors.setPID();
        motors.winch.enable();
        robotWinch.resetHook();
        System.out.println("Starting teleop periodic");
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
    	
    	RobotSpeedMultiplier = Prefs.getDouble("RobotSpeedMuliplier",100);
        Scheduler.getInstance().run();
        processDriveLogic();
        processArmInput();
        processWinchInput();
        processShootingInput();
        solenoids.compressor.start();
    }
    
    public void processArmInput(){    	
         if(buttonBoard.getArmReset()) {
        	lightManager.Flash("Green",1500);
        	 robotArm.Reset();
    		System.out.println("ArmGateDown");
        } else if(buttonBoard.getClimb()) {
    		robotArm.Lift();
        } else if(buttonBoard.getTravelPosition()){
        	robotArm.Travel();
        }else if(buttonBoard.getStopArm())
        	{
        	robotArm.Stop();
        } else if(buttonBoard.getArmHang()){
        	robotArm.hang();
    	}
/*         
        else if(armcontroller.getRawButton(1)) {
    		System.out.println("ArmControl");
    		double newPoint = motors.armMotor.getSetpoint()+(armcontroller.getY()*5);
    		if(newPoint>robotArm.PositionLift)
    			newPoint=robotArm.PositionLift;
    		if(newPoint<robotArm.PositionLoad)
    			newPoint=robotArm.PositionLoad;
    		robotArm.setArmPosition(newPoint);
    		}
//*/    		
        } 
    
    
    public void processShootingInput(){
		if(buttonBoard.getLightOn()){
			//System.out.println("Light is On");
			//System.out.println(light.get());
			light.set(Relay.Value.kForward);
		}
    	if(buttonBoard.getOpenJaws()){
			robotShooter.openJaws();
		}
		if(!buttonBoard.getTakeIn() && !buttonBoard.getSpinrollers() && !buttonBoard.getOpenJaws() && !buttonBoard.getShootClose() && !buttonBoard.getDeployHook() && !buttonBoard.getStartHanging() && !buttonBoard.getEjectBall()){
			robotShooter.closeJaws();
		}
		
    	if(buttonBoard.getEjectBall())
		{
			//System.out.println("Shooting");
			lightManager.Flash("red", 1500);
			robotArm.Shoot();
			robotShooter.openJaws();
			light.set(Relay.Value.kForward);
		}
    	if(!buttonBoard.getEjectBall() && !buttonBoard.getSpinrollers() && !buttonBoard.getLightOn()){
    		light.set(Relay.Value.kOff);
    	}
    	if(buttonBoard.getTriggered()){
    		robotShooter.trigger();
    	} 
    	if(!buttonBoard.getTriggered()){
    		robotShooter.triggerFalse();
    	}
		if(buttonBoard.getTakeIn())
		{
			//System.out.println("Taking in");
			lightManager.Flash("blue", 1500);
			robotArm.LoadBall();
//			robotShooter.LoadBall();
			motors.shooterTop.set(1);
			robotShooter.openJaws();
		}
		if(!buttonBoard.getEjectBall() && !buttonBoard.getTakeIn() && !buttonBoard.getSpinrollers() && !buttonBoard.getShootClose()){
			//System.out.println("Roller Stop");
//			robotShooter.Stop();
			motors.shooterTop.set(0);
			//robotShooter.GripBall();
		}
		if(buttonBoard.getSpinrollers()){
			//System.out.println("Spinning Rollers");
//			robotShooter.SpinUp();
			motors.shooterTop.enableControl();
			motors.shooterBottom.enableControl();
			motors.shooterTop.set(1);
			System.out.println("Top shooter ID: " + motors.shooterTop.getDeviceID());
			System.out.println("Top shooter enabled: " + motors.shooterTop.isEnabled());
			System.out.println("Top shooter control enabled: " + motors.shooterTop.isControlEnabled());
			System.out.println("Top shooter mode: " + motors.shooterTop.getControlMode());
			System.out.println("Top shooter vout: " + motors.shooterTop.getOutputVoltage());
			System.out.println("Top shooter setpoint: " + motors.shooterTop.getSetpoint());
			System.out.println("Bottom shooter ID: " + motors.shooterBottom.getDeviceID());
			System.out.println("Bottom shooter enabled: " + motors.shooterBottom.isEnabled());
			System.out.println("Bottom shooter control enabled: " + motors.shooterBottom.isControlEnabled());
			System.out.println("Bottom shooter mode: " + motors.shooterBottom.getControlMode());
			System.out.println("Bottom shooter vout: " + motors.shooterBottom.getOutputVoltage());
			System.out.println("Bottom shooter setpoint: " + motors.shooterBottom.getSetpoint());
			robotShooter.openJaws();
			light.set(Relay.Value.kForward);
		}
		if(buttonBoard.getShootClose()) {
//        	robotShooter.ShootClose();
			motors.shooterTop.set(1);
        	robotShooter.openJaws();
		}
    }
    
    public void processWinchInput()
    {
		if(buttonBoard.getDeployHook()) //&& (startTime-hookDuration)>=hookDuration)
		{
			//System.out.println("Deploying Hook");
			robotWinch.DeployHook();
			robotShooter.openJaws();
			}
		if(buttonBoard.getStartHanging())
		{
			//System.out.println("Winch");
			robotWinch.startHanging();
			robotWinch.retractHook();
			robotShooter.openJaws();
		}
		if(!buttonBoard.getStartHanging()&&!buttonBoard.getWinchPush()){
			robotWinch.stopTurning();
		}
		if(!buttonBoard.getDeployHook() && !buttonBoard.getStartHanging())
		{
			robotWinch.normalHook();
		}
		if(buttonBoard.getWinchPush())
		{
			robotWinch.reverseWinch();
			robotShooter.openJaws();
		}
    }
    
    public void processDriveLogic(){
    
    	if(controller1.getRawButton(1) && !lastToggleGear)
        {
        	RobotDrive.toggleGear();
        }
        
    	lastToggleGear = controller1.getRawButton(1);
    	
        if(controller2.getRawButton(1))
        {
        	//System.out.println("Gyro Drive");
        	if(!headingSet)
        	{
        		headingSet = true;
        		heading = gyro.getAngle();
        	}
        	RobotDrive.DriveWithHeadingHold(controller1.getY(), -controller2.getY(), heading);
        }
        else
        {
        	headingSet = false;
        	RobotDrive.drive(controller1.getY(), -controller2.getY());
        }
        if(controller2.getRawButton(7))
        {
        	gyro.calibrate();
        }
    	//RobotDrive.drive(controller1.getY(), -1*controller2.getY());
    }
}
