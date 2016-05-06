package com.google.zxing.client.android.book;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.zxing.client.android.R.id;
import java.util.Locale;

public final class SearchBookContentsListItem
  extends LinearLayout
{
  private TextView pageNumberView;
  private TextView snippetView;
  
  SearchBookContentsListItem(Context paramContext)
  {
    super(paramContext);
  }
  
  public SearchBookContentsListItem(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.pageNumberView = ((TextView)findViewById(R.id.page_number_view));
    this.snippetView = ((TextView)findViewById(R.id.snippet_view));
  }
  
  public void set(SearchBookContentsResult paramSearchBookContentsResult)
  {
    this.pageNumberView.setText(paramSearchBookContentsResult.getPageNumber());
    Object localObject = paramSearchBookContentsResult.getSnippet();
    if (((String)localObject).length() > 0)
    {
      if (paramSearchBookContentsResult.getValidSnippet())
      {
        paramSearchBookContentsResult = SearchBookContentsResult.getQuery().toLowerCase(Locale.getDefault());
        String str = ((String)localObject).toLowerCase(Locale.getDefault());
        localObject = new SpannableString((CharSequence)localObject);
        StyleSpan localStyleSpan = new StyleSpan(1);
        int j = paramSearchBookContentsResult.length();
        int i = 0;
        for (;;)
        {
          i = str.indexOf(paramSearchBookContentsResult, i);
          if (i < 0)
          {
            this.snippetView.setText((CharSequence)localObject);
            return;
          }
          ((Spannable)localObject).setSpan(localStyleSpan, i, i + j, 0);
          i += j;
        }
      }
      this.snippetView.setText((CharSequence)localObject);
      return;
    }
    this.snippetView.setText("");
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\book\SearchBookContentsListItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */