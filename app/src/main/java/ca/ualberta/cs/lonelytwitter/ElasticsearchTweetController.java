package ca.ualberta.cs.lonelytwitter;

import android.os.AsyncTask;
import android.util.Log;

import com.searchly.jestdroid.DroidClientConfig;
import com.searchly.jestdroid.JestClientFactory;
import com.searchly.jestdroid.JestDroidClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.searchbox.client.JestResult;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

/**
 * Created by romansky on 10/20/16.
 */
public class ElasticsearchTweetController {
    static JestDroidClient client  = null;

    public static void addTweets(Tweet tweet){
        setClient();
        Index index = new Index.Builder(tweet)
                .index("ajaypart-Thursday")
                .type("tweet")
                .build();
        try {
            DocumentResult result = client.execute(index);
            if (result.isSucceeded()) {
                tweet.setTweetID(result.getId());

            }
        }
        catch(IOException e){
            //do something here
        }
    }
    public static void setClient(){
        if (client == null){
            DroidClientConfig config = new DroidClientConfig
                    .Builder("http://cmput301.softwareprocess.es:8080/")
                    .build();
            JestClientFactory factory = new JestClientFactory();
            factory.setDroidClientConfig(config);
            client = (JestDroidClient) factory.getObject();
        }
    }

    public static class AddTweetTask extends AsyncTask<Tweet,Void,Void>{
        @Override
        protected Void doInBackground(Tweet... params){
            return null;
        }
    }
    public static class GetTweetsTask extends AsyncTask<String,Void,ArrayList<Tweet>>{
        @Override
        protected ArrayList<Tweet> doInBackground(String... params){
            setClient();
            ArrayList<Tweet> tweets = new ArrayList<Tweet>();
            Search search =  new Search.Builder(params[0])
                    .addIndex("ajaypart-new-test")
                    .addType("tweet")
                    .build();
            try {
                JestResult result = client.execute(search);

                if (result.isSucceeded()){
                    List<NormalTweet> tweetList;
                    tweetList = result.getSourceAsObjectList(NormalTweet.class);
                    tweets.addAll(tweetList);
                }
            } catch (IOException e ){}
        }
        protected Void doInBackground(Tweet... params){
            return null;
        }
    }
}