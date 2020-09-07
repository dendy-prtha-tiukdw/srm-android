package id.ukdw.srmmobile.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.library.baseAdapters.BR;

import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityHomeBinding;

import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.KalenderFragment;
import id.ukdw.srmmobile.ui.KelasFragment;
import id.ukdw.srmmobile.ui.PengumumanFragment;
import id.ukdw.srmmobile.ui.ProfilFragment;
import id.ukdw.srmmobile.ui.TambahPengumuman;
import id.ukdw.srmmobile.ui.base.BaseActivity;
import id.ukdw.srmmobile.ui.login.LoginActivity;


/**
 * Project: srmmobile
 * Package: id.ukdw.srmmobile.ui.home
 * <p>
 * User: dendy
 * Date: 03/09/2020
 * Time: 10:51
 * <p>
 * Description : HomeActivity
 */
public class HomeActivity extends BaseActivity<ActivityHomeBinding, HomeViewModel> implements HomeNavigator {
    private static final String TAG = HomeActivity.class.getSimpleName();
    private ActivityHomeBinding activityHomeBinding;
    private String role;
    private String mahasiswa;
    private String staff;
    private String dosen;
    boolean drawerOpened = false;

    ActionBarDrawerToggle mDrawerToggle;

    public static Intent newIntent(Context context) {
        return new Intent(context, HomeActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.homeViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    //prepare side nav
    private void prepareSideNav() {

        mDrawerToggle = new ActionBarDrawerToggle(this, getViewDataBinding().drawerLayout,
                getViewDataBinding().toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
                drawerOpened = false;
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
                drawerOpened = true;
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        getViewDataBinding().drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        getViewDataBinding().navView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_pengumuman:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new PengumumanFragment()).commit();
                    break;
                case R.id.nav_daftar_kelas:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new KelasFragment()).commit();
                    break;
                case R.id.nav_profile:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new ProfilFragment()).commit();
                    break;
                case R.id.nav_calendar:
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            new KalenderFragment()).commit();
                    break;
                case R.id.nav_signOut:
                    mViewModel.logOut();
                    break;
            }
            getViewDataBinding().drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        setSupportActionBar(getViewDataBinding().toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prepareSideNav();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PengumumanFragment()).commit();
            getViewDataBinding().navView.setCheckedItem(R.id.nav_pengumuman);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_menu, menu);
        return true;
    }

    //Onclick on each icon Appbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addPengumuman:
                Intent intent = new Intent(this, TambahPengumuman.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openLoginActivity() {
        startActivity(LoginActivity.newIntent(this));
        finish();
    }

    private void checkRole() {
        if (role != mahasiswa) {
            findViewById(R.id.addPengumuman).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.addPengumuman).setVisibility(View.GONE);
        }
    }

    public void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

}
