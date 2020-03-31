package com.example.deng.myapplication2.Model;//package com.example.deng.myapplication2;

import android.content.Context;

import com.example.deng.myapplication2.ApiAddress.ApiName;
import com.example.deng.myapplication2.ApiAddress.ApiService;
import com.example.deng.myapplication2.Util.CheckFolders;
import com.example.deng.myapplication2.Database.DataBase;
import com.example.deng.myapplication2.Database.DataBase2;
import com.example.deng.myapplication2.Util.DataBaseUtil;
import com.example.deng.myapplication2.Util.DataBaseUtil2;
import com.example.deng.myapplication2.Util.DealFileUtil;
import com.example.deng.myapplication2.Util.FileUtil;
import com.example.deng.myapplication2.IModel.IModel_GETDATA;
import com.example.deng.myapplication2.Manager.RetrofitManager;
import com.example.deng.myapplication2.Bean.StationBean;
import com.example.deng.myapplication2.Bean.TrainBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Model_GETDATA implements IModel_GETDATA {
    ArrayList<TrainBean> trainmessage;
    ArrayList<StationBean> stationmessage;
    ArrayList<Integer> integers=new ArrayList<Integer>();
    ArrayList<String> strings=new ArrayList<String>();
    OnFinish onFinish;
    Context context;
    int i=0;
    ArrayList<StationBean> stationBeans=new ArrayList<StationBean>();
    public Model_GETDATA(OnFinish onFinish, Context context){
        this.onFinish=onFinish;
        this.context=context;
    }
    @Override
    public void train() {
        ApiService apiService = RetrofitManager.getInstance(ApiName.Path,context).setCreate(ApiService.class);
        Observable<ResponseBody> getData=apiService.getTrainMessage();
        ApiService apiService2 = RetrofitManager.getInstance(ApiName.Path,context).setCreate(ApiService.class);
        Observable<ResponseBody> getData2=apiService2.getStationMessage();
        Observable<ResponseBody>[] observables=new Observable[3];
        observables[0]=getData2;
        observables[1]=getData2;
        observables[2]=getData;
        Observable.concatArray(observables).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread()).subscribe(new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBody responseBody) {
                i++;
                if(i==1) {
                    String b = CheckFolders.getSDPath();
                    FileUtil.deleteFile(b + "/stationmessage.txt");
                    FileUtil.writeBig(b + "/stationmessage.txt", responseBody.byteStream(),false);
                    FileUtil.replaceFileStr(b + "/stationmessage.txt", "var station_names ='", "");
                    FileUtil.replaceFileStr(b + "/stationmessage.txt", "';", "");
                }
                if(i==2){
                    ArrayList<String> mymessage = DealFileUtil.divider(responseBody.byteStream(), integers, strings,"@");
                    mymessage.remove(0);
                    mymessage.set(mymessage.size()-1,mymessage.get(mymessage.size()-1).substring(0,mymessage.get(mymessage.size()-1).length()-2));
                   ArrayList<String[]> mymessages=new ArrayList<String[]>();
                    for(int i=0;i<mymessage.size();i++){
                        String[] scope=mymessage.get(i).split("\\|");
                        mymessages.add(scope);
                    }
                    for(int j=0;j<mymessages.size();j++){
                            stationBeans.add(new StationBean(mymessages.get(j)[0], mymessages.get(j)[1],
                                    mymessages.get(j)[2],mymessages.get(j)[3],mymessages.get(j)[4],mymessages.get(j)[5]));
                    }
                    DataBase2 dataBase2 = new DataBase2(context, "stationmessage.db", null, 1);
                    if (DataBaseUtil2.select(dataBase2) != 0) {
                        DataBaseUtil2.delete(dataBase2, context);
                    }
                    DataBaseUtil2.PiliangIsert(dataBase2, stationBeans);
                    stationmessage=stationBeans;
                    onFinish.showStations(stationmessage);
                }
              if(i==3) {
                   String a = CheckFolders.getSDPath();
                  FileUtil.deleteFile(a + "/trainmessage.txt");
                    FileUtil.writeBig(a + "/trainmessage.txt", responseBody.byteStream(),false);
                    FileUtil.replaceFileStr(a + "/trainmessage.txt", "var train_list =", "");
                    ArrayList<TrainBean> trainbeans = FileUtil.readToJson(a + "/trainmessage.txt");
                    DataBase dataBase = new DataBase(context, "trainmessage.db", null, 1);
                    if (DataBaseUtil.select(dataBase) != 0) {
                        DataBaseUtil.delete(dataBase, context);
                    }
                    DataBaseUtil.PiliangIsert(dataBase, trainbeans);
                    trainmessage=trainbeans;
                    onFinish.showTrains(trainmessage);
                }
            }


            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }




    public interface OnFinish{
        void showTrains(ArrayList<TrainBean> trainmessage);
        void showStations(ArrayList<StationBean> stationmessage);
    }
}
