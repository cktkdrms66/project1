package com.example.proejct1;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TableLayout;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.ActivityResultRegistry;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.proejct1.fragment.Fragment1;
import com.example.proejct1.fragment.Fragment2;
import com.example.proejct1.fragment.Fragment3;
import com.example.proejct1.fragment.FragmentAdapter;
import com.example.proejct1.model.Contact;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TabActivity extends AppCompatActivity {

    private TabLayout layout;
    private ViewPager2 viewPager2;
    private FragmentAdapter adapter;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    private ArrayList<Contact> contacts;

    private static final int CONTACT_RESULT_CODE = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        findViewByIds();

        setTab();

        requestContactsData();
    }

    private void findViewByIds() {
        layout = findViewById(R.id.tablayout);
        viewPager2 = findViewById(R.id.viewpager);

    }

    private void setTab() {
        adapter = new FragmentAdapter(getSupportFragmentManager(), getLifecycle());

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();

        adapter.addFragment(fragment1);
        adapter.addFragment(fragment2);
        adapter.addFragment(fragment3);

        viewPager2.setAdapter(adapter);

        viewPager2.setUserInputEnabled(true);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                layout.selectTab(layout.getTabAt(position));
            }
        });

        layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    private void requestContactsData() {

        contacts = new ArrayList<>();

        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setData(ContactsContract.CommonDataKinds.Phone.CONTENT_URI);

        ActivityResultLauncher<Intent> activityResultLauncher
                = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    System.out.println("돌아옴");


                    Cursor cursor = getContentResolver().query(result.getData().getData(),
                            new String[] { ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                                    ContactsContract.CommonDataKinds.Phone.NUMBER },
                            null, null, null);

                    while (cursor.moveToNext()) {
                        String name = cursor.getString(0);
                        String number = cursor.getString(1);
                        System.out.println(name);
                        contacts.add(new Contact(name, number));
                    }

                    cursor.close();

                    fragment1.setContacts(contacts);

                }
            }
        });
        activityResultLauncher.launch(intent);

    }


}
