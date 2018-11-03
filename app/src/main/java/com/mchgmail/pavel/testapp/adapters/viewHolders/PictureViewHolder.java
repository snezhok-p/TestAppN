package com.mchgmail.pavel.testapp.adapters.viewHolders;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mchgmail.pavel.testapp.R;

import retrofit2.http.Url;

public class PictureViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView pic1;
    ImageView pic2;
    ImageView pic3;

    public PictureViewHolder(@NonNull View itemView, String[] link, Context context) {
        super(itemView);

        Uri uri0 = Uri.parse(link[0]);
        DraweeController controller0 = Fresco.newDraweeControllerBuilder()
                .setUri(uri0)
                .setAutoPlayAnimations(true)
                .build();
        SimpleDraweeView gif0 = itemView.findViewById(R.id.drawee_view0);
        gif0.setController(controller0);
        gif0.setLayerType(View.LAYER_TYPE_HARDWARE,null);
//        Animatable animatable = gif0.getController().getAnimatable();
//        if(animatable != null){
//            animatable.start();
//        }

        Uri uri1 = Uri.parse(link[1]);
        DraweeController controller1 = Fresco.newDraweeControllerBuilder()
                .setUri(uri1)
                .setAutoPlayAnimations(true)
                .build();

        SimpleDraweeView gif1 = itemView.findViewById(R.id.drawee_view1);
        gif1.setController(controller1);


        Uri uri2 = Uri.parse(link[2]);
        DraweeController controller2 = Fresco.newDraweeControllerBuilder()
                .setUri(uri2)
                .setAutoPlayAnimations(true)
                .build();

        SimpleDraweeView gif2 = itemView.findViewById(R.id.drawee_view2);
        gif2.setController(controller2);
    }

    @Override
    public void onClick(View v) {

    }
}
