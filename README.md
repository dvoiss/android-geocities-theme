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
* 🔊 Plays an awesome 90s era audio midi file

## Usage

In your application class:

```java
override fun onCreate() {
    super.onCreate()
    Geocities.init(this)
}
```

Choose the activity you'd like to use to drive your users away and add this to it:

```java
@Override
protected void attachBaseContext(Context newBase) {
    super.attachBaseContext(Geocities.wrap(newBase));
}
```

Add the jitpack repo to your root `build.gradle`:

```
allprojects {
  repositories {
    maven { url 'https://jitpack.io' }
  }
}
```

Add the dependency in your app `build.gradle`:

```
compile 'com.github.dvoiss:android-geocities-theme:1.0.0'
```

## Roadmap / Feature request:

* More GIFs
* Support for "web ring" functionality

## FAQ

### How does it work?

The [ViewPump](https://github.com/InflationX/ViewPump) library is used to intercept a few UI components that are then modified to be "Geocitified".

### Can I use this in a real app?

It may be a surprise but this library was not meant for serious consumption, as such some of the resources below are pulled from public sources and if you really do want to use this you may need to check the usage/licenses for them or replace them.

### There's a lot of GIFs will it crash my app? Is this library stable?

🤷

## Assets/Resources used:

* http://gifcities.org
* http://alllayedout.com/Backgrounds/Clouds
* http://moziru.com/explore/Night%20Sky%20clipart%20star%20gif/
* http://www.midiworld.com/files/175/
* http://www.911fonts.com/font/family/comic_sans_ms
