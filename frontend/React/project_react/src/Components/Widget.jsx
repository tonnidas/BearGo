import React from 'react';

import image_man from '../images/man_avatar1.jpg';
import parcel_1 from '../images/parcel-1.jpg';
import image_woman from '../images/women_avatar1.jpg';

export default function Widget(props) {
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
            <h4>{props.post.sender.fullname}</h4>
            <span>12:53 PM · {props.post.contract.contractStartDate}</span>
          </div>
        </a>
      </div>
      <div className='widget-inner'>
        <div className='post'>
          <p>
            {props.post.productPost.description}
          </p>
          <span className='icon-time'>
            Delivery Date: {props.post.productPost.expectedDeliveryDate}
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
                <i className='icon-check'></i>Interested
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
  );
}