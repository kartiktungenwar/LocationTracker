package com.android.locationtracker;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

public class NotificationListActivity extends BaseActivity {
        private RelativeLayout listLayout;
        private LinearLayout noNotificationLayout, loadingNotificationLayout;
        private ListView listViewNotification;
        private List<NotificationObject> notificationList;
        private NotificationAdapter pAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_notification_list);
            getSupportActionBar().setTitle("Notifications");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            listLayout = (RelativeLayout) findViewById(R.id.listLayout);
            noNotificationLayout = (LinearLayout) findViewById(R.id.noNotificationLayout);
            loadingNotificationLayout = (LinearLayout) findViewById(R.id.loadingNotificationLayout);
            listViewNotification = (ListView) findViewById(R.id.listViewNotification);

            notificationList = new ArrayList<>();
            dataManager.open();
            notificationList = dataManager.getnotificationByDate();
            dataManager.close();


            pAdapter = new NotificationAdapter(com.android.locationtracker.NotificationListActivity.this);
            pAdapter.setList(notificationList);
            listViewNotification.setAdapter(pAdapter);


        }
}
