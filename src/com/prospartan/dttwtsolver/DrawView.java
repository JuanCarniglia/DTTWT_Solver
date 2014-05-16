package com.prospartan.dttwlsolver;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.grapgics.Paint;
import android.view.View;


public class DrawView extends View
{
  Paint paint = new Paint();
  
  public DrawView(Context context)
  {
    super(context);
    paint.setColor(Color.RED);
    paint.setStrokeWidth(5);
  }
  
  @Override
  public void onDraw(Canvas cancas)
  {
    canvas.drawLine(0, 960, 540, 960, paint);
  }
}
