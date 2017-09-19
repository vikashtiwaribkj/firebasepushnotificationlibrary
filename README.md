# firebasepushnotificationlibrary

Step 1> Do firebase push notification setup
      Register your app in firebase console using your sha1 key
  
Step 2>  Include firbase dependencies into your app as below
        compile 'com.google.firebase:firebase-messaging:11.2.2'
        compile 'com.google.firebase:firebase-core:11.2.2'
        
  A>  Add apply plugin: 'com.google.gms.google-services' at the bottom line of module level build.gradle file
  
  B>  Add below code in project level build.gradle file
        
        dependencies {
          classpath 'com.android.tools.build:gradle:2.3.3'
          classpath 'com.google.gms:google-services:3.1.0'
        }
        
        allprojects {
          repositories {
            jcenter()
            maven {
              url "https://maven.google.com"
            }
          }
        }
        
  C>  Add below code in AndroidManifest.xml
  
           <!-- Your class which extends FirebaseMessagingService-->
          <service
              android:name=".firebaseutilities.FirebaseMessagingServiceHelper"
              android:enabled="true"
              android:exported="false">
              <intent-filter>
                  <action android:name="com.google.firebase.MESSAGING_EVENT" />
              </intent-filter>
          </service>

          <!-- Your class which extends FirebaseInstnaceIdService-->
          <service
              android:name=".firebaseutilities.FirebaseInstnaceIdServiceHelper"
              android:enabled="true"
              android:exported="false">
              <intent-filter>
                  <action android:name="com.google.firebase.INSTANCE_ID_EVENT" />
              </intent-filter>
          </service>  
          
         <meta-data
            android:name="com.google.firebase.messaging.default_notification_icon"
            android:resource="@drawable/notification_small_icon" />
        
        <meta-data
            android:name="com.google.firebase.messaging.default_notification_color"
            android:resource="@color/colorAccent" />
    

Step 3> Subscribe to server by making a call to register device api on our server

          String android_id = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
          RegisterDevicePresenter presenter = (RegisterDevicePresenter)         PresenterFactory.getInstance(UtilityConstant.REGISTER_DEVICE_PRSENTER);
          
        if (RetrofitConnectionHelper.isInternetConnected(this)) {
            Observable<Response<RegisterDeviceResModel>> responseObservable = presenter.registerDeviceToFirebase(lifeCycleListener, android_id, currentRefreshToken);
        }
        
Step 4>  Configure notifcation when it is received in FirebaseMessagingService using below code

           FirebaseNotification firebaseNotification = (FirebaseNotification) Class.forName(FirebaseNotification.class.getName()).newInstance();
           
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                firebaseNotification.setSmallIcon(R.mipmap.ic_launcher);
            } else {
                // Lollipop specific setColor method goes here.
                firebaseNotification.setSmallIcon(R.drawable.notification_small_icon);
            }

            firebaseNotification.setDefaultSoundUri(defaultSoundUri);
            firebaseNotification.setPattern(pattern);
            firebaseNotification.setAutoCancel(true);
            firebaseNotification.setColor(ContextCompat.getColor( getApplicationContext(), R.color.colorPureWhite));

            if (remoteMessage.getData() != null) {
                notificationCompatBuilder = firebaseNotification.setUpNotification(getApplicationContext(), remoteMessage.getData());
            }

            firebaseNotification.sendNotification(getApplicationContext(), notificationCompatBuilder, MainActivity.class);
            
Step 5>  You are done.
