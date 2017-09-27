package com.example.pratiksha.afinal;

/**
 * Created by pratiksha on 21/9/17.
 */

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import static com.example.pratiksha.afinal.R.id.textViewName2;

public class SuppliersListActivity extends AppCompatActivity {

    private AppCompatActivity activity = SuppliersListActivity.this;
    private AppCompatTextView textViewName22;
    private RecyclerView recyclerViewSuppliers;
    private List<Supplier> listSuppliers;
    private SuppliersRecyclerAdapter SuppliersRecyclerAdapter;
    private DatabaseHelper2 databaseHelper2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers_list);
        getSupportActionBar().setTitle("");
        initViews();
        initObjects();

    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        textViewName22 = (AppCompatTextView) findViewById(textViewName2);
        recyclerViewSuppliers = (RecyclerView) findViewById(R.id.recyclerViewSuppliers);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        listSuppliers = new ArrayList<>();
        SuppliersRecyclerAdapter = new SuppliersRecyclerAdapter(listSuppliers);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewSuppliers.setLayoutManager(mLayoutManager);
        recyclerViewSuppliers.setItemAnimator(new DefaultItemAnimator());
        recyclerViewSuppliers.setHasFixedSize(true);
        recyclerViewSuppliers.setAdapter(SuppliersRecyclerAdapter);
        databaseHelper2 = new DatabaseHelper2(activity);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName22.setText(emailFromIntent);

        getDataFromSQLite();
    }

    /**
     * This method is to fetch all user records from SQLite
     */
    private void getDataFromSQLite() {
        // AsyncTask is used that SQLite operation not blocks the UI Thread.
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                listSuppliers.clear();
                listSuppliers.addAll(databaseHelper2.getAllSuppliers());

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                SuppliersRecyclerAdapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
