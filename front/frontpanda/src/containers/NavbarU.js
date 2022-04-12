import React from 'react';
import {
  Nav,
  NavLink,
  Bars,
  NavMenu,
  NavBtn,
  NavBtnLink,
} from './NavbarElements';
  
const NavbarU = () => {
  return (
    <>
      <Nav>
        <Bars />
        <NavBtn>
          <NavBtnLink to='/viewmU'>View Menu</NavBtnLink>
          <NavBtnLink to='/restaurantU'>View All Restaurants</NavBtnLink>
          <NavBtnLink to='/cart'>Go to Cart</NavBtnLink>
          <NavBtnLink to='/history'>See History</NavBtnLink>
        </NavBtn>
      </Nav>
    </>
  );
};
  
export default NavbarU;