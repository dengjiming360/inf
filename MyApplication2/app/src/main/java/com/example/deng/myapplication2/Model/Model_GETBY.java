package com.example.deng.myapplication2.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.deng.myapplication2.ApiAddress.ApiName;
import com.example.deng.myapplication2.ApiAddress.ApiService2;
import com.example.deng.myapplication2.Bean.TrainByBean;
import com.example.deng.myapplication2.Database.DataBase2;
import com.example.deng.myapplication2.IModel.IModel_GETBY;
import com.example.deng.myapplication2.Manager.RetrofitManager;
import com.example.deng.myapplication2.Util.CheckFolders;
import com.example.deng.myapplication2.Util.DataBaseUtil2;
import com.example.deng.myapplication2.Util.FileUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class Model_GETBY implements IModel_GETBY {
    String[] query = {"","A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    List<String> list = Arrays.asList(query);
    ArrayList<String> str2 = new ArrayList<String>();
    Model_GETBY.OnFinish onFinish;
    Context context;
    int i=0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    boolean initdata=false;
    ArrayList<String> data=new ArrayList<String>();
    ArrayList<ArrayList<TrainByBean>> datanum=new ArrayList<ArrayList<TrainByBean>>();
    ArrayList<ArrayList<TrainByBean>> datalist=new ArrayList<ArrayList<TrainByBean>>();
    ArrayList<TrainByBean> trainByBeans=new ArrayList<TrainByBean>();
    public Model_GETBY(Model_GETBY.OnFinish onFinish, Context context){
        this.onFinish=onFinish;
        this.context=context;
    }
    public void trainby(final RecyclerView rcv1, final String leave, final String arrive){
            Observable.fromIterable(list).concatMapIterable(new Function<String, Iterable<ArrayList<TrainByBean>>>() {
                @Override
                public Iterable<ArrayList<TrainByBean>> apply(String s) throws Exception {
                    ApiService2 apiService2 = RetrofitManager.getInstance(ApiName.Path2, context).setCreate(ApiService2.class);
                    DataBase2 dataBase2 = new DataBase2(context, "stationmessage.db", null, 1);
                    String leaveno = DataBaseUtil2.selectInvstation(dataBase2, leave);
                    String arriveno = DataBaseUtil2.selectInvstation(dataBase2, arrive);
                    Observable<ResponseBody> getBy = apiService2.getTrainByMessage("query" + s, "2020-03-18", leaveno, arriveno, "ADULT");
                    getBy.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResponseBody>() {

                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(ResponseBody responseBody) {
                            String a = CheckFolders.getSDPath();
                            FileUtil.writeBig(a + "/getbytrainmessage.txt", responseBody.byteStream(),false);
                            try {
                                data = FileUtil.readToJson2(a + "/getbytrainmessage.txt");
                                if (data.size() > 0) {
                                    for (int i = 0; i < data.size(); i++) {
                                        String[] arrays = data.get(i).split("\\|");
                                        trainByBeans.add(new TrainByBean("1", arrays[3], arrays[4], arrays[5], arrays[6], arrays[7], arrays[8], arrays[9], "2"));
                                    }
                                    datalist.add(trainByBeans);
                                    if (datalist.size() == 1) {
                                        onFinish.showTrainBys(trainByBeans, rcv1);
                                    }
                                }

                            } catch (Exception e) {

                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }

                    });
                    return datalist;
                }
            }).subscribe(new Consumer<ArrayList<TrainByBean>>() {
                @Override
                public void accept(ArrayList<TrainByBean> trainByBeans) throws Exception {

                }
            });
    }
public interface OnFinish{
    void showTrainBys(ArrayList<TrainByBean> trainByBeans,RecyclerView rcv1);
}
}