package com.example.proejct1.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.proejct1.R;
import com.example.proejct1.contact.ContactAdapter;
import com.example.proejct1.fragment.Fragment1;
import com.example.proejct1.fragment.Fragment2;
import com.example.proejct1.fragment.Fragment3;
import com.example.proejct1.fragment.FragmentAdapter;
import com.example.proejct1.model.Contact;
import com.example.proejct1.model.Person;
import com.example.proejct1.util.Util;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TabLayout layout;
    private ViewPager2 viewPager2;
    private FragmentAdapter adapter;
    private int one;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    public static Context context;

    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    public static final int PERMISSIONS_REQUEST_CALL_PHONE = 200;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        context = this;

        findViewByIds();
        setTab();

        setPersonData();
    }

    @Override
    protected void onDestroy() {
        context = null;
        super.onDestroy();
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

    public void callContactPermission() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // 해당 로직으로 이동
            setContactData();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                setContactData();
            } else {
                Toast.makeText(this, "권한을 허용해주세요", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                ContactAdapter.callTarget();
            }
        }
    }

    @SuppressLint("Range")
    private void setContactData() {

        ArrayList<Contact> contacts = new ArrayList<>();

        Cursor c = getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                null, null, null);
        while (c.moveToNext()) {

            String name = c
                    .getString(c
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = c
                    .getString(c
                            .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));


            contacts.add(new Contact(name, number));

        }
        c.close();

        for (int i =0 ; i< 100; i++) {
            contacts.add(new Contact("q" + i, "" + i));
        }


        fragment1.setContacts(contacts);

    }

    public void setPersonData() {
        String personsTxt = Util.readRawTxt(this, R.raw.persons);

        ObjectMapper mapper = new ObjectMapper();

        try {
            List<Person> persons = mapper.readValue(personsTxt, new TypeReference<List<Person>>(){});
            fragment3.setPersons(persons);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
