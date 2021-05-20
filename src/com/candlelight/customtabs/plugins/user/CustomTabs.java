
/*
 DroidScript Plugin class.
 (This is where you put your plugin code)
 */

package com.candlelight.customtabs.plugins.user;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import java.lang.reflect.Method;

public class CustomTabs
{
	public static String TAG = "CustomTabs";	
	public static float VERSION = 3.0f;	
	private Method m_callscript;
	private Object m_parent;
	private Context m_ctx;

    private CustomTabsManager tabsManager;

	//Contruct plugin.
	public CustomTabs() {
		Log.d(TAG, "Creating plugin object");
	}

	//Initialise plugin.
	public void Init(Context ctx, Object parent) {
		try {
			Log.d(TAG, "Initialising plugin object");
			m_ctx = ctx;
			m_parent = parent;
			m_callscript = parent.getClass().getMethod("CallScript", Bundle.class);

			//Your initialisation code goes here.
            tabsManager = new CustomTabsManager(m_ctx);
        } 
        catch(Exception e) {
            Log.e(TAG, "Failed to Initialise plugin!", e);
        }
	}

	//Release plugin resources.
	public void Release() {
		//Your tidy up code goes here.
		//...
	}

	//Use this method to call a function in the user's script.
	private void CallScript(Bundle b) {
		try {
			m_callscript.invoke(m_parent, b);
		}
		catch(Exception e) {
			Log.e(TAG, "Failed to call script function!", e);
		}
	}

	//Handle commands from DroidScript.
	public String CallPlugin(Bundle b) {
		//Extract command.
		String cmd = b.getString("cmd");

		//Process commands.
		String ret = null;
		try {
			switch(cmd) {
                case "open":
                    tabsManager.openCustomTab(b.getString("p1"));
                    break;
				case "packageNames":
					return android.text.TextUtils.join(",", tabsManager.getPackagesSupportingCustomTabs());
                default:
                    return Float.toString(VERSION);
            }
		} 
		catch(Exception e) {
            Log.e(TAG, "Plugin command failed!", e);
		}
		return ret;
	}
} 


