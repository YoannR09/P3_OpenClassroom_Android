package com.example.mareu;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.NumberPicker;

import com.example.mareu.di.UseCases;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Room;
import com.example.mareu.ui.AddMeetingActivity;
import com.example.mareu.ui.adapter.MeetingAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity  implements AdapterView.OnItemClickListener {

    private MeetingAdapter adapter;
    private ArrayList<Meeting> meetings = new ArrayList<>();
    private AutoCompleteTextView actv;
    private AutoCompleteTextView actvHour;
    private MaterialToolbar toolbar;
    private int roomFilter;
    private int hourFilter;

    /**
     * This method has called on init class
     * Here we init every element on page
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.topAppBar);
        toolbar.inflateMenu(R.menu.menu_filter);

        toolbar.setOnMenuItemClickListener(item -> {
            if(item.getItemId()==R.id.action_filter) {
                new FilterActionListener(this).onShow();
            }
            return false;
        });

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);
        refreshData();

        adapter = new MeetingAdapter(meetings);
        rvContacts.setAdapter(adapter);
        rvContacts.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton fab = findViewById(R.id.button_adding_meeting);
        fab.setOnClickListener(new AddMeetingActionListener(this));
    }

    /**
     * Refresh adapter to display dynamic list
     */
    public void refreshAdapter() {
        refreshData();
        adapter.notifyDataSetChanged();
    }

    /**
     * Refresh list data contains on adapter
     */
    public void refreshData() {
        meetings.clear();
        meetings.addAll(UseCases.getMeetingsUseCase().call());
    }

    /**
     * Notif when the user return on main activity to refresh adapter
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 999) {
            refreshAdapter();
        }
    }

    /**
     * This method has call when has execute on element view
     * Here we catch the room select click
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        boolean roomSelect = false;
        for(Room r: UseCases.getRoomsUseCase().call()) {
            if (parent.getItemAtPosition(position).toString().equals(r.getTitle())) {
                roomFilter = r.getId();
                roomSelect = true;
            }
        }
        if(!roomSelect) {
            hourFilter = position;
        }
    }

    /**
     * This class show activity for create new meeting
     */
    public class AddMeetingActionListener implements View.OnClickListener {

        private MainActivity mainActivity;

        public AddMeetingActionListener(MainActivity mainActivity){
            this.mainActivity = mainActivity;
        }

        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(mainActivity, AddMeetingActivity.class);
            MainActivity.this.startActivityForResult(myIntent, 999);
        }
    }

    /**
     * This class contain filter dialog
     * Order by name filter the meetings list by alphabetical room title.
     * Order by time filter the meetings list by early hour and min.
     */
    public class FilterActionListener {

        private MainActivity mainActivity;

        public FilterActionListener(MainActivity mainActivity) {
            this.mainActivity = mainActivity;
        }

        public void onShow() {
            final Dialog dialog = new Dialog(mainActivity);
            roomFilter = 0;
            hourFilter = 24;
            dialog.setContentView(R.layout.dialog_filter_meetings);
            dialog.setTitle("Filter meetings");
            Button filter = dialog.findViewById(R.id.button_filter);
            List<String> horros = new ArrayList<>();
            for(int i = 0; i < 24; i++) {
                switch (i) {
                    case 0 :
                        horros.add("12:00 AM - 1:00 AM");
                        break;
                    case 1:case 2: case 3:case 4:case 5:
                    case 6:case 7: case 8:case 9:case 10:
                        horros.add(i + ":00 AM - "+ (i+1) + ":00 AM");
                        break;
                    case 11:
                        horros.add("11:00 AM - 12:00 PM");
                        break;
                    case 12:
                        horros.add("12:00 PM - 1:00 PM");
                        break;
                    case 13:case 14:case 15:case 16: case 17:
                    case 18:case 19: case 20:case 21:case 22:
                        horros.add((i - 12) + ":00 PM - "+ (i + 1 - 12) + ":00 PM");
                        break;
                    case 23 :
                        horros.add((i-12) + ":00 PM - " + "12:00 AM");
                        break;
                }
            }
            ArrayAdapter<String> adHour = new ArrayAdapter<>
                    (mainActivity, android.R.layout.select_dialog_item, horros);
            actvHour = (AutoCompleteTextView) dialog.findViewById(R.id.list_autocomplete_hour_filter);
            actvHour.setThreshold(1);
            actvHour.setAdapter(adHour);
            actvHour.setOnItemClickListener(mainActivity);
            List<String> items = new ArrayList<>();
            for(Room r: UseCases.getRoomsUseCase().call()) {
                items.add(r.getTitle());
            }
            ArrayAdapter<String> ad = new ArrayAdapter<>
                    (mainActivity, android.R.layout.select_dialog_item, items);
            actv = (AutoCompleteTextView) dialog.findViewById(R.id.list_autocomplete_filter);
            actv.setThreshold(1);
            actv.setAdapter(ad);
            actv.setOnItemClickListener(mainActivity);
            filter.setOnClickListener(view -> {
                List<Meeting> vListFilter;
                boolean roomUsed = roomFilter != 0;
                boolean hourUsed = hourFilter != 24;
                if(roomUsed && !hourUsed) {
                    vListFilter = UseCases.filterMeetingsByRoomUseCase().call(roomFilter);
                } else if(!roomUsed && hourUsed) {
                    vListFilter = UseCases.filterMeetingsByHourUseCase()
                            .call(Integer.valueOf(hourFilter));
                } else if(roomUsed && hourUsed) {
                    vListFilter = UseCases.filterMeetingsByRoomAndHourUseCase()
                            .call(Integer.valueOf(hourFilter), roomFilter);
                } else {
                    vListFilter = UseCases.getMeetingsUseCase().call();
                }
                meetings.clear();
                meetings.addAll(vListFilter);
                adapter.notifyDataSetChanged();
                dialog.cancel();
            });
            dialog.show();
        }
    }
}