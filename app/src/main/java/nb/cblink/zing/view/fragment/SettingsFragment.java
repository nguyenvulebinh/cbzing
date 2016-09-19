package nb.cblink.zing.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import nb.cblink.zing.R;
import nb.cblink.zing.viewmodel.DetectClipboardService;
import nb.cblink.zing.viewmodel.SettingModelView;

/**
 * Created by nguyenbinh on 13/09/2016.
 */

public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{
    public static final String KEY_PREF_CLIPBOARD_SWITCH = "switch_preference_clipboard_service";
    SettingModelView settingModelView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
        settingModelView = new SettingModelView(getActivity());
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(KEY_PREF_CLIPBOARD_SWITCH)){
            if(sharedPreferences.getBoolean(KEY_PREF_CLIPBOARD_SWITCH, false)){
                settingModelView.startServiceDetectClipboard();
            }else{
                settingModelView.stopServiceDetectClipBoard();
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
        super.onPause();
    }
}
