# PSI Android Application

### Introduction
This is android application for showing Singapore Pollutant Standards Index
(https://data.gov.sg/dataset/psi)

feature list on this application are:

 * Showing PSI Based on region (east,west,north,central,south) on google Maps
 * Chart of 24 hours PSI
 * Chart of Pollutant sub indices
 
 see javadoc (https://egiadtya.github.io/psi-android/javadoc/)

### Installation

First, you need android studio to open this project. check this link if you doesn't have android studio https://developer.android.com/studio
1. Clone this project to your local project

    ```
    git clone https://github.com/egiadtya/psi-android
    ```
2. Open android studio, and then open psi-android directory.
3. Build & Run, and application should be install to emulator or connected device.


### Dependency

 * [ButterKnife 8.4.0](http://jakewharton.github.io/butterknife/) for Inject view
 * [Retrofit 2.1.0](http://square.github.io/retrofit/) used as HTTP Client for consume API call
 * [MPAndroidChart 3.0.2](https://github.com/PhilJay/MPAndroidChart) for showing chart
 * [Play Service Maps](https://developers.google.com/android/guides/setup) for showing maps


### Screenshot
![PSI on Maps](https://github.com/egiadtya/psi-android/blob/master/screenshot/device-2017-07-29-193601.png)
![24Hour PSI Chart](https://github.com/egiadtya/psi-android/blob/master/screenshot/device-2017-07-29-193635.png)
![Pollutant sub indices Chart](https://github.com/egiadtya/psi-android/blob/master/screenshot/device-2017-07-29-193658.png)