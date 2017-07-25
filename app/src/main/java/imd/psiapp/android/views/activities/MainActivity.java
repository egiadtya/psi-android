package imd.psiapp.android.views.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import imd.psiapp.android.R;
import imd.psiapp.android.views.fragments.PSIMapFragment;

/**
 * this class for showing main page
 * Created by egiadtya on 7/25/17.
 */
public class MainActivity extends AppCompatActivity {
    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @Nullable
    @BindView(R.id.tv_page_title)
    TextView tvTitle;
    @BindView(R.id.tab_layout)
    TabLayout tabMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        initTabMenu();
        changeFragment(new PSIMapFragment()); //default selected
    }

    /**
     * for set page title
     *
     * @param title {@link String} of page title
     */
    public void setTitle(String title) {
        if (tvTitle != null) {
            tvTitle.setText(title);
        }
    }

    /**
     * method for initialize tab menu
     */
    private void initTabMenu() {
        tabMenu.addTab(tabMenu.newTab().setText(R.string.map), true);
        tabMenu.addTab(tabMenu.newTab().setText(R.string.three_hr_psi));
        tabMenu.addTab(tabMenu.newTab().setText(R.string.one_day_psi));
        tabMenu.addTab(tabMenu.newTab().setText(R.string.pollutan_sub_indice));
        tabMenu.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    changeFragment(new PSIMapFragment());
                } else if (tab.getPosition() == 1) {
                } else if (tab.getPosition() == 2) {
                } else if (tab.getPosition() == 3) {
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * for change tab menu fragment
     *
     * @param fragment {@link Fragment} page will be shown
     */
    private void changeFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
