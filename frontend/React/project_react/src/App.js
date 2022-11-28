import { BrowserRouter, Route, Routes } from 'react-router-dom';

import MyPosts from './MyPosts/MyPosts';
import BlogPosts from './BlogPosts/BlogPosts';
import ContactSender from './ContactSender/ContactSender';
import ContractTraveller from './ContractTraveller/ContractTraveller';
import CreatePost from './CreatePost/CreatePost';
import CreateBlog from './CreateBlog/CreateBlog';
import FrontPage from './FrontPage/FrontPage';
import Login from './Login/Login';
import Register from './Register/Register';
import Profile from './Profile/Profile';
import Message from './Message/Message';
import urlPaths from './urlPaths';
import ProfileSearch from './ProfileSearch/ProfileSearch';
import UpdatePost from './UpdatePost/UpdatePost';
import ReviewPost from './ReviewPost/ReviewPost';
import MyBlogs from './MyBlogs/MyBlogs';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={urlPaths.home} element={<FrontPage />} />
        <Route path={urlPaths.blog} element={<BlogPosts />} />
        <Route path={urlPaths.myPosts} element={<MyPosts />} />
        <Route path={urlPaths.myBlog} element={<MyBlogs />} />
        <Route path={urlPaths.createPost} element={<CreatePost />} />
        <Route path={urlPaths.createBlog} element={<CreateBlog />} />
        <Route path={urlPaths.contactSender} element={<ContactSender />} />
        <Route path={urlPaths.contractTraveller} element={<ContractTraveller />} />
        <Route path={urlPaths.register} element={<Register />} />
        <Route path={urlPaths.login} element={<Login />} />
        <Route path={urlPaths.profile} element={<Profile />} />
        <Route path={urlPaths.profileSearch} element={<ProfileSearch />} />
        <Route path={urlPaths.message} element={<Message />} />
        <Route path={urlPaths.updatePost} element={<UpdatePost />} />
        <Route path={urlPaths.reviewPost} element={<ReviewPost />} />
      </Routes>
    </BrowserRouter>

  );
}

export default App;
