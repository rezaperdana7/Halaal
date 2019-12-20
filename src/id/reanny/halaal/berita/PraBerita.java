package id.reanny.halaal.berita;

import id.reanny.halaal.R;
import id.reanny.halaal.R.layout;
import id.reanny.halaal.R.menu;
import android.os.Bundle;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

class PraBeritaAct extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.berita_pra_berita);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pra_berita, menu);
		return true;
	}

}

public class PraBerita extends Fragment {
	Button tombol;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.berita_pra_berita, container,
				false);
		tombol = (Button) rootView.findViewById(R.id.button1);
		tombol.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),LokoUtama.class);
				startActivity(intent);
			}
		});

		
		
		return rootView;
	}
}
