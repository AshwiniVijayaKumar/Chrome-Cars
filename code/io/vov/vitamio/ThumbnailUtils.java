package io.vov.vitamio;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

public class ThumbnailUtils
{
  private static final int OPTIONS_NONE = 0;
  public static final int OPTIONS_RECYCLE_INPUT = 2;
  private static final int OPTIONS_SCALE_UP = 1;
  public static final int TARGET_SIZE_MICRO_THUMBNAIL_HEIGHT = 160;
  public static final int TARGET_SIZE_MICRO_THUMBNAIL_WIDTH = 212;
  public static final int TARGET_SIZE_MINI_THUMBNAIL_HEIGHT = 320;
  public static final int TARGET_SIZE_MINI_THUMBNAIL_WIDTH = 426;
  
  /* Error */
  public static Bitmap createVideoThumbnail(android.content.Context paramContext, String paramString, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 36	io/vov/vitamio/Vitamio:isInitialized	(Landroid/content/Context;)Z
    //   4: ifne +7 -> 11
    //   7: aconst_null
    //   8: astore_1
    //   9: aload_1
    //   10: areturn
    //   11: aconst_null
    //   12: astore_3
    //   13: aconst_null
    //   14: astore 4
    //   16: aconst_null
    //   17: astore 5
    //   19: new 38	io/vov/vitamio/MediaMetadataRetriever
    //   22: dup
    //   23: aload_0
    //   24: invokespecial 41	io/vov/vitamio/MediaMetadataRetriever:<init>	(Landroid/content/Context;)V
    //   27: astore_0
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual 45	io/vov/vitamio/MediaMetadataRetriever:setDataSource	(Ljava/lang/String;)V
    //   33: aload_0
    //   34: ldc2_w 46
    //   37: invokevirtual 51	io/vov/vitamio/MediaMetadataRetriever:getFrameAtTime	(J)Landroid/graphics/Bitmap;
    //   40: astore_1
    //   41: aload_0
    //   42: invokevirtual 54	io/vov/vitamio/MediaMetadataRetriever:release	()V
    //   45: aload_1
    //   46: astore_0
    //   47: aload_0
    //   48: astore_1
    //   49: aload_0
    //   50: ifnull -41 -> 9
    //   53: iload_2
    //   54: iconst_3
    //   55: if_icmpne +50 -> 105
    //   58: aload_0
    //   59: sipush 212
    //   62: sipush 160
    //   65: iconst_2
    //   66: invokestatic 58	io/vov/vitamio/ThumbnailUtils:extractThumbnail	(Landroid/graphics/Bitmap;III)Landroid/graphics/Bitmap;
    //   69: areturn
    //   70: astore_0
    //   71: aload 5
    //   73: astore_0
    //   74: aload_0
    //   75: invokevirtual 54	io/vov/vitamio/MediaMetadataRetriever:release	()V
    //   78: aload_3
    //   79: astore_0
    //   80: goto -33 -> 47
    //   83: astore_0
    //   84: aload_3
    //   85: astore_0
    //   86: goto -39 -> 47
    //   89: astore_0
    //   90: aload 4
    //   92: astore_1
    //   93: aload_1
    //   94: invokevirtual 54	io/vov/vitamio/MediaMetadataRetriever:release	()V
    //   97: aload_0
    //   98: athrow
    //   99: astore_0
    //   100: aload_1
    //   101: astore_0
    //   102: goto -55 -> 47
    //   105: aload_0
    //   106: astore_1
    //   107: iload_2
    //   108: iconst_1
    //   109: if_icmpne -100 -> 9
    //   112: aload_0
    //   113: sipush 426
    //   116: sipush 320
    //   119: iconst_2
    //   120: invokestatic 58	io/vov/vitamio/ThumbnailUtils:extractThumbnail	(Landroid/graphics/Bitmap;III)Landroid/graphics/Bitmap;
    //   123: areturn
    //   124: astore_1
    //   125: goto -28 -> 97
    //   128: astore_3
    //   129: aload_0
    //   130: astore_1
    //   131: aload_3
    //   132: astore_0
    //   133: goto -40 -> 93
    //   136: astore_1
    //   137: goto -63 -> 74
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	140	0	paramContext	android.content.Context
    //   0	140	1	paramString	String
    //   0	140	2	paramInt	int
    //   12	73	3	localObject1	Object
    //   128	4	3	localObject2	Object
    //   14	77	4	localObject3	Object
    //   17	55	5	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   19	28	70	java/lang/Exception
    //   74	78	83	java/lang/RuntimeException
    //   19	28	89	finally
    //   41	45	99	java/lang/RuntimeException
    //   93	97	124	java/lang/RuntimeException
    //   28	41	128	finally
    //   28	41	136	java/lang/Exception
  }
  
