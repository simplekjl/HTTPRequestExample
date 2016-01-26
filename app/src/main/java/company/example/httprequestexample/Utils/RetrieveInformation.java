package company.example.httprequestexample.Utils;

import android.os.AsyncTask;

import org.xml.sax.InputSource;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by admin on 1/26/2016.
 */
public class RetrieveInformation extends AsyncTask<String, Void, String> {

    private Exception exception;
    public Reference mref;

    protected String doInBackground(String... urls) {

        //http://stackoverflow.com/questions/15719942/get-json-in-asynctask-android
        String msg ="";
        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            msg = readStream(in);
            urlConnection.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return msg;
    }

    protected void onPostExecute(String feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
        mref.ReferenceActivity(feed);
    }

    private String readStream(InputStream is) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = is.read();
            while(i != -1) {
                bo.write(i);
                i = is.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "there is an error";
        }
    }
}