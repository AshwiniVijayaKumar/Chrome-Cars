package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.a.c;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.GamesMetadata.LoadGamesResult;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players.LoadPlayersResult;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult;
import com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.leaderboard.c;
import com.google.android.gms.games.leaderboard.d;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.ParticipantUtils;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests.LoadRequestsResult;
import com.google.android.gms.games.request.Requests.UpdateRequestsResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public final class fx
  extends eh<gb>
  implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
{
  private boolean GA = false;
  private boolean GB = false;
  private int GC;
  private final Binder GD;
  private final long GE;
  private final boolean GF;
  private final int GG;
  private final boolean GH;
  private final String Gv;
  private final Map<String, RealTimeSocket> Gw;
  private PlayerEntity Gx;
  private GameEntity Gy;
  private final gd Gz;
  private final String vi;
  
  public fx(Context paramContext, Looper paramLooper, String paramString1, String paramString2, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, String[] paramArrayOfString, int paramInt1, View paramView, boolean paramBoolean1, boolean paramBoolean2, int paramInt2, boolean paramBoolean3, int paramInt3)
  {
    super(paramContext, paramLooper, paramConnectionCallbacks, paramOnConnectionFailedListener, paramArrayOfString);
    this.Gv = paramString1;
    this.vi = ((String)er.f(paramString2));
    this.GD = new Binder();
    this.Gw = new HashMap();
    this.Gz = gd.a(this, paramInt1);
    e(paramView);
    this.GB = paramBoolean2;
    this.GC = paramInt2;
    this.GE = hashCode();
    this.GF = paramBoolean1;
    this.GH = paramBoolean3;
    this.GG = paramInt3;
    registerConnectionCallbacks(this);
    registerConnectionFailedListener(this);
  }
  
  private Room G(DataHolder paramDataHolder)
  {
    com.google.android.gms.games.multiplayer.realtime.a locala = new com.google.android.gms.games.multiplayer.realtime.a(paramDataHolder);
    paramDataHolder = null;
    try
    {
      if (locala.getCount() > 0) {
        paramDataHolder = (Room)((Room)locala.get(0)).freeze();
      }
      return paramDataHolder;
    }
    finally
    {
      locala.close();
    }
  }
  
  /* Error */
  private RealTimeSocket aw(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 345	com/google/android/gms/internal/fx:eb	()Landroid/os/IInterface;
    //   4: checkcast 347	com/google/android/gms/internal/gb
    //   7: aload_1
    //   8: invokeinterface 351 2 0
    //   13: astore_2
    //   14: aload_2
    //   15: ifnull +35 -> 50
    //   18: ldc_w 353
    //   21: ldc_w 355
    //   24: invokestatic 360	com/google/android/gms/internal/fz:f	(Ljava/lang/String;Ljava/lang/String;)V
    //   27: new 362	com/google/android/gms/internal/gc
    //   30: dup
    //   31: aload_2
    //   32: invokespecial 365	com/google/android/gms/internal/gc:<init>	(Landroid/os/ParcelFileDescriptor;)V
    //   35: astore_2
    //   36: aload_0
    //   37: getfield 275	com/google/android/gms/internal/fx:Gw	Ljava/util/Map;
    //   40: aload_1
    //   41: aload_2
    //   42: invokeinterface 371 3 0
    //   47: pop
    //   48: aload_2
    //   49: areturn
    //   50: ldc_w 353
    //   53: ldc_w 373
    //   56: invokestatic 360	com/google/android/gms/internal/fz:f	(Ljava/lang/String;Ljava/lang/String;)V
    //   59: aload_0
    //   60: invokevirtual 345	com/google/android/gms/internal/fx:eb	()Landroid/os/IInterface;
    //   63: checkcast 347	com/google/android/gms/internal/gb
    //   66: aload_1
    //   67: invokeinterface 376 2 0
    //   72: astore_2
    //   73: aload_2
    //   74: ifnonnull +5 -> 79
    //   77: aconst_null
    //   78: areturn
    //   79: new 378	android/net/LocalSocket
    //   82: dup
    //   83: invokespecial 379	android/net/LocalSocket:<init>	()V
    //   86: astore_3
    //   87: aload_3
    //   88: new 381	android/net/LocalSocketAddress
    //   91: dup
    //   92: aload_2
    //   93: invokespecial 384	android/net/LocalSocketAddress:<init>	(Ljava/lang/String;)V
    //   96: invokevirtual 388	android/net/LocalSocket:connect	(Landroid/net/LocalSocketAddress;)V
    //   99: new 390	com/google/android/gms/internal/ge
    //   102: dup
    //   103: aload_3
    //   104: aload_1
    //   105: invokespecial 393	com/google/android/gms/internal/ge:<init>	(Landroid/net/LocalSocket;Ljava/lang/String;)V
    //   108: astore_2
    //   109: aload_0
    //   110: getfield 275	com/google/android/gms/internal/fx:Gw	Ljava/util/Map;
    //   113: aload_1
    //   114: aload_2
    //   115: invokeinterface 371 3 0
    //   120: pop
    //   121: aload_2
    //   122: areturn
    //   123: astore_1
    //   124: ldc_w 353
    //   127: ldc_w 395
    //   130: invokestatic 397	com/google/android/gms/internal/fz:h	(Ljava/lang/String;Ljava/lang/String;)V
    //   133: aconst_null
    //   134: areturn
    //   135: astore_1
    //   136: ldc_w 353
    //   139: new 399	java/lang/StringBuilder
    //   142: dup
    //   143: invokespecial 400	java/lang/StringBuilder:<init>	()V
    //   146: ldc_w 402
    //   149: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: aload_1
    //   153: invokevirtual 410	java/io/IOException:getMessage	()Ljava/lang/String;
    //   156: invokevirtual 406	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: invokevirtual 413	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   162: invokestatic 397	com/google/android/gms/internal/fz:h	(Ljava/lang/String;Ljava/lang/String;)V
    //   165: aconst_null
    //   166: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	167	0	this	fx
    //   0	167	1	paramString	String
    //   13	109	2	localObject	Object
    //   86	18	3	localLocalSocket	android.net.LocalSocket
    // Exception table:
    //   from	to	target	type
    //   0	14	123	android/os/RemoteException
    //   18	48	123	android/os/RemoteException
    //   50	73	123	android/os/RemoteException
    //   79	87	123	android/os/RemoteException
    //   87	99	123	android/os/RemoteException
    //   99	121	123	android/os/RemoteException
    //   136	165	123	android/os/RemoteException
    //   87	99	135	java/io/IOException
  }
  
  private void fG()
  {
    Iterator localIterator = this.Gw.values().iterator();
    while (localIterator.hasNext())
    {
      RealTimeSocket localRealTimeSocket = (RealTimeSocket)localIterator.next();
      try
      {
        localRealTimeSocket.close();
      }
      catch (IOException localIOException)
      {
        fz.a("GamesClientImpl", "IOException:", localIOException);
      }
    }
    this.Gw.clear();
  }
  
  private void fm()
  {
    this.Gx = null;
  }
  
  protected gb H(IBinder paramIBinder)
  {
    return gb.a.J(paramIBinder);
  }
  
  public int a(RealTimeMultiplayer.ReliableMessageSentCallback paramReliableMessageSentCallback, byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    try
    {
      int i = ((gb)eb()).a(new an(paramReliableMessageSentCallback), paramArrayOfByte, paramString1, paramString2);
      return i;
    }
    catch (RemoteException paramReliableMessageSentCallback)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return -1;
  }
  
  public int a(byte[] paramArrayOfByte, String paramString, String[] paramArrayOfString)
  {
    er.b(paramArrayOfString, "Participant IDs must not be null");
    try
    {
      int i = ((gb)eb()).b(paramArrayOfByte, paramString, paramArrayOfString);
      return i;
    }
    catch (RemoteException paramArrayOfByte)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return -1;
  }
  
  public Intent a(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    try
    {
      Intent localIntent = ((gb)eb()).a(paramInt1, paramInt2, paramBoolean);
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public Intent a(int paramInt1, byte[] paramArrayOfByte, int paramInt2, Bitmap paramBitmap, String paramString)
  {
    try
    {
      paramArrayOfByte = ((gb)eb()).a(paramInt1, paramArrayOfByte, paramInt2, paramString);
      er.b(paramBitmap, "Must provide a non null icon");
      paramArrayOfByte.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", paramBitmap);
      return paramArrayOfByte;
    }
    catch (RemoteException paramArrayOfByte)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public Intent a(Room paramRoom, int paramInt)
  {
    try
    {
      paramRoom = ((gb)eb()).a((RoomEntity)paramRoom.freeze(), paramInt);
      return paramRoom;
    }
    catch (RemoteException paramRoom)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  protected void a(int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    if ((paramInt == 0) && (paramBundle != null)) {
      this.GA = paramBundle.getBoolean("show_welcome_popup");
    }
    super.a(paramInt, paramIBinder, paramBundle);
  }
  
  public void a(IBinder paramIBinder, Bundle paramBundle)
  {
    if (isConnected()) {}
    try
    {
      ((gb)eb()).a(paramIBinder, paramBundle);
      return;
    }
    catch (RemoteException paramIBinder)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<Requests.LoadRequestsResult> paramc, int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      ((gb)eb()).a(new ar(paramc), paramInt1, paramInt2, paramInt3);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<Players.LoadPlayersResult> paramc, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    try
    {
      ((gb)eb()).a(new ak(paramc), paramInt, paramBoolean1, paramBoolean2);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<TurnBasedMultiplayer.LoadMatchesResult> paramc, int paramInt, int[] paramArrayOfInt)
  {
    try
    {
      ((gb)eb()).a(new bq(paramc), paramInt, paramArrayOfInt);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<Leaderboards.LoadScoresResult> paramc, LeaderboardScoreBuffer paramLeaderboardScoreBuffer, int paramInt1, int paramInt2)
  {
    try
    {
      ((gb)eb()).a(new r(paramc), paramLeaderboardScoreBuffer.fX().fY(), paramInt1, paramInt2);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<TurnBasedMultiplayer.InitiateMatchResult> paramc, TurnBasedMatchConfig paramTurnBasedMatchConfig)
  {
    try
    {
      ((gb)eb()).a(new bi(paramc), paramTurnBasedMatchConfig.getVariant(), paramTurnBasedMatchConfig.getMinPlayers(), paramTurnBasedMatchConfig.getInvitedPlayerIds(), paramTurnBasedMatchConfig.getAutoMatchCriteria());
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<Players.LoadPlayersResult> paramc, String paramString)
  {
    try
    {
      ((gb)eb()).a(new ak(paramc), paramString);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<Achievements.UpdateAchievementResult> paramc, String paramString, int paramInt)
  {
    if (paramc == null) {}
    for (paramc = null;; paramc = new d(paramc)) {
      try
      {
        ((gb)eb()).a(paramc, paramString, paramInt, this.Gz.fP(), this.Gz.fO());
        return;
      }
      catch (RemoteException paramc)
      {
        fz.g("GamesClientImpl", "service died");
      }
    }
  }
  
  public void a(a.c<Leaderboards.LoadScoresResult> paramc, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    try
    {
      ((gb)eb()).a(new r(paramc), paramString, paramInt1, paramInt2, paramInt3, paramBoolean);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<Players.LoadPlayersResult> paramc, String paramString, int paramInt, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (!paramString.equals("playedWith")) {
      throw new IllegalArgumentException("Invalid player collection: " + paramString);
    }
    try
    {
      ((gb)eb()).d(new ak(paramc), paramString, paramInt, paramBoolean1, paramBoolean2);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<Leaderboards.SubmitScoreResult> paramc, String paramString1, long paramLong, String paramString2)
  {
    if (paramc == null) {}
    for (paramc = null;; paramc = new bd(paramc)) {
      try
      {
        ((gb)eb()).a(paramc, paramString1, paramLong, paramString2);
        return;
      }
      catch (RemoteException paramc)
      {
        fz.g("GamesClientImpl", "service died");
      }
    }
  }
  
  public void a(a.c<TurnBasedMultiplayer.LeaveMatchResult> paramc, String paramString1, String paramString2)
  {
    try
    {
      ((gb)eb()).c(new bk(paramc), paramString1, paramString2);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<Leaderboards.LoadPlayerScoreResult> paramc, String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    try
    {
      ((gb)eb()).a(new ai(paramc), paramString1, paramString2, paramInt1, paramInt2);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<Leaderboards.LeaderboardMetadataResult> paramc, String paramString, boolean paramBoolean)
  {
    try
    {
      ((gb)eb()).c(new t(paramc), paramString, paramBoolean);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<TurnBasedMultiplayer.UpdateMatchResult> paramc, String paramString1, byte[] paramArrayOfByte, String paramString2, ParticipantResult[] paramArrayOfParticipantResult)
  {
    try
    {
      ((gb)eb()).a(new bo(paramc), paramString1, paramArrayOfByte, paramString2, paramArrayOfParticipantResult);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<TurnBasedMultiplayer.UpdateMatchResult> paramc, String paramString, byte[] paramArrayOfByte, ParticipantResult[] paramArrayOfParticipantResult)
  {
    try
    {
      ((gb)eb()).a(new bo(paramc), paramString, paramArrayOfByte, paramArrayOfParticipantResult);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<Players.LoadPlayersResult> paramc, boolean paramBoolean)
  {
    try
    {
      ((gb)eb()).c(new ak(paramc), paramBoolean);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(a.c<Requests.UpdateRequestsResult> paramc, String[] paramArrayOfString)
  {
    try
    {
      ((gb)eb()).a(new at(paramc), paramArrayOfString);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(OnInvitationReceivedListener paramOnInvitationReceivedListener)
  {
    try
    {
      paramOnInvitationReceivedListener = new l(paramOnInvitationReceivedListener);
      ((gb)eb()).a(paramOnInvitationReceivedListener, this.GE);
      return;
    }
    catch (RemoteException paramOnInvitationReceivedListener)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(RoomConfig paramRoomConfig)
  {
    try
    {
      ax localax = new ax(paramRoomConfig.getRoomUpdateListener(), paramRoomConfig.getRoomStatusUpdateListener(), paramRoomConfig.getMessageReceivedListener());
      ((gb)eb()).a(localax, this.GD, paramRoomConfig.getVariant(), paramRoomConfig.getInvitedPlayerIds(), paramRoomConfig.getAutoMatchCriteria(), paramRoomConfig.isSocketEnabled(), this.GE);
      return;
    }
    catch (RemoteException paramRoomConfig)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(RoomUpdateListener paramRoomUpdateListener, String paramString)
  {
    try
    {
      ((gb)eb()).c(new ax(paramRoomUpdateListener), paramString);
      fG();
      return;
    }
    catch (RemoteException paramRoomUpdateListener)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(OnTurnBasedMatchUpdateReceivedListener paramOnTurnBasedMatchUpdateReceivedListener)
  {
    try
    {
      paramOnTurnBasedMatchUpdateReceivedListener = new x(paramOnTurnBasedMatchUpdateReceivedListener);
      ((gb)eb()).b(paramOnTurnBasedMatchUpdateReceivedListener, this.GE);
      return;
    }
    catch (RemoteException paramOnTurnBasedMatchUpdateReceivedListener)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void a(OnRequestReceivedListener paramOnRequestReceivedListener)
  {
    try
    {
      paramOnRequestReceivedListener = new ao(paramOnRequestReceivedListener);
      ((gb)eb()).c(paramOnRequestReceivedListener, this.GE);
      return;
    }
    catch (RemoteException paramOnRequestReceivedListener)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  protected void a(en paramen, eh.e parame)
    throws RemoteException
  {
    String str = getContext().getResources().getConfiguration().locale.toString();
    Bundle localBundle = new Bundle();
    localBundle.putBoolean("com.google.android.gms.games.key.isHeadless", this.GF);
    localBundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.GB);
    localBundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.GC);
    localBundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", this.GH);
    localBundle.putInt("com.google.android.gms.games.key.sdkVariant", this.GG);
    paramen.a(parame, 4323000, getContext().getPackageName(), this.vi, ea(), this.Gv, this.Gz.fP(), str, localBundle);
  }
  
  protected String aF()
  {
    return "com.google.android.gms.games.service.START";
  }
  
  protected String aG()
  {
    return "com.google.android.gms.games.internal.IGamesService";
  }
  
  public void aT(int paramInt)
  {
    this.Gz.setGravity(paramInt);
  }
  
  public void aU(int paramInt)
  {
    try
    {
      ((gb)eb()).aU(paramInt);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public Intent au(String paramString)
  {
    try
    {
      paramString = ((gb)eb()).au(paramString);
      return paramString;
    }
    catch (RemoteException paramString)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public void av(String paramString)
  {
    try
    {
      ((gb)eb()).aC(paramString);
      return;
    }
    catch (RemoteException paramString)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public Intent b(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    try
    {
      Intent localIntent = ((gb)eb()).b(paramInt1, paramInt2, paramBoolean);
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public void b(a.c<Status> paramc)
  {
    try
    {
      ((gb)eb()).a(new bb(paramc));
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void b(a.c<Achievements.UpdateAchievementResult> paramc, String paramString)
  {
    if (paramc == null) {}
    for (paramc = null;; paramc = new d(paramc)) {
      try
      {
        ((gb)eb()).a(paramc, paramString, this.Gz.fP(), this.Gz.fO());
        return;
      }
      catch (RemoteException paramc)
      {
        fz.g("GamesClientImpl", "service died");
      }
    }
  }
  
  public void b(a.c<Achievements.UpdateAchievementResult> paramc, String paramString, int paramInt)
  {
    if (paramc == null) {}
    for (paramc = null;; paramc = new d(paramc)) {
      try
      {
        ((gb)eb()).b(paramc, paramString, paramInt, this.Gz.fP(), this.Gz.fO());
        return;
      }
      catch (RemoteException paramc)
      {
        fz.g("GamesClientImpl", "service died");
      }
    }
  }
  
  public void b(a.c<Leaderboards.LoadScoresResult> paramc, String paramString, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    try
    {
      ((gb)eb()).b(new r(paramc), paramString, paramInt1, paramInt2, paramInt3, paramBoolean);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void b(a.c<Leaderboards.LeaderboardMetadataResult> paramc, boolean paramBoolean)
  {
    try
    {
      ((gb)eb()).b(new t(paramc), paramBoolean);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void b(a.c<Requests.UpdateRequestsResult> paramc, String[] paramArrayOfString)
  {
    try
    {
      ((gb)eb()).b(new at(paramc), paramArrayOfString);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void b(RoomConfig paramRoomConfig)
  {
    try
    {
      ax localax = new ax(paramRoomConfig.getRoomUpdateListener(), paramRoomConfig.getRoomStatusUpdateListener(), paramRoomConfig.getMessageReceivedListener());
      ((gb)eb()).a(localax, this.GD, paramRoomConfig.getInvitationId(), paramRoomConfig.isSocketEnabled(), this.GE);
      return;
    }
    catch (RemoteException paramRoomConfig)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  protected void b(String... paramVarArgs)
  {
    int i = 0;
    int j = 0;
    boolean bool1 = false;
    if (i < paramVarArgs.length)
    {
      String str = paramVarArgs[i];
      boolean bool2;
      if (str.equals("https://www.googleapis.com/auth/games")) {
        bool2 = true;
      }
      for (;;)
      {
        i += 1;
        bool1 = bool2;
        break;
        bool2 = bool1;
        if (str.equals("https://www.googleapis.com/auth/games.firstparty"))
        {
          j = 1;
          bool2 = bool1;
        }
      }
    }
    if (j != 0)
    {
      if (!bool1) {}
      for (bool1 = true;; bool1 = false)
      {
        er.a(bool1, String.format("Cannot have both %s and %s!", new Object[] { "https://www.googleapis.com/auth/games", "https://www.googleapis.com/auth/games.firstparty" }));
        return;
      }
    }
    er.a(bool1, String.format("Games APIs requires %s to function.", new Object[] { "https://www.googleapis.com/auth/games" }));
  }
  
  public void c(a.c<Invitations.LoadInvitationsResult> paramc, int paramInt)
  {
    try
    {
      ((gb)eb()).a(new o(paramc), paramInt);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void c(a.c<Achievements.UpdateAchievementResult> paramc, String paramString)
  {
    if (paramc == null) {}
    for (paramc = null;; paramc = new d(paramc)) {
      try
      {
        ((gb)eb()).b(paramc, paramString, this.Gz.fP(), this.Gz.fO());
        return;
      }
      catch (RemoteException paramc)
      {
        fz.g("GamesClientImpl", "service died");
      }
    }
  }
  
  public void c(a.c<Achievements.LoadAchievementsResult> paramc, boolean paramBoolean)
  {
    try
    {
      ((gb)eb()).a(new f(paramc), paramBoolean);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public Bundle cY()
  {
    try
    {
      Bundle localBundle = ((gb)eb()).cY();
      if (localBundle != null) {
        localBundle.setClassLoader(fx.class.getClassLoader());
      }
      return localBundle;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public void connect()
  {
    fm();
    super.connect();
  }
  
  public int d(byte[] paramArrayOfByte, String paramString)
  {
    try
    {
      int i = ((gb)eb()).b(paramArrayOfByte, paramString, null);
      return i;
    }
    catch (RemoteException paramArrayOfByte)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return -1;
  }
  
  public void d(a.c<TurnBasedMultiplayer.InitiateMatchResult> paramc, String paramString)
  {
    try
    {
      ((gb)eb()).l(new bi(paramc), paramString);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void disconnect()
  {
    this.GA = false;
    if (isConnected()) {}
    try
    {
      gb localgb = (gb)eb();
      localgb.fH();
      localgb.n(this.GE);
      fG();
      super.disconnect();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        fz.g("GamesClientImpl", "Failed to notify client disconnect.");
      }
    }
  }
  
  public void e(View paramView)
  {
    this.Gz.f(paramView);
  }
  
  public void e(a.c<TurnBasedMultiplayer.InitiateMatchResult> paramc, String paramString)
  {
    try
    {
      ((gb)eb()).m(new bi(paramc), paramString);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void f(a.c<TurnBasedMultiplayer.LeaveMatchResult> paramc, String paramString)
  {
    try
    {
      ((gb)eb()).o(new bk(paramc), paramString);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public int fA()
  {
    try
    {
      int i = ((gb)eb()).fA();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return 4368;
  }
  
  public String fB()
  {
    try
    {
      String str = ((gb)eb()).fB();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public int fC()
  {
    try
    {
      int i = ((gb)eb()).fC();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return 2;
  }
  
  public Intent fD()
  {
    try
    {
      Intent localIntent = ((gb)eb()).fD();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public int fE()
  {
    try
    {
      int i = ((gb)eb()).fE();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return 2;
  }
  
  public int fF()
  {
    try
    {
      int i = ((gb)eb()).fF();
      return i;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return 2;
  }
  
  public void fH()
  {
    if (isConnected()) {}
    try
    {
      ((gb)eb()).fH();
      return;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public String fn()
  {
    try
    {
      String str = ((gb)eb()).fn();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public String fo()
  {
    try
    {
      String str = ((gb)eb()).fo();
      return str;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  /* Error */
  public com.google.android.gms.games.Player fp()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 911	com/google/android/gms/internal/fx:bm	()V
    //   4: aload_0
    //   5: monitorenter
    //   6: aload_0
    //   7: getfield 447	com/google/android/gms/internal/fx:Gx	Lcom/google/android/gms/games/PlayerEntity;
    //   10: astore_1
    //   11: aload_1
    //   12: ifnonnull +51 -> 63
    //   15: new 913	com/google/android/gms/games/PlayerBuffer
    //   18: dup
    //   19: aload_0
    //   20: invokevirtual 345	com/google/android/gms/internal/fx:eb	()Landroid/os/IInterface;
    //   23: checkcast 347	com/google/android/gms/internal/gb
    //   26: invokeinterface 917 1 0
    //   31: invokespecial 918	com/google/android/gms/games/PlayerBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   34: astore_1
    //   35: aload_1
    //   36: invokevirtual 919	com/google/android/gms/games/PlayerBuffer:getCount	()I
    //   39: ifle +20 -> 59
    //   42: aload_0
    //   43: aload_1
    //   44: iconst_0
    //   45: invokevirtual 922	com/google/android/gms/games/PlayerBuffer:get	(I)Lcom/google/android/gms/games/Player;
    //   48: invokeinterface 925 1 0
    //   53: checkcast 927	com/google/android/gms/games/PlayerEntity
    //   56: putfield 447	com/google/android/gms/internal/fx:Gx	Lcom/google/android/gms/games/PlayerEntity;
    //   59: aload_1
    //   60: invokevirtual 928	com/google/android/gms/games/PlayerBuffer:close	()V
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_0
    //   66: getfield 447	com/google/android/gms/internal/fx:Gx	Lcom/google/android/gms/games/PlayerEntity;
    //   69: areturn
    //   70: astore_2
    //   71: aload_1
    //   72: invokevirtual 928	com/google/android/gms/games/PlayerBuffer:close	()V
    //   75: aload_2
    //   76: athrow
    //   77: astore_1
    //   78: ldc_w 353
    //   81: ldc_w 462
    //   84: invokestatic 464	com/google/android/gms/internal/fz:g	(Ljava/lang/String;Ljava/lang/String;)V
    //   87: goto -24 -> 63
    //   90: astore_1
    //   91: aload_0
    //   92: monitorexit
    //   93: aload_1
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	fx
    //   10	62	1	localObject1	Object
    //   77	1	1	localRemoteException	RemoteException
    //   90	4	1	localObject2	Object
    //   70	6	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   35	59	70	finally
    //   15	35	77	android/os/RemoteException
    //   59	63	77	android/os/RemoteException
    //   71	77	77	android/os/RemoteException
    //   6	11	90	finally
    //   15	35	90	finally
    //   59	63	90	finally
    //   63	65	90	finally
    //   71	77	90	finally
    //   78	87	90	finally
    //   91	93	90	finally
  }
  
  /* Error */
  public com.google.android.gms.games.Game fq()
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 911	com/google/android/gms/internal/fx:bm	()V
    //   4: aload_0
    //   5: monitorenter
    //   6: aload_0
    //   7: getfield 932	com/google/android/gms/internal/fx:Gy	Lcom/google/android/gms/games/GameEntity;
    //   10: astore_1
    //   11: aload_1
    //   12: ifnonnull +51 -> 63
    //   15: new 934	com/google/android/gms/games/GameBuffer
    //   18: dup
    //   19: aload_0
    //   20: invokevirtual 345	com/google/android/gms/internal/fx:eb	()Landroid/os/IInterface;
    //   23: checkcast 347	com/google/android/gms/internal/gb
    //   26: invokeinterface 937 1 0
    //   31: invokespecial 938	com/google/android/gms/games/GameBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
    //   34: astore_1
    //   35: aload_1
    //   36: invokevirtual 939	com/google/android/gms/games/GameBuffer:getCount	()I
    //   39: ifle +20 -> 59
    //   42: aload_0
    //   43: aload_1
    //   44: iconst_0
    //   45: invokevirtual 942	com/google/android/gms/games/GameBuffer:get	(I)Lcom/google/android/gms/games/Game;
    //   48: invokeinterface 945 1 0
    //   53: checkcast 947	com/google/android/gms/games/GameEntity
    //   56: putfield 932	com/google/android/gms/internal/fx:Gy	Lcom/google/android/gms/games/GameEntity;
    //   59: aload_1
    //   60: invokevirtual 948	com/google/android/gms/games/GameBuffer:close	()V
    //   63: aload_0
    //   64: monitorexit
    //   65: aload_0
    //   66: getfield 932	com/google/android/gms/internal/fx:Gy	Lcom/google/android/gms/games/GameEntity;
    //   69: areturn
    //   70: astore_2
    //   71: aload_1
    //   72: invokevirtual 948	com/google/android/gms/games/GameBuffer:close	()V
    //   75: aload_2
    //   76: athrow
    //   77: astore_1
    //   78: ldc_w 353
    //   81: ldc_w 462
    //   84: invokestatic 464	com/google/android/gms/internal/fz:g	(Ljava/lang/String;Ljava/lang/String;)V
    //   87: goto -24 -> 63
    //   90: astore_1
    //   91: aload_0
    //   92: monitorexit
    //   93: aload_1
    //   94: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	this	fx
    //   10	62	1	localObject1	Object
    //   77	1	1	localRemoteException	RemoteException
    //   90	4	1	localObject2	Object
    //   70	6	2	localObject3	Object
    // Exception table:
    //   from	to	target	type
    //   35	59	70	finally
    //   15	35	77	android/os/RemoteException
    //   59	63	77	android/os/RemoteException
    //   71	77	77	android/os/RemoteException
    //   6	11	90	finally
    //   15	35	90	finally
    //   59	63	90	finally
    //   63	65	90	finally
    //   71	77	90	finally
    //   78	87	90	finally
    //   91	93	90	finally
  }
  
  public Intent fr()
  {
    try
    {
      Intent localIntent = ((gb)eb()).fr();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public Intent fs()
  {
    try
    {
      Intent localIntent = ((gb)eb()).fs();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public Intent ft()
  {
    try
    {
      Intent localIntent = ((gb)eb()).ft();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public Intent fu()
  {
    try
    {
      Intent localIntent = ((gb)eb()).fu();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public void fv()
  {
    try
    {
      ((gb)eb()).o(this.GE);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void fw()
  {
    try
    {
      ((gb)eb()).p(this.GE);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void fx()
  {
    try
    {
      ((gb)eb()).q(this.GE);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public Intent fy()
  {
    try
    {
      Intent localIntent = ((gb)eb()).fy();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public Intent fz()
  {
    try
    {
      Intent localIntent = ((gb)eb()).fz();
      return localIntent;
    }
    catch (RemoteException localRemoteException)
    {
      fz.g("GamesClientImpl", "service died");
    }
    return null;
  }
  
  public void g(a.c<GamesMetadata.LoadGamesResult> paramc)
  {
    try
    {
      ((gb)eb()).d(new j(paramc));
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void g(a.c<TurnBasedMultiplayer.CancelMatchResult> paramc, String paramString)
  {
    try
    {
      ((gb)eb()).n(new bg(paramc), paramString);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void h(a.c<TurnBasedMultiplayer.LoadMatchResult> paramc, String paramString)
  {
    try
    {
      ((gb)eb()).p(new bm(paramc), paramString);
      return;
    }
    catch (RemoteException paramc)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public RealTimeSocket i(String paramString1, String paramString2)
  {
    if ((paramString2 == null) || (!ParticipantUtils.aE(paramString2))) {
      throw new IllegalArgumentException("Bad participant ID");
    }
    RealTimeSocket localRealTimeSocket = (RealTimeSocket)this.Gw.get(paramString2);
    if (localRealTimeSocket != null)
    {
      paramString1 = localRealTimeSocket;
      if (!localRealTimeSocket.isClosed()) {}
    }
    else
    {
      paramString1 = aw(paramString2);
    }
    return paramString1;
  }
  
  public void l(String paramString, int paramInt)
  {
    try
    {
      ((gb)eb()).l(paramString, paramInt);
      return;
    }
    catch (RemoteException paramString)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void m(String paramString, int paramInt)
  {
    try
    {
      ((gb)eb()).m(paramString, paramInt);
      return;
    }
    catch (RemoteException paramString)
    {
      fz.g("GamesClientImpl", "service died");
    }
  }
  
  public void onConnected(Bundle paramBundle)
  {
    if (this.GA)
    {
      this.Gz.fN();
      this.GA = false;
    }
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    this.GA = false;
  }
  
  public void onConnectionSuspended(int paramInt) {}
  
  abstract class a
    extends fx.c
  {
    private final ArrayList<String> GI = new ArrayList();
    
    a(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder);
      int i = 0;
      int j = paramArrayOfString.length;
      while (i < j)
      {
        this.GI.add(paramArrayOfString[i]);
        i += 1;
      }
    }
    
    protected void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom)
    {
      a(paramRoomStatusUpdateListener, paramRoom, this.GI);
    }
    
    protected abstract void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList);
  }
  
  final class aa
    extends eh<gb>.b<RoomStatusUpdateListener>
  {
    private final String GZ;
    
    aa(RoomStatusUpdateListener paramRoomStatusUpdateListener, String paramString)
    {
      super(paramRoomStatusUpdateListener);
      this.GZ = paramString;
    }
    
    public void a(RoomStatusUpdateListener paramRoomStatusUpdateListener)
    {
      if (paramRoomStatusUpdateListener != null) {
        paramRoomStatusUpdateListener.onP2PConnected(this.GZ);
      }
    }
    
    protected void cP() {}
  }
  
  final class ab
    extends eh<gb>.b<RoomStatusUpdateListener>
  {
    private final String GZ;
    
    ab(RoomStatusUpdateListener paramRoomStatusUpdateListener, String paramString)
    {
      super(paramRoomStatusUpdateListener);
      this.GZ = paramString;
    }
    
    public void a(RoomStatusUpdateListener paramRoomStatusUpdateListener)
    {
      if (paramRoomStatusUpdateListener != null) {
        paramRoomStatusUpdateListener.onP2PDisconnected(this.GZ);
      }
    }
    
    protected void cP() {}
  }
  
  final class ac
    extends fx.a
  {
    ac(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder, paramArrayOfString);
    }
    
    protected void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeersConnected(paramRoom, paramArrayList);
    }
  }
  
  final class ad
    extends fx.a
  {
    ad(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder, paramArrayOfString);
    }
    
    protected void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeerDeclined(paramRoom, paramArrayList);
    }
  }
  
  final class ae
    extends fx.a
  {
    ae(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder, paramArrayOfString);
    }
    
    protected void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeersDisconnected(paramRoom, paramArrayList);
    }
  }
  
  final class af
    extends fx.a
  {
    af(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder, paramArrayOfString);
    }
    
    protected void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeerInvitedToRoom(paramRoom, paramArrayList);
    }
  }
  
  final class ag
    extends fx.a
  {
    ag(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder, paramArrayOfString);
    }
    
    protected void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeerJoined(paramRoom, paramArrayList);
    }
  }
  
  final class ah
    extends fx.a
  {
    ah(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder, paramArrayOfString);
    }
    
    protected void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom, ArrayList<String> paramArrayList)
    {
      paramRoomStatusUpdateListener.onPeerLeft(paramRoom, paramArrayList);
    }
  }
  
  final class ai
    extends fw
  {
    private final a.c<Leaderboards.LoadPlayerScoreResult> vj;
    
    ai()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void C(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.aj(fx.this, this.vj, paramDataHolder));
    }
  }
  
  final class aj
    extends eh<gb>.d<a.c<Leaderboards.LoadPlayerScoreResult>>
    implements Leaderboards.LoadPlayerScoreResult
  {
    private final d Ha;
    private final Status vl;
    
    /* Error */
    aj(DataHolder paramDataHolder)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: putfield 20	com/google/android/gms/internal/fx$aj:GJ	Lcom/google/android/gms/internal/fx;
      //   5: aload_0
      //   6: aload_1
      //   7: aload_2
      //   8: aload_3
      //   9: invokespecial 23	com/google/android/gms/internal/eh$d:<init>	(Lcom/google/android/gms/internal/eh;Ljava/lang/Object;Lcom/google/android/gms/common/data/DataHolder;)V
      //   12: aload_0
      //   13: new 25	com/google/android/gms/common/api/Status
      //   16: dup
      //   17: aload_3
      //   18: invokevirtual 31	com/google/android/gms/common/data/DataHolder:getStatusCode	()I
      //   21: invokespecial 34	com/google/android/gms/common/api/Status:<init>	(I)V
      //   24: putfield 36	com/google/android/gms/internal/fx$aj:vl	Lcom/google/android/gms/common/api/Status;
      //   27: new 38	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer
      //   30: dup
      //   31: aload_3
      //   32: invokespecial 41	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   35: astore_1
      //   36: aload_1
      //   37: invokevirtual 44	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:getCount	()I
      //   40: ifle +25 -> 65
      //   43: aload_0
      //   44: aload_1
      //   45: iconst_0
      //   46: invokevirtual 48	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:get	(I)Lcom/google/android/gms/games/leaderboard/LeaderboardScore;
      //   49: invokeinterface 54 1 0
      //   54: checkcast 56	com/google/android/gms/games/leaderboard/d
      //   57: putfield 58	com/google/android/gms/internal/fx$aj:Ha	Lcom/google/android/gms/games/leaderboard/d;
      //   60: aload_1
      //   61: invokevirtual 62	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:close	()V
      //   64: return
      //   65: aload_0
      //   66: aconst_null
      //   67: putfield 58	com/google/android/gms/internal/fx$aj:Ha	Lcom/google/android/gms/games/leaderboard/d;
      //   70: goto -10 -> 60
      //   73: astore_2
      //   74: aload_1
      //   75: invokevirtual 62	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:close	()V
      //   78: aload_2
      //   79: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	80	0	this	aj
      //   0	80	1	this$1	fx
      //   0	80	2	paramDataHolder	DataHolder
      //   8	24	3	localDataHolder	DataHolder
      // Exception table:
      //   from	to	target	type
      //   36	60	73	finally
      //   65	70	73	finally
    }
    
    protected void a(a.c<Leaderboards.LoadPlayerScoreResult> paramc, DataHolder paramDataHolder)
    {
      paramc.b(this);
    }
    
    public LeaderboardScore getScore()
    {
      return this.Ha;
    }
    
    public Status getStatus()
    {
      return this.vl;
    }
  }
  
  final class ak
    extends fw
  {
    private final a.c<Players.LoadPlayersResult> vj;
    
    ak()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void e(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.al(fx.this, this.vj, paramDataHolder));
    }
  }
  
  final class al
    extends fx.av<a.c<Players.LoadPlayersResult>>
    implements Players.LoadPlayersResult
  {
    private final PlayerBuffer Hb;
    
    al(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
      this.Hb = new PlayerBuffer(localDataHolder);
    }
    
    protected void a(a.c<Players.LoadPlayersResult> paramc, DataHolder paramDataHolder)
    {
      paramc.b(this);
    }
    
    public PlayerBuffer getPlayers()
    {
      return this.Hb;
    }
  }
  
  final class am
    extends eh<gb>.b<RealTimeMultiplayer.ReliableMessageSentCallback>
  {
    private final String Hc;
    private final int Hd;
    private final int yJ;
    
    am(RealTimeMultiplayer.ReliableMessageSentCallback paramReliableMessageSentCallback, int paramInt1, int paramInt2, String paramString)
    {
      super(paramReliableMessageSentCallback);
      this.yJ = paramInt1;
      this.Hd = paramInt2;
      this.Hc = paramString;
    }
    
    public void a(RealTimeMultiplayer.ReliableMessageSentCallback paramReliableMessageSentCallback)
    {
      if (paramReliableMessageSentCallback != null) {
        paramReliableMessageSentCallback.onRealTimeMessageSent(this.yJ, this.Hd, this.Hc);
      }
    }
    
    protected void cP() {}
  }
  
  final class an
    extends fw
  {
    final RealTimeMultiplayer.ReliableMessageSentCallback He;
    
    public an(RealTimeMultiplayer.ReliableMessageSentCallback paramReliableMessageSentCallback)
    {
      this.He = paramReliableMessageSentCallback;
    }
    
    public void b(int paramInt1, int paramInt2, String paramString)
    {
      fx.this.a(new fx.am(fx.this, this.He, paramInt1, paramInt2, paramString));
    }
  }
  
  final class ao
    extends fw
  {
    private final OnRequestReceivedListener Hf;
    
    ao(OnRequestReceivedListener paramOnRequestReceivedListener)
    {
      this.Hf = paramOnRequestReceivedListener;
    }
    
    public void m(DataHolder paramDataHolder)
    {
      GameRequestBuffer localGameRequestBuffer = new GameRequestBuffer(paramDataHolder);
      paramDataHolder = null;
      try
      {
        if (localGameRequestBuffer.getCount() > 0) {
          paramDataHolder = (GameRequest)((GameRequest)localGameRequestBuffer.get(0)).freeze();
        }
        localGameRequestBuffer.close();
        if (paramDataHolder != null) {
          fx.this.a(new fx.ap(fx.this, this.Hf, paramDataHolder));
        }
        return;
      }
      finally
      {
        localGameRequestBuffer.close();
      }
    }
    
    public void onRequestRemoved(String paramString)
    {
      fx.this.a(new fx.aq(fx.this, this.Hf, paramString));
    }
  }
  
  final class ap
    extends eh<gb>.b<OnRequestReceivedListener>
  {
    private final GameRequest Hg;
    
    ap(OnRequestReceivedListener paramOnRequestReceivedListener, GameRequest paramGameRequest)
    {
      super(paramOnRequestReceivedListener);
      this.Hg = paramGameRequest;
    }
    
    protected void b(OnRequestReceivedListener paramOnRequestReceivedListener)
    {
      paramOnRequestReceivedListener.onRequestReceived(this.Hg);
    }
    
    protected void cP() {}
  }
  
  final class aq
    extends eh<gb>.b<OnRequestReceivedListener>
  {
    private final String Hh;
    
    aq(OnRequestReceivedListener paramOnRequestReceivedListener, String paramString)
    {
      super(paramOnRequestReceivedListener);
      this.Hh = paramString;
    }
    
    protected void b(OnRequestReceivedListener paramOnRequestReceivedListener)
    {
      paramOnRequestReceivedListener.onRequestRemoved(this.Hh);
    }
    
    protected void cP() {}
  }
  
  final class ar
    extends fw
  {
    private final a.c<Requests.LoadRequestsResult> Hi;
    
    public ar()
    {
      Object localObject;
      this.Hi = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void b(int paramInt, Bundle paramBundle)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      Status localStatus = new Status(paramInt);
      fx.this.a(new fx.as(fx.this, this.Hi, localStatus, paramBundle));
    }
  }
  
  final class as
    extends eh<gb>.b<a.c<Requests.LoadRequestsResult>>
    implements Requests.LoadRequestsResult
  {
    private final Bundle Hj;
    private final Status vl;
    
    as(Status paramStatus, Bundle paramBundle)
    {
      super(paramStatus);
      this.vl = paramBundle;
      Bundle localBundle;
      this.Hj = localBundle;
    }
    
    protected void c(a.c<Requests.LoadRequestsResult> paramc)
    {
      paramc.b(this);
    }
    
    protected void cP()
    {
      release();
    }
    
    public GameRequestBuffer getRequests(int paramInt)
    {
      String str = gs.aW(paramInt);
      if (!this.Hj.containsKey(str)) {
        return null;
      }
      return new GameRequestBuffer((DataHolder)this.Hj.get(str));
    }
    
    public Status getStatus()
    {
      return this.vl;
    }
    
    public void release()
    {
      Iterator localIterator = this.Hj.keySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (String)localIterator.next();
        localObject = (DataHolder)this.Hj.getParcelable((String)localObject);
        if (localObject != null) {
          ((DataHolder)localObject).close();
        }
      }
    }
  }
  
  final class at
    extends fw
  {
    private final a.c<Requests.UpdateRequestsResult> Hk;
    
    public at()
    {
      Object localObject;
      this.Hk = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void D(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.au(fx.this, this.Hk, paramDataHolder));
    }
  }
  
  final class au
    extends fx.av<a.c<Requests.UpdateRequestsResult>>
    implements Requests.UpdateRequestsResult
  {
    private final hb Hl;
    
    au(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
      this.Hl = hb.H(localDataHolder);
    }
    
    protected void a(a.c<Requests.UpdateRequestsResult> paramc, DataHolder paramDataHolder)
    {
      paramc.b(this);
    }
    
    public Set<String> getRequestIds()
    {
      return this.Hl.getRequestIds();
    }
    
    public int getRequestOutcome(String paramString)
    {
      return this.Hl.getRequestOutcome(paramString);
    }
  }
  
  abstract class av<R extends a.c<?>>
    extends eh<gb>.d<R>
    implements Releasable, Result
  {
    final Status vl;
    final DataHolder zU;
    
    public av(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
      this.vl = new Status(localDataHolder.getStatusCode());
      this.zU = localDataHolder;
    }
    
    public Status getStatus()
    {
      return this.vl;
    }
    
    public void release()
    {
      if (this.zU != null) {
        this.zU.close();
      }
    }
  }
  
  final class aw
    extends fx.c
  {
    aw(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder);
    }
    
    public void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom)
    {
      paramRoomStatusUpdateListener.onRoomAutoMatching(paramRoom);
    }
  }
  
  final class ax
    extends fw
  {
    private final RoomUpdateListener Hm;
    private final RoomStatusUpdateListener Hn;
    private final RealTimeMessageReceivedListener Ho;
    
    public ax(RoomUpdateListener paramRoomUpdateListener)
    {
      this.Hm = ((RoomUpdateListener)er.b(paramRoomUpdateListener, "Callbacks must not be null"));
      this.Hn = null;
      this.Ho = null;
    }
    
    public ax(RoomUpdateListener paramRoomUpdateListener, RoomStatusUpdateListener paramRoomStatusUpdateListener, RealTimeMessageReceivedListener paramRealTimeMessageReceivedListener)
    {
      this.Hm = ((RoomUpdateListener)er.b(paramRoomUpdateListener, "Callbacks must not be null"));
      this.Hn = paramRoomStatusUpdateListener;
      this.Ho = paramRealTimeMessageReceivedListener;
    }
    
    public void a(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      fx.this.a(new fx.af(fx.this, this.Hn, paramDataHolder, paramArrayOfString));
    }
    
    public void b(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      fx.this.a(new fx.ag(fx.this, this.Hn, paramDataHolder, paramArrayOfString));
    }
    
    public void c(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      fx.this.a(new fx.ah(fx.this, this.Hn, paramDataHolder, paramArrayOfString));
    }
    
    public void d(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      fx.this.a(new fx.ad(fx.this, this.Hn, paramDataHolder, paramArrayOfString));
    }
    
    public void e(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      fx.this.a(new fx.ac(fx.this, this.Hn, paramDataHolder, paramArrayOfString));
    }
    
    public void f(DataHolder paramDataHolder, String[] paramArrayOfString)
    {
      fx.this.a(new fx.ae(fx.this, this.Hn, paramDataHolder, paramArrayOfString));
    }
    
    public void onLeftRoom(int paramInt, String paramString)
    {
      fx.this.a(new fx.v(fx.this, this.Hm, paramInt, paramString));
    }
    
    public void onP2PConnected(String paramString)
    {
      fx.this.a(new fx.aa(fx.this, this.Hn, paramString));
    }
    
    public void onP2PDisconnected(String paramString)
    {
      fx.this.a(new fx.ab(fx.this, this.Hn, paramString));
    }
    
    public void onRealTimeMessageReceived(RealTimeMessage paramRealTimeMessage)
    {
      fx.this.a(new fx.z(fx.this, this.Ho, paramRealTimeMessage));
    }
    
    public void s(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.ba(fx.this, this.Hm, paramDataHolder));
    }
    
    public void t(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.q(fx.this, this.Hm, paramDataHolder));
    }
    
    public void u(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.az(fx.this, this.Hn, paramDataHolder));
    }
    
    public void v(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.aw(fx.this, this.Hn, paramDataHolder));
    }
    
    public void w(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.ay(fx.this, this.Hm, paramDataHolder));
    }
    
    public void x(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.h(fx.this, this.Hn, paramDataHolder));
    }
    
    public void y(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.i(fx.this, this.Hn, paramDataHolder));
    }
  }
  
  final class ay
    extends fx.b
  {
    ay(RoomUpdateListener paramRoomUpdateListener, DataHolder paramDataHolder)
    {
      super(paramRoomUpdateListener, paramDataHolder);
    }
    
    public void a(RoomUpdateListener paramRoomUpdateListener, Room paramRoom, int paramInt)
    {
      paramRoomUpdateListener.onRoomConnected(paramInt, paramRoom);
    }
  }
  
  final class az
    extends fx.c
  {
    az(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder);
    }
    
    public void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom)
    {
      paramRoomStatusUpdateListener.onRoomConnecting(paramRoom);
    }
  }
  
  abstract class b
    extends eh<gb>.d<RoomUpdateListener>
  {
    b(RoomUpdateListener paramRoomUpdateListener, DataHolder paramDataHolder)
    {
      super(paramRoomUpdateListener, paramDataHolder);
    }
    
    protected void a(RoomUpdateListener paramRoomUpdateListener, DataHolder paramDataHolder)
    {
      a(paramRoomUpdateListener, fx.a(fx.this, paramDataHolder), paramDataHolder.getStatusCode());
    }
    
    protected abstract void a(RoomUpdateListener paramRoomUpdateListener, Room paramRoom, int paramInt);
  }
  
  final class ba
    extends fx.b
  {
    public ba(RoomUpdateListener paramRoomUpdateListener, DataHolder paramDataHolder)
    {
      super(paramRoomUpdateListener, paramDataHolder);
    }
    
    public void a(RoomUpdateListener paramRoomUpdateListener, Room paramRoom, int paramInt)
    {
      paramRoomUpdateListener.onRoomCreated(paramInt, paramRoom);
    }
  }
  
  final class bb
    extends fw
  {
    private final a.c<Status> vj;
    
    public bb()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void cM()
    {
      Status localStatus = new Status(0);
      fx.this.a(new fx.bc(fx.this, this.vj, localStatus));
    }
  }
  
  final class bc
    extends eh<gb>.b<a.c<Status>>
  {
    private final Status vl;
    
    public bc(Status paramStatus)
    {
      super(paramStatus);
      Status localStatus;
      this.vl = localStatus;
    }
    
    public void c(a.c<Status> paramc)
    {
      paramc.b(this.vl);
    }
    
    protected void cP() {}
  }
  
  final class bd
    extends fw
  {
    private final a.c<Leaderboards.SubmitScoreResult> vj;
    
    public bd()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void d(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.be(fx.this, this.vj, paramDataHolder));
    }
  }
  
  final class be
    extends fx.av<a.c<Leaderboards.SubmitScoreResult>>
    implements Leaderboards.SubmitScoreResult
  {
    private final ScoreSubmissionData Hp;
    
    public be(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
      try
      {
        this.Hp = new ScoreSubmissionData(localDataHolder);
        return;
      }
      finally
      {
        localDataHolder.close();
      }
    }
    
    public void a(a.c<Leaderboards.SubmitScoreResult> paramc, DataHolder paramDataHolder)
    {
      paramc.b(this);
    }
    
    public ScoreSubmissionData getScoreData()
    {
      return this.Hp;
    }
  }
  
  abstract class bf<T extends a.c<?>>
    extends fx.av<T>
  {
    final TurnBasedMatch GX;
    
    /* Error */
    bf(DataHolder paramDataHolder)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: putfield 16	com/google/android/gms/internal/fx$bf:GJ	Lcom/google/android/gms/internal/fx;
      //   5: aload_0
      //   6: aload_1
      //   7: aload_2
      //   8: aload_3
      //   9: invokespecial 18	com/google/android/gms/internal/fx$av:<init>	(Lcom/google/android/gms/internal/fx;Lcom/google/android/gms/common/api/a$c;Lcom/google/android/gms/common/data/DataHolder;)V
      //   12: new 20	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer
      //   15: dup
      //   16: aload_3
      //   17: invokespecial 23	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   20: astore_1
      //   21: aload_1
      //   22: invokevirtual 27	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer:getCount	()I
      //   25: ifle +28 -> 53
      //   28: aload_0
      //   29: aload_1
      //   30: iconst_0
      //   31: invokevirtual 31	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer:get	(I)Ljava/lang/Object;
      //   34: checkcast 33	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatch
      //   37: invokeinterface 37 1 0
      //   42: checkcast 33	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatch
      //   45: putfield 39	com/google/android/gms/internal/fx$bf:GX	Lcom/google/android/gms/games/multiplayer/turnbased/TurnBasedMatch;
      //   48: aload_1
      //   49: invokevirtual 43	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer:close	()V
      //   52: return
      //   53: aload_0
      //   54: aconst_null
      //   55: putfield 39	com/google/android/gms/internal/fx$bf:GX	Lcom/google/android/gms/games/multiplayer/turnbased/TurnBasedMatch;
      //   58: goto -10 -> 48
      //   61: astore_2
      //   62: aload_1
      //   63: invokevirtual 43	com/google/android/gms/games/multiplayer/turnbased/TurnBasedMatchBuffer:close	()V
      //   66: aload_2
      //   67: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	68	0	this	bf
      //   0	68	1	this$1	fx
      //   0	68	2	paramDataHolder	DataHolder
      //   8	9	3	localDataHolder	DataHolder
      // Exception table:
      //   from	to	target	type
      //   21	48	61	finally
      //   53	58	61	finally
    }
    
    protected void a(T paramT, DataHolder paramDataHolder)
    {
      h(paramT);
    }
    
    public TurnBasedMatch getMatch()
    {
      return this.GX;
    }
    
    abstract void h(T paramT);
  }
  
  final class bg
    extends fw
  {
    private final a.c<TurnBasedMultiplayer.CancelMatchResult> Hq;
    
    public bg()
    {
      Object localObject;
      this.Hq = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void f(int paramInt, String paramString)
    {
      Status localStatus = new Status(paramInt);
      fx.this.a(new fx.bh(fx.this, this.Hq, localStatus, paramString));
    }
  }
  
  final class bh
    extends eh<gb>.b<a.c<TurnBasedMultiplayer.CancelMatchResult>>
    implements TurnBasedMultiplayer.CancelMatchResult
  {
    private final String Hr;
    private final Status vl;
    
    bh(Status paramStatus, String paramString)
    {
      super(paramStatus);
      this.vl = paramString;
      String str;
      this.Hr = str;
    }
    
    public void c(a.c<TurnBasedMultiplayer.CancelMatchResult> paramc)
    {
      paramc.b(this);
    }
    
    protected void cP() {}
    
    public String getMatchId()
    {
      return this.Hr;
    }
    
    public Status getStatus()
    {
      return this.vl;
    }
  }
  
  final class bi
    extends fw
  {
    private final a.c<TurnBasedMultiplayer.InitiateMatchResult> Hs;
    
    public bi()
    {
      Object localObject;
      this.Hs = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void o(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.bj(fx.this, this.Hs, paramDataHolder));
    }
  }
  
  final class bj
    extends fx.bf<a.c<TurnBasedMultiplayer.InitiateMatchResult>>
    implements TurnBasedMultiplayer.InitiateMatchResult
  {
    bj(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
    }
    
    protected void h(a.c<TurnBasedMultiplayer.InitiateMatchResult> paramc)
    {
      paramc.b(this);
    }
  }
  
  final class bk
    extends fw
  {
    private final a.c<TurnBasedMultiplayer.LeaveMatchResult> Ht;
    
    public bk()
    {
      Object localObject;
      this.Ht = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void q(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.bl(fx.this, this.Ht, paramDataHolder));
    }
  }
  
  final class bl
    extends fx.bf<a.c<TurnBasedMultiplayer.LeaveMatchResult>>
    implements TurnBasedMultiplayer.LeaveMatchResult
  {
    bl(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
    }
    
    protected void h(a.c<TurnBasedMultiplayer.LeaveMatchResult> paramc)
    {
      paramc.b(this);
    }
  }
  
  final class bm
    extends fw
  {
    private final a.c<TurnBasedMultiplayer.LoadMatchResult> Hu;
    
    public bm()
    {
      Object localObject;
      this.Hu = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void n(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.bn(fx.this, this.Hu, paramDataHolder));
    }
  }
  
  final class bn
    extends fx.bf<a.c<TurnBasedMultiplayer.LoadMatchResult>>
    implements TurnBasedMultiplayer.LoadMatchResult
  {
    bn(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
    }
    
    protected void h(a.c<TurnBasedMultiplayer.LoadMatchResult> paramc)
    {
      paramc.b(this);
    }
  }
  
  final class bo
    extends fw
  {
    private final a.c<TurnBasedMultiplayer.UpdateMatchResult> Hv;
    
    public bo()
    {
      Object localObject;
      this.Hv = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void p(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.bp(fx.this, this.Hv, paramDataHolder));
    }
  }
  
  final class bp
    extends fx.bf<a.c<TurnBasedMultiplayer.UpdateMatchResult>>
    implements TurnBasedMultiplayer.UpdateMatchResult
  {
    bp(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
    }
    
    protected void h(a.c<TurnBasedMultiplayer.UpdateMatchResult> paramc)
    {
      paramc.b(this);
    }
  }
  
  final class bq
    extends fw
  {
    private final a.c<TurnBasedMultiplayer.LoadMatchesResult> Hw;
    
    public bq()
    {
      Object localObject;
      this.Hw = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void a(int paramInt, Bundle paramBundle)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      Status localStatus = new Status(paramInt);
      fx.this.a(new fx.br(fx.this, this.Hw, localStatus, paramBundle));
    }
  }
  
  final class br
    extends eh<gb>.b<a.c<TurnBasedMultiplayer.LoadMatchesResult>>
    implements TurnBasedMultiplayer.LoadMatchesResult
  {
    private final LoadMatchesResponse Hx;
    private final Status vl;
    
    br(Status paramStatus, Bundle paramBundle)
    {
      super(paramStatus);
      this.vl = paramBundle;
      Bundle localBundle;
      this.Hx = new LoadMatchesResponse(localBundle);
    }
    
    protected void c(a.c<TurnBasedMultiplayer.LoadMatchesResult> paramc)
    {
      paramc.b(this);
    }
    
    protected void cP() {}
    
    public LoadMatchesResponse getMatches()
    {
      return this.Hx;
    }
    
    public Status getStatus()
    {
      return this.vl;
    }
    
    public void release()
    {
      this.Hx.close();
    }
  }
  
  abstract class c
    extends eh<gb>.d<RoomStatusUpdateListener>
  {
    c(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder);
    }
    
    protected void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder)
    {
      a(paramRoomStatusUpdateListener, fx.a(fx.this, paramDataHolder));
    }
    
    protected abstract void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom);
  }
  
  final class d
    extends fw
  {
    private final a.c<Achievements.UpdateAchievementResult> vj;
    
    d()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void e(int paramInt, String paramString)
    {
      fx.this.a(new fx.e(fx.this, this.vj, paramInt, paramString));
    }
  }
  
  final class e
    extends eh<gb>.b<a.c<Achievements.UpdateAchievementResult>>
    implements Achievements.UpdateAchievementResult
  {
    private final String GK;
    private final Status vl;
    
    e(int paramInt, String paramString)
    {
      super(paramInt);
      this.vl = new Status(paramString);
      String str;
      this.GK = str;
    }
    
    protected void c(a.c<Achievements.UpdateAchievementResult> paramc)
    {
      paramc.b(this);
    }
    
    protected void cP() {}
    
    public String getAchievementId()
    {
      return this.GK;
    }
    
    public Status getStatus()
    {
      return this.vl;
    }
  }
  
  final class f
    extends fw
  {
    private final a.c<Achievements.LoadAchievementsResult> vj;
    
    f()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void b(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.g(fx.this, this.vj, paramDataHolder));
    }
  }
  
  final class g
    extends fx.av<a.c<Achievements.LoadAchievementsResult>>
    implements Achievements.LoadAchievementsResult
  {
    private final AchievementBuffer GL;
    
    g(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
      this.GL = new AchievementBuffer(localDataHolder);
    }
    
    protected void a(a.c<Achievements.LoadAchievementsResult> paramc, DataHolder paramDataHolder)
    {
      paramc.b(this);
    }
    
    public AchievementBuffer getAchievements()
    {
      return this.GL;
    }
  }
  
  final class h
    extends fx.c
  {
    h(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder);
    }
    
    public void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom)
    {
      paramRoomStatusUpdateListener.onConnectedToRoom(paramRoom);
    }
  }
  
  final class i
    extends fx.c
  {
    i(RoomStatusUpdateListener paramRoomStatusUpdateListener, DataHolder paramDataHolder)
    {
      super(paramRoomStatusUpdateListener, paramDataHolder);
    }
    
    public void a(RoomStatusUpdateListener paramRoomStatusUpdateListener, Room paramRoom)
    {
      paramRoomStatusUpdateListener.onDisconnectedFromRoom(paramRoom);
    }
  }
  
  final class j
    extends fw
  {
    private final a.c<GamesMetadata.LoadGamesResult> vj;
    
    j()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void g(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.k(fx.this, this.vj, paramDataHolder));
    }
  }
  
  final class k
    extends fx.av<a.c<GamesMetadata.LoadGamesResult>>
    implements GamesMetadata.LoadGamesResult
  {
    private final GameBuffer GM;
    
    k(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
      this.GM = new GameBuffer(localDataHolder);
    }
    
    protected void a(a.c<GamesMetadata.LoadGamesResult> paramc, DataHolder paramDataHolder)
    {
      paramc.b(this);
    }
    
    public GameBuffer getGames()
    {
      return this.GM;
    }
  }
  
  final class l
    extends fw
  {
    private final OnInvitationReceivedListener GN;
    
    l(OnInvitationReceivedListener paramOnInvitationReceivedListener)
    {
      this.GN = paramOnInvitationReceivedListener;
    }
    
    public void l(DataHolder paramDataHolder)
    {
      InvitationBuffer localInvitationBuffer = new InvitationBuffer(paramDataHolder);
      paramDataHolder = null;
      try
      {
        if (localInvitationBuffer.getCount() > 0) {
          paramDataHolder = (Invitation)((Invitation)localInvitationBuffer.get(0)).freeze();
        }
        localInvitationBuffer.close();
        if (paramDataHolder != null) {
          fx.this.a(new fx.m(fx.this, this.GN, paramDataHolder));
        }
        return;
      }
      finally
      {
        localInvitationBuffer.close();
      }
    }
    
    public void onInvitationRemoved(String paramString)
    {
      fx.this.a(new fx.n(fx.this, this.GN, paramString));
    }
  }
  
  final class m
    extends eh<gb>.b<OnInvitationReceivedListener>
  {
    private final Invitation GO;
    
    m(OnInvitationReceivedListener paramOnInvitationReceivedListener, Invitation paramInvitation)
    {
      super(paramOnInvitationReceivedListener);
      this.GO = paramInvitation;
    }
    
    protected void b(OnInvitationReceivedListener paramOnInvitationReceivedListener)
    {
      paramOnInvitationReceivedListener.onInvitationReceived(this.GO);
    }
    
    protected void cP() {}
  }
  
  final class n
    extends eh<gb>.b<OnInvitationReceivedListener>
  {
    private final String GP;
    
    n(OnInvitationReceivedListener paramOnInvitationReceivedListener, String paramString)
    {
      super(paramOnInvitationReceivedListener);
      this.GP = paramString;
    }
    
    protected void b(OnInvitationReceivedListener paramOnInvitationReceivedListener)
    {
      paramOnInvitationReceivedListener.onInvitationRemoved(this.GP);
    }
    
    protected void cP() {}
  }
  
  final class o
    extends fw
  {
    private final a.c<Invitations.LoadInvitationsResult> vj;
    
    o()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void k(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.p(fx.this, this.vj, paramDataHolder));
    }
  }
  
  final class p
    extends fx.av<a.c<Invitations.LoadInvitationsResult>>
    implements Invitations.LoadInvitationsResult
  {
    private final InvitationBuffer GQ;
    
    p(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
      this.GQ = new InvitationBuffer(localDataHolder);
    }
    
    protected void a(a.c<Invitations.LoadInvitationsResult> paramc, DataHolder paramDataHolder)
    {
      paramc.b(this);
    }
    
    public InvitationBuffer getInvitations()
    {
      return this.GQ;
    }
  }
  
  final class q
    extends fx.b
  {
    public q(RoomUpdateListener paramRoomUpdateListener, DataHolder paramDataHolder)
    {
      super(paramRoomUpdateListener, paramDataHolder);
    }
    
    public void a(RoomUpdateListener paramRoomUpdateListener, Room paramRoom, int paramInt)
    {
      paramRoomUpdateListener.onJoinedRoom(paramInt, paramRoom);
    }
  }
  
  final class r
    extends fw
  {
    private final a.c<Leaderboards.LoadScoresResult> vj;
    
    r()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void a(DataHolder paramDataHolder1, DataHolder paramDataHolder2)
    {
      fx.this.a(new fx.s(fx.this, this.vj, paramDataHolder1, paramDataHolder2));
    }
  }
  
  final class s
    extends fx.av<a.c<Leaderboards.LoadScoresResult>>
    implements Leaderboards.LoadScoresResult
  {
    private final com.google.android.gms.games.leaderboard.a GR;
    private final LeaderboardScoreBuffer GS;
    
    /* Error */
    s(DataHolder paramDataHolder1, DataHolder paramDataHolder2)
    {
      // Byte code:
      //   0: aload_0
      //   1: aload_1
      //   2: putfield 20	com/google/android/gms/internal/fx$s:GJ	Lcom/google/android/gms/internal/fx;
      //   5: aload_0
      //   6: aload_1
      //   7: aload_2
      //   8: aload 4
      //   10: invokespecial 23	com/google/android/gms/internal/fx$av:<init>	(Lcom/google/android/gms/internal/fx;Lcom/google/android/gms/common/api/a$c;Lcom/google/android/gms/common/data/DataHolder;)V
      //   13: new 25	com/google/android/gms/games/leaderboard/LeaderboardBuffer
      //   16: dup
      //   17: aload_3
      //   18: invokespecial 28	com/google/android/gms/games/leaderboard/LeaderboardBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   21: astore_1
      //   22: aload_1
      //   23: invokevirtual 32	com/google/android/gms/games/leaderboard/LeaderboardBuffer:getCount	()I
      //   26: ifle +41 -> 67
      //   29: aload_0
      //   30: aload_1
      //   31: iconst_0
      //   32: invokevirtual 36	com/google/android/gms/games/leaderboard/LeaderboardBuffer:get	(I)Ljava/lang/Object;
      //   35: checkcast 38	com/google/android/gms/games/leaderboard/Leaderboard
      //   38: invokeinterface 42 1 0
      //   43: checkcast 44	com/google/android/gms/games/leaderboard/a
      //   46: putfield 46	com/google/android/gms/internal/fx$s:GR	Lcom/google/android/gms/games/leaderboard/a;
      //   49: aload_1
      //   50: invokevirtual 50	com/google/android/gms/games/leaderboard/LeaderboardBuffer:close	()V
      //   53: aload_0
      //   54: new 52	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer
      //   57: dup
      //   58: aload 4
      //   60: invokespecial 53	com/google/android/gms/games/leaderboard/LeaderboardScoreBuffer:<init>	(Lcom/google/android/gms/common/data/DataHolder;)V
      //   63: putfield 55	com/google/android/gms/internal/fx$s:GS	Lcom/google/android/gms/games/leaderboard/LeaderboardScoreBuffer;
      //   66: return
      //   67: aload_0
      //   68: aconst_null
      //   69: putfield 46	com/google/android/gms/internal/fx$s:GR	Lcom/google/android/gms/games/leaderboard/a;
      //   72: goto -23 -> 49
      //   75: astore_2
      //   76: aload_1
      //   77: invokevirtual 50	com/google/android/gms/games/leaderboard/LeaderboardBuffer:close	()V
      //   80: aload_2
      //   81: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	82	0	this	s
      //   0	82	1	this$1	fx
      //   0	82	2	paramDataHolder1	DataHolder
      //   0	82	3	paramDataHolder2	DataHolder
      //   8	51	4	localDataHolder	DataHolder
      // Exception table:
      //   from	to	target	type
      //   22	49	75	finally
      //   67	72	75	finally
    }
    
    protected void a(a.c<Leaderboards.LoadScoresResult> paramc, DataHolder paramDataHolder)
    {
      paramc.b(this);
    }
    
    public Leaderboard getLeaderboard()
    {
      return this.GR;
    }
    
    public LeaderboardScoreBuffer getScores()
    {
      return this.GS;
    }
  }
  
  final class t
    extends fw
  {
    private final a.c<Leaderboards.LeaderboardMetadataResult> vj;
    
    t()
    {
      Object localObject;
      this.vj = ((a.c)er.b(localObject, "Holder must not be null"));
    }
    
    public void c(DataHolder paramDataHolder)
    {
      fx.this.a(new fx.u(fx.this, this.vj, paramDataHolder));
    }
  }
  
  final class u
    extends fx.av<a.c<Leaderboards.LeaderboardMetadataResult>>
    implements Leaderboards.LeaderboardMetadataResult
  {
    private final LeaderboardBuffer GT;
    
    u(DataHolder paramDataHolder)
    {
      super(paramDataHolder, localDataHolder);
      this.GT = new LeaderboardBuffer(localDataHolder);
    }
    
    protected void a(a.c<Leaderboards.LeaderboardMetadataResult> paramc, DataHolder paramDataHolder)
    {
      paramc.b(this);
    }
    
    public LeaderboardBuffer getLeaderboards()
    {
      return this.GT;
    }
  }
  
  final class v
    extends eh<gb>.b<RoomUpdateListener>
  {
    private final String GU;
    private final int yJ;
    
    v(RoomUpdateListener paramRoomUpdateListener, int paramInt, String paramString)
    {
      super(paramRoomUpdateListener);
      this.yJ = paramInt;
      this.GU = paramString;
    }
    
    public void a(RoomUpdateListener paramRoomUpdateListener)
    {
      paramRoomUpdateListener.onLeftRoom(this.yJ, this.GU);
    }
    
    protected void cP() {}
  }
  
  final class w
    extends eh<gb>.b<OnTurnBasedMatchUpdateReceivedListener>
  {
    private final String GV;
    
    w(OnTurnBasedMatchUpdateReceivedListener paramOnTurnBasedMatchUpdateReceivedListener, String paramString)
    {
      super(paramOnTurnBasedMatchUpdateReceivedListener);
      this.GV = paramString;
    }
    
    protected void b(OnTurnBasedMatchUpdateReceivedListener paramOnTurnBasedMatchUpdateReceivedListener)
    {
      paramOnTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchRemoved(this.GV);
    }
    
    protected void cP() {}
  }
  
  final class x
    extends fw
  {
    private final OnTurnBasedMatchUpdateReceivedListener GW;
    
    x(OnTurnBasedMatchUpdateReceivedListener paramOnTurnBasedMatchUpdateReceivedListener)
    {
      this.GW = paramOnTurnBasedMatchUpdateReceivedListener;
    }
    
    public void onTurnBasedMatchRemoved(String paramString)
    {
      fx.this.a(new fx.w(fx.this, this.GW, paramString));
    }
    
    public void r(DataHolder paramDataHolder)
    {
      TurnBasedMatchBuffer localTurnBasedMatchBuffer = new TurnBasedMatchBuffer(paramDataHolder);
      paramDataHolder = null;
      try
      {
        if (localTurnBasedMatchBuffer.getCount() > 0) {
          paramDataHolder = (TurnBasedMatch)((TurnBasedMatch)localTurnBasedMatchBuffer.get(0)).freeze();
        }
        localTurnBasedMatchBuffer.close();
        if (paramDataHolder != null) {
          fx.this.a(new fx.y(fx.this, this.GW, paramDataHolder));
        }
        return;
      }
      finally
      {
        localTurnBasedMatchBuffer.close();
      }
    }
  }
  
  final class y
    extends eh<gb>.b<OnTurnBasedMatchUpdateReceivedListener>
  {
    private final TurnBasedMatch GX;
    
    y(OnTurnBasedMatchUpdateReceivedListener paramOnTurnBasedMatchUpdateReceivedListener, TurnBasedMatch paramTurnBasedMatch)
    {
      super(paramOnTurnBasedMatchUpdateReceivedListener);
      this.GX = paramTurnBasedMatch;
    }
    
    protected void b(OnTurnBasedMatchUpdateReceivedListener paramOnTurnBasedMatchUpdateReceivedListener)
    {
      paramOnTurnBasedMatchUpdateReceivedListener.onTurnBasedMatchReceived(this.GX);
    }
    
    protected void cP() {}
  }
  
  final class z
    extends eh<gb>.b<RealTimeMessageReceivedListener>
  {
    private final RealTimeMessage GY;
    
    z(RealTimeMessageReceivedListener paramRealTimeMessageReceivedListener, RealTimeMessage paramRealTimeMessage)
    {
      super(paramRealTimeMessageReceivedListener);
      this.GY = paramRealTimeMessage;
    }
    
    public void a(RealTimeMessageReceivedListener paramRealTimeMessageReceivedListener)
    {
      if (paramRealTimeMessageReceivedListener != null) {
        paramRealTimeMessageReceivedListener.onRealTimeMessageReceived(this.GY);
      }
    }
    
    protected void cP() {}
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\fx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */