import React from 'react';
import { Link } from 'react-router-dom';
import classes from './Nav.module.css';
import SearchInput from '../search/SearchInput';
import { useRecoilValue } from 'recoil';
import {memberNumberState} from '../../store/loginUserState'
export default function Nav() {

  const memberNumber = useRecoilValue(memberNumberState);
  console.log("memberNumberrrrrrr", memberNumber)
  return (
    <nav className={classes.navBar}>
      <Link to='/'> <img src="../../aquh-logo.png" alt="aquh-logo" className={classes.logoPng} /></Link>
      <div className={classes.navItemContainer}>
        <div className={classes.menuItems}>
          <Link to='/feed' style={{ textDecoration : "none" }}><span className={classes.navItem}>피드</span></Link>
          <Link to='/bubble' style={{ textDecoration : "none" }}><span className={classes.navItem}>버블</span></Link>
          <Link to={memberNumber !== -1 ? `/auth/${memberNumber}` : '/login' } style={{ textDecoration : "none" }}><span className={classes.navItem}>My</span></Link>
        </div>
        <div className={classes.searchContainer}>
        <SearchInput />
        <img src="../../avatar-image-circle.png" alt="search" className={classes.avatarPng} />  
        </div>
      </div>     
    </nav>
  );
}

