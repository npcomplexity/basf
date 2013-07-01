package com.booteak.basf.advmat.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import us.monoid.json.JSONArray;
import us.monoid.json.JSONException;
import us.monoid.json.JSONObject;

public class JSONToCollectionUtil {
	public static Map<String, Object> toMap(JSONObject object) throws JSONException
	{
	    Map<String, Object> map = new HashMap<String, Object>();
	    Iterator<?> keys = object.keys();
	    while (keys.hasNext())
	    {
	        String key = (String) keys.next();
	        map.put(key, fromJson(object.get(key)));
	    }
	    return map;
	}

	public static List toList(JSONArray array) throws JSONException
	{
	    List list = new ArrayList();
	    for (int i = 0; i < array.length(); i++)
	    {
	        list.add(fromJson(array.get(i)));
	    }
	    return list;
	}

	private static Object fromJson(Object json) throws JSONException
	{
	    if (json instanceof JSONObject)
	    {
	        return toMap((JSONObject) json);
	    } else if (json instanceof JSONArray)
	    {
	        return toList((JSONArray) json);
	    } else
	    {
	        return json;
	    }
	}

}
