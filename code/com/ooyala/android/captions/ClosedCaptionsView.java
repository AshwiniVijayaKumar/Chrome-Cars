package com.ooyala.android.captions;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.os.Handler;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Scroller;
import android.widget.TextView;
import com.ooyala.android.item.Caption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class ClosedCaptionsView
  extends TextView
{
  private Paint StrokePaint;
  private Caption caption;
  private final Runnable charPainter = new Runnable()
  {
    public void run()
    {
      ClosedCaptionsView.this.setText(ClosedCaptionsView.this.paintOnText.subSequence(0, ClosedCaptionsView.access$008(ClosedCaptionsView.this)));
      if (ClosedCaptionsView.this.paintOnIndex <= ClosedCaptionsView.this.paintOnText.length()) {
        ClosedCaptionsView.this.paintOnHandler.postDelayed(ClosedCaptionsView.this.charPainter, ClosedCaptionsView.this.paintOnDelay);
      }
    }
  };
  private String currentText = "";
  private String existingText = "";
  private long paintOnDelay = 10L;
  private final Handler paintOnHandler = new Handler();
  private int paintOnIndex;
  private CharSequence paintOnText;
  private Scroller scroller;
  private ClosedCaptionsStyle style;
  private Rect textBounds;
  private double textHeight;
  private Paint textPaint;
  
  public ClosedCaptionsView(Context paramContext)
  {
    super(paramContext);
    initStyle();
  }
  
  public ClosedCaptionsView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    initStyle();
  }
  
  public ClosedCaptionsView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initStyle();
  }
  
  private void setClosedCaptions(String paramString, Boolean paramBoolean)
  {
    setBackgroundColor(this.style.backgroundColor);
    if (this.style.edgeType == 1) {
      setTextColor(0);
    }
    setGravity(49);
    setText(getSplitTextAndUpdateFrame(paramString));
  }
  
  public Caption getCaption()
  {
    return this.caption;
  }
  
  public String getSplitTextAndUpdateFrame(String paramString)
  {
    int k = (int)(((View)getParent()).getWidth() * 0.9D - getPaddingLeft() - getPaddingRight());
    Object localObject3 = new ArrayList(Arrays.asList(paramString.split("\n")));
    setText(paramString);
    int j = 0;
    Object localObject1 = ((ArrayList)localObject3).iterator();
    int i;
    do
    {
      do
      {
        i = j;
        if (!((Iterator)localObject1).hasNext()) {
          break;
        }
        localObject2 = (String)((Iterator)localObject1).next();
        super.getPaint().getTextBounds((String)localObject2, 0, ((String)localObject2).length(), this.textBounds);
      } while (this.textBounds.width() <= j);
      i = this.textBounds.width();
      j = i;
    } while (this.textBounds.width() < k);
    int m = i;
    Object localObject2 = new ArrayList();
    int n = ((ArrayList)localObject3).size();
    j = n;
    localObject1 = paramString;
    if (i >= k)
    {
      paramString = new Rect();
      localObject1 = ((ArrayList)localObject3).iterator();
      i = n;
      label338:
      while (((Iterator)localObject1).hasNext())
      {
        localObject3 = (String)((Iterator)localObject1).next();
        m = 0;
        j = 0;
        for (;;)
        {
          if (j >= ((String)localObject3).length()) {
            break label338;
          }
          String str = ((String)localObject3).substring(0, j + 1);
          super.getPaint().getTextBounds(str, 0, str.length(), paramString);
          if (paramString.width() > k)
          {
            ((ArrayList)localObject2).add(((String)localObject3).substring(0, m));
            ((ArrayList)localObject2).add(((String)localObject3).substring(m + 1, ((String)localObject3).length()));
            i += 1;
            break;
          }
          if (j == ((String)localObject3).length() - 1) {
            ((ArrayList)localObject2).add(localObject3);
          }
          if (((String)localObject3).charAt(j) == ' ') {
            m = j;
          }
          j += 1;
        }
      }
      paramString = (String)((ArrayList)localObject2).get(0);
      n = 1;
      for (;;)
      {
        j = i;
        localObject1 = paramString;
        m = k;
        if (n >= ((ArrayList)localObject2).size()) {
          break;
        }
        paramString = paramString + "\n" + (String)((ArrayList)localObject2).get(n);
        n += 1;
      }
    }
    setLayoutParams(new FrameLayout.LayoutParams(Math.max(150, m * 10 / 9 + getPaddingLeft() + getPaddingRight()), (int)(j * this.textHeight + (getPaddingBottom() + getPaddingTop())), 81));
    updateBottomMargin();
    return (String)localObject1;
  }
  
  public void initStyle()
  {
    setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 81));
    this.textBounds = new Rect();
    setPadding(10, 10, 10, 10);
    setEnabled(false);
  }
  
  public void onDraw(Canvas paramCanvas)
  {
    if (this.style.edgeType == 1)
    {
      String[] arrayOfString = super.getText().toString().split("\n");
      int i = 0;
      while (i < arrayOfString.length)
      {
        String str = arrayOfString[i];
        if ((getLayout() != null) && (i < getLineCount()))
        {
          int j = getLayout().getLineTop(i) + getBaseline();
          super.getPaint().getTextBounds(str, 0, str.length(), this.textBounds);
          int k = (int)((getWidth() - this.textBounds.width()) * 0.5D);
          paramCanvas.drawText(str, k, j, this.StrokePaint);
          paramCanvas.drawText(str, k, j, this.textPaint);
        }
        i += 1;
      }
    }
    super.onDraw(paramCanvas);
  }
  
  public void paintOn(CharSequence paramCharSequence)
  {
    this.paintOnText = paramCharSequence;
    this.paintOnIndex = 0;
    setText("");
    this.paintOnHandler.removeCallbacks(this.charPainter);
    this.paintOnHandler.postDelayed(this.charPainter, this.paintOnDelay);
  }
  
  public void setCaption(Caption paramCaption)
  {
    double d = -1.0D;
    if (this.caption != null) {
      d = this.caption.getBegin();
    }
    this.caption = paramCaption;
    if ((this.caption != null) && ((!this.currentText.equals(this.caption.getText())) || (d != this.caption.getBegin())))
    {
      this.currentText = paramCaption.getText();
      setClosedCaptions(this.caption.getText(), Boolean.valueOf(false));
      return;
    }
    setBackgroundColor(0);
    setText("");
  }
  
  public void setCaptionText(String paramString)
  {
    if (paramString != null)
    {
      setBackgroundColor(this.style.backgroundColor);
      setClosedCaptions(paramString, Boolean.valueOf(true));
      return;
    }
    setBackgroundColor(0);
    setText("");
  }
  
  public void setStyle(ClosedCaptionsStyle paramClosedCaptionsStyle)
  {
    this.style = paramClosedCaptionsStyle;
    setTextSize(2, paramClosedCaptionsStyle.textSize);
    super.getPaint().getTextBounds("just for height", 0, "just for height".length(), this.textBounds);
    this.textHeight = (this.textBounds.height() * 1.5D);
    setTextColor(this.style.textColor);
    setTypeface(paramClosedCaptionsStyle.textFont);
    updateEdgeStyle();
    updateBottomMargin();
  }
  
  public void updateBottomMargin()
  {
    ViewGroup.MarginLayoutParams localMarginLayoutParams = (ViewGroup.MarginLayoutParams)getLayoutParams();
    localMarginLayoutParams.bottomMargin = this.style.bottomMargin;
    setLayoutParams(localMarginLayoutParams);
  }
  
  public void updateEdgeStyle()
  {
    if (this.style.edgeType == 1)
    {
      this.StrokePaint = new Paint();
      this.StrokePaint.setAntiAlias(true);
      this.StrokePaint.setTextSize(super.getTextSize());
      this.StrokePaint.setStyle(Paint.Style.STROKE);
      this.StrokePaint.setColor(this.style.edgeColor);
      this.StrokePaint.setTypeface(super.getTypeface());
      this.StrokePaint.setFlags(super.getPaintFlags());
      this.StrokePaint.setStrokeWidth(4.0F);
      this.textPaint = new Paint();
      this.textPaint.setAntiAlias(true);
      this.textPaint.setTextSize(super.getTextSize());
      this.textPaint.setStyle(Paint.Style.FILL);
      this.textPaint.setColor(this.style.textColor);
      this.textPaint.setTypeface(super.getTypeface());
      this.textPaint.setFlags(super.getPaintFlags());
      setTextColor(0);
    }
    while (this.style.edgeType != 2) {
      return;
    }
    setShadowLayer(4.0F, 4.0F, 4.0F, this.style.edgeColor);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ooyala\android\captions\ClosedCaptionsView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */