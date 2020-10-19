package com.medicto.medicto.DateList;

import com.medicto.medicto.model.PharmacyModel;

import java.util.ArrayList;
import java.util.List;




public class PharDataList {



   // ArrayList<PharmacyModel> List = null;


    PharDataList pharDataList = PharDataList.getInstance();

    private static PharDataList PDL = new PharDataList();

    public PharDataList(){

    }

    public static PharDataList getInstance(){
        return PDL;
    }

    public ArrayList<PharmacyModel> getPharDataList(){
        ArrayList<PharmacyModel> List = new ArrayList<>();

        List.clear();
        //List.add(new PharmacyModel("","","","","","", "","",""));
        List.add(new PharmacyModel("JDQ4MTYyMiM2MSMkMSMkMiMkMDMkNDgxMzUxIzExIyQxIyQ3IyQ3MiQyNjEwMDIjNTEjJDEjJDIjJDgz","유성미래약국","대전광역시 유성구 계룡로 150 1층 (봉명동)","34189","042-822-8221","36.35179", "127.3469003","20180601","YuseongMirae Yakguk"));
        List.add(new PharmacyModel("JDQ4MTYyMiM2MSMkMSMkMiMkMDMkNDgxMzUxIzExIyQxIyQ3IyQ3MiQzNjE0ODEjNzEjJDEjJDgjJDgz","반석약국","대전광역시 유성구 반석로 20 플러스존 1층 102호 (반석동)","34068","042-825-3978","36.3917888", "127.3123291","20180601","BanSeok Yakguk"));
        List.add(new PharmacyModel("JDQ4MTYyMiM2MSMkMSMkMiMkMDMkMzgxOTYxIzUxIyQyIyQxIyQwMCQzNjE4MzIjNjEjJDEjJDQjJDgz","우리들의약국","대전광역시 유성구 궁동로18번길 31 (궁동)","34137","042-822-0427","36.3628951", "127.3491626","19991127","WooriDeului Yakguk"));
        List.add(new PharmacyModel("JDQ4MTYyMiM2MSMkMSMkMiMkMDMkMzgxMzUxIzIxIyQxIyQ5IyQ2MiQ0NjE0ODEjNDEjJDEjJDQjJDgz","명근당약국","대전광역시 유성구 어은로58번길 2 (어은동)","34139","042-863-7737","36.3633003", "127.3567316","19991210","MyeongGeunDang Yakguk"));
        List.add(new PharmacyModel("JDQ4MTYyMiM2MSMkMSMkMiMkMDMkMzgxMTkxIzUxIyQxIyQ1IyQxMyQyNjE4MzIjODEjJDEjJDIjJDgz","갤러리비타민약국","대전광역시 서구 대덕대로 203 (둔산동)","35230","042-486-0505","36.3512445", "127.3779281","20080728","GalleryVitamin Yakguk"));
        List.add(new PharmacyModel("JDQ4MTYyMiM2MSMkMSMkMiMkMDMkMzgxMTkxIzUxIyQxIyQxIyQ3OSQyNjE0ODEjNDEjJDEjJDgjJDgz","가톨릭약국","대전광역시 중구 대전천서로 457 (은행동)","34922","042-257-4006","36.3294978", "127.4288251","20061018","Catholic Yakguk"));

        return List;
    }




}
