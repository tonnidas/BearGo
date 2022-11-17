import './CreatePost.css';

import React from 'react';

import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';
import image_man from '../images/man_avatar1.jpg';
import parcel_1 from '../images/parcel-1.jpg';

export default function CreatePost() {
  return (
    <>
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
                        <form action='#'>
                          <div className='form-group'>
                            <label>Description</label>
                            <textarea
                              name=''
                              className='form-control'
                              rows='5'
                            ></textarea>
                          </div>
                          <div className='form-group'>
                            <img className='img-fluid' src={parcel_1} alt='' />
                          </div>
                          <div className='form-group'>
                            <label className='upload-btn'>
                              {' '}
                              <i className='icon-upload'></i> Upload Image
                              <input type='file' multiple />
                            </label>
                          </div>
                          <div className='row'>
                            <div className='col-md-6'>
                              <div className='form-group'>
                                <label>Start Date</label>
                                <input
                                  className='form-control'
                                  type='date'
                                  name=''
                                  value=''
                                />
                              </div>
                            </div>
                            <div className='col-md-6'>
                              <div className='form-group'>
                                <label>End Date</label>
                                <input
                                  className='form-control'
                                  type='date'
                                  name=''
                                  value=''
                                />
                              </div>
                            </div>
                          </div>
                          <div className='form-group'>
                            <label>Source Location</label>
                            <div className='select-style'>
                              <select name='#'>
                                <option value=''>New York</option>
                                <option value=''>Los Angeles</option>
                                <option value=''>Chicago</option>
                              </select>
                            </div>
                          </div>
                          <div className='form-group'>
                            <label>Destination Location</label>
                            <div className='select-style'>
                              <select name='#'>
                                <option value=''>New York</option>
                                <option value=''>Los Angeles</option>
                                <option value=''>Chicago</option>
                              </select>
                            </div>
                          </div>
                          <div className='form-group'>
                            <label className='checkbox'>
                              <input type='checkbox' name='isblog' value='' />
                              <span className='checkmark'></span>
                              is travel blog
                            </label>
                          </div>
                          <button className='common-btn'>Post</button>
                        </form>
                      </div>
                    </div>
                  </div>
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
    </>
  );
}
