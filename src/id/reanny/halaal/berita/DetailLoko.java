package id.reanny.halaal.berita;

import id.reanny.halaal.MainActivity;
import id.reanny.halaal.R;
import id.reanny.halaal.R.id;
import id.reanny.halaal.R.layout;
import id.reanny.halaal.R.menu;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailLoko extends Activity {

	public ImageLoader imageLoader;
	{

		imageLoader = new ImageLoader(null);
	}

	
	JSONArray string_json = null;

	String idberita;

	private ProgressDialog pDialog;

	JSONParser jsonParser = new JSONParser();

	public static final String TAG_ID = "id";
	public static final String TAG_JUDUL = "judul";
	public static final String TAG_GAMBAR = "gambar";
	//private static final String url_detail_berita = "http://192.168.1.2/project/halal-culinary/android/detailberita.php";
	private static final String url_detail_berita = "http://192.168.43.39/project/halal-culinary/android/detailberita.php";

	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.berita_single);
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
			pDialog = new ProgressDialog(DetailLoko.this);
			pDialog.setMessage("Mohon Tunggu ... !");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		protected String doInBackground(String... params) {

					try {

						List<NameValuePair> params1 = new ArrayList<NameValuePair>();
						params1.add(new BasicNameValuePair("idberita",idberita));

						JSONObject json = jsonParser.makeHttpRequest(
								url_detail_berita, "GET", params1);
						string_json = json.getJSONArray("berita");

							runOnUiThread(new Runnable() {
								public void run() {

									ImageView thumb_image = (ImageView) findViewById(R.id.imageView1);
									TextView judul = (TextView) findViewById(R.id.judul);
							        TextView detail = (TextView) findViewById(R.id.detail);
							        TextView isi = (TextView) findViewById(R.id.content);

							try {
								// ambil objek member pertama dari JSON Array
								JSONObject ar = string_json.getJSONObject(0);
								String judul_d = ar.getString("judul");
								String detail_d = ar.getString("hari")+" , "+ar.getString("tanggal")+" Diposting Oleh : "+ar.getString("username");
								String isi_d = ar.getString("isi");	
								
					        judul.setText(judul_d);
					        detail.setText(detail_d);
					        isi.setText(isi_d);
					        
							imageLoader.DisplayImage(ar.getString(TAG_GAMBAR),thumb_image);		        
							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
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
	public boolean onOptionsItemSelected(MenuItem item)
    {
         
        switch (item.getItemId())
        {
        
 
        case R.id.exit:
        	keluar();
        	return true;
 
        case android.R.id.home:
			finish();
			return true;
        	
        default:
            return super.onOptionsItemSelected(item);
        }
    }    
	public void keluar(){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		 builder.setMessage("Apakah Anda Ingin" + " keluar?")
		 .setCancelable(false)
		 .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
		 public void onClick(DialogInterface dialog, int id) {
		 finish();
		 }
		 })
		 .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
		 public void onClick(DialogInterface dialog, int id) {
		 dialog.cancel();
		 }
		 }).show();
		}

}
