import React, { useEffect, useState } from 'react';

import Navbar from './Navbar';
import Sidebar from './Sidebar';

import axios from 'axios';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import urlPaths from '../urlPaths';
import image_man from '../images/man_avatar1.jpg';
import image_woman from '../images/women_avatar1.jpg';
import Moment from 'react-moment';
import CommentWidget from './CommentWidget';

export default function PostWidget(props) {
  const navigate = useNavigate();
  const [comments, setComments] = useState([]);

  useEffect(() => {
    setComments(props.post.comments);
  }, []);

  const handleCommentSubmit = async (event) => {
    event.preventDefault();

    const commentData = event.target[0].value

    AuthService.setAxiosAuthHeader();

    try {
      const resp = await axios.post('/api/comments?productPostId=' + props.post.id, {
        comment: commentData
      });
      setComments([...comments, {comment: resp.data.comment, commentTime: resp.data.commentTime, commentedBy: {fullname: props.user}}]);
      event.target[0].value = "";
    } catch (error) {
      console.log(error);
      alert('Failed to post comment! Please try again');
    }
  }

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
            <h4>{props.post.contract.sender.fullname}</h4>
            <span><Moment format="LLL">{props.post.createdAt}</Moment></span>
          </div>
        </a>
      </div>

      <div className='widget-inner'>
        <div className='post'>
          <p>
            {props.post.description}
          </p>
          <span className='icon-time'>
            Pickup from:&nbsp; <Moment format="LL">{props.post.expectedPickupDate}</Moment>
          </span>
          <span className='icon-time' style={{ marginLeft: '50px' }}>
            Delivery within:&nbsp; <Moment format="LL">{props.post.expectedDeliveryDate}</Moment>
          </span>
          <img className='img-fluid' src={"/api/images/download/" + props.post.imageId} alt='product' />
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
              <a
                data-toggle='collapse'
                href={'#collapsecomments_' + props.post.id}
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

        <div className='collapse comments' id={'collapsecomments_' + props.post.id}>
          <ul>

            {
              comments.map(comment => <CommentWidget comment={comment} key={comment.id} />)
            }

            {/* new comment */}
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
                    <h4>{props.user}</h4>
                    <span><Moment format="LLL">{Date.now()}</Moment></span>
                  </div>
                  <form onSubmit={handleCommentSubmit}>
                    <textarea className='form-control' rows='2' placeholder="Write comment"></textarea>
                    <p><button type="submit" className='common-btn'>Post</button></p>
                  </form>
                </div>
              </div>
            </li>
          </ul>
        </div>
      </div>
    </div>
  );
}
