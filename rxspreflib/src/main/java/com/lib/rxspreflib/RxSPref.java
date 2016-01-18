package com.lib.rxspreflib;

import android.content.Context;

import com.lib.spref.SPref;
import com.lib.spref.exceptions.SDKNotInitializedException;

import java.util.Set;

import rx.Observable;

/**
 * @author lpereira on 11/01/2016.
 */
public class RxSPref {
    private static RxSPref sInstance;
    private int mResource = -1;


    private RxSPref() {
    }

    private RxSPref(int resource) {
        mResource = resource;
    }

    /**
     * Instance of the Spref lib
     * @return the instance of Spref lib
     */
    private static synchronized RxSPref getInstance()  {
        if (sInstance == null) {
            throw new SDKNotInitializedException();
        }
        return sInstance;
    }

    /**
     * This should be called in application onCreate
     * @param context the application context
     */
    public static void init(Context context){
        if(sInstance != null && sInstance.getResource() != -1) {
            SPref.init(context).provideDefaultResourceFile(sInstance.getResource());
        }else{
            SPref.init(context);
            sInstance = new RxSPref();
        }
    }

    /**
     * Receives an xml file to be merged upon initialization
     * @param resource the resource xml file
     * @return instance to be initialized
     */
    @SuppressWarnings("unused")
    public static RxSPref provideDefaulFile(int resource){
        sInstance =  new RxSPref(resource);
        return sInstance;
    }


    /**
     * Retrieve a value from shared preferences
     * @param key the key
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public static Observable<String> retrieve(final String key){
        return Observable.create( subscriber -> {
            if(!subscriber.isUnsubscribed()) {
                if(getInstance() != null) {
                    subscriber.onNext(SPref.buildSettings().getSetting(key));
                }else{
                    subscriber.onError(new SDKNotInitializedException());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Retrieve a value from shared preferences
     * @param key the key
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public static Observable<Integer> retrieveAsInt(final String key){
        return Observable.create( subscriber -> {
            if(!subscriber.isUnsubscribed()) {
                if(getInstance() != null) {
                    subscriber.onNext(SPref.buildSettings().getIntSetting(key));
                }else{
                    subscriber.onError(new SDKNotInitializedException());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public static Observable<Boolean> write(final String key, final String value){
        return Observable.create(subscriber -> {
            if(!subscriber.isUnsubscribed()) {
                if(getInstance() != null) {
                    SPref.buildSettings().saveSetting(key, value);
                    subscriber.onNext(true);
                }else{
                    subscriber.onError(new SDKNotInitializedException());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public static Observable<Boolean> write(final String key, final int value){
        return Observable.create(subscriber -> {
            if(!subscriber.isUnsubscribed()) {
                if(getInstance() != null) {
                    SPref.buildSettings().saveSetting(key, value);
                    subscriber.onNext(true);
                }else{
                    subscriber.onError(new SDKNotInitializedException());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public static Observable<Boolean> write(final String key, final boolean value){
        return Observable.create(subscriber -> {
            if(!subscriber.isUnsubscribed()) {
                if(getInstance() != null) {
                    SPref.buildSettings().saveSetting(key, value);
                    subscriber.onNext(true);
                }else{
                    subscriber.onError(new SDKNotInitializedException());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public static Observable<Boolean> write(final String key, final long value){
        return Observable.create(subscriber -> {
            if(!subscriber.isUnsubscribed()) {
                if(getInstance() != null) {
                    SPref.buildSettings().saveSetting(key, value);
                    subscriber.onNext(true);
                }else{
                    subscriber.onError(new SDKNotInitializedException());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key the key
     * @param value the value
     * @return the Observable
     */
    @SuppressWarnings("unused")
    public static Observable<Boolean> write(final String key, final Set<String> value){
        return Observable.create(subscriber -> {
            if(!subscriber.isUnsubscribed()) {
                if(getInstance() != null) {
                    SPref.buildSettings().saveSetting(key, value);
                    subscriber.onNext(true);
                }else{
                    subscriber.onError(new SDKNotInitializedException());
                }
                subscriber.onCompleted();
            }
        });
    }

    /**
     * Remove a setting
     * @param key the setting key
     * @return the observable
     */
    @SuppressWarnings("unused")
    public static Observable<Boolean> remove(final String key){
        return Observable.create(subscriber -> {
            if(!subscriber.isUnsubscribed()) {
                if(getInstance() != null) {
                    SPref.buildSettings().removeSetting(key);
                    subscriber.onNext(true);
                }else{
                    subscriber.onError(new SDKNotInitializedException());
                }
                subscriber.onCompleted();
            }
        });
    }

    private int getResource() {
        return mResource;
    }
}
