package com.apps.mobile.android.commons.questionnaire;


import com.apps.mobile.android.commons.Activity_Base_Ads_Banner;
import com.apps.mobile.android.commons.app.Application_Base;
import com.apps.mobile.android.commons.questionnaire.model.BestResults;
import com.apps.mobile.android.commons.questionnaire.model.GameData;
import com.apps.mobile.android.commons.questionnaire.model.UserSettings;
import com.apps.mobile.android.commons.storage.StorageUtils;


public abstract class Activity_Base_Questionnaire extends Activity_Base_Ads_Banner {
	
	
	private static final String FILE_NAME_GAME_DATA		 	= "game_data";
	private static final String FILE_NAME_BEST_RESULTS		= "best_results";
	
	
	@Override
	public void onPause() {
		
		storeData();
		
		super.onPause();
	}
	
	
	@Override
	public void onResume() {
		
		super.onResume();
		
		getGameData();
		getUserSettings();
		getBestResults();
	}
	
	
	protected void storeData() {
		
		StorageUtils.writeStore(this, UserSettings.FILE_NAME_USER_SETTINGS);
		StorageUtils.writeStore(this, FILE_NAME_GAME_DATA);
		
		getBestResults();
		StorageUtils.writeStore(this, FILE_NAME_BEST_RESULTS);
	}
	
	
	public GameData getGameData() {
		GameData data = (GameData) StorageUtils.readStorage(this, FILE_NAME_GAME_DATA);
		if (data == null) {
			data = new GameData();
			StorageUtils.writeStore(this, FILE_NAME_GAME_DATA, data);
			data = (GameData) StorageUtils.readStorage(this, FILE_NAME_GAME_DATA);
		}
		return data;
	}
	
	
	public UserSettings getUserSettings() {
		return (UserSettings) ((Application_Base)getApplication()).getUserSettings();
	}
	
	
	public BestResults getBestResults() {
		BestResults results = (BestResults) StorageUtils.readStorage(this, FILE_NAME_BEST_RESULTS);
		if (results == null) {
			results = new BestResults();
			StorageUtils.writeStore(this, FILE_NAME_BEST_RESULTS, results);
			results = (BestResults) StorageUtils.readStorage(this, FILE_NAME_BEST_RESULTS);
		}
		return results;
	}
}
