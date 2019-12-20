package id.reanny.halaal.hubungikami;

import id.reanny.halaal.R;
import id.reanny.halaal.R.layout;
import id.reanny.halaal.R.menu;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;



public class HubungiKami extends Fragment
{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.awal_hubungi_kami, container,
				false);
		
		Button button1=(Button)rootView.findViewById(R.id.button1);
		button1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("http://www.twitter.com/rezaperdana7"));
                    startActivity(myWebLink);
			}
		});
		
		Button button2=(Button)rootView.findViewById(R.id.button2);
		button2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("http://www.facebook.com/rezaperdana7"));
                    startActivity(myWebLink);
			}
		});
		
		Button button3=(Button)rootView.findViewById(R.id.button3);
		button3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent myWebLink = new Intent(android.content.Intent.ACTION_VIEW);
                myWebLink.setData(Uri.parse("http://plus.google.com/+RezaPerdana/"));
                    startActivity(myWebLink);
			}
		});

		return rootView;
	}
	
	
}
