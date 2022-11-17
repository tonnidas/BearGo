import FrontPage from "./FrontPage/FrontPage";
import CreatePost from "./CreatePost/CreatePost";
import { Route, Routes, BrowserRouter } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
     <Routes>
      <Route path="/" element={<FrontPage />} />
          <Route path="/createPost" element={<CreatePost />} />
          
        </Routes> 
        </BrowserRouter>
  //  <FrontPage/>
   
  );
}

export default App;
