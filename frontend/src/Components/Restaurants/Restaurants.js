import React from "react";
import Card from '../UI/Card'
import classes from './Restaurants.module.css'

function Restaurants(props) {

    // const restaurantData = {
    //     name: "",
    //     image: "",
    //     description: "",
    //     time: "",
    //     type: "",
    //     open: ""

    //     };

    //     props.onGetRestaurantList(restaurantData);

    return(
        <Card>
        <li className={classes.item}>
            <div className={classes.image}>
                <img src="./images/ali-inay-y3aP9oo9Pjc-upslash.jpg" alt="title" />
            </div>
            <div className={classes.content}>
                <h3>TITLE</h3>
                <p>DESCRIPTION</p>
                <p>HOURS</p>
                <p>BREAKFAST</p>
                <p>OPEN</p>
            </div>
            <div className={classes.action}>
                <button>
                    Like
                </button>
            </div>
        </li>
        </Card >
    );
}

export default Restaurants;