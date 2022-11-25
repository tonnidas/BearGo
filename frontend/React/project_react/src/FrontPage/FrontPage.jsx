import './FrontPage.css';

import React, { useEffect, useState } from 'react';

import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';
import Widget from '../Components/Widget';

export default function FrontPage() {
  const [posts, setPosts] = useState([]);

  var _headers = {
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json',
      'Authorization': 'Bearer ' + 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2VyMDFAYmVhcmdvLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2NjkzNDEzMjYsImV4cCI6MTY2OTM2MjkyNn0.1T999dy4VGZkdAJdM8tYkLGyBj9K277GPUTcYO1JML0'
    }
  };

  useEffect(() => {
    // axios.get("http://localhost:8080/allContest")
    fetch('api/productPosts/getAllProductPost', _headers)
      .then((res) => res.json())
      .then((res) => {
        console.log(res);
        setPosts(res);
      });
  }, []);
  return (
    <div>
      {/* <!-- Bootstrap CSS --> */}
      <title>Dashboard</title>

      <Navbar />

      <div className='container-fluid'>
        <div className='row'>
          <Sidebar />

          <main role='main' className='main col-md-12 ml-sm-auto col-lg-9'>
            <div className='row' style={{ position: 'relative' }}>
              <div className='col-md-8'>
                <div className='main-inner'>
                  {posts.map(post => <Widget post={post} key={post.id} />
                  )}
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
      <script src='js/jquery-3.2.1.min.js'></script>
      <script src='js/bootstrap.min.js'></script>
      <script src='js/scripts.js'></script>
    </div>
  );
}
