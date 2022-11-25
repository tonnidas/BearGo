import './FrontPage.css';

import React, { useEffect, useState } from 'react';

import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';
import image_man from '../images/man_avatar1.jpg';
import parcel_1 from '../images/parcel-1.jpg';
import image_woman from '../images/women_avatar1.jpg';
import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import urlPaths from '../urlPaths';

export default function FrontPage() {
  const navigate = useNavigate();
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    AuthService.setAxiosAuthHeader();
    axios.get("/api/productPosts/getAllProductPost")
      .then((res) => {
        console.log(res.data);
        setPosts(res.data);
      })
      .catch((err) => {
        console.log(err);
        if(err.response.status === 401) {
          navigate(urlPaths.login);
        }
      });
  }, []);

  const postWidgets = posts.map(post => {
    return (
      <div className='widget'>
        <div className='widget-head'>
          <a href='#' className='user-avatar'>
            <div className='mask'>
              <img className='mask-img' src={image_man} alt='' />
              <svg>
                <use href='#icon-mask'></use>
              </svg>
            </div>
            <div className='user-avatar-name'>
              <h4>Nusa Penida</h4>
              <span>12:53 PM · Sep 22, 2022</span>
            </div>
          </a>
        </div>
        <div className='widget-inner'>
          <div className='post'>
            <p>
              {post.productPost.description}
            </p>
            <span className='icon-time'>
              Delivery Date: {post.productPost.expectedDeliveryDate}
            </span>
            <img className='img-fluid' src={parcel_1} alt='' />
          </div>
        </div>
        <div className='widget-footer'>
          <div className='post-action'>
            <ul>
              <li>
                <a href='#'>
                  <i className='icon-message-square'></i>Message
                </a>
              </li>
              <li>
                <a href='#'>
                  <i className=' icon-share-2'></i>Share
                </a>
              </li>
              <li>
                <a
                  data-toggle='collapse'
                  href='#collapsecomments'
                  role='button'
                  aria-expanded='false'
                  aria-controls='collapseExample'
                >
                  <i className='icon-message-circle'></i>Comments
                </a>
              </li>
              <li>
                <a href='#'>
                  <i className='icon-alert'></i>Report
                </a>
              </li>
            </ul>
          </div>
          <div className='collapse comments' id='collapsecomments'>
            <ul>
              <li>
                <div className='comment-box'>
                  <div className='mask'>
                    <img
                      className='mask-img'
                      src={image_man}
                      alt=''
                    />
                    <svg>
                      <use href='#icon-mask'></use>
                    </svg>
                  </div>
                  <div className='comment-box-content'>
                    <div className='user-avatar-name'>
                      <h4>Nicole Engelbrecht</h4>
                      <span>12:53 PM · Sep 22, 2022</span>
                    </div>
                    <p>
                      Lorem ipsum dolor sit amet, consectetur
                      adipiscing elit. Metus, felis, sed fames vel
                      odio risus.
                    </p>
                    <a href='#'>Reply</a>
                  </div>
                </div>
                <ul>
                  <li>
                    <div className='comment-box'>
                      <div className='mask'>
                        <img
                          className='mask-img'
                          src={image_woman}
                          alt=''
                        />
                        <svg>
                          <use href='#icon-mask'></use>
                        </svg>
                      </div>
                      <div className='comment-box-content'>
                        <div className='user-avatar-name'>
                          <h4>Nicole Engelbrecht</h4>
                          <span>12:53 PM · Sep 22, 2022</span>
                        </div>
                        <textarea
                          className='form-control'
                          name=''
                          rows='2'
                        ></textarea>
                      </div>
                    </div>
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </div>
      </div>
    )
  })


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
                  {postWidgets}
                </div>
              </div>

              <div className='col-md-4'>
                <div className='twitter-feed'>
                  <blockquote
                    className='twitter-tweet'
                    style={{ marginBottom: '10px' }}
                  >
                    <p lang='en' dir='ltr'>
                      I think it&#39;s entirely appropriate that the courier
                      delivery pin to receive my parcel from
                      <a href='https://twitter.com/CounterLove?ref_src=twsrc%5Etfw'>
                        @CounterLove
                      </a>
                      was 911 because these limited edition Hertzoggie cookies
                      are criminally delicious!
                      <a href='https://t.co/1z52eA0Oor'>
                        pic.twitter.com/1z52eA0Oor
                      </a>
                    </p>
                    &mdash; Nicole Engelbrecht (@the_pod_one)
                    <a href='https://twitter.com/the_pod_one/status/1572841559812411392?ref_src=twsrc%5Etfw'>
                      September 22, 2022
                    </a>
                  </blockquote>
                  <blockquote
                    className='twitter-tweet'
                    style={{ marginBottom: '10px' }}
                  >
                    <p lang='en' dir='ltr'>
                      I think it&#39;s entirely appropriate that the courier
                      delivery pin to receive my parcel from
                      <a href='https://twitter.com/CounterLove?ref_src=twsrc%5Etfw'>
                        @CounterLove
                      </a>
                      was 911 because these limited edition Hertzoggie cookies
                      are criminally delicious!
                      <a href='https://t.co/1z52eA0Oor'>
                        pic.twitter.com/1z52eA0Oor
                      </a>
                    </p>
                    &mdash; Nicole Engelbrecht (@the_pod_one)
                    <a href='https://twitter.com/the_pod_one/status/1572841559812411392?ref_src=twsrc%5Etfw'>
                      September 22, 2022
                    </a>
                  </blockquote>
                  <script
                    async
                    src='https://platform.twitter.com/widgets.js'
                    charSet='utf-8'
                  ></script>
                </div>
              </div>
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
