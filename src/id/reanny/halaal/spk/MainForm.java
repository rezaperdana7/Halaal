package id.reanny.halaal.spk;

import id.reanny.halaal.R;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class MainForm extends Activity {

	private ProgressDialog pdialog;

	JSONParser jsonParser = new JSONParser();
	Spinner spinnerharga, spinnerlama, spinnermenu, spinnersejarah,
			spinnerbuka, spinnercitarasa, spinnerkhas;
	Button tombol;

	// inisialisasi tambah.php
	// private static String url_tambah =
	// "http://192.168.1.45/project/CRUDsimpelJSON/tambah.php";
	// private static String url_tambah =
	// "http://192.168.1.2/project/halal-culinary/android/spk-topsis.php";
	private static String url_tambah = "http://192.168.43.39/project/halal-culinary/android/spk-topsis.php";
	// inisisalisasi node JSON

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.spk_main_form);
		ActionBar actionBar = getActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);

		spinnerharga = (Spinner) findViewById(R.id.spinnerharga);
		spinnerlama = (Spinner) findViewById(R.id.spinnerlama);
		spinnermenu = (Spinner) findViewById(R.id.spinnermenu);
		spinnersejarah = (Spinner) findViewById(R.id.spinnersejarah);
		spinnerbuka = (Spinner) findViewById(R.id.spinnerbuka);

		spinnercitarasa = (Spinner) findViewById(R.id.spinnercitarasa);
		spinnerkhas = (Spinner) findViewById(R.id.spinnerkhas);

		tombol = (Button) findViewById(R.id.button1);
		tombol.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				new BuatAnggotaBaru().execute();
			}
		});
	}
	
	class BuatAnggotaBaru extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pdialog = new ProgressDialog(MainForm.this);
			pdialog.setMessage("Silahkan Menunggu");
			pdialog.setIndeterminate(false);
			pdialog.setCancelable(true);
			pdialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			// TODO Auto-generated method stub
			// ambil semua value
			String harga = spinnerharga.getSelectedItem().toString();
			String lama = spinnerlama.getSelectedItem().toString();
			String menu = spinnermenu.getSelectedItem().toString();
			String sejarah = spinnersejarah.getSelectedItem().toString();
			String buka = spinnerbuka.getSelectedItem().toString();
			String citarasa = spinnercitarasa.getSelectedItem().toString();
			String khas= spinnerkhas.getSelectedItem().toString();
			
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("harga", harga));
			params.add(new BasicNameValuePair("lama", lama));
			params.add(new BasicNameValuePair("menu", menu));
			params.add(new BasicNameValuePair("sejarah", sejarah));
			params.add(new BasicNameValuePair("buka", buka));
			params.add(new BasicNameValuePair("citarasa", citarasa));
			params.add(new BasicNameValuePair("khas", khas));
			JSONObject json = jsonParser.makeHttpRequest(url_tambah, "POST",
					params);
			// periksa respon logcat

			Log.d("Respon tambah anggota", json.toString());
			Log.d("Citarasa=",citarasa);
			// cek TAG_SUKSES

			// int sukses = json.getInt(TAG_SUKSES);

			// kalau sukses nambah data
			Intent i = new Intent(getApplicationContext(), HasilForm.class);
			startActivity(i);
			// tutup activity ini
			finish();

			return null;
		}

		protected void onPostExecute(String file_url) {
			// hilangkan dialognya
			pdialog.dismiss();
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_form, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {

		case android.R.id.home:
			finish();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
