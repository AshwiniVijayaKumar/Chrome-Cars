package com.google.android.gms.internal;

public enum b
{
  private final String eN;
  
  static
  {
    bA = new b("CAMPAIGN_CONTENT_OVERRIDE", 20, "campaign_content_override");
    bB = new b("CAMPAIGN_COOKIE_TIMEOUT", 21, "campaign_cookie_timeout");
    bC = new b("CAMPAIGN_MEDIUM_KEY", 22, "campaign_medium_key");
    bD = new b("CAMPAIGN_MEDIUM_OVERRIDE", 23, "campaign_medium_override");
    bE = new b("CAMPAIGN_NAME_KEY", 24, "campaign_name_key");
    bF = new b("CAMPAIGN_NAME_OVERRIDE", 25, "campaign_name_override");
    bG = new b("CAMPAIGN_NO_KEY", 26, "campaign_no_key");
    bH = new b("CAMPAIGN_SOURCE_KEY", 27, "campaign_source_key");
    bI = new b("CAMPAIGN_SOURCE_OVERRIDE", 28, "campaign_source_override");
    bJ = new b("CAMPAIGN_TERM_KEY", 29, "campaign_term_key");
    bK = new b("CAMPAIGN_TERM_OVERRIDE", 30, "campaign_term_override");
    bL = new b("CAMPAIGN_TRACK", 31, "campaign_track");
    bM = new b("CATEGORY", 32, "category");
    bN = new b("CHECK_VALIDATION", 33, "check_validation");
    bO = new b("CLEAR_PERSISTENT_DATA_LAYER_PREFIX", 34, "clear_data_layer_prefix");
    bP = new b("CLICK_ID", 35, "click_id");
    bQ = new b("CLIENT_INFO", 36, "client_info");
    bR = new b("COMPANY", 37, "company");
    bS = new b("COMPONENT", 38, "component");
    bT = new b("CONTENT_DESCRIPTION", 39, "content_description");
    bU = new b("CONTENT_GROUP", 40, "content_group");
    bV = new b("CONVERSION_ID", 41, "conversion_id");
    bW = new b("COOKIE_DOMAIN", 42, "cookie_domain");
    bX = new b("COOKIE_EXPIRATION", 43, "cookie_expiration");
    bY = new b("COOKIE_NAME", 44, "cookie_name");
    bZ = new b("COOKIE_PATH", 45, "cookie_path");
    ca = new b("COOKIE_PATH_COPY", 46, "cookie_path_copy");
    cb = new b("COUNTRY", 47, "country");
    cc = new b("CURRENCY_CODE", 48, "currency_code");
    cd = new b("CUSTOM_VARS", 49, "custom_vars");
    ce = new b("CUSTOMER_ID", 50, "customer_id");
    cf = new b("DATA_LAYER_VERSION", 51, "data_layer_version");
    cg = new b("DATA_PROVIDER_ID", 52, "data_provider_id");
    ch = new b("DEBUG", 53, "debug");
    ci = new b("DECORATE_FORM", 54, "decorate_form");
    cj = new b("DECORATE_FORMS_AUTO_LINK", 55, "decorate_forms_auto_link");
    ck = new b("DECORATE_LINK", 56, "decorate_link");
    cl = new b("DEFAULT_PAGES", 57, "default_pages");
    cm = new b("DEFAULT_VALUE", 58, "default_value");
    cn = new b("DEPENDENCIES", 59, "dependencies");
    co = new b("DETECT_FLASH", 60, "detect_flash");
    cp = new b("DETECT_TITLE", 61, "detect_title");
    cq = new b("DIMENSION", 62, "dimension");
    cr = new b("DOMAIN_NAME", 63, "domain_name");
    cs = new b("DOUBLE_CLICK", 64, "double_click");
    ct = new b("ECOMMERCE_MACRO_DATA", 65, "ecommerce_macro_data");
    cu = new b("ECOMMERCE_USE_DATA_LAYER", 66, "ecommerce_use_data_layer");
    cv = new b("ELEMENT", 67, "element");
    cw = new b("EMAIL", 68, "email");
    cx = new b("EMPLOYEE_RANGE", 69, "employee_range");
    cy = new b("ENABLE_ECOMMERCE", 70, "enable_ecommerce");
    cz = new b("ESCAPE", 71, "escape");
    cA = new b("EVENT_ACTION", 72, "event_action");
    cB = new b("EVENT_CATEGORY", 73, "event_category");
    cC = new b("EVENT_LABEL", 74, "event_label");
    cD = new b("EVENT_VALUE", 75, "event_value");
    cE = new b("EXCEPTION_DESCRIPTION", 76, "exception_description");
    cF = new b("EXCEPTION_FATAL", 77, "exception_fatal");
    cG = new b("FIELDS_TO_SET", 78, "fields_to_set");
    cH = new b("FORM_OBJECT", 79, "form_object");
    cI = new b("FUNCTION", 80, "function");
    cJ = new b("FUNCTION_CALL_NAME", 81, "function_call_macro_name");
    cK = new b("GROUP", 82, "group");
    cL = new b("HIT_CALLBACK", 83, "hit_callback");
    cM = new b("HTML", 84, "html");
    cN = new b("ID", 85, "id");
    cO = new b("IGNORED_ORGANIC", 86, "ignored_organic");
    cP = new b("IGNORED_REF", 87, "ignored_ref");
    cQ = new b("IGNORE_CASE", 88, "ignore_case");
    cR = new b("INPUT", 89, "input");
    cS = new b("INPUT_FORMAT", 90, "input_format");
    cT = new b("INSTANCE_NAME", 91, "instance_name");
    cU = new b("INSTANCE_LABEL", 92, "instance_label");
    cV = new b("INTERVAL", 93, "interval");
    cW = new b("ITEM_SEPARATOR", 94, "item_separator");
    cX = new b("JAVASCRIPT", 95, "javascript");
    cY = new b("KEYWORD", 96, "keyword");
    cZ = new b("KEY_VALUE_SEPARATOR", 97, "key_value_separator");
    da = new b("LABEL", 98, "label");
    db = new b("LANGUAGE", 99, "language");
    dc = new b("LIMIT", 100, "limit");
    dd = new b("LINK", 101, "link");
    de = new b("LINK_BY_POST", 102, "link_by_post");
    df = new b("LINK_ID", 103, "link_id");
    dg = new b("LIVE_ONLY", 104, "live_only");
    dh = new b("LOCAL_GIF_PATH", 105, "local_gif_path");
    di = new b("LOCATION", 106, "location");
    dj = new b("MAP", 107, "map");
    dk = new b("MAX", 108, "max");
    dl = new b("MIN", 109, "min");
    dm = new b("METRIC", 110, "metric");
    dn = new b("NAME", 111, "name");
    do = new b("NAMESPACE_CODE", 112, "namespace_code");
    dp = new b("NAMESPACE_ID", 113, "namespace_id");
    dq = new b("NAMESPACE_VALUE", 114, "namespace_value");
    dr = new b("NONINTERACTION", 115, "noninteraction");
    ds = new b("NOT_DEFAULT_MACRO", 116, "not_default_macro");
    dt = new b("NO_PADDING", 117, "no_padding");
    du = new b("NUMBER", 118, "number");
    dv = new b("OPTOUT", 119, "optout");
    dw = new b("ORDER", 120, "order");
    dx = new b("ORDER_ID", 121, "order_id");
    dy = new b("ORDER_VALUE", 122, "order_value");
    dz = new b("ORDINAL", 123, "ordinal");
    dA = new b("ORGANIC", 124, "organic");
    dB = new b("OUTPUT_FORMAT", 125, "output_format");
    dC = new b("PAGE", 126, "page");
    dD = new b("PAGE_PATH", 127, "page_path");
    dE = new b("PARTITION", 128, "partition");
    dF = new b("PIXEL", 129, "pixel");
    dG = new b("PLATFORM", 130, "platform");
    dH = new b("PRICES", 131, "prices");
    dI = new b("PRIORITY", 132, "priority");
    dJ = new b("PRODUCT", 133, "product");
    dK = new b("PRODUCT_ID", 134, "product_id");
    dL = new b("PRODUCT_IDS", 135, "product_ids");
    dM = new b("PUSH_AFTER_EVALUATE", 136, "push_after_evaluate");
    dN = new b("QUANTITY", 137, "quantity");
    dO = new b("QUERY_KEY", 138, "query_key");
    dP = new b("REFERRER", 139, "referrer");
    dQ = new b("REFERRER_OVERRIDE", 140, "referrer_override");
    dR = new b("REVENUE", 141, "revenue");
    dS = new b("SAMPLE_RATE", 142, "sample_rate");
    dT = new b("SEND_HITS_TO_GOOGLE", 143, "send_hits_to_google");
    dU = new b("SESSION_CONTROL", 144, "session_control");
    dV = new b("SESSION_COOKIE_TIMEOUT", 145, "session_cookie_timeout");
    dW = new b("SITE_SPEED_SAMPLE_RATE", 146, "site_speed_sample_rate");
    dX = new b("SOCIAL_ACTION", 147, "social_action");
    dY = new b("SOCIAL_ACTION_TARGET", 148, "social_action_target");
    dZ = new b("SOCIAL_NETWORK", 149, "social_network");
    ea = new b("SOCIAL_USE_DATA_LAYER", 150, "social_use_data_layer");
    eb = new b("SERVER_SIDE", 151, "server_side");
    ec = new b("STANDARD_INDUSTRIAL_CLASSIFICATION", 152, "standard_industrial_classification");
    ed = new b("STRIP_WWW", 153, "strip_www");
    ee = new b("TAG_ID", 154, "tag_id");
    ef = new b("TARGET_URL", 155, "target_url");
    eg = new b("TIMING_CATEGORY", 156, "timing_category");
    eh = new b("TIMING_LABEL", 157, "timing_label");
    ei = new b("TIMING_SAMPLE_RATE", 158, "timing_sample_rate");
    ej = new b("TIMING_VALUE", 159, "timing_value");
    ek = new b("TIMING_VAR", 160, "timing_var");
    el = new b("TITLE", 161, "title");
    em = new b("TRACK_APPVIEW", 162, "track_appview");
    en = new b("TRACK_EVENT", 163, "track_event");
    eo = new b("TRACK_EXCEPTION", 164, "track_exception");
    ep = new b("TRACK_SOCIAL", 165, "track_social");
    eq = new b("TRACK_TIMING", 166, "track_timing");
    er = new b("TRACK_TRANSACTION", 167, "track_transaction");
    es = new b("TRACKER_NAME", 168, "tracker_name");
    et = new b("TRANSACTION_DATALAYER_MAP", 169, "transaction_datalayer_map");
    eu = new b("TRANSACTION_ID", 170, "transaction_id");
    ev = new b("TRANSACTION_ITEM_DATALAYER_MAP", 171, "transaction_item_datalayer_map");
    ew = new b("TRANSACTION_VARIABLE", 172, "transaction_variable");
    ex = new b("TYPE", 173, "type");
    ey = new b("UNREPEATABLE", 174, "unrepeatable");
    ez = new b("URL", 175, "url");
  }
  
  private b(String paramString)
  {
    this.eN = paramString;
  }
  
  public String toString()
  {
    return this.eN;
  }
}


/* Location:              C:\Users\ADMIN\Desktop\foss\dex2jar-2.0\classes-dex2jar.jar!\com\google\android\gms\internal\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */