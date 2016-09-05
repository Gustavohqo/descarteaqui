package com.descarteaqui.descarteaqui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.lock.AuthenticationCallback;
import com.auth0.android.lock.Lock;
import com.auth0.android.lock.LockCallback;
import com.auth0.android.lock.utils.LockException;
import com.auth0.android.result.Credentials;
import com.descarteaqui.descarteaqui.App;
import com.descarteaqui.descarteaqui.MainActivity;
import com.descarteaqui.descarteaqui.R;

public class LoginActivity extends AppCompatActivity {
    private Lock lock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Auth0 auth0 = new Auth0(getString(R.string.auth0_client_id), getString(R.string.auth0_domain));
        this.lock = Lock.newBuilder(auth0, mCallback)
                // Add parameters to the Lock Builder
                .build();
        this.lock.onCreate(this);
        startActivity(this.lock.newIntent(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Your own Activity code
        this.lock.onDestroy(this);
        this.lock = null;
    }

    private LockCallback mCallback = new AuthenticationCallback() {
        @Override
        public void onAuthentication(Credentials credentials) {
            System.out.println(credentials);
            App.getInstance().setUserCredentials(credentials);
            Toast.makeText(getApplicationContext(), "Log In - Success", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
            //App.getInstance().setUserCredentials(credentials);
            //startActivity(new Intent(getApplicationContext(), MainActivity.class));
            // Login Success response
        }

        @Override
        public void onCanceled() {
            Toast.makeText(getApplicationContext(), "Log In - Cancelled", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(LockException error) {
            Toast.makeText(getApplicationContext(), "Log In - Error Occurred", Toast.LENGTH_SHORT).show();
        }
    };
}