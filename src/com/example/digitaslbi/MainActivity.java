package com.example.digitaslbi;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btnClickMe;
	DBHelper db;
	Animal_Feature_class model;
	Context con;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnClickMe = (Button) findViewById(R.id.btnClick);
		db = new DBHelper(getApplicationContext());
		model = new Animal_Feature_class();
		con = MainActivity.this;
		btnClickMe.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int row = db.countdata();
				if (row == 0) {
					new DownloadData().execute(""); // download data from url
				} else {
					Intent intentToFragment = new Intent(con,
							activityAttachFragment.class);
					startActivity(intentToFragment);
				}

			}
		});
	}

	class DownloadData extends AsyncTask<String, String, String> {
		ProgressDialog dialog = new ProgressDialog(con);

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dialog.setMessage("Documents sync in progress");
			dialog.setIndeterminate(false);
			dialog.setCancelable(false);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {

			String resp = null;

			HttpClient httpClient = new DefaultHttpClient();

			HttpResponse response;

			HttpPost httpPost = new HttpPost(
					"http://files.ilicco.com/digitaslbi/recruitment/test.json");
			try {

				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
						.permitAll().build();
				StrictMode.setThreadPolicy(policy);

				response = httpClient.execute(httpPost);
				InputStream obj = response.getEntity().getContent();
				String res = convertInputStreamToString(obj);

				JSONObject o = new JSONObject(res);
				JSONObject objAnimal = o.getJSONObject("Animals");

				final Iterator<String> keys = objAnimal.keys();
				while (keys.hasNext()) {
					final String key = keys.next();
					model.setTypeofAnimal(key);
					JSONArray subobj = objAnimal.getJSONArray(key);

					for (int i = 0; i < subobj.length(); i++) {

						JSONObject json_application = subobj.getJSONObject(i);

						model.setSpecies(json_application.getString("species"));

						model.setFamily(json_application.getString("family"));

						model.setIUCN(json_application.getString("IUCN"));

						model.setYear(json_application.getString("year"));

						JSONObject note = json_application
								.getJSONObject("notes");

						model.setNotes(note.getString("__cdata"));

						String pic = json_application.getString("picture");
						Bitmap btm = getBitmapFromURL(pic);
						if (btm != null) {

							model.setPicture(btm);
							ByteArrayOutputStream baos = new ByteArrayOutputStream();
							btm.compress(Bitmap.CompressFormat.PNG, 0, baos);
							byte[] b = baos.toByteArray();

							model.setPhoto(b);

						} else {
							model.setPhoto(null);
						}
						db.insertData(model);
					}
				}

			} catch (Exception e) {
				String k = "Data issue : " + e;
			}

			return resp;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			Intent inMoveToFragment = new Intent(con,
					activityAttachFragment.class);
			startActivity(inMoveToFragment);
			dialog.dismiss();

		}
	}

	public Bitmap getBitmapFromURL(String src) {

		URL url1;
		Bitmap bmp = null;

		try {

			url1 = new URL(src);
			try {
				URLConnection urlConnection = url1.openConnection();
				TrustManagerManipulator.allowAllSSL();
				InputStream in = urlConnection.getInputStream();

				BitmapFactory.Options options = new BitmapFactory.Options();
				options.inSampleSize = 8;
				bmp = BitmapFactory.decodeStream(in, null, options);

				// different code
				// bmp = BitmapFactory.decodeStream(bufferedInputStream);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return bmp;

	}

	private static String convertInputStreamToString(InputStream inputStream)
			throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		String line = "";
		String result = "";
		while ((line = bufferedReader.readLine()) != null)
			result += line;

		inputStream.close();

		return result;

	}
}