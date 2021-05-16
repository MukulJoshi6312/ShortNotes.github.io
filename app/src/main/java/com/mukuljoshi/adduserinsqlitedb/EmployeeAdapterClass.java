package com.mukuljoshi.adduserinsqlitedb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapterClass extends RecyclerView.Adapter<EmployeeAdapterClass.ViewHolder> {

    List<EmployeeModelClass> list;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public EmployeeAdapterClass(List<EmployeeModelClass> list, Context context) {
        this.list = list;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.employee_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final EmployeeModelClass employeeModelClass = list.get(position);
        //  holder.textId.setText(Integer.toString(employeeModelClass.getId()));
        holder.editName.setText(employeeModelClass.getName());
        holder.editEmail.setText(employeeModelClass.getEmail());


        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringName = holder.editName.getText().toString();
                String stringEmail = holder.editEmail.getText().toString();

                databaseHelperClass.updateEmployee(new EmployeeModelClass(stringName, stringEmail,
                        employeeModelClass.getId()));
                notifyDataSetChanged();

                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());

            }
        });

        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context)
                        .setTitle("Delete Note")
                        .setMessage("Are you sure you want to delete this Note?")
                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                databaseHelperClass.deleteEmployee(employeeModelClass.getId());
                                list.remove(position);
                                notifyDataSetChanged();
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        holder.shear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Shear data", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                String title = holder.editName.getText().toString();
                String description = holder.editEmail.getText().toString();
                intent.setType("text/plan");
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Title = " + title + "\n" + "\n" + "Description = " + description);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textId;
        EditText editName, editEmail;
        Button editBtn, deleteBtn;
        ImageView shear;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // textId = itemView.findViewById(R.id.text_id);
            editName = itemView.findViewById(R.id.text_name);
            editEmail = itemView.findViewById(R.id.text_email);
            editBtn = itemView.findViewById(R.id.editBtn);
            deleteBtn = itemView.findViewById(R.id.deleteBtn);
            shear = itemView.findViewById(R.id.shear);
        }
    }
}
