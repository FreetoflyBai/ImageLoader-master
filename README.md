# ImageLoader-master
ImageLoader-master is an image processing framework, mainly used LruCache 、 DiskLrucache and ThreadPoolExecutor

Instructions:

![image](https://github.com/FreetoflyBai/ImageLoader-master/blob/master/screenshots/1.png)

    ##1. ImageLoader##

       ImageLoader mImageLoader=ImageLoader.build(this);
       mImageLoader.bindBitmap(uri,imageView,width,height);

    ##2. FixImageView##

       FixImageView has nothing to do with ImageLoader
       Is to achieve wide and high equality

       @Override
       protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
           super.onMeasure(widthMeasureSpec, widthMeasureSpec);
       }