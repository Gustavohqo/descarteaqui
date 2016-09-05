package com.descarteaqui.descarteaqui;

import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.result.UserProfile;
import com.descarteaqui.descarteaqui.activities.LoginActivity;
import com.descarteaqui.descarteaqui.fragments.MapsFragment;
import com.descarteaqui.descarteaqui.fragments.PetiFragment;
import com.descarteaqui.descarteaqui.fragments.TipFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    private Auth0 mAuth0;
    private UserProfile mUserProfile;
    private TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth0 = new Auth0(getString(R.string.auth0_client_id), getString(R.string.auth0_domain));
        // The process to reclaim an UserProfile is preceded by an Authentication call.
        AuthenticationAPIClient aClient = new AuthenticationAPIClient(mAuth0);

        if(App.getInstance().getUserCredentials()!= null) {
            aClient.tokenInfo(App.getInstance().getUserCredentials().getIdToken())
                    .start(new BaseCallback<UserProfile, AuthenticationException>() {
                        @Override
                        public void onSuccess(final UserProfile payload) {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    mUserProfile = payload;
                                    refreshScreenInformation();
                                }
                            });

                        }

                        @Override
                        public void onFailure(AuthenticationException error) {
                            MainActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Profile Request Failed", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
        }

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        onNavigationItemSelected(navigationView.getMenu().getItem(0).setChecked(true));

        navigationView.setNavigationItemSelectedListener(this);


    }

    private void refreshScreenInformation() {
        View header=navigationView.getHeaderView(0);
        email = (TextView)header.findViewById(R.id.textView);
        email.setText(mUserProfile.getEmail());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentManager fm = getFragmentManager();
        Fragment fragment = null;

        int id = item.getItemId();

        if (id == R.id.nav_map){

            fragment = new MapsFragment();

        } else if (id == R.id.nav_tip) {

            fragment = new TipFragment();

        } else if (id == R.id.nav_petitions) {

            fragment = new PetiFragment();

        } else if (id == R.id.nav_accounts) {
            Intent lockIntent = new Intent(this, LoginActivity.class);
            startActivity(lockIntent);
        }


        if (fragment != null) {
            fm.beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
