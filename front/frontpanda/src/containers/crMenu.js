import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import Navbar from './Navbar';
import {useLocation} from 'react-router-dom';
function CrMenu() {
    
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");
    const [data, setData] = useState(["No data"]);
    function handleSubmit(event) {
      event.preventDefault();
      var { uname, pass} = document.forms[0];
      const params = new URLSearchParams({
        restaurant: pass.value,
      }).toString();

      const url =
       "http://localhost:8080/menu/save?" + params;

      axios.post(url,{name: uname.value})
      .then(response =>{
        setData(response.data);
      })
      .catch(({ response }) => { 
          setMessage("Enter a valid Id");
      })
    }
  
    return (
      <div><Navbar/>
      <div className="form">
      <form onSubmit= {handleSubmit}>
          <div className="input-container">
              <label>Menu Name </label>
              <input type="text" name="uname" required />
              
          </div>
          <div className="input-container">
              <label>Restaurant Id </label>
              <input type="number" name="pass" required />
          
          </div>
          <div>
              <label>{errorMessage} </label>
          </div>
          <div className="button-container">
              <input type="submit" />
          </div>
      </form>
      </div>
      </div>
    );
}

export default CrMenu;