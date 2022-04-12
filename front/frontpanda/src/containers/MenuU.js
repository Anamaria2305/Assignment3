import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import "./Restaurant.css";
import NavbarU from './NavbarU';
function MenuU() {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");
    const [data, setData] = useState(["No data"]);
    const [data2, setData2] = useState(["No data"]);
    function handleSubmit(event) {
        event.preventDefault();
        var { uname} = document.forms[0];
        const params = new URLSearchParams({
            restaurant: uname.value
          }).toString();
        const url =
       "http://localhost:8080/menu/restaurant?" + params;
        axios.post(url,{})
        .then(response =>{
        
          setData(response.data);
          setData2(response.data["food"]);
           
        })
        .catch(({ response }) => { 
            if(typeof  response=="undefined")
            setMessage("Succes");

            else
            setMessage("Failed");
            
        })
      }

function tryhard(Object){
    
    if (typeof Object === 'undefined') 

    return  <td>
        
    </td>
    else
    return  <td>{
                                
    Object.name                
    }</td>
    
}
    return (
        <div>
            <NavbarU />
            <div>
            <h1>{data.name} </h1>
             
            <table className="myAwesomeTable">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Category</th>
                      
                    </tr>
                </thead>
                <tbody>
                    {data2.map( (food)=>(
                        <tr>
                            <td>{food.id}</td>
                            <td>{food.name}</td>
                            <td>{food.description}</td>
                            <td>{food.price}</td>
                            {tryhard(food.category)}
                            
                        </tr>
                    ))
                    }
                </tbody>
            </table>
            </div>

            <div className="form">
    <form onSubmit= {handleSubmit}>
        <div className="input-container">
            <label>Restaurant Name </label>
            <input type="text" name="uname" required />
            
        </div>
        <div>
            <label>{errorMessage} </label>
        </div>
        <div className="button-container">
            <input type="submit" value="See Menu" />
        </div>
    </form>

    
    </div>
        </div>
      );
}

export default MenuU;