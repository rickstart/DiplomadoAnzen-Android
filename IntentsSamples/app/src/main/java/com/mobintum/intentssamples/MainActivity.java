package com.mobintum.intentssamples;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView listIntents;
    private IntentAdapterList adapter;
    private ArrayList<IntentModel> models = new ArrayList<>();
    //ArrayList<String> reqPermission = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] reqPermission = new String[]{ Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE,Manifest.permission.SEND_SMS};
        //reqPermission.add(Manifest.permission.CAMERA);
        //reqPermission.add(Manifest.permission.CALL_PHONE);
        //reqPermission.add(Manifest.permission.SEND_SMS);
        ActivityCompat.requestPermissions(this,reqPermission,100);
        setContentView(R.layout.activity_main);
        listIntents = (ListView) findViewById(R.id.listIntent);
        loadData();
        adapter = new IntentAdapterList(getApplicationContext(), R.layout.item_list, models);
        listIntents.setAdapter(adapter);
        listIntents.setOnItemClickListener(this);
    }

    void loadData() {
        models.add(new IntentModel("Take a Photo", getApplicationContext().getResources().getDrawable(R.drawable.ic_camera), 1));
        models.add(new IntentModel("Send an Email", getApplicationContext().getResources().getDrawable(R.drawable.ic_email), 2));
        models.add(new IntentModel("Navigate with GMaps", getApplicationContext().getResources().getDrawable(R.drawable.ic_maps), 3));
        models.add(new IntentModel("Call a Friend", getApplicationContext().getResources().getDrawable(R.drawable.ic_phone), 4));
        models.add(new IntentModel("My Twitter", getApplicationContext().getResources().getDrawable(R.drawable.ic_twitter), 5));
        models.add(new IntentModel("Send Whatssapp", getApplicationContext().getResources().getDrawable(R.drawable.ic_whatssapp), 6));
        models.add(new IntentModel("Send SMS", getApplicationContext().getResources().getDrawable(R.drawable.ic_sms), 7));
        models.add(new IntentModel("Change Activity", getApplicationContext().getResources().getDrawable(R.drawable.ic_screen), 8));
        //for (int i=0;i<5000;i++)
        //    models.add(new IntentModel("Action "+i,getApplicationContext().getResources().getDrawable(R.mipmap.ic_launcher),i));
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.e("DEBUG", "requestCode: "+requestCode);
        for (String permission: permissions)
            Log.e("DEBUG", permission);
        for (int index:grantResults)
            Log.e("DEBUG", "i: "+index);
        if (requestCode == 50){
            int index = 0;
            for (String permission: permissions) {
                if (permission == Manifest.permission.CALL_PHONE) {
                    if(grantResults[index]==-1){
                        ActivityCompat.requestPermissions(this,new String[]{ Manifest.permission.CALL_PHONE},50);
                    }else {
                        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:5212345678"));
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                            startActivity(intent);
                        }else{
                            ActivityCompat.requestPermissions(this,new String[]{ Manifest.permission.CAMERA,Manifest.permission.CALL_PHONE},50);
                        }
                    }
                }
                index++;
            }

        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        IntentModel model = adapter.getItem(i);
        Intent intent;
        switch (model.getAction()) {
            case 1:
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:ricardo.centeno@mobintum.com"));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Example Intent");
                intent.putExtra(Intent.EXTRA_TEXT, "This email was sent from my android app");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"ricardo.celj@gmail.com"});
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=19.361775,-99.173733"));
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:5212345678"));
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                    startActivity(intent);
                }else{
                    ActivityCompat.requestPermissions(this,new String[]{ Manifest.permission.CALL_PHONE},50);
                }
                break;
            case 5:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=rickstart"));

                try{
                    startActivity(intent);
                }catch (ActivityNotFoundException e){
                    intent = new Intent (Intent.ACTION_VIEW, Uri.parse("market://details?id=com.twitter.android"));
                    try {
                        startActivity(intent);
                    }catch (ActivityNotFoundException k){
                        Toast.makeText(getApplicationContext(),"Sorry :) I didn't find the app",Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case 6:
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                intent.setType("text/plain");
                intent.setPackage("com.whatsapp");
                break;
            case 7:
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:5514382887"));
                intent.putExtra("sms_body", "Body of message");
                startActivity(intent);
                break;
            case 8:
                intent = new Intent(this, SecondActivity.class);
                intent.putExtra("id","Hello");
                startActivity(intent);
                break;
            default:
                break;
        }


    }
}
