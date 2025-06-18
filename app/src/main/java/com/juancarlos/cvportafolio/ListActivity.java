package com.juancarlos.cvportafolio;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
import com.juancarlos.cvportafolio.app.AppConfig;
import com.juancarlos.cvportafolio.app.BaseActivity;

public class ListActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Item> itemList = new ArrayList<>();
    private RequestQueue requestQueue;

    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lister_activity);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        requestQueue = Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, AppConfig.JSONURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = response.length() - 1; i >= 0; i--) {
                                JSONObject item = response.getJSONObject(i);

                                int id = item.getInt("id");
                                String imgPath = item.getString("imgPath");
                                boolean isBlog = item.getBoolean("isBlog");
                                String title = item.getString("title");
                                String description = item.getString("description");
                                String ghLink = item.optString("ghLink", "");
                                String demoLink = item.getString("demoLink");

                                itemList.add(new Item(id, imgPath, isBlog, title, description, ghLink, demoLink));
                            }

                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }

}
