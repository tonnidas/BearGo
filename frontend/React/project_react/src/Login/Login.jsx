import { useEffect, useState } from 'react';
import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';
import AuthService from '../Service/AuthService';
import { useNavigate } from "react-router-dom";

export default function Login() {
    const navigate = useNavigate();
    const [inputs, setInputs] = useState({});

    useEffect(() => {
        AuthService.logout();
    }, []);

    const handleChange = (event) => {
        const name = event.target.name;
        const value = event.target.value;
        setInputs(values => ({ ...values, [name]: value }))
    }

    const handleSubmit = async (event) => {
        event.preventDefault();
        console.log("Username: " + inputs.username);
        const authenticated = await AuthService.login(inputs.username, inputs.password);
        if (authenticated === true) {
            navigate(urlPaths.home);
        } else {
            alert('Login failed! Please check your username and password');
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
                            <br/>
                            <div className="form-group">
                                <label>Email / Username</label>
                                <input type="text" className="form-control" placeholder="user@example.com" name='username' value={inputs.username || ""} onChange={handleChange} />
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
                                        <a href={urlPaths.forgetPassword} className="sm-link fr gray">
                                        <i className="icon-help"></i> forgot password?</a>
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