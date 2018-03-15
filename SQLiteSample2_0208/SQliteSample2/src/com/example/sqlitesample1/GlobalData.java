package com.example.sqlitesample1;

import java.util.ArrayList;

import android.app.Application;

public class GlobalData extends Application {
	//グローバル変数　主に追加情報データの保持に使う
	public static ArrayList<String> tuikadata1= new ArrayList<String>();
	public static ArrayList<String> tuikadata2be= new ArrayList<String>();
	public static ArrayList<String> tuikadata2af= new ArrayList<String>();
//	public static ArrayList<String> tuikadatatenki = new ArrayList<String>();

	//tuikadata1:目的地に対する所持物に対して所持物が足りない時　削除操作
	//tuikadata2:目的地に対する所持物に対して余分な所持物がある時　追加操作
	//tuikadatatenki:おてんき　追加操作
	//基本的には追加操作を行ってから削除操作を行うようにする，つまりtuikadata1の呼び出しと操作は一番最後に行うこと

	//天気用追加データ
//	for (String item : new String[]{"[雨][傘]","[雨][合羽]","[雨][タオル]",""}) 
//	{
//	    tuikadatatenki.add(item);
//	}
	
	public static boolean[] flag;
	public static String[] kekkaString;
	public static ArrayList<String> checkedItems = new ArrayList<String>();
}



