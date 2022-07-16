package com.example.lab2_mob403.AsyncTank;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.example.lab2_mob403.Adapter.AdapterBai1;
import com.example.lab2_mob403.Fragment.Fragment_Bai1;
import com.example.lab2_mob403.Http.HttpHandler;
import com.example.lab2_mob403.Model.Bai1Model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AsyncTankBai1 extends AsyncTask<Void, Void, Void> {
    private static final String TAG = Fragment_Bai1.class.getSimpleName();
    public static String url = "https://tucaomypham.000webhostapp.com/android_networking_mob403/select_contacts.php";
    private List<Bai1Model> bai1ModelList;
    private ListView lvBai1;
    private ProgressDialog progressDialog;
    Context context;
    AdapterBai1 adapterBai1;

    public AsyncTankBai1(ListView lvBai1, Context context) {
        this.lvBai1 = lvBai1;
        this.context = context;
        bai1ModelList = new ArrayList<>();
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        adapterBai1 = new AdapterBai1(context, bai1ModelList);
        lvBai1.setAdapter(adapterBai1);
    }

    @Override
    protected Void doInBackground(Void... voids) {
        HttpHandler httpHandler = new HttpHandler();
        String jsonString = httpHandler.makeServiceCall(url);
        Log.e(TAG, "Trả về dữ liệu từ link: " + jsonString);
        if (jsonString != null) {
            try {
                JSONArray jsonArray = new JSONArray(jsonString);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject responseObject = jsonArray.getJSONObject(i);
                    int id = responseObject.getInt("id");
                    int phone = responseObject.getInt("phone");
                    String name = responseObject.getString("name");
                    String email = responseObject.getString("email");
                    String address = responseObject.getString("address");
                    String gender = responseObject.getString("gender");
                    String home = responseObject.getString("home");
                    String office = responseObject.getString("office");
                    Bai1Model bai1Model = new Bai1Model(id, phone, name, email, address, gender, home, office);
                    bai1ModelList.add(bai1Model);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "Json parsing error: " + e.getMessage());
            }
        } else {
            Log.e(TAG, "Không thể lấy json từ server");
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Vui lòng chờ ... ");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
}
