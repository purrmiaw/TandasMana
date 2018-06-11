package xyz.miaw.android.base.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import xyz.miaw.android.base.R;

public class AccountDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        // Set Layout
        setContentView(R.layout.activity_account_details);

        // Toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.all_toolbar);

        if (toolbar != null){
            toolbar.setTitle(R.string.accountdetails_title);
            setSupportActionBar(toolbar);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // update
        TextView accountNumber = (TextView) findViewById(R.id.textview_accountsdetails_accountnumber);
        accountNumber.setText("5465212345");

//        findViewById(R.id.textview_accountsdetails_accounttype).Text = "Pelajaran";
//        findViewById(R.id.textview_accountsdetails_userid).Text = "001350996199";
//        findViewById(R.id.textview_accountsdetails_bakiornilai).Text = "29,355.48";
//        findViewById(R.id.textview_accountsdetails_jumlahdibayar).Text = "10,423.23";
//        findViewById(R.id.textview_accountsdetails_jumlahtertunggak).Text = "19,577.68";
//        findViewById(R.id.textview_accountsdetails_accountstatus).Text = "PEMBAYAR, AKTIF";
//        findViewById(R.id.textview_accountsdetails_emandatestatus).Text = "TIADA";

        // Events
        // Clicks

        // Hide progressbar, show data
        ProgressBar progressBar = findViewById(R.id.progressbar_accountsdetails);
        progressBar.setVisibility(View.GONE);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearlayout_accountsdetails_container);
        linearLayout.setVisibility(View.VISIBLE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return true;
    }
}
