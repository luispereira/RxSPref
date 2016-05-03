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

    protected SPref getPref() {
        return sPref;
    }

    /**
     * This should be called in application onCreate
     *
     * @param context the application context
     */
    public static RxSPref init(Context context) {
        return new RxSPref(context);
    }

    /**
     * Receives an xml file to be merged upon initialization
     *
     * @param resource the resource xml file
     * @return instance to be initialized
     */
    @SuppressWarnings("unused")
    public RxSPref provideDefaultFile(int resource) {
        sPref = sPref.provideDefaultResourceFile(resource);
        return this;
    }

    /**
     * The initializer method of the RxSPref lib
     *
     * @param preferencesName application context
     * @return the instance of SPref
     */
    public RxSPref name(String preferencesName) {
        sPref = sPref.name(preferencesName);
        return this;
    }

    /**
     * Builds shared preference in order to access, save and remove  them
     */
    public RxSPref buildSettings() {
        mSettingsConnector = sPref.buildSettings();
        return this;
    }

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
     * Retrieve a value from shared preferences
     *
     * @param key the key
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public Observable<Integer> retrieveAsInt(final String key) {
        return Observable.defer(() -> Observable.just(getSettingsConnector().getIntSetting(key)));
    }

    @SuppressWarnings("unused")
    public Observable<Boolean> retrieveAsBoolean(final String key, boolean defaultValue) {
        return Observable.defer(() -> Observable.just(getSettingsConnector().getBooleanSetting(key, defaultValue)));
    }

    @SuppressWarnings("unused")
    public <T> Observable<List<T>> retrieveAsList(final String key) {
        return Observable.defer(() -> Observable.from(getSettingsConnector().getListSetting(key)));
    }

    @SuppressWarnings("unused")
    public Observable<Float> retrieveAsFloat(final String key) {
        return Observable.defer(() -> Observable.just(getSettingsConnector().getFloatSetting(key)));
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
//
//    /**
//     * Writes a value to the shared preferences
//     *
//     * @param key   the key
//     * @param value the value
//     * @return the Observable
//     */
//    @SuppressWarnings("unused")
//    public Observable<Boolean> write(final String key, final float value) {
//        return Observable.create(subscriber -> {
//            if (!subscriber.isUnsubscribed()) {
//                if (getPref() != null) {
//                    getSettingsConnector().saveSetting(key, value);
//                    subscriber.onNext(true);
//                } else {
//                    subscriber.onError(new SDKNotInitialized());
//                }
//                subscriber.onCompleted();
//            }
//        });
//    }

    /**
     * Writes a value to the shared preferences
     *
     * @param key   the key
     * @param value the value
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
}
