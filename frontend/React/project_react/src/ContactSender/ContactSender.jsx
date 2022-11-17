import "./ContactSender.css"


import image_man from "../images/man_avatar1.jpg"
import image_woman from "../images/women_avatar1.jpg"
import logo_white from "../images/logo-white.svg"
import parcel_1 from "../images/parcel-1.jpg"
import parcel_2 from "../images/parcel-2.jpg"
import favicon from "../images/favicon.ico"
import admin from "../images/admin.jpg"

export default function ContactSender() {
    let url = ""
    return(
        <div>
            
    <nav class="navbar navbar-expand-lg navbar-light fixed-top">
        <div class="col-md-3 sidebar-top">
            <a class="navbar-brand" href={url}><img src={logo_white} alt="" />
            </a>
        </div>
        <span class="sidebar-toggler">
            <i class="icon-more"></i>
        </span>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-expanded="false"
            aria-label="Toggle navigation">
            <i class="icon-bold"></i>
        </button>

        <div class="collapse navbar-collapse" id="navbar">
            <ul class="navbar-nav">
                <li class="nav-item dropdown search">
                    <a class="input-search dropdown-toggle" href={url} id="navbarDropdown" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"></a>
                    <div class="search-area dropdown-menu" aria-labelledby="navbarDropdown">
                        <form action="#">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>Start Date</label>
                                        <input class="form-control" type="date" name="" value="" />
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group">
                                        <label>End Date</label>
                                        <input class="form-control" type="date" name="" value="" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Source Location</label>
                                <div class="select-style">
                                    <select >
                                        <option value="">New York</option>
                                        <option value="">Los Angeles</option>
                                        <option value="">Chicago</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Destination Location</label>
                                <div class="select-style">
                                    <select >
                                        <option value="">New York</option>
                                        <option value="">Los Angeles</option>
                                        <option value="">Chicago</option>
                                    </select>
                                </div>
                            </div>
                            <button class="common-btn">Search</button>
                        </form>
                    </div>
                </li>

                <li class="nav-item dropdown ml-auto">
                    <a class="nav-link dropdown-toggle" href={url} id="navbarDropdown" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="icon-notifications"></i>
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <h3>Notifications</h3>
                        <div class="notifications">
                            <p class="unread">
                                Your account has been restricted from advertising.
                            </p>
                            <p>Your account has been restricted from advertising.</p>
                        </div>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href={url} id="navbarDropdown" role="button"
                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <img src={admin} alt="" />
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <h3>Jhon Doe</h3>
                        <a class="dropdown-item" href="#">Profile</a>
                        <a class="dropdown-item" href="#">Logout</a>
                    </div>
                </li>
            </ul>
        </div>
    </nav>

    <div class="container-fluid">
        <div class="row">
            <nav class="col-md-3 d-md-block sidebar">
                <div class="sidebar-sticky">
                    <div>
                        <ul class="sidebar-menu">
                            <li class="active">
                                <a href={url}> <i class="icon-home"></i><span>Home </span> </a>
                            </li>
                            <li>
                                <a href={url}>
                                    <i class="icon-message-square"></i><span>message </span>
                                </a>
                            </li>
                            <li>
                                <a href={url}>
                                    <i class="icon-settings"></i><span>Settings </span>
                                </a>
                            </li>
                            <li>
                                <a href={url}>
                                    <i class="icon-phone"></i><span>My Contact </span>
                                </a>
                            </li>
                            <li>
                                <a href={url}>
                                    <i class="icon-clipboard"></i><span>My blog </span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <button class="common-btn global-add" onclick="showModal('addRule')">
                        <i class="icon-plus"></i> <span>New Post</span>
                    </button>
                </div>
            </nav>

            <main role="main" class="main col-md-12 ml-sm-auto col-lg-9">
                <div class="row" style="position: relative">
                    <div class="col-md-8">
                        <div class="main-inner">
                            <div class="widget">
                                <div class="widget-head">
                                    <a href="#" class="user-avatar">
                                        <div class="mask">
                                            <img class="mask-img" src={image_man} alt="" />
                                            
                                        </div>
                                        <div class="user-avatar-name">
                                            <h4>Nusa Penida</h4>
                                            <span>12:53 PM · Sep 22, 2022</span>
                                        </div>
                                    </a>
                                </div>
                                <div class="widget-inner">
                                    <div class="post post-contact">
                                        <form action="#">
                                            <div class="form-group">
                                                <label>Description</label>
                                                <p>
                                                    I think it's entirely appropriate that the courier
                                                    delivery pin to receive my parcel from @CounterLove
                                                    was 911 because these limited edition Hertzoggie
                                                    cookies are criminally delicious!
                                                </p>
                                            </div>
                                            <div class="form-group">
                                                <img class="img-fluid" src={parcel_1} alt="" />
                                            </div>
                                           
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>Start Date</label>
                                                        <p>Sep 22, 2022</p>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group">
                                                        <label>End Date</label>
                                                        <p>Sep 22, 2022</p>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <label>Source Location</label>
                                                    <p>New york</p>
                                                </div>
                                                <div class="col-md-6">
                                                    <label>Destination Location</label>
                                                    <p>Los Angeles</p>
                                                </div>
                                            </div>
                                            
                                            <div class="form-group">
                                                <label class="checkbox">
                                                    <input type="checkbox" name="terms" value="" />
                                                    <span class="checkmark"></span>
                                                    Terms & conditions
                                                </label>
                                            </div>
                                            <button class="common-btn">Submit</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="twitter-feed">
                            <blockquote class="twitter-tweet" style="margin-bottom: 10px">
                                <p lang="en" dir="ltr">
                                    I think it&#39;s entirely appropriate that the courier
                                    delivery pin to receive my parcel from
                                    <a href="https://twitter.com/CounterLove?ref_src=twsrc%5Etfw">@CounterLove</a>
                                    was 911 because these limited edition Hertzoggie cookies are
                                    criminally delicious!
                                    <a href="https://t.co/1z52eA0Oor">pic.twitter.com/1z52eA0Oor</a>
                                </p>
                                &mdash; Nicole Engelbrecht (@the_pod_one)
                                <a
                                    href="https://twitter.com/the_pod_one/status/1572841559812411392?ref_src=twsrc%5Etfw">September
                                    22, 2022</a>
                            </blockquote>
                            <blockquote class="twitter-tweet" style="margin-bottom: 10px">
                                <p lang="en" dir="ltr">
                                    I think it&#39;s entirely appropriate that the courier
                                    delivery pin to receive my parcel from
                                    <a href="https://twitter.com/CounterLove?ref_src=twsrc%5Etfw">@CounterLove</a>
                                    was 911 because these limited edition Hertzoggie cookies are
                                    criminally delicious!
                                    <a href="https://t.co/1z52eA0Oor">pic.twitter.com/1z52eA0Oor</a>
                                </p>
                                &mdash; Nicole Engelbrecht (@the_pod_one)
                                <a
                                    href="https://twitter.com/the_pod_one/status/1572841559812411392?ref_src=twsrc%5Etfw">September
                                    22, 2022</a>
                            </blockquote>
                            <script async src="https://platform.twitter.com/widgets.js" charset="utf-8"></script>
                        </div>
                    </div>
                </div>
            </main>
        </div>
    </div>
    
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/scripts.js"></script>
        </div>
    )
}