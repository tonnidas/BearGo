import React, { useEffect, useState, Component } from 'react';

//import admin from '../images/admin.jpg';
//import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';

import * as SockJS from 'sockjs-client';

import { Stomp } from "@stomp/stompjs";

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import { State } from 'country-state-city';

//var stompClient = null;
const socket = SockJS('http://localhost:8080/ws');
const stompClient = Stomp.over(socket);




export default function Notification() {
    const navigate = useNavigate();
    var [posts, setPosts] = useState([]); 
    
    
    const [stompClient1, setStompClient] = useState([]);
    var [notificationdata, setnotificationdata] = useState([]);

    useEffect(() => {

        
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
    }, []);

    
    
   
    useEffect(() => {

        console.log("Connecting");
        //const socket = SockJS('http://localhost:8080/ws');
        
        //const stompClient = Stomp.over(socket);
        //stompClient = Stomp.over(socket);

        stompClient.connect({}, onConnected);

        

    });
    
    const onConnected = () => {
        console.log("Connected");
        stompClient.subscribe
        stompClient.subscribe("/topic/newNotification2", onMessage);

    };
    const onMessage = (data) => {
        console.log("data received");
        const dto = JSON.parse(data.body);
        console.log(dto);
        posts.data.push(dto);
        //setnotificationdata(dto);
        //notificationdata.push(dto);
        //setnotificationdata(notificationdata);
        //notificationdata.push(dto);
        //setnotificationdata(notificationdata);
        /*
        setnotificationdata(values => [
            dto,
            ...values
        ]);
         
        
        setPosts(values => [
            dto,
            ...values
        ]);
        
        */

    };
        /*
        stompClient.connect({}, () => {
            stompClient.subscribe('/topic/newNotification2', (data) => {

                

                
                const dto = JSON.parse(data.body);
                console.log("I am printing");
                
                setPosts(values => [
                    dto,
                    ...values
                ]);
            
                console.log(posts);
                
               
            });
        });

    
        setStompClient(stompClient);

    }, []);
    
       */
    


  return (
    <>
    <div>
    <div>
              <a
                className='nav-link dropdown-toggle'
                href='#'
                id='navbarDropdown'
                role='button'
                data-toggle='dropdown'
                aria-haspopup='true'
                aria-expanded='false'
              >
                <i className='icon-notifications'></i>
              </a>
              <div className='dropdown-menu' aria-labelledby='navbarDropdown'>
                      <h3>Notifications</h3>
                      


                      <div className='notifications'>

                          {notificationdata.map(n =>
                              <p className='unread'>
                                  {n.notificationMsg}
                              </p>
                          )} 
                          

                        
                                            
                {posts.map(post =>
                  <p className='unread'>
                  {post.notificationMsg}
                  </p>
                )}
                      </div>

                     
              </div>
            </div>
    </div>
       </>
  );
}