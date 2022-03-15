package com.finn.accounting.fragment;

import android.annotation.SuppressLint;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.finn.accounting.R;
import com.finn.accounting.adapter.TypeBaseAdapter;
import com.finn.accounting.db.DBManager;
import com.finn.accounting.db.MyDBOpenHelper;
import com.finn.accounting.entity.Account;
import com.finn.accounting.entity.Type;
import com.finn.accounting.utils.DescriptionDialog;
import com.finn.accounting.utils.KeyboardUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link ConsumptionFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class ConsumptionFragment extends Fragment implements View.OnClickListener {

//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;

    private KeyboardView keyboardView;
    private EditText moneyEditText;
    private ImageView typeImageView;
    private TextView typeTextView;
    private TextView descriptionTextView;
    private TextView timeTextView;
    private GridView typeGridView;
    private List<Type> typeList;
    private TypeBaseAdapter typeBaseAdapter;
    private Account account;

    public ConsumptionFragment() {
        // Required empty public constructor
    }

//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment ConsumptionFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static ConsumptionFragment newInstance(String param1, String param2) {
//        ConsumptionFragment fragment = new ConsumptionFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
        account = new Account();
        account.setTypeName("Others");
        account.setsImageId(R.mipmap.ic_qita_fs);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_consumption, container, false);
        initView(inflate);
        initLocalTime();
        loadDataToGrid();
        setGridViewListener();
        return inflate;
    }

    @SuppressLint("SimpleDateFormat")
    private void initLocalTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String time = simpleDateFormat.format(date);
        timeTextView.setText(time);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        account.setTime(time);
        account.setYear(year);
        account.setMonth(month);
        account.setDay(day);
    }

    private void setGridViewListener() {
        typeGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                typeBaseAdapter.setSelectPos(i);
                typeBaseAdapter.notifyDataSetInvalidated();
                Type type = typeList.get(i);
                String typeName = type.getTypeName();
                int sImageId = type.getsImageId();
                typeTextView.setText(typeName);
                typeImageView.setImageResource(sImageId);
                account.setTypeName(typeName);
                account.setsImageId(sImageId);
            }
        });
    }

    private void loadDataToGrid() {
        typeList = new ArrayList<>();
        typeBaseAdapter = new TypeBaseAdapter(getContext(), typeList);
        typeGridView.setAdapter(typeBaseAdapter);
        List<Type> consumptionList = DBManager.getTypeList(0);
        typeList.addAll(consumptionList);
        typeBaseAdapter.notifyDataSetChanged();
    }

    private void initView(View view) {
        keyboardView = view.findViewById(R.id.frag_record_keyboard);
        moneyEditText = view.findViewById(R.id.frag_record_spend_money);
        typeImageView = view.findViewById(R.id.frag_record_type_icon);
        typeTextView = view.findViewById(R.id.frag_record_type_name);
        descriptionTextView = view.findViewById(R.id.frag_record_description);
        timeTextView = view.findViewById(R.id.frag_record_time);
        typeGridView = view.findViewById(R.id.frag_record_content);

        KeyboardUtils keyboardUtils = new KeyboardUtils(keyboardView, moneyEditText);
        keyboardUtils.showKeyboard();
        keyboardUtils.setOnEnsureListener(() -> {
            String m = moneyEditText.getText().toString();
            if("0".equals(m) || TextUtils.isEmpty(m)) {
                getActivity().finish();
                return;
            } else {
                float money = Float.parseFloat(m);
                account.setMoney(money);

                getActivity().finish();
            }
        });
    }

    public void saveAccountToDB() {
        account.setKind(0);
        DBManager.insertItemToAccountTb(account);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.frag_record_time:
                showTimeDialog();
                break;
            case R.id.frag_record_description:
                showDescriptionDialog();
                break;
        }
    }

    /*
    * @Description: Time
    * @Param: []
    * @return:
    * @Author: Finn
    * @Date: 2022/3/13
    */
    private void showTimeDialog() {
//        SelectTimeDialog dialog = new SelectTimeDialog(getContext());
//        dialog.show();
//        dialog.setOnEnsureListener(new SelectTimeDialog.OnEnsureListener() {
//            @Override
//            public void onEnsure(String time, int year, int month, int day) {
//                timeTv.setText(time);
//                accountBean.setTime(time);
//                accountBean.setYear(year);
//                accountBean.setMonth(month);
//                accountBean.setDay(day);
//            }
//        });
    }

    /*
    * @Description: Description对话框
    * @Param: []
    * @return:
    * @Author: Finn
    * @Date: 2022/3/13
    */
    public  void showDescriptionDialog(){
        final DescriptionDialog dialog = new DescriptionDialog(getContext());
        dialog.show();
//        dialog.setDialogSize();
        dialog.setOnEnsureListener(() -> {
            String msg = dialog.getEditText();
            if (!TextUtils.isEmpty(msg)) {
                descriptionTextView.setText(msg);
                account.setDescription(msg);
            }
            dialog.cancel();
        });
    }
}