  public static Bitmap extractThumbnail(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    return extractThumbnail(paramBitmap, paramInt1, paramInt2, 0);
  }
  
  public static Bitmap extractThumbnail(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramBitmap == null) {
      return null;
    }
    if (paramBitmap.getWidth() < paramBitmap.getHeight()) {}
    for (float f = paramInt1 / paramBitmap.getWidth();; f = paramInt2 / paramBitmap.getHeight())
    {
      Matrix localMatrix = new Matrix();
      localMatrix.setScale(f, f);
      return transform(localMatrix, paramBitmap, paramInt1, paramInt2, paramInt3 | 0x1);
    }
  }
  
  private static Bitmap transform(Matrix paramMatrix, Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if ((paramInt3 & 0x1) != 0)
    {
      i = 1;
      if ((paramInt3 & 0x2) == 0) {
        break label198;
      }
    }
    int j;
    Object localObject;
    label198:
    for (paramInt3 = 1;; paramInt3 = 0)
    {
      int k = paramBitmap.getWidth() - paramInt1;
      j = paramBitmap.getHeight() - paramInt2;
      if ((i != 0) || ((k >= 0) && (j >= 0))) {
        break label204;
      }
      localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
      paramMatrix = new Canvas(localBitmap);
      i = Math.max(0, k / 2);
      j = Math.max(0, j / 2);
      localObject = new Rect(i, j, Math.min(paramInt1, paramBitmap.getWidth()) + i, Math.min(paramInt2, paramBitmap.getHeight()) + j);
      i = (paramInt1 - ((Rect)localObject).width()) / 2;
      j = (paramInt2 - ((Rect)localObject).height()) / 2;
      paramMatrix.drawBitmap(paramBitmap, (Rect)localObject, new Rect(i, j, paramInt1 - i, paramInt2 - j), null);
      paramMatrix = localBitmap;
      if (paramInt3 != 0)
      {
        paramBitmap.recycle();
        paramMatrix = localBitmap;
      }
      return paramMatrix;
      i = 0;
      break;
    }
    label204:
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    if (f1 / f2 > paramInt1 / paramInt2)
    {
      f1 = paramInt2 / f2;
      label239:
      if ((f1 >= 0.9F) && (f1 <= 1.0F)) {
        break label384;
      }
      paramMatrix.setScale(f1, f1);
      label262:
      if (paramMatrix == null) {
        break label389;
      }
    }
    label384:
    label389:
    for (Bitmap localBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), paramMatrix, true);; localBitmap = paramBitmap)
    {
      if ((paramInt3 != 0) && (localBitmap != paramBitmap)) {
        paramBitmap.recycle();
      }
      i = Math.max(0, localBitmap.getWidth() - paramInt1);
      j = Math.max(0, localBitmap.getHeight() - paramInt2);
      localObject = Bitmap.createBitmap(localBitmap, i / 2, j / 2, paramInt1, paramInt2);
      paramMatrix = (Matrix)localObject;
      if (localObject == localBitmap) {
        break;
      }
      if (paramInt3 == 0)
      {
        paramMatrix = (Matrix)localObject;
        if (localBitmap == paramBitmap) {
          break;
        }
      }
      localBitmap.recycle();
      return (Bitmap)localObject;
      f1 = paramInt1 / f1;
      break label239;
      paramMatrix = null;
      break label262;
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\io\vov\vitamio\ThumbnailUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */