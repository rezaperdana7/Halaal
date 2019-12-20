package id.reanny.halaal;

import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;

public class SplashScreen extends Activity {

	public void onAttachedToWindow()
	{
		super.onAttachedToWindow();
		Window window=getWindow();
		window.setFormat(PixelFormat.RGBA_8888);
	}
	// timer untuk splash screen
	private static int SPLASH_TIME_OUT=5000;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.awal_splash);
		ActionBar actionBar = getActionBar();
		actionBar.hide();
		StartAnimations();
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent i=new Intent(SplashScreen.this,MainActivity.class);
				startActivity(i);
				
				finish();
			}
		},SPLASH_TIME_OUT);
	}

	private void StartAnimations() {
		// TODO Auto-generated method stub
		Animation anim=AnimationUtils.loadAnimation(this, R.anim.alpha);
		anim.reset();
		LinearLayout l=(LinearLayout) findViewById(R.id.lin_lay);
		l.clearAnimation();
		l.startAnimation(anim);
		anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);
        TextView tv = (TextView) findViewById(R.id.textView1);
        tv.clearAnimation();
        tv.startAnimation(anim);
	}

}
