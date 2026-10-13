package com.apextools.app;

import android.app.*;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    final int bg=Color.rgb(5,10,24), panel=Color.rgb(13,24,48), cyan=Color.rgb(0,220,255), white=Color.WHITE;
    LinearLayout root, content;
    String[] tools={
      "Device Settings","Display Settings","Sound Settings","Bluetooth Settings","Wi-Fi Settings","Battery Settings","Storage Settings","App Settings","Accessibility Settings","Date & Time",
      "Calculator","Calendar","Clock","Alarm","Stopwatch","Timer","Flashlight Help","QR Scanner Shortcut","Voice Recorder","Notes",
      "Network Guide","Internet Speed Guide","IP Address Help","DNS Guide","VPN Settings","Data Usage","Hotspot Settings","Airplane Mode","SIM Settings","Mobile Network",
      "Battery Tips","Power Saving","Screen Brightness","Dark Mode","Font Size","Screen Timeout","Wallpaper Settings","Ringtone Settings","Notification Settings","Do Not Disturb",
      "App Permissions","Privacy Settings","Security Settings","Password Tips","Safe Browsing","Find My Device","Backup Settings","Google Settings","Account Settings","Play Store",
      "Storage Cleanup Guide","Large Files Guide","Downloads","Files App","Photos","Camera","Screenshot Tips","Share Files","Nearby Share","USB Settings",
      "Focus Timer","Study Planner","To-Do List","Habit Tracker","Pomodoro Guide","Text Tools","Word Counter","Unit Converter","Weather Shortcut","Maps Shortcut",
      "Emergency Info","First Aid Guide","Cyber Safety","Scam Awareness","Device Info","Developer Options Guide","System Update","Accessibility Help","Feedback","About APEX"
    };
    @Override public void onCreate(Bundle b){super.onCreate(b); showLogin();}
    GradientDrawable shape(int color,int stroke){GradientDrawable d=new GradientDrawable();d.setColor(color);d.setCornerRadius(24);if(stroke>0)d.setStroke(2,stroke);return d;}
    TextView text(String s,int size,int color){TextView t=new TextView(this);t.setText(s);t.setTextSize(size);t.setTextColor(color);t.setGravity(Gravity.CENTER);return t;}
    void base(){root=new LinearLayout(this);root.setOrientation(1);root.setPadding(22,22,22,22);root.setBackground(new GradientDrawable(GradientDrawable.Orientation.TL_BR,new int[]{Color.rgb(4,12,32),Color.rgb(10,24,50),Color.rgb(2,6,18)}));setContentView(root);}
    Button button(String s){Button b=new Button(this);b.setText(s);b.setTextColor(white);b.setAllCaps(false);b.setBackground(shape(Color.rgb(0,100,190),cyan));return b;}
    void showLogin(){
      base(); root.setGravity(Gravity.CENTER);
      TextView logo=text("✦",62,cyan);logo.setTypeface(null,Typeface.BOLD);root.addView(logo);
      TextView title=text("APEX TOOLS",30,white);title.setTypeface(null,Typeface.BOLD);root.addView(title);
      TextView sub=text("YOUR DEVICE. YOUR CONTROL.",12,cyan);root.addView(sub);
      EditText email=new EditText(this);email.setSingleLine();email.setHint("Email address");email.setTextColor(white);email.setHintTextColor(0xff9aabc7);email.setBackground(shape(panel,cyan));email.setPadding(18,8,18,8);root.addView(email,new LinearLayout.LayoutParams(-1,58));
      EditText pass=new EditText(this);pass.setSingleLine();pass.setHint("Password (demo screen)");pass.setInputType(129);pass.setTextColor(white);pass.setHintTextColor(0xff9aabc7);pass.setBackground(shape(panel,cyan));pass.setPadding(18,8,18,8);LinearLayout.LayoutParams pp=new LinearLayout.LayoutParams(-1,58);pp.topMargin=12;root.addView(pass,pp);
      Button sign=button("ENTER APEX TOOLS");LinearLayout.LayoutParams sp=new LinearLayout.LayoutParams(-1,58);sp.topMargin=20;root.addView(sign,sp);
      TextView note=text("Login UI preview • Firebase authentication not connected yet",12,0xffb6c7e5);root.addView(note);
      sign.setOnClickListener(v->{if(email.getText().toString().trim().isEmpty()){email.setError("Enter an email to continue");return;} showDashboard();});
      TextView skip=text("Continue to tools preview",14,cyan);root.addView(skip);skip.setOnClickListener(v->showDashboard());
    }
    void showDashboard(){
      base(); LinearLayout header=new LinearLayout(this);header.setGravity(Gravity.CENTER_VERTICAL);header.setOrientation(1);
      TextView title=text("✦ APEX TOOLS",25,cyan);title.setTypeface(null,Typeface.BOLD);header.addView(title);
      TextView sub=text("80 QUICK TOOLS  •  CYBER BLUE",12,white);header.addView(sub);root.addView(header);
      EditText search=new EditText(this);search.setSingleLine();search.setHint("Search tools…");search.setTextColor(white);search.setHintTextColor(0xff9aabc7);search.setBackground(shape(panel,cyan));search.setPadding(16,0,16,0);LinearLayout.LayoutParams se=new LinearLayout.LayoutParams(-1,52);se.topMargin=14;root.addView(search,se);
      ScrollView scroll=new ScrollView(this);content=new LinearLayout(this);content.setOrientation(1);content.setPadding(0,10,0,20);scroll.addView(content);root.addView(scroll,new LinearLayout.LayoutParams(-1,0,1));
      Runnable fill=()->{content.removeAllViews();String q=search.getText().toString().toLowerCase(Locale.ROOT);for(String name:tools){if(!name.toLowerCase(Locale.ROOT).contains(q))continue;TextView item=text("◈   "+name+"     ›",16,white);item.setGravity(Gravity.CENTER_VERTICAL);item.setPadding(18,0,12,0);item.setBackground(shape(panel,0xff164b80));LinearLayout.LayoutParams ip=new LinearLayout.LayoutParams(-1,54);ip.bottomMargin=8;content.addView(item,ip);item.setOnClickListener(v->openTool(name));}};
      fill.run();search.addTextChangedListener(new android.text.TextWatcher(){public void beforeTextChanged(CharSequence s,int st,int c,int a){}public void onTextChanged(CharSequence s,int st,int before,int count){fill.run();}public void afterTextChanged(android.text.Editable e){}});
      Button back=button("SIGN OUT / BACK");root.addView(back);back.setOnClickListener(v->showLogin());
    }
    void openTool(String name){
      if(name.equals("Device Settings")){try{startActivity(new android.content.Intent(android.provider.Settings.ACTION_SETTINGS));return;}catch(Exception ignored){}}
      if(name.equals("Wi-Fi Settings")){try{startActivity(new android.content.Intent(android.provider.Settings.ACTION_WIFI_SETTINGS));return;}catch(Exception ignored){}}
      if(name.equals("Bluetooth Settings")){try{startActivity(new android.content.Intent(android.provider.Settings.ACTION_BLUETOOTH_SETTINGS));return;}catch(Exception ignored){}}
      if(name.equals("Battery Settings")){try{startActivity(new android.content.Intent(android.provider.Settings.ACTION_BATTERY_SAVER_SETTINGS));return;}catch(Exception ignored){}}
      new AlertDialog.Builder(this).setTitle(name).setMessage("This starter version provides a shortcut or guidance entry. Some functions require Android permissions, extra implementation, or system access and are not active yet.").setPositiveButton("OK",null).show();
    }
}
