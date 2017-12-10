# Android Geocities Theme

Bringing the best of the web to native!

<img src="https://github.com/dvoiss/android-geocities-theme/blob/master/art/sample.gif"/>

## Features

* 🚧 Cool "under construction" views
* 👍 `ImageViews` have their images replaced with GIFs that are better than whatever was there before.
* ❌ Replaces some images with "broken image" images
* 😎 Marquee and blink text
* 🔥 An awesome hit/visitor counter for your app
* 🙏 Uses Comic Sans as the Gods intended
* 🔊 Plays awesome audio midi files

## Sample video (contains audio):

[![Sample Video](https://github.com/dvoiss/android-geocities-theme/blob/master/art/sample.png)](https://youtu.be/gF9O9lXn3pc)

## Usage

In your application class:

```java
override fun onCreate() {
    super.onCreate()
    Geocities.init(this)
}
```

Add this to your activity:

```java
@Override
protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(Geocities.wrap(newBase));
}
```

Add the dependency in your app `build.gradle`:

```
compile 'com.github.dvoiss:android-geocities-theme:1.0.0'
```

Add the jitpack repo to your root `build.gradle`:

```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

## Roadmap / Feature request:

* More GIFs
* Support for "web ring" functionality?
* Guestbook functionality
* Create an issue or pull request to add a feature!

## FAQ

### How does it work?

The [ViewPump](https://github.com/InflationX/ViewPump) library is used to intercept a few UI components that are then modified to be "Geocitified".

* `ImageViews` have their images replaced with GIFs or "broken image" images.
* `Toolbars` are modified to have the GIF back button seen in the video above as well as a hit counter, rainbow colored title text, and an "under construction" GIF.
* `TextViews` blink and scroll (marquee effect) and use comic sans.
* The root layout (`ContentFrameLayout`) has a background set with one of the supplied backgrounds.

### Can I use this in a real app?

That's probably ill advised. This library was not meant for serious consumption (surprised?) and as such some of the resources below are pulled from public sources. If you really want to use this you may need to check the usage/licenses for them or replace them.

### There's a lot of GIFs will it crash my app? Is this library stable?

🤷

## Assets/Resources used:

* http://gifcities.org
* http://alllayedout.com/Backgrounds/Clouds
* http://moziru.com/explore/Night%20Sky%20clipart%20star%20gif/
* http://www.midiworld.com/files/175/
* https://freemidi.org/download-12160-africa-toto
* http://www.911fonts.com/font/family/comic_sans_ms
