
# SearchingAnimView

[中文版README](README_CN.md)

SearchingAnimView is a more beautiful search animation,you can not only use it to express the search properties, but also can used it with matching users, matching songs or other scenes.

###Demo
----
You can set the size, color, and the text or icon in the middle of the animation to suit your needs.

![SearchingAnimView](https://github.com/wangfeihang/SearchingAnimView/raw/master/image/demo_color.gif)
![SearchingAnimView](https://github.com/wangfeihang/SearchingAnimView/raw/master/image/demo_text.gif)
![SearchingAnimView](https://github.com/wangfeihang/SearchingAnimView/raw/master/image/demo_icon.gif)


[Download Demo](https://github.com/wangfeihang/SearchingAnimView/blob/master/demo/demo-release.apk)

###Usage
----

#### Gradle

```groovy
dependencies {
   compile 'com.sakuramomoko.searchinganimview:library:1.4'
}
```

#### Maven 

```xml
<dependency>
  <groupId>com.sakuramomoko.searchinganimview</groupId>
  <artifactId>library</artifactId>
  <version>1.4</version>
  <type>pom</type>
</dependency>
```

use it in your code:

```java
    <com.sakuramomoko.searchinganimview.SearchingAnimView
        android:layout_width="306dp"
        android:layout_height="306dp"
        app:search_color="#ff9e21"
        android:id="@+id/searching_anim_view"/>
```	

```java
    SearchingAnimView searchingAnimView=(SearchingAnimView)findViewById(R.id.searching_anim_view);
    //start animation
    searchingAnimView.startAnimations();
    //stop animation
    searchingAnimView.stopAnimations();
```
If you want to modify the size of the View, you can directly set layout_width and layout_height in the layout file.It can also be set via setViewSize (int parentWith, int parentHeight) or setViewSize (AttributeSet attrs).

If you want to modify the color of the View, you can directly set the app: search_color property in the layout file.
###Attention
----
If the layout_width and layout_height properties in the layout file do not set a specific value, the layout_width or layout_height of the View is 206dp by default.

If the color of the View is not set in the layout file, the default color of the View is # ff9e21.
