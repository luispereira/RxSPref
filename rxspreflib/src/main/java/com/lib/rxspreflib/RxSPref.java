package com.lib.rxspreflib;

import android.content.Context;

import com.lib.spref.SPref;
import com.lib.spref.exceptions.SDKNotInitializedException;

import java.util.Set;

import rx.Subscriber;

/**
 * @author lpereira on 11/01/2016.
 */
public class RxSPref {
    private static SPref sInstance;

    /**
     * Empty Constructor
     * @param context the context
     */
    private RxSPref(Context context) {
        SPref.buildSettings(context);
    }

    private RxSPref() {
        SPref.buildSettings();
    }

    private RxSPref(Context context, int resource) {
        SPref.init(context).provideDefaultResourceFile(resource);
    }

    /**
     * Instance of the Spref lib
     * @return the instance of Spref lib
     */
    public static synchronized SPref getInstance()  {
        if (sInstance == null) {
            throw new SDKNotInitializedException();
        }
        return sInstance;
    }


    /**
     * Opens the shared preferences
     * @param context the application context
     * @return the instance of the RxSPref
     */
    public static RxSPref buildSettings(Context context){
        return new RxSPref(context);
    }

    /**
     * Opens the shared preferences, before calling this you must cal {@link #init(Context, int)}
     * @return the preferences
     */
    public static RxSPref buildSettings(){
        return new RxSPref();
    }

    /**
     * After calling this the developer should call {@link #buildSettings()} instead
     * @param context the application context
     * @param resource the resource xml file
     */
    public static RxSPref init(Context context, int resource){
        return new RxSPref(context, resource);
    }


    /**
     * Retrieve a value from shared preferences
     * @param key the key
     * @return the Observable
     */
    public rx.Observable<String> retrieve(final String key){
        return rx.Observable.create(new rx.Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if(!subscriber.isUnsubscribed()) {
                    if(SPref.getInstance() != null) {
                        subscriber.onNext(SPref.buildSettings().getSetting(key));
                    }else{
                        subscriber.onError(new SDKNotInitializedException());
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

    /**
     * Retrieve a value from shared preferences
     * @param key the key
     * @return the Observable
     */
    public rx.Observable<Integer> retrieveAsInt(final String key){
        return rx.Observable.create(new rx.Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                if(!subscriber.isUnsubscribed()) {
                    if(SPref.getInstance() != null) {
                        subscriber.onNext(SPref.buildSettings().getIntSetting(key));
                    }else{
                        subscriber.onError(new SDKNotInitializedException());
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key the key
     * @param value the value
     * @return the Observable
     */
    public rx.Observable<Boolean> write(final String key, final String value){
        return rx.Observable.create(new rx.Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if(!subscriber.isUnsubscribed()) {
                    if(SPref.getInstance() != null) {
                        SPref.buildSettings().saveSetting(key, value);
                        subscriber.onNext(true);
                    }else{
                        subscriber.onError(new SDKNotInitializedException());
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key the key
     * @param value the value
     * @return the Observable
     */
    public rx.Observable<Boolean> write(final String key, final int value){
        return rx.Observable.create(new rx.Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if(!subscriber.isUnsubscribed()) {
                    if(SPref.getInstance() != null) {
                        SPref.buildSettings().saveSetting(key, value);
                        subscriber.onNext(true);
                    }else{
                        subscriber.onError(new SDKNotInitializedException());
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key the key
     * @param value the value
     * @return the Observable
     */
    public rx.Observable<Boolean> write(final String key, final boolean value){
        return rx.Observable.create(new rx.Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if(!subscriber.isUnsubscribed()) {
                    if(SPref.getInstance() != null) {
                        SPref.buildSettings().saveSetting(key, value);
                        subscriber.onNext(true);
                    }else{
                        subscriber.onError(new SDKNotInitializedException());
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key the key
     * @param value the value
     * @return the Observable
     */
    public rx.Observable<Boolean> write(final String key, final long value){
        return rx.Observable.create(new rx.Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if(!subscriber.isUnsubscribed()) {
                    if(SPref.getInstance() != null) {
                        SPref.buildSettings().saveSetting(key, value);
                        subscriber.onNext(true);
                    }else{
                        subscriber.onError(new SDKNotInitializedException());
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

    /**
     * Writes a value to the shared preferences
     * @param key the key
     * @param value the value
     * @return the Observable
     */
    public rx.Observable<Boolean> write(final String key, final Set<String> value){
        return rx.Observable.create(new rx.Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if(!subscriber.isUnsubscribed()) {
                    if(SPref.getInstance() != null) {
                        SPref.buildSettings().saveSetting(key, value);
                        subscriber.onNext(true);
                    }else{
                        subscriber.onError(new SDKNotInitializedException());
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

    /**
     * Remove a setting
     * @param key the setting key
     * @return the observable
     */
    public rx.Observable<Boolean> remove(final String key){
        return rx.Observable.create(new rx.Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if(!subscriber.isUnsubscribed()) {
                    if(SPref.getInstance() != null) {
                        SPref.buildSettings().removeSetting(key);
                        subscriber.onNext(true);
                    }else{
                        subscriber.onError(new SDKNotInitializedException());
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }
}
