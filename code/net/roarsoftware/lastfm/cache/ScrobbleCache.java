package net.roarsoftware.lastfm.cache;

import java.io.IOException;
import java.util.Collection;
import net.roarsoftware.lastfm.scrobble.Scrobbler;
import net.roarsoftware.lastfm.scrobble.SubmissionData;

public abstract interface ScrobbleCache
{
  public abstract void cacheScrobble(Collection<SubmissionData> paramCollection);
  
  public abstract void cacheScrobble(SubmissionData... paramVarArgs);
  
  public abstract void clearScrobbleCache();
  
  public abstract boolean isEmpty();
  
  public abstract void scrobble(Scrobbler paramScrobbler)
    throws IOException;
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\net\roarsoftware\lastfm\cache\ScrobbleCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */