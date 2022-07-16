package com.example.lab2_mob403.Fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.lab2_mob403.AppController.AppController;
import com.example.lab2_mob403.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Fragment_Bai2 extends Fragment {
    Button btnJsonObject, btnJsonArray;
    TextView tvResponse;
    private String urlJsonObject = "https://tucaomypham.000webhostapp.com/android_networking_mob403/person_json_object.json";
    private String urlJsonArray = "https://tucaomypham.000webhostapp.com/android_networking_mob403/person_array.json";
    private static final String TAG = Fragment_Bai2.class.getSimpleName();
    private ProgressDialog progressDialog;
    private String jsonResponse;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bai2, container, false);

        initViewById(view);

        initClickListener();

        initProgressDialog();

        return view;
    }

    private void initProgressDialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Vui lòng chờ ...");
        progressDialog.setCancelable(false);
    }

    private void initClickListener() {
        this.btnJsonArray.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonArrayResponse();

            }
        });
        this.btnJsonObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jsonObjectResponse();
            }
        });
    }

    private void jsonObjectResponse() {
        showDialog();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(urlJsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d(TAG, response.toString());
                try {
                    jsonResponse = "";
                    String name = response.getString("name");
                    String email = response.getString("email");
                    JSONObject jsonObject = response.getJSONObject("phone");
                    String home = jsonObject.getString("home");
                    String mobile = jsonObject.getString("mobile");
                    jsonResponse += "Name: " + name + "\n\n";
                    jsonResponse += "Email: " + email + "\n\n";
                    jsonResponse += "Phone: " + home + " - " + mobile + "\n\n";
                    tvResponse.setText(jsonResponse);
                    hideDialog();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    hideDialog();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });
        AppController.getInstance().addToResponseQueue(jsonObjectRequest);
    }

    private void jsonArrayResponse() {
        showDialog();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(urlJsonArray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e(TAG, response.toString());
                Log.e("SizeResponese", String.valueOf(response.length()));
                try {
                    jsonResponse = "";
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = (JSONObject) response.get(i);
                        String name = jsonObject.getString("name");
                        String email = jsonObject.getString("email");
                        JSONObject phone = jsonObject.getJSONObject("phone");
                        String home = phone.getString("home");
                        String mobile = phone.getString("mobile");
                        jsonResponse += "Name: " + name + "\n\n";
                        jsonResponse += "Email: " + email + "\n\n";
                        jsonResponse += "Phone: " + home + " - " + mobile + "\n\n";
                    }
                    tvResponse.setText(jsonResponse);
                    hideDialog();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    hideDialog();
                }
            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Lỗi: " + error.getMessage());
                Toast.makeText(getContext(), "Lỗi: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                hideDialog();
            }
        });
        AppController.getInstance().addToResponseQueue(jsonArrayRequest);
    }

    private void showDialog() {
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    private void hideDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    private void initViewById(View view) {
        btnJsonObject = view.findViewById(R.id.btn_ObjectJson);
        btnJsonArray = view.findViewById(R.id.btn_ArrayJson);
        tvResponse = view.findViewById(R.id.tv_response);
    }
}