import React from "react";
import './style/App.css'
import { Route, Routes } from "react-router-dom";
import Home from './components/Home'
import Nav from './components/Nav'
import SignIn from "./components/SignIn";
import SignUp from "./components/SignUp";

class App extends React.Component {
  render () {
    return (
        <React.Fragment>
            <Nav />
            <div className="App">
                  <Routes>
                    <Route path="/home" element={<Home />}/>
                    <Route path="/signin" element={<SignIn />} />
                    <Route path="/signup" element={<SignUp />} />
                  </Routes>
            </div>
        </React.Fragment>
    )
  }
}

export default App;
