import "./Register.css"

import logo_white from '../images/logo-white.svg';

export default function Register() {
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
                        <label>Phone</label>
                        <input type="text" className="form-control" placeholder="(XXX)-XXX-XXXX)" />
                    </div>
                    <div className="form-group">
                        <label>Email</label>
                        <input type="text" className="form-control" placeholder="jhon@xyz.com" />
                    </div>
                    <div className="form-group">
                        <label>Social Security Number</label>
                        <input type="text" className="form-control" placeholder="XXX)-XXX-XXXX" />
                    </div>
                    <div className="form-group">
                        <label>Password</label>
                        <input type="password" className="form-control" placeholder="12345" />
                    </div>


                    <button type="button" onclick="showModal('mymodal')"
                        className="common-btn btn-primary">Register</button>
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