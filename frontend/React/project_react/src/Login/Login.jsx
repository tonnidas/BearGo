import "./Login.css"

import logo_white from '../images/logo-white.svg';
import urlPaths from '../urlPaths';

export default function Login() {
    let url = ""
    return(
        <div>
            <div className="container">
        <div className="row justify-content-center">
            <div className="col-md-5">
                <form className="user-form">
                    <div className="text-center">
                        <img src={logo_white} alt="" />
                    </div>
                    <div className="form-group">
                        <label>Username</label>
                        <input type="text" className="form-control" placeholder="Jhon" />
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" className="form-control" placeholder="*****" />
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