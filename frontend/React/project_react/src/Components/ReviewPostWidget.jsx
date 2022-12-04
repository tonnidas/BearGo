import React, {  } from 'react';


import axios from 'axios';
import AuthService from '../Service/AuthService';
import image_man from '../images/man_avatar1.jpg';
import Moment from 'react-moment';
import ReportWidget from './ReportWidget';
import ProfileImage from '../Service/Helper';

export default function ReviewPostWidget(props) {

  const handleResolve = async (verdict) => {
    if (window.confirm("Are you sure to mark this as " + verdict + "?") == false) {
      return
    }

    AuthService.setAxiosAuthHeader();

    try {
      const resp = await axios.post('/api/admins/resolve/productPost?productPostId=' + props.post.id + "&verdict=" + verdict);
      console.log(resp.data);
      alert("Thank you for resolving the issue!");
    } catch (error) {
      console.log(error);
      alert('Failed to reolve the issue, reason: ' + error.response.data.message);
    }
  }

  const handleIgnore = async (event) => {
    event.preventDefault();
    handleResolve("ignored");
  }

  const handleBlock = async (event) => {
    event.preventDefault();
    handleResolve("blocked");
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
                href={'#collapsereports_' + props.post.id}
                role='button'
                aria-expanded='false'
                aria-controls='collapseExample'
              >
                <i className='icon-message-circle'></i>Complaints
              </a>
            </li>
            <li>
              <a role="button" href='#' onClick={handleIgnore}>
                <i className='icon-check'></i>Ignore
              </a>
            </li>
            <li>
              <a role="button" href='#' onClick={handleBlock}>
                <i className='icon-alert'></i>Block
              </a>
            </li>
          </ul>
        </div>

        <div className='collapse reports' id={'collapsereports_' + props.post.id}>
          <ul>
            {props.post.complaints.map(report => <ReportWidget report={report} key={report.id} />)}
          </ul>
        </div>
      </div>
    </div>
  );
}
