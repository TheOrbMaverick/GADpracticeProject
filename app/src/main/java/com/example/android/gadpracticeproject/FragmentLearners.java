package com.example.android.gadpracticeproject;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLearners extends Fragment {


    public static String URL_DATA = "https://gadsapi.herokuapp.com/api/hours";

    public RecyclerView myRecyclerView;

    public  List<Learners> learners = new ArrayList<>();
    RecyclerViewAdapter adapter;


    public FragmentLearners() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_learners, container, false);

        myRecyclerView = v.findViewById(R.id.learners_recycler);
        myRecyclerView.setHasFixedSize(false);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RecyclerViewAdapter(getContext(), learners);
        myRecyclerView.setAdapter( adapter);

        learners = new ArrayList<>();
        extractdata();



     //   loadRecyclerViewData();



        return v;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*

        learners = new ArrayList<>();

        learners.add(new Learners("Mamudu", "156hrs","Nigeria","https://res.cloudinary.com/mikeattara/image/upload/v1596700848/Top-learner.png"));
        learners.add(new Learners("Yahaya", "970hrs","Nigeria", "https://res.cloudinary.com/mikeattara/image/upload/v1596700848/Top-learner.png"));
        Toast.makeText(getActivity(), "loaded data", Toast.LENGTH_SHORT).show();

        */

    //    loadRecyclerViewData();



    }

    private void extractdata(){

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading Data...");
        progressDialog.show();

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL_DATA, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                progressDialog.dismiss();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        Learners learner = new Learners();

                        learner.setName(object.get("name").toString());
                        learner.setHours(object.get("hours").toString());
                        learner.setCountry(object.get("country").toString());
                        learner.setBadgeUrl(object.get("badgeUrl").toString());

                        learners.add(learner);


                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }

                adapter.addData(learners);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

        queue.add(jsonArrayRequest);


    }

}
