package com.finn.accounting.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.finn.accounting.R;

/*
 * @description: 预算
 * @author: Finn
 * @create: 2022-03-15-16-43
 */
public class BudgetDialog extends Dialog implements View.OnClickListener {
    ImageView closeImageView;
    Button enterBtn;
    EditText moneyEditText;

    public  interface OnEnsureListener{
        public void onEnsure(float money);
    }

    OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public BudgetDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_budget);
        closeImageView = findViewById(R.id.dialog_budget_close);
        enterBtn = findViewById(R.id.dialog_budget_btn_enter);
        moneyEditText = findViewById(R.id.dialog_budget_et);

        closeImageView.setOnClickListener(this);
        enterBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_budget_close:
                cancel();
                break;
            case R.id.dialog_budget_btn_enter:

                String data = moneyEditText.getText().toString();
                if (TextUtils.isEmpty(data)) {
                    Toast.makeText(getContext(),"No budget！",Toast.LENGTH_SHORT).show();
                    return;
                }
                float money = Float.parseFloat(data);
                if (money <= 0) {
                    Toast.makeText(getContext(),"Budget must more than 0!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (onEnsureListener != null) {
                    onEnsureListener.onEnsure(money);
                }
                cancel();
                break;
        }
    }

    /* 
    * @Description: 设置Dialog的尺寸和屏幕尺寸一致 
    * @Param: [] 
    * @return:  
    * @Author: Finn
    * @Date: 2022/3/15 
    */
    public void setDialogSize(){
        Window window = getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        
        Display d = window.getWindowManager().getDefaultDisplay();
        wlp.width = (int)(d.getWidth()); 
        wlp.gravity = Gravity.BOTTOM;
        window.setBackgroundDrawableResource(android.R.color.transparent);
        window.setAttributes(wlp);
        handler.sendEmptyMessageDelayed(1,100);
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            //自动弹出软键盘的方法
            InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
        }
    };
}
