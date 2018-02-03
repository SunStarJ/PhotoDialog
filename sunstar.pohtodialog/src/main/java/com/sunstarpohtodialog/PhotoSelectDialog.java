package com.sunstarpohtodialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.view.menu.MenuWrapperFactory;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @Description: $<选择照片通用弹窗>
 * @author: 孙浩
 * @data: 2018/2/2 16:51
 * @version: V1.0
 */
public class PhotoSelectDialog {
    private Dialog dialog;
    private Context context;
    private View dialogView;
    private TextView check;
    private PhotoSelectCheckListener listener;
    private String imgPath;

    public interface PhotoSelectCheckListener {
        void check();

        void formCamera();

        void formBox();
    }

    private PhotoSelectDialog hidenCheck() {
        check.setVisibility(View.GONE);
        return this;
    }

    public PhotoSelectDialog(Context context,String imgPath) {
        this.context = context;
        this.imgPath = imgPath;
        initDialog();
    }

    public void show(PhotoSelectCheckListener listener) {
        this.listener = listener;
        dialog.show();
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.gravity=Gravity.BOTTOM;
        layoutParams.width= ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.height= ViewGroup.LayoutParams.WRAP_CONTENT;

        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);

        dialog.getWindow().setAttributes(layoutParams);
    }

    private void initDialog() {
        dialog = new Dialog(context, R.style.selectorDialog);
        initView();
        Window window = ((Activity) context).getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.x = 0;
        layoutParams.y = ((Activity) context).getWindowManager().getDefaultDisplay().getHeight();
        layoutParams.width = (int) ((((Activity) context).getWindowManager().getDefaultDisplay().getWidth())*0.8);
        dialog.onWindowAttributesChanged(layoutParams);
        dialog.setCanceledOnTouchOutside(true);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogView,new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        Window mywindow = dialog.getWindow();
        mywindow.setGravity(Gravity.BOTTOM);
    }

    private void initView() {
        dialogView = View.inflate(context, R.layout.select_photo_dialog, null);
        check = $(R.id.check);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.check();
                dialog.dismiss();
                dialog = null;
            }
        });

        $(R.id.from_box).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.formBox();
                dialog.dismiss();
                dialog = null;
            }
        });

        $(R.id.take_pic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.formCamera();
                dialog.dismiss();
                dialog = null;
            }
        });

        $(R.id.dismiss).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                dialog = null;
            }
        });
        if(imgPath==null||imgPath.equals("")){
            hidenCheck();
        }
    }


    private <T extends View> T $(int id) {
        return (T) dialogView.findViewById(id);
    }

}