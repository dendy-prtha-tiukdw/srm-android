package id.ukdw.srmmobile.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.library.baseAdapters.BR;

import com.bumptech.glide.Glide;

import de.hdodenhof.circleimageview.CircleImageView;
import id.ukdw.srmmobile.R;
import id.ukdw.srmmobile.databinding.ActivityHomeBinding;
import id.ukdw.srmmobile.di.component.ActivityComponent;
import id.ukdw.srmmobile.ui.calendar.KalenderFragment;
import id.ukdw.srmmobile.ui.daftarkelas.DaftarKelasFragment;
import id.ukdw.srmmobile.ui.pengumuman.PengumumanFragment;
import id.ukdw.srmmobile.ui.profile.ProfileFragment;
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
    boolean drawerOpened = false;
    ActionBarDrawerToggle mDrawerToggle;
    TextView navTextViewName;
    TextView navTextViewEmail;
    CircleImageView navImageProfile;

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

        View header = getViewDataBinding().navView.getHeaderView(0);
        navTextViewName = header.findViewById(R.id.navTxtName);
        navTextViewEmail = header.findViewById(R.id.navTxtEmail);
        navImageProfile = header.findViewById(R.id.navImgProfile);
        
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
                    showFragmentPengumuman();
                    break;
                case R.id.nav_daftar_kelas:
                    showFragmentDaftarKelas();
                    break;
                case R.id.nav_profile:
                    showFragmentProfile();
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

    private void showFragmentPengumuman() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                PengumumanFragment.newInstance()).commit();

    }

    private void showFragmentDaftarKelas() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                DaftarKelasFragment.newInstance()).commit();
    }

    private void showFragmentProfile() {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                ProfileFragment.newInstance()).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityHomeBinding = getViewDataBinding();
        mViewModel.setNavigator(this);
        mViewModel.setContext(this);
        setSupportActionBar(getViewDataBinding().toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        prepareSideNav();
        if (savedInstanceState == null) {
            showFragmentPengumuman();
            getViewDataBinding().navView.setCheckedItem(R.id.nav_pengumuman);
        }
        mViewModel.setUpSideNavProfile();
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
       /* switch (item.getItemId()) {
            case R.id.addPengumuman:
                Intent intent = new Intent(this, TambahPengumuman.class);
                startActivity(intent);
        }*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openLoginActivity() {
        mViewModel.getGoogleSignInClient().signOut();
        startActivity(LoginActivity.newIntent(this));
        finish();
    }

    @Override
    public void onSetNavProfile(String name, String email, String imgUrl) {
        navTextViewName.setText(name);
        navTextViewEmail.setText(email);
        Glide.with(this)
                .load(imgUrl)
                .circleCrop()
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(navImageProfile);
    }

    private void checkRole() {
      /*  if (role != mahasiswa) {
            findViewById(R.id.addPengumuman).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.addPengumuman).setVisibility(View.GONE);
        }*/
    }

    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

}
