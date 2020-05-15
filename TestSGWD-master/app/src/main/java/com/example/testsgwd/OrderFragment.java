package com.example.testsgwd;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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
import com.example.testsgwd.model.Categories;
import com.example.testsgwd.model.ProductDetail;
import com.example.testsgwd.model.Products;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class OrderFragment extends Fragment {

    Spinner spinnerDW, spinnerTypeDoor, spinnerProduct, spinnerColor;
    EditText tv_quantity;
    Button btn_subtraction, btn_add;
    int quantity = 0;

    String urlGetPro = "http://192.168.1.20:8080/test/getProduct.php";
    String urlGetProDetail = "http://192.168.1.20:8080/test/getProDetail.php";
    String urlGetProDoor = "http://192.168.1.20:8080/test/getProDoor.php";
    String urlGetProWindow = "http://192.168.1.20:8080/test/getProWindow.php";
    String urlGetCategories = "http://192.168.1.20:8080/test/getCategories.php";


    ArrayList<Products> arrayListPro;
    ArrayList<Products> arrayProDoorOrWindow;
    ArrayList<String> arrayProduct;
    ArrayList<ProductDetail> arrayListProDetail;
    ArrayList<Categories> arrayListCategories;
    ArrayList<String> tempArrCategory = new ArrayList<>();
    ArrayList<String> tempArrayPro = new ArrayList<>();
    ArrayList<String> tempArrayColor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_order, container, false);

        arrayListPro = new ArrayList<>();
        arrayProDoorOrWindow = new ArrayList<>();
        arrayListProDetail = new ArrayList<>();
        arrayListCategories = new ArrayList<>();
        GetProduct(urlGetPro);
        GetProDetail(urlGetProDetail);

        btn_subtraction = (Button) v.findViewById(R.id.btn_subtraction);
        btn_add = (Button) v.findViewById(R.id.btn_add);
        tv_quantity = (EditText) v.findViewById(R.id.tv_quantity);

        tv_quantity.setText(""+quantity);
        btn_subtraction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tv_quantity.getText().toString().equals(""))
                {
                    quantity = 0;
                }else{
                    quantity = Integer.parseInt(tv_quantity.getText().toString());
                }
                if(quantity<1){
                    quantity = 0;
                    tv_quantity.setText(""+quantity);
                }else{
                    quantity--;
                    tv_quantity.setText(""+quantity);
                }
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (tv_quantity.getText().toString().equals(""))
                {
                    quantity = 0;
                }else{
                    quantity = Integer.parseInt(tv_quantity.getText().toString());
                }
                quantity++;
                tv_quantity.setText(""+quantity);
            }
        });

        spinnerTypeDoor = (Spinner) v.findViewById(R.id.spinnerTypeDoor);
        spinnerProduct = (Spinner) v.findViewById(R.id.spinnerProduct);
        spinnerColor = (Spinner) v.findViewById(R.id.spinnerColor);
        GetCategories(urlGetCategories);
        tempArrCategory.add("Loại cửa");
        ArrayAdapter arrayAdapterTypeDoor = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, tempArrCategory);
        arrayAdapterTypeDoor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeDoor.setAdapter(arrayAdapterTypeDoor);

        spinnerTypeDoor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int sttCate = 0;

                for (Categories categories : arrayListCategories){
                    if(tempArrCategory.get(i).equals(categories.getCate_name())){
                        sttCate = categories.getCate_id();

                    }
                }
                


                tempArrayPro = new ArrayList<String>();
//                tempArrayPro.add("Chọn cửa");
                for (Products pro : arrayListPro){
                    if(pro.getmCateId() == sttCate){
                        tempArrayPro.add(pro.getmProName());
                    }
                }

                ArrayAdapter arrayAdapterProduct = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, tempArrayPro);
                arrayAdapterProduct.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerProduct.setAdapter(arrayAdapterProduct);

                spinnerProduct.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        int sttPro = 0;

                        for (Products pro : arrayListPro){
                            if(tempArrayPro.get(i).equals(pro.getmProName())){
                                sttPro = pro.getmProductsId();
                            }
                        }

                        tempArrayColor = new ArrayList<String>();
                        for (ProductDetail productDetail : arrayListProDetail){

                            if(productDetail.getmProID() == sttPro){

                                tempArrayColor.add(productDetail.getmProColor());
                                ArrayAdapter arrayAdapterColor = new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item, tempArrayColor);
                                arrayAdapterColor.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spinnerColor.setAdapter(arrayAdapterColor);
                            }

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi!",Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }



    private void GetProDoorOrWindow(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayProDoorOrWindow.add(new Products(object.getInt("prod_id"),
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

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi!",Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetCategories(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayListCategories.add(new Categories(
                                        object.getInt("cate_id"),
                                        object.getString("cate_name")));

                                tempArrCategory.add(object.getString("cate_name"));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
//                        tempArrCategory.remove(0);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi!",Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetProDetail(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i = 0; i < response.length(); i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                arrayListProDetail.add(new ProductDetail(object.getInt("prod_detail_id"),
                                        object.getString("prod_color"),
                                        object.getString("prod_price"),
                                        object.getString("prod_image"),
                                        object.getInt("prod_id")));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), "Lỗi!",Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

}
