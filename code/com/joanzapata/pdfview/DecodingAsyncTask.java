package com.joanzapata.pdfview;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import org.vudroid.core.DecodeService;
import org.vudroid.core.DecodeServiceBase;
import org.vudroid.pdfdroid.codec.PdfContext;

class DecodingAsyncTask
  extends AsyncTask<Void, Void, Void>
{
  private boolean cancelled = false;
  private DecodeService decodeService;
  private PDFView pdfView;
  private Uri uri;
  
  public DecodingAsyncTask(Uri paramUri, PDFView paramPDFView)
  {
    this.pdfView = paramPDFView;
    this.uri = paramUri;
  }
  
  protected Void doInBackground(Void... paramVarArgs)
  {
    this.decodeService = new DecodeServiceBase(new PdfContext());
    this.decodeService.setContentResolver(this.pdfView.getContext().getContentResolver());
    this.decodeService.open(this.uri);
    return null;
  }
  
  protected void onCancelled()
  {
    this.cancelled = true;
  }
  
  protected void onPostExecute(Void paramVoid)
  {
    if (!this.cancelled) {
      this.pdfView.loadComplete(this.decodeService);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\joanzapata\pdfview\DecodingAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */