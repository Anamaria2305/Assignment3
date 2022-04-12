import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import "./Restaurant.css";
import NavbarU from './NavbarU';
function RestaurantU() {

    const [data, setData] = useState([]);

    useEffect(()=>{
        axios.get("http://localhost:8080/restaurants/all")
            .then(res =>{
                setData(res.data);
            })
            .catch(err =>{
                console.log(err);
            })
    },[])


    return (
        <div>
            <NavbarU />
            <table className="myAwesomeTable">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Location</th>
                        <th>Delivery Zones</th>
                    </tr>
                </thead>
                <tbody>
                    {data.map((restaurant)=>(
                        <tr>
                            <td>{restaurant.id}</td>
                            <td>{restaurant.name}</td>
                            <td>{restaurant.location}</td>
                            <td>{restaurant.zones}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
      );
}

export default RestaurantU;