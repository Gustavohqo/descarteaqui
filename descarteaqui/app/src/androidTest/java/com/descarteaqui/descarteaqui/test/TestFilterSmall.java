package com.descarteaqui.descarteaqui.test;

import com.descarteaqui.descarteaqui.MainActivity;
import com.robotium.solo.*;
import android.test.ActivityInstrumentationTestCase2;


public class TestFilterSmall extends ActivityInstrumentationTestCase2<MainActivity> {
  	private Solo solo;
  	
  	public TestFilterSmall() {
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
        //Wait for activity: 'com.descarteaqui.descarteaqui.MainActivity'
		solo.waitForActivity(com.descarteaqui.descarteaqui.MainActivity.class, 2000);
        //Wait for activity: 'com.descarteaqui.descarteaqui.MainActivity'
		assertTrue("com.descarteaqui.descarteaqui.MainActivity is not found!", solo.waitForActivity(com.descarteaqui.descarteaqui.MainActivity.class));
        //Click on ImageView
		solo.clickOnView(solo.getView(0x2));
        //Click on android.view.TextureView
		solo.clickOnView(solo.getView(android.view.TextureView.class, 0));
        //Click on android.view.TextureView
		solo.clickOnView(solo.getView(android.view.TextureView.class, 0));
        //Click on android.view.TextureView
		solo.clickOnView(solo.getView(android.view.TextureView.class, 0));
        //Click on android.view.TextureView
		solo.clickOnView(solo.getView(android.view.TextureView.class, 0));
        //Click on android.view.TextureView
		solo.clickOnView(solo.getView(android.view.TextureView.class, 0));
        //Click on android.view.TextureView
		solo.clickOnView(solo.getView(android.view.TextureView.class, 0));
        //Click on android.view.TextureView
		solo.clickOnView(solo.getView(android.view.TextureView.class, 0));
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 1));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_battery));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_oil));
        //Click on ImageView
		solo.clickOnView(solo.getView(com.descarteaqui.descarteaqui.R.id.fab_clear_filter));
        //Click on android.view.TextureView
		solo.clickOnView(solo.getView(android.view.TextureView.class, 0));
        //Click on ImageView
		solo.clickOnView(solo.getView(android.widget.ImageButton.class, 6));
	}
}
