import React, { Component } from 'react';
import classes from './Header.module.css';
import { Redirect, Link } from 'react-router-dom';
import { addToken, deleteUser } from '../../Redux/actionCreators';

class Header extends Component {

    constructor(props) {
        super(props);
    }

    handleLogout = () => {
        this.props.addToken("")
        this.props.deleteUser()
    }

    render() {
        console.log("Header:");
        console.log(this.props);
        return (
            <section>
                <div className={classes.header}>
                    <div className={classes.logo}> 
                    {this.props.token !== undefined  ?

                    <Link class = 'a' to='/home'>Hungri</Link>
                    :
                    <Link class = 'a' to='/Splash'>Hungri</Link>
                    }

                    </div>
                    <div className={classes.navbar}>
                        <nav>
                        
                        <Link class = 'a' to ='/CreateGroup' className={classes.navItem}>Invite Friends</Link>
                        
                        {this.props.token.token !== undefined ?
                            <>
                            <Link to='/Splash' onClick={this.handleLogout}>Logout</Link><Redirect to='/home' />
                            </>
                        :
                        <Link to='/login'>Login</Link>
                        }

                        </nav> 
    
                    </div>
                </div>
            </section>
        )
    }

}
export default Header;





