package com.lib.rxspreflib;

import android.content.Context;

import com.lib.rxspreflib.eceptions.SDKNotInitialized;
import com.lib.spref.SPref;
import com.lib.spref.SettingsConnector;

import java.util.List;
import java.util.Set;

import rx.Observable;

/**
 * @author lpereira on 11/01/2016.
 */
public class RxSPref {
    private SPref sPref;
    private SettingsConnector mSettingsConnector;

    private RxSPref(Context context) {
        sPref = SPref.init(context);
    }

    private SPref getPref() {
        return sPref;
    }

    /**
     * This should be called in application onCreate
     * @param context the application context
     * @return the RxSPref instance
     */
    public static RxSPref init(Context context) {
        return new RxSPref(context);
    }

    /**
     * Receives an xml file to be merged upon initialization
     * @param resource the resource xml file
     * @return instance to be initialized
     * @deprecated use {@link #provideDefaultResourceFile(int, boolean)} instead
     */
    @Deprecated
    @SuppressWarnings("unused")
    public RxSPref provideDefaultFile(int resource) {
        sPref = sPref.provideDefaultResourceFile(resource);
        return this;
    }

    /**
     * Provide a default resource file to merge all managed settings
     * @param resource the resource file
     * @param shouldOverride if every field found should override the preferences already written
     * @return instance of RxSPref
     */
    @SuppressWarnings("unused")
    public RxSPref provideDefaultResourceFile(int resource, boolean shouldOverride){
        sPref = sPref.provideDefaultResourceFile(resource, shouldOverride);
        return this;
    }

    /**
     * The initializer method of the RxSPref lib
     * @param preferencesName shared preference name
     * @return the instance of RxSPref
     */
    public RxSPref name(String preferencesName) {
        sPref = sPref.name(preferencesName);
        return this;
    }

    /**
     * Encrypt configurations providing a key this key should have at least 128bits
     * Remember that if you change this key the values that were written before are no longer accessible
     * @param key the key in byte[] with at least 128bits
     * @return the RxSPref instance
     */
    @SuppressWarnings("unused")
    public RxSPref encrypt(byte[] key){
        sPref = sPref.encrypt(key);
        return this;
    }

    /**
     * Encrypt configurations providing a key
     * Remember that if you change this key the values that were written before are no longer accessible
     * @param key the key hexadecimal to encrypt
     * @return the RxSPref instance
     */
    @SuppressWarnings("unused")
    public RxSPref encrypt(String key){
        sPref = sPref.encrypt(key);
        return this;
    }

    /**
     * Builds shared preference in order to access, save and remove  them
     * @return the RxSPref instance
     */
    public RxSPref buildSettings() {
        mSettingsConnector = sPref.buildSettings();
        return this;
    }

    /**
     * Checks for a correct initialization of the SDK and retrieves the shared preferences instance
     * @return shared preferences instance
     */
    private SettingsConnector getSettingsConnector() {
        if (sPref == null) {
            throw new SDKNotInitialized();
        }

        if (mSettingsConnector == null) {
            mSettingsConnector = sPref.buildSettings();
        }
        return mSettingsConnector;
    }

