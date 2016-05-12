# RxSPref #

Allows to retrieve and save Shared Preferences in a simpler way (using SPref lib) with RxJava / RxAndroid goodies

### How To Setup 

Dependency:

```groovy 
   compile 'com.github.luispereira:rxspreflib:0.2.0'
```

Repository:
```groovy 
    repositories {
        jcenter()   //or  mavenCentral()
    }
```  

### How To Use 
 
 In order to initialize the library, the following must be applied
 
```java 
 public class SampleApplication extends Application {
    private RxSPref sPref;
    private static SampleApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sPref = RxSPref.init(this).buildSettings();
        //name() to provide the name of the shared preference
        //provideDefaultFile() to provide a default settings file to merge without overriding
    }

    public RxSPref getPref() {
        return sPref;
    }
}
```
 
 Then the user can manage the SharedPreferences the way we want by using e.g.
 
```java 
     SampleApplication.getInstance().getPref().write(KEY, VALUE).subscribe();
```
 
 Or, to retrieve:
 
```java 
     SampleApplication.getInstance().getPref().retrieveAsInt(KEY).subscribe();
     //retrieve() from string observable
     //retrieveAsBoolean() for boolean observable 
     //retrieveAsList() for a generic list observable
     //retrieveAsFloat() for a float obserable
     //retrieveAsLong() for a long observable
```
 
 The user can always remove a shared preference:
 
```java 
     SampleApplication.getInstance().getPref().remove(KEY).subscribe();
     //removeAll() to remove all shared preferences 
```
 
### Encryption ###

As in SPref, RxSPref also allows encryption on String preferences. 

You can always use the API method to encrypt always all String keys:

```java
  encrypt(STRING_KEY);
  //encrypt(BYTE_KEY);
```

Or you can Encrypt the String whenever you like by calling:
```java
   retrieveEncrypted(KEY); 
```

And to save:
```java
   writeEncrypted(KEY, VALUE);
```

### SPref ###
RxSPref is base on SPref as you can check the repository here: 
https://github.com/luispereira/SPref