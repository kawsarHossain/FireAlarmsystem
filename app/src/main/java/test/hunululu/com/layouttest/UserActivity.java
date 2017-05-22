package test.hunululu.com.layouttest;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class UserActivity extends AppCompatActivity {

    private File imgFile;
    Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public void setAlarm(View view){

        playMusic();
        captureImage();




    }

    public void startInstructionActivityFromUser (View view){

        Intent intent = new Intent(UserActivity.this,InstructionActivity.class);
        startActivity(intent);


    }

    public void captureImage(){

        Intent imageIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        imgFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"test.jpg");
        imgUri = Uri.fromFile(imgFile);
        imageIntent.putExtra(MediaStore.EXTRA_OUTPUT,imgUri);
        imageIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY,1);
        startActivityForResult(imageIntent,0);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode==0)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                if(imgFile.exists())
                {
                    Toast.makeText(this,"Stored at "+ imgFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
                    sendMMS("5556","Fire At LOC: 22.67 90.47");
                }
                else
                {
                    Toast.makeText(this,"Error" ,Toast.LENGTH_LONG).show();

                }
            }
            if (resultCode==Activity.RESULT_CANCELED)
            {

            }
        }

    }





    public void playMusic(){

        MediaPlayer mp = new MediaPlayer();
        AssetFileDescriptor afd;

        if(mp.isPlaying())
        {
            mp.stop();
        }

        try {
            mp.reset();
            afd = getAssets().openFd("alarm.mp3");
            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            mp.prepare();
            mp.start();
        } catch (IllegalStateException e) {
            Toast.makeText(this,"Somethig wrong",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this,"Somethig wrong in 2",Toast.LENGTH_LONG).show();
            Log.e(e.getClass().getName(),e.getMessage(),e);
        }
    }



        private void sendMMS(String phoneNumber, String message) {


            Intent mmsIntent = new Intent(Intent.ACTION_SEND, Uri.parse("smsto:" + phoneNumber));
            mmsIntent.setType("image/*");
            mmsIntent.putExtra(Intent.EXTRA_STREAM,imgUri);
            mmsIntent.putExtra(Intent.EXTRA_TEXT,message); //YHis is for mail text
            mmsIntent.putExtra("sms_body",message); //This is for Messeging app
          //  Intent choser = Intent.createChooser(mmsIntent,"Sent Image by");
            startActivity(mmsIntent);





            //SmsManager sms = SmsManager.getDefault();
            //sms.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this,"SMS created succesfully",Toast.LENGTH_LONG).show();

        }

    public void sendSMS(View v) {


        Intent smsIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:5556"));
        smsIntent.putExtra("sms_body","Fire At LOC: 22.67 90.47");
        startActivity(smsIntent);





        //SmsManager sms = SmsManager.getDefault();
        //sms.sendTextMessage(phoneNumber, null, message, null, null);
        Toast.makeText(this,"SMS sent succesfully",Toast.LENGTH_LONG).show();

    }




}
