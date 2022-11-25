
import { useState } from 'react';
import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';
import axios from 'axios';

export default function Login() {    
    const [inputs, setInputs] = useState({});

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({...values, [name]: value}))
    }

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log("Username: " + inputs.username);

        axios.post('/api/auth/login', {
            username: inputs.username,
            password: inputs.password
        })
        .then(function (response) {
            console.log(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });
    }

    
    return(
        <div>
            <div className="container">
        <div className="row justify-content-center">
            <div className="col-md-5">
                <form className="user-form" onSubmit={handleSubmit}>
                    <div className="text-center">
                        <img src={logo_white} alt="" />
                    </div>
                    <div className="form-group">
                        <label>Username</label>
                        <input type="text" className="form-control" placeholder="Jhon" name='username' value={inputs.username || ""} onChange={handleChange} />
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" className="form-control" placeholder="*****" name='password' value={inputs.password || ""} onChange={handleChange} />
                    </div>

                    <div className="form-group">
                        <div className="row justify-content-between">
                            <div className="col">
                                <a href={urlPaths.register} className="sm-link fr gray">
                                    <i className="icon-person"></i> Create Account</a>
                            </div>
                            <div className="col text-right">
                                <a href="#" className="sm-link fr gray"><i className="icon-help"></i> forgot password?</a>
                            </div>
                        </div>
                    </div>

                    <button type="submit" className="common-btn btn-primary">Login</button>
                </form>
            </div>
        </div>
    </div>


    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
        </div>
    )
}