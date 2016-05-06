package com.google.android.gms.common.images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.internal.ea;
import com.google.android.gms.internal.eb;
import com.google.android.gms.internal.ec;
import com.google.android.gms.internal.ed;
import com.google.android.gms.internal.ep;
import com.google.android.gms.internal.fr;
import java.lang.ref.WeakReference;

public final class a
{
  final a AF;
  private int AG = 0;
  private int AH = 0;
  int AI;
  private int AJ;
  private WeakReference<ImageManager.OnImageLoadedListener> AK;
  private WeakReference<ImageView> AL;
  private WeakReference<TextView> AM;
  private int AN = -1;
  private boolean AO = true;
  private boolean AP = false;
  private int AQ;
  
  public a(int paramInt)
  {
    this.AF = new a(null);
    this.AH = paramInt;
  }
  
  public a(Uri paramUri)
  {
    this.AF = new a(paramUri);
    this.AH = 0;
  }
  
  private ea a(Drawable paramDrawable1, Drawable paramDrawable2)
  {
    if (paramDrawable1 != null)
    {
      localDrawable = paramDrawable1;
      if (!(paramDrawable1 instanceof ea)) {}
    }
    for (Drawable localDrawable = ((ea)paramDrawable1).dO();; localDrawable = null) {
      return new ea(localDrawable, paramDrawable2);
    }
  }
  
  private void a(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    switch (this.AI)
    {
    }
    Object localObject;
    do
    {
      do
      {
        do
        {
          do
          {
            return;
          } while (paramBoolean2);
          localObject = (ImageManager.OnImageLoadedListener)this.AK.get();
        } while (localObject == null);
        ((ImageManager.OnImageLoadedListener)localObject).onImageLoaded(this.AF.uri, paramDrawable, paramBoolean3);
        return;
        localObject = (ImageView)this.AL.get();
      } while (localObject == null);
      a((ImageView)localObject, paramDrawable, paramBoolean1, paramBoolean2, paramBoolean3);
      return;
      localObject = (TextView)this.AM.get();
    } while (localObject == null);
    a((TextView)localObject, this.AN, paramDrawable, paramBoolean1, paramBoolean2);
  }
  
  private void a(ImageView paramImageView, Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if ((!paramBoolean2) && (!paramBoolean3)) {}
    for (int i = 1; (i != 0) && ((paramImageView instanceof ec)); i = 0)
    {
      int j = ((ec)paramImageView).dQ();
      if ((this.AH == 0) || (j != this.AH)) {
        break;
      }
      return;
    }
    paramBoolean1 = b(paramBoolean1, paramBoolean2);
    if (paramBoolean1) {
      paramDrawable = a(paramImageView.getDrawable(), paramDrawable);
    }
    for (;;)
    {
      paramImageView.setImageDrawable(paramDrawable);
      ec localec;
      if ((paramImageView instanceof ec))
      {
        localec = (ec)paramImageView;
        if (!paramBoolean3) {
          break label149;
        }
        paramImageView = this.AF.uri;
        label110:
        localec.d(paramImageView);
        if (i == 0) {
          break label154;
        }
      }
      label149:
      label154:
      for (i = this.AH;; i = 0)
      {
        localec.N(i);
        if (!paramBoolean1) {
          break;
        }
        ((ea)paramDrawable).startTransition(250);
        return;
        paramImageView = null;
        break label110;
      }
    }
  }
  
