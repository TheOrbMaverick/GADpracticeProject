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
public class FragmentSkillIQ extends Fragment {


    public static String URL_DATA = "https://gadsapi.herokuapp.com/api/skilliq";

    public RecyclerView mRecyclerView;

    public List<SkillIq> skillIqs = new ArrayList<>();
    SkillRecyclerAdapter adapter;


    public FragmentSkillIQ() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_skill, container, false);

        mRecyclerView = v.findViewById(R.id.skill_recycler);
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new SkillRecyclerAdapter(getContext(), skillIqs);
        mRecyclerView.setAdapter( adapter);

        skillIqs = new ArrayList<>();
        extractSkillData();



        return v;
    }

    private void extractSkillData(){

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
                        SkillIq skillIq = new SkillIq();

                        skillIq.setName(object.get("name").toString());
                        skillIq.setScore(object.get("score").toString());
                        skillIq.setCountry(object.get("country").toString());
                        skillIq.setBadgeUrl(object.get("badgeUrl").toString());

                        skillIqs.add(skillIq);

                        Toast.makeText(getActivity(), "loaded data", Toast.LENGTH_SHORT).show();


                    } catch (JSONException e) {
                        Toast.makeText(getActivity(), "Cant load data", Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }

                }

                adapter.addData(skillIqs);

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
