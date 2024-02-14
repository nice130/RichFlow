import React from 'react';


import {Route, Routes} from "react-router-dom";
import Home from "./components/Home.jsx";
import Welcome from "./components/Welcome.jsx";
import UserLogin from "./components/UserLogin.jsx";
import UserJoin from "./components/UserJoin.jsx";
import NotFound from "./components/NotFound.jsx";



const App = () => {
    return (
        <Routes>
            <Route path="/" element={<Welcome/>}/>
            <Route path="/home" element={<Home/>}/>
            <Route path="/login" element={<UserLogin/>}/>
            <Route path="/join" element={<UserJoin/>}/>
            <Route path="*" element={<NotFound/>}/>
        </Routes>
    );
};
export default App;

