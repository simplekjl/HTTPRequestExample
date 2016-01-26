package company.example.httprequestexample.Utils;

import android.os.AsyncTask;

import org.xml.sax.InputSource;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            msg = readStream(urlConnection.getInputStream());
            //InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            //msg = readStream(in);
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
        StringBuilder sb = new StringBuilder();
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String nextLine= "";
            while((nextLine = reader.readLine() ) != null){
                sb.append(nextLine);
            }
        }catch(IOException ex){

        }
        return sb.toString();
    }
}