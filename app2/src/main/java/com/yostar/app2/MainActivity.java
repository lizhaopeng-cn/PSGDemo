package com.yostar.app2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.games.GamesSignInClient;
import com.google.android.gms.games.PlayGames;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GamesSignInClient gamesSignInClient = PlayGames.getGamesSignInClient(this);
        gamesSignInClient.isAuthenticated().addOnCompleteListener(isAuthenticatedTask -> {
            boolean isAuthenticated =
                    (isAuthenticatedTask.isSuccessful() && isAuthenticatedTask.getResult().isAuthenticated());
            if (isAuthenticated) {
                // Continue with Play Games Services
            } else {
                // Disable your integration with Play Games Services or show a login button to ask
                // users to sign-in. Clicking it should call GamesSignInClient.signIn().
                gamesSignInClient.signIn();
//                try {
//                    gamesSignInClient.isAuthenticated().getResult(ApiException.class);
//                } catch (ApiException e) {
//                    e.printStackTrace();
//                }
            }
        });
    }
}
