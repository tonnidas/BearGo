import React from 'react';

import image_man from '../images/man_avatar1.jpg';
import image_woman from '../images/women_avatar1.jpg';
import Moment from 'react-moment';
import ProfileImage from '../Service/Helper';

export default function CommentWidget(props) {
  return (
    <li>
      <div className='comment-box'>
        <div className='mask'>
          <img
            className='mask-img'
            src={ProfileImage(props.comment.commentedBy)}
            alt=''
          />
          <svg>
            <use href='#icon-mask'></use>
          </svg>
        </div>
        <div className='comment-box-content'>
          <div className='user-avatar-name'>
            <h4>{props.comment.commentedBy.fullname}</h4>
            <span><Moment format="LLL">{props.comment.commentTime}</Moment></span>
          </div>
          <p>{props.comment.comment}</p>
        </div>
      </div>
    </li>
  );
}
