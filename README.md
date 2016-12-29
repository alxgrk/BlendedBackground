# BlendedBackground

[ ![Download](https://api.bintray.com/packages/alxgrk-ag/blended-background/com.github.alxgrk.blendedbackground/images/download.svg) ](https://bintray.com/alxgrk-ag/blended-background/com.github.alxgrk.blendedbackground/_latestVersion)

Smooth background for your app.

## Inspiration

The idea for this came while using Spotify.
In the new version, there is a smooth color transition between the color grey
and the dominating color of the album cover.

![inspiration](/preview/Screenshot_Spotify.png)

## Features

**BlendedBackground** extends RelativeLayout and can therefore contain every other View/ViewGroup.

Demo:

![demo](/preview/preview.gif)

- *Solo* \- reference a single ImageView
- *From Background* \- reference every other View that can have a background source
- *Complex* \- even if several Views are referenced, only the first one is taken into consideration
- *Fixed* \- you can also define fixed colors for the background or blend the Views dominant colors with the fixed ones
- *Changing* \- **BlendedBackground** reacts to layout changes: whether the source or the referenced View is changed, the colors will change automatically

## How to use

### 1. Gradle
To use this library in your own project, simply add the following line to your dependencies ta module `build.gradle`:

```
compile 'com.github.alxgrk.blendedbackground:blendedbackground:1.+'
```

### 2. Referencing a View
To reference a View, simply add `android:tag="@string/bb_ref_tag"` to your View.

**Example**
```xml
<ImageView
        android:layout_width="500dp"
        android:layout_height="500dp"
        android:tag="@string/bb_ref_tag"/>
```

### 3. Defining Attributes
To define custom attributes, you have to add `xmlns:app="http://schemas.android.com/apk/res-auto"` to the root view of your layout.

After doing that, you can use the following attributes to define behaviour:

#### upper_color
```
app:upper_color="<color>"
```

Description | Value
:--- | :---
sets the upper color to a fixed value | colorInt (e.g. "@android:color/white") 

---
#### lower_color
```
app:lower_color="<color>"
```

Description | Value
:--- | :---
sets the lower color to a fixed value | colorInt (e.g. "@android:color/black") 

---
#### upper_blend_in
```
app:upper_blend_in="<boolean>"
```

Description | Value
:--- | :---
set to true, if fixed upper color and dominating color should be blended together; default is false | true or false 

---
#### lower_blend_in
```
app:lower_blend_in="<boolean>"
```

Description | Value
:--- | :---
set to true, if fixed lower color and dominating color should be blended together; default is false | true or false 

---
#### invert
```
app:invert="<boolean>"
```

Description | Value
:--- | :---
set to true, if upper and lower color should change position; default is false | true or false

---
#### gradient_type
```
app:gradient_type="linear | radial"
```

Description | Value
:--- | :---
the type of the color gradient | linear or radial

## Support

This is a hobby project. If there are issues appearing, simply create an issue on GitHub and I will fix it ASAP.
Or even better, create a PullRequest, if you have some time left. ;)