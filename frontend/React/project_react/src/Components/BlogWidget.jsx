import React, { useEffect, useState } from 'react';

import image_man from '../images/man_avatar1.jpg';
import Moment from 'react-moment';

export default function BlogWidget(props) {

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
            <h4>{props.post.postedBy.fullname}</h4>
            <span><Moment format="LLL">{props.post.postedDateTime}</Moment></span>
          </div>
        </a>
      </div>

      <div className='widget-inner'>
        <div className='post'>
          <p>
            {props.post.description}
          </p>
          <img className='img-fluid' src={"/api/images/download/" + props.post.imageId} alt='product' />
        </div>
      </div>
    </div>
  );
}
