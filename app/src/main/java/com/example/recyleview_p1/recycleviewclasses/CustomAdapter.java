package com.example.recyleview_p1.recycleviewclasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.recyleview_p1.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {

    private ArrayList<DataModel> dataSet;       // Current displayed list
    private ArrayList<DataModel> fullDataSet;   // Backup for filtering

    public CustomAdapter(ArrayList<DataModel> dataSet) {
        this.dataSet = new ArrayList<>(dataSet);
        this.fullDataSet = new ArrayList<>(dataSet);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewDetail;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewTitle);
            textViewDetail = itemView.findViewById(R.id.textViewSubtitle);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        DataModel dataModel = dataSet.get(position);
        holder.textViewName.setText(dataModel.getName());
        holder.textViewDetail.setText(dataModel.getDetail());
        holder.imageView.setImageResource(dataModel.getImage());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                if (currentPosition != RecyclerView.NO_POSITION) {
                    DataModel clickedItem = dataSet.get(currentPosition);
                    String message = "Clicked on: " + clickedItem.getName();

                    LayoutInflater inflater = LayoutInflater.from(v.getContext());
                    View layout = inflater.inflate(R.layout.custom_toast, null);

                    ImageView toastImage = layout.findViewById(R.id.toast_image);
                    TextView toastText = layout.findViewById(R.id.toast_text);

                    toastImage.setImageResource(clickedItem.getImage());
                    toastText.setText(clickedItem.getName());

                    Toast toast = new Toast(v.getContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                ArrayList<DataModel> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {

                    filteredList.addAll(fullDataSet);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (DataModel item : fullDataSet) {
                        if (item.getName().toLowerCase().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }

                FilterResults results = new FilterResults();
                results.values = filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataSet.clear();
                dataSet.addAll((ArrayList<DataModel>) results.values);
                notifyDataSetChanged();
            }
        };
    }
}
