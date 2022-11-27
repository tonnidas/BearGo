import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';
import { useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';


export default function Register() {
    const navigate = useNavigate();
    const [inputs, setInputs] = useState({});

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({ ...values, [name]: value }))
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        console.log("Username: " + inputs.username);

        if (inputs.password != inputs.confirmPassword) {
            alert('Password did not matched');
            return;
        }

        try {
            const resp = await axios.post('/api/auth/register', {
                username: inputs.username,
                fullname: inputs.name,
                password: inputs.password,
                phoneNumber: inputs.phone,
                street: inputs.street,
                city: inputs.city,
                state: inputs.state,
                zip: inputs.zip,
                country: inputs.country
            });
            console.log(resp.data);
            alert('Registration succeed! Please login');
            navigate(urlPaths.login);
        } catch (error) {
            console.log(error);
            alert('Registration failed! Please try again');
        }
    }

    return (
        <div>
            <div className="container">
                <div className="row justify-content-center">
                    <div className="col-md-5">
                        <form className="user-form" onSubmit={handleSubmit}>
                            <div className="text-center">
                                <img src={logo_white} alt="" />
                            </div>
                            <div className="form-group">
                                <label>Name</label>
                                <input required type="text" className="form-control" placeholder="Jhon Doe" name='name' value={inputs.name || ""} onChange={handleChange} />
                            </div>
                            <div className="form-group">
                                <label>Email / Username</label>
                                <input required type="text" className="form-control" placeholder="jhon@example.com" name='username' value={inputs.username || ""} onChange={handleChange} />
                            </div>
                            <div className="form-group">
                                <label>Phone</label>
                                <input required type="text" className="form-control" placeholder="(XXX)-XXX-XXXX" name='phone' value={inputs.phone || ""} onChange={handleChange} />
                            </div>

                            <div className='row'>
                                <div className='col-md-6'>
                                    <div className='form-group'>
                                        <label>Street</label>
                                        <input required className='form-control' name='street' value={inputs.street} onChange={handleChange} />
                                    </div>
                                </div>

                                <div className='col-md-6'>
                                    <div className='form-group'>
                                        <label>City</label>
                                        <input required className='form-control' name='city' value={inputs.city} onChange={handleChange} />
                                    </div>
                                </div>

                                <div className='col-md-6'>
                                    <div className='form-group'>
                                        <label>State</label>
                                        <input required className='form-control' name='state' value={inputs.state} onChange={handleChange} />
                                    </div>
                                </div>

                                <div className='col-md-6'>
                                    <div className='form-group'>
                                        <label>Zip</label>
                                        <input required className='form-control' name='zip' value={inputs.zip} onChange={handleChange} />
                                    </div>
                                </div>
                            </div>

                            <div className="form-group">
                                <label>Password</label>
                                <input required type="password" className="form-control" placeholder="12345" name='password' value={inputs.password || ""} onChange={handleChange} />
                            </div>
                            <div className="form-group">
                                <label>Confirm Password</label>
                                <input required type="password" className="form-control" placeholder="12345" name='confirmPassword' value={inputs.confirmPassword || ""} onChange={handleChange} />
                            </div>

                            {/* <button type="button" onclick="showModal('mymodal')" className="common-btn btn-primary">Register</button> */}
                            <button type="submit" className="common-btn btn-primary">Register</button>
                        </form>
                    </div>
                </div>
            </div>


            {/*<div className="modal" id="mymodal">
                <div className="modal-inner">

                    <form className="">
                        <span className="close" onclick="hideModal('mymodal')">
                            <i className="icon-close"></i>
                        </span>
                        <h3 className="common-heading">OTP Verification</h3>
                        <div className="form-group">
                            <input type="hidden" name="_token" value="" />
                            <label className="form-label">Enter the six digit verification code</label>
                            <input className="form-control" type="text" name="otp" />
                        </div>
                        <p id="wrongcouponmsgareaV" style="display: none;" className="error-msg">The OTP you entered is not correct.
                            Please try again with correct OTP</p>
                        <div className="btn-group">
                            <a href="#" className="common-btn btn-back" id="resend-otp">Resend</a>
                            <button type="submit" className="common-btn" id="resend-otp-next">Next</button>
                        </div>
                    </form>

                </div>
            </div>*/}



            <script src="js/jquery-3.2.1.min.js"></script>
            <script src="js/bootstrap.min.js"></script>
            <script src="js/scripts.js"></script>
        </div>
    )
}