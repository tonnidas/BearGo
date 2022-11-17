import FrontPage from "./FrontPage/FrontPage";
import CreatePost from "./CreatePost/CreatePost";
import ContactSender from "./ContactSender/ContactSender";
import { Route, Routes, BrowserRouter } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
     <Routes>
      <Route path="/" element={<FrontPage />} />
      <Route path="/createPost" element={<CreatePost />} />
      <Route path="/contactSender" element={<ContactSender />} />
        </Routes> 
        </BrowserRouter>
  //  <FrontPage/>
   
  );
}

export default App;
