package com.example.digitaslbi;

public class TableContract {
	public TableContract() {
	}

	public interface Animals {
		public static final String TABLE_NAME = "Animal";
		public static final String CAUTO_ID = "C_Info";
		public static final String CTypesOfAnimal = "C_TypesOfAnimal";
		public static final String CSpecies = "C_Species";
		public static final String Cfamily = "C_family";
		public static final String CIUCN = "C_IUCN";
		public static final String Cyear = "C_year";
		public static final String Cpicture = "C_picture";
		public static final String Cnotes = "C_notes";

		public String SQL_CREATE = "CREATE TABLE " + TABLE_NAME + "( "
				+ CAUTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  CTypesOfAnimal + " TEXT," + CSpecies + " TEXT,"
				+ Cfamily + " TEXT," + CIUCN + " TEXT," + Cyear + " INTEGER,"
				+ Cpicture + " BLOB," + Cnotes + " TEXT"+ ")";

		public String SQL_DELETE = "DROP TABLE IF EXISTS " + TABLE_NAME;
	}

}
