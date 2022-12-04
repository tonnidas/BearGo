import './CreatePost.css';

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
import ProfileImage from '../Service/Helper';

export default function CreatePost() {

  const navigate = useNavigate();
  const FormData = require('form-data');
  const [inputs, setInputs] = React.useState({'user':{}});

  React.useEffect(() => {
    AuthService.setAxiosAuthHeader();
    axios.get("/api/users/current")
      .then(res => {
        console.log(res.data.fullname);
        setInputs(values => ({ ...values, ['user']: res.data }));
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

      // product post details
      const data = {
        description: inputs.productPostDescription,
        imageId: respImageUpload.data.id,
        expectedPickupDate: inputs.pickupDate,
        expectedDeliveryDate: inputs.deliveryDate,
        source: {
          street: inputs.sourceStreet,
          city: inputs.sourceCity,
          state: inputs.sourceState,
          zip: inputs.sourceZip,
          country: inputs.sourceCountry
        },
        destination: {
          street: inputs.destStreet,
          city: inputs.destCity,
          state: inputs.destState,
          zip: inputs.destZip,
          country: inputs.destCountry
        },
        product: {
          description: inputs.productPostDescription,
          weight: inputs.weight
        }
      }

      const resp = await axios.post('/api/productPosts', data);
      console.log(resp.data);
      alert('Product post created');
      navigate(urlPaths.home);

    } catch (error) {
      console.log(error);
      alert('Failed to create product post, reason: ' + error.response.data.message);
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
                          <img className='mask-img' src={ProfileImage(inputs.user)} alt='' />
                          <svg>
                            <use href='#icon-mask'></use>
                          </svg>
                        </div>
                        <div className='user-avatar-name'>
                          <h4>{inputs.user.fullname || "John Doe"}</h4>
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
                            <label>Weight (LBS)</label>
                            <input required type="number" min="1" className='form-control' name='weight' value={inputs.weight} onChange={handleChange} />
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

                          <div className='row'>
                            <div className='col-md-6'>
                              <div className='form-group'>
                                <label>Expected Pickup Date</label>
                                <input required className='form-control' type='date' name='pickupDate' value={inputs.pickupDate} onChange={handleChange} />
                              </div>
                            </div>
                            <div className='col-md-6'>
                              <div className='form-group'>
                                <label>Expected Delivery Date</label>
                                <input required className='form-control' type='date' name='deliveryDate' value={inputs.deliveryDate} onChange={handleChange} />
                              </div>
                            </div>
                          </div>

                          <h5>Source Location</h5>

                          <div className='row'>
                            <div className='col-md-6'>
                              <div className='form-group'>
                                <label>Street</label>
                                <input required className='form-control' name='sourceStreet' value={inputs.sourceStreet} onChange={handleChange} />
                              </div>
                            </div>

                            <div className='col-md-6'>
                              <div className='form-group'>
                                <label>City</label>
                                <input required className='form-control' name='sourceCity' value={inputs.sourceCity} onChange={handleChange} />
                              </div>
                            </div>

                            <div className='col-md-4'>
                              <div className='form-group'>
                                <label>State</label>
                                <input required className='form-control' name='sourceState' value={inputs.sourceState} onChange={handleChange} />
                              </div>
                            </div>

                            <div className='col-md-4'>
                              <div className='form-group'>
                                <label>Zip</label>
                                <input required className='form-control' name='sourceZip' value={inputs.sourceZip} onChange={handleChange} />
                              </div>
                            </div>

                            <div className='col-md-4'>
                              <div className='form-group'>
                                <label>Country</label>
                                <input required className='form-control' name='sourceCountry' value={inputs.sourceCountry} onChange={handleChange} />
                              </div>
                            </div>
                          </div>

                          <h5>Destination Location</h5>

                          <div className='row'>
                            <div className='col-md-6'>
                              <div className='form-group'>
                                <label>Street</label>
                                <input required className='form-control' name='destStreet' value={inputs.destStreet} onChange={handleChange} />
                              </div>
                            </div>

                            <div className='col-md-6'>
                              <div className='form-group'>
                                <label>City</label>
                                <input required className='form-control' name='destCity' value={inputs.destCity} onChange={handleChange} />
                              </div>
                            </div>

                            <div className='col-md-4'>
                              <div className='form-group'>
                                <label>State</label>
                                <input required className='form-control' name='destState' value={inputs.destState} onChange={handleChange} />
                              </div>
                            </div>

                            <div className='col-md-4'>
                              <div className='form-group'>
                                <label>Zip</label>
                                <input required className='form-control' name='destZip' value={inputs.destZip} onChange={handleChange} />
                              </div>
                            </div>

                            <div className='col-md-4'>
                              <div className='form-group'>
                                <label>Country</label>
                                <input required className='form-control' name='destCountry' value={inputs.destCountry} onChange={handleChange} />
                                {/* <div className='select-style'>
                                  <select name='destCountry' value={inputs.destCountry} onChange={handleChange}>
                                    {Country.getAllCountries().map((option) => (<option>{option.name}</option>));}
                                  </select>
                                </div> */}
                              </div>
                            </div>
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
