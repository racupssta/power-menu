/*
 * MainActivity.java
 *
 * Author: Ming Tsang
 * Copyright (c) 2015 Ming Tsang
 * Refer to LICENSE for details
 */

package com.nkming.powermenu;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// Disable the standard activity launch animation
		overridePendingTransition(0, 0);
		super.onCreate(savedInstanceState);

		if (!ensureDeviceAdmin())
		{
			// We don't want the animation here
			super.finish();
			return;
		}

		setContentView(R.layout.activity_main);
		if (savedInstanceState == null)
		{
			mFrag = new MainFragment();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, mFrag)
					.commit();
		}
	}

	@Override
	protected void onUserLeaveHint()
	{
		super.onUserLeaveHint();
		overridePendingTransition(0, R.anim.activity_close_exit);
	}

	@Override
	public void finish()
	{
		super.finish();
		overridePendingTransition(0, R.anim.activity_close_exit);
	}

	/**
	 * Ensure device admin rights are granted to this app. If not, the app would
	 * be finished and direct user to the device admin settings instead
	 */
	private boolean ensureDeviceAdmin()
	{
		if (!SystemHelper.isDeviceAdmin(this))
		{
			SystemHelper.enableDeviceAdmin(this);
			return false;
		}
		else
		{
			return true;
		}
	}

	private MainFragment mFrag;
}
