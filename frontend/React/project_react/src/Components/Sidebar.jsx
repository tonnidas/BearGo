import React from 'react';
import { useNavigate } from 'react-router-dom';
import urlPaths from '../urlPaths';

export default function Sidebar(props) {
  const url = window.location.pathname;

  const navigate = useNavigate();
  const handleClickCreateProductPost = (e) => {
    navigate(urlPaths.createPost);
  };
  const handleClickCreateBlogPost = (e) => {
    navigate(urlPaths.createBlog);
  };
  return (
    <>
      <nav className='col-md-3 d-md-block sidebar'>
        <div className='sidebar-sticky'>
          <div>
            <ul className='sidebar-menu'>
              <li className={url === urlPaths.home ? 'active' : ''}>
                <a href={urlPaths.home}>
                  {' '}
                  <i className='icon-home'></i>
                  <span>Home </span>{' '}
                </a>
              </li>
              <li className={url === urlPaths.message ? 'active' : ''}>
                <a href={urlPaths.message}>
                  <i className='icon-message-square'></i>
                  <span>message </span>
                </a>
              </li>
              <li className={url === urlPaths.settings ? 'active' : ''}>
                <a href={urlPaths.home}>
                  <i className='icon-settings'></i>
                  <span>Settings </span>
                </a>
              </li>
              <li className={url === urlPaths.myPosts ? 'active' : ''}>
                <a href={urlPaths.myPosts}>
                  <i className='icon-phone'></i>
                  <span>My Product Post </span>
                </a>
              </li>
              <li className={url === urlPaths.contactSender ? 'active' : ''}>
                <a href={urlPaths.contactSender}>
                  <i className='icon-phone'></i>
                  <span>My Contact </span>
                </a>
              </li>
              <li className={url === urlPaths.blog ? 'active' : ''}>
                <a href={urlPaths.blog}>
                  {' '}
                  <i className='icon-home'></i>
                  <span>Blogs </span>{' '}
                </a>
              </li>
              <li className={url === urlPaths.myBlog ? 'active' : ''}>
                <a href={urlPaths.myBlog}>
                  <i className='icon-clipboard'></i>
                  <span>My blog </span>
                </a>
              </li>
              <li className={url === urlPaths.profileSearch ? 'active' : ''}>
                <a href={urlPaths.profileSearch}>
                  <i className='icon-search'></i>
                  <span>Profile Search </span>
                </a>
              </li>
              <li className={url === urlPaths.reviewPost ? 'active' : ''}>
                <a href={urlPaths.reviewPost}>
                  <i className='icon-search'></i>
                  <span>Admin . Review Post </span>
                </a>
              </li>
            </ul>
          </div>
          <button className='common-btn global-add' onClick={handleClickCreateProductPost}>
            <i className='icon-plus'></i> <span>Product Post</span>
          </button>
          <button className='common-btn global-add' onClick={handleClickCreateBlogPost}>
            <i className='icon-plus'></i> <span>Blog Post</span>
          </button>
        </div>
      </nav>
    </>
  );
}
