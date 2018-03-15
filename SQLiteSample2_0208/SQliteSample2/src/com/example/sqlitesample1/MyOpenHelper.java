package com.example.sqlitesample1;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {
	// データベース名
	  private static final String DB_NAME = "LocationDB";
	// テーブル名
	public static final String TABLE_NAME = "Item";
	// カラム名
	public static final String COLUMN_IDP = "_id";
	public static final String COLUMN_ID = "ID";
	public static final String COLUMN_NAME = "Name";
	public static final String COLUMN_TEMP = "Temp";	
    static private int DB_VERSION = 1;
	
	  //所持物DB
	  private String[][] datas = new String[][]{
		  {"00","財布","00"},
		  {"01","携帯電話","00"},
		  {"02","筆箱","01"},
		  {"03","学生証","01"},
		  {"04","ルーズリーフ","01"},
		  {"05","USB","01"},
		  {"06","教科書","01"},
		  {"07","ファイル","01"},
		  {"08","印鑑","02"},
		  {"09","住基カード","02"},
		  {"10","免許証","00"},
		  {"11","診察券","03"},
		  {"12","お薬手帳","03"},
		  {"13","リュックサック","01"},
		  };
	  
	  /**
	   * コンストラクタ
	   */
	  public MyOpenHelper(Context context) {
	    // 指定したデータベース名が存在しない場合は、新たに作成されonCreate()が呼ばれる
	    // バージョンを変更するとonUpgrade()が呼ばれる
	    super(context, DB_NAME, null, 1);
	    }	
	    /**
	     * データベースの生成に呼び出されるので、 スキーマの生成を行う
	    */
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	    db.beginTransaction();		
	    try{		
	      // テーブルの生成
	      //ItemDB作成
	      StringBuilder createSql = new StringBuilder();
	      createSql.append("create table " + TABLE_NAME + " (");
	      createSql.append(COLUMN_IDP + " integer primary key,");
	      createSql.append(COLUMN_ID + " text,");
	      createSql.append(COLUMN_NAME + " text,");
	      createSql.append(COLUMN_TEMP + " text");
	      createSql.append(")");
	      db.execSQL( createSql.toString());
	      db.setTransactionSuccessful();
//	      db.setTransactionSuccessful();
	    }
	    finally {
	      db.endTransaction();
	    }	
	    
	    
	    // サンプルデータの投入
	    db.beginTransaction();
	    try{
		      for( String[] data: datas){
		        ContentValues values = new ContentValues();
		        values.put(COLUMN_ID, data[ 0]);
		        values.put(COLUMN_NAME, data[ 1]);
		        values.put(COLUMN_TEMP, data[ 2]);
		        db.insert(TABLE_NAME, null, values);
		      }
		      db.setTransactionSuccessful();
		    } finally {
		      db.endTransaction();
		    }		
	    
	    
	  }
	  //データベースの更新
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	  }
	}