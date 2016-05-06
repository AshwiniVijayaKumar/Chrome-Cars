package com.ons.bellareader;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
public class EpubReaderMain
  extends Activity
{
  protected static ImageButton bookmarkButton;
  public static String bookurl;
  public static String currentpageurl;
  public static int currentx = 0;
  protected static ImageButton iconCloseView1;
  static int initialPagecount;
  protected static LinearLayout layout;
  protected static RelativeLayout layoutView1;
  protected static WebView view1;
  String activityName;
  String addCSSRule;
  private Animation animation1;
  private Animation animation2;
  private Animation animation3;
  private Animation animation4;
  String authorName;
  protected BookEnum bookSelector;
  TextView btn_minus;
  TextView btn_plus;
  int currentpageno = 1;
  int currentpagewidth = 0;
  ArrayList<Integer> currentpagewidthGlobalList = new ArrayList();
  private SimpleGestureFilter detector;
  private DownloadManager dm;
  private long enqueue;
  int fontsize = 15;
  String h1;
  String h2;
  String h3;
  String h4;
  String h5;
  String h6;
  int height;
  String insertRule1;
  String insertRule2;
  String insertRule3;
  String insertRule4;
  String insertRule5;
  private boolean isBackOfCardShowing = true;
  boolean lastChapterLoaded = false;
  LinearLayout ll_main;
  int m_downX;
  int m_downY;
  DatabaseHelperAdapter mydb;
  EpubNavigator navigator;
  boolean nightmodestatus = false;
  List<File> opfFiles = new ArrayList();
  String p_page_value;
  boolean prePage = false;
  boolean prePageNightMode = false;
  protected int screenHeight;
  protected int screenWidth;
  String setImageRule;
  String setSvgRule;
  boolean sizechange = false;
  String span;
  protected ViewStateEnum stateView1;
  protected String swipeLeftOrRight = "";
  protected float swipeOriginX;
  protected float swipeOriginY;
  String title;
  boolean tocstatus = false;
  ToggleButton togbtn;
  boolean togglestatus;
  ImageButton tv_toc;
  int val = 1;
  String varMySheet;
  int width;
  
  public static WebView getView1()
  {
    return view1;
  }
  
  public void errorMessage(String paramString)
  {
    Toast.makeText(getApplicationContext(), paramString, 0).show();
  }
  
  public void getBookName(File paramFile)
  {
    paramFile = paramFile.listFiles();
    int j = paramFile.length;
    int i = 0;
    if (i < j)
    {
      File localFile = paramFile[i];
      if (localFile.isDirectory()) {
        getBookName(localFile);
      }
      for (;;)
      {
        i += 1;
        break;
        if (localFile.getName().endsWith(".opf")) {
          this.opfFiles.add(localFile);
        }
      }
    }
  }
  
  public void injectJavascript()
  {
    view1.loadUrl(this.varMySheet);
    view1.loadUrl(this.addCSSRule);
    view1.loadUrl(this.insertRule2);
    view1.loadUrl(this.insertRule3);
    view1.loadUrl(this.insertRule4);
    view1.loadUrl(this.insertRule5);
    view1.loadUrl(this.setImageRule);
    view1.loadUrl(this.setSvgRule);
  }
  
  public void injectJavascript1()
  {
    view1.loadUrl("javascript:function initialize() {var d = document.getElementsByTagName('body')[0];d.style.color = 'white';}");
    view1.loadUrl("javascript:initialize()");
    view1.loadUrl(this.varMySheet);
    view1.loadUrl(this.addCSSRule);
    view1.loadUrl(this.insertRule2);
    view1.loadUrl(this.insertRule3);
    view1.loadUrl(this.insertRule4);
    view1.loadUrl(this.insertRule5);
    view1.loadUrl(this.setImageRule);
    view1.loadUrl(this.setSvgRule);
    view1.loadUrl(this.h1);
    view1.loadUrl(this.h2);
    view1.loadUrl(this.h3);
    view1.loadUrl(this.h4);
    view1.loadUrl(this.h5);
    view1.loadUrl(this.h6);
    view1.loadUrl(this.span);
  }
  
  public void injectJavascript2()
  {
    view1.loadUrl(this.varMySheet);
    view1.loadUrl(this.addCSSRule);
    view1.loadUrl(this.insertRule2);
    view1.loadUrl(this.insertRule3);
    view1.loadUrl(this.insertRule4);
    view1.loadUrl(this.insertRule5);
    view1.loadUrl(this.setImageRule);
    view1.loadUrl(this.setSvgRule);
  }
  
  public void injectJavascriptNew()
  {
    view1.loadUrl(this.varMySheet);
    view1.loadUrl(this.addCSSRule);
    view1.loadUrl(this.insertRule2);
    view1.loadUrl(this.insertRule3);
    view1.loadUrl(this.insertRule4);
    view1.loadUrl(this.insertRule5);
    view1.loadUrl(this.setImageRule);
    view1.loadUrl(this.setSvgRule);
  }
  
  public void injectJavascriptNew1()
  {
    System.out.println("injecting js injectJavascriptNew1>>>>>");
    view1.loadUrl("javascript:function initialize() {var d = document.getElementsByTagName('body')[0];d.style.color = 'white';d.style.background = 'black';}");
    view1.loadUrl("javascript:initialize()");
    view1.loadUrl(this.varMySheet);
    view1.loadUrl(this.addCSSRule);
    view1.loadUrl(this.insertRule2);
    view1.loadUrl(this.insertRule3);
    view1.loadUrl(this.insertRule4);
    view1.loadUrl(this.insertRule5);
    view1.loadUrl(this.setImageRule);
    view1.loadUrl(this.setSvgRule);
    view1.loadUrl("javascript:addCSSRule('h1', 'color: white;','white')");
    view1.loadUrl("javascript:addCSSRule('h2', 'color: white;','white')");
    view1.loadUrl("javascript:addCSSRule('h3', 'color: white;','white')");
    view1.loadUrl("javascript:addCSSRule('h4', 'color: white;','white')");
    view1.loadUrl("javascript:addCSSRule('h5', 'color: white;','white')");
    view1.loadUrl("javascript:addCSSRule('h6', 'color: white;','white')");
    view1.loadUrl("javascript:addCSSRule('span', 'color: white;','white')");
  }
  
  public void injectJavascriptNew3()
  {
    String str = "javascript:function initialize() {var d = document.getElementsByTagName('body')[0];if(" + this.togglestatus + "){" + "d.style.color = 'white';d.style.background = 'black';" + "}}";
    view1.loadUrl(str);
    view1.loadUrl("javascript:initialize()");
    view1.loadUrl(this.varMySheet);
    view1.loadUrl(this.addCSSRule);
    view1.loadUrl(this.insertRule2);
    view1.loadUrl(this.insertRule3);
    view1.loadUrl(this.insertRule4);
    view1.loadUrl(this.insertRule5);
    view1.loadUrl(this.setImageRule);
    view1.loadUrl(this.setSvgRule);
    if (this.togglestatus)
    {
      view1.loadUrl(this.h1);
      view1.loadUrl(this.h2);
      view1.loadUrl(this.h3);
      view1.loadUrl(this.h4);
      view1.loadUrl(this.h5);
      view1.loadUrl(this.h6);
      view1.loadUrl(this.span);
    }
  }
  
  protected void loadState(SharedPreferences paramSharedPreferences)
  {
    updateView1(ViewStateEnum.valueOf(paramSharedPreferences.getString("FirstState", ViewStateEnum.books.name())));
    if (!this.navigator.loadState(paramSharedPreferences)) {
      errorMessage("Cannot load the application/book state");
    }
  }
  
  @TargetApi(11)
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(7);
    setContentView(2130903043);
    getWindow().setFeatureInt(7, 2130903063);
    paramBundle = getIntent().getStringExtra("url");
    final String str = getIntent().getStringExtra("info");
    this.authorName = getIntent().getStringExtra("authorname");
    this.title = getIntent().getStringExtra("title");
    this.activityName = getIntent().getStringExtra("activityName");
    this.fontsize = getIntent().getExtras().getInt("fontsize");
    this.mydb = new DatabaseHelperAdapter(this);
    this.animation1 = AnimationUtils.loadAnimation(this, 2130968589);
    this.animation1.setDuration(1000L);
    this.animation2 = AnimationUtils.loadAnimation(this, 2130968580);
    this.animation2.setDuration(1000L);
    this.animation3 = AnimationUtils.loadAnimation(this, 2130968581);
    this.animation4 = AnimationUtils.loadAnimation(this, 2130968584);
    this.varMySheet = "javascript: var style=document.createElement('style');style.type = 'text/css';document.getElementsByTagName('head')[0].appendChild(style); var mySheet =  style.sheet;";
    this.addCSSRule = "javascript: function addCSSRule(selector, newRule,status) {if(status){var cssValue=selector + '{' + newRule + '}';style.appendChild(document.createTextNode(cssValue));}}";
    this.setImageRule = "javascript:addCSSRule('img', 'max-width:100%; max-height:100%;','imginsert')";
    this.insertRule2 = "javascript:addCSSRule('body', 'margin:5px 10px 0px 10px;')";
    this.insertRule3 = "javascript:addCSSRule('h2', 'margin:0px;')";
    this.insertRule4 = "javascript:addCSSRule('h1', 'margin:0px;')";
    this.insertRule5 = "javascript:addCSSRule('h3', 'margin:0px;')";
    this.h1 = "javascript:addCSSRule('h1', 'color: white;')";
    this.h2 = "javascript:addCSSRule('h2', 'color: white;')";
    this.h3 = "javascript:addCSSRule('h3', 'color: white;')";
    this.h4 = "javascript:addCSSRule('h4', 'color: white;')";
    this.h5 = "javascript:addCSSRule('h5', 'color: white;')";
    this.h6 = "javascript:addCSSRule('h6', 'color: white;')";
    this.span = "javascript:addCSSRule('span', 'color: white;');console.log('krishna>>>>>>>>>>>>>>>>>>>>>>>>>>>epub'+document.getElementsByTagName('head')[0].innerHTML);";
    this.ll_main = ((LinearLayout)findViewById(2131558435));
    this.togbtn = ((ToggleButton)findViewById(2131558438));
    this.togbtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        EpubReaderMain.this.nightmodestatus = true;
        if (paramAnonymousBoolean)
        {
          EpubReaderMain.this.togglestatus = true;
          EpubReaderMain.this.prePageNightMode = true;
          EpubReaderMain.view1.setBackgroundColor(-16777216);
          EpubReaderMain.this.ll_main.setBackgroundColor(-16777216);
          EpubReaderMain.this.updateView1(EpubReaderMain.this.navigator.setView1(EpubReaderMain.currentpageurl));
          return;
        }
        EpubReaderMain.this.togglestatus = false;
        EpubReaderMain.this.prePageNightMode = false;
        EpubReaderMain.view1.setBackgroundColor(EpubReaderMain.this.getResources().getColor(2131361829));
        EpubReaderMain.this.ll_main.setBackgroundColor(EpubReaderMain.this.getResources().getColor(2131361829));
        EpubReaderMain.this.updateView1(EpubReaderMain.this.navigator.setView1(EpubReaderMain.currentpageurl));
      }
    });
    this.btn_plus = ((TextView)findViewById(2131558436));
    this.btn_minus = ((TextView)findViewById(2131558437));
    this.btn_plus.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (EpubReaderMain.this.fontsize <= 30)
        {
          EpubReaderMain.this.sizechange = true;
          EpubReaderMain.this.fontsize += 5;
          EpubReaderMain.view1.getSettings().setDefaultFontSize(EpubReaderMain.this.fontsize);
          EpubReaderMain.this.updateView1(EpubReaderMain.this.navigator.setView1(EpubReaderMain.currentpageurl));
        }
      }
    });
    this.btn_minus.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (EpubReaderMain.this.fontsize > 15)
        {
          EpubReaderMain.this.sizechange = true;
          EpubReaderMain.this.fontsize -= 5;
          EpubReaderMain.view1.getSettings().setDefaultFontSize(EpubReaderMain.this.fontsize);
          EpubReaderMain.this.updateView1(EpubReaderMain.this.navigator.setView1(EpubReaderMain.currentpageurl));
        }
      }
    });
    this.tv_toc = ((ImageButton)findViewById(2131558501));
    this.tv_toc.setVisibility(0);
    this.tv_toc.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramAnonymousView = new Intent(EpubReaderMain.this, TOC.class);
        paramAnonymousView.putExtra("dirName", EpubReaderMain.this.title);
        paramAnonymousView.putExtra("currentPageUrl", EpubReaderMain.currentpageurl.substring(0, EpubReaderMain.currentpageurl.lastIndexOf('/') + 1));
        paramAnonymousView.putExtra("infoValue", str);
        paramAnonymousView.putExtra("authorName", EpubReaderMain.this.authorName);
        paramAnonymousView.putExtra("currentpageno", EpubReaderMain.this.currentpageno);
        paramAnonymousView.putExtra("bookurl", EpubReaderMain.bookurl);
        paramAnonymousView.putExtra("currentx", EpubReaderMain.view1.getScrollY());
        paramAnonymousView.putExtra("fontsize", EpubReaderMain.this.fontsize);
        paramAnonymousView.putExtra("currentpageurl1", EpubReaderMain.currentpageurl);
        EpubReaderMain.this.startActivity(paramAnonymousView);
      }
    });
    view1 = (WebView)findViewById(2131558439);
    view1.setBackgroundColor(Color.parseColor("#FAEBD7"));
    layout = (LinearLayout)findViewById(2131558435);
    iconCloseView1 = (ImageButton)findViewById(2131558452);
    bookmarkButton = (ImageButton)findViewById(2131558502);
    bookmarkButton.setVisibility(0);
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    this.screenWidth = localDisplayMetrics.widthPixels;
    this.screenHeight = localDisplayMetrics.heightPixels;
    this.navigator = new EpubNavigator();
    view1.getSettings().setJavaScriptEnabled(true);
    view1.getSettings().setBuiltInZoomControls(false);
    view1.getSettings().setDisplayZoomControls(false);
    view1.getSettings().setDefaultFontSize(this.fontsize);
    if ((this.activityName.equals("Bookmark")) || (this.activityName.equals("toc"))) {
      this.navigator.openBook1(bookurl, str, paramBundle);
    }
    for (;;)
    {
      updateView1(ViewStateEnum.books);
      view1.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          EpubReaderMain.this.swipePage(paramAnonymousView, paramAnonymousMotionEvent, BookEnum.first);
          return ((WebView)paramAnonymousView).onTouchEvent(paramAnonymousMotionEvent);
        }
      });
      iconCloseView1.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          EpubReaderMain.this.saveBookStatus();
        }
      });
      bookmarkButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          EpubNavigator.getWebViewUrl();
          paramAnonymousView = new AlertDialog.Builder(EpubReaderMain.this);
          LinearLayout localLinearLayout = new LinearLayout(EpubReaderMain.this);
          localLinearLayout.setOrientation(0);
          final EditText localEditText = new EditText(EpubReaderMain.this);
          localEditText.setLayoutParams(new LinearLayout.LayoutParams(-1, -1, 1.0F));
          localEditText.setHint("Name");
          localLinearLayout.addView(localEditText);
          paramAnonymousView.setView(localLinearLayout);
          paramAnonymousView.setPositiveButton("Ok", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              if (localEditText.getText().toString().equals(""))
              {
                Toast.makeText(EpubReaderMain.this.getApplicationContext(), "Please enter bookmark.", 0).show();
                return;
              }
              paramAnonymous2DialogInterface = "";
              paramAnonymous2Int = 0;
              while (paramAnonymous2Int < EpubReaderMain.this.currentpagewidthGlobalList.size())
              {
                str = paramAnonymous2DialogInterface + String.valueOf(EpubReaderMain.this.currentpagewidthGlobalList.get(paramAnonymous2Int));
                paramAnonymous2DialogInterface = str;
                if (paramAnonymous2Int < EpubReaderMain.this.currentpagewidthGlobalList.size() - 1) {
                  paramAnonymous2DialogInterface = str + ",";
                }
                paramAnonymous2Int += 1;
              }
              EpubReaderMain.this.getBookName(new File(Environment.getExternalStorageDirectory() + "/epubtemp/" + EpubReaderMain.this.title));
              String str = EpubReaderMain.this.parseXml((File)EpubReaderMain.this.opfFiles.get(0));
              if (EpubReaderMain.this.mydb.addValues(localEditText.getText().toString(), EpubReaderMain.currentpageurl, EpubReaderMain.this.currentpageno, EpubReaderMain.this.authorName, EpubReaderMain.this.title, EpubReaderMain.bookurl, EpubReaderMain.view1.getScrollY(), paramAnonymous2DialogInterface, str, EpubReaderMain.this.fontsize) == true)
              {
                Toast.makeText(EpubReaderMain.this.getApplicationContext(), "Bookmark already exist.", 1500).show();
                return;
              }
              Toast.makeText(EpubReaderMain.this.getApplicationContext(), "Bookmark saved.", 1500).show();
            }
          });
          paramAnonymousView.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              paramAnonymous2DialogInterface.cancel();
            }
          });
          paramAnonymousView.show();
        }
      });
      view1.setWebViewClient(new WebViewClient()
      {
        @SuppressLint({"NewApi"})
        public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          super.onPageFinished(paramAnonymousWebView, paramAnonymousString);
          EpubReaderMain.currentpageurl = paramAnonymousString;
          if (EpubReaderMain.this.activityName.equalsIgnoreCase("Bookmark"))
          {
            EpubReaderMain.this.currentpagewidth = EpubReaderMain.this.getIntent().getExtras().getInt("xvalue");
            EpubReaderMain.this.currentpageno = EpubReaderMain.this.getIntent().getExtras().getInt("pageno");
            EpubReaderMain.this.p_page_value = EpubReaderMain.this.getIntent().getExtras().getString("p_page_value");
            if (EpubReaderMain.this.p_page_value.equals(""))
            {
              EpubReaderMain.this.activityName = "bookmark_change";
              EpubReaderMain.this.scrollView(EpubReaderMain.this.currentpagewidth);
              EpubReaderMain.this.injectJavascript2();
            }
          }
          do
          {
            return;
            paramAnonymousWebView = EpubReaderMain.this.p_page_value.split(",");
            EpubReaderMain.this.currentpagewidthGlobalList.clear();
            int i = 0;
            while (i < paramAnonymousWebView.length)
            {
              EpubReaderMain.this.currentpagewidthGlobalList.add(Integer.valueOf(Integer.parseInt(paramAnonymousWebView[i])));
              i += 1;
            }
            break;
            if (EpubReaderMain.this.activityName.equalsIgnoreCase("toc"))
            {
              EpubReaderMain.this.activityName = "bookmark_change";
              EpubReaderMain.this.injectJavascript2();
              return;
            }
            if (EpubReaderMain.this.nightmodestatus == true)
            {
              if (EpubReaderMain.this.togglestatus == true)
              {
                EpubReaderMain.this.injectJavascriptNew1();
                return;
              }
              EpubReaderMain.this.injectJavascriptNew();
              return;
            }
            if (EpubReaderMain.this.sizechange != true) {
              break label372;
            }
            if (EpubReaderMain.this.togglestatus == true)
            {
              EpubReaderMain.view1.setBackgroundColor(-16777216);
              EpubReaderMain.this.ll_main.setBackgroundColor(-16777216);
              EpubReaderMain.this.injectJavascriptNew3();
            }
          } while (EpubReaderMain.this.togglestatus);
          EpubReaderMain.view1.setBackgroundColor(EpubReaderMain.this.getResources().getColor(2131361829));
          EpubReaderMain.this.ll_main.setBackgroundColor(EpubReaderMain.this.getResources().getColor(2131361829));
          EpubReaderMain.this.injectJavascriptNew3();
          return;
          label372:
          if (EpubReaderMain.this.togglestatus == true)
          {
            EpubReaderMain.this.injectJavascript1();
            return;
          }
          EpubReaderMain.this.injectJavascript();
        }
        
        public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
        {
          try
          {
            EpubReaderMain.this.updateView1(EpubReaderMain.this.navigator.setView1(paramAnonymousString));
            return true;
          }
          catch (Exception paramAnonymousWebView)
          {
            for (;;)
            {
              EpubReaderMain.this.errorMessage("Cannot load page");
            }
          }
        }
      });
      return;
      this.navigator.openBook1(bookurl, str, "");
    }
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      saveBookStatus();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  protected void onPause()
  {
    super.onPause();
    SharedPreferences.Editor localEditor = getPreferences(0).edit();
    saveState(localEditor);
    localEditor.commit();
  }
  
  public String parseXml(File paramFile)
  {
    localObject2 = "";
    Object localObject1 = localObject2;
    try
    {
      paramFile = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(paramFile);
      localObject1 = localObject2;
      paramFile.getDocumentElement().normalize();
      localObject1 = localObject2;
      NodeList localNodeList = paramFile.getElementsByTagName("metadata");
      int i = 0;
      for (paramFile = (File)localObject2;; paramFile = (File)localObject2)
      {
        localObject1 = paramFile;
        localObject2 = paramFile;
        if (i >= localNodeList.getLength()) {
          break;
        }
        localObject1 = paramFile;
        Node localNode = localNodeList.item(i);
        localObject2 = paramFile;
        localObject1 = paramFile;
        if (localNode.getNodeType() == 1)
        {
          localObject1 = paramFile;
          localObject2 = ((Element)localNode).getElementsByTagName("dc:title").item(0).getTextContent();
        }
        i += 1;
      }
      return (String)localObject2;
    }
    catch (Exception paramFile)
    {
      paramFile.printStackTrace();
      localObject2 = localObject1;
    }
  }
  
  public void refreshLanguages(BookEnum paramBookEnum, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramBookEnum == BookEnum.second) {}
  }
  
  public void saveBookStatus()
  {
    this.mydb.addBookStatus(currentpageurl, this.currentpageno, this.authorName, this.title, bookurl, view1.getScrollY(), this.fontsize);
    finish();
  }
  
  protected void saveState(SharedPreferences.Editor paramEditor)
  {
    paramEditor.putString("FirstState", this.stateView1.name());
    this.navigator.saveState(paramEditor);
  }
  
  public void scrollView(final int paramInt)
  {
    view1.postDelayed(new Runnable()
    {
      public void run()
      {
        EpubReaderMain.view1.scrollTo(0, paramInt);
      }
    }, 300L);
  }
  
  protected void swipePage(View paramView, MotionEvent paramMotionEvent, BookEnum paramBookEnum)
  {
    switch (MotionEventCompat.getActionMasked(paramMotionEvent))
    {
    }
    int i;
    float f1;
    float f3;
    float f2;
    do
    {
      return;
      this.swipeOriginX = paramMotionEvent.getX();
      this.swipeOriginY = paramMotionEvent.getY();
      return;
      i = (int)(this.screenWidth * 0.25D);
      f1 = this.swipeOriginX - paramMotionEvent.getX();
      f3 = this.swipeOriginY;
      float f4 = paramMotionEvent.getY();
      f2 = Math.abs(f1);
      f3 = Math.abs(f3 - f4);
      if ((f1 > i) && (f2 > f3)) {
        try
        {
          this.swipeLeftOrRight = "right";
          this.navigator.goToNextChapter(BookEnum.first);
          view1.startAnimation(this.animation3);
          return;
        }
        catch (Exception paramView)
        {
          errorMessage("Cannot turn page!");
          return;
        }
      }
    } while ((f1 >= -i) || (f2 <= f3));
    try
    {
      this.swipeLeftOrRight = "left";
      this.navigator.goToPrevChapter(BookEnum.first);
      view1.setAnimation(this.animation4);
      view1.startAnimation(this.animation4);
      return;
    }
    catch (Exception paramView)
    {
      errorMessage("Cannot turn page!");
    }
  }
  
  protected void updateView1(ViewStateEnum paramViewStateEnum)
  {
    this.stateView1 = paramViewStateEnum;
    if (this.stateView1 == ViewStateEnum.invisible)
    {
      this.stateView1 = ViewStateEnum.books;
      finish();
    }
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\ons\bellareader\EpubReaderMain.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */