package com.example.sapapython.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.sapapython.R;
import com.example.sapapython.model.Hospital;
import java.util.List;

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.HospitalViewHolder> {

    private Context context;
    private List<Hospital> hospitalList;

    public HospitalAdapter(Context context, List<Hospital> hospitalList) {
        this.context = context;
        this.hospitalList = hospitalList;
    }

    @Override
    public HospitalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_hospital, parent, false);
        return new HospitalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HospitalViewHolder holder, int position) {
        Hospital hospital = hospitalList.get(position);
        holder.hospitalName.setText(hospital.getFirstName() + " " + hospital.getMiddleName() + " " + hospital.getLastName());
        holder.hospitalAddress.setText(hospital.getLastName());

        if (hospital.getImageBase64() != null) {
            byte[] decoded = Base64.decode(hospital.getImageBase64(), Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
            holder.hospitalImage.setImageBitmap(bitmap);
        }else{
            holder.hospitalImage.setImageResource(R.drawable.hospitals);
        }
    }

    @Override
    public int getItemCount() {
        return hospitalList.size();
    }

    static class HospitalViewHolder extends RecyclerView.ViewHolder {
        TextView hospitalName, hospitalAddress;
        ImageView hospitalImage;

        HospitalViewHolder(View itemView) {
            super(itemView);
            hospitalName = itemView.findViewById(R.id.hospitalName);
            hospitalAddress = itemView.findViewById(R.id.hospitalAddress);
            hospitalImage = itemView.findViewById(R.id.profileImage);
        }
    }
}
