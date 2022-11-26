import './ContactSender.css';

import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';
import image_man from '../images/man_avatar1.jpg';
import parcel_1 from '../images/parcel-1.jpg';

import urlPaths from '../urlPaths';

export default function ContactSender() {

  const handleClick = (e) => {
    
  };
  return (
    <>
      <Navbar />
    
      <div className='container-fluid'>
        <div className='row'>
          <Sidebar />

          <main role='main' className='main col-md-12 ml-sm-auto col-lg-9'>
          <div className='topnav'>
          <button className='common-btn global' onClick={handleClick}>
            <i className='icon'></i> <span>My Post</span>
          </button>

          <button className='common-btn global' onClick={handleClick}>
            <i className='icon'></i> <span>In-transit</span>
          </button>

          <button className='common-btn global' onClick={handleClick}>
            <i className='icon'></i> <span>Completed</span>
          </button>

          <button className='common-btn global' onClick={handleClick}>
            <i className='icon'></i> <span>Interested People</span>
          </button>

        {/* <a class="active" href="#posts" >My Posts</a>
        <a href="#interested">Interested Travellers</a>
        <a href="#completed">Completed</a>
        <a href="#intransit">In-Transit</a> */}
      </div>
            <div className='row' style={{ position: 'relative' }}>
              <div className='col-md-8'>
                <div className='main-inner'>
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
                          <h4>Nusa Penida</h4>
                          <span>12:53 PM · Sep 22, 2022</span>
                        </div>
                      </a>
                    </div>
                    <div className='widget-inner'>
                      <div className='post post-contact'>
                        <form action='#'>
                          <div className='form-group'>
                            <label>Description</label>
                            <p>
                              I think it's entirely appropriate that the courier
                              delivery pin to receive my parcel from
                              @CounterLove was 911 because these limited edition
                              Hertzoggie cookies are criminally delicious!
                            </p>
                          </div>
                          <div className='form-group'>
                            <img className='img-fluid' src={parcel_1} alt='' />
                          </div>

                          <div className='row'>
                            <div className='col-md-6'>
                              <div className='form-group'>
                                <label>Start Date</label>
                                <p>Sep 22, 2022</p>
                              </div>
                            </div>
                            <div className='col-md-6'>
                              <div className='form-group'>
                                <label>End Date</label>
                                <p>Sep 22, 2022</p>
                              </div>
                            </div>
                            <div className='col-md-6'>
                              <label>Source Location</label>
                              <p>New york</p>
                            </div>
                            <div className='col-md-6'>
                              <label>Destination Location</label>
                              <p>Los Angeles</p>
                            </div>
                          </div>

                          <div className='form-group'>
                            <label className='checkbox'>
                              <input type='checkbox' name='terms' value='' />
                              <span className='checkmark'></span>
                              Terms & conditions
                            </label>
                          </div>
                          <button className='common-btn'>Submit</button>
                        </form>
                      </div>
                    </div>
                  </div>
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
    </>
  );
}
