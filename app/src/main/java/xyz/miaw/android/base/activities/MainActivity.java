package xyz.miaw.android.base.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import xyz.miaw.android.base.extensions.BottomNavigationViewBehavior;
import xyz.miaw.android.base.fragments.AccountsFragment;
import xyz.miaw.android.base.fragments.ConfigurationFragment;

public class MainActivity extends AppCompatActivity {

    private ViewPager _viewPager;
    private TabLayout _tabLayout;
    private BottomNavigationView _bottomNavigationView;
    private View _bottomNavigationViewInvoicesButton;
    private ViewGroup _mainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);

        // TODO: Firebase

        // Set layout
        setContentView(xyz.miaw.android.base.R.layout.activity_main);

        // Toolbar
        android.support.v7.widget.Toolbar toolbar = findViewById(xyz.miaw.android.base.R.id.all_toolbar);

        if (toolbar != null){
            toolbar.setTitle(xyz.miaw.android.base.R.string.app_name);
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(false);
        }
//
//        // TODO: check refresh token
//
//        // TODO: check version. force upgrade.
//
        // Assign items
        _mainContainer = findViewById(xyz.miaw.android.base.R.id.coordinatorlayout_main_maincontainer);
        _bottomNavigationView = findViewById(xyz.miaw.android.base.R.id.bottom_navigation);

        // BottomNavigationView
        // Listener
        _bottomNavigationView.setOnNavigationItemSelectedListener(_onNavigationItemSelectedListener);
        // Behavior (hide if scrolling down, show if scrolling up)
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) _bottomNavigationView.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior());

        // OnCreate only run once when this activity is created. So let's set the default
        // fragment to show by 'clicking' on the bottomnavigationview_invoices button.
        _bottomNavigationViewInvoicesButton = _bottomNavigationView.findViewById(xyz.miaw.android.base.R.id.menu_home);
        _bottomNavigationViewInvoicesButton.performClick();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener _onNavigationItemSelectedListener
        = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction fragmentTransaction =
                    MainActivity.super.getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {
                case xyz.miaw.android.base.R.id.menu_home:

                    AccountsFragment accountsFragment = new AccountsFragment();
                    fragmentTransaction.replace(xyz.miaw.android.base.R.id.framelayout_main_fragmentcontainer, accountsFragment);
                    fragmentTransaction.commit();

                    //mTextMessage.setText(R.string.title_home);
                    return true;

                case xyz.miaw.android.base.R.id.menu_configuration:

                    ConfigurationFragment configurationFragment = new ConfigurationFragment();
                    fragmentTransaction.replace(xyz.miaw.android.base.R.id.framelayout_main_fragmentcontainer, configurationFragment);
                    fragmentTransaction.commit();

                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };


//    private TextView mTextMessage;
//
//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
////            = new BottomNavigationView.OnNavigationItemSelectedListener() {
////
////        @Override
////        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
////
////            FragmentTransaction fragmentTransaction =
////                    MainActivity.super.getSupportFragmentManager().beginTransaction();
////
////            switch (item.getItemId()) {
////                case R.id.navigation_home:
////                    mTextMessage.setText(R.string.title_home);
////                    return true;
////                case R.id.navigation_dashboard:
////                    mTextMessage.setText(R.string.title_dashboard);
////                    return true;
////                case R.id.navigation_notifications:
////
////                    AccountsFragment accountsFragment = new AccountsFragment();
////                    fragmentTransaction.replace(R.id.framelayout_mainactivity_mainframe, accountsFragment);
////
////                    mTextMessage.setText(R.string.title_notifications);
////                    return true;
////            }
////            return false;
////        }
////    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        mTextMessage = (TextView) findViewById(R.id.message);
//        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//
//    }

}
