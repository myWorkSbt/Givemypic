package com.shrishri1108.givemypic.Activity.compress_image_code;

import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.shrishri1108.givemypic.UtilityMethods.OnActionButtonsClick;
import com.shrishri1108.givemypic.databinding.ItemCompressedDbImagesLaysBinding;

import java.util.ArrayList;

public class CompressedImageAdapter extends RecyclerView.Adapter<CompressedImageAdapter.ViewHolderss> {

    private ArrayList<CompressedImageModel> compressedImageList;

    OnActionButtonsClick onActionButtonsClick ;
    public CompressedImageAdapter(@NonNull ArrayList<CompressedImageModel> compressedImageLists , @NonNull OnActionButtonsClick onActionButtonsClick) {
        this.compressedImageList = compressedImageLists ;
        this.onActionButtonsClick =  onActionButtonsClick ;
    }

    @NonNull
    @Override
    public CompressedImageAdapter.ViewHolderss onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCompressedDbImagesLaysBinding itemCompressedDbImagesLaysBinding = ItemCompressedDbImagesLaysBinding.inflate(LayoutInflater.from(parent.getContext()));
        
        return new ViewHolderss(itemCompressedDbImagesLaysBinding );
    }

    @Override
    public void onBindViewHolder(@NonNull CompressedImageAdapter.ViewHolderss holder, int position) {
         CompressedImageModel oneImgmodels = compressedImageList.get(position);

         if (position == 0 ) {
             setItemNameAndStyleToBoldAndCenter(holder.compressedDbImagesLaysBinding.tvId, "Id") ;
             setItemNameAndStyleToBoldAndCenter(holder.compressedDbImagesLaysBinding.tvName , "Name") ;
             setItemNameAndStyleToBoldAndCenter(holder.compressedDbImagesLaysBinding.tvSize, "Size") ;
             setItemNameAndStyleToBoldAndCenter(holder.compressedDbImagesLaysBinding.tvCompressedRatio, "Compression Ratio ") ;
             setItemNameAndStyleToBoldAndCenter(holder.compressedDbImagesLaysBinding.tvCreatedAt , "Created At") ;
             setItemNameAndStyleToBoldAndCenter(holder.compressedDbImagesLaysBinding.tvDownload, "Download ") ;
             setItemNameAndStyleToBoldAndCenter(holder.compressedDbImagesLaysBinding.tvType, "Image Type") ;
             setItemNameAndStyleToBoldAndCenter(holder.compressedDbImagesLaysBinding.tvDelete ,"Delete") ;
         }
         else {
             if (oneImgmodels != null ) {
                 holder.compressedDbImagesLaysBinding.tvId.setText(String.valueOf(oneImgmodels.getId()));
                 holder.compressedDbImagesLaysBinding.tvName.setText(oneImgmodels.getName());
                 holder.compressedDbImagesLaysBinding.tvCompressedRatio.setText(oneImgmodels.getCompressed_ratio());
                 holder.compressedDbImagesLaysBinding.tvType.setText(oneImgmodels.getType());
                 holder.compressedDbImagesLaysBinding.tvSize.setText(oneImgmodels.getSize());
                 holder.compressedDbImagesLaysBinding.tvCreatedAt.setText(oneImgmodels.getCreated_at());
             }
             holder.compressedDbImagesLaysBinding.tvDownload.setOnClickListener(v -> {
                onActionButtonsClick.onDownloadButtonClicked(position);
                notifyItemChanged(position);
             });
             holder.compressedDbImagesLaysBinding.tvDelete.setOnClickListener(v -> {
                 onActionButtonsClick.onDeleteButtonClicked(oneImgmodels.getId()) ;
                 notifyItemChanged(position);
             });

         }
    }

    private void setItemNameAndStyleToBoldAndCenter(AppCompatEditText et_btns, String btn_names) {
        et_btns.setTypeface(Typeface.DEFAULT_BOLD);
        et_btns.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        et_btns.setText(btn_names);
    }

    private void setItemNameAndStyleToBoldAndCenter(AppCompatTextView tv_field, String field_names) {
        tv_field.setTypeface(Typeface.DEFAULT_BOLD);
        tv_field.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tv_field.setText(field_names);
    }

    @Override
    public int getItemCount() {
        return compressedImageList.size();
    }

    public void refreshLists(ArrayList<CompressedImageModel> allImagesLists ) {
        this.compressedImageList = allImagesLists ;
        notifyDataSetChanged();
    }

    public class ViewHolderss extends RecyclerView.ViewHolder { 
        ItemCompressedDbImagesLaysBinding compressedDbImagesLaysBinding;
        public ViewHolderss(@NonNull ItemCompressedDbImagesLaysBinding itemView) {
            super(itemView.getRoot());
             this.compressedDbImagesLaysBinding = itemView ;
        }
    }
}
