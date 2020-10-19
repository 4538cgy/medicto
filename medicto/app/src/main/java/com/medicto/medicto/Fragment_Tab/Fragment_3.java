package com.medicto.medicto.Fragment_Tab;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.medicto.medicto.DateList.PharDataList;
import com.medicto.medicto.ListView.ListViewItem;
import com.medicto.medicto.ListViewSearchAdapter;
import com.medicto.medicto.MainActivity;
import com.medicto.medicto.R;
import com.medicto.medicto.map.CurrentPositionMap;
import com.medicto.medicto.map.SearchMap;
import com.medicto.medicto.model.PharmacyModel;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.content.Context.LOCATION_SERVICE;
import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_3 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    EditText searchEdittext;
    ListView listView;

    ArrayList<PharmacyModel> list;
    ArrayList<PharmacyModel> copyList;

    ListViewSearchAdapter listViewSearchAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fragment_3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fragment_3.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_3 newInstance(String param1, String param2) {
        Fragment_3 fragment = new Fragment_3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_3,container,false);

        searchEdittext = (EditText)view.findViewById(R.id.fragment_3_edittext_search);
        listView = (ListView)view.findViewById(R.id.fragment_3_listview);

        list = new ArrayList<>();
        copyList = new ArrayList<>();


        list = PharDataList.getInstance().getPharDataList();

        System.out.println( " 병원 이름은 ? : " + list.get(0).getPharName());
        copyList.addAll(list);

        listViewSearchAdapter  = new ListViewSearchAdapter(list,getActivity());


        switch (getActivity().getSharedPreferences("userData",MODE_PRIVATE).getString("userData_Lang",null)){
            case "ENGLISH":
               searchEdittext.setHint("Search Pharmacy");
                break;
            case "French":
                searchEdittext.setHint("Recherche Pharmacie");
                break;
            case "한국어":
                searchEdittext.setHint("약국 검색");
                break;
            case "汉语":
                searchEdittext.setHint("搜索药房");
                break;
        }
        listView.setAdapter(listViewSearchAdapter);

        searchEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = searchEdittext.getText().toString();
                search(text);
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                System.out.println(new PharDataList().getPharDataList().get(position).getPharName());




                Intent intent = new Intent(getActivity(),CurrentPositionMap.class);
                intent.putExtra("pharName",new PharDataList().getPharDataList().get(position).getPharName());
                intent.putExtra("pharLatitude",new PharDataList().getPharDataList().get(position).getLatitude());
                intent.putExtra("pharLongitude",new PharDataList().getPharDataList().get(position).getLongitude());
                intent.putExtra("pharCheck",true);
                startActivity(intent);


            }
        });





        return view;
    }



    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(copyList);
        }
        // 문자 입력을 할때..
        else
        {
            // 리스트의 모든 데이터를 검색한다.
            for(int i = 0;i < copyList.size(); i++)
            {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (copyList.get(i).getPharName().toLowerCase().contains(charText))
                {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(copyList.get(i));
                }

                if(copyList.get(i).getPharPhoneNumber().toLowerCase().contains(charText)){
                    list.add(copyList.get(i));
                }
                if(copyList.get(i).getPharAddress().toLowerCase().contains(charText)){
                    list.add(copyList.get(i));
                }
                if(copyList.get(i).getEngName().toLowerCase().contains(charText)){
                    list.add(copyList.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        listViewSearchAdapter.notifyDataSetChanged();
    }



}