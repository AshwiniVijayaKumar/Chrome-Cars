package org.apache.cordova.file;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.LOG;

public class PermissionHelper
{
  private static final String LOG_TAG = "CordovaPermissionHelper";
  
  private static void deliverPermissionResult(CordovaPlugin paramCordovaPlugin, int paramInt, String[] paramArrayOfString)
  {
    int[] arrayOfInt = new int[paramArrayOfString.length];
    Arrays.fill(arrayOfInt, 0);
    try
    {
      CordovaPlugin.class.getDeclaredMethod("onRequestPermissionResult", new Class[] { Integer.TYPE, String[].class, int[].class }).invoke(paramCordovaPlugin, new Object[] { Integer.valueOf(paramInt), paramArrayOfString, arrayOfInt });
      return;
    }
    catch (NoSuchMethodException paramCordovaPlugin)
    {
      LOG.e("CordovaPermissionHelper", "NoSuchMethodException when delivering permissions results", paramCordovaPlugin);
      return;
    }
    catch (IllegalAccessException paramCordovaPlugin)
    {
      LOG.e("CordovaPermissionHelper", "IllegalAccessException when delivering permissions results", paramCordovaPlugin);
      return;
    }
    catch (InvocationTargetException paramCordovaPlugin)
    {
      LOG.e("CordovaPermissionHelper", "InvocationTargetException when delivering permissions results", paramCordovaPlugin);
    }
  }
  
  public static boolean hasPermission(CordovaPlugin paramCordovaPlugin, String paramString)
  {
    try
    {
      boolean bool = ((Boolean)CordovaInterface.class.getDeclaredMethod("hasPermission", new Class[] { String.class }).invoke(paramCordovaPlugin.cordova, new Object[] { paramString })).booleanValue();
      return bool;
    }
    catch (NoSuchMethodException paramCordovaPlugin)
    {
      LOG.d("CordovaPermissionHelper", "No need to check for permission " + paramString);
      return true;
    }
    catch (IllegalAccessException paramCordovaPlugin)
    {
      LOG.e("CordovaPermissionHelper", "IllegalAccessException when checking permission " + paramString, paramCordovaPlugin);
      return false;
    }
    catch (InvocationTargetException paramCordovaPlugin)
    {
      for (;;)
      {
        LOG.e("CordovaPermissionHelper", "invocationTargetException when checking permission " + paramString, paramCordovaPlugin);
      }
    }
  }
  
  public static void requestPermission(CordovaPlugin paramCordovaPlugin, int paramInt, String paramString)
  {
    requestPermissions(paramCordovaPlugin, paramInt, new String[] { paramString });
  }
  
  public static void requestPermissions(CordovaPlugin paramCordovaPlugin, int paramInt, String[] paramArrayOfString)
  {
    try
    {
      CordovaInterface.class.getDeclaredMethod("requestPermissions", new Class[] { CordovaPlugin.class, Integer.TYPE, String[].class }).invoke(paramCordovaPlugin.cordova, new Object[] { paramCordovaPlugin, Integer.valueOf(paramInt), paramArrayOfString });
      return;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      LOG.d("CordovaPermissionHelper", "No need to request permissions " + Arrays.toString(paramArrayOfString));
      deliverPermissionResult(paramCordovaPlugin, paramInt, paramArrayOfString);
      return;
    }
    catch (IllegalAccessException paramCordovaPlugin)
    {
      LOG.e("CordovaPermissionHelper", "IllegalAccessException when requesting permissions " + Arrays.toString(paramArrayOfString), paramCordovaPlugin);
      return;
    }
    catch (InvocationTargetException paramCordovaPlugin)
    {
      LOG.e("CordovaPermissionHelper", "invocationTargetException when requesting permissions " + Arrays.toString(paramArrayOfString), paramCordovaPlugin);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\file\PermissionHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */