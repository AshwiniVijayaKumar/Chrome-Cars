package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public final class ht
  implements SafeParcelable
{
  public static final hu CREATOR = new hu();
  public static final ht LH = aI("accounting");
  public static final ht LI = aI("airport");
  public static final ht LJ = aI("amusement_park");
  public static final ht LK = aI("aquarium");
  public static final ht LL = aI("art_gallery");
  public static final ht LM = aI("atm");
  public static final ht LN = aI("bakery");
  public static final ht LO = aI("bank");
  public static final ht LP = aI("bar");
  public static final ht LQ = aI("beauty_salon");
  public static final ht LR = aI("bicycle_store");
  public static final ht LS = aI("book_store");
  public static final ht LT = aI("bowling_alley");
  public static final ht LU = aI("bus_station");
  public static final ht LV = aI("cafe");
  public static final ht LW = aI("campground");
  public static final ht LX = aI("car_dealer");
  public static final ht LY = aI("car_rental");
  public static final ht LZ = aI("car_repair");
  public static final ht MA;
  public static final ht MB;
  public static final ht MC;
  public static final ht MD;
  public static final ht ME;
  public static final ht MF;
  public static final ht MG;
  public static final ht MH;
  public static final ht MI;
  public static final ht MJ;
  public static final ht MK;
  public static final ht ML;
  public static final ht MM;
  public static final ht MN;
  public static final ht MO;
  public static final ht MP;
  public static final ht MQ;
  public static final ht MR;
  public static final ht MS;
  public static final ht MT;
  public static final ht MU;
  public static final ht MV;
  public static final ht MW;
  public static final ht MX;
  public static final ht MY;
  public static final ht MZ;
  public static final ht Ma = aI("car_wash");
  public static final ht Mb = aI("casino");
  public static final ht Mc = aI("cemetary");
  public static final ht Md = aI("church");
  public static final ht Me = aI("city_hall");
  public static final ht Mf = aI("clothing_store");
  public static final ht Mg = aI("convenience_store");
  public static final ht Mh = aI("courthouse");
  public static final ht Mi = aI("dentist");
  public static final ht Mj = aI("department_store");
  public static final ht Mk = aI("doctor");
  public static final ht Ml = aI("electrician");
  public static final ht Mm = aI("electronics_store");
  public static final ht Mn = aI("embassy");
  public static final ht Mo = aI("establishment");
  public static final ht Mp = aI("finance");
  public static final ht Mq = aI("fire_station");
  public static final ht Mr = aI("florist");
  public static final ht Ms = aI("food");
  public static final ht Mt = aI("funeral_home");
  public static final ht Mu = aI("furniture_store");
  public static final ht Mv = aI("gas_station");
  public static final ht Mw = aI("general_contractor");
  public static final ht Mx = aI("grocery_or_supermarket");
  public static final ht My = aI("gym");
  public static final ht Mz = aI("hair_care");
  public static final ht NA;
  public static final ht NB;
  public static final ht NC;
  public static final ht ND;
  public static final ht NE;
  public static final ht NF;
  public static final ht NG;
  public static final ht NH;
  public static final ht NI;
  public static final ht NJ;
  public static final ht NK;
  public static final ht NL;
  public static final ht NM;
  public static final ht NN;
  public static final ht NO;
  public static final ht NP;
  public static final ht NQ;
  public static final ht NR;
  public static final ht NS;
  public static final ht NT;
  public static final ht NU;
  public static final ht NV;
  public static final ht NW;
  public static final ht NX;
  public static final ht NY;
  public static final ht NZ;
  public static final ht Na;
  public static final ht Nb;
  public static final ht Nc;
  public static final ht Nd;
  public static final ht Ne;
  public static final ht Nf;
  public static final ht Ng;
  public static final ht Nh;
  public static final ht Ni;
  public static final ht Nj;
  public static final ht Nk;
  public static final ht Nl;
  public static final ht Nm;
  public static final ht Nn;
  public static final ht No;
  public static final ht Np;
  public static final ht Nq;
  public static final ht Nr;
  public static final ht Ns;
  public static final ht Nt;
  public static final ht Nu;
  public static final ht Nv;
  public static final ht Nw;
  public static final ht Nx;
  public static final ht Ny;
  public static final ht Nz;
  public static final ht Oa;
  public static final ht Ob;
  final String Oc;
  final int wj;
  
  static
  {
    MA = aI("hardware_store");
    MB = aI("health");
    MC = aI("hindu_temple");
    MD = aI("home_goods_store");
    ME = aI("hospital");
    MF = aI("insurance_agency");
    MG = aI("jewelry_store");
    MH = aI("laundry");
    MI = aI("lawyer");
    MJ = aI("library");
    MK = aI("liquor_store");
    ML = aI("local_government_office");
    MM = aI("locksmith");
    MN = aI("lodging");
    MO = aI("meal_delivery");
    MP = aI("meal_takeaway");
    MQ = aI("mosque");
    MR = aI("movie_rental");
    MS = aI("movie_theater");
    MT = aI("moving_company");
    MU = aI("museum");
    MV = aI("night_club");
    MW = aI("painter");
    MX = aI("park");
    MY = aI("parking");
    MZ = aI("pet_store");
    Na = aI("pharmacy");
    Nb = aI("physiotherapist");
    Nc = aI("place_of_worship");
    Nd = aI("plumber");
    Ne = aI("police");
    Nf = aI("post_office");
    Ng = aI("real_estate_agency");
    Nh = aI("restaurant");
    Ni = aI("roofing_contractor");
    Nj = aI("rv_park");
    Nk = aI("school");
    Nl = aI("shoe_store");
    Nm = aI("shopping_mall");
    Nn = aI("spa");
    No = aI("stadium");
    Np = aI("storage");
    Nq = aI("store");
    Nr = aI("subway_station");
    Ns = aI("synagogue");
    Nt = aI("taxi_stand");
    Nu = aI("train_station");
    Nv = aI("travel_agency");
    Nw = aI("university");
    Nx = aI("veterinary_care");
    Ny = aI("zoo");
    Nz = aI("administrative_area_level_1");
    NA = aI("administrative_area_level_2");
    NB = aI("administrative_area_level_3");
    NC = aI("colloquial_area");
    ND = aI("country");
    NE = aI("floor");
    NF = aI("geocode");
    NG = aI("intersection");
    NH = aI("locality");
    NI = aI("natural_feature");
    NJ = aI("neighborhood");
    NK = aI("political");
    NL = aI("point_of_interest");
    NM = aI("post_box");
    NN = aI("postal_code");
    NO = aI("postal_code_prefix");
    NP = aI("postal_town");
    NQ = aI("premise");
    NR = aI("room");
    NS = aI("route");
    NT = aI("street_address");
    NU = aI("sublocality");
    NV = aI("sublocality_level_1");
    NW = aI("sublocality_level_2");
    NX = aI("sublocality_level_3");
    NY = aI("sublocality_level_4");
    NZ = aI("sublocality_level_5");
    Oa = aI("subpremise");
    Ob = aI("transit_station");
  }
  
  ht(int paramInt, String paramString)
  {
    er.aj(paramString);
    this.wj = paramInt;
    this.Oc = paramString;
  }
  
  public static ht aI(String paramString)
  {
    return new ht(0, paramString);
  }
  
  public int describeContents()
  {
    hu localhu = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof ht)) && (this.Oc.equals(((ht)paramObject).Oc));
  }
  
  public int hashCode()
  {
    return this.Oc.hashCode();
  }
  
  public String toString()
  {
    return this.Oc;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    hu localhu = CREATOR;
    hu.a(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\ht.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */