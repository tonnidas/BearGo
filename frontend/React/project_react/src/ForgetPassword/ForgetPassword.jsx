import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';
import { useEffect, useState } from 'react';
import { useNavigate } from "react-router-dom";
import axios from 'axios';
import AuthService from '../Service/AuthService';


export default function ForgetPassword() {
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

        if (inputs.password !== inputs.confirmPassword) {
            alert('Password did not matched');
            return;
        }

        try {
            const resp = await axios.post('/api/auth/sendCode?email=' + inputs.username);
            console.log(resp.data);
        } catch (error) {
            console.log(error);
            alert('Failed to send verification email');
            return;
        }

        const verificationCode = prompt("Enter verfication code sent to your email", "");
        if (verificationCode === null) {
            return;
        }

        try {
            const resp = await axios.post('/api/auth/resetPassword?email=' + inputs.username + '&newPassword=' + inputs.password + '&code=' + verificationCode);
            console.log(resp.data);
            alert('Password reset completed! Please login');
            navigate(urlPaths.login);
        } catch (error) {
            console.log(error);
            alert('Password reset failed, reason: ' + error.response.data.message);
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
                                <label>Email / Username</label>
                                <input required type="text" className="form-control" placeholder="jhon@example.com" name='username' value={inputs.username || ""} onChange={handleChange} />
                            </div>
                            <div className="form-group">
                                <label>New Password</label>
                                <input required type="password" className="form-control" placeholder="12345" name='password' value={inputs.password || ""} onChange={handleChange} />
                            </div>
                            <div className="form-group">
                                <label>Confirm Password</label>
                                <input required type="password" className="form-control" placeholder="12345" name='confirmPassword' value={inputs.confirmPassword || ""} onChange={handleChange} />
                            </div>

                            <button type="submit" className="common-btn btn-primary">Reset</button>
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