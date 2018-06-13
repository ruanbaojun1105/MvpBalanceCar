package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import com.hwx.balancingcar.balancingcar.app.utils.UserHelper;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by dd on 2016/10/12.
 */

public class UsersSelfManager {

    private static UsersSelfManager instance;
    public static UsersSelfManager getManager(){
        if (instance==null){
            instance=new UsersSelfManager();
        }
        return instance;
    }


    public List<UsersSelf> queryListAll(){
        Realm mRealm= Realm.getDefaultInstance();
        RealmResults<UsersSelf> locationItems = mRealm.where(UsersSelf.class).findAll();
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
    public void addUser(final UsersSelf item){
//        deleteAll();
        Realm mRealm= Realm.getDefaultInstance();
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(item);
                UserHelper.getInstance().setSelfUser(item);
                //realm.copyToRealm(item);
            }
        });
    }

    public void deleteByName(int name){
    }
    public void deleteItem(UsersSelf cocktail){
    }
    public void deleteAll(){
        Realm mRealm= Realm.getDefaultInstance();

        final RealmResults<UsersSelf> tokens=  mRealm.where(UsersSelf.class).findAll();

        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //删除所有数据
                tokens.deleteAllFromRealm();
            }
        });
    }

}
