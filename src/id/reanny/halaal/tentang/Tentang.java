package id.reanny.halaal.tentang;

import java.util.ArrayList;
import java.util.HashMap;

import id.reanny.halaal.R;
import id.reanny.halaal.R.layout;
import id.reanny.halaal.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Tentang extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.awal_tentang, container,
				false);
		// ArrayList<HashMap<String, String>> DaftarBerita = new
		// ArrayList<HashMap<String, String>>();

		return rootView;
	}
}
