package com.yostar.appv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.games.AuthenticationResult;
import com.google.android.gms.games.GamesSignInClient;
import com.google.android.gms.games.PlayGames;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayersClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GamesSignInClient gamesSignInClient = PlayGames.getGamesSignInClient(this);
//        gamesSignInClient
//                .requestServerSideAccess("273818645006-37uabfhb8d4flpfc7pu1v0269b3lc0l3.apps.googleusercontent.com", false)
//                .addOnCompleteListener(new OnCompleteListener<String>() {
//                    @Override
//                    public void onComplete(@NonNull Task<String> task) {
//                    }
//                });


        Task<AuthenticationResult> authenticationResultTask =  gamesSignInClient.isAuthenticated();
        authenticationResultTask.addOnCompleteListener(isAuthenticatedTask -> {
            boolean isAuthenticated =
                    (isAuthenticatedTask.isSuccessful() && isAuthenticatedTask.getResult().isAuthenticated());
            if (isAuthenticated) {
                // Continue with Play Games Services
                AuthenticationResult authenticationResult = authenticationResultTask.getResult();
                boolean isAuthenticatedOut = authenticationResult.isAuthenticated();

                PlayersClient playersClient = PlayGames.getPlayersClient(this);
                Task<String> task = playersClient.getCurrentPlayerId();
                task.addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        String playerId = task.getResult();
                        // zhaopengli@yo-star.com a_6668682212583032435
                        // lizhaopeng.cn@gmail.com a_3201689475231923229
                        Log.e("playerId", "playerId:" + playerId); //
                    }
                });
            } else {
                // Disable your integration with Play Games Services or show a login button to ask
                // users to sign-in. Clicking it should call GamesSignInClient.signIn().
                gamesSignInClient.signIn();
            }
        });

//        PlayersClient playersClient = PlayGames.getPlayersClient(this);
//        Task<String> task = playersClient.getCurrentPlayerId();
//        task.addOnCompleteListener(new OnCompleteListener<String>() {
//            @Override
//            public void onComplete(@NonNull Task<String> task) {
//                String playerId = task.getResult();
//                Log.i("playerId", "playerId:" + playerId);
//            }
//        });
    }
}
