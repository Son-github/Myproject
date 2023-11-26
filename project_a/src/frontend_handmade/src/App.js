import React from "react";
import './style/App.css'
import { Route, Routes } from "react-router-dom";
import Nav from './components/Nav'
import SignIn from "./components/SignIn";
import SignUp from "./components/SignUp";
import MealKits from "./components/MealKits";

class App extends React.Component {
  render () {
    return (
        <React.Fragment>
            <Nav />
            <div className="App">
                  <Routes>
                    <Route path="/MealKits" element={<MealKits />}/>
                    <Route path="/signin" element={<SignIn />} />
                    <Route path="/signup" element={<SignUp />} />
                  </Routes>
            </div>
        </React.Fragment>
    )
  }
}

export default App;
