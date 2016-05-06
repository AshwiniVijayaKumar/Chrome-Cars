package com.phonegap.plugin;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnKeyListener;
import android.os.Build.VERSION;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TimePicker;
import java.lang.reflect.Field;
import java.util.Calendar;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
public class DatePickerPlugin
  extends CordovaPlugin
{
  private static final String ACTION_DATE = "date";
  private static final String ACTION_TIME = "time";
  private final String pluginName = "DatePickerPlugin";
  
  public boolean execute(String paramString, JSONArray paramJSONArray, CallbackContext paramCallbackContext)
  {
    Log.d("DatePickerPlugin", "DatePicker called with options: " + paramJSONArray);
    show(paramJSONArray, paramCallbackContext);
    return true;
  }
  
  public void show(JSONArray paramJSONArray, final CallbackContext paramCallbackContext)
  {
    for (;;)
    {
      final Activity localActivity;
      long l2;
      final long l3;
      Object localObject1;
      final int n;
      long l1;
      final int m;
      final int j;
      try
      {
        localActivity = this.cordova.getActivity();
        Calendar localCalendar = Calendar.getInstance();
        Object localObject2 = "date";
        l2 = 0L;
        l3 = 0L;
        int i2 = -1;
        int i1 = -1;
        int i4 = -1;
        int i3 = -1;
        int i5 = -1;
        localObject1 = localObject2;
        n = i1;
        final int k = i3;
        final int i = i5;
        l1 = l2;
        m = i2;
        j = i4;
        try
        {
          JSONObject localJSONObject = paramJSONArray.getJSONObject(0);
          localObject1 = localObject2;
          n = i1;
          k = i3;
          i = i5;
          l1 = l2;
          m = i2;
          j = i4;
          paramJSONArray = localJSONObject.getString("mode");
          localObject1 = paramJSONArray;
          n = i1;
          k = i3;
          i = i5;
          l1 = l2;
          m = i2;
          j = i4;
          localObject2 = localJSONObject.getString("date").split("/");
          localObject1 = paramJSONArray;
          n = i1;
          k = i3;
          i = i5;
          l1 = l2;
          m = i2;
          j = i4;
          i2 = Integer.parseInt(localObject2[0]);
          localObject1 = paramJSONArray;
          n = i1;
          k = i3;
          i = i5;
          l1 = l2;
          m = i2;
          j = i4;
          i1 = Integer.parseInt(localObject2[1]);
          localObject1 = paramJSONArray;
          n = i1;
          k = i3;
          i = i5;
          l1 = l2;
          m = i2;
          j = i4;
          i4 = Integer.parseInt(localObject2[2]);
          localObject1 = paramJSONArray;
          n = i1;
          k = i3;
          i = i5;
          l1 = l2;
          m = i2;
          j = i4;
          i3 = Integer.parseInt(localObject2[3]);
          localObject1 = paramJSONArray;
          n = i1;
          k = i3;
          i = i5;
          l1 = l2;
          m = i2;
          j = i4;
          i5 = Integer.parseInt(localObject2[4]);
          localObject1 = paramJSONArray;
          n = i1;
          k = i3;
          i = i5;
          l1 = l2;
          m = i2;
          j = i4;
          l2 = localJSONObject.getLong("minDate");
          localObject1 = paramJSONArray;
          n = i1;
          k = i3;
          i = i5;
          l1 = l2;
          m = i2;
          j = i4;
          long l4 = localJSONObject.getLong("maxDate");
          l1 = l4;
          j = i4;
          m = i2;
          l3 = l2;
          l2 = l1;
          i = i5;
          k = i3;
          n = i1;
        }
        catch (JSONException paramJSONArray)
        {
          paramJSONArray.printStackTrace();
          paramJSONArray = (JSONArray)localObject1;
          l2 = l3;
          l3 = l1;
          continue;
        }
        if (j == -1)
        {
          j = localCalendar.get(1);
          if (m != -1) {
            break label566;
          }
          m = localCalendar.get(2);
          if (n != -1) {
            break label575;
          }
          n = localCalendar.get(5);
          if (k != -1) {
            break label578;
          }
          k = localCalendar.get(11);
          if (i != -1) {
            break label581;
          }
          i = localCalendar.get(12);
          if (!"time".equalsIgnoreCase(paramJSONArray)) {
            break label584;
          }
          paramJSONArray = new Runnable()
          {
            public void run()
            {
              Object localObject = new DatePickerPlugin.TimeSetListener(DatePickerPlugin.this, jdField_this, paramCallbackContext, null);
              localObject = new TimePickerDialog(localActivity, (TimePickerDialog.OnTimeSetListener)localObject, k, i, true);
              if (Build.VERSION.SDK_INT >= 11)
              {
                ((TimePickerDialog)localObject).setCancelable(true);
                ((TimePickerDialog)localObject).setCanceledOnTouchOutside(false);
                ((TimePickerDialog)localObject).setButton(-2, "Cancel", new DialogInterface.OnClickListener()
                {
                  public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                  {
                    DatePickerPlugin.1.this.val$callbackContext.success("cancel");
                  }
                });
                ((TimePickerDialog)localObject).setOnKeyListener(new DialogInterface.OnKeyListener()
                {
                  public boolean onKey(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int, KeyEvent paramAnonymous2KeyEvent)
                  {
                    return false;
                  }
                });
              }
              ((TimePickerDialog)localObject).show();
            }
          };
          this.cordova.getActivity().runOnUiThread(paramJSONArray);
          return;
        }
      }
      finally {}
      continue;
      label566:
      m -= 1;
      continue;
      label575:
      continue;
      label578:
      continue;
      label581:
      continue;
      label584:
      if ("date".equalsIgnoreCase(paramJSONArray)) {
        paramJSONArray = new Runnable()
        {
          public void run()
          {
            Object localObject1 = new DatePickerPlugin.DateSetListener(DatePickerPlugin.this, jdField_this, paramCallbackContext, null);
            DatePickerDialog localDatePickerDialog = new DatePickerDialog(localActivity, (DatePickerDialog.OnDateSetListener)localObject1, j, m, n);
            if (Build.VERSION.SDK_INT >= 11)
            {
              localObject1 = localDatePickerDialog.getDatePicker();
              if (l3 > 0L) {
                ((DatePicker)localObject1).setMinDate(l3);
              }
              if ((this.val$maxDate > 0L) && (this.val$maxDate > l3)) {
                ((DatePicker)localObject1).setMaxDate(this.val$maxDate);
              }
              localDatePickerDialog.setCancelable(true);
              localDatePickerDialog.setCanceledOnTouchOutside(false);
              localDatePickerDialog.setButton(-2, "Cancel", new DialogInterface.OnClickListener()
              {
                public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
                {
                  DatePickerPlugin.2.this.val$callbackContext.success("cancel");
                }
              });
              localDatePickerDialog.setOnKeyListener(new DialogInterface.OnKeyListener()
              {
                public boolean onKey(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int, KeyEvent paramAnonymous2KeyEvent)
                {
                  return false;
                }
              });
            }
            for (;;)
            {
              localDatePickerDialog.show();
              return;
              localObject1 = null;
              try
              {
                localObject4 = localDatePickerDialog.getClass().getDeclaredField("mDatePicker");
                localObject1 = localObject4;
                ((Field)localObject1).setAccessible(true);
                localObject4 = null;
              }
              catch (NoSuchFieldException localNoSuchFieldException)
              {
                try
                {
                  localObject1 = (DatePicker)((Field)localObject1).get(localDatePickerDialog);
                  Object localObject4 = Calendar.getInstance();
                  ((Calendar)localObject4).setTimeInMillis(l3);
                  Calendar localCalendar = Calendar.getInstance();
                  localCalendar.setTimeInMillis(this.val$maxDate);
                  final int i = ((Calendar)localObject4).get(1);
                  final int j = ((Calendar)localObject4).get(2);
                  final int k = ((Calendar)localObject4).get(5);
                  final int m = localCalendar.get(1);
                  final int n = localCalendar.get(2);
                  final int i1 = localCalendar.get(5);
                  if ((localObject4 == null) && (localCalendar == null)) {
                    continue;
                  }
                  ((DatePicker)localObject1).init(j, m, n, new DatePicker.OnDateChangedListener()
                  {
                    public void onDateChanged(DatePicker paramAnonymous2DatePicker, int paramAnonymous2Int1, int paramAnonymous2Int2, int paramAnonymous2Int3)
                    {
                      if ((DatePickerPlugin.2.this.val$maxDate > 0L) && (DatePickerPlugin.2.this.val$maxDate > DatePickerPlugin.2.this.val$minDate) && ((paramAnonymous2Int1 > m) || ((paramAnonymous2Int2 > n) && (paramAnonymous2Int1 == m)) || ((paramAnonymous2Int3 > i1) && (paramAnonymous2Int1 == m) && (paramAnonymous2Int2 == n)))) {
                        paramAnonymous2DatePicker.updateDate(m, n, i1);
                      }
                      if ((DatePickerPlugin.2.this.val$minDate > 0L) && ((paramAnonymous2Int1 < i) || ((paramAnonymous2Int2 < j) && (paramAnonymous2Int1 == i)) || ((paramAnonymous2Int3 < k) && (paramAnonymous2Int1 == i) && (paramAnonymous2Int2 == j)))) {
                        paramAnonymous2DatePicker.updateDate(i, j, k);
                      }
                    }
                  });
                  continue;
                  localNoSuchFieldException = localNoSuchFieldException;
                  localNoSuchFieldException.printStackTrace();
                }
                catch (IllegalArgumentException localIllegalArgumentException)
                {
                  for (;;)
                  {
                    localIllegalArgumentException.printStackTrace();
                    Object localObject2 = localNoSuchFieldException;
                  }
                }
                catch (IllegalAccessException localIllegalAccessException)
                {
                  for (;;)
                  {
                    localIllegalAccessException.printStackTrace();
                    Object localObject3 = localNoSuchFieldException;
                  }
                }
              }
            }
          }
        };
      } else {
        Log.d("DatePickerPlugin", "Unknown action. Only 'date' or 'time' are valid actions");
      }
    }
  }
  
  private final class DateSetListener
    implements DatePickerDialog.OnDateSetListener
  {
    private final CallbackContext callbackContext;
    private final DatePickerPlugin datePickerPlugin;
    
    private DateSetListener(DatePickerPlugin paramDatePickerPlugin, CallbackContext paramCallbackContext)
    {
      this.datePickerPlugin = paramDatePickerPlugin;
      this.callbackContext = paramCallbackContext;
    }
    
    public void onDateSet(DatePicker paramDatePicker, int paramInt1, int paramInt2, int paramInt3)
    {
      paramDatePicker = paramInt1 + "/" + (paramInt2 + 1) + "/" + paramInt3;
      this.callbackContext.success(paramDatePicker);
    }
  }
  
  private final class TimeSetListener
    implements TimePickerDialog.OnTimeSetListener
  {
    private final CallbackContext callbackContext;
    private final DatePickerPlugin datePickerPlugin;
    
    private TimeSetListener(DatePickerPlugin paramDatePickerPlugin, CallbackContext paramCallbackContext)
    {
      this.datePickerPlugin = paramDatePickerPlugin;
      this.callbackContext = paramCallbackContext;
    }
    
    public void onTimeSet(TimePicker paramTimePicker, int paramInt1, int paramInt2)
    {
      paramTimePicker = "";
      Calendar localCalendar = Calendar.getInstance();
      localCalendar.set(11, paramInt1);
      localCalendar.set(12, paramInt2);
      if (localCalendar.get(9) == 0)
      {
        paramTimePicker = "AM";
        if (localCalendar.get(10) != 0) {
          break label117;
        }
      }
      label117:
      for (String str = "12";; str = localCalendar.get(10) + "")
      {
        paramTimePicker = str + ":" + localCalendar.get(12) + " " + paramTimePicker;
        this.callbackContext.success(paramTimePicker);
        return;
        if (localCalendar.get(9) != 1) {
          break;
        }
        paramTimePicker = "PM";
        break;
      }
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\phonegap\plugin\DatePickerPlugin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */