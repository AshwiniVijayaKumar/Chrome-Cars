package nl.siegmann.epublib.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nl.siegmann.epublib.service.MediatypeService;
import nl.siegmann.epublib.util.StringUtil;

public class Resources
  implements Serializable
{
  private static final String IMAGE_PREFIX = "image_";
  private static final String ITEM_PREFIX = "item_";
  private static final long serialVersionUID = 2450876953383871451L;
  private int lastId = 1;
  private Map<String, Resource> resources = new HashMap();
  
  private String createHref(MediaType paramMediaType, int paramInt)
  {
    if (MediatypeService.isBitmapImage(paramMediaType)) {
      return "image_" + paramInt + paramMediaType.getDefaultExtension();
    }
    return "item_" + paramInt + paramMediaType.getDefaultExtension();
  }
  
  private String createUniqueResourceId(Resource paramResource)
  {
    int j = this.lastId;
    int i = j;
    if (j == Integer.MAX_VALUE)
    {
      if (this.resources.size() == Integer.MAX_VALUE) {
        throw new IllegalArgumentException("Resources contains 2147483647 elements: no new elements can be added");
      }
      i = 1;
    }
    String str = getResourceItemPrefix(paramResource);
    for (paramResource = str + i; containsId(paramResource); paramResource = i)
    {
      paramResource = new StringBuilder().append(str);
      i += 1;
    }
    this.lastId = i;
    return paramResource;
  }
  
  public static Resource findFirstResourceByMediaType(Collection<Resource> paramCollection, MediaType paramMediaType)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Resource localResource = (Resource)paramCollection.next();
      if (localResource.getMediaType() == paramMediaType) {
        return localResource;
      }
    }
    return null;
  }
  
  private void fixResourceHref(Resource paramResource)
  {
    if ((StringUtil.isNotBlank(paramResource.getHref())) && (!this.resources.containsKey(paramResource.getHref()))) {}
    while (!StringUtil.isBlank(paramResource.getHref())) {
      return;
    }
    if (paramResource.getMediaType() == null) {
      throw new IllegalArgumentException("Resource must have either a MediaType or a href");
    }
    int i = 1;
    for (Object localObject = createHref(paramResource.getMediaType(), 1); this.resources.containsKey(localObject); localObject = createHref((MediaType)localObject, i))
    {
      localObject = paramResource.getMediaType();
      i += 1;
    }
    paramResource.setHref((String)localObject);
  }
  
  private String getResourceItemPrefix(Resource paramResource)
  {
    if (MediatypeService.isBitmapImage(paramResource.getMediaType())) {
      return "image_";
    }
    return "item_";
  }
  
  private String makeValidId(String paramString, Resource paramResource)
  {
    String str = paramString;
    if (StringUtil.isNotBlank(paramString))
    {
      str = paramString;
      if (!Character.isJavaIdentifierStart(paramString.charAt(0))) {
        str = getResourceItemPrefix(paramResource) + paramString;
      }
    }
    return str;
  }
  
  public Resource add(Resource paramResource)
  {
    fixResourceHref(paramResource);
    fixResourceId(paramResource);
    this.resources.put(paramResource.getHref(), paramResource);
    return paramResource;
  }
  
  public void addAll(Collection<Resource> paramCollection)
  {
    paramCollection = paramCollection.iterator();
    while (paramCollection.hasNext())
    {
      Resource localResource = (Resource)paramCollection.next();
      fixResourceHref(localResource);
      this.resources.put(localResource.getHref(), localResource);
    }
  }
  
  public boolean containsByHref(String paramString)
  {
    if (StringUtil.isBlank(paramString)) {
      return false;
    }
    return this.resources.containsKey(StringUtil.substringBefore(paramString, '#'));
  }
  
  public boolean containsId(String paramString)
  {
    if (StringUtil.isBlank(paramString)) {}
    Iterator localIterator;
    do
    {
      while (!localIterator.hasNext())
      {
        return false;
        localIterator = this.resources.values().iterator();
      }
    } while (!paramString.equals(((Resource)localIterator.next()).getId()));
    return true;
  }
  
  public Resource findFirstResourceByMediaType(MediaType paramMediaType)
  {
    return findFirstResourceByMediaType(this.resources.values(), paramMediaType);
  }
  
  public void fixResourceId(Resource paramResource)
  {
    Object localObject = paramResource.getId();
    if (StringUtil.isBlank(paramResource.getId())) {
      localObject = StringUtil.substringAfterLast(StringUtil.substringBeforeLast(paramResource.getHref(), '.'), '/');
    }
    String str = makeValidId((String)localObject, paramResource);
    if (!StringUtil.isBlank(str))
    {
      localObject = str;
      if (!containsId(str)) {}
    }
    else
    {
      localObject = createUniqueResourceId(paramResource);
    }
    paramResource.setId((String)localObject);
  }
  
  public Collection<Resource> getAll()
  {
    return this.resources.values();
  }
  
  public Collection<String> getAllHrefs()
  {
    return this.resources.keySet();
  }
  
  public Resource getByHref(String paramString)
  {
    if (StringUtil.isBlank(paramString)) {
      return null;
    }
    paramString = StringUtil.substringBefore(paramString, '#');
    return (Resource)this.resources.get(paramString);
  }
  
  public Resource getById(String paramString)
  {
    if (StringUtil.isBlank(paramString)) {
      return null;
    }
    Iterator localIterator = this.resources.values().iterator();
    while (localIterator.hasNext())
    {
      Resource localResource = (Resource)localIterator.next();
      if (paramString.equals(localResource.getId())) {
        return localResource;
      }
    }
    return null;
  }
  
  public Resource getByIdOrHref(String paramString)
  {
    Resource localResource2 = getById(paramString);
    Resource localResource1 = localResource2;
    if (localResource2 == null) {
      localResource1 = getByHref(paramString);
    }
    return localResource1;
  }
  
  public Map<String, Resource> getResourceMap()
  {
    return this.resources;
  }
  
  public List<Resource> getResourcesByMediaType(MediaType paramMediaType)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramMediaType == null) {}
    for (;;)
    {
      return localArrayList;
      Iterator localIterator = getAll().iterator();
      while (localIterator.hasNext())
      {
        Resource localResource = (Resource)localIterator.next();
        if (localResource.getMediaType() == paramMediaType) {
          localArrayList.add(localResource);
        }
      }
    }
  }
  
  public List<Resource> getResourcesByMediaTypes(MediaType[] paramArrayOfMediaType)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramArrayOfMediaType == null) {}
    for (;;)
    {
      return localArrayList;
      paramArrayOfMediaType = Arrays.asList(paramArrayOfMediaType);
      Iterator localIterator = getAll().iterator();
      while (localIterator.hasNext())
      {
        Resource localResource = (Resource)localIterator.next();
        if (paramArrayOfMediaType.contains(localResource.getMediaType())) {
          localArrayList.add(localResource);
        }
      }
    }
  }
  
  public boolean isEmpty()
  {
    return this.resources.isEmpty();
  }
  
  public Resource remove(String paramString)
  {
    return (Resource)this.resources.remove(paramString);
  }
  
  public void set(Collection<Resource> paramCollection)
  {
    this.resources.clear();
    addAll(paramCollection);
  }
  
  public void set(Map<String, Resource> paramMap)
  {
    this.resources = new HashMap(paramMap);
  }
  
  public int size()
  {
    return this.resources.size();
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\nl\siegmann\epublib\domain\Resources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */