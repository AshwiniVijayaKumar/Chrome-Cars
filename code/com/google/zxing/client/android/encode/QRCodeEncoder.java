package com.google.zxing.client.android.encode;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Log;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.client.android.Contents;
import com.google.zxing.client.android.R.string;
import com.google.zxing.client.result.AddressBookParsedResult;
import com.google.zxing.client.result.ResultParser;
import com.google.zxing.common.BitMatrix;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

final class QRCodeEncoder
{
  private static final int BLACK = -16777216;
  private static final String TAG = QRCodeEncoder.class.getSimpleName();
  private static final int WHITE = -1;
  private final Activity activity;
  private String contents;
  private final int dimension;
  private String displayContents;
  private BarcodeFormat format;
  private String title;
  private final boolean useVCard;
  
  QRCodeEncoder(Activity paramActivity, Intent paramIntent, int paramInt, boolean paramBoolean)
    throws WriterException
  {
    this.activity = paramActivity;
    this.dimension = paramInt;
    this.useVCard = paramBoolean;
    paramIntent.getAction();
    encodeContentsFromZXingIntent(paramIntent);
  }
  
  private void encodeContentsFromShareIntent(Intent paramIntent)
    throws WriterException
  {
    if (paramIntent.hasExtra("android.intent.extra.STREAM"))
    {
      encodeFromStreamExtra(paramIntent);
      return;
    }
    encodeFromTextExtras(paramIntent);
  }
  
  private boolean encodeContentsFromZXingIntent(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("ENCODE_FORMAT");
    this.format = null;
    if (str != null) {}
    try
    {
      this.format = BarcodeFormat.valueOf(str);
      if ((this.format == null) || (this.format == BarcodeFormat.QR_CODE))
      {
        str = paramIntent.getStringExtra("ENCODE_TYPE");
        if ((str != null) && (str.length() != 0)) {}
      }
      for (;;)
      {
        return false;
        this.format = BarcodeFormat.QR_CODE;
        encodeQRCodeContents(paramIntent, str);
        while ((this.contents != null) && (this.contents.length() > 0))
        {
          return true;
          paramIntent = paramIntent.getStringExtra("ENCODE_DATA");
          if ((paramIntent != null) && (paramIntent.length() > 0))
          {
            this.contents = paramIntent;
            this.displayContents = paramIntent;
            this.title = this.activity.getString(R.string.contents_text);
          }
        }
      }
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
  }
  
  private void encodeFromStreamExtra(Intent paramIntent)
    throws WriterException
  {
    this.format = BarcodeFormat.QR_CODE;
    paramIntent = paramIntent.getExtras();
    if (paramIntent == null) {
      throw new WriterException("No extras");
    }
    paramIntent = (Uri)paramIntent.getParcelable("android.intent.extra.STREAM");
    if (paramIntent == null) {
      throw new WriterException("No EXTRA_STREAM");
    }
    try
    {
      paramIntent = this.activity.getContentResolver().openInputStream(paramIntent);
      Object localObject = new ByteArrayOutputStream();
      byte[] arrayOfByte = new byte['ࠀ'];
      for (;;)
      {
        int i = paramIntent.read(arrayOfByte);
        if (i <= 0)
        {
          paramIntent = ((ByteArrayOutputStream)localObject).toByteArray();
          localObject = new String(paramIntent, 0, paramIntent.length, "UTF-8");
          Log.d(TAG, "Encoding share intent content:");
          Log.d(TAG, (String)localObject);
          paramIntent = ResultParser.parseResult(new Result((String)localObject, paramIntent, null, BarcodeFormat.QR_CODE));
          if ((paramIntent instanceof AddressBookParsedResult)) {
            break;
          }
          throw new WriterException("Result was not an address");
        }
        ((ByteArrayOutputStream)localObject).write(arrayOfByte, 0, i);
      }
      encodeQRCodeContents((AddressBookParsedResult)paramIntent);
    }
    catch (IOException paramIntent)
    {
      throw new WriterException(paramIntent);
    }
    if ((this.contents == null) || (this.contents.length() == 0)) {
      throw new WriterException("No content to encode");
    }
  }
  
  private void encodeFromTextExtras(Intent paramIntent)
    throws WriterException
  {
    String str = ContactEncoder.trim(paramIntent.getStringExtra("android.intent.extra.TEXT"));
    Object localObject = str;
    if (str == null)
    {
      str = ContactEncoder.trim(paramIntent.getStringExtra("android.intent.extra.HTML_TEXT"));
      localObject = str;
      if (str == null)
      {
        str = ContactEncoder.trim(paramIntent.getStringExtra("android.intent.extra.SUBJECT"));
        localObject = str;
        if (str == null)
        {
          localObject = paramIntent.getStringArrayExtra("android.intent.extra.EMAIL");
          if (localObject == null) {
            break label87;
          }
        }
      }
    }
    label87:
    for (localObject = ContactEncoder.trim(localObject[0]); (localObject == null) || (((String)localObject).length() == 0); localObject = "?") {
      throw new WriterException("Empty EXTRA_TEXT");
    }
    this.contents = ((String)localObject);
    this.format = BarcodeFormat.QR_CODE;
    if (paramIntent.hasExtra("android.intent.extra.SUBJECT")) {
      this.displayContents = paramIntent.getStringExtra("android.intent.extra.SUBJECT");
    }
    for (;;)
    {
      this.title = this.activity.getString(R.string.contents_text);
      return;
      if (paramIntent.hasExtra("android.intent.extra.TITLE")) {
        this.displayContents = paramIntent.getStringExtra("android.intent.extra.TITLE");
      } else {
        this.displayContents = this.contents;
      }
    }
  }
  
  private void encodeQRCodeContents(Intent paramIntent, String paramString)
  {
    if (paramString.equals("TEXT_TYPE"))
    {
      paramIntent = paramIntent.getStringExtra("ENCODE_DATA");
      if ((paramIntent != null) && (paramIntent.length() > 0))
      {
        this.contents = paramIntent;
        this.displayContents = paramIntent;
        this.title = this.activity.getString(R.string.contents_text);
      }
    }
    label312:
    label337:
    label468:
    label495:
    float f1;
    float f2;
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return;
                  if (!paramString.equals("EMAIL_TYPE")) {
                    break;
                  }
                  paramIntent = ContactEncoder.trim(paramIntent.getStringExtra("ENCODE_DATA"));
                } while (paramIntent == null);
                this.contents = ("mailto:" + paramIntent);
                this.displayContents = paramIntent;
                this.title = this.activity.getString(R.string.contents_email);
                return;
                if (!paramString.equals("PHONE_TYPE")) {
                  break;
                }
                paramIntent = ContactEncoder.trim(paramIntent.getStringExtra("ENCODE_DATA"));
              } while (paramIntent == null);
              this.contents = ("tel:" + paramIntent);
              this.displayContents = PhoneNumberUtils.formatNumber(paramIntent);
              this.title = this.activity.getString(R.string.contents_phone);
              return;
              if (!paramString.equals("SMS_TYPE")) {
                break;
              }
              paramIntent = ContactEncoder.trim(paramIntent.getStringExtra("ENCODE_DATA"));
            } while (paramIntent == null);
            this.contents = ("sms:" + paramIntent);
            this.displayContents = PhoneNumberUtils.formatNumber(paramIntent);
            this.title = this.activity.getString(R.string.contents_sms);
            return;
            if (!paramString.equals("CONTACT_TYPE")) {
              break;
            }
            paramIntent = paramIntent.getBundleExtra("ENCODE_DATA");
          } while (paramIntent == null);
          paramString = paramIntent.getString("name");
          String str1 = paramIntent.getString("company");
          String str2 = paramIntent.getString("postal");
          ArrayList localArrayList1 = new ArrayList(Contents.PHONE_KEYS.length);
          int i = 0;
          ArrayList localArrayList2;
          String str3;
          String str4;
          if (i >= Contents.PHONE_KEYS.length)
          {
            localArrayList2 = new ArrayList(Contents.EMAIL_KEYS.length);
            i = 0;
            if (i < Contents.EMAIL_KEYS.length) {
              break label468;
            }
            str3 = paramIntent.getString("URL_KEY");
            str4 = paramIntent.getString("NOTE_KEY");
            if (!this.useVCard) {
              break label495;
            }
          }
          for (paramIntent = new VCardContactEncoder();; paramIntent = new MECARDContactEncoder())
          {
            paramIntent = paramIntent.encode(Collections.singleton(paramString), str1, Collections.singleton(str2), localArrayList1, localArrayList2, str3, str4);
            if (paramIntent[1].length() <= 0) {
              break;
            }
            this.contents = paramIntent[0];
            this.displayContents = paramIntent[1];
            this.title = this.activity.getString(R.string.contents_contact);
            return;
            localArrayList1.add(paramIntent.getString(Contents.PHONE_KEYS[i]));
            i += 1;
            break label312;
            localArrayList2.add(paramIntent.getString(Contents.EMAIL_KEYS[i]));
            i += 1;
            break label337;
          }
        } while (!paramString.equals("LOCATION_TYPE"));
        paramIntent = paramIntent.getBundleExtra("ENCODE_DATA");
      } while (paramIntent == null);
      f1 = paramIntent.getFloat("LAT", Float.MAX_VALUE);
      f2 = paramIntent.getFloat("LONG", Float.MAX_VALUE);
    } while ((f1 == Float.MAX_VALUE) || (f2 == Float.MAX_VALUE));
    this.contents = ("geo:" + f1 + ',' + f2);
    this.displayContents = (f1 + "," + f2);
    this.title = this.activity.getString(R.string.contents_location);
  }
  
  private void encodeQRCodeContents(AddressBookParsedResult paramAddressBookParsedResult)
  {
    if (this.useVCard) {}
    for (Object localObject = new VCardContactEncoder();; localObject = new MECARDContactEncoder())
    {
      paramAddressBookParsedResult = ((ContactEncoder)localObject).encode(toIterable(paramAddressBookParsedResult.getNames()), paramAddressBookParsedResult.getOrg(), toIterable(paramAddressBookParsedResult.getAddresses()), toIterable(paramAddressBookParsedResult.getPhoneNumbers()), toIterable(paramAddressBookParsedResult.getEmails()), paramAddressBookParsedResult.getURL(), null);
      if (paramAddressBookParsedResult[1].length() > 0)
      {
        this.contents = paramAddressBookParsedResult[0];
        this.displayContents = paramAddressBookParsedResult[1];
        this.title = this.activity.getString(R.string.contents_contact);
      }
      return;
    }
  }
  
  private static String guessAppropriateEncoding(CharSequence paramCharSequence)
  {
    int i = 0;
    for (;;)
    {
      if (i >= paramCharSequence.length()) {
        return null;
      }
      if (paramCharSequence.charAt(i) > 'ÿ') {
        return "UTF-8";
      }
      i += 1;
    }
  }
  
  private static Iterable<String> toIterable(String[] paramArrayOfString)
  {
    if (paramArrayOfString == null) {
      return null;
    }
    return Arrays.asList(paramArrayOfString);
  }
  
  Bitmap encodeAsBitmap()
    throws WriterException
  {
    Object localObject2 = this.contents;
    if (localObject2 == null) {
      return null;
    }
    Object localObject1 = null;
    Object localObject3 = guessAppropriateEncoding((CharSequence)localObject2);
    if (localObject3 != null)
    {
      localObject1 = new EnumMap(EncodeHintType.class);
      ((Map)localObject1).put(EncodeHintType.CHARACTER_SET, localObject3);
    }
    localObject3 = new MultiFormatWriter();
    int m;
    int i;
    int j;
    for (;;)
    {
      try
      {
        localObject2 = ((MultiFormatWriter)localObject3).encode((String)localObject2, this.format, this.dimension, this.dimension, (Map)localObject1);
        m = ((BitMatrix)localObject2).getWidth();
        int n = ((BitMatrix)localObject2).getHeight();
        localObject1 = new int[m * n];
        i = 0;
        if (i >= n)
        {
          localObject2 = Bitmap.createBitmap(m, n, Bitmap.Config.ARGB_8888);
          ((Bitmap)localObject2).setPixels((int[])localObject1, 0, m, 0, 0, m, n);
          return (Bitmap)localObject2;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        return null;
      }
      j = 0;
      if (j < m) {
        break;
      }
      i += 1;
    }
    if (((BitMatrix)localObject2).get(j, i)) {}
    for (int k = -16777216;; k = -1)
    {
      localIllegalArgumentException[(i * m + j)] = k;
      j += 1;
      break;
    }
  }
  
  String getContents()
  {
    return this.contents;
  }
  
  String getDisplayContents()
  {
    return this.displayContents;
  }
  
  String getTitle()
  {
    return this.title;
  }
  
  boolean isUseVCard()
  {
    return this.useVCard;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\zxing\client\android\encode\QRCodeEncoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */