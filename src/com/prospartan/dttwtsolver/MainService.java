package com.prospartan.dttwtsolver;

import com.prospartan.dttwtsolver.DrawView;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Switch;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

public class MainService extends Service
{
  private WindowManager winManager;
  private DrawView drawView;
  
  @Override
  public IBinder onBind(Intent intent)
  {
    return null;
  }
  
  @Override
  public void onCreate()
  {
		super.onCreate();
    
		winManager = (WindowManager)getSystemService(WINDOW_SERVICE);
		switchProgramOn = new Switch(this);
		switchGameOn = new Switch(this);
		drawView = new DrawView(this);
		
		switchProgramOn.setChecked(false);
		switchGameOn.setChecked(false);

		final WindowManager.LayoutParams paramsSwitch = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.TYPE_PHONE,
			WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
			PixelFormat.TRANSLUCENT);
		final WindowManager.LayoutParams paramsSwitchRunGame = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.WRAP_CONTENT, 
			WindowManager.LayoutParams.WRAP_CONTENT, 
			WindowManager.LayoutParams.TYPE_PHONE, 
			indowManager.LayoutParams.FLAG_NOT_FOCUSABLE, 
			ixelFormat.TRANSLUCENT);
		final WindowManager.LayoutParams paramsDraw = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.WRAP_CONTENT, 
			WindowManager.LayoutParams.WRAP_CONTENT, 
			WindowManager.LayoutParams.TYPE_PHONE, 
			WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, 
			PixelFormat.TRANSLUCENT);
		
		paramsSwitch.gravity = Gravity.TOP | Gravity.LEFT;
		paramsImage.x=0;
		paramsImage.y=200;
    
		paramsSwitchRunGame.gravity = Gravity.TOP | Gravity.Right;
		paramsText.x=0;
		paramsText.y=200;
    
		paramsDraw.gravity = Gravity.TOP | Gravity.LEFT;
		paramsDraw.x=0;
		paramsDraw.y=0;
    
		winManager.addView(switchProgramOn, paramsSwitch);
		
		try{
			switchProgramOn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(CompoundButton compBtn, boolean isChecked) {
					if(switchProgramOn.isChecked()){
						winManager.addView(switchGameOn, paramsSwitchRunGame);
						winManager.addView(drawView, paramsDraw);
						try{
							switchGameOn.setOnCheckedChangeListener(new OnCheckedChangeListener() {
								public void onCheckedChanged(CompoundButton compBtn, boolean isChecked)
								{
									if(switchGameOn.isChecked())
									{
										Toast.makeText(getApplicationContext(), "Switch is checked", Toast.LENGTH_LONG).show();
									} else {
										Toast.makeText(getApplicationContext(), "Switch is unchecked", Toast.LENGTH_LONG).show();
									}
								}
							});
						} catch (Exception e) {
							//empty
						}
					} else {
						winManager.removeView(switchGameOn);
						winManager.removeView(drawView);
						switchGameOn.setChecked(false);
					}
				}
			});
		} catch (Exception e){
			//empty
		}
	}
  
  @Override
	public void onDestroy(){
		if(switchProgramOn.isChecked())
		{
			winManager.removeView(switchGameOn);
			winManager.removeView(drawView);
			winManager.removeView(switchProgramOn);
		} else {
			winManager.removeView(switchProgramOn);
		}
		super.onDestroy();
	}
}
