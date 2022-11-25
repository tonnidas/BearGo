import { BrowserRouter, Route, Routes } from 'react-router-dom';

import ContactSender from './ContactSender/ContactSender';
import CreatePost from './CreatePost/CreatePost';
import FrontPage from './FrontPage/FrontPage';
import Login from './Login/Login';
import Register from './Register/Register';
import urlPaths from './urlPaths';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={urlPaths.home} element={<FrontPage />} />
        <Route path={urlPaths.createPost} element={<CreatePost />} />
        <Route path={urlPaths.contactSender} element={<ContactSender />} />
        <Route path={urlPaths.register} element={<Register />} />
        <Route path={urlPaths.login} element={<Login />} />
      </Routes>
    </BrowserRouter>

  );
}

export default App;
