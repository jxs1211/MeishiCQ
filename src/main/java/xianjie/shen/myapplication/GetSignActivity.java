package xianjie.shen.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import im.yixin.algorithm.MD5;

public class GetSignActivity extends Activity
{
    TextView tv1;
    Button btn1;
    Button back;
    EditText edt1;
    EditText result;
    private String pkgName;
    PackageInfo info;

    protected void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.get_signature_ctivity);

        initViews();
        initDatas();
        initEvents();

    }

    private void initViews()
    {
        edt1 = (EditText) findViewById(R.id.edt1);
        result = (EditText) findViewById(R.id.edt2);
        tv1 = (TextView) findViewById(R.id.tv1);
        btn1 = (Button) findViewById(R.id.btn1);
        back = (Button) findViewById(R.id.back);
    }

    private void initDatas()
    {
        edt1.setText("");
        edt1.setText(this.getPackageName().toString());
    }

    private void initEvents()
    {
        btn1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View paramAnonymousView)
            {
                GetSignActivity.this.tv1.setText("");
                pkgName = GetSignActivity.this.edt1.getText().toString();
                if ((pkgName != null) && (pkgName.length() > 0))
                {
                    GetSignActivity.this.getSign(pkgName);
                    Log.d("shen", pkgName);
                }
            }
        });
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                GetSignActivity.this.finish();
            }
        });
//        showDialog();
    }

    private void getSign(String pkgName)
    {
        Signature[] signatures = getRawSignature(this, pkgName);
        if ((pkgName == null) || (signatures.toString().length() == 0))
        {
            errout("signs is null");
            return;
        }
        for (int i = 0; i < signatures.toString().length() - 1; i++)
        {
            stdout(MD5.getMessageDigest(signatures[i].toByteArray()));
        }
//        while (true)
//        {
//            return;
//            int j = signatures.toString().length();
//            int i = 0;
//            while (i < j)
//            {
//                stdout(MD5.getMessageDigest(signatures[i].toByteArray()));
//                i += 1;
//            }
//        }
    }


    private Signature[] getRawSignature(Context paramContext, String paramString)
    {
        if ((paramString == null) || (paramString.length() == 0))
        {
            errout("getSignature, packageName is null");
            return null;
        }
        try
        {
            info = getApplicationContext().getPackageManager().getPackageInfo(paramString, PackageManager.GET_SIGNATURES);
            if (info == null)
            {
                errout("info is null, packageName = " + paramString);
                return null;
            }
        } catch (PackageManager.NameNotFoundException e)
        {
            errout("NameNotFoundException");
            return null;
        }
        return info.signatures;
    }

    private void errout(String paramString)
    {
        this.tv1.append(paramString + "\n");
    }

    private void stdout(String paramString)
    {
        this.result.append(paramString + "\n");
    }

    private void showDialog()
    {
        AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
        localBuilder.setCancelable(false);
        localBuilder.setTitle("alert_title").setMessage("alert_msg");
        localBuilder.setPositiveButton("ok", null);
        localBuilder.setNegativeButton("cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
//                GetSignActivity.this.finish();
            }
        });
        localBuilder.show();
    }
}
