import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import "./Restaurant.css";
import Navbar from './Navbar';
function VOrders() {
    let navigate = useNavigate();
    const [errorMessage, setMessage]= useState("");
    const [data, setData] = useState(["No data"]);
    const [data2, setData2] = useState(["No data"]);
   
    useEffect(()=>{
        axios.get("http://localhost:8080/orders/all")
            .then(res =>{
                setData(res.data);
                setData2(res.data["user"]);
            })
            .catch(err =>{
                console.log(err);
            })
    },[])
      

function tryhard(Object){
    
    if (typeof Object === 'undefined') 

    return  <td>
    </td>
    else if(typeof Object.user === 'undefined')
    return <td>
    </td>
    else
    return  <td>{
                                
    Object.food[0]               
    }</td>
    
}



const [data3, setData3] = useState([""]);

const [data4, setData4] = useState([""]);

const onClick2 = (orders)=>{
   
    let foods=[]
  for(let f of orders.food)
  {
      foods.push(f.name)
      foods.push(", ")
  }
    foods.pop()
    foods.push(".")
    setData4(<div>
        <p>Order of user {orders.user.username}</p>
        <p>with address {orders.user.address}</p>
        <p>Foods in this order: {foods} </p>
    </div>)
    // foods=JSON.stringify(data[x].food, null, '\t').replace(/["{[,\}\]]/g, "")   
};

  
    return (
        <div>
            <Navbar />
            <div>
            
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
                            <td className="myclass" ><input className='myclass' type='button' value='View Details'  onClick={()=>onClick2(orders)}/></td>
                        </tr>
                    ))
                    }
                </tbody>
            </table>
           
            {data4}
            </div>

         
        </div>
      );
}

export default VOrders;