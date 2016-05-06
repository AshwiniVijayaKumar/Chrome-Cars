package org.apache.cordova;

import android.app.Activity;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class NativeToJsMessageQueue
{
  static final boolean DISABLE_EXEC_CHAINING = false;
  private static final boolean FORCE_ENCODE_USING_EVAL = false;
  private static final String LOG_TAG = "JsMessageQueue";
  private static int MAX_PAYLOAD_SIZE = 524288000;
  private BridgeMode activeBridgeMode;
  private ArrayList<BridgeMode> bridgeModes = new ArrayList();
  private boolean paused;
  private final LinkedList<JsMessage> queue = new LinkedList();
  
  private int calculatePackedMessageLength(JsMessage paramJsMessage)
  {
    int i = paramJsMessage.calculateEncodedLength();
    return String.valueOf(i).length() + i + 1;
  }
  
  private void enqueueMessage(JsMessage paramJsMessage)
  {
    try
    {
      if (this.activeBridgeMode == null)
      {
        Log.d("JsMessageQueue", "Dropping Native->JS message due to disabled bridge");
        return;
      }
      this.queue.add(paramJsMessage);
      if (!this.paused) {
        this.activeBridgeMode.onNativeToJsMessageAvailable(this);
      }
      return;
    }
    finally {}
  }
  
  private void packMessage(JsMessage paramJsMessage, StringBuilder paramStringBuilder)
  {
    paramStringBuilder.append(paramJsMessage.calculateEncodedLength()).append(' ');
    paramJsMessage.encodeAsMessage(paramStringBuilder);
  }
  
  public void addBridgeMode(BridgeMode paramBridgeMode)
  {
    this.bridgeModes.add(paramBridgeMode);
  }
  
  public void addJavaScript(String paramString)
  {
    enqueueMessage(new JsMessage(paramString));
  }
  
  public void addPluginResult(PluginResult paramPluginResult, String paramString)
  {
    if (paramString == null)
    {
      Log.e("JsMessageQueue", "Got plugin result with no callbackId", new Throwable());
      return;
    }
    if (paramPluginResult.getStatus() == PluginResult.Status.NO_RESULT.ordinal()) {}
    for (int i = 1;; i = 0)
    {
      boolean bool = paramPluginResult.getKeepCallback();
      if ((i != 0) && (bool)) {
        break;
      }
      enqueueMessage(new JsMessage(paramPluginResult, paramString));
      return;
    }
  }
  
  public boolean isBridgeEnabled()
  {
    return this.activeBridgeMode != null;
  }
  
  public boolean isEmpty()
  {
    return this.queue.isEmpty();
  }
  
  public String popAndEncode(boolean paramBoolean)
  {
    try
    {
      if (this.activeBridgeMode == null) {
        return null;
      }
      this.activeBridgeMode.notifyOfFlush(this, paramBoolean);
      if (this.queue.isEmpty()) {
        return null;
      }
    }
    finally {}
    int j = 0;
    int i = 0;
    Object localObject2 = this.queue.iterator();
    for (;;)
    {
      if (!((Iterator)localObject2).hasNext())
      {
        localObject2 = new StringBuilder(j);
        j = 0;
      }
      int k;
      for (;;)
      {
        if (j >= i)
        {
          if (!this.queue.isEmpty()) {
            ((StringBuilder)localObject2).append('*');
          }
          localObject2 = ((StringBuilder)localObject2).toString();
          return (String)localObject2;
          k = calculatePackedMessageLength((JsMessage)((Iterator)localObject2).next());
          if ((i <= 0) || (j + k <= MAX_PAYLOAD_SIZE)) {
            break label175;
          }
          if (MAX_PAYLOAD_SIZE > 0) {
            break;
          }
          break label175;
        }
        packMessage((JsMessage)this.queue.removeFirst(), (StringBuilder)localObject2);
        j += 1;
      }
      label175:
      j += k;
      i += 1;
    }
  }
  
  public String popAndEncodeAsJs()
  {
    for (;;)
    {
      int j;
      Object localObject;
      try
      {
        if (this.queue.size() == 0) {
          return null;
        }
        i = 0;
        j = 0;
        localObject = this.queue.iterator();
        if (((Iterator)localObject).hasNext()) {
          break label118;
        }
        if (j != this.queue.size()) {
          break label251;
        }
        k = 1;
        if (k == 0) {
          break label256;
        }
        m = 0;
        localObject = new StringBuilder(m + i);
        i = 0;
        if (i < j) {
          break label157;
        }
        if (k != 0) {
          break label231;
        }
        ((StringBuilder)localObject).append("window.setTimeout(function(){cordova.require('cordova/plugin/android/polling').pollOnce();},0);");
      }
      finally {}
      if (i >= j)
      {
        localObject = ((StringBuilder)localObject).toString();
        return (String)localObject;
        label118:
        k = ((JsMessage)localStringBuilder.next()).calculateEncodedLength() + 50;
        if ((j > 0) && (i + k > MAX_PAYLOAD_SIZE))
        {
          if (MAX_PAYLOAD_SIZE > 0) {
            continue;
          }
          break label240;
          label157:
          JsMessage localJsMessage = (JsMessage)this.queue.removeFirst();
          if ((k != 0) && (i + 1 == j))
          {
            localJsMessage.encodeAsJsMessage(localStringBuilder);
            break label263;
          }
          localStringBuilder.append("try{");
          localJsMessage.encodeAsJsMessage(localStringBuilder);
          localStringBuilder.append("}finally{");
          break label263;
        }
      }
      else
      {
        localStringBuilder.append('}');
        i += 1;
        continue;
        label231:
        if (k == 0) {
          break label270;
        }
        i = 1;
        continue;
      }
      label240:
      i += k;
      j += 1;
      continue;
      label251:
      int k = 0;
      continue;
      label256:
      int m = 100;
      continue;
      label263:
      i += 1;
      continue;
      label270:
      int i = 0;
    }
  }
  
  public void reset()
  {
    try
    {
      this.queue.clear();
      setBridgeMode(-1);
      return;
    }
    finally {}
  }
  
  public void setBridgeMode(int paramInt)
  {
    if ((paramInt < -1) || (paramInt >= this.bridgeModes.size()))
    {
      Log.d("JsMessageQueue", "Invalid NativeToJsBridgeMode: " + paramInt);
      return;
    }
    BridgeMode localBridgeMode1;
    label45:
    StringBuilder localStringBuilder;
    if (paramInt < 0)
    {
      localBridgeMode1 = null;
      if (localBridgeMode1 == this.activeBridgeMode) {
        break label144;
      }
      localStringBuilder = new StringBuilder("Set native->JS mode to ");
      if (localBridgeMode1 != null) {
        break label146;
      }
    }
    BridgeMode localBridgeMode2;
    label144:
    label146:
    for (String str = "null";; str = localBridgeMode2.getClass().getSimpleName())
    {
      Log.d("JsMessageQueue", str);
      try
      {
        this.activeBridgeMode = localBridgeMode1;
        if (localBridgeMode1 != null)
        {
          localBridgeMode1.reset();
          if ((!this.paused) && (!this.queue.isEmpty())) {
            localBridgeMode1.onNativeToJsMessageAvailable(this);
          }
        }
        return;
      }
      finally {}
      localBridgeMode2 = (BridgeMode)this.bridgeModes.get(paramInt);
      break label45;
      break;
    }
  }
  
  public void setPaused(boolean paramBoolean)
  {
    if ((this.paused) && (paramBoolean)) {
      Log.e("JsMessageQueue", "nested call to setPaused detected.", new Throwable());
    }
    this.paused = paramBoolean;
    if (!paramBoolean) {
      try
      {
        if ((!this.queue.isEmpty()) && (this.activeBridgeMode != null)) {
          this.activeBridgeMode.onNativeToJsMessageAvailable(this);
        }
        return;
      }
      finally {}
    }
  }
  
  public static abstract class BridgeMode
  {
    public void notifyOfFlush(NativeToJsMessageQueue paramNativeToJsMessageQueue, boolean paramBoolean) {}
    
    public abstract void onNativeToJsMessageAvailable(NativeToJsMessageQueue paramNativeToJsMessageQueue);
    
    public void reset() {}
  }
  
  private static class JsMessage
  {
    final String jsPayloadOrCallbackId;
    final PluginResult pluginResult;
    
    JsMessage(String paramString)
    {
      if (paramString == null) {
        throw new NullPointerException();
      }
      this.jsPayloadOrCallbackId = paramString;
      this.pluginResult = null;
    }
    
    JsMessage(PluginResult paramPluginResult, String paramString)
    {
      if ((paramString == null) || (paramPluginResult == null)) {
        throw new NullPointerException();
      }
      this.jsPayloadOrCallbackId = paramString;
      this.pluginResult = paramPluginResult;
    }
    
    static int calculateEncodedLengthHelper(PluginResult paramPluginResult)
    {
      int k;
      switch (paramPluginResult.getMessageType())
      {
      case 2: 
      default: 
        k = paramPluginResult.getMessage().length();
        return k;
      case 4: 
      case 5: 
        return 1;
      case 3: 
        return paramPluginResult.getMessage().length() + 1;
      case 1: 
        return paramPluginResult.getStrMessage().length() + 1;
      case 7: 
        return paramPluginResult.getMessage().length() + 1;
      case 6: 
        return paramPluginResult.getMessage().length() + 1;
      }
      int i = 1;
      int j = 0;
      for (;;)
      {
        k = i;
        if (j >= paramPluginResult.getMultipartMessagesSize()) {
          break;
        }
        k = calculateEncodedLengthHelper(paramPluginResult.getMultipartMessage(j));
        i += String.valueOf(k).length() + 1 + k;
        j += 1;
      }
    }
    
    static void encodeAsMessageHelper(StringBuilder paramStringBuilder, PluginResult paramPluginResult)
    {
      switch (paramPluginResult.getMessageType())
      {
      case 2: 
      default: 
        paramStringBuilder.append(paramPluginResult.getMessage());
      }
      for (;;)
      {
        return;
        paramStringBuilder.append(paramPluginResult.getMessage().charAt(0));
        return;
        paramStringBuilder.append('N');
        return;
        paramStringBuilder.append('n').append(paramPluginResult.getMessage());
        return;
        paramStringBuilder.append('s');
        paramStringBuilder.append(paramPluginResult.getStrMessage());
        return;
        paramStringBuilder.append('S');
        paramStringBuilder.append(paramPluginResult.getMessage());
        return;
        paramStringBuilder.append('A');
        paramStringBuilder.append(paramPluginResult.getMessage());
        return;
        paramStringBuilder.append('M');
        int i = 0;
        while (i < paramPluginResult.getMultipartMessagesSize())
        {
          PluginResult localPluginResult = paramPluginResult.getMultipartMessage(i);
          paramStringBuilder.append(String.valueOf(calculateEncodedLengthHelper(localPluginResult)));
          paramStringBuilder.append(' ');
          encodeAsMessageHelper(paramStringBuilder, localPluginResult);
          i += 1;
        }
      }
    }
    
    int calculateEncodedLength()
    {
      if (this.pluginResult == null) {
        return this.jsPayloadOrCallbackId.length() + 1;
      }
      int i = String.valueOf(this.pluginResult.getStatus()).length();
      int j = this.jsPayloadOrCallbackId.length();
      return calculateEncodedLengthHelper(this.pluginResult) + (i + 2 + 1 + j + 1);
    }
    
    void encodeAsJsMessage(StringBuilder paramStringBuilder)
    {
      if (this.pluginResult == null)
      {
        paramStringBuilder.append(this.jsPayloadOrCallbackId);
        return;
      }
      int i = this.pluginResult.getStatus();
      boolean bool;
      if ((i != PluginResult.Status.OK.ordinal()) && (i != PluginResult.Status.NO_RESULT.ordinal()))
      {
        bool = false;
        paramStringBuilder.append("cordova.callbackFromNative('").append(this.jsPayloadOrCallbackId).append("',").append(bool).append(",").append(i).append(",[");
        switch (this.pluginResult.getMessageType())
        {
        default: 
          paramStringBuilder.append(this.pluginResult.getMessage());
        }
      }
      for (;;)
      {
        paramStringBuilder.append("],").append(this.pluginResult.getKeepCallback()).append(");");
        return;
        bool = true;
        break;
        paramStringBuilder.append("atob('").append(this.pluginResult.getMessage()).append("')");
        continue;
        paramStringBuilder.append("cordova.require('cordova/base64').toArrayBuffer('").append(this.pluginResult.getMessage()).append("')");
      }
    }
    
    void encodeAsMessage(StringBuilder paramStringBuilder)
    {
      if (this.pluginResult == null)
      {
        paramStringBuilder.append('J').append(this.jsPayloadOrCallbackId);
        return;
      }
      int k = this.pluginResult.getStatus();
      int i;
      int j;
      label58:
      label79:
      StringBuilder localStringBuilder;
      if (k == PluginResult.Status.NO_RESULT.ordinal())
      {
        i = 1;
        if (k != PluginResult.Status.OK.ordinal()) {
          break label137;
        }
        j = 1;
        boolean bool = this.pluginResult.getKeepCallback();
        if ((i == 0) && (j == 0)) {
          break label143;
        }
        c = 'S';
        localStringBuilder = paramStringBuilder.append(c);
        if (!bool) {
          break label149;
        }
      }
      label137:
      label143:
      label149:
      for (char c = '1';; c = '0')
      {
        localStringBuilder.append(c).append(k).append(' ').append(this.jsPayloadOrCallbackId).append(' ');
        encodeAsMessageHelper(paramStringBuilder, this.pluginResult);
        return;
        i = 0;
        break;
        j = 0;
        break label58;
        c = 'F';
        break label79;
      }
    }
  }
  
  public static class LoadUrlBridgeMode
    extends NativeToJsMessageQueue.BridgeMode
  {
    private final CordovaInterface cordova;
    private final CordovaWebViewEngine engine;
    
    public LoadUrlBridgeMode(CordovaWebViewEngine paramCordovaWebViewEngine, CordovaInterface paramCordovaInterface)
    {
      this.engine = paramCordovaWebViewEngine;
      this.cordova = paramCordovaInterface;
    }
    
    public void onNativeToJsMessageAvailable(final NativeToJsMessageQueue paramNativeToJsMessageQueue)
    {
      this.cordova.getActivity().runOnUiThread(new Runnable()
      {
        public void run()
        {
          String str = paramNativeToJsMessageQueue.popAndEncodeAsJs();
          if (str != null) {
            NativeToJsMessageQueue.LoadUrlBridgeMode.this.engine.loadUrl("javascript:" + str, false);
          }
        }
      });
    }
  }
  
  public static class NoOpBridgeMode
    extends NativeToJsMessageQueue.BridgeMode
  {
    public void onNativeToJsMessageAvailable(NativeToJsMessageQueue paramNativeToJsMessageQueue) {}
  }
  
  public static class OnlineEventsBridgeMode
    extends NativeToJsMessageQueue.BridgeMode
  {
    private final OnlineEventsBridgeModeDelegate delegate;
    private boolean ignoreNextFlush;
    private boolean online;
    
    public OnlineEventsBridgeMode(OnlineEventsBridgeModeDelegate paramOnlineEventsBridgeModeDelegate)
    {
      this.delegate = paramOnlineEventsBridgeModeDelegate;
    }
    
    public void notifyOfFlush(NativeToJsMessageQueue paramNativeToJsMessageQueue, boolean paramBoolean)
    {
      if ((paramBoolean) && (!this.ignoreNextFlush)) {
        if (!this.online) {
          break label26;
        }
      }
      label26:
      for (paramBoolean = false;; paramBoolean = true)
      {
        this.online = paramBoolean;
        return;
      }
    }
    
    public void onNativeToJsMessageAvailable(final NativeToJsMessageQueue paramNativeToJsMessageQueue)
    {
      this.delegate.runOnUiThread(new Runnable()
      {
        public void run()
        {
          if (!paramNativeToJsMessageQueue.isEmpty())
          {
            NativeToJsMessageQueue.OnlineEventsBridgeMode.this.ignoreNextFlush = false;
            NativeToJsMessageQueue.OnlineEventsBridgeMode.this.delegate.setNetworkAvailable(NativeToJsMessageQueue.OnlineEventsBridgeMode.this.online);
          }
        }
      });
    }
    
    public void reset()
    {
      this.delegate.runOnUiThread(new Runnable()
      {
        public void run()
        {
          NativeToJsMessageQueue.OnlineEventsBridgeMode.this.online = false;
          NativeToJsMessageQueue.OnlineEventsBridgeMode.this.ignoreNextFlush = true;
          NativeToJsMessageQueue.OnlineEventsBridgeMode.this.delegate.setNetworkAvailable(true);
        }
      });
    }
    
    public static abstract interface OnlineEventsBridgeModeDelegate
    {
      public abstract void runOnUiThread(Runnable paramRunnable);
      
      public abstract void setNetworkAvailable(boolean paramBoolean);
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\org\apache\cordova\NativeToJsMessageQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */