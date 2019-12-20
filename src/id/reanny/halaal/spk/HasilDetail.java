package id.reanny.halaal.spk;

import id.reanny.halaal.R;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class HasilDetail extends FragmentActivity {

	public ImageLoader imageLoader;
	{

		imageLoader = new ImageLoader(null);
	}

	GoogleMap googlemap;
	JSONArray string_json = null;

	// TODO sering lupa
	String idberita;

	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();

	public static final String TAG_ID = "id";
	public static final String TAG_NAMA = "nama";
	public static final String TAG_LOKASI = "lokasi";
	public static final String TAG_KHAS = "khas";
	public static final String TAG_CITARASA = "citarasa";
	public static final String TAG_SUASANA = "suasana";
	public static final String TAG_LAT = "lat";
	public static final String TAG_LONG = "long";
	public static final String TAG_KONTEN = "konten";
	public static final String TAG_GAMBAR = "gambar";
	private static final String url_detail_berita = "http://192.168.43.39/project/halal-culinary/android/spk-hasil-detail.php";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.spk_hasil_detail);

		ActionBar actionBar = getActionBar();

		actionBar.setDisplayHomeAsUpEnabled(true);

		Intent i = getIntent();

		idberita = i.getStringExtra(TAG_ID);

		new AmbilDetailBerita().execute();

	}

	class AmbilDetailBerita extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(HasilDetail.this);
			pDialog.setMessage("Mohon Tunggu ... !");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... args) {

			try {

				List<NameValuePair> params1 = new ArrayList<NameValuePair>();
				params1.add(new BasicNameValuePair("idberita", idberita));

				JSONObject json = jsonParser.makeHttpRequest(url_detail_berita,
						"GET", params1);

				string_json = json.getJSONArray("berita");

				runOnUiThread(new Runnable() {
					public void run() {

						MapFragment mf = (MapFragment) getFragmentManager()
								.findFragmentById(R.id.mapsaya);
						googlemap = mf.getMap();
						ImageView thumb_image = (ImageView) findViewById(R.id.imageView1);
						TextView judul = (TextView) findViewById(R.id.judul);
						TextView detail = (TextView) findViewById(R.id.detail);
						
						TextView content2 = (TextView) findViewById(R.id.content2);
						TextView citarasatv=(TextView) findViewById(R.id.citarasa);
						TextView khastv=(TextView) findViewById(R.id.khas);
						TextView suasanatv=(TextView) findViewById(R.id.suasana);

						try {
							// ambil objek member pertama dari JSON Array
							JSONObject ar = string_json.getJSONObject(0);
							String nama = ar.getString("nama");
							String lokasi = ar.getString("lokasi");
							String khas = ar.getString("khas");
							String citarasa = ar.getString("citarasa");
							String suasana = ar.getString("suasana");
							String konten = ar.getString("konten");
							String lat = ar.getString("lat");
							String lng = ar.getString("long");

							imageLoader.DisplayImage(ar.getString(TAG_GAMBAR),
									thumb_image);

							Log.d("Respon",
									idberita + " " + string_json.toString());
							judul.setText(nama);
							detail.setText(lokasi);
							suasanatv.setText(suasana);
							khastv.setText(khas);
							citarasatv.setText(citarasa);
							content2.setText(konten);
							
							
							// LatLng latlng = new
							// LatLng(Double.parseDouble(lat),
							// Double.parseDouble(lng));
							MarkerOptions marker = new MarkerOptions()
									.position(
											new LatLng(Double.parseDouble(lat),
													Double.parseDouble(lng)))
									.title(nama).visible(true);
							LatLng latlng = new LatLng(Double.parseDouble(lat),
									Double.parseDouble(lng));
							googlemap.moveCamera(CameraUpdateFactory
									.newLatLng(latlng));
							googlemap.animateCamera(CameraUpdateFactory
									.zoomTo(15));
							googlemap.addMarker(marker);

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Log.d("Respon", string_json.toString());
						}
					}
				});

			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		protected void onPostExecute(String file_url) {

			pDialog.dismiss();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;

		case R.id.exit:
			keluar();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void keluar() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Apakah Anda Ingin" + " keluar?")
				.setCancelable(false)
				.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						finish();
					}
				})
				.setNegativeButton("Tidak",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						}).show();
	}

}

class myMap extends FragmentActivity implements LocationListener {

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
		// TODO Auto-generated method stub

	}

}
