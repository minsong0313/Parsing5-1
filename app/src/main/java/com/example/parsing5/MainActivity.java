package com.example.parsing5;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.view.View;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends Activity {
    EditText edit;
    TextView text;
    Bitmap bm;
    XmlPullParser xpp;
    String key = "gyhnkvw8BuHNtPGQzXT5Nluh3Ri3hGlcpEnheMdjI1gjDbZhPSEpy05ofIMaFu2a96c%2FUX%2FzOVblYrTa%2B%2Fu%2Bjg%3D%3D"; //약국 공공데이터 서비스키


    String data;
    //////////////////////////////////////////////////////////////
    Bitmap bmImg;
    ImageView imageView;
    String imag; //검색한 약의 이미지 주소

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.result);


        imageView = (ImageView) findViewById(R.id.imview);

    }


    //버튼 클릭시 호출되는 callback 메서드
    public void mOnClick(View v) {
        switch (v.getId()) {
            case R.id.button:

                //Android 4.0 이상 부터는 네트워크를 이용할 때 반드시 Thread 사용해야 함
                new Thread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        data = getXmlData();//아래 메소드를 호출하여 XML data를 파싱해서 String 객체로 얻어오기


                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // TODO Auto-generated method stub
                                text.setText(data); //TextView에 문자열  data 출력
                                imageView.setImageBitmap(bm);

                            }

                        });
                    }

                }).start();
                break;
        }
    }



    private String getXmlData() {
        StringBuffer buffer = new StringBuffer();

        String str = edit.getText().toString();//EditText에 작성된 Text얻어오기
        String location = null;
        try {
            location = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //    String query = "bDT10I5VhSNuQu1J_zZv";// 네이버 검색

        String queryUrl = "http://apis.data.go.kr/1470000/MdcinGrnIdntfcInfoService/getMdcinGrnIdntfcInfoList?ServiceKey="//요청 URL
                + key + "&item_name=" + location; //동 이름으로 검색

        try {
            URL url = new URL(queryUrl);
            InputStream is = url.openStream(); //url위치로 입력스트림 연결

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new InputStreamReader(is, "UTF-8")); //inputstream 으로부터 xml 입력받기

            String tag;

            xpp.next();
            int eventType = xpp.getEventType();

                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                switch (eventType) {
                                    case XmlPullParser.START_DOCUMENT:
                                        buffer.append("파싱 시작...\n\n");
                                        break;

                                    case XmlPullParser.START_TAG:
                                        tag = xpp.getName();//테그 이름 얻어오기

                                        if (tag.equals("CHART")) ;// 첫번째 태그값이랑 비교
                                        else if (tag.equals("addr")) {
                                            buffer.append("주소 : ");
                                            xpp.next();
                                            buffer.append(xpp.getText());//title 요소의 TEXT 읽어오기
                                            buffer.append("\n"); //줄바꿈
                                        } else if (tag.equals("ITEM_IMAGE")) {
                                            buffer.append("약 사진 주소 :");
                                            xpp.next();
                                            imag=xpp.getText();
                                            try {
                                                URL url1 = new URL(imag);
                                                URLConnection conn = url1.openConnection();
                                                conn.connect();
                                                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                                                bm = BitmapFactory.decodeStream(bis);
                                                bis.close();
                                            } catch (Exception e) {
                                            }
                                            buffer.append(xpp.getText());

                                            buffer.append("\n");
                                        } else if (tag.equals("PRINT_BACK")) {
                                            buffer.append("전화번호 :");
                                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        tag = xpp.getName(); //테그 이름 얻어오기

                        if (tag.equals("item")) buffer.append("\n");// 첫번째 검색결과종료 후 줄바꿈
                        break;
                }

                eventType = xpp.next();
            }

        } catch (Exception e) {
            // TODO Auto-generated catch blocke.printStackTrace();
        }
        
        return buffer.toString();//StringBuffer 문자열 객체 반환

    }


}
