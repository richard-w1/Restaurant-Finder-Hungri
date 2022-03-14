import React from 'react';
import { Link } from 'react-router-dom';
import { Component } from 'react';
import SearchForm from '../SearchForm/SearchForm';
import classes from './Home.module.css'
import '../../style.css'
import Card from '../UI/Card'
// const Home = (props) => {
//     return(
//         <div>
//             You must be authorized to see this page.
//         </div>
//     )
// }

class Home extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        console.log(this.props.token);

        return (
            <div class="input"  >
                <h1 class='header'>You Must Be Hungri!</h1>
                <p className={classes.tagline}>Find Food And Grab A Bite With Friends</p>
                <img src="../public/images/FoodBanner.png" alt="MMMMMM Yummy Yummy " />

                <div className={classes.logincard}>

                    {/* <span><a class="button" href="/SearchForm" >Search Restaurants</a></span>
                    <span><a class="button" href="/CreateGroup" >Create an Invitation</a></span>
                    <span><a class="button" href="/ViewGroup" >See Your Existing Invitations</a></span> */}
                    <div className={classes.card}>
                        <p className={classes.p}>Find Amazing Restaurants Near You</p>
                        <Link to="/SearchForm">
                            <button class="button2">
                                <span>Search Restaurants</span>
                            </button>
                        </Link>
                    </div>
                    <div className={classes.card}>
                        <p className={classes.p}>Inivite Freinds and Share a Meal</p>
                        <Link to="/CreateGroup">
                            <button class="button2">
                                <span>Create an Invitation</span>
                            </button>
                        </Link>
                    </div>
                    <div className={classes.card}>
                        <p className={classes.p}>Ready to Choose Your Resturant? Check Your Group Status</p>
                        <Link to="/ViewGroup">
                            <button class="button2">
                                <span>See Your Existing Invitations</span>
                            </button>
                        </Link>

                    </div>
                </div>

            </div>
        );
    }

}
export default Home;