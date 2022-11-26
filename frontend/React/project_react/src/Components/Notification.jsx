import React, { useEffect, useState } from 'react';

import admin from '../images/admin.jpg';
import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";

export default function Notification() {
    const navigate = useNavigate();
    const [posts, setPosts] = useState([]);
  
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