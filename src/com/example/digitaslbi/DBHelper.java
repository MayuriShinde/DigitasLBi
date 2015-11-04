package com.example.digitaslbi;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

public class DBHelper extends SQLiteOpenHelper {
	public SQLiteDatabase db1;
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "digital.db";

	static String FILE_DIR = "Android";

	public DBHelper(Context context) {
		// super(context, DATABASE_NAME, null, DATABASE_VERSION);

		super(context, Environment.getExternalStorageDirectory()
				+ File.separator + FILE_DIR + File.separator + DATABASE_NAME,
				null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(TableContract.Animals.SQL_CREATE);
		} catch (Exception e) {
			String k = "Error" + e;
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		db.execSQL(TableContract.Animals.SQL_DELETE);
	}

	public long insertData(Animal_Feature_class model) {
		long v = 0;
		try {
			db1 = this.getWritableDatabase();
			ContentValues cv = new ContentValues();
			cv.put(TableContract.Animals.CSpecies, model.getSpecies());
			cv.put(TableContract.Animals.Cfamily, model.getFamily());
			cv.put(TableContract.Animals.CIUCN, model.getIUCN());
			cv.put(TableContract.Animals.Cyear, model.getYear());

			cv.put(TableContract.Animals.CSpecies, model.getSpecies());
			cv.put(TableContract.Animals.CTypesOfAnimal,
					model.getTypeofAnimal());
			cv.put(TableContract.Animals.Cpicture, model.getPhoto());
			String where = TableContract.Animals.CSpecies + " = " + "'"
					+ model.getSpecies() + "'";
			v = db1.update(TableContract.Animals.TABLE_NAME, cv, where, null);

			if (v <= 0) {
				db1.insert(TableContract.Animals.TABLE_NAME, null, cv);
			}
		} catch (Exception e) {
			String k = e + "";
		} finally {
			db1.close();
		}

		return v;
	}

	public int countdata() {

		int row = -1;
		db1 = this.getReadableDatabase();
		try {

			Cursor c = db1.rawQuery("SELECT * FROM "
					+ TableContract.Animals.TABLE_NAME, null);
			row=c.getCount();

		} catch (Exception e) {
			String value = e + "";
		} finally {
			db1.close();
		}
		return row;

	}

	public List<Animal_Feature_class> getCustomerDetail() {

		db1 = DBHelper.this.getReadableDatabase();
		List<Animal_Feature_class> list = new ArrayList<Animal_Feature_class>();

		try {
			String where = TableContract.Animals.CTypesOfAnimal + "=" + "'"
					+ "favourites" + "'";
			Cursor cursor = db1.query(TableContract.Animals.TABLE_NAME, null,
					where, null, null, null, null);

			if (cursor != null) {

				cursor.moveToFirst();
				do {
					String species, family, IUCN, year, notes;
					byte[] btmpic = null;

					species = cursor.getString(cursor
							.getColumnIndex(TableContract.Animals.CSpecies));
					family = cursor.getString(cursor
							.getColumnIndex(TableContract.Animals.Cfamily));
					IUCN = cursor.getString(cursor
							.getColumnIndex(TableContract.Animals.CIUCN));
					year = cursor.getString(cursor
							.getColumnIndex(TableContract.Animals.Cyear));
					// btmpic = convertStirngToByteArray(year);
					notes = cursor.getString(cursor
							.getColumnIndex(TableContract.Animals.Cnotes));
					byte[] bit = cursor.getBlob(cursor
							.getColumnIndex(TableContract.Animals.Cpicture));
					Bitmap bitmap = null;
					if (bit != null) {
						bitmap = BitmapFactory.decodeByteArray(bit, 0,
								bit.length);
					}
					list.add(new Animal_Feature_class(species, family, IUCN,
							year, notes, "", bitmap, null));

					cursor.moveToNext();
				} while (!cursor.isAfterLast());
			}

		} catch (Exception e) {
			String l = e + "";
		} finally {
			db1.close();
		}
		return list;
	}

	public static byte[] convertStirngToByteArray(String s) {
		byte[] byteArray = null;
		if (s != null) {
			if (s.length() > 0) {
				try {
					byteArray = s.getBytes();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return byteArray;
	}

	public List<Animal_Feature_class> getCustomerDetailother() {

		db1 = DBHelper.this.getReadableDatabase();
		List<Animal_Feature_class> list = new ArrayList<Animal_Feature_class>();

		try {
			String where = TableContract.Animals.CTypesOfAnimal + "=" + "'"
					+ "others" + "'";
			Cursor cursor = db1.query(TableContract.Animals.TABLE_NAME, null,
					where, null, null, null, null);

			if (cursor != null) {

				cursor.moveToFirst();
				do {
					String species, family, IUCN, year, notes;
					byte[] btmpic = null;

					species = cursor.getString(cursor
							.getColumnIndex(TableContract.Animals.CSpecies));
					family = cursor.getString(cursor
							.getColumnIndex(TableContract.Animals.Cfamily));
					IUCN = cursor.getString(cursor
							.getColumnIndex(TableContract.Animals.CIUCN));
					year = cursor.getString(cursor
							.getColumnIndex(TableContract.Animals.Cyear));
					// btmpic = convertStirngToByteArray(year);
					notes = cursor.getString(cursor
							.getColumnIndex(TableContract.Animals.Cnotes));
					byte[] bit = cursor.getBlob(cursor
							.getColumnIndex(TableContract.Animals.Cpicture));
					Bitmap bitmap = null;
					if (bit != null) {
						bitmap = BitmapFactory.decodeByteArray(bit, 0,
								bit.length);
					}
					list.add(new Animal_Feature_class(species, family, IUCN,
							year, notes, "", bitmap, null));

					cursor.moveToNext();
				} while (!cursor.isAfterLast());
			}

		} catch (Exception e) {
			String l = e + "";
		} finally {
			db1.close();
		}
		return list;
	}

}
