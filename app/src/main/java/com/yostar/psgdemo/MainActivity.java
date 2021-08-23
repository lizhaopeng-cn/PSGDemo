package com.yostar.psgdemo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {
    private final int RC_SIGN_IN = 60011;
    private GoogleSignInOptions signInOptions;
    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount googleSignInAccount;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text);

                signInOptions =
                        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
//                        new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                                .requestScopes(Games.SCOPE_GAMES_SNAPSHOTS)
                                .requestId()
                                .requestEmail()
                                .requestIdToken("527953851962-htan2je5kqu12htoabib8n2bkjqotmbt.apps.googleusercontent.com")
//                                .requestIdToken("527953851962-5h94nh409p27hmjibmvv04fekv1nlhmb.apps.googleusercontent.com")
//                                .requestIdToken("527953851962-8icfp8gn68hbno3oh66g3apn7tpun553.apps.googleusercontent.com")
//                                .requestIdToken("273818645006-1f0kj4dhingkhbt4pderis2iho5gt2j3.apps.googleusercontent.com")
                                .requestProfile()
//                                .requestServerAuthCode("527953851962-8icfp8gn68hbno3oh66g3apn7tpun553.apps.googleusercontent.com")
                                .build();

        googleSignInClient = GoogleSignIn.getClient(this, signInOptions);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignInIntent();
            }
        });

        findViewById(R.id.btn_logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (googleSignInClient != null) {
                    googleSignInClient.signOut();
                }
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
//        signInSilently();
    }

    /**
     * 静默登录
     */
    private void signInSilently() {
        // 已经登录。  登录的帐户存储在 'account' 变量中。
        GoogleSignInAccount googleSignInAccountTemp = GoogleSignIn.getLastSignedInAccount(this);
        if (googleSignInAccountTemp != null && GoogleSignIn.hasPermissions(googleSignInAccountTemp, signInOptions.getScopeArray())) {
            googleSignInAccount = googleSignInAccountTemp;
        } else {
            // 之前没有登录过。首先尝试静默登录。
            Task<GoogleSignInAccount> task =  googleSignInClient.silentSignIn();
            task.addOnCompleteListener(this, new OnCompleteListener<GoogleSignInAccount>() {
                @Override
                public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                    if (task.isSuccessful()) {
                        googleSignInAccount = task.getResult();
                    } else {
                        startSignInIntent();
                    }
                }
            });
        }
    }

    /**
     * 交互式登录
     */
    private void startSignInIntent() {
        Intent intent = googleSignInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            if (result.isSuccess()) {
//                // The signed in account is stored in the result.
//                googleSignInAccount = result.getSignInAccount();
//                Log.i("PSG", "getId:" + googleSignInAccount.getId());
//                Log.i("PSG", "getIdToken:" + googleSignInAccount.getIdToken());
//                Log.i("PSG", "getEmail:" + googleSignInAccount.getEmail());
//                Log.i("PSG", "getAccount:" + googleSignInAccount.getAccount());
//                Log.i("PSG", "getGrantedScopes:" + googleSignInAccount.getGrantedScopes());
//                Log.i("PSG", "getDisplayName" + googleSignInAccount.getDisplayName());
//                Log.i("PSG", "getFamilyName:" + googleSignInAccount.getFamilyName());
//                Log.i("PSG", "getGivenName:" + googleSignInAccount.getGivenName());
//                Log.i("PSG", "getServerAuthCode:" + googleSignInAccount.getServerAuthCode());
//                Log.i("PSG", "getPhotoUrl:" + googleSignInAccount.getPhotoUrl());
//                Log.i("PSG", "getRequestedScopes:" + googleSignInAccount.getRequestedScopes());
//
//                textView.append("getId:" + googleSignInAccount.getId() + "\n");
//                textView.append("getIdToken:" + googleSignInAccount.getIdToken() + "\n");
//                textView.append("getEmail:" + googleSignInAccount.getEmail() + "\n");
//                textView.append("getAccount:" + googleSignInAccount.getAccount() + "\n");
//                textView.append("getGrantedScopes:" + googleSignInAccount.getGrantedScopes() + "\n");
//                textView.append("getDisplayName" + googleSignInAccount.getDisplayName() + "\n");
//                textView.append("getFamilyName:" + googleSignInAccount.getFamilyName() + "\n");
//                textView.append("getGivenName:" + googleSignInAccount.getGivenName() + "\n");
//                textView.append("getServerAuthCode:" + googleSignInAccount.getServerAuthCode() + "\n");
//                textView.append("getPhotoUrl:" + googleSignInAccount.getPhotoUrl() + "\n");
//                textView.append("getRequestedScopes:" + googleSignInAccount.getRequestedScopes() + "\n");
//            } else {
//                Log.i("PSG", "auth fail");
//            }


            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            GoogleSignInAccount result = null;
            try {
                googleSignInAccount = task.getResult(ApiException.class);
                if (googleSignInAccount != null) {
                Log.i("PSG", "getId:" + googleSignInAccount.getId());
                Log.i("PSG", "getIdToken:" + googleSignInAccount.getIdToken());
                Log.i("PSG", "getEmail:" + googleSignInAccount.getEmail());
                Log.i("PSG", "getAccount:" + googleSignInAccount.getAccount());
                Log.i("PSG", "getGrantedScopes:" + googleSignInAccount.getGrantedScopes());
                Log.i("PSG", "getDisplayName" + googleSignInAccount.getDisplayName());
                Log.i("PSG", "getFamilyName:" + googleSignInAccount.getFamilyName());
                Log.i("PSG", "getGivenName:" + googleSignInAccount.getGivenName());
                Log.i("PSG", "getServerAuthCode:" + googleSignInAccount.getServerAuthCode());
                Log.i("PSG", "getPhotoUrl:" + googleSignInAccount.getPhotoUrl());
                Log.i("PSG", "getRequestedScopes:" + googleSignInAccount.getRequestedScopes());


                    textView.append("getId:" + googleSignInAccount.getId() + "\n");
                    textView.append("getIdToken:" + googleSignInAccount.getIdToken() + "\n");
                    textView.append("getEmail:" + googleSignInAccount.getEmail() + "\n");
                    textView.append("getAccount:" + googleSignInAccount.getAccount() + "\n");
                    textView.append("getGrantedScopes:" + googleSignInAccount.getGrantedScopes() + "\n");
                    textView.append("getDisplayName" + googleSignInAccount.getDisplayName() + "\n");
                    textView.append("getFamilyName:" + googleSignInAccount.getFamilyName() + "\n");
                    textView.append("getGivenName:" + googleSignInAccount.getGivenName() + "\n");
                    textView.append("getServerAuthCode:" + googleSignInAccount.getServerAuthCode() + "\n");
                    textView.append("getPhotoUrl:" + googleSignInAccount.getPhotoUrl() + "\n");
                    textView.append("getRequestedScopes:" + googleSignInAccount.getRequestedScopes() + "\n");
                } else {
                    showError(result);
                }
            } catch (ApiException e) {
                e.printStackTrace();
            }





//            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
//            if (result.isSuccess()) {
//                // The signed in account is stored in the result.
//                googleSignInAccount = result.getSignInAccount();

//            } else {
//                showError(result);
//            }
        }
    }

    private void showError(GoogleSignInAccount result) {
        Log.i("PSG", "auth fail");
    }
}