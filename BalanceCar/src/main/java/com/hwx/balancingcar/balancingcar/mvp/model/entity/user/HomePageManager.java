package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by dd on 2016/10/12.
 */

public class HomePageManager {

    private static HomePageManager instance;
    public static HomePageManager getManager(){
        if (instance==null){
            instance=new HomePageManager();
        }
        return instance;
    }

    public Homepage getItem(long id){
        Homepage item= null;
        try {
            Realm mRealm= Realm.getDefaultInstance();
            item = mRealm.copyFromRealm(mRealm.where(Homepage.class).equalTo("homeId",id).findFirst());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return item;
    }

    public List<Homepage> queryListAll(){
        Realm mRealm= Realm.getDefaultInstance();
        RealmResults<Homepage> homepages = mRealm.where(Homepage.class).findAll();
        return mRealm.copyFromRealm(homepages);

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
    public void add(final Homepage item){
        Realm mRealm= Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(item);
            }
        });
    }

    public void deleteByName(int name){
    }
    public void deleteItem(Homepage homepage){
    }
    public void deleteAll(){
        Realm mRealm= Realm.getDefaultInstance();

        final RealmResults<Homepage> tokens=  mRealm.where(Homepage.class).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //删除所有数据
                tokens.deleteAllFromRealm();
            }
        });
    }

}
