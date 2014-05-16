package com.prosartan.dttwtsolver;

import com.prosartan.dttwtsolver;
import com.prosartan.dttwtsolver;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity
{
  @Override
  protected void onCreate(Bundle icicle)
  {
    super.onCreate(icicle);
    setContentView(R.layout.main);
    
    Bundle bund = getIntent().getExtras();
    
    if(bund != null && bund.getString("LAUNCHER").equals("YES")
    {
      startService(new Intent(MainActivity.this, MainService.class));
    }
    
    Button start = (Button)findViewById(R.id.btnStart);
    start.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        startService(new Intent(MainActivity.this, MainService.class));
      }
    });
    
    Button stop = (Button)findViewById(R.id.btnStop);
    stop.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        stopService(new Intent(MainActivity.this, MainService.class));
      }
    });
  }
  
  @Override
  protected void onResume()
  {
    Bundle bund = getIntent().getExtras();
    
    if(bund != null && bund.getString("LAUNCHER").equals("YES"))
    {
      startService(new Intent(MainActivity.this, MainService.class));
    }
    super.onResume();
  }
}
