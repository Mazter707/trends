package com.mazter707.trends;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.mazter707.trends.fragments.FavFragment;
import com.mazter707.trends.fragments.HistoryFragment;
import com.mazter707.trends.fragments.NavigateFragment;
import com.mazter707.trends.fragments.NotifyFragment;
import com.mazter707.trends.fragments.SearchFragment;
import com.mazter707.trends.fragments.SendFragment;
import com.mazter707.trends.fragments.ShareFragment;
import com.mazter707.trends.fragments.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, NavigateFragment.OnFragmentInteractionListener,
        SearchFragment.OnFragmentInteractionListener, NotifyFragment.OnFragmentInteractionListener,
        ShoppingFragment.OnFragmentInteractionListener, HistoryFragment.OnFragmentInteractionListener,
        FavFragment.OnFragmentInteractionListener, ShareFragment.OnFragmentInteractionListener, SendFragment.OnFragmentInteractionListener {

    private List<Album> albumList;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_products);

        mRecyclerView.setHasFixedSize(true);

        albumList = new ArrayList<>();
        adapter = new ProductAdapter(this, albumList);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setAdapter(adapter);

        prepareAlbums();

        try{
            Glide.with(this).load(R.drawable.cover).into((ImageView) findViewById(R.id.image_product));
        }catch (Exception e){
            e.printStackTrace();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (AccessToken.getCurrentAccessToken()==null) {
            goLoginScreen();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with the fab activity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void prepareAlbums() {
        int [] covers = new  int[]{
                R.drawable.accesorios,
                R.drawable.chamarra_rojo,
                R.drawable.colorete,
                R.drawable.delineador,
                R.drawable.leggins_amarillo,
                R.drawable.maquillaje,
                R.drawable.tenis_blanco,
                R.drawable.tenisrosa,
                R.drawable.chamarra_camuflaje,
                R.drawable.chamarra_chida,
                R.drawable.bolso_dama,
                R.drawable.gafas};
        Album a = new Album(covers[0], "$ 760");
        albumList.add(a);

        a = new Album(covers[1], "$ 760");
        albumList.add(a);

        a = new Album(covers[2], "$ 1760");
        albumList.add(a);

        a = new Album(covers[3], "$ 60");
        albumList.add(a);

        a = new Album(covers[4], "$ 70");
        albumList.add(a);

        a = new Album(covers[5], "$ 160");
        albumList.add(a);

        a = new Album(covers[6], "$ 76");
        albumList.add(a);

        a = new Album(covers[7], "$ 1700");
        albumList.add(a);

        a = new Album(covers[8], "$ 1600");
        albumList.add(a);

        a = new Album(covers[9], "$ 760");
        albumList.add(a);

        a = new Album(covers[10], "$ 760");
        albumList.add(a);

        a = new Album(covers[11], "$ 700");
        albumList.add(a);

        adapter.notifyDataSetChanged();

    }

    private void goLoginScreen() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public void logout(View view) {
        LoginManager.getInstance().logOut();
        goLoginScreen();
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        boolean FragmentTransaction = false;
        Fragment fragment = null;


        if (id == R.id.nav_navigate) {
            // Handle the camera action
            fragment = new NavigateFragment();
            FragmentTransaction = true;
        } else if (id == R.id.nav_notify) {
            fragment = new NotifyFragment();
            FragmentTransaction = true;

        } else if (id == R.id.nav_shopping) {
            fragment = new ShoppingFragment();
            FragmentTransaction = true;

        } else if (id == R.id.nav_share) {
            fragment = new ShareFragment();
            FragmentTransaction = true;

        } else if (id == R.id.nav_send) {
            fragment = new SendFragment();
            FragmentTransaction = true;

        }else if (id == R.id.nav_history) {
            fragment = new HistoryFragment();
            FragmentTransaction = true;

        }else if (id == R.id.nav_fav) {
            fragment = new FavFragment();
            FragmentTransaction = true;

        }

        if(FragmentTransaction){
            getSupportFragmentManager().beginTransaction().replace(R.id.content_main, fragment).commit();

            item.setChecked(true);
            getSupportActionBar().setTitle(item.getTitle());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
