package com.example.mareu.ui;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.mareu.R;
import com.example.mareu.di.Repositories;
import com.example.mareu.di.UseCases;
import com.example.mareu.model.Meeting;
import com.example.mareu.model.Participant;
import com.example.mareu.model.Room;
import com.example.mareu.model.User;
import com.example.mareu.ui.exception.DialogError;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * This class define the view for creating activity
 */
public class AddMeetingActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private AddMeetingActivity activity;

    private TextView hoursView;
    private TextView minuteView;
    private Button changeHoursPicker;
    private ExtendedFloatingActionButton confirmButton;
    private List<User> users = new ArrayList<>();
    private ChipGroup chipsGroupUsers;
    private MaterialTimePicker time;
    private MaterialToolbar toolbar;
    private TextInputEditText editTextAbout;
    private AutoCompleteTextView actv;
    private int roomSelect;

    /**
     * Show items on add meeting activity
     * Define object on view
     * @param savedInstanceState
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);
        initView();
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        setActivity(this);
        hoursView.setText("12");
        minuteView.setText("00");
        generateChipGroupUser();
        List<String> items = new ArrayList<>();
        for(Room r: UseCases.getRoomsUseCase().call()) {
            items.add(r.getTitle());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.select_dialog_item, items);
        actv = (AutoCompleteTextView) findViewById(R.id.list_autocomplete);
        actv.setThreshold(1);
        actv.setAdapter(adapter);
        actv.setOnItemClickListener(this);
        changeHoursPicker.setOnClickListener(view -> generateTimePicker());
        confirmButton.setOnClickListener(view -> addMeeting());
    }

    public void generateTimePicker() {
        time = new MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_12H)
                .setHour(12)
                .setMinute(10)
                .build();
        time.addOnPositiveButtonClickListener(view -> {
            setHoursView(String.valueOf(time.getHour()));
            setMinuteView(String.valueOf(time.getMinute()));
        });
        time.show(getSupportFragmentManager(), "fragment_time");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        for(Room r: UseCases.getRoomsUseCase().call()) {
            if (parent.getItemAtPosition(position).toString().equals(r.getTitle())) {
                roomSelect = r.getId();
            }
        }
    }

    /**
     * Generate array for chip group
     * Get users list on usesCasse
     */
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void generateChipGroupUser() {
        for(User u: UseCases.getUsersUseCase().call()) {
            Chip chip = new Chip(this);
            chip.setText(u.getMail().substring(0,u.getMail().indexOf("@")));
            chip.setCloseIconVisible(false);
            chip.setTextColor(
                    ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorPrimary)));
            chip.setCheckable(true);
            chip.setChipBackgroundColorResource(R.color.colorWhite);
            chip.setChipStrokeWidth(2);
            chip.setChipStrokeColor(getColorStateList(R.color.colorPrimary));
            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @RequiresApi(api = Build.VERSION_CODES.P)
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if(b) {
                        for(User user: UseCases.getUsersUseCase().call()) {
                            if(user.getMail().substring(
                                    0,user.getMail().indexOf("@"))
                                    .equals(compoundButton.getText().toString())) {
                                users.add(user);
                            }
                        }
                    } else {
                        Iterator<User> iter = users.iterator();

                        while (iter.hasNext()) {
                            User user = iter.next();
                            if(user.getMail().substring(
                                    0,user.getMail().indexOf("@"))
                                    .equals(compoundButton.getText().toString())) {
                                iter.remove();
                            }
                        }
                    }
                }
            });
            chipsGroupUsers.addView(chip);
        }
    }

    /**
     * This method add meting to repository
     * Check if the room at the selected time is free or not
     * Create multiple participants for each users
     */
    public void addMeeting() {
        boolean canBeCreate = true;
        if(users.size() == 0) {
            new DialogError(getBaseContext().getResources().getString(R.string.error_user), this.findViewById(R.id.coordinator));
            canBeCreate = false;
        } else if (editTextAbout.getText().toString().length() < 1) {
            new DialogError(getBaseContext().getResources().getString(R.string.error_subject),
                    this.findViewById(R.id.coordinator) );
            canBeCreate = false;
        } else if (roomSelect == 0) {
            new DialogError(getBaseContext().getResources().getString(R.string.error_rooms),
                    this.findViewById(R.id.coordinator));
            canBeCreate = false;
        }
        boolean freeRoom = true;

        int hour = Integer.parseInt(hoursView.getText().toString());
        int minute = Integer.parseInt(minuteView.getText().toString());

        int time = (hour * 60) + minute;

        for(Meeting m: UseCases.getGetMeetingByRoomIdUseCase().call(roomSelect)) {
            int timeMeetingFromListMax = ((m.getHour() * 60) + m.getMin() + 60 );
            int timeMeetingFromListMin = ((m.getHour() * 60) + m.getMin() - 60 );
            if(timeMeetingFromListMax > time && timeMeetingFromListMin < time) {
                freeRoom = false;
                new DialogError(getBaseContext().getResources().getString(R.string.not_free), this.findViewById(R.id.coordinator));
            } else {
                freeRoom = true;
            }
        }
        if(freeRoom && canBeCreate) {
            int nextIdMeeting = UseCases.getMeetingsUseCase().call().size() + 1;
            for(User u: users) {
                Repositories.getParticipantRepository().addParticipant(new Participant(u.getId(),nextIdMeeting));
            }
            Repositories.getMeetingRepository().addMeeting(
                    new Meeting(nextIdMeeting,
                            editTextAbout.getText().toString(),
                            roomSelect,
                            hour,
                            minute));
            finish();
        }
    }

    /**
     * Selected item on header.
     * id home can finish the current activity and return to main
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        hoursView = (TextView) findViewById(R.id.add_hours_info);
        minuteView = (TextView) findViewById(R.id.add_minute_info);
        changeHoursPicker = (Button) findViewById(R.id.add_change_hours);
        chipsGroupUsers = (ChipGroup) findViewById(R.id.chipsUsers);
        confirmButton = (ExtendedFloatingActionButton) findViewById(R.id.fab);
        toolbar = (MaterialToolbar) findViewById(R.id.topAppBar);
        editTextAbout = (TextInputEditText) findViewById(R.id.about_add_meeting);
    }


    public void setHoursView(String hours) {
        this.hoursView .setText(hours);
    }

    public void setMinuteView(String minutes) {
        this.minuteView.setText(minutes);
    }

    public AddMeetingActivity getActivity() {
        return activity;
    }

    public void setActivity(AddMeetingActivity activity) {
        this.activity = activity;
    }
}