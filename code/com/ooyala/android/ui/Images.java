package com.ooyala.android.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.Base64;
import android.util.DisplayMetrics;

class Images
{
  public static final int CLOSED_CAPTIONS = 6;
  public static final int FULLSCREEN = 2;
  public static final int NEXT = 4;
  public static final int PAUSE = 1;
  public static final int PLAY = 0;
  public static final int PREVIOUS = 5;
  public static final int SMALLSCREEN = 3;
  
  public static void closedCaptions(Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte = Base64.decode("iVBORw0KGgoAAAANSUhEUgAAAUAAAAE1CAMAAAChocnqAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAAZQTFRF////////VXz1bAAAAAJ0Uk5T/wDltzBKAAAEC0lEQVR42uzdQXIjMQxDUeD+l56sZ5UOKImUPva0xWcnVa1mWzKJIggABBBAAAmAAAIIIAEQQAABJAACCCCABEAAAQSQAAgggAASAAEEEEACIIAAAkgABBBAAAmAAAIIIAEQQAABBJAACCCAABIAAQQQQALgeEC1yxxANU5/QLVPZ0ANSU9AjUo7QI1LK0CNTBtAjU0PQOl5QT3KV0aoZ/mKCPWyX4WgnvYrENTTfAWEet0vFdTzfqGg8MsEhV8mKPwyQeGXCQq/TFD4ZYLCLxMEcCOghGACKCGYAEoIAngQUEIwAZQQPAF4cOysF+AcuN0rXwHYbgbyPOBgvdUNFAO6bY4CXuC3rIlKQDdPd0D7DsF6wEv41rRSBWjfI1gNeJNffTc1gPZVggBOA7TvEqwFvM2vuKMCQPs6QQAnAdr3CVYC6kVAbQS03/4KAgjgbED7SkEAAQQQQAABBPB6QPtOQQABBBBAAAEEEEAAAQQQwMcBJ1Q0BvxetKNiDOD3sh0VYwC/F+6omAP4vXJHxRzA73uWOyrmAH6fP9lRMQfw+yTtjoo5gN8fyNhRMQfw+zNBOyquA9TmijmA35+M3FEB4DOA0tfudlQA+AygPje3o+JSQG2rABBAAAEEEEAAAQQQQAABBBBANhMAZD+Q/cAUcGcF90S4qQTgr9/oe287KphMeGcygdkYprOYD0w+4A6ATKjGb1R8eVBUMQjQlf/a6yoGAfKciHlS6Tjg/y/RpWISYMsAeAcgP/0EIIAAAggggABOBeSHuAEEcDYgx2G8+BUs7IkjgQAcD8ixaI8J1vbD0ZAA3gDI8bjPCJb3whHhGwA5pH4PoO7wOwjYm3BRE/IjgqtaKAbsSriuAZUvoJ/h0sWvAGxluHrlWrSO45C7FrwYcEHWrm4ZYGvBg36/B3RfwJN+EwE1FLCt4FE/yx7+R3zWz/JwwcN+QwG1YkkbAPt9BY/7WZ4seN7PnzcfWgme9/P3i+dGgA38/IeLvzaCHfz8l6u/JoAt/Pynq5fL4t2Axi8ENH4h4D2E+c63nxb0OUDjFwLOJ6y5eXXi1tc9fCmgn/dz/EJv8xUATiQsvYF/cArgAr4iwEGG9SMkx6dRJuvVAjY3XDXE1GYuap7dKsAGn8bOBfpCQQMYARrASNAARoIGMAI0gJkggJmgAYwADWAkaAAjQQMYARrATBDATNAARoAGMBI0gJGgAYwADWAkaAABPCloACNBAxgBGsBI0ABGggYwAjSAmSCAmaABjAANYCRoACNBAxgBGsBI0ABmggBmgAbwjgAIIIAAAkgABBBAAAmAAAIIIAEQQAABJAACCCCABEAAAQSQAAgggAASAAEEEEACIIAAAkgABBBAAMlP/gkwAEQgHow8opxxAAAAAElFTkSuQmCC", 0);
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
    localOptions.inDither = true;
    paramCanvas.drawBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length, localOptions), paramInt1, paramInt2, true), 0.0F, 0.0F, paramPaint);
  }
  
  public static int dpToPixels(Context paramContext, int paramInt)
  {
    float f = paramContext.getResources().getDisplayMetrics().density;
    return (int)(paramInt * f + 0.5F);
  }
  
  public static void drawImage(int paramInt1, Context paramContext, Canvas paramCanvas, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean)
  {
    paramCanvas.drawColor(paramInt2);
    Paint localPaint = new Paint();
    localPaint.setDither(true);
    localPaint.setAntiAlias(true);
    localPaint.setColor(paramInt3);
    localPaint.setStyle(Paint.Style.FILL);
    paramInt2 = dpToPixels(paramContext, paramInt6);
    switch (paramInt1)
    {
    }
    for (;;)
    {
      if (paramBoolean)
      {
        paramContext = new GradientDrawable(GradientDrawable.Orientation.BOTTOM_TOP, new int[] { -1, 16777215 });
        paramContext.setGradientType(1);
        paramContext.setGradientRadius(paramInt4 / 2);
        paramContext.setBounds(0, 0, paramInt4, paramInt5);
        paramContext.setDither(true);
        paramContext.draw(paramCanvas);
      }
      return;
      play(paramCanvas, localPaint, paramInt4, paramInt5, paramInt2);
      continue;
      pause(paramCanvas, localPaint, paramInt4, paramInt5, paramInt2);
      continue;
      fullscreen(paramCanvas, localPaint, paramInt4, paramInt5, paramInt2);
      continue;
      smallscreen(paramCanvas, localPaint, paramInt4, paramInt5, paramInt2);
      continue;
      next(paramCanvas, localPaint, paramInt4, paramInt5, paramInt2);
      continue;
      previous(paramCanvas, localPaint, paramInt4, paramInt5, paramInt2);
      continue;
      closedCaptions(paramCanvas, localPaint, paramInt4, paramInt5, paramInt2);
    }
  }
  
  public static void fullscreen(Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject = new Path();
    float f3 = paramInt3;
    float f4 = paramInt3;
    float f1;
    float f2;
    if (paramInt1 > paramInt2)
    {
      f1 = f3 + (paramInt1 - paramInt2) / 2.0F;
      f2 = f4;
    }
    for (;;)
    {
      f3 = paramInt1 - 2.0F * f1;
      f4 = paramInt2 - 2.0F * f2;
      ((Path)localObject).moveTo(f1, f2);
      ((Path)localObject).lineTo(f3 / 2.0F + f1, f2);
      ((Path)localObject).lineTo(f1, f4 / 2.0F + f2);
      ((Path)localObject).lineTo(f1, f2);
      ((Path)localObject).moveTo(f1 + f3, f4 / 2.0F + f2);
      ((Path)localObject).lineTo(f1 + f3, f2 + f4);
      ((Path)localObject).lineTo(f3 / 2.0F + f1, f2 + f4);
      ((Path)localObject).lineTo(f1 + f3, f4 / 2.0F + f2);
      paramCanvas.drawPath((Path)localObject, paramPaint);
      localObject = new Paint();
      ((Paint)localObject).setDither(true);
      ((Paint)localObject).setColor(paramPaint.getColor());
      ((Paint)localObject).setStyle(Paint.Style.STROKE);
      float f5 = f3 / 5.0F;
      ((Paint)localObject).setStrokeWidth(f5);
      paramCanvas.drawLine(f1 + f5, f2 + f5, f3 / 2.0F + f1 - f5 / 2.0F, f4 / 2.0F + f2 - f5 / 2.0F, (Paint)localObject);
      float f6 = f3 / 2.0F;
      float f7 = f5 / 2.0F;
      float f8 = f4 / 2.0F;
      paramCanvas.drawLine(f1 + f3 - f5, f2 + f4 - f5, f7 + (f6 + f1), f5 / 2.0F + (f8 + f2), (Paint)localObject);
      return;
      f1 = f3;
      f2 = f4;
      if (paramInt2 > paramInt1)
      {
        f2 = f4 + (paramInt2 - paramInt1) / 2.0F;
        f1 = f3;
      }
    }
  }
  
  public static GradientDrawable gradientBackground(GradientDrawable.Orientation paramOrientation)
  {
    paramOrientation = new GradientDrawable(paramOrientation, new int[] { -16777216, -2146101995 });
    paramOrientation.setCornerRadius(0.0F);
    return paramOrientation;
  }
  
  public static void next(Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3)
  {
    Object localObject = new Path();
    float f1 = paramInt1 - paramInt3 * 2.0F;
    float f2 = paramInt2 - paramInt3 * 2.0F;
    float f3 = f1 / 10.0F;
    ((Path)localObject).moveTo(paramInt3, paramInt3);
    ((Path)localObject).lineTo(paramInt3 + (f1 - f3) / 2.0F, paramInt3 + f2 / 2.0F);
    ((Path)localObject).lineTo(paramInt3, paramInt3 + f2);
    ((Path)localObject).lineTo(paramInt3, paramInt3);
    ((Path)localObject).moveTo(paramInt3 + (f1 - f3) / 2.0F, paramInt3);
    ((Path)localObject).lineTo(paramInt3 + f1 - f3, paramInt3 + f2 / 2.0F);
    ((Path)localObject).lineTo(paramInt3 + (f1 - f3) / 2.0F, paramInt3 + f2);
    ((Path)localObject).lineTo(paramInt3 + (f1 - f3) / 2.0F, paramInt3);
    paramCanvas.drawPath((Path)localObject, paramPaint);
    localObject = new Paint();
    ((Paint)localObject).setDither(true);
    ((Paint)localObject).setColor(paramPaint.getColor());
    ((Paint)localObject).setStyle(Paint.Style.STROKE);
    ((Paint)localObject).setStrokeWidth(f3);
    paramCanvas.drawLine(paramInt3 + f1 - f3 / 2.0F, paramInt3, paramInt3 + f1 - f3 / 2.0F, paramInt3 + f2, (Paint)localObject);
  }
  
  public static void pause(Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3)
  {
    Path localPath = new Path();
    float f1 = paramInt1 - paramInt3 * 2.0F;
    float f2 = paramInt2 - paramInt3 * 2.0F;
    localPath.moveTo(paramInt3 + 1.5F * f1 / 8.0F, paramInt3);
    localPath.lineTo(paramInt3 + 3.5F * f1 / 8.0F, paramInt3);
    localPath.lineTo(paramInt3 + 3.5F * f1 / 8.0F, paramInt3 + f2);
    localPath.lineTo(paramInt3 + 1.5F * f1 / 8.0F, paramInt3 + f2);
    localPath.lineTo(paramInt3 + 1.5F * f1 / 8.0F, paramInt3);
    localPath.moveTo(paramInt3 + 4.5F * f1 / 8.0F, paramInt3);
    localPath.lineTo(paramInt3 + 6.5F * f1 / 8.0F, paramInt3);
    localPath.lineTo(paramInt3 + 6.5F * f1 / 8.0F, paramInt3 + f2);
    localPath.lineTo(paramInt3 + 4.5F * f1 / 8.0F, paramInt3 + f2);
    localPath.lineTo(paramInt3 + 4.5F * f1 / 8.0F, paramInt3);
    paramCanvas.drawPath(localPath, paramPaint);
  }
  
  public static void play(Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3)
  {
    Path localPath = new Path();
    float f1 = paramInt2 - paramInt3 * 2.0F;
    float f3 = paramInt1;
    float f4 = paramInt3;
    float f2 = (float)(Math.sqrt(3.0D) / 2.0D * f1);
    f3 = paramInt3 + (f3 - f4 * 2.0F - f2) / 2.0F;
    localPath.moveTo(f3, paramInt3);
    localPath.lineTo(f3 + f2, paramInt3 + f1 / 2.0F);
    localPath.lineTo(f3, paramInt2 - paramInt3);
    localPath.lineTo(f3, paramInt3);
    paramCanvas.drawPath(localPath, paramPaint);
  }
  
  public static void previous(Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3)
  {
    Path localPath = new Path();
    float f1 = paramInt1 - paramInt3 * 2.0F;
    float f2 = paramInt2 - paramInt3 * 2.0F;
    float f3 = f1 / 10.0F;
    Paint localPaint = new Paint();
    localPaint.setDither(true);
    localPaint.setColor(paramPaint.getColor());
    localPaint.setStyle(Paint.Style.STROKE);
    localPaint.setStrokeWidth(f3);
    float f4 = paramInt3;
    float f5 = f3 / 2.0F;
    float f6 = paramInt3;
    float f7 = paramInt3;
    paramCanvas.drawLine(f5 + f4, f6, f3 / 2.0F + f7, paramInt3 + f2, localPaint);
    localPath.moveTo(paramInt3 + f3, paramInt3 + f2 / 2.0F);
    localPath.lineTo(paramInt3 + f3 + (f1 - f3) / 2.0F, paramInt3);
    localPath.lineTo(paramInt3 + f3 + (f1 - f3) / 2.0F, paramInt3 + f2);
    localPath.lineTo(paramInt3 + f3, paramInt3 + f2 / 2.0F);
    localPath.moveTo(paramInt3 + f3 + (f1 - f3) / 2.0F, paramInt3 + f2 / 2.0F);
    localPath.lineTo(paramInt3 + f1, paramInt3);
    localPath.lineTo(paramInt3 + f1, paramInt3 + f2);
    localPath.lineTo(paramInt3 + f3 + (f1 - f3) / 2.0F, paramInt3 + f2 / 2.0F);
    paramCanvas.drawPath(localPath, paramPaint);
  }
  
  public static void smallscreen(Canvas paramCanvas, Paint paramPaint, int paramInt1, int paramInt2, int paramInt3)
  {
    Path localPath = new Path();
    float f3 = paramInt3;
    float f4 = paramInt3;
    float f1;
    float f2;
    if (paramInt1 > paramInt2)
    {
      f1 = f3 + (paramInt1 - paramInt2) / 2.0F;
      f2 = f4;
    }
    for (;;)
    {
      f3 = paramInt1 - 2.0F * f1;
      f4 = paramInt2 - 2.0F * f2;
      Paint localPaint = new Paint();
      localPaint.setDither(true);
      localPaint.setColor(paramPaint.getColor());
      localPaint.setStyle(Paint.Style.STROKE);
      float f5 = f3 / 5.0F;
      localPaint.setStrokeWidth(f5);
      paramCanvas.drawLine(f1, f2, f3 / 2.0F + f1 - f5, f4 / 2.0F + f2 - f5, localPaint);
      paramCanvas.drawLine(f1 + f3, f2 + f4, f3 / 2.0F + f1 + f5, f4 / 2.0F + f2 + f5, localPaint);
      localPath.moveTo(f3 / 2.0F + f1 - f5 / 4.0F, f2);
      localPath.lineTo(f3 / 2.0F + f1 - f5 / 4.0F, f4 / 2.0F + f2 - f5 / 4.0F);
      localPath.lineTo(f1, f4 / 2.0F + f2 - f5 / 4.0F);
      localPath.lineTo(f3 / 2.0F + f1 - f5 / 4.0F, f2);
      localPath.moveTo(f3 / 2.0F + f1 + f5 / 4.0F, f4 / 2.0F + f2 + f5 / 4.0F);
      localPath.lineTo(f1 + f3, f4 / 2.0F + f2 + f5 / 4.0F);
      localPath.lineTo(f3 / 2.0F + f1 + f5 / 4.0F, f2 + f4);
      localPath.lineTo(f3 / 2.0F + f1 + f5 / 4.0F, f4 / 2.0F + f2 + f5 / 4.0F);
      paramCanvas.drawPath(localPath, paramPaint);
      return;
      f1 = f3;
      f2 = f4;
      if (paramInt2 > paramInt1)
      {
        f2 = f4 + (paramInt2 - paramInt1) / 2.0F;
        f1 = f3;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\ui\Images.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */