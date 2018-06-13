package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by dd on 2016/10/12.
 */

public class UserTokenManager {

    private static UserTokenManager instance;
    public static UserTokenManager getManager(){
        if (instance==null){
            instance=new UserTokenManager();
        }
        return instance;
    }


    public List<UserToken> queryListAll(){
        Realm mRealm= Realm.getDefaultInstance();
        RealmResults<UserToken> items = mRealm.where(UserToken.class).findAll();
        return mRealm.copyFromRealm(items);

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
    public void add(final UserToken item){
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
    public void deleteItem(UserToken cocktail){
    }
    public void deleteAll(){
        Realm mRealm= Realm.getDefaultInstance();

        final RealmResults<UserToken> tokens=  mRealm.where(UserToken.class).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //删除所有数据
                tokens.deleteAllFromRealm();
            }
        });
    }

}
