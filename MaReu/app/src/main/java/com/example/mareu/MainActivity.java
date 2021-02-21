package com.example.mareu;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import com.example.mareu.di.UseCases;
import com.example.mareu.model.Meeting;
import com.example.mareu.ui.AddMeetingActivity;
import com.example.mareu.ui.adapter.MeetingAdapter;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MeetingAdapter adapter;
    private ArrayList<Meeting> meetings = new ArrayList<>();
    private MaterialToolbar toolbar;
    private Button buttonFilter;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.topAppBar);

        toolbar.inflateMenu(R.menu.menu_filter);

        toolbar.setOnMenuItemClickListener(item -> {
            System.out.println(R.id.action_filter);
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

    public void refreshAdapter() {
        refreshData();
        adapter.notifyDataSetChanged();
    }

    public void refreshData() {
        meetings.clear();
        meetings.addAll(UseCases.getMeetingsUseCase().call());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 999) {
            refreshAdapter();
        }
    }

    /**
     * this class show activity for create new meeting
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
            final AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle("Select meetings order");
            String[] items = {"Order by name",  "Order by time"};
            List<Meeting> vList = UseCases.getMeetingsUseCase().call();
            builder.setItems(items, (dialog, which) -> {
                switch (which) {
                    case 0:
                        Collections.sort(vList, (Comparator) (softDrinkOne, softDrinkTwo) ->
                                ((Meeting)softDrinkOne).getRoom().getTitle()
                                        .compareTo(((Meeting)softDrinkTwo).getRoom().getTitle()));
                        break;
                    case 1:
                        Collections.sort(vList, (Comparator) (softDrinkOne, softDrinkTwo) -> (new Integer((((Meeting)softDrinkOne).getHour() * 60) + ((Meeting)softDrinkTwo).getMin())
                                .compareTo((((Meeting)softDrinkTwo).getHour() * 60) + ((Meeting)softDrinkTwo).getMin())));
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + which);
                }
                meetings.clear();
                meetings.addAll(vList);
                adapter.notifyDataSetChanged();
            });
            builder.show();
        }
    }

    public int compare(Meeting d, Meeting d1) {
        return d1.getId() - d.getId();
    }

    public int compareTime(Meeting d, Meeting d1) {
        return ((d1.getHour() * 60) + d1.getMin()) - ((d.getId() * 60) + d1.getMin());
    }
}