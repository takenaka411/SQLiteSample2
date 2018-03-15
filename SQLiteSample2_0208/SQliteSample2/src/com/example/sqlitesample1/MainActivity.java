package com.example.sqlitesample1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	@Override
	 protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final String TAG = "MainActivity";



//		public String savelocation;

		MyOpenHelper helper = new MyOpenHelper(this);
		final SQLiteDatabase db = helper.getWritableDatabase();
		//ボタン情報
		Button kiso = (Button) findViewById(R.id.kiso);
		kiso.setOnClickListener(this);
		Button rireki = (Button) findViewById(R.id.rireki);
		rireki.setOnClickListener(this);
		Button tuikadate = (Button) findViewById(R.id.tuikadate);
		tuikadate.setOnClickListener(this);
		Button done = (Button) findViewById(R.id.done);
		done.setOnClickListener(this);
		Button zyouken = (Button) findViewById(R.id.zyouken);
		zyouken.setOnClickListener(MainActivity.this);
		Button reset = (Button) findViewById(R.id.reset);
		reset.setOnClickListener(MainActivity.this);
	}

	@Override
	public void onClick(View v){
		//入力
		if (v.getId() == R.id.zyouken){
	    TextView textView = (TextView) findViewById(R.id.mokutekitiview);
	    EditText editText = (EditText) findViewById(R.id.mokutekitiIn);
	    TextView textView2 = (TextView) findViewById(R.id.syozibutuview);
	    EditText editText2 = (EditText) findViewById(R.id.syozibutuIn);
	    TextView textView3 = (TextView) findViewById(R.id.tenkiview);
	    EditText editText3 = (EditText) findViewById(R.id.tenkiIn);
        // 入力された文字を取得
        String str = editText.getText().toString();
        String str2 = editText2.getText().toString();
        String str3 = editText3.getText().toString();
        //TextView内の文字列を取得
        String txt = textView.getText().toString();
        String txt2 = textView2.getText().toString();
        String txt3 = textView3.getText().toString();
        // 取得した文字をTextViewに出力
        textView.setText(txt+","+str);
        textView2.setText(txt2+","+str2);
        textView3.setText(txt3+","+str3);
        editText.setText("");
        editText2.setText("");
        editText3.setText("");
		}
		if (v.getId() == R.id.reset){
            TextView textView = (TextView) findViewById(R.id.mokutekitiview);
            TextView textView2 = (TextView) findViewById(R.id.syozibutuview);
            TextView textView3 = (TextView) findViewById(R.id.tenkiview);
            textView.setText("");
            textView2.setText("");
            textView3.setText("");
		}

		//所持物DB呼出
		if (v.getId() == R.id.kiso){
		Intent intent = new Intent(MainActivity.this,com.example.sqlitesample1.sdb.class);
		startActivity(intent);
		}
		//過去推定結果呼出
		if (v.getId() == R.id.rireki){
            int q = GlobalData.tuikadata1.size();
            Toast.makeText(this,String.valueOf(q),Toast.LENGTH_SHORT).show();
            for(int i = 0; i < GlobalData.tuikadata1.size(); i++){
                String r = GlobalData.tuikadata1.get(i);
                Toast.makeText(this,r,Toast.LENGTH_SHORT).show();
            }
		}
		//追加DB呼出
		if (v.getId() == R.id.tuikadate){
			Toast.makeText(this,"未実装",Toast.LENGTH_SHORT).show();
		}




		//推定実行
		if (v.getId() == R.id.done){
//			Toast.makeText(this,"未実装",Toast.LENGTH_LONG).show();
//			Intent intent = new Intent(MainActivity.this,com.example.sqlitesample1.suitei.class);

			//目的地名を取得
			TextView mktkname = (TextView) findViewById(R.id.mokutekitiview);
			String mktk = mktkname.getText().toString();
			TextView tenkiname = (TextView) findViewById(R.id.tenkiview);
			String tenki = tenkiname.getText().toString();
			String mktkaf = "";
			String kekkastring = "";
			String[] nokori;
			int Tempkari1 = 0;
			int Tempkari2 = 0;
			int Tempkari3 = 0;
			int TempkariR = 0;
			int Tempkari99 = 0;
			final ArrayList<String> kekka = new ArrayList<String>();

			//目的地名を比較　上手い方法見つかったらここは差し替え
			if (mktk.matches(".*" + "大学" + ".*")) {
			    Tempkari1 = 1;
			}
			if (mktk.matches(".*" + "公共施設" + ".*")) {
			    Tempkari2 = 1;
			}
			if (mktk.matches(".*" + "医療機関" + ".*")) {
			    Tempkari3 = 1;
			}
			if (tenki.matches(".*" + "雨" + ".*")) {
			    TempkariR = 1;
			}
			if(Tempkari1 == 0 && Tempkari2 == 0 && Tempkari3 == 0){
				Tempkari99 = 1;
			}

			//所持物の文字列を取得
			TextView syoname = (TextView) findViewById(R.id.syozibutuview);
			String syo = syoname.getText().toString();


			String[] Temp0 = {"財布","携帯電話","免許証"};
			String[] Temp1 = {"筆箱","学生証","ルーズリーフ","リュックサック","USB","教科書","ファイル"};
			String[] Temp2 = {"印鑑","住基カード"};
			String[] Temp3 = {"診察券","保険証","お薬手帳"};
			String[] TempR = {"傘","カッパ"};


			//一括して必需品は一覧に表示されるようにする、追加削除で目的地と結びつくといい感じ
			for(int i = 0; i < Temp0.length; i++){
				if (syo.matches(".*" + Temp0[i] + ".*")) {
					String motimonokari = Temp0[i];
					syo = syo.replaceAll(motimonokari, "");
					}
				else {
					String motimonokari = Temp0[i];
					kekka.add(motimonokari);
					}
			}
			//所持物の確認　文字列から目的地へ持っていく所持物名を引いていく 無い場合は提案
			//例外の処理はオミット
			if (Tempkari1 == 1){
				for(int i = 0; i < Temp1.length; i++){
					if (syo.matches(".*" + Temp1[i] + ".*")) {
						String motimonokari = Temp1[i];
						syo = syo.replaceAll(motimonokari, "");
						}
					else {
						String motimonokari = Temp1[i];
						kekka.add(motimonokari);
						}
				}
			}

			if (Tempkari2 == 1){
				for(int i = 0; i < Temp2.length; i++){
					if (syo.matches(".*" + Temp2[i] + ".*")) {
						String motimonokari = Temp2[i];
						syo = syo.replaceAll(motimonokari, "");
						}
					else {
						String motimonokari = Temp2[i];
						kekka.add(motimonokari);
						}
				}
			}

			if (Tempkari3 == 1){
				for(int i = 0; i < Temp3.length; i++){
					if (syo.matches(".*" + Temp3[i] + ".*")) {
						String motimonokari = Temp3[i];
						syo = syo.replaceAll(motimonokari, "");
						}
					else {
						String motimonokari = Temp3[i];
						kekka.add(motimonokari);
						}
				}
			}

			if (TempkariR == 1){
				for(int i = 0; i < TempR.length; i++){
					if (syo.matches(".*" + TempR[i] + ".*")) {
						String motimonokari = TempR[i];
						syo = syo.replaceAll(motimonokari, "");
					}
					else {
						String motimonokari = TempR[i];
						kekka.add(motimonokari);
					}
				}
			}

			//目的地入力のTextViewから区切りを取り除く
			final String mktkafter = mktk.replaceAll(",","");
			//追加データ用，目的地Viewを配列に分割，空の要素を削除
			String[] mktknokori = mktk.split(",");
			List<String> mktknokori2=new ArrayList<String>(Arrays.asList(mktknokori));
			mktknokori2.remove("");
			//所持物の文字列を分割して配列に配置（追加データ用）
			//同様にして空の要素を削除
			nokori = syo.split(",");
			List<String> nokori2=new ArrayList<String>(Arrays.asList(nokori));
			nokori2.remove("");
			//分割したリストをarraylistに変換し，要素を全て追加データ用に置き換える
			ArrayList<String> nokoriarray = new ArrayList<String>();
			for(int j = 0;j < mktknokori2.size();j++){

				for(int k = 0;k < nokori2.size();k++){
					String mn = (String) mktknokori2.get(j);
					String n = (String) nokori2.get(k);
					nokoriarray.add("["+mn+"]["+n+"]");
				}
			}
			//追加データ用のarraylistに保存　グローバル変数
			GlobalData.tuikadata2be.addAll(nokoriarray);

			//バグ取
//			String[] a = nokoriarray.toArray(new String[nokoriarray.size()]);

			//追加データを提案表示用のarraylistに追加
			ArrayList<String> nokoripath = new ArrayList<String>();

			//tuikadata2追加作業
			for (int h = 0; h < mktknokori2.size();h++){
			for (int i = 0; i < GlobalData.tuikadata2af.size(); i++) {
				String mn2 = (String) mktknokori2.get(h);
		        if (GlobalData.tuikadata2af.get(i).contains(mn2)) {
		        	String n2 = (String)GlobalData.tuikadata2af.get(i);
		        	String n21 = n2.replace("[","").replace("]","").replace(mn2,"");
		        	nokoripath.add(n21);
		        }
				}
			}

			//tuikadata2beの中身をtuikadata2afに置き換える
			//具体的にはtuikadata2afの中身を削除してtuikadatabeの中身を全て入れている
			GlobalData.tuikadata2af.clear();
			GlobalData.tuikadata2af.addAll(GlobalData.tuikadata2be);

			//追加データを一覧表示用のarraylistに付与
			kekka.addAll(nokoripath);

			//追加データ1の処理の前に、追加データ1の処理指示を消す操作
			//[大学][筆箱]を一端削除する操作を実装すると永遠に推定に表示されなくなる為、以下で復活させようという試み
			//但し入力目的地が2箇所までしか対応できないのでそこは考えどころ
			String syo2 = syoname.getText().toString();
			String[] tk1you = syo2.split(",");
			List<String> tk1you2=new ArrayList<String>(Arrays.asList(tk1you));
			List<String> tk1hukkatu=new ArrayList<String>();
			List<String> tuikadata1tuika=new ArrayList<String>();
			tk1you2.remove("");
			for(int i =0;i<mktknokori2.size();i++){
				String tkmktk = mktknokori2.get(i);
				for(int j =0;j<tk1you2.size();j++){
					String tksyozi = tk1you2.get(j);
					for(int k =0;k<GlobalData.tuikadata1.size();k++){
						String TD1 = GlobalData.tuikadata1.get(k);
						if(TD1.matches(".*" + tkmktk + ".*")){
							if(TD1.matches(".*" + tksyozi + ".*")){
								String TD11 = TD1.replace("[","").replace("]","").replace(tkmktk,"").replace(tksyozi,"");
								if(TD11==""){
									tk1hukkatu.add(TD1);
								}
								else{
									tuikadata1tuika.add("["+ TD11 +"]["+ tksyozi +"]");
									tk1hukkatu.add(TD1);
								}
							}
						}

					}
				}
			}
			GlobalData.tuikadata1.addAll(tuikadata1tuika);
			GlobalData.tuikadata1.removeAll(tk1hukkatu);
			tk1hukkatu.clear();

			//追加データ1処理作り直し
			//nokorituika1に削除するデータを格納する
			//aとcという所持物を削除する場合，nokorituika1にはaとcのデータが格納される
			if(GlobalData.tuikadata1.size() != 0){
			ArrayList<String> nokorituika1 = new ArrayList<String>();
			for (int i = 0; i < GlobalData.tuikadata1.size() ; i++){
			for (int h = 0; h <kekka.size();h++){
					for(int j = 0; j < mktknokori2.size(); j++){
					String mn3 = (String)kekka.get(h);
					String n3 = (String)GlobalData.tuikadata1.get(i);
					String mktk3 = (String)mktknokori2.get(j);
					if(n3.matches(".*"+mn3+".*")&&n3.matches(".*"+mktk3+".*")){
						nokorituika1.add(mn3);
					}
					}
				}
			}
			//kekkaからnokorituika1のデータと同じものを削除
			kekka.removeAll(nokorituika1);
			}


			//AllayListを配列に変換
			GlobalData.kekkaString = kekka.toArray(new String[kekka.size()]);

			//推定結果表示
			//一覧のarraylistを作成した後，同じ長さのチェック判定用の配列flagを作成し，初期値を全てfalseにする
			GlobalData.flag = new boolean[GlobalData.kekkaString.length];
			Arrays.fill(GlobalData.flag,false);
			//チェックボックス作成
			new AlertDialog.Builder(this)
	        .setTitle("所持物提案:"+"目的地「"+mktkafter+"」\n"+"適切でない所持物提案を選択してください")
	        .setMultiChoiceItems(GlobalData.kekkaString, GlobalData.flag, new DialogInterface.OnMultiChoiceClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
	            	//チェックボックスが選択された時の処理
	            		GlobalData.flag[which] = isChecked;
	            }})
	        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	            @Override
	            public void onClick(DialogInterface dialog, int idx) {
	            	for(int i = 0; i < GlobalData.flag.length;i++){
	            		if(GlobalData.flag[i]==true){
	            			GlobalData.checkedItems.add("["+mktkafter+"]["+GlobalData.kekkaString[i]+"]");
	            		}
	            	}

	            	//上記の削除用追加データをグローバル変数に返す
	            	GlobalData.tuikadata1.addAll(GlobalData.checkedItems);

	            	//checkedItems初期化
	            	GlobalData.checkedItems.clear();

	            	//推定が完了する度にEditText内とTextView内を初期化
	        	    TextView textView = (TextView) findViewById(R.id.mokutekitiview);
	        	    EditText editText = (EditText) findViewById(R.id.mokutekitiIn);
	        	    TextView textView2 = (TextView) findViewById(R.id.syozibutuview);
	        	    EditText editText2 = (EditText) findViewById(R.id.syozibutuIn);
	        	    TextView textView3 = (TextView) findViewById(R.id.tenkiview);
	        	    EditText editText3 = (EditText) findViewById(R.id.tenkiIn);
	                textView.setText("");
	                textView2.setText("");
	                textView3.setText("");
	                editText.setText("");
	                editText2.setText("");
	                editText3.setText("");
	            	}
	        })
//	        .setNegativeButton("Cancel", null)
	        .show();
			//
			}
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
