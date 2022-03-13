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

                    <Link class = 'a' to='/home'>Hungri.</Link>
                    :
                    <Link class = 'a' to='/Splash'>Hungri.</Link>
                    }

                    </div>
                    <div className={classes.navbar}>
                        <nav>

                        {this.props.token.token !== undefined ?
                            <>
                            <Link className = 'b' to = '/Splash' onClick={this.handleLogout}> &nbsp; Logout</Link><Redirect to='/home' />
                            </>
                        :
                        <Link className = 'b' to = '/login'> &nbsp; Login</Link>
                        }

                        </nav> 
    
                    </div>
                </div>
            </section>
        )
    }

}
export default Header;





