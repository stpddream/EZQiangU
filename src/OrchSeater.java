import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stpanda
 * Date: 11/26/13
 * Time: 4:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrchSeater {

    private static String LOGIN_URL = "https://tickets.philorch.org/api/Login.asmx/TessituraLogin";
    private static String LOGIN_2 = "https://tickets.philorch.org/account/myaccount.aspx";

    private CookieStore cookieStore;
    private RequestConfig globalRequestConf;


    public OrchSeater() {

        cookieStore = new BasicCookieStore();
        globalRequestConf = RequestConfig.custom()
                .setCookieSpec(CookieSpecs.BEST_MATCH)
                .build();


    }

    public boolean auth() throws IOException {

        HttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCookieStore(cookieStore)  //Cookies are ignored
                .build();


        HttpPost post = new HttpPost(LOGIN_URL);

        String jsonStr = "{\"userName\":\"tyang@haverford.edu\",\"password\":\"123456abc\",\"saveUserName\":false}";



        EntityBuilder builder = EntityBuilder.create();

        builder.setText(jsonStr)
               .setContentType(ContentType.APPLICATION_JSON);

        HttpEntity entity = builder.build();
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);

        parseResponse(response);


        HttpGet request = new HttpGet(LOGIN_2);

        response = httpClient.execute(request);

        parseResponse(response);


        return true;


    }




    private String parseResponse(HttpResponse response) throws IOException {

        BufferedReader br = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));

        String line;
        StringBuilder strBody = new StringBuilder();
        while((line = br.readLine()) != null) {
            strBody.append(line);
        }

        System.out.println(strBody.toString());
        /*
        log("**************************************************************");
        log(strBody.toString());
        log("**************************************************************");
          */

        return strBody.toString();

    }







}
