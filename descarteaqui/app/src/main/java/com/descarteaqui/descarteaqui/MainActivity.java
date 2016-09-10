package com.descarteaqui.descarteaqui;

import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.descarteaqui.descarteaqui.fragments.MapsFragment;
import com.descarteaqui.descarteaqui.fragments.PetiFragment;
import com.descarteaqui.descarteaqui.fragments.TipFragment;
import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;
    private GoogleSignInAccount userInfo;
    private Profile facebookProfile;
    private TextView email;
    private ImageView photo;
    private TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        if(App.getInstance() != null && App.getInstance().getUserGoogleInfo() != null) {
            userInfo = App.getInstance().getUserGoogleInfo();
            refreshScreenInformation();
        }else if(App.getInstance() != null && App.getInstance().getFacebookProfile() != null) {
            facebookProfile = App.getInstance().getFacebookProfile();
            refreshFacebookInformation();
        }
    }

    private void refreshFacebookInformation(){
        View header=navigationView.getHeaderView(0);
        // Bitmap bmp = BitmapFactory.decodeStream(userInfo.getPhotoUrl());
        photo = (ImageView)header.findViewById(R.id.imageView);
        email = (TextView)header.findViewById(R.id.textView);
        name = (TextView)header.findViewById(R.id.nameView);

        name.setText("Bem vindo, " + facebookProfile.getFirstName());
        email.setText(" ");
        photo.setImageURI(facebookProfile.getProfilePictureUri(120,120));

        Picasso.with(this).load(facebookProfile.getProfilePictureUri(120,120))
                .resize(120, 120)
                .into(photo);
    }

    private void refreshScreenInformation()  {
        View header=navigationView.getHeaderView(0);
       // Bitmap bmp = BitmapFactory.decodeStream(userInfo.getPhotoUrl());
        photo = (ImageView)header.findViewById(R.id.imageView);
        email = (TextView)header.findViewById(R.id.textView);
        name = (TextView)header.findViewById(R.id.nameView);

        email.setText(userInfo.getEmail());
        name.setText(userInfo.getDisplayName());
        photo.setImageURI(userInfo.getPhotoUrl());

        Picasso.with(this).load(userInfo.getPhotoUrl())
                .resize(120, 120)
                .into(photo);

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
            Intent intent = new Intent(this, AccountsActivity.class);
            startActivity(intent);
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
