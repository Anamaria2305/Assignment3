import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import Navbar from './Navbar';
import {useLocation} from 'react-router-dom';
function AddFood() {
   
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");
    const [data, setData] = useState(["No data"]);
    function handleSubmit(event) {
        event.preventDefault();
        var { uname,pass,pr,cat,men} = document.forms[0];
        const params = new URLSearchParams({
            category: cat.value,
          }).toString();
          const params2 = new URLSearchParams({
            menu: men.value,
          }).toString();
        const url =
       "http://localhost:8080/food/save?" + params+ "&" +params2;
        axios.post(url,{ name: uname.value, description: pass.value,price: pr.value })
        .then(response =>{
        
          setData(response.data);
           
        })
        .catch(({ response }) => { 
            if(typeof  response=="undefined")
            setMessage("Succes");

            else
            setMessage("Enter a valid menu name");
            
        })
      }
  
    return (
        <div><Navbar/>
      <div className="form">
      <form onSubmit= {handleSubmit}>
          <div className="input-container">
              <label>Name </label>
              <input type="text" name="uname" required />
              
          </div>
          <div className="input-container">
              <label>Description </label>
              <input type="text" name="pass" required />
          
          </div>
          <div className="input-container">
              <label>Price </label>
              <input type="number" step="0.1"  name="pr" required onChange={(event) =>
        event.target.value < 0
            ? (event.target.value = 0)
            : event.target.value
    } />
          
          </div>
          <div className="input-container">
              <label>Category </label>
              <select name="cat" required>
                                <option value="drinks">Drinks</option>
                                <option value="sweets">Sweets</option>
                                <option value="soup">Soup</option>
                                <option value="lunch">Lunch</option>
                                <option value="snack">Snack</option>
                               
                            </select>
          </div>
          <div className="input-container">
              <label>Menu </label>
              <input type="text" name="men" required />
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

export default AddFood;