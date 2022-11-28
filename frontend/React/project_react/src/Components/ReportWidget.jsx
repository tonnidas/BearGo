import React from 'react';

import image_man from '../images/man_avatar1.jpg';
import Moment from 'react-moment';

export default function ReportWidget(props) {
  return (
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
            <h4>{props.report.complainedBy.fullname}</h4>
            <span><Moment format="LLL">{props.report.complainDate}</Moment></span>
          </div>
          <p>{props.report.reason}</p>
        </div>
      </div>
    </li>
  );
}
