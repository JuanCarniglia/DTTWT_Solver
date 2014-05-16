package com.prospartan.dttwtsolver;

import com.prospartan.dttwtsolver.DrawView;
import com.prospartan.dttwtsolver.R;
import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class MainService extends Service
{
  private WindowManager winManager;
  private ImageView imgView;
  private TextView txtView;
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
		imgView = new ImageView(this);
		txtView = new TextView(this);
		drawView = new DrawView(this);
    
		txtView.setText("Off");
		imgView.setImageResource(R.drawable.floating_icon);

		final WindowManager.LayoutParams paramsImage = new WindowManager.LayoutParams(
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.WRAP_CONTENT,
			WindowManager.LayoutParams.TYPE_PHONE,
			WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
			PixelFormat.TRANSLUCENT);
		final WindowManager.LayoutParams paramsText = new WindowManager.LayoutParams(
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
		
		paramsImage.gravity = Gravity.TOP | Gravity.LEFT;
		paramsImage.x=0;
		paramsImage.y=100;
    
		paramsText.gravity = Gravity.TOP | Gravity.LEFT;
		paramsText.x=100;
		paramsText.y=100;
    
		paramsDraw.gravity = Gravity.TOP | Gravity.LEFT;
		paramsDraw.x=0;
		paramsDraw.y=0;
    
		winManager.addView(imgView, paramsImage);
		winManager.addView(txtView, paramsText);
    
		try{
			imgView.setOnTouchListener(new View.OnTouchListener() {
				private WindowManager.LayoutParams paramsF = paramsImage;
				private int initX;
				private int initY;
				private float initTouchX;
				private float initTouchY;
			
				@Override
				public boolean onTouch(View v, MotionEvent event) 
				{
					switch(event.getAction()){
					case MotionEvent.ACTION_DOWN:
						initX = paramsF.x;
						initY = paramsF.y;
						initTouchX = event.getRawX();
						initTouchY = event.getRawY();
						break;
					case MotionEvent.ACTION_UP:
						break;
					case MotionEvent.ACTION_MOVE:
						paramsF.x = initX + (int) (event.getRawX() - initTouchX);
						paramsF.y = initY + (int) (event.getRawY() - initTouchY);
						winManager.updateViewLayout(imgView,  paramsF);
						break;
					}
					return false;
				}
			});
		} catch (Exception e){
			//empty
		}
		
		try{
			imgView.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if(txtView.getText()=="Off"){
						txtView.setText("On");
						winManager.addView(drawView, paramsDraw);
					} else {
						txtView.setText("Off");
						winManager.removeView(drawView);
					}
				}
			});
		} catch (Exception e){
			//empty
		}
	}
  
  @Override
	public void onDestroy(){
		super.onDestroy();
		if(imgView != null) winManager.removeView(imgView);
		if(txtView != null) winManager.removeView(txtView);
		if(txtView.getText()=="On") winManager.removeView(drawView);
	}
}
