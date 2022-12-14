import React, { useRef ,useEffect, useState, Component } from 'react';

//import admin from '../images/admin.jpg';
//import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';

import * as SockJS from 'sockjs-client';

import { Stomp } from "@stomp/stompjs";

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";


var stompClient = null;
import { State } from 'country-state-city';

var stompClient = null;
//var socket = null;

var counter = 0;

export default function Notification() {
    const [not, setNot] = useState(0);
    

    const socket = (location.hostname === "localhost" || location.hostname === "127.0.0.1") ? SockJS('http://localhost:8080/ws') : SockJS('https://beargo.live/ws');

    const navigate = useNavigate();
    var [posts, setPosts] = useState([]);

    useEffect(() => {
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function (frame) {
            stompClient.subscribe(
                "/topic/newNotification2",
                message => {
                    console.log("Saad");
                    if (message.body) {
                        var dto = JSON.parse(message.body);
                        //not = not + 1;
                        counter += 1;
                        setNot(counter);
                        console.log("#####################");
                        console.log(not);
                        console.log(dto);
                        setPosts(values => [
                            dto,
                            ...values
                        ]);
                    }
                }
            );
        });
        stompClient.activate();

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

    const handleClick = (e) => {
        e.preventDefault();
        
            counter = 0;
            console.log('The link was clicked.');
            setNot(0);
      
            console.log(counter);

    }

    

    return (
        <>
            <div>
                <div>
                    <a onClick={handleClick}
                        className='nav-link dropdown-toggle'
                        href='#'
                        id='navbarDropdown'
                        role='button'
                        data-toggle='dropdown'
                        aria-haspopup='true'
                        aria-expanded='false'
                    >
                        <i className='icon-notifications'></i>
                        ({ not})
                    </a>
                    
                    <div className='dropdown-menu' aria-labelledby='navbarDropdown'>
                        <h3>Notifications</h3>



                        <div className='notifications'>





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