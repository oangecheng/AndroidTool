package orange.com.androidtool.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.LinkedList;
import java.util.List;

import orange.com.androidtool.PhotoLoad.CommonAdapter;
import orange.com.androidtool.R;

/**
 * Created by Orange on 2016/9/22.
 */
public class PhotoAdapter extends CommonAdapter<String> {




    /**
         * 用户选择的图片，存储为图片的完整路径
         */
        public static List<String> mSelectedImage = new LinkedList<String>();

        /**
         * 文件夹路径
         */
        private String mDirPath;

        public PhotoAdapter(Context context, List<String> mDatas, int itemLayoutId,
        String dirPath)
        {
            super(context, mDatas, itemLayoutId);
            this.mDirPath = dirPath;
        }

        @Override
        public void convert(final orange.com.androidtool.PhotoLoad.ViewHolder helper, final String item)
        {
            //设置no_pic
            helper.setImageResource(R.id.id_item_image, R.mipmap.pictures_no);
            //设置no_selected
            helper.setImageResource(R.id.id_item_select,
                    R.mipmap.picture_unselected);
            //设置图片
            helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);

            final ImageView mImageView = helper.getView(R.id.id_item_image);
            final ImageView mSelect = helper.getView(R.id.id_item_select);

            mImageView.setColorFilter(null);
            //设置ImageView的点击事件

            mImageView.setOnClickListener(new View.OnClickListener()
            {
                   //选择，则将图片变暗，反之则反之
                   @Override
                   public void onClick(View v)
                   {

                       // 已经选择过该图片
                       if (mSelectedImage.contains(mDirPath + "/" + item))
                       {
                           mSelectedImage.remove(mDirPath + "/" + item);
                           mSelect.setImageResource(R.mipmap.picture_unselected);
                           mImageView.setColorFilter(null);
                       } else
                       // 未选择该图片
                       {
                           mSelectedImage.add(mDirPath + "/" + item);
                           mSelect.setImageResource(R.mipmap.pictures_selected);
                           mImageView.setColorFilter(Color.parseColor("#77000000"));

                           System.out.println("****************orange hahaha");
                       }

                   }
            });

            /**
             * 已经选择过的图片，显示出选择过的效果
             */
            if (mSelectedImage.contains(mDirPath + "/" + item))
            {
                mSelect.setImageResource(R.mipmap.pictures_selected);
                mImageView.setColorFilter(Color.parseColor("#77000000"));
            }
        }


}
