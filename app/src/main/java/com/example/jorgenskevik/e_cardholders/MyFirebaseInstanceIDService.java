package com.example.jorgenskevik.e_cardholders;

/**
 * Created by jorgenskevik on 11.01.2018.
 */

import android.util.Log;

import com.example.jorgenskevik.e_cardholders.Variables.KVTVariables;
import com.example.jorgenskevik.e_cardholders.models.LoginModel;
import com.example.jorgenskevik.e_cardholders.models.SessionManager;
import com.example.jorgenskevik.e_cardholders.models.Token;
import com.example.jorgenskevik.e_cardholders.models.User;
import com.example.jorgenskevik.e_cardholders.remote.UserAPI;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        System.out.println("skjer det noe refresh maaffaka???---------------");
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        User user;
        HashMap<String,String> authHeader = new HashMap<String, String>();
        HashMap<String, String> userDetails;

        String beartoken;
        SessionManager sessionManager;
        sessionManager = new SessionManager(getApplicationContext());
        userDetails = sessionManager.getUserDetails();
        beartoken = "Bearer " + userDetails.get(SessionManager.KEY_SUPER);

        authHeader.put("client_key", KVTVariables.getAppkey());
        authHeader.put("Accept-Version", KVTVariables.getAcceptVersion());
        authHeader.put("AuthToken", beartoken);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //local eller base
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(KVTVariables.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        UserAPI userapi = retrofit.create(UserAPI.class);

        Token sendToken = new Token(token);

        userapi.postToken(
                sendToken,
                authHeader.get("AuthToken"),
                authHeader.get("Accept-Version"),
                authHeader.get("client_key")).enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()){
                }else{
                }
            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
            }
        });

    }
}
