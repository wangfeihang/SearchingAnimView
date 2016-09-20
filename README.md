
# SearchingAnimView
SearchingAnimView是一个比较美观的搜索动画，但是你不仅仅可以用它来表达搜索的属性，它还可以用与匹配用户、歌曲播放等场景。

###Demo
----
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

在代码中这样使用：

```java
    <com.sakuramomoko.searchinganimview.SearchingAnimView
        android:layout_width="306dp"
        android:layout_height="306dp"
        app:search_color="#ff9e21"
        android:id="@+id/searching_anim_view"/>
```	

```java
    SearchingAnimView searchingAnimView=(SearchingAnimView)findViewById(R.id.searching_anim_view);
    //开始动画
    searchingAnimView.startAnimations();
    //停止动画
    searchingAnimView.stopAnimations();
```
如果你想修改该View的大小，可以直接在布局文件中设置layout_width和layout_height，像例子中那样。也可以通过setViewSize(int parentWith, int parentHeight)或者public void setViewSize(AttributeSet attrs)函数进行设置。

如果你想修改该View的颜色，可以直接在布局文件中设置app:search_color的属性值即可。
###注意
----
如果布局文件中没有设置layout_width和layout_height为具体的数值，而是设置了wrap_content等的属性，则默认该View的layout_width或layout_height为206dp。

如果布局文件中没有设置该View的颜色，则默认该View的颜色为#ff9e21。
