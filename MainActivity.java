package com.example.owner.hw1bubblesort;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //Field to hold original set
    EditText editSet;

    //Field to hold sort button
    Button sort;

    //Field to hold result
    TextView showSet;

    //Field to hold Ascending/Descending toggle button
    ToggleButton order;

    //Field to hold Sorted Set
    TextView showFinal;

    //Field to hold the final set label
    TextView finalSet;

    //Field to hold the exit button
    Button exit;

    //Field to hold the clear button
    Button clear;

    //variables for bubbleSort
    int temp = 0;
    int c = 0;
    String comma = ",,";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Link instances to widget in the activity view
        editSet = (EditText) findViewById(R.id.editSet);
        sort = (Button) findViewById(R.id.sort);
        showSet = (TextView) findViewById(R.id.showSet);
        order = (ToggleButton) findViewById(R.id.order);
        showFinal = (TextView) findViewById(R.id.showFinal);
        finalSet = (TextView) findViewById(R.id.finalSet);
        clear = (Button) findViewById(R.id.clear);
        exit = (Button) findViewById(R.id.exit);

        //Error message for an empty set.
        sort.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hold user input in a single string.
                String userInput = editSet.getText().toString();

                if(userInput.length() == 0 || userInput.length() < 2 || userInput.contains(comma)){
                    editSet.setError("Error: Please enter a valid set: Example:1,5,3,6,2,7,4");
                }
                else{
                    //Save user string into an array
                    String[] inputArray = userInput.split(",");
                    //Array to hold the integer values of the set.
                    int[] intInputArray = new int[inputArray.length];

                    //For loop to convert the string set to an integer set for sorting.
                    for(int i = 0; i < inputArray.length; i++) {
                        intInputArray[i] = Integer.parseInt(inputArray[i]);
                    }

                    if(order.isChecked()){
                        descendingBubbleSort(intInputArray);
                    }
                    else
                    {
                        ascendingBubbleSort(intInputArray);
                    }
                    finalSet.setVisibility(View.VISIBLE);
                }
            }
        }));

        clear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                editSet.setText("");
                showSet.setText("");
                showFinal.setText("");
                finalSet.setVisibility(View.INVISIBLE);
            }
        });

        exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Are you sure you want to exit?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        finish();
                    }
                });
                builder.setNegativeButton("No", null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
    }

    //Function to sort the set in Ascending order
    public  void ascendingBubbleSort(int[] a){
        //Original set
        showSet.setText("Original Set: " + Arrays.toString(a) + "\n");

        //Bubble sort method
        for(int x = 0 ; x < (a.length)-1; x++){
            showSet.append("-------Pass " + (x+1) + " ------" + "\n");
            for(int i = 0; i < (a.length)-1; i++){
                if(a[i] > a[i+1])
                {
                    temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    showSet.append("Swap : " + a[i+1] + " and " + a[i] + "\n");
                    showSet.append(Arrays.toString(a) + "\n");
                }
                else if(a[i] <= a[i+1])
                {
                    c++;
                }
            }
            if(c == (a.length -1))
            {
                x = a.length;
                showSet.append("Set Sorted" + "\n");
            }
            else
            {
                c = 0;
            }
        }
        showSet.append("-----Sorted Set-----" + "\n");
        showSet.append(Arrays.toString(a));
        showFinal.setText(Arrays.toString(a));
    }
    //Function to sort the set in Descending order
    public  void descendingBubbleSort(int[] a){
        //Original set
        showSet.setText("Original Set: " + Arrays.toString(a) + "\n");

        //Bubble sort method
        for(int x = 0 ; x < (a.length)-1; x++){
            showSet.append("-------Pass " + (x+1) + " ------" + "\n");
            for(int i = 0; i < (a.length)-1; i++){
                if(a[i] < a[i+1])
                {
                    temp = a[i];
                    a[i] = a[i+1];
                    a[i+1] = temp;
                    showSet.append("Swap : " + a[i+1] + " and " + a[i] + "\n");
                    showSet.append(Arrays.toString(a) + "\n");
                }
                else if(a[i] >= a[i+1])
                {
                    c++;
                }
            }
            if(c == (a.length -1))
            {
                x = a.length;
                showSet.append("Set Sorted" + "\n");
            }
            else
            {
                c = 0;
            }
        }
        showSet.append("-----Sorted Set-----" + "\n");
        showSet.append(Arrays.toString(a));
        showFinal.setText(Arrays.toString(a));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
