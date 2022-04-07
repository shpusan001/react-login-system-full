import "./App.css";
import RegisterPage from "./pages/RegisterPage";
import LoginPage from "./pages/LoginPage";
import PostListPage from "./pages/PostListPage";
import WritePage from "./pages/WritePage";
import { Route, Routes } from "react-router-dom";
import PostPage from "./pages/PostPage";

function App() {
  return (
    <>
      <Route component={PostListPage} path={["/@:username", "/"]} exact />
      <Route component={LoginPage} path="/login" />
      <Route component={RegisterPage} path="/register" />
      <Route component={WritePage} path="/write" />
      <Route component={PostPage} path="/@:username/:postId" />
    </>
  );
}

export default App;
