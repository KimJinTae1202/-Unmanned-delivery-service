package com.example.project;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RegisterTask extends AsyncTask<String, Void,String> {
    String sendMsg, receiveMsg;

    @Override
    protected String doInBackground(String... strings) {
        try {
            String str;

            // 접속할 서버 주소 (이클립스에서 android.jsp 실행시 웹브라우저 주소)
            URL url = new URL("http://192.168.0.43:8080/Project/androidDB.jsp");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            // 전송할 데이터. GET 방식으로 작성
            sendMsg = "id=" + strings[0] + "&pw=" + strings[1]+ "&name=" + strings[2]+ "&email=" + strings[3];

            osw.write(sendMsg);
            osw.flush();

            //jsp와 통신 성공 시 수행
            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();


                // jsp에서 보낸 값을 받는 부분
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();


                Log.d("msg", receiveMsg);

            } else {
                Log.d("http???", "failed!!!!!!!!");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //jsp로부터 받은 리턴 값
        return receiveMsg;
    }

}
