import React, { useEffect, useState } from 'react';


import axios from 'axios';
import AuthService from '../Service/AuthService';
import urlPaths from '../urlPaths';
import image_man from '../images/man_avatar1.jpg';
import image_woman from '../images/women_avatar1.jpg';
import Moment from 'react-moment';
import CommentWidget from './CommentWidget';
import ProfileImage from '../Service/Helper';

export default function MyPostWidget(props) {
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
      setComments([...comments, { comment: resp.data.comment, commentTime: resp.data.commentTime, commentedBy: { fullname: props.user } }]);
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
            <img className='mask-img' src={ProfileImage(props.post.contract.sender)} alt='' />
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
          <div className='row'>
            <div className='col-md-12'>
              <label>Status</label>
              <p>{props.post.contract.deliveryStatus}</p>
            </div>
            <div className='col-md-6'>
              <label>Source Location</label>
              <p>{props.post.source.street + ", " + props.post.source.city + ", " + props.post.source.state + ", " + props.post.source.zip + ", " + props.post.source.country}</p>
            </div>
            <div className='col-md-6'>
              <label>Destination Location</label>
              <p>{props.post.destination.street + ", " + props.post.destination.city + ", " + props.post.destination.state + ", " + props.post.destination.zip + ", " + props.post.destination.country}</p>
            </div>
          </div>
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
              <a href={urlPaths.updatePost + "?id=" + props.post.id}>
                <i className='icon-edit'></i>Edit
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
                    src={ProfileImage(props.user)}
                    alt=''
                  />
                  <svg>
                    <use href='#icon-mask'></use>
                  </svg>
                </div>
                <div className='comment-box-content'>
                  <div className='user-avatar-name'>
                    <h4>{props.user.fullname}</h4>
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
