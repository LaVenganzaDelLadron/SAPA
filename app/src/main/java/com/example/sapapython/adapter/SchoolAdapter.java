package com.example.sapapython.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sapapython.R;
import com.example.sapapython.SchoolInfoActivity;
import com.example.sapapython.model.School;
import com.example.sapapython.model.Student;

import java.util.List;

public class SchoolAdapter extends RecyclerView.Adapter<SchoolAdapter.SchoolViewHolder>{

    private Context context;
    private List<School> schoolList;


    public SchoolAdapter(Context context, List<School> schoolList) {
        this.context = context;
        this.schoolList = schoolList;
    }

    @NonNull
    @Override
    public SchoolViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_school, parent, false);
        return new SchoolViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolViewHolder holder, int position) {
        School school = schoolList.get(position);
        holder.schoolName.setText(school.getSchoolName());
        holder.schoolAddress.setText(school.getSchoolAddress());

        if (school.getImageBase64() != null) {
            byte[] decoded = Base64.decode(school.getImageBase64(), Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
            holder.schoolImage.setImageBitmap(bitmap);
        }

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, SchoolInfoActivity.class);
            intent.putExtra("school_id", school.getSchoolId());
            intent.putExtra("school_name", school.getSchoolName());
            intent.putExtra("school_number", school.getSchoolNumber());
            intent.putExtra("school_code", school.getSchoolCode());
            intent.putExtra("school_address", school.getSchoolAddress());
            intent.putExtra("school_bio", school.getSchoolBio());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return schoolList.size();
    }

    static class SchoolViewHolder extends RecyclerView.ViewHolder {
        TextView schoolName, schoolAddress;
        ImageView schoolImage;

        SchoolViewHolder(View itemView) {
            super(itemView);
            schoolName = itemView.findViewById(R.id.schoolName);
            schoolAddress = itemView.findViewById(R.id.schoolAddress);
            schoolImage = itemView.findViewById(R.id.profileImage);
        }
    }



}
