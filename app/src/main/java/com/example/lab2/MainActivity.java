package com.example.lab2;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    //declare the vars so that you will be able to ref it later.
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    //confirm button mode 1 is add, 2 is remove
    int confirm_button_mode = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);



        //links the bottom text layout to the XML
        LinearLayout text_input_view = findViewById(R.id.text_input_view);
        //makes the bottom layout blank by default
        text_input_view.setVisibility(View.GONE);

        //linking textbox to the XML
        TextInputEditText text_input_box = findViewById(R.id.text_input_box);






        Button confirm_input_button = (findViewById(R.id.confirm_input_button));
        //responding to click event


        //this stores add_button id in a variable
        Button add_city_button = (findViewById(R.id.add_city_button));
        //responding to click event
        add_city_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //log message: delete later
                Log.d("BUTTONS", "User tapped the add_city_button");
                confirm_button_mode = 1;
                text_input_view.setVisibility(View.VISIBLE);
            }


        });

        Button delete_city_button = (findViewById(R.id.delete_city_button));
        //responding to click event
        delete_city_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("BUTTONS", "User tapped the remove_city_button");
                text_input_view.setVisibility(View.VISIBLE);
                confirm_button_mode = 2;
            }
        });











        //assigns reference to ListView to the reference 'cityList'
        cityList = findViewById(R.id.city_list);

        //list of cities that can be fed into 'ListView' later
        String []cities = {"Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"};

        //contains data(the string array of cities)
        dataList = new ArrayList<>();
        //adding data(string array containing city names) to 'dataList'
        dataList.addAll(Arrays.asList(cities));

        //linking content.xml to 'dataList' so that each element is displayed in a separate row in list
        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        //connect 'ListView' to 'ArrayAdapter' which will show each 'TextView'
            //in the form of a scrolling list.
        cityList.setAdapter(cityAdapter);

        confirm_input_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //has to check if add city or remove city


                Log.d("BUTTONS", "User tapped the confirm_input_button");
                //store string city in a variable if the button was pressed
                String input_text = text_input_box.getText().toString();
                if (confirm_button_mode == 1) { //add mode
                    //append 'input_text' to city list
                    dataList.add(input_text);
                }
                else if (confirm_button_mode == 2) { //remove mode
                    //do the removal
                    for (int i = 0; i < dataList.size(); i++) {
                        if (Objects.equals(dataList.get(0), input_text)) {
                            dataList.remove(i);
                        }
                    }
                }


                text_input_view.setVisibility(View.GONE);
            }
        });














        //handles 'remove city' button



;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;



        });
    }


}

//next: