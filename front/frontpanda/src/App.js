import React from "react";
import logo from './fp.png';
import './App.css';
import {Component} from "react";
import { Button } from 'react-native';
import Home from "./Home";
import Navbar from './containers/Navbar';
import {
  BrowserRouter as Router,
  Routes,
  Route,
  Redirect,
  useNavigate
} from "react-router-dom";

import Login from "./containers/Login";
import LoginU from "./containers/LoginU";
import RegistrationForm from "./containers/Register";
import Restaurant from "./containers/Restaurant";
import RestaurantU from "./containers/RestaurantU";
import AdminProf from "./containers/adminProf";
import AddRest from "./containers/addRest";
import Menu from "./containers/Menu";
import UserProf from "./containers/userProf";
import MenuU from "./containers/MenuU";
import AddFood from "./containers/addFood";
import CrMenu from "./containers/crMenu";
import VOrders from "./containers/vOrders";
import Status from "./containers/Status";
import SOrders from "./containers/sOrders";
import Cart from "./containers/cart";
import Place from "./containers/place";
import History from "./containers/History";
class App extends Component {
  constructor(props) {
    super(props);

  }
  render() {
      function clickMe(){
          alert('you clicked me');
      }
      
    return ( 
      <div style={styles.app}>
      <Router>
        
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/loginU" element={<LoginU />} />
          <Route path="/register" element={<RegistrationForm />} />
          <Route path="/restaurant" element={<Restaurant />} />
          <Route path="/adminprof" element={<AdminProf />} />
          <Route path="/addrest" element={<AddRest />} />
          <Route path="/viewm" element={<Menu />} />
          <Route path="/userprof" element={<UserProf />} />
          <Route path="/viewmU" element={<MenuU />} />
          <Route path="/restaurantU" element={<RestaurantU />} />
          <Route path="/addfood" element={<AddFood />} />
          <Route path="/crmenu" element={<CrMenu />} />
          <Route path="/ordersv" element={<VOrders />} />
          <Route path="/accdec" element={<Status />} />
          <Route path="/sorders" element={<SOrders />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/place" element={<Place />} />
          <Route path="/history" element={<History />} />
        </Routes>
      </Router>
    </div>
    );
  }
}

export default App;

const styles = {
  app: {
    padding: 50,
  },
};