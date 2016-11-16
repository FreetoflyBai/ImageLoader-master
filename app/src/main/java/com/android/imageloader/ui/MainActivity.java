package com.android.imageloader.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.android.imageloader.R;
import com.android.imageloader.util.ImageLoader;
import com.android.imageloader.util.NetworkUtils;
import com.bumptech.glide.Glide;

import java.util.Arrays;
import java.util.List;

import static android.R.attr.tag;

public class MainActivity extends AppCompatActivity {

    private boolean mCanGetBitmapFromNetWork=false;
    private boolean mIsGridViewIdle=true;
    private ImageAdapter mImageAdapter;
    private GridView mImageGridView;
    private ImageLoader mImageLoader;
    private boolean mIsWiFi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageLoader=ImageLoader.build(this);
        networkTips();
    }

    private void networkTips(){
        mIsWiFi=NetworkUtils.isWifiConnected(this);
        if(!mIsWiFi){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setMessage("初次使用会从网络中下载大概5M的图片，确认要下载吗？");
            builder.setTitle("注意");
            builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    initList();
                }
            });
            builder.setNegativeButton("否",null);
            builder.show();
        }else{
            initList();
        }

    }


    private void initList(){
        mCanGetBitmapFromNetWork=true;
        mImageGridView= (GridView) findViewById(R.id.gridView);
        mImageAdapter=new ImageAdapter(this);
        mImageGridView.setAdapter(mImageAdapter);

        mImageGridView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(scrollState== AbsListView.OnScrollListener.SCROLL_STATE_IDLE){
                    mIsGridViewIdle=true;
                    mImageAdapter.notifyDataSetChanged();
                }else{
                    mIsGridViewIdle=false;
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });
    }


    public class ImageAdapter extends BaseAdapter {

        private List<String> mUrList;
        private LayoutInflater mInflater;

        public ImageAdapter(Context context){
            mUrList= Arrays.asList(getResources().getStringArray(R.array.http_url_list));
            this.mInflater=LayoutInflater.from(context);
        }
        @Override
        public int getCount() {
            return mUrList.size();
        }

        @Override
        public Object getItem(int position) {
            return mUrList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;
            if(convertView==null){
                convertView=mInflater.inflate(R.layout.adapter_item,parent,false);
                holder=new ViewHolder();
                holder.imageView= (ImageView) convertView.findViewById(R.id.image_item);
                convertView.setTag(holder);
            }else{
                holder= (ViewHolder) convertView.getTag();
            }
            ImageView imageView=holder.imageView;
            final String tag= (String) imageView.getTag();
            final String uri= (String) getItem(position);
            if(!uri.equals(tag)){
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
            if(mIsGridViewIdle && mCanGetBitmapFromNetWork){
                imageView.setTag(uri);
                mImageLoader.bindBitmap(uri,imageView,100,100);
            }

            return convertView;
        }


        class ViewHolder{
            ImageView imageView;
        }
    }
}
