package id.ukdw.srmmobile.data.model.preference;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

public abstract class SharedPreferenceLiveData<T> extends LiveData<T> {

    SharedPreferences sharedPrefs;
    String key;
    public T email;

    public SharedPreferenceLiveData(SharedPreferences prefs, String key, T email) {
        this.sharedPrefs = prefs;
        this.key = key;
        this.email = email;
    }

    private SharedPreferences.OnSharedPreferenceChangeListener preferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            if (SharedPreferenceLiveData.this.key.equals(key)) {
                setValue(getValueFromPreferences(key, email));
            }
        }
    };
    abstract T getValueFromPreferences(String key, T defValue);

    @Override
    protected void onActive() {
        super.onActive();
        setValue(getValueFromPreferences(key, email));
        sharedPrefs.registerOnSharedPreferenceChangeListener(preferenceChangeListener);
    }

    @Override
    protected void onInactive() {
        sharedPrefs.unregisterOnSharedPreferenceChangeListener(preferenceChangeListener);
        super.onInactive();
    }
    public SharedPreferenceLiveData<Boolean> getBooleanLiveData(String key, Boolean defaultValue) {
        return new SharedPreferenceBooleanLiveData(sharedPrefs,key, defaultValue);
    }
}