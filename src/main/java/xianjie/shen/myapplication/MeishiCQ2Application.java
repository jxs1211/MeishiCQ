package xianjie.shen.myapplication;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.umeng.socialize.PlatformConfig;

public class MeishiCQ2Application extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    //各个平台的配置，建议放在全局Application或者程序入口
    {
        //微信    AppID：wxf6f97c780f4e8f15,AppSecret：6e7f5f4501d630294db90ed4942c0cf9
        PlatformConfig.setWeixin("wxf6f97c780f4e8f15", "6e7f5f4501d630294db90ed4942c0cf9");
        //豆瓣RENREN平台目前只能在服务器端配置
        //新浪微博
        PlatformConfig.setSinaWeibo("4084397810", "bced117876ba6e46d76ef48a91b78331");
        //易信
        PlatformConfig.setYixin("yxc0614e80c9304c11b0391514d09f13bf");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setTwitter("3aIN7fuF685MuZ7jtXkQxalyi", "MK6FEYG63eWcpDFgRYw4w9puJhzDl0tyuqWjZ3M7XJuuG7mMbO");
        PlatformConfig.setAlipay("2015111700822536");
        PlatformConfig.setLaiwang("laiwangd497e70d4", "d497e70d4c3e4efeab1381476bac4c5e");
        PlatformConfig.setPinterest("1439206");
    }

    @Override
    protected void attachBaseContext(Context base)
    {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}

