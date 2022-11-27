import './FrontPage.css';

import React, { useEffect, useState } from 'react';

import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import urlPaths from '../urlPaths';
import PostWidget from '../Components/PostWidget';
import TwitterWidget from '../Components/TwitterWidget';


export default function FrontPage() {
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);
  const [user, setUser] = useState([]);

  useEffect(() => {
    AuthService.setAxiosAuthHeader();

    axios.get("/api/users/current")
      .then(res => {
        console.log(res.data.fullname);
        setUser(res.data.fullname);
      })
      .catch((err) => {
        console.log(err);
        if (err.response.status === 401) {
          navigate(urlPaths.login);
        }
      });

    axios.get("/api/productPosts/getAllProductPost/searchingTraveler")
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
    <div>
      <title>Dashboard</title>

      <Navbar />

      <div className='container-fluid'>
        <div className='row'>
          <Sidebar />

          <main role='main' className='main col-md-12 ml-sm-auto col-lg-9'>
            <div className='row' style={{ position: 'relative' }}>
              <div className='col-md-8'>
                <div className='main-inner'>
                  {posts.map(post => <PostWidget post={post} user={user} key={post.id} />)}
                </div>
              </div>

              <TwitterWidget/>
            </div>
          </main>
        </div>
      </div>
      <script src='js/jquery-3.2.1.min.js'></script>
      <script src='js/bootstrap.min.js'></script>
      <script src='js/scripts.js'></script>
    </div>
  );
}
