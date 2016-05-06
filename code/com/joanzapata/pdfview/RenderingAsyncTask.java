package com.joanzapata.pdfview;

import android.graphics.Bitmap;
import android.graphics.RectF;
import android.os.AsyncTask;
import com.joanzapata.pdfview.model.PagePart;
import java.util.ArrayList;
import java.util.List;
import org.vudroid.core.DecodeService;
import org.vudroid.core.codec.CodecPage;

class RenderingAsyncTask
  extends AsyncTask<Void, PagePart, Void>
{
  private DecodeService decodeService;
  private PDFView pdfView;
  private List<RenderingTask> renderingTasks;
  
  public RenderingAsyncTask(PDFView paramPDFView)
  {
    this.pdfView = paramPDFView;
    this.renderingTasks = new ArrayList();
  }
  
  private PagePart proceed(RenderingTask paramRenderingTask)
  {
    this.decodeService = this.pdfView.getDecodeService();
    Object localObject = this.decodeService.getPage(paramRenderingTask.page);
    synchronized (this.decodeService.getClass())
    {
      localObject = ((CodecPage)localObject).renderBitmap(Math.round(paramRenderingTask.width), Math.round(paramRenderingTask.height), paramRenderingTask.bounds);
      return new PagePart(paramRenderingTask.userPage, paramRenderingTask.page, (Bitmap)localObject, paramRenderingTask.width, paramRenderingTask.height, paramRenderingTask.bounds, paramRenderingTask.thumbnail, paramRenderingTask.cacheOrder);
    }
  }
  
  private boolean waitForRenderingTasks()
  {
    try
    {
      synchronized (this.renderingTasks)
      {
        this.renderingTasks.wait();
        return true;
      }
      return false;
    }
    catch (InterruptedException localInterruptedException) {}
  }
  
  public void addRenderingTask(int paramInt1, int paramInt2, float paramFloat1, float paramFloat2, RectF paramRectF, boolean paramBoolean, int paramInt3)
  {
    paramRectF = new RenderingTask(paramFloat1, paramFloat2, paramRectF, paramInt1, paramInt2, paramBoolean, paramInt3);
    this.renderingTasks.add(paramRectF);
    wakeUp();
  }
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    do
    {
      if (isCancelled()) {
        break;
      }
      while (!this.renderingTasks.isEmpty())
      {
        paramVarArgs = (RenderingTask)this.renderingTasks.get(0);
        PagePart localPagePart = proceed(paramVarArgs);
        if (this.renderingTasks.remove(paramVarArgs)) {
          publishProgress(new PagePart[] { localPagePart });
        } else {
          localPagePart.getRenderedBitmap().recycle();
        }
      }
    } while ((waitForRenderingTasks()) && (!isCancelled()));
    return null;
  }
  
  protected void onPreExecute() {}
  
  protected void onProgressUpdate(PagePart... paramVarArgs)
  {
    this.pdfView.onBitmapRendered(paramVarArgs[0]);
  }
  
  public void removeAllTasks()
  {
    this.renderingTasks.clear();
  }
  
  public void wakeUp()
  {
    synchronized (this.renderingTasks)
    {
      this.renderingTasks.notify();
      return;
    }
  }
  
  private class RenderingTask
  {
    RectF bounds;
    int cacheOrder;
    float height;
    int page;
    boolean thumbnail;
    int userPage;
    float width;
    
    public RenderingTask(float paramFloat1, float paramFloat2, RectF paramRectF, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
    {
      this.page = paramInt2;
      this.width = paramFloat1;
      this.height = paramFloat2;
      this.bounds = paramRectF;
      this.userPage = paramInt1;
      this.thumbnail = paramBoolean;
      this.cacheOrder = paramInt3;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\joanzapata\pdfview\RenderingAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */