# Android Geocities Theme

Bringing the best of the web to native!

Style your app like the web in 1997 (or Android circa 2009? 🤔).

Note that this isn't a theme per se, see below, it intercepts an activity's layout inflation to customize certain UI components.

## Features

* 👍 Replaces the images in ImageViews with awesome GIFs
* 🚧 Cool "under construction" views
* ❌ Replaces some images with "broken image" images
* 😎 <marquee>Marquee and blink text</marquee>
* 🔥 An awesome hit/visitor counter for your app
* 🙏 Uses Comic Sans as the Gods intended
* 🔊 Plays awesome audio midi files

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
* Create an issue to add a request!

## FAQ

### How does it work?

The [ViewPump](https://github.com/InflationX/ViewPump) library is used to intercept a few UI components that are then modified to be "Geocitified".

### Can I use this in a real app?

That's ill advised. This library was not meant for serious consumption (surprised?) and as such some of the resources below are pulled from public sources. If you really want to use this you may need to check the usage/licenses for them or replace them.

### There's a lot of GIFs will it crash my app? Is this library stable?

🤷

## Assets/Resources used:

* http://gifcities.org
* http://alllayedout.com/Backgrounds/Clouds
* http://moziru.com/explore/Night%20Sky%20clipart%20star%20gif/
* http://www.midiworld.com/files/175/
* https://freemidi.org/download-12160-africa-toto
* http://www.911fonts.com/font/family/comic_sans_ms
