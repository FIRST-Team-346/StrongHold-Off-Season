package org.usfirst.frc.team346.robot;

import java.util.HashMap;

public class LightManager extends Thread {
	
	Light[] lights;
	HashMap<String, Light> LightMap;
	
	public LightManager (Light...lights){

		this.lights = lights;
		LightMap = new HashMap<String, Light>();
		for(Light light: lights){
			LightMap.put(light.Name, light);
		}
		
	}
	
	public void TurnOn(String name){
		Light lightToChange = LightMap.get(name);
		if(lightToChange != null)
			lightToChange.CurrentState = Light.lightState.On;
	}
	
	public void TurnOff(String name){
		Light lightToChange = LightMap.get(name);
		if(lightToChange != null)
			lightToChange.CurrentState = Light.lightState.Off;
	}
	
	public void Flash(String name, double rate){
		Light lightToChange = LightMap.get(name);
		if(lightToChange != null){
			lightToChange.CurrentState = Light.lightState.Flashing;
			lightToChange.flashRate = rate;
		}
	}
	
	public boolean IsOn(String name){
		Light lightToChange = LightMap.get(name);
		if(lightToChange != null)
			if(lightToChange.CurrentState == Light.lightState.On)
				return true;
		return false;
	}

	public void run()
	{
		while(true)
		{
			long curTime = System.currentTimeMillis();
			for(Light light: lights){
//				System.out.println(light);
				switch(light.CurrentState)
				{
					case On:{
						light.TurnOn();
					} break;
					case Off:{
						light.TurnOff();
					} break;
					case Flashing:{
						if(curTime % (light.flashRate*2) > light.flashRate)
							light.TurnOn();
						else
							light.TurnOff();
					} break;
				}
			}
			
			//Dont hog the cpu!
			yield();
			try{sleep(1);}catch(Exception e){}
		}
	}
}
