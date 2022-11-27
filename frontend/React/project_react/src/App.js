import { BrowserRouter, Route, Routes } from 'react-router-dom';

import MyPosts from './MyPosts/MyPosts';
import ContactSender from './ContactSender/ContactSender';
import ContractTraveller from './ContractTraveller/ContractTraveller';
import CreatePost from './CreatePost/CreatePost';
import FrontPage from './FrontPage/FrontPage';
import Login from './Login/Login';
import Register from './Register/Register';
import Profile from './Profile/Profile';
import urlPaths from './urlPaths';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={urlPaths.home} element={<FrontPage />} />
        <Route path={urlPaths.createPost} element={<CreatePost />} />
        <Route path={urlPaths.contactSender} element={<ContactSender />} />
        <Route path={urlPaths.contractTraveller} element={<ContractTraveller />} />
        <Route path={urlPaths.register} element={<Register />} />
        <Route path={urlPaths.login} element={<Login />} />
        <Route path={urlPaths.profile} element={<Profile />} />
      </Routes>
    </BrowserRouter>

  );
}

export default App;
