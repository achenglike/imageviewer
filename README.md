# imageviewer  [![](https://jitpack.io/v/achenglike/imageviewer.svg)](https://jitpack.io/#achenglike/imageviewer)
图片浏览库，基本完全参考(说拷贝更合适)https://github.com/stfalcon-studio/FrescoImageViewer ,只是因为这个库使用了fresco，才稍微改造了下。解除了对图片库的依赖

## 使用到的库

PhotoView: https://github.com/chrisbanes/PhotoView

FrescoImageViewer: https://github.com/stfalcon-studio/FrescoImageViewer

## 使用方法

工程根目录下 `build.gradle` 

```gradle
allprojects {
	repositories {
        maven { url "https://jitpack.io" }
    }
}
```

需要使用此库的moudle下 `build.gradle`
```gradle
dependencies {
    compile 'com.github.achenglike:imageviewer:x.x'
}
```

吊起预览
```
new ImageViewer.Builder(MainActivity.this, urls)
	.setImageLoader(new ImageLoader() {
	    @Override
	    public void display(PhotoView photoView, String location) {
	        Glide.with(getApplicationContext()).load(location).into(photoView);
	    }

	    @Override
	    public void display(PhotoView photoView, @DrawableRes int resId) {
	        Glide.with(getApplicationContext()).load(resId).into(photoView);
	    }
	})
	.setStartPosition(position)
	.setImageChangeListener(new ImageViewer.OnImageChangeListener() {
	    @Override
	    public void onImageChange(int position) {
	    
	    }
	})
	.show();
```

更详细的用法可以参考：
https://github.com/stfalcon-studio/FrescoImageViewer 

## License

```
Copyright (C) 2016 stfalcon.com

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

```

[FrescoImageViewer]: <https://github.com/stfalcon-studio/FrescoImageViewer>
[PhotoView]: <https://github.com/chrisbanes/PhotoView>
