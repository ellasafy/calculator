package com.example.ellsasa.calculator;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.view.ContextThemeWrapper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class calculator extends Activity implements View.OnClickListener {

        private EditText number1EditText;
        private EditText number2EditText;
        private EditText number3EditText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);


//            toolbar.getBackground().setAlpha(0);
//            getWindow().requestFeature(Window.FEATURE_ACTION_BAR_OVERLAY);

            setContentView(R.layout.calculator_layout);

//google的actionbar是分为上下两栏显示的，上面的代码只能设置顶部actionbar的背景色，
//为了让下面的背景色一致，还需要添加一行代码：
//            actionBar.setSplitBackgroundDrawable(new ColorDrawable(Color.parseColor("#33000000")));
            gatherControls();


            setUpButtons();

            Button button = (Button) this.findViewById(R.id.next);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setClass(calculator.this, contTent.class);
                    startActivity(intent);
                }
            });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_calculator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_1:
                dialog("menu1");
                return true;
            case R.id.menu_2:
                dialog("menu2");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    protected void dialog(String menu) {
        AlertDialog.Builder builder = new AlertDialog.Builder(
                new ContextThemeWrapper(this, R.style.AlertDialogCustom));
         builder.setMessage("menu " + menu);
         builder.setTitle("提示");
          builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
               @Override
              public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();


                  }
          });
         builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
               @Override
              public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                  }
         });
         builder.create().show();
        }

      private void gatherControls() {
          number1EditText = (EditText) this.findViewById(R.id.editText1);
          number2EditText = (EditText) this.findViewById(R.id.editText2);
          number3EditText = (EditText) this.findViewById(R.id.editText3);


          number2EditText.requestFocus();
      }

      private void setUpButtons() {
          Button b = (Button) this.findViewById(R.id.plusButton);
          b.setOnClickListener(this);

          b = (Button) this.findViewById(R.id.minusButton);
          b.setOnClickListener(this);

          b = (Button) this.findViewById(R.id.multiplyButton);
          b.setOnClickListener(this);

          b = (Button) this.findViewById(R.id.divideButton);
          b.setOnClickListener(this);

          b = (Button) this.findViewById(R.id.next);
          b.setOnClickListener(this);
      }


    public void onClick(View v) {
        String sNum1 = number1EditText.getText().toString();
        String sNum2 = number2EditText.getText().toString();

        if (sNum1 == null || sNum2 == null
                || sNum1.trim().equalsIgnoreCase("")
                ||sNum2.trim().equalsIgnoreCase(""))
        {
            return;
        }


        double num1 = getDouble(sNum1);
        double num2 = getDouble(sNum2);
        Button b = (Button)v;

        double value = 0;

        if (b.getId() == R.id.plusButton) {
            value = plus(num1, num2);
        } else if (b.getId() == R.id.minusButton) {
            value = minus(num1, num2);
        } else if (b.getId() == R.id.multiplyButton) {
            value = multiply(num1, num2);
        } else if (b.getId() == R.id.divideButton) {
            value = divide(num1, num2);
        }

        number3EditText.setText(Double.toString(value));
    }

    private double plus(double n1, double n2) {
        return n1 + n2;
    }
    private double minus(double n1, double n2) {
        return n1 - n2;
    }
    private double multiply(double n1, double n2) {
        return n1 * n2;
    }
    private double divide(double n1, double n2) {
        if (n2 == 0) {
            return 0;
        }
        return n1/n2;
    }

    private double getDouble(String s) {
        if (validString(s)) {
            return Double.parseDouble(s);
        }
        return 0;
    }
    private boolean invalidString(String s) {
        return !validString(s);
    }

    private boolean validString(String s) {
        if (s == null) {
            return false;
        }
        if (s.trim().equalsIgnoreCase("")) {
            return false;
        }
        return true;
    }
}
