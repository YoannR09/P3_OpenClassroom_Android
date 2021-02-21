package com.example.mareu.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.R;
import com.example.mareu.di.UseCases;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MeetingAdapter extends
        RecyclerView.Adapter<MeetingAdapter.ViewHolder>{

    private List<Meeting> meetings;

    public MeetingAdapter(List<Meeting> m) {
        meetings = m;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView meetingInfo;
        public TextView meetingUsers;
        public FloatingActionButton deleteButton;
        public CardView cardViewColor;
        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            super(itemView);

            meetingInfo = (TextView) itemView.findViewById(R.id.item_meeting_info);
            meetingUsers = (TextView) itemView.findViewById(R.id.item_meeting_users);
            deleteButton = (FloatingActionButton) itemView.findViewById(R.id.item_meeting_delete);
            cardViewColor = (CardView) itemView.findViewById(R.id.card_color_meeting);
        }
    }

    // Usually involves inflating a layout from XML and returning the holder
    @NonNull
    @Override
    public MeetingAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.item_meeting, parent, false);

        // Return a new holder instance
        return new ViewHolder(contactView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(MeetingAdapter.ViewHolder holder, final int position) {
        final Meeting meeting = meetings.get(position);

        // Set item views based on your views and data model
        TextView infos = holder.meetingInfo;
        infos.setText(meeting.getRoom().getTitle()
        + " - " + meeting.getHour() + "h");
        if(meeting.getMin() == 0) {
            infos.setText(infos.getText() + "00");
        } else {
            infos.setText(infos.getText() + String.valueOf(meeting.getMin()));
        }
        infos.setText(infos.getText() + " - " + meeting.getSujet());
        TextView users = holder.meetingUsers;
        String listUsersText = "";
        int placement = 0;
        for(User u: meeting.getUsers()) {
            listUsersText += u.getMail();
            if(placement++ != meeting.getUsers().size() - 1) {
                listUsersText += ", ";
            }
        }
        int color = Color.rgb(
                meeting.getHour() * 10,
                meeting.getMin() * 4,
                meeting.getHour() * 8);
        holder.cardViewColor.setCardBackgroundColor(color);
        users.setText(listUsersText);
        holder.deleteButton.setOnClickListener((view -> {
            UseCases.deleteMeetingUseCase().delete(meeting);
            meetings.remove(meeting);
            notifyItemRemoved(position);
        }));
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return meetings.size();
    }
}
