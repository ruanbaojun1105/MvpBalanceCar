package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import com.hwx.balancingcar.balancingcar.bean.LocationItem;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by dd on 2016/10/12.
 */

public class PlaceManager {

    private static PlaceManager instance;
    public static PlaceManager getLocationItemManager(){
        if (instance==null){
            instance=new PlaceManager();
        }
        return instance;
    }


    public List<LocationItem> queryLocationItemListAll(){
        Realm mRealm= Realm.getDefaultInstance();
        RealmResults<LocationItem> locationItems = mRealm.where(LocationItem.class).findAll();
        return mRealm.copyFromRealm(locationItems);

        //异步操作
       /* RealmResults<LocationItem> cats=mRealm.where(LocationItem.class).findAllAsync();
        cats.addChangeListener(new RealmChangeListener<RealmResults<LocationItem>>() {
            @Override
            public void onChange(RealmResults<LocationItem> element) {
                element= element.sort("id");
                List<LocationItem> datas=mRealm.copyFromRealm(element);
            }
        });*/
    }
    public void addLoc(final LocationItem item){
        Realm mRealm= Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(item);
            }
        });
    }

    public void deleteByName(int name){
    }
    public void deleteLocationItem(LocationItem cocktail){
    }
    public void deleteAllLocationItem(){
    }

}
