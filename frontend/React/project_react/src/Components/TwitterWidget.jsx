import React, { useEffect, useState } from 'react';
import { Helmet } from "react-helmet";

import urlPaths from '../urlPaths';
import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";

import * as SockJS from 'sockjs-client';
import { Stomp } from "@stomp/stompjs";

var stompClient = null;

export default function TwitterWidget(props) {

    //const socket = SockJS('http://localhost:8080/ws');
    const navigate = useNavigate();
    var [tweets, settweets] = useState([]); 

    useEffect(() => {


        AuthService.setAxiosAuthHeader();
        axios.get("api/twitter")

            .then((res) => {

                console.log(res.data);
                settweets(res.data);
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            });
    }, []);
    /*
    useEffect(() => {
       

        console.log("Connecting");
        
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onConnected);


    }, []);

    const onConnected = () => {
        console.log("Connected");
        stompClient.subscribe("/topic/tweet", onMessage);

    };
    const onMessage = (data) => {
        console.log("data received");
        const dto = JSON.parse(data.body);
        console.log(dto);

        settweets(values => [
            dto,
            ...values
        ]);

    };
    */
  return (
    <div className='col-md-4'>
          <div className='twitter-feed'>

              <blockquote class="twitter-tweet">
                  <p lang="en" dir="ltr">
                      I think it&#39;s entirely appropriate that the courier
                      delivery pin to receive my parcel from
                  <a href="https://twitter.com/CounterLove?ref_src=twsrc%5Etfw">@CounterLove</a>
                  was 911 because these limited edition Hertzoggie cookies are
                  criminally delicious!
                  <a href="https://t.co/1z52eA0Oor">pic.twitter.com/1z52eA0Oor</a>
                  </p>

                  <a href="https://twitter.com/the_pod_one/status/1572841559812411392"></a>
              </blockquote>
              <Helmet>
                <script src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
              </Helmet>
          {tweets.map(tweet =>
              <blockquote class="twitter-tweet">

                  
                      <p lang="en" dir="ltr">
                          {tweet.ttext}
                      </p>

                  <a href="https://twitter.com/the_pod_one/status/1572841559812411392"></a>
              </blockquote>
              )}
        
      </div>
    </div>
  );
}
