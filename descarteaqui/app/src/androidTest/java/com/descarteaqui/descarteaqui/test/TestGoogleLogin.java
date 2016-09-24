package com.descarteaqui.descarteaqui.test;

import com.descarteaqui.descarteaqui.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class TestGoogleLogin extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public TestGoogleLogin() {
		super(MainActivity.class);
  	}

  	public void setUp() throws Exception {
        super.setUp();
		solo = new Solo(getInstrumentation());
		getActivity();
  	}
  
   	@Override
   	public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
  	}
  
	public void testRun() {
        //Take screenshot
        solo.takeScreenshot();
        //Wait for activity: 'com.descarteaqui.descarteaqui.MainActivity'
		solo.waitForActivity(com.descarteaqui.descarteaqui.MainActivity.class, 2000);
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 0));
        //Click on Contas FrameLayout
		solo.clickInRecyclerView(6, 0);
        //Wait for activity: 'com.descarteaqui.descarteaqui.activities.AccountsActivity'
		assertTrue("com.descarteaqui.descarteaqui.activities.AccountsActivity is not found!", solo.waitForActivity(com.descarteaqui.descarteaqui.activities.AccountsActivity.class));
        //Wait for dialog
		solo.waitForDialogToOpen(5000);
        //Click on Fazer login
		solo.clickOnText(java.util.regex.Pattern.quote("Fazer login"));
        //Wait for activity: 'com.google.android.gms.auth.api.signin.internal.SignInHubActivity'
		assertTrue("com.google.android.gms.auth.api.signin.internal.SignInHubActivity is not found!", solo.waitForActivity(com.google.android.gms.auth.api.signin.internal.SignInHubActivity.class));
        //Wait for activity: 'com.descarteaqui.descarteaqui.MainActivity'
		assertTrue("com.descarteaqui.descarteaqui.MainActivity is not found!", solo.waitForActivity(com.descarteaqui.descarteaqui.MainActivity.class));
	}
}
