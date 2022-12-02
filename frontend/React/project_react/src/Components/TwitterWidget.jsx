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

    const socket = SockJS('http://localhost:8080/ws');



    const navigate = useNavigate();
    var [tweet, setTweets] = useState([]);

    useEffect(() => {
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe(
                "/topic/tweet",
                message => {
                    
                    if (message.body) {
                        var dto = JSON.parse(message.body);

                        console.log(dto);
                        setTweets(values => [
                            dto,
                            ...values
                        ]);
                    }
                }
            );
        });
        stompClient.activate();
        /*
        AuthService.setAxiosAuthHeader();
        axios.get("api/notification")

            .then((res) => {

                console.log(res.data);
                setPosts(res.data);
            })
            .catch((err) => {
                console.log(err);
                if (err.response.status === 401) {
                    navigate(urlPaths.login);
                }
            });
            */

    }, []);

    function getUrl(url) {
   
        console.log(url);
        return url;

    }
  return (
    <div className='col-md-4'>
          
              

              
          <div className='twitter-feed'> 
              
          {tweet.map(t =>
              
              
              <blockquote className="twitter-tweet">

                  

                  
                      <p>{t.ttext}</p>
                  
                      
                  
                      {/*<a href={getUrl(t.tweeturl)}></a>*/}
                  <a href={t.tweeturl}></a>
                  <Helmet>
                      <script src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
                  </Helmet>
                  
                      
              </blockquote>

              
              

              )}
              
          </div>      
            
     
    </div>
  );
}
