package backup;

import id.reanny.halaal.R;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<HashMap<String, String>> data;
	private static LayoutInflater inflater = null;
	public ImageLoader imageLoader;

	public LazyAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
		activity = a;
		data = d;
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		imageLoader = new ImageLoader(activity.getApplicationContext());
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		View vi = convertView;
		if (convertView == null)
			vi = inflater.inflate(R.layout.spk_list_row, null);

		TextView id_berita = (TextView) vi.findViewById(R.id.kode);
		TextView judul = (TextView) vi.findViewById(R.id.judul); 
		TextView skor= (TextView) vi.findViewById(R.id.skor);
		ImageView thumb_image = (ImageView) vi.findViewById(R.id.gambar); 
		
		HashMap<String, String> daftar_berita = new HashMap<String, String>();
		daftar_berita = data.get(position);
	
		id_berita.setText(daftar_berita.get(HasilForm.TAG_ID));
		judul.setText(daftar_berita.get(HasilForm.TAG_JUDUL));
		skor.setText(daftar_berita.get(HasilForm.TAG_SKOR));
		imageLoader.DisplayImage(daftar_berita.get(HasilForm.TAG_GAMBAR),thumb_image);
		return vi;
	}
}