

import logo_white from '../images/logo-white.svg';

import urlPaths from '../urlPaths';


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


    {/* <div className="modal" id="mymodal">
        <div className="modal-inner">

            <form className="">
                <span className="close" onclick="hideModal('mymodal')">
                    <i className="icon-close"></i>
                </span>
                <h3 className="common-heading">OTP Verification</h3>
                <div className="form-group">
                    <input type="hidden" name="_token" value=""/>
                    <label className="form-label">Enter the six digit verification code</label>
                    <input className="form-control" type="text" name="otp"/>
                </div>
                <p id="wrongcouponmsgareaV" style="display: none;" className="error-msg">The OTP you entered is not correct.
                    Please try again with correct OTP</p>
                <div className="btn-group">
                    <a href="#" className="common-btn btn-back" id="resend-otp">Resend</a>
                    <button type="submit" className="common-btn" id="resend-otp-next">Next</button>
                </div>
            </form>



        </div>
    </div> */}


   
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
        </div>
    )
}