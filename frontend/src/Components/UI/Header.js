import React, { Component } from 'react';
import classes from './Header.module.css';
import { Redirect, Link } from 'react-router-dom';
import { addToken, deleteUser } from '../../Redux/actionCreators';

function Header() {
    return (
        <section>
            <div className={classes.header}>
                <div className={classes.logo}> 
                <a href="/home">Restaurant Tinder</a>
                </div>
                <div className={classes.navbar}>
                    <nav>
                    <a href='/CreateGroup' className={classes.navItem}>Invite Friends</a>
                    </nav> 

                </div>
            </div>
        </section>
    )
}
export default Header;





