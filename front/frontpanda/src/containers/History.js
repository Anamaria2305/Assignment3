import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import "./Restaurant.css";
import NavbarU from './NavbarU';
function History() {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");
    const [data, setData] = useState(["No data"]);
    
    function handleSubmit(event) {
        event.preventDefault();
        var { uname} = document.forms[0];
        const params = new URLSearchParams({
            user_id: uname.value
          }).toString();
        const url =
       "http://localhost:8080/orders/userorder?" + params;
        axios.post(url,{})
        .then(response =>{
        
          setData(response.data);
           
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
const [data2, setData2] = useState(["No data"]);
function handleSubmit2(event2) {
    event2.preventDefault();

        var { uid} = document.forms[1];
        const params2 = new URLSearchParams({
            user_id: uid.value
          }).toString();
        const url =
       "http://localhost:8080/orders/active?" + params2;
        axios.post(url,{})
        .then(res =>{
        
          setData2(res.data);
           
        })
        .catch(({ res }) => { 
            if(typeof  res=="undefined")
            setMessage("Succes");

            else
            setMessage("Failed");
            
        })
  }
  
  const [data4, setData4] = useState([""]);

  const onClick2 = (orders)=>{
     
      let foods2=[]
    for(let f of orders.food)
    {
        foods2.push(f.name)
        foods2.push(", ")
    }
      foods2.pop()
      foods2.push(".")
      setData4(<div>
          <p>Order of user {orders.user.username}</p>
          <p>Foods in this order: {foods2} </p>
      </div>)
  };
  

  const [data3, setData3] = useState([""]);

  const onClick = (orders)=>{
     
      let foods=[]
    for(let f of orders.food)
    {
        foods.push(f.name)
        foods.push(", ")
    }
      foods.pop()
      foods.push(".")
      setData3(<div>
          <p>Order of user {orders.user.username}</p>
          <p>Foods in this order: {foods} </p>
      </div>)
      // foods=JSON.stringify(data[x].food, null, '\t').replace(/["{[,\}\]]/g, "")   
  };
  


    return (
        <div>
            <NavbarU />
            <div className="form">
    <form onSubmit= {handleSubmit}>
        <div className="input-container">
            <label>Your user id </label>
            <input type="number" name="uname" required />
            
        </div>
        <div>
            <label>{errorMessage} </label>
        </div>
        <div className="button-container">
            <input className="myclass" type="submit" value="See All Orders" />
        </div>
    </form>

    
    </div>
            <div>
            <h1>All Orders</h1>
             
            <table className="myAwesomeTable">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Status</th>
                        <th>View Details</th>
                      
                      
                    </tr>
                </thead>
                <tbody>
                    {data.map( (orders)=>(
                        <tr>
                            <td>{orders.id}</td>
                            <td>{orders.status}</td>
                            <td className="myclass" ><input className='myclass' type='button' value='View Details'onClick={()=>onClick(orders)}/></td>
                        </tr>
                    ))
                    }
                </tbody>
            </table>

           {data3}
            </div>
            <div className="form">
    <form onSubmit= {handleSubmit2}>
        <div className="input-container">
            <label>Your user id </label>
            <input type="number" name="uid" required />
            
        </div>
        <div>
            <label>{errorMessage} </label>
        </div>
        <div className="button-container">
            <input className="myclass" type="submit" value="Active Orders" />
        </div>
    </form>

    </div>
 
    
            <h1>Active Orders</h1>
            <table className="myAwesomeTable">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Status</th>
                        <th>View Details</th>
                      
                      
                    </tr>
                </thead>
                <tbody>
                    {data2.map( (orders)=>(
                        <tr>
                            <td>{orders.id}</td>
                            <td>{orders.status}</td>
                            <td className="myclass" ><input className='myclass' type='button' value='View Details'  onClick={()=>onClick2(orders)} /></td>
                        </tr>
                    ))
                    }
                </tbody>

            </table>
            {data4}
        </div>
      );
}

export default History;