import FrontPage from "./FrontPage/FrontPage";
import CreatePost from "./CreatePost/CreatePost";
import ContactSender from "./ContactSender/ContactSender";
import Register from "./Register/Register";
import Login from "./Login/Login";
import { Route, Routes, BrowserRouter } from "react-router-dom";
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
  //  <FrontPage/>
   
  );
}

export default App;
