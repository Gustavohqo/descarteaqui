package com.descarteaqui.descarteaqui.test;

import com.descarteaqui.descarteaqui.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class TestFilter extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public TestFilter() {
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
        //Take screenshot
        solo.takeScreenshot();
        //Take screenshot
        solo.takeScreenshot();
        //Take screenshot
        solo.takeScreenshot();
        //Take screenshot
        solo.takeScreenshot();
        //Wait for activity: 'com.descarteaqui.descarteaqui.MainActivity'
		solo.waitForActivity(com.descarteaqui.descarteaqui.MainActivity.class, 2000);
        //Click on ImageView
		solo.clickOnView(solo.getView(0x2));
        //Click on android.view.TextureView
		solo.clickOnView(solo.getView(android.view.TextureView.class, 0));
        //Click on android.view.TextureView
		solo.clickOnView(solo.getView(android.view.TextureView.class, 0));
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 1));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_chemistry));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_hospital));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_hospital));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_hospital));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_oil));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_battery));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_selective));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_selective));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_clear_filter));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_hospital));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_chemistry));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_clear_filter));
	}
}
