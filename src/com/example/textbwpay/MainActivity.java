package com.example.textbwpay;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bw30.pay.BWPay;
import com.bw30.pay.BWPayService;
import com.bw30.pay.utils.DexUtils.PayCallBack;
import com.umeng.analytics.game.UMGameAgent;


public class MainActivity extends Activity {
    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        BWPay.initBWpay(this);
//         BWPayService.initBWpay(this, 186, 1124, "org.cocos2dx.sfpopgame",null);//喜洋洋消消乐
         BWPayService.initBWpay(this, 186, 1124, "org.cocos2dx.sfpopgame","+8615858260341");//星星
         
//         BWPayService.initBWpay(this, 196, 1092);
         BWPay.setDebug(true);
        UMGameAgent.init( this );
        setContentView(R.layout.activity_main);
        et = (EditText) findViewById(R.id.editText1);
    }

    public void pay(View v) {
        BWPay.pay(this, 1, "味儿", Integer.parseInt(et.getText().toString().trim()) * 100, "刀", 10,
                new PayCallBack() {
                    @Override
                    public void onProgress(int arg0, int arg1) {

                    }

                    @Override
                    public void onFail() {
                        Toast.makeText(MainActivity.this, "支付失败", 2000).show();
                    }

                    @Override
                    public void onDone() {
                        Toast.makeText(MainActivity.this, "支付成功", 2000).show();
                        UMGameAgent.pay(10,"magic_bottle",2,50,2);
                        UMGameAgent.buy("helmet", 1, 1000);
                        UMGameAgent.use("magic_bottle", 2 , 50);
                    }

                    @Override
                    public void onCancle() {
                        Toast.makeText(MainActivity.this, "支付取消", 2000).show();

                    }
                }, "bw");
    }

    @Override
    protected void onDestroy() {
        BWPay.destroyBWPay();
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        UMGameAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        UMGameAgent.onPause(this);
    }
}
