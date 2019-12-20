package id.reanny.halaal.bantuan;

import id.reanny.halaal.R;
import id.reanny.halaal.R.layout;
import id.reanny.halaal.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Bantuan extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.awal_bantuan, container,
				false);
		WebView myWebView = (WebView) rootView.findViewById(R.id.webview);
		myWebView.loadUrl("file:///android_asset/www/index.html");
		myWebView.setWebViewClient(new WebViewClient());
		return rootView;
	}

}