  private void a(TextView paramTextView, int paramInt, Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2)
  {
    paramBoolean1 = b(paramBoolean1, paramBoolean2);
    Object localObject;
    Drawable localDrawable1;
    if (fr.eO())
    {
      localObject = paramTextView.getCompoundDrawablesRelative();
      localDrawable1 = localObject[paramInt];
      if (!paramBoolean1) {
        break label166;
      }
      paramDrawable = a(localDrawable1, paramDrawable);
    }
    label48:
    label56:
    label64:
    label72:
    label124:
    label133:
    label142:
    label151:
    label166:
    for (;;)
    {
      Drawable localDrawable2;
      Drawable localDrawable3;
      if (paramInt == 0)
      {
        localDrawable1 = paramDrawable;
        if (paramInt != 1) {
          break label124;
        }
        localDrawable2 = paramDrawable;
        if (paramInt != 2) {
          break label133;
        }
        localDrawable3 = paramDrawable;
        if (paramInt != 3) {
          break label142;
        }
        localObject = paramDrawable;
        if (!fr.eO()) {
          break label151;
        }
        paramTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(localDrawable1, localDrawable2, localDrawable3, (Drawable)localObject);
      }
      for (;;)
      {
        if (paramBoolean1) {
          ((ea)paramDrawable).startTransition(250);
        }
        return;
        localObject = paramTextView.getCompoundDrawables();
        break;
        localDrawable1 = localObject[0];
        break label48;
        localDrawable2 = localObject[1];
        break label56;
        localDrawable3 = localObject[2];
        break label64;
        localObject = localObject[3];
        break label72;
        paramTextView.setCompoundDrawablesWithIntrinsicBounds(localDrawable1, localDrawable2, localDrawable3, (Drawable)localObject);
      }
    }
  }
  
  private boolean b(boolean paramBoolean1, boolean paramBoolean2)
  {
    return (this.AO) && (!paramBoolean2) && ((!paramBoolean1) || (this.AP));
  }
  
  public void L(int paramInt)
  {
    this.AH = paramInt;
  }
  
  void a(Context paramContext, Bitmap paramBitmap, boolean paramBoolean)
  {
    ed.d(paramBitmap);
    Bitmap localBitmap = paramBitmap;
    if ((this.AQ & 0x1) != 0) {
      localBitmap = eb.a(paramBitmap);
    }
    a(new BitmapDrawable(paramContext.getResources(), localBitmap), paramBoolean, false, true);
  }
  
  public void a(ImageView paramImageView)
  {
    ed.d(paramImageView);
    this.AK = null;
    this.AL = new WeakReference(paramImageView);
    this.AM = null;
    this.AN = -1;
    this.AI = 2;
    this.AJ = paramImageView.hashCode();
  }
  
  public void a(ImageManager.OnImageLoadedListener paramOnImageLoadedListener)
  {
    ed.d(paramOnImageLoadedListener);
    this.AK = new WeakReference(paramOnImageLoadedListener);
    this.AL = null;
    this.AM = null;
    this.AN = -1;
    this.AI = 1;
    this.AJ = ep.hashCode(new Object[] { paramOnImageLoadedListener, this.AF });
  }
  
  void b(Context paramContext, boolean paramBoolean)
  {
    Object localObject = null;
    if (this.AH != 0)
    {
      Resources localResources = paramContext.getResources();
      paramContext = localResources.getDrawable(this.AH);
      localObject = paramContext;
      if ((this.AQ & 0x1) != 0) {
        localObject = eb.a(localResources, paramContext);
      }
    }
    a((Drawable)localObject, paramBoolean, false, false);
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof a)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (this == paramObject);
      bool1 = bool2;
    } while (((a)paramObject).hashCode() == hashCode());
    return false;
  }
  
  public int hashCode()
  {
    return this.AJ;
  }
  
  void x(Context paramContext)
  {
    Object localObject = null;
    if (this.AG != 0)
    {
      Resources localResources = paramContext.getResources();
      paramContext = localResources.getDrawable(this.AG);
      localObject = paramContext;
      if ((this.AQ & 0x1) != 0) {
        localObject = eb.a(localResources, paramContext);
      }
    }
    a((Drawable)localObject, false, true, false);
  }
  
  public static final class a
  {
    public final Uri uri;
    
    public a(Uri paramUri)
    {
      this.uri = paramUri;
    }
    
    public boolean equals(Object paramObject)
    {
      boolean bool2 = true;
      boolean bool1;
      if (!(paramObject instanceof a)) {
        bool1 = false;
      }
      do
      {
        do
        {
          return bool1;
          bool1 = bool2;
        } while (this == paramObject);
        bool1 = bool2;
      } while (((a)paramObject).hashCode() == hashCode());
      return false;
    }
    
    public int hashCode()
    {
      return ep.hashCode(new Object[] { this.uri });
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\common\images\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */