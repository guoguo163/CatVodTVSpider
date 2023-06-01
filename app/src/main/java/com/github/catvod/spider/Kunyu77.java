package com.github.catvod.spider;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;

import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.utils.okhttp.OKCallBack;
import com.github.catvod.utils.okhttp.OkHttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.Response;

/* loaded from: classes.dex */
public class Kunyu77 extends Spider {
    private String c = "Dalvik/2.1.0 (Linux; U; Android " + Build.VERSION.RELEASE + "; " + Build.MODEL + " Build/" + Build.ID + ")";
    private String Q = "okhttp/3.12.0";

    private HashMap<String, String> Q(String str, String str2) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("user-agent", this.c);
        hashMap.put("TK", ay.Vc(str, ay.q));
        hashMap.put("t", str2);
        return hashMap;
    }

    private HashMap<String, String> r(String str, String str2) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("user-agent", this.Q);
        hashMap.put("TK", ay.Vc(str, ay.q));
        hashMap.put("t", str2);
        return hashMap;
    }

    protected String c(String str) {
        return str;
    }

    public String categoryContent(String str, String str2, boolean z, HashMap<String, String> hashMap) {
        String trim;
        try {
            String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
            String str3 = "http://api.tyun77.cn/api.php/provide/searchFilter?type_id=" + str + "&pagenum=" + str2 + "&pagesize=24";
            for (String str4 : hashMap.keySet()) {
                if (hashMap.get(str4).trim().length() != 0) {
                    str3 = str3 + "&" + str4 + "=" + URLEncoder.encode(trim);
                }
            }
            JSONObject jSONObject = new JSONObject(c(Vf.h(str3, Q(str3, valueOf)))).getJSONObject("data");
            JSONArray jSONArray = jSONObject.getJSONArray("result");
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("vod_id", jSONObject2.getString("id"));
                jSONObject3.put("vod_name", jSONObject2.getString("title"));
                jSONObject3.put("vod_pic", jSONObject2.getString("videoCover"));
                jSONObject3.put("vod_remarks", jSONObject2.getString("msg"));
                jSONArray2.put(jSONObject3);
            }
            JSONObject jSONObject4 = new JSONObject();
            int parseInt = Integer.parseInt(jSONObject.getString("page"));
            int i2 = jSONObject.getInt("total");
            int i3 = jSONObject.getInt("pagesize");
            jSONObject4.put("page", parseInt);
            jSONObject4.put("pagecount", i3);
            jSONObject4.put("limit", 24);
            jSONObject4.put("total", i2);
            jSONObject4.put("list", jSONArray2);
            return jSONObject4.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public String detailContent(List<String> list) {
        try {
            String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
            JSONObject jSONObject = new JSONObject(c(Vf.h("http://api.tyun77.cn/api.php/provide/videoDetail?ids=" + list.get(0) + "&pcode=010110002&version=2.0.4&devid=4ac3fe96a6133de96904b8d3c8cfe16d&package=com.sevenVideo.app.android&sys=android&sysver=7.1.2&brand=realme&model=RMX1931&sj=" + valueOf, Q("/api.php/provide/videoDetailrealme4ac3fe96a6133de96904b8d3c8cfe16d" + list.get(0) + "RMX1931com.sevenVideo.app.android010110002" + valueOf + "android7.1.22.0.4" + valueOf + "XSpeUFjJ", valueOf)))).getJSONObject("data");
            JSONObject jSONObject2 = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject3 = new JSONObject();
            String string = jSONObject.getString("videoName");
            jSONObject3.put("vod_id", jSONObject.getString("id"));
            jSONObject3.put("vod_name", string);
            jSONObject3.put("vod_pic", jSONObject.getString("videoCover"));
            jSONObject3.put("type_name", jSONObject.getString("subCategory"));
            jSONObject3.put("vod_year", jSONObject.getString("year"));
            jSONObject3.put("vod_area", jSONObject.getString("area"));
            jSONObject3.put("vod_remarks", jSONObject.getString("msg"));
            jSONObject3.put("vod_actor", jSONObject.getString("actor"));
            jSONObject3.put("vod_director", jSONObject.getString("director"));
            jSONObject3.put("vod_content", jSONObject.getString("brief").trim());
            String valueOf2 = String.valueOf(System.currentTimeMillis() / 1000);
            JSONArray jSONArray2 = new JSONObject(Vf.h("http://api.tyun77.cn/api.php/provide/videoPlaylist?ids=" + list.get(0) + "&pcode=010110002&version=2.0.4&devid=4ac3fe96a6133de96904b8d3c8cfe16d&package=com.sevenVideo.app.android&sys=android&sysver=7.1.2&brand=realme&model=RMX1931&sj=" + valueOf2, Q("/api.php/provide/videoPlaylistrealme4ac3fe96a6133de96904b8d3c8cfe16d" + list.get(0) + "RMX1931com.sevenVideo.app.android010110002" + valueOf2 + "android7.1.22.0.4" + valueOf2 + "XSpeUFjJ", valueOf2))).getJSONObject("data").getJSONArray("episodes");
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (int i = 0; i < jSONArray2.length(); i++) {
                JSONArray jSONArray3 = jSONArray2.getJSONObject(i).getJSONArray("playurls");
                for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                    JSONObject jSONObject4 = jSONArray3.getJSONObject(i2);
                    String string2 = jSONObject4.getString("playfrom");
                    ArrayList arrayList = (ArrayList) linkedHashMap.get(string2);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                        linkedHashMap.put(string2, arrayList);
                    }
                    String trim = jSONObject4.getString("title").replace(string, "").trim();
                    if (trim.isEmpty()) {
                        trim = (i + 1) + "";
                    }
                    arrayList.add(trim + "$" + jSONObject4.getString("playurl"));
                }
            }
            String join = TextUtils.join("$$$", linkedHashMap.keySet());
            StringBuilder sb = new StringBuilder();
            short size = (short) linkedHashMap.size();
            for (ArrayList arrayList2 : linkedHashMap.values()) {
                size = (short) (size - 1);
                sb.append(TextUtils.join("#", arrayList2));
                if (size > 0) {
                    sb.append("$$$");
                }
            }
            jSONObject3.put("vod_play_from", join);
            jSONObject3.put("vod_play_url", sb.toString());
            jSONArray.put(jSONObject3);
            jSONObject2.put("list", jSONArray);
            return jSONObject2.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public String homeContent(boolean z) {
        String str;
        String str2;
        String str3;
        JSONArray jSONArray;
        JSONArray jSONArray2;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONArray jSONArray3;
        JSONObject jSONObject3;
        Kunyu77 kunyu77 = this;
        String str4 = "2021";
        String str5 = "2023";
        String str6 = "data";
        try {
            JSONObject jSONObject4 = new JSONObject(kunyu77.c(Vf.h("http://api.tyun77.cn/api.php/provide/filter", kunyu77.Q("/api.php/provide/filter", String.valueOf(System.currentTimeMillis() / 1000))))).getJSONObject("data");
            Iterator<String> keys = jSONObject4.keys();
            JSONArray jSONArray4 = new JSONArray();
            JSONObject jSONObject5 = new JSONObject();
            JSONArray jSONArray5 = null;
            while (keys.hasNext()) {
                String next = keys.next();
                Iterator<String> it = keys;
                JSONObject jSONObject6 = jSONObject4;
                String string = jSONObject4.getJSONArray(next).getJSONObject(0).getString("cat");
                JSONObject jSONObject7 = new JSONObject();
                JSONObject jSONObject8 = jSONObject5;
                jSONObject7.put("type_id", next);
                jSONObject7.put("type_name", string);
                jSONArray4.put(jSONObject7);
                if (jSONArray5 == null) {
                    try {
                        try {
                            jSONArray2 = jSONArray5;
                        } catch (Exception e) {
                            e = e;
                            str = str4;
                            str2 = str5;
                            str3 = str6;
                            jSONArray = jSONArray4;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        str = str4;
                        str2 = str5;
                        str3 = str6;
                        jSONArray = jSONArray4;
                    }
                    try {
                        JSONObject jSONObject9 = new JSONObject(Vf.h("http://api.tyun77.cn/api.php/provide/searchFilter?type_id=0&pagenum=1&pagesize=1", kunyu77.Q("/api.php/provide/searchFilter", String.valueOf(System.currentTimeMillis() / 1000)))).getJSONObject(str6).getJSONObject("conditions");
                        JSONArray jSONArray6 = new JSONArray();
                        try {
                            JSONObject jSONObject10 = new JSONObject();
                            jSONObject10.put("key", "year");
                            jSONObject10.put("name", "年份");
                            JSONArray jSONArray7 = new JSONArray();
                            JSONObject jSONObject11 = new JSONObject();
                            jSONObject11.put("n", "全部");
                            jSONObject11.put("v", "");
                            jSONArray7.put(jSONObject11);
                            JSONObject jSONObject12 = new JSONObject();
                            jSONObject12.put("n", str5);
                            jSONObject12.put("v", str5);
                            jSONArray7.put(jSONObject12);
                            JSONObject jSONObject13 = new JSONObject();
                            jSONObject13.put("n", str4);
                            jSONObject13.put("v", str4);
                            jSONArray7.put(jSONObject13);
                            JSONArray jSONArray8 = jSONObject9.getJSONArray("y");
                            str = str4;
                            str2 = str5;
                            int i = 0;
                            while (true) {
                                try {
                                    str3 = str6;
                                    if (i >= jSONArray8.length()) {
                                        break;
                                    }
                                    try {
                                        jSONObject2 = jSONArray8.getJSONObject(i);
                                        jSONArray3 = jSONArray8;
                                        jSONObject3 = new JSONObject();
                                        jSONArray = jSONArray4;
                                    } catch (Exception e3) {
                                        e = e3;
                                        jSONArray = jSONArray4;
                                        jSONArray5 = jSONArray6;
                                        jSONObject = jSONObject8;
                                        SpiderDebug.log(e);
                                        jSONObject5 = jSONObject;
                                        keys = it;
                                        jSONObject4 = jSONObject6;
                                        str4 = str;
                                        str5 = str2;
                                        str6 = str3;
                                        jSONArray4 = jSONArray;
                                        kunyu77 = this;
                                    }
                                    try {
                                        jSONObject3.put("n", jSONObject2.getString("name"));
                                        jSONObject3.put("v", jSONObject2.getString("value"));
                                        jSONArray7.put(jSONObject3);
                                        i++;
                                        jSONArray8 = jSONArray3;
                                        str6 = str3;
                                        jSONArray4 = jSONArray;
                                    } catch (Exception e4) {
                                        e = e4;
                                        jSONArray5 = jSONArray6;
                                        jSONObject = jSONObject8;
                                        SpiderDebug.log(e);
                                        jSONObject5 = jSONObject;
                                        keys = it;
                                        jSONObject4 = jSONObject6;
                                        str4 = str;
                                        str5 = str2;
                                        str6 = str3;
                                        jSONArray4 = jSONArray;
                                        kunyu77 = this;
                                    }
                                } catch (Exception e5) {
                                    e = e5;
                                    str3 = str6;
                                    jSONArray = jSONArray4;
                                    jSONArray5 = jSONArray6;
                                    jSONObject = jSONObject8;
                                    SpiderDebug.log(e);
                                    jSONObject5 = jSONObject;
                                    keys = it;
                                    jSONObject4 = jSONObject6;
                                    str4 = str;
                                    str5 = str2;
                                    str6 = str3;
                                    jSONArray4 = jSONArray;
                                    kunyu77 = this;
                                }
                            }
                            jSONArray = jSONArray4;
                            jSONObject10.put("value", jSONArray7);
                            jSONArray6.put(jSONObject10);
                            JSONObject jSONObject14 = new JSONObject();
                            jSONObject14.put("key", "area");
                            jSONObject14.put("name", "地区");
                            JSONArray jSONArray9 = new JSONArray();
                            JSONObject jSONObject15 = new JSONObject();
                            jSONObject15.put("n", "全部");
                            jSONObject15.put("v", "");
                            jSONArray9.put(jSONObject15);
                            int i2 = 0;
                            for (JSONArray jSONArray10 = jSONObject9.getJSONArray("a"); i2 < jSONArray10.length(); jSONArray10 = jSONArray10) {
                                JSONObject jSONObject16 = jSONArray10.getJSONObject(i2);
                                JSONObject jSONObject17 = new JSONObject();
                                jSONObject17.put("n", jSONObject16.getString("name"));
                                jSONObject17.put("v", jSONObject16.getString("value"));
                                jSONArray9.put(jSONObject17);
                                i2++;
                            }
                            jSONObject14.put("value", jSONArray9);
                            jSONArray6.put(jSONObject14);
                            JSONObject jSONObject18 = new JSONObject();
                            jSONObject18.put("key", "category");
                            jSONObject18.put("name", "类型");
                            JSONArray jSONArray11 = new JSONArray();
                            JSONObject jSONObject19 = new JSONObject();
                            jSONObject19.put("n", "全部");
                            jSONObject19.put("v", "");
                            jSONArray11.put(jSONObject19);
                            JSONArray jSONArray12 = jSONObject9.getJSONArray("scat");
                            for (int i3 = 0; i3 < jSONArray12.length(); i3++) {
                                JSONObject jSONObject20 = jSONArray12.getJSONObject(i3);
                                JSONObject jSONObject21 = new JSONObject();
                                jSONObject21.put("n", jSONObject20.getString("name"));
                                jSONObject21.put("v", jSONObject20.getString("value"));
                                jSONArray11.put(jSONObject21);
                            }
                            jSONObject18.put("value", jSONArray11);
                            jSONArray6.put(jSONObject18);
                            jSONArray5 = jSONArray6;
                        } catch (Exception e6) {
                            e = e6;
                            str = str4;
                            str2 = str5;
                        }
                    } catch (Exception e7) {
                        e = e7;
                        str = str4;
                        str2 = str5;
                        str3 = str6;
                        jSONArray = jSONArray4;
                        jSONObject = jSONObject8;
                        jSONArray5 = jSONArray2;
                        SpiderDebug.log(e);
                        jSONObject5 = jSONObject;
                        keys = it;
                        jSONObject4 = jSONObject6;
                        str4 = str;
                        str5 = str2;
                        str6 = str3;
                        jSONArray4 = jSONArray;
                        kunyu77 = this;
                    }
                } else {
                    str = str4;
                    str2 = str5;
                    str3 = str6;
                    jSONArray = jSONArray4;
                }
                try {
                    if (jSONArray5.length() > 0) {
                        jSONObject = jSONObject8;
                        try {
                            jSONObject.put(next, jSONArray5);
                        } catch (Exception e8) {
                            e = e8;
                            SpiderDebug.log(e);
                            jSONObject5 = jSONObject;
                            keys = it;
                            jSONObject4 = jSONObject6;
                            str4 = str;
                            str5 = str2;
                            str6 = str3;
                            jSONArray4 = jSONArray;
                            kunyu77 = this;
                        }
                    } else {
                        jSONObject = jSONObject8;
                    }
                } catch (Exception e9) {
                    e = e9;
                    jSONObject = jSONObject8;
                    SpiderDebug.log(e);
                    jSONObject5 = jSONObject;
                    keys = it;
                    jSONObject4 = jSONObject6;
                    str4 = str;
                    str5 = str2;
                    str6 = str3;
                    jSONArray4 = jSONArray;
                    kunyu77 = this;
                }
                jSONObject5 = jSONObject;
                keys = it;
                jSONObject4 = jSONObject6;
                str4 = str;
                str5 = str2;
                str6 = str3;
                jSONArray4 = jSONArray;
                kunyu77 = this;
            }
            JSONObject jSONObject22 = jSONObject5;
            JSONObject jSONObject23 = new JSONObject();
            jSONObject23.put("class", jSONArray4);
            if (z) {
                jSONObject23.put("filters", jSONObject22);
            }
            return jSONObject23.toString();
        } catch (Throwable unused) {
            return "";
        }
    }

    public String homeVideoContent() {
        try {
            JSONArray jSONArray = new JSONArray();
            try {
                String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
                JSONArray jSONArray2 = new JSONObject(c(Vf.h("http://api.tyun77.cn/api.php/provide/homeBlock?type_id=0&pcode=010110002&version=2.0.4&devid=4ac3fe96a6133de96904b8d3c8cfe16d&package=com.sevenVideo.app.android&sys=android&sysver=7.1.2&brand=realme&model=RMX1931&sj=" + valueOf, Q("/api.php/provide/homeBlockrealme4ac3fe96a6133de96904b8d3c8cfe16dRMX1931com.sevenVideo.app.android010110002" + valueOf + "android7.1.22.0.4" + valueOf + "XSpeUFjJ", valueOf)))).getJSONObject("data").getJSONArray("blocks");
                for (int i = 0; i < jSONArray2.length(); i++) {
                    JSONObject jSONObject = jSONArray2.getJSONObject(i);
                    if (jSONObject.getString("block_name").startsWith("热播")) {
                        JSONArray jSONArray3 = jSONObject.getJSONArray("contents");
                        for (int i2 = 0; i2 < jSONArray3.length() && i2 < 3; i2++) {
                            JSONObject jSONObject2 = jSONArray3.getJSONObject(i2);
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("vod_id", jSONObject2.getString("id"));
                            jSONObject3.put("vod_name", jSONObject2.getString("title"));
                            jSONObject3.put("vod_pic", jSONObject2.getString("videoCover"));
                            jSONObject3.put("vod_remarks", jSONObject2.getString("msg"));
                            jSONArray.put(jSONObject3);
                        }
                    }
                }
            } catch (Exception unused) {
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("list", jSONArray);
            return jSONObject4.toString();
        } catch (Throwable unused2) {
            return "";
        }
    }

    public String playerContent(String str, String str2, List<String> list) {
        String next;
        try {
            String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
            JSONObject jSONObject = new JSONObject(c(Vf.h("http://api.tyun77.cn/api.php/provide/parserUrl?url=" + str2 + "&retryNum=0&pcode=010110002&version=2.0.4&devid=4ac3fe96a6133de96904b8d3c8cfe16d&package=com.sevenVideo.app.android&sys=android&sysver=7.1.2&brand=realme&model=RMX1931&sj=" + valueOf, Q("/api.php/provide/parserUrlrealme4ac3fe96a6133de96904b8d3c8cfe16dRMX1931com.sevenVideo.app.android010110002" + str2 + "android7.1.2" + str2 + "2.0.4" + valueOf + "XSpeUFjJ", valueOf)))).getJSONObject("data");
            JSONObject optJSONObject = jSONObject.optJSONObject("playHeader");
            String string = jSONObject.getString("url");
            JSONObject L = ay.L(string, Vf.h(string, Q(string, "")));
            if (L != null) {
                L.put("parse", 0);
                L.put("playUrl", "");
                if (optJSONObject != null) {
                    JSONObject jSONObject2 = L.getJSONObject("header");
                    Iterator<String> keys = optJSONObject.keys();
                    while (keys.hasNext()) {
                        jSONObject2.put(keys.next(), " " + optJSONObject.getString(next));
                    }
                    L.put("header", jSONObject2.toString());
                }
                return L.toString();
            }
        } catch (Throwable unused) {
        }
        try {
            if (ay.bq(str2)) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("parse", 1);
                jSONObject3.put("jx", "1");
                jSONObject3.put("url", str2);
                return jSONObject3.toString();
            }
            JSONObject jSONObject4 = new JSONObject();
            jSONObject4.put("parse", 0);
            jSONObject4.put("playUrl", "");
            jSONObject4.put("url", str2);
            return jSONObject4.toString();
        } catch (Throwable unused2) {
            return "";
        }
    }

    public String searchContent(String str, boolean z) {
        try {
            String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
            JSONArray jSONArray = new JSONObject(c(Vf.h("http://api.tyun77.cn/api.php/provide/searchVideo?pcode=010110002&version=2.0.4&devid=4ac3fe96a6133de96904b8d3c8cfe16d&package=com.sevenVideo.app.android&sys=android&sysver=7.1.2&brand=realme&model=RMX1931&sj=" + valueOf + "&searchName=" + str + "&pg=1", r("/api.php/provide/searchVideorealme4ac3fe96a6133de96904b8d3c8cfe16dRMX1931com.sevenVideo.app.android0101100021" + str + valueOf + "android7.1.202.0.4" + valueOf + "XSpeUFjJ", valueOf)))).getJSONArray("data");
            JSONArray jSONArray2 = new JSONArray();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSONObject jSONObject2 = new JSONObject();
                String string = jSONObject.getString("videoName");
                if (string.contains(str)) {
                    jSONObject2.put("vod_id", jSONObject.getString("id"));
                    jSONObject2.put("vod_name", string);
                    jSONObject2.put("vod_pic", jSONObject.getString("videoCover"));
                    jSONObject2.put("vod_remarks", jSONObject.getString("msg"));
                    jSONArray2.put(jSONObject2);
                }
            }
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("list", jSONArray2);
            return jSONObject3.toString();
        } catch (Throwable unused) {
            return "";
        }
    }
}
