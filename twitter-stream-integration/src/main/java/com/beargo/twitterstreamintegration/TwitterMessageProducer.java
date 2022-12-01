package com.beargo.twitterstreamintegration;

import java.util.List;

import com.beargo.twitterstreamintegration.Model.TwitterModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.endpoint.MessageProducerSupport;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.CollectionUtils;
import twitter4j.FilterQuery;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusAdapter;
import twitter4j.TwitterStream;

@Slf4j
public class TwitterMessageProducer extends MessageProducerSupport {

    @Autowired
    private KafkaTemplate<String, TwitterModel> twitterKafkaTemplate;


    private final TwitterStream twitterStream;

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

            String tid = String.valueOf(status.getId());
            String username = status.getUser().getScreenName();
            String textdata = status.getText();

            log.info(tid);
            log.info(username);
            log.info(textdata);

            tModel.setTid(tid);
            tModel.setTUsername(username);
            tModel.setTText(textdata);


            log.info(tid);
            log.info(username);
            log.info(textdata);

            String topicName = "tweet";
            twitterKafkaTemplate.send(topicName,tModel);


            //sendMessage(MessageBuilder.withPayload(status).build());
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
