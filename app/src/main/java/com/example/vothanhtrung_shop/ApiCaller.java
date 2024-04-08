package com.example.vothanhtrung_shop;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class ApiCaller {

    private RequestQueue requestQueue;
    private static ApiCaller instance;
    private static Context ctx;

    public static String url = "http://192.168.1.8:8080/api";

    private ApiCaller(Context context) {
        ctx = context.getApplicationContext();  // Sử dụng getApplicationContext() để tránh leak memory
        requestQueue = getRequestQueue();
    }

    public static synchronized ApiCaller getInstance(Context context) {
        if (instance == null) {
            instance = new ApiCaller(context);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx);
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void makeStringRequest(String url, final ApiResponseListener<String> listener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Đảm bảo giải mã response sử dụng UTF-8
                        try {
                            String utf8Response = new String(response.getBytes("ISO-8859-1"), "UTF-8");
                            listener.onSuccess(utf8Response);
                        } catch (Exception e) {
                            listener.onError(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });
        addToRequestQueue(stringRequest);
    }

    public void makeJsonArrayRequest(String url, final ApiResponseListener<JSONArray> listener) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        listener.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error.getMessage());
            }
        });
        addToRequestQueue(jsonArrayRequest);
    }

    public interface ApiResponseListener<T> {
        void onSuccess(T response);

        void onError(String errorMessage);
    }

    public JSONArray stringToJsonArray(String jsonString) {
        JSONArray jsonArray = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("content");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }

    public void addUser(User user, final ApiResponseListener<User> listener) {
        String addUserUrl = url + "/users/adduser";

        StringRequest request = new StringRequest(Request.Method.POST, addUserUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            // Xử lý phản hồi từ máy chủ (lưu thông tin người dùng)
                            User savedUser = user;
                            listener.onSuccess(savedUser);
                        } catch (JSONException e) {
                            listener.onError("Lỗi kết nối đến sever.");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError("Không thể thêm user:" + error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", user.getUsername());
                params.put("email", user.getEmail());
                params.put("numphone", user.getNumphone());
                params.put("photo", user.getPhoto());
                params.put("pass", BCrypt.hashpw(user.getPass(), BCrypt.gensalt()));
                return params;
            }
        };

        addToRequestQueue(request);
    }
}