# BlendedBackground

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

### Gradle
// TODO gradle integration

### Referencing a View
To reference a View, simply add `android:tag="@string/ref_tag"` to your View. // TODO replace with custom attribute

### Defining Attributes
You can use the following attributes to define behaviour:

`android:upper_color="\<color\>"` - sets the upper color to a fixed value

`android:lower_color="\<color\>"` - sets the lower color to a fixed value

`android:upper_blend_in="\<boolean\>"` - set to true, if fixed upper color and dominating color should be blended together; default is false

`android:lower_blend_in="\<boolean\>"` - set to true, if fixed lower color and dominating color should be blended together; default is false

`android:invert="\<boolean\>"` - set to true, if upper and lower color should change position; default is false

## Support

This is a hobby project. If there are issues appearing, simply create an issue on GitHub and I will fix it ASAP.
Or even better, create a PullRequest, if you have some time left. ;)