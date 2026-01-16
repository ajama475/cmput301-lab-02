package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    int targetPos = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cityList = findViewById(R.id.city_list);
        Button confirmButt = findViewById(R.id.confirm_button);
        Button deleteButt = findViewById(R.id.delete_city_button);
        EditText addCity = findViewById(R.id.add_city_field);


        String []cities = {"Edmonton", "Montreal"};
        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                targetPos = position;
            }
        });

        confirmButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = addCity.getText().toString();
                dataList.add(cityName);
                cityAdapter.notifyDataSetChanged();
                addCity.setText("");
            }

        });

        deleteButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Check if an item is selected and the list isn't empty
                if (targetPos != -1 && !dataList.isEmpty()) {
                    dataList.remove(targetPos);
                    cityAdapter.notifyDataSetChanged();
                    targetPos = -1; // Reset selection after deleting
                }
            }
        });


    }
}