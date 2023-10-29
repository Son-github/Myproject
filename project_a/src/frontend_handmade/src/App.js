import React, { useEffect, useState } from "react";
import './style/App.css'
import { Route, Routes } from "react-router-dom";
import Home from './components/Home'
import Place from './components/Place'
import Nav from './components/Nav'
import SignIn from "./components/SignIn";
import Contact from "./components/Contact";
import SignUp from "./components/SignUp";
import Checkout from "./components/CheckOut";

function java() {
    const [data, setData] = useState('');

    useEffect(() => {
        fetch('/api/main')
            .then(response => response.text())
            .then(data => setData(data))
            .catch(error => console.error(error));
    }, []);

    return (
        <p>{data}</p>
    )
}
class App extends React.Component {
  render () {
    return (
        <React.Fragment>
            <Nav />
            <div className="App">
                  <Routes>
                    <Route path="/home" element={<Home />}/>
                    <Route path="/contact" element={<Contact />} />
                    <Route path="/checkout" element={<Checkout />} />
                    <Route path="/place" element={<Place />} />
                    <Route path="/signin" element={<SignIn />} />
                    <Route path="/signup" element={<SignUp />} />
                  </Routes>
            </div>
        </React.Fragment>
    )
  }
}

export default App;
