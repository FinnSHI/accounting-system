package com.finn.accounting.utils;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.finn.accounting.R;

import java.util.List;

/*
 * @description: 描述对话框
 * @author: Finn
 * @create: 2022-03-13-23-10
 */
public class DescriptionDialog extends Dialog implements View.OnClickListener {

    EditText et;
    Button cancelBtn,ensureBtn;
    OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public interface OnEnsureListener{
        public void onEnsure();
    }

    public DescriptionDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_record_fragment_desc);//设置对话框显示布局
        et = findViewById(R.id.dialog_description_et);
        cancelBtn = findViewById(R.id.dialog_description_btn_cancel);
        ensureBtn = findViewById(R.id.dialog_description_btn_ensure);
        cancelBtn.setOnClickListener(this);
        ensureBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dialog_description_btn_cancel:
                cancel();
                break;
            case R.id.dialog_description_btn_ensure:
                if (onEnsureListener!=null) {
                    onEnsureListener.onEnsure();
                }
                break;
        }
    }

    public String getEditText(){
        return et.getText().toString().trim();
    }

//    public void setDialogSize(){
//        Window window = getWindow();
//        WindowManager.LayoutParams wlp = window.getAttributes();
//        Display d = window.getWindowManager().getDefaultDisplay();
//        wlp.width = (int)(d.getWidth());
//        wlp.gravity = Gravity.BOTTOM;
//        window.setBackgroundDrawableResource(android.R.color.transparent);
//        window.setAttributes(wlp);
//        handler.sendEmptyMessageDelayed(1,100);
//    }
//
//    Handler handler = new Handler(){
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//            inputMethodManager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    };
}
