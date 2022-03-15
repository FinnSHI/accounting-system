package com.finn.accounting.utils;

import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.text.Editable;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import com.finn.accounting.R;

/*
 * @description: keyboard utils
 * @author: Finn
 * @create: 2022-03-05-21-41
 */
public class KeyboardUtils {

    private final Keyboard keyboard;

    private KeyboardView keyboardView;
    private EditText editText;

    private OnEnsureListener onEnsureListener;

    public void setOnEnsureListener(OnEnsureListener onEnsureListener) {
        this.onEnsureListener = onEnsureListener;
    }

    public KeyboardUtils(KeyboardView keyboardView, EditText editText) {
        this.keyboardView = keyboardView;
        this.editText = editText;
        this.editText.setInputType(InputType.TYPE_NULL);
        keyboard = new Keyboard(this.editText.getContext(), R.xml.keyboard);

        this.keyboardView.setKeyboard(keyboard);
        this.keyboardView.setEnabled(true);
        this.keyboardView.setPreviewEnabled(false);
        this.keyboardView.setOnKeyboardActionListener(listener);
    }


    /*
     * @Description: 按钮点击监听
     * @Param:
     * @return:
     * @Author: Finn
     * @Date: 2022/3/5
     */
    KeyboardView.OnKeyboardActionListener listener = new KeyboardView.OnKeyboardActionListener() {
        @Override
        public void onPress(int i) {

        }

        @Override
        public void onRelease(int i) {

        }

        @Override
        public void onKey(int i, int[] ints) {
            Editable text = editText.getText();
            int selectionStart = editText.getSelectionStart();
            switch (i) {
                case Keyboard.KEYCODE_DELETE:
                    if (text != null && text.length() > 0) {
                        if (selectionStart > 0) {
                            text.delete(selectionStart - 1, selectionStart);
                        }
                    }
                    break;
                case Keyboard.KEYCODE_CANCEL:
                    text.clear();
                    break;
                case Keyboard.KEYCODE_DONE:
                    onEnsureListener.onEnsure();
                    break;
                default:  // others
                    char c = (char) i;
                    text.insert(selectionStart, Character.toString(c));
                    break;
            }
        }

        @Override
        public void onText(CharSequence charSequence) {

        }

        @Override
        public void swipeLeft() {

        }

        @Override
        public void swipeRight() {

        }

        @Override
        public void swipeDown() {

        }

        @Override
        public void swipeUp() {

        }
    };

    public void showKeyboard(){
        int visibility = keyboardView.getVisibility();
        if (visibility == View.INVISIBLE ||visibility==View.GONE) {
            keyboardView.setVisibility(View.VISIBLE);
        }
    }

    public void hideKeyboard(){
        int visibility = keyboardView.getVisibility();
        if (visibility == View.VISIBLE || visibility == View.INVISIBLE) {
            keyboardView.setVisibility(View.GONE);
        }
    }

}
