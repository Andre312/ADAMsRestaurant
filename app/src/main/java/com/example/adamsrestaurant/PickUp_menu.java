package com.example.adamsrestaurant;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class PickUp_menu extends AppCompatActivity {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private StartersFragment startersFragment;
    private SaladsFragment saladsFragment;
    private PastaFragment pastaFragment;
    private PizzaFragment pizzaFragment;
    private BurgersFragment burgersFragment;
    private KidsFragment kidsFragment;
    private GrillFragment grillFragment;
    private FishFragment fishFragment;
    private DessertFragment dessertFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_up_menu);

        toolbar = findViewById(R.id.toolbar_complete);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.view_pager);
        toolbar = findViewById(R.id.menu_toolbar);
        tabLayout = findViewById(R.id.tab_layout);

        startersFragment = new StartersFragment();
        saladsFragment =new SaladsFragment();
        pastaFragment= new PastaFragment();
        pizzaFragment = new PizzaFragment();
        burgersFragment = new BurgersFragment();
        kidsFragment = new KidsFragment();
        grillFragment = new GrillFragment();
        fishFragment = new FishFragment();
        dessertFragment = new DessertFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(startersFragment, "Starters");
        viewPagerAdapter.addFragment(saladsFragment, "Salads");
        viewPagerAdapter.addFragment(pastaFragment, "Pasta");
        viewPagerAdapter.addFragment(pizzaFragment, "Pizza");
        viewPagerAdapter.addFragment(burgersFragment, "Burgers");
        viewPagerAdapter.addFragment(kidsFragment, "Kids' Section");
        viewPagerAdapter.addFragment(grillFragment, "Grill");
        viewPagerAdapter.addFragment(fishFragment, "Fish");
        viewPagerAdapter.addFragment(dessertFragment, "Dessert");

        viewPager.setAdapter(viewPagerAdapter);

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> fragmentTitle = new ArrayList<>();

        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            fragmentTitle.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return fragmentTitle.get(position);
        }
    }
}
