package com.example.testsgwd;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.testsgwd.Adapter.ProductAdapter;
import com.example.testsgwd.model.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StoreFragment extends Fragment {
    String urlGetPro = "http://192.168.1.20:8080/test/getProduct.php";


    GridView gvPro;
    ArrayList<Products> arrayListPro;
    ProductAdapter productAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_store, container, false);

        gvPro = (GridView) v.findViewById(R.id.gv_product);
        arrayListPro = new ArrayList<>();

        productAdapter = new ProductAdapter(getActivity(), R.layout.row_item_product, arrayListPro);
        gvPro.setAdapter(productAdapter);

        GetProduct(urlGetPro);


        return v;
    }

    private void GetProduct(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayListPro.add(new Products(object.getInt("prod_id"),
                                        object.getString("prod_code"),
                                        object.getString("prod_name"),
                                        object.getString("prod_description"),
                                        object.getString("prod_status"),
                                        object.getString("prod_unit"),
                                        object.getString("prod_warranty"),
                                        object.getString("prod_price"),
                                        object.getString("prod_img"),
                                        object.getInt("prod_doororwindow"),
                                        object.getInt("cate_id")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        productAdapter.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi!",Toast.LENGTH_SHORT).show();

                    }
                }
        );
        requestQueue.add(jsonArrayRequest);
    }
}
