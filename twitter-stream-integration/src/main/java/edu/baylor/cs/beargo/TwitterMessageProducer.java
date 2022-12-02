package edu.baylor.cs.beargo;

import java.util.List;

import edu.baylor.cs.beargo.Model.TwitterModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.util.CollectionUtils;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;

//@Slf4j
@Slf4j



public class TwitterMessageProducer extends MessageProducerSupport {

    private final TwitterStream twitterStream;

//    @Autowired
//    TwitterService twitterService;

    @Autowired
    //private KafkaTemplate<String, String> tTemplate;
    private KafkaTemplate<String, TwitterModel> tTemplate;



    private List<Long> follows;
    private List<String> terms;

    private StatusListener statusListener;
    private FilterQuery filterQuery;

    public TwitterMessageProducer(TwitterStream twitterStream, MessageChannel outputChannel) {
        this.twitterStream = twitterStream;
        setOutputChannel(outputChannel);
    }

    @Override
    protected void onInit() {
        super.onInit();

        statusListener = new StatusListener();

        long[] followsArray = null;

        if (!CollectionUtils.isEmpty(follows)) {
            followsArray = new long[follows.size()];
            for (int i = 0; i < follows.size(); i++) {
                followsArray[i] = follows.get(i);
            }
        }

        String[] termsArray = null;
        if (!CollectionUtils.isEmpty(terms)) {
            termsArray = terms.toArray(new String[0]);
        }

        filterQuery = new FilterQuery(0, followsArray, termsArray);
    }

    @Override
    public void doStart() {
        twitterStream.addListener(statusListener);
        twitterStream.filter(filterQuery);

    }

    @Override
    public void doStop() {
        twitterStream.cleanUp();
        twitterStream.clearListeners();
    }

    public void setFollows(List<Long> follows) {
        this.follows = follows;
    }

    public void setTerms(List<String> terms) {
        this.terms = terms;
    }

    StatusListener getStatusListener() {
        return statusListener;
    }

    FilterQuery getFilterQuery() {
        return filterQuery;
    }

    class StatusListener extends StatusAdapter {

        @Override
        public void onStatus(Status status) {
            TwitterModel tModel = new TwitterModel();

            Long tid = status.getId();
            String username = status.getUser().getScreenName();
            String textdata = status.getText();



            tModel.setTid(tid);
            tModel.setTUsername(username);
            tModel.setTText(textdata);



            //twitterService.sendTweetKafka(tModel);

            String topicName = "tweet";
            tTemplate.send(topicName,tModel);


            log.info("Sent to kafka");
            log.info(tid.toString());
            log.info(username);
            log.info(textdata);

            //
            // sendMessage(MessageBuilder.withPayload(status).build());
        }

        @Override
        public void onException(Exception ex) {
            log.error(ex.getMessage(), ex);
        }

        @Override
        public void onStallWarning(StallWarning warning) {
            log.warn(warning.toString());
        }

    }

}
