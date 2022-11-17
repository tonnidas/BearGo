import './FrontPage.css';

import React, { useEffect, useState } from 'react';

import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';
import image_man from '../images/man_avatar1.jpg';
import parcel_1 from '../images/parcel-1.jpg';
import parcel_2 from '../images/parcel-2.jpg';
import image_woman from '../images/women_avatar1.jpg';

export default function FrontPage() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    // axios.get("http://localhost:8080/allContest")
    fetch('allContest')
      .then((res) => res.json())
      .then((res) => {
        console.log(res);
        setPosts(res);
      });
  }, []);
  return (
    <div>
      {/* <!-- Bootstrap CSS --> */}
      <title>Dashboard</title>

      <Navbar />

      <div className='container-fluid'>
        <div className='row'>
          <Sidebar />

          <main role='main' className='main col-md-12 ml-sm-auto col-lg-9'>
            <div className='row' style={{ position: 'relative' }}>
              <div className='col-md-8'>
                <div className='main-inner'>
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
                          Finally, the focus on catalyzing payments innovation
                          is part and parcel to the evolution of the financial
                          services industry at large, and brings into focus the
                          kind of impact it can have on financial inclusion
                          especially for low and moderate income (LMI)
                          communities. (8/9)
                        </p>
                        <span className='icon-time'>
                          Delivery Date: Sep 22, 2022
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
                  <div className='widget'>
                    <div className='widget-head'>
                      <a href='#' className='user-avatar'>
                        <div className='mask'>
                          <img className='mask-img' src={image_woman} alt='' />
                          <svg>
                            <use href='#icon-mask'></use>
                          </svg>
                        </div>
                        <div className='user-avatar-name'>
                          <h4>Nicole Engelbrecht</h4>
                          <span>12:53 PM · Sep 22, 2022</span>
                        </div>
                      </a>
                    </div>
                    <div className='widget-inner'>
                      <div className='post'>
                        <p>
                          NEW for Autumn 2022! 🍁🍂🍁 With Toffee Apple Tea,
                          Blackberry Preserve, Smoked Cheese Straws, Chocolate
                          Leaves and more, Fortnum's Autumn Days Hamper is the
                          perfect parcel to squirrel away with.
                        </p>
                        <span className='icon-time'>
                          Delivery Date: Sep 22, 2022
                        </span>
                        <img className='img-fluid' src={parcel_2} alt='' />
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
                            <a href='#'>
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
                    </div>
                  </div>
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
                          Finally, the focus on catalyzing payments innovation
                          is part and parcel to the evolution of the financial
                          services industry at large, and brings into focus the
                          kind of impact it can have on financial inclusion
                          especially for low and moderate income (LMI)
                          communities. (8/9)
                        </p>
                        <span className='icon-time'>
                          Delivery Date: Sep 22, 2022
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
                            <a href='#'>
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
                    </div>
                  </div>
                </div>
              </div>
              {/* <div className='col-md-4'>
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
              </div> */}
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
