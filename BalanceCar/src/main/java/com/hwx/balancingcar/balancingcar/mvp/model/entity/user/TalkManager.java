package com.hwx.balancingcar.balancingcar.mvp.model.entity.user;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmObject;
import io.realm.RealmResults;

/**
 * Created by dd on 2016/10/12.
 */

public class TalkManager {

    private static TalkManager instance;
    public static TalkManager getManager(){
        if (instance==null){
            instance=new TalkManager();
        }
        return instance;
    }

    public Talk getTalk(long talkId){
        Talk talk= null;
        try {
            Realm mRealm= Realm.getDefaultInstance();
            Talk realmObject=mRealm.where(Talk.class).equalTo("talkId",talkId).findFirst();
            if (realmObject!=null)
                talk = mRealm.copyFromRealm(realmObject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return talk;
    }

    public List<RealmObject> getTalkList(String tag){
        Realm mRealm= Realm.getDefaultInstance();
        RealmResults<Talk> talks = mRealm.where(Talk.class).equalTo("listTag",tag).findAll();
        List<Talk> talkList=mRealm.copyFromRealm(talks);
        List<RealmObject> realmObjectList=new ArrayList<>();
        for(Talk talk:talkList){
            realmObjectList.add(talk);
        }
        return realmObjectList;
    }

    public void saveTalkList(final List<RealmObject> talkList, final String tag, final boolean isClean){
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Realm mRealm= Realm.getDefaultInstance();
                if (isClean) {
                    final RealmResults<Talk> talks=  mRealm.where(Talk.class).equalTo("listTag",tag).findAll();
                    mRealm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            //删除所有数据
                            talks.deleteAllFromRealm();
                        }
                    });
                }
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                CopyOnWriteArrayList<RealmObject> realmObjects=new CopyOnWriteArrayList<>(talkList);
                for (RealmObject talk:realmObjects){
                    if (!(talk instanceof Talk))
                        continue;
                    mRealm.beginTransaction();
                    mRealm.copyToRealm(talk);
                    mRealm.commitTransaction();
                }
            }
        }).start();
    }

    private void addTalk(final Talk talk) {
        Realm mRealm= Realm.getDefaultInstance();
        RealmAsyncTask addTask=  mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(talk);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
            }
        });

    }

}
