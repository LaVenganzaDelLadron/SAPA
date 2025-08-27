package com.example.sapapython.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sapapython.R;
import com.example.sapapython.model.Student;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private Context context;
    private List<Student> studentList;
    public StudentAdapter(Context context, List<Student> studentList) {
        this.context = context;
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.studentName.setText(student.getStudent_firstname());
        holder.studentAddress.setText(student.getStudent_address());

        if (student.getImageBase64() != null) {
            byte[] decoded = Base64.decode(student.getImageBase64(), Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(decoded, 0, decoded.length);
            holder.studentImage.setImageBitmap(bitmap);
        }

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(context, "Selected", Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentName, studentAddress;
        ImageView studentImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentName);
            studentAddress = itemView.findViewById(R.id.studentAddress);
            studentImage = itemView.findViewById(R.id.profileImage);
        }
    }
}
