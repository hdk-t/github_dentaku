package com.example.dentaku;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText display;
    Button equal;
    String str;//演算子判定用（四則演算）
    String str2;//演算子判定用（演算子→数字の順で押したときに、数字を消す時の判断）
    String str3;//整数、小数を文字列で判定するために数値を文字列で代入する用
    String str4;//小数点をつけるかの判定用
    String str5;//小数を文字列で判定するための数値を文字列で代入する用
    String str6;//イコールを押したか否かの判定
    String str7;//＋－判定用
    String str8;//パーセント代入用
    String str9;//C、AC判定用
    int a;
    int b;
    int c;
    int aa;
    int bb;
    int cc;
    double d;
    double e;
    double f;
    double dd;
    double ee;

    View.OnClickListener buttonListener = new View.OnClickListener() {
        //↑ボタンを押したときに処理するインスタンス化されたクラス。どのボタンにも適用可能で今は下文でequalに適用されてる。
        @Override
        public void onClick(View v) {
            display.setText(display.getText().toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.display);
        equal = findViewById(R.id.equal);
        equal.setOnClickListener(buttonListener);

        //↓↓小数点ボタン
        findViewById(R.id.DP).setOnClickListener(buttonNumberListener2);
        //↓↓数字ボタン
        findViewById(R.id.zero).setOnClickListener(buttonNumberListener);
        findViewById(R.id.one).setOnClickListener(buttonNumberListener);
        findViewById(R.id.two).setOnClickListener(buttonNumberListener);
        findViewById(R.id.three).setOnClickListener(buttonNumberListener);
        findViewById(R.id.four).setOnClickListener(buttonNumberListener);
        findViewById(R.id.five).setOnClickListener(buttonNumberListener);
        findViewById(R.id.six).setOnClickListener(buttonNumberListener);
        findViewById(R.id.seven).setOnClickListener(buttonNumberListener);
        findViewById(R.id.eight).setOnClickListener(buttonNumberListener);
        findViewById(R.id.nine).setOnClickListener(buttonNumberListener);
        //↓↓演算子ボタン
        findViewById(R.id.plus).setOnClickListener(buttonOperator);
        findViewById(R.id.minus).setOnClickListener(buttonOperator);
        findViewById(R.id.multiply).setOnClickListener(buttonOperator);
        findViewById(R.id.divide).setOnClickListener(buttonOperator);
        //↓↓イコールボタン
        findViewById(R.id.equal).setOnClickListener(buttonEqual);
        //↓↓クリアボタン
        findViewById(R.id.clear).setOnClickListener(buttonAc);
        //↓↓±ボタン
        findViewById(R.id.PandN).setOnClickListener(buttonPuramai);
        //パーセントボタン
        findViewById(R.id.percent).setOnClickListener(buttonPercent);
    }

    public void ac(){         //すべてリセットするメソッド
        display.setText("");
        str = null;
        str2 = null;
        str3 = null;
        str4 = null;
        str5 = null;
        str6 = null;
        str7 = null;
        str8 = null;
        str9 = ("AC");
        a = 0;
        b = 0;
        c = 0;
        d = 0;
        e = 0;
        f = 0;
        aa = 0;
        bb = 0;
        cc = 0;
        dd = 0;
        ee = 0;
    }

    public void calculate (){  //一項目、２項目をstrに代入された演算子を読み取って計算するメソッド
        if (e == 0) {
            e = Double.parseDouble(display.getText().toString());
        }
        switch (str) {
            case ("＋"):
                f = d + e;
                break;
            case ("－"):
                f = d - e;
                break;
            case ("×"):
                f = d * e;
                break;
            case ("÷"):
                f = d / e;
                break;
        }
        //文字列から.0を読み取り整数にできるか判定
        a = (int) f;
        str3 = String.valueOf(f);
        b = str3.length() - 1;
        c = str3.length() - 2;

        char c1 = str3.charAt(b);
        char c2 = str3.charAt(c);
        if (c1 == '0' && c2 == '.') {
            display.setText(String.valueOf(a));
        } else {
            display.setText(String.valueOf(f));
        }
        d = Double.parseDouble(display.getText().toString());
        str7 = null;
    }

    public void c(){ //ディスプレイに何か表示されているならばACボタンにCを何も表示されていないならACを
        Button button1 = findViewById(R.id.clear);
        str5 = display.getText().toString();
        if(str5.equals("") || str6 != null) {
            button1.setText("AC");
        }else if(!(str5.equals(""))) {
            button1.setText("C");
        }
    }

    View.OnClickListener buttonNumberListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (str6 != null) {
                display.setText("");
                ac();
            }
            str5 = display.getText().toString();
            if (str4 == null && !(str5.equals(""))) {
                str4 = ("dp");
                display.append(".");
            } else {
                //すでに小数点がdisplayに存在または最初に入力するならば小数点は打てなくなる
            }
            c();
        }
    };

    View.OnClickListener buttonNumberListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Button number = (Button) v;
            if (str6 != null) {
               ac();
            } else if (str2 != null) {
                        str2 = null;
                        if (str7 != null) {
                            display.setText("-");
                        } else {
                            display.setText("");
                        }
            }
            if(str8 != null) {
                ac();
            }
            display.append(number.getText());
            c();
        }
    };

    View.OnClickListener buttonOperator = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str4 = null;
            try {
            if (str6 == null) {
                if (d != 0) {
                    str6 = "判定用1";
                    c();
                    calculate();
                    }
                }
                Button operator = (Button) v;
                str = operator.getText().toString();
                str2 = operator.getText().toString();
                str6 = null;
                str7 = null;
                e = 0;
                d = Double.parseDouble(display.getText().toString());
            } catch(NumberFormatException g){
                //値を入れずに演算子ボタンを押されることによるエラーの防止
            }
        }
    };

    View.OnClickListener buttonEqual = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            str4 = null;
            str6 = "判定用1";
            c();
            if (d == 0 && str == null) {
                //値を一項目のみに入力してイコールを押されることによるエラーの防止
            } else {
                try {
                    calculate();
                } catch (NumberFormatException g) {
                    //値を入力せずにイコールを押されることによるエラーの防止
                }
            }
        }
    };

    View.OnClickListener buttonAc = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Button button1 = findViewById(R.id.clear);
            str9 = button1.getText().toString();
            if(str9.equals("AC")) {
                ac();
            }else if(str9.equals("C")){
                display.setText("");
           }
            c();
       }
    };

    View.OnClickListener buttonPuramai = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (str6 != null) {
                display.setText("");
                ac();
            }
            if (str2 != null) {
                display.setText("");
            }
            if (str7 == null) {
                String str = display.getText().toString();
                String str2 = ("-" + str);
                display.setText(str2);
                str7 = ("-");
            } else {
                String str = display.getText().toString();
                String str2 = str.replaceAll("-", "");
                display.setText(str2);
                str7 = null;
            }
            c();
        }
    };

    View.OnClickListener buttonPercent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try {
                str8 = "判定用2";
                dd = Double.parseDouble(display.getText().toString());
                ee = dd / 100;
                aa = (int) dd;
                String str = String.valueOf(ee);
                bb = str.length() - 1;
                cc = str.length() - 2;

                char c1 = str.charAt(bb);
                char c2 = str.charAt(cc);
                if (c1 == '0' && c2 == '.') {
                    display.setText(String.valueOf(aa));
                } else {
                    display.setText(String.valueOf(ee));
                }
            }catch(NumberFormatException h){

            }
            c();
        }
    };
}
