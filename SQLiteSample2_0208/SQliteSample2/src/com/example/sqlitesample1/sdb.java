package com.example.sqlitesample1;    
  
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;  
  
public class sdb extends Activity {  
	 protected SQLiteDatabase db;
	 protected Cursor cursor;
	 protected ListAdapter adapter;
	 protected ListView cigaretteList;

	
    @Override
	 public void onCreate(Bundle savedInstanceState) {

	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.showdatabase);
	  db = (new MyOpenHelper(this)).getWritableDatabase();
	  cigaretteList = (ListView)findViewById(R.id.lvkiso);
	  Cursor c = db.query("Item",null, null, null, null, null, null);
	  c.moveToFirst();
	  cigaretteList.setAdapter(new SimpleCursorAdapter(this, 
               R.layout.list_item,
	               c, 
	               new String[] {"ID", "Name", "Temp"}, 
             new int[] {R.id.ID, R.id.Name, R.id.Temp}));
    }  
}  