package com.mainbo.uclass_v4.testreclyerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * auther: elliott zhang
 * Emaill:18292967668@163.com
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_IMAGE = 0;
    private static final int TYPE_GROUP = 1;
    private List<ItemObject> mData = new ArrayList<>();
    private List<ItemObject> mDownlingList = new ArrayList<>();
    private List<ItemObject> mFinishList = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext,List<ItemObject> mData,List<ItemObject> mDownlingList,List<ItemObject> mFinishList) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDownlingList=mDownlingList;
        this.mFinishList=mFinishList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from (mContext);
        switch ( viewType ) {
            case TYPE_IMAGE:
                ViewGroup vImage = ( ViewGroup ) mInflater.inflate (R.layout.item_image, parent, false );
                ImageViewHolder vhImage = new ImageViewHolder ( vImage );
                return vhImage;
            case TYPE_GROUP:
                ViewGroup vGroup = ( ViewGroup ) mInflater.inflate ( R.layout.item_text, parent, false );
                GroupViewHolder vhGroup = new GroupViewHolder ( vGroup );
                return vhGroup;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        switch ( holder.getItemViewType ()) {
            case TYPE_IMAGE:
                ImageViewHolder imageViewHolder = ( ImageViewHolder ) holder;
                if(position==0){
                    imageViewHolder.mTitle.setVisibility(View.VISIBLE);
                    imageViewHolder.mTitle.setText("正在下载：" +"("+mDownlingList.size()+")");
                }else{
                    imageViewHolder.mTitle.setVisibility(View.GONE);
                }
                imageViewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ItemObject object = mData.get(position);
                        mDownlingList.remove(object);
                        object.setItemType(1);
                        mFinishList.add(object);

                        mData.clear();
                        mData.addAll(mDownlingList);
                        mData.addAll(mFinishList);
                        notifyDataSetChanged();
                    }
                });
                imageViewHolder.mContent.setText(mData.get(position).getContentStr());
                break;
            case TYPE_GROUP:
                GroupViewHolder groupViewHolder = ( GroupViewHolder ) holder;

                if(mFinishList!=null&&mFinishList.size()>0&&position==mDownlingList.size()){
                    groupViewHolder.mTitle.setVisibility(View.VISIBLE);
                    groupViewHolder.mTitle.setText("下载完成：" +"("+mFinishList.size()+")");
                }else{
                    groupViewHolder.mTitle.setVisibility(View.GONE);
                }
                groupViewHolder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ItemObject object = mData.get(position);
                        mFinishList.remove(object);
                        mData.remove(object);
                        notifyItemRemoved(position);
                    }
                });
                groupViewHolder.mContent.setText (mData.get(position).getContentStr());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size ();
    }

    @Override
    public int getItemViewType ( int position ) {
        return mData.get(position).getItemType();
    }

    public class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mContent;
        Button deleteBtn;
        public GroupViewHolder ( View itemView ) {
            super(itemView);
            mContent= (TextView) itemView.findViewById(R.id.text);
            mTitle= (TextView) itemView.findViewById(R.id.title);
            deleteBtn= (Button) itemView.findViewById(R.id.delete);
        }

    }
    public class ImageViewHolder extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mContent;
        Button deleteBtn;
        public ImageViewHolder ( View itemView ) {
            super(itemView );
            mTitle= (TextView) itemView.findViewById(R.id.title);
            mContent= (TextView) itemView.findViewById(R.id.text);
            deleteBtn= (Button) itemView.findViewById(R.id.delete);
        }
    }

    public  static class ItemObject{
        int itemType;
        String contentStr;

        public int getItemType() {
            return itemType;
        }

        public String getContentStr() {
            return contentStr;
        }

        public void setItemType(int itemType) {
            this.itemType = itemType;
        }

        public void setContentStr(String contentStr) {
            this.contentStr = contentStr;
        }
    }


}