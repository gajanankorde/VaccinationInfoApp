package com.gajanan.vaccinationinfoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_fetchDetails,btn_selectDate;
    RecyclerView rv_vaccination;
    ArrayList<VaccinationModel> vaccinationArrayList;
    VaccinationRecyclerAdapter vaccinationRecyclerAdapter;
    EditText et_pinCode;
    private Calendar date1;
    private String selectedVaccinationDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        btnListeners();

        vaccinationArrayList=new ArrayList<>();

    }

    private void btnListeners() {
        btn_fetchDetails.setOnClickListener(this);
        btn_selectDate.setOnClickListener(this);
    }


    private void bindViews() {
        btn_fetchDetails=findViewById(R.id.btn_fetchDetails);
        rv_vaccination=findViewById(R.id.rv_vaccination);
        et_pinCode=findViewById(R.id.et_pinCode);
        btn_selectDate=findViewById(R.id.btn_selectDate);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_fetchDetails:

                String pinCode=et_pinCode.getText().toString();

                fetchVaccinationDetails(pinCode);

                break;

            case R.id.btn_selectDate:

                chooseVaccinationDate();

                break;
            default:
                break;
        }

    }

    public void chooseVaccinationDate() {
        final Calendar currentDate = Calendar.getInstance();
        date1 = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                date1.set(year, monthOfYear, dayOfMonth);

                Calendar c = Calendar.getInstance();
                c.set(year, monthOfYear, dayOfMonth);

                //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                selectedVaccinationDate = sdf.format(c.getTime());

                Toast.makeText(MainActivity.this, selectedVaccinationDate, Toast.LENGTH_LONG).show();

                // Toast.makeText(getActivity(), "formattedDate" + formattedDate1, Toast.LENGTH_LONG).show();
                btn_selectDate.setText(selectedVaccinationDate);
                //use this date as per your requirement
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, dateSetListener, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH));
        // datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
        datePickerDialog.show();
    }


    private void fetchVaccinationDetails(String pinCode) {
        //JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API.VACCINATION_CENTERS+"pincode=431001&date=26-05-2021", null, new Response.Listener<JSONObject>() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, API.VACCINATION_CENTERS+"pincode="+pinCode+"&date="+selectedVaccinationDate, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("TAG", "onResponse: " + response);
                try {
                    JSONArray arr = response.getJSONArray("sessions");
                    Log.d("TAG", "onResponse: length" + arr.length());
                    for (int i = 0; i < arr.length(); i++) {
                        JSONObject vaccineObject = arr.getJSONObject(i);
                        Log.d("TAG", "onResponse: vaccineObject" + vaccineObject.toString());
                        vaccinationArrayList.add(new VaccinationModel(String.valueOf(vaccineObject.getInt("center_id")),
                                vaccineObject.getString("name"),
                                vaccineObject.getString("address"),
                                String.valueOf(vaccineObject.getString("pincode")),
                                vaccineObject.getString("date"),
                                vaccineObject.getString("from"),
                                vaccineObject.getString("to"),
                                vaccineObject.getString("fee_type"),
                                vaccineObject.getString("fee"),
                                String.valueOf(vaccineObject.getString("min_age_limit")),
                                vaccineObject.getString("vaccine")
                        ));

                    }
                    bindList(vaccinationArrayList);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG", "onResponse: error" + error);
            }
        });
        VolleyInstance.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);
    }

    private void bindList(ArrayList<VaccinationModel> vaccinationArrayList) {

        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        rv_vaccination.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(MainActivity.this,
                linearLayoutManager.getOrientation());
        rv_vaccination.addItemDecoration(dividerItemDecoration);

        vaccinationRecyclerAdapter=new VaccinationRecyclerAdapter(MainActivity.this, vaccinationArrayList, new VaccinationRecyclerAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        rv_vaccination.setAdapter(vaccinationRecyclerAdapter); // set the Adapter to RecyclerView

    }
}