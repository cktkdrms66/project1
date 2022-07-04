package com.example.proejct1.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.kakao.sdk.common.KakaoSdk;
import com.kakao.sdk.common.util.KakaoCustomTabsClient;
import com.kakao.sdk.share.ShareClient;
import com.kakao.sdk.share.WebSharerClient;
import com.kakao.sdk.template.model.Content;
import com.kakao.sdk.template.model.FeedTemplate;
import com.kakao.sdk.template.model.ItemContent;
import com.kakao.sdk.template.model.ItemInfo;
import com.kakao.sdk.template.model.Link;
import com.kakao.sdk.template.model.Social;
import com.kakao.sdk.template.model.TextTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TabLayout layout;
    private ViewPager2 viewPager2;
    private FragmentAdapter adapter;
    private int one;

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private TextView globalScoreTxt;

    private ImageView bragImage;


    public static Context context;

    public static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    public static final int PERMISSIONS_REQUEST_CALL_PHONE = 200;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        KakaoSdk.init(this, "40d70cd10ff6eb2b1dca1f85ccb64b82");


        context = this;

        findViewByIds();
        setTab();

        setBragImage();
        setGlobalScore(Util.getData(this, "score", 0));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setGlobalScore(Util.getData(this, "score", 0));

    }

    @Override
    protected void onDestroy() {
        context = null;
        super.onDestroy();
    }

    private void findViewByIds() {
        layout = findViewById(R.id.tablayout);
        viewPager2 = findViewById(R.id.viewpager);
        globalScoreTxt = findViewById(R.id.global_score_txt);
        bragImage = findViewById(R.id.brag_image);
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

    private void setBragImage() {
        bragImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int score = Util.getData(MainActivity.this, "score", 0);
                String text;
                Random random = new Random();
                int value = random.nextInt(100);

                if (value < 10) {
                    text = "제 점수는 " + score + "입니다 부끄부끄...!!";
                } else if (value < 20) {
                    text = "제 점수는 무려 " + score + "입니다 ^~^";
                } else if (value < 40) {
                    text = "제 점수는 " + score + "입니다 ^^ 따라와보세요;;;;!!!! ^^";
                } else if (value < 60) {
                    text = "제 점수는 무려 '" + score + "' 입니다 (굿)";
                } else if (value < 80) {
                    text = "'" + score + "'. :)";
                } else {
                    text = "난 " + score + "점이다. (굿)(굿)(굿)(굿)(굿)(굿)(굿)";
                }

                FeedTemplate feedTemplate = new FeedTemplate(
                        new Content(text,
                                "https://postfiles.pstatic.net/MjAyMjA3MDRfMjU0/MDAxNjU2OTA3NjEyNjc4.7YdxLeFrn2iu3tWWEYmSrh1SHb3SyXkRAudojlR64W0g.C58TG-LzYamqGp87TdSHi91mIfY9zL5rky72Z_msNkYg.PNG.kln753/KakaoTalk_Image_2022-06-30-16-38-07.png?type=w966",
                                new Link("kakao40d70cd10ff6eb2b1dca1f85ccb64b82://kakaolink",
                                        "kakao40d70cd10ff6eb2b1dca1f85ccb64b82://kakaolink")
                        ),
                        new ItemContent(),
                        new Social(),
                        Arrays.asList(new com.kakao.sdk.template.model.Button("앱으로 보기", new Link("kakao40d70cd10ff6eb2b1dca1f85ccb64b82://kakaolink", "kakao40d70cd10ff6eb2b1dca1f85ccb64b82://kakaolink")))
                );

                if (ShareClient.getInstance().isKakaoTalkSharingAvailable(context)) {
                    ShareClient.getInstance().shareDefault(context, feedTemplate, (sharingResult, throwable) -> {
                        startActivity(sharingResult.getIntent());
                        return null;
                    });
                } else {
                    try {
                        KakaoCustomTabsClient.INSTANCE.openWithDefault(context, WebSharerClient.getInstance().makeDefaultUrl(feedTemplate));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

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

        fragment1.setContacts(contacts);
    }



    public void setGlobalScore(int score) {
        globalScoreTxt.setText(String.valueOf(score));
    }
}