    /**
     * Retrieve a value from shared preferences
     *
     * @param key the key
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<String> retrieve(final String key) {
        return Observable.defer(() -> Observable.just(getSettingsConnector().getSetting(key)));
    }

    /**
     * Retrieve an encrypted value from shared preferences
     *
     * @param key the key
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<String> retrieveEncrypted(final String key) {
        return Observable.defer(() -> Observable.just(getSettingsConnector().getEncryptedSetting(key)));
    }

    /**
     * Retrieve a value from shared preferences
     * @param key the key
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Integer> retrieveAsInt(final String key) {
        return Observable.defer(() -> Observable.just(getSettingsConnector().getIntSetting(key)));
    }

    /**
     * Retrieve a value from shared preferences
     * @param key the key
     * @param defaultValue the default boolean value to save in case of error
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Boolean> retrieveAsBoolean(final String key, boolean defaultValue) {
        return Observable.defer(() -> Observable.just(getSettingsConnector().getBooleanSetting(key, defaultValue)));
    }

    /**
     * Retrieve a value from shared preferences
     * @param key the key
     * @param <T> generic value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public <T> Observable<List<T>> retrieveAsList(final String key) {
        return Observable.defer(() -> Observable.from(getSettingsConnector().getListSetting(key)));
    }

    /**
     * Retrieve a value from shared preferences
     * @param key the key
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Float> retrieveAsFloat(final String key) {
        return Observable.defer(() -> Observable.just(getSettingsConnector().getFloatSetting(key)));
    }

    /**
     * Retrieve a value from shared preferences
     * @param key the key
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Long> retrieveAsLong(final String key) {
        return Observable.defer(() -> Observable.just(getSettingsConnector().getLongSetting(key)));
    }

    /**
     * Writes a value to the shared preferences
     *
     * @param key   the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Boolean> write(final String key, final String value) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                if (getPref() != null) {
                    getSettingsConnector().saveSetting(key, value);
                    subscriber.onNext(true);
                } else {
                    subscriber.onError(new SDKNotInitialized());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value, which will be encrypted to the shared preferences
     *
     * @param key   the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Boolean> writeEncrypted(final String key, final String value) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                if (getPref() != null) {
                    getSettingsConnector().saveEncryptedSetting(key, value);
                    subscriber.onNext(true);
                } else {
                    subscriber.onError(new SDKNotInitialized());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     *
     * @param key   the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Boolean> write(final String key, final int value) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                if (getPref() != null) {
                    getSettingsConnector().saveSetting(key, value);
                    subscriber.onNext(true);
                } else {
                    subscriber.onError(new SDKNotInitialized());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     *
     * @param key   the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Boolean> write(final String key, final boolean value) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                if (getPref() != null) {
                    getSettingsConnector().saveSetting(key, value);
                    subscriber.onNext(true);
                } else {
                    subscriber.onError(new SDKNotInitialized());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     *
     * @param key   the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Boolean> write(final String key, final float value) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                if (getPref() != null) {
                    getSettingsConnector().saveSetting(key, value);
                    subscriber.onNext(true);
                } else {
                    subscriber.onError(new SDKNotInitialized());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key   the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Boolean> write(final String key, final long value) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                if (getPref() != null) {
                    getSettingsConnector().saveSetting(key, value);
                    subscriber.onNext(true);
                } else {
                    subscriber.onError(new SDKNotInitialized());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key   the key
     * @param value the value
     * @param <T> generic value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public <T> Observable<Boolean> write(final String key, final List<T> value) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                if (getPref() != null) {
                    getSettingsConnector().saveSetting(key, value);
                    subscriber.onNext(true);
                } else {
                    subscriber.onError(new SDKNotInitialized());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     *
     * @param key   the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Boolean> write(final String key, final Set<String> value) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                if (getPref() != null) {
                    getSettingsConnector().saveSetting(key, value);
                    subscriber.onNext(true);
                } else {
                    subscriber.onError(new SDKNotInitialized());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Remove a setting
     *
     * @param key the setting key
     * @return the observable
     */
    @SuppressWarnings("unused")
    public Observable<Boolean> remove(final String key) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                if (getPref() != null) {
                    getSettingsConnector().removeSetting(key);
                    subscriber.onNext(true);
                } else {
                    subscriber.onError(new SDKNotInitialized());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Remove all settings
     *
     * @param key the setting key
     * @return the observable
     */
    @SuppressWarnings("unused")
    public Observable<Boolean> removeAll(final String key) {
        return Observable.create(subscriber -> {
            if (!subscriber.isUnsubscribed()) {
                if (getPref() != null) {
                    getSettingsConnector().removeAllSetting();
                    subscriber.onNext(true);
                } else {
                    subscriber.onError(new SDKNotInitialized());
                }
                subscriber.onCompleted();
            }
        });
    }
}
