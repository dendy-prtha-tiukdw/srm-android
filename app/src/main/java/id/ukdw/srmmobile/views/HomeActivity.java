package id.ukdw.srmmobile.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

import id.ukdw.srmmobile.R;

//import android.widget.Toolbar;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private GoogleSignInClient mGoogleSignInClient;
    private String role;
    private String mahasiswa;
    private String staff;
    private String dosen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        String serverClientId = getString( R.string.server_client_id );
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder( GoogleSignInOptions.DEFAULT_SIGN_IN )
                .requestScopes( new Scope( Scopes.DRIVE_APPFOLDER ) )
                .requestServerAuthCode( serverClientId )
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient( this, gso );

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer=findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new PengumumanFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_pengumuman);
        }

        mahasiswa = getString(R.string.mahasiswa);
    }

    //sidebar
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_pengumuman:
                checkRole();
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
                signOut();
                revokeAccess();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Customize Icon Appbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar_menu, menu);
        return true;
    }

    //Onclick on each icon Appbar
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.addPengumuman:
                Intent intent = new Intent(HomeActivity.this, TambahPengumuman.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override

                    public void onComplete(@NonNull Task<Void> task) {
                        //hit API signOut
                    }
                });
    }

    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
    }

    private void checkRole(){
        if(role != mahasiswa){
            findViewById(R.id.addPengumuman).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.addPengumuman).setVisibility(View.GONE);
        }
    }
}
