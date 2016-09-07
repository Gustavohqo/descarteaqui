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
import com.facebook.Profile;
import android.widget.Toast;

import com.descarteaqui.descarteaqui.fragments.MapsFragment;
import com.descarteaqui.descarteaqui.fragments.PetiFragment;
import com.descarteaqui.descarteaqui.fragments.TipFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    private TextView nome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println(App.getInstance());
        if (App.getInstance().getProfile() != null){
            View header=navigationView.getHeaderView(0);
            nome = (TextView)header.findViewById(R.id.textView);
            nome.setText("Olá, " + App.getInstance().getProfile().getFirstName());
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
        nome = (TextView)header.findViewById(R.id.textView);
        nome.setText("Olá, " + Profile.getCurrentProfile().getFirstName());
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
