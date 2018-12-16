package com.idevwizard.sharedprefsobjectserializationdemo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.sql.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView txvDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txvDisplay = (TextView) findViewById(R.id.txvDisplay);
    }

    public void saveObjectType(View view) {
        Employee employee = getEmployeeObject();
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // - Serialisation
        // Open app / build.gradle
        // Add dependency: implementation 'com.google.code.gson:gson:2.8.2'
        // More at: https://developer.android.com/studio/build/dependencies

        Gson gson = new Gson();
        String jsonString = gson.toJson(employee, Employee.class);
        Log.i(TAG, jsonString);

        editor.putString("employee_key", jsonString);
        editor.apply();
    }

    public void loadObjectType(View view) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("employee_key", "N/A");
        Log.i(TAG + " Load", jsonString);

        // - Deserialisation
        Gson gson = new Gson();
        Employee employee = gson.fromJson(jsonString, Employee.class);

        displayText(employee);
    }

    public void saveGenericType(View view) {
        Employee employee = getEmployeeObject();
        Foo<Employee> foo = new Foo<>();
        foo.setObject(employee);

        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        Type type = new TypeToken<Foo<Employee>>() {}.getType();
        String jsonString = gson.toJson(foo, type); // Foo.class DOES NOT work, because of Type Erasure
        Log.i(TAG, jsonString);

        editor.putString("foo_key", jsonString);
        editor.apply();
    }

    public void loadGenericType(View view) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        String jsonString = sharedPreferences.getString("foo_key", "N/A");
        Log.i(TAG + " Load", jsonString);

        // - Deserialisation
        Gson gson = new Gson();
        Type type = new TypeToken<Foo<Employee>>() {}.getType();
        Foo<Employee> employeeFoo = gson.fromJson(jsonString, type); // Foo.class DOES NOT work, because of Type Erasure
        Employee employee = employeeFoo.getObject();

        displayText(employee);
    }

    private Employee getEmployeeObject() {
        Employee employee = new Employee();

        employee.setName("Pavel Palancica");
        employee.setProfession("Software Engineer");
        employee.setProfId(152);
        employee.setRoles(Arrays.asList("Developer", "Admin"));

        return employee;
    }

    private void displayText(Employee employee) {
        if (employee == null) { return; }

        String text = employee.getName() +
                "\n" + employee.getProfession() +
                "\n" + employee.getProfId() +
                "\n" + employee.getRoles().toString();

        txvDisplay.setText(text);
    }
}
