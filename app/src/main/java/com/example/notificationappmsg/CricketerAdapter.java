package com.example.notificationappmsg;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CricketerAdapter extends RecyclerView.Adapter<CricketerAdapter.CricketerView> {

    ArrayList<Cricketer> cricketersList = new ArrayList<>();

    public CricketerAdapter(ArrayList<Cricketer> cricketersList) {
        this.cricketersList = cricketersList;
    }

    @NonNull
    @Override
    public CricketerView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cricketer,parent,false);

        return new CricketerView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CricketerView holder, int position) {

        Cricketer cricketer = cricketersList.get(position);
        holder.textCricketerName.setText(cricketer.getModelNumber());
        holder.textTeamName.setText(cricketer.getModelName());


    }

    @Override
    public int getItemCount() {
        return cricketersList.size();
    }

    public class CricketerView extends RecyclerView.ViewHolder{

        TextView textCricketerName,textTeamName;
        public CricketerView(@NonNull View itemView) {
            super(itemView);

            textCricketerName = (TextView)itemView.findViewById(R.id.text_cricketer_name);
            textTeamName = (TextView)itemView.findViewById(R.id.text_team_name);

        }
    }

}
