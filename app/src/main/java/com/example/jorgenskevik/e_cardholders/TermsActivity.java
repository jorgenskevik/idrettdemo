package com.example.jorgenskevik.e_cardholders;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.digits.sdk.android.Digits;
import com.example.jorgenskevik.e_cardholders.models.SessionManager;


/**
 * The type Terms activity.
 */
public class TermsActivity extends Activity {
    /**
     * The constant CAM_REQUEST_CODE.
     */
    public static final int CAM_REQUEST_CODE = 4545;
    /**
     * The constant maxBuildVersion.
     */
    public static final int maxBuildVersion = 6;

    /**
     * The Check box.
     */
    String checkBox = "";
    /**
     * The Session manager.
     */
    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.terms_view);
        sessionManager = new SessionManager(getApplicationContext());


        final CheckBox yourCheckBox = (CheckBox) findViewById(R.id.checkBox);

        yourCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(yourCheckBox.isChecked()){
                    checkBox = "check";
                    sessionManager.updateCheck(checkBox);
                }else{
                    checkBox = "notCheck";
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        Toast.makeText(this, R.string.acceptTerms, Toast.LENGTH_SHORT).show();

    }

    /**
     * Open card.
     *
     * @param view the view
     */


    public void openCard(View view) {
        if (checkBox.equals("check")) {
            Intent intent = new Intent(TermsActivity.this, UserActivity.class);
            startActivity(intent);
            return;
        } else {
            Toast.makeText(this, R.string.acceptTerms, Toast.LENGTH_SHORT).show();
        }
    }
}
