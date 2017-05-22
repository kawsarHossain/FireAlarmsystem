package test.hunululu.com.layouttest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startUserActivity (View view){

        Intent intent = new Intent(MainActivity.this,UserActivity.class);
        startActivity(intent);




    }

    public void startVolunteerActivity(View view){

        Intent intent = new Intent(MainActivity.this,VolunteerActivity.class);
        startActivity(intent);


    }

    public void startInstructionActivity (View view){

        Intent intent = new Intent(MainActivity.this,InstructionActivity.class);
        startActivity(intent);


    }

    public void startAboutActivity (View view){

        Intent intent = new Intent(MainActivity.this,AboutActivity.class);
        startActivity(intent);


    }
}
