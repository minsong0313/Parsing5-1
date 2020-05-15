package com.example.parsing5;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.EditText;
import android.widget.TextView;
import android.app.Activity;
import android.view.View;


import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends Activity {
    EditText edit;
    TextView text;

    XmlPullParser xpp;
    String key = "gyhnkvw8BuHNtPGQzXT5Nluh3Ri3hGlcpEnheMdjI1gjDbZhPSEpy05ofIMaFu2a96c%2FUX%2FzOVblYrTa%2B%2Fu%2Bjg%3D%3D"; //약국 공공데이터 서비스키


    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit = (EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.result);

        /*
        StrictMode.enableDefaults();

        TextView pharm1 = (TextView) findViewById(R.id.result); //파싱된 결과확인!

        boolean initem = false, indutyAddr = false, indutyInf = false, indutyName = false, indutyTel1 = false;
        boolean inwgs84Lat = false, inwgs84Lon = false;

        String dutyAddr = null, dutyInf = null, dutyName = null, dutyTel1 = null, wgs84Lat = null, wgs84Lon = null;

        try {
            URL url = new URL("http://apis.data.go.kr/B552657/ErmctInsttInfoInqireService/getParmacyFullDown?serviceKey="
                    + "gyhnkvw8BuHNtPGQzXT5Nluh3Ri3hGlcpEnheMdjI1gjDbZhPSEpy05ofIMaFu2a96c%2FUX%2FzOVblYrTa%2B%2Fu%2Bjg%3D%3D"
                    + "&pageNo=1&numOfRows=10&"
            ); //검색 URL부분

            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT) {
                switch (parserEvent) {
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if (parser.getName().equals("dutyAddr")) { //title 만나면 내용을 받을수 있게 하자
                            indutyAddr = true;
                        }
                        if (parser.getName().equals("dutyInf")) { //address 만나면 내용을 받을수 있게 하자
                            indutyInf = true;
                        }
                        if (parser.getName().equals("dutyName")) { //mapx 만나면 내용을 받을수 있게 하자
                            indutyName = true;
                        }
                        if (parser.getName().equals("dutyTel1")) { //mapy 만나면 내용을 받을수 있게 하자
                            indutyTel1 = true;
                        }
                        if (parser.getName().equals("wgs84Lat")) { //mapy 만나면 내용을 받을수 있게 하자
                            inwgs84Lat = true;
                        }
                        if (parser.getName().equals("wgs84Lon")) { //mapy 만나면 내용을 받을수 있게 하자
                            inwgs84Lon = true;
                        }
                        if (parser.getName().equals("message")) { //message 태그를 만나면 에러 출력
                            pharm1.setText(pharm1.getText() + "에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if (indutyAddr) { //isTitle이 true일 때 태그의 내용을 저장.
                            dutyAddr = parser.getText();
                            indutyAddr = false;
                        }
                        if (indutyInf) { //isAddress이 true일 때 태그의 내용을 저장.
                            dutyInf = parser.getText();
                            indutyInf = false;
                        }
                        if (indutyName) { //isMapx이 true일 때 태그의 내용을 저장.
                            dutyName = parser.getText();
                            indutyName = false;
                        }
                        if (indutyTel1) { //isMapy이 true일 때 태그의 내용을 저장.
                            dutyTel1 = parser.getText();
                            indutyTel1 = false;
                        }
                        if (inwgs84Lat) { //isMapy이 true일 때 태그의 내용을 저장.
                            wgs84Lat = parser.getText();
                            inwgs84Lat = false;
                        }
                        if (inwgs84Lon) { //isMapy이 true일 때 태그의 내용을 저장.
                            wgs84Lon = parser.getText();
                            inwgs84Lon = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            pharm1.setText(pharm1.getText() + "주소 : " + dutyAddr + "\n 운영 정보: " + dutyInf + "\n 약국명 : " + dutyName
                                    + "\n 전화번호 : " + dutyTel1 + "\n 위도 : " + wgs84Lat + "\n 경도 : " + wgs84Lon );
                            initem = false;
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch (Exception e) {
            pharm1.setText("에러가..났습니다...");
        }
        
         */

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

        String queryUrl = "http://apis.data.go.kr/B551182/pharmacyInfoService/getParmacyBasisList?serviceKey="//요청 URL
                + key + "&emdongNm=" + location; //동 이름으로 검색

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

                        if (tag.equals("item")) ;// 첫번째 태그값이랑 비교
                        else if (tag.equals("addr")) {
                            buffer.append("주소 : ");
                            xpp.next();
                            buffer.append(xpp.getText());//title 요소의 TEXT 읽어오기
                            buffer.append("\n"); //줄바꿈
                        } else if (tag.equals("YadmNm")) {
                            buffer.append("약국명 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("telno")) {
                            buffer.append("전화번호 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("XPos")) {
                            buffer.append("위도 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("\n");
                        } else if (tag.equals("YPos")) {
                            buffer.append("경도 :");
                            xpp.next();
                            buffer.append(xpp.getText());
                            buffer.append("  ,  ");
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

        buffer.append("파싱 끝\n");
        return buffer.toString();//StringBuffer 문자열 객체 반환

    }
}
