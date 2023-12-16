import React from "react";
import './style/App.css'
import { Route, Routes } from "react-router-dom";
import Nav from './components/Nav'
import SignIn from "./components/SignIn";
import SignUp from "./components/SignUp";
import MealKits from "./components/MealKits";
import Details from "./components/Details";

class App extends React.Component {
  render () {
    return (
        <React.Fragment>
            <Nav />
            <div className="App">
                  <Routes>
                      <Route path="/" element={<MealKits />}/>
                      <Route path="/signin" element={<SignIn />} />
                      <Route path="/signup" element={<SignUp />} />
                      <Route path="/detail/:id" element={<Details/>}/>
                  </Routes>
            </div>
        </React.Fragment>
    )
  }
}

export default App;
