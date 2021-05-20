package com.candlelight.customtabs.plugins.user;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.browser.customtabs.CustomTabsCallback;
import androidx.browser.customtabs.CustomTabsClient;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsService;
import androidx.browser.customtabs.CustomTabsServiceConnection;
import androidx.browser.customtabs.CustomTabsSession;
import java.util.ArrayList;
import java.util.List;

public class CustomTabsManager {
    final String BROWSER_CHROME = "com.android.chrome";
    final String BROWSER_SAMSUNG = "com.sec.android.app.sbrowser";

    private String customTabsPackageName;
    private ArrayList<String> packagesSupportingCustomTabs;

    // Tabs fields.
    private CustomTabsClient mCustomTabsClient;
    private CustomTabsIntent customTabsIntent;
    private CustomTabsSession session;

    Activity activity;

    public CustomTabsManager(Context context) {
        activity = (Activity) context;
        packagesSupportingCustomTabs = getCustomTabsPackageList();
        customTabsPackageName = getCustomTabsPackageName(packagesSupportingCustomTabs);

        CustomTabsClient.bindCustomTabsService(context, customTabsPackageName, connection);

        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        builder.setShowTitle(true);
        builder.enableUrlBarHiding();
        //        builder.setStartAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        //        builder.setExitAnimations(context, android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        customTabsIntent = builder.build();
        customTabsIntent.intent.setPackage(customTabsPackageName);
		customTabsIntent.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }

    /**
     * Open URL
     */
    public void openCustomTab(String url) {
        // session.mayLaunchUrl(Uri.parse(url), null, null);
        customTabsIntent.launchUrl(activity, Uri.parse(url));
    }

    CustomTabsServiceConnection connection = new CustomTabsServiceConnection() {
        @Override
        public void onCustomTabsServiceConnected(ComponentName name, CustomTabsClient client) {
            mCustomTabsClient = client;
            session = mCustomTabsClient.newSession(callback);
            mCustomTabsClient.warmup(0);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mCustomTabsClient = null;
        }
    };

    CustomTabsCallback callback = new CustomTabsCallback() {};

    /**
     * Returns a package name that support Custom Tabs.
     */
    public String getCustomTabsPackageName(ArrayList<String> packages) {
        // If chrome exists select chrome default.
        if (packages.contains(BROWSER_CHROME))
            return BROWSER_CHROME;
        else if (packages.contains(BROWSER_SAMSUNG))
            return BROWSER_SAMSUNG;
        else
            return packages.get(0);
    }

    /**
     * Returns a packages list that support Custom Tabs.
     */
    public ArrayList<String> getCustomTabsPackageList() {
        PackageManager pm = activity.getPackageManager();
        // Get default VIEW intent handler.
        Intent activityIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.example.com"));

        // Get all apps that can handle VIEW intents.
        List<ResolveInfo> resolvedActivityList = pm.queryIntentActivities(activityIntent, PackageManager.MATCH_ALL);
        ArrayList<String> listSupportingCustomTabs = new ArrayList<>();
        for (ResolveInfo info: resolvedActivityList) {
            Intent serviceIntent = new Intent();
            serviceIntent.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
            serviceIntent.setPackage(info.activityInfo.packageName);

            // Check if this package also resolves the Custom Tabs service.
            if (pm.resolveService(serviceIntent, 0) != null) {
                listSupportingCustomTabs.add(info.activityInfo.packageName);
            }
        }

        return listSupportingCustomTabs;
    }

    /**
     * Returns a ArrayList that support Custom Tabs variable.
     */
    public List<String> getPackagesSupportingCustomTabs() {
        return packagesSupportingCustomTabs;
    }
}
