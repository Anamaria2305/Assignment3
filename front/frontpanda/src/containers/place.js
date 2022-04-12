import React, { Component, useEffect, useState } from 'react';
import { useParams, useNavigate, Link} from 'react-router-dom';
import axios from 'axios'
import Navbar from './Navbar';
import {useLocation} from 'react-router-dom';
function Place() {
    const place = useLocation();


    return (
        <div>
            <Navbar />
            <h1> Admin Profile</h1>
            <h2> My id is </h2>
            <div>{place.state.price}</div>
        </div>
      );
}

export default Place;