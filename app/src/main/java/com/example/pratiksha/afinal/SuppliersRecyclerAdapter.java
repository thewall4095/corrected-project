package com.example.pratiksha.afinal;

/**
 * Created by pratiksha on 21/9/17.
 */

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class SuppliersRecyclerAdapter extends RecyclerView.Adapter<SuppliersRecyclerAdapter.SupplierViewHolder> {

    private List<Supplier> listSuppliers;

    public SuppliersRecyclerAdapter(List<Supplier> listSuppliers) {
        this.listSuppliers = listSuppliers;
    }

    @Override
    public SupplierViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_suppliers_recycler, parent, false);

        return new SupplierViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SupplierViewHolder holder, int position) {
        holder.textViewNameS.setText(listSuppliers.get(position).getName2());
        holder.textViewEmailS.setText(listSuppliers.get(position).getEmail2());
        holder.textViewPasswordS.setText(listSuppliers.get(position).getPassword2());
    }

    @Override
    public int getItemCount() {
        Log.v(SuppliersRecyclerAdapter.class.getSimpleName(),""+listSuppliers.size());
        return listSuppliers.size();
    }


    /**
     * ViewHolder class
     */
    public class SupplierViewHolder extends RecyclerView.ViewHolder {

        public AppCompatTextView textViewNameS;
        public AppCompatTextView textViewEmailS;
        public AppCompatTextView textViewPasswordS;

        public SupplierViewHolder(View view) {
            super(view);
            textViewNameS = (AppCompatTextView) view.findViewById(R.id.textViewName2);
            textViewEmailS = (AppCompatTextView) view.findViewById(R.id.textViewEmail2);
            textViewPasswordS= (AppCompatTextView) view.findViewById(R.id.textViewPassword2);
        }
    }


}