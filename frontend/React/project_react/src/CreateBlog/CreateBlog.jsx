import './CreateBlog.css';

import React from 'react';
import Navbar from '../Components/Navbar';
import Sidebar from '../Components/Sidebar';
import image_man from '../images/man_avatar1.jpg';
import parcel_1 from '../images/parcel-1.jpg';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";
import urlPaths from '../urlPaths';
import { Country, State, City } from "country-state-city";
import axios from 'axios';
import Moment from 'react-moment';

export default function CreateBlog() {

  const navigate = useNavigate();
  const FormData = require('form-data');
  const [inputs, setInputs] = React.useState({});

  React.useEffect(() => {
    AuthService.setAxiosAuthHeader();
    axios.get("/api/users/current")
      .then(res => {
        console.log(res.data.fullname);
        setInputs(values => ({ ...values, ['user']: res.data.fullname }));
      })
      .catch((err) => {
        console.log(err);
        if (err.response.status === 401) {
          navigate(urlPaths.login);
        }
      });
  }, []);

  const handleChange = (event) => {
    const name = event.target.name;

    if (name === 'imageFile' && event.target.files && event.target.files[0]) {
      const file = event.target.files[0];
      const value = URL.createObjectURL(file);
      setInputs(values => ({ ...values, [name]: value, ['imageUploadFile']: file }));
    } else if (name === 'imageFile') {
      setInputs(values => ({ ...values, [name]: null, ['imageUploadFile']: null }));
    } else {
      const value = event.target.value;
      setInputs(values => ({ ...values, [name]: value }))
    }
  }

  const handleSubmit = async (event) => {
    event.preventDefault();
    // console.log(inputs);

    if (!inputs.imageUploadFile) {
      alert('Please select an image');
      return;
    }

    AuthService.setAxiosAuthHeader();

    try {
      // first upload the image and get image id
      let formData = new FormData();
      formData.append('file', inputs.imageUploadFile);
      const respImageUpload = await axios.post('/api/images/upload', formData);
      console.log('image id:' + respImageUpload.data.id);

      // blog post details
      const data = {
        description: inputs.productPostDescription,
        imageId: respImageUpload.data.id
      }

      const resp = await axios.post('/api/blogPosts/createBlogPost', data);
      console.log(resp.data);
      alert('Blog post created');
      navigate(urlPaths.home);

    } catch (error) {
      console.log(error);
      alert('Failed to create blog post');
    }
  }

  return (
    <>
      <Navbar />
      <div className='container-fluid'>
        <div className='row'>
          <Sidebar />

          <main role='main' className='main col-md-12 ml-sm-auto col-lg-9'>
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
                          <h4>{inputs.user || "John Doe"}</h4>
                          <span><Moment format="LLL">{Date.now()}</Moment></span>
                        </div>
                      </a>
                    </div>
                    <div className='widget-inner'>
                      <div className='post'>
                        <form onSubmit={handleSubmit}>

                          <div className='form-group'>
                            <label>Description</label>
                            <textarea required className='form-control' rows='5' name='productPostDescription' value={inputs.productPostDescription} onChange={handleChange}></textarea>
                          </div>

                          <div className='form-group'>
                            <img className='img-fluid' src={inputs.imageFile} alt='' />
                          </div>

                          <div className='form-group'>
                            <label className='upload-btn'>
                              <i className='icon-upload'></i> Upload Image
                              <input type='file' single name='imageFile' onChange={handleChange} />
                            </label>
                          </div>

                          <button type="submit" className='common-btn'>Post</button>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>

            </div>
          </main>
        </div>
      </div>
    </>
  );
}
