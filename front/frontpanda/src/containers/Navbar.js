import React from 'react';
import {
  Nav,
  NavLink,
  Bars,
  NavMenu,
  NavBtn,
  NavBtnLink,
} from './NavbarElements';
  
const Navbar = () => {
  return (
    <>
      <Nav>
        <Bars />
        <NavBtn>
          <NavBtnLink to='/addrest'>Add Restaurant</NavBtnLink>
          <NavBtnLink to='/addfood'>Add Food</NavBtnLink>
          <NavBtnLink to='/viewm'>View Menu</NavBtnLink>
          <NavBtnLink to='/restaurant'>View All Restaurants</NavBtnLink>
          <NavBtnLink to='/crmenu'>Create Menu</NavBtnLink>
          <NavBtnLink to='/ordersv'>View Orders</NavBtnLink>
          <NavBtnLink to='/accdec'>Accept/Decline Orders</NavBtnLink>
          <NavBtnLink to='/sorders'>Filter Status</NavBtnLink>
        </NavBtn>
      </Nav>
    </>
  );
};
  
export default Navbar;