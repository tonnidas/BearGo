import "./FrontPage.css"

import React, { useState, useEffect } from "react"
import axios from "axios"

import image_man from "../images/man_avatar1.jpg"
import image_woman from "../images/women_avatar1.jpg"
import logo_white from "../images/logo-white.svg"
import parcel_1 from "../images/parcel-1.jpg"
import parcel_2 from "../images/parcel-2.jpg"
import favicon from "../images/favicon.ico"
import admin from "../images/admin.jpg"


export default function FrontPage() {
  const [posts, setPosts] = useState([])

  useEffect(() => {
    // axios.get("http://localhost:8080/allContest")
    fetch('allContest')
      .then(res => res.json())
      .then((res) => {
        console.log(res)
        setPosts(res)
      })

  }, []);
  let url = ""
  return (
    
    <div>
      <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
  <link href={favicon} type="image/x-icon" rel="shortcut icon" />
  <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap" rel="stylesheet" />
  {/* <!-- Bootstrap CSS --> */}
  <link rel="stylesheet" href="./FrontPage.css" />
  <title>Dashboard</title>
  
      <nav class="navbar navbar-expand-lg navbar-light fixed-top">
        <div class="col-md-3 sidebar-top">
          <a class="navbar-brand" href={url}><img src={logo_white} alt="" />
          </a>
        </div>
        <span class="sidebar-toggler">
          <i class="icon-more"></i>
        </span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
          aria-label="Toggle navigation">
          <i class="icon-bold"></i>
        </button>

        <div class="collapse navbar-collapse" id="navbar">
          <input class="form-control" type="text" name="" value="" />
          <ul class="navbar-nav ml-auto">


            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href={url} id="navbarDropdown" role="button" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
                <i class="icon-notifications"></i>
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <h3>Notifications</h3>
                <div class="notifications">
                  <p>Your account has been restricted from advertising.</p>
                  <p>Your account has been restricted from advertising.</p>
                </div>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href={url} id="navbarDropdown" role="button" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
                <img src={admin} alt="" />
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                <h3>Jhon Doe</h3>
                <a class="dropdown-item" href={url}>Profile</a>
                <a class="dropdown-item" href={url}>Logout</a>
              </div>
            </li>
          </ul>
        </div>
      </nav>
      <div class="container-fluid">
        <div class="row">
          <nav class="col-md-3 d-md-block sidebar">
            <div class="sidebar-sticky">

              <div>
                <ul class="sidebar-menu">
                  <li class="active">
                    <a href={url}> <i class="icon-home"></i><span>Home </span> </a>
                  </li>
                  <li>
                    <a href={url}>
                      <i class="icon-message-square"></i><span>message </span>
                    </a>
                  </li>
                  <li>
                    <a href={url}>
                      <i class="icon-settings"></i><span>Settings </span>
                    </a>
                  </li>
                  <li>
                    <a href={url}>
                      <i class="icon-phone"></i><span>My Contact </span>
                    </a>
                  </li>
                  <li>
                    <a href={url}>
                      <i class="icon-clipboard"></i><span>My blog </span>
                    </a>
                  </li>
                </ul>
              </div>
              <button class="common-btn global-add" onclick="showModal('addRule')">
                <i class="icon-plus"></i> <span>New Post</span>
              </button>
            </div>
          </nav>

          <main role="main" class="main col-md-12 ml-sm-auto col-lg-9">
            <div class="row" style={{ position: "relative" }}>
              <div class="col-md-8">
                <div class="main-inner">
                  <div class="widget">
                    <div class="widget-head">
                      <a href={url} class="user-avatar">
                        <div class="mask">
                          <img class="mask-img" src={image_man} alt="" />

                        </div>
                        <div class="user-avatar-name">
                          <h4>Nusa Penida</h4>
                          <div>
                            <h5>API data</h5>
                            <ul>
                              {posts.map((item, i) => {
                                return <li key={i}>{item.name}</li>
                              })}
                            </ul>
                          </div>
                          <span>12:53 PM · Sep 22, 2022</span>
                        </div>
                      </a>
                    </div>
                    <div class="widget-inner">
                      <div class="post">
                        <p>Finally, the focus on catalyzing payments innovation is part and parcel to the evolution of the
                          financial services industry at large, and brings into focus the kind of impact it can have on
                          financial inclusion especially for low and moderate income (LMI) communities. (8/9)</p>
                        <span class="icon-time">Delivery Date: Sep 22, 2022</span>
                        <img class="img-fluid" src={parcel_1} alt="" />

                      </div>
                    </div>
                    <div class="widget-footer">
                      <div class="post-action">
                        <ul>

                          <li><a href={url}>
                            <i class="icon-message-square"></i>Message
                          </a></li>
                          <li><a href={url}>
                            <i class=" icon-share-2"></i>Share
                          </a></li>
                          <li><a href={url}>
                            <i class="icon-message-circle"></i>Comments
                          </a></li>
                          <li><a href={url}>
                            <i class="icon-alert"></i>Report
                          </a></li>
                        </ul>
                      </div>
                    </div>
                  </div>
                  <div class="widget">
                    <div class="widget-head">
                      <a href={url} class="user-avatar">
                        <div class="mask">
                          <img class="mask-img" src={image_woman} alt="" />

                        </div>
                        <div class="user-avatar-name">
                          <h4>Nicole Engelbrecht</h4>
                          <span>12:53 PM · Sep 22, 2022</span>
                        </div>
                      </a>
                    </div>
                    <div class="widget-inner">
                      <div class="post">
                        <p>NEW for Autumn 2022! 🍁🍂🍁 With Toffee Apple Tea, Blackberry Preserve, Smoked Cheese Straws,
                          Chocolate Leaves and more, Fortnum's Autumn Days Hamper is the perfect parcel to squirrel away
                          with.</p>
                        <span class="icon-time">Delivery Date: Sep 22, 2022</span>
                        <img class="img-fluid" src={parcel_2} alt="" />

                      </div>
                    </div>
                    <div class="widget-footer">
                      <div class="post-action">
                        <ul>

                          <li><a href={url}>
                            <i class="icon-message-square"></i>Message
                          </a></li>
                          <li><a href={url}>
                            <i class=" icon-share-2"></i>Share
                          </a></li>
                          <li><a href={url}>
                            <i class="icon-message-circle"></i>Comments
                          </a></li>
                          <li><a href={url}>
                            <i class="icon-alert"></i>Report
                          </a></li>
                        </ul>
                      </div>
                    </div>
                  </div>
                  <div class="widget">
                    <div class="widget-head">
                      <a href={url} class="user-avatar">
                        <div class="mask">
                          <img class="mask-img" src={image_man} alt="" />

                        </div>
                        <div class="user-avatar-name">
                          <h4>Nusa Penida</h4>
                          <span>12:53 PM · Sep 22, 2022</span>
                        </div>
                      </a>
                    </div>
                    <div class="widget-inner">
                      <div class="post">
                        <p>Finally, the focus on catalyzing payments innovation is part and parcel to the evolution of the
                          financial services industry at large, and brings into focus the kind of impact it can have on
                          financial inclusion especially for low and moderate income (LMI) communities. (8/9)</p>
                        <span class="icon-time">Delivery Date: Sep 22, 2022</span>
                        <img class="img-fluid" src={parcel_1} alt="" />

                      </div>
                    </div>
                    <div class="widget-footer">
                      <div class="post-action">
                        <ul>

                          <li><a href={url}>
                            <i class="icon-message-square"></i>Message
                          </a></li>
                          <li><a href={url}>
                            <i class=" icon-share-2"></i>Share
                          </a></li>
                          <li><a href={url}>
                            <i class="icon-message-circle"></i>Comments
                          </a></li>
                          <li><a href={url}>
                            <i class="icon-alert"></i>Report
                          </a></li>
                        </ul>
                      </div>
                    </div>
                  </div>

                </div>
              </div>

            </div>
            <div class="col-md-4 twitter-sticky">
              <blockquote class="twitter-tweet" style={{ marginTop: '10px' }}>
                <p lang="en" dir="ltr">
                  I think it&#39;s entirely appropriate that the courier
                  delivery pin to receive my parcel from
                  <a href="https://twitter.com/CounterLove?ref_src=twsrc%5Etfw">@CounterLove</a>
                  was 911 because these limited edition Hertzoggie cookies are
                  criminally delicious!
                  <a href="https://t.co/1z52eA0Oor">pic.twitter.com/1z52eA0Oor</a>
                </p>
                &mdash; Nicole Engelbrecht (@the_pod_one)
                <a href="https://twitter.com/the_pod_one/status/1572841559812411392?ref_src=twsrc%5Etfw">September 22,
                  2022</a>
              </blockquote>
              <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
            </div>

          </main>
        </div>
      </div>
      <script src="js/jquery-3.2.1.min.js"></script>
  <script src="js/bootstrap.min.js"></script>
  <script src="js/scripts.js"></script>
    </div>
  )
}
