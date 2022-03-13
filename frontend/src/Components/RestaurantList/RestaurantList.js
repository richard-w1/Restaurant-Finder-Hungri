import Restaurants from '../Restaurants/Restaurants'
import classes from './RestaurantList.module.css';


function RestaurantList(props) {

    return (
        <ul className={classes.list}>
            {props.restaurants.map((restaurants) => (
                <Restaurants
                    key={restaurants.location}
                    id={restaurants.id}
                    image={restaurants.image}
                    name={restaurants.name}
                    description={restaurants.description}
                    time={restaurants.hoursOfOperation}
                    type={restaurants.type}
                    open={restaurants.open}

                />
            ))}
        </ul>
    );
}

export default RestaurantList;